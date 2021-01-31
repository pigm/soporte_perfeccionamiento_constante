[#include '*/commons/debug.ftl' /]
[#macro html_top]
<!DOCTYPE html>
<html lang="es">
	<head>
    	<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">		
		<title>[@spring.message "app.nombre"/] - [@spring.message "app.titulo"/]</title>
		<meta content="Mineduc - Departamento de Tecnologia" name="Author" />
		<link href="https://staticv2.mineduc.cl/img/favicon.ico" type="image/x-icon" rel="shortcut icon" />
		<!-- CAME -->
		<link href="${context}/locals/css/estilos.css" rel="stylesheet" media="screen">
		<link href="${context}/locals/css/perzonalized.css" rel="stylesheet" media="screen">
		<link href="${context}/locals/css/mineduc-custom.css" rel="stylesheet" media="screen">

		<link href="${context}/locals/css/selectize.css" rel="stylesheet" media="screen">

		<link href="${context}/locals/js/datatables/datatables.css" rel="stylesheet" media="screen">
		<!-- Bootstrap -->		
		<link href="${context}/locals/css/font-fix.css" rel="stylesheet" media="screen">
		<!-- Aquí otras hojas de estilo -->
		<link href="https://staticv2.mineduc.cl/css/jquery-ui.theme.min.css" rel="stylesheet" media="screen">	    
	    <link href="https://staticv2.mineduc.cl/css/buttons.bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="${context}/locals/css/bstreeview.css" rel="stylesheet">
		<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
			integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
		[@scripts /]
		
		[@google_analytics /]
	</head>
	<body>
[/#macro]

[#macro scripts]
		<!-- faster loads -->
		<script type="text/javascript" src="${context}/locals/js/jquery-3.5.1.slim.min.js"></script> 		
		<script src="https://staticv2.mineduc.cl/js/jquery-2.1.4.min.js"></script>
		
		<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<script type="text/javascript" src="${context}/locals/js/popper.min.js"></script>
		<script type="text/javascript" src="${context}/locals/js/bootstrap.js"></script>
		
		<script type="text/javascript" src="${context}/locals/js/standalone/selectize.js"></script>
		<script type="text/javascript" src="${context}/locals/js/selectize.js"></script>

		<!-- scripts de usuario por template -->
		<script type="text/javascript" src="${context}/locals/js/datatables/datatables.js"></script>
		<script type="text/javascript" src="${context}/locals/js/bstreeview.js"> </script>

		<script type="text/javascript">
			var came=typeof came == "object" ? came:{cache:{}};
		    came.contexto="${context}";
		    came.uploadMainFolder="${uploadMainFolder}";
		</script>

		<!-- este es un script que solo se cargara en el template login -->
		[#if springMacroRequestContext.requestUri?contains("/login")]
		<script type="text/javascript" src="${context}/locals/js/login/login.js"></script>
		[/#if]
		
		<script type="text/javascript" src="${context}/locals/js/app.js"></script>
		<script type="text/javascript"	src="${context}/locals/js/shared/came.main.js"></script>
		<script type="text/javascript"	src="${context}/locals/js/shared/came.storage.js"></script>
		<script type="text/javascript"	src="${context}/locals/js/shared/came.env.js"></script>
		<script type="text/javascript"	src="${context}/locals/js/shared/came.menu.main.js"></script>		
[/#macro]
[#include './message.ftl' /]
[#macro html_bottom]

		</div>
	</body>
</html>
[/#macro]

[#macro footer]		
		[#include '../shared/loading.ftl' /]
		<br>
		<footer>
			<!-- <hr class="line-separator red"/> -->
			<address>
			<p>
				<strong>Ministerio de Educación de Chile</strong><br>
				<a href="https://www.gob.cl" title="Ir al portal del Gobierno de Chile">Gobierno de Chile</a><br>
				Dirección: Av. Libertador Bernardo O'Higgins 1371. Teléfono +56 2 24066000<br>
				<span class="text-blue-light">Versión ${version}</span><br>
			</p>
			</address>			
		</footer>	
[/#macro]

[#macro header]
	<div class="container page-mineduc rounded-bottom cf">
		<div class="fondo-head">
            <img src="https://staticv2.mineduc.cl/img/logo-med.png" class="logo-mineduc" alt="logo mineduc">
            <h1 class="title-application"><a href="index.html">Mineduc <span class="text-uppercase">[@spring.message "app.nombre"/]</span></a></h1>
        </div>        
		
[/#macro]

[#macro user_info]
		<div class="row user-info">
			<div class="col-sm-12 text-align-right">
				<!-- Bienvenido Sr(a). <strong>${(currentUser.nombre)!}&nbsp;${(currentUser.paterno)!}&nbsp;${(currentUser.materno)!}</strong>&nbsp;-&nbsp;<a class="red-text" href="${context}/logout">Cerrar Sesión</a> -->
				Bienvenido Sr(a). <strong id="current-user"></strong>&nbsp;-&nbsp;<a class="red-text" href="${context}/logout">Cerrar Sesión</a>
			</div>
		</div>		
[/#macro]

[#macro menu]
		 <!-- Menú principal -->
        <nav class="navbar navbar-expand-lg navbar-light mb-3">
            <!-- <a class="navbar-brand" href="#">Navbar</a> -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto" id="contentMenuPrincipal">
                    <li id="start" class="nav-item">
                        <a class="nav-link" href="${context}/home/index">Inicio</a>
                    </li>
                   
                 
                    
                   
                   
                   
                </ul>

            </div>
        </nav>
[/#macro]

[#macro menu_item_uns]
	<li class="dropdown">
		<a href="#" class="dropdown-toggle" data-toggle="dropdown">Apoyo UNS&nbsp;<b class="caret"></b></a>
		<ul class="dropdown-menu">
			[@item_uns_excedentes /]
		</ul>
	</li>
[/#macro]

[#macro item_uns_excedentes]
			<li><a href="${context}/mvc/alumnos-excedentes/carga">Carga Masiva Alumnos Excedentes</a></li>
[/#macro]

[#macro menu_item_establecimientos]
	<li class="dropdown">
		<a href="#" class="dropdown-toggle" data-toggle="dropdown">Ficha&nbsp;<b class="caret"></b></a>
		<ul class="dropdown-menu">
			<li><a href="">Establecimiento</a></li>
		</ul>
	</li>
[/#macro]

[#macro menu_item_reportes]
	<li class="dropdown">
		<a href="#" class="dropdown-toggle" data-toggle="dropdown">Reportes&nbsp;<b class="caret"></b></a>
		<ul class="dropdown-menu">
			<li><a href="">Asis.- Estado Avance Mes (Cursos)</a></li>
			<li><a href="">Asis.- Avance Mes (Alumnos)</a></li>
			<li><a href="">Asis.- % Declarado Mes</a></li>
			<li><a href="">Asist.Buscador % Declarado</a></li>
			<li><a href="">Reporte Actas Nacional</a></li>
			<li><a href="">Des Declaraciones</a></li>
		</ul>
	</li>
[/#macro]

[#macro no_javascript]
		<noscript>
			<div class="alert alert-warning alert-dismissible" role="alert">
				<span class="glyphicon glyphicon-alert"></span>
				<p>Para utilizar las funcionalidades completas de este sitio es necesario tener JavaScript habilitado, por favor habilite la funcionalidad para continuar.</p>
			</div>	 
		</noscript>
[/#macro]

[#macro main_page]
	[@html_top /]
	[#escape x as x?html]
		[@header /]
		[@user_info /]
		
		[@menu /]
		[@no_javascript /]
		<div class="container page-mineduc rounded-bottom cf">	
			[#nested /]
			[@footer /]
			[@_debugContent /]
		</div>
	[/#escape]
	[@html_bottom /]
[/#macro]

[#macro main_errors]
	[@html_top /]
	[#escape x as x?html]
		[@header /]
		[@no_javascript /]
		<div class="container-fluid content with-outline rounded-top rounded-bottom">	
			[#nested /]
			[@footer /]
			[@_debugContent /]
		</div>
	[/#escape]
	[@html_bottom /]
[/#macro]

[#macro main_page_login]
	[@html_top /]
	[#escape x as x?html]
		[@header/]
		[@no_javascript /]
		[#nested /]
			
		<div class="container-fluid content with-outline rounded-top rounded-bottom">	
				[@footer /]
		</div>
	[/#escape]
	[@html_bottom /]
[/#macro]

[#macro google_analytics]
	<!-- TODO: pegar aquí el codigo analytics de la aplicacion -->
[/#macro]

[#import "/spring.ftl" as spring /]
