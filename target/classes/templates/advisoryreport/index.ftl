[#include '*/commons/page-structure.ftl' /]
[@main_page]

<div class="container mt-3 px-0">
    <div class="row">
        <div class="col-12">
            <ol class="breadcrumb bg-white p-0 ml-4">
                Estás&nbsp;en:&nbsp;
                <li> Reportes /</li> &nbsp;
                <li><a href="#" class="active">Reportes asesorías directas realizadas</a></li> &nbsp;
            </ol>
        </div>
    </div>
</div>

<div class="container px-3" id="search-asesoria">
    <div class="row">
        [#include '../shared/menu-reportes.ftl' /]
        
        <!-- Contenido escritorio-->
                <div class="col-md-9 vis-des">
                    <div class="row">
                        <div class="col-12">
                            <h2>Reportes asesorías directas realizadas</h2>
                        </div>
                    </div>
                    <div class="row mt-4">
                       
                        <div class="col-md-3">
                            <div class="form-group">
                                <label for="fecha-inicio">Fecha desde</label> <br>
                                <input type="date" class="form-control form-control-sm" id="selector-fecha-inicio">
                            </div>
                        </div>

                        <div class="col-md-3">
                            <div class="form-group">
                                <label for="fecha-inicio">Fecha hasta</label> <br>
                                <input type="date" class="form-control form-control-sm" id="selector-fecha-fin">
                            </div>
                        </div>

                        <div class="col-md-3">
                            <div class="form-group">
                               
                            </div>
                        </div>

                        <div class="col-md-3">
                            <label for="">&nbsp;</label> <br>
                            <a id="button-asesoria" class="btn btn-primary btn-sm float-right px-4">
                                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
                                    <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
                                </svg> &nbsp; Buscar
                            </a>
                        </div>
                    </div>
                </div>
    </div>
</div>


<div class="container"  id="search-result" style="display: none;">
            <div class="row ">

                <div class="col-12">
                    <a id="button-volver"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chevron-left" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z" />
                        </svg> Volver</a>
                </div>

                <div class="col-md-12 mt-3">
                    <h3 class="float-left">Reporte asesoria directa</h3>
                </div>

                <div class="col-6 mt-2">
                    Resultado de la búsqueda (<strong id="count-directory">0</strong> resultados)
                </div>

                <div class="col-6">
                    <a id="button-exportar-excel" class="btn btn-link float-right px-0">
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

                <div class="col-12 mt-1 table-responsive">
                    <div class=" table-responsive ">
                        <table id="table-asesoria" class="table mine table-hover table-bordered">
                            <thead class="">
                                <tr>
                                    <th scope="col">Región</th>
                                    <th scope="col">Deprov</th>
                                    <th scope="col">RBD</th>
                                    <th scope="col">Establecimiento</th>
                                    <th scope="col">Nombre&nbsp;supervisor</th>
                                    <th scope="col">N°&nbsp;sesión</th>
                                    <th scope="col">Tipo&nbsp;de&nbsp;planificación</th>
                                    <th scope="col">Fecha&nbsp;planificada</th>
                                    <th scope="col">Fecha&nbsp;realizada</th>
                                    <th scope="col">Estado</th>
                                    <th scope="col">Foco</th>
                                    <th scope="col">Estado&nbsp;foco</th>
                                    <th scope="col">Acciones&nbsp;mejoras&nbsp;desarrollo</th>
                                    <th scope="col">Movimiento&nbsp;clave&nbsp;comprometido</th>
                                    <th scope="col">Movimiento&nbsp;clave&nbsp;desarrollado</th>
                                    <th scope="col">Plazo</th>
                                    <th scope="col">Responsable</th>

                                </tr>
                            </thead>
                            <tbody class="table-sm">                            
                                
                            </tbody>
                        </table>
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
<script type="text/javascript" src="${context}/locals/js/advisoryreport/came.advisoryreport.js"></script>
[/@main_page]