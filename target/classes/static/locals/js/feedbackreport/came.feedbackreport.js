'use strict';
var came = came || {};

came.feedbackreport = (function () {

    var urls = {
        api: {            
            getFeedback: came.contexto + "/feedbackreport/getFeedback",   
            exportToExcel: came.contexto + "/feedbackreport/export/excel",
        }
    };

    var getIds = {
        panel_search: "#panel-search",
        panel_search_result: "#panel-search-result",
        find_feedback: "#find-feedback",
        button_find_again: "#button-find-again",
        button_back: "#button-back",
        count_feedback: "#count-feedback",
        table_feedback: "#table-feedback",

        fecha_inicio: "#fecha-inicio",
        fecha_fin: "#fecha-fin",
        button_export_excel: "#button-export-excel",
    };

    var initListener = function () {
        $(document)
            .on("click", getIds.find_feedback, function () {
                getPlanning(this);
            })
            .on("click", getIds.button_find_again, function () {
                findPlanningAgain(this);
            })
            .on("click", getIds.button_back, function () {
                findPlanningAgain(this);
            })
            .on("click", getIds.button_export_excel, function () {
                getExcel(this);
            });
        };

    function getPlanning(sender){
        console.log(this);
        $.ajax({
            type: "GET",
            url: urls.api.getFeedback,
            //dataType: "json",
            // async: false,
            contentType: 'application/json',
            data: jQuery.param({
                startDate: $(getIds.fecha_inicio).val(),
                endDate: $(getIds.fecha_fin).val()
            })
        }).done(function (response) {
            if (typeof (response) !== "string") {
                $(getIds.count_feedback).text(response.length);
                $(getIds.table_feedback + ' tbody tr').remove();
                var tablePaso = $(getIds.table_feedback);
                loadFeedback(tablePaso, response);

                $(getIds.panel_search).css("display", "none");
                $(getIds.panel_search_result).css("display", "");

                //came.main.hideLoading();
            } else {
                came.notify.showError("Asignar supervisores", "Su solicitud se ha procesado con errores.");
            }
        });
    }

    function loadFeedback(table, elements) {
        console.log(elements);
        if (elements) {
            for (var i = 0; i < elements.length; i++) {
                var row = $('<tr></tr>');
                
                row.append($('<td></td>').append($('<span />').text(elements[i].region).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].deprov).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].semestre).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].jefeTecnico).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].supervisor).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].frecuencia).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].fechaPlanificada).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].fechaRealizada).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].horaRealizada).html()));
                // row.append($('<td></td>').append($('<span />').text(elements[i].tipoRetroalimentacion).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].fechaAcompaniamiento).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].focoAbordado).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].aspectosReforzar).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].acciones).html()));
                // row.append($('<td></td>').append($('<span />').text(elements[i].responsable).html()));
                // row.append($('<td></td>').append($('<span />').text(elements[i].plazos).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].comentarios).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].fechaProxima).html()));  

                $(table).append(row);
            }

            came.main.initList(table);            
        }
    }

    function getExcel(sender) {
        console.log(sender);

        var link = document.createElement('a');
        link.href = (urls.api.exportToExcel +
            "?startDate=" + $(getIds.fecha_inicio).val() +
            "&endDate=" + $(getIds.fecha_fin).val());
        console.log(link.href);
        link.click();
    }

    function findPlanningAgain(sender){
        console.log(this);
        $(getIds.panel_search).css("display", "");
        $(getIds.panel_search_result).css("display", "none");
    }

    function init() {
        initListener();
    }

    return {
        init: init
    };
})();


$(document).ready(function () {
    console.log('came.feedbackreport.init()');
    came.feedbackreport.init();
});