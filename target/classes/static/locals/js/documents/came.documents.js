'use strict';
var came = came || {};

came.documents = (function () {
    var readOnly = false;
    var popoverPaso = null;

    var urls = {
        api: {
            getProvincias: came.contexto + "/documents/getProvincias",
            setDocumentRegional: came.contexto + "/documents/setDocumentRegional",
            setDocumentProvincial: came.contexto + "/documents/setDocumentProvincial",

            getDocumentosRegionales: came.contexto + "/documents/getDocumentosRegionales",
            getDocumentosProvinciales: came.contexto + "/documents/getDocumentosProvinciales"
        }
    };

    var getIds = {
        upload_document: ".upload-document",
        view_document: ".view-document",
        view_document_provincial: ".view-document-provincial",
        closePopover: '.closePopover',

        upload_file: ".upload-file",
        upload_file_provincial: ".upload-file-provincial",

        selector_deprov: "#selector-deprov",

        svg_documento_regional_upload: "#svg-documento-regional-upload",
        svg_documento_regional_download: "#svg-documento-regional-download",
        svg_documento_regional_a1_upload: "#svg-documento-regional-a1-upload",
        svg_documento_regional_a1_download: "#svg-documento-regional-a1-download",
        svg_documento_regional_a2_upload: "#svg-documento-regional-a2-upload",
        svg_documento_regional_a2_download: "#svg-documento-regional-a2-download",

        fecha_creacion_documento_regional: "#fecha-creacion-documento-regional",
        fecha_creacion_documento_anexo1_regional: "#fecha-creacion-documento-anexo1-regional",
        fecha_creacion_documento_anexo2_regional: "#fecha-creacion-documento-anexo2-regional",

        plantilla_documento_provincial: "#plantilla-documento-provincial",
        from_documento_provincial: "#from-documento-provincial",
        to_documento_provincial: "#to-documento-provincial",

        svg_documento_provincial_upload: "#svg-documento-provincial-upload",
        svg_documento_provincial_download: "#svg-documento-provincial-download",
        svg_documento_provincial_a1_upload: "#svg-documento-provincial-a1-upload",
        svg_documento_provincial_a1_download: "#svg-documento-provincial-a1-download",
        svg_documento_provincial_a2_upload: "#svg-documento-provincial-a2-upload",
        svg_documento_provincial_a2_download: "#svg-documento-provincial-a2-download",

        fecha_creacion_documento_provincial: "#fecha-creacion-documento-provincial",
        fecha_creacion_documento_anexo1_provincial: "#fecha-creacion-documento-anexo1-provincial",
        fecha_creacion_documento_anexo2_provincial: "#fecha-creacion-documento-anexo2-provincial"
    };

    var initListener = function () {
        $(document)
            .on("click", getIds.upload_document, function () {
                if ($(this).attr('data-location') === 'region') {
                    showUploadDoc(this);
                } else {
                    showUploadDocProvincial(this);
                }
            })
            .on("click", getIds.view_document, function () {
                showViewDoc(this);
            })
            .on("click", getIds.view_document_provincial, function () {
                showViewDocProvincial(this);
            })
            .on("click", getIds.closePopover, function () {
                closePopover(this);
            })
            .on("change", getIds.upload_file, function () {
                uploadFile(this);
            })
            .on("change", getIds.upload_file_provincial, function () {
                uploadFileProvincial(this);
            })
            .on("change", getIds.selector_deprov, function () {
                getProvincialesDoc();
            });
    };

    function showUploadDoc(sender) {
        if (readOnly) {
            came.notify.showError("Documentos ", "No tienes permitido subir documentos.");
        } else {
            console.log(sender);

            var title = $(sender).attr('data-title');
            // console.log(idLista);

            var documentType = $(sender).attr('data-document-type');

            if (popoverPaso) {
                $(popoverPaso).popover('hide');
            }

            popoverPaso = $(sender).popover({
                placement: 'top',
                container: 'body',
                html: true,
                sanitize: false,
                title: title + '<a class="close closePopover" href="#");">&times;</a>',
                content:
                    '<div id="PopoverContent"><div class="form-group files1"><input type="file" class="form-control form-control-sm upload-file" multiple="" data-document-type=' + documentType + '></div> </div>'
            });

            popoverPaso.popover('show');
        }
    }

    function showUploadDocProvincial(sender) {
        if (readOnly) {
            came.notify.showError("Documentos ", "No tienes permitido subir documentos.");
        } else {
            console.log(sender);

            var title = $(sender).attr('data-title');
            // console.log(idLista);

            var documentType = $(sender).attr('data-document-type');

            if (popoverPaso) {
                $(popoverPaso).popover('hide');
            }

            popoverPaso = $(sender).popover({
                placement: 'top',
                container: 'body',
                html: true,
                sanitize: false,
                title: title + '<a class="close closePopover" href="#");">&times;</a>',
                content:
                    '<div id="PopoverContent"><div class="form-group files1"><input type="file" class="form-control form-control-sm upload-file-provincial" multiple="" data-document-type=' + documentType + '></div> </div>'
            });

            popoverPaso.popover('show');
        }
    }


    function showViewDoc(sender) {
        console.log(sender);

        var title = $(sender).attr('data-title');
        var documentType = $(sender).attr('data-document-type');
        // console.log(idLista);

        if (popoverPaso) {
            $(popoverPaso).popover('hide');
        }

        var url_download = $(sender).attr('data-url-download');

        popoverPaso = $(sender).popover({
            placement: 'top',
            container: 'body',
            html: true,
            sanitize: false,
            title: title + '<a class="close closePopover" href="#");">&times;</a>',
            content:
                '<div id="PopoverContent"> <div class="row">' +
                '<div class="col-6 text-center text-info">' +
                '<a href="' + url_download + '"><svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-eye-fill mt-2" fill="currentColor" xmlns="http://www.w3.org/2000/svg"> <path d="M10.5 8a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0z"/> <path fill-rule="evenodd" d="M0 8s3-5.5 8-5.5S16 8 16 8s-3 5.5-8 5.5S0 8 0 8zm8 3.5a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7z"/> </svg> <p>Ver documento</p></a>' +
                '</div>' +
                '<div class="col-6 text-center text-info">' +
                '<a class="upload-document"  data-title="' + title + '" data-document-type="' + documentType + '" data-location="region"><svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-cloud-arrow-up mt-2" fill="currentColor" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M4.406 3.342A5.53 5.53 0 0 1 8 2c2.69 0 4.923 2 5.166 4.579C14.758 6.804 16 8.137 16 9.773 16 11.569 14.502 13 12.687 13H3.781C1.708 13 0 11.366 0 9.318c0-1.763 1.266-3.223 2.942-3.593.143-.863.698-1.723 1.464-2.383zm.653.757c-.757.653-1.153 1.44-1.153 2.056v.448l-.445.049C2.064 6.805 1 7.952 1 9.318 1 10.785 2.23 12 3.781 12h8.906C13.98 12 15 10.988 15 9.773c0-1.216-1.02-2.228-2.313-2.228h-.5v-.5C12.188 4.825 10.328 3 8 3a4.53 4.53 0 0 0-2.941 1.1z" />' +
                '<path fill-rule="evenodd" d="M7.646 5.146a.5.5 0 0 1 .708 0l2 2a.5.5 0 0 1-.708.708L8.5 6.707V10.5a.5.5 0 0 1-1 0V6.707L6.354 7.854a.5.5 0 1 1-.708-.708l2-2z" />' +
                '</svg> <p>Volver a subir </p></a></div> </div></div>'
        });

        popoverPaso.popover('show');
    }

    function showViewDocProvincial(sender) {
        console.log(sender);

        var title = $(sender).attr('data-title');
        var documentType = $(sender).attr('data-document-type');
        // console.log(idLista);

        if (popoverPaso) {
            $(popoverPaso).popover('hide');
        }

        var url_download = $(sender).attr('data-url-download');

        popoverPaso = $(sender).popover({
            placement: 'top',
            container: 'body',
            html: true,
            sanitize: false,
            title: title + '<a class="close closePopover" href="#");">&times;</a>',
            content:
                '<div id="PopoverContent"> <div class="row">' +
                '<div class="col-6 text-center text-info">' +
                '<a href="' + url_download + '"><svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-eye-fill mt-2" fill="currentColor" xmlns="http://www.w3.org/2000/svg"> <path d="M10.5 8a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0z"/> <path fill-rule="evenodd" d="M0 8s3-5.5 8-5.5S16 8 16 8s-3 5.5-8 5.5S0 8 0 8zm8 3.5a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7z"/> </svg> <p>Ver documento</p></a>' +
                '</div>' +
                '<div class="col-6 text-center text-info">' +
                '<a class="upload-document"  data-title="' + title + '" data-document-type="' + documentType + '" data-location="deprov"><svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-cloud-arrow-up mt-2" fill="currentColor" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M4.406 3.342A5.53 5.53 0 0 1 8 2c2.69 0 4.923 2 5.166 4.579C14.758 6.804 16 8.137 16 9.773 16 11.569 14.502 13 12.687 13H3.781C1.708 13 0 11.366 0 9.318c0-1.763 1.266-3.223 2.942-3.593.143-.863.698-1.723 1.464-2.383zm.653.757c-.757.653-1.153 1.44-1.153 2.056v.448l-.445.049C2.064 6.805 1 7.952 1 9.318 1 10.785 2.23 12 3.781 12h8.906C13.98 12 15 10.988 15 9.773c0-1.216-1.02-2.228-2.313-2.228h-.5v-.5C12.188 4.825 10.328 3 8 3a4.53 4.53 0 0 0-2.941 1.1z" />' +
                '<path fill-rule="evenodd" d="M7.646 5.146a.5.5 0 0 1 .708 0l2 2a.5.5 0 0 1-.708.708L8.5 6.707V10.5a.5.5 0 0 1-1 0V6.707L6.354 7.854a.5.5 0 1 1-.708-.708l2-2z" />' +
                '</svg> <p>Volver a subir </p></a></div> </div></div>'
        });

        popoverPaso.popover('show');
    }

    function closePopover(sender) {
        console.log(sender);
        $(popoverPaso).popover('hide');
    }

    function getProvincias(regionId) {

        $(getIds.selector_deprov + " option").each(function () {
            $(this).remove();
        });

        var idRegion = regionId;
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

    async function uploadFile(sender) {

        $.ajax({
            type: "POST",
            url: urls.api.setDocumentRegional,
            dataType: "json",
            contentType: 'application/json',
            data: JSON.stringify(await getFile(sender))
        }).done(function (msg) {
            console.log("response done", msg);
            came.notify.showSucces("documentos", "Su solicitud se ha procesado correctamente.");
            location.reload();
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            // TODO: aqui se debe agregar un mensaje en caso de error.
            came.notify.showError("documentos", "Su solicitud se ha procesado con errores.");
            return false;
        });
    }

    async function uploadFileProvincial(sender) {

        var filePaso = await getFile(sender);
        filePaso.idDeprov = $(getIds.selector_deprov).val();
        console.log(filePaso);
        $.ajax({
            type: "POST",
            url: urls.api.setDocumentProvincial,
            dataType: "json",
            contentType: 'application/json',
            data: JSON.stringify(filePaso)
        }).done(function (msg) {
            console.log("response done", msg);
            came.notify.showSucces("documentos", "Su solicitud se ha procesado correctamente.");
            location.reload();
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            // TODO: aqui se debe agregar un mensaje en caso de error.
            came.notify.showError("documentos", "Su solicitud se ha procesado con errores.");
            return false;
        });
    }

    async function getFile(sender) {

        var documento = $(sender).prop("files")[0];
        if (documento) {
            var result = await came.main.getBase64(documento);
        }

        var usuarioPaso = came.env.getEnvUser();

        return {
            document: result,
            documentName: documento?.name,
            documentType: $(sender).attr('data-document-type'),
            idRegion: usuarioPaso?.idRegion,
            idDeprov: null
        };
    }

    function getRegionalDoc() {
        var usuarioPaso = came.env.getEnvUser();

        $.ajax({
            type: "GET",
            url: urls.api.getDocumentosRegionales,
            dataType: "json",
            async: false,
            //contentType: 'application/json',
            data: { idRegion: usuarioPaso?.idRegion }
        }).done(function (response) {
            console.log("response done", JSON.stringify(response));

            loadRegionales(response);

        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
            return false;
        });
    }

    function getProvincialesDoc() {
        var usuarioPaso = came.env.getEnvUser();

        $.ajax({
            type: "GET",
            url: urls.api.getDocumentosProvinciales,
            dataType: "json",
            async: false,
            //contentType: 'application/json',
            data: { idRegion: usuarioPaso?.idRegion, idDeprov: $(getIds.selector_deprov).val() }
        }).done(function (response) {
            console.log("response done", JSON.stringify(response));

            loadProvinciales(response);

        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
            return false;
        });
    }

    function loadRegionales(regional) {
        if (regional.documento) {
            $(getIds.svg_documento_regional_upload).css("display", "none");
            $(getIds.svg_documento_regional_download).css("display", "");
            $(getIds.svg_documento_regional_download).addClass('text-success');

            $(getIds.fecha_creacion_documento_regional).text(came.main.formatDate(regional.documento.uploadDate));
            $(getIds.svg_documento_regional_download).attr('data-url-download', came.contexto + '/downloadController/getFile?filePath=' + came.uploadMainFolder + regional.documento.path.replace(/\\/g, '/'));
        }

        if (regional.anexo1) {
            $(getIds.svg_documento_regional_a1_upload).css("display", "none");
            $(getIds.svg_documento_regional_a1_download).css("display", "");
            $(getIds.svg_documento_regional_a1_download).addClass('text-success');
            $(getIds.fecha_creacion_documento_anexo1_regional).text(came.main.formatDate(regional.anexo1.uploadDate));
            $(getIds.svg_documento_regional_a1_download).attr('data-url-download', came.contexto + '/downloadController/getFile?filePath=' + came.uploadMainFolder + regional.anexo1.path.replace(/\\/g, '/'));
        }

        if (regional.anexo2) {
            $(getIds.svg_documento_regional_a2_upload).css("display", "none");
            $(getIds.svg_documento_regional_a2_download).css("display", "");
            $(getIds.svg_documento_regional_a2_download).addClass('text-success');
            $(getIds.fecha_creacion_documento_anexo2_regional).text(came.main.formatDate(regional.anexo1.uploadDate));
            $(getIds.svg_documento_regional_a2_download).attr('data-url-download', came.contexto + '/downloadController/getFile?filePath=' + came.uploadMainFolder + regional.anexo1.path.replace(/\\/g, '/'));
        }
    }

    function loadProvinciales(provincial) {

        if (provincial.docProvincial){
            $(getIds.from_documento_provincial).text(came.main.formatDate(provincial.docProvincial.fechaInicio));
            $(getIds.to_documento_provincial).text(came.main.formatDate(provincial.docProvincial.fechaFin));
            $(getIds.plantilla_documento_provincial).attr('href', came.contexto + '/downloadController/getFile?filePath=' + came.uploadMainFolder + provincial.docProvincial.templatePath.replace(/\\/g, '/'));
        }

        if (provincial.documento) {
            $(getIds.svg_documento_provincial_upload).css("display", "none");
            $(getIds.svg_documento_provincial_download).css("display", "");
            $(getIds.svg_documento_provincial_download).addClass('text-success');
            $(getIds.fecha_creacion_documento_provincial).text(came.main.formatDate(provincial.documento.uploadDate));
            $(getIds.svg_documento_provincial_download).attr('data-url-download', came.contexto + '/downloadController/getFile?filePath=' + came.uploadMainFolder + provincial.documento.path.replace(/\\/g, '/'));
        } else {
            $(getIds.svg_documento_provincial_upload).css("display", "");
            $(getIds.svg_documento_provincial_download).css("display", "none");
            $(getIds.svg_documento_provincial_download).addClass('text-primary');
            $(getIds.fecha_creacion_documento_provincial).text('');
            $(getIds.svg_documento_provincial_download).attr('data-url-download', '');
        }

        if (provincial.anexo1) {
            $(getIds.svg_documento_provincial_a1_upload).css("display", "none");
            $(getIds.svg_documento_provincial_a1_download).css("display", "");
            $(getIds.svg_documento_provincial_a1_download).addClass('text-success');
            $(getIds.fecha_creacion_documento_anexo1_provincial).text(came.main.formatDate(provincial.anexo1.uploadDate));
            $(getIds.svg_documento_provincial_a1_download).attr('href', came.contexto + '/downloadController/getFile?filePath=' + came.uploadMainFolder + provincial.anexo1.path.replace(/\\/g, '/'));
        } else {
            $(getIds.svg_documento_provincial_a1_upload).css("display", "");
            $(getIds.svg_documento_provincial_a1_download).css("display", "none");
            $(getIds.svg_documento_provincial_a1_download).addClass('text-primary');
            $(getIds.fecha_creacion_documento_anexo1_provincial).text('');
            $(getIds.svg_documento_provincial_a1_download).attr('data-url-download', '');
        }

        if (provincial.anexo2) {
            $(getIds.svg_documento_provincial_a2_upload).css("display", "none");
            $(getIds.svg_documento_provincial_a2_download).css("display", "");
            $(getIds.svg_documento_provincial_a2_download).addClass('text-success');
            $(getIds.fecha_creacion_documento_anexo2_provincial).text(came.main.formatDate(provincial.anexo1.uploadDate));
            $(getIds.svg_documento_provincial_a2_download).attr('data-url-download', came.contexto + '/downloadController/getFile?filePath=' + came.uploadMainFolder + provincial.anexo1.path.replace(/\\/g, '/'));
        } else {
            $(getIds.svg_documento_provincial_a2_upload).css("display", "");
            $(getIds.svg_documento_provincial_a2_download).css("display", "none");
            $(getIds.svg_documento_provincial_a2_download).addClass('text-primary');
            $(getIds.fecha_creacion_documento_anexo2_provincial).text('');
            $(getIds.svg_documento_provincial_a2_download).attr('data-url-download', '');
        }
    }

    function setViewAcces() {
        var menuPso = came.storage.getSessionStorage('menu');
        var subMenuProcess = menuPso.find(e => e.idModule == '2416198290916770826');
        console.log(subMenuProcess);
        if (subMenuProcess) {
            var documentsPaso = subMenuProcess.subMenu.find(e => e.idSubModule == '2416254586126861332');
            console.log(documentsPaso);
            readOnly = documentsPaso.readOnly;
            if (readOnly) {
                // $("#content-network :input").prop("disabled", true);
                // $("#red-button-guardar").prop("disabled", true);
                $("#assignment-button-editar").hide();
            }
        }
    }

    function selectDeprov() {
        var usuarioPaso = came.env.getEnvUser();
        if (usuarioPaso) {
            getProvincias(usuarioPaso.idRegion);
            if (usuarioPaso.idDeprov) {
                $(getIds.selector_deprov).val(usuarioPaso.idDeprov);
                $(getIds.selector_deprov).change();
            }
        }
    }

    function selectRegionDeprov() {
        var usuarioPaso = came.env.getEnvUser();
        if (usuarioPaso) {           
            if (usuarioPaso.nombrePerfilNivel === "regional") {
                $("#document-regional").show();
                $("#document-provincial").show();
            } else if (usuarioPaso.nombrePerfilNivel === "provincial") {
                $("#div-selector-deprov").css("display", "none");
                $("#document-regional").hide();
                $("#document-provincial").show();
            } else {
                // no hace nada
            }
        }
    }

    var init = function () {
        setViewAcces();
        initListener();
        //came.main.showLoading();
        selectDeprov();
        if (!came.env.getEnvUser().currentPeriod) {
            came.notify.showError("Mensaje", "No existe un periodo para crear o modificar documentos.");
        } else {
            selectRegionDeprov()
            getRegionalDoc();
            getProvincialesDoc();
        }        
    };

    return {
        init: init
    }

})();

$(document).ready(function () {
    came.documents.init();
});
