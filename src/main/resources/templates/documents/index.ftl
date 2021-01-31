[#include '*/commons/page-structure.ftl' /]
[@main_page]
<style>
    .popover {
        max-width: 800px !important;
        width: 350px
    }
</style>

<!-- CONTENIDO ESCRITORIO-->
<div class="container px-0"" >
    <div class=" row">
    [#include '../shared/menu-proceso.ftl' /]

    <div class="col-md-9">
        <div class="row mb-3">
            <!-- Contenido escritorio-->
            <div class="col-md-12 mb-3">
                <h2>Ingresar documentos de la asesoría</h2>
            </div>

            <div class="col-md-12">
                <p>
                    <svg width="2em" height="2em" viewBox="0 0 16 16" class="bi bi-hand-thumbs-up" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" d="M6.956 1.745C7.021.81 7.908.087 8.864.325l.261.066c.463.116.874.456 1.012.965.22.816.533 2.511.062 4.51a9.84 9.84 0 0 1 .443-.051c.713-.065 1.669-.072 2.516.21.518.173.994.681 1.2 1.273.184.532.16 1.162-.234 1.733.058.119.103.242.138.363.077.27.113.567.113.856 0 .289-.036.586-.113.856-.039.135-.09.273-.16.404.169.387.107.819-.003 1.148a3.163 3.163 0 0 1-.488.901c.054.152.076.312.076.465 0 .305-.089.625-.253.912C13.1 15.522 12.437 16 11.5 16v-1c.563 0 .901-.272 1.066-.56a.865.865 0 0 0 .121-.416c0-.12-.035-.165-.04-.17l-.354-.354.353-.354c.202-.201.407-.511.505-.804.104-.312.043-.441-.005-.488l-.353-.354.353-.354c.043-.042.105-.14.154-.315.048-.167.075-.37.075-.581 0-.211-.027-.414-.075-.581-.05-.174-.111-.273-.154-.315L12.793 9l.353-.354c.353-.352.373-.713.267-1.02-.122-.35-.396-.593-.571-.652-.653-.217-1.447-.224-2.11-.164a8.907 8.907 0 0 0-1.094.171l-.014.003-.003.001a.5.5 0 0 1-.595-.643 8.34 8.34 0 0 0 .145-4.726c-.03-.111-.128-.215-.288-.255l-.262-.065c-.306-.077-.642.156-.667.518-.075 1.082-.239 2.15-.482 2.85-.174.502-.603 1.268-1.238 1.977-.637.712-1.519 1.41-2.614 1.708-.394.108-.62.396-.62.65v4.002c0 .26.22.515.553.55 1.293.137 1.936.53 2.491.868l.04.025c.27.164.495.296.776.393.277.095.63.163 1.14.163h3.5v1H8c-.605 0-1.07-.081-1.466-.218a4.82 4.82 0 0 1-.97-.484l-.048-.03c-.504-.307-.999-.609-2.068-.722C2.682 14.464 2 13.846 2 13V9c0-.85.685-1.432 1.357-1.615.849-.232 1.574-.787 2.132-1.41.56-.627.914-1.28 1.039-1.639.199-.575.356-1.539.428-2.59z" />
                    </svg>&nbsp;&nbsp; Por favor descarga las plantillas, complétalas y vuelve a subirlas, dentro de los
                    plazos definidos.
                </p>
            </div>

            <div id="document-regional" class="col-6">
                <div class="rounded p-2 bg-light" style="width: 100%">
                    <div class="row">
                        <div class="col-md-12">
                            <h3>Documentos Regionales</h3>
                            <span>Desde el <strong>${docRegional.fechaInicio?string["dd-MM-yyyy"]}</strong> hasta el
                                <strong>${docRegional.fechaFin?string["dd-MM-yyyy"]}</strong></span>
                        </div>
                        <div class="col-md-6 text-center mt-3 text-dark">
                            <a href="${context}/downloadController/getFile?filePath=${uploadMainFolder}${docRegional.templatePath}" target="_blank">
                                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-file-earmark-text" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M4 0h5.5v1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V4.5h1V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2z" />
                                    <path d="M9.5 3V0L14 4.5h-3A1.5 1.5 0 0 1 9.5 3z" />
                                    <path fill-rule="evenodd" d="M5 11.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5zm0-2a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm0-2a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5z" />
                                    <p class="mb-0 mt-2">Plantilla del <br> Documento Regional</p>
                                </svg>
                            </a>
                        </div>
                        <div class="col-md-6 text-center mt-3 text-info">
                            <a id="svg-documento-regional-upload" href="#" class="upload-document" data-title="Documento Regional" data-document-type="document" data-location="region">
                                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-cloud-arrow-up" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M4.406 3.342A5.53 5.53 0 0 1 8 2c2.69 0 4.923 2 5.166 4.579C14.758 6.804 16 8.137 16 9.773 16 11.569 14.502 13 12.687 13H3.781C1.708 13 0 11.366 0 9.318c0-1.763 1.266-3.223 2.942-3.593.143-.863.698-1.723 1.464-2.383zm.653.757c-.757.653-1.153 1.44-1.153 2.056v.448l-.445.049C2.064 6.805 1 7.952 1 9.318 1 10.785 2.23 12 3.781 12h8.906C13.98 12 15 10.988 15 9.773c0-1.216-1.02-2.228-2.313-2.228h-.5v-.5C12.188 4.825 10.328 3 8 3a4.53 4.53 0 0 0-2.941 1.1z" />
                                    <path fill-rule="evenodd" d="M7.646 5.146a.5.5 0 0 1 .708 0l2 2a.5.5 0 0 1-.708.708L8.5 6.707V10.5a.5.5 0 0 1-1 0V6.707L6.354 7.854a.5.5 0 1 1-.708-.708l2-2z" />
                                </svg>
                                <p class="mb-0 mt-2">Documento Regional</p>
                                <small>No lo has subido</small>
                            </a>
                            <a id="svg-documento-regional-download" href="#" style="display: none;" class="view-document" data-title="Documento Regional" data-document-type="document" data-location="region">
                                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-file-earmark-check-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M2 2a2 2 0 0 1 2-2h5.293A1 1 0 0 1 10 .293L13.707 4a1 1 0 0 1 .293.707V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2zm7.5 1.5v-2l3 3h-2a1 1 0 0 1-1-1zm1.354 4.354a.5.5 0 0 0-.708-.708L7.5 9.793 6.354 8.646a.5.5 0 1 0-.708.708l1.5 1.5a.5.5 0 0 0 .708 0l3-3z" />
                                </svg>
                                <p class="mb-0 mt-2">Documento Regional</p>
                                <small>Lo subiste el <span id="fecha-creacion-documento-regional"></span></small>
                            </a>
                        </div>
                        <div class="col-md-6 text-center mt-3 text-info">
                            <a id="svg-documento-regional-a1-upload" href="#" class="upload-document" data-title="Documento Regional - Anexo 1" data-document-type="anexo1" data-location="region">
                                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-cloud-arrow-up" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M4.406 3.342A5.53 5.53 0 0 1 8 2c2.69 0 4.923 2 5.166 4.579C14.758 6.804 16 8.137 16 9.773 16 11.569 14.502 13 12.687 13H3.781C1.708 13 0 11.366 0 9.318c0-1.763 1.266-3.223 2.942-3.593.143-.863.698-1.723 1.464-2.383zm.653.757c-.757.653-1.153 1.44-1.153 2.056v.448l-.445.049C2.064 6.805 1 7.952 1 9.318 1 10.785 2.23 12 3.781 12h8.906C13.98 12 15 10.988 15 9.773c0-1.216-1.02-2.228-2.313-2.228h-.5v-.5C12.188 4.825 10.328 3 8 3a4.53 4.53 0 0 0-2.941 1.1z" />
                                    <path fill-rule="evenodd" d="M7.646 5.146a.5.5 0 0 1 .708 0l2 2a.5.5 0 0 1-.708.708L8.5 6.707V10.5a.5.5 0 0 1-1 0V6.707L6.354 7.854a.5.5 0 1 1-.708-.708l2-2z" />
                                </svg>
                                <p class="mb-0 mt-2">Anexo 1 <em>(Opcional)</em></p>
                                <small>No lo has subido</small>
                            </a>
                            <a id="svg-documento-regional-a1-download" style="display: none;" class="view-document" data-title="Documento Regional - Anexo 1" data-document-type="anexo1" data-location="region">
                                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-file-earmark-check-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M2 2a2 2 0 0 1 2-2h5.293A1 1 0 0 1 10 .293L13.707 4a1 1 0 0 1 .293.707V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2zm7.5 1.5v-2l3 3h-2a1 1 0 0 1-1-1zm1.354 4.354a.5.5 0 0 0-.708-.708L7.5 9.793 6.354 8.646a.5.5 0 1 0-.708.708l1.5 1.5a.5.5 0 0 0 .708 0l3-3z" />
                                </svg>
                                <p class="mb-0 mt-2">Anexo 1 <em>(Opcional)</em></p>
                                <small>Lo subiste el <span id="fecha-creacion-documento-anexo1-regional"></span></small>
                            </a>
                        </div>
                        <div class="col-md-6 text-center mt-3 text-info">
                            <a id="svg-documento-regional-a2-upload" href="#" class="upload-document" data-title="Documento Regional - Anexo 2" data-document-type="anexo2" data-location="region">
                                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-cloud-arrow-up" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M4.406 3.342A5.53 5.53 0 0 1 8 2c2.69 0 4.923 2 5.166 4.579C14.758 6.804 16 8.137 16 9.773 16 11.569 14.502 13 12.687 13H3.781C1.708 13 0 11.366 0 9.318c0-1.763 1.266-3.223 2.942-3.593.143-.863.698-1.723 1.464-2.383zm.653.757c-.757.653-1.153 1.44-1.153 2.056v.448l-.445.049C2.064 6.805 1 7.952 1 9.318 1 10.785 2.23 12 3.781 12h8.906C13.98 12 15 10.988 15 9.773c0-1.216-1.02-2.228-2.313-2.228h-.5v-.5C12.188 4.825 10.328 3 8 3a4.53 4.53 0 0 0-2.941 1.1z" />
                                    <path fill-rule="evenodd" d="M7.646 5.146a.5.5 0 0 1 .708 0l2 2a.5.5 0 0 1-.708.708L8.5 6.707V10.5a.5.5 0 0 1-1 0V6.707L6.354 7.854a.5.5 0 1 1-.708-.708l2-2z" />
                                </svg>
                                <p class="mb-0 mt-2">Anexo 2 <em>(Opcional)</em></p>
                                <small>No lo has subido</small>
                            </a>
                            <a id="svg-documento-regional-a2-download" style="display: none;" class="view-document" data-title="Documento Regional - Anexo 2" data-document-type="anexo2" data-location="region">
                                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-file-earmark-check-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M2 2a2 2 0 0 1 2-2h5.293A1 1 0 0 1 10 .293L13.707 4a1 1 0 0 1 .293.707V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2zm7.5 1.5v-2l3 3h-2a1 1 0 0 1-1-1zm1.354 4.354a.5.5 0 0 0-.708-.708L7.5 9.793 6.354 8.646a.5.5 0 1 0-.708.708l1.5 1.5a.5.5 0 0 0 .708 0l3-3z" />
                                </svg>
                                <p class="mb-0 mt-2">Anexo 2 <em>(Opcional)</em></p>
                                <small>Lo subiste el <span id="fecha-creacion-documento-anexo2-regional"></span></small>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div id="document-provincial" class="col-6">
                <div class="rounded p-2 bg-light" style="width: 100%">
                    <div class="row">
                        <div class="col-md-6">
                            <h3>Documentos Provinciales</h3>                           
                        </div>
                        <div id="div-selector-deprov" class="col-md-6 text-right">
                            <select id="selector-deprov" class="form-control">
                            </select>
                        </div>
                        <div class="col-md-12">
                            <span>Desde el <strong id="from-documento-provincial"></strong> hasta el <strong id="to-documento-provincial"></strong> </span>
                        </div>
                        <div class="col-md-6 text-center mt-3 text-dark">
                            <a href="#" id="plantilla-documento-provincial" target="_blank">
                                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-file-earmark-text" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M4 0h5.5v1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V4.5h1V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2z" />
                                    <path d="M9.5 3V0L14 4.5h-3A1.5 1.5 0 0 1 9.5 3z" />
                                    <path fill-rule="evenodd" d="M5 11.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5zm0-2a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm0-2a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5z" />
                                    <p class="mb-0 mt-2">Plantilla del <br> Documento Provincial</p>
                                </svg>
                            </a>
                        </div>
                        <div class="col-md-6 text-center mt-3 text-info">
                            <a id="svg-documento-provincial-upload" href="#" class="upload-document" data-title="Documento Provincial" data-document-type="document" data-location="deprov">
                                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-cloud-arrow-up" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M4.406 3.342A5.53 5.53 0 0 1 8 2c2.69 0 4.923 2 5.166 4.579C14.758 6.804 16 8.137 16 9.773 16 11.569 14.502 13 12.687 13H3.781C1.708 13 0 11.366 0 9.318c0-1.763 1.266-3.223 2.942-3.593.143-.863.698-1.723 1.464-2.383zm.653.757c-.757.653-1.153 1.44-1.153 2.056v.448l-.445.049C2.064 6.805 1 7.952 1 9.318 1 10.785 2.23 12 3.781 12h8.906C13.98 12 15 10.988 15 9.773c0-1.216-1.02-2.228-2.313-2.228h-.5v-.5C12.188 4.825 10.328 3 8 3a4.53 4.53 0 0 0-2.941 1.1z" />
                                    <path fill-rule="evenodd" d="M7.646 5.146a.5.5 0 0 1 .708 0l2 2a.5.5 0 0 1-.708.708L8.5 6.707V10.5a.5.5 0 0 1-1 0V6.707L6.354 7.854a.5.5 0 1 1-.708-.708l2-2z" />
                                </svg>
                                <p class="mb-0 mt-2">Documento Provincial</p>
                                <small>No lo has subido</small>
                            </a>
                            <a id="svg-documento-provincial-download" href="#" style="display: none;" class="view-document-provincial" data-title="Documento Provincial" data-document-type="document" data-location="deprov">
                                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-file-earmark-check-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M2 2a2 2 0 0 1 2-2h5.293A1 1 0 0 1 10 .293L13.707 4a1 1 0 0 1 .293.707V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2zm7.5 1.5v-2l3 3h-2a1 1 0 0 1-1-1zm1.354 4.354a.5.5 0 0 0-.708-.708L7.5 9.793 6.354 8.646a.5.5 0 1 0-.708.708l1.5 1.5a.5.5 0 0 0 .708 0l3-3z" />
                                </svg>
                                <p class="mb-0 mt-2">Documento Provincial</p>
                                <small>Lo subiste el <span id="fecha-creacion-documento-provincial"></span></small>
                            </a>
                        </div>
                        <div class="col-md-6 text-center mt-3 text-info">
                            <a id="svg-documento-provincial-a1-upload" href="#" class="upload-document" data-title="Documento Provincial - Anexo 1" data-document-type="anexo1" data-location="deprov">
                                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-cloud-arrow-up" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M4.406 3.342A5.53 5.53 0 0 1 8 2c2.69 0 4.923 2 5.166 4.579C14.758 6.804 16 8.137 16 9.773 16 11.569 14.502 13 12.687 13H3.781C1.708 13 0 11.366 0 9.318c0-1.763 1.266-3.223 2.942-3.593.143-.863.698-1.723 1.464-2.383zm.653.757c-.757.653-1.153 1.44-1.153 2.056v.448l-.445.049C2.064 6.805 1 7.952 1 9.318 1 10.785 2.23 12 3.781 12h8.906C13.98 12 15 10.988 15 9.773c0-1.216-1.02-2.228-2.313-2.228h-.5v-.5C12.188 4.825 10.328 3 8 3a4.53 4.53 0 0 0-2.941 1.1z" />
                                    <path fill-rule="evenodd" d="M7.646 5.146a.5.5 0 0 1 .708 0l2 2a.5.5 0 0 1-.708.708L8.5 6.707V10.5a.5.5 0 0 1-1 0V6.707L6.354 7.854a.5.5 0 1 1-.708-.708l2-2z" />
                                </svg>
                                <p class="mb-0 mt-2">Anexo 1 <em>(Opcional)</em></p>
                                <small>No lo has subido</small>
                            </a>
                            <a id="svg-documento-provincial-a1-download" style="display: none;" class="view-document-provincial" data-title="Documento Provincial - Anexo 1" data-document-type="anexo1" data-location="deprov">
                                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-file-earmark-check-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M2 2a2 2 0 0 1 2-2h5.293A1 1 0 0 1 10 .293L13.707 4a1 1 0 0 1 .293.707V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2zm7.5 1.5v-2l3 3h-2a1 1 0 0 1-1-1zm1.354 4.354a.5.5 0 0 0-.708-.708L7.5 9.793 6.354 8.646a.5.5 0 1 0-.708.708l1.5 1.5a.5.5 0 0 0 .708 0l3-3z" />
                                </svg>
                                <p class="mb-0 mt-2">Anexo 1 <em>(Opcional)</em></p>
                                <small>Lo subiste el <span id="fecha-creacion-documento-anexo1-provincial"></span></small>
                            </a>
                        </div>
                        <div class="col-md-6 text-center mt-3 text-info">
                            <a id="svg-documento-provincial-a2-upload" href="#" class="upload-document" data-title="Documento Provincial - Anexo 2" data-document-type="anexo2" data-location="deprov">
                                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-cloud-arrow-up" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M4.406 3.342A5.53 5.53 0 0 1 8 2c2.69 0 4.923 2 5.166 4.579C14.758 6.804 16 8.137 16 9.773 16 11.569 14.502 13 12.687 13H3.781C1.708 13 0 11.366 0 9.318c0-1.763 1.266-3.223 2.942-3.593.143-.863.698-1.723 1.464-2.383zm.653.757c-.757.653-1.153 1.44-1.153 2.056v.448l-.445.049C2.064 6.805 1 7.952 1 9.318 1 10.785 2.23 12 3.781 12h8.906C13.98 12 15 10.988 15 9.773c0-1.216-1.02-2.228-2.313-2.228h-.5v-.5C12.188 4.825 10.328 3 8 3a4.53 4.53 0 0 0-2.941 1.1z" />
                                    <path fill-rule="evenodd" d="M7.646 5.146a.5.5 0 0 1 .708 0l2 2a.5.5 0 0 1-.708.708L8.5 6.707V10.5a.5.5 0 0 1-1 0V6.707L6.354 7.854a.5.5 0 1 1-.708-.708l2-2z" />
                                </svg>
                                <p class="mb-0 mt-2">Anexo 2 <em>(Opcional)</em></p>
                                <small>No lo has subido</small>
                            </a>
                            <a id="svg-documento-provincial-a2-download" style="display: none;" class="view-document-provincial" data-title="Documento Provincial - Anexo 2" data-document-type="anexo2" data-location="deprov">
                                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-file-earmark-check-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M2 2a2 2 0 0 1 2-2h5.293A1 1 0 0 1 10 .293L13.707 4a1 1 0 0 1 .293.707V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2zm7.5 1.5v-2l3 3h-2a1 1 0 0 1-1-1zm1.354 4.354a.5.5 0 0 0-.708-.708L7.5 9.793 6.354 8.646a.5.5 0 1 0-.708.708l1.5 1.5a.5.5 0 0 0 .708 0l3-3z" />
                                </svg>
                                <p class="mb-0 mt-2">Anexo 2 <em>(Opcional)</em></p>
                                <small>Lo subiste el <span id="fecha-creacion-documento-anexo2-provincial"></span></small>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

<script type="text/javascript" src="${context}/locals/js/documents/came.documents.js"></script>
[/@main_page]