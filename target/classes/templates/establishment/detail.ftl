
  <!-- Contenido escritorio-->
  <div class="col-md-12 vis-des px-0">
    <div class="row">
        <div class="col-12">
            <a id="detail-back" href="#"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chevron-left" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z" />
                </svg> Volver</a>
        </div>

        <div class="col-6 mt-3">
            <h3 class="float-left">Ficha del establecimiento</h3>
        </div>

        <div class="col-6">
            <h2 class="float-right"><span id="establecimiento-detalle-nombre"></span></h2>
        </div>
        <div class="col-12">
            <!-- <a href="#" class="fon-14 px-0 float-right"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-file-earmark-excel" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path d="M4 0h5.5v1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V4.5h1V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2z" />
                    <path d="M9.5 3V0L14 4.5h-3A1.5 1.5 0 0 1 9.5 3z" />
                    <path fill-rule="evenodd" d="M5.18 6.616a.5.5 0 0 1 .704.064L8 9.219l2.116-2.54a.5.5 0 1 1 .768.641L8.651 10l2.233 2.68a.5.5 0 0 1-.768.64L8 10.781l-2.116 2.54a.5.5 0 0 1-.768-.641L7.349 10 5.116 7.32a.5.5 0 0 1 .064-.704z" />
                </svg> Exportar datos</a> -->
        </div>
    </div>

    <div class="row mt-3 ">
        <div class="col-md-4 ">
            <div class="rounded p-2 bg-light">
                <iframe id="establecimiento-location-iframe" width="100%" height="300" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
               
                    <p class="mb-0">
                        Dirección: <strong id="establecimiento-detalle-direccion"></strong><br>
                        Región: <strong id="establecimiento-detalle-region"></strong><br>
                        Deprov: <strong id="establecimiento-detalle-deprov"></strong><br>
                        Comuna: <strong id="establecimiento-detalle-comuna"></strong><br>
                        Email: <strong id="establecimiento-detalle-email"></strong><br>
                        Teléfono: <strong id="establecimiento-detalle-telefono"></strong>
                    </p>
               
            </div>
        </div>
        <div class="col-8 px-0">
            <div class="row">
                <div class="col-md-6">
                    <div class="rounded p-2 bg-light">
                        <div class="row">
                            <div class="col-12 text-center">
                                <svg width="5em" height="5em" viewBox="0 0 16 16" class="bi bi-building mt-2" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M14.763.075A.5.5 0 0 1 15 .5v15a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5V14h-1v1.5a.5.5 0 0 1-.5.5h-9a.5.5 0 0 1-.5-.5V10a.5.5 0 0 1 .342-.474L6 7.64V4.5a.5.5 0 0 1 .276-.447l8-4a.5.5 0 0 1 .487.022zM6 8.694L1 10.36V15h5V8.694zM7 15h2v-1.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 .5.5V15h2V1.309l-7 3.5V15z" />
                                    <path d="M2 11h1v1H2v-1zm2 0h1v1H4v-1zm-2 2h1v1H2v-1zm2 0h1v1H4v-1zm4-4h1v1H8V9zm2 0h1v1h-1V9zm-2 2h1v1H8v-1zm2 0h1v1h-1v-1zm2-2h1v1h-1V9zm0 2h1v1h-1v-1zM8 7h1v1H8V7zm2 0h1v1h-1V7zm2 0h1v1h-1V7zM8 5h1v1H8V5zm2 0h1v1h-1V5zm2 0h1v1h-1V5zm0-2h1v1h-1V3z" />
                                </svg>
                                <h5 class="mt-3"><strong id="establecimiento-detalle-sostenedor-nombre"></strong></h4>
                                Sostenedor
                                <hr>
                            </div>
                            <div class="col-12">
                                <p>
                                    Rut : <strong id="establecimiento-detalle-sostenedor-rut"></strong><br>
                                    Teléfono: <strong id="establecimiento-detalle-sostenedor-telefono"></strong><br>
                                    Email : <strong id="establecimiento-detalle-sostenedor-email"></strong>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-6 h-50 pb-4">
                    <div class="rounded p-2 bg-light">
                        <div class="row">
                            <div class="col-12 text-center">
                                <svg width="5em" height="5em" viewBox="0 0 16 16" class="bi bi-person-circle mt-2" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M13.468 12.37C12.758 11.226 11.195 10 8 10s-4.757 1.225-5.468 2.37A6.987 6.987 0 0 0 8 15a6.987 6.987 0 0 0 5.468-2.63z" />
                                    <path fill-rule="evenodd" d="M8 9a3 3 0 1 0 0-6 3 3 0 0 0 0 6z" />
                                    <path fill-rule="evenodd" d="M8 1a7 7 0 1 0 0 14A7 7 0 0 0 8 1zM0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8z" />
                                </svg>
                                <h5 class="mt-3"><strong id="establecimiento-detalle-director-nombre"></strong></h4>
                                Director
                                <hr>
                            </div>
                            <div class="col-12">
                                <p>
                                    Rut : <strong id="establecimiento-detalle-director-rut"></strong><br>
                                    Teléfono: <strong id="establecimiento-detalle-director-telefono"></strong><br>
                                    Email : <strong id="establecimiento-detalle-director-email"></strong><br>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-12 h-50">
                    <div class="rounded p-2 bg-light">
                        <div class="row">
                            <div class="col-6">
                                <p class="mb-0 mt-3 mb-3">
                                    RBD: <strong id="establecimiento-detalle-establecimiento-rbd"></strong> <br>
                                    Estado: <strong id="establecimiento-detalle-establecimiento-estado"></strong> <br>
                                    Clasificación SEP: <strong id="establecimiento-detalle-establecimiento-clasificacion-sep"></strong>
                                </p>
                            </div>
                            <div class="col-6">
                                <p class="mb-0 mt-3 mb-3">
                                    Rurabilidad: <strong id="establecimiento-detalle-establecimiento-rurabilidad"></strong> <br>
                                    Dependencias: <strong id="establecimiento-detalle-establecimiento-dependencias"></strong> <br>
                                    Categoria Basica: <strong id="establecimiento-detalle-establecimiento-categoria-basica"></strong> <br>
                                    Categoria Media: <strong id="establecimiento-detalle-establecimiento-categoria-media"></strong>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="row mt-4">
        <div class="col-8">
            <div class="col-12 px-0">
                <h2>Matrículas</h2>
                <!-- <a href="#" class="fon-14 px-0 float-right"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-file-earmark-excel" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path d="M4 0h5.5v1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V4.5h1V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2z" />
                        <path d="M9.5 3V0L14 4.5h-3A1.5 1.5 0 0 1 9.5 3z" />
                        <path fill-rule="evenodd" d="M5.18 6.616a.5.5 0 0 1 .704.064L8 9.219l2.116-2.54a.5.5 0 1 1 .768.641L8.651 10l2.233 2.68a.5.5 0 0 1-.768.64L8 10.781l-2.116 2.54a.5.5 0 0 1-.768-.641L7.349 10 5.116 7.32a.5.5 0 0 1 .064-.704z" />
                    </svg> Exportar matrículas</a> -->
            </div>
            <!--BÁSICA-->
            <div class="col-12 mt-5 px-0">
                <div class="table-responsive">
                    <table class="table mine table-hover table-bordered table-sm table-striped ">
                        <thead>
                            <tr>
                                <th scope="col">Nivel</th>
                                <th scope="col">Hombres</th>
                                <th scope="col">Mujeres</th>
                                <th scope="col">Total</th>
                            </tr>
                        </thead>
                        <tbody class="table-sm">
                            <!-- <tr>
                                <td>1° Básico</td>
                                <td>10</td>
                                <td>10</td>
                                <td>20</td>
                            </tr>
                            <tr>
                                <td>2° Básico</td>
                                <td>10</td>
                                <td>10</td>
                                <td>20</td>
                            </tr>
                            <tr>
                                <td>3° Básico</td>
                                <td>10</td>
                                <td>10</td>
                                <td>20</td>
                            </tr>
                            <tr>
                                <td>4° Básico</td>
                                <td>10</td>
                                <td>10</td>
                                <td>20</td>
                            </tr>
                            <tr>
                                <td>5° Básico</td>
                                <td>10</td>
                                <td>10</td>
                                <td>20</td>
                            </tr>
                            <tr>
                                <td>6° Básico</td>
                                <td>10</td>
                                <td>10</td>
                                <td>20</td>
                            </tr>
                            <tr>
                                <td>7° Básico</td>
                                <td>10</td>
                                <td>10</td>
                                <td>20</td>
                            </tr>
                            <tr>
                                <td>8° Básico</td>
                                <td>10</td>
                                <td>10</td>
                                <td>20</td>
                            </tr>
                            <tr>
                                <td><b>Total</b></td>
                                <td><b>80</b></td>
                                <td><b>80</b></td>
                                <td><b>160</b></td>
                            </tr> -->
                        </tbody>
                    </table>
                </div>

            </div>
            <!--⁄MEDIO-->
            <div class="col-12 mt-2 px-0">
                <div class="table-responsive">
                    <table class="table mine table-hover table-bordered table-sm table-striped ">
                        <thead>
                            <tr>
                                <th scope="col">Nivel</th>
                                <th scope="col">Hombres</th>
                                <th scope="col">Mujeres</th>
                                <th scope="col">Total</th>
                            </tr>
                        </thead>
                        <tbody class="table-sm">
                            <!-- <tr>
                                <td>1° Medio</td>
                                <td>10</td>
                                <td>10</td>
                                <td>20</td>
                            </tr>
                            <tr>
                                <td>2° Medio</td>
                                <td>10</td>
                                <td>10</td>
                                <td>20</td>
                            </tr>
                            <tr>
                                <td>3° Medio</td>
                                <td>10</td>
                                <td>10</td>
                                <td>20</td>
                            </tr>
                            <tr>
                                <td>4° Medio</td>
                                <td>10</td>
                                <td>10</td>
                                <td>20</td>
                            </tr>
                            <tr>
                                <td><b>Total</b></td>
                                <td><b>80</b></td>
                                <td><b>80</b></td>
                                <td><b>160</b></td>
                            </tr> -->
                        </tbody>
                    </table>
                </div>
            </div>
            <!--ETNIA-->
            <div class="col-12 mt-2 px-0">
                <div class="table-responsive">
                    <table class="table mine table-hover table-bordered table-sm table-striped ">
                        <thead>
                            <tr>
                                <th scope="col">Etnia</th>
                                <th scope="col">Hombres</th>
                                <th scope="col">Mujeres</th>
                                <th scope="col">Total</th>
                            </tr>
                        </thead>
                        <tbody class="table-sm">
                            <!-- <tr>
                                <td>Aymara</td>
                                <td>10</td>
                                <td>10</td>
                                <td>20</td>
                            </tr>
                            <tr>
                                <td>Mapuche</td>
                                <td>10</td>
                                <td>10</td>
                                <td>20</td>
                            </tr>
                            <tr>
                                <td>No pertenece</td>
                                <td>10</td>
                                <td>10</td>
                                <td>20</td>
                            </tr>
                            <tr>
                                <td><b>Total</b></td>
                                <td><b>80</b></td>
                                <td><b>80</b></td>
                                <td><b>160</b></td>
                            </tr> -->
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="col-4">
            <div class="col-12 px-0">
                <h2>Categorización</h2>
                <!-- <a href="#" class="fon-14 px-0 float-right"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-file-earmark-excel" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path d="M4 0h5.5v1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V4.5h1V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2z" />
                        <path d="M9.5 3V0L14 4.5h-3A1.5 1.5 0 0 1 9.5 3z" />
                        <path fill-rule="evenodd" d="M5.18 6.616a.5.5 0 0 1 .704.064L8 9.219l2.116-2.54a.5.5 0 1 1 .768.641L8.651 10l2.233 2.68a.5.5 0 0 1-.768.64L8 10.781l-2.116 2.54a.5.5 0 0 1-.768-.641L7.349 10 5.116 7.32a.5.5 0 0 1 .064-.704z" />
                    </svg> Exportar categorización</a> -->
            </div>

            <div class="col-12 mt-5 px-0">
                <div class="table-responsive">
                    <table id="table-detail-orden" class="table mine table-hover table-bordered table-sm table-striped ">
                        <thead>
                            <tr>
                                <th scope="col">Año</th>
                                <th scope="col">Nivel</th>
                                <th scope="col">Categorización</th>
                            </tr>
                        </thead>
                        <tbody class=" table-sm">                            
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>
