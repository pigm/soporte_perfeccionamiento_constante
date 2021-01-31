'use strict';
var came = came || {};

came.users = (function () {
    var dataTable = null;
    var readOnly = false;

    var urls = {
        api: {
            getUsers: came.contexto + "/users/getUsers",
            getProvincias: came.contexto + "/users/getProvincias",
            findUserLdap: came.contexto + "/users/findUserLdap",
            setUser: came.contexto + "/users/setUser",
            getUsuario: came.contexto + "/users/getUsuario",
            setUserStatus: came.contexto + "/users/setUserStatus"
        }
    };

    var getIds = {
        findUser: "#userNameText",
        agregar: "#btnAgregar",
        editar: ".editarUsuario",
        setUser: "#buttonSetUser",
        lista: "#table",
        modal: "#agregarUsuario",
        habilitar: ".habilitar",
        descripcion: "#textboxDescripcion",
        nombre: "#textboxNombre",
        guardar: "#btnGuardar",
        findUserLdap: "#findUserLdap",
        perfilUsuario: "#perfilUsuario",
        region: "#region",
        provincia: "#provincia",
        habilitado: "#habilitado",
        findUserLdapRow: ".find-user-ldap",
        headerModal: "#staticBackdropLabel",
        userLdap: {
            userName: "#userUserName",
            rut: "#userRut",
            nombre: "#userNombre",
            apellidoPaterno: "#userApellidoPaterno",
            apellidoMaterno: "#userApellidoMaterno",
            email: "#userEmail"
        },
        validators: {
            errorNombre: "#errorNombre",
            errorPerfil: "#errorPefil",
            errorRegion: "#errorRegion",
            errorProvincia: "#errorProvincia"
        }
    };

    var initListener = function () {
        $(document)
            .on("click", getIds.agregar, function () {
                newUsuario(this);
            })
            .on("click", getIds.findUserLdap, function () {
                findUserLdap(this);
            })
            .on("click", getIds.editar, function () {
                editUsuario(this);
            })
            .on("click", getIds.setUser, function () {
                setUser(this);
            })
            .on("change", getIds.region, function () {
                getProvincias(this);
            })
            .on("change", getIds.perfilUsuario, function () {
                validaPerfil(this);
            })
            .on("change", getIds.provincia, function () {
                validaPerfil(this);
            })
            .on("change", getIds.region, function () {
                validaPerfil(this);
            })
            .on("change", getIds.habilitado, function () {
                setStatusChk(this);
            })
            .on("change", getIds.habilitar, function () {
                setStatusTr(this);
            })
            .on("hidden.bs.modal", getIds.modal, function () {
                cleanModal();
            });
    };

    var findUserLdap = function (sender) {        
        came.main.setValidControl($(getIds.findUser));      
        $(sender).attr('disabled', true);
        cleanModal();

        const username = $(getIds.findUser).val();
        if (username !== '') {
            $.ajax({
                type: "GET",
                url: urls.api.findUserLdap,
                dataType: "json",
                async: false,
                //contentType: 'application/json',
                data: { userName: username }
            }).done(function (response) {
                $(getIds.findUser).val('');
                console.log("response done", response);

                loadUser(response);
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
        } else {
            came.main.setInvalidControl($(getIds.findUser));      
        }

        $(sender).attr('disabled', false);
    }

    var getProvincias = function (sender) {

        $(getIds.provincia + " option").each(function () {
            $(this).remove();
        });

        var idRegion = $(sender).val();
        if (idRegion !== '') {
            $.ajax({
                type: "GET",
                url: urls.api.getProvincias,
                dataType: "json",
                async: false,
                //contentType: 'application/json',
                data: { idRegion: idRegion }
            }).done(function (response) {
                console.log("response done", JSON.stringify(response));
                $(getIds.provincia).append($('<option>').val('').text(''));
                for (var i = 0; i < response.length; i++) {
                    $(getIds.provincia).append($('<option>').val(response[i].value).text(response[i].displayText));
                }
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");                                
                return false;
            });
        }
    }

    var validaPerfil = function (sender) {
        console.log(sender);
        if ($(getIds.perfilUsuario).val() === '') {
            console.log($(getIds.perfilUsuario).val());
            came.main.setInvalidControl($(getIds.perfilUsuario));                    
        } else {
            came.main.setValidControl($(getIds.perfilUsuario));
            const nivelPaso = $(getIds.perfilUsuario).find("option:selected").attr('data-nivel');
            if (nivelPaso === "regional") {
                if ($(getIds.region).val() === '') {
                    console.log($(getIds.region).val());
                    came.main.setInvalidControl($(getIds.region));
                } else {
                    came.main.setValidControl($(getIds.region));
                }
            } else if (nivelPaso === "provincial") {
                if ($(getIds.region).val() === '') {
                    console.log($(getIds.region).val());
                    came.main.setInvalidControl($(getIds.region));                    
                } else {
                    came.main.setValidControl($(getIds.region));
                }

                if ($(getIds.provincia).val() === '' || $(getIds.provincia).val() === null) {
                    console.log($(getIds.provincia).val());
                    came.main.setInvalidControl($(getIds.provincia));                    
                } else {
                    came.main.setValidControl($(getIds.provincia));
                }
            } else {
                came.main.setValidControl($(getIds.provincia));
                came.main.setValidControl($(getIds.region));
            }
        }
    }

    var newUsuario = function () {
        if (readOnly) {
            came.notify.showError("Usuarios", "No tienes permitido crear o modificar usuarios.");
        } else {
            $(getIds.findUserLdapRow).show();
            $(getIds.modal).modal('show');
        }
    }

    var editUsuario = function (sender) {
        var tr = $(sender).closest('tr');
        var row = dataTable.row(tr);
        var rowData = row.data();

        $(getIds.findUserLdapRow).hide();

        if (rowData.idUsuario !== '') {
            $.ajax({
                type: "GET",
                url: urls.api.getUsuario,
                dataType: "json",
                //contentType: 'application/json',
                data: { idUsuario: rowData.idUsuario }
            }).done(function (response) {
                console.log("response done", response);
                //alert(JSON.stringify(response));                
                loadUser(response);

                $(getIds.modal).modal('show');
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");                                
                return false;
            });
        } else {
            alert("Aqui un error.");
        }
    }

    var setUser = function (sender) {
        var isValid = true;

        var userObject = {
            idUsuario: $(getIds.userLdap.userName).attr("data-idUsuario"),
            userName: $(getIds.userLdap.userName).text(),
            rut: $(getIds.userLdap.rut).text(),
            nombre: $(getIds.userLdap.nombre).text(),
            apellidoPaterno: $(getIds.userLdap.apellidoPaterno).text(),
            apellidoMaterno: $(getIds.userLdap.apellidoMaterno).text(),
            email: $(getIds.userLdap.email).text(),

            idPerfil: $(getIds.perfilUsuario).val(),
            idRegion: $(getIds.region).val(),
            idDeprov: $(getIds.provincia).val(),
            habilitado: $(getIds.habilitado).prop('checked')
        };

        if (userObject.userName === '') {
            came.main.isValidControl($("#userNameText"));
            isValid = false;
        } else {
            came.main.isValidControl($("#userNameText"));
        }

        if (userObject.idPerfil === '') {
            console.log($(getIds.perfilUsuario).val());
            came.main.isValidControl($(getIds.perfilUsuario));
            isValid = false;
        } else {
            came.main.isValidControl($(getIds.perfilUsuario));
            const nivelPaso = $(getIds.perfilUsuario).find("option:selected").attr('data-nivel');
            if (nivelPaso === "regional") {

                if (userObject.idRegion === '') {
                    console.log($(getIds.idRegion).val());
                    came.main.isValidControl($(getIds.region));                    
                    isValid = false;
                } else {
                    came.main.isValidControl($(getIds.region));
                }

            } else if (nivelPaso === "provincial") {
                if (userObject.idRegion === '') {
                    console.log($(getIds.idRegion).val());
                    came.main.isValidControl($(getIds.region));
                    isValid = false;
                } else {
                    came.main.isValidControl($(getIds.region));
                }

                if (userObject.idProvincia === '' || userObject.idProvincia === null) {
                    console.log($(getIds.idProvincia).val());
                    came.main.isValidControl($(getIds.provincia));
                    isValid = false;
                } else {
                    came.main.isValidControl($(getIds.provincia));
                }
            } else {
                came.main.isValidControl($(getIds.provincia));
                came.main.isValidControl($(getIds.region));
            }
        }

        console.log(userObject);
        if (isValid) {
            $.ajax({
                type: "POST",
                url: urls.api.setUser,
                dataType: "json",
                contentType: 'application/json',
                data: JSON.stringify(userObject)
            }).done(function (msg) {
                console.log("response done", msg);
                // cierro el modal.
                initList();
                closeModal();
                came.notify.showSucces("usuarios", "Su solicitud se ha procesado correctamente.");
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                // TODO: aqui se debe agregar un mensaje en caso de error.
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");                                
                return false;
            });
        } else {
            console.log('setUser invalid');
            return false;
        }
    }

    var loadUser = function (user) {
        $(getIds.headerModal).text('Modificar Usuario');
        $(getIds.userLdap.userName).attr("data-idUsuario", user.idUsuario);
        $(getIds.userLdap.userName).text(user.userName);
        $(getIds.userLdap.rut).text(user.rut);
        $(getIds.userLdap.nombre).text(user.nombre);
        $(getIds.userLdap.apellidoPaterno).text(user.apellidoPaterno);
        $(getIds.userLdap.apellidoMaterno).text(user.apellidoMaterno);
        $(getIds.userLdap.email).text(user.email);

        // set profile
        $(getIds.perfilUsuario).val(user.idPerfil).change();
        $(getIds.region).val(user.idRegion).change();

        $(getIds.provincia).val(user.idDeprov).change();

        $(getIds.habilitado).prop('checked', user.habilitado);
    }

    var cleanModal = function () {
        $(getIds.headerModal).text('Agregar Usuario');
        $(getIds.userLdap.userName).text('');
        $(getIds.userLdap.rut).text('');
        $(getIds.userLdap.nombre).text('');
        $(getIds.userLdap.apellidoPaterno).text('');
        $(getIds.userLdap.apellidoMaterno).text('');
        $(getIds.userLdap.email).text('');

        // set profile
        $(getIds.perfilUsuario).val('').change();
        $(getIds.region).val('').change();
        $(getIds.provincia).val('').change();

        $(getIds.habilitado).prop('checked', true);

        came.main.setValidControl($(getIds.perfilUsuario));
        came.main.setValidControl($(getIds.region));
        came.main.setValidControl($(getIds.provincia));
    }

    var closeModal = function () {
        $(getIds.modal).modal('toggle');
    }

    var initList = function () {
        if (dataTable !== null) {
            dataTable.destroy();
        }
        dataTable = $(getIds.lista)
            .DataTable({
                "ordering": true,
                "searching": true,
                "paging": true,
                "info": true,
                "language": {
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
                "columns": [
                    { "data": 'idUsuario', "visible": false },
                    {
                        "title": "Usuario",
                        "render": function (data, type, full, meta) {
                            //console.log(full);
                            return "<a href='#' class='editarUsuario'>" + full.username + "</a>"
                        }
                    },
                    { "data": 'apellidoPaterno', "title": "Apellido paterno" },
                    { "data": 'apellidoMaterno', "title": "Apellido materno" },
                    { "data": 'nombre', "title": "Nombre" },
                    { "data": 'rut', "title": "Rut" },
                    { "data": 'region', "title": "Region" },
                    { "data": 'deprov', "title": "Provincia" },
                    { "data": 'perfil', "title": "Perfil" },
                    { "data": 'lastConnection', "title": "Última conexión" },
                    {
                        "title": "Habilitar",
                        sortable: true,
                        "render": function (data, type, full, meta) {
                            //console.log(full);
                            var buttonID = full.idUsuario;
                            var checked = full.habilitar ? 'checked' : '';
                            var readOnlyPaso = readOnly ? 'disabled' : '';
                            //return '<a id='+buttonID+' class="btn btn-success rolloverBtn" role="button">Rollover</a>';
                            return "<div class='custom-control custom-switch text-center'> <input type='checkbox' class='custom-control-input habilitar' id='" + buttonID + "' " + checked + " select " + readOnlyPaso + "> <label class='custom-control-label' for='" + buttonID + "'></label></div>"
                        }
                    }],
                "ajax": {
                    "url": urls.api.getUsers,
                    "dataSrc": function (json) {
                        console.log(json);
                        return json;
                    }
                }
            });
    };

    var setStatusTr = function(sender){
        var tr = $(sender).closest('tr');
        var row = dataTable.row(tr);
        var rowData = row.data();

        console.log({ idUser: rowData.idUsuario, status: $(sender).is(":checked") });
        setStatus({ idUser: rowData.idUsuario, status: $(sender).is(":checked") });
    };

    var setStatusChk = function(sender){
        console.log({ idUser: $(getIds.userLdap.userName).attr("data-idUsuario"), status: $(sender).is(":checked") });
        if( $(getIds.userLdap.userName).attr("data-idUsuario")){
            setStatus({ idUser: $(getIds.userLdap.userName).attr("data-idUsuario"), status: $(sender).is(":checked") });
        }
    };

    var setStatus = function(userStatus){
        $.ajax({
            type: "POST",
            url: urls.api.setUserStatus,
            dataType: "json",
            async: false,
            contentType: 'application/json',
            data: JSON.stringify(userStatus)
        }).done(function (msg) {
            console.log("response done", msg);
            // cierro el modal.           
            came.notify.showSucces("usuarios", "Su solicitud se ha procesado correctamente.");                                         
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            // TODO: aqui se debe agregar un mensaje en caso de error.
            came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");   
            return false;
        });
    };

    function setViewAcces() {
        var menuPso = came.storage.getSessionStorage('menu');
        var subMenuAdmin = menuPso.find(e => e.idModule == '2416198290916770825');
        console.log(subMenuAdmin);
        if (subMenuAdmin) {
            var users = subMenuAdmin.subMenu.find(e => e.idSubModule == '2416254586118472722');
            console.log(users);
            readOnly = users.readOnly;
            if (readOnly) {
                $("#usuario-content-detail :input").prop("disabled", true);
                $(getIds.setUser).prop("disabled", true);
                $("#habilitado").prop("disabled", true);
            }
        }
    }

    var init = function () {
        setViewAcces();
        initListener();
        initList();
    };

    return {
        init: init
    }
})();

$(document).ready(function () {
    came.users.init();
});