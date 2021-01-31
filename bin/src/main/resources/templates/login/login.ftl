[#include '*/commons/page-structure.ftl' /] [@main_page_login]

<script src="https://www.google.com/recaptcha/api.js?render=${recaptchaSiteKey}"></script>
<div class="row">
	<div class="col-md-4 col-md-offset-4">
		<div class="userinfo">Conectado desde la dirección
			IP:&nbsp;${ip}&nbsp;</div>
	</div>
</div>
<br>
<div class="container row justify-content-center">
	<div class="col-md-4 col-12 rounded-top rounded-bottom bg-light">
		<h3 class="font-gob text-center mt-3">Inicio de Sesión</h3>
		<form role="form" id="form" method=post action="do_login" accept-charset="UTF-8">
			<div class="form-group mt-3">
				<label for="nombrePerfil">Nombre de Usuario</label>
				<input type="text" class="form-control" name="txtUsuario" id="txtUsuario" placeholder="Usuario"
					value="">
				<div class="invalid-feedback">
					<p>Debe especificar un nombre de usuario.</p>
				</div>
			</div>
			<div class="form-group">
				<label for="nombrePerfil">Contraseña</label>
				<input type="password" class="form-control" name="txtClave" id="form_passw" placeholder="Contraseña"
					value="">
				<div class="invalid-feedback">
					<p>Debe especificar una contraseña de usuario.</p>
				</div>
			</div>
			<input type="hidden" id="g-recaptcha-response" name="g-recaptcha-response" value="" />
			<br>
			[#if ((error)!-1) == 1]
			<div class="row justify-content-md-center">
				<div class="col-xs-10">
					<div id="warning" class="alert alert-danger alert-dismissible" role="alert">
						<span class="glyphicon glyphicon-exclamation-sign"></span>
						<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
								aria-hidden="true">&times;</span></button>&nbsp;
						<p>El nombre de usuario o la contraseña introducidos no son correctos.</p>
					</div>
				</div>
			</div>
			[/#if]

			<div class="row justify-content-md-center">
				<div class="col-xs-10">
					<div class="alert alert-danger alert-dismissible" role="alert" id="status-disabled"
						style="display:none">
						<span class="glyphicon glyphicon-exclamation-sign"></span>
						<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
								aria-hidden="true">&times;</span></button>&nbsp;
						<p>Usuario Inactivo en plataforma Apoyo a la Mejora Continua.</p>
					</div>
				</div>
			</div>
			<div class="text-center mb-3">
				<button id="ingresar" type="button" class="btn btn-primary">Ingresar</button>
			</div>
		</form>
	</div>
	<input type="hidden" id="txtSiteKey" value="${recaptchaSiteKey}" />
</div>




<br>
<br>
[/@main_page_login]