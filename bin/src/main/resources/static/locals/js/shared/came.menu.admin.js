'use strict';
var came = came || {};
came.menu = came.menu || {};

came.menu.admin = (function () {
    //#region Privado
    var getIds = {
        menu_profile: "#profiles",
        menu_lists: "#lists",
        menu_users: "#users",
        menu_period: "#period",
        menu_maxassign: "#maxassign",
        menu_substitutions: "#substitutions",
        menu_focus: "#focus",
        navegaTo: ".navegaTo",
        profiles: "/apoyo-mejora-continua/profile/profile",
        lists: "/apoyo-mejora-continua/lists/lists",
        users: "/apoyo-mejora-continua/users/index",
        period: "/apoyo-mejora-continua/period/index",
        maxassign: "/apoyo-mejora-continua/assign/index",
        substitutions: "/apoyo-mejora-continua/substitutions/index",
        focus: "/apoyo-mejora-continua/focus/index"
    }

    var initListener = function () {
        $(document)
            .on("click", getIds.navegaTo, function () {
                processRequestUrl(this);
            });
    };

    var getCurrentPath = function () {
        $(came.menu.getIds.menu_admin).addClass("active");
        console.log(window.location.pathname);
        if (window.location.pathname === getIds.profiles) {
            console.log("es profile");
            $(getIds.menu_profile).addClass("active");
        } else if (window.location.pathname === getIds.lists) {
            console.log("es List");
            $(getIds.menu_lists).addClass("active");
        } else if (window.location.pathname === getIds.users) {
            console.log("es Users");
            $(getIds.menu_users).addClass("active");
        } else if (window.location.pathname === getIds.period) {
            console.log("es Periodo");
            $(getIds.menu_period).addClass("active");
        } else if (window.location.pathname === getIds.maxassign) {
            console.log("es Asignacion Maxima");
            $(getIds.menu_maxassign).addClass("active");
        } else if (window.location.pathname === getIds.substitutions) {
            console.log("es suplencia");
            $(getIds.menu_substitutions).addClass("active");
        } else if (window.location.pathname === getIds.focus) {
            console.log("es Foco");
            $(getIds.menu_focus).addClass("active");
        }
    }

    function configureSubMenu() {
        var menuAdmin = $("#menuAdmin");
        var menuPso = came.storage.getSessionStorage('menu');
        console.log(menuPso);
        if (menuPso) {
            var subMenuAdmin = menuPso.find(e => e.idModule == '2416198290916770825');
            console.log(subMenuAdmin.subMenu);
            for (var i = 0; i <= subMenuAdmin.subMenu.length; i++) {
                if(subMenuAdmin.subMenu[i]){
                    console.log(subMenuAdmin.subMenu[i]);
                    if (subMenuAdmin.subMenu[i].idSubModule === '2416254586118472721' && subMenuAdmin.subMenu[i].idMenuAcceso !== '2415742140157002753') {                          
                        var menPerfil = '<li id="profiles"> <a href="/apoyo-mejora-continua/profile/profile" class="navegaTo"> Perfiles</a> </li>';
                        $(menuAdmin).append(
                            menPerfil
                        );
                    } else  if (subMenuAdmin.subMenu[i].idSubModule === '2416254586126861347' && subMenuAdmin.subMenu[i].idMenuAcceso !== '2415742140157002753') {                          
                        var menListas = '<li id="lists"> <a href="/apoyo-mejora-continua/lists/lists" class="navegaTo">Listas&nbsp;desplegables</a> </li>';
                        $(menuAdmin).append(
                            menListas
                        );
                    } else  if (subMenuAdmin.subMenu[i].idSubModule === '2416254586126861331' && subMenuAdmin.subMenu[i].idMenuAcceso !== '2415742140157002753') {
                        var menPeriodo = '<li id="period"> <a href="/apoyo-mejora-continua/period/index">Periodos</a> </li>';
                        $(menuAdmin).append(
                            menPeriodo
                        );
                    } else  if (subMenuAdmin.subMenu[i].idSubModule === '2416254586118472722' && subMenuAdmin.subMenu[i].idMenuAcceso !== '2415742140157002753') {
                        var menUsuarios = '<li id="users"> <a href="/apoyo-mejora-continua/users/index">Usuarios</a> </li>';
                        $(menuAdmin).append(
                            menUsuarios
                        );
                    } else  if (subMenuAdmin.subMenu[i].idSubModule === '2416254586126861348' && subMenuAdmin.subMenu[i].idMenuAcceso !== '2415742140157002753') {
                        var menAsignacionMaxima = '<li id="maxassign"> <a href="/apoyo-mejora-continua/assign/index">Asignaci&oacute;n&nbsp;m&aacute;xima</a> </li>';
                        $(menuAdmin).append(
                            menAsignacionMaxima
                        );
                    } else  if (subMenuAdmin.subMenu[i].idSubModule === '2416254586126861349' && subMenuAdmin.subMenu[i].idMenuAcceso !== '2415742140157002753') {
                        var menSuplencia = '<li id="substitutions"> <a href="/apoyo-mejora-continua/substitutions/index">Suplencia</a> </li>';
                        $(menuAdmin).append(
                            menSuplencia
                        );
                    } else  if (subMenuAdmin.subMenu[i].idSubModule === '2416254586126861350' && subMenuAdmin.subMenu[i].idMenuAcceso !== '2415742140157002753') {
                        var menFocos = '<li id="focus"> <a href="/apoyo-mejora-continua/focus/index">Focos</a> </li>';
                        $(menuAdmin).append(
                            menFocos
                        );
                    }
                }
            }
        }

    }

    function processRequestUrl(sender) {
        window.location.href = came.contexto + $(this).attr('href');
    }
    //#endregion

    function init() {
        console.log('came.menu.admin.init');
        configureSubMenu();
        getCurrentPath();
    }

    return {
        init: init,
        initListener: initListener
    };
})();

$(document).ready(function () {
    came.menu.admin.init();
});