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
    }

    var initListener = function () {
        $(document)
            .on("click", getIds.navegaTo, function () {
                processRequestUrl(this);
            });
    };   

    function configureSubMenu() {
        var menuAdmin = $("#menuAdmin");
        var menuPso = came.storage.getSessionStorage('menu');
        console.log(menuPso);
        if (menuPso) {
            var subMenuAdmin = menuPso.find(e => e.idModule == '2416198290916770825');
            console.log(subMenuAdmin.subMenu);
            subMenuAdmin.subMenu.forEach(m => {
                console.log(m.name);
                console.log(m.idModule);
                console.log(m.access);
                if (m.idMenuAcceso !== "2415742140157002753") {
                    var smem = $('<li id="' + m.idSubModule + '" class="' + currentMenuClass(m.url) + '"> <a href="' + m.url + '" class="navegaTo"> ' + m.name + ' </a> </li>');         
                    console.log(smem);       
                    $(menuAdmin).append(
                        smem
                    );
                }
            });            
        }

    }

    function currentMenuClass(url){
        console.log(url);
        console.log(window.location.pathname);
        if(window.location.pathname === url){
            return 'active'
        } else {
            return '';
        }        
    }

    function processRequestUrl(sender) {
        window.location.href = came.contexto + $(this).attr('href');
    }
    //#endregion

    function init() {
        console.log('came.menu.admin.init');
        configureSubMenu();
        // getCurrentPath();
    }

    return {
        init: init,
        initListener: initListener
    };
})();

$(document).ready(function () {
    came.menu.admin.init();
});