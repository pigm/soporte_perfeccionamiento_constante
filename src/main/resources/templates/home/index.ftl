[#include '*/commons/page-structure.ftl' /]
[@main_page]
 <!-- Breadcrumb -->
 <ol class="breadcrumb bg-white py-0">
	<span class="glyphicon glyphicon-chevron-right"></span>&nbsp;&nbsp;Estás&nbsp;en:&nbsp;
	<li><a href="#" class="active">Inicio</a></li> &nbsp;
	
</ol>

 <!-- CONTENIDO -->
 <div class="container">
	<div class="row">
	 <div class="col-12 inicio">
		 <h1>Bienvenido</h1>
		 <em class="mt-4">La Unidad Apoyo a la Mejora Educativa, perteneciente a la División de Educación General, le da una cordial bienvenida a este espacio destinado al registro de las asesorías técnico-pedagógicas que desarrolla la supervisión ministerial a establecimientos educacionales del país, resguardando para ello sus principios básicos: <b>la reflexión</b>, la <b>práctica</b> regular, el <b>monitoreo</b> permanente y la <b>retroalimentación</b>.</em>
	 </div>
	 </div>
 </div>
<!-- VER MENSAJE 2-->
<div class="modal fade" id="modal-check-message" data-idpmessage1="" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <a href="#" data-dismiss="modal" data-toggle="modal" data-target="#verMensaje">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-chevron-left mt-1" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z" />
                        </svg>
                    </a>
                    <h5 class="modal-title col-10 text-center" id="title-cant-messagges"></h5>

                    <a href="#" data-dismiss="modal" data-toggle="modal" data-target="#verMensaje3">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-chevron-right mt-1" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z" />
                        </svg>
                    </a>
                </div>

                <div class="modal-body">
                    <div class="row">
                        <div class="col-12">
                            <h5 id="subject-message"></h5>
                        </div>

                        <!--MENSAJE-->
                        <div class="col-12">
                            <div class="form-control sinborde" id="body-message" rows="4"></div> 
                        </div>

                        <!--CONFIRMACIÓN LECTURA-->
                        <div class="col-12 mt-4">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="customCheck1">
                                <label class="custom-control-label" for="customCheck1">He leído el mensaje (no volver a mostrar)</label>
                            </div>

                        </div>

                    </div>
                </div>

                <div class="modal-footer">
                    <div class="col-12 text-center">
                        <button id="button-message-read"type="button" class="btn btn-primary px-4" data-dismiss="modal">Aceptar</button>
                    </div>
                </div>
            </div>
        </div>
</div>
<!-- VER OTRO TIPO MENSAJE 2-->
<div class="modal fade" id="modal-check-message2" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog">
<div class="modal-content">

    <div class="container text-center my-3">
    <h2 class="font-weight-light">Bootstrap 4 - Multi Item Carousel</h2>
    <div class="row mx-auto my-auto">
        <div id="recipeCarousel" class="carousel slide w-100" data-ride="carousel">
            <div class="carousel-inner w-100" role="listbox">
                <div class="carousel-item active">
                    <div class="col-md-4">
                        <div class="card card-body">
                            <img class="img-fluid" src="http://placehold.it/380?text=1">
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <div class="col-md-4">
                        <div class="card card-body">
                            <img class="img-fluid" src="http://placehold.it/380?text=2">
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <div class="col-md-4">
                        <div class="card card-body">
                            <img class="img-fluid" src="http://placehold.it/380?text=3">
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <div class="col-md-4">
                        <div class="card card-body">
                            <img class="img-fluid" src="http://placehold.it/380?text=4">
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <div class="col-md-4">
                        <div class="card card-body">
                            <img class="img-fluid" src="http://placehold.it/380?text=5">
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <div class="col-md-4">
                        <div class="card card-body">
                            <img class="img-fluid" src="http://placehold.it/380?text=6">
                        </div>
                    </div>
                </div>
            </div>
            <a class="carousel-control-prev w-auto" href="#recipeCarousel" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon bg-dark border border-dark rounded-circle" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next w-auto" href="#recipeCarousel" role="button" data-slide="next">
                <span class="carousel-control-next-icon bg-dark border border-dark rounded-circle" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
    <h5 class="mt-2">Advances one slide at a time</h5>
    </div>

</div>
</div>
</div>

[#include '*/survey/survey.ftl' /]

<script type="text/javascript"	src="${context}/locals/js/home/home.js"></script>
[/@main_page]