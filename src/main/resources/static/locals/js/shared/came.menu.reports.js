'use strict';
var came = came || {};
came.menu = came.menu || {};

came.menu.reports = (function () {
    //#region Privado
    var getIds = {
        menu_focus: "#focus",
        navegaTo: ".navegaTo",
    }

    var initListener = function () {
        $(document)
            .on("click", getIds.navegaTo, function () {
                processRequestUrl(this);
            });
    };

    function configureSubMenuReports() {
        console.log("configureSubMenu");
        var menureports = $("#menureports");
        var menuPso = came.storage.getSessionStorage('menu');
        console.log(menuPso);
        if (menuPso) {
            var subMenureports = menuPso.find(e => e.idModule == '2416198290916770829');
            console.log(subMenureports.subMenu);
            subMenureports.subMenu.forEach(m => {
                console.log(m.name);
                console.log(m.idModule);
                console.log(m.access);
                if (m.idMenuAcceso !== "2415742140157002753") {
                    var smem = $('<li id="' + m.idSubModule + '" class="' + currentMenuClass(m.url) + '"> <a href="' + m.url + '" class="navegaTo"> ' + m.name + ' </a> </li>');
                    console.log(smem);    
                    $(menureports).append(
                        smem
                    );
                }
            });
        }

    }

    function currentMenuClass(url) {
        console.log(url);
        console.log(window.location.pathname);
        if (window.location.pathname.includes(url)) {
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
        console.log('came.menu.reports.init');
        configureSubMenuReports();
        // getCurrentPath();
    }

    return {
        init: init,
        initListener: initListener
    };
})();

$(document).ready(function () {
    came.menu.reports.init();
});