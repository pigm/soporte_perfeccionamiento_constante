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
                <li>Detalle retroalimentación </li>
            </ol>
        </div>
    </div>
</div>

<div class="container">
    <div class="vis-des">
        <div class="row">
            <div class="col-8">
                <p>
                    <a id="button-back" href="#">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-left" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z" />
                        </svg> Volver
                    </a>
                </p>
            </div>
            <div class="col-4">
                <button class="btn btn-link float-right px-0 exporta-pdf">
                    <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" class="bi bi-file-earmark-break" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M9 0H4a2 2 0 0 0-2 2v7h1V2a1 1 0 0 1 1-1h5v2.5A1.5 1.5 0 0 0 10.5 5H13v4h1V5L9 0zm5 12h-1v2a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1v-2H2v2a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2v-2zM0 10.5a.5.5 0 0 1 .5-.5h15a.5.5 0 0 1 0 1H.5a.5.5 0 0 1-.5-.5z" />
                    </svg> Exporta acuerdo a PDF
                </button>
            </div>
        </div>
    </div>
</div>

<!-- CONTENIDO ESCRITORIO-->
<div class="container canvas_div_pdf">
    <!-- Contenido escritorio-->
    <div class="vis-des">       
        <div class="">
            <div class="row">
                <div class="col-8">
                    <h2>Detalle retroalimentación</h2>
                    <p class="mb-1 fon-18 mt-2">Reunión de retroalimentación N° <strong>${feedback.numero?c}</strong></p>
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
                            <input type="text" class="form-control form-control-sm" value="${feedback.fechaRegistroSesion?substring(0, 10)}" disabled>
                        </div>
                        <div class="col-md-4 pl-0">
                            <label for="Hora">Hora</label>
                            <input type="text" class="form-control form-control-sm" min="09:00" max="18:00" value="${feedback.fechaRegistroSesion?substring(10)}" disabled>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="row">
                        <div class="col-md-12">
                            <label for="nombrePerfil">Momento de la asesoría</label>
                            [#if feedback.idMomentoAsesoria == "2485198233605768719"]
                            <input type="text" class="form-control form-control-sm" value="Preparación de la asesoría" disabled>
                            [/#if]
                            [#if feedback.idMomentoAsesoria == "2485198233622545936"]
                            <input type="text" class="form-control form-control-sm" value="Desarrollo de reunión técnica" disabled>
                            [/#if]
                            [#if feedback.idMomentoAsesoria == "2485198233622545937"]
                            <input type="text" class="form-control form-control-sm" value="Proceso de monitoreo y evaluación" disabled>
                            [/#if]                            
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="row">
                        <div class="col-md-11">
                            <label for="Fecha planificada">Jefe técnico acompañó terreno:</label>
                            [#if feedback.fechaRegistroJefeTecnico??]
                            <input type="text" class="form-control form-control-sm" value="${feedback.fechaRegistroJefeTecnico?substring(0, 10)}" disabled>
                            [#else]
                            <input type="text" class="form-control form-control-sm" disabled>
                            [/#if]
                        </div>
                        <div class="col-1">
                            <label for="">&nbsp;</label> <br>
                            <a id="button-popover-test" href="#" data-placement="top" data-toggle="popover" data-trigger="hover" data-content='<div class="col-12"> <p>Selecciona está fecha solo si un Jefe Técnico te acompaño a terreno.</p> </div>'>
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
                    <textarea class="form-control" rows="3" placeholder="Descripción del foco que el supervisor ejecuto." disabled>${feedback.practicaAbordada}</textarea>
                </div>

                <div class="col-12 mt-3">
                    <p class="mb-1">Acuerdos sobre aspectos a reforzar por el supervisor:</p>
                    <textarea class="form-control" rows="3" placeholder="Descripción del acuerdo con el supervisor." disabled>${feedback.acuerdos}</textarea>
                </div>

                <div class="col-12 mt-3">
                    <p class="mb-1">Acciones a desarrollar por el supervisor:</p>
                    <textarea class="form-control" rows="3" placeholder="Descripción de acciones a desarrollar por el supervisor.." disabled>${feedback.acciones}</textarea>
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
                    <textarea class="form-control" rows="3" placeholder="Texto libre ..." disabled>${feedback.observaciones}</textarea>
                </div>
            </div>
        </div>        
    </div>
</div>

<div class="container">
    <div class="vis-des">
        <div class="row mt-3">
            <div class="col-12 text-right">
                <button class="btn btn-secondary px-5 mr-4 exporta-pdf-button">
                    <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" class="bi bi-file-earmark-break" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M9 0H4a2 2 0 0 0-2 2v7h1V2a1 1 0 0 1 1-1h5v2.5A1.5 1.5 0 0 0 10.5 5H13v4h1V5L9 0zm5 12h-1v2a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1v-2H2v2a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2v-2zM0 10.5a.5.5 0 0 1 .5-.5h15a.5.5 0 0 1 0 1H.5a.5.5 0 0 1-.5-.5z" />
                    </svg> &nbsp; Exporta acuerdo a PDF
                </button>
                <button id="button-back" class="btn btn-primary px-5">Aceptar</button>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.3/jspdf.min.js"></script>
<script src="https://html2canvas.hertzen.com/dist/html2canvas.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>


<script type="text/javascript" src="${context}/locals/js/feedback/came.feedback.js"></script>
[/@main_page]