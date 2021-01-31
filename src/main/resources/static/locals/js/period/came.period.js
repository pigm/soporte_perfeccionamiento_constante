'use strict';
var came = came || {};


came.period = (function () {
	var readOnly = false;
	var urls = {
		api: {
			existPeriod: came.contexto + "/period/existPeriod",
			setPeriod: came.contexto + "/period/setPeriod",
			getPeriod: came.contexto + "/period/getPeriod",
			setSpecialCase: came.contexto + "/period/setSpecialCase",
			getProvincias: came.contexto + "/period/getProvincias",
		}
	};

	var getIds = {
		planificar: "#btnPlanificarPeriodo",
		withPeriod: "#withPeriod",
		withoutPeriod: "#withoutPeriod",
		withoutPeriod_step1: "#withoutPeriod-step1",
		withoutPeriod_step2: "#withoutPeriod-step2",
		setPeriod: "#buttonSetPeriod",
		cancelPeriod: "#buttonCancelarPeriod",
		selector_periodo_year: "#selector-periodo-year",	
		selector_periodo_year_2: "#selector-periodo-year-2",		
		period_current_year: ".period-current-year",
		period: {
			anio: "#selector-periodo-year",
			fechaInicioRegional: "#fecha-inicio-regional",
			fechaFinRegional: "#fecha-fin-regional",
			documentoRegional: "#documento-regional",
			fechaInicioProvincial: "#fecha-inicio-provincial",
			fechaFinProvincial: "#fecha-fin-provincial",
			documentoProvincial: "#documento-provincial",
			fechaInicioRedes: "#fecha-inicio-redes",
			fechaFinRedes: "#fecha-fin-redes",
			fechaInicioSupervisores: "#fecha-inicio-supervisores",
			fechaFinSupervisores: "#fecha-fin-supervisores",
			fechaInicioImpApoyo: "#fecha-inicio-apoyo",
			fechaFinImpApoyo: "#fecha-fin-apoyo"
		},
		period_exist: {
			fechaInicioRegional: "#regionales-fecha-inicio",
			fechaFinRegional: "#regionales-fecha-fin",
			archivoRegional: "#btn_documento_regional_file",
			special_case_regional_count: "#special-case-regional-count",
			table_documentos_regionales: "#table-documentos-regionales",

			// documentoRegional: "#provinciales-fecha-inicio",
			fechaInicioProvincial: "#provinciales-fecha-inicio",
			fechaFinProvincial: "#provinciales-fecha-fin",
			archivoPronviciales: "#btn_documento_provinciales_file",
			
			special_case_provinciales_count: "#special-case-provinciales-count",
			table_documentos_provinciales: "#table-documentos-provinciales",

			// documentoProvincial: "#documento-provincial",

			fechaInicioRedes: "#redes-fecha-inicio",
			fechaFinRedes: "#redes-fecha-fin",
			special_case_redes_count: "#special-case-redes-count",
			table_specials_cases_redes: "#table-specials-cases-redes",

			fechaInicioSupervisores: "#supervisores-fecha-inicio",
			fechaFinSupervisores: "#supervisores-fecha-fin",
			special_case_supervisores_count: "#special-case-supervisores-count",
			table_specials_cases_supervisores: "#table-specials-cases-supervisores",

			fechaInicioImpApoyo: "#apoyo-fecha-inicio",
			fechaFinImpApoyo: "#apoyo-fecha-fin",
			special_case_apoyo_count: "#special-case-apoyo-count",
			table_specials_cases_apoyo: "#table-specials-cases-apoyo",

			special_case: {
				modal: "#modal-new-special-case",
				modal_confirm: "#confirmarCaso",
				create: ".create-special-case",
				period: "#special-case-period",
				module: "#special-case-module",
				region: "#special-case-region",
				inicio: "#special-case-fecha-inicio",
				fin: "#special-case-fecha-fin",
				observation: "#special-case-observation",
				template: "#special-case-template",
				table_special_case_deprov: "#table-special-case-deprov",

				deprov_check_all: ".deprov-check-all",

				save: "#special-case-save",



				special_case_confirm_periodo: "#special-case-confirm-periodo",
				special_case_confirm_modulo: "#special-case-confirm-modulo",
				special_case_confirm_region: "#special-case-confirm-region",
				special_case_confirm_deprov: "#special-case-confirm-deprov",
				special_case_confirm_fecha_inicio: "#special-case-confirm-fecha-inicio",
				special_case_confirm_fecha_termino: "#special-case-confirm-fecha-termino",
				special_case_confirm_observation: "#special-case-confirm-observation",
				special_case_confirm_template: "#special-case-confirm-template",

				special_case_confirm_Modificar: "#special-case-confirm-Modificar",
				special_case_confirm_crear: "#special-case-confirm-crear"
			}
		}
	};

	var initListener = function () {

		$(document)
			.on("click", getIds.planificar, function () {
				setVisible(false);
				setwithoutPeriodStep(2);
			})
			.on("click", getIds.setPeriod, function () {
				setPeriod(this);
			})
			.on("click", getIds.cancelPeriod, function () {
				window.location.reload();
			})
			.on("click", getIds.period_exist.special_case.create, function () {
				showNewSpecialCase(this);
			})
			.on("click", getIds.period_exist.special_case.save, function () {
				showConfirmSpecialCase(this);
			})
			.on("click", getIds.period_exist.special_case.special_case_confirm_Modificar, function () {
				showNewSpecialCase(this);
			})
			.on("click", getIds.period_exist.special_case.special_case_confirm_crear, function () {
				setSpecialCase(this);
			})
			.on("change", getIds.selector_periodo_year, function () {		
				$(getIds.selector_periodo_year_2).val($(this).val());		
				selectPeriodo(this);
			})
			.on("change", getIds.selector_periodo_year_2, function () {		
				$(getIds.selector_periodo_year).val($(this).val());				
				selectPeriodo(this);
			})
			.on("change", getIds.period_exist.special_case.region, function () {
				getProvincias(this);
			})
			.on("change", getIds.period_exist.special_case.deprov_check_all, function () {
				checkAllDeprov(this);
			})
			.on("hidden.bs.modal", getIds.modal, function () {
				clearModal(this);
			});
	};

	function clearModal(sender){
		$(getIds.period_exist.special_case.period).text('');
		$(getIds.period_exist.special_case.module).attr('data-moduloId', '');
		$(getIds.period_exist.special_case.region).val('');
		$(getIds.period_exist.special_case.region).change();
		$(getIds.period_exist.special_case.module).attr('data-deprov', '');
		$(getIds.period_exist.special_case.inicio).val('');
		$(getIds.period_exist.special_case.fin).val('');
		$(getIds.period_exist.special_case.observation).val('');

		$(getIds.period_exist.special_case.template).prop("files", null);
	}

	function selectPeriodo(sender) {
		console.log($(sender).val());
		existPeriod($(sender).val());
	}

	var existPeriod = function (periodo) {
		$.ajax({
			type: "GET",
			url: urls.api.existPeriod,
			dataType: "json",
			async: false,
			// contentType: 'application/json',
			data: {
				period: periodo
			}
		}).done(function (response) {
			console.log("response done", response);
			// alert(JSON.stringify(response));
			setVisible(response);

		}).fail(function (jqXHR, textStatus) {
			console.error("response fail", jqXHR);
			return false;
		});
	};

	function getPeriod(period) {
		$.ajax({
			type: "GET",
			url: urls.api.getPeriod,
			dataType: "json",
			async: false,
			// contentType: 'application/json',
			data: {
				period: period
			}
		}).done(function (response) {
			console.log("response done", response);
			// alert(JSON.stringify(response));
			displayPeriodExist(response);
			displayFiles(response);

		}).fail(function (jqXHR, textStatus) {
			console.error("response fail", jqXHR);
			return false;
		});
	}

	async function setPeriod(sender) {
		var isValid = true;

		var periodObject = await loadPeriod();
		isValid = isValidPeriod(periodObject);
		console.log(periodObject);
		if (isValid) {
			$.ajax({
				type: "POST",
				url: urls.api.setPeriod,
				dataType: "json",
				contentType: 'application/json',
				data: JSON.stringify(periodObject)
			}).done(function (msg) {
				console.log("response done", msg);
				came.notify.showSucces("usuarios", "Su solicitud se ha procesado correctamente.");
				location.reload();
			}).fail(function (jqXHR, textStatus) {
				console.error("response fail", jqXHR);
				// TODO: aqui se debe agregar un mensaje en caso de error.
				came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
				return false;
			});
		} else {
			console.log('setUser invalid');
			return false;
		}
	}

	async function loadPeriod() {
		var documentoRegional = $(getIds.period.documentoRegional).prop("files")[0];
		if (documentoRegional) {
			var documentRegionalPaso = await came.main.getBase64(documentoRegional);
		}

		var documentoProvincial = $(getIds.period.documentoProvincial).prop("files")[0];
		if (documentoProvincial) {
			var documentProvincialPaso = await came.main.getBase64(documentoProvincial);
		}

		return {
			// idPeriodo: 0,
			anio: $(getIds.period.anio).val(),
			documentosRegionales: {
				fechaInicio: $(getIds.period.fechaInicioRegional).val(),
				fechaFin: $(getIds.period.fechaFinRegional).val(),
				regionalDocument: documentRegionalPaso,
				regionalDocumentName: documentoRegional?.name
			},
			documentosProvinciales: {
				fechaInicio: $(getIds.period.fechaInicioProvincial).val(),
				fechaFin: $(getIds.period.fechaFinProvincial).val(),
				provincialDocument: documentProvincialPaso,
				provincialDocumentName: documentoProvincial?.name
			},
			conformacionRedes: {
				fechaInicio: $(getIds.period.fechaInicioRedes).val(),
				fechaFin: $(getIds.period.fechaFinRedes).val(),
			},
			asignaciones: {
				fechaInicio: $(getIds.period.fechaInicioSupervisores).val(),
				fechaFin: $(getIds.period.fechaFinSupervisores).val()
			},
			implementacionesApoyo: {
				fechaInicio: $(getIds.period.fechaInicioImpApoyo).val(),
				fechaFin: $(getIds.period.fechaFinImpApoyo).val()
			}
		}
	}

	function isValidPeriod(period) {
		let valid = true;
		
		if (!(period.documentosRegionales.fechaInicio) ||
			!(period.documentosRegionales.fechaFin) ||
			((period.documentosRegionales.fechaInicio) && new Date(period.documentosRegionales.fechaInicio).getFullYear() != period.anio) ||
			((period.documentosRegionales.fechaFinRegional) && new Date(period.documentosRegionales.fechaFinRegional).getFullYear() != period.anio) ||
			(period.documentosRegionales.fechaInicio > period.documentosRegionales.fechaFin)) {
			came.main.setInvalidControl($(getIds.period.fechaInicioRegional));
			came.main.setInvalidControl($(getIds.period.fechaFinRegional));
			valid = false
		} else {
			came.main.setValidControl($(getIds.period.fechaInicioRegional));
			came.main.setValidControl($(getIds.period.fechaFinRegional));
		}

		if (!(period.documentosProvinciales.fechaInicio) ||
			!(period.documentosProvinciales.fechaFin) ||

			((period.documentosProvinciales.fechaInicio) && new Date(period.documentosProvinciales.fechaInicio).getFullYear() != period.anio) ||
			((period.documentosProvinciales.fechaFinRegional) && new Date(period.documentosProvinciales.fechaFinRegional).getFullYear() != period.anio) ||

			(period.documentosProvinciales.fechaInicio > period.documentosProvinciales.fechaFin)) {
			came.main.setInvalidControl($(getIds.period.fechaInicioProvincial));
			came.main.setInvalidControl($(getIds.period.fechaFinProvincial));
			valid = false;
		} else {
			came.main.setValidControl($(getIds.period.fechaInicioProvincial));
			came.main.setValidControl($(getIds.period.fechaFinProvincial));
		}

		if (!(period.conformacionRedes.fechaInicio) ||
			!(period.conformacionRedes.fechaFin) ||

			((period.conformacionRedes.fechaInicio) && new Date(period.conformacionRedes.fechaInicio).getFullYear() != period.anio) ||
			((period.conformacionRedes.fechaFinRegional) && new Date(period.conformacionRedes.fechaFinRegional).getFullYear() != period.anio) ||

			(period.conformacionRedes.fechaInicio > period.conformacionRedes.fechaFin)) {
			came.main.setInvalidControl($(getIds.period.fechaInicioRedes));
			came.main.setInvalidControl($(getIds.period.fechaFinRedes));
			valid = false
		} else {
			came.main.setValidControl($(getIds.period.fechaInicioRedes));
			came.main.setValidControl($(getIds.period.fechaFinRedes));
		}

		if (!(period.asignaciones.fechaInicio) ||
			!(period.asignaciones.fechaFin) ||

			((period.asignaciones.fechaInicio) && new Date(period.asignaciones.fechaInicio).getFullYear() != period.anio) ||
			((period.asignaciones.fechaFinRegional) && new Date(period.asignaciones.fechaFinRegional).getFullYear() != period.anio) ||

			(period.asignaciones.fechaInicio > period.asignaciones.fechaFin)) {
			came.main.setInvalidControl($(getIds.period.fechaInicioSupervisores));
			came.main.setInvalidControl($(getIds.period.fechaFinSupervisores));
			valid = false
		} else {
			came.main.setValidControl($(getIds.period.fechaInicioSupervisores));
			came.main.setValidControl($(getIds.period.fechaFinSupervisores));
		}

		if (!(period.implementacionesApoyo.fechaInicio) ||
			!(period.implementacionesApoyo.fechaFin) ||

			((period.asignaciones.fechaInicio) && new Date(period.asignaciones.fechaInicio).getFullYear() != period.anio) ||
			((period.asignaciones.fechaFinRegional) && new Date(period.asignaciones.fechaFinRegional).getFullYear() != period.anio) ||

			(period.implementacionesApoyo.fechaInicio > period.implementacionesApoyo.fechaFin)) {
			came.main.setInvalidControl($(getIds.period.fechaInicioImpApoyo));
			came.main.setInvalidControl($(getIds.period.fechaFinImpApoyo));
			valid = false
		} else {
			came.main.setValidControl($(getIds.period.fechaInicioImpApoyo));
			came.main.setValidControl($(getIds.period.fechaFinImpApoyo));
		}

		return valid;
	}

	function setVisible(exist) {
		// exist = true;
		if (exist) {
			$(getIds.withPeriod).css("display", "");
			$(getIds.withoutPeriod).css("display", "none");
			// si el periodo existe, lo obtengo			
			getPeriod($(getIds.selector_periodo_year).val());
		} else {			
			$(getIds.withPeriod).css("display", "none");
			$(getIds.withoutPeriod).css("display", "");
			setwithoutPeriodStep(1);
		}
	}

	function setwithoutPeriodStep(step) {
		// exist = true;
		if (step === 1) {
			$(getIds.withoutPeriod_step1).css("display", "");
			$(getIds.withoutPeriod_step2).css("display", "none");
		} else {
			console.log($(getIds.selector_periodo_year).val());
			$(getIds.period_current_year).text($(getIds.selector_periodo_year).val());

			$(getIds.withoutPeriod_step1).css("display", "none");
			$(getIds.withoutPeriod_step2).css("display", "");
		}
	}
	
	function displayFiles(period) {
		if( period.documentosRegionales){
			$(getIds.period_exist.archivoRegional).attr('href', came.contexto + '/downloadController/getFile?filePath=' + came.uploadMainFolder + period.documentosRegionales.templatePath);
		} else{
			$(getIds.period_exist.archivoRegional).css("display", "none");
		}
		if(period.documentosProvinciales){
			$(getIds.period_exist.archivoPronviciales).attr('href', came.contexto + '/downloadController/getFile?filePath=' + came.uploadMainFolder + period.documentosProvinciales.templatePath);
		} else {
			$(getIds.period_exist.archivoPronviciales).css("display", "none");
		}
	}

	function displayPeriodExist(period) {
		if(period.documentosRegionales){
			$(getIds.period_exist.fechaInicioRegional).text(came.main.formatDate(period.documentosRegionales.fechaInicio));
			$(getIds.period_exist.fechaFinRegional).text(came.main.formatDate(period.documentosRegionales.fechaFin));
			$(getIds.period_exist.special_case_regional_count).text(period.documentosRegionalesEspeciales?.length);
		}
		loadSpecialCases(getIds.period_exist.table_documentos_regionales, period.documentosRegionalesEspeciales);
		if(period.documentosProvinciales){
			$(getIds.period_exist.fechaInicioProvincial).text(came.main.formatDate(period.documentosProvinciales.fechaInicio));
			$(getIds.period_exist.fechaFinProvincial).text(came.main.formatDate(period.documentosProvinciales.fechaFin));
			$(getIds.period_exist.special_case_provinciales_count).text(period.documentosProvincialesEspeciales?.length ?? 0);
		}
		loadSpecialCases(getIds.period_exist.table_documentos_provinciales, period.documentosProvincialesEspeciales);
		if(period.conformacionRedes){
			$(getIds.period_exist.fechaInicioRedes).text(came.main.formatDate(period.conformacionRedes.fechaInicio));
			$(getIds.period_exist.fechaFinRedes).text(came.main.formatDate(period.conformacionRedes.fechaFin));
			$(getIds.period_exist.special_case_redes_count).text(period.conformacionRedesEspeciales?.length);
		}
		loadSpecialCases(getIds.period_exist.table_specials_cases_redes, period.conformacionRedesEspeciales);
		if(period.asignaciones){
			$(getIds.period_exist.fechaInicioSupervisores).text(came.main.formatDate(period.asignaciones.fechaInicio));
			$(getIds.period_exist.fechaFinSupervisores).text(came.main.formatDate(period.asignaciones.fechaFin));
			$(getIds.period_exist.special_case_supervisores_count).text(period.asignacionesEspeciales?.length);
		}		
		loadSpecialCases(getIds.period_exist.table_specials_cases_supervisores, period.asignacionesEspeciales);
		if(period.implementacionesApoyo){
			$(getIds.period_exist.fechaInicioImpApoyo).text(came.main.formatDate(period.implementacionesApoyo.fechaInicio));
			$(getIds.period_exist.fechaFinImpApoyo).text(came.main.formatDate(period.implementacionesApoyo.fechaFin));
			$(getIds.period_exist.special_case_apoyo_count).text(period.implementacionesApoyoEspeciales?.length);
		}		
		loadSpecialCases(getIds.period_exist.table_specials_cases_apoyo, period.implementacionesApoyoEspeciales);
	}

	function loadSpecialCases(table, elements) {
		console.log(elements);

		if ($(table).DataTable() !== null) {
			$(table).DataTable().destroy();
		}

		$(table + ' tbody tr').remove();


		if (elements) {
			for (var i = 0; i < elements.length; i++) {
				var file = '';
				if (elements[i].templatePath && elements[i].templatePath != ''){
					file = '<a href="' + came.contexto + '/downloadController/getFile?filePath=' + came.uploadMainFolder + elements[i].templatePath + '" class="float-right">' +
						'<svg width="0.9em" height="0.9em" viewBox="0 0 16 16" class="bi bi-file-arrow-down"	fill="currentColor" xmlns = "http://www.w3.org/2000/svg">' +
						'<path fill-rule="evenodd" d="M4 0h8a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2zm0 1a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H4z" />' +
						'<path fill-rule="evenodd" d="M8 5a.5.5 0 0 1 .5.5v3.793l1.146-1.147a.5.5 0 0 1 .708.708l-2 2a.5.5 0 0 1-.708 0l-2-2a.5.5 0 1 1 .708-.708L7.5 9.293V5.5A.5.5 0 0 1 8 5z" /></svg >' +
						'<a>';
				}				

				var row = $('<tr></tr>');
				row.append($('<td></td>').append(elements[i].region + file));
				row.append($('<td></td>').append($('<span />').text(elements[i].deprov).html()));
				row.append($('<td></td>').append($('<span />').text(came.main.formatDate(elements[i].fechaRegistro)).html()));
				row.append($('<td></td>').append($('<span />').text(elements[i].usuarioRegistro).html()));
				row.append($('<td></td>').append($('<span />').text(came.main.formatDate(elements[i].fechaInicio)).html()));
				row.append($('<td></td>').append($('<span />').text(came.main.formatDate(elements[i].fechaFin)).html()));
				row.append($('<td></td>').append($('<span />').text(elements[i].motivo).html()));
				$(table).append(row);
			}

			came.main.initList(table);
			
		}
	}

	// function initList(table) {

	// 	if ($(table).DataTable() !== null) {
	// 		$(table).DataTable().destroy();
	// 	}

	// 	$(table)
	// 		.DataTable({
	// 			ordering: true,
	// 			searching: false,
	// 			paging: false,
	// 			info: false,
	// 			language: {
	// 				"decimal": "",
	// 				"emptyTable": "No data available in table",
	// 				"info": "Mostrando pagina _PAGE_ de _PAGES_",
	// 				"infoEmpty": "Mostrando Pagina 0 de 0",
	// 				"infoFiltered": "(filtered from _MAX_ total entries)",
	// 				"infoPostFix": "",
	// 				"thousands": ",",
	// 				"lengthMenu": "Mostrar _MENU_ registros",
	// 				"loadingRecords": "Cargando...",
	// 				"processing": "Procesando...",
	// 				"search": "Buscar:",
	// 				"zeroRecords": "No matching records found",
	// 				"paginate": {
	// 					"first": "Primero",
	// 					"last": "Ultimo",
	// 					"next": "Siguiente",
	// 					"previous": "Anterior"
	// 				},
	// 				"aria": {
	// 					"sortAscending": ": activate to sort column ascending",
	// 					"sortDescending": ": activate to sort column descending"
	// 				}
	// 			}
	// 		});
	// }

	function checkAllDeprov(sender){
		if($(sender).prop('checked')){
			$('tbody tr td input[type="checkbox"]').each(function(){
				$(this).prop('checked', true);
			});
		}else{
			$('tbody tr td input[type="checkbox"]').each(function(){
				$(this).prop('checked', false);
			});
		}
	}
	
	function showNewSpecialCase(sender) {
		console.log(sender);
		var hasTemplate = $(sender).attr("data-has-template");
		if(hasTemplate === "true"){
			$("#period-special-case-template").css("display", "");
		} else{
			$("#period-special-case-template").css("display", "none");
		}
		$(getIds.period_exist.special_case.period).text($(getIds.selector_periodo_year).val());
		$(getIds.period_exist.special_case.module).text($(sender).attr("data-modulo"));
		$(getIds.period_exist.special_case.module).attr('data-moduloId', $(sender).attr("data-moduloId"));
		$(getIds.period_exist.special_case.modal).modal('show');
	}

	function showConfirmSpecialCase(sender) {
		console.log(sender);
		var isValid = true;
		var valueList = [];
		var nameList = [];
		// table-special-case-deprov
		console.log($(getIds.period_exist.special_case.table_special_case_deprov).find('> tbody > tr'));
		$(getIds.period_exist.special_case.table_special_case_deprov)
				.find('> tbody > tr')
				.each(function() {
			$(this).find("input[name='deprov']:checked").each(function() {						  
				valueList.push($(this).attr('data-deprovId'));
				nameList.push($(this).attr('data-deprovName'));
			});
		  });
		console.log(valueList);
		console.log(nameList);

		if ($(getIds.period_exist.special_case.region).val() === '') {
			came.main.setInvalidControl(getIds.period_exist.special_case.region);
			isValid = false;
		}

		if ($(getIds.period_exist.special_case.inicio).val() === '') {
			came.main.setInvalidControl(getIds.period_exist.special_case.inicio);
			isValid = false;
		}

		if ($(getIds.period_exist.special_case.inicio).val() === '') {
			came.main.setInvalidControl(getIds.period_exist.special_case.fin);
			isValid = false;
		}

		if (isValid) {
			$(getIds.period_exist.special_case.special_case_confirm_deprov).attr('data-deprov', valueList.join());
			$(getIds.period_exist.special_case.special_case_confirm_deprov).text(nameList.join());
			$(getIds.period_exist.special_case.special_case_confirm_periodo).text($(getIds.period_exist.special_case.period).text());
			$(getIds.period_exist.special_case.special_case_confirm_modulo).text($(getIds.period_exist.special_case.module).text());
			$(getIds.period_exist.special_case.special_case_confirm_modulo).attr('data-moduloId', $(getIds.period_exist.special_case.module).attr('data-moduloId'));

			$(getIds.period_exist.special_case.special_case_confirm_region).text($(getIds.period_exist.special_case.region + " option:selected").text());
			$(getIds.period_exist.special_case.special_case_confirm_region).attr('data-regionId', $(getIds.period_exist.special_case.region + " option:selected").val());

			$(getIds.period_exist.special_case.special_case_confirm_fecha_inicio).text($(getIds.period_exist.special_case.inicio).val());
			$(getIds.period_exist.special_case.special_case_confirm_fecha_termino).text($(getIds.period_exist.special_case.fin).val());
			$(getIds.period_exist.special_case.special_case_confirm_observation).text($(getIds.period_exist.special_case.observation).val());
			$(getIds.period_exist.special_case.special_case_confirm_template).text($(getIds.period_exist.special_case.template).prop("files")[0]?.name);
			$(getIds.period_exist.special_case.modal).modal('hide');
			$(getIds.period_exist.special_case.modal_confirm).modal('show');
		}
	}

	async function setSpecialCase(sender) {

		var docSpecialCase = $(getIds.period_exist.special_case.template).prop("files")[0];
		if (docSpecialCase) {
			var docSpecialPaso = await came.main.getBase64(docSpecialCase);
		}
		
		var specialObject = {
			periodo: $(getIds.period_exist.special_case.special_case_confirm_periodo).text(),
			moduleId: $(getIds.period_exist.special_case.special_case_confirm_modulo).attr('data-moduloId'),
			regionId: $(getIds.period_exist.special_case.special_case_confirm_region).attr('data-regionId'),
			deprovId: $(getIds.period_exist.special_case.special_case_confirm_deprov).attr('data-deprov').split(','),
			startDate: $(getIds.period_exist.special_case.special_case_confirm_fecha_inicio).text(),
			endDate: $(getIds.period_exist.special_case.special_case_confirm_fecha_termino).text(),
			observation: $(getIds.period_exist.special_case.special_case_confirm_observation).text(),
			template: docSpecialPaso,
			templateFileName: docSpecialCase?.name
		};
		console.log(specialObject);
		$.ajax({
			type: "POST",
			url: urls.api.setSpecialCase,
			dataType: "json",
			contentType: 'application/json',
			data: JSON.stringify(specialObject)
		}).done(function (msg) {
			console.log("response done", msg);
			came.notify.showSucces("usuarios", "Su solicitud se ha procesado correctamente.");
			$(getIds.period_exist.special_case.modal_confirm).modal('hide');
			cleanSpecialsCases();
			location.reload();
		}).fail(function (jqXHR, textStatus) {
			console.error("response fail", jqXHR);
			// TODO: aqui se debe agregar un mensaje en caso de error.
			came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
			return false;
		});
	}

	function cleanSpecialsCases() {

		$(getIds.period_exist.special_case.period).text('');
		$(getIds.period_exist.special_case.module).text('');
		$(getIds.period_exist.special_case.region).val('');
		$(getIds.period_exist.special_case.inicio).val('');
		$(getIds.period_exist.special_case.fin).val('');
		$(getIds.period_exist.special_case.observation).val('');
		$(getIds.period_exist.special_case.template).val('');

		$(getIds.period_exist.special_case.special_case_confirm_periodo).text('');
		$(getIds.period_exist.special_case.special_case_confirm_modulo).text('');
		$(getIds.period_exist.special_case.special_case_confirm_region).text('');
		$(getIds.period_exist.special_case.special_case_confirm_fecha_inicio).text('');
		$(getIds.period_exist.special_case.special_case_confirm_fecha_termino).text('');
		$(getIds.period_exist.special_case.special_case_confirm_observation).text('');
		$(getIds.period_exist.special_case.special_case_confirm_template).text('');
	}

	var getProvincias = function (sender) {

		$(getIds.period_exist.special_case.table_special_case_deprov + ' tbody tr').remove();

		var idRegion = $(sender).val();
		var period = $(getIds.period_exist.special_case.period).text();
		var moduleId = $(getIds.period_exist.special_case.module).attr('data-moduloId');
		var tableDeprov = $(getIds.period_exist.special_case.table_special_case_deprov);
		var start = Date(); 
		var end = Date();

        if (idRegion !== '') {
            $.ajax({
                type: "GET",
                url: urls.api.getProvincias,
                dataType: "json",
                async: false,
                contentType: 'application/json',
                data: { idRegion: idRegion, period: period, moduleId: moduleId }
            }).done(function (response) {
                console.log("response done", JSON.stringify(response));
				
				if(response.length > 0){
					for (var i = 0; i < response.length; i++) {
						var row = $('<tr></tr>');
						row.append($('<td><input type="checkbox" name="deprov" data-deprovId="' + response[i].deprovId + '" data-deprovName="' + response[i].deprov +'" />&nbsp;</td>'));
						row.append($('<td></td>').append($('<span />').text(response[i].deprov).html()));
						row.append($('<td></td>').append($('<span />').text(came.main.formatDate(response[i].fechaInicio)).html()));
						row.append($('<td></td>').append($('<span />').text(came.main.formatDate(response[i].fechaFin)).html()));
	
						tableDeprov.append(row);
					}
				} else {
					var rowa = $('<tr></tr>');
					rowa.append('<td colspan="4"><span class="text-muted">Sin región seleccionada</span></td>');  
					tableDeprov.append(rowa);                                       
				}                
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");                                
                return false;
            });
        }
    }

	function setViewAcces() {
		var menuPso = came.storage.getSessionStorage('menu');
		var subMenuAdmin = menuPso.find(e => e.idModule == '2416198290916770825');
		console.log(subMenuAdmin);
		if (subMenuAdmin) {
			var period = subMenuAdmin.subMenu.find(e => e.idSubModule == '2416254586126861331');
			console.log(period);
			readOnly = period.readOnly;
			if (readOnly) {
				$(".create-special-case").prop("disabled", true);
				$(getIds.planificar).prop("disabled", true);
			}
		}
	}

	function init() {
		setViewAcces();
		initListener();
		$(getIds.selector_periodo_year).change();
	}

	return {
		init: init
	};

})();

$(document).ready(function () {
	came.period.init();
});