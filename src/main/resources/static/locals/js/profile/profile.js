'use strict';
var came = came || {};


came.profile = (function () {
    var readOnly = false;
    var urls = {
        api: {
            getProfiles: came.contexto + "/profile/getProfiles",
            getModulos: came.contexto + "/profile/getModulos",
            setProfile: came.contexto + "/profile/setProfile",
            getProfile: came.contexto + "/profile/getProfile",
            setProfileStatus: came.contexto + "/profile/setProfileStatus"
        }
    };

    var getIds = {
        agregar: "#btnAgregarPerfil",
        editar: ".editarPerfil",
        lista: "#tableProfiles",
        modalProfile: "#agregarPerfil",
        acceso: ".acceso",
        nivel: ".nivel",
        nivelName: "nivel",
        habilitar: ".habilitar",
        descripcion: "#textboxDescripcion",
        lenDescription: "#lenDescription",
        nombre: "#textboxNombre",
        guardar: "#btnGuardar",
        goup_nivel: "#goup-nivel",
        validators: {
            errorNombre: "#errorNombre",
            errorDescripcion: "#errorDescripcion",
            errorNivel: "#errorNivel"
        }
    };

    var dataTable = null;
    var profileObject = {
        idPerfil: '',
        nombre: '',
        descripcion: '',
        idNivel: '',
        accesos: []
    };

    var initListener = function () {
        $(document)
            .on("click", getIds.agregar, function () {
                newProfile();
            })
            .on("click", getIds.editar, function () {
                editProfile(this);
            })
            .on("click", getIds.acceso, function () {
                addAcceso($(this).closest('tr').attr("id"), $(this).val());
            })
            .on("click", getIds.nivel, function () {               
                came.main.isValidGroup(this, getIds.goup_nivel);
            })
            .on("click", getIds.guardar, function () {
                setProfile();
            })
            .on("click", getIds.habilitar, function () {
                setStatus(this);
            })
            .on("keypress", getIds.descripcion, function () {
                setlenDescripcion(this);
            })
            .on("change", getIds.nombre, function () {
                came.main.isValidControl(this);
                // isValidControl(this);
            })
            .on("change keyup paste", getIds.descripcion, function () {
                came.main.isValidControl(this);
            })
            .on("hidden.bs.modal", getIds.modalProfile, function () {
                cleanObject();
            });
    };

    var cleanObject = function () {
        profileObject.idPerfil = '';
        profileObject.nombre = '';
        profileObject.descripcion = '';
        profileObject.idNivel = '';
        profileObject.accesos = [];

        $(getIds.nombre).val('');
        $(getIds.nombre).attr("disabled", false);
        $(getIds.descripcion).val('');
        // niveles
        var nivelItems = $(getIds.nivel);
        for (var i = 0; i < nivelItems.length; i++) {
            $(nivelItems[i]).attr('checked', false);
        }
        // accesos
        var accesoItems = $(getIds.acceso);
        for (var x = 0; x < accesoItems.length; x++) {
            if ($(accesoItems[x]).val() === '2415742140157002753') {
                console.log($(accesoItems[x]).attr('name'));
                console.log($(accesoItems[x]).val());
                console.log($(accesoItems[x]).attr('checked'));
                $(accesoItems[x]).prop("checked", true);
            } else {
                $(accesoItems[x]).prop('checked', false);
            }
        }

        $(getIds.goup_nivel).removeClass("is-invalid");
        $(getIds.goup_nivel + " :input").removeClass("is-invalid");
        $(getIds.nombre).removeClass("is-invalid");
        $(getIds.descripcion).removeClass("is-invalid");
    }

    var addAcceso = function (id, referred) {
        var existePaso = false;
        for (var i = 0; i < profileObject.accesos.length; i++) {
            //console.log(profileObject.accesos[i]);
            if (profileObject.accesos[i].id === id) {
                profileObject.accesos[i].referred = referred;
                existePaso = true;
            }
        }
        if (!existePaso) {
            profileObject.accesos.push(
                {
                    id: id,
                    referred: referred
                });
        }
    }

    var setlenDescripcion = function (sender) {
        const lenPaso = $(sender).val().length + 1;
        console.log(lenPaso);
        if (lenPaso <= 150) {
            $(getIds.lenDescription).text(lenPaso);
        } else {
            return;
        }

    }

    var loadProfile = function (record) {
        console.log(record);
        profileObject = record;
        console.log(profileObject);

        $(getIds.nombre).val(record.nombre);
        $(getIds.nombre).attr("disabled", true);
        $(getIds.descripcion).val(record.descripcion);
        setNivelProfile(record.idNivel);
        setAccesosProfile(record.accesos);
    }

    var setStatus = function (sender) {

        var tr = $(sender).closest('tr');
        var row = dataTable.row(tr);
        var rowData = row.data();

        console.log({ idProfile: rowData.idPerfil, status: $(sender).is(":checked") });

        $.ajax({
            type: "POST",
            url: urls.api.setProfileStatus,
            dataType: "json",
            contentType: 'application/json',
            data: JSON.stringify({ idProfile: rowData.idStr, status: $(sender).is(":checked") })
        }).done(function (msg) {
            console.log("response done", msg);
            came.notify.showSucces("Perfil", "Su solicitud se ha procesado correectamente.");
            // cierro el modal.           
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            // TODO: aqui se debe agregar un mensaje en caso de error.
            came.notify.showError("Perfil", "El perfil indicado ha presentado errores.");
            return false;
        });

    }

    var setProfile = function () {
        var isValid = true;
       
        profileObject.nombre = $(getIds.nombre).val();
        if (profileObject.nombre === '') {
            console.log($(getIds.nombre).val());
            $(getIds.nombre).addClass("is-invalid");
            isValid = false;
        } else {
            $(getIds.nombre).removeClass("is-invalid");
        }

        profileObject.descripcion = $(getIds.descripcion).val();
        if (profileObject.descripcion === '') {
            console.log($(getIds.descripcion).val());
            $(getIds.descripcion).addClass("is-invalid");
            isValid = false;
        } else {
            $(getIds.descripcion).removeClass("is-invalid");
        }        

        profileObject.idNivel = $("input[name='" + getIds.nivelName + "']:checked").val();
        console.log(profileObject);
        if (profileObject.idNivel === undefined || profileObject.idNivel === '') {
            $(getIds.goup_nivel).addClass("is-invalid");
            $(getIds.goup_nivel + " :input").addClass("is-invalid");
            isValid = false;
        } else {
            $(getIds.goup_nivel).removeClass("is-invalid");
            $(getIds.goup_nivel + " :input").removeClass("is-invalid");
        }

        console.log(profileObject);
        if (isValid) {
            $.ajax({
                type: "POST",
                url: urls.api.setProfile,
                dataType: "json",
                contentType: 'application/json',
                data: JSON.stringify(profileObject)
            }).done(function (msg) {
                console.log("response done", msg);
                came.notify.showSucces("Perfil", "Su solicitud se ha procesado correectamente.");
                // cierro el modal.
                initList();
                closeProfile();
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                // TODO: aqui se debe agregar un mensaje en caso de error.
                came.notify.showError("Perfil", "El perfil indicado ha presentado errores.");
                return false;
            });
        } else {
            return false;
        }
    }

    var setNivelProfile = function (idNivel) {
        var nivelItems = $(getIds.nivel);
        for (var i = 0; i < nivelItems.length; i++) {
            if ($(nivelItems[i]).val() === idNivel) {
                $(nivelItems[i]).prop('checked', true);
            }
        }
    }

    var setAccesosProfile = function (accesos) {
        var accesoItems = $(getIds.acceso);
        if (accesos.length > 0) {
            for (var i = 0; i < accesos.length; i++) {
                //console.log(accesos[i]);
                for (var x = 0; x < accesoItems.length; x++) {
                    if ($(accesoItems[x]).closest('tr').attr("id") === accesos[i].id &&
                        $(accesoItems[x]).val() === accesos[i].referred) {
                        $(accesoItems[x]).prop('checked', true);
                    }
                }
            }
        } else {
            for (var y = 0; y < accesoItems.length; y++) {
                if ($(accesoItems[y]).val() === '2415742140157002753') {
                    console.log($(accesoItems[y]).val());
                    $(accesoItems[y]).prop('checked', true);
                    $(accesoItems[y]).click();
                }
            }
        }
    }

    // Levanta el modal para crear nuevo perfil
    var newProfile = function () {

        if(readOnly){
            came.notify.showError("Perfil", "No tienes permitido crear o modificar perfiles.");
        } else{
            // accesos
            var accesoItems = $(getIds.acceso);
            for (var x = 0; x < accesoItems.length; x++) {
                if ($(accesoItems[x]).val() === '2415742140157002753') {
                    console.log($(accesoItems[x]).attr('name'));
                    console.log($(accesoItems[x]).val());
                    console.log($(accesoItems[x]).attr('checked'));
                    $(accesoItems[x]).prop("checked", true);
                } else {
                    $(accesoItems[x]).prop('checked', false);
                }
            }

            $(getIds.modalProfile).modal('show');
        }        
    };

    var closeProfile = function () {
        $(getIds.modalProfile).modal('toggle');
    }

    var editProfile = function (sender) {
        var tr = $(sender).closest('tr');
        var row = dataTable.row(tr);
        var rowData = row.data();

        if (rowData.idStr !== '') {
            $.ajax({
                type: "GET",
                url: urls.api.getProfile,
                dataType: "json",
                //contentType: 'application/json',
                data: { idProfile: rowData.idStr }
            }).done(function (response) {
                console.error("response done", response);
                //alert(JSON.stringify(response));
                loadProfile(response);

                $(getIds.modalProfile).modal('show');
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                return false;
            });
        } else {
            alert("Aqui un error.")
        }
    };

    var initList = function () {
        if (dataTable !== null) {
            dataTable.destroy();
        }
        dataTable = $(getIds.lista)
            .DataTable({
                ordering: true,
                searching: true,
                paging: true,
                info: true,
                responsive: {
                    breakpoints: [
                      {name: 'bigdesktop', width: Infinity},
                      {name: 'meddesktop', width: 1480},
                      {name: 'smalldesktop', width: 1280},
                      {name: 'medium', width: 1188},
                      {name: 'tabletl', width: 1024},
                      {name: 'btwtabllandp', width: 848},
                      {name: 'tabletp', width: 768},
                      {name: 'mobilel', width: 480},
                      {name: 'mobilep', width: 320}
                    ]
                  },
                language: {
                    "decimal": "",
                    "emptyTable": "No data available in table",
                    "info": "Mostrando pagina _PAGE_ de _PAGES_",
                    "infoEmpty": "Mostrando Pagina 0 de 0",
                    "infoFiltered": "(filtered from _MAX_ total entries)",
                    "infoPostFix": "",
                    "thousands": ",",
                    "lengthMenu": "Mostrar _MENU_ registros",
                    "loadingRecords": "Cargando...",
                    "processing": "Procesando...",
                    "search": "Buscar:",
                    "zeroRecords": "No matching records found",
                    "paginate": {
                        "first": "Primero",
                        "last": "Ultimo",
                        "next": "Siguiente",
                        "previous": "Anterior"
                    },
                    "aria": {
                        "sortAscending": ": activate to sort column ascending",
                        "sortDescending": ": activate to sort column descending"
                    }
                },
                columns: [
                    { data: 'idStr', visible: false },
                    {
                        title: "Nombre",
                        render: function (data, type, full, meta) {
                            //console.log(full);
                            return "<a href='#' class='editarPerfil'>" + full.nombre + "</a>"
                        }
                    },
                    { data: 'perfilNivel.nombre', title: "Nivel" },
                    { 
                        // data: "descripcion", 
                        title: "Descripci&oacute;n",
                        render: function (data, type, full, meta) {
                          
                            return (full.descripcion.length > 25) ? '<a href="#" class="editarPerfil">' + (full.descripcion.substring(0, 25)) + '...</a>' : '<span>' + full.descripcion + '</span>'
                        }
                    },
                    {
                        width: "5%",
                        title: "Habilitado",
                        sortable: true,
                        render: function (data, type, full, meta) {
                            //console.log(full);
                            var buttonID = full.idStr;
                            var checked = full.habilitado ? 'checked' : '';
                            var readOnlyPaso = readOnly ? 'disabled' : '';
                            //return '<a id='+buttonID+' class="btn btn-success rolloverBtn" role="button">Rollover</a>';
                            return "<div class='custom-control custom-switch text-center'> <input type='checkbox' class='custom-control-input habilitar' id='" + buttonID + "' " + checked + " select " + readOnlyPaso + "> <label class='custom-control-label' for='" + buttonID + "'></label></div>"
                        }
                    }],
                ajax: {
                    url: urls.api.getProfiles,
                    dataSrc: function (json) {
                        console.log(json);
                        return json;
                    }
                }
            });
    };

    function setViewAcces(){
        var menuPso = came.storage.getSessionStorage('menu');
        var subMenuAdmin = menuPso.find(e => e.idModule == '2416198290916770825');
        console.log(subMenuAdmin);
        if (subMenuAdmin){
            var profile = subMenuAdmin.subMenu.find(e => e.idSubModule == '2416254586118472721');
            console.log(profile);
            readOnly = profile.readOnly;
            if(readOnly){
                $("#perfil-content-detail :input").prop("disabled", true);
                $(getIds.guardar).prop("disabled", true);
            }
        }        
    }

    function init() {
        setViewAcces();
        initListener();
        initList();        
    }

    return {
        init: init
    };
})();

$(document).ready(function () {
    came.profile.init();
});