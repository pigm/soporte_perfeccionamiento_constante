'use strict';
var came = came || {};

came.planning = (function () {
    var readOnly = false;
    var popoverPaso = null;
    var focos = [];

    var urls = {
        api: {
            getFocos: came.contexto + "/planning/getFocos",
            getProvincias: came.contexto + "/planning/getProvincias",
            getComunas: came.contexto + "/planning/getComunas",
            getSupUserNames: came.contexto + "/planning/getSupUserNames",
            getPlanning: came.contexto + "/planning/getPlanning",
            exportToExcel: came.contexto + "/planning/export/excel",

            registro_directa: came.contexto + "/planning/registro-directa",
            registro_red: came.contexto + "/planning/registro-red",
            setPrimeraPlanificacion: came.contexto + "/planning/setPrimeraPlanificacion",
            setRegistraSesion: came.contexto + "/planning/setRegistraSesion",
            setRegistraSesionRed: came.contexto + "/planning/setRegistraSesionRed",
            getSesionDetail: came.contexto + "/planning/getSesionDetail",
            getSesionRedDetail: came.contexto + "/planning/getSesionRedDetail",
            getAccionesFoco: came.contexto + "/planning/getAccionesFoco",

            getSesionInfoDetail: came.contexto + "/planning/getSesionInfoDetail",
            getSesionRedInfoDetail: came.contexto + "/planning/getSesionRedInfoDetail",
        }
    };

    var getIds = {
        button_back: "#button-back",
        panel_search: "#panel-search",
        panel_search_result: "#panel-search-result",
        button_find_again: "#button-find-again",
        table_planning: "#table-planning",
        count_planning: "#count-planning",

        selector_region: "#selector-region",
        selector_deprov: "#selector-deprov",
        selector_comuna: "#selector-comuna",
        selector_supervisor: "#selector-supervisor",
        selector_categorizacion: "#selector-categorizacion",
        selector_dependencias: "#selector-dependencias",
        input_rbd: "#input-rbd",
        input_nombre_establecimiento: "#input-nombre-establecimiento",
        selector_tipo: "#selector-tipo",
        selector_tipo_red: "#selector-tipo-red",
        input_nombre_red: "#input-nombre-red",

        modal_primera_planificacion: "#modal-primera-planificacion",
        button_primera_planificacion: ".button-primera-planificacion",
        button_planificar_planificar: "#button-planificar-planificar",
        button_planificar_si: "#button-planificar-si",
        button_planificar_no: "#button-planificar-no",

        modal_registrar_sesion: "#modal-registrar-sesion",
        modal_ver_sesion: "#modal-ver-sesion",
        button_registrar_sesion: "#button-registrar-sesion",
        button_ver_sesion: ".button-ver-sesion",
        registra_sesion_focos: "#registra-sesion-focos",
        registra_sesion_fecha_planificada: "#registra-sesion-fecha-planificada",
        registra_sesion_hora_planificada: "#registra-sesion-hora-planificada",
        registra_sesion_fecha_proxima: "#registra-sesion-fecha-proxima",
        registra_sesion_hora_proxima: "#registra-sesion-hora-proxima",
        registra_sesion_tipo_asesoria: "registra-sesion-tipo-asesoria",

        ver_sesion_focos: "#ver-sesion-focos",
        ver_sesion_fecha_planificada: "#ver-sesion-fecha-planificada",
        ver_sesion_hora_planificada: "#ver-sesion-hora-planificada",
        ver_sesion_fecha_realizada: "#ver-sesion-fecha-realizada",
        ver_sesion_hora_realizada: "#ver-sesion-hora-realizada",
        ver_sesion_fecha_proxima: "#ver-sesion-fecha-proxima",
        ver_sesion_hora_proxima: "#ver-sesion-hora-proxima",
        ver_sesion_tipo_asesoria_presencial: "#ver-sesion-tipo-asesoria-presencial",
        ver_sesion_tipo_asesoria_remota: "#ver-sesion-tipo-asesoria-remota",

        planificar_fecha_inicio: "#planificar-fecha-inicio",
        planificar_hora_inicio: "#planificar-hora-inicio",

        button_new_planning: "#button-new-planning",
        find_planning: "#find-planning",

        button_foco_add: "#button-foco-add",
        select_foco_agregar: "#select-foco-agregar",
        button_foco_agregar: "#button-foco-agregar",
        button_registrar_sesion_guardar: "#button-registrar-sesion-guardar",

        modal_focos_seleccionar: "#modal-focos-seleccionar",

        button_registrar_sesion_red: "#button-registrar-sesion-red",
        button_registra_sesion_red_agregar_foco: "#registra-sesion-red-agregar-foco",
        modal_registrar_sesion_red: "#modal-registrar-sesion-red",
        modal_registrar_sesion_red_foco: "#modal-registrar-sesion-red-foco",

        button_close_popover: ".button-close-popover",
        button_export_excel: "#button-export-excel",

        registra_sesion_red_nombre: "#registra-sesion-red-nombre",
        registra_sesion_red_cargo: "#registra-sesion-red-cargo",
        registra_sesion_red_correo: "#registra-sesion-red-correo",

        registra_sesion_red_fecha_planificada: "#registra-sesion-red-fecha-planificada",
        registra_sesion_red_hora_planificada: "#registra-sesion-red-hora-planificada",
        registra_sesion_red_fecha_proxima: "#registra-sesion-red-fecha-proxima",
        registra_sesion_red_hora_proxima: "#registra-sesion-red-hora-proxima",
        registra_sesion_red_tipo_asesoria: "registra-sesion-red-tipo-asesoria",
        registra_sesion_red_objetivo1: "#registra-sesion-red-objetivo1",
        registra_sesion_red_objetivo2: "#registra-sesion-red-objetivo2",
        registra_sesion_red_focos: "#registra-sesion-red-focos",
        registra_sesion_red_participantes: "#registra-sesion-red-participantes",

        registra_sesion_red_selector_foco: "#registra-sesion-red-selector-foco",
        registra_sesion_red_foco_objetivo1: "#registra-sesion-red-foco-objetivo1",
        registra_sesion_red_foco_objetivo2: "#registra-sesion-red-foco-objetivo2",
        registra_sesion_red_foco_acciones: "#registra-sesion-red-foco-acciones",
        registra_sesion_red_foco_nueva_accion: "#registra-sesion-red-foco-nueva-accion",

        planning_encargado_nombre: "#planning-encargado-nombre",
        planning_encargado_apellido: "#planning-encargado-apellido",
        planning_encargado_cargo: "#planning-encargado-cargo",
        planning_encargado_correo: "#planning-encargado-correo",

        agregar_accion_mejora: ".agregar-accion-mejora",
        registra_sesion_red_foco_guardar: "#registra-sesion-red-foco-guardar",
        registra_sesion_red_guardar: "#registra-sesion-red-guardar-sesion",

        remove_accion_foco: ".remove-accion-foco",
        confirmRemoveAccion: ".confirmRemoveAccion",

        button_ver_sesion_red: "#button-ver-sesion-red",
        modal_ver_sesion_red: "#modal-ver-sesion-red",
        ver_sesion_red_nombre: "#ver-sesion-red-nombre",
        ver_sesion_red_cargo: "#ver-sesion-red-cargo",
        ver_sesion_red_correo: "#ver-sesion-red-correo",

        ver_sesion_red_fecha_planificada: "#ver-sesion-red-fecha-planificada",
        ver_sesion_red_hora_planificada: "#ver-sesion-red-hora-planificada",
        ver_sesion_red_fecha_proxima: "#ver-sesion-red-fecha-proxima",
        ver_sesion_red_hora_proxima: "#ver-sesion-red-hora-proxima",
        ver_sesion_red_tipo_asesoria: "ver-sesion-red-tipo-asesoria",
        ver_sesion_red_objetivo1: "#ver-sesion-red-objetivo1",
        ver_sesion_red_objetivo2: "#ver-sesion-red-objetivo2",
        ver_sesion_red_focos: "#ver-sesion-red-focos",
        ver_sesion_red_participantes: "#ver-sesion-red-participantes",

        ver_sesion_red_tipo_asesoria_presencial: "#ver-sesion-red-tipo-asesoria-presencial",
        ver_sesion_red_tipo_asesoria_remota: "#ver-sesion-red-tipo-asesoria-remota",

        modal_ver_focos_detalle: "#modal-ver-focos-detalle",
        ver_asesoria_focos: "#ver-asesoria-focos",
        button_ver_asesoria_focos: "#button-ver-asesoria-focos",


        ver_sesion_red_focos_detalle: "#ver-sesion-red-focos-detalle",
        modal_red_ver_focos_detalle: "#modal-red-ver-focos-detalle",
        button_ver_asesoria_red_focos: "#button-ver-asesoria-red-focos"

    };

    var initListener = function () {
        $(document)
            .on("change", getIds.selector_region, function () {
                getProvincias(this);
                getUserNamesSuper(this);
            })
            .on("change", getIds.selector_deprov, function () {
                getComunas(this);
                getUserNamesSuper(this);
            })
            .on("change", getIds.registra_sesion_red_selector_foco, function () {
                getAccionesMejoraFoco(this);
            })
            .on("click", getIds.find_planning, function () {
                getPlanning(this);
            })
            .on("click", getIds.button_find_again, function () {
                findPlanningAgain(this);
            })
            .on("click", getIds.button_primera_planificacion, function () {
                console.log(this);
                $(getIds.modal_primera_planificacion).modal('show');
            })
            .on("click", getIds.button_planificar_planificar, function () {
                confirmPrimeraPlanificacion(this);
            })
            .on("click", getIds.button_planificar_si, function () {
                setPrimeraPlanificacion(this);
            })
            .on("click", getIds.button_planificar_no, function () {
                closePopover(this);
            })
            .on("click", getIds.button_close_popover, function () {
                closePopover(this);
            })
            .on("click", getIds.button_registrar_sesion, function () {
                showRegistraSesion(this);
            })
            .on("click", getIds.button_registrar_sesion_red, function () {
                showRegistraSesionRed(this);
            })
            .on("click", getIds.button_ver_sesion_red, function () {
                showVerSesionRed(this);
            })
            .on("click", getIds.button_ver_sesion, function () {
                verSesion(this);
            })
            .on("click", getIds.button_back, function () {
                goBack();
            })
            .on("click", getIds.button_foco_add, function () {
                agregarFoco(this);
            })
            .on("click", getIds.button_foco_agregar, function () {
                agregarFocoSelected(this);
            })
            .on("click", getIds.button_registra_sesion_red_agregar_foco, function () {
                agregarFocoRed(this);
            })
            .on("click", getIds.registra_sesion_red_foco_nueva_accion, function () {
                agregarAccionMejoraSesionRed(this);
            })
            .on("click", getIds.button_registrar_sesion_guardar, function () {
                registraSesion(this);
            })
            .on("click", getIds.button_export_excel, function () {
                getExcel(this);
            })
            .on("click", getIds.agregar_accion_mejora, function () {
                agregaAccion(this);
            })
            .on("click", getIds.registra_sesion_red_foco_guardar, function () {
                guardarRegistroFoco(this);
            })
            .on("click", getIds.remove_accion_foco, function () {
                eliminaAccionMejoraSesionRed(this);
            })
            .on("click", getIds.confirmRemoveAccion, function () {
                confirmRemoveAccion(this);
            })
            .on("click", getIds.registra_sesion_red_guardar, function () {
                registraSesionred(this);
            })
            .on("click", getIds.button_ver_asesoria_focos, function () {
                showAsesoriaFocosDetalle(this);
            })
            .on("click", getIds.button_ver_asesoria_red_focos, function () {
                showAsesoriaRedFocosDetalle(this);
            })
            .on("hidden.bs.modal", getIds.modal_registrar_sesion, function () {
                clearModalRegistra(this);
            })
            .on("hidden.bs.modal", getIds.modal_ver_sesion, function () {
                clearModalVer(this);
            });
    }

    function showAsesoriaFocosDetalle(sender) {
        console.log(sender);

        var asesoriaId = $(sender).attr('data-id-asesoria-directa');
        $.ajax({
            type: "GET",
            url: urls.api.getSesionInfoDetail,
            // dataType: "json",
            async: false,
            // contentType: 'application/json',
            data: jQuery.param({ asesoriaId: asesoriaId })
        }).done(function (response) {
            console.info("response done", response);
            $(getIds.ver_asesoria_focos).empty();
            response.focos.forEach(f => {
                agregarFocoSesion(f, getIds.ver_asesoria_focos, false);
            });

        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            return false;
        });


        $(getIds.modal_ver_focos_detalle).modal('show');
    }

    function showAsesoriaRedFocosDetalle(sender) {
        console.log(sender);

        var asesoriaId = $(sender).attr('data-id-asesoria');
        $.ajax({
            type: "GET",
            url: urls.api.getSesionRedInfoDetail,
            // dataType: "json",
            async: false,
            contentType: 'application/json',
            data: jQuery.param({ asesoriaId: asesoriaId })
        }).done(function (response) {
            console.info("response done", response);
            response.focos.forEach(f => {
                agregarFocoDetalleSesionRed(f, true);
            });
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            return false;
        });


        $(getIds.modal_red_ver_focos_detalle).modal('show');
    }

    function showRegistraSesion(sender) {
        if (readOnly) {
            came.notify.showError("Registro de asesoría directa", "No tienes permitido registrar asesoría directa.");
        } else {
            console.log(sender);
            var dataIdSesion = $(sender).attr('data-id-sesion');
            var fechaPlanificacion = $(sender).attr('data-fecha-planificacion');
            console.log(fechaPlanificacion.split(' '));
            $(getIds.registra_sesion_fecha_planificada).val(fechaPlanificacion.split(' ')[0]);
            console.log($(getIds.registra_sesion_fecha_planificada).val());
            $(getIds.registra_sesion_hora_planificada).val(fechaPlanificacion.split(' ')[1]);
            $(getIds.modal_registrar_sesion).attr('data-id-sesion', dataIdSesion);

            $.ajax({
                type: "GET",
                url: urls.api.getSesionDetail,
                // dataType: "json",
                async: false,
                // contentType: 'application/json',
                data: jQuery.param({ idSesion: dataIdSesion })
            }).done(function (response) {
                console.info("response done", response);

                $(getIds.registra_sesion_focos).empty();
                response.focos.forEach(f => {
                    agregarFocoSesion(f, getIds.registra_sesion_focos, true);
                });

            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                return false;
            });

            $(getIds.modal_registrar_sesion).modal('show');
        }
    }

    function showRegistraSesionRed(sender) {
        if (readOnly) {
            came.notify.showError("Registro de asesoría red", "No tienes permitido registrar asesoría red.");
        } else {
            console.log(sender);
            var dataIdSesion = $(sender).attr('data-id-sesion');
            var fechaPlanificacion = $(sender).attr('data-fecha-planificacion');
            console.log(fechaPlanificacion.split(' '));
            $(getIds.registra_sesion_red_fecha_planificada).val(fechaPlanificacion.split(' ')[0]);
            console.log($(getIds.registra_sesion_red_fecha_planificada).val());
            $(getIds.registra_sesion_red_hora_planificada).val(fechaPlanificacion.split(' ')[1]);
            $(getIds.modal_registrar_sesion_red).attr('data-id-sesion', dataIdSesion);

            $(getIds.registra_sesion_red_nombre).text($(getIds.planning_encargado_nombre).text() + " " + $(getIds.planning_encargado_apellido).text())
            $(getIds.registra_sesion_red_cargo).text($(getIds.planning_encargado_cargo).text());
            $(getIds.registra_sesion_red_correo).text($(getIds.planning_encargado_correo).text());

            $.ajax({
                type: "GET",
                url: urls.api.getSesionRedDetail,
                // dataType: "json",
                async: false,
                // contentType: 'application/json',
                data: jQuery.param({ idSesion: dataIdSesion })
            }).done(function (response) {
                console.info("response done", response);

                response.objetivos.forEach(o => {
                    if (o.numero === 1) {
                        $(getIds.registra_sesion_red_objetivo1).val(o.objetivo);
                    }
                    if (o.numero === 2) {
                        $(getIds.registra_sesion_red_objetivo2).val(o.objetivo);
                    }
                });

                response.focos.forEach(f => {
                    agregarFocoSesionRed(f, true);
                });

                agregaParticipantes(response.participantes, response.tipoRed);

                $(getIds.modal_registrar_sesion_red).modal('show');
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                return false;
            });
        }
    }

    function showVerSesionRed(sender) {
        console.log(sender);
        var dataIdSesion = $(sender).attr('data-id-sesion');
        var fechaPlanificacion = $(sender).attr('data-fecha-planificacion');
        console.log(fechaPlanificacion.split(' '));
        $(getIds.ver_sesion_red_fecha_planificada).val(fechaPlanificacion.split(' ')[0]);
        console.log($(getIds.ver_sesion_red_fecha_planificada).val());
        $(getIds.ver_sesion_red_hora_planificada).val(fechaPlanificacion.split(' ')[1]);
        $(getIds.modal_ver_sesion_red).attr('data-id-sesion', dataIdSesion);

        $(getIds.ver_sesion_red_nombre).text($(getIds.planning_encargado_nombre).text() + " " + $(getIds.planning_encargado_apellido).text())
        $(getIds.ver_sesion_red_cargo).text($(getIds.planning_encargado_cargo).text());
        $(getIds.ver_sesion_red_correo).text($(getIds.planning_encargado_correo).text());

        $.ajax({
            type: "GET",
            url: urls.api.getSesionRedDetail,
            // dataType: "json",
            async: false,
            // contentType: 'application/json',
            data: jQuery.param({ idSesion: dataIdSesion })
        }).done(function (response) {
            console.info("response done", response);

            response.objetivos.forEach(o => {
                if (o.numero === 1) {
                    $(getIds.ver_sesion_red_objetivo1).val(o.objetivo);
                }
                if (o.numero === 2) {
                    $(getIds.ver_sesion_red_objetivo2).val(o.objetivo);
                }
            });

            response.focos.forEach(f => {
                agregarFocoSesionRedVer(f, true);
            });

            if (response.tipoSesion === "Presencial") {
                $(getIds.ver_sesion_red_tipo_asesoria_presencial).prop('checked', 'checked');
            } else {
                $(getIds.ver_sesion_red_tipo_asesoria_remota).prop('checked', 'checked');
            }

            agregaParticipantesVer(response.participantes, response.tipoRed);

            $(getIds.modal_ver_sesion_red).modal('show');
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            return false;
        });
    }

    function agregaParticipantes(participantes, tipoRed) {
        $(getIds.registra_sesion_red_participantes + ' tbody tr').remove();

        $("#header-participantes-rbd").text(tipoRed === "S" ? "Rut" : "RBD");
        $("#header-participantes-est").text(tipoRed === "S" ? "Sostenedor" : "Establecimiento");

        var table = $(getIds.registra_sesion_red_participantes);
        if (participantes && participantes.length > 0) {
            for (var i = 0; i < participantes.length; i++) {
                var row = $('<tr data-idParticipante="' + participantes[i].idParticipante + '" data-tipoParticipante="' + participantes[i].tipoParticipante + '" data-rbd="' + participantes[i].rbd + '"></tr>');
                row.append($('<td></td>').append($('<span />').text(participantes[i].numero)));
                row.append($('<td></td>').append($('<span />').text(participantes[i].rbd)));
                row.append($('<td></td>').append($('<span />').text(participantes[i].participante)));
                row.append($('<td class="text-center">' +
                    '<div class= "custom-control custom-checkbox ml-2">' +
                    '<input type="checkbox" class="custom-control-input check-participante" id="' + participantes[i].numero + '">' +
                    '<label class="custom-control-label" for="' + participantes[i].numero + '"></label>' +
                    '</div></td>'));
                table.append(row);
            }
        } else {
            var row2 = $('<tr></tr>');
            row2.append($('<td colspan="2" class="text-center">No hay Datos</td>'));
            table.append(row2);
        }
        came.main.initList(table);
    }

    function agregaParticipantesVer(participantes, tipoRed) {
        $(getIds.ver_sesion_red_participantes + ' tbody tr').remove();

        $("#header-participantes-rbd-ver").text(tipoRed === "S" ? "Rut" : "RBD");
        $("#header-participantes-est-ver").text(tipoRed === "S" ? "Sostenedor" : "Establecimiento");

        var table = $(getIds.ver_sesion_red_participantes);
        if (participantes && participantes.length > 0) {
            for (var i = 0; i < participantes.length; i++) {
                var row = $('<tr data-idParticipante="' + participantes[i].idParticipante + '" data-tipoParticipante="' + participantes[i].tipoParticipante + '" data-rbd="' + participantes[i].rbd + '"></tr>');
                row.append($('<td></td>').append($('<span />').text(participantes[i].numero)));
                row.append($('<td></td>').append($('<span />').text(participantes[i].rbd)));
                row.append($('<td></td>').append($('<span />').text(participantes[i].participante)));
                var checkPaso = participantes[i].presente ? 'checked' : '';
                row.append($('<td class="text-center">' +
                    '<div class= "custom-control custom-checkbox ml-2">' +
                    '<input type="checkbox" class="custom-control-input check-participante" id="' + participantes[i].numero + '" ' + checkPaso + ' disabled="disabled">' +
                    '<label class="custom-control-label" for="' + participantes[i].numero + '"></label>' +
                    '</div></td>'));
                table.append(row);
            }
        } else {
            var row2 = $('<tr></tr>');
            row2.append($('<td colspan="2" class="text-center">No hay Datos</td>'));
            table.append(row2);
        }
        came.main.initList(table);
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

    function getComunas(sender) {

        $(getIds.selector_comuna + " option").each(function () {
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
                $(getIds.selector_comuna).append($('<option selected>').val('').text('Seleccionar'));
                for (var i = 0; i < response.length; i++) {
                    $(getIds.selector_comuna).append($('<option>').val(response[i].value).text(response[i].displayText));
                }
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
        } else {
            $(getIds.selector_comuna).append($('<option selected>').val('').text('Seleccionar'));
        }
    }

    function getUserNamesSuper(sender) {

        $(getIds.selector_supervisor + " option").each(function () {
            $(this).remove();
        });

        var idRegion = $(getIds.selector_region).val();
        var idDeprov = $(getIds.selector_deprov).val();

        $.ajax({
            type: "GET",
            url: urls.api.getSupUserNames,
            dataType: "json",
            async: false,
            contentType: 'application/json',
            data: { regionId: idRegion, deprovId: idDeprov }
        }).done(function (response) {
            console.info("response done", response);
            $(getIds.selector_supervisor).append($('<option selected>').val('').text('Seleccionar'));
            for (var i = 0; i < response.length; i++) {
                $(getIds.selector_supervisor).append($('<option>').val(response[i]).text(response[i]));
            }
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            return false;
        });
    }

    function getPlanning(sender) {
        
        $.ajax({
            type: "GET",
            url: urls.api.getPlanning,
            //dataType: "json",
            // async: false,
            contentType: 'application/json',
            data: jQuery.param({
                idRegion: $(getIds.selector_region).val(),
                idDeprov: $(getIds.selector_deprov).val(),
                idComuna: $(getIds.selector_comuna).val(),
                userNameSupervisor: $(getIds.selector_supervisor).val(),
                categorizacion: $(getIds.selector_categorizacion).val(),
                dependencia: $(getIds.selector_dependencias).val(),
                rbd: $(getIds.input_rbd).val(),
                nombreEstablecimiento: $(getIds.input_nombre_establecimiento).val(),
                tipo: $(getIds.selector_tipo).val(),
                idTipoRed: $(getIds.selector_tipo_red).val(),
                nombreRed: $(getIds.input_nombre_red).val()
            })
        }).done(function (response) {
            if (typeof (response) !== "string") {

                if ($(getIds.table_planning).DataTable() !== null) {
                    $(getIds.table_planning).DataTable().destroy();
                }

                $(getIds.count_planning).text(response.length);
                $(getIds.table_planning + ' tbody tr').remove();
                var tablePaso = $(getIds.table_planning);
                loadPlanning(tablePaso, response);

                $(getIds.panel_search).css("display", "none");
                $(getIds.panel_search_result).css("display", "");

                came.main.hideLoading();
            } else {
                came.notify.showError("Asignar supervisores", "Su solicitud se ha procesado con errores.");
            }
        });
    }

    function findPlanningAgain(sender) {
        console.log(sender);
        $(getIds.panel_search).css("display", "");
        $(getIds.panel_search_result).css("display", "none");
    }

    function getExcel(sender) {
        console.log(sender);

        var link = document.createElement('a');
        link.href = (urls.api.exportToExcel +
            "?idRegion=" + $(getIds.selector_region).val() +
            "&idDeprov=" + $(getIds.selector_deprov).val() +
            "&idComuna=" + $(getIds.selector_comuna).val() +
            "&userNameSupervisor=" + $(getIds.selector_supervisor).val() +
            "&categorizacion=" + $(getIds.selector_categorizacion).val() +
            "&dependencia=" + $(getIds.selector_dependencias).val() +
            "&rbd=" + $(getIds.input_rbd).val() +
            "&nombreEstablecimiento=" + $(getIds.input_nombre_establecimiento).val() +
            "&tipo=" + $(getIds.selector_tipo).val() +
            "&idTipoRed=" + $(getIds.selector_tipo_red).val() +
            "&nombreRed=" + $(getIds.input_nombre_red).val());
        console.log(link.href);
        link.click();
    }

    function loadPlanning(table, elements) {
        console.log(elements);
        if (elements) {
            for (var i = 0; i < elements.length; i++) {
                var row = $('<tr></tr>');
                row.append($('<td></td>').append($('<span />').text(elements[i].region).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].deprov).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].comuna).html()));
                var div = $('<div></div>');
                if (elements[i].supervisores) {
                    elements[i].supervisores.forEach(s => {
                        div.append($('<span /><br/>').text(s))
                    });
                }
                row.append($('<td></td>').append(div.html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].tipo).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].nombreRed).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].sessiones).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].establecimientos).html()));
                if (elements[i].supervisores && elements[i].supervisores.length > 0) {
                    if (elements[i].tipo.toLowerCase() === 'directa') {
                        row.append($('<td class="text-center"></td>').append('<a href="' + urls.api.registro_directa + "?asesoriaId=" + elements[i].idAsesoriaDirecta + '" class="badge badge-pill badge-primary mt-3">ir</a>'));
                    } else {
                        row.append($('<td class="text-center"></td>').append('<a href="' + urls.api.registro_red + "?redId=" + elements[i].idRed + '" class="badge badge-pill badge-primary mt-3">ir</a>'));
                    }

                } else {
                    row.append($('<td></td>').append($('<span />').text("Sin Supervisor").html()))
                }

                $(table).append(row);
            }

            came.main.initList(table);
        }
    }

    function confirmPrimeraPlanificacion(sender) {
        console.log(sender);
        // title="<div class="text-center">Seguro que quieres <br> eliminar el elemento </div>" data-content="<div class=""> <a class="btn btn-sm btn-secondary col-4 float-left mb-2" href="#">No</a> <a class="btn btn-sm btn-danger col-4 float-right" href="#">Si</a> </div>">'

        if (popoverPaso) {
            $(popoverPaso).popover('hide');
        }

        var idAsesoria = $(sender).attr('data-id-asesoria');
        console.log(idAsesoria);
        popoverPaso = $(sender).popover({
            placement: 'top',
            container: 'body',
            // trigger: 'focus',
            html: true,
            sanitize: false,
            content:
                '<div class="">' +
                '<a id="button-planificar-no" class="btn btn-sm btn-secondary col-4 float-left mb-2" href="#">No</a>' +
                '<a id="button-planificar-si" class="btn btn-sm btn-primary col-4 float-right" href="#" data-id-asesoria="' + idAsesoria + '" >Si</a>' +
                '</div>'

        });

        popoverPaso.popover('show');
    }

    function setPrimeraPlanificacion(sender) {
        var isValid = true;

        var idAsesoria = $(sender).attr('data-id-asesoria');
        var fechaPlanificacion = $(getIds.planificar_fecha_inicio).val() + " " + $(getIds.planificar_hora_inicio).val();

        if ($(getIds.planificar_fecha_inicio).val() === '') {
            came.main.setInvalidControl($(getIds.planificar_fecha_inicio));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.planificar_fecha_inicio));
        }

        if ($(getIds.planificar_hora_inicio).val() === '') {
            came.main.setInvalidControl($(getIds.planificar_hora_inicio));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.planificar_hora_inicio));
        }

        if (isValid) {
            $.ajax({
                type: "POST",
                url: urls.api.setPrimeraPlanificacion,
                data: jQuery.param({ idAsesoria: idAsesoria, fechaPlanificacion: fechaPlanificacion })
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

    function goBack() {
        window.history.back();
    }

    function agregarFoco(sender) {
        console.log(sender);
        // title="<div class="text-center">Seguro que quieres <br> eliminar el elemento </div>" data-content="<div class=""> <a class="btn btn-sm btn-secondary col-4 float-left mb-2" href="#">No</a> <a class="btn btn-sm btn-danger col-4 float-right" href="#">Si</a> </div>">'

        if (popoverPaso) {
            $(popoverPaso).popover('hide');
        }
        var selectFocoOptions = '';
        for (var i = 0; i < focos.length; i++) {
            selectFocoOptions += '<option value="' + focos[i].idFoco + '">' + focos[i].nombre + '</option>'
        }

        popoverPaso = $(sender).popover({
            placement: 'left',
            container: 'body',
            // trigger: 'focus',
            html: true,
            sanitize: false,
            content:
                '<div class="input-group">' +
                '<select id="select-foco-agregar" class="custom-select custom-select-sm">' +
                '<option value="" selected>Seleccionar</option>' +
                selectFocoOptions +
                '</select>' +
                '<div class="invalid-feedback">' +
                '<p>Debes seleccionar un foco.</p>' +
                '</div>' +
                '</div>' +
                '<div class="mt-2">' +
                '<a class="btn btn-sm btn-secondary col-4 float-left mb-2 button-close-popover" href="#">No</a> <a id="button-foco-agregar" class="btn btn-sm btn-primary col-6 float-right" href="#">Agregar</a>' +
                '</div>'
        });

        popoverPaso.popover('show');
    }

    function agregarAccionMejoraSesionRed(sender) {
        console.log(sender);
        // title="<div class="text-center">Seguro que quieres <br> eliminar el elemento </div>" data-content="<div class=""> <a class="btn btn-sm btn-secondary col-4 float-left mb-2" href="#">No</a> <a class="btn btn-sm btn-danger col-4 float-right" href="#">Si</a> </div>">'

        if (popoverPaso) {
            $(popoverPaso).popover('hide');
        }
        var selectFocoOptions = '';
        for (var i = 0; i < focos.length; i++) {
            selectFocoOptions += '<option value="' + focos[i].idFoco + '">' + focos[i].nombre + '</option>'
        }

        popoverPaso = $(sender).popover({
            placement: 'left',
            container: 'body',
            // trigger: 'focus',
            html: true,
            sanitize: false,
            content:
                '<div class="input-group">' +
                '<input id="input-nombre-accion" type="text" class="form-control" placeholder="Ingresa acción de mejora">' +
                '<div class="invalid-feedback">' +
                '<p>Debes especificar un nombre valido.</p>' +
                '</div >' +
                '</div> <div class="mt-2">' +
                '<a class="btn btn-sm btn-secondary col-4 float-left mb-2 button-close-popover" href="#">No</a>' +
                '<a class="btn btn-sm btn-primary col-6 float-right agregar-accion-mejora" href="#">Guardar</a>' +
                '</div>'
        });

        popoverPaso.popover('show');
    }

    function agregaAccion(sender) {
        if ($('#input-nombre-accion').val() !== '' && !existeAccion($('#input-nombre-accion').val())) {
            came.main.setValidControl($('#input-nombre-accion'));
            var table = $(getIds.registra_sesion_red_foco_acciones);
            var row = $('<tr class="text-rea"></tr>');
            var spanOne = $();
            var deletePaso = '<span class="nombre-accion"> ' + $('#input-nombre-accion').val() + '</span> ' +
                '<a href="#" class="float-right text-danger remove-accion-foco" data-sesionFocoAccionMejora="' + $('#input-nombre-accion').val() + '" data-placement="top" data-original-title="Seguro que quieres <br> eliminar la acción">' +
                '<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">' +
                '<path fill-rule="evenodd" d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z" />' +
                '</svg>' +
                '</a>';
            $(spanOne).append(deletePaso);
            console.log(spanOne);
            row.append($('<td></td>').append(deletePaso));
            var checked = '';
            var idCheck = new Date().getTime();
            row.append('<td class="text-center">' +
                '<div class="custom-control custom-checkbox ml-2">' +
                '<input type="checkbox" class="custom-control-input check-completado" id="' + idCheck + '" ' + checked + '>' +
                '<label class="custom-control-label" for="' + idCheck + '"></label>' +
                '</div></td>');

            table.append(row);

            // row.append('<td class="text-rea"><span class="nombre-accion">' + $('#input-nombre-accion').val() + '</span></td>');
            // var idCheck = new Date().getTime();
            // row.append('<td class="text-center">' +
            //     '<div class="custom-control custom-checkbox ml-2">' +
            //     '<input type="checkbox" class="custom-control-input check-completado" id="' + idCheck + '">' +
            //     '<label class="custom-control-label" for="' + idCheck + '"></label>' +
            //     '</div></td>');
            // table.append(row);
            $(".button-close-popover").click();
        } else {
            came.main.setInvalidControl($('#input-nombre-accion'));
        }
    }

    function eliminaAccionMejoraSesionRed(sender) {
        console.log(sender);
        // title="<div class="text-center">Seguro que quieres <br> eliminar el elemento </div>" data-content="<div class=""> <a class="btn btn-sm btn-secondary col-4 float-left mb-2" href="#">No</a> <a class="btn btn-sm btn-danger col-4 float-right" href="#">Si</a> </div>">'

        if (popoverPaso) {
            $(popoverPaso).popover('hide');
        }

        var sesionFocoAccionMejora = $(sender).attr('data-sesionFocoAccionMejora');
        var idSesionFocoAccionMejora = $(sender).attr('data-idSesionFocoAccionMejora');
        console.log(idSesionFocoAccionMejora);
        popoverPaso = $(sender).popover({
            placement: 'top',
            container: 'body',
            // trigger: 'focus',
            html: true,
            //sanitize: false,
            content:
                '<div class="">' +
                '<a class="btn btn-sm btn-secondary col-4 float-left mb-2 button-close-popover" href="#">No</a>' +
                '<a class="btn btn-sm btn-danger col-4 float-right confirmRemoveAccion" data-idSesionFocoAccionMejora="' + idSesionFocoAccionMejora + '" data-sesionFocoAccionMejora="' + sesionFocoAccionMejora + '" href="#">Si</a>' +
                '</div>'
        });

        popoverPaso.popover('show');
    }

    function confirmRemoveAccion(sender) {
        console.log(sender);
        var idSesionFocoAccionMejora = $(sender).attr('data-idSesionFocoAccionMejora');
        console.log(idSesionFocoAccionMejora);
        console.log(typeof (idSesionFocoAccionMejora));
        if (idSesionFocoAccionMejora !== null && idSesionFocoAccionMejora !== "undefined") {
            // aqui eliminar segun el id
            console.log("no deberia estar aqui!!!");
            console.log(idSesionFocoAccionMejora);
            $(getIds.registra_sesion_red_foco_acciones + ' tbody > tr').each(function () {
                var trPaso = this;
                console.log(trPaso);
                $(this).find('td').each(function () {
                    var idAccion = $(this).find(".nombre-accion").attr('data-idSesionFocoAccionMejora');
                    if (idAccion) {
                        console.log(idAccion);
                        console.log(idSesionFocoAccionMejora);
                        if (idAccion === idSesionFocoAccionMejora) {
                            trPaso.remove();
                        }
                    }
                });
            });
        } else {
            // aqui eliminar segun el nombre
            var sesionFocoAccionMejora = $(sender).attr('data-sesionFocoAccionMejora');
            console.log(sesionFocoAccionMejora);
            $(getIds.registra_sesion_red_foco_acciones + ' tbody > tr').each(function () {
                var trPaso = this;
                console.log(trPaso);
                $(this).find('td').each(function () {
                    var nombreAccion = $(this).find(".nombre-accion");
                    if (nombreAccion && nombreAccion.length > 0) {
                        console.log(nombreAccion.text().trim());
                        console.log(sesionFocoAccionMejora);
                        if (nombreAccion.text().trim() === sesionFocoAccionMejora) {
                            trPaso.remove();
                        }
                    }
                });
            });
        }
        closePopover(this);
    }

    function existeAccion(accion) {
        var result = false;
        console.log(accion);
        $(getIds.registra_sesion_red_foco_acciones + ' tbody > tr').each(function () {
            var trPaso = this;
            console.log(trPaso);
            $(this).find('td').each(function () {
                var nombreAccion = $(this).find(".nombre-accion");
                if (nombreAccion && nombreAccion.length > 0) {
                    console.log(nombreAccion.text().trim());
                    console.log(accion);
                    result = nombreAccion.text().trim().toLowerCase() === accion.toLowerCase();
                }
            });
        });
        return result;
    }

    function guardarRegistroFoco(sender) {
        var foco = {
            idFoco: $(getIds.registra_sesion_red_selector_foco).val(),
            nombre: $(getIds.registra_sesion_red_selector_foco + ' option:selected').text(),
            accionesMejora: []
        };
        $(getIds.registra_sesion_red_foco_acciones + ' tbody > tr').each(function () {
            var accion = {};
            $(this).find('td').each(function () {
                console.log(this);
                var nombreAccion = $(this).find(".nombre-accion");
                if (nombreAccion && nombreAccion.length > 0) {
                    console.log(nombreAccion.text());
                    accion['accionMejora'] = nombreAccion.text();
                }
                var checkCompletado = $(this).find(".check-completado");
                if (checkCompletado && checkCompletado.length > 0) {
                    console.log(checkCompletado.prop('checked'));
                    accion['completado'] = checkCompletado.prop('checked');
                }
            });
            foco.accionesMejora.push(accion);
        });

        foco['logro'] = Math.round((foco.accionesMejora.filter(x => x.completado === true).length * 100) / foco.accionesMejora.length);

        console.log(foco);
        agregarFocoSesionRed(foco, true);
        $(getIds.modal_registrar_sesion_red).modal('show');
        $(getIds.modal_registrar_sesion_red_foco).modal('hide');
    }

    function agregarFocoRed(sender) {
        console.log($(getIds.modal_registrar_sesion_red).attr('data-id-sesion'));
        $(getIds.registra_sesion_red_selector_foco).attr('data-id-sesion', $(getIds.modal_registrar_sesion_red).attr('data-id-sesion'));
        $(getIds.registra_sesion_red_selector_foco + " option").each(function () {
            $(this).remove();
        });
        $(getIds.registra_sesion_red_selector_foco).append($('<option selected>').val('').text('Seleccionar'));
        for (var i = 0; i < focos.length; i++) {
            $(getIds.registra_sesion_red_selector_foco).append($('<option>').val(focos[i].idFoco).text(focos[i].nombre));
        }

        $(getIds.registra_sesion_red_foco_objetivo1).val($(getIds.registra_sesion_red_objetivo1).val());
        $(getIds.registra_sesion_red_foco_objetivo2).val($(getIds.registra_sesion_red_objetivo2).val());

        $(getIds.modal_registrar_sesion_red).modal('hide');
        $(getIds.modal_registrar_sesion_red_foco).modal('show');
    }

    function getAccionesMejoraFoco(sender) {
        console.log(sender);
        if ($(sender).val() !== '') {
            $.ajax({
                type: "GET",
                url: urls.api.getAccionesFoco,
                dataType: "json",
                async: false,
                //contentType: 'application/json',
                data: jQuery.param({ idSesion: $(sender).attr('data-id-sesion'), idFoco: $(sender).val() })
            }).done(function (response) {
                console.log("response done", JSON.stringify(response));
                $(getIds.registra_sesion_red_foco_acciones + ' tbody tr').remove();
                var table = $(getIds.registra_sesion_red_foco_acciones);
                if (response && response.length > 0) {
                    for (var i = 0; i < response.length; i++) {
                        console.log(response[i]);
                        var row = $('<tr class="text-rea"></tr>');
                        var tdOne = $('<td></td>');
                        tdOne.append('<span class="nombre-accion" data-idSesionFocoAccionMejora="' + response[i].idSesionFocoAccionMejora + '">' + response[i].accionMejora + '</span>');
                        if (!response[i].completado) {
                            tdOne.append('<a href="#" class="float-right text-danger remove-accion-foco" data-sesionFocoAccionMejora="' + response[i].accionMejora + '" data-placement="top" data-original-title="Seguro que quieres <br> eliminar la acción" data-idSesionFocoAccionMejora="' + response[i].idSesionFocoAccionMejora + '">' +
                                '<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">' +
                                '<path fill-rule="evenodd" d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z" />' +
                                '</svg>' +
                                '</a>');
                        }

                        row.append(tdOne);
                        var checked = response[i].completado ? 'checked' : '';
                        var idCheck = new Date().getTime();
                        var disabledPaso = !response[i].completado ? '' : 'disabled="disabled"';
                        row.append('<td class="text-center">' +
                            '<div class="custom-control custom-checkbox ml-2">' +
                            '<input type="checkbox" class="custom-control-input check-completado" id="' + idCheck + '" ' + checked + ' ' + disabledPaso + '>' +
                            '<label class="custom-control-label" for="' + idCheck + '"></label>' +
                            '</div></td>');

                        table.append(row);
                    }
                }
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
            $(getIds.registra_sesion_red_foco_nueva_accion).css("display", "");
        } else {
            $(getIds.registra_sesion_red_foco_nueva_accion).css("display", "none");
            $(getIds.registra_sesion_red_foco_acciones + ' tbody tr').remove();
        }
    }

    function agregarFocoSelected(sender) {
        var idFocoSelected = $(getIds.select_foco_agregar).val();
        console.log("Seleccionado: " + idFocoSelected);
        console.log($(getIds.modal_registrar_sesion).attr('data-max-foco'));
        if (idFocoSelected === '') {
            console.log('Error marca select.');
            came.main.setInvalidControl($(getIds.select_foco_agregar));
        } else {
            var focosSesion = obtenerFocosSesion();
            if (focosSesion.length >= parseInt($(getIds.modal_registrar_sesion).attr('data-max-foco'))) {
                alert("No puede agregar mas focos, ha llegado al maximo permitido.");
                return;
            }
            came.main.setValidControl(focosSesion);
            console.log(idFocoSelected);
            console.log(focosSesion.find(f => f.idFoco == idFocoSelected));
            if (focosSesion.length > 0 && focosSesion.find(f => f.idFoco == idFocoSelected) !== undefined) {
                alert("Foco ya esta agregado.");
            } else {
                focos.forEach(f => {
                    if (f.idFoco === idFocoSelected) {
                        console.log(f);
                        var accionesPaso = '';
                        f.accionesMejoras.forEach(a => {
                            console.log(a);
                            var movimientosPaso = '';
                            a.movimientosClaves.forEach(m => {
                                movimientosPaso += '<tr>' +
                                    '<td>' + m.nombre + '</td>' +
                                    '<td class="text-center">' +
                                    '<div class="custom-control custom-checkbox ml-2">' +
                                    '<input type="checkbox" class="custom-control-input movimientos" id="' + m.idMovimientosClaves + '">' +
                                    '<label class="custom-control-label" for="' + m.idMovimientosClaves + '"></label>' +
                                    '</div>' +
                                    '</td>' +
                                    '</tr>';
                            });
                            accionesPaso += '<table class="table-striped table table-sm mine table-bordered mb-0 accion-mejora" data-id-accion="' + a.idAccionesMejoras + '">' +
                                '<thead><tr><th class="" colspan="2">' + a.nombre + '</th></tr></thead>' +
                                '<tbody>' +
                                movimientosPaso +
                                '</tbody>' +
                                '</table>';
                        });
                        var x1 = '<div class="card foco" data-id-foco="' + f.idFoco + '"><div class="card-header px-2 py-1" id="headingOne"><h2 class="mb-0">' +
                            '<a class="btn btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#' + f.idFoco + '" aria-expanded="true" aria-controls="collapseOne">' +
                            '<strong>' + f.nombre + '</strong></a></h2></div><div id="' + f.idFoco + '" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">' +
                            '<div class="card-body px-3 pt-2"><div class="row"><div class="col-12 mb-3"><p class="mb-1">EID</p>' +
                            '<textarea class="form-control" id="exampleFormControlTextarea1" rows="3" disabled>' + f.descripcion + '.</textarea>' +
                            '</div><div class="col-12 table-responsive">' +
                            accionesPaso +
                            '</div></div></div></div></div>';
                        $(getIds.registra_sesion_focos).append(x1);
                        $(getIds.button_close_popover).click();
                    }
                });
            }
        }
    }

    function obtenerFocos() {
        $.ajax({
            type: "GET",
            url: urls.api.getFocos,
            dataType: "json",
            async: false,
            //contentType: 'application/json',
            data: {}
        }).done(function (response) {
            console.log("response done", JSON.stringify(response));
            focos = response;
            // $(getIds.selector_deprov).append($('<option selected>').val('').text('Seleccionar'));
            // for (var i = 0; i < response.length; i++) {
            //     $(getIds.selector_deprov).append($('<option>').val(response[i].value).text(response[i].displayText));
            // }
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
            return false;
        });
    }

    function obtenerFocosSesion() {
        var focosPaso = [];
        console.log($(getIds.registra_sesion_focos).find('.foco').html());
        $(getIds.registra_sesion_focos).find('.foco').each((index, element) => {
            var fp = {
                idFoco: $(element).attr('data-id-foco'),
                accionesMejoras: []
            };
            fp['idFoco'] = $(element).attr('data-id-foco');
            console.log($(element).attr('data-id-foco'));
            $(element).find('.accion-mejora').each((i2, e2) => {
                var ap = {
                    idAccionesMejoras: $(e2).attr('data-id-accion'),
                    movimientosClaves: []
                };
                $(e2).find('.movimientos').each((i3, e3) => {
                    var mp = {
                        idMovimientosClaves: $(e3).attr('id'),
                        completado: $(e3).is(":checked")
                    };
                    ap.movimientosClaves.push(mp);

                });
                fp.accionesMejoras.push(ap);
            });
            focosPaso.push(fp);
        });
        console.log(focosPaso.length);
        console.log(focosPaso);
        return focosPaso;
    }

    function obtenerFocosSesionRed() {
        var focosPaso = [];
        $(getIds.registra_sesion_red_focos + ' tbody > tr').each(function () {
            var nombreFoco = $(this).find(".foco").text();
            if (nombreFoco !== '') {
                var logro = $(this).find(".logro").text().replace('%', '').trim();

                var foco = {
                    idFoco: $(this).attr('data-idFoco'),
                    nombre: nombreFoco,
                    logro: logro
                };
                var accionesMejora = [];
                $(this).find(".accion-mejora").each(function () {
                    console.log(this);
                    accionesMejora.push({
                        accionMejora: $(this).attr('data-accionMejora'),
                        completado: $(this).attr('data-completado')
                    });
                });
                foco["accionesMejora"] = accionesMejora;
                focosPaso.push(foco);
            }
        });
        // console.log(focosPaso.length);
        // console.log(focosPaso);
        return focosPaso;
    }

    function obtenerParticipantes() {
        var participantes = [];

        $(getIds.registra_sesion_red_participantes + ' tbody > tr').each(function () {
            // console.log(this);
            participantes.push({
                idParticipante: $(this).attr("data-idparticipante"),
                tipoParticipante: $(this).attr("data-tipoParticipante"),
                rbd: $(this).attr("data-rbd"),
                presente: $(this).find(".check-participante").prop('checked')
            });
        });
        // console.log(participantes);
        return participantes;
    }

    function registraSesion(sender) {
        var isValid = true;

        var fechaProxima = '';
        if ($(getIds.registra_sesion_fecha_proxima).val() !== '') {
            fechaProxima = $(getIds.registra_sesion_fecha_proxima).val() + " " + $(getIds.registra_sesion_hora_proxima).val();
        }

        console.log(fechaProxima);

        // if ($(getIds.registra_sesion_fecha_proxima).val() === '') {
        //     came.main.setInvalidControl($(getIds.registra_sesion_fecha_proxima));
        //     isValid = false;
        // } else {
        //     came.main.setValidControl($(getIds.registra_sesion_fecha_proxima));
        // }

        // if ($(getIds.registra_sesion_hora_proxima).val() === '') {
        //     came.main.setInvalidControl($(getIds.registra_sesion_hora_proxima));
        //     isValid = false;
        // } else {
        //     came.main.setValidControl($(getIds.registra_sesion_hora_proxima));
        // }

        console.log($('input[name=' + getIds.registra_sesion_tipo_asesoria + ']').filter(":checked").val());
        if ($('input[name=' + getIds.registra_sesion_tipo_asesoria + ']').filter(":checked").val() === undefined) {
            came.main.setInvalidControl($('.registra-sesion-tipo-asesoria-group'));
            isValid = false;
        } else {
            came.main.setValidControl($('.registra-sesion-tipo-asesoria-group'));
        }

        var focosRegistra = obtenerFocosSesion();
        if (focosRegistra.length === 0) {
            came.main.setInvalidControl($(getIds.registra_sesion_focos));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.registra_sesion_focos));
        }

        if (isValid) {
            $.ajax({
                type: "POST",
                url: urls.api.setRegistraSesion,
                dataType: "json",
                contentType: 'application/json',
                data: JSON.stringify({
                    idSesion: $(getIds.modal_registrar_sesion).attr('data-id-sesion'),
                    fechaProximaReunion: fechaProxima,
                    tipoAsesoria: $('input[name=' + getIds.registra_sesion_tipo_asesoria + ']').filter(":checked").val(),
                    focos: focosRegistra
                })
            }).done(function (msg) {
                console.log("response done", msg);

                came.notify.showSucces("Sesion", "Su solicitud se ha procesado correctamente.");
                // cierro el modal.           
                $(getIds.modal_registrar_sesion).modal('hide');
                location.reload();
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                // TODO: aqui se debe agregar un mensaje en caso de error.
                came.notify.showError("Sesion", "Su solicitud se ha procesado con errores.");
                return false;
            });
        }
    }

    function registraSesionred(sender) {
        var isValid = true;

        var participantes = obtenerParticipantes();
        // if (participantes.filter(x => x.presente === 'true').length === 0){
        //     console.log('invalid');
        //     came.main.setInvalidControl($(getIds.registra_sesion_red_participantes));
        //     isValid = false;
        // } else {
        //     console.log('valid');
        //     came.main.setValidControl($(getIds.registra_sesion_red_participantes));
        // }

        var focosRegistra = obtenerFocosSesionRed();
        if (focosRegistra.length === 0) {
            came.main.setInvalidControl($(getIds.registra_sesion_red_focos));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.registra_sesion_red_focos));
        }

        if ($(getIds.registra_sesion_red_fecha_proxima).val()!= ''){
            var fechaProxima = $(getIds.registra_sesion_red_fecha_proxima).val() + " " + $(getIds.registra_sesion_red_hora_proxima).val();
            if ($(getIds.registra_sesion_fecha_proxima).val() === '') {
                came.main.setInvalidControl($(getIds.registra_sesion_red_fecha_proxima));
                isValid = false;
            } else {
                came.main.setValidControl($(getIds.registra_sesion_red_fecha_proxima));
            }
        }        

        if ($(getIds.registra_sesion_red_hora_proxima).val() === '') {
            came.main.setInvalidControl($(getIds.registra_sesion_red_hora_proxima));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.registra_sesion_red_hora_proxima));
        }

        console.log($('input[name=' + getIds.registra_sesion_red_tipo_asesoria + ']').filter(":checked").val());
        if ($('input[name=' + getIds.registra_sesion_red_tipo_asesoria + ']').filter(":checked").val() === undefined) {
            came.main.setInvalidControl($('.registra-sesion-red-tipo-asesoria-group'));
            isValid = false;
        } else {
            came.main.setValidControl($('.registra-sesion-red-tipo-asesoria-group'));
        }

        var objetivos = [];
        if ($(getIds.registra_sesion_red_objetivo1).val() === '') {
            came.main.setInvalidControl($(getIds.registra_sesion_red_objetivo1));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.registra_sesion_red_objetivo1));
            objetivos.push({
                idSesion: $(getIds.modal_registrar_sesion_red).attr('data-id-sesion'),
                numero: 1,
                objetivo: $(getIds.registra_sesion_red_objetivo1).val()
            });
        }

        if ($(getIds.registra_sesion_red_objetivo2).val() !== '') {
            objetivos.push({
                idSesion: $(getIds.modal_registrar_sesion_red).attr('data-id-sesion'),
                numero: 2,
                objetivo: $(getIds.registra_sesion_red_objetivo2).val()
            });
        }

        // console.log(JSON.stringify({
        //     idSesion: $(getIds.modal_registrar_sesion_red).attr('data-id-sesion'),
        //     fechaProximaReunion: fechaProxima,
        //     tipoAsesoria: $('input[name=' + getIds.registra_sesion_red_tipo_asesoria + ']').filter(":checked").val(),
        //     focos: focosRegistra,
        //     objetivos: objetivos,
        //     participantes: participantes
        // }));

        if (isValid) {
            $.ajax({
                type: "POST",
                url: urls.api.setRegistraSesionRed,
                dataType: "json",
                contentType: 'application/json',
                data: JSON.stringify({
                    idSesion: $(getIds.modal_registrar_sesion_red).attr('data-id-sesion'),
                    fechaProximaReunion: fechaProxima,
                    tipoAsesoria: $('input[name=' + getIds.registra_sesion_red_tipo_asesoria + ']').filter(":checked").val(),
                    focos: focosRegistra,
                    objetivos: objetivos,
                    participantes: participantes
                })
            }).done(function (msg) {
                console.log("response done", msg);
                came.notify.showSucces("Sesion", "Su solicitud se ha procesado correctamente.");
                // cierro el modal.           
                $(getIds.modal_registrar_sesion_red).modal('hide');
                location.reload();
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                // TODO: aqui se debe agregar un mensaje en caso de error.
                came.notify.showError("Sesion", "Su solicitud se ha procesado con errores.");
                return false;
            });
        }
    }

    function verSesion(sender) {

        var idSesion = $(sender).attr('data-id-sesion');

        $.ajax({
            type: "GET",
            url: urls.api.getSesionDetail,
            // dataType: "json",
            async: false,
            // contentType: 'application/json',
            data: jQuery.param({ idSesion: idSesion })
        }).done(function (response) {
            console.info("response done", response);

            $(getIds.ver_sesion_fecha_planificada).val(came.main.formatDate(response.fechaPlanificacion));
            $(getIds.ver_sesion_hora_planificada).val(came.main.formatTime(response.fechaPlanificacion));
            $(getIds.ver_sesion_fecha_realizada).val(came.main.formatDate(response.fechaRalizada));
            $(getIds.ver_sesion_hora_realizada).val(came.main.formatTime(response.fechaRalizada));

            if (response.tipoSesion === "Presencial") {
                $(getIds.ver_sesion_tipo_asesoria_presencial).prop('checked', 'checked');
            } else {
                $(getIds.ver_sesion_tipo_asesoria_remota).prop('checked', 'checked');
            }
            $(getIds.ver_sesion_focos).empty();
            response.focos.forEach(f => {
                agregarFocoSesion(f, $(getIds.ver_sesion_focos));
            });

            $(getIds.modal_ver_sesion).modal('show');
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            return false;
        });
    }

    function agregarFocoSesion(foco, sesionFocos, isEditable) {


        console.log(foco);
        focos.forEach(f => {
            if (f.idFoco === foco.idFoco) {
                console.log(f);
                var accionesPaso = '';
                f.accionesMejoras.forEach(a => {
                    if (foco.accionesMejoras) {
                        var aPaso = foco.accionesMejoras.find(x => x.idAccionesMejoras === a.idAccionesMejoras);
                        console.log(a);
                        console.log(aPaso);
                        var movimientosPaso = '';
                        a.movimientosClaves.forEach(m => {
                            var mPaso = aPaso.movimientosClaves.find(x => x.idMovimientosClaves == m.idMovimientosClaves);

                            var checkPaso = mPaso.completado ? 'checked' : '';
                            var disabledPaso = (isEditable != null && isEditable) && !mPaso.completado ? '' : 'disabled="disabled"';
                            console.log(disabledPaso);
                            movimientosPaso += '<tr>' +
                                '<td>' + m.nombre + '</td>' +
                                '<td class="text-center">' +
                                '<div class="custom-control custom-checkbox ml-2">' +
                                '<input type="checkbox" class="custom-control-input movimientos" id="' + m.idMovimientosClaves + '" ' + checkPaso + ' ' + disabledPaso + '>' +
                                '<label class="custom-control-label" for="' + m.idMovimientosClaves + '"></label>' +
                                '</div>' +
                                '</td>' +
                                '</tr>';
                        });
                        accionesPaso += '<table class="table-striped table table-sm mine table-bordered mb-0 accion-mejora" data-id-accion="' + a.idAccionesMejoras + '">' +
                            '<thead><tr><th class="" colspan="2">' + a.nombre + '</th></tr></thead>' +
                            '<tbody>' +
                            movimientosPaso +
                            '</tbody>' +
                            '</table>';
                    }
                });
                var targetId = new Date().getTime();
                var x1 = '<div class="card foco" data-id-foco="' + f.idFoco + '"><div class="card-header px-2 py-1" id="headingOne">' +
                    '<div class="row">' +
                    '<div class="col-7">' +
                    '<h2 class="mb-0">' +
                    '<a class="btn btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#' + targetId + '" aria-expanded="true" aria-controls="collapseOne">' +
                    '<strong>' + f.nombre + '</strong>' +
                    '</a>' +
                    '</h2>' +
                    '</div>' +
                    '<div class="col-5 ">' +
                    '<h2 class="mb-0">' +
                    '<a class="btn btn-link btn-block text-right" type="button" data-toggle="collapse" data-target="#' + targetId + '" aria-expanded="true" aria-controls="collapseOne">' +
                    '<b>Logrado: ' + foco.logrado + ' de ' + foco.total + '</b>' +
                    '</a>' +
                    '</h2>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '<div id="' + targetId + '" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">' +
                    '<div class="card-body px-3 pt-2"><div class="row"><div class="col-12 mb-3"><p class="mb-1">EID</p>' +
                    '<textarea class="form-control" id="exampleFormControlTextarea1" rows="3" disabled>' + f.descripcion + '.</textarea>' +
                    '</div><div class="col-12 table-responsive">' +
                    accionesPaso +
                    '</div></div></div></div></div>';
                $(sesionFocos).append(x1);
            }
        });
    }

    function agregarFocoSesionRed(foco, isEditable) {
        console.log(foco);

        $(getIds.registra_sesion_red_focos + ' tbody > tr').each(function () {
            var idFocoPaso = $(this).attr('data-idFoco');
            console.log(idFocoPaso);
            console.log(foco.idFoco);
            if (idFocoPaso === foco.idFoco) {
                console.log('se remueve');
                $(this).remove();
            }
        });

        var table = $(getIds.registra_sesion_red_focos);
        var row = $('<tr data-idFoco="' + foco.idFoco + '"></tr>');
        row.append($('<td></td>').append($('<span class="foco">' + foco.nombre + '</span>')));
        row.append($('<td></td>').append($('<span class="logro">' + foco.logro + '% </span>')));
        var acciones = '';
        foco.accionesMejora.forEach(a => {
            console.log(a);
            acciones += '<span class="accion-mejora" data-accionMejora="' + a.accionMejora.trim() + '" data-completado="' + a.completado + '">' + a.accionMejora.trim() +
                (a.completado == true ?
                    '<svg xmlns = "http://www.w3.org/2000/svg" width = "16" height = "16" fill = "currentColor" class="bi bi-check-circle text-success float-right" viewBox = "0 0 16 16">' +
                    '<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />' +
                    '<path d="M10.97 4.97a.235.235 0 0 0-.02.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05z" />' +
                    '</svg><br>' :
                    '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-exclamation-circle text-warning float-right" viewBox="0 0 16 16">' +
                    '<path d = "M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />' +
                    '<path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0zM7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 4.995z" />' +
                    '</svg><br>') + '</span>';
        });

        row.append($('<td></td>').append($('<span>' + acciones + '</span>')));
        table.append(row);

        // $(getIds.registra_sesion_red_focos + ' tbody > tr').each(function () {
        //     console.log(this);
        // });
    }

    function agregarFocoDetalleSesionRed(foco, isEditable) {
        console.log(foco);

        $(getIds.ver_sesion_red_focos_detalle + ' tbody > tr').each(function () {
            var idFocoPaso = $(this).attr('data-idFoco');
            console.log(idFocoPaso);
            console.log(foco.idFoco);
            if (idFocoPaso === foco.idFoco) {
                console.log('se remueve');
                $(this).remove();
            }
        });

        var table = $(getIds.ver_sesion_red_focos_detalle);
        var row = $('<tr data-idFoco="' + foco.idFoco + '"></tr>');
        row.append($('<td></td>').append($('<span class="foco">' + foco.nombre + '</span>')));
        row.append($('<td></td>').append($('<span class="logro">' + foco.logro + '% </span>')));
        var acciones = '';
        foco.accionesMejora.forEach(a => {
            console.log(a);
            acciones += '<span class="accion-mejora" data-accionMejora="' + a.accionMejora.trim() + '" data-completado="' + a.completado + '">' + a.accionMejora.trim() +
                (a.completado == true ?
                    '<svg xmlns = "http://www.w3.org/2000/svg" width = "16" height = "16" fill = "currentColor" class="bi bi-check-circle text-success float-right" viewBox = "0 0 16 16">' +
                    '<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />' +
                    '<path d="M10.97 4.97a.235.235 0 0 0-.02.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05z" />' +
                    '</svg><br>' :
                    '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-exclamation-circle text-warning float-right" viewBox="0 0 16 16">' +
                    '<path d = "M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />' +
                    '<path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0zM7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 4.995z" />' +
                    '</svg><br>') + '</span>';
        });

        row.append($('<td></td>').append($('<span>' + acciones + '</span>')));
        table.append(row);

        // $(getIds.registra_sesion_red_focos + ' tbody > tr').each(function () {
        //     console.log(this);
        // });
    }

    function agregarFocoSesionRedVer(foco, isEditable) {
        console.log(foco);

        $(getIds.ver_sesion_red_focos + ' tbody > tr').each(function () {
            var idFocoPaso = $(this).attr('data-idFoco');
            console.log(idFocoPaso);
            console.log(foco.idFoco);
            if (idFocoPaso === foco.idFoco) {
                console.log('se remueve');
                $(this).remove();
            }
        });

        var table = $(getIds.ver_sesion_red_focos);
        var row = $('<tr data-idFoco="' + foco.idFoco + '"></tr>');
        row.append($('<td></td>').append($('<span class="foco">' + foco.nombre + '</span>')));
        row.append($('<td></td>').append($('<span class="logro">' + foco.logro + '% </span>')));
        var acciones = '';
        foco.accionesMejora.forEach(a => {
            console.log(a);
            acciones += '<span class="accion-mejora" data-accionMejora="' + a.accionMejora.trim() + '" data-completado="' + a.completado + '">' + a.accionMejora.trim() +
                (a.completado == true ?
                    '<svg xmlns = "http://www.w3.org/2000/svg" width = "16" height = "16" fill = "currentColor" class="bi bi-check-circle text-success float-right" viewBox = "0 0 16 16">' +
                    '<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />' +
                    '<path d="M10.97 4.97a.235.235 0 0 0-.02.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05z" />' +
                    '</svg><br>' :
                    '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-exclamation-circle text-warning float-right" viewBox="0 0 16 16">' +
                    '<path d = "M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />' +
                    '<path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0zM7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 4.995z" />' +
                    '</svg><br>') + '</span>';
        });

        row.append($('<td></td>').append($('<span>' + acciones + '</span>')));
        table.append(row);
    }

    function clearModalVer(sender) {
        $(getIds.ver_sesion_fecha_planificada).val('');
        $(getIds.ver_sesion_hora_planificada).val('');
        $(getIds.ver_sesion_fecha_realizada).val('');
        $(getIds.ver_sesion_hora_realizada).val('');

        $(getIds.ver_sesion_tipo_asesoria_presencial).prop('checked', '');

        $(getIds.ver_sesion_tipo_asesoria_remota).prop('checked', '');

    }

    function clearModalRegistra(sender) {
        $(getIds.registra_sesion_fecha_proxima).val('');
        $(getIds.registra_sesion_hora_proxima).val('');
        $('input[name=' + getIds.registra_sesion_tipo_asesoria + ']').prop('checked', '');
    }

    function setViewAcces() {
        var menuPso = came.storage.getSessionStorage('menu');
        var subMenuProcess = menuPso.find(e => e.idModule == '2416198290916770826');
        console.log(subMenuProcess);
        if (subMenuProcess) {
            var planningView = subMenuProcess.subMenu.find(e => e.idSubModule == '2416254586126861336');
            console.log(planningView);
            readOnly = planningView.readOnly;
            if (readOnly) {
                // $("#content-network :input").prop("disabled", true);
                // $("#red-button-guardar").prop("disabled", true);
                $("#assignment-button-editar").hide();
            }
        }
    }

    function init() {
        setViewAcces();
        initListener();
        obtenerFocos();
        selectRegionDeprov();
    }

    return {
        init: init
    };
})();

$(document).ready(function () {
    console.log('came.planning.init()');
    came.planning.init();
});