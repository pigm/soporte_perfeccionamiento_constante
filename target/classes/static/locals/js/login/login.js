'use strict';

var login = {};

login.url = {
	api: {
		getStatus: came.contexto + "/login/getStatus",
	}
}

$(document).ready(function () {
	console.log('Clear storage..');
	came.storage.clear();

	grecaptcha.ready(function () {
		grecaptcha.execute($("#txtSiteKey").val(), { action: 'login' })
			.then(function (token) {
				$("#g-recaptcha-response").val(token);
			});
	});

	$('#ingresar').click(function () {
		
		var i = 0;
		if ($("#form_name").val() == '') {
			// alert("Debe ingresar el nombre de usuario.");
			i++;
		} else {
			if ($("#form_passw").val() == '') {
				// alert("Debe ingresar la contraseña.");
				i++;
			}
		}

		var valid = true;
		console.log($('#txtUsuario').val());
		if($('#txtUsuario').val() === ''){
			$('#txtUsuario').addClass("is-invalid");
			valid = false;
		} else {
			$('#txtUsuario').removeClass("is-invalid");
		}

		console.log($('#form_passw').val());
		if($('#form_passw').val() === ''){
			$('#form_passw').addClass("is-invalid");
			valid = false;
		} else {
			$('#form_passw').removeClass("is-invalid");
		}

		if(valid){
			$.ajax({
				type: "POST",
				dataType: "text",
				url: (came.contexto + "/login/validarCaptcha"),
				data: { captcha: $("#g-recaptcha-response").val() }
			}).done(function (data, statusText, xhr) {
				if (xhr.status == 200) {
					autentication($("#txtUsuario").val())
				}
			});
		}		

	});

	$("#form_passw").keypress(function (e) {
		if (e.which == 13) {
			jQuery(this).blur();
			jQuery('#ingresar').focus().click();
		}
	});

});

var autentication = function (username) {
	$.ajax({
		type: "GET",
		url: login.url.api.getStatus,
		dataType: "json",
		async: false,
		//contentType: 'application/json',
		data: { username: username }
	}).done(function (data, statusText, xhr) {
		if (data) {
			$("#form").submit();
		} else {
			$("#status-disabled").show();
		}

	});
}