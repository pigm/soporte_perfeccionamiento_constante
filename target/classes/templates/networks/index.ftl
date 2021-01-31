[#include '*/commons/page-structure.ftl' /]
[@main_page]

<!--DATE RANGE PICKER-->
<link rel="stylesheet" href="${context}/locals/css/daterangepicker.css">
<!--DUAL LIST BOX-->
<link rel="stylesheet" href="${context}/locals/css/bootstrap-duallistbox.css">

<!-- Breadcrumb -->
<div class="container mt-3">
    <div class="row">
        <div class="col-6 ">
            <ol class="breadcrumb bg-white p-0">
                Estás&nbsp;en:&nbsp;

                <li><a href="#" class="active">Redes ></a></li> &nbsp;
                <li>Resultado de búsqueda </li>
            </ol>
        </div>

        <div class="col-6">
            <button id="button-new-network" class="btn btn-sm btn-primary float-right" title='<div class="text-center">Estas fuera del rango establecido para la conformación de redes.</div>'>
                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-plus-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                    <path fill-rule="evenodd" d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
                </svg> &nbsp; Crear red
            </button>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        [#include '../shared/menu-proceso.ftl' /]
        <div class="col-md-9">
            <!-- Contenido escritorio-->
            <div id="panel-search" class="vis-des">
                <div class="row">
                    <div class="col-12">
                        <h2>Redes</h2>
                    </div>
                </div>

                <div class="row">                   
                    <!--Región-->
                    <div class="col-3">
                        <form>
                            <label for="nombrePerfil">Región</label>
                            <select id="selector-region" class="custom-select custom-select-sm">
                                <option value="" selected>Seleccionar</option>
                                [#list regiones as r]
                                <option value="${r.value}">${r.displayText}</option>
                                [/#list]
                            </select>
                        </form>
                    </div>
                    <!--Deprov-->
                    <div class="col-3">
                        <form>
                            <div class="form-group">
                                <label for="nombrePerfil">Deprov</label>
                                <select id="selector-deprov" class="custom-select custom-select-sm">
                                    <option value="" selected>Seleccionar</option>
                                </select>
                            </div>
                        </form>
                    </div>

                    <!--Buscar-->
                    <div class="col-6">
                        <form>
                            <div class="form-group">
                                <label for="nombreRed">Nombre de la red</label>
                                <input id="input-nombre" type="text" class="form-control form-control-sm" aria-describedby="nombreRed">
                            </div>
                        </form>
                    </div>

                    <!--RBD-->
                    <div class="col-4">
                        <form>
                            <div class="form-group">
                                <label for="nombrePerfil">RBD</label>
                                <input id="input-rbd" type="text" class="form-control form-control-sm" aria-describedby="rbd">
                            </div>
                        </form>
                    </div>

                    <!--Tipo de red-->
                    <div class="col-4">
                        <form>
                            <div class="form-group">
                                <label for="nombrePerfil">Tipo de red</label>
                                <select id="selector-tipo-red" class="custom-select custom-select-sm">
                                    <option value="" selected>Seleccionar</option>
                                    [#list tipoRed as tr]
                                    <option value="${tr.value}">${tr.displayText}</option>
                                    [/#list]
                                </select>
                            </div>
                        </form>
                    </div>

                    <!--Fecha creación-->
                    <div class="col-4">
                        <div class="form-group">
                            <label for="nombrePerfil">Creado entre las fechas</label>
                            <input id="input-fecha-rango" type="text" name="daterange" class="form-control form-control-sm ">
                        </div>
                    </div>

                    <div class="col-4">
                        <label for=""></label>
                        <a id="find-networks" href="#" class="btn btn-primary btn-sm btn-block mt-3">Buscar</a>
                    </div>

                </div>

            </div>

            <div id="panel-search-result" class="vis-des" style="display: none;">
                <div class="row ">
                    <div class="col-12">
                        <h2>Redes</h2>
                    </div>

                    <div class="col-6 mt-2">
                        Resultado de la búsqueda (<strong id="count-networks"></strong> redes)
                    </div>

                    <div class="col-6">
                        <button id="export-excel-networks" class="btn btn-link float-right px-0">
                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-file-earmark-excel" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path d="M4 0h5.5v1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V4.5h1V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2z" />
                                <path d="M9.5 3V0L14 4.5h-3A1.5 1.5 0 0 1 9.5 3z" />
                                <path fill-rule="evenodd" d="M5.18 6.616a.5.5 0 0 1 .704.064L8 9.219l2.116-2.54a.5.5 0 1 1 .768.641L8.651 10l2.233 2.68a.5.5 0 0 1-.768.64L8 10.781l-2.116 2.54a.5.5 0 0 1-.768-.641L7.349 10 5.116 7.32a.5.5 0 0 1 .064-.704z" />
                            </svg> Exporta a Excel
                        </button>

                        <a id="red-find-again" href="#" class="btn btn-link float-right px-0 mr-4">
                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
                                <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
                            </svg> Buscar nuevamente
                        </a>
                    </div>

                    <div class="col-12 mt-1 table-responsive">
                        <table id="table-redes" class="table mine table-hover table-bordered table-sm">
                            <thead>
                                <tr>
                                    <th scope="col">Nombre de la red</th>
                                    <th scope="col">Región</th>
                                    <th scope="col">Deprov</th>
                                    <th scope="col">Tipo de red</th>
                                    <th scope="col">Fecha creación</th>
                                    <th scope="col">Establecimientos</th>
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

<!-- Modal -->
<div class="modal fade" id="modal-new-network" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Red</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="col-12">
                        <label for="fecha-inicio">Fecha creación</label> <br>
                        <input type="date" class="form-control col-6" id="red-fecha-inicio" name="fecha-inicio">
                        <div class="invalid-feedback">
                            <p>Debes ingresar una fecha dentro del rango establecido.</p>
                        </div>
                    </div>

                    <div class="form-group col-12 mb-0 mt-1 ">
                        <label for="nombrePerfil">Nombre de la red</label>
                        <input id="red-input-nombre" type="text" class="form-control" aria-describedby="nombrePerfil" placeholder="Ingresar nombre de la red">
                        <div class="invalid-feedback">
                            <p>Debes ingresar un nombre valido y que no este en uso.</p>
                        </div>
                    </div>

                    <div class="form-group col-6 mt-1">
                        <label for="nombrePerfil">Región</label>
                        <select id="red-selector-region" class="custom-select custom-select-sm">
                            <option value="" selected>Seleccionar</option>
                            [#list regiones as r]
                            <option value="${r.value}">${r.displayText}</option>
                            [/#list]
                        </select>
                        <div class="invalid-feedback">
                            <p>Debes seleccionar una región.</p>
                        </div>
                    </div>

                    <div class="form-group col-6 mt-1">
                        <label for="nombrePerfil">Deprov</label>
                        <select id="red-selector-deprov" class="custom-select custom-select-sm">
                            <option value="" selected>Seleccionar</option>
                        </select>
                        <div class="invalid-feedback">
                            <p>Debes seleccionar una dirección provincial.</p>
                        </div>
                    </div>

                    <div class="form-group col-6 mt-1">
                        <label for="nombrePerfil">Comuna</label>
                        <select id="red-selector-comuna" class="custom-select custom-select-sm">
                            <option value="" selected>Seleccionar</option>
                        </select>
                        <div class="invalid-feedback">
                            <p>Debes seleccionar una comuna.</p>
                        </div>
                    </div>

                    <div class="form-group col-6 mt-1">
                        <label for="nombrePerfil">Tipo de red</label>
                        <select id="red-selector-tipo-red" class="custom-select custom-select-sm">
                            <option value="" selected>Seleccionar</option>
                            [#list tipoRed as tr]
                            <option value="${tr.value}">${tr.displayText}</option>
                            [/#list]
                        </select>
                        <div class="invalid-feedback">
                            <p>Debes seleccionar un tipo de red.</p>
                        </div>
                    </div>

                    <div class="list col-12 mt-1">
                        <select multiple="multiple" class="demo2" id="red-selector-establecimientos" name="duallistbox_demo2_helper1" style="height: 200px;">
                        </select>
                        <div class="invalid-feedback">
                            <p>Debe seleccionar a lo menos un elemento.</p>
                        </div>
                    </div>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button id="red-button-guardar" type="button" class="btn btn-primary">Crear</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal detalle colegios-->
<div id="modal-detalle-red" class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="red-detalle-nombre">Nombre de la red</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div id="content-network" class="modal-body">
                <div class="row">
                    <div class="col-3">Región</div>
                    <div class="col-9">: <strong id="red-detalle-selector-region"></strong></div>

                    <div class="col-3">Deprov</div>
                    <div class="col-9">: <strong id="red-detalle-selector-deprov"></strong></div>

                    <div class="col-3">Tipo de red</div>
                    <div class="col-9">: <strong id="red-detalle-selector-tipo-red"></strong></div>
                </div>

                <div class="row mt-4">
                    <div class="col-12">
                        <label for="">Establecimientos en la red: <strong id="red-detalle-count-networks">25</strong></label>
                        <table id="red-detalle-table-networks" class="table mine table-hover table-bordered table-sm">
                            <thead>
                                <tr>
                                    <th scope="col">Nombre establecimiento</th>
                                    <th scope="col">Habilitar</th>
                                </tr>
                            </thead>
                            <tbody class=" table-sm">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <a id="red-button-editar" href="#" class="footer-switch  fon-14" >
                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
                        <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
                    </svg>
                    Editar
                </a>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                <!-- <button type="button" class="btn btn-primary">Crear</button> -->
            </div>
        </div>
    </div>
</div>

<!--DATA RANGE PICKER JS-->
<script type="text/javascript" src="${context}/locals/js/moment.min.js"></script>
<script type="text/javascript" src="${context}/locals/js/daterangepicker.js"></script>
<!--Tree duallistbox-->
<script type="text/javascript" src="${context}/locals/js/jquery.bootstrap-duallistbox.js"></script>

<script>
    $(function () {
        var date = new Date();
        var minDate = new Date(date.getFullYear(), 0, 1);
        var maxDate = new Date(date.getFullYear(), 11, 31);

        $('input[name="daterange"]').daterangepicker({
            opens: 'left',
             locale: {
                format: 'DD/MM/YYYY'
            },
            startDate: minDate,
            endDate: maxDate
        }, function (start, end, label) {
            console.log("A new date selection was made: " + minDate + ' to ' + maxDate);
        });
    });
</script>
<script>
    var demo2 = $('.demo2').bootstrapDualListbox({
        nonSelectedListLabel: 'No seleccionados',
        selectedListLabel: 'Seleccionados',
        preserveSelectionOnMove: 'moved',
        moveOnSelect: false,
    });
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

<script type="text/javascript" src="${context}/locals/js/networks/came.networks.js"></script>
[/@main_page]