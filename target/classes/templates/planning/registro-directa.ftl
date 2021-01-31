[#include '*/commons/page-structure.ftl' /]
[@main_page]



<!-- Breadcrumb -->
<div class="container mt-3">
    <div class="row">
        <div class="col-12">
            <ol class="breadcrumb bg-white p-0">
                Estás&nbsp;en:&nbsp;
                <li><a href="#" class="active">Planificación ></a></li> &nbsp;
                <li><a href="#" class="active">Resultado de búsqueda ></a></li> &nbsp;
                <li>Registro de asesoría ditecta </li>
            </ol>
        </div>
    </div>
</div>

<div class="col-12">
    <p>
        <a id="button-back" href="#">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-left" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z" />
            </svg> Volver
        </a>
    </p>
</div>

<!-- CONTENIDO ESCRITORIO-->
<div class="container">


    <!-- Contenido escritorio-->
    <div class="vis-des">
        <div class="row">
            <div class="col-12">
                <h2>Asesoría directa</h2>
            </div>
        </div>

        <div class="row mt-3">
            <div class="col-3">RBD</div>
            <div class="col-9">: <strong>${planning.rbd?c}</strong></div>

            <div class="col-3">Nombre del Establecimiento</div>
            <div class="col-9">: <strong>${planning.nombreEstablecimiento}</strong></div>

            <div class="col-3">Número de sesiones</div>
            <div class="col-9">: <b>${planning.sesiones?size}</b></div>
        </div>

        <div class="row mt-3">
            <div class="col-10">
                <p class="mb-0">Supervisor(es) encargado(s):</p>
            </div>
            <div class="col-2 text-right">

            </div>

            <div class="col-12 table-responsive">
                <table class="table-striped table table-sm mine table-bordered">
                    <thead>
                        <tr>
                            <th>Apellido Parterno</th>
                            <th>Apeliido Materno</th>
                            <th>Nombres</th>
                        </tr>
                    </thead>
                    <tbody>
                        [#list planning.supervisores as s]
                        <tr>
                            <td>${s.nombres}</td>
                            <td>${s.apellidoPaterno}</td>
                            <td>${s.apellidoMaterno}</td>
                        </tr>
                        [/#list]
                    </tbody>
                </table>
            </div>

            <div class="col-10">
                <h5>Sesiones</h5>
            </div>

            [#if planning.sesiones?size > 0]
            <div class="col-2 text-right">
                [#if puedePlanificar]
                <a href="#" class="button-primera-planificacion badge badge-info" data-id-asesoria="${planning.idAsesoria}">Planificar</a>
                [/#if]
                <a id="button-ver-asesoria-focos" href="#" class="badge badge-success" data-id-asesoria-directa="${planning.idAsesoriaDirecta}">Ver focos</a>
            </div>

            <div class="col-12 table-responsive">
                <table class="table-striped table table-sm mine table-bordered">
                    <thead>
                        <tr>
                            <th>N° Sesiones</th>
                            <th>Fecha planificación</th>
                            <th>Fecha Realizada</th>
                            <th>Estado</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                        [#list planning.sesiones as s]
                        <tr>
                            <td>${s.numero}</td>
                            <td>${s.fechaPlanificacion?string('dd-MM-yyyy HH:mm')}</td>
                            [#if s.fechaRalizada??]
                            <td>${s.fechaRalizada?string('dd-MM-yyyy HH:mm')}</td>
                            [#else]
                            <td>-</td>
                            [/#if]
                            <td>${s.estado}</td>
                            [#if s.fechaRalizada??]
                            <td class="text-center"><a href="#" class="button-ver-sesion" data-id-sesion="${s.idSesion}">Ver sesión</a></td>
                            [#else]
                            <td class="text-center"><a id="button-registrar-sesion" href="#" data-id-sesion="${s.idSesion}" data-fecha-planificacion="${s.fechaPlanificacion?string('dd-MM-yyyy HH:mm')}" class="badge badge-pill badge-primary">Registrar</a></td>
                            [/#if]
                        </tr>
                        [/#list]
                    </tbody>
                </table>
            </div>
            [#else]
            <div class="col-12 text-center">
                <div class="col-12 sinplan">
                    <p> No has planificado ninguna sesión aún. </p>
                    <button class="btn btn-primary button-primera-planificacion">Planificar
                        nueva sesión</button>
                </div>
            </div>
            [/#if]

        </div>
    </div>
</div>

<!-- Modal Primera planificación-->
<div class="modal" id="modal-primera-planificacion" role="dialog" aria-labelledby="primeraPlanificacion" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Planificación próxima sesión</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-8 mb-2">
                        <p>Ingresa la fecha próxima sesión:</p>
                        <input id="planificar-fecha-inicio" type="date" class="form-control col-8" name="fecha-inicio">
                        <div class="invalid-feedback">
                            <p>Debes especificar una fecha.</p>
                        </div>
                    </div>
                    <div class="col-4 mb-2">
                        <p>hora:</p>
                        <input id="planificar-hora-inicio" type="time" class="form-control" id="appt" name="appt" min="09:00" max="18:00" value="13:30">
                        <div class="invalid-feedback">
                            <p>Debes especificar una hora.</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary">Cerrar</button>
                <button id="button-planificar-planificar" type="button" class="btn btn-primary" data-id-asesoria="${planning.idAsesoria}" title='<div class="text-center">¿Estás seguro?<br>Después no se puede modificar la fecha</div>'>Planificar</button>
            </div>
        </div>
    </div>
</div>

<!-- Registrar sesión -->
<div class="modal" id="modal-registrar-sesion" role="dialog" aria-labelledby="primeraPlanificacion" aria-hidden="true" data-max-foco="${maxFoco}">
    <div class="modal-dialog modal-dialog-scrollable" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Registrar sesión</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-6">
                        <label for="Fecha planificada">Fecha planificada:</label>
                        <input type="text" class="form-control " id="registra-sesion-fecha-planificada" readonly>
                    </div>
                    <div class="col-2"></div>
                    <div class="col-4">
                        <label for="Hora">Hora</label>
                        <input type="text" class="form-control" id="registra-sesion-hora-planificada" readonly>
                    </div>
                </div>

                <div class="row mt-4">
                    <div class="col-8 mb-0">
                        <!-- <p class="mb-0">Movimientos claves selecionados: 2</p> -->
                    </div>
                    <div class="col-4 mb-0">
                        <button id="button-foco-add" class="btn btn-sm btn-primary float-right" title='<div class="text-center">Agregar nuevo foco</div>'>
                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-plus-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                                <path fill-rule="evenodd" d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
                            </svg> &nbsp; Agregar foco</button>
                    </div>
                </div>

                <div class="row mt-1  pb-3 pt-2">
                    <div class="col-12">
                        <div class="col-12">
                            <div class="accordion" id="registra-sesion-focos">
                            </div>
                            <div class="invalid-feedback ml-3">
                                <p>Debes especificar un foco.</p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6">
                        <label for="Fecha planificada">Fecha próxima reunión:</label>
                        <input type="date" class="form-control " id="registra-sesion-fecha-proxima">
                        <div class="invalid-feedback">
                            <p>Debes especificar una fecha.</p>
                        </div>
                    </div>
                    <div class="col-2"></div>
                    <div class="col-4">
                        <label for="Hora">Hora:</label>
                        <input type="time" class="form-control" id="registra-sesion-hora-proxima">
                        <div class="invalid-feedback">
                            <p>Debes especificar una hora.</p>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-6 mt-4">
                        <label for="Tipo de asesoría">Tipo de asesoría:</label>
                    </div>
                    <div class="col-6 mt-4 registra-sesion-tipo-asesoria-group">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="registra-sesion-tipo-asesoria" id="inlineRadio2" value="Presencial">
                            <label class="form-check-label" for="inlineRadio2">Presencial</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="registra-sesion-tipo-asesoria" id="inlineRadio3" value="Remota">
                            <label class="form-check-label" for="inlineRadio3">Remota</label>
                        </div>
                    </div>
                    <div class="invalid-feedback ml-3">
                        <p>Debes especificar un tipo de asesoria.</p>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button id="button-registrar-sesion-guardar" class="btn btn-primary">Guardar</button>
            </div>
        </div>
    </div>
</div>

<!-- Ver sesión -->
<div class="modal" id="modal-ver-sesion" role="dialog" aria-labelledby="primeraPlanificacion" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Detalle de la sesión</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-6">
                        <label for="Fecha planificada">Fecha planificada:</label>
                        <input type="text" class="form-control " id="ver-sesion-fecha-planificada" disabled>
                    </div>
                    <div class="col-2"></div>
                    <div class="col-4">
                        <label for="Hora">Hora</label>
                        <input type="text" class="form-control" id="ver-sesion-hora-planificada" disabled>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-6">
                        <label for="Fecha planificada">Fecha realizada:</label>
                        <input type="text" class="form-control " id="ver-sesion-fecha-realizada" disabled>
                    </div>
                    <div class="col-2"></div>
                    <div class="col-4">
                        <label for="Hora">Hora</label>
                        <input type="text" class="form-control" id="ver-sesion-hora-realizada" disabled>
                    </div>
                </div>
                <div class="row mt-4">
                    <div class="col-12 mb-0">
                        <p class="mb-0">Movimientos claves realizados:</p>
                    </div>
                </div>

                <div class="row mt-2">
                    <div class="col-12">
                        <div class="accordion" id="ver-sesion-focos">
                        </div>
                    </div>
                </div>

                <div class="row mt-4">
                    <!-- <div class="col-6">
                        <label for="Fecha planificada">Fecha próxima reunión:</label>
                    </div>
                    <div class="col-6">
                        <input type="date" class="form-control " value="2020-10-10" disabled>
                    </div> -->
                    <div class="col-6 mt-4">
                        <label for="Tipo de asesoría">Tipo de asesoría:</label>
                    </div>
                    <div class="col-6 mt-4">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="ver-sesion-tipo-asesoria" id="ver-sesion-tipo-asesoria-presencial" value="Presencial" disabled>
                            <label class="form-check-label" for="inlineRadio2">Presencial</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="ver-sesion-tipo-asesoria" id="ver-sesion-tipo-asesoria-remota" value="Remota" disabled>
                            <label class="form-check-label" for="inlineRadio3">Remota</label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Aceptar</button>
            </div>
        </div>
    </div>
</div>

<div class="modal" id="modal-ver-focos-detalle" role="dialog" aria-labelledby="primeraPlanificacion" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Detalle focos asesoría</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row mt-4">
                    <div class="col-12 mb-0">
                        <p class="mb-0">Movimientos claves realizados:</p>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <div class="accordion" id="ver-asesoria-focos">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Aceptar</button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

<!--Estilos tree pluggins-->

<script type="text/javascript" src="${context}/locals/js/planning/came.planning.js"></script>
[/@main_page]