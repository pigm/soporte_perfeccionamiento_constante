[#include '*/commons/page-structure.ftl' /] [@main_page]
        <!-- Breadcrumb -->
        <div class="container mt-3">
            <div class="row">
                <div class="col-6 ">
                    <ol class="breadcrumb bg-white p-0">
                        Estás&nbsp;en:&nbsp;

                        <li><a href="#" class="active"> Incio </a> </li> &nbsp;
                        <li> > Mensajería</li>
                    </ol>
                </div>

                <div class="col-6">
                    <button id="button-new-message" class="btn btn-primary float-right btn-sm">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-plus-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                            <path fill-rule="evenodd" d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
                        </svg> &nbsp; Crear mensaje
                    </button>
                </div>
            </div>
        </div>

        <!-- CONTENIDO ESCRITORIO-->
        <div class="container">
            <div class="row">
                <!-- Menú segundario -->


                <!-- Contenido -->
                <div class="col-md-12">
                    <h2>Mensajería</h2>
                    <p></p>
                </div>

                <div class="col-12">
                    <a class="btn btn-primary btn-sm float-right px-4" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-down-fill" viewBox="0 0 16 16">
                            <path d="M7.247 11.14L2.451 5.658C1.885 5.013 2.345 4 3.204 4h9.592a1 1 0 0 1 .753 1.659l-4.796 5.48a1 1 0 0 1-1.506 0z" />
                        </svg> &nbsp; Filtros
                    </a>
                </div>

                <div class="collapse col-12" id="collapseExample">
                    <div class="row">
                        <div class="col-4">
                            <div class="form-group">
                                <label class="mb-1" for="Perfil">Perfil</label>
                                <select id="message-selector-filter-perfil" class="custom-select custom-select-sm">
                                    <option value="" selected>Seleccionar</option>
                                    [#list perfiles as p]
                                    <option value="${p.value}">${p.displayText}</option>
                                    [/#list]
                                </select>
                            </div>
                        </div>

                        <div class="col-4">
                            <div class="form-group">
                                <label class="mb-1" for="Región">Región</label>
                                <select id="message-selector-filter-region" class="custom-select custom-select-sm">
                                    <option value="" selected>Seleccionar</option>
                                    [#list regiones as r]
                                    <option value="${r.value}">${r.displayText}</option>
                                    [/#list]
                                </select>
                            </div>
                        </div>

                        <div class="col-4">
                            <div class="form-group">
                                <label class="mb-1" for="Deproc">Deprov</label>
                                <select id="message-selector-filter-deprov" class="custom-select custom-select-sm">
                                    <option value="" selected>Seleccionar</option>                                   
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-12 mt-1 table-responsive">
                    <table id="table-mensajeria" class="table mine table-hover table-bordered table-sm">
                        <thead>
                            <tr>
                                <th scope="col">Asunto</th>
                                <th scope="col">Perfil</th>
                                <th scope="col">Región</th>
                                <th scope="col">Deprov</th>
                                <th scope="col">Destinatario</th>
                                <th scope="col">Fecha envío</th>
                                <th scope="col">Fecha leído</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- CREAR MENSAJE -->
    <div class="modal fade" id="modal-new-message" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Enviar nuevo mensaje</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <div class="modal-body">
                    <div class="row">
                        <div class="col-12">
                            <p>Proceso: <b>año ${anio}</b></p>
                        </div>

                        <!--PERFILES-->
                        <div class="col-12">
                            <div class="control-group">
                                <label class="mb-1" for="select-state">Para</label>
                                <select id="message-selector-perfil" multiple name="state[]" class="demo-default " placeholder="Busca o seleciona perfiles." style="width:100%">
                                    [#list perfiles as p]
                                    <option value="${p.value}">${p.displayText}</option>
                                    [/#list]
                                </select>
                            </div>
                        </div>

                        <!--REGIÓN-->
                        <div class="col-12">
                            <div class="control-group">
                                <label class="mb-1" for="region">Región</label>
                                <select id="message-selector-region" multiple name="state[]" class="demo-default" placeholder="Busca o seleciona regiones." style="width:100%;">
                                    [#list regiones as r]
                                    <option value="${r.value}">${r.displayText}</option>
                                    [/#list]
                                </select>
                            </div>
                        </div>

                        <!--DEPROV-->
                        <div class="col-12">
                            <div id="contenedorsd" class="control-group">
                                <label class="mb-1" for="select-state2">Deprov (Opcional)</label>
                                    <select id="selector-deprov" multiple name="state[]" class="demo-default" placeholder="Busca o seleciona provincias." style="width:100%;"> 
                                    </select>
                            </div>
                        </div>

                        <!--ASUNTO-->
                        <div class="col-12">
                            <div class="control-group">
                                <label class="mb-1" for="Asunto">Asunto</label>
                                <input id="asunto-mensaje" class="form-control" placeholder="Ingresar asunto del mensaje.">
                            </div>
                        </div>

                        <div class="col-12 mt-2">
                            <label class="mb-1" for="Mensaje">Mensaje</label>
                            <textarea class="form-control" id="contenido-mensaje" rows="7" placeholder="Ingresa tu mensaje aquí."></textarea>
                            <label for="Mensaje" class="text-muted float-right mt-1">Máximo 5000 caracteres. <strong id="contenido-mensaje-count">5000</strong> Restantes</label>
                        </div>


                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                    <button id="message-button-guardar" type="button" class="btn btn-primary">Enviar mensaje</button>
                </div>
            </div>
        </div>
    </div>

    <!-- VER MENSAJE 1-->
    <div id="modal-detail-message"  class="modal fade" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="message-title"></h5>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-12">
                            <div class="form-control" id="body-message"></div>  
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <div class="col-12 text-center">
                        <button type="button" class="btn btn-primary px-4" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
<script>
        var eventHandler = function(name) {
            return function() {
                console.log(name, arguments);
                $('#log').append('<div><span class="name">' + name + '</span></div>');
            };
        };
        var $select1 = $('#message-selector-perfil').selectize({
            create: true           
        });

        var $select2 = $('#message-selector-region').selectize({
            create: true           
        });
        
        $('#selector-deprov').selectize({
            create: true            
        });
        
        $(".selectize-input input[placeholder]").attr("style", "width: 100%;");
</script>
<script type="text/javascript" src="${context}/locals/js/messenger/came.messenger.js"></script> 
[/@main_page]
