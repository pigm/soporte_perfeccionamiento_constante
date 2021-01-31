'use strict';
var came = came || {};

came.messenger = (function () {

    var selectHtml = '<select id="selector-deprov" multiple name="state[]" class="demo-default" placeholder="Busca o seleciona provincias." style="width:100%; ::placeholder:100%"></select>';

    var urls = {
        api: {
            getProvincias: came.contexto + "/messenger/getProvincias",
            setMessage: came.contexto + "/messenger/setMessage",
            getMessages: came.contexto + "/messenger/getMessages",
            getMessageDetail: came.contexto + "/messenger/getMessageDetail",
            getProvinciasFilter: came.contexto + "/messenger/getProvinciasFilter",


        }
    };

    var getIds = {

        button_new_message: "#button-new-message",
        message_button_guardar: "#message-button-guardar",
        message_selector_perfil: "#message-selector-perfil",
        message_selector_region: "#message-selector-region",
        message_selector_deprov: "#selector-deprov",
        table_messages: "#table-mensajeria",
        asunto_mensaje: "#asunto-mensaje",
        contenido_mensaje: "#contenido-mensaje",
        modal_new_message: "#modal-new-message",
        detail_message: ".detail-message",
        message_title: "#message-title",
        modal_detail_message: "#modal-detail-message",
        body_message: "#body-message",
        modal_check_message: "#modal-check-message",
        message_selector_filter_perfil: "#message-selector-filter-perfil",
        message_selector_filter_region: "#message-selector-filter-region",
        message_selector_filter_deprov: "#message-selector-filter-deprov"
    }

    var initListener = function () {
        $(document)
            .on("change", getIds.message_selector_region, function () {
                getProvincias(this);
            })
            .on("click", getIds.button_new_message, function () {
                $(getIds.modal_new_message).modal('show');
            })
            .on("click", getIds.message_button_guardar, function () {
                setMessage(this);
            }).on("click", getIds.detail_message, function () {
                showDetailMessage(this);
            }).on("change", getIds.message_selector_filter_perfil, function () {
                getMessages(this);
            }).on("change", getIds.message_selector_filter_region, function () {
                getProvinciasFilter(this);
                getMessages(this);
            }).on("change", getIds.message_selector_filter_deprov, function () {
                getMessages(this);
            })
            .on("keyup", getIds.contenido_mensaje, function () {
                countContentMesg(this);
            });
    }

    function countContentMesg(sender){
        // console.log($(getIds.contenido_mensaje).val().length);
        var count = 5000 - $(getIds.contenido_mensaje).val().length;
        $("#contenido-mensaje-count").text(count);
    }


    function getProvinciasFilter(sender) {

        $(getIds.message_selector_filter_deprov + " option").each(function () {
            $(this).remove();
        });

        var idRegion = $(sender).val();
        if (idRegion !== '') {
            $.ajax({
                type: "GET",
                url: urls.api.getProvinciasFilter,
                dataType: "json",
                async: false,
                //contentType: 'application/json',
                data: { idRegion: idRegion }
            }).done(function (response) {
                console.log("response done", JSON.stringify(response));
                $(getIds.message_selector_filter_deprov).append($('<option selected>').val('').text('Seleccionar'));
                for (var i = 0; i < response.length; i++) {
                    $(getIds.message_selector_filter_deprov).append($('<option>').val(response[i].value).text(response[i].displayText));
                }
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
        } else {
            $(getIds.message_selector_filter_deprov).append($('<option selected>').val('').text('Seleccionar'));
        }
    }



    function getMessages() {
        var idPerfil = $(getIds.message_selector_filter_perfil).val();

        $.ajax({
            type: "GET",
            url: urls.api.getMessages,
            //dataType: "json",
            // async: false,
            contentType: 'application/json',
            data: jQuery.param({
                idRegion: $(getIds.message_selector_filter_region).val(),
                idProvincia: $(getIds.message_selector_filter_deprov).val(),
                idPerfil: $(getIds.message_selector_filter_perfil).val(),
                leido: null

            })
        }).done(function (response) {
            if (typeof (response) !== "string") {
                var tablePaso = $(getIds.table_messages);
                $(getIds.table_messages + ' tbody tr').remove();

                loadMessages(tablePaso, response);
                if ($(tablePaso).DataTable() === null) {
                    came.main.initList(tablePaso);
                }


                $(getIds.panel_search).css("display", "none");
                $(getIds.panel_search_result).css("display", "");
            } else {
                came.notify.showError("Asignar supervisores", "Su solicitud se ha procesado con errores.");
            }
        });
    }


    function loadMessages(table, elements) {

        if (elements) {
            for (var i = 0; i < elements.length; i++) {
                var row = $('<tr></tr>');
                var fechaLeido = elements[i].fechaLeido == null ? "Mensaje no leído" : came.main.formatDate(elements[i].fechaLeido);
                var destinatario = elements[i].idUsuarioregistro == null ? "Mensaje sin destinatario" : elements[i].idUsuarioregistro;
                row.append($('<td></td>').append('<a href="#" class="detail-message" data-id-message="' + elements[i].idMensaje + '">' + elements[i].asunto + '</a>'));
                row.append($('<td></td>').append($('<span />').text(elements[i].perfil.nombre).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].nombreRegion).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].nombreProvincia).html()));
                row.append($('<td></td>').append($('<span />').text(destinatario).html()));
                row.append($('<td></td>').append($('<span />').text(came.main.formatDate(elements[i].fechaCreacion)).html()));
                row.append($('<td></td>').append($('<span />').text(fechaLeido).html()));
                $(table).append(row);
            }
            //came.main.initList(table);

        }
        //came.main.initList(table);
    }

    function getProvincias(sender) {
        console.log($(sender).val());

        $(getIds.message_selector_deprov + " option").each(function () {
            $(this).remove();
        });

        var idRegion = $(sender).val();

        if (idRegion && idRegion !== '') {
            $.ajax({
                type: "GET",
                url: urls.api.getProvincias,
                dataType: "json",
                async: false,
                //contentType: 'application/json',
                data: { idRegion: idRegion + "" }
            }).done(function (response) {

                console.log("response done", JSON.stringify(response));

                let select = $(selectHtml);
                for (var i = 0; i < response.length; i++) {

                    select.append($('<option>').val(response[i].value).text(response[i].displayText));
                    // $("#selector-deprov").append($('<option>').val(response[i].value).text(response[i].displayText));
                }

                $("#contenedorsd .selectize-control").remove();
                $("#contenedorsd .selectized").remove();
                $("#contenedorsd").append(select);
                select.selectize({
                    create: true
                });
            }).fail(function (jqXHR, textStatus) {
                came.notify.showError("Mensajeria", "Su solicitud se ha procesado con errores.");
                return false;
            });
        }

    }

    function setMessage(sender) {

        console.log($(getIds.message_selector_perfil).val());

        var isValid = true;
        var msgPaso = {
            asunto: $(getIds.asunto_mensaje).val(),
            mensaje: $(getIds.contenido_mensaje).val(),
            idPerfil: $(getIds.message_selector_perfil).val(),
            idRegion: $(getIds.message_selector_region).val(),
            idProvincia: $(getIds.message_selector_deprov).val(),
            idComuna: $(getIds.red_selector_comuna).val()
        };

        if (msgPaso.asunto === '') {
            came.main.setInvalidControl($(getIds.asunto_mensaje));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.asunto_mensaje));
        }

        if (msgPaso.contenido === '') {
            came.main.setInvalidControl($(getIds.contenido_mensaje));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.contenido_mensaje));
        }

        if ($(getIds.contenido_mensaje).val().length > 5000){
            came.main.setInvalidControl($(getIds.contenido_mensaje));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.contenido_mensaje));
        }

        if (msgPaso.idPerfil === '') {
            came.main.setInvalidControl($(getIds.message_selector_perfil));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.message_selector_perfil));
        }

        if (msgPaso.idRegion === '') {
            came.main.setInvalidControl($(getIds.message_selector_region));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.message_selector_region));
        }

        if (msgPaso.idProvincia === '') {
            came.main.setInvalidControl($(getIds.message_selector_deprov));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.message_selector_deprov));
        }
        
        if (isValid) {
            $.ajax({
                type: "POST",
                url: urls.api.setMessage,
                dataType: "json",
                contentType: 'application/json',
                data: JSON.stringify(msgPaso)
            }).done(function (msg) {
                console.log("response done", msg);
                came.notify.showSucces("Mensajeria", "Su solicitud se ha procesado correctamente.");
                location.reload();
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                // TODO: aqui se debe agregar un mensaje en caso de error.
                came.notify.showError("Mensajeria", "Su solicitud se ha procesado con errores.");
                return false;
            });
        }

    }

    function selectRegionDeprov() {
        var usuarioPaso = came.env.getEnvUser();
        if (usuarioPaso) {
            $(getIds.message_selector_filter_region).val(usuarioPaso.idRegion);
            $(getIds.message_selector_filter_region).change();
            $(getIds.message_selector_filter_deprov).val(usuarioPaso.idDeprov);
            $(getIds.message_selector_filter_deprov).change();

            if (usuarioPaso.nombrePerfilNivel === "regional") {
                $(getIds.message_selector_filter_region).prop('disabled', 'disabled');
            } else if (usuarioPaso.nombrePerfilNivel === "provincial") {
                $(getIds.message_selector_filter_region).prop('disabled', 'disabled');
                $(getIds.message_selector_filter_deprov).prop('disabled', 'disabled');
            } else {
                $(getIds.message_selector_filter_region).prop('disabled', false);
                $(getIds.message_selector_filter_deprov).prop('disabled', false);
            }

            console.log(usuarioPaso.idPerfil);
            if (usuarioPaso.idPerfil !== "2416264829766468643") {
                $(getIds.message_selector_filter_perfil).val(usuarioPaso.idPerfil);
                $(getIds.message_selector_filter_perfil).prop('disabled', 'disabled');
            }
        }
    }

    function showDetailMessage(sender) {
        console.log(sender);
        var idMessage = $(sender).attr('data-id-message');

        if (idMessage != null) {
            $.ajax({
                type: "GET",
                url: urls.api.getMessageDetail,
                dataType: "json",
                async: false,
                contentType: 'application/json',
                data: { idMessage: idMessage }
            }).done(function (response) {
                console.log("response done", JSON.stringify(response));

                $(getIds.message_title).text(response.asunto);
                $(getIds.body_message).text(response.mensaje);


                $(getIds.modal_detail_message).modal('show');
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
        }
    }

    function cleanRegion() {
        $(getIds.message_selector_region + " option").each(function () {
            $(this).remove();
        });
    }    

    function init() {
        initListener();
        selectRegionDeprov();
        //cleanRegion();
        getMessages();
    }

    return {
        init: init
    };

})();

$(document).ready(function () {
    console.log('came.messenger.init()');
    came.messenger.init();
});