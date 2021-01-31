[#include '*/commons/page-structure.ftl' /]
[@main_page]

<!-- Breadcrumb -->
<div class="container mt-3 px-0">
    <div class="row">
        <div class="col-6 px-0">
            <ol class="breadcrumb bg-white p-0 ml-4">
                Estás&nbsp;en:&nbsp;

                <li><a href="#" class="active">Documentos</a></li> &nbsp;
            </ol>
        </div>
    </div>
</div>

<!-- CONTENIDO ESCRITORIO-->
<div class="container">
    <div class="row">
        [#include '../shared/menu-proceso.ftl' /]
        <div class="col-md-9">
            <div id="content-find-documents" class="row vis-des">
                <div class="col-12">
                    <h2>Ver documentos de asesorías</h2>
                </div>
                <!--Año-->
                <div class="col-2">
                    <form>
                        <div class="form-group">
                            <label for="nombrePerfil">Año</label>
                            <select id="selector-periodo-year" class="custom-select">
                            [#list years as year]
                            [#if year?string == currentYear?c]
                            <option value="${year?string}" selected>${year?string}</option>
                            [#else]
                            <option value="${year?string}">${year?string}</option>
                            [/#if]
                            [/#list]
                            </select>
                        </div>
                    </form>
                </div>
                <!--Región-->
                <div class="col-3">
                    <form>
                        <div class="form-group">
                            <label for="nombrePerfil">Región</label>
                            <select id="region" class="form-control">
                                <option value="" selected>Seleccionar</option>
                                [#list regiones as r]
                                <option value="${r.value}">${r.displayText}</option>
                                [/#list]
                            </select>
                        </div>
                    </form>
                </div>
                <!--Deprov-->
                <div class="col-3">
                    <form>
                        <div class="form-group">
                            <label for="nombrePerfil">Deprov</label>
                            <select id="provincia" class="form-control">
                                <option value="" selected>Seleccionar</option>
                            </select>
                        </div>
                    </form>
                </div>
                <!--Estado-->
                <div class="col-2">
                    <form>
                        <div class="form-group">
                            <label for="nombrePerfil">Estado</label>
                            <select id="selector-estado" class="custom-select">
                                <option value="" selected>Seleccionar</option>
                                <option value="true">Observado</option>
                                <option value="false">No Observado</option>
                            </select>
                        </div>
                    </form>
                </div>
                <div class="col-2 mt-3">
                    <label for=""></label>
                    <a id="find-documents" href="#" class="btn btn-primary btn-sm btn-block mt-2">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
                            <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
                        </svg> &nbsp; Buscar</a>
                </div>
            </div>
            
            <!-- Contenido escritorio-->
            <div id="content-list-documents" class="vis-des" style="display: none;">
                <div class="row">
                    <div class="col-12">
                        <h2>Ver documentos de asesorías año <strong id="current-period-year"></strong></h2>
                    </div>
            
                </div>
            
                <div class="row mt-3">
                    <div class="col-6">
                        Resultado de la búsqueda (<strong id="count-documents"></strong> resultados)
                    </div>
                    <div class="col-6">
                        <button id="export-excel-documentos" class="btn btn-link float-right px-0"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-file-earmark-excel" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path d="M4 0h5.5v1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V4.5h1V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2z" />
                                <path d="M9.5 3V0L14 4.5h-3A1.5 1.5 0 0 1 9.5 3z" />
                                <path fill-rule="evenodd" d="M5.18 6.616a.5.5 0 0 1 .704.064L8 9.219l2.116-2.54a.5.5 0 1 1 .768.641L8.651 10l2.233 2.68a.5.5 0 0 1-.768.64L8 10.781l-2.116 2.54a.5.5 0 0 1-.768-.641L7.349 10 5.116 7.32a.5.5 0 0 1 .064-.704z" />
                            </svg> Exporta a Excel</button>
            
                        <a id="find-documents-again" href="#" class="btn btn-link float-right px-0 mr-4"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
                                <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
                            </svg> Buscar nuevamente</a>
                    </div>
                </div>
            
                <div class="row mt-1">
                    <div class="table-responsive col-12">
                        <table id="table-documents" class="table mine table-hover table-bordered table-sm">
                            <thead>
                                <tr>
                                    <th scope="col">Región</th>
                                    <th scope="col">Tipo</th>
                                    <th scope="col">Deprov</th>
                                    <th scope="col">Estado</th>                                    
                                    <th scope="col">Documentos</th>
                                </tr>
                            </thead>
                            <tbody class=" table-sm">
            
                            </tbody>
                        </table>
                    </div>
            
                </div>
            </div>
        </div>
    </div>   
</div>
<script type="text/javascript" src="${context}/locals/js/documents/came.documents.adm.js"></script>
[/@main_page]