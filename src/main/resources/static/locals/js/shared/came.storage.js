'use strict';
var came = came || {};


came.storage = (function (){
    function addLocalStorage(key, value){
        localStorage.setItem(key, value);
    }

    function addSessionStorage(key, value){
        sessionStorage.setItem(key, JSON.stringify(value));
    }

    function getLocalStorage(key){
        return localStorage.getItem(key);
    }

    function getSessionStorage(key, value){
        return JSON.parse(sessionStorage.getItem(key));
    }

    function removeLocalStorage(key, value){
        localStorage.removeItem(Apellido);
    }

    function removeSessionStorage(key, value){
        sessionStorage.removeItem(Apellido)
    }

    function clear(){
        localStorage.clear();
        sessionStorage.clear();
    }

    return {
        addLocalStorage: addLocalStorage,
        addSessionStorage: addSessionStorage,
        getLocalStorage: getLocalStorage,
        getSessionStorage: getSessionStorage,
        removeLocalStorage: removeLocalStorage,
        removeSessionStorage: removeSessionStorage,
        clear: clear
    }

})();