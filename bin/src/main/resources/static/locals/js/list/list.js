'use strict';
var came = came || {};

came.list = (function () {

    var popoverPaso = null;
    //#region Privador  
    var urls = {
        api: {
            listItems: came.contexto + "/lists/listItems",
            getItems: came.contexto + "/lists/getItems",
            setItem: came.contexto + "/lists/setItem",
            setStatus: came.contexto + "/lists/setItemStatus",
            removeItem: came.contexto + "/lists/removeItem"
        }
    };

    var getIds = {
        nombreItem: ".textNombreElement",
        agregar: "#btnAgregar",
        setItem: ".setItem",
        view_elements: ".show-elements",
        modalElements: "#ListaElementos",
        lista: "#ListaElementosData",
        collapseItem: "#collapseItem",
        cancelar: "#btnCancelar",
        habilitar: ".habilitar",
        changeName: ".changeName",
        newItem: ".newItem",
        removeItem: ".removeItem",
        confirmRemoveItem: ".confirmRemoveItem",
        closePopover: '.closePopover',
        validators: {
            errorNombre: "#errorNombre",
            errorDescripcion: "#errorDescripcion",
            errorNivel: "#errorNivel"
        }
    };

    var initListener = function () {
        $(document)
            .on("click", getIds.agregar, function () {
                newItem(this);
            })
            .on("click", getIds.view_elements, function () {
                showElements(this);
            })
            .on("click", getIds.setItem, function () {
                setItem(this);
            })
            .on("click", getIds.cancelar, function () {
                cancelar(this);
            })
            .on("click", getIds.habilitar, function () {
                setItemStatus(this);
            })
            .on("click", getIds.newItem, function () {
                changeName(this);
            })
            .on("click", getIds.changeName, function () {
                changeName(this);
            })
            .on("click", getIds.removeItem, function () {
                removeItemAdv(this);
            })
            .on("click", getIds.closePopover, function () {
                closePopover(this);
            })
            .on("click", getIds.confirmRemoveItem, function () {
                confirmRemoveItem(this);
            })
            .on("hidden.bs.modal", getIds.modalElements, function () {
                location.reload();
            });
    };

    var showElements = function (sender) {
        console.log($(sender).attr('data-idList'));

        if ($(sender).attr('data-idList') !== '' && $(sender).attr('data-idList') !== undefined) {
            initList($(sender).attr('data-idList'));

            $(getIds.agregar).attr('idLista', $(sender).attr('data-idList'));
            $(getIds.modalElements).modal('show');
        } else {
            alert("Aqui un error.")
        }
    };

    var agregarItem = function (sender) {
        console.log(sender);
        $(getIds.agregar).attr('idLista');

        $(getIds.collapseItem).collapse('show');
    };

    var setItem = function (sender) {
        var isValid = true;

        console.log(sender);
        console.log($(getIds.nombreItem).val());

        var nombreItem = $(getIds.nombreItem).val();
        var idLista = $(getIds.nombreItem).attr('data-idLista');
        var idElementoLista = $(getIds.nombreItem).attr('data-idElementoLista');
        var statusPaso = $(idElementoLista).is(":checked");

        console.log(statusPaso);

        if ($(getIds.nombreItem).val() === '') {
            console.log($(getIds.nombreItem).val());
            came.main.isValidControl($(getIds.nombreItem));
            isValid = false;
        } else {
            came.main.isValidControl($(getIds.nombreItem));
        }

        if (isValid) {
            $.ajax({
                type: "POST",
                url: urls.api.setItem,
                dataType: "json",
                contentType: 'application/json',
                data: JSON.stringify({ idElementoLista: idElementoLista, idLista: idLista, nombre: nombreItem, status: statusPaso })
            }).done(function (msg) {
                console.log("response done", msg);
                // cierro el modal.
                $(getIds.nombreItem).attr('idLista', '');
                $(getIds.nombreItem).val('');

                if (popoverPaso) {
                    $(popoverPaso).popover('hide');
                }

                initList($(getIds.agregar).attr('idLista'));

                $(getIds.collapseItem).collapse('hide');
                came.notify.showSucces("Lista", "Su solicitud se ha procesado correctamente.");
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                // TODO: aqui se debe agregar un mensaje en caso de error.
                came.notify.showError("Lista", "Su solicitud se ha procesado con errores.");
                return false;
            });
        } else {
            return false;
        }
    }

    var setItemStatus = function (sender) {

        console.log(sender);

        var idElementoLista = $(sender).attr('id');

        console.log(JSON.stringify({ idElementoLista: idElementoLista, status: $(sender).is(":checked") }));

        $.ajax({
            type: "POST",
            url: urls.api.setStatus,
            dataType: "json",
            contentType: 'application/json',
            data: JSON.stringify({ idElementoLista: idElementoLista, status: $(sender).is(":checked") })
        }).done(function (msg) {
            console.log("response done", msg);
            initList($(getIds.agregar).attr('idLista'));
            came.notify.showSucces("Lista", "Su solicitud se ha procesado correctamente.");
            // cierro el modal.           
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            // TODO: aqui se debe agregar un mensaje en caso de error.
            came.notify.showError("Lista", "Su solicitud se ha procesado con errores.");
            return false;
        });
    }

    var confirmRemoveItem = function (sender) {

        console.log(sender);
        var idElementoLista = $(sender).attr('data-idElementoLista');
        console.log(JSON.stringify({ idElementoLista: idElementoLista }));

        $.ajax({
            type: "POST",
            url: urls.api.removeItem,
            dataType: "json",
            contentType: 'application/json',
            data: JSON.stringify({ idElementoLista: idElementoLista })
        }).done(function (msg) {
            console.log("response done", msg);
            $(popoverPaso).popover('hide');
            initList($(getIds.agregar).attr('idLista'));
            came.notify.showSucces("Lista", "Su solicitud se ha procesado correctamente.");
            // cierro el modal.           
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            // TODO: aqui se debe agregar un mensaje en caso de error.
            came.notify.showError("Lista", "Su solicitud se ha procesado con errores.");
            return false;
        });
    }

    var cancelar = function () {
        $(getIds.nombreItem).attr('idLista', '');
        $(getIds.nombreItem).val('');

        initList($(getIds.agregar).attr('idLista'));

        $(getIds.collapseItem).collapse('hide');
    }

    var initList = function (idList) {

        $.ajax({
            type: "GET",
            url: urls.api.getItems,
            dataType: "json",
            async: false,
            contentType: 'application/json',
            data: { idList: idList }
        }).done(function (response) {
            console.log("response done", response);

            $('#tableItemsList tbody tr').remove();

            var table = $('#tableItemsList');


            for (var i = 0; i < response.length; i++) {
                var row = $('<tr></tr>');

                var spanOne = $('<span />').text(response[i].nombre);

                var deletePaso = '<a href="#" class="float-right text-danger removeItem" data-placement="top" data-original-title="Seguro que quieres <br> eliminar el elemento" data-idElementoLista="' + response[i].idElementoLista + '">' +
                    '<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">' +
                    '<path fill-rule="evenodd" d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z" />' +
                    '</svg>' +
                    '</a>';

                $(spanOne).append(deletePaso);

                var editPaso = '<a href="#" class="changeName float-right mr-3" title="" data-original-title="Nombre elemento" data-itemNombre="' + response[i].nombre + '" data-idLista="' + response[i].idLista + '" data-idElementoLista="' + response[i].idElementoLista + '">' +
                    '<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">' +
                    '<path fill-rule="evenodd" d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"></path>' +
                    '</svg>' +
                    '</a>';

                console.log(editPaso);
                $(spanOne).append(editPaso);

                console.log($(spanOne));

                var buttonID = response[i].idElementoLista;
                var checked = response[i].status ? 'checked' : '';
                var statusPaso = "<span class='custom-control custom-switch text-center'> <input type='checkbox' class='custom-control-input habilitar'" +
                    " id='" + buttonID + "' " + checked + " select> <label class='custom-control-label' for='" + buttonID + "'></label></span>"

                row.append($('<td></td>').append(spanOne.html()));
                row.append($('<td></td>').append(statusPaso));

                table.append(row);
            }
            // cierro el modal.           
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            // TODO: aqui se debe agregar un mensaje en caso de error.
            came.notify.showError("Lista", "Su solicitud se ha procesado con errores.");
            return false;
        });
    };
    //#endregion

    function changeName(sender) {
        console.log(sender);
        var nombrePaso1 = $(sender).attr('data-itemNombre');
        var idLista = $(sender).attr('data-idLista');
        var idElementoLista = $(sender).attr('data-idElementoLista');

        if (popoverPaso) {
            $(popoverPaso).popover('hide');
        }

        popoverPaso = $(sender).popover({
            placement: 'left',
            container: 'body',
            // trigger: 'focus',
            html: true,
            sanitize: false,
            content:
                '<div>' +
                '<div class="input-group">' +
                '<input type="text" style="z-index: 9999" class="form-control textNombreElement" value="' + nombrePaso1 + '" data-idLista="' + idLista + '" data-idElementoLista="' + idElementoLista + '" >' +
                '<div class="invalid-feedback">' +
                '<p>Debe especificar un nombre valido.</p>' +
                '</div>' +
                '</div>' +
                '<div class="mt-2">' +
                '<a class="btn btn-sm btn-secondary col-4 float-left mb-2 closePopover" href="#">No</a>' +
                '<a class="btn btn-sm btn-primary col-6 float-right setItem" href="#">Guardar</a></div>' +
                '</div>'
        });

        popoverPaso.popover('show');
    }

    function closePopover(sender) {
        console.log(sender);
        $(popoverPaso).popover('hide');
    }

    function newItem(sender) {
        console.log(sender);

        var idLista = $(sender).attr('idLista');
        console.log(idLista);

        if (popoverPaso) {
            $(popoverPaso).popover('hide');
        }

        popoverPaso = $(sender).popover({
            placement: 'left',
            container: 'body',
            // trigger: 'focus',
            html: true,
            sanitize: false,
            content:
                '<div>' +
                '<div class="input-group">' +
                '<input type="text" style="z-index: 9999" class="form-control textNombreElement" data-idLista="' + idLista + '" >' +
                '<div class="invalid-feedback">' +
                '<p>Debe especificar un nombre valido.</p>' +
                '</div>' +
                '</div>' +
                '<div class="mt-2">' +
                '<a class="btn btn-sm btn-secondary col-4 float-left mb-2 closePopover" href="#">No</a>' +
                '<a class="btn btn-sm btn-primary col-6 float-right setItem" href="#">Guardar</a></div>' +
                '</div>'
        });

        popoverPaso.popover('show');
    }

    function removeItemAdv(sender) {
        console.log(sender);
        // title="<div class="text-center">Seguro que quieres <br> eliminar el elemento </div>" data-content="<div class=""> <a class="btn btn-sm btn-secondary col-4 float-left mb-2" href="#">No</a> <a class="btn btn-sm btn-danger col-4 float-right" href="#">Si</a> </div>">'

        if (popoverPaso) {
            $(popoverPaso).popover('hide');
        }

        var idElementoLista = $(sender).attr('data-idElementoLista');
        console.log(idElementoLista);
        popoverPaso = $(sender).popover({
            placement: 'top',
            container: 'body',
            // trigger: 'focus',
            html: true,
            sanitize: false,
            content:
                '<div class="">' +
                '<a class="btn btn-sm btn-secondary col-4 float-left mb-2 closePopover" href="#">No</a>' +
                '<a class="btn btn-sm btn-danger col-4 float-right confirmRemoveItem" data-idElementoLista="' + idElementoLista + '" href="#">Si</a>' +
                '</div>'
        });

        popoverPaso.popover('show');
    }

    function init() {
        console.info('came.list.init');
        initListener();
        // initPopover();
    }

    return {
        init: init
    };
})();

$(document).ready(function () {
    came.list.init();
});