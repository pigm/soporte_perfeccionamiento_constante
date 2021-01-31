[#include '*/commons/page-structure.ftl' /]
[@main_page]

<!-- Breadcrumb -->
<div class="container mt-3 px-0">
    <div class="row">
        <div class="col-12">
            <ol class="breadcrumb bg-white p-0 ml-4">
                Estás&nbsp;en:&nbsp;
                <li> Administración /</li> &nbsp;
                <li><a href="#" class="active">Listas desplegables</a></li> &nbsp;
            </ol>
        </div>
    </div>
</div>

<!-- CONTENIDO -->
<div class="container px-3">
    <div class="row">
        <!-- Menú segundario -->
        [#include '../shared/menu-admin.ftl' /]

        <!-- Contenido -->
        <div class="col-md-9">
            <div class="row mb-3">
                <div class="col-12 px-0">
                    <h2>Listas desplegables</h2>
                </div>
            </div>

            <div class="row">
                [#list listas as l]
                <div class="col-md-3 text-center">
                    <a href="#" data-idList="${l.idLista?c}" class="show-elements">
                        [#switch l.idLista?c]
                        [#case "2420130603857871959"]
                        <svg width="5em" height="5em" viewBox="0 0 16 16" class="bi bi-ui-checks-grid"
                            fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                d="M2 10a1 1 0 0 0-1 1v3a1 1 0 0 0 1 1h3a1 1 0 0 0 1-1v-3a1 1 0 0 0-1-1H2zm9-9a1 1 0 0 0-1 1v3a1 1 0 0 0 1 1h3a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1h-3zm0 9a1 1 0 0 0-1 1v3a1 1 0 0 0 1 1h3a1 1 0 0 0 1-1v-3a1 1 0 0 0-1-1h-3zm0-10a2 2 0 0 0-2 2v3a2 2 0 0 0 2 2h3a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2h-3zM2 9a2 2 0 0 0-2 2v3a2 2 0 0 0 2 2h3a2 2 0 0 0 2-2v-3a2 2 0 0 0-2-2H2zm7 2a2 2 0 0 1 2-2h3a2 2 0 0 1 2 2v3a2 2 0 0 1-2 2h-3a2 2 0 0 1-2-2v-3zM0 2a2 2 0 0 1 2-2h3a2 2 0 0 1 2 2v3a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm5.354.854l-2 2a.5.5 0 0 1-.708 0l-1-1a.5.5 0 1 1 .708-.708L3 3.793l1.646-1.647a.5.5 0 1 1 .708.708z" />
                        </svg>
                        [#break]
                        [#case "2420130603924980824"]
                        <svg width="5em" height="5em" viewBox="0 0 16 16" class="bi bi-percent" fill="currentColor"
                            xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                d="M13.442 2.558a.625.625 0 0 1 0 .884l-10 10a.625.625 0 1 1-.884-.884l10-10a.625.625 0 0 1 .884 0zM4.5 6a1.5 1.5 0 1 0 0-3 1.5 1.5 0 0 0 0 3zm0 1a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5zm7 6a1.5 1.5 0 1 0 0-3 1.5 1.5 0 0 0 0 3zm0 1a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5z" />
                        </svg>
                        [#break]
                        [#case "2420130603941758041"]
                        <svg width="5em" height="5em" viewBox="0 0 16 16" class="bi bi-share" fill="currentColor"
                            xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                d="M13.5 1a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3zM11 2.5a2.5 2.5 0 1 1 .603 1.628l-6.718 3.12a2.499 2.499 0 0 1 0 1.504l6.718 3.12a2.5 2.5 0 1 1-.488.876l-6.718-3.12a2.5 2.5 0 1 1 0-3.256l6.718-3.12A2.5 2.5 0 0 1 11 2.5zm-8.5 4a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3zm11 5.5a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3z" />
                        </svg>
                        [#break]
                        [#case "2420130603950146650"]
                        <svg width="5em" height="5em" viewBox="0 0 16 16" class="bi bi-bar-chart-steps"
                            fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M.5 0a.5.5 0 0 1 .5.5v15a.5.5 0 0 1-1 0V.5A.5.5 0 0 1 .5 0z" />
                            <rect width="5" height="2" x="2" y="1" rx=".5" />
                            <rect width="8" height="2" x="4" y="5" rx=".5" />
                            <path
                                d="M6 9.5a.5.5 0 0 1 .5-.5h6a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-6a.5.5 0 0 1-.5-.5v-1zm2 4a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-7a.5.5 0 0 1-.5-.5v-1z" />
                        </svg>
                        [#break]
                        [#default]
                        <img src="${context}/locals/images/logo-med.png" alt="">
                        [#break]
                        [/#switch]

                        <h5 class="mt-2">${l.nombre}</h5>
                        <p class="text-muted mt-2">${l.elementosListas?size} de elementos</p>
                    </a>
                </div>
                [/#list]
            </div>
        </div>
    </div>
</div>


[#include './item.ftl' /]

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

<script type="text/javascript" src="${context}/locals/js/list/list.js"></script>
[/@main_page]