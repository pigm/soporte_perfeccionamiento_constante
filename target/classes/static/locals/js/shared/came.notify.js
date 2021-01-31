
'use strict';
var came = came || {};

came.notify = (function () {
    var getIds = {
        succes: "#toast-succes",
        alert: "#toast-alert",
        danger: "#toast-danger",

        titleSucess: "#title-succes",
        messageSucess: "#message-succes",
        titleAlert: "#title-alert",
        messageAlert: "#message-alert",
        titleDanger: "#title-danger",
        messageDanger: "#message-danger",

        close_alert: "#close-alert"
    };

    function showSucces (title, message) {
        $(getIds.titleSucess).text(title);
        $(getIds.messageSucess).text(message);
        console.log($(getIds.succes).html());
        $(getIds.succes).toast('show');
    }

    function showWarning (title, message) {
        $(getIds.close_alert).on('click', function () {
            console.log(this);
            $(getIds.alert).toast('hide');    
        });

        $(getIds.titleAlert).text(title);
        $(getIds.messageAlert).text(message);
        $(getIds.alert).toast('show');
    }

    function showError (title, message) {
        $(getIds.titleDanger).text(title);
        $(getIds.messageDanger).text(message);
        $(getIds.danger).toast('show');
    }

    return {
        showSucces: showSucces,
        showWarning: showWarning,
        showError: showError
    }
})();