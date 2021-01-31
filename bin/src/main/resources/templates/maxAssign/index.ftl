[#include '*/commons/page-structure.ftl' /]
[@main_page]

<!-- Breadcrumb -->
<div class="container mt-3 px-0">
    <div class="row">
        <div class="col-12">
            <ol class="breadcrumb bg-white p-0 ml-4">
                Estás&nbsp;en:&nbsp;
                <li> Administración /</li> &nbsp;
                <li><a href="#" class="active">Asignaciones máximas</a></li> &nbsp;
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
                    <h2>Asignaciones máximas</h2>
                </div>
            </div>
            <div class="row mt-4">
                <div class="col-6 text-center">
                    <span style="font-size: 50px">${assing.supervisores}</span>
                    <a href="#" class="supervisor" data-placement="top"
                        title='<div class="text-center">Máximo de <br> supervisores </div>' data-content=''><svg
                            width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil-square" fill="currentColor"
                            xmlns="http://www.w3.org/2000/svg">
                            <path
                                d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
                            <path fill-rule="evenodd"
                                d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
                            </svg>
                    </a>
                    <p><strong>Supervisores</strong></p>
                    <p>Máximo que se pueden asignar a cada red o establecimiento, para que entreguen asesoría.</p>
                </div>

                <div class="col-6 text-center">
                    <span style="font-size: 50px">${assing.foco}</span>
                    <a href="#" class="foco"
                        title='<div class="text-center">Máximo de <br> foco </div>'
                        data-content=''><svg
                            width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil-square" fill="currentColor"
                            xmlns="http://www.w3.org/2000/svg">
                            <path
                                d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
                            <path fill-rule="evenodd"
                                d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
                            </svg>
                    </a>
                    <p><strong>Foco</strong></p>
                    <p>Máximo de focos que pueden estar sin terminar, antes de poder pasar a otro, en el módulo
                        Organización y Planificación Operacional Provincial > Planificación y Registro de Sesiones.</p>
                </div>
            </div>
        </div>


        <!-- Contenido celular -->
        <div class="col-12 vis-cel mb-5">
            <div class="row">
                <div class="col-12">
                    <h2>Asignaciones máximas</h2>
                </div>
            </div>
            <div class="row mt-4">
                <div class="col-12 text-center">
                    <span style="font-size: 50px">${assing.supervisores}</span>
                    <a href="#" class="supervisor"
                        title='<div class="text-center">Máximo de <br> supervisores </div>'
                        data-content=''><svg
                            width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil-square" fill="currentColor"
                            xmlns="http://www.w3.org/2000/svg">
                            <path
                                d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
                            <path fill-rule="evenodd"
                                d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
                            </svg>
                    </a>
                    <p><strong>Supervisores</strong></p>
                    <p>Máximo que se pueden asignar a cada red o establecimiento, para que entreguen asesoría.</p>
                </div>

                <div class="col-12 text-center">
                    <span style="font-size: 50px">${assing.foco}</span>
                    <a href="#" class="foco"
                        title='<div class="text-center">Máximo de <br> foco </div>'
                        data-content=''><svg
                            width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil-square" fill="currentColor"
                            xmlns="http://www.w3.org/2000/svg">
                            <path
                                d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
                            <path fill-rule="evenodd"
                                d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
                            </svg>
                    </a>
                    <p><strong>Foco</strong></p>
                    <p>Máximo de focos que pueden estar sin terminar, antes de poder pasar a otro, en el módulo
                        Organización y Planificación Operacional Provincial > Planificación y Registro de Sesiones.</p>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

<script type="text/javascript" src="${context}/locals/js/maxassign/came.maxassign.js"></script>


<script>
    came.maxAssign.setInitialmaxAssign({ idAsignacionMaxima: '${assing.idAsignacionMaxima}', supervisores: '${assing.supervisores}', foco: '${assing.foco}' });
</script>

[/@main_page]