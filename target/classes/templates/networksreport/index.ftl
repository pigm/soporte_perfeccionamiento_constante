[#include '*/commons/page-structure.ftl' /]
[@main_page]

<div class="container mt-3 px-0">
    <div class="row">
        <div class="col-12">
            <ol class="breadcrumb bg-white p-0 ml-4">
                Estás&nbsp;en:&nbsp;
                <li> Reportes /</li> &nbsp;
                <li><a href="#" class="active">Reportes redes</a></li> &nbsp;
            </ol>
        </div>
    </div>
</div>

<div class="container px-3" id="search-redes">
    <div class="row">
        [#include '../shared/menu-reportes.ftl' /]
        
        <!-- Contenido escritorio-->
                <div class="col-md-9 vis-des">
                    <div class="row">
                        <div class="col-12">
                            <h2>Reportes redes</h2>
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
                            <a id="button-buscar-redes" class="btn btn-primary btn-sm float-right px-4">
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
<script type="text/javascript" src="${context}/locals/js/networksreport/came.networksreport.js"></script>
[/@main_page]