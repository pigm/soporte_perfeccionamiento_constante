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
    </div>
</div>

<!-- CONTENIDO ESCRITORIO-->
<div class="container">

    <!-- Titulo -->
    <div class="row">
        <div class="col-12">
            <div class="col-12 px-0">
                <a id="button-back" href="#">
                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chevron-left" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z" />
                    </svg> Volver
                </a>
            </div>
        </div>

        <div class="col-12 mt-3">
            <h2>${survey.nombre}</h2>
        </div>

        <div class="col-6 mt-2">
            <p>
                Proceso: <strong>${currentYear?c}</strong> <br>
                Destinatario: <strong>${survey.perfiles?join(", ")}</strong> <br>
                Encuestas enviados: <strong>${survey.enviadas}</strong> <br>
                Encuestas respondidas: <strong>${survey.respondidas}</strong>
            </p>
        </div>
        <div class="col-6 mt-2">
            <p>
                Fecha inicio: <strong>${survey.fechaInicio}</strong> <br>
                Fecha fin: <strong>${survey.fechaFin}</strong>
            </p>
        </div>
    </div>

    <!--Tabla de resultado-->
    <div class="row mt-1">
        <div class="col-6">
            Resultados
        </div>

        <div class="col-6">
            <a href="/apoyo-mejora-continua/survey/export/excel?idSurvey=${survey.idSurvey}" class="btn btn-link float-right px-0"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-file-earmark-excel" viewBox="0 0 16 16">
                    <path d="M5.884 6.68a.5.5 0 1 0-.768.64L7.349 10l-2.233 2.68a.5.5 0 0 0 .768.64L8 10.781l2.116 2.54a.5.5 0 0 0 .768-.641L8.651 10l2.233-2.68a.5.5 0 0 0-.768-.64L8 9.219l-2.116-2.54z" />
                    <path d="M14 14V4.5L9.5 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2zM9.5 3A1.5 1.5 0 0 0 11 4.5h2V14a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h5.5v2z" />
                </svg> Exportar a Excel
            </a>
        </div>

        <div class="table-responsive col-12">
            <table class="table mine table-hover table-bordered table-sm">
                <thead>
                    <tr>
                        <th scope="col">N°</th>
                        <th scope="col">Pregunta</th>
                        <th scope="col">Promedio</th>
                    </tr>
                </thead>
                <tbody class=" table-sm">
                    [#list survey.preguntas as p]
                    <tr>
                        <td>${p.number}</td>
                        <td>${p.question}</td>
                        <td class="text-center">${p.average}</td>
                    </tr>
                    [/#list]
                </tbody>
            </table>
        </div>
    </div>

    <div class="row">
        <div class="col-12">
            <p class="mb-2">Comentarios y/o observaciones <small>(Cantidad: ${survey.observaciones?size})</small></p>
        </div>
        <div class="table-responsive col-12">
            <table class="table mine table-hover table-bordered table-sm">
                <tbody class=" table-sm">
                    [#list survey.observaciones as o]
                    <tr>
                        <td>${o}</td>
                    </tr>
                    [/#list]                   
                </tbody>
            </table>
        </div>
    </div>
</div>

<script type="text/javascript" src="${context}/locals/js/survey/came.survey.js"></script>
[/@main_page]