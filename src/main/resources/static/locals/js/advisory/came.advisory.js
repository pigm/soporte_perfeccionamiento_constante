'use strict';
var came = came || {};

came.advisory = (function () {
    var readOnly = false;
    var popoverPaso = null;
    var urls = {
        api: {
            getProvincias: came.contexto + "/advisory/getProvincias",
            getRbd: came.contexto + "/advisory/getRbd",
            getComunas: came.contexto + "/advisory/getComunas",
            isValidStartDate: came.contexto + "/advisory/isValidStartDate",
            exportToExcel: came.contexto + "/advisory/export/excel",
            setAdvisory: came.contexto + "/advisory/setAdvisory",
            getAdvisory: came.contexto + "/advisory/getAdvisory",
            setStatusAdvisory: came.contexto + "/advisory/setStatusAdvisory",
            getAdvisoryDetail: came.contexto + "/advisory/getAdvisoryDetail",
        }
    };

    var getIds = {
        panel_search: "#panel-search",
        panel_search_result: "#panel-search-result",
        table_advisory: "#table-advisory",
        count_advisory: "#count-advisory",

        selector_region: "#selector-region",
        selector_deprov: "#selector-deprov",
        selector_rbd: "#selector-rbd",
        input_fecha_rango: "#input-fecha-rango",

        button_new_advisory: "#button-new-advisory",
        find_advisory: "#find-advisory",
        advisory_find_again: "#advisory-find-again",
        export_excel_advisory: "#export-excel-advisory",

        modal_new_advisory: "#modal-new-advisory",
        modal_detail_advisory: "#modal-detail-advisory",

        advisory_fecha_inicio: "#advisory-fecha-inicio",
        advisory_selector_region: "#advisory-selector-region",
        advisory_selector_deprov: "#advisory-selector-deprov",
        advisory_selector_comuna: "#advisory-selector-comuna",

        advisory_button_save: "#advisory-button-save",
        advisory_set_status: ".advisory-set-status",
        advisory_detail: ".advisory-detail",
        advisory_button_editar: "#advisory-button-editar",

        advisory_detalle_fecha_creacion: "#advisory-detalle-fecha-creacion",
        advisory_detalle_selector_region: "#advisory-detalle-selector-region",
        advisory_detalle_selector_deprov: "#advisory-detalle-selector-deprov",
        advisory_detalle_selector_comuna: "#advisory-detalle-selector-comuna",
        advisory_detalle_selector_rbd: "#advisory-detalle-selector-rbd",
        advisory_detalle_establecimiento: "#advisory-detalle-establecimiento",

        asesoria_lista: "#asesoria-lista",
        input_establecimiento_search: "#input-establecimiento-search",
        button_close_popover: ".button-close-popover"
    }

    var initListener = function () {
        $(document)
            .on("change", getIds.selector_region, function () {
                getProvincias(this);
            })
            .on("change", getIds.selector_deprov, function () {
                getRbd(this);
            })
            .on("change", getIds.advisory_fecha_inicio, function () {
                // validarFechaInicio(this);
            })
            .on("change", getIds.advisory_selector_region, function () {
                getProvinciasAdvisory(this);
            })
            .on("change", getIds.advisory_selector_deprov, function () {
                getComunasAdvisory(this);
                getRbdAdvisory(this);
            })
            .on("change", getIds.advisory_selector_comuna, function () {
                getRbdAdvisory(this);
            })
            .on("change", getIds.advisory_set_status, function () {
                setStatusAdvisory(this);
            })
            .on("keyup", getIds.input_establecimiento_search, function () {
                searchEstablecimientos(this);
            })
            .on("change", getIds.input_establecimiento_search, function () {
                searchEstablecimientos(this);
            })
            .on("click", getIds.advisory_button_save, function () {
                setAdvisory(this);
            })
            .on("click", getIds.find_advisory, function () {
                getAdvisory(this);
            })
            .on("click", getIds.advisory_find_again, function () {
                findAdvisoryAgain(this);
            })
            .on("click", getIds.button_new_advisory, function () {
                showNewAdvisory(this);
            })
            .on("click", getIds.advisory_detail, function () {
                showDetailAdvisory(this);
            })
            .on("click", getIds.advisory_button_editar, function () {
                showEditAdvisory(this);
            })
            .on("click", getIds.export_excel_advisory, function () {
                getExcel(this);
            })
            .on("hidden.bs.modal", getIds.modal_new_advisory, function () {
                clearModaladvisory(this);
            })
            .on("hidden.bs.modal", getIds.modal_detail_advisory, function () {
                clearModaladvisoryDetail(this);
            })
            .on("click", getIds.button_close_popover, function () {
                closePopover(this);
            });
    }

    function clearModaladvisory(sender) {
        $(getIds.modal_new_advisory).attr('data-id-asesoria-directa', '');
        $(getIds.advisory_fecha_inicio).val('');
        $(getIds.advisory_selector_region).val('');
        $(getIds.advisory_selector_deprov).val('');
        $(getIds.advisory_selector_comuna).val('');
        clearEstablecimientos(sender);
    }

    function clearModaladvisoryDetail(sender) {
        $(getIds.advisory_button_editar).attr('data-id-asesoria-directa', '');
        $(getIds.advisory_detalle_fecha_creacion).val('');
        $(getIds.advisory_detalle_selector_region).text('');
        $(getIds.advisory_detalle_selector_deprov).text('');
        $(getIds.advisory_detalle_selector_comuna).text('');
        $(getIds.advisory_detalle_selector_rbd).text('');
        $(getIds.advisory_detalle_establecimiento).text('');
    }

    function showNewAdvisory(sender) {
        if (readOnly) {
            came.notify.showError("Asesorías directa", "No tienes permitido crear o modificar asesorías directa.");
        } else {
            if (isValidDate()) {
                var usuarioPaso = came.env.getEnvUser();
                if (usuarioPaso) {
                    $(getIds.advisory_selector_region).val(usuarioPaso.idRegion);
                    $(getIds.advisory_selector_region).change();
                    $(getIds.advisory_selector_deprov).val(usuarioPaso.idDeprov);
                    $(getIds.advisory_selector_deprov).change();

                    if (usuarioPaso.nombrePerfilNivel === "regional") {
                        $(getIds.advisory_selector_region).prop('disabled', 'disabled');
                    } else if (usuarioPaso.nombrePerfilNivel === "provincial") {
                        $(getIds.advisory_selector_region).prop('disabled', 'disabled');
                        $(getIds.advisory_selector_deprov).prop('disabled', 'disabled');
                    } else {
                        $(getIds.advisory_selector_region).prop('disabled', false);
                        $(getIds.advisory_selector_deprov).prop('disabled', false);
                    }
                }
                $(getIds.modal_new_advisory).modal('show');
            }
            else {
                showOutOfRangue(sender);
            }
        }
    }

    function showDetailAdvisory(sender) {
        console.log(sender);
        var idAsesoriaDirecta = $(sender).attr('data-id-advisory');
        if (idAsesoriaDirecta != null) {
            $.ajax({
                type: "GET",
                url: urls.api.getAdvisoryDetail,
                dataType: "json",
                async: false,
                contentType: 'application/json',
                data: { idAsesoriaDirecta: idAsesoriaDirecta }
            }).done(function (response) {
                console.log("response done", JSON.stringify(response));
                $(getIds.advisory_button_editar).attr('data-id-asesoria-directa', idAsesoriaDirecta);
                console.log(came.main.formatDate(response.fechaCreacion));
                $(getIds.advisory_detalle_fecha_creacion).val(came.main.formatDate(response.fechaCreacion));
                $(getIds.advisory_detalle_selector_region).text(response.region);
                $(getIds.advisory_detalle_selector_deprov).text(response.deprov);
                $(getIds.advisory_detalle_selector_comuna).text(response.comuna);
                $(getIds.advisory_detalle_selector_rbd).text(response.rbd.split("-")[0]);
                $(getIds.advisory_detalle_establecimiento).text(response.rbd.split("-")[1]);

                $(getIds.modal_detail_advisory).modal('show');
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
        }
    }

    function showEditAdvisory(sender) {
        console.log(sender);
        var idAsesoriaDirecta = $(sender).attr('data-id-asesoria-directa');
        if (idAsesoriaDirecta != null) {
            $.ajax({
                type: "GET",
                url: urls.api.getAdvisoryDetail,
                dataType: "json",
                async: false,
                contentType: 'application/json',
                data: { idAsesoriaDirecta: idAsesoriaDirecta }
            }).done(function (response) {
                console.log("response done", JSON.stringify(response));
                $(getIds.modal_new_advisory).attr('data-id-asesoria-directa', idAsesoriaDirecta);
                console.log(came.main.formatDate(response.fechaCreacion));
                $(getIds.advisory_fecha_inicio).val(response.fechaCreacion.split('T')[0]);
                $(getIds.advisory_selector_region).val(response.idRegion);
                $(getIds.advisory_selector_region).change();
                $(getIds.advisory_selector_deprov).val(response.idDeprov);
                $(getIds.advisory_selector_deprov).change();
                $(getIds.advisory_selector_comuna).val(response.idComuna);
                $(getIds.advisory_selector_comuna).change();
                $(getIds.input_establecimiento_search).val(response.rbd);
                $(getIds.input_establecimiento_search).change();

                setSelectedEstablecimiento(response.rbd);

                $(getIds.modal_new_advisory).modal('show');
                $(getIds.modal_detail_advisory).modal('hide');
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
        }
    }

    // function validarFechaInicio(sender) {
    //     var fechaInicio = $(sender).val();
    //     console.log(fechaInicio);
    //     if (fechaInicio !== '') {
    //         $.ajax({
    //             type: "GET",
    //             url: urls.api.isValidStartDate,
    //             dataType: "json",
    //             async: false,
    //             //contentType: 'application/json',
    //             data: { startDate: fechaInicio }
    //         }).done(function (response) {
    //             console.log("response done", JSON.stringify(response));
    //             if (response) {
    //                 came.main.setValidControl($(sender));
    //             } else {
    //                 came.main.setInvalidControl($(sender));
    //             }
    //             console.log(came.main.isControlValid(sender));
    //         }).fail(function (jqXHR, textStatus) {
    //             console.error("response fail", jqXHR);
    //             came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
    //             return false;
    //         });
    //     }
    // }

    function isValidDate() {
        var result = false;

        var d = new Date();
        $.ajax({
            type: "GET",
            url: urls.api.isValidStartDate,
            dataType: "json",
            async: false,
            //contentType: 'application/json',
            data: { startDate: d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate() }
        }).done(function (response) {
            console.log(response);
            result = response;
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
            return false;
        });

        return result;
    }

    function showOutOfRangue(sender) {
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
                '<div class="text-center">' +
                '<a class="btn btn-sm btn-secondary mb-2 button-close-popover" href="#">Aceptar</a>' +
                '</div>'

        });

        popoverPaso.popover('show');
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
        } else {
            $(getIds.selector_deprov).append($('<option selected>').val('').text('Seleccionar'));
        }
    }

    function getRbd(sender) {

        $(getIds.selector_rbd + " option").each(function () {
            $(this).remove();
        });

        var regionId = $(getIds.selector_region).val();
        var deprovId = $(sender).val();

        console.log({ regionId: regionId, deprovId: deprovId });

        if (regionId && deprovId) {
            $.ajax({
                type: "GET",
                url: urls.api.getRbd,
                dataType: "json",
                async: true,
                //contentType: 'application/json',
                data: { regionId: regionId, deprovId: deprovId }
            }).done(function (response) {
                console.log("response done", JSON.stringify(response));
                $(getIds.selector_rbd).append($('<option selected>').val('').text('Seleccionar'));
                for (var i = 0; i < response.length; i++) {
                    $(getIds.selector_rbd).append($('<option>').val(response[i].value).text(response[i].displayText));
                }
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
        }
    }

    function getProvinciasAdvisory(sender) {

        $(getIds.advisory_selector_deprov + " option").each(function () {
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
                $(getIds.advisory_selector_deprov).append($('<option selected>').val('').text('Seleccionar'));
                for (var i = 0; i < response.length; i++) {
                    $(getIds.advisory_selector_deprov).append($('<option>').val(response[i].value).text(response[i].displayText));
                }
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
        } else {
            $(getIds.advisory_selector_deprov).append($('<option selected>').val('').text('Seleccionar'));
        }
    }

    function getComunasAdvisory(sender) {

        $(getIds.advisory_selector_comuna + " option").each(function () {
            $(this).remove();
        });

        var idDeprov = $(sender).val();
        if (idDeprov !== '') {
            $.ajax({
                type: "GET",
                url: urls.api.getComunas,
                dataType: "json",
                async: false,
                //contentType: 'application/json',
                data: { idDeprov: idDeprov }
            }).done(function (response) {
                console.log("response done", JSON.stringify(response));
                $(getIds.advisory_selector_comuna).append($('<option selected>').val('').text('Seleccionar'));
                for (var i = 0; i < response.length; i++) {
                    $(getIds.advisory_selector_comuna).append($('<option>').val(response[i].value).text(response[i].displayText));
                }
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
        } else {
            $(getIds.advisory_selector_comuna).append($('<option selected>').val('').text('Seleccionar'));
        }
    }

    function getRbdAdvisory(sender) {

        $(getIds.asesoria_lista).find('.establecimiento').each((index, element) => {
            console.log(element);
            $(element).remove();
        });

        var regionId = $(getIds.advisory_selector_region).val();
        var deprovId = $(getIds.advisory_selector_deprov).val();
        var comunaId = $(getIds.advisory_selector_comuna).val();

        console.log({ regionId: regionId, deprovId: deprovId, comunaId: comunaId });

        if (regionId && deprovId) {
            $.ajax({
                type: "GET",
                url: urls.api.getRbd,
                dataType: "json",
                async: false,
                //contentType: 'application/json',
                data: { regionId: regionId, deprovId: deprovId, comunaId: comunaId }
            }).done(function (response) {
                console.log("response done", JSON.stringify(response));

                for (var i = 0; i < response.length; i++) {
                    $(getIds.asesoria_lista)
                        .append('<div class="custom-control custom-radio establecimiento">' +
                            '<input type = "checkbox" id = "' + response[i].value + '" name = "customRadio" class= "custom-control-input" style="z-index: 99999;">' +
                            '<label class="custom-control-label" for="customRadio1">' + response[i].displayText + '</label>' +
                            '</div >');
                }



            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
        }
    }

    function searchEstablecimientos(sender) {
        console.log(sender);
        var text = $(sender).val().toLowerCase();

        $(getIds.asesoria_lista).children().each((index, element) => {
            $(element).find("label").each((i, e) => {
                if ($(e).is("label")) {
                    if ($(e).text().toLowerCase().includes(text)) {
                        $(element).css("display", "");
                    } else {
                        $(element).css("display", "none");
                    }
                }
            });

            $(element).find("input").each((i, e) => {
                if ($(e).is("input")) {
                    $(e).prop('checked', '');
                }
            });
        });
    }

    function clearEstablecimientos(sender) {
        console.log(sender);

        $(getIds.asesoria_lista).children().each((index, element) => {
            $(element).find("input").each((i, e) => {
                if ($(e).is("input")) {
                    $(e).prop('checked', '');
                }
            });
        });
    }

    function setSelectedEstablecimiento(establecimiento) {
        var text = establecimiento.toLowerCase();
        $(getIds.asesoria_lista).children().each((index, element) => {
            $(element).find("label").each((i, e) => {
                if ($(e).is("label")) {
                    if ($(e).text().toLowerCase().includes(text)) {
                        $(element).find("input").each((i2, e2) => {
                            if ($(e2).is("input")) {
                                $(e2).prop('checked', 'checked');
                            }
                        });
                    }
                }
            });
        });
    }

    function setAdvisory(sender) {
        var isValid = true;
        var advPaso = {
            idAsesoriaDirecta: $(getIds.modal_new_advisory).attr('data-id-asesoria-directa'),
            fechaCreacion: $(getIds.advisory_fecha_inicio).val(),
            idRegion: $(getIds.advisory_selector_region).val(),
            idDeprov: $(getIds.advisory_selector_deprov).val(),
            idComuna: $(getIds.advisory_selector_comuna).val(),
            establecimiento: getSelectedEstablecimiento()
        };

        console.log(advPaso);
        if (advPaso.fechaCreacion === '') {
            came.main.setInvalidControl($(getIds.advisory_fecha_inicio));
            isValid = false;
        } else {
            // if (came.main.isControlValid($(getIds.advisory_fecha_inicio))) {
            came.main.setValidControl($(getIds.advisory_fecha_inicio));
            // } else {
            //     came.main.setInvalidControl($(getIds.advisory_fecha_inicio));
            //     isValid = false;
            // }
        }

        if (advPaso.idRegion === '') {
            came.main.setInvalidControl($(getIds.advisory_selector_region));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.advisory_selector_region));
        }

        if (advPaso.idDeprov === '') {
            came.main.setInvalidControl($(getIds.advisory_selector_deprov));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.advisory_selector_deprov));
        }

        if (advPaso.idComuna === '') {
            came.main.setInvalidControl($(getIds.advisory_selector_comuna));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.advisory_selector_comuna));
        }

        if (advPaso.establecimiento === '') {
            came.main.setInvalidControl($(getIds.asesoria_lista));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.asesoria_lista));
        }

        if (isValid) {
            $.ajax({
                type: "POST",
                url: urls.api.setAdvisory,
                dataType: "json",
                contentType: 'application/json',
                data: JSON.stringify(advPaso)
            }).done(function (msg) {
                console.log("response done", msg);
                came.notify.showSucces("Asesorías", "Su solicitud se ha procesado correctamente.");

                getAdvisory(this);

                $(getIds.modal_new_advisory).modal('hide');
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                // TODO: aqui se debe agregar un mensaje en caso de error.
                came.notify.showError("Red", "Su solicitud se ha procesado con errores.");
                return false;
            });
        }

    }

    function getSelectedEstablecimiento() {
        var result = [];
        $(getIds.asesoria_lista).children().each((index, element) => {
            $(element).find("input").each((i, e) => {
                if ($(e).is("input")) {
                    if ($(e).prop('checked')) {
                        $(element).find("label").each((i2, e2) => {
                            if ($(e2).is("label")) {
                                result.push($(e2).text());
                            }
                        });
                    }
                }
            });
        });
        return result;
    }

    function getAdvisory(sender) {

        $.ajax({
            type: "GET",
            url: urls.api.getAdvisory,
            //dataType: "json",
            async: true,
            contentType: 'application/json',
            data: jQuery.param({
                rbd: $(getIds.selector_rbd).val(),
                idRegion: $(getIds.selector_region).val(),
                idDeprov: $(getIds.selector_deprov).val(),
                startDate: $(getIds.input_fecha_rango).val().split('-')[0].trim().replace(/[/]/g, '-'),
                endDate: $(getIds.input_fecha_rango).val().split('-')[1].trim().replace(/[/]/g, '-')
            })
        }).done(function (response) {
            if (typeof (response) !== "string") {
                $(getIds.count_advisory).text(response.length);
                $(getIds.table_advisory + ' tbody tr').remove();
                var tablePaso = $(getIds.table_advisory);
                loadAdvisory(tablePaso, response);

                $(getIds.panel_search).css("display", "none");
                $(getIds.panel_search_result).css("display", "");
            } else {
                came.notify.showError("Mensaje", "Su solicitud se ha procesado con errores.");
            }
        });
    }

    function getExcel(sender) {
        console.log(sender);
        var link = document.createElement('a');
        link.href = (urls.api.exportToExcel +
            "?rbd=" + $(getIds.selector_rbd).val() +
            "&idRegion=" + $(getIds.selector_region).val() +
            "&idDeprov=" + $(getIds.selector_deprov).val() +
            "&startDate=" + $(getIds.input_fecha_rango).val().split('-')[0].trim().replace(/[/]/g, '-') +
            "&endDate=" + $(getIds.input_fecha_rango).val().split('-')[1].trim().replace(/[/]/g, '-'));
        console.log(link.href);
        link.click();
    }

    function findAdvisoryAgain(sender) {
        console.log(sender);
        $(getIds.panel_search).css("display", "");
        $(getIds.panel_search_result).css("display", "none");
    }

    function loadAdvisory(table, elements) {
        console.log(elements);
        if (elements) {
            for (var i = 0; i < elements.length; i++) {
                var row = $('<tr></tr>');

                row.append($('<td></td>').append('<a href="#" class="advisory-detail" data-id-advisory="' + elements[i].idAsesoriaDirecta + '">' + elements[i].rbd + '</a>'));
                row.append($('<td></td>').append($('<span />').text(elements[i].region).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].deprov).html()));
                row.append($('<td></td>').append($('<span />').text(came.main.formatDate(elements[i].fechaCreacion)).html()));
                // row.append($('<td></td>').append($('<span />').text(elements[i].establecimientos).html()));

                var buttonID = elements[i].idAsesoriaDirecta;
                var checked = elements[i].habilitado ? 'checked' : '';
                var readOnlyPaso = readOnly ? 'disabled' : '';
                var statusPaso = "<span class='custom-control custom-switch text-center'> <input type='checkbox' class='custom-control-input advisory-set-status'" +
                    " id='" + buttonID + "' " + checked + " select " + readOnlyPaso + "> <label class='custom-control-label' for='" + buttonID + "'></label></span>"
                row.append($('<td></td>').append(statusPaso));


                // row.append($('<td></td>').append($('<span />').text(elements[i].habilitado).html()));
                $(table).append(row);
            }

            came.main.initList(table);
        }
    }

    function setStatusAdvisory(sender) {
        console.log(sender);

        var idAsesoriaDirecta = $(sender).attr('id');
        var status = $(sender).prop('checked');

        $.ajax({
            type: "POST",
            url: urls.api.setStatusAdvisory,
            //dataType: "json",
            //contentType: 'application/json',
            data: jQuery.param({ idAsesoriaDirecta: idAsesoriaDirecta, status: status })
        }).done(function (msg) {
            console.log("response done", msg);
            came.notify.showSucces("Red", "Su solicitud se ha procesado correctamente.");
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            // TODO: aqui se debe agregar un mensaje en caso de error.
            came.notify.showError("Red", "Su solicitud se ha procesado con errores.");
            return false;
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
        }
    }

    function closePopover(sender) {
        console.log(sender);
        $(popoverPaso).popover('hide');
    }

    function setViewAcces() {
        var menuPso = came.storage.getSessionStorage('menu');
        var subMenuProcess = menuPso.find(e => e.idModule == '2416198290916770826');
        console.log(subMenuProcess);
        if (subMenuProcess) {
            var directa = subMenuProcess.subMenu.find(e => e.idSubModule == '2416254586126861334');
            console.log(directa);
            readOnly = directa.readOnly;
            if (readOnly) {
                // $("#content-network :input").prop("disabled", true);
                // $("#red-button-guardar").prop("disabled", true);
                $("#advisory-button-editar").hide();
            }
        }
    }

    function init() {
        setViewAcces();
        initListener();
        selectRegionDeprov();
    }

    return {
        init: init
    };

})();

$(document).ready(function () {
    console.log('came.advisory.init()');
    came.advisory.init();
});