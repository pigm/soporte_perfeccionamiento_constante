<div class="col-md-12 vis-des">  
    
        <div class="col-12 px-0">
            <form class="form-inline">
                <h2 class="my-1 mr-2" for="inlineFormCustomSelectPref">Periodo</h2>
                <select id="selector-periodo-year-2" class="custom-select my-1 mr-sm-2">
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
    
    <!--DOCUMENTOS REGIONALES-->
    <div class="col-12 px-0">
        <div class="card mt-2">
            <div class="card-header">
                Documentos regionales
                <a class="btn btn-link float-right p-0"
                href="" id="btn_documento_regional_file"
											target="_blank">
                    <svg width="0.9em" height="0.9em" viewBox="0 0 16 16" class="bi bi-file-arrow-down"
                        fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd"
                            d="M4 0h8a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2zm0 1a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H4z" />
                        <path fill-rule="evenodd"
                            d="M8 5a.5.5 0 0 1 .5.5v3.793l1.146-1.147a.5.5 0 0 1 .708.708l-2 2a.5.5 0 0 1-.708 0l-2-2a.5.5 0 1 1 .708-.708L7.5 9.293V5.5A.5.5 0 0 1 8 5z" />
                    </svg> Descargar plantilla Word
                </a>
            </div>
            <div class="card-body row">
                <div class="col-3">
                    <p><span id="regionales-fecha-inicio"></span> <br> <small>Fecha inicio</small></p>
                </div>
                <div class="col-3">
                    <p><span id="regionales-fecha-fin"></span> <br> <small>Fecha fin</small></p>
                </div>
                <div class="col-6">
                    <button class="btn btn-secondary btn-sm float-right create-special-case" data-modulo="Documentos regionales" data-moduloId="0" data-has-template="true">Crear caso especial</button>
                </div>

                <div class="col-12">
                    <p>
                        <a class="btn-link" data-toggle="collapse" href="#collapseExample" role="button"
                            aria-expanded="false" aria-controls="collapseExample"> Casos especiales (<span id="special-case-regional-count"></span>)
                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-caret-down-fill"
                                fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path
                                    d="M7.247 11.14L2.451 5.658C1.885 5.013 2.345 4 3.204 4h9.592a1 1 0 0 1 .753 1.659l-4.796 5.48a1 1 0 0 1-1.506 0z" />
                            </svg></a>
                    </p>

                    <div class="collapse" id="collapseExample">
                        <div class="table-responsive-sm col-12 px-0">
                            <table class="table mine table-hover" id="table-documentos-regionales">
                                <thead>
                                    <tr>
                                        <th scope="col">Región</th>
                                        <th scope="col">Deprov</th>
                                        <th scope="col">Creado</th>
                                        <th scope="col">Usuario</th>
                                        <th scope="col">Inicio</th>
                                        <th scope="col">Fin</th>                                        
                                        <th scope="col">Motivo</th>
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


    <!--DOCUMENTOS PROVINCIALES-->
    <div class="col-12 px-0 mt-4">
        <div class="card mt-2">
            <div class="card-header">
                Documentos provinciales
                <a class="btn btn-link float-right p-0"
                href="" id="btn_documento_provinciales_file">
                    <svg width="0.9em" height="0.9em" viewBox="0 0 16 16" class="bi bi-file-arrow-down"
                        fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd"
                            d="M4 0h8a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2zm0 1a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H4z" />
                        <path fill-rule="evenodd"
                            d="M8 5a.5.5 0 0 1 .5.5v3.793l1.146-1.147a.5.5 0 0 1 .708.708l-2 2a.5.5 0 0 1-.708 0l-2-2a.5.5 0 1 1 .708-.708L7.5 9.293V5.5A.5.5 0 0 1 8 5z" />
                    </svg> Descargar plantilla Word
                </a>
            </div>
            <div class="card-body row">

                <div class="col-3">
                    <p><span id="provinciales-fecha-inicio"></span> <br> <small>Fecha inicio</small></p>
                </div>
                <div class="col-3">
                    <p><span id="provinciales-fecha-fin"></span> <br> <small>Fecha fin</small></p>
                </div>
                <div class="col-6">
                    <button class="btn btn-secondary btn-sm float-right create-special-case" data-modulo="Documentos provinciales" data-moduloId="1" data-has-template="true">Crear caso especial</button>
                </div>

                <div class="col-12">
                    <p>
                        <a class="btn-link" data-toggle="collapse" href="#collapseExample1" role="button"
                            aria-expanded="false" aria-controls="collapseExample"> Casos especiales (<span id="special-case-provinciales-count"></span>)
                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-caret-down-fill"
                                fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path
                                    d="M7.247 11.14L2.451 5.658C1.885 5.013 2.345 4 3.204 4h9.592a1 1 0 0 1 .753 1.659l-4.796 5.48a1 1 0 0 1-1.506 0z" />
                            </svg></a>
                    </p>

                    <div class="collapse" id="collapseExample1">
                        <div class="table-responsive-sm col-12 px-0">
                            <table class="table mine table-hover" id="table-documentos-provinciales">
                                <thead>
                                    <tr>
                                        <th scope="col">Región</th>
                                        <th scope="col">Deprov</th>
                                        <th scope="col">Creado</th>
                                        <th scope="col">Usuario</th>
                                        <th scope="col">Inicio</th>
                                        <th scope="col">Fin</th>
                                        <th scope="col">Motivo</th>
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

    <!--CONFORMACIÓN DE REDES-->
    <div class="col-12 px-0 mt-4">
        <div class="card mt-2">
            <div class="card-header">
                conformación de redes y asesorías directas
            </div>
            <div class="card-body row">
                <div class="col-3">
                    <p><span id="redes-fecha-inicio"></span> <br> <small>Fecha inicio</small></p>
                </div>
                <div class="col-3">
                    <p><span id="redes-fecha-fin"></span> <br> <small>Fecha fin</small></p>
                </div>
                <div class="col-6">
                    <button class="btn btn-secondary btn-sm float-right create-special-case" data-modulo="Conformación de redes" data-moduloId="2" data-has-template="false">Crear caso especial</button>
                </div>

                <div class="col-12">
                    <p>
                        <a class="btn-link" data-toggle="collapse" href="#collapseExample2" role="button"
                            aria-expanded="false" aria-controls="collapseExample"> Casos especiales (<span id="special-case-redes-count"></span>)
                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-caret-down-fill"
                                fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path
                                    d="M7.247 11.14L2.451 5.658C1.885 5.013 2.345 4 3.204 4h9.592a1 1 0 0 1 .753 1.659l-4.796 5.48a1 1 0 0 1-1.506 0z" />
                            </svg></a>
                    </p>

                    <div class="collapse" id="collapseExample2">
                        <div class="table-responsive-sm col-12 px-0">
                            <table class="table mine table-hover" id="table-specials-cases-redes">
                                <thead>
                                    <tr>
                                        <th scope="col">Región</th>
                                        <th scope="col">Deprov</th>
                                        <th scope="col">Creado</th>
                                        <th scope="col">Usuario</th>
                                        <th scope="col">Inicio</th>
                                        <th scope="col">Fin</th>
                                        <th scope="col">Motivo</th>
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

    <!--ASIGNACIÓN DE SUPERVISORES-->
    <div class="col-12 px-0 mt-4">
        <div class="card mt-2">
            <div class="card-header">
                Asignación de supervisores
            </div>
            <div class="card-body row">
                <div class="col-3">
                    <p><span id="supervisores-fecha-inicio"></span> <br> <small>Fecha inicio</small></p>
                </div>
                <div class="col-3">
                    <p><span id="supervisores-fecha-fin"></span> <br> <small>Fecha fin</small></p>
                </div>
                <div class="col-6">
                    <button class="btn btn-secondary btn-sm float-right create-special-case" data-modulo="Asignación de supervisores" data-moduloId="3" data-has-template="false">Crear caso especial</button>
                </div>

                <div class="col-12">
                    <p>
                        <a class="btn-link" data-toggle="collapse" href="#collapseExample3" role="button"
                            aria-expanded="false" aria-controls="collapseExample"> Casos especiales (<span id="special-case-supervisores-count"></span>)
                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-caret-down-fill"
                                fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path
                                    d="M7.247 11.14L2.451 5.658C1.885 5.013 2.345 4 3.204 4h9.592a1 1 0 0 1 .753 1.659l-4.796 5.48a1 1 0 0 1-1.506 0z" />
                            </svg></a>
                    </p>

                    <div class="collapse" id="collapseExample3">
                        <table class="table mine table-hover" id="table-specials-cases-supervisores">
                            <thead>
                                <tr>
                                    <th scope="col">Región</th>
                                    <th scope="col">Deprov</th>
                                    <th scope="col">Creado</th>
                                    <th scope="col">Usuario</th>
                                    <th scope="col">Inicio</th>
                                    <th scope="col">Fin</th>
                                    <th scope="col">Motivo</th>
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

    <!--PLANIFICACIÓN E IMPLEMETANCIÓN DEL APOYO-->
    <div class="col-12 px-0 mt-4">
        <div class="card mt-2">
            <div class="card-header">
                Planificación e implementanción del apoyo
            </div>
            <div class="card-body row">
                <div class="col-3">
                    <p><span id="apoyo-fecha-inicio"></span> <br> <small>Fecha inicio</small></p>
                </div>
                <div class="col-3">
                    <p><span id="apoyo-fecha-fin"></span> <br> <small>Fecha fin</small></p>
                </div>
                <div class="col-6">
                    <button class="btn btn-secondary btn-sm float-right create-special-case" data-modulo="Planificación e implemetanción del apoyo" data-moduloId="4" data-has-template="false">Crear caso especial</button>
                </div>

                <div class="col-12">
                    <p>
                        <a class="btn-link" data-toggle="collapse" href="#collapseExample4" role="button"
                            aria-expanded="false" aria-controls="collapseExample"> Casos especiales (<span id="special-case-apoyo-count"></span>)
                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-caret-down-fill"
                                fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path
                                    d="M7.247 11.14L2.451 5.658C1.885 5.013 2.345 4 3.204 4h9.592a1 1 0 0 1 .753 1.659l-4.796 5.48a1 1 0 0 1-1.506 0z" />
                            </svg></a>
                    </p>

                    <div class="collapse" id="collapseExample4">
                        <table class="table mine table-hover" id="table-specials-cases-apoyo">
                            <thead>
                                <tr>
                                    <th scope="col">Región</th>
                                    <th scope="col">Deprov</th>
                                    <th scope="col">Creado</th>
                                    <th scope="col">Usuario</th>
                                    <th scope="col">Inicio</th>
                                    <th scope="col">Fin</th>
                                    <th scope="col">Motivo</th>
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


