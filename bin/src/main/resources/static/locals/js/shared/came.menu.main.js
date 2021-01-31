'use strict';
var came = came || {};

came.menu = (function () {

    var urls = {
        api: {
            getMenu: came.contexto + "/home/getMenu"
        }
    };

    var getIds = {
        menu_start: "#start",
        menu_admin: {
            admin: "/apoyo-mejora-continua/profile/profile",
            lists: "/apoyo-mejora-continua/lists/lists",
            users: "/apoyo-mejora-continua/users/index",
            period: "/apoyo-mejora-continua/period/index",
            maxassign: "/apoyo-mejora-continua/maxassign/index",
            substitutions: "/apoyo-mejora-continua/substitutions/index",
            focus: "/apoyo-mejora-continua/focus/index"
        },
        menu_establishment: "/apoyo-mejora-continua/establishment/index",
        menu_feedback: "#feedback",
        menu_direct: "#direct",
        menu_documents: "#documents",
        menu_reports: "#reports",
        menu_messenger: "#messenger",
        menu: "#navbarSupportedContent",
        menu_link: ".nav-link"
    }

    //#region privado 
    var initListener = function () {
        $(document)
            .on("click", getIds.menu_link, function () {
                processRequestUrl(this);
            });
    };

    //#endregion

    function getMenu() {
        $.ajax({
            type: "GET",
            url: urls.api.getMenu,
            dataType: "json",
            contentType: 'application/json'
            // data: { idProfile: rowData.idStr }
        }).done(function (response) {
            console.log("response done", response);
            //alert(JSON.stringify(response));

            came.storage.addSessionStorage('menu', response);
            configureMenu(response);
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            return false;
        });
    }

    function configureMenu(menu) {
        var menuPrincipal = $("#contentMenuPrincipal");
        for (var i = 0; i <= menu.length; i++) {
            if (menu[i]) {
                console.log(menu[i].name);
                if (menu[i].idModule === '2416198290916770825' && menu[i].access) {
                    var menPaso = $('<li class="admin nav-item ' + getClassMenu(menu[i].idModule) + '">' +
                        '<a class="nav-link" href="/apoyo-mejora-continua/profile/profile">Administración</a>' +
                        '</li>');
                    $(menuPrincipal).append(
                        menPaso
                    );
                } else if (menu[i].idModule === '2416198290916770826' && menu[i].access) {
                    $(menuPrincipal).append(
                        '<li id="establishment" class="nav-item ' + getClassMenu(menu[i].idModule) + '">' +
                            '<a class="nav-link" href="/apoyo-mejora-continua/establishment/index">Establecimientos</a>' +
                        '</li>'
                    );
                } else if (menu[i].idModule === '2416198290916770827' && menu[i].access) {
                    $(menuPrincipal).append(
                        '<li id="feedback" class="nav-item ' + getClassMenu(menu[i].idModule) + '">' +
                        '<a class="nav-link" href="#">Retroalimentación</a>' +
                        '</li>'
                    );
                } else if (menu[i].idModule === '2416198290916770828' && menu[i].access) {
                    $(menuPrincipal).append(
                        '<li id="direct" class="nav-item ' + getClassMenu(menu[i].idModule) + '">' +
                        '<a class="nav-link" href="#">Directorio</a>' +
                        '</li>'
                    );
                } else if (menu[i].idModule === '2416198290916770829' && menu[i].access) {
                    $(menuPrincipal).append(
                        '<li id="documents" class="nav-item ' + getClassMenu(menu[i].idModule) + '">' +
                        '<a class="nav-link" href="#">Documentos</a>' +
                        '</li>'
                    );
                } else if (menu[i].idModule === '2416198290916770830' && menu[i].access) {
                    $(menuPrincipal).append(
                        '<li id="reports" class="nav-item ' + getClassMenu(menu[i].idModule) + '">' +
                        '<a class="nav-link" href="#">Reportes</a>' +
                        '</li>'
                    );
                } else if (menu[i].idModule === '2416198290916770831' && menu[i].access) {
                    $(menuPrincipal).append(
                        '<li id="messenger" class="nav-item ' + getClassMenu(menu[i].idModule) + '">' +
                        '<a class="nav-link" href="#">Mensajería</a>' +
                        '</li>'
                    );
                } else if (menu[i].idModule === '2416198290916770832' && menu[i].access) {
                    $(menuPrincipal).append(
                        '<li id="messenger" class="nav-item ' + getClassMenu(menu[i].idModule) + '">' +
                        '<a class="nav-link" href="#">Encuesta</a>' +
                        '</li>'
                    );
                }
            }
        }
    }

    function getClassMenu(menu) {
        console.log(menu);
        if (menu === '2416198290916770825') {
            if (window.location.pathname === getIds.menu_admin.admin ||
                window.location.pathname === getIds.menu_admin.lists ||
                window.location.pathname === getIds.menu_admin.users ||
                window.location.pathname === getIds.menu_admin.period ||
                window.location.pathname === getIds.menu_admin.maxassign ||
                window.location.pathname === getIds.menu_admin.substitutions ||
                window.location.pathname === getIds.menu_admin.focus) {
                return 'active'
            }
        } else if (menu === '2416198290916770826' && 
                   window.location.pathname === getIds.menu_establishment) {
            return 'active';
        } else {
            return '';
        }
    }

    function processRequestUrl(sender) {
        window.location.href = came.contexto + $(this).attr('href');
    }

    function init() {
        getMenu();
        initListener();
    }

    return {
        getIds: getIds,
        init: init
    };
})();

$(document).ready(function () {
    came.menu.init();
});