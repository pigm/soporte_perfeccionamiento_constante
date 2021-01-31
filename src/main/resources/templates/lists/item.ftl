<!-- CATEGORIAS-->
<div class="modal fade" id="ListaElementos" data-backdrop="static" data-keyboard="false"
    aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Categorías</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-12">
                        <!-- NUEVO ELEMENTO -->
                        <button type="button" class="btn btn-link float-right px-0" id="btnAgregar" data-idLista="" data-original-title="Nombre nuevo elemento">
                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-plus-circle"
                                fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd"
                                    d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                                <path fill-rule="evenodd"
                                    d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
                            </svg>&nbsp;Nuevo elemento
                        </button>
                    </div>
                    <div class="col-12">
                        <div class="collapse" id="collapseItem">
                            <div class="card card-body">
                                <div class="row">
                                    <div class="col-8">
                                        <span>Nombre nuevo elemento</span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-9">
                                        <input id="textNombreElement" class="form-control" data-idItem="" />
                                        <div id="errorNombre" class="alert alert-danger alert-dismissible mt-1"
                                            role="alert" style="display: none;">
                                            <span class="glyphicon glyphicon-exclamation-sign"></span>
                                            <button type="button" class="close" data-dismiss="alert"
                                                aria-label="Close"><span
                                                    aria-hidden="true">&times;</span></button>&nbsp;
                                            <p>Debe especificar un nombre valido.</p>
                                        </div>
                                        <button type="button" id="btnCancelar" class="btn btn-link">Cancelar</button>
                                    </div>
                                    <div class="col-3">
                                        <a href="#" class="float-center text-center" id="btnSetItem">
                                            <svg width="2em" height="2em" viewBox="0 0 16 16" class="bi bi-check-circle"
                                                fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd"
                                                    d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                                                <path fill-rule="evenodd"
                                                    d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z" />
                                            </svg>
                                        </a>
                                        <a href="#" class="float-right text-danger">
                                            <svg width="2em" height="2em" viewBox="0 0 16 16" class="bi bi-trash-fill"
                                                fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd"
                                                    d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z" />
                                            </svg>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <table id="tableItemsList" class="table table mine table-hover table-sm">
                            <thead class="">
                                <tr>
                                    <th>Elemento</th>
                                    <th width="5%" class="text-center">Habilitar</th>
                                </tr>
                            </thead>
                            <tbody class="table-bordered">                                
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