'use strict';
var came = came || {};

came.main = (function(){
	var modalLoading = null;

    var setValidControl = function (sender) {
		$(sender).removeClass("is-invalid");
    }
	
    var setInvalidControl = function (sender, message) {
		$(sender).addClass("is-invalid");
		if(message && message != ''){
			console.log($(this).next("div").is('.message'));
			if($(this).next("div").is('.message')){
				$(this).next("div").remove();
			}
			$(sender).after('<div class="invalid-feedback message"><p>'+message+'</p></div>');
		}
    }

    var isValidControl = function (sender) {
        console.log($(sender)); 
        console.log($(sender).val());       
        if ($(sender).val() === undefined || $(sender).val() === '') {
            console.log("is-invalid"); 
            $(sender).addClass("is-invalid");            
        } else {
            $(sender).removeClass("is-invalid");
            console.log("is-valid");
        }        
    }

    var isValidGroup = function (sender, groupName) {
        console.log($(sender).val());
        console.log(groupName);
        if ($(sender).val() === undefined || $(sender).val() === '') {
            $(groupName).addClass("is-invalid");
            $(groupName + " :input").addClass("is-invalid");
        } else {
            $(groupName).removeClass("is-invalid");
            $(groupName + " :input").removeClass("is-invalid");
        }
    }

	function isControlValid(control){
		console.log($(control).val());
		return !($(control).hasClass('is-invalid'));
	}

    function formatDate(date){
		if (date){
		// console.log(date.split('T')[0].split('-')[2]);
		// console.log(date.split('T')[0].split('-')[1]);
		// console.log(date.split('T')[0].split('-')[0]);

		return date.split('T')[0].split('-')[2] + '-' +
			date.split('T')[0].split('-')[1] + '-' +
			date.split('T')[0].split('-')[0];
		}
	}
	
	function formatTime(date) {
		if (date) {
			console.log(date.split('T')[1].split(':')[0]);			
			console.log(date.split('T')[1].split(':')[1]);
			console.log(date.split('T')[1].split(':')[2]);

			return date.split('T')[1].split(':')[0] + ':' +
				   date.split('T')[1].split(':')[1];
		}
	}

    function getBase64(file) {
		return new Promise((resolve, reject) => {
		  const reader = new FileReader();
		  reader.readAsDataURL(file);
		  reader.onload = () => {
			let encoded = reader.result.toString().replace(/^data:(.*,)?/, '');
			if ((encoded.length % 4) > 0) {
			  encoded += '='.repeat(4 - (encoded.length % 4));
			}
			resolve(encoded);
		  };
		  reader.onerror = error => reject(error);
		});
	  }

      function initList(table) {
		  console.log("DataTable => initList" + $(table).DataTable());
		if ($(table).DataTable() !== null) {
			$(table).DataTable().destroy();
		}

		$(table)
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
	}

	function showLoading(){		
		console.log("showLoading");
		$("#modalLoading").modal('show');
		//  modalLoading.modal('show');		
		// jQuery.ajaxSetup({
		// 	beforeSend: function() {
		// 		console.log('beforeSend');
		// 		console.log(modalLoading);
		// 		modalLoading.modal('show');				
		// 	},
		// 	complete: function(){
		// 		console.log('complete');
		// 		console.log(modalLoading);
		// 		modalLoading.modal('hide').data('bs.modal', null);
		// 	},
		// 	success: function() {
		// 		console.log('success');
		// 		modalLoading.modal('hide').data('bs.modal', null);
		// 	}
		//   });
	}

	function hideLoading(){
		console.log("hideLoading");
		$("#modalLoading").modal('hide').data('bs.modal', null);
	}

	function configureSetupAjax(){
		console.log("---------------------------------------------------------------------");
		$.ajaxSetup({
			dataFilter: function (data, type) {
				console.log("Response Data:");
				console.log(data.includes('data-page="login"'));
				if (data.includes('data-page="login"')){
					if (!window.location.pathname.includes('login/login')){
						window.location.reload();
						return false;
					}					
				}
				// console.log(data);
				// if (data !== "" && data === "NOAUTH") {
				// 	window.location = '/';
				// }
				return data;
			}
		});
	}

	function startLoading(){
		jQuery.ajaxSetup({
			beforeSend: function() {
				console.log('beforeSend');
				// console.log($("#modalLoading"));
				// $("#modalLoading").modal('show');				
			},
			complete: function(){
				console.log('complete');
				// console.log($("#modalLoading"));
				// $("#modalLoading").modal('hide');
			},
			success: function() {
				console.log('success');
				// $("#modalLoading").modal('hide');
			},
			error: function(){
				console.log('error');
				if (!window.location.pathname.includes('login/login')) {
					came.notify.showError("Mensaje", "Su solicitud se ha procesado con errores.");
					return false;
				}				
			}
		  });
	}

    function init() {		
		configureSetupAjax();
		startLoading();
    }

    return {
        init: init,
        isValidControl: isValidControl,
        isValidGroup: isValidGroup,
        setValidControl: setValidControl,
        setInvalidControl: setInvalidControl,
        getBase64: getBase64,
		formatDate: formatDate,
		formatTime: formatTime,
		initList: initList,
		showLoading: showLoading,
		hideLoading: hideLoading,
		isControlValid: isControlValid
    };
})();

$(document).ready(function () {
	came.main.init();
});