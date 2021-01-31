'use strict';
var came = came || {};

came.establishment = (function () {

    var urls = {
        api: {
            findEst: came.contexto + "/wsdw/wewe",
            getProvincias: came.contexto + "/establishment/getProvincias",
            getEstablecimientos: came.contexto + "/establishment/getEstablecimientos",
            getEstablecimiento: came.contexto + "/establishment/getEstablecimiento",
            exportToExcel: came.contexto + "/establishment/export/excel"
        }
    };

    var getIds = {
        find_directory: "#find-directory",
        count_directory: "#count-directory",
        search_establecimientos: "#search-establecimientos",
        view_result: "#search-result",
        view_search: "#view-search",
        view_detail: "#detail-establecimiento",
        move_to_detail: ".move_to_detail",

        export_excel_establecimientos: "#export-excel-establecimientos",
        establecimiento_location_iframe: "#establecimiento-location-iframe",

        selector_region: "#selector-region",
        selector_deprov: "#selector-deprov",
        selector_ruralidad: "#selector-ruralidad",
        selector_categorizacion: "#selector-categorizacion",
        selector_clasificacion_sep: "#selector-clasificacion-sep",
        selector_estado: "#selector-estado",
        selector_dependencias: "#selector-dependencias",
        text_filter: "#text-filter",
        table_establecimientos: "#table-establecimientos",
        detail_back: "#detail-back",
        establecimiento_detail: {
            nombre: '#establecimiento-detalle-nombre',
            direccion: '#establecimiento-detalle-direccion',
            region: '#establecimiento-detalle-region',
            deprov: '#establecimiento-detalle-deprov',
            comuna: '#establecimiento-detalle-comuna',
            email: '#establecimiento-detalle-email',
            telefono: '#establecimiento-detalle-telefono',
            table_detail_orden: "#table-detail-orden",
            sostenedor: {
                nombre: '#establecimiento-detalle-sostenedor-nombre',
                rut: '#establecimiento-detalle-sostenedor-rut',
                telefono: '#establecimiento-detalle-sostenedor-telefono',
                email: '#establecimiento-detalle-sostenedor-email'
            },
            director: {
                nombre: '#establecimiento-detalle-director-nombre',
                rut: '#establecimiento-detalle-director-rut',
                telefono: '#establecimiento-detalle-director-telefono',
                email: '#establecimiento-detalle-director-email'
            },
            establecimiento: {
                rbd: '#establecimiento-detalle-establecimiento-rbd',
                estado: '#establecimiento-detalle-establecimiento-estado',
                clasificacion_sep: '#establecimiento-detalle-establecimiento-clasificacion-sep',
                rurabilidad: '#establecimiento-detalle-establecimiento-rurabilidad',
                dependencias: '#establecimiento-detalle-establecimiento-dependencias',
                categoria_basica: '#establecimiento-detalle-establecimiento-categoria-basica',
                categoria_media: '#establecimiento-detalle-establecimiento-categoria-media'
            }
        }
    };

    var initListener = function () {
        $(document)
            .on("click", getIds.find_directory, function () {
                findDirectory(this);
            })
            .on("click", getIds.move_to_detail, function () {
                moveToDetail(this);
            })
            .on("click", getIds.detail_back, function () {
                detailBack(this);
            })
            .on("click", getIds.export_excel_establecimientos, function () {
                getExcel(this);
            })
            .on("change", getIds.selector_region, function () {
                getProvincias(this);
            });
    }

    function findDirectory(sender) {
        getEstablecimientos(this);
    }

    function detailBack(sender) {
        $(getIds.view_search).show();
        $(getIds.view_detail).hide();

        clearDetail(sender);
    }

    function moveToDetail(sender) {
        // came.main.showLoading();
        var rbd = $(sender).attr("data-rbd");
        $.ajax({
            type: "GET",
            url: urls.api.getEstablecimiento,
            dataType: "json",
            contentType: 'application/json',
            async: true,
            data: { rbd: rbd }
        }).done(function (response) {
            console.log("response done", JSON.stringify(response));
            loadDetail(response);
            
            $(getIds.view_search).css("display", "none");
            $(getIds.view_detail).css("display", "");
            // came.main.hideLoading();
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
            return false;
        });        
    }

    function getProvincias(sender) {

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
        }
    }

    function getExcel(sender){
        

        var link = document.createElement('a');
        link.href = (urls.api.exportToExcel + "?region="+ $(getIds.selector_region).val() +
                                        "&deprov=" + $(getIds.selector_deprov).val() + 
                                        "&ruralidad="+$(getIds.selector_ruralidad).val()+
                                        "&categorizacion="+$(getIds.selector_categorizacion).val()+
                                        "&clasificacionSEP="+$(getIds.selector_clasificacion_sep).val()+
                                        "&estado="+ $(getIds.selector_estado).val() +
                                        "&dependencia="+$(getIds.selector_dependencias).val()+
                                        "&filterText=" +$(getIds.text_filter).val());
        link.click();       
    }

    function getEstablecimientos(sender) {
        came.main.showLoading();
        $.ajax({
            type: "GET",
            url: urls.api.getEstablecimientos,
            dataType: "json",
            async: true,
            contentType: 'application/json',
            data: {
                region: $(getIds.selector_region).val(),
                deprov: $(getIds.selector_deprov).val(),
                ruralidad: $(getIds.selector_ruralidad).val(),
                categorizacion: $(getIds.selector_categorizacion).val(),
                clasificacionSEP: $(getIds.selector_clasificacion_sep).val(),
                estado: $(getIds.selector_estado).val(),
                dependencia: $(getIds.selector_dependencias).val(),
                filterText: $(getIds.text_filter).val()
            }
        }).done(function (response) {
            // console.log("response done", JSON.stringify(response));
            $(getIds.count_directory).text(response.length);
            var table = $(getIds.table_establecimientos);
            for (var i = 0; i < response.length; i++) {
                var row = $('<tr></tr>');
                row.append($('<td></td>').append($('<span />').text(response[i].rbd).html()));
                row.append($('<td><a href="#" class="move_to_detail" data-rbd="' + response[i].rbd + '">' + response[i].nombre + '</a></td>'));
                row.append($('<td></td>').append($('<span />').text(response[i].region).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].deprov).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].comuna).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].ruralidad).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].categorizacion).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].clasificacionSEP).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].dependencia).html()));
                row.append($('<td></td>').append($('<span />').text(response[i].estado).html()));

                table.append(row);
            }

            came.main.initList(table);
            
            $(getIds.search_establecimientos).css("display", "none");
            $(getIds.view_result).css("display", "");

            came.main.hideLoading();
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
            return false;
        });
    }

    function loadDetail(detail) {

        if (detail) {
            $(getIds.establecimiento_detail.nombre).text(detail.nombre);
            $(getIds.establecimiento_detail.direccion).text(detail.datosContacto.direccion + " " + detail.datosContacto.numeroDireccion);
            $(getIds.establecimiento_detail.region).text(detail.codigoGeografico.nombreRegion);
            $(getIds.establecimiento_detail.deprov).text(detail.codigoGeografico.nombreDeprov);
            $(getIds.establecimiento_detail.comuna).text(detail.codigoGeografico.nombreComuna);
            $(getIds.establecimiento_detail.email).text(detail.datosContacto.mail);
            $(getIds.establecimiento_detail.telefono).text(detail.datosContacto.telefono);

            $(getIds.establecimiento_location_iframe).attr('src',"https://maps.google.com/maps?q=" + detail.codigoGeografico.lat + "," + detail.codigoGeografico.lon + "&hl=es&z=15&output=embed");

            // sostenedor
            if (detail.sostenedor) {
                $(getIds.establecimiento_detail.sostenedor.nombre).text(detail.sostenedor.nombreSostenedor);
                $(getIds.establecimiento_detail.sostenedor.rut).text(detail.sostenedor.rutSostenedor + '-' + detail.sostenedor.dvSostenedor);
                $(getIds.establecimiento_detail.sostenedor.telefono).text(detail.sostenedor.rutSostenedor);
                $(getIds.establecimiento_detail.sostenedor.email).text(detail.datosContacto.mail);
            }
            // director establecimientoDirector
            if (detail.establecimientoDirector) {
                $(getIds.establecimiento_detail.director.nombre).text(detail.establecimientoDirector.director.nombres + " " + detail.establecimientoDirector.director.apellidoPaterno + " " + detail.establecimientoDirector.director.apellidoMaterno);
                $(getIds.establecimiento_detail.director.rut).text(detail.establecimientoDirector.director.rut + "-" + detail.establecimientoDirector.director.dv);
                $(getIds.establecimiento_detail.director.telefono).text(detail.establecimientoDirector.director.datosContacto.celular);
                $(getIds.establecimiento_detail.director.email).text(detail.establecimientoDirector.director.datosContacto.mail);
            }

            // detalle
            $(getIds.establecimiento_detail.establecimiento.rbd).text(detail.rbd + "-" + detail.dvRbd);
            $(getIds.establecimiento_detail.establecimiento.estado).text(detail.estadoEstablecimiento.glosaEstado);
            $(getIds.establecimiento_detail.establecimiento.clasificacion_sep).text(detail.clasificacionSep);
            $(getIds.establecimiento_detail.establecimiento.rurabilidad).text(detail.rural);
            $(getIds.establecimiento_detail.establecimiento.dependencias).text(detail.tipoDependencia.descripcionTipoDependencia);
            $(getIds.establecimiento_detail.establecimiento.categoria_basica).text(detail.categoriaBasica);
            $(getIds.establecimiento_detail.establecimiento.categoria_media).text(detail.categoriaMedia);

            loadCategorizacion(detail.orden);
        }
    }

    function loadCategorizacion(orden) {
        var table = $(getIds.establecimiento_detail.table_detail_orden);
        console.log(orden);

        for (var i = 0; i < orden.length; i++) {
            var row = $('<tr></tr>');
            row.append($('<td></td>').append($('<span />').text(orden[i].anio).html()));
            row.append($('<td></td>').append($('<span />').text(orden[i].nivel).html()));
            row.append($('<td></td>').append($('<span />').text(orden[i].ordenacion).html()));

            table.append(row);
        }
    }

    function clearDetail(detail) {

        if (detail) {
            $(getIds.establecimiento_detail.nombre).text('');
            $(getIds.establecimiento_detail.direccion).text('');
            $(getIds.establecimiento_detail.region).text('');
            $(getIds.establecimiento_detail.deprov).text('');
            $(getIds.establecimiento_detail.comuna).text('');
            $(getIds.establecimiento_detail.email).text('');
            $(getIds.establecimiento_detail.telefono).text('');
            // sostenedor

            $(getIds.establecimiento_detail.sostenedor.nombre).text('');
            $(getIds.establecimiento_detail.sostenedor.rut).text('');
            $(getIds.establecimiento_detail.sostenedor.telefono).text('');
            $(getIds.establecimiento_detail.sostenedor.email).text('');

            // director establecimientoDirector

            $(getIds.establecimiento_detail.director.nombre).text('');
            $(getIds.establecimiento_detail.director.rut).text('');
            $(getIds.establecimiento_detail.director.telefono).text('');
            $(getIds.establecimiento_detail.director.email).text('');


            // detalle
            $(getIds.establecimiento_detail.establecimiento.rbd).text('');
            $(getIds.establecimiento_detail.establecimiento.estado).text('');
            $(getIds.establecimiento_detail.establecimiento.clasificacion_sep).text('');
            $(getIds.establecimiento_detail.establecimiento.rurabilidad).text('');
            $(getIds.establecimiento_detail.establecimiento.dependencias).text('');
            $(getIds.establecimiento_detail.establecimiento.categoria_basica).text('');
            $(getIds.establecimiento_detail.establecimiento.categoria_media).text('');

            $(getIds.establecimiento_detail.table_detail_orden + ' tbody tr').remove();
        }
    }

    function selectRegionDeprov(){
        var usuarioPaso = came.env.getEnvUser();
        if(usuarioPaso){
            $(getIds.selector_region).val(usuarioPaso.idRegion);
            $(getIds.selector_region).change();
            $(getIds.selector_deprov).val(usuarioPaso.idDeprov);

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
        console.log('start directorio.');
        initListener();
        selectRegionDeprov();
    }

    return {
        init: init
    };
})();

$(document).ready(function () {
    came.establishment.init();
});