<!-- MODAL CASO ESPECIAL -->
<div class="modal fade" id="modal-new-special-case" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Crear caso especial</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <!--Periodo-->
                    <div class="col-4">
                        <div class="form-group">
                            <label for="exampleFormControlSelect1">Período:</label>
                            <strong><span id="special-case-period"></span></strong>
                        </div>
                    </div>

                    <!--Modulo-->
                    <div class="col-8">
                        <div class="form-group">
                            <label for="exampleFormControlSelect1">Módulo</label>
                            <strong><span id="special-case-module"></span></strong>                           
                        </div>
                    </div>

                    <!--Multiselect-->
                    <div class="col-12">
                        <div class="form-group">
                            <label>Región</label> <br>
                            <!--selectpicker -->
                            <select id="special-case-region" class="form-control">
                                <option value="" selected></option>
                                [#list regiones as r]
                                <option value="${r.value}">${r.displayText}</option>
                                [/#list]
                            </select>
                            <div class="invalid-feedback">
                                <p>Debe especificar una region valida.</p>
                            </div>
                        </div>
                    </div>

                    <!--Deprov-->
                    <div class="col-12">
                        <div class="table-responsive">
                            <table class="mine table-hover table table-sm table-bordered" id="table-special-case-deprov">
                                <thead>
                                    <tr>
                                        <th><input type="checkbox" class="deprov-check-all" />&nbsp;</th>
                                        <th>Deprov</th>
                                        <th>Fecha inicio actual</th>
                                        <th>Fecha término actual</th>
                                    </tr>
                                </thead>
                                <tbody>
                                                              
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <!--Nuevas fechas-->
                    <div class="col-6">
                        <form>
                            <div>
                                <label for="fecha-inicio">Nueva fecha inicio</label> <br>
                                <input type="date" class="form-control" id="special-case-fecha-inicio" name="special-case-fecha-inicio">
                                <div class="invalid-feedback">
                                    <p>Debe especificar una fecha valida.</p>
                                </div>
                            </div>
                        </form>
                    </div>

                    <div class="col-6">
                        <form>
                            <div>
                                <label for="fecha-fin">Nueva fecha término</label> <br>
                                <input type="date" class="form-control" id="special-case-fecha-fin" name="fecha-fin">
                                <div class="invalid-feedback">
                                    <p>Debe especificar una fecha valida.</p>
                                </div>
                            </div>
                        </form>
                    </div>

                    <!--Motivo - plantilla-->
                    <div class="col-12 mt-3">
                        <form>
                            <div class="form-group">
                                <label for="exampleFormControlTextarea1">Motivo caso especial (opcional)</label>
                                <textarea class="form-control" id="special-case-observation" rows="4"></textarea>                            
                            </div>
                        </form>
                    </div>
                    <div class="col-12" id="period-special-case-template">
                        <form>
                            <div class="form-group">
                                <label for="SubirDocumentoWordOpcional">Plantilla formato Word (Opcional)</label>
                                <input type="file" class="form-control-file" id="special-case-template">
                                <label for="SubirDocumentoWordOpcional" class="text-muted">Hasta 10MB</label>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-primary" id="special-case-save">Guardar</button>
            </div>
        </div>
    </div>
</div>


 <!-- MODAL CONFIRMACIÓN CASO ESPECIAL -->
 <div class="modal fade" id="confirmarCaso" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Crear caso especial</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="col-12 text-center">
                        <span class="text-warning">
                            <svg width="4em" height="4em" viewBox="0 0 16 16" class="bi bi-exclamation-circle-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8 4a.905.905 0 0 0-.9.995l.35 3.507a.552.552 0 0 0 1.1 0l.35-3.507A.905.905 0 0 0 8 4zm.002 6a1 1 0 1 0 0 2 1 1 0 0 0 0-2z" />
                            </svg></span> <br>
                        <p class="mt-3">¿Está seguro de querer crear este caso especial? <br>
                            Está acción no se puede deshacer.</p>
                    </div>

                    <div class="col-4"><b>Período</b></div>
                    <div class="col-8">: <span id="special-case-confirm-periodo"></span> <br>
                    </div>

                    <div class="col-4"><b>Módulo</b></div>
                    <div class="col-8">: <span id="special-case-confirm-modulo"></span></div>

                    <div class="col-4"><b>Región</b></div>
                    <div class="col-8">: <span id="special-case-confirm-region"></span> </div>

                    <div class="col-4"><b>Deprov</b></div>
                    <div class="col-8">: <span id="special-case-confirm-deprov"></span></div>

                    <div class="col-4"><b>Nueva fecha de inicio</b></div>
                    <div class="col-8">: <span id="special-case-confirm-fecha-inicio"></span> </div>

                    <div class="col-4"><b>Nueva fecha de término</b></div>
                    <div class="col-8">: <span id="special-case-confirm-fecha-termino"></span> </div>

                    <div class="col-4"><b>Mótivo del caso especial</b></div>
                    <div class="col-8">: <span id="special-case-confirm-observation"></span> </div>


                    <div class="col-4"><b>Plantilla formato Word</b></div>
                    <div class="col-8">: <span id="special-case-confirm-template"></span> </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal" id="special-case-confirm-Modificar">Modificar</button>
                <button type="button" class="btn btn-primary" id="special-case-confirm-crear">Si, crear</button>
            </div>
        </div>
    </div>
</div>