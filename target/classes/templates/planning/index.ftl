[#include '*/commons/page-structure.ftl' /]
[@main_page]

<!-- Breadcrumb -->
<div class="container mt-3">
    <div class="row">
        <div class="col-12 ">
            <ol class="breadcrumb bg-white p-0">
                Estás&nbsp;en:&nbsp;
                <li>Procesos > &nbsp; </li>
                <li><a href="#" class="active">Planificación y Registro </a></li>
            </ol>
        </div>
    </div>
</div>

<div class="container px-3">
    <div class="row">
        [#include '../shared/menu-proceso.ftl' /]
        <div class="col-md-9">
            <div  id="panel-search" class="vis-des">
                <div class="row mb-3">
                    <div class="col-12">
                        <h2>Planificación y Registro</h2>
                        <p class="mb-0">Selecciona filtros para realizar una búsqueda</p>
                    </div>
                </div>
                
                <div class="row px-0">
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
                        <div class="form-group">
                            <label for="nombrePerfil">Deprov</label>
                            <select id="selector-deprov" class="custom-select custom-select-sm">
                                <option value="" selected>Seleccionar</option>
                            </select>
                        </div>
                    </div>
                
                    <!--Comuna-->
                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="nombrePerfil">Comuna</label>
                            <select id="selector-comuna" class="custom-select custom-select-sm">
                                <option value="" selected>Seleccionar</option>
                            </select>
                        </div>
                    </div>
                
                    <!--Supervisor-->
                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="nombrePerfil">Supervisor</label>
                            <select id="selector-supervisor" class="custom-select custom-select-sm">
                                <option value="" selected>Seleccionar</option>
                            </select>
                        </div>
                    </div>
                
                    <!--Categorización-->
                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="nombrePerfil">Categorización</label>
                            <select id="selector-categorizacion" class="custom-select custom-select-sm">
                                <option value="" selected>Seleccionar</option>
                                <option>Sin Ordenacion</option>
                                <option>Medio</option>
                                <option>Medio Bajo</option>
                            </select>
                        </div>
                    </div>
                
                    <!--Dependencias-->
                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="nombrePerfil">Dependencias</label>
                            <select id="selector-dependencias" class="custom-select custom-select-sm">
                                <option value="" selected>Seleccionar</option>
                                <option value="1">Municipal - Corporacion</option>
                                <option value="2">Municipal - DAEM</option>
                                <option value="3">Particular Subvencionado</option>
                                <option value="4">Particular no Subvencionado</option>
                                <option value="5">Corporacion Privada 3166</option>
                                <option value="6">Servicio Local de Educacion (SLE)</option>
                                <option value="7">JUNJI</option>
                                <option value="8">INTEGRA</option>
                                <option value="9">VTF</option>
                                <option value="10">CAD</option>
                                <option value="11">Parvulo - Servicio Local de Educacion (SLE)</option>
                            </select>
                        </div>
                    </div>
                    
                    <!--Tipos-->
                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="nombrePerfil">Tipo</label>
                            <select id="selector-tipo" class="custom-select custom-select-sm">
                                <option value="" selected>Seleccionar</option>
                                <option value="red">Red</option>
                                <option value="directa">Directa</option>
                            </select>
                        </div>
                    </div>
                    
                    <!--Tipo de red-->
                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="nombrePerfil">Tipo de red</label>
                            <select id="selector-tipo-red" class="custom-select custom-select-sm">
                                <option value="" selected>Seleccionar</option>
                                [#list tipoRed as tr]
                                <option value="${tr.value}">${tr.displayText}</option>
                                [/#list]
                            </select>
                        </div>
                    </div>

                    <!--RBD-->
                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="nombrePerfil">RBD</label>
                            <input id="input-rbd" class="form-control form-control-sm">
                        </div>
                    </div>
                
                    <!--Nombre establecimiento-->
                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="nombrePerfil">Nombre establecimiento</label>
                            <input id="input-nombre-establecimiento" class="form-control form-control-sm">
                        </div>
                    </div>
                
                    <!--Nombre de la red-->
                    <div class="col-md-5">
                        <div class="form-group">
                            <label for="nombrePerfil">Nombre de la red</label>
                            <input id="input-nombre-red" class="form-control form-control-sm">
                        </div>
                    </div>
                
                    <!--Botón buscar-->
                    <div class="col-md-3">
                        <label for=""></label>
                        <a id="find-planning" href="#" class="btn btn-primary btn-sm btn-block mt-2">
                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor"
                                xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd"
                                    d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
                                <path fill-rule="evenodd"
                                    d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
                            </svg> &nbsp; Buscar</a>
                    </div>
                </div>
            </div>

            <div id="panel-search-result" class="row" style="display: none;">
                <!-- Contenido escritorio-->
                <div class="vis-des">
                    <div class="row">
                        <div class="col-12">
                            <h2>Planificación y registro de sesiones</h2>
                        </div>                
                    </div>
                
                    <div class="row mt-3">
                        <div class="col-6">
                            Resultado de la búsqueda (<strong id="count-planning"></strong> resultados)
                        </div>
                        <div class="col-6">
                            <button id="button-export-excel" class="btn btn-link float-right px-0"><svg width="1em" height="1em" viewBox="0 0 16 16"
                                    class="bi bi-file-earmark-excel" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path
                                        d="M4 0h5.5v1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V4.5h1V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2z" />
                                    <path d="M9.5 3V0L14 4.5h-3A1.5 1.5 0 0 1 9.5 3z" />
                                    <path fill-rule="evenodd"
                                        d="M5.18 6.616a.5.5 0 0 1 .704.064L8 9.219l2.116-2.54a.5.5 0 1 1 .768.641L8.651 10l2.233 2.68a.5.5 0 0 1-.768.64L8 10.781l-2.116 2.54a.5.5 0 0 1-.768-.641L7.349 10 5.116 7.32a.5.5 0 0 1 .064-.704z" />
                                </svg> Exporta a Excel</button>
                            <a id="button-find-again" href="#" class="btn btn-link float-right px-0 mr-4"><svg width="1em" height="1em"
                                    viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd"
                                        d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
                                    <path fill-rule="evenodd"
                                        d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
                                </svg> Buscar nuevamente</a>
                        </div>
                    </div>
                
                    <div class="row mt-1">
                        <div class="col-12 table-responsive">
                            <table id="table-planning" class="table mine table-hover table-bordered table-sm">
                                <thead>
                                    <tr>
                                        <th scope="col">Región</th>
                                        <th scope="col">Deprov</th>
                                        <th scope="col">Comuna</th>
                                        <th scope="col">Supervisores</th>
                                        <th scope="col">Tipo</th>
                                        <th scope="col">Nombre red</th>
                                        <!-- <th scope="col">Dependencias</th>
                                        <th scope="col">Categoración</th> -->
                                        <th scope="col">N° Sesiones</th>
                                        <th scope="col">Establecimientos</th>
                                        <th scope="col">Planificación</th>
                                    </tr>
                                </thead>
                                <tbody>                                   
                                </tbody>
                            </table>
                        </div>                
                    </div>
                
                </div>
            </div>
            
        </div>
    </div>

</div>



<script type="text/javascript" src="${context}/locals/js/planning/came.planning.js"></script>
[/@main_page]