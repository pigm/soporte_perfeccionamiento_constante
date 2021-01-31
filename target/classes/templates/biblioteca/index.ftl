[#include '*/commons/page-structure.ftl' /]
[@main_page]
<div class="container mt-3">
	<div class="row">
		<div class="col-md-6 ">
			<ol class="breadcrumb bg-white p-0">
				Estás&nbsp;en:&nbsp;

				<li><a href="#" class="active"> Incio </a> </li> &nbsp;
				<li> > Biblioteca</li>
			</ol>
		</div>
		<div class="col-3"></div>
		<div class="col-md-3 vis-des">
			<button class="btn btn-sm btn-success float-right px-4" id="button-agregar-documento">
				<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-plus-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					<path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
					<path fill-rule="evenodd" d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
				</svg> &nbsp; Agregar documentos
			</button>
		</div>

	</div>
</div>
<!-- CONTENIDO ESCRITORIO-->
<div class="container">
	<div class="row">
		<!-- Contenido -->
		<div class="col-md-12">
			<h2>Bibliotecas de apoyo</h2>
		</div>		

		<div id="search-result" style="display: none;" class="col-12">
			<!--Titulo resultado búsqueda-->
			<div class="row mt-3">
				<div class="col-6">
					Resultado de la búsqueda (<strong id="count-directory">0</strong> resultados)
				</div>
				<div class="col-6">
					<a id="button-nueva-busqueda" class="btn btn-link float-right px-0"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
							<path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
							<path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
						</svg> Buscar nuevamente</a>
				</div>
			</div>

			<!--Tabla de resultado-->
			<div class="row mt-1">
				<div class="table-responsive col-12">
					<table id="table-biblioteca" class="table mine table-hover table-bordered table-sm">
						<thead>
							<tr>
								<th scope="col">Nombre del documento</th>
								<th scope="col">Categoría</th>
								<th scope="col">Perfil</th>
								<th scope="col">Región</th>
								<th scope="col">Deprov</th>
								<th scope="col">Fecha publicación</th>
							</tr>
						</thead>
						<tbody class=" table-sm">

						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div id="search-biblioteca" class="row">
			<!--Año-->
			<div class="col-md-3">
				<div class="form-group">
					<label for="Año">Año</label>
					<select id="selector-year" class="custom-select custom-select-sm" disabled>
						[#list years as year]
						[#if year?string == currentYear?c]
						<option value="${year?string}" selected>${year?string}</option>
						[#else]
						<option value="${year?string}">${year?string}</option>
						[/#if]
						[/#list]
					</select>
				</div>
			</div>

			<!--Región-->
			<div class="col-md-3">
				<div class="form-group">
					<label for="nombrePerfil">Región</label>
					<select id="selector-region" class="custom-select custom-select-sm">
						<option value="" selected>Seleccionar</option>
						[#list regiones as r]
						<option value="${r.value}">${r.displayText}</option>
						[/#list]
					</select>
				</div>
			</div>
			<!--Deprov-->
			<div class="col-md-3">
				<form>
					<div class="form-group">
						<label for="nombrePerfil">Deprov</label>
						<select id="selector-deprov" class="custom-select custom-select-sm">
							<option value="" selected>Seleccionar</option>
						</select>
					</div>
				</form>
			</div>

			<!--Categoría-->
			<div class="col-md-3">
				<div class="form-group">
					<label for="categoría">Categoría</label>
					<select id="selector-categoria" class="custom-select custom-select-sm">
						<option value="" selected>Seleccionar</option> [#list tipoCategoria as tc]
						<option value="${tc.value}">${tc.displayText}</option> [/#list]
					</select>
				</div>
			</div>

			<!--Perfil-->
			<div class="col-md-3">
				<form>
					<div class="form-group">
						<label for="Perfil">Perfil</label>
						<select id="selector-perfil" class="custom-select custom-select-sm">
							<option value="" selected>Seleccionar</option> [#list profiles as pf]
							<option value="${pf.value}">${pf.displayText}</option> [/#list]
						</select>
					</div>
				</form>
			</div>

			<!--Nombre del documento-->
			<div class="col-md-6">
				<div class="form-group">
					<div class="form-group">
						<label for="nombreDoc">Nombre documento</label>
						<input id="input-nombre" type="text" class="form-control form-control-sm" aria-describedby="nombreDoc">
					</div>
				</div>
			</div>

			<div class="col-md-3">
				<button id="button-buscar-biblioteca" class="btn btn-primary btn-sm float-right px-4 mt-4">
					<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
						<path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
						<path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
					</svg> &nbsp; Buscar</button>

			</div>
		</div>
	</div>
</div>
</div>



<!-- AGREGAR DOCUMENTOS -->
<div class="modal fade" id="sbuirDoc" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Documento de apoyo</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<div id="content-biblioteca-modal" class="modal-body">
				<div class="row">
					<!--Nombre del documento-->
					<div class="col-11">
						<div class="form-group">
							<label for="nombreDoc">Nombre documento</label>
							<input id="input-add-nombre" type="text" class="form-control form-control-sm" aria-describedby="nombreDoc">
							<div class="invalid-feedback">
								<p>Debes ingresar un nombre valido.</p>
							</div>
						</div>
					</div>

					<!--Información-->
					<div class="col-1">
						<label for="">&nbsp;</label> <br>
						<a href="#" data-placement="top" data-toggle="popover" data-trigger="hover" data-content='<div class="col-12"> <p>El nombre del documento no pueder ser mayor a 100 caracteres.</p> </div>'>
							<svg width="1.7em" height="1.7em" viewBox="0 0 16 16" class="bi bi-info-circle-fill float-right text-warning" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
								<path fill-rule="evenodd" d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412l-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM8 5.5a1 1 0 1 0 0-2 1 1 0 0 0 0 2z" />
							</svg>
						</a>
					</div>

					<!--Categoría-->
					<div class="col-6">
						<div class="form-group">
							<label for="categoría">Categoría</label>
							<select id="selector-add-categoria" class="custom-select custom-select-sm">
								<option value="" selected>Seleccionar</option> [#list tipoCategoria as tc]
								<option value="${tc.value}">${tc.displayText}</option> [/#list]
							</select>
							<div class="invalid-feedback">
								<p>Debes seleccionar una categoría.</p>
							</div>
						</div>
					</div>

					<!--Región-->
					<div class="col-6">
						<div class="form-group">
							<label for="región">Región</label>
							<select id="selector-add-region" class="custom-select custom-select-sm">
								<option value="" selected>Seleccionar</option> [#list regiones as rg]
								<option value="${rg.value}">${rg.displayText}</option> [/#list]
							</select>
							<div class="invalid-feedback">
								<p>Debes seleccionar una región.</p>
							</div>
						</div>
					</div>

					<!--Deprov-->
					<div class="col-6">
						<div class="form-group">
							<label for="selector-add-deprov">Deprov</label>
							<select id="selector-add-deprov" class="custom-select custom-select-sm">
								<option value="" selected>Seleccionar</option>
							</select>
							<div class="invalid-feedback">
								<p>Debes seleccionar una dirección provincial.</p>
							</div>
						</div>
					</div>

				</div>

				<!--Seleccionar Perfil-->
				<div class="row">
					<div class="col-12">
						<form>
							<div class="control-group">
								<label for="select-add-perfil">Seleciona los perfiles que podrán ver el documento</label>
								<select id="select-add-perfil" multiple name="state[]" class="demo-default" placeholder="Busca o seleciona perfiles" style="width:100%">
									[#list profiles as pf]
									<option value="${pf.value}">${pf.displayText}</option>
									[/#list]
								</select>
								<div class="invalid-feedback">
									<p>Debe seleccionar a lo menos un elemento.</p>
								</div>
							</div>
						</form>
					</div>
				</div>

				<!--Subir documentos-->
				<div class="row mt-3" id="contenedor-subir-documentos">
					<div class="col-12">
						<div class="form-group files mb-0">
							<label>Selecciona documentos: </label>
							<input id="control-add-doc" type="file" class="form-control" multiple="">
							<div class="invalid-feedback">
								<p>Debe agregar un archivo.</p>
							</div>
						</div>
						<label for="docApoyo" class="text-muted float-right mt-1">Tipo archivo: Excel, Word o PDF con un máximo de 10MB</label>
					</div>
				</div>

				<div class="row mt-3" id="contenedor-descargar-documentos">
					<div class="col-12">
						<a id="descargar-documento" href="#">Descargar documento</a>
					</div>
				</div>

			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
				<button id="button-agregar-biblioteca" type="button" class="btn btn-primary">Guardar</button>
			</div>

		</div>
	</div>
</div>

<script>	
	var $select = $('#select-add-perfil').selectize({
		create: true
	});
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
<script type="text/javascript" src="${context}/locals/js/biblioteca/came.biblioteca.js"></script>
[/@main_page]