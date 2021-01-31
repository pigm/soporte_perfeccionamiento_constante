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
                <li>Registro de asesoría redes </li>
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
                <h2>Registro de asesoría red</h2>
            </div>
        </div>

        <div class="row mt-3">
            <div class="col-3">Nombre de red</div>
            <div class="col-9">: <strong>${planning.nombreRed}</strong></div>

            <div class="col-3">Tipo de red</div>
            <div class="col-9">: <strong>${planning.tipoRed}</strong></div>

            <div class="col-3">Deprov</div>
            <div class="col-9">: <strong>${planning.deprov}</strong></div>
        </div>

        <!--Encargado-->
        <div class="row mt-3">
            <div class="col-12">
                <p class="mb-0">Encargado de la red:</p>
            </div>

            <div class="col-12 table-responsive">
                <table class="table-striped table table-sm mine table-bordered">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Cargo</th>
                            <th>Correo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td id="planning-encargado-nombre">${planning.encargado.nombre}</td>
                            <td id="planning-encargado-apellido">${planning.encargado.apellido}</td>
                            <td id="planning-encargado-cargo">${planning.encargado.cargo}</td>
                            <td><a id="planning-encargado-correo" href="mailto:${planning.encargado.correo}">${planning.encargado.correo}</a></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <!--Establecimientos-->
        <div class="row mt-3">
            <div class="col-12">
                <p class="mb-0">Establecimientos (total <strong>${planning.establecimientos?size}</strong>)</p>
            </div>

            <div class="col-12 table-responsive">
                <div class="estable-redes">
                    <table class="table-striped table table-sm mine table-bordered asesoria-lista">
                        <thead>
                            <tr>
                                <th>RBD</th>
                                <th>Establecimiento</th>
                            </tr>
                        </thead>
                        <tbody>
                            [#list planning.establecimientos as e]
                            <tr>
                                <td>${e.rbd?string["0"]}</td>
                                <td>${e.establecimiento}</td>
                            </tr>
                            [/#list]
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="row mt-3">
            <div class="col-12">
                <p class="mb-0">Supervisor(es) encargado(s):</p>
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
                            <td>${s.apellidoPaterno}</td>
                            <td>${s.apellidoMaterno}</td>
                            <td>${s.nombres}</td>
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
                <a id="button-ver-asesoria-red-focos" href="#" class="badge badge-success" data-id-asesoria="${planning.idAsesoria}">Ver focos</a>
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
                            <td class="text-center">
                                <a id="button-ver-sesion-red" href="#" data-id-sesion="${s.idSesion}" data-fecha-planificacion="${s.fechaPlanificacion?string('dd-MM-yyyy HH:mm')}">Ver sesión</a>
                            </td>
                            [#else]
                            <td class="text-center"><a id="button-registrar-sesion-red" href="#" data-id-sesion="${s.idSesion}" data-fecha-planificacion="${s.fechaPlanificacion?string('dd-MM-yyyy HH:mm')}" class="badge badge-pill badge-primary">Registrar</a></td>
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
<div class="modal" id="modal-registrar-sesion-red" role="dialog" aria-labelledby="primeraPlanificacion" aria-hidden="true" style="z-index: 9999;">
    <div class="modal-dialog  modal-lg modal-dialog-scrollable" role="document">
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
                        <input type="text" class="form-control " id="registra-sesion-red-fecha-planificada" disabled>
                    </div>
                    <div class="col-2"></div>
                    <div class="col-4">
                        <label for="Hora">Hora</label>
                        <input type="text" class="form-control" id="registra-sesion-red-hora-planificada" disabled>
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-12 mb-0">
                        <p class="mb-2"><b>Encargado de red</b></p>
                    </div>
                    <div class="col-2">Nombre</div>
                    <div class="col-10">: <strong id="registra-sesion-red-nombre">Pedro Gonzales</strong></div>

                    <div class="col-2">Cargo</div>
                    <div class="col-10">: <strong id="registra-sesion-red-cargo">Jefe Técnico</strong></div>

                    <div class="col-2">Correo</div>
                    <div class="col-10">: <a id="registra-sesion-red-correo" href="mailto:"></a></div>
                </div>

                <div class="row mt-3">
                    <div class="col-12">
                        <p class="mb-2"><b>Objetivo anual de la red</b></p>
                    </div>

                    <div class="col-6">
                        <p class="mb-1 fon-14">Objetivo 1</p>
                        <textarea class="form-control" id="registra-sesion-red-objetivo1" rows="3"></textarea>
                        <div class="invalid-feedback ml-3">
                            <p>Debes especificar el objetivo 1.</p>
                        </div>
                    </div>

                    <div class="col-6">
                        <p class="mb-1 fon-14">Objetivo 2 (Opcional)</p>
                        <textarea class="form-control" id="registra-sesion-red-objetivo2" rows="3"></textarea>
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-6">
                        <p class="mb-2"><b>Focos</b></p>
                    </div>
                    <div class="col-6">
                        <button id="registra-sesion-red-agregar-foco" class="btn btn-primary btn-sm float-right px-4">Agregar foco</button>
                    </div>
                    <div class="col-12 table-responsive mt-2">
                        <table id="registra-sesion-red-focos" class="table-striped table table-sm mine table-bordered">
                            <thead>
                                <tr>
                                    <th width="15%">Foco</th>
                                    <th>Porcentaje logro</th>
                                    <th>Acción de mejora seleccionada</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                </tr>
                            </tbody>
                        </table>
                        <div class="invalid-feedback ml-3">
                            <p>Debes especificar un foco.</p>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-8 mb-2">
                        <p>Ingresa la fecha próxima sesión:</p>
                        <input id="registra-sesion-red-fecha-proxima" type="date" class="form-control col-8" name="fecha-inicio">
                        <div class="invalid-feedback">
                            <p>Debes especificar una fecha.</p>
                        </div>
                    </div>
                    <div class="col-4 mb-2">
                        <p>hora:</p>
                        <input id="registra-sesion-red-hora-proxima" type="time" class="form-control" id="appt" name="appt" min="09:00" max="18:00" value="13:30">
                        <div class="invalid-feedback">
                            <p>Debes especificar una hora.</p>
                        </div>
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-6 mt-4">
                        <label for="Tipo de asesoría">Tipo de asesoría:</label>
                    </div>
                    <div class="col-6 mt-4 registra-sesion-red-tipo-asesoria-group">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="registra-sesion-red-tipo-asesoria" id="inlineRadio2" value="Presencial">
                            <label class="form-check-label" for="inlineRadio2">Presencial</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="registra-sesion-red-tipo-asesoria" id="inlineRadio3" value="Remota">
                            <label class="form-check-label" for="inlineRadio3">Remota</label>
                        </div>
                    </div>
                    <div class="invalid-feedback ml-3">
                        <p>Debes especificar un tipo de asesoria.</p>
                    </div>
                </div>

                <div class="row mt-3 mb-3">
                    <div class="col-7">
                        <p class="mb-2"><b>Participantes presentes</b> <small></small> </p>
                    </div>
                    <div class="col-5">
                    </div>
                    <div class="col-12 table-responsive mt-2">
                        <div class="estable-redes">
                            <table id="registra-sesion-red-participantes" class="table-striped table table-sm mine table-bordered">
                                <thead>
                                    <tr>
                                        <th>N°</th>
                                        <th id="header-participantes-rbd">RBD</th>
                                        <th id="header-participantes-est">Establecimiento</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                    </tr>
                                </tbody>
                            </table>
                            <div class="invalid-feedback ml-3">
                                <p>Debes marcar a lo menos un participante.</p>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button id="registra-sesion-red-guardar-sesion" class="btn btn-primary">Guardar</button>
            </div>
        </div>
    </div>
</div>

<!-- Ver sesión -->
<div class="modal" id="modal-ver-sesion-red" role="dialog" aria-labelledby="primeraPlanificacion" aria-hidden="true">
    <div class="modal-dialog  modal-lg modal-dialog-scrollable" role="document">
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
                        <input type="text" class="form-control " id="ver-sesion-red-fecha-planificada" name="fecha-inicio" disabled>
                    </div>
                    <div class="col-2"></div>
                    <div class="col-4">
                        <label for="Hora">Hora</label>
                        <input type="text" class="form-control" id="ver-sesion-red-hora-planificada" name="appt" disabled>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-6">
                        <label for="Fecha planificada">Fecha realizada:</label>
                        <input type="text" class="form-control " id="ver-sesion-red-fecha-realizada" name="fecha-inicio" value="2020-10-10" disabled>
                    </div>
                    <div class="col-2"></div>
                    <div class="col-4">
                        <label for="Hora">Hora</label>
                        <input type="text" class="form-control" id="ver-sesion-red-hora-realizada" name="appt" min="09:00" max="18:00" value="13:30" disabled>
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-12 mb-0">
                        <p class="mb-2"><b>Encargado de red</b></p>
                    </div>
                    <div class="col-2">Nombre</div>
                    <div class="col-10">: <strong id="ver-sesion-red-nombre">Pedro Gonzales</strong></div>

                    <div class="col-2">Cargo</div>
                    <div class="col-10">: <strong id="ver-sesion-red-cargo">Jefe Técnico</strong></div>

                    <div class="col-2">Correo</div>
                    <div class="col-10">: <a id="ver-sesion-red-correo" href="mailto:"></a></div>
                </div>

                <div class="row mt-3">
                    <div class="col-12">
                        <p class="mb-2"><b>Objetivo anual de la red</b></p>
                    </div>

                    <div class="col-6">
                        <p class="mb-1 fon-14">Objetivo 1</p>
                        <textarea class="form-control" id="ver-sesion-red-objetivo1" rows="3" readonly></textarea>
                        <div class="invalid-feedback ml-3">
                            <p>Debes especificar el objetivo 1.</p>
                        </div>
                    </div>

                    <div class="col-6">
                        <p class="mb-1 fon-14">Objetivo 2 (Opcional)</p>
                        <textarea class="form-control" id="ver-sesion-red-objetivo2" rows="3" readonly></textarea>
                    </div>
                </div>

                <div class="row mt-4">
                    <div class="col-12 mb-0">
                        <p class=""><b>Acciones de mejora:</b></p>
                    </div>
                    <div class="col-12 table-responsive">
                        <table id="ver-sesion-red-focos" class="table-striped table table-sm mine table-bordered mb-1">
                            <thead>
                                <tr>
                                    <th>Foco</th>
                                    <th>Porcentaje Logro</th>
                                    <th>Acciones de mejora</th>                                    
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="row mt-4">
                    <div class="col-6 mt-4">
                        <label for="Tipo de asesoría">Tipo de asesoría:</label>
                    </div>
                    <div class="col-6 mt-4 ver-sesion-red-tipo-asesoria-group">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="ver-sesion-red-tipo-asesoria" id="ver-sesion-red-tipo-asesoria-presencial" value="Presencial" disabled>
                            <label class="form-check-label" for="inlineRadio2">Presencial</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="ver-sesion-red-tipo-asesoria" id="ver-sesion-red-tipo-asesoria-remota" value="Remota" disabled>
                            <label class="form-check-label" for="inlineRadio3">Remota</label>
                        </div>
                    </div>
                </div>

                <div class="row mt-3 mb-3">
                    <div class="col-7">
                        <p class="mb-2"><b>Participantes presentes</b> <small>(8)</small> </p>
                    </div>
                    <div class="col-5">
                        <input class="form-control form-control-sm" placeholder="Buscar participantes ...">
                    </div>
                    <div class="col-12 table-responsive mt-2">
                        <div class="estable-redes">
                            <table id="ver-sesion-red-participantes" class="table-striped table table-sm mine table-bordered">
                                <thead>
                                    <tr>
                                        <th>N°</th>
                                        <th id="header-participantes-rbd-ver">RBD</th>
                                        <th id="header-participantes-est-ver">Establecimiento</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                    </tr>
                                </tbody>
                            </table>
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

<!-- Agregar foco -->
<div class="modal" id="modal-registrar-sesion-red-foco" role="dialog" aria-labelledby="primeraPlanificacion" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Agregar foco</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-6">
                        <div class="form-group">
                            <label for="nombrePerfil">Selecciona un foco</label>
                            <select id="registra-sesion-red-selector-foco" class="custom-select custom-select-sm">
                            </select>
                        </div>
                    </div>
                    <div class="col-12 mt-2">
                        <p class="mb-1">Objetivos anuales de la red</p>
                        <small>Objetivo 1</small>
                        <textarea class="form-control" id="registra-sesion-red-foco-objetivo1" rows="3" disabled></textarea>
                    </div>
                    <div class="col-12 mt-2">
                        <small>Objetivo 2</small>
                        <textarea class="form-control" id="registra-sesion-red-foco-objetivo2" rows="3" disabled></textarea>
                    </div>

                    <div class="col-10 mt-3">
                        <p class="mb-1">Acciones de mejoras:</p>
                    </div>
                    <div class="col-2 mt-3">
                        <a href="#" data-placement="top" data-toggle="popover" data-trigger="hover" data-content='<div class="col-12 text-rea"> <p>Acción de mejora realizada.</p> </div> <div class="col-12 text-warning"> <p class="mb-0">Acción de mejora sesión anterior.</p> </div>'>
                            <svg width="1.7em" height="1.7em" viewBox="0 0 16 16" class="bi bi-info-circle-fill float-right text-warning" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412l-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM8 5.5a1 1 0 1 0 0-2 1 1 0 0 0 0 2z" />
                            </svg>
                        </a>
                    </div>
                    <div class="col-12 table-responsive mt-2">
                        <table id="registra-sesion-red-foco-acciones" class="table-striped table table-sm mine table-bordered">
                            <thead>
                                <tr>
                                    <th>Acciones de mejoras</th>
                                    <th>Ejecutado</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td colspan="2" class="text-center">No hay Datos</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-12 mb-2">
                        <!-- NUEVO ELEMENTO -->
                        <button style="display: none;" id="registra-sesion-red-foco-nueva-accion" type="button" class="btn btn-link float-right px-0" title='<div class="text-center">Nombre acción de mejora </div>'>
                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-plus-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                                <path fill-rule="evenodd" d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
                            </svg>&nbsp;Nueva acción de mejora
                        </button>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button id="registra-sesion-red-foco-guardar" class="btn btn-primary">Guardar</button>
            </div>
        </div>
    </div>
</div>

<!-- Ver detalle focos -->
<div class="modal" id="modal-red-ver-focos-detalle" role="dialog" aria-labelledby="primeraPlanificacion" aria-hidden="true">
    <div class="modal-dialog  modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Detalle de la asesoría red</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">                
                <div class="row mt-4">
                    <div class="col-12 mb-0">
                        <p class=""><b>Acciones de mejora:</b></p>
                    </div>
                    <div class="col-12 table-responsive">
                        <table id="ver-sesion-red-focos-detalle" class="table-striped table table-sm mine table-bordered mb-1">
                            <thead>
                                <tr>
                                    <th>Foco</th>
                                    <th>Porcentaje Logro</th>
                                    <th>Acciones de mejora</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                </tr>
                            </tbody>
                        </table>
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