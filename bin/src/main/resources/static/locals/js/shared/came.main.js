'use strict';
var came = came || {};

came.main = (function(){

    var setValidControl = function (sender) {
        $(sender).removeClass("is-invalid");
    }

    var setInvalidControl = function (sender) {
        $(sender).addClass("is-invalid");
    }

    var isValidControl = function (sender) {
        console.log($(sender)); 
        console.log($(sender).val());       
        if ($(sender).val() === undefined || $(sender).val() === '') {
            console.log("is-invalid"); 
            $(sender).addClass("is-invalid");            
        } else {
            $(sender).removeClass("is-invalid");
            console.log("is-valid");
        }        
    }

    var isValidGroup = function (sender, groupName) {
        console.log($(sender).val());
        console.log(groupName);
        if ($(sender).val() === undefined || $(sender).val() === '') {
            $(groupName).addClass("is-invalid");
            $(groupName + " :input").addClass("is-invalid");
        } else {
            $(groupName).removeClass("is-invalid");
            $(groupName + " :input").removeClass("is-invalid");
        }
    }

    function formatDate(date){
        return new Date(date).getDate() + '-' +
			new Date(date).getMonth() + '-' +
			new Date(date).getFullYear();
    }

    function getBase64(file) {
		return new Promise((resolve, reject) => {
		  const reader = new FileReader();
		  reader.readAsDataURL(file);
		  reader.onload = () => {
			let encoded = reader.result.toString().replace(/^data:(.*,)?/, '');
			if ((encoded.length % 4) > 0) {
			  encoded += '='.repeat(4 - (encoded.length % 4));
			}
			resolve(encoded);
		  };
		  reader.onerror = error => reject(error);
		});
	  }

    function init() {
     
    }

    return {
        init: init,
        isValidControl: isValidControl,
        isValidGroup: isValidGroup,
        setValidControl: setValidControl,
        setInvalidControl: setInvalidControl,
        getBase64: getBase64,
        formatDate: formatDate
    };
})();