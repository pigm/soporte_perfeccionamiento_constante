'use strict';
var came = came || {};

came.substitutions = (function () {
    var availableSupers = [];

    var urls = {
        api: {
            getUserNamesSuper: came.contexto + "/substitutions/getUserNamesSuper",
            getSuperByUserName: came.contexto + "/substitutions/getSuperByUserName",
            setSupervisorSuplencia: came.contexto + "/substitutions/setSupervisorSuplencia"
        }
    };

    var getIds = {
        modalsuplencias: "#suplencias",
        lista: "#tabla-suplencia",
        editSuplencia: ".editSuplencia",

        userNameSA: "#userNameSA",
        userNameSS: "#userNameSS",

        nombreSA: "#nombreSA",
        perfilSA: "#perfilSA",
        regionSA: "#regionSA",
        deprovSA: "#deprovSA",
        domainUserNameSA: "#domainUserNameSA",

        nombreSS: "#nombreSS",
        perfilSS: "#perfilSS",
        regionSS: "#regionSS",
        deprovSS: "#deprovSS",
        domainUserNameSS: "#domainUserNameSS",

        fecha_inicio: "#fecha-inicio",
        fecha_termino: "#fecha-termino",

        guardarSuplencia: "#guardarSuplencia"

    };

    var initListener = function () {
        $(document)
            .on("change", getIds.userNameSA, function () {
                getSuperByUserName(this, loadDataSA);
            })
            .on("change", getIds.userNameSS, function () {
                getSuperByUserName(this, loadDataSS);
            })
            .on("click", getIds.guardarSuplencia, function () {
                setSuplencia(this);
            })
            .on("click", getIds.editSuplencia, function () {                
                loadSuplencia(this);
            })
            .on("hidden.bs.modal", getIds.modalsuplencias, function () {
                cleanObject();
            });
    };

    function getUserNamesSuper() {
        $.ajax({
            type: "GET",
            url: urls.api.getUserNamesSuper,
            dataType: "json",
            async: false,
            contentType: 'application/json'
        }).done(function (response) {
            console.info("response done", response);
            availableSupers = response;
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            return false;
        });
    }

    function getSuperByUserName(sender, callback) {
        if ($(sender).val() != '') {
            $.ajax({
                type: "GET",
                url: urls.api.getSuperByUserName,
                dataType: "json",
                async: false,
                contentType: 'application/json',
                data: { userName: $(sender).val() }
            }).done(function (response) {
                console.info("response done", response);
                callback(response);

                $(sender).val('');
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                return false;
            });
        }
    }

    function loadDataSA(response) {
        $(getIds.domainUserNameSA).attr('data-usuarioId', response.usuarioId);
        $(getIds.domainUserNameSA).text(response.userName);
        $(getIds.nombreSA).text(response.nombre);
        $(getIds.perfilSA).text(response.perfil);
        $(getIds.regionSA).text(response.region);
        $(getIds.deprovSA).text(response.deprov);
    }

    function loadDataSS(response) {
        $(getIds.domainUserNameSS).attr('data-usuarioId', response.usuarioId);
        $(getIds.domainUserNameSS).text(response.userName);
        $(getIds.nombreSS).text(response.nombre);
        $(getIds.perfilSS).text(response.perfil);
        $(getIds.regionSS).text(response.region);
        $(getIds.deprovSS).text(response.deprov);
    }

    function setSuplencia(sender) {

        let supPaso = {
            idSupervisorSuplencia: $(getIds.modalsuplencias).attr('data-idSupervisorSuplencia'),
            idUsuarioSupervisorAusente: $(getIds.domainUserNameSA).attr('data-usuarioId'),
            idUsuarioSupervisorSuplente: $(getIds.domainUserNameSS).attr('data-usuarioId'),
            startDate: $(getIds.fecha_inicio).val(),
            endDate: $(getIds.fecha_termino).val()
        };

        var isValid = true;

        if (supPaso.startDate === '') {            
            $(getIds.fecha_inicio).addClass("is-invalid");
            isValid = false;
        } else {
            $(getIds.fecha_inicio).removeClass("is-invalid");
        }

        if (supPaso.endDate === '') {            
            $(getIds.fecha_termino).addClass("is-invalid");
            isValid = false;
        } else {
            $(getIds.fecha_termino).removeClass("is-invalid");
        }

        console.log(supPaso);
        if (isValid) {
            $.ajax({
                type: "POST",
                url: urls.api.setSupervisorSuplencia,
                dataType: "json",
                contentType: 'application/json',
                data: JSON.stringify(supPaso)
            }).done(function (msg) {
                console.log("response done", msg);
                came.notify.showSucces("Suplencia", "Su solicitud se ha procesado correectamente.");
                // cierro el modal.
                $(getIds.modalsuplencias).modal('toggle');
                location.reload();                
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                if(jqXHR.status === 400){
                    came.notify.showError("Suplencia", jqXHR.responseText);    
                    alert(jqXHR.responseText);
                } else {
                    came.notify.showError("Suplencia", "El perfil indicado ha presentado errores.");
                }
                return false;
            });
        } else {
            return false;
        }

    }

    function initAutoComplete() {
        $(getIds.userNameSA).autocomplete({
            source: availableSupers.map((x) => x)
        });

        $(getIds.userNameSS).autocomplete({
            source: availableSupers.map((x) => x)
        });
    }

    function cleanObject(){
        $(getIds.domainUserNameSA).attr('data-usuarioId', '');
        $(getIds.domainUserNameSA).text('');
        $(getIds.nombreSA).text('');
        $(getIds.perfilSA).text('');
        $(getIds.regionSA).text('');
        $(getIds.deprovSA).text('');

        $(getIds.domainUserNameSS).attr('data-usuarioId', '');
        $(getIds.domainUserNameSS).text('');
        $(getIds.nombreSS).text('');
        $(getIds.perfilSS).text('');
        $(getIds.regionSS).text('');
        $(getIds.deprovSS).text('');

        $(getIds.modalsuplencias).attr('data-idSupervisorSuplencia', '');
    }

    var initList = function () {        
        $(getIds.lista)
            .DataTable({
                ordering: true,
                searching: true,
                paging: true,
                info: true,               
                language: {
                    "decimal": "",
                    "emptyTable": "No data available in table",
                    "info": "Mostrando pagina _PAGE_ de _PAGES_",
                    "infoEmpty": "Mostrando Pagina 0 de 0",
                    "infoFiltered": "(filtered from _MAX_ total entries)",
                    "infoPostFix": "",
                    "thousands": ",",
                    "lengthMenu": "Mostrar _MENU_ registros",
                    "loadingRecords": "Cargando...",
                    "processing": "Procesando...",
                    "search": "Buscar:",
                    "zeroRecords": "No matching records found",
                    "paginate": {
                        "first": "Primero",
                        "last": "Ultimo",
                        "next": "Siguiente",
                        "previous": "Anterior"
                    },
                    "aria": {
                        "sortAscending": ": activate to sort column ascending",
                        "sortDescending": ": activate to sort column descending"
                    }
                }               
            });            
    };

    function loadSuplencia(sender){
        
        $(getIds.modalsuplencias).attr('data-idSupervisorSuplencia', $(sender).attr("data-idSupervisorSuplencia"));
        $(getIds.userNameSA).val($(sender).attr("data-userNameSA")).change();
        $(getIds.userNameSS).val($(sender).attr("data-userNameSS")).change();

        $(getIds.fecha_inicio).val($(sender).attr("data-startDate"));
        $(getIds.fecha_termino).val($(sender).attr("data-endDate"));

       $(getIds.modalsuplencias).modal('show');
    }

    function init() {
        initListener();
        getUserNamesSuper();
        initAutoComplete();
        initList();
    }

    return {
        init: init
    };
})();

$(document).ready(function () {
    console.log('came.substitutions.init()');
    came.substitutions.init();
});