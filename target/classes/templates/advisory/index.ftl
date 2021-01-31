[#include '*/commons/page-structure.ftl' /]
[@main_page]
<!--DATE RANGE PICKER-->
<link rel="stylesheet" href="${context}/locals/css/daterangepicker.css">

<!-- Breadcrumb -->
<div class="container mt-3">
    <div class="row">
        <div class="col-6 ">
            <ol class="breadcrumb bg-white p-0">
                Estás&nbsp;en:&nbsp;

                <li><a href="#" class="active">Asesoría directa </a></li>
            </ol>
        </div>

        <div class="col-6">
            <button id="button-new-advisory" class="btn btn-sm btn-primary float-right" title='<div class="text-center">Estas fuera del rango establecido para la conformación de asesorías directas.</div>'>
                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-plus-circle" fill="currentColor"
                    xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd"
                        d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                    <path fill-rule="evenodd"
                        d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
                </svg> &nbsp; Crear asesoría directa
            </button>
        </div>
    </div>
</div>

<!-- CONTENIDO ESCRITORIO-->
<div class="container">
    <div class="row">
        [#include '../shared/menu-proceso.ftl' /]
        <div class="col-md-9">
            <!-- Contenido escritorio-->
            <div id="panel-search" class="vis-des">
                <div class="row">
                    <div class="col-12">
                        <h2>Asesorías directa</h2>
                    </div>
                </div>

                <div class="row">
                    <!--Región-->
                    <div class="col-4">
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
                    <div class="col-4">
                        <div class="form-group">
                            <label for="nombrePerfil">Deprov</label>
                            <select id="selector-deprov" class="custom-select custom-select-sm">
                                <option value="" selected>Seleccionar</option>
                            </select>
                        </div>
                    </div>

                    <!--RBD-->
                    <div class="col-4">
                        <form>
                            <div class="form-group">
                                <label for="nombrePerfil">RBD</label>
                                <select id="selector-rbd" class="custom-select custom-select-sm">
                                    <option value="" selected>Seleccionar</option>
                                </select>
                            </div>
                        </form>
                    </div>

                    <!--Fecha creación-->
                    <div class="col-4">
                        <div class="form-group">
                            <label for="nombrePerfil">Creado entre las fechas</label>
                            <input id="input-fecha-rango" type="text" name="daterange" value="01/06/2020 - 01/12/2020"
                                class="form-control form-control-sm ">
                        </div>
                    </div>

                    <div class="col-4">
                        <label for=""></label>
                        <a id="find-advisory" href="#" class="btn btn-primary btn-sm btn-block mt-3">Buscar</a>
                    </div>

                </div>

            </div>

            <div id="panel-search-result" class="row" style="display: none;">
                <div class="col-12">
                    <h2>Asesoría directa</h2>
                </div>

                <div class="col-6 mt-2">
                    Resultado de la búsqueda (<strong id="count-advisory"></strong> asesorías)
                </div>

                <div class="col-6">
                    <button id="export-excel-advisory" class="btn btn-link float-right px-0">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-file-earmark-excel"
                            fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path
                                d="M4 0h5.5v1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V4.5h1V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2z" />
                            <path d="M9.5 3V0L14 4.5h-3A1.5 1.5 0 0 1 9.5 3z" />
                            <path fill-rule="evenodd"
                                d="M5.18 6.616a.5.5 0 0 1 .704.064L8 9.219l2.116-2.54a.5.5 0 1 1 .768.641L8.651 10l2.233 2.68a.5.5 0 0 1-.768.64L8 10.781l-2.116 2.54a.5.5 0 0 1-.768-.641L7.349 10 5.116 7.32a.5.5 0 0 1 .064-.704z" />
                        </svg> Exporta a Excel
                    </button>

                    <a id="advisory-find-again" href="#" class="btn btn-link float-right px-0 mr-4">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor"
                            xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
                            <path fill-rule="evenodd"
                                d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
                        </svg> Buscar nuevamente
                    </a>
                </div>

                <div class="col-12 mt-1 table-responsive">
                    <table id="table-advisory" class="table mine table-hover table-bordered table-sm">
                        <thead>
                            <tr>
                                <th scope="col">RBD</th>
                                <th scope="col">Región</th>
                                <th scope="col">Deprov</th>
                                <th scope="col">Fecha creación</th>
                                <!-- <th scope="col">Establecimientos</th> -->
                                <th scope="col">Habilitado</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>              
            </div>

            <!-- Contenido celular -->
        </div>
    </div>
</div>

<!-- CREAR NUEVA ASESORIA -->
<div class="modal fade" id="modal-new-advisory" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Asesoría directa</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="col-6">
                        <div>
                            <label for="fecha-inicio">Fecha creación</label> <br>
                            <input type="date" class="form-control" id="advisory-fecha-inicio" name="fecha-inicio">
                            <div class="invalid-feedback">
                                <p>Debes ingresar una fecha dentro del rango establecido.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-6"></div>

                    <div class="form-group col-6  mt-2 ">
                        <label for="nombrePerfil">Región</label>
                        <select id="advisory-selector-region" class="custom-select custom-select-sm">
                            <option value="" selected>Seleccionar</option>
                            [#list regiones as r]
                            <option value="${r.value}">${r.displayText}</option>
                            [/#list]
                        </select>
                        <div class="invalid-feedback">
                            <p>Debes seleccionar una region.</p>
                        </div>
                    </div>

                    <div class="col-6"></div>

                    <div class="form-group col-6 ">
                        <label for="nombrePerfil">Deprov</label>
                        <select id="advisory-selector-deprov" class="custom-select custom-select-sm">
                            <option value="" selected>Seleccionar</option>
                        </select>
                        <div class="invalid-feedback">
                            <p>Debes seleccionar una Deprov.</p>
                        </div>
                    </div>

                    <div class="col-6"></div>

                    <div class="form-group col-6  ">
                        <label for="nombrePerfil">Comuna</label>
                        <select id="advisory-selector-comuna" class="custom-select custom-select-sm">
                            <option value="" selected>Seleccionar</option>
                        </select>
                        <div class="invalid-feedback">
                            <p>Debes seleccionar una comuna.</p>
                        </div>
                    </div>

                    <div class="col-6"></div>

                    <div class="col-12 mt-2 ">
                        <div class="form-group col-12 pl-0">
                            <input type="text" id="input-establecimiento-search" class="form-control form-control-sm"
                                placeholder="Buscar...">
                        </div>

                        <div id="asesoria-lista" class="asesoria-lista">
                        </div>
                        <div class="invalid-feedback">
                            <p>Debes seleccionar un establecimiento.</p>
                        </div>

                    </div>

                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button type="button" id="advisory-button-save" class="btn btn-primary">Guardar</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal asesoría directa-->
<div class="modal fade" id="modal-detail-advisory" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Asesoría directa</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="col-6">
                        <div>
                            <label for="fecha-inicio">Fecha creación</label> <br>
                            <input type="text" class="form-control" id="advisory-detalle-fecha-creacion"
                                name="fecha-inicio" disabled>
                        </div>
                    </div>

                    <div class="col-6">
                    </div>

                    <div class="col-12 mt-4">
                        <p class="mb-2">Datos de la asesoría</p>
                    </div>

                    <div class="col-4">Región</div>
                    <div class="col-8">: <strong id="advisory-detalle-selector-region"></strong></div>

                    <div class="col-4">Deprov</div>
                    <div class="col-8">: <strong id="advisory-detalle-selector-deprov"></strong></div>

                    <div class="col-4">Comuna</div>
                    <div class="col-8">: <strong id="advisory-detalle-selector-comuna"></strong></div>

                    <div class="col-4">RBD</div>
                    <div class="col-8">: <strong id="advisory-detalle-selector-rbd"></strong></div>

                    <div class="col-4">Establecimiento</div>
                    <div class="col-8">: <strong id="advisory-detalle-establecimiento"></strong></div>
                </div>
            </div>

            <div class="modal-footer">
                <a id="advisory-button-editar" href="#" class="footer-switch fon-14">
                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil-square" fill="currentColor"
                        xmlns="http://www.w3.org/2000/svg">
                        <path
                            d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
                        <path fill-rule="evenodd"
                            d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
                    </svg>
                    Editar
                </a>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
            </div>
        </div>
    </div>
</div>


<!--DATA RANGE PICKER JS-->
<script type="text/javascript" src="${context}/locals/js/moment.min.js"></script>
<script type="text/javascript" src="${context}/locals/js/daterangepicker.js"></script>

<script>
    $(function () {
        var date = new Date();
        var minDate = new Date(date.getFullYear(), date.getMonth(), 1);
        var maxDate = new Date(date.getFullYear(), date.getMonth() + 1, -0);

        $('input[name="daterange"]').daterangepicker({
            opens: 'left',
            startDate: minDate,
            endDate: maxDate
        }, function (start, end, label) {
            console.log("A new date selection was made: " + minDate.format('YYYY-MM-DD') + ' to ' + maxDate.format('YYYY-MM-DD'));
        });
    });
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

<script type="text/javascript" src="${context}/locals/js/advisory/came.advisory.js"></script>
[/@main_page]