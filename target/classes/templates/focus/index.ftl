[#include '*/commons/page-structure.ftl' /]
[@main_page]
<!-- Breadcrumb -->
<div class="container mt-3 px-0">
    <div class="row">
        <div class="col-12">
            <ol class="breadcrumb bg-white p-0 ml-4">
                Estás&nbsp;en:&nbsp;
                <li> Administración /</li> &nbsp;
                <li><a href="#" class="active">Suplencias</a></li> &nbsp;
            </ol>
        </div>
    </div>
</div>

<div class="container px-3">
    <div class="row">
        <!-- Menú segundario -->
        [#include '../shared/menu-admin.ftl' /]

        <!-- Contenido escritorio-->
        <div class="col-md-9 vis-des">
            <div class="row">
                <div class="col-8 px-0">
                    <form class="form-inline">
                        <h2 class="my-1 mr-2" for="inlineFormCustomSelectPref">Focos</h2>
                        <select class="custom-select my-1 mr-sm-2" id="foco-year">                            
                            [#list years as year]
                            [#if year?string == currentYear?c]
                                <option value="${year?string}" selected>${year?string}</option>
                            [#else]
                                <option value="${year?string}">${year?string}</option>
                            [/#if]
                            [/#list]
                        </select>
                    </form>
                </div>
                <div class="col-4 px-0">
                    <button id="agregar-foco" class="btn btn-sm btn-primary float-right">
                        <svg width="1em" height="1em" viewBox="0 0 16 16"
                            class="bi bi-plus-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                            <path fill-rule="evenodd"
                                d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
                        </svg> &nbsp; Agregar focos</button>
                </div>
            </div>

            <div class="row">
                <div class="table-responsive-md col-12 px-0 mt-2">
                    <table class="table mine table-hover table-bordered table-sm" id="table-focos">
                        <thead>
                            <tr>
                                <th scope="col" width="24%">Foco</th>
                                <th scope="col">Creado</th>
                                <th scope="col">Descripción</th>
                                <th scope="col">Acciones de mejora</th>
                            </tr>
                        </thead>
                        <tbody class=" table-sm">
                            [#list focos as foco]
                            <tr>                               
                                <td><a href="#" data-focoId="${foco.idFoco}" class="ver-foco">${foco.nombre}</a></td>
                                <td>
                                    ${foco.fechaRegistro?date}
                                </td>
                                <td>
                                    [#if foco.descripcion?length < 25]
                                    ${foco.descripcion}
                                    [#else]
                                    <a href="#" data-focoId="${foco.idFoco}" class="ver-foco">${foco.descripcion?substring(0, 25)}...</a>
                                    [/#if]



                                </td>
                                <td>
                                    ${foco.acciones?size}
                                </td>
                            </tr>
                            [/#list]
                        </tbody>
                    </table>
                </div>
            </div>

        </div>        
    </div>
</div>

<!-- AGREGAR FOCOS-->
<div class="modal fade" id="agregarFoco" data-backdrop="modal" data-keyboard="false" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Agregar foco</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div id="content-focus" class="row">
                    <!-- NOMBRE PERFIL -->
                    <div class="col-8">
                        <div class="form-group">
                            <label for="nombrePerfil">Nombre del foco</label>
                            <input type="text" id="foco-nombre" class="form-control" aria-describedby="nombrePerfil"
                                placeholder="Ingresar nombre del foco">
                                <div class="invalid-feedback">
                                    <p>Debe especificar un nombre valido.</p>
                                </div>
                        </div>
                    </div>

                    <!-- PERIDO -->
                    <div class="col-8">
                        <div class="form-group">
                            <label for="periodo">Periodo</label>
                            <select class="custom-select my-1 mr-sm-2" id="foco-periodo">
                            [#list years as year]
                            [#if year?string == currentYear?c]
                            <option value="${year?string}" selected>${year?string}</option>
                            [#else]
                            <option value="${year?string}">${year?string}</option>
                            [/#if]
                            [/#list]
                            </select>
                        </div>
                    </div>

                    <div class="col-12">

                    </div>

                    <!-- DESCRIPCIÓN -->
                    <div class="col-12">
                        <div class="form-group">
                            <label for="Descripción">EID</label>
                            <textarea class="form-control" id="foco-descripcion" rows="3"
                                placeholder="Ingresar descripción del foco"></textarea>
                                <div class="invalid-feedback">
                                    <p>Debe especificar una descripcion valida.</p>
                                </div>
                        </div>
                    </div>


                    <!-- ACCIÓN DE MEJORA -->
                    <div id="acciones">
                        <!-- <div class="col-12">
                            <div class="form-row bg-agregar p-2 pb-3 accion">
                                <div class="col-12">
                                    <div class="form-group">
                                        <label for="nombrePerfil">Acción de mejora</label>
                                        <input type="text" id="foco-accion-nombre" class="form-control foco-accion-nombre" aria-describedby="nombreAccionMejora" placeholder="Ingresa nombre de acción de mejora">
                                        <div class="invalid-feedback">
                                            <p>Debe especificar una acción de mejora valida.</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="control-group">
                                        <label>Movimientos claves</label>
                                        <select multiple name="state[]" id="foco-accion-movimientos"
                                            class="demo-default movimientos-clave foco-accion-movimientos"
                                            placeholder="Escribe los movimientos claves..." style="width:100%">                                          
                                        </select>
                                        <div class="invalid-feedback">
                                            <p>Debe especificar una movimientos claves valido.</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> -->

                    </div>

                    <!-- BOTÓN NUEVA ACCIÓN DE MEJORDA-->
                    <div class="col-12 mt-2">
                        <button class="btn btn-link float-right p-0" id="agregar-acciones-mejora">
                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-plus-circle"
                                fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd"
                                    d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                                <path fill-rule="evenodd"
                                    d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
                            </svg> Agregar acción de mejora
                        </button>
                    </div>


                </div>
            </div>
            <div class="modal-footer">
                <a id="button-remove-foco" href="#" class="footer-switch text-danger fon-14 remove-foco" data-original-title="Seguro que quieres <br> eliminar esta foco">
                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash" fill="currentColor"
                        xmlns="http://www.w3.org/2000/svg">
                        <path
                            d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z" />
                        <path fill-rule="evenodd"
                            d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z" />
                    </svg>
                    Eliminar
                </a>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-primary" id="guardar-foco">Guardar</button>
            </div>
        </div>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>


<script type="text/javascript" src="${context}/locals/js/focus/came.focus.js"></script>
[/@main_page]