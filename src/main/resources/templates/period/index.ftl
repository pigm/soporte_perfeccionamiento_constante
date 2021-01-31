[#include '*/commons/page-structure.ftl' /]
[@main_page]

<!-- Breadcrumb -->
<div class="container mt-3 px-0">
    <div class="row">
        <div class="col-6">
            <ol class="breadcrumb bg-white p-0 ml-4">
                Estás&nbsp;en:&nbsp;
                <li> Administración /</li> &nbsp;
                <li><a href="#" class="active">Periodo</a></li> &nbsp;
            </ol>
        </div>
    </div>
</div>

<!-- CONTENIDO -->
<div class="container px-3">
    <div class="row">
        <!-- Menú segundario -->
        [#include '../shared/menu-admin.ftl' /]

        <!-- Contenido escritorio-->
       
        <div id="withoutPeriod" class="col-md-9">
            <div id="withoutPeriod-step1" class="col-md-12 vis-des">
                <div class="row">
                    <div class="col-12 px-0">
                        <form class="form-inline">
                            <h2 class="my-1 mr-2" for="inlineFormCustomSelectPref">Periodo</h2>
                            <select id="selector-periodo-year" class="custom-select my-1 mr-sm-2">
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
                </div>
                <div class="col-12 text-center mt-5">
                    <svg width="5em" height="5em" viewBox="0 0 16 16" class="bi bi-emoji-frown" fill="currentColor"
                        xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd"
                            d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                        <path fill-rule="evenodd"
                            d="M4.285 12.433a.5.5 0 0 0 .683-.183A3.498 3.498 0 0 1 8 10.5c1.295 0 2.426.703 3.032 1.75a.5.5 0 0 0 .866-.5A4.498 4.498 0 0 0 8 9.5a4.5 4.5 0 0 0-3.898 2.25.5.5 0 0 0 .183.683z" />
                        <path
                            d="M7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zm4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5z" />
                    </svg>
                    <p class="mt-2">Período <span class="period-current-year"></span> aún no ha sido planificado</p>
                    <button type="button" class="btn btn-primary" id="btnPlanificarPeriodo">Planificar</button>

                </div>
            </div>

            <!-- Contenido escritorio-->
            <div id="withoutPeriod-step2" class="col-md-12 vis-des" style="display: none;">
                <div class="row">
                    <div class="col-12">
                        <h2>Planificar año <span class="period-current-year"></span></h2>
                    </div>
                </div>

                <!--DOCUMENTOS REGIONALES-->
                <div class="col-12 px-0">
                    <div class="card mt-2">
                        <div class="card-header">
                            Documentos regionales
                        </div>
                        <div class="card-body row">
                            <div class="col-4 ">
                                <div>
                                    <label for="fecha-inicio">Fecha inicio</label> <br>
                                    <input type="date" class="form-control" id="fecha-inicio-regional" name="fecha-inicio">        
                                    <div class="invalid-feedback">
                                        <p>Debe especificar una fecha valida.</p>
                                    </div>                            
                                </div>

                                <div class="mt-3">
                                    <label for="fecha-fin">Fecha fin</label> <br>
                                    <input type="date" class="form-control" id="fecha-fin-regional" name="fecha-fin">
                                    <div class="invalid-feedback">
                                        <p>Debe especificar una fecha valida.</p>
                                    </div>                                   
                                </div>
                            </div>

                            <div class="col-8">
                                <form>
                                    <div class="form-group files">
                                        <label>Plantilla formato Word (hasta 10 MB) </label>
                                        <input type="file" class="form-control" id="documento-regional" multiple="">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <!--DOCUMENTOS PROVINCIALES -->
                <div class="col-12 px-0 mt-4">
                    <div class="card mt-2">
                        <div class="card-header">
                            Documentos provinciales
                        </div>
                        <div class="card-body row">
                            <div class="col-4 ">
                                <form>
                                    <div>
                                        <label for="fecha-inicio">Fecha inicio</label> <br>
                                        <input type="date" class="form-control" id="fecha-inicio-provincial"
                                            name="fecha-inicio">
                                        <div class="invalid-feedback">
                                            <p>Debe especificar una fecha valida.</p>
                                        </div>
                                    </div>

                                    <div class="mt-3">
                                        <label for="fecha-fin">Fecha fin</label> <br>
                                        <input type="date" class="form-control" id="fecha-fin-provincial"
                                            name="fecha-fin">
                                        <div class="invalid-feedback">
                                            <p>Debe especificar una fecha valida.</p>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div class="col-8">
                                <form method="post" action="#" id="#">
                                    <div class="form-group files">
                                        <label>Plantilla formato Word (hasta 10 MB) </label>
                                        <input type="file" class="form-control" multiple="" id="documento-provincial">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <!--Conformación de redes-->
                <div class="col-12 px-0 mt-4">
                    <div class="card mt-2">
                        <div class="card-header">
                            Conformación de redes
                        </div>
                        <div class="card-body row">
                            <div class="col-4 ">
                                <form>
                                    <div>
                                        <label for="fecha-inicio">Fecha inicio</label> <br>
                                        <input type="date" class="form-control" id="fecha-inicio-redes"
                                            name="fecha-inicio">
                                        <div class="invalid-feedback">
                                            <p>Debe especificar una fecha valida.</p>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div class="col-4">
                                <form method="post" action="#" id="#">
                                    <div>
                                        <label for="fecha-fin">Fecha fin</label> <br>
                                        <input type="date" class="form-control" id="fecha-fin-redes" name="fecha-fin">
                                        <div class="invalid-feedback">
                                            <p>Debe especificar una fecha valida.</p>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <!--Asignación de supervisores-->
                <div class="col-12 px-0 mt-4">
                    <div class="card mt-2">
                        <div class="card-header">
                            Asignación de supervisores
                        </div>
                        <div class="card-body row">
                            <div class="col-4 ">
                                <form>
                                    <div>
                                        <label for="fecha-inicio">Fecha inicio</label> <br>
                                        <input type="date" class="form-control" id="fecha-inicio-supervisores"
                                            name="fecha-inicio">
                                        <div class="invalid-feedback">
                                            <p>Debe especificar una fecha valida.</p>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div class="col-4">
                                <form method="post" action="#" id="#">
                                    <div>
                                        <label for="fecha-fin">Fecha fin</label> <br>
                                        <input type="date" class="form-control" id="fecha-fin-supervisores"
                                            name="fecha-fin">
                                        <div class="invalid-feedback">
                                            <p>Debe especificar una fecha valida.</p>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!--Planificación e implemetanción del apoyo-->
                <div class="col-12 px-0 mt-4">
                    <div class="card mt-2">
                        <div class="card-header">
                            Planificación e implementanción del apoyo
                        </div>
                        <div class="card-body row">
                            <div class="col-4 ">
                                <form>
                                    <div>
                                        <label for="fecha-inicio">Fecha inicio</label> <br>
                                        <input type="date" class="form-control" id="fecha-inicio-apoyo"
                                            name="fecha-inicio">
                                        <div class="invalid-feedback">
                                            <p>Debe especificar una fecha valida.</p>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div class="col-4">
                                <form method="post" action="#" id="#">
                                    <div>
                                        <label for="fecha-fin">Fecha fin</label> <br>
                                        <input type="date" class="form-control" id="fecha-fin-apoyo" name="fecha-fin">
                                        <div class="invalid-feedback">
                                            <p>Debe especificar una fecha valida.</p>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- BOTONES -->
                <div class="col-12 mt-4 px-0">
                    <button class="btn btn-primary col-2 float-right" id="buttonSetPeriod">Guardar</button>
                    <button class="btn btn-secondary float-right mr-4" id="buttonCancelarPeriod">Cancelar</button>
                </div>
            </div>
        </div>

        <div id="withPeriod" class="col-md-9" style="display: none;">
            [#include './periodo-ver.ftl' /]
        </div>        

    </div>
</div>


<script type="text/javascript" src="${context}/locals/js/period/came.period.js"></script>
[/@main_page]