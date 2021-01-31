'use strict';
var came = came || {};

came.advisoryreport = (function() {

    var urls = {
        api: {
            getDirecta: came.contexto + "/advisoryreport/getDirecta",
            exportToExcel: came.contexto + "/advisoryreport/export/excel",
        }
    };

    var getIds = {
        selector_fecha_inicio: "#selector-fecha-inicio",
        selector_fecha_fin: "#selector-fecha-fin",
        search_asesoria: "#search-asesoria",
        search_result: "#search-result",
        table_asesoria: "#table-asesoria",
        count_directory: "#count-directory",

        button_asesoria: "#button-asesoria",
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
            .on("click", getIds.button_asesoria, function() {
                getDirecta(this);
            });

    }

    function exportarExcel(sender) {
        var link = document.createElement('a');
        link.href = (urls.api.exportToExcel + "?fechaInicio=" + $(getIds.selector_fecha_inicio).val() +
            "&fechaFin=" + $(getIds.selector_fecha_fin).val());
        link.click();
    }

    async function getDirecta(sender) {
        came.main.showLoading();
        $.ajax({
            type: "GET",
            url: urls.api.getDirecta,
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
                row.append($('<td></td>').append($('<span />').text(response[i].region).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].deprov).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].rbd).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].establecimiento).html()));
                var nombreSupervisor = '';
                response[i].nombreSupervisor.forEach(element => {
                    nombreSupervisor += element + '</br>';
                });                
                row.append($('<td></td>').append(nombreSupervisor));
                row.append($('<td></td>').append($('<span />').text(response[i].numeroSesion).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].tipoPlanificacion).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].fechaPlanificada).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].fechaRealizada).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].estado).html()));
                var foco = '';
                response[i].foco.forEach(element => {
                    foco += element + '</br>';
                });
                row.append($('<td></td>').append(foco));
                row.append($('<td></td>').append($('<span />').text(response[i].estadoFoco).html()));
                var acionesMejorasDesarrollo = '';
                response[i].acionesMejorasDesarrollo.forEach(element => {
                    acionesMejorasDesarrollo += element + '</br>';
                });
                row.append($('<td></td>').append(acionesMejorasDesarrollo));
                var movimientoClaveComprometido = '';
                response[i].movimientoClaveComprometido.forEach(element => {
                    movimientoClaveComprometido += element + '</br>';
                });
                row.append($('<td></td>').append(movimientoClaveComprometido));
                var movimientoClaveDesarrollado = '';
                response[i].movimientoClaveDesarrollado.forEach(element => {
                    movimientoClaveDesarrollado += element + '</br>';
                });
                row.append($('<td></td>').append(movimientoClaveDesarrollado));
                row.append($('<td></td>').append($('<span />').text(response[i].plazo).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].responsable).html()));

                table.append(row);
            }

            came.main.initList(getIds.table_asesoria);

            $(getIds.search_asesoria).css("display", "none");
            $(getIds.search_result).css("display", "");
            came.main.hideLoading();

        }).fail(function(jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            came.main.hideLoading();
            came.notify.showError("Reporte asesoria directa", "Su solicitud se ha procesado con errores.");
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
    came.advisoryreport.init();
});