[#include '*/commons/page-structure.ftl' /]
[@main_page]

<!-- Breadcrumb -->
<div class="container mt-3 px-0">
    <div class="row">
        <div class="col-12">
            <ol class="breadcrumb bg-white p-0 ml-4">
                Estás&nbsp;en:&nbsp;

                <li><a href="#" class="active">Establecimientos educacionales</a></li> &nbsp;
            </ol>
        </div>
    </div>
</div>


<!-- CONTENIDO ESCRITORIO-->
<div class="container px-3">
    <div class="row">

        <!-- Contenido escritorio-->
        <div class="col-md-12 vis-des" id="view-search">
            <div class="row">
                <div class="col-12">
                    <h2>Establecimientos educacionales</h2>
                </div>
            </div>

            <div class="row" id="search-establecimientos">
                <!--Región-->
                <div class="col-3">
                    <form>
                        <div class="form-group">
                            <label for="nombrePerfil">Región</label>
                            <select id="selector-region" class="custom-select custom-select-sm">
                                <option value="" selected>Seleccionar</option>
                                [#list regiones as r]
                                <option value="${r.value}">${r.displayText}</option>
                                [/#list]
                            </select>
                        </div>
                    </form>
                </div>
                <!--Ruralidad-->
                <div class="col-3">
                    <form>
                        <div class="form-group">
                            <label for="nombrePerfil">Ruralidad</label>
                            <select id="selector-ruralidad" class="custom-select custom-select-sm">
                                <option value="" selected>Seleccionar</option>
                                <option value="SI">Rural</option>
                                <option value="NO">Urbano</option>
                            </select>
                        </div>
                    </form>
                </div>
                <!--Clasifiación SEP-->
                <div class="col-3">
                    <form>
                        <div class="form-group">
                            <label for="nombrePerfil">Clasificación SEP</label>
                            <select id="selector-clasificacion-sep" class="custom-select custom-select-sm">
                                <option value="" selected>Seleccionar</option>
                                <option value="2446811709885646160">AUTONOMO</option>
                                <option value="2446811709927589201">EMERGENTE</option>
                                <option value="2446811709927589202">EN RECUPERACION</option>
                            </select>
                        </div>
                    </form>
                </div>
                <!--Depemdemcoas-->
                <div class="col-3">
                    <form>
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
                    </form>
                </div>
                <!--Deprov-->
                <div class="col-4">
                    <form>
                        <div class="form-group">
                            <label for="nombrePerfil">Deprov</label>
                            <select id="selector-deprov" class="custom-select custom-select-sm">
                                <option value="" selected>Seleccionar</option>
                            </select>
                        </div>
                    </form>
                </div>
                <!--Categorización-->
                <div class="col-4">
                    <form>
                        <div class="form-group">
                            <label for="nombrePerfil">Categorización</label>
                            <select id="selector-categorizacion" class="custom-select custom-select-sm">
                                <option value="" selected>Seleccionar</option>
                                <option>Sin Ordenacion</option>
                                <option>Medio</option>
                                <option>Medio Bajo</option>
                            </select>
                        </div>
                    </form>
                </div>
                <!--Estado-->
                <div class="col-4">
                    <div class="form-group">
                        <label for="nombrePerfil">Estado</label>
                        <select id="selector-estado" class="custom-select custom-select-sm">
                            <option value="" selected>Seleccionar</option>
                            <option value="1">ACTIVO</option>
                            <option value="2">SUSPENCION_TEMPORAL</option>
                            <option value="3">CERRADO</option>
                            <option value="4">EN TRAMITE DE RECONICIMIENTO OFICIAL</option>
                        </select>
                    </div>
                </div>

                <div class="col-8">
                    <div class="form-group">
                        <label for="nombrePerfil">Texto en el nombre</label>
                        <input id="text-filter" type="text" class="form-control" aria-describedby="nombrePerfil">
                    </div>
                </div>
                <div class="col-4">
                    <label for=""></label>
                    <button id="find-directory" class="btn btn-primary btn-block mt-3">Buscar</button>
                </div>

            </div>

            <div id="search-result" style="display: none;">
                <div class="row mt-3">
                    <div class="col-6">
                        Resultado de la búsqueda (<strong id="count-directory">0</strong> establecimientos)
                    </div>
                    <div class="col-6">
                        <button id="export-excel-establecimientos" class="btn btn-link float-right px-0"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-file-earmark-excel" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path d="M4 0h5.5v1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V4.5h1V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2z" />
                                <path d="M9.5 3V0L14 4.5h-3A1.5 1.5 0 0 1 9.5 3z" />
                                <path fill-rule="evenodd" d="M5.18 6.616a.5.5 0 0 1 .704.064L8 9.219l2.116-2.54a.5.5 0 1 1 .768.641L8.651 10l2.233 2.68a.5.5 0 0 1-.768.64L8 10.781l-2.116 2.54a.5.5 0 0 1-.768-.641L7.349 10 5.116 7.32a.5.5 0 0 1 .064-.704z" />
                            </svg> Exporta a Excel</button>
                        <a href="/apoyo-mejora-continua/establishment/index" class="btn btn-link float-right px-0 mr-4"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
                                <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
                            </svg> Buscar nuevamente</a>
                    </div>
                </div>

                <div class="row mt-1">
                    <div class="col-12 table-responsive">
                        <table id="table-establecimientos" class="table mine table-hover table-bordered table-sm">
                            <thead>
                                <tr>
                                    <th scope="col">RBD</th>
                                    <th scope="col">Nombre establecimiento</th>
                                    <th scope="col">Región</th>
                                    <th scope="col">Deprov</th>
                                    <th scope="col">Comuna</th>
                                    <th scope="col">Ruralidad</th>
                                    <th scope="col">Categorización</th>
                                    <th scope="col">Clasificación SEP</th>
                                    <th scope="col">Dependencia</th>
                                    <th scope="col">Estado</th>
                                </tr>
                            </thead>
                            <tbody class=" table-sm">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <div id="detail-establecimiento" style="display: none;">
            [#include './detail.ftl' /]
        </div>

    </div>
</div>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBIwzALxUPNbatRBj3Xi1Uhp0fFzwWNBkE&callback=initMap&libraries=&v=weekly" defer></script>
<script type="text/javascript" src="${context}/locals/js/establishment/came.establishment.js"></script>
[/@main_page]