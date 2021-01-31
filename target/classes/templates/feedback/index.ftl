[#include '*/commons/page-structure.ftl' /]
[@main_page]

<!-- Breadcrumb -->
<div class="container mt-3">
    <div class="row">
        <div class="col-6 ">
            <ol class="breadcrumb bg-white p-0">
                Estás&nbsp;en:&nbsp;

                <li><a href="#" class="active"> Incio </a> </li> &nbsp;
                <li> > Retroalimentación</li>
            </ol>
        </div>

        <div class="col-6">
            <button id="button-primera-planificacion" class="btn btn-sm btn-success float-right">
                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-plus-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                    <path fill-rule="evenodd" d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
                </svg> &nbsp; Planificar retroalimentación
            </button>
        </div>
    </div>
</div>

<div id="panel-search" class="vis-des">
    <!-- CONTENIDO ESCRITORIO-->
    <div class="container px-3">
        <div class="row">

            <!-- Contenido -->
            <div class="col-md-12">
                <h2>Retroalimentación</h2>
            </div>

            <!--Jefe Técnico-->
            <div class="col-md-3">
                <div class="form-group">
                    <label for="nombrePerfil">Jefe Técnico</label>
                    <select id="selector-jefe-tecnico" class="custom-select custom-select-sm">
                        <option value="" selected>Seleccionar</option>
                        [#list jefesTecnico as jt]
                        <option value="${jt}">${jt}</option>
                        [/#list]
                    </select>
                </div>
            </div>
            <!--Región-->
            <div class="col-md-3">
                <div class="form-group">
                    <label for="nombrePerfil">Región</label>
                    <select id="selector-region" class="custom-select custom-select-sm">
                        <option value="" selected>Seleccionar</option>
                        [#list regiones as r]
                        <option value="${r.value}">${r.displayText}</option>
                        [/#list]
                    </select>
                </div>
            </div>
            <!--Deprov-->
            <div class="col-md-3">
                <form>
                    <div class="form-group">
                        <label for="nombrePerfil">Deprov</label>
                        <select id="selector-deprov" class="custom-select custom-select-sm">
                            <option value="" selected>Seleccionar</option>
                        </select>
                    </div>
                </form>
            </div>
            <!--Semestre-->
            <div class="col-md-3">
                <div class="form-group">
                    <label for="nombrePerfil">Semestre</label>
                    <select id="selector-semestre" class="custom-select custom-select-sm">
                        <option value="" selected>Seleccionar</option>
                        <option value="1">1er semestre</option>
                        <option value="2">2er semestre</option>
                    </select>
                </div>
            </div>

            <div class="col-md-9"></div>

            <div class="col-md-3">
                <a id="button-search" href="#" class="btn btn-primary btn-sm float-right px-4 mt-2">
                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
                        <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
                    </svg> &nbsp; Buscar</a>
            </div>
        </div>
    </div>
</div>

<div id="panel-search-result" style="display: none;" class="vis-des">
    <div class="row">
        <div class="col-12">
            <h2>Retroalimentación <span class="fon-18 text-rea">- Proceso ${currentYear?c}</span></h2>
        </div>
    
    </div>
    
    <div class="row mt-3">
        <div class="col-6">
            Resultado de la búsqueda (<strong id="count-feedback"></strong> resultados)
        </div>
        <div class="col-6">
            <a href="#" class="btn btn-link float-right px-0"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
                    <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
                </svg> Buscar nuevamente </a>
        </div>
    </div>
    
    <div class="row mt-1">
        <div class="table-responsive col-12">
            <table id="table-feedback" class="table mine table-hover table-bordered table-sm">
                <thead>
                    <tr>
                        <th></th>
                        <th scope="col">Supervisor</th>
                        <th scope="col">Jefe Técnico</th>
                        <th scope="col">Región</th>
                        <th scope="col">Deprov</th>
                        <th scope="col">Semestre</th>
                        <th scope="col">Número</th>
                        <th scope="col">Fecha planifacada</th>
                        <th scope="col">Fecha realizada</th>
                    </tr>
                </thead>
                <tbody class=" table-sm">
                </tbody>
            </table>
        </div>
    
    </div>
</div>


<!-- Modal Primera planificación-->
<div class="modal" id="modal-primera-planificacion" role="dialog" aria-labelledby="primeraPlanificacion" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Planificar retroalimentación</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <!-- NOMBRE PERFIL -->
                    <div class="col-8">
                        <div class="form-group">
                            <label for="nombrePerfil">Supervisor</label>
                            <input id="input-supervisor-username" type="text" class="form-control" aria-describedby="nombrePerfil">
                            <div class="invalid-feedback">
                                <p>Debes especificar un supervisor.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-4"></div>
                    <div class="col-2">Usuario</div>
                    <div class="col-10">: <strong id="planificar-supervisor-username"></strong></div>

                    <div class="col-2">Nombre</div>
                    <div class="col-10">: <strong id="planificar-supervisor-name"></strong></div>

                    <div class="col-2">Región</div>
                    <div class="col-10">: <strong id="planificar-supervisor-region"></strong></div>

                    <div class="col-2">Deprov</div>
                    <div class="col-10">: <strong id="planificar-supervisor-deprov"></strong></div>

                    <div class="col-8 mt-3">
                        <div class="form-group">
                            <label for="nombrePerfil">Frecuencia</label>
                            <select id="planificar-frecuencia" class="custom-select custom-select-sm">
                                <option value="" selected>Seleccionar</option>
                                <option value="2484537666033419784">Semanal</option>
                                <option value="2484537666050197001">Quincenal</option>
                                <option value="2484537666050197002">Mensual</option>
                            </select>
                            <div class="invalid-feedback">
                                <p>Debes especificar una frecuencia.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-4"></div>
                    [#assign today = .now]
                    <div class="col-8">
                        <label for="nombrePerfil">Fecha planificación</label>
                        
                        <input id="planificar-fecha" type="date" class="form-control" min="${today?date}">
                        <div class="invalid-feedback">
                            <p>Debes especificar una fecha.</p>
                        </div>
                    </div>
                    <div class="col-4">
                    </div>

                    <div class="col-8 mt-4">
                        Semestre: <br> 
                        [#if currentSemester == 1]
                        <strong id="planificar-semestre" data-semestre="1">1er Semestre</strong>
                        [#else]
                        <strong id="planificar-semestre" data-semestre="2">2er Semestre</strong>
                        [/#if]
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                <button id="button-planificar-save" type="button" class="btn btn-primary" data-toggle="popover" title='<div class="text-center">¿Estás seguro?<br>Después no se puede modificar la fecha</div>'>Planificar</button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${context}/locals/js/feedback/came.feedback.js"></script>
<style>
    .ui-autocomplete {
        z-index: 9999;
    }
</style>
[/@main_page]