'use strict';
var came = came || {};

came.assignment = (function () {
    var readOnly = false;
    var urls = {
        api: {            
            getProvincias: came.contexto + "/assignment/getProvincias",
            getComunas: came.contexto + "/assignment/getComunas",
            isValidStartDate: came.contexto + "/assignment/isValidStartDate",   
            getAssignments: came.contexto + "/assignment/getAssignments",
            getAssignmentsDetails: came.contexto + "/assignment/getAssignmentsDetails",
            getAssignmentsSet: came.contexto + "/assignment/getAssignmentsSet",
            setAssignSupervisor: came.contexto + "/assignment/setAssignSupervisor",
            exportToExcel: came.contexto + "/assignment/export/excel",
            beginSupervisores: came.contexto + "/assignment/index",
        }
    };

    var getIds = {       
        panel_search: "#panel-search",
        panel_search_result: "#panel-search-result",
        table_assignment: "#table-assignment",
        count_assignment: "#count-assignment",
        selector_region: "#selector-region",
        selector_deprov: "#selector-deprov",
        selector_comuna: "#selector-comuna",        
        selector_tipo: "#selector-tipo",
        selector_tipo_red: "#selector-tipo-red",
        selector_categoria: "#selector-categoria",
        input_rbd: "#input-rbd",
        nombreEstablecimiento: "#input-nombre-establecimiento",
        begin_find:  "#begin-find",
        find_assignment: "#find-assignment",
        detalle_supervisor: ".detalle-supervisor",
        asignar_supervisor: ".asignar-supervisor",
        modal_detalle_asignacion: "#modal-detalle-asignacion",
        modal_assign_supervisor: "#modal-assign-supervisor",
        super_selector_asignaciones: "#super-selector-asignaciones",
        modal_type_assignment: "#modal-type-assignment",
        modal_name_assignment: "#modal-name-assignment",
        modal_count_establishment: "#modal-count-establishment",
        modal_table_establishment: "#modal-table-establishment",
        establishment_class: ".establishment-class",
        asignacion_button_guardar: "#asignacion-button-guardar",
        id_assign_supervisor: "#id-assign-supervisor",
        id_max_assign: "#id-max-assign",
        supervisores_modal: "#supervisores-modal",
        detail_region: "#detail-region",
        detail_deprov: "#detail-deprov",
        detail_comuna: "#detail-comuna",
        detail_tipo: "#detail-tipo",
        detail_tipo_apoyo: "detail-tipo-apoyo",
        detail_categoria: "#detail-categoria",
        detail_nombre_region: "#detail-nombre-region",
        detail_establishment_class: ".detail_establishment-class",
        detail_table_establishment: "#detail-table-establishment",
        detail_count_establishment: "#detail-count-establishment",
        export_excel_supervisores: "#export-excel-supervisores",
        mueve_supervisor: ".move",
        nombreRed: "#input-nombre-red",
        assignment_button_editar: "#assignment-button-editar",
        edit_data_id_red: "#edit-data-id-red",
        edit_data_id_asignacion_supervisor: "#edit-data-id-asignacion-supervisor",
        edit_data_id_supervisor: "#edit-data-id-supervisor"    
        	
        
    }

    var initListener = function () {
        $(document)
        	.on("click", getIds.find_assignment, function () {
        		findAssignments(this);
            })
            .on("change", getIds.selector_region, function () {
                getProvincias(this);
            })
            .on("change", getIds.selector_deprov, function () {
                getComunas(this);
            })
            .on("click", getIds.detalle_supervisor, function () {
                showDetailSupervisor(this);
            })
            .on("click", getIds.asignar_supervisor, function () {
            	showAssignSupervisor(this);
            })
            .on("click", getIds.asignacion_button_guardar, function () {
                setAssignSupervisor(this);
            })
            .on("click", getIds.begin_find, function () {
                beginFind(this);
            }).on("click", getIds.export_excel_supervisores, function () {
                getExcel(this);
            }).on("click", getIds.mueve_supervisor, function () {
                agregaSupervisor(this);
            }).on("click", getIds.assignment_button_editar, function () {
                editAssignSup(this);
            })
            
            
            
    }
    var listS = null;
    function editAssignSup(sender){

        var idRed = $(getIds.edit_data_id_red).val();
        var idAsignacionSupervisor = $(getIds.edit_data_id_asignacion_supervisor).val();
        var supervisores = [];
        if(idRed != null){
            $.ajax({
                type: "GET",
                url: urls.api.getAssignmentsDetails,
                dataType: "json",
                async: false,
                contentType: 'application/json',
                data: { idRed: idRed,
                	idAsignacionSupervisor: idAsignacionSupervisor}
            }).done(function (response) {                
                listS = response.supervisores;
                listS.forEach(function (i) {
                    supervisores.push(i.id);
                });
                $(getIds.edit_data_id_supervisor).val(supervisores);
                $(getIds.modal_detalle_asignacion).modal('hide');
                showAssignSupervisor(response, idRed, idAsignacionSupervisor);
            });
        }
        
    }


    function beginFind(sender){
        window.location.href = urls.api.beginSupervisores;
    }
    
    function setAssignSupervisor(sender) {
    	var isValid = true;
    	var assignPaso = {
                id: $(getIds.id_assign_supervisor).val(),
                supervisores: $(getIds.super_selector_asignaciones).val(),
                maxAsignacion: $(getIds.id_max_assign).val()
            };
    	
    	if(assignPaso.supervisores.length > assignPaso.maxAsignacion) {
    		came.main.setInvalidControl($(getIds.super_selector_asignaciones));
            isValid = false;
    	} else {
            came.main.setValidControl($(getIds.super_selector_asignaciones));
        }
    	
    	if(isValid) {
    		$.ajax({
                type: "POST",
                url: urls.api.setAssignSupervisor,
                dataType: "json",
                contentType: 'application/json',
                data: JSON.stringify(assignPaso)
            }).done(function (msg) {
                came.notify.showSucces("Asignación", "Su solicitud se ha procesado correctamente.");
                location.reload();
            }).fail(function (jqXHR, textStatus) {
                // TODO: aqui se debe agregar un mensaje en caso de error.
                came.notify.showError("Asignación", "Su solicitud se ha procesado con errores.");
                return false;
            });
    	}
    }
    
    function showAssignSupervisor(sender, idRed, idAsignacionSupervisor){
        var id;
        if($(sender).attr('data-id-red') != null){
            id = $(sender).attr('data-id-red');
        }else{
            id = idRed;          
        }
        if(id != null){
            $.ajax({
                type: "GET",
                url: urls.api.getAssignmentsSet,
                dataType: "json",
                async: false,
                contentType: 'application/json',
                data: { id: id}
            }).done(function (response) {        
                if (response.supervisores.length > 0) {        
                    $(getIds.modal_assign_supervisor).modal('show');
                    loadAssignSupervisor(response);
                } else {
                    came.notify.showError("Asignar supervisores", "No hay supervisores disponibles para asignar.");
                }   
            });
        }
    }
    //levanta modal para agregar supervisor
    function loadAssignSupervisor(sender) {
    
    	$(getIds.super_selector_asignaciones + " option").each(function () {
            $(this).remove();
        });

    	$(getIds.modal_type_assignment).text(sender.tipo);
    	$(getIds.modal_name_assignment).text(sender.nombre);
    	$(getIds.id_assign_supervisor).val(sender.id);
    	$(getIds.id_max_assign).val(sender.asignacionMaxima);
    	
    	if(sender.tipo === "Red") {
    		$(getIds.establishment_class).show();
    		$(getIds.modal_count_establishment).text(sender.establecimientos.length);
        	
        	$(getIds.modal_table_establishment + ' tbody tr').remove();
            var tablePaso = $(getIds.modal_table_establishment);  
            for (var i = 0; i < sender.establecimientos.length; i++) {
    	        var row = $('<tr></tr>');
    	        row.append($('<td></td>').append($('<span />').text(sender.establecimientos[i].displayText).html()));
    	        $(tablePaso).append(row);
            }
    	} else {
    		$(getIds.establishment_class).hide();
    		
    	}
        
    	for (var i = 0; i < sender.supervisores.length; i++) {
            if(listS!=null){
                for(var j = 0; j < listS.length; j++){
                    if(listS[j].id != sender.supervisores[i].value){
                        $(getIds.super_selector_asignaciones).append($('<option>').val(sender.supervisores[i].value).text(sender.supervisores[i].displayText));
                    }else{
                        $(getIds.super_selector_asignaciones).append($('<option selected>').val(sender.supervisores[i].value).text(sender.supervisores[i].displayText));
                    }
    
                }
            }else{
                $(getIds.super_selector_asignaciones).append($('<option>').val(sender.supervisores[i].value).text(sender.supervisores[i].displayText));
            }
            
    		
    	}
    	$(getIds.super_selector_asignaciones).bootstrapDualListbox('refresh');
    	
    }
    
    function showDetailSupervisor(sender){
        var idRed = $(sender).attr('data-id-red'); 
        var idAsignacionSupervisor = $(sender).attr('data-id-asignacion-supervisor'); 
        $(getIds.edit_data_id_red).val($(sender).attr('data-id-red'));
        $(getIds.edit_data_id_asignacion_supervisor).val($(sender).attr('data-id-asignacion-supervisor'));
        
        if(idRed != null){
            $.ajax({
                type: "GET",
                url: urls.api.getAssignmentsDetails,
                dataType: "json",
                async: false,
                contentType: 'application/json',
                data: { idRed: idRed,
                	idAsignacionSupervisor: idAsignacionSupervisor}
            }).done(function (response) {
                
                $(getIds.modal_detalle_asignacion).modal('show');
                loadDetailsEstablishment(response);
            }).fail(function (jqXHR, textStatus) {
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
        }
    }
    
    function loadDetailsEstablishment(sender) {
    	$(getIds.supervisores_modal).empty();
    	if(sender.tipo === "Directa") {
    		$(getIds.detail_establishment_class).hide();
    	} else {
    		$(getIds.detail_establishment_class).show();
    		$(getIds.detail_count_establishment).text(sender.establecimientos.length);
        	
        	$(getIds.detail_table_establishment + ' tbody tr').remove();
            var tablePaso = $(getIds.detail_table_establishment);  
            for (var i = 0; i < sender.establecimientos.length; i++) {
    	        var row = $('<tr></tr>');
    	        row.append($('<td></td>').append($('<span />').text(sender.establecimientos[i].displayText).html()));
    	        $(tablePaso).append(row);
            }
    	}
    	var supervisores = sender.supervisores;
    	for (var i = 0; i < supervisores.length; i++) {
            $(getIds.supervisores_modal).append('<div class="col-md-4 px-4">&nbsp;Usuario Dominio</div><div class="col-md-8">: <b>'+ supervisores[i].displayText+'</b></div>');
            $(getIds.supervisores_modal).append('<div class="col-md-4 px-4">&nbsp;Correo:</div><div class="col-md-8">: <b>'+ supervisores[i].value+'</b></div>');
    	}
    	$(getIds.detail_region).text(sender.region);
    	$(getIds.detail_deprov).text(sender.deprov);
    	$(getIds.detail_comuna).text(sender.comuna);
    	$(getIds.detail_tipo).text(sender.tipo);
    	$(getIds.detail_tipo_apoyo).text(sender.tipoApoyo);
    	$(getIds.detail_categoria).text(sender.categoria);
        $(getIds.detail_nombre_region).text(sender.nombreRed);
        
    	
    }
    
    function findAssignments(sender){
        $.ajax({
            type: "GET",
            url: urls.api.getAssignments,
            //dataType: "json",
            // async: false,
            contentType: 'application/json',
            data: jQuery.param({ 
            	regionId: $(getIds.selector_region).val(),
            	deprovId: $(getIds.selector_deprov).val(),
            	comunaId: $(getIds.selector_comuna).val(),
            	tipoId: $(getIds.selector_tipo).val(),
            	tipoRedId: $(getIds.selector_tipo_red).val(),
            	categoriaId: $(getIds.selector_categoria).val(),
            	rbd: $(getIds.input_rbd).val(),
            	nombreEstablecimiento: $(getIds.input_nombre_establecimiento).val(),
            	nombreRed: $(getIds.input_nombre_red).val()
                              
            })
        }).done(function (response) {
            console.log(typeof (response));
            if (typeof (response) !== "string"){
                $(getIds.count_assignment).text(response.length);
                $(getIds.table_assignment + ' tbody tr').remove();
                var tablePaso = $(getIds.table_assignment);
                loadAssignments(tablePaso, response);

                $(getIds.panel_search).css("display", "none");
                $(getIds.panel_search_result).css("display", "");
            } else {
                came.notify.showError("Asignar supervisores", "Su solicitud se ha procesado con errores.");
            }            
        }).fail(function (jqXHR, textStatus) {
            came.notify.showError("Asignar supervisores", "Su solicitud se ha procesado con errores.");
            return false;
        });
    }

    function getExcel(sender){
        
        var link = document.createElement('a');
        link.href = (urls.api.exportToExcel +  "?regionId="+ $(getIds.selector_region).val() +
                                                "&deprovId=" + $(getIds.selector_deprov).val() + 
                                                "&comunaId="+$(getIds.selector_comuna).val()+
                                                "&tipoId="+$(getIds.selector_tipo).val()+
                                                "&tipoRedId="+$(getIds.selector_tipo_red).val()+
                                                "&categoriaId="+$(getIds.selector_categoria).val()+
                                                "&rbd="+$(getIds.input_rbd).val()+
                                                "&nombreEstablecimiento="+$(getIds.nombreEstablecimiento).val()+
                                                "&nombreRed="+$(getIds.nombreEstablecimiento).val());
        link.click();
    }
    
    function loadAssignments(table, elements) {
        if (elements) {
            for (var i = 0; i < elements.length; i++) {   
                var supervisor = ''; 
                if ((elements[i].supervisors != null && elements[i].supervisors.length != 0)){
                    elements[i].supervisors.forEach(x =>{
                        supervisor += x + '</br>';
                    });
                } else{
                    supervisor = "Sin Asignar"
                }

            	// var supervisor = (elements[i].supervisors != null && elements[i].supervisors.length != 0) ? elements[i].supervisors : "Sin Asignar";
            	var estilo_supervisor = (elements[i].supervisors != null && elements[i].supervisors.length != 0) ? "detalle-supervisor" : "asignar-supervisor";
                var row = $('<tr></tr>');
                if (readOnly && supervisor === "Sin Asignar"){
                    row.append($('<td></td>').append($('<span />').text(supervisor).html()));    
                } else {
                    row.append($('<td></td>').append('<a href="#" class="' + estilo_supervisor + '" data-id-red="' + elements[i].id + '" data-id-asignacion-supervisor="' + elements[i].idAsignacionSupervisor + '">' + supervisor + '</a>'));
                }
                row.append($('<td></td>').append($('<span />').text(elements[i].rbd).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].region).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].deprov).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].comuna).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].tipo).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].tipoRed).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].categoria).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].nombreRed).html()));
                // row.append($('<td></td>').append('<a href="#" class="detalle-red" data-id-red="' + elements[i].idAsignacionSupervisor + '">Ver...</a>'));
                $(table).append(row);
            }

            came.main.initList(table);
        }
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
                $(getIds.selector_deprov).append($('<option selected>').val('').text('Seleccionar'));
                for (var i = 0; i < response.length; i++) {
                    $(getIds.selector_deprov).append($('<option>').val(response[i].value).text(response[i].displayText));
                }
            }).fail(function (jqXHR, textStatus) {
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
        } else{
            $(getIds.selector_deprov).append($('<option selected>').val('').text('Seleccionar'));
        }
    }

    function getComunas(sender) {

        $(getIds.red_selector_comuna + " option").each(function () {
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
            }).done(function (response) {
                $(getIds.selector_comuna).append($('<option selected>').val('').text('Seleccionar'));
                for (var i = 0; i < response.length; i++) {
                    $(getIds.selector_comuna).append($('<option>').val(response[i].value).text(response[i].displayText));
                }
            }).fail(function (jqXHR, textStatus) {
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
        }
    }

    function selectRegionDeprov() {
        var usuarioPaso = came.env.getEnvUser();
        if (usuarioPaso) {
            $(getIds.selector_region).val(usuarioPaso.idRegion);
            $(getIds.selector_region).change();
            $(getIds.selector_deprov).val(usuarioPaso.idDeprov);
            $(getIds.selector_deprov).change();

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

    function setViewAcces() {
        var menuPso = came.storage.getSessionStorage('menu');
        var subMenuProcess = menuPso.find(e => e.idModule == '2416198290916770826');
        console.log(subMenuProcess);
        if (subMenuProcess) {
            var supervisorPaso = subMenuProcess.subMenu.find(e => e.idSubModule == '2416254586126861335');
            console.log(supervisorPaso);
            readOnly = supervisorPaso.readOnly;
            if (readOnly) {
                // $("#content-network :input").prop("disabled", true);
                // $("#red-button-guardar").prop("disabled", true);
                $("#assignment-button-editar").hide();
            }
        }
    }

    function init() {
        setViewAcces();
        initListener();
        selectRegionDeprov();
    }

    return {
        init: init
    };

})();

$(document).ready(function () {
    console.log('came.assignment.init()');
    came.assignment.init();
});