[#include '*/commons/page-structure.ftl' /]
[@main_page]

<div class="container mt-3 px-0">
    <div class="row">
        <div class="col-12">
            <ol class="breadcrumb bg-white p-0 ml-4">
                Estás&nbsp;en:&nbsp;
                <li> Reportes /</li> &nbsp;
                <li><a href="#" class="active">Reporte Directorio</a></li> &nbsp;
            </ol>
        </div>
    </div>
</div>

<div class="container px-3" id="search-directorio">
    <div class="row">
        [#include '../shared/menu-reportes.ftl' /]

        <!-- Contenido -->
        <div class="col-md-9">
        	<div class="row">
                <div class="col-12">
                    <h2>Reportes directorios</h2>
                </div>
            </div>
            <!--FILTRO ASESORIA DIRECTA-->
            <div class="row">      
                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="Filtros" class="mb-1">Región:</label>
                            <select name="" id="selector-region" class="custom-select">
                           		<option value="" selected>Seleccionar</option> 
                                [#list regiones as rg]
                        			<option value="${rg.value}">${rg.displayText}</option> 
                    			[/#list]
                            </select>                    
	                      
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="Filtros" class="mb-1">Deprov:</label>
                            <select name="" id="selector-deprov" class="custom-select">
                            	<option value="" selected>Seleccionar</option>
                            </select>	                            
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="Filtros" class="mb-1">Comuna:</label>
                            <select name="" id="selector-comuna" class="custom-select">
                                <option value="" selected>Seleccionar</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="Filtros" class="mb-1">&nbsp;</label>
                            <button id="button-buscar-directorio" class="btn btn-primary btn-block">Buscar</button>
                        </div>
                    </div>
            
            </div>
        </div>
    </div>
</div>
 <!-- CONTENIDO ESCRITORIO-->
        <div class="container px-0" id="search-result" style="display: none;">
            <div class="row p-3">

                <!-- Contenido escritorio-->
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-12">
                            <a id="button-volver"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chevron-left" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z" />
                                </svg> Volver</a>
                        </div>

                        <div class="col-md-6 mt-3">
                            <h3 class="float-left">Reporte directorio</h3>
                        </div>

                        <div class="col-6">

                        </div>
                    </div>


                    <!--Datos-->

                    <div class="row mt-3 ">
                        <div class="col-md-12 d-flex">
                            <div class="rounded p-2 bg-light" style="width: 100%">
                                <div class="row">
                                    <div class="col-md-4">
                                        <p class="mb-0">
                                            Región: <b><strong id="lbl-region"></strong></b>
                                        </p>
                                    </div>
                                    <div class="col-md-4">
                                        <p class="mb-0">
                                            Deprov: <b><strong id="lbl-deprov"></strong></b>
                                        </p>
                                    </div>
                                    <div class="col-md-4">
                                        <p class="mb-0">
                                            Comuna: <b><strong id="lbl-comuna"></strong></b>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row mt-4">
                        <div class="col-6">
                            Resultado de la búsqueda (<strong id="count-directory">0</strong> resultados)
                        </div>
                        <div class="col-6">
                    <a id="button-exportar-excel"  class="btn btn-link float-right px-0">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-file-earmark-excel" viewBox="0 0 16 16">
                            <path d="M5.884 6.68a.5.5 0 1 0-.768.64L7.349 10l-2.233 2.68a.5.5 0 0 0 .768.64L8 10.781l2.116 2.54a.5.5 0 0 0 .768-.641L8.651 10l2.233-2.68a.5.5 0 0 0-.768-.64L8 9.219l-2.116-2.54z" />
                            <path d="M14 14V4.5L9.5 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2zM9.5 3A1.5 1.5 0 0 0 11 4.5h2V14a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h5.5v2z" />
                        </svg> Exportar a Excel
                    </a>

                    <a id="button-buscar-nuevamente" class="btn btn-link float-right px-0 mr-4">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
                            <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
                        </svg> Buscar nuevamente

                    </a>
                        </div>
                        <div class="table-responsive col-12">
                            <table id="table-directorio" class="table mine table-hover table-bordered table-sm">
                                <thead>
                                    <tr>
                                        <th scope="col">Nombre del establecimiento</th>
                                        <th scope="col">RBD</th>
                                        <th scope="col">Ruralidad</th>
                                        <th scope="col">Categorización</th>
                                        <th scope="col">Clasificación SEP</th>
                                        <th scope="col">Dependencia</th>
                                        <th scope="col">Estado</th>
                                        <th scope="col">Matriculas total</th>
                                        <th scope="col">Tipo de apoyo</th>
                                        <th scope="col">Supervisor</th>
                                    </tr>
                                </thead>
                                <tbody class="table-sm">
                                </tbody>
                            </table>
                        </div>                    
                    </div>
                </div>
            </div>
        </div>
<!-- Carga jQuery -->
<script src="js/jquery-3.5.1.slim.min.js"></script>

<!-- Carga Popper -->
<script src="js/popper.min.js"></script>

<!-- Carga de Bootstrap JS -->
<script src="libs/bootstrap-4.5.2/dist/js/bootstrap.min.js"></script>

<!--Popovers-->
<script src="js/apps.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
<script type="text/javascript" src="${context}/locals/js/directoryreport/came.directoryreport.js"></script>
[/@main_page]