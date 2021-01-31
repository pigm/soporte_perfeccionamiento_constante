[#include '*/commons/page-structure.ftl' /]
[@main_page]

<!-- Breadcrumb -->
<div class="container mt-3 px-0">
    <div class="row">
        <div class="col-6">
            <ol class="breadcrumb bg-white p-0 ml-4">
                Estás&nbsp;en:&nbsp;
                <li>
                    Administración /</li>
                &nbsp;
                <li>
                    <a href="#" class="active">Perfiles</a>
                </li>
            </ol>
        </div>

       <div class="col-6">
            <button id="btnAgregarPerfil" class="btn btn-primary float-right"><svg
                    width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-plus-circle" fill="currentColor"
                    xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd"
                        d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                    <path fill-rule="evenodd"
                        d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
                </svg> &nbsp; Agregar Perfil
            </button>
        </div>
    </div>
</div>

<div id="principal-container" class="container px-0">
    <div class="row">
        [#include '../shared/menu-admin.ftl' /]
        <div class="col-md-9">
            <div class="row vis-des">
                <div class="col-8">
                    <h2>Perfiles</h2>
                </div>
                <div class="col-4 px-0">
                    <div class="px-0 float-right">
                        
                    </div>                
                </div>                 
            </div>
            <div class="row">
                    <div class="col-12">
                        <table id="tableProfiles" class="table mine table-hover">
                            <thead class="table-bordered">
                            </thead>
                            <tbody class="table-bordered">
                            </tbody>
                        </table>
                    </div>
                </div>
        </div>
    </div>    
    [#include './new.ftl' /]
</div>



<script type="text/javascript"	src="${context}/locals/js/profile/profile.js"></script>
[/@main_page]
