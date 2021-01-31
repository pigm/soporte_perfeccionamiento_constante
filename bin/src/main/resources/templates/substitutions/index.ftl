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
                <div class="col-12">
                    <h2>Suplencias</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <p>¿Un supervisor se va a ausentar por un tiempo determinado? Aquí se puede asignar su
                        reemplazante, delegando sus asignaciones mientras no esté.</p>
                </div>
                <div class="col-6">
                    
                </div>
                <div class="col-6">
                    <button class="btn btn-primary col-6 float-right" data-toggle="modal"
                        data-target="#suplencias">Agregar suplencia</button>
                </div>
                <div class="col-12 mt-4">
                    <div class="table-responsive">
                        <table class="table mine table-hover" id="tabla-suplencia">
                            <thead>
                                <tr class="table-sm">
                                    <th class="text-center border-suplencia" colspan="3">Supervisor ausentado
                                    </th>
                                    <th class="text-center bg-suplencia" colspan="3">Supervisor suplente</th>
                                </tr>
                                <tr>
                                    <th scope="col">Apellidos</th>
                                    <th scope="col">Nombres</th>
                                    <th scope="col">RUN</th>
                                    <th scope="col" class="bg-suplencia">Apellidos</th>
                                    <th scope="col" class="bg-suplencia">Nombres</th>
                                    <th scope="col" class="bg-suplencia">RUN</th>
                                    <th scope="col">Inicio</th>
                                    <th scope="col">Término</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody class="table-bordered table-sm">
                                [#list suplencias as ss]
                                <tr>
                                    <td>
                                        ${ss.apellidosSA} <br/>
                                    </td>
                                    <td>
                                        ${ss.nombresSA}
                                    </td>
                                    <td>
                                        ${ss.runSA}
                                    </td>
                                    <td>
                                        ${ss.apellidosSS}
                                    </td>
                                    <td>
                                        ${ss.nombresSS}
                                    </td>
                                    <td>
                                        ${ss.runSS}
                                    </td>
                                    <td>
                                        ${ss.startDate?date}
                                    </td>
                                    <td>
                                        ${ss.endDate?date}
                                    </td>
                                    <td class="text-center">
                                        <a href="#" class="editSuplencia" data-idSupervisorSuplencia="${ss.idSupervisorSuplencia}" data-userNameSA="${ss.userNameSA}" data-userNameSS="${ss.userNameSS}" data-startDate="${ss.startDate?date}" data-endDate="${ss.endDate?date}">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z" />
                                            </svg>
                                        </a>
                                    </td>
                                </tr>
                                [/#list]      
                            </tbody>
                        </table>


                    </div>

                </div>
            </div>
        </div>


        <!-- Contenido celular -->
        <div class="col-12 vis-cel mb-5">
            <div class="row">
                <div class="col-12">
                    <h2>Suplencias</h2>
                </div>
            </div>
            <div class="row mt-4">
                <div class="col-12">
                    <p>¿Un supervisor se va a ausentar por un tiempo determinado? Aquí se puede asignar su
                        reemplazante, delegando sus asignaciones mientras no esté.</p>
                </div>
                <div class="col-12 mb-3">
                    <button class="btn btn-primary col-6 float-right" data-toggle="modal"
                        data-target="#suplencias">Agregar suplencia</button>
                </div>

                <div class="col-12">
                    <input class="form-control col-md-5" type="search" placeholder="Buscador" aria-label="Search">
                </div>

                <div class="col-12 mt-3">
                    <div class="table-responsive">
                        <table class="table mine table-hover" id="tabla-suplencia">
                            <thead>
                                <tr class="table-sm">
                                    <th class="text-center border-suplencia" colspan="3">Supervisor ausentado
                                    </th>
                                    <th class="text-center bg-suplencia" colspan="3">Supervisor suplente</th>
                                </tr>
                                <tr>
                                    <th scope="col">Apellidos</th>
                                    <th scope="col">Nombres</th>
                                    <th scope="col">RUN</th>
                                    <th scope="col" class="bg-suplencia">Apellidos</th>
                                    <th scope="col" class="bg-suplencia">Nombres</th>
                                    <th scope="col" class="bg-suplencia">RUN</th>
                                    <th scope="col">Inicio</th>
                                    <th scope="col">Término</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody class="table-bordered table-sm">
                                
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal Suplencia -->
<div class="modal fade" id="suplencias" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Nueva suplencia</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-10">
                        <div class="row">
                            <div class="form-group col-12 mb-0">
                                <label for="nombrePerfil">Supervisor a ausentarse</label>
                                <input id="userNameSA" type="text" class="form-control" aria-describedby="nombrePerfil">
                                <label class="text-muted">Comienza a escribir el nombre del usuario y seleccione el
                                    correspondiente de la lista que aparecerá.</label>
                            </div>
                            <!--NOMBRE-->
                            <div class="col-4">User Dominio</div>
                            <div class="col-8">: <b><span id="domainUserNameSA"></span></b></div>
                            <!--NOMBRE-->
                            <div class="col-4">Nombre</div>
                            <div class="col-8">: <b><span id="nombreSA"></span></b></div>
                            <!--PERFIL-->
                            <div class="col-4">Perfil</div>
                            <div class="col-8">: <b><span id="perfilSA"></span></b></div>
                            <!--REGIÓN-->
                            <div class="col-4">Región</div>
                            <div class="col-8">: <b><span id="regionSA"></span></b></div>
                            <!--DEPROV-->
                            <div class="col-4">Deprov</div>
                            <div class="col-8">: <b><span id="deprovSA"></span></b></div>
                        </div>
                    </div>
                    <div class="col-10 mt-4">
                        <div class="row">
                            <div class="form-group col-12 mb-0">
                                <label for="nombrePerfil">Supervisor suplente</label>
                                <input id="userNameSS" type="text" class="form-control" aria-describedby="nombrePerfil">
                                <label class="text-muted">Comienza a escribir el nombre del usuario y seleccione el
                                    correspondiente de la lista que aparecerá.</label>
                            </div>
                           <!--NOMBRE-->
                           <div class="col-4">User Dominio</div>
                           <div class="col-8">: <b><span id="domainUserNameSS"></span></b></div>
                           <!--NOMBRE-->
                           <div class="col-4">Nombre</div>
                           <div class="col-8">: <b><span id="nombreSS"></span></b></div>
                           <!--PERFIL-->
                           <div class="col-4">Perfil</div>
                           <div class="col-8">: <b><span id="perfilSS"></span></b></div>
                           <!--REGIÓN-->
                           <div class="col-4">Región</div>
                           <div class="col-8">: <b><span id="regionSS"></span></b></div>
                           <!--DEPROV-->
                           <div class="col-4">Deprov</div>
                           <div class="col-8">: <b><span id="deprovSS"></span></b></div>
                        </div>
                    </div>

                    <div class="col-12 mt-4">
                        <form>
                            <div>
                                <label for="fecha-inicio">Inicio de la suplencia</label> <br>
                                <input type="date" class="form-control col-md-6" id="fecha-inicio" name="fecha-inicio">
                                <div class="invalid-feedback">
                                    <p>Debe especificar una fecha de inicio valida.</p>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-12 mt-4">
                        <form>
                            <div>
                                <label for="fecha-termino">Término de la suplencia</label> <br>
                                <input type="date" class="form-control col-md-6" id="fecha-termino" name="fecha-termino">
                                <div class="invalid-feedback">
                                    <p>Debe especificar una fecha de termino valida.</p>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer mt-2">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button id="guardarSuplencia" type="button" class="btn btn-primary">Guardar</button>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript"	src="${context}/locals/js/substitutions/came.substitutions.js"></script>

<style>
    .ui-autocomplete {
        z-index: 9999;
        }
</style>
[/@main_page]