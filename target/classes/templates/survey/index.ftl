[#include '*/commons/page-structure.ftl' /]
[@main_page]

<!-- Breadcrumb -->
<div class="container mt-3">
    <div class="row">
        <div class="col-md-6 ">
            <ol class="breadcrumb bg-white p-0">
                Estás&nbsp;en:&nbsp;

                <li><a href="#" class="active"> Incio </a> </li> &nbsp;
                <li> > Encuestas </li>
            </ol>
        </div>
        <div class="col-3"></div>
        <div class="col-md-3 vis-des">
            <button id="button-nueva-encuesta" class="btn btn-sm btn-success float-right px-4">
                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-plus-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                    <path fill-rule="evenodd" d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
                </svg> &nbsp; Crear encuesta
            </button>
        </div>

    </div>
</div>

<div id="panel-search" class="vis-des">
    <!-- CONTENIDO ESCRITORIO-->
    <div class="container px-3">
        <div class="row">

            <!-- Contenido -->
            <div class="col-md-12">
                <h2>Encuesta <span class="fon-18 text-rea">- Proceso ${currentYear?c}</span></h2>
            </div>

            <!--Jefe Técnico-->
            <div class="col-md-3">
                <div class="form-group">
                    <label for="nombrePerfil">Perfil</label>
                    <select id="selector-perfil" placeholder="seleciona perfil" class="custom-select custom-select-sm">
                        <option value="" selected>Seleccionar</option>
                        [#list profiles as pf]
                        <option value="${pf.value}">${pf.displayText}</option>
                        [/#list]
                    </select>
                </div>
            </div>
            <!--Región-->
            <!-- <div class="col-md-3">
                <div class="form-group">
                    <label for="nombrePerfil">Región</label>
                    <select id="selector-region" class="custom-select custom-select-sm">
                        <option value="" selected>Seleccionar</option>         
                        [#list regiones as r]
                        <option value="${r.value}">${r.displayText}</option>
                        [/#list]
                    </select>
                </div>
            </div> -->
            <!--Deprov-->
            <!-- <div class="col-md-3">
                <form>
                    <div class="form-group">
                        <label for="nombrePerfil">Deprov</label>
                        <select id="selector-deprov" class="custom-select custom-select-sm">
                            <option value="" selected>Seleccionar</option>
                        </select>
                    </div>
                </form>
            </div> -->
            <!--Semestre-->
            <div class="col-md-3">
                <div class="form-group">
                    <label for="nombrePerfil">Estado</label>
                    <select id="selector-estado" class="custom-select custom-select-sm">
                        <option value="" selected>Seleccionar</option>
                        <option value="true">Habilitado</option>
                        <option value="false">Deshabilitado</option>
                    </select>
                </div>
            </div>

            <div class="col-md-9"></div>

            <div class="col-md-3">
                <a id="button-search" href="#" class="btn btn-primary btn-sm float-right px-4 mt-2">
                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
                        <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
                    </svg> &nbsp; Buscar</a>
            </div>
        </div>
    </div>
</div>

<div id="panel-search-result" class="vis-des" style="display: none;">
    <div class="container px-3">

        <div class="row mt-1">
            <!-- Contenido -->
            <div class="col-md-12">
                <h2>Encuesta <span class="fon-18 text-rea">- Proceso ${currentYear?c}</span></h2>
            </div>

            <div class="col-md-6 mt-2">
                Resultado de la búsqueda (<strong id="count-survey"></strong> encuestas)
            </div>

            <div class="col-6">               
                <a id="survey-find-again" href="#" class="btn btn-link float-right px-0 mr-4">
                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
                        <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
                    </svg> Buscar nuevamente
                </a>
            </div>

            <div class="table-responsive col-12">
                <table id="table-survey" class="table mine table-hover table-bordered table-sm">
                    <thead>
                        <tr>
                            <th scope="col">Nombre encuesta</th>
                            <th scope="col">Destinatario</th>
                            <th scope="col">Fecha inicio</th>
                            <th scope="col">Fecha fin</th>
                            <th scope="col">Habilitar</th>
                        </tr>
                    </thead>
                    <tbody class=" table-sm">                        
                    </tbody>
                </table>
            </div>
        </div>
    </div>    
</div>

<!-- CREAR ENCUESTA -->
<div class="modal fade" id="modal-crear-encuesta" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Encuesta</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <div class="row">
                    <!--Nombre del documento-->
                    <div class="col-12">
                        <p class="mb-2">Proceso: <strong>${currentYear?c}</strong></p>
                        <div class="form-group mb-1">
                            <label for="nombreDoc">Nombre de la encuesta</label>
                            <input id="survey-nombre" type="text" class="form-control form-control-sm" aria-describedby="nombreDoc">
                        </div>
                    </div>

                    <!--Seleccionar perfil-->
                    <div class="col-12">
                        <form>
                            <div class="control-group">
                                <label for="select-add-perfil">Seleciona los perfiles que podrán ver la encuesta</label>
                                <select id="select-add-perfil" multiple name="state[]" class="demo-default" placeholder="Seleciona perfiles" style="width:100%">
                                    [#list profiles as pf]
                                    <option value="${pf.value}">${pf.displayText}</option>
                                    [/#list]
                                </select>
                                <div class="invalid-feedback">
                                    <p>Debe seleccionar a lo menos un perfil.</p>
                                </div>
                            </div>
                        </form>
                    </div>

                    <!--Fecha inicio-->
                    <div class="col-6">
                        <label for="Fecha planificada">Fecha inicio:</label>
                        <input id="survey-fecha-inicio" type="date" class="form-control form-control-sm" id="fecha-inicio" name="fecha-inicio" date-format="DD-MM-YYYY">
                    </div>

                    <!--Fecha fin-->
                    <div class="col-6">
                        <label for="Fecha planificada">Fecha fin:</label>
                        <input id="survey-fecha-fin" type="date" class="form-control form-control-sm" id="fecha-inicio" name="fecha-inicio">
                    </div>
                </div>

                <div id="survey-questions-list" class="row mt-4">

                    <!--Pregunta 1-->
                    <div class="col-12">
                        <div class="form-group mb-0">
                            <label class="mb-0 question-title" for="">Pregunta 1</label>
                            <input type="text" class="form-control form-control-sm question" aria-describedby="nombreDoc" maxlength="150">
                            <div class="invalid-feedback">
                                <p>Debe ingresar la pregunta.</p>
                            </div>
                        </div>
                    </div>
                    
                    <!--Pregunta 2-->
                    <div class="col-12">
                        <div class="form-group mb-0">
                            <label class="mb-0 question-title" for="">Pregunta 2</label>
                            <input type="text" class="form-control form-control-sm question" aria-describedby="nombreDoc" maxlength="150">
                            <div class="invalid-feedback">
                                <p>Debe ingresar la pregunta.</p>
                            </div>
                        </div>
                    </div>
                    
                    <!--Pregunta 3-->
                    <div class="col-12">
                        <div class="form-group mb-0">
                            <label class="mb-0 question-title" for="">Pregunta 3</label>
                            <input type="text" class="form-control form-control-sm question" aria-describedby="nombreDoc" maxlength="150">
                            <div class="invalid-feedback">
                                <p>Debe ingresar la pregunta.</p>
                            </div>
                        </div>
                    </div>                    
                </div>

                <div class="row">
                    <!--Agregar pregunta-->
                    <div class="col-12">
                        <button id="button-add-question" class="btn btn-link float-right p-0">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-square" viewBox="0 0 16 16">
                                <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z" />
                                <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
                            </svg> Agregar pregunta
                        </button>
                    </div>

                    <!--Mensaje aviso-->
                    <div class="col-12 mt-3">
                        <div class="alert alert-primary fon-14" role="alert">
                            Puedes agregar hasta 15 preguntas, y si seleccionas la casilla “comentarios y/o observaciones” se habilitara en la encuesta un campo de texto libre de hasta 150 caracteres.
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="survey-permite-observacion">
                            <label class="form-check-label" for="survey-permite-observacion">
                                Permitir comentarios y/o observaciones
                            </label>
                        </div>
                    </div>
                </div>


            </div>

            <div class="modal-footer">
                <button type="button" id="button-survey-cancel" class="btn btn-secondary">Cancelar</button>
                <button type="button" id="button-survey-save" class="btn btn-primary">Enviar</button>
            </div>
        </div>
    </div>
</div>

<script>
    var $select = $('#select-add-perfil').selectize({
        create: true
    });
</script>

[#include './survey.ftl' /]

<script type="text/javascript" src="${context}/locals/js/survey/came.survey.js"></script>
[/@main_page]