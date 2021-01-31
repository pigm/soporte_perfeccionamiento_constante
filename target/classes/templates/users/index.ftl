[#include '*/commons/page-structure.ftl' /]
[@main_page]

<!-- Breadcrumb -->
<div class="container mt-3 px-0">
    <div class="row">
        <div class="col-6">
            <ol class="breadcrumb bg-white p-0 ml-4">
                Estás&nbsp;en:&nbsp;
                <li> Administración /</li> &nbsp;
                <li><a href="#" class="active">Usuario</a></li> &nbsp;
            </ol>
        </div>

        <div class="col-6">
            <button id="btnAgregar" class="btn btn-primary float-right"><svg
                    width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-plus-circle" fill="currentColor"
                    xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd"
                        d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                    <path fill-rule="evenodd"
                        d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
                </svg> &nbsp; Agregar Usuario</button>
        </div>
    </div>
</div>

<!-- CONTENIDO -->
<div class="container px-3">
    <div class="row">
        <!-- Menú segundario -->
        [#include '../shared/menu-admin.ftl' /]

        <div class="col-md-9">
            <div class="row vis-des">
                <div class="col-8 px-0">
                    <h2>Usuarios</h2>
                </div>
                <div class="col-4 px-0">
                    <div class="px-0 float-right">
                        
                    </div>
                </div>
            </div>
            
            <div class="row">
                <div class="overflow-auto col-12 px-0">
                   <div class="table-responsive">
                    <table id="table" class="table mine table-hover">
                        <thead class="table-bordered">
                        </thead>
                        <tbody class="table-bordered">
                        </tbody>
                    </table>
                   </div>
                </div>
            </div>
        </div>
    </div>
</div>
[#include './new.ftl' /]

<script type="text/javascript"	src="${context}/locals/js/users/users.js?v=202010081852"></script>
[/@main_page]