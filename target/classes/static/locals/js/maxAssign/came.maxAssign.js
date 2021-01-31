'use strict';
var came = came || {};

came.maxAssign = (function () {
    var readOnly = false;
    let maxAssignObj = {};
    var popoverPaso = null;

    var urls = {
        api: {
            setMaxAssign: came.contexto + "/maxassign/setMaxAssign"
        }
    };

    var getIds = {
        supervisor: ".supervisor",
        foco: ".foco",
        closePopover: '.closePopover',
        setSupervisores: '.setSupervisores',
        setFoco: '.setFoco',
        supervisoresAsignacionMaxima: "#supervisoresAsignacionMaxima",
        focoAsignacionMaxima: "#focoAsignacionMaxima"
    };

    var initListener = function () {
        $(document)
            .on("click", getIds.supervisor, function () {                
                showSupervisorMax(this);
            })
            .on("click", getIds.foco, function () {
                showFocoMax(this);
            })
            .on("click", getIds.closePopover, function () {
                closePopover(this);
            })
            .on("click", getIds.setSupervisores, function () {
                setSupervisores(this);
            })
            .on("click", getIds.setSupervisores, function () {
                setSupervisores(this);
            })
            .on("click", getIds.setFoco, function () {
                setFoco(this);
            });
        }

    function setInitialmaxAssign(record){
        console.log(record);
        maxAssignObj = record;
    }

    function setSupervisores(sender){
        
        console.log($('#valueSupervisor').val())

        maxAssignObj.supervisores = $('#valueSupervisor').val()

        $.ajax({
            type: "POST",
            url: urls.api.setMaxAssign,
            dataType: "json",
            contentType: 'application/json',
            data: JSON.stringify(maxAssignObj)
        }).done(function (msg) {
            console.log("response done", msg);           
            if (popoverPaso) {
                $(popoverPaso).popover('hide');
            }
            came.notify.showSucces("Lista", "Su solicitud se ha procesado correctamente.");
            $(getIds.supervisoresAsignacionMaxima).text($('#valueSupervisor').val());
            setInitialmaxAssign(maxAssignObj);
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            // TODO: aqui se debe agregar un mensaje en caso de error.
            came.notify.showError("Lista", "Su solicitud se ha procesado con errores.");
            return false;
        });        
    }

    function setFoco(sender){
        
        console.log($('#valueFoco').val());

        maxAssignObj.foco = $('#valueFoco').val()

        $.ajax({
            type: "POST",
            url: urls.api.setMaxAssign,
            dataType: "json",
            contentType: 'application/json',
            data: JSON.stringify(maxAssignObj)
        }).done(function (msg) {
            console.log("response done", msg);           
            if (popoverPaso) {
                $(popoverPaso).popover('hide');
            }
            came.notify.showSucces("Lista", "Su solicitud se ha procesado correctamente.");
            $(getIds.focoAsignacionMaxima).text($('#valueFoco').val());
            setInitialmaxAssign(maxAssignObj);
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            // TODO: aqui se debe agregar un mensaje en caso de error.
            came.notify.showError("Lista", "Su solicitud se ha procesado con errores.");
            return false;
        });        
    }

    function showSupervisorMax(sender){
        console.log(sender);
        console.log(maxAssignObj);
        if (popoverPaso) {
            $(popoverPaso).popover('hide');

            $(popoverPaso).popover('dispose');
        }

        popoverPaso = $(sender).popover({
            placement: 'top',
            container: 'body',
            // trigger: 'focus',
            html: true,
            sanitize: false,
            content:
            '<div class="">' +
            '<select class="custom-select custom-select-sm" id="valueSupervisor">' +            
            '<option' + (maxAssignObj.supervisores === '1' ? ' selected' : '') + '>1</option>' +
            '<option' + (maxAssignObj.supervisores === '2' ? ' selected' : '') + '>2</option>' +
            '<option' + (maxAssignObj.supervisores === '3' ? ' selected' : '') + '>3</option>' +
            '<option' + (maxAssignObj.supervisores === '4' ? ' selected' : '') + '>4</option>' +
            '</select> <br> <a class="btn btn-sm btn-secondary float-left mb-2 mt-2 closePopover" href="#">Cancelar</a> <a class="setSupervisores btn btn-sm btn-primary mt-2 ml-2 float-right" href="#">Guardar</a> </div>'
        });

        popoverPaso.popover('show');
    }

    function showFocoMax(sender){
        console.log(sender);
        console.log(maxAssignObj);
        if (popoverPaso) {
            $(popoverPaso).popover('hide');
            $(popoverPaso).popover('dispose');
        }

        popoverPaso = $(sender).popover({
            placement: 'top',
            container: 'body',
            // trigger: 'focus',
            html: true,
            sanitize: false,
            content:
            '<div class="">' +
            '<select class="custom-select custom-select-sm" id="valueFoco">' +            
            '<option' + (maxAssignObj.foco === '1' ? ' selected' : '') + '>1</option>' +
            '<option' + (maxAssignObj.foco === '2' ? ' selected' : '') + '>2</option>' +
            '<option' + (maxAssignObj.foco === '3' ? ' selected' : '') + '>3</option>' +
            '<option' + (maxAssignObj.foco === '4' ? ' selected' : '') + '>4</option>' +
            '</select> <br> <a class="btn btn-sm btn-secondary float-left mb-2 mt-2 closePopover" href="#">Cancelar</a> <a class="setFoco btn btn-sm btn-primary mt-2 ml-2 float-right" href="#">Guardar</a> </div>'
        });

        popoverPaso.popover('show');
    }

    function closePopover(sender) {
        console.log(sender);
        $(popoverPaso).popover('hide');
    }

    function setViewAcces() {
        var menuPso = came.storage.getSessionStorage('menu');
        var subMenuAdmin = menuPso.find(e => e.idModule == '2416198290916770825');
        console.log(subMenuAdmin);
        if (subMenuAdmin) {
            var maxAssign = subMenuAdmin.subMenu.find(e => e.idSubModule == '2416254586126861348');
            console.log(maxAssign);
            readOnly = maxAssign.readOnly;
            if (readOnly) {
                $(".supervisor").prop("disabled", true);
                $(".foco").prop("disabled", true);
            }
        }
    }

    function init(){
        console.log('start Asignacion maxima.'); 
        setViewAcces();
        initListener();   
    }

    return {
        setInitialmaxAssign: setInitialmaxAssign,
        
        init: init
    };
})();

$(document).ready(function () {
    came.maxAssign.init();
});