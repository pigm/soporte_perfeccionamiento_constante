<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Notificaci&oacute;n de Entrega de Certificado del Ministerio de Educación</title>
<!-- Bootstrap -->
<link href="https://staticv2.mineduc.cl/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="https://staticv2.mineduc.cl/css/mineduc-custom.css"
	rel="stylesheet" media="screen">
</head>
<body>
	<div class="container with-outline page-mineduc rounded-bottom cf">
		<img class="logo-mineduc"
			src="https://staticv2.mineduc.cl/img/logo-med.png">
		<div
			class="container-fluid content with-outline rounded-top rounded-bottom">
			<br>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3></h3>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-10">
							<p>
								Estimada(o) <strong>${nombre}</strong>
							</p>
							<p>
								Por medio de este correo, confirmamos que se ha procedido a realizar suplencia en la Deprov:  ${deprov} <br>
								El Nombre de usuario ${suplente} por suplencia ${ausente}
							</p>
							
							<p>Este es un correo generado autom&aacute;ticamente por el
								Portal de Certificados de AyudaMineduc, favor no responder.
								Muchas Gracias</p>
						</div>
					</div>
				</div>
			</div>
			<br>
			<footer>
				<hr class="line-separator red" />
				<address>
					<p>
						<strong>Ministerio de Educaci&oacute;n de Chile</strong><br>
						<a href="https://www.gob.cl"
							title="Ir al portal del Gobierno de Chile">Gobierno de Chile</a><br>
						Direcci&oacute;n: Av. Libertador Bernardo O'Higgins 1371.
						Tel&eacute;fono +56 2 24066000<br>
					</p>
				</address>
			</footer>
		</div>
		<br>
	</div>
</body>
</html>

