'use strict';
var came = came || {};

came.networksreport = (function() {

    var urls = {
        api: {
        
            //exportToExcel: came.contexto + "/advisoryreport/export/excel",
        }
    };

    var getIds = {
        //selector_fecha_inicio: "#selector-fecha-inicio",
        //selector_fecha_fin: "#selector-fecha-fin",
        // search_asesoria: "#search-asesoria",
        // search_result: "#search-result",
        // table_asesoria: "#table-asesoria",
        // count_directory: "#count-directory",

         button_buscar_redes: "#button-buscar-redes",
        // button_buscar_nuevamente: "#button-buscar-nuevamente",
        // button_volver: "#button-volver",
        // button_exportar_excel: "#button-exportar-excel",
    }

    var initListener = function() {
        $(document)
            // .on("click", getIds.button_buscar_nuevamente, function() {
            //     buscarNuevamente(this);
            // })
            // .on("click", getIds.button_volver, function() {
            //     buscarNuevamente(this);
            // })
            // .on("click", getIds.button_exportar_excel, function() {
            //     exportarExcel(this);
            // })
            .on("click", getIds.button_buscar_redes, function() {
                getRedes(this);
            });

    }

    function exportarExcel(sender) {
        // var link = document.createElement('a');
        // link.href = (urls.api.exportToExcel + "?fechaInicio="+ $(getIds.selector_fecha_inicio).val() +
        //                                 "&fechaFin=" + $(getIds.selector_fecha_fin).val());
        // link.click();  
    }

    async function getRedes(sender) {
       
    }

    function buscarNuevamente(sender) {
        //$(getIds.search_asesoria).css("display", "");
        //$(getIds.search_result).css("display", "none");
    }

    function init() {
        initListener();
    }

    return {
        init: init
    };
})();

$(document).ready(function() {
    came.networksreport.init();
});