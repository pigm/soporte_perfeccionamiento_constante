'use strict';
var came = came || {};

came.directoryreport = (function() {

    var urls = {
        api: {
            getProvincias: came.contexto + "/directoryreport/getProvincias",
            getComunas: came.contexto + "/directoryreport/getComunas",
            getDirectoryReport: came.contexto + "/directoryreport/getDirectoryReport",
            exportToExcel: came.contexto + "/directoryreport/export/excel",
        }
    };

    var getIds = {

        selector_region: "#selector-region",
        selector_deprov: "#selector-deprov",
        selector_comuna: "#selector-comuna",
        table_directorio: "#table-directorio",
        count_directory: "#count-directory",
        search_directorio: "#search-directorio",
        search_result: "#search-result",

        lbl_region: "#lbl-region",
        lbl_deprov: "#lbl-deprov",
        lbl_comuna: "#lbl-comuna",

        button_buscar_directorio: "#button-buscar-directorio",
        button_buscar_nuevamente: "#button-buscar-nuevamente",
        button_exportar_excel: "#button-exportar-excel",
        button_volver: "#button-volver",
    }

    var initListener = function() {
        $(document)
            .on("click", getIds.button_buscar_directorio, function() {
                getDirectorios(this);
            })
            .on("click", getIds.button_buscar_nuevamente, function() {
                buscarNuevamente(this);
            })
            .on("click", getIds.button_volver, function() {
                buscarNuevamente(this);
            })
            .on("click", getIds.button_exportar_excel, function() {
                exportarExcel(this);
            })
            .on("change", getIds.selector_deprov, function() {
                getComunas(this);
            })
            .on("change", getIds.selector_region, function() {
                getProvincias(this);
            });

    }

    function buscarNuevamente(sender) {
        $(getIds.search_directorio).css("display", "");
        $(getIds.search_result).css("display", "none");
    }

    function exportarExcel(sender) {
    	var link = document.createElement('a');
        link.href = (urls.api.exportToExcel + "?region="+ $(getIds.selector_region).val() +
                                        "&deprov=" + $(getIds.selector_deprov).val() + 
                                        "&comuna="+$(getIds.selector_comuna).val());
        link.click();  
    }

    async function getDirectorios(sender) {
        came.main.showLoading();
        var usuarioPaso = came.env.getEnvUser();

        $.ajax({
            type: "GET",
            url: urls.api.getDirectoryReport,
            dataType: "json",
            async: true,
            contentType: 'application/json',
            data: {
                region: usuarioPaso.idRegion,
                deprov: usuarioPaso.idDeprov,
                comuna: $(getIds.selector_comuna).val(),
            }

        }).done(function(response) {
            if ($(getIds.table_directorio).DataTable() !== null) {
                $(getIds.table_directorio).DataTable().destroy();
            }
            $(getIds.table_directorio + ' tbody tr').remove();
            console.log(response);
            $(getIds.count_directory).text(response.length);
            var table = $(getIds.table_directorio);
            for (var i = 0; i < response.length; i++) {
                //var urlDescarga = came.contexto + '/downloadController/getFile?filePath=' + came.uploadMainFolder +  response[i].templatePath.replace(/\\/g, '/');

                var row = $('<tr></tr>');
                row.append($('<td></td>').append($('<span />').text(response[i].nombre).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].rbd).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].ruralidad).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].categorizacion).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].clasificacionSEP).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].dependencia).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].estado).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].matriculasTotal).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].tipoDeApoyo).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].supervisor).html()));

                table.append(row);
            }

            came.main.initList(getIds.table_directorio);

            $(getIds.search_directorio).css("display", "none");
            $(getIds.search_result).css("display", "");
            came.main.hideLoading();

        }).fail(function(jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            came.main.hideLoading();
            came.notify.showError("Reporte directorio", "Su solicitud se ha procesado con errores.");
            return false;
        });
    }

    function getProvincias(sender) {

        $(getIds.selector_deprov + " option").each(function() {
            $(this).remove();
        });

        var idRegion = $(sender).val();
        var region = $(getIds.selector_region + " option:selected").text();
        console.log(region);
        if (idRegion !== '') {
            $.ajax({
                type: "GET",
                url: urls.api.getProvincias,
                dataType: "json",
                async: false,
                //contentType: 'application/json',
                data: { idRegion: idRegion }
            }).done(function(response) {
                console.log("response done", JSON.stringify(response));
                $(getIds.selector_deprov).append($('<option selected>').val('').text('Seleccionar'));
                for (var i = 0; i < response.length; i++) {
                    $(getIds.selector_deprov).append($('<option>').val(response[i].value).text(response[i].displayText));
                    $(getIds.lbl_deprov).val(response[i].value).text(response[i].displayText.toUpperCase());
                    $(getIds.lbl_region).text(region.toUpperCase());
                   
                }
            }).fail(function(jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Reporte directorio", "Su solicitud se ha procesado con errores.");
                return false;
            });
        }else{
        	$(getIds.selector_deprov).append($('<option selected>').val('').text('Seleccionar'));
        }
    }

    function getComunas(sender) {

        $(getIds.selector_comuna + " option").each(function() {
            $(this).remove();
        });

        var idDeprov = $(sender).val();
        if (idDeprov !== '') {
            $.ajax({
                type: "GET",
                url: urls.api.getComunas,
                dataType: "json",
                async: false,
                //contentType: 'application/json',
                data: { idDeprov: idDeprov }
            }).done(function(response) {
                $(getIds.selector_comuna).append($('<option selected>').val('').text('Seleccionar'));
                for (var i = 0; i < response.length; i++) {
                    $(getIds.selector_comuna).append($('<option>').val(response[i].value).text(response[i].displayText));
                    $(getIds.lbl_comuna).val(response[i].value).text(response[i].displayText.toUpperCase());
                    
                }
            }).fail(function(jqXHR, textStatus) {
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
        }else{
            $(getIds.selector_comuna).append($('<option selected>').val('').text('Seleccionar'));
        }
    }

    function selectRegionDeprov() {
        var usuarioPaso = came.env.getEnvUser();
        if (usuarioPaso) {
            $(getIds.selector_region).val(usuarioPaso.idRegion);
            $(getIds.selector_region).change();
            $(getIds.selector_deprov).val(usuarioPaso.idDeprov);
            $(getIds.selector_deprov).change();
            
            $(getIds.lbl_region).val(usuarioPaso.idRegion);
            $(getIds.lbl_region).change();
         	$(getIds.lbl_deprov).val(usuarioPaso.idRegion);
            $(getIds.lbl_deprov).change();           

            if (usuarioPaso.nombrePerfilNivel === "regional") {
                $(getIds.selector_region).prop('disabled', 'disabled');
            } else if (usuarioPaso.nombrePerfilNivel === "provincial") {
                $(getIds.selector_region).prop('disabled', 'disabled');
                $(getIds.selector_deprov).prop('disabled', 'disabled');
            } else {
                $(getIds.selector_region).prop('disabled', false);
                $(getIds.selector_deprov).prop('disabled', false);
            }
        }
    }

    function init() {
        initListener();
        selectRegionDeprov();
    }

    return {
        init: init
    };
})();

$(document).ready(function() {
    came.directoryreport.init();
});