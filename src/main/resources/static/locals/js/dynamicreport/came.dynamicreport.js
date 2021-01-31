'use strict';
var came = came || {};

came.dynamicreport = (function () {

    var urls = {
        api: {
            getDynamic: came.contexto + "/dynamicreport/getDynamic",
            exportToExcel: came.contexto + "/dynamicreport/export/excel",
        }
    };

    var getIds = {
        panel_search: "#panel-search",
        panel_search_result: "#panel-search-result",
        find_dynamic: "#find-dynamic",
        button_find_again: "#button-find-again",
        button_back: "#button-back",
        count_dynamic: "#count-dynamic",
        table_dynamic: "#table-dynamic",

        fecha_inicio: "#fecha-inicio",
        fecha_fin: "#fecha-fin",
        button_export_excel: "#button-export-excel",
    };

    var initListener = function () {
        $(document)
            .on("click", getIds.find_dynamic, function () {
                getDynamic(this);
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

    function getDynamic(sender) {
        console.log(this);
        $.ajax({
            type: "GET",
            url: urls.api.getDynamic,
            //dataType: "json",
            // async: false,
            contentType: 'application/json',
            data: jQuery.param({
                startDate: $(getIds.fecha_inicio).val(),
                endDate: $(getIds.fecha_fin).val()
            })
        }).done(function (response) {
            if (typeof (response) !== "string") {
                $(getIds.count_dynamic).text(response.length);
                $(getIds.table_dynamic + ' tbody tr').remove();
                var tablePaso = $(getIds.table_dynamic);
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

                row.append($('<td></td>').append($('<span />').text(elements[i].rbd).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].establecimiento).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].estado).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].region).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].deprov).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].comuna).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].dependencia).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].categorizacion).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].periodo).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].tipoApoyo).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].tipoRed).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].supervisorDirecta).html()));
                var supervisor = "";
                if (elements[i].supervisorRed){
                    elements[i].supervisorRed.forEach(x => {
                        supervisor += x + '</br>';
                    });                
                }                
                row.append($('<td></td>').append(supervisor));
                row.append($('<td></td>').append($('<span />').text(elements[i].nombreRed).html()));                
                row.append($('<td></td>').append($('<span />').text(elements[i].sesionesProgramadasDirecta).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].sesionesProgramadasRed).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].sesionesRealizadasDirecta).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].sesionesRealizadasRed).html()));


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

    function findPlanningAgain(sender) {
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
    console.log('came.dynamicreport.init()');
    came.dynamicreport.init();
});