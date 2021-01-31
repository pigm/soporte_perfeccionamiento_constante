'use strict';
var came = came || {};

came.supervisorsreport = (function() {

    var urls = {
        api: {
            getSupervisores: came.contexto + "/supervisorsreport/getSupervisores",
            //exportToExcel: came.contexto + "/supervisorsreport/export/excel",
        }
    };

    var getIds = {
        selector_fecha_inicio: "#selector-fecha-inicio",
        selector_fecha_fin: "#selector-fecha-fin",
        search_supervisores: "#search-supervisores",
        search_result: "#search-result",
        table_asesoria: "#table-asesoria",
        count_directory: "#count-directory",

        button_buscar_supervisores: "#button-buscar-supervisores",
        button_buscar_nuevamente: "#button-buscar-nuevamente",
        button_volver: "#button-volver",
        button_exportar_excel: "#button-exportar-excel",
    }

    var initListener = function() {
        $(document)
            .on("click", getIds.button_buscar_nuevamente, function() {
                buscarNuevamente(this);
            })
            .on("click", getIds.button_volver, function() {
                buscarNuevamente(this);
            })
            .on("click", getIds.button_exportar_excel, function() {
                exportarExcel(this);
            })
            .on("click", getIds.button_buscar_supervisores, function() {
                getSupervisores(this);
            });

    }

    function exportarExcel(sender) {
        // var link = document.createElement('a');
        // link.href = (urls.api.exportToExcel + "?fechaInicio="+ $(getIds.selector_fecha_inicio).val() +
        //                                 "&fechaFin=" + $(getIds.selector_fecha_fin).val());
        // link.click();  
    }

    async function getSupervisores(sender) {
        came.main.showLoading();
        $.ajax({
            type: "GET",
            url: urls.api.getSupervisores,
            dataType: "json",
            async: true,
            contentType: 'application/json',
            data: {
                fechaInicio: $(getIds.selector_fecha_inicio).val(),
                fechaFin: $(getIds.selector_fecha_fin).val(),
            }

        }).done(function(response) {
            if ($(getIds.table_asesoria).DataTable() !== null) {
                $(getIds.table_asesoria).DataTable().destroy();
            }
            $(getIds.table_asesoria + ' tbody tr').remove();
            console.log(response);
            $(getIds.count_directory).text(response.length);
            var table = $(getIds.table_asesoria);
            for (var i = 0; i < response.length; i++) {

                var row = $('<tr></tr>');
                row.append($('<td></td>').append($('<span />').text(response[i].nombre).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].rut).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].email).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].region).html()));            
                row.append($('<td></td>').append($('<span />').text(response[i].deprov).html()));
            
                var establecimientosApoyadosFormaDirecta = '';
                response[i].establecimientosApoyadosFormaDirecta.forEach(element => {
                    establecimientosApoyadosFormaDirecta += element + '</br>';
                });
                row.append($('<td></td>').append(establecimientosApoyadosFormaDirecta));
                var redesApoyadas = '';
                response[i].redesApoyadas.forEach(element => {
                    redesApoyadas += element + '</br>';
                });
                row.append($('<td></td>').append(redesApoyadas));
            
                row.append($('<td></td>').append($('<span />').text(response[i].asesoriasRedRegistradas).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].asesoriasDirectasRegistradas).html()));

                table.append(row);
            }

            came.main.initList(getIds.table_asesoria);

            $(getIds.search_asesoria).css("display", "none");
            $(getIds.search_result).css("display", "");
            came.main.hideLoading();

        }).fail(function(jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            came.main.hideLoading();
            came.notify.showError("Reporte supervisor", "Su solicitud se ha procesado con errores.");
            return false;
        });
    }

    function buscarNuevamente(sender) {
        $(getIds.search_asesoria).css("display", "");
        $(getIds.search_result).css("display", "none");
    }

    function init() {
        initListener();
    }

    return {
        init: init
    };
})();

$(document).ready(function() {
    came.supervisorsreport.init();
});