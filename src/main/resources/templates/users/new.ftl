<style>
    .footer-switch {
        position: absolute;
        left: 15px;
    }
</style>

<div class="modal fade" id="agregarUsuario" data-backdrop="static" data-keyboard="false" tabindex="-1"
    aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">                
                <h5 class="modal-title" id="staticBackdropLabel">Agregar Usuario</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div id="usuario-content-detail" class="modal-body">
                <div class="row">
                    <!-- NOMBRE USUARIO -->
                    <div class="col-10 find-user-ldap">
                        <label for="nombrePerfil">Nombre del Usuario</label>
                        <div class="input-group mb-3">
                            <input id="userNameText" type="text" class="form-control" placeholder="usuario de dominio"
                                aria-label="usuario de dominio" aria-describedby="basic-addon2">
                            <div class="invalid-feedback">
                                <p>Debe especificar un nivel valido.</p>
                            </div>
                            <div class="input-group-append">
                                <button id="findUserLdap" class="btn btn-primary" type="button">Buscar</button>
                            </div>
                        </div>
                    </div>                  
                </div>

                <div class="row">
                    <div class="col-10">
                        <p><span><strong>UserName: </strong></span><span id="userUserName"></span></p>
                        <p><span><strong>Rut: </strong></span><span id="userRut"></span></p>
                        <p><span><strong>Nombre: </strong></span><span id="userNombre"></span></p>
                        <p><span><strong>Apellido paterno: </strong></span><span id="userApellidoPaterno"></span></p>
                        <p><span><strong>Apellido materno: </strong></span><span id="userApellidoMaterno"></span></p>
                        <p><span><strong>Email: </strong></span><span id="userEmail"></span></p>
                    </div>
                </div>

                <div class="row">
                    <!-- Profile -->
                    <div class="col-10">
                        <div class="form-group">
                            <label for="nombrePerfil">Perfil</label>
                            <select class="form-control" id="perfilUsuario">
                                <option value="" selected></option>
                                [#list profiles as p]
                                <option value="${p.value}" data-nivel="${p.nivel}">${p.displayText}</option>
                                [/#list]
                            </select>
                            <div class="invalid-feedback">
                                <p>Debe especificar un perfil valido.</p>
                            </div>
                        </div>
                    </div>                 
                </div>

                <div class="row">
                    <!-- region -->
                    <div class="col-10">
                        <div class="form-group">
                            <label for="region">Region (opcional)</label>
                            <select id="region" class="form-control" id="exampleFormControlSelect1">
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
                </div>

                <div class="row">
                    <!-- region -->
                    <div class="col-10">
                        <div class="form-group">
                            <label for="nombrePerfil">Deprov (opcional)</label>
                            <select id="provincia" class="form-control">
                            </select>
                            <div class="invalid-feedback">
                                <p>Debe especificar una Deprov valida.</p>
                            </div>
                        </div>
                    </div>                    
                </div>
            </div>
            <div class="modal-footer">
                <div class="custom-control custom-switch mt-1 footer-switch">
                    <input type="checkbox" class="custom-control-input" id="habilitado" checked select style="z-index: 9999;">
                    <label class="custom-control-label" for="customSwitch1">Habilitar</label>
                </div>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">cancelar</button>
                <button type="button" class="btn btn-primary" id="buttonSetUser">Guardar</button>
            </div>
        </div>
    </div>
</div>