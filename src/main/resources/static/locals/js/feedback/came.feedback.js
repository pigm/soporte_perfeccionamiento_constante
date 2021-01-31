'use strict';
var came = came || {};

came.feedback = (function () {
    var readOnly = false;
    var popoverPaso = null;
    var availableSupers = [];

    var urls = {
        api: {
            registra_feedback: came.contexto + "/feedback/registra-feedback",
            detalle_feedback: came.contexto + "/feedback/detalle-feedback",

            getFeedback: came.contexto + "/feedback/getFeedback",
            getFeedBackOthers: came.contexto + "/feedback/getFeedBackOthers",
            getProvincias: came.contexto + "/feedback/getProvincias",
            getSupUserNames: came.contexto + "/feedback/getSupUserNames",
            getSupervisorData: came.contexto + "/feedback/getSupervisorData",
            setPlanificarFeedback: came.contexto + "/feedback/setPlanificarFeedback",
            setFeedback: came.contexto + "/feedback/setFeedback",
        }
    };

    var getIds = {
        button_back: "#button-back",
        panel_search: "#panel-search",
        panel_search_result: "#panel-search-result",

        selector_region: "#selector-region",
        selector_deprov: "#selector-deprov",
        selector_jefe_tecnico: "#selector-jefe-tecnico",
        selector_semestre: "#selector-semestre",
        button_search: "#button-search",
        count_feedback: "#count-feedback",
        table_feedback: "#table-feedback",

        button_close_popover: ".button-close-popover",

        button_primera_planificacion: "#button-primera-planificacion",
        modal_primera_planificacion: "#modal-primera-planificacion",
        button_planificar_save: "#button-planificar-save",

        input_supervisor_username: "#input-supervisor-username",
        planificar_supervisor_username: "#planificar-supervisor-username",
        planificar_supervisor_name: "#planificar-supervisor-name",
        planificar_supervisor_region: "#planificar-supervisor-region",
        planificar_supervisor_deprov: "#planificar-supervisor-deprov",
        planificar_frecuencia: "#planificar-frecuencia",
        planificar_fecha: "#planificar-fecha",
        planificar_semestre: "#planificar-semestre",

        button_planificar_si: "#button-planificar-si",

        registro_feedback_numero: "#registro-feedback-numero",
        registro_feedback_fecha_registro: "#registro-feedback-fecha-registro",
        registro_feedback_hora_registro: "#registro-feedback-hora-registro",
        registro_feedback_momento_asesoria: "#registro-feedback-momento-asesoria",
        registro_feedback_fecha_registro_jefe: "#registro-feedback-fecha-registro-jefe",
        registro_feedback_practica_abordada: "#registro-feedback-practica-abordada",
        registro_feedback_acuerdos: "#registro-feedback-acuerdos",
        registro_feedback_accion: "#registro-feedback-accion",
        registro_feedback_observaciones: "#registro-feedback-observaciones",
        registro_feedback_fecha_proxima: "#registro-feedback-fecha-proxima",

        registro_feedback_button_cancelar: "#registro-feedback-button-cancelar",
        registro_feedback_button_guardar: "#registro-feedback-button-guardar",

        exporta_pdf: ".exporta-pdf",
        exporta_pdf_button: ".exporta-pdf-button"

    };

    var initListener = function () {
        $(document)
            .on("change", getIds.selector_region, function () {
                getProvincias(this);
            })
            .on("click", getIds.button_search, function () {
                getFeedback(this);
            })
            .on("click", getIds.button_primera_planificacion, function () {
                showPrimeraPlanificacion(this);
            })
            .on("click", getIds.button_planificar_si, function () {
                setPlanificacion(this);
            })
            .on("click", getIds.button_planificar_save, function () {
                confirmPrimeraPlanificacion(this);
            })
            .on("click", getIds.button_close_popover, function () {
                closePopover(this);
            })
            .on("click", getIds.registro_feedback_button_guardar, function () {
                setRegistraFeedback(this);
            })
            .on("click", getIds.button_back, function () {
                goBack();
            })
            .on("click", getIds.exporta_pdf, function () {
                getPDF();
            })
            .on("click", getIds.exporta_pdf_button, function () {
                $(getIds.exporta_pdf).click();
            })
            .on("change", getIds.input_supervisor_username, function () {
                getSuperByUserName(this);
            })
            .on("click", "#button-popover-test", function () {
                // $("#button-popover-test").popover();
            })
    }

    function goBack() {
        window.history.back();
    }

    function showPrimeraPlanificacion(sender) {
        if (readOnly) {
            came.notify.showError("Retroalimentación", "No tienes permitido planificar una retroalimentación.");
        } else if (!came.env.getEnvUser().currentPeriod) {
            came.notify.showError("Retroalimentación", "No existe un periodo para crear o modificar retroalimentación.");
        } else {
            $(getIds.modal_primera_planificacion).modal('show');
        }
    }

    function getUserNamesSuper() {

        var usuarioPaso = came.env.getEnvUser();

        $.ajax({
            type: "GET",
            url: urls.api.getSupUserNames,
            dataType: "json",
            async: false,
            contentType: 'application/json',
            data: { idRegion: usuarioPaso.idRegion, idDeprov: usuarioPaso.idDeprov }
        }).done(function (response) {
            console.info("response done", response);
            availableSupers = response;
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            return false;
        });
    }

    function getSuperByUserName(sender) {
        if (availableSupers.indexOf($(sender).val()) >= 0) {
            if ($(sender).val() != '') {
                $.ajax({
                    type: "GET",
                    url: urls.api.getSupervisorData,
                    dataType: "json",
                    async: false,
                    contentType: 'application/json',
                    data: { userName: $(sender).val() }
                }).done(function (response) {
                    console.info("response done", response);

                    $(getIds.planificar_supervisor_username).attr('data-usuarioId', response.usuarioId);
                    $(getIds.planificar_supervisor_username).text(response.userName);
                    $(getIds.planificar_supervisor_name).text(response.nombre);
                    $(getIds.planificar_supervisor_region).text(response.region);
                    $(getIds.planificar_supervisor_deprov).text(response.deprov);

                    $(sender).val('');
                }).fail(function (jqXHR, textStatus) {
                    console.error("response fail", jqXHR);
                    return false;
                });
            }
        } else {
            came.notify.showError("Suplencia", "El usuario indicado no esta en la lista de supervisores activos.");
        }
    }


    function confirmPrimeraPlanificacion(sender) {
        console.log(sender);

        if (popoverPaso) {
            $(popoverPaso).popover('hide');
        }

        popoverPaso = $(sender).popover({
            placement: 'top',
            container: 'body',
            // trigger: 'focus',
            html: true,
            sanitize: false,
            content:
                '<div class="">' +
                '<a id="button-planificar-no" class="btn btn-sm btn-secondary col-4 float-left mb-2 button-close-popover" href="#">No</a>' +
                '<a id="button-planificar-si" class="btn btn-sm btn-primary col-4 float-right" href="#">Si</a>' +
                '</div>'

        });

        popoverPaso.popover('show');
    }

    function closePopover(sender) {
        console.log(sender);
        $(popoverPaso).popover('hide');
    }

    function setPlanificacion(sender) {
        var isValid = true;

        var supervisor = $(getIds.planificar_supervisor_username).text();
        if (supervisor === '') {
            came.main.setInvalidControl($(getIds.input_supervisor_username));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.input_supervisor_username));
        }

        var frecuencia = $(getIds.planificar_frecuencia).val();
        if (frecuencia === '') {
            came.main.setInvalidControl($(getIds.planificar_frecuencia));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.planificar_frecuencia));
        }

        var fecha = $(getIds.planificar_fecha).val();
        if (fecha === '') {
            came.main.setInvalidControl($(getIds.planificar_fecha));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.planificar_fecha));
        }

        var semestre = $(getIds.planificar_semestre).attr('data-semestre');

        if (isValid) {
            $.ajax({
                type: "POST",
                dataType: "json",
                async: false,
                contentType: 'application/json',
                url: urls.api.setPlanificarFeedback,
                data: JSON.stringify({ supervisor: supervisor, frecuenciaId: frecuencia, semestre: semestre, fechaPlanificacion: fecha })
            }).done(function (msg) {
                console.log("response done", msg);

                came.notify.showSucces("Sesion", "Su solicitud se ha procesado correctamente.");
                // cierro el modal.         
                location.reload();
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                // TODO: aqui se debe agregar un mensaje en caso de error.
                came.notify.showError("Sesion", "Su solicitud se ha procesado con errores.");
                return false;
            });
        }

    }

    function setRegistraFeedback(sender) {
        console.log(sender);
        var isValid = true;

        var idFeedback = $(sender).attr('data-feedback-id');

        var fechaRegistroSesion = $(getIds.registro_feedback_fecha_registro).val() + " " + $(getIds.registro_feedback_hora_registro).val();
        console.log($(getIds.registro_feedback_fecha_registro).val());
        console.log(fechaRegistroSesion);

        if ($(getIds.registro_feedback_fecha_registro).val() === '') {
            came.main.setInvalidControl($(getIds.registro_feedback_fecha_registro));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.registro_feedback_fecha_registro));
        }

        if ($(getIds.registro_feedback_hora_registro).val() === '') {
            came.main.setInvalidControl($(getIds.registro_feedback_hora_registro));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.registro_feedback_hora_registro));
        }

        var idMomentoAsesoria = $(getIds.registro_feedback_momento_asesoria).val();
        if ($(getIds.registro_feedback_momento_asesoria).val() === '') {
            came.main.setInvalidControl($(getIds.registro_feedback_momento_asesoria));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.registro_feedback_momento_asesoria));
        }

        var fechaRegistroJefe = $(getIds.registro_feedback_fecha_registro_jefe).val();

        var practicaAbordada = $(getIds.registro_feedback_practica_abordada).val();
        if ($(getIds.registro_feedback_practica_abordada).val() === '') {
            came.main.setInvalidControl($(getIds.registro_feedback_practica_abordada));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.registro_feedback_practica_abordada));
        }

        var acuerdos = $(getIds.registro_feedback_acuerdos).val();
        if ($(getIds.registro_feedback_acuerdos).val() === '') {
            came.main.setInvalidControl($(getIds.registro_feedback_acuerdos));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.registro_feedback_acuerdos));
        }

        var acciones = $(getIds.registro_feedback_accion).val();
        if ($(getIds.registro_feedback_accion).val() === '') {
            came.main.setInvalidControl($(getIds.registro_feedback_accion));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.registro_feedback_accion));
        }

        var observaciones = $(getIds.registro_feedback_observaciones).val();
        if ($(getIds.registro_feedback_observaciones).val() === '') {
            came.main.setInvalidControl($(getIds.registro_feedback_observaciones));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.registro_feedback_observaciones));
        }

        var fechaProxima = $(getIds.registro_feedback_fecha_proxima).val();
        if ($(getIds.registro_feedback_fecha_proxima).val() === '') {
            came.main.setInvalidControl($(getIds.registro_feedback_fecha_proxima));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.registro_feedback_fecha_proxima));
        }

        if (isValid) {
            $.ajax({
                type: "POST",
                url: urls.api.setFeedback,
                dataType: "json",
                contentType: 'application/json',
                data: JSON.stringify({
                    idFeedback: idFeedback,
                    numero: $(getIds.registro_feedback_numero).text(),
                    fechaRegistroSesion: fechaRegistroSesion,
                    idMomentoAsesoria: idMomentoAsesoria,
                    fechaRegistroJefeTecnico: fechaRegistroJefe,
                    practicaAbordada: practicaAbordada,
                    acuerdos: acuerdos,
                    acciones: acciones,
                    observaciones: observaciones,
                    fechaProxima: fechaProxima
                })
            }).done(function (response) {
                console.log("response done", response);
                // cierro el modal.         
                came.notify.showSucces("Retroalimentación", "Su solicitud se ha procesado correctamente.");
                //cierro el modal.         
                var link = document.createElement('a');
                link.href = (urls.api.detalle_feedback + "?idFeedback=" + response.idFeedback);
                console.log(link.href);
                link.click();

            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                // TODO: aqui se debe agregar un mensaje en caso de error.
                came.notify.showError("Retroalimentación", "Su solicitud se ha procesado con errores.");
                return false;
            });
        }
    }

    function getProvincias(sender) {

        $(getIds.selector_deprov + " option").each(function () {
            $(this).remove();
        });

        var idRegion = $(sender).val();
        if (idRegion !== '') {
            $.ajax({
                type: "GET",
                url: urls.api.getProvincias,
                dataType: "json",
                async: false,
                //contentType: 'application/json',
                data: { idRegion: idRegion }
            }).done(function (response) {
                console.log("response done", JSON.stringify(response));
                $(getIds.selector_deprov).append($('<option selected>').val('').text('Seleccionar'));
                for (var i = 0; i < response.length; i++) {
                    $(getIds.selector_deprov).append($('<option>').val(response[i].value).text(response[i].displayText));
                }
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
        }
    }

    function getFeedback(sender) {
        //came.main.showLoading();
        $.ajax({
            type: "GET",
            url: urls.api.getFeedback,
            //dataType: "json",
            // async: false,
            contentType: 'application/json',
            data: jQuery.param({
                idRegion: $(getIds.selector_region).val(),
                idDeprov: $(getIds.selector_deprov).val(),
                jefeTecnico: $(getIds.selector_jefe_tecnico).val(),
                semestre: $(getIds.selector_semestre).val()
            })
        }).done(function (response) {
            if (typeof (response) !== "string") {
                $(getIds.count_feedback).text(response.length);
                $(getIds.table_feedback + ' tbody tr').remove();
                var tablePaso = $(getIds.table_feedback);
                loadFeedback(tablePaso, response);

                $(getIds.panel_search).css("display", "none");
                $(getIds.panel_search_result).css("display", "");

                //came.main.hideLoading();
            } else {
                came.notify.showError("Asignar supervisores", "Su solicitud se ha procesado con errores.");
            }
        });
    }

    function loadFeedback(table, elements) {
        console.log(elements);
        if (elements) {
            for (var i = 0; i < elements.length; i++) {
                var row = $('<tr data-id-feedback="' + elements[i].idFeedback + '"></tr>');
                row.append($('<td class="details-control"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-down" viewBox="0 0 16 16">' +
                    '<path d = "M3.204 5h9.592L8 10.481 3.204 5zm-.753.659l4.796 5.48a1 1 0 0 0 1.506 0l4.796-5.48c.566-.647.106-1.659-.753-1.659H3.204a1 1 0 0 0-.753 1.659z" /></svg ></td>'));
                row.append($('<td></td>').append($('<span />').text(elements[i].supervisor).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].jefeTecnico).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].region).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].deprov).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].semestre).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].numero).html()));
                row.append($('<td></td>').append($('<span />').text(came.main.formatDate(elements[i].fechaPlanificada)).html()));

                if (elements[i].fechaRealizada != null) {
                    row.append($('<td></td>').append($('<span />').text(came.main.formatDate(elements[i].fechaRealizada)).html()));
                } else {
                    if (readOnly) {
                        row.append($('<td class="text-center">-</td>'));
                    } else {
                        row.append($('<td><div class="text-center"><a href="' + urls.api.registra_feedback + "?idFeedback=" + elements[i].idFeedback + '" class="badge badge-pill badge-primary">Registrar</a></div></td>'));
                    }
                }

                $(table).append(row);
            }

            came.main.initList(table);

            $(getIds.table_feedback + ' tbody').on('click', 'td.details-control', function () {
                var tr = $(this).closest('tr');
                console.log(tr);
                var row1 = $(table).DataTable().row(tr);

                if (row1.child.isShown()) {
                    // This row is already open - close it
                    row1.child.hide();
                    tr.removeClass('shown');
                }
                else {
                    // Open this row
                    row1.child(format($(tr).attr('data-id-feedback'))).show();
                    tr.addClass('shown');
                }
            });
        }
    }

    function format(idFeedback) {
        var result = '';
        $.ajax({
            type: "GET",
            url: urls.api.getFeedBackOthers,
            //dataType: "json",
            async: false,
            contentType: 'application/json',
            data: jQuery.param({
                idFeedback: idFeedback
            })
        }).done(function (response) {
            console.log("response done", JSON.stringify(response));
            result = '<table style="width: 100%;">';
            for (var i = 0; i < response.length; i++) {
                result += '<tr>';
                result += '<td><a href="' + urls.api.detalle_feedback + "?idFeedback=" + response[i].idFeedback + '">' + response[i].supervisor + '</a></td>';
                result += '<td>' + response[i].jefeTecnico + '</td>';
                result += '<td>' + response[i].region + '</td>';
                result += '<td>' + response[i].deprov + '</td>';
                result += '<td>' + response[i].semestre + '</td>';
                result += '<td>' + response[i].numero + '</td>';
                result += '<td>' + came.main.formatDate(response[i].fechaPlanificada) + '</td>';
                result += '<td>' + came.main.formatDate(response[i].fechaRealizada) + '</td>';
                result += '</tr>'
            }

            result += '</table>';

        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
            return false;
        });

        return result;
    }

    function initAutoComplete() {
        console.log(typeof ($(getIds.input_supervisor_username).val()) === 'string');
        if (typeof ($(getIds.input_supervisor_username).val()) === 'string') {
            $(getIds.input_supervisor_username).autocomplete({
                source: availableSupers.map((x) => x)
            });
        }
    }

    function topFunction() {
        document.body.scrollTop = 0; // For Safari
        document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
    }

    function getPDF() {
        topFunction();
        console.log('getPDF');
        var HTML_Width = $(".canvas_div_pdf").width() + 200;
        var HTML_Height = $(".canvas_div_pdf").height();
        var top_left_margin = 15;
        var PDF_Width = HTML_Width + (top_left_margin * 2);
        var PDF_Height = (PDF_Width * 1.5) + (top_left_margin * 2);
        var canvas_image_width = HTML_Width;
        var canvas_image_height = HTML_Height;

        var totalPDFPages = Math.ceil(HTML_Height / PDF_Height) - 1;


        html2canvas($(".canvas_div_pdf")[0], { allowTaint: true }).then(function (canvas) {
            canvas.getContext('2d');

            console.log(canvas.height + "  " + canvas.width);


            var imgData = canvas.toDataURL("image/jpeg", 1.0);
            var pdf = new jsPDF('p', 'pt', [PDF_Width, PDF_Height]);
            pdf.addImage(imgData, 'JPG', top_left_margin, top_left_margin, canvas_image_width, canvas_image_height);


            for (var i = 1; i <= totalPDFPages; i++) {
                pdf.addPage(PDF_Width, PDF_Height);
                pdf.addImage(imgData, 'JPG', top_left_margin, -(PDF_Height * i) + (top_left_margin * 4), canvas_image_width, canvas_image_height);
            }

            pdf.save("detalle-retroalimentacion.pdf");
        });
    }

    function selectRegionDeprov() {
        var usuarioPaso = came.env.getEnvUser();
        if (usuarioPaso) {
            $(getIds.selector_region).val(usuarioPaso.idRegion);
            $(getIds.selector_region).change();
            $(getIds.selector_deprov).val(usuarioPaso.idDeprov);
            $(getIds.selector_deprov).change();

            if (usuarioPaso.nombrePerfilNivel === "regional") {
                $(getIds.selector_region).prop('disabled', 'disabled');
            } else if (usuarioPaso.nombrePerfilNivel === "provincial") {
                $(getIds.selector_region).prop('disabled', 'disabled');
                $(getIds.selector_deprov).prop('disabled', 'disabled');
            } else {
                $(getIds.selector_region).prop('disabled', false);
                $(getIds.selector_deprov).prop('disabled', false);
            }
            console.log(usuarioPaso.idPerfil);
            if (usuarioPaso.idPerfil == "2416264829774857257") {
                $(getIds.selector_jefe_tecnico).val(usuarioPaso.userName);
                $(getIds.selector_jefe_tecnico).prop('disabled', 'disabled');
            }
        }
    }

    function setViewAcces() {
        var menuPso = came.storage.getSessionStorage('menu');
        var subMenuAdmin = menuPso.find(e => e.idModule == '2416198290916770827');
        console.log(subMenuAdmin);
        if (subMenuAdmin) {
            var feedbackPaso = subMenuAdmin.subMenu.find(e => e.idSubModule == '2416254586126861337');
            console.log(feedbackPaso);
            readOnly = feedbackPaso.readOnly;
            if (readOnly) {
                $("#usuario-content-detail :input").prop("disabled", true);
                $(getIds.setUser).prop("disabled", true);
                $("#habilitado").prop("disabled", true);
            }
        }
    }

    function initPopover() {
        $('[data-toggle="popover"]').popover({
            // placement: 'top',
            container: 'body',
            // trigger: 'focus',
            html: true,
            sanitize: false
        });
    }

    function init() {
        console.info('came.feedback.init');
        setViewAcces();
        initListener();
        selectRegionDeprov();
        getUserNamesSuper();
        initAutoComplete();
        initPopover();
    }

    return {
        init: init
    };
})();

$(document).ready(function () {
    came.feedback.init();
});