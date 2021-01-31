'use strict';
var came = came || {};

came.menu = (function () {

    var urls = {
        api: {
            getMenu: came.contexto + "/home/getMenu"
        }
    };

    var getIds = {
        menu_logout: "#menu-logout",
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
        menu_process: {
            documentacion: "/apoyo-mejora-continua/documents/index",
            redes: "/apoyo-mejora-continua/networks/index",
            asesoria_directa: "/apoyo-mejora-continua/advisory/index",
            supervisores: "/apoyo-mejora-continua/assignment/index",
            planificacion_registro: "/apoyo-mejora-continua/planning/index"        
        },
        menu_feedback: "#feedback",
        menu_direct: "#direct",
        menu_documents: "#documents",
        menu_reports: "#reports",
        menu_messenger: "/apoyo-mejora-continua/messenger/index" ,
        menu: "#navbarSupportedContent",
        menu_link: ".nav-link"
    }


    //#region privado 
    var initListener = function () {
        $(document)
            .on("click", getIds.menu_link, function () {
                processRequestUrl(this);
            })
            .on("click", getIds.menu_logout, function () {
                logOut(this);
            });
    };

    //#endregion

    
    function logOut(sender){
        console.log(sender);        
        came.storage.clear();
    }

    function getMenu() {
        if(came.storage.getSessionStorage('menu')){
            configureMenu(came.storage.getSessionStorage('menu'));
        } else {
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
            });
            // .fail(function (jqXHR, textStatus) {
            //     console.error("response fail", jqXHR);
            //     return false;
            // });
        }        
    }

    function configureMenu(menu) {
        var menuPrincipal = $("#contentMenuPrincipal");
        menu.forEach(m => {
            console.log(m.name);
            console.log(m.idModule);
            console.log(m.access);
            if(m.access){                
                console.log(m.subMenu.find(x => x.idMenuAcceso !== "2415742140157002753"));
                var urlMenu = m.subMenu.length > 0 ? m.subMenu.find(x => x.idMenuAcceso !== "2415742140157002753").url ?? '' : '';
                console.log(urlMenu);
                var men = $('<li class="admin nav-item ' + getClassMenu(m.subMenu) + '">' +
                    '<a class="nav-link" href="' + urlMenu + '">' + m.name + '</a>' +
                    '</li>');
                $(menuPrincipal).append(
                    men
                );
            }            
        });
    }

    function getClassMenu(subMenu) {
        var result = '';
        console.log(subMenu);
        subMenu.forEach(m =>{
            if (window.location.pathname.includes(m.url)) {
                result = 'active';
            }
        });
        console.log(result);
        return result;       
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
    console.log("came.menu.init()");
    came.menu.init();
});