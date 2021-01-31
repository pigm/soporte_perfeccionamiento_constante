[#include '*/commons/page-structure.ftl' /]
[@main_page]

<!-- Breadcrumb -->
<div class="container mt-3">
    <div class="row">
        <div class="col-12 ">
            <ol class="breadcrumb bg-white p-0">
                Estás&nbsp;en:&nbsp;

                <li><a href="#" class="active">Retroalimentación ></a></li> &nbsp;
                <li><a href="#" class="active">Resultado de búsqueda ></a></li> &nbsp;
                <li>Registrar retroalimentación </li>
            </ol>
        </div>
    </div>
</div>

<!-- CONTENIDO ESCRITORIO-->
<div class="container">

    <!-- Contenido escritorio-->
    <div class="vis-des">
        <div class="row">
            <div class="col-12">
                <p>
                    <a id="button-back" href="#">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-left" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z" />
                        </svg> Volver
                    </a>
                </p>
            </div>

            <div class="col-12">
                <h2>Registrar retroalimentación</h2>
                <p class="mb-1 fon-18 mt-2">Reunión de retroalimentación N° <strong id="registro-feedback-numero" data-feedback-id="${feedback.idFeedback}">${feedback.numero?c}</strong></p>
            </div>

            <div class="col-md-12">             

            </div>
            <div class="col-md-2">Supervisor</div>
            <div class="col-md-10">: <strong> ${feedback.supervisorNombre}</strong></div>

            <div class="col-md-2">Region</div>
            <div class="col-md-10">: <strong>${feedback.region}</strong></div>

            <div class="col-md-2">Deprov</div>
            <div class="col-md-10">: <strong>${feedback.deprov}</strong></div>

            <div class="col-md-2">Semestre</div>
            <div class="col-md-10">: <strong>${feedback.semestre}</strong></div>
        </div>

        <div class="row mt-3">
            <div class="col-md-4">
                <div class="row">
                    <div class="col-md-8">
                        <label for="Fecha planificada">Fecha registro sesión:</label>
                        <input type="date" class="form-control form-control-sm" id="registro-feedback-fecha-registro" name="fecha-inicio">
                        <div class="invalid-feedback">
                            <p>Debes especificar una fecha.</p>
                        </div>
                    </div>
                    <div class="col-md-4 pl-0">
                        <label for="Hora">Hora</label>
                        <input type="time" class="form-control form-control-sm" id="registro-feedback-hora-registro" name="appt" min="09:00" max="18:00">
                        <div class="invalid-feedback">
                            <p>Debes especificar una hora.</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="row">
                    <div class="form-group">
                        <label for="nombrePerfil">Momento de la asesoría</label>
                        <select id="registro-feedback-momento-asesoria" class="custom-select custom-select-sm form-control">
                            <option value="" selected>Seleccionar</option>
                            <option value="2485198233605768719">Preparación de la asesoría</option>
                            <option value="2485198233622545936">Desarrollo de reunión técnica</option>
                            <option value="2485198233622545937">Proceso de monitoreo y evaluación</option>
                        </select>
                        <div class="invalid-feedback">
                            <p>Debes especificar un momento de la asesoria.</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="row">
                    <div class="col-md-11">
                        <label for="Fecha planificada">Jefe técnico acompañó terreno:</label>
                        <input id="registro-feedback-fecha-registro-jefe" type="date" class="form-control form-control-sm" name="fecha-inicio">
                    </div>
                    <div class="col-1">
                        <label for="">&nbsp;</label> <br>
                        <a href="#" data-placement="top" data-toggle="popover" data-trigger="hover" data-content='<div class="col-12"> <p>Selecciona está fecha solo si un Jefe Técnico te acompaño a terreno.</p> </div>'>
                            <svg width="1.7em" height="1.7em" viewBox="0 0 16 16" class="bi bi-info-circle-fill float-right text-warning" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412l-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM8 5.5a1 1 0 1 0 0-2 1 1 0 0 0 0 2z" />
                            </svg>
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-12 mt-2">
                <p class="mb-1">Practica abordada:</p>
                <textarea class="form-control" id="registro-feedback-practica-abordada" rows="3" placeholder="Descripción del foco que el supervisor ejecuto."></textarea>
                <div class="invalid-feedback">
                    <p>Debes especificar la practica abordada.</p>
                </div>
            </div>

            <div class="col-12 mt-3">
                <p class="mb-1">Acuerdos sobre aspectos a reforzar por el supervisor:</p>
                <textarea class="form-control" id="registro-feedback-acuerdos" rows="3" placeholder="Descripción del acuerdo con el supervisor."></textarea>
                <div class="invalid-feedback">
                    <p>Debes especificar los acuerdos.</p>
                </div>
            </div>

            <div class="col-12 mt-3">
                <p class="mb-1">Acciones a desarrollar por el supervisor:</p>
                <textarea class="form-control" id="registro-feedback-accion" rows="3" placeholder="Descripción de acciones a desarrollar por el supervisor.."></textarea>
                <div class="invalid-feedback">
                    <p>Debes especificar las acciones a desarrollar.</p>
                </div>
            </div>

            <div class="col-12 mt-3">
                <p class="mb-1">Retroalimentación anterior:</p>
            </div>
            <div class="col-12 table-responsive">
                <table class="table-striped table table-sm mine table-bordered">
                    <thead>
                        <tr>
                            <th>Acuerdos</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>                      
                        [#list feedback.feedbackBefore as fb]
                        <tr>                          
                            <td>
                                ${fb.acuerdo}
                            </td>
                            <td>
                                ${fb.accion}
                            </td>                          
                        </tr>
                        [/#list]
                    </tbody>
                </table>
            </div>
            <div class="col-12 mt-2">
                <p class="mb-1">Comentarios y/o observaciones:</p>
                <textarea class="form-control" id="registro-feedback-observaciones" rows="3" placeholder="Texto libre ..."></textarea>
                <div class="invalid-feedback">
                    <p>Debes especificar un comentario u observacion.</p>
                </div>
            </div>
        </div>

        <div class="row mt-3">
            <div class="col-3">
                <label for="Fecha planificada">Fecha próxima retroaliemtanción:</label>
                <input type="date" class="form-control form-control-sm" id="registro-feedback-fecha-proxima" name="fecha-inicio">
                <div class="invalid-feedback">
                    <p>Debes especificar una fecha.</p>
                </div>
            </div>
        </div>

        <div class="row mt-3">
            <div class="col-12 text-right">
                <button class="btn btn-secondary mr-4 px-4" id="registro-feedback-button-cancelar">Cancelar</button>
                <button class="btn btn-primary px-5" id="registro-feedback-button-guardar" data-feedback-id="${feedback.idFeedback}">Guardar</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${context}/locals/js/feedback/came.feedback.js"></script>
[/@main_page]