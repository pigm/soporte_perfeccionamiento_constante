<!-- VER ENCUESTA -->
<div class="modal fade" id="modal-ver-encuesta" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="ver-survey-nombre">Nombre de la encuesta</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <div class="row">
                    <!--Nombre del documento-->
                    <div class="col-12">
                        <p class="mb-2">Debes responder todas las preguntas, siendo el 1 la nota mínima y 7 la nota máxima </p>
                    </div>
                </div>

                <div id="ver-survey-questions-list" class="row">
                </div>

                <div id="ver-content-observations" class="row mt-4">
                    <!--Campo libre-->
                    <div class="col-12">
                        <p class="mb-1 fon-14">Comentarios y/o observaciones (Opcional)</p>
                        <textarea class="form-control" id="ver-survey-observation" rows="3"></textarea>
                        <div class="invalid-feedback">
                            <p>Debe registrar un comentario.</p>
                        </div>
                    </div>
                </div>

            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button type="button" id="answer-survey-save" class="btn btn-primary">Guardar</button>
            </div>
        </div>
    </div>
</div>