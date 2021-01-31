'use strict';
var came = came || {};

came.biblioteca = (function () {
    var readOnly = false;
    var popoverPaso = null;
    var urls = {
        api: {
            getProvincias: came.contexto + "/biblioteca/getProvincias",
            setDocument: came.contexto + "/biblioteca/setDocument",
            getDocuments: came.contexto + "/biblioteca/getDocuments",
            getDeleteDocuments: came.contexto + "/biblioteca/getDeleteDocuments",
            getUpdateBiblioteca: came.contexto + "/biblioteca/getUpdateBiblioteca",
            updateBiblioteca: came.contexto + "/biblioteca/updateBiblioteca"

        }
    };

    var getIds = {
        selector_year: "#selector-year",
        selector_categoria: "#selector-categoria",
        selector_perfil: "#selector-perfil",
        fecha_inicio: "#fecha-inicio",
        input_nombre: "#input-nombre",

        selector_region: "#selector-region",
        selector_deprov: "#selector-deprov",

        selector_add_region: "#selector-add-region",
        selector_add_deprov: "#selector-add-deprov",
        selector_add_categoria: "#selector-add-categoria",
        select_add_perfil: "#select-add-perfil",
        input_add_nombre: "#input-add-nombre",
        control_add_doc: "#control-add-doc",
        search_biblioteca: "#search-biblioteca",
        search_result: "#search-result",
        count_directory: "#count-directory",
        table_biblioteca: "#table-biblioteca",

        button_agregar_biblioteca: "#button-agregar-biblioteca",
        button_buscar_biblioteca: "#button-buscar-biblioteca",
        button_buscar_nuevamente: "#button-nueva-busqueda",
        button_agregar_documento: "#button-agregar-documento",

        removeItem: ".removeItem",
        updateItem: ".updateItem",
        confirmRemoveItem: ".confirmRemoveItem",
        closePopover: '.closePopover',
        modal_add_doc: "#sbuirDoc",

        contenedor_subir_documentos: "#contenedor-subir-documentos",
        contenedor_descargar_documentos: "#contenedor-descargar-documentos",
        descargar_documento: "#descargar-documento"
    }

    var initListener = function () {
        $(document)
            .on("change", getIds.selector_region, function () {
                getProvinciasFilter(this);
            })
            .on("click", getIds.button_agregar_biblioteca, function () {
                setDocument(this);
            })
            .on("click", getIds.button_agregar_documento, function () {
                agregarDocumento(this);
            })
            .on("click", getIds.button_buscar_nuevamente, function () {
                nuevaBusqueda(this);
            })
            .on("click", getIds.button_buscar_biblioteca, function () {
                getDocuments(this);
            })
            .on("click", getIds.removeItem, function () {
                removeItem(this);
            })
            .on("click", getIds.confirmRemoveItem, function () {
                confirmRemoveItem(this);
            })
            .on("click", getIds.updateItem, function () {
                updateItem(this);
            })
            .on("click", getIds.closePopover, function () {
                closePopover(this);
            })
            .on("change", getIds.selector_add_region, function () {
                getProvincias(this);
            });

    }

    function getProvinciasFilter(sender) {

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

    function agregarDocumento(sender) {

        if (readOnly) {
            came.notify.showError("Biblioteca", "No tienes permitido crear o modificar biblioteca.");
        } else {
            $(getIds.input_add_nombre).val('');
            $(getIds.selector_add_categoria).val('');
            var $select = $('#select-add-perfil').selectize({
                create: true,
            });
            $select[0].selectize.setValue([]);
            $(getIds.contenedor_subir_documentos).show();
            $(getIds.contenedor_descargar_documentos).hide();
            
            var usuarioPaso = came.env.getEnvUser();
            if (usuarioPaso.idPerfil === "2416264829766468643") {
                $(getIds.selector_add_region).prop('disabled', false);            
                $(getIds.selector_add_deprov).prop('disabled', false);
            }
            $(getIds.modal_add_doc).modal('show');
        }
    }

    async function getFile() {
        var documento = $(getIds.control_add_doc).prop("files")[0];
        if (documento) {
            var result = await came.main.getBase64(documento);
        }

        return {
            documentName: documento?.name,
            document: result
        };
    }

    function nuevaBusqueda(sender) {
        $(getIds.search_biblioteca).css("display", "");
        $(getIds.search_result).css("display", "none");
    }

    function getProvincias(sender) {

        $(getIds.selector_add_deprov + " option").each(function () {
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
                $(getIds.selector_add_deprov).append($('<option selected>').val('').text('Seleccionar'));
                for (var i = 0; i < response.length; i++) {
                    $(getIds.selector_add_deprov).append($('<option>').val(response[i].value).text(response[i].displayText));
                }
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
        } else {
            $(getIds.selector_add_deprov).append($('<option selected>').val('').text('Seleccionar'));
        }
    }

    function getDocuments(sender) {
        // came.main.showLoading();
        var usuarioPaso = came.env.getEnvUser();
        console.log(usuarioPaso);
        $.ajax({
            type: "GET",
            url: urls.api.getDocuments,
            dataType: "json",
            async: true,
            contentType: 'application/json',
            data: {
                anio: $(getIds.selector_year).val(),
                categoriaId: $(getIds.selector_categoria).val(),
                perfilId: $(getIds.selector_perfil).val(),
                nombreDocumento: $(getIds.input_nombre).val(),
                regionId: $(getIds.selector_region).val(),
                deprov: $(getIds.selector_deprov).val()
            }

        }).done(function (response) {
            if (typeof (response) !== "string") {
                if ($(getIds.table_biblioteca).DataTable() !== null) {
                    $(getIds.table_biblioteca).DataTable().destroy();
                }
                $(getIds.table_biblioteca + ' tbody tr').remove();
                console.log(response);
                $(getIds.count_directory).text(response.length);
                var table = $(getIds.table_biblioteca);

                loadDocuments(table, response)

                $(getIds.search_biblioteca).css("display", "none");
                $(getIds.search_result).css("display", "");

                // came.main.hideLoading();   
            } else {
                came.notify.showError("Asignar supervisores", "Su solicitud se ha procesado con errores.");
            }
        });
    }

    function loadDocuments(table, items) {

        for (var i = 0; i < items.length; i++) {
            var urlDescarga = came.contexto + '/downloadController/getFile?filePath=' + came.uploadMainFolder + items[i].templatePath.replace(/\\/g, '/');
            var row = $('<tr></tr>');

            var tdPaso1 = $('<td></td>');
            tdPaso1.append('<a href="' + urlDescarga + '">' + items[i].nombreDocumento + '</a>');

            var deletePaso = '<a href="#" class="float-right text-danger removeItem" data-placement="top" data-original-title="Seguro que quieres <br> eliminar el elemento" data-idBiblioteca="' + items[i].idBiblioteca + '">' +
                '<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">' +
                '<path fill-rule="evenodd" d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z" />' +
                '</svg>' +
                '</a>';

            if (!readOnly) {
                $(tdPaso1).append(deletePaso);
            }

            var editPaso = '<a href="#" class="updateItem float-right mr-3" title=""  data-idBiblioteca="' + items[i].idBiblioteca + '" data-idRegion="' + items[i].idRegion + '" data-idDeprov="' + items[i].idDeprov + '" >' +
                '<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">' +
                '<path fill-rule="evenodd" d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"></path>' +
                '</svg>' +
                '</a>';

            //if (!readOnly) {
            $(tdPaso1).append(editPaso);
            //}

            row.append(tdPaso1);
            row.append($('<td></td>').append($('<span />').text(items[i].categoria).html()));
            
            row.append($('<td></td>').append(items[i].perfil.replace(",", "<br>")));

            row.append($('<td></td>').append($('<span />').text(items[i].region).html()));
            row.append($('<td></td>').append($('<span />').text(items[i].deprov).html()));
            row.append($('<td></td>').append($('<span />').text(items[i].fechaPublicacion).html()));

            table.append(row);
        }

        came.main.initList(getIds.table_biblioteca);
    }

    function updateItem(sender) {
        var idBiblioteca = $(sender).attr('data-idBiblioteca');
        var idRegion = $(sender).attr('data-idRegion');
        var idDeprov = $(sender).attr('data-idDeprov');

        console.log(idBiblioteca);
        $.ajax({
            type: "GET",
            url: urls.api.getUpdateBiblioteca,
            dataType: "json",
            async: false,
            //contentType: 'application/json',
            data: { idBiblioteca: idBiblioteca, idRegion: idRegion, idDeprov: idDeprov }
        }).done(function (response) {
            console.log("response done", JSON.stringify(response));
            $(getIds.input_add_nombre).val(response.nombre);
            
            $(getIds.selector_add_region).val(response.idRegion);
            $(getIds.selector_add_region).prop('disabled', 'disabled');
            $(getIds.selector_add_deprov).val(response.idDeprov);
            $(getIds.selector_add_deprov).prop('disabled', 'disabled');

            $(getIds.selector_add_categoria).val(response.categoria);
            var $select = $('#select-add-perfil').selectize({
                create: true,
            });
            $select[0].selectize.setValue(response.perfil);

            $(getIds.contenedor_subir_documentos).hide();
            $(getIds.contenedor_descargar_documentos).show();
            var urlDescarga = came.contexto + '/downloadController/getFile?filePath=' + came.uploadMainFolder + response.document.replace(/\\/g, '/');
            $(getIds.descargar_documento).attr('href', urlDescarga);

            $(getIds.button_agregar_biblioteca).attr('data-idBiblioteca', idBiblioteca);

        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            came.notify.showError("Biblioteca", "Su solicitud se ha procesado con errores.");
            return false;
        });
        $(getIds.modal_add_doc).modal('show');
    }

    function removeItem(sender) {
        console.log(sender);
        // title="<div class="text-center">Seguro que quieres <br> eliminar el elemento </div>" data-content="<div class=""> <a class="btn btn-sm btn-secondary col-4 float-left mb-2" href="#">No</a> <a class="btn btn-sm btn-danger col-4 float-right" href="#">Si</a> </div>">'

        if (popoverPaso) {
            $(popoverPaso).popover('hide');
        }

        var idBiblioteca = $(sender).attr('data-idBiblioteca');
        console.log(idBiblioteca);
        popoverPaso = $(sender).popover({
            placement: 'top',
            container: 'body',
            // trigger: 'focus',
            html: true,
            sanitize: false,
            content:
                '<div class="">' +
                '<a class="btn btn-sm btn-secondary col-4 float-left mb-2 closePopover" href="#">No</a>' +
                '<a class="btn btn-sm btn-danger col-4 float-right confirmRemoveItem" data-idBiblioteca="' + idBiblioteca + '" href="#">Si</a>' +
                '</div>'
        });

        popoverPaso.popover('show');
    }

    function closePopover(sender) {
        console.log(sender);
        $(popoverPaso).popover('hide');
    }

    function confirmRemoveItem(sender) {

        console.log(sender);
        var idBiblioteca = $(sender).attr('data-idBiblioteca');
        console.log(idBiblioteca);
        $.ajax({
            type: "POST",
            url: urls.api.getDeleteDocuments,
            data: { idBiblioteca: idBiblioteca }
        }).done(function (msg) {
            console.log("response done", msg);
            $(popoverPaso).popover('hide');
            //initList($(getIds.agregar).attr('idLista'));
            //came.notify.showSucces("Lista", "Su solicitud se ha procesado correctamente.");
            // cierro el modal.   
            $(getIds.button_buscar_biblioteca).click();

        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            // TODO: aqui se debe agregar un mensaje en caso de error.
            came.notify.showError("Lista", "Su solicitud se ha procesado con errores.");
            return false;
        });
    }




    async function setDocument(sender) {

        var idBiblioteca = $(sender).attr('data-idBiblioteca');

        var isValid = true;
        var doc = await getFile();
        var docPaso = {
            document: doc.document,
            documentType: '',
            documentName: doc.documentName,
            nombre: $(getIds.input_add_nombre).val(),
            idRegion: $(getIds.selector_add_region).val(),
            idDeprov: $(getIds.selector_add_deprov).val(),
            categoria: $(getIds.selector_add_categoria).val(),
            perfil: $(getIds.select_add_perfil).val()

        };

        var urlController = urls.api.setDocument;
        if (idBiblioteca !== undefined) {
            docPaso['idBiblioteca'] = idBiblioteca;
            urlController = urls.api.updateBiblioteca;
        }

        console.log(docPaso);
        if (docPaso.nombre === '') {
            came.main.setInvalidControl($(getIds.input_add_nombre));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.input_add_nombre));
        }

        // if (docPaso.idRegion === '') {
        //     came.main.setInvalidControl($(getIds.selector_add_region));
        //     isValid = false;
        // } else {
        //     came.main.setValidControl($(getIds.selector_add_region));
        // }

        // if (docPaso.idDeprov === '') {
        //     came.main.setInvalidControl($(getIds.selector_add_deprov));
        //     isValid = false;
        // } else {
        //     came.main.setValidControl($(getIds.selector_add_deprov));
        // }

        if (docPaso.categoria === '') {
            came.main.setInvalidControl($(getIds.selector_add_categoria));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.selector_add_categoria));
        }

        if (docPaso.perfil === null) {
            came.main.setInvalidControl($(getIds.select_add_perfil));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.select_add_perfil));
        }

        if (docPaso.document === undefined && idBiblioteca === undefined) {
            came.main.setInvalidControl($(getIds.control_add_doc));
            isValid = false;
        } else {
            came.main.setValidControl($(getIds.control_add_doc));
        }


        if (isValid) {
            $.ajax({
                type: "POST",
                url: urlController,
                dataType: "json",
                contentType: 'application/json',
                data: JSON.stringify(docPaso)
            }).done(function (msg) {
                console.log("response done", msg);
                came.notify.showSucces("Biblioteca", "Su solicitud se ha procesado correctamente.");
                location.reload();
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                // TODO: aqui se debe agregar un mensaje en caso de error.
                came.notify.showError("Biblioteca", "Su solicitud se ha procesado con errores.");
                return false;
            });
        }

    }

    function selectRegionDeprov() {
        var usuarioPaso = came.env.getEnvUser();
        if (usuarioPaso) {
            $(getIds.selector_add_region).val(usuarioPaso.idRegion);
            $(getIds.selector_add_region).change();
            $(getIds.selector_add_deprov).val(usuarioPaso.idDeprov);
            $(getIds.selector_add_deprov).change();

            if (usuarioPaso.nombrePerfilNivel === "regional") {
                $(getIds.selector_add_region).prop('disabled', 'disabled');
            } else if (usuarioPaso.nombrePerfilNivel === "provincial") {
                $(getIds.selector_add_region).prop('disabled', 'disabled');
                $(getIds.selector_add_deprov).prop('disabled', 'disabled');
            } else {
                $(getIds.selector_add_region).prop('disabled', false);
                $(getIds.selector_add_deprov).prop('disabled', false);
            }
        }
    }

    function selectRegionDeprovStart() {
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
            if (usuarioPaso.idPerfil !== "2416264829766468643") {
                $(getIds.selector_perfil).val(usuarioPaso.idPerfil);
                $(getIds.selector_perfil).prop('disabled', 'disabled');
            }
        }
    }

    function setViewAcces() {
        var menuPso = came.storage.getSessionStorage('menu');
        var subMenuProcess = menuPso.find(e => e.idModule == '2416198290916770830');
        console.log(subMenuProcess);
        if (subMenuProcess) {
            var biblio = subMenuProcess.subMenu.find(e => e.idSubModule == '2416254586126861338');
            console.log(biblio);
            readOnly = biblio.readOnly;
            if (readOnly) {
                $("#content-biblioteca-modal :input").prop("disabled", true);

                $("#button-agregar-biblioteca").prop("disabled", true);

                $('#select-add-perfil')[0].selectize.disable();
            }
        }
    }

    function init() {
        initListener();
        selectRegionDeprovStart();
        selectRegionDeprov();
        setViewAcces();
    }

    return {
        init: init
    };

})();

$(document).ready(function () {
    came.biblioteca.init();
});