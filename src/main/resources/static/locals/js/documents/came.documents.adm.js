'use strict';
var came = came || {};
came.documents = came.documents || {};

came.documents.admin = (function () {
    var urls = {
        api: {
            getProvincias: came.contexto + "/documents/getProvincias",
            getDocuments: came.contexto + "/documents/getDocuments",
            setReadDocument: came.contexto + "/documents/setReadDocument",
            exportToExcel: came.contexto + "/documents/export/excel"
        }
    };

    var getIds = {
        region: "#region",
        provincia: "#provincia",
        selector_periodo_year: "#selector-periodo-year",
        selector_estado: "#selector-estado",
        find_documents: "#find-documents",
        current_period_year: "#current-period-year",
        count_documents: "#count-documents",
        table_documents: "#table-documents",
        find_documents_again: "#find-documents-again",
        content_find_documents: "#content-find-documents",
        content_list_documents: "#content-list-documents",
        download_file: '.download-file',
        export_excel_documentos: "#export-excel-documentos",
    };

    var initListener = function () {
        $(document)
            .on("change", getIds.region, function () {
                getProvincias(this);
            })
            .on("click", getIds.find_documents, function () {
                findDocuments(this);
            })
            .on("click", getIds.find_documents_again, function () {
                findDocumentsAgain(this);
            })
            .on("click", getIds.download_file, function () {
                notifyDownloadDocumento(this);
            })
            .on("click", getIds.export_excel_documentos, function () {
                getExcel(this);
            });
    };

    function notifyDownloadDocumento(sender) {
        if ($(sender).attr('data-document-type') === 'document') {
            $.ajax({
                type: "POST",
                url: urls.api.setReadDocument,
                dataType: "json",
                contentType: 'application/x-www-form-urlencoded;',
                data: jQuery.param({ documentoId: $(sender).attr('data-id-documento'), tipo: $(sender).attr('data-tipo') })
            }).done(function (msg) {
                console.log("response done", msg);
                //came.notify.showSucces("usuarios", "Su solicitud se ha procesado correctamente.");
                findDocuments(sender);
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                // TODO: aqui se debe agregar un mensaje en caso de error.
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
        } else {
            // luego lo hago aca
        }
    }

    var getProvincias = function (sender) {

        $(getIds.provincia + " option").each(function () {
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
                $(getIds.provincia).append($('<option>').val('').text('Seleccionar'));
                for (var i = 0; i < response.length; i++) {
                    $(getIds.provincia).append($('<option>').val(response[i].value).text(response[i].displayText));
                }
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
        }
    }

    function findDocuments(sender) {
        var currentYear = $(getIds.selector_periodo_year).val();
        $.ajax({
            type: "GET",
            url: urls.api.getDocuments,
            dataType: "json",
            async: true,
            //contentType: 'application/json',
            data: { anio: $(getIds.selector_periodo_year).val(), regionId: $(getIds.region).val(), deprovId: $(getIds.provincia).val(), estado: $(getIds.selector_estado).val() }
        }).done(function (response) {
            console.log("response done", JSON.stringify(response));
            if (typeof (response) !== "string") {
                $(getIds.current_period_year).text(currentYear);
                $(getIds.count_documents).text(response.length);
                $(getIds.table_documents + ' tbody tr').remove();
                var table = $(getIds.table_documents);
                for (var i = 0; i < response.length; i++) {
                    var row = $('<tr></tr>');
                    row.append($('<td></td>').append($('<span />').text(response[i].region).html()));
                    row.append($('<td></td>').append($('<span />').text(response[i].tipo).html()));
                    row.append($('<td></td>').append($('<span />').text(response[i].deprov).html()));
                    row.append($('<td></td>').append($('<span />').text(response[i].estado).html()));                    
                    var divDocs = $('<div></div>');
                    response[i].documentos.forEach(x => {
                        var typedoc = x.typeDocument === 'document' ? 'Documento' : x.typeDocument === 'anexo1' ? 'Anexo 1' : 'Anexo 2';

                        var url_file = came.contexto + '/downloadController/getFile?filePath=' + came.uploadMainFolder + x.path.replace(/\\/g, '/');
                        $(divDocs).append($('<a href="' + url_file + '" class="download-file" data-id-documento="' + x.idDocumento + '" data-document-type="' + x.typeDocument + '" data-tipo="' + response[i].tipo + '">' + typedoc + ' - ' + x.name + ' (' + x.uploadDate +')</a></br>'));
                    });
                    console.log($(divDocs).html());
                    row.append($('<td></td>').append(divDocs.html()));

                    //row.append($('<td><a href="#" class="move_to_detail" data-rbd="' + response[i].rbd + '">' + response[i].nombre + '</a></td>'));

                    table.append(row);
                }

                //came.main.initList(table);        

                $(getIds.content_find_documents).css("display", "none");
                $(getIds.content_list_documents).css("display", "");
            } else {
                came.notify.showError("Mensaje", "Su solicitud se ha procesado con errores.");
            }
        });
    }

    function getExcel(sender) {


        var link = document.createElement('a');
        link.href = (urls.api.exportToExcel + "?anio=" + $(getIds.selector_periodo_year).val() +
            "&regionId=" + $(getIds.region).val() +
            "&deprovId=" + $(getIds.provincia).val() +
            "&estado=" + $(getIds.selector_estado).val());
        link.click();
    }

    function findDocumentsAgain(sender) {
        $(getIds.content_find_documents).css("display", "");
        $(getIds.content_list_documents).css("display", "none");
    }

    var init = function () {
        initListener();
    };

    return {
        init: init
    }

})();

$(document).ready(function () {
    came.documents.admin.init();
});
