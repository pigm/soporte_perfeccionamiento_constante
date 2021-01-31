'use strict';
var came = came || {};

came.focus = (function () {
	var readOnly = false;
	var popoverPaso = null;

	var urls = {
		api: {
			setFocus: came.contexto + "/focus/setFocus",
			getFocusById: came.contexto + "/focus/getFocusById",
			removeAccion: came.contexto + "/focus/removeAccion",
			removeFoco: came.contexto + "/focus/removeFoco",
			loadFocus: came.contexto + "/focus/index"
		}
	};

	var getIds = {
		foco_year: "#foco-year",
		table_focos: "#table-focos",
		accion_mejora: "#accion-mejora",
		acciones: "#acciones",
		agregar_acciones_mejora: "#agregar-acciones-mejora",
		movimientos_clave: ".movimientos-clave",
		agregar_foco: "#agregar-foco",
		guardar_foco: "#guardar-foco",
		modal: "#agregarFoco",
		ver_foco: ".ver-foco",
		remove_current_action: ".remove-current-action",
		confirm_remove_action: ".confirm-remove-action",
		confirm_remove_foco: ".confirm-remove-foco",
		remove_foco: ".remove-foco",
		closePopover: '.closePopover',
		foco: {
			nombre: '#foco-nombre',
			periodo: '#foco-periodo',
			descripcion: '#foco-descripcion',
			acciones: {
				nombre: "#foco-accion-nombre",
				movimientos: "#foco-accion-movimientos"
			}
		}
	};

	var initListener = function () {

		$(document)
			.on("click", getIds.guardar_foco, function () {
				setFoco(this);
			})
			.on("click", getIds.agregar_foco, function () {
				agregarFoco(this);
			})
			.on("click", getIds.agregar_acciones_mejora, function () {
				agregarAccionesMejora(this);
			})
			.on("click", getIds.ver_foco, function () {
				showFoco(this);
			})
			.on("click", getIds.remove_current_action, function () {
				removeAction(this);
			})
			.on("click", getIds.confirm_remove_action, function () {
				confirmRemoveAction(this);
			})
			.on("click", getIds.confirm_remove_foco, function () {
				confirmRemoveFoco(this);
			})
			.on("click", getIds.remove_foco, function () {
				removeFoco(this);
			})
			.on("hidden.bs.modal", getIds.modal, function () {
				clearModal(this);
			})
			.on("click", getIds.closePopover, function () {
                closePopover(this);
			})
			.on("change", getIds.foco_year, function () {
				reloadPageFocus(this);	
			});
	}

	function reloadPageFocus(sender){
		console.log(sender);
		var link = document.createElement('a');
		link.href = (urls.api.loadFocus + "?year=" + $(sender).val());
		console.log(link.href);
		link.click();
	}

	function closePopover(sender) {
        console.log(sender);
        $(popoverPaso).popover('hide');
	}
	
	function clearModal(sender) {
		$(getIds.foco.nombre).val('');
		$(getIds.foco.periodo).val('');
		$(getIds.foco.descripcion).val('');

		$(getIds.foco.nombre).attr('data-focoId', null);

		came.main.setValidControl($(getIds.foco.nombre));
		came.main.setValidControl($(getIds.foco.periodo));
		came.main.setValidControl($(getIds.foco.descripcion));

		came.main.setValidControl($(".foco-accion-nombre"));
		came.main.setValidControl($(".foco-accion-movimientos"));

		$(getIds.acciones).find('.dynamic').each((index, element) => {
			console.log(element);
			$(element).remove();
		});

		$(getIds.acciones).each((index, element) => {
			console.log(element);
			$(element).find("input").each((i, e) => {
				if ($(e).is("input")) {
					$(e).val('');
				}
			});

			$(element).find("select").each((i, e) => {
				if ($(e).is("select")) {
					$(e).find('option')
						.remove()
						.end();
				}
			});

		});

		$(".foco-accion-movimientos").selectize({
			delimiter: ',',
			placeholder: "Escribe los movimientos claves...",
			create: function (input) {
				return {
					value: input,
					text: input
				}
			}
		});
	}

	function agregarFoco(sender) {
		if (readOnly) {
			came.notify.showError("Focos", "No tienes permitido crear o modificar focos.");
		} else if (!came.env.getEnvUser().currentPeriod) {
			came.notify.showError("Focos", "No existe un periodo para crear o modificar focos.");		
		} else {
			$(getIds.agregar_acciones_mejora).click();
			loadPluginSelect();
			$(getIds.modal).modal('show');
		}
	}

	function showFoco(sender) {
		var focoId = $(sender).attr("data-focoId");
		
		$.ajax({
			type: "GET",
			url: urls.api.getFocusById,
			dataType: "json",
			async: false,
			contentType: 'application/json',
			data: {
				focoId: focoId
			}
		}).done(function (response) {
			console.log("response done", response);

			$(getIds.foco.nombre).attr("data-focoId", response.idFoco);
			$(getIds.foco.nombre).val(response.nombre);
			$(getIds.foco.periodo).val(response.periodo);
			$(getIds.foco.descripcion).val(response.descripcion);
			
			setValuesActions(response.acciones);

			$(getIds.modal).modal('show');
		}).fail(function (jqXHR, textStatus) {
			console.error("response fail", jqXHR);
			return false;
		});
	}

	function setValuesActions(actiosArray) {
		for (var k = 0; k < actiosArray.length; k++) {
			agregarAccionesMejora(this, actiosArray[k].idAccionesMejoras);
		}

		$(getIds.acciones).find('.accion')
			.each((index, element) => {
				console.log(actiosArray[index]);
				if (actiosArray[index]) {
					var accionNombre = actiosArray[index].nombre;
					var item = $(element).find('.foco-accion-nombre');
					item.val(accionNombre);
					item.attr('idAccionesMejoras', actiosArray[index].idAccionesMejoras);
					var item2 = $(element).find('.foco-accion-movimientos');

					actiosArray[index].movimientos.forEach(function (mov) {
						console.log(mov.nombre);
						$(item2).append('<option selected>' + mov.nombre + '</option>');
						console.log(item2);
					});

					$(item2).selectize({
						delimiter: ',',
						placeholder: "Escribe los movimientos claves...",
						create: function (input) {
							return {
								value: input,
								text: input
							}
						}
					});
				}
			});

		loadPluginSelect();
	}

	function setFoco(sender) {
		let valid = true;
		var setFoco = {
			idFoco: $(getIds.foco.nombre).attr("data-focoId"),
			nombre: $(getIds.foco.nombre).val(),
			periodo: $(getIds.foco.periodo).val(),
			descripcion: $(getIds.foco.descripcion).val(),
			acciones: []
		};

		var actiosArray = [];
		console.log($(getIds.acciones).find('.accion').length);
		$(getIds.acciones).find('.accion').each((index, element) => {
			var item_action = {};
			console.log($(element));
			var item = $(element).find('.foco-accion-nombre');
			if ($(item).val()) {
				console.log($(item).val());
				item_action.nombre = $(item).val();
				// item_action.idAccionesMejoras = $(item).val();
				came.main.setValidControl($(item));
			} else {
				came.main.setInvalidControl($(item));
				valid = false;
			}

			var item2 = $(element).find('.foco-accion-movimientos');
			console.log(item2);
			if ($(item2).val()) {
				console.log($(item2).val());
				console.log($(item2).text());
				var mov = $(item2).val();
				if (mov.length > 0) {
					item_action.movimientos = [];
					for (var i = 0; i < mov.length; i++) {
						console.log(mov[i]);
						item_action.movimientos.push(
							{
								nombre: mov[i]
							});
					}
					came.main.setValidControl($(item));
				} else {
					came.main.setInvalidControl($(item2));
					valid = false;
				}
			}

			actiosArray.push(item_action);
		});

		console.log(actiosArray);
		setFoco.acciones = actiosArray;
		if (setFoco.nombre === '') {
			came.main.setInvalidControl($(getIds.foco.nombre));
			valid = false;
		} else {
			came.main.setValidControl($(getIds.foco.nombre));
		}

		if (setFoco.descripcion === '') {
			came.main.setInvalidControl($(getIds.foco.descripcion));
			valid = false;
		} else {
			came.main.setValidControl($(getIds.foco.descripcion));
		}

		console.log(setFoco);
		// valid = false;
		if (valid) {
			$.ajax({
				type: "POST",
				url: urls.api.setFocus,
				dataType: "json",
				contentType: 'application/json',
				data: JSON.stringify(setFoco)
			}).done(function (msg) {
				console.log("response done", msg);
				came.notify.showSucces("usuarios", "Su solicitud se ha procesado correctamente.");
				$(getIds.modal).modal('hide');
				location.reload();
			}).fail(function (jqXHR, textStatus) {
				console.error("response fail", jqXHR);
				// TODO: aqui se debe agregar un mensaje en caso de error.
				came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
				return false;
			});
		}
	}

	function agregarAccionesMejora(sender, activityId) {
		console.log(sender);
		console.log(activityId);
		var idActivity = activityId ?? '';
		$(getIds.acciones)
			.append('<div class="col-12 mt-3 dynamic">' +
				'<div class="form-row bg-agregar p-2 pb-3 accion">' +
				'<div class="col-6">' +
				'<div class="form group">' +
				'<label>Acción de mejora</label>' +
				'</div>' +
				'</div>' +
				'<div class="col-6">' +
				'<a href="#" class="text-danger float-right remove-current-action" data-original-title="Seguro que quieres <br> eliminar la actividad" data-activityId="' + idActivity + '" >' +
				'<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-dash-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">' +
				'<path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />' +
				'<path fill-rule="evenodd" d="M4 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 4 8z" />' +
				'</svg>' +
				'</a>' +
				'</div>' +
				'<div class="col-12">' +
				'<div class="form-group">' +
				'<input type="text" class="form-control foco-accion-nombre" aria-describedby="nombrePerfil">' +
				'</div>' +
				'</div>' +
				'<div class="col-12">' +
				'<div class="control-group">' +
				'<label>Movimientos claves</label>' +
				'<select multiple name="state[]" class="demo-default movimientos-clave foco-accion-movimientos" style="width:100%">' +
				'</select>' +
				'<div class="invalid-feedback">' +
				'<p>Debe especificar una movimientos claves valido.</p>' +
				'</div>' +
				'</div>' +
				'</div>' +
				'</div>' +
				'</div>');
		if ($(sender).attr('id') === 'agregar-acciones-mejora') {
			loadPluginSelect();
		}
	}

	function initList(table) {

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
					"emptyTable": "No hay datos disponibles",
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

	function loadPluginSelect() {

		$(getIds.movimientos_clave).each((index, element) => {
			//var selectize = element[0].selectize;
			console.log(element);
			console.log($(element).is("select"));
			console.log($(element).is(".selectized"));
			if ($(element).is("select") && !($(element).is(".selectized"))) {
				$(element).selectize({
					delimiter: ',',
					placeholder: "Escribe los movimientos claves...",
					create: function (input) {
						return {
							value: input,
							text: input
						}
					}
				});
			}
		});


		// $(getIds.movimientos_clave).selectize({
		// 			delimiter: ',',
		// 			placeholder: "Escribe los movimientos claves...",
		// 			create: function (input) {
		// 				return {
		// 					value: input,
		// 					text: input
		// 				}
		// 			}
		// 		});

	}

	function removeAction(sender) {
		console.log(sender);
		// var nombrePaso1 = $(sender).attr('data-itemNombre');
		// var idLista = $(sender).attr('data-idLista');
		// var idElementoLista = $(sender).attr('data-idElementoLista');

		if (popoverPaso) {
			$(popoverPaso).popover('hide');
		}
		var activityId = $(sender).attr("data-activityId");
		console.log(activityId);

		popoverPaso = $(sender).popover({
			placement: 'top',
			container: 'body',
			// trigger: 'focus',
			html: true,
			sanitize: false,
			content:
				'<div class="">' +
				'<a class="btn btn-sm btn-secondary col-4 float-left mb-2 closePopover" href="#">No</a>' +
				'<a class="btn btn-sm btn-danger col-4 float-right confirm-remove-action" href="#" data-activityId= ' + activityId + '>Si</a>' +
				'</div>'
		});

		popoverPaso.popover('show');
	}

	function removeFoco(sender) {
		console.log(sender);
		// var nombrePaso1 = $(sender).attr('data-itemNombre');
		// var idLista = $(sender).attr('data-idLista');
		// var idElementoLista = $(sender).attr('data-idElementoLista');

		if (popoverPaso) {
			$(popoverPaso).popover('hide');
		}
		var focoId = $(getIds.foco.nombre).attr("data-focoId");
		console.log(focoId);

		popoverPaso = $(sender).popover({
			placement: 'top',
			container: 'body',
			// trigger: 'focus',
			html: true,
			sanitize: false,
			content:
				'<div class="">' +
				'<a class="btn btn-sm btn-secondary col-4 float-left mb-2 closePopover" href="#">No</a>' +
				'<a class="btn btn-sm btn-danger col-4 float-right confirm-remove-foco" href="#" data-focoId= ' + focoId + '>Si</a>' +
				'</div>'
		});

		popoverPaso.popover('show');
	}

	function confirmRemoveFoco(sender) {
		var focoId = $(sender).attr('data-focoId');
		console.log(focoId);
		if (focoId) {
			$.ajax({
				type: "POST",
				url: urls.api.removeFoco,
				dataType: "json",
				contentType: 'application/x-www-form-urlencoded;',
				data: jQuery.param({ focoId: focoId })
			}).done(function (msg) {
				console.log("response done", msg);
				came.notify.showSucces("usuarios", "Su solicitud se ha procesado correctamente.");
				$(getIds.modal).modal('hide');
				location.reload();
			}).fail(function (jqXHR, textStatus) {
				console.error("response fail", jqXHR);
				// TODO: aqui se debe agregar un mensaje en caso de error.
				came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
				return false;
			});
		} else {
			/// 
		}
	}

	function confirmRemoveAction(sender) {
		var activityId = $(sender).attr('data-activityId');
		console.log(activityId);
		if (activityId) {
			$.ajax({
				type: "POST",
				url: urls.api.removeAccion,
				dataType: "json",
				contentType: 'application/x-www-form-urlencoded;',
				data: jQuery.param({ activityId: activityId })
			}).done(function (msg) {
				console.log("response done", msg);
				came.notify.showSucces("usuarios", "Su solicitud se ha procesado correctamente.");
				$(getIds.modal).modal('hide');
				location.reload();
			}).fail(function (jqXHR, textStatus) {
				console.error("response fail", jqXHR);
				// TODO: aqui se debe agregar un mensaje en caso de error.
				came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
				return false;
			});
		} else {
			/// 
		}
	}

	function setViewAcces() {
		var menuPso = came.storage.getSessionStorage('menu');
		var subMenuAdmin = menuPso.find(e => e.idModule == '2416198290916770825');
		console.log(subMenuAdmin);
		if (subMenuAdmin) {
			var focos = subMenuAdmin.subMenu.find(e => e.idSubModule == '2416254586126861350');
			console.log(focos);
			readOnly = focos.readOnly;
			if (readOnly) {
				$("#guardar-foco").prop("disabled", true);
				$("#button-remove-foco").hide();
				$("#content-focus :input").prop("disabled", true);
			}
		}
	}

	function init() {
		setViewAcces();
		initListener();
		initList($(getIds.table_focos));
	}

	return {
		init: init
	};
})();

$(document).ready(function () {
	came.focus.init();
});