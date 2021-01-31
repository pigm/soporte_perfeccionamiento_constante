'use strict';
var came = came || {};

came.env = (function(){
    var urls = {
        api: {
            getUserEnv: came.contexto + "/home/getUserEnv"      
        }
    };

    function getEnvUser() {
        var userEnv = {};

        $.ajax({
			type: "GET",
			url: urls.api.getUserEnv,
			dataType: "json",
			async: false			
		}).done(function (response) {
            console.log("response done", response);
            came.storage.addSessionStorage("userEnv", response);
			userEnv = response;
		}).fail(function (jqXHR, textStatus) {
			console.error("response fail", jqXHR);
			return false;
		});        
        
        return userEnv;
    }

    function setCurrenUserHome(){
        var userEnv = getEnvUser();
        $("#current-user").text(userEnv.nombreCompleto + ", " + userEnv.perfil + ", " + userEnv.deprov);
    }

    return {
        getEnvUser: getEnvUser,
        setCurrenUserHome: setCurrenUserHome
    };
})();

$(document).ready(function () {
    came.env.setCurrenUserHome();
});