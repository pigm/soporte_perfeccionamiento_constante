<!-- AgregarPerfil  MODAL-->
    <div class="modal" id="agregarPerfil" data-backdrop="static" data-keyboard="false" tabindex="-1"
        aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-scrollable">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Agregar Perfil</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div id="perfil-content-detail" class="row">                        
                        <!-- NOMBRE PERFIL -->
                        <div class="col-8">
                            <div class="form-group">
                                <label for="nombrePerfil">Nombre del Perfil</label>
                                <input type="text" class="form-control " aria-describedby="nombrePerfil" id="textboxNombre">          
                                <div class="invalid-feedback">
                                    <p>Debe especificar un nombre valido.</p>
                                </div>
                            </div>                            	
                        </div>                      
                        <!-- ESTADO PERFIL -->
                        <!-- <div class="col-4 text-center">
                            <p>Estado del Perfil</p>
                        </div> -->

                        <!-- NIVEL -->
                        <div class="col-12">
                            <div id="goup-nivel" class="form-group">
                                <label for="">Nivel</label>
                                <div class="custom-control custom-radio">
                                    <input type="radio" id="customRadio1" name="nivel" value="2416184421980832772" class="custom-control-input nivel">
                                    <label class="custom-control-label" for="customRadio1">Nacional</label>
                                </div>
                                <div class="custom-control custom-radio">
                                    <input type="radio" id="customRadio2" name="nivel" value="2416184421980832773" class="custom-control-input nivel">
                                    <label class="custom-control-label" for="customRadio2">Regional</label>
                                </div>
                                <div class="custom-control custom-radio">
                                    <input type="radio" id="customRadio3" name="nivel" value="2416184421980832774" class="custom-control-input nivel">
                                    <label class="custom-control-label" for="customRadio3">Provincial</label>
                                </div>
                            </div>
                            <div class="invalid-feedback">
                                <p>Debe especificar un nivel valido.</p>
                            </div>                
                        </div>

                        <!-- DESCRIPCION -->
                        <div class="col-12">
                            <div class="form-group py-0">
                                <label for="exampleFormControlTextarea1">Descripción</label>
                                <textarea class="form-control" id="textboxDescripcion" rows="3" maxlength="150"></textarea>
                                 <small class="text-muted">Llevas <span id="lenDescription">0</span> de 150 caracteres como máximo</small> 
                                 <div class="invalid-feedback">
                                    <p>Debe especificar una Descripción valida.</p>
                                </div>
                            </div>                            
                        </div>
                        <!-- Secciones -->
                        <div class="col-12 py-0">
                            <table class="table table mine table-hover">
                                <thead>
                                    <tr>
                                        <th>Secciones</th>
                                        <th class="text-center"><small>Sin acceso</small></th>
                                        <th class="text-center"><small>Ver</small></th>
                                        <th class="text-center"><small>Ver&nbsp;y&nbsp;editar</small></th>
                                    </tr>
                                </thead>
                                <tbody class="table-bordered table-sm">
                                    [#list modulos as modulo]
                                    <tr>
                                        <th colspan="5">${modulo.nombre}</th>
                                    </tr>                              
                                    [#list modulo.subModulo as sm]
                                    <tr id="${sm.idSubModulo?c}">
                                        <td>${sm.nombre}</td>
                                        <td class="text-center">
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input acceso" type="radio" name="${modulo.nombre}.${sm.nombre}" value="2415742140157002753">
                                                <label class="form-check-label" for="inlineRadio1"></label>
                                            </div>
                                        </td>
                                        <td class="text-center">
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input acceso" type="radio" name="${modulo.nombre}.${sm.nombre}" value="2415742140157002754">
                                                <label class="form-check-label" for="inlineRadio1"></label>
                                            </div>
                                        </td>
                                        <td class="text-center">
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input acceso" type="radio" name="${modulo.nombre}.${sm.nombre}" value="2415742140157002755">
                                                <label class="form-check-label" for="inlineRadio1"></label>
                                            </div>
                                        </td>
                                    </tr>
                                    [/#list]      
                                    [/#list]
                                </tbody>
                                <tfoot>
                                </tfoot>
                            </table>

                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">cancelar</button>
                    <button type="button" class="btn btn-primary" id="btnGuardar">Guardar</button>
                </div>
            </div>
        </div>
    </div>