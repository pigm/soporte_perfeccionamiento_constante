'use strict';
var came = came || {};

came.substitutions = (function () {
    var readOnly = false;

    var availableSupers = [];

    var urls = {
        api: {
            getUserNamesSuper: came.contexto + "/substitutions/getUserNamesSuper",
            getSuperByUserName: came.contexto + "/substitutions/getSuperByUserName",
            setSupervisorSuplencia: came.contexto + "/substitutions/setSupervisorSuplencia",
            isValidRange: came.contexto + "/substitutions/isValidRange",
        }
    };

    var getIds = {
        button_agregar_suplencia: "#button-agregar-suplencia",
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
            .on("click", getIds.button_agregar_suplencia, function () {
                newSuplencia(this);
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

    function newSuplencia(sender){
        if (readOnly) {
            came.notify.showError("Suplencias", "No tienes permitido crear o modificar suplencias.");
        } else if (!came.env.getEnvUser().currentPeriod) {
            came.notify.showError("Suplencias", "No existe un periodo para crear o modificar suplencias.");
        } else {
            $(getIds.modalsuplencias).modal('show');
        }
    }

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
        if(availableSupers.indexOf($(sender).val()) >= 0){
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
        } else{
            came.notify.showError("Suplencia", "El usuario indicado no esta en la lista de supervisores activos.");
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

    function validarRangoFecha(idSupervisorAusente, startDate, endDate){
        var result = false;
                
        $.ajax({
            type: "GET",
            url: urls.api.isValidRange,
            dataType: "json",
            async: false,
            //contentType: 'application/json',
            data: { idSupervisorAusente: idSupervisorAusente, startDate: startDate, endDate: endDate }
        }).done(function (response) {
            console.log("response done", JSON.stringify(response));
            result = response;
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
            return false;
        });
        
        return result;
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
        if (isValid) {
            var valida = validarRangoFecha(supPaso.idUsuarioSupervisorAusente, supPaso.startDate, supPaso.endDate);
            if(valida){
                $(getIds.fecha_inicio).removeClass("is-invalid");
                $(getIds.fecha_termino).removeClass("is-invalid");
            } else{
                $(getIds.fecha_inicio).addClass("is-invalid");
                $(getIds.fecha_termino).addClass("is-invalid");
                isValid = false;
                came.notify.showError("Suplencia", "Verifique el rango de suplencia, parte de el puede que ya este registrado.");
            }
        }

        console.log(supPaso);
        // isValid = false;
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
        
        $(getIds.fecha_inicio).val('');
        $(getIds.fecha_termino).val('');

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

    function setViewAcces() {
        var menuPso = came.storage.getSessionStorage('menu');
        var subMenuAdmin = menuPso.find(e => e.idModule == '2416198290916770825');
        console.log(subMenuAdmin);
        if (subMenuAdmin) {
            var suplencia = subMenuAdmin.subMenu.find(e => e.idSubModule == '2416254586126861349');
            console.log(suplencia);
            readOnly = suplencia.readOnly;
            if (readOnly) {
                $("#guardarSuplencia").prop("disabled", true);          
                $("#content-suplencias :input").prop("disabled", true);
            }
        }
    }

    function init() {
        setViewAcces();
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