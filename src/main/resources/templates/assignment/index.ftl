[#include '*/commons/page-structure.ftl' /] [@main_page]

<!--DUAL LIST BOX-->
<link rel="stylesheet" href="${context}/locals/css/bootstrap-duallistbox.css">
<!-- Breadcrumb -->
<div class="container mt-3">
    <div class="row">
        <div class="col-6 ">
            <ol class="breadcrumb bg-white p-0">
                Estás&nbsp;en:&nbsp;

                <li><a href="#" class="active">Asignar supervisores </a></li>
            </ol>
        </div>
    </div>
</div>

<div class="container px-3">
    <div class="row">
        [#include '../shared/menu-proceso.ftl' /]
        <div class="col-md-9">
            <div id="panel-search" class="vis-des">
                <div class="row ">
                    <div class="col-12">
                        <h2>Asignar supervisores</h2>
                    </div>

                    <!--REGIÓN-->
                    <div class="col-3">
                        <div class="form-group">
                            <label for="nombrePerfil">Región</label> <select id="selector-region"
                                class="custom-select custom-select-sm">
                                <option value="" selected>Seleccionar</option> [#list regiones as r]
                                <option value="${r.value}">${r.displayText}</option> [/#list]
                            </select>
                        </div>
                    </div>

                    <!--Deprov-->
                    <div class="col-3">
                        <form>
                            <div class="form-group">
                                <label for="nombrePerfil">Deprov</label> <select id="selector-deprov"
                                    class="custom-select custom-select-sm">
                                    <option value="" selected>Seleccionar</option>
                                </select>
                            </div>
                        </form>
                    </div>

                    <!--COMUNA-->
                    <div class="col-3">
                        <form>
                            <div class="form-group">
                                <label for="nombrePerfil">Comuna</label> <select id="selector-comuna"
                                    class="custom-select custom-select-sm">
                                    <option value="" selected>Seleccionar</option>
                                </select>
                                <div class="invalid-feedback">
                                    <p>Debes seleccionar una comuna.</p>
                                </div>
                            </div>
                        </form>
                    </div>

                    <!--TIPO-->
                    <div class="col-3">
                        <form>
                            <div class="form-group">
                                <label for="nombrePerfil">Tipo</label> <select id="selector-tipo"
                                    class="custom-select custom-select-sm">
                                    <option value="" selected>Seleccionar</option>
                                    <option value="2465568769376782016">Red</option>
                                    <option value="2465568769368393407">Directa</option>
                                </select>
                            </div>
                        </form>
                    </div>

                    <!--TIPO DE APOYO-->
                    <div class="col-4">
                        <form>
                            <div class="form-group">
                                <label for="nombrePerfil">Tipo de red</label> <select id="selector-tipo-red"
                                    class="custom-select custom-select-sm">
                                    <option value="" selected>Seleccionar</option> [#list tipoRed as tr]
                                    <option value="${tr.value}">${tr.displayText}</option> [/#list]
                                </select>
                            </div>
                        </form>
                    </div>

                    <!--CATEGORÍA-->
                    <div class="col-4">
                        <form>
                            <div class="form-group">
                                <label for="nombrePerfil">Categoría</label> <select id="selector-categoria"
                                    class="custom-select custom-select-sm">
                                    <option value="" selected>Seleccionar</option> [#list tipoCategoria as tc]
                                    <option value="${tc.value}">${tc.displayText}</option> [/#list]
                                </select>
                            </div>
                        </form>
                    </div>

                    <!--RBD-->
                    <div class="col-4">
                        <div class="form-group">
                            <label for="nombrePerfil">RBD</label> <input type="text" id="input-rbd"
                                class="form-control form-control-sm ">
                        </div>
                    </div>

                    <!--NOMBRE ESTABLECIMIENTO-->
                    <div class="col-6">
                        <div class="form-group">
                            <label for="nombrePerfil">Nombre establecimiento</label> <input type="text"
                                id="input-nombre-establecimiento" class="form-control form-control-sm ">
                        </div>
                    </div>

                    <!--NOMBRE RED-->
                    <div class="col-6">
                        <div class="form-group">
                            <label for="nombrePerfil">Nombre de Red</label> <input type="text" id="input-nombre-red"
                                class="form-control form-control-sm ">
                        </div>
                    </div>

                    <div class="col-3">
                        <label for=""></label> <a id="find-assignment" href="#"
                            class="btn btn-primary btn-sm btn-block mt-3">Buscar</a>
                    </div>
                </div>               
            </div>
            <div id="panel-search-result" class="vis-des" style="display: none;">
                <div class="vis-des">
                    <div class="row">
                        <div class="col-12">
                            <h2>Asignar supervisores</h2>
                        </div>

                    </div>

                    <div class="row mt-3">
                        <div class="col-6">
                            Resultado de la búsqueda (<strong id="count-assignment"></strong> redes)
                        </div>
                        <div class="col-6">
                            <button id="export-excel-supervisores" class="btn btn-link float-right px-0"><svg width="1em" height="1em"
                                    viewBox="0 0 16 16" class="bi bi-file-earmark-excel" fill="currentColor"
                                    xmlns="http://www.w3.org/2000/svg">
                                    <path
                                        d="M4 0h5.5v1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V4.5h1V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2z" />
                                    <path d="M9.5 3V0L14 4.5h-3A1.5 1.5 0 0 1 9.5 3z" />
                                    <path fill-rule="evenodd"
                                        d="M5.18 6.616a.5.5 0 0 1 .704.064L8 9.219l2.116-2.54a.5.5 0 1 1 .768.641L8.651 10l2.233 2.68a.5.5 0 0 1-.768.64L8 10.781l-2.116 2.54a.5.5 0 0 1-.768-.641L7.349 10 5.116 7.32a.5.5 0 0 1 .064-.704z" />
                                </svg> Exporta a Excel</button>
                            <a id="begin-find" href="#" class="btn btn-link float-right px-0 mr-4"><svg
                                    width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search"
                                    fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd"
                                        d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
                                    <path fill-rule="evenodd"
                                        d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
                                </svg> Buscar nuevamente</a>
                        </div>
                    </div>

                    <div class="row mt-1">
                        <div class="overflow-auto col-12 px-0">
                            <div class="table-responsive">
                                <table id="table-assignment" class="table mine table-hover" style="width: 100%;">
                                    <thead class="table-bordered">
                                        <th scope="col">Supervisor</th>
                                        <th scope="col">RBD</th>
                                        <th scope="col">Región</th>
                                        <th scope="col">Deprov</th>
                                        <th scope="col">Comuna</th>
                                        <th scope="col">Tipo</th>
                                        <th scope="col">Tipo apoyo</th>
                                        <th scope="col">Categoría</th>
                                        <th scope="col">Nombre red</th>
                                    </thead>
                                    <tbody class="table-bordered">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>                  
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Asignar Supervisor -->
<div class="modal" id="modal-assign-supervisor" tabindex="-1" role="dialog" aria-labelledby="AsigSuper"
    aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Asignación Supervisores</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                

                    <div class="col-4">Tipo</div>
                    <input type="hidden" id="id-assign-supervisor" value="" />
                    <input type="hidden" id="id-max-assign" value="" />
                    <input type="hidden" id="edit-data-id-supervisor" value="" />
                    <div class="col-8">: <strong id="modal-type-assignment"></strong></div>

                    <div class="col-4">Nombre de la Red</div>
                    <div class="col-8">: <strong id="modal-name-assignment"></strong></div>

                    <div class="col-12 mt-3 establishment-class" >
                        <p class="mb-2">Establecimientos de la red: <strong id="modal-count-establishment"></strong></p>
                        <div class="asesoria-lista">
                            <table id="modal-table-establishment" class="table table-striped table-sm">
                                <tbody>
                                    
                                </tbody>
                            </table>
                        </div>
                    </div>
                    
                    <div class="list col-12 mt-1">
                        <select multiple="multiple" class="demo2"
                            id="super-selector-asignaciones"
                            name="duallistbox_demo2_helper1" style="height: 200px;">                           
                        </select>
                        <div class="invalid-feedback">
                            <p>Sobrepasa asignación máxima.</p>
                        </div>
                    </div>

                   

                </div>
            </div>
            <div id="error"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button type="button" id="asignacion-button-guardar" class="btn btn-primary">Asignar</button>
            </div>
        </div>
    </div>
</div>

<div class="modal" id="modal-detalle-asignacion" tabindex="-1" role="dialog" aria-labelledby="detalleAsig"
    aria-hidden="true">
<input type="hidden" id="edit-data-id-red" value="" />
<input type="hidden" id="edit-data-id-asignacion-supervisor" value="" />        
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Establecimientos asociados</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-12">
                        <h5>Supervisor</h5>
                    </div>
                    <div id="supervisores-modal" class="row">
                    
                    </div>
                    
                    <div class="col-12 mt-3">
                        <h5>Datos asesoría</h5>
                    </div>
                    <div class="col-4">Región</div>
                    <div class="col-8">: <strong id="detail-region"></strong></div>

                    <div class="col-4">Deprov</div>
                    <div class="col-8">: <strong id="detail-deprov"></strong></div>

                    <div class="col-4">Comuna</div>
                    <div class="col-8">: <strong id="detail-comuna"></strong></div>

                    <div class="col-4">Tipo</div>
                    <div class="col-8">: <strong id="detail-tipo"></strong></div>

                    <div class="col-4">Tipo de apoyo</div>
                    <div class="col-8">: <strong id="detail-tipo-apoyo"></strong></div>


                    <div class="col-4">Categoría</div>
                    <div class="col-8">: <strong id="detail-categoria"></strong></div>

                    <div class="col-4">Nombre de la reda</div>
                    <div class="col-8">: <strong id="detail-nombre-region"></strong></div>


                    <div class="col-12 mt-3 detail_establishment-class">
                        <p class="mb-0">Establecimientos de la red: <strong id="detail-count-establishment"></strong></p>
                        <div class="asesoria-lista">
                            <table id="detail-table-establishment" class="table table-striped table-sm">
                                <tbody>
                                    
                                </tbody>
                            </table>
                        </div>
                    </div>


                </div>
            </div>
            <div class="modal-footer">
                <a id="assignment-button-editar" href="#" class="footer-switch fon-14">
                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil-square" fill="currentColor"
                        xmlns="http://www.w3.org/2000/svg">
                        <path
                            d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
                        <path fill-rule="evenodd"
                            d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
                    </svg>
                    Editar
                </a>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Aceptar</button>
            </div>
        </div>
    </div>
</div>
<!--Tree duallistbox-->
<script type="text/javascript" src="${context}/locals/js/jquery.bootstrap-duallistbox.js"></script>

<script>
    var demo2 = $('.demo2').bootstrapDualListbox({
        nonSelectedListLabel: 'No seleccionados',
        selectedListLabel: 'Seleccionados',
        preserveSelectionOnMove: 'moved',
        moveOnSelect: false,
    });
</script>
<script type="text/javascript" src="${context}/locals/js/assignment/came.assignment.js"></script>





[/@main_page]