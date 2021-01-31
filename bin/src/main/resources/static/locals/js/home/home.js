'use strict';
var came = came || {};

came.home = (function () {

    // var getIds = {
    //     home: "/came/home/index"
    // }

    function getCurrentPath() {
        $(came.menu.getIds.menu_start).addClass("active");
        console.log(window.location.pathname);
    }

    function init() {
        
        getCurrentPath();
    }

    return {
        getCurrentPath: getCurrentPath,
        init: init
    };
})();

$(document).ready(function () {
    came.home.init();        
});