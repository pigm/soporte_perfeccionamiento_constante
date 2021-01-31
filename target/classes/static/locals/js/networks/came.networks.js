'use strict';
var came = came || {};

came.networks = (function () {
    var readOnly = false;
    var popoverPaso;
    var popoverPasoSelect;

    var urls = {
        api: {
            getNetworks: came.contexto + "/networks/getNetworks",
            getProvincias: came.contexto + "/networks/getProvincias",
            getComunas: came.contexto + "/networks/getComunas",
            isValidStartDate: came.contexto + "/networks/isValidStartDate",
            isValidName: came.contexto + "/networks/isValidName",
            getEstablecimientos: came.contexto + "/networks/getEstablecimientos",
            setNetwork: came.contexto + "/networks/setNetwork",
            getNetworkDetail: came.contexto + "/networks/getNetworkDetail",
            setStatusEstablecimiento: came.contexto + "/networks/setStatusEstablecimiento",
            exportToExcel: came.contexto + "/networks/export/excel"
        }
    };

    var getIds = {
        input_nombre: '#input-nombre',
        selector_region: "#selector-region",
        selector_deprov: "#selector-deprov",
        input_rbd: "#input-rbd",
        selector_tipo_red: "#selector-tipo-red",
        input_fecha_rango: "#input-fecha-rango",

        panel_search: "#panel-search",
        panel_search_result: "#panel-search-result",
        table_networks: "#table-redes",
        count_networks: "#count-networks",
        find_networks: "#find-networks",
        red_find_again: "#red-find-again",

        red_fecha_inicio: "#red-fecha-inicio",
        red_input_nombre: "#red-input-nombre",
        red_selector_region: "#red-selector-region",
        red_selector_deprov: "#red-selector-deprov",
        red_selector_comuna: "#red-selector-comuna",
        red_selector_tipo_red: "#red-selector-tipo-red",
        red_selector_establecimientos: "#red-selector-establecimientos",

        red_detalle_nombre: "#red-detalle-nombre",
        red_detalle_selector_region: "#red-detalle-selector-region",
        red_detalle_selector_deprov: "#red-detalle-selector-deprov",
        red_detalle_selector_tipo_red: "#red-detalle-selector-tipo-red",
        red_detalle_count_networks: "#red-detalle-count-networks",
        red_detalle_table_networks: "#red-detalle-table-networks",

        button_new_network: "#button-new-network",
        modal_new_network: "#modal-new-network",
        red_button_guardar: "#red-button-guardar",
        red_set_status: ".red-set-status",

        detalle_red: ".detalle-red",
        modal_detalle_red: "#modal-detalle-red",

        red_button_editar: "#red-button-editar",
        export_excel_networks: "#export-excel-networks",

        button_close_popover: ".button-close-popover"
    };

    var initListener = function () {
        $(document)
            .on("click", getIds.find_networks, function () {
                findNetworks(this);
            })
            .on("change", getIds.selector_region, function () {
                getProvincias(this);
            })
            .on("change", getIds.red_selector_region, function () {
                getProvinciasRed(this);
            })
            .on("change", getIds.red_selector_deprov, function () {
                getComunasRed(this);
                getEstablecimientos(this);
            })
            .on("change", getIds.red_selector_comuna, function () {
                getEstablecimientos(this);
            })
            .on("change", getIds.red_selector_tipo_red, function () {
                getEstablecimientos(this);
            })
            .on("change", getIds.red_fecha_inicio, function () {
                // validarFechaInicio(this);
            })
            .on("click", getIds.button_new_network, function () {
                showNewNetwork(this);
            })
            .on("click", getIds.red_button_guardar, function () {
                setNetwork(this);
            })
            .on("click", getIds.detalle_red, function () {
                showDetailNetwork(this);
            })
            .on("click", getIds.red_find_again, function () {
                findNetworksAgain(this);
            })
            .on("hidden.bs.modal", getIds.modal_new_network, function () {
                clearModal(this);
            })
            .on("click", getIds.red_set_status, function () {
                setStatusEstablecimiento(this);
            })
            .on("click", getIds.red_button_editar, function () {
                editNetwork(this);
            })
            .on("click", getIds.export_excel_networks, function () {
                getExcel(this);
            })
            .on("click", getIds.button_close_popover, function () {
                closePopover(this);
            });
    };

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

    function getProvinciasRed(sender) {

        $(getIds.red_selector_deprov + " option").each(function () {
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
                $(getIds.red_selector_deprov).append($('<option selected>').val('').text('Seleccionar'));
                for (var i = 0; i < response.length; i++) {
                    $(getIds.red_selector_deprov).append($('<option>').val(response[i].value).text(response[i].displayText));
                }
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
        } else {
            $(getIds.red_selector_deprov).append($('<option selected>').val('').text('Seleccionar'));
        }
    }

    function getComunasRed(sender) {

        $(getIds.red_selector_comuna + " option").each(function () {
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
                $(getIds.red_selector_comuna).append($('<option selected>').val('').text('Seleccionar'));
                for (var i = 0; i < response.length; i++) {
                    $(getIds.red_selector_comuna).append($('<option>').val(response[i].value).text(response[i].displayText));
                }
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
        } else {
            $(getIds.red_selector_comuna).append($('<option selected>').val('').text('Seleccionar'));
        }
    }

    // function validarFechaInicio(sender){
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
    //             if (response){
    //                 came.main.setValidControl($(sender));                    
    //             } else{
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
            result = response;
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
            return false;
        });

        return result;
    }

    function showNewNetwork(sender) {
        if (readOnly) {
            came.notify.showError("Redes", "No tienes permitido crear o modificar redes.");
        } else {
            if (isValidDate()) {
                selectRegionDeprovModal();
                $(getIds.modal_new_network).modal('show');
            } else {
                showOutOfRangue(sender);
            }
        }
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

    function getEstablecimientos(sender) {

        $(getIds.red_selector_establecimientos + " option").each(function () {
            $(this).remove();
        });

        var idRegion = $(getIds.red_selector_region).val();
        var idDeprov = $(getIds.red_selector_deprov).val();
        var idComuna = $(getIds.red_selector_comuna).val();
        var idTipoRed = $(getIds.red_selector_tipo_red).val();

        $.ajax({
            type: "GET",
            url: urls.api.getEstablecimientos,
            dataType: "json",
            async: false,
            contentType: 'application/json',
            data: { idRegion: idRegion, idDeprov: idDeprov, idComuna: idComuna, idTipoRed: idTipoRed }
        }).done(function (response) {
            // console.log("response done", JSON.stringify(response));
            for (var i = 0; i < response.length; i++) {
                $(getIds.red_selector_establecimientos).append($('<option class="popover-testa" data-content="' + response[i].displayText + '">').val(response[i].value).text(response[i].displayText));
            }
            $(getIds.red_selector_establecimientos).bootstrapDualListbox('refresh');

            $('.popover-testa').on("mouseover", function (e) {
                $(this).popover({
                    placement: 'top',
                    container: 'body',
                    trigger: 'hover',
                    html: true,
                    sanitize: false
                });
                $(this).popover('show');
            });

            $('.popover-testa').dblclick(function () {
                $(this).popover('hide');
            });

            console.log($(getIds.red_selector_establecimientos).val());
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
            return false;
        });
    }

    function findNetworks(sender) {

        $.ajax({
            type: "GET",
            url: urls.api.getNetworks,
            //dataType: "json",
            // async: false,
            contentType: 'application/json',
            data: jQuery.param({
                nombre: $(getIds.input_nombre).val(),
                rbd: $(getIds.input_rbd).val(),
                regionId: $(getIds.selector_region).val(),
                deprovId: $(getIds.selector_deprov).val(),
                tipoRedId: $(getIds.selector_tipo_red).val(),
                startDate: $(getIds.input_fecha_rango).val().split('-')[0].trim().replace(/[/]/g, '-'),
                endDate: $(getIds.input_fecha_rango).val().split('-')[1].trim().replace(/[/]/g, '-')
            })
        }).done(function (response) {
            if (typeof (response) !== "string") {
                $(getIds.count_networks).text(response.length);
                $(getIds.table_networks + ' tbody tr').remove();
                var tablePaso = $(getIds.table_networks);
                loadRedes(tablePaso, response);

                $(getIds.panel_search).css("display", "none");
                $(getIds.panel_search_result).css("display", "");
            } else {
                came.notify.showError("Asignar supervisores", "Su solicitud se ha procesado con errores.");
            }
        });
    }

    function getExcel(sender) {
        console.log(sender);

        var link = document.createElement('a');
        link.href = (urls.api.exportToExcel +
            "?nombre=" + $(getIds.input_nombre).val() +
            "&rbd=" + $(getIds.input_rbd).val() +
            "&regionId=" + $(getIds.selector_region).val() +
            "&deprovId=" + $(getIds.selector_deprov).val() +
            "&tipoRedId=" + $(getIds.selector_tipo_red).val() +
            "&startDate=" + $(getIds.input_fecha_rango).val().split('-')[0].trim().replace(/[/]/g, '-') +
            "&endDate=" + $(getIds.input_fecha_rango).val().split('-')[1].trim().replace(/[/]/g, '-'));
        console.log(link.href);
        link.click();
    }

    function findNetworksAgain(sender) {
        console.log(sender);
        $(getIds.panel_search).css("display", "");
        $(getIds.panel_search_result).css("display", "none");
    }

    function loadRedes(table, elements) {
        console.log(elements);
        if (elements) {
            for (var i = 0; i < elements.length; i++) {
                var row = $('<tr></tr>');

                row.append($('<td></td>').append('<a href="#" class="detalle-red" data-id-red="' + elements[i].idRed + '">' + elements[i].nombre + '</a>'));

                row.append($('<td></td>').append($('<span />').text(elements[i].region).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].deprov).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].tipoRed).html()));
                row.append($('<td></td>').append($('<span />').text(came.main.formatDate(elements[i].fechaCreacion)).html()));
                var dataContent = "";
                for (var s = 0; s < elements[i].establecimientosList.length; s++) {
                    dataContent += '<strong><small>' + elements[i].establecimientosList[s] + '</small></strong></br>'
                }
                var aPaso = '<a href="#" data-placement="top" data-toggle="popover" data-trigger="hover" data-content="' + dataContent + '">' + elements[i].establecimientos + '</a>';
                row.append($('<td></td>').append(aPaso));

                $(table).append(row);
            }
            initPopover();
            came.main.initList(table);
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

    function showDetailNetwork(sender) {
        console.log(sender);
        var idRed = $(sender).attr('data-id-red');
        if (idRed != null) {
            $.ajax({
                type: "GET",
                url: urls.api.getNetworkDetail,
                dataType: "json",
                async: false,
                contentType: 'application/json',
                data: { idRed: idRed }
            }).done(function (response) {
                console.log("response done", JSON.stringify(response));
                $(getIds.red_button_editar).attr('data-id-red', idRed);

                $(getIds.red_detalle_nombre).text(response.nombre);
                $(getIds.red_detalle_selector_region).text(response.region);
                $(getIds.red_detalle_selector_deprov).text(response.deprov);
                $(getIds.red_detalle_selector_tipo_red).text(response.tipoRed);
                $(getIds.red_detalle_count_networks).text(response.establecimientos.length);

                $(getIds.red_detalle_table_networks + ' tbody tr').remove();

                var table = $(getIds.red_detalle_table_networks);
                for (var i = 0; i < response.establecimientos.length; i++) {
                    var row = $('<tr></tr>');
                    row.append($('<td></td>').append($('<span />').text(response.establecimientos[i].displayText).html()));
                    var buttonID = response.establecimientos[i].value;
                    var checked = response.establecimientos[i].status ? 'checked' : '';
                    var readOnlyPaso = readOnly ? 'disabled' : '';
                    var statusPaso = "<span class='custom-control custom-switch text-center'> <input type='checkbox' class='custom-control-input red-set-status'" + " data-id-red='" + response.idRed + "' " +
                        " id='" + buttonID + "' " + checked + " select " + readOnlyPaso + "> <label class='custom-control-label' for='" + buttonID + "'></label></span>"
                    row.append($('<td></td>').append(statusPaso));

                    // row.append('<td class="text-center">' +
                    //     '<div class= "custom-control custom-switch">' +
                    //     '<input type="checkbox" class="custom-control-input red-set-status" data-id-red="' + response.idRed + '" id="' + response.establecimientos[i].value + '" checked select>' +
                    //     '<label class="custom-control-label"></label>' +
                    //     '</div></td>');
                    console.log(row.html());
                    $(table).append(row);
                }


                $(getIds.modal_detalle_red).modal('show');
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
        }
    }

    function editNetwork(sender) {
        console.log(sender);
        var idRed = $(sender).attr('data-id-red');
        if (idRed != null) {
            $.ajax({
                type: "GET",
                url: urls.api.getNetworkDetail,
                dataType: "json",
                async: true,
                contentType: 'application/json',
                data: { idRed: idRed }
            }).done(function (response) {
                console.log("response done", JSON.stringify(response));
                console.log(came.main.formatDate(response.fechaCreacion.split('T')[0]));
                $(getIds.red_fecha_inicio).val(response.fechaCreacion.split('T')[0]);
                $(getIds.red_input_nombre).val(response.nombre);
                $(getIds.red_selector_region).val(response.idRegion);
                $(getIds.red_selector_region).change();
                $(getIds.red_selector_deprov).val(response.idDeprov);
                $(getIds.red_selector_deprov).change();
                $(getIds.red_selector_comuna).val(response.idComuna);
                $(getIds.red_selector_tipo_red).val(response.idTipoRed);
                $(getIds.red_selector_tipo_red).change();
                var items = [];
                for (var i = 0; i < response.establecimientos.length; i++) {
                    var itemId = response.establecimientos[i].value;
                    items.push(itemId);
                }

                $(getIds.red_selector_establecimientos).val(items);
                $(getIds.red_selector_establecimientos).bootstrapDualListbox('refresh');

                $(getIds.modal_detalle_red).modal('hide');
                $(getIds.modal_new_network).modal('show');

            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
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

    function selectRegionDeprovModal() {
        var usuarioPaso = came.env.getEnvUser();
        if (usuarioPaso) {
            $(getIds.red_selector_region).val(usuarioPaso.idRegion);
            $(getIds.red_selector_region).change();
            $(getIds.red_selector_deprov).val(usuarioPaso.idDeprov);
            $(getIds.red_selector_deprov).change();

            if (usuarioPaso.nombrePerfilNivel === "regional") {
                $(getIds.red_selector_region).prop('disabled', 'disabled');
            } else if (usuarioPaso.nombrePerfilNivel === "provincial") {
                $(getIds.red_selector_region).prop('disabled', 'disabled');
                $(getIds.red_selector_deprov).prop('disabled', 'disabled');
            } else {
                $(getIds.red_selector_region).prop('disabled', false);
                $(getIds.red_selector_deprov).prop('disabled', false);
            }
        }
    }

    function validarNombreRed(nombreRed) {
        var result = false;

        if (nombreRed !== '') {
            $.ajax({
                type: "GET",
                url: urls.api.isValidName,
                async: false,
                //contentType: 'application/json',
                data: jQuery.param({ nombreRed: nombreRed })
            }).done(function (response) {
                result = response;
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                result = false;
            });
        }

        return result;
    }

    function setNetwork(sender) {
        var isValid = true;
        var netPaso = {
            idRed: '',
            fechaCreacion: $(getIds.red_fecha_inicio).val(),
            nombre: $(getIds.red_input_nombre).val(),
            idRegion: $(getIds.red_selector_region).val(),
            idDeprov: $(getIds.red_selector_deprov).val(),
            idComuna: $(getIds.red_selector_comuna).val(),
            idTipoRed: $(getIds.red_selector_tipo_red).val(),
            establecimientos: $(getIds.red_selector_establecimientos).val()
        };

        console.log(netPaso);
        if (netPaso.fechaCreacion === '') {
            came.main.setInvalidControl($(getIds.red_fecha_inicio));
            isValid = false;
        } else {
            // if(came.main.isControlValid($(getIds.red_fecha_inicio))) {
            came.main.setValidControl($(getIds.red_fecha_inicio));
            // } else{
            //     came.main.setInvalidControl($(getIds.red_fecha_inicio));
            //     isValid = false;
            // }
        }
        var isValidNamePaso = validarNombreRed(netPaso.nombre);
        console.log(isValidNamePaso);
        if (netPaso.nombre === '' || !(isValidNamePaso)) {
            came.main.setInvalidControl($(getIds.red_input_nombre));
            isValid = false;
        } else {
            console.log("Nombre valido!!!");
            came.main.setValidControl($(getIds.red_input_nombre));
        }

        if (netPaso.idRegion === '') {
            came.main.setInvalidControl($(getIds.red_selector_region));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.red_selector_region));
        }

        if (netPaso.idDeprov === '') {
            came.main.setInvalidControl($(getIds.red_selector_deprov));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.red_selector_deprov));
        }

        // if (netPaso.idComuna === '') {
        //     came.main.setInvalidControl($(getIds.red_selector_comuna));
        //     isValid = false;
        // } else {
        //     came.main.setValidControl($(getIds.red_selector_comuna));
        // }

        if (netPaso.idTipoRed === '') {
            came.main.setInvalidControl($(getIds.red_selector_tipo_red));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.red_selector_tipo_red));
        }

        if (netPaso.establecimientos === null) {
            came.main.setInvalidControl($(getIds.red_selector_establecimientos));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.red_selector_establecimientos));
        }

        if (isValid) {
            $.ajax({
                type: "POST",
                url: urls.api.setNetwork,
                dataType: "json",
                contentType: 'application/json',
                data: JSON.stringify(netPaso)
            }).done(function (msg) {
                console.log("response done", msg);
                came.notify.showSucces("Red", "Su solicitud se ha procesado correctamente.");
                location.reload();
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                // TODO: aqui se debe agregar un mensaje en caso de error.
                came.notify.showError("Red", "Su solicitud se ha procesado con errores.");
                return false;
            });
        }

    }

    function clearModal(sender) {
        $(getIds.red_fecha_inicio).val('');
        $(getIds.red_input_nombre).val('');
        $(getIds.red_selector_region).val('');
        $(getIds.red_selector_deprov).val('');
        $(getIds.red_selector_comuna).val('');
        $(getIds.red_selector_tipo_red).val('');
        $(getIds.red_selector_establecimientos).val('');
        $(getIds.red_selector_establecimientos + " option").each(function () {
            $(this).remove();
        });
    }

    function setStatusEstablecimiento(sender) {
        console.log(sender);

        var idRed = $(sender).attr('data-id-red');
        var establecimiento = $(sender).attr('id');
        var status = $(sender).prop('checked');

        $.ajax({
            type: "POST",
            url: urls.api.setStatusEstablecimiento,
            //dataType: "json",
            //contentType: 'application/json',
            data: jQuery.param({ idRed: idRed, establecimiento: establecimiento, status: status })
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

    function closePopover(sender) {
        console.log(sender);
        $(popoverPaso).popover('hide');
    }

    function setViewAcces() {
        var menuPso = came.storage.getSessionStorage('menu');
        var subMenuProcess = menuPso.find(e => e.idModule == '2416198290916770826');
        console.log(subMenuProcess);
        if (subMenuProcess) {
            var netw = subMenuProcess.subMenu.find(e => e.idSubModule == '2416254586126861333');
            console.log(netw);
            readOnly = netw.readOnly;
            if (readOnly) {
                $("#content-network :input").prop("disabled", true);
                $("#red-button-guardar").prop("disabled", true);
                $("#red-button-editar").hide();
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
    console.log('came.networks.init()');
    came.networks.init();
});
