'use strict';
var came = came || {};

came.home = (function () {
    var current_user = null;
    var listMesg = null;

    var urls = {
        api: {
            getUsers: came.contexto + "/users/getUsers",
            getSurveyForUserProfile: came.contexto + "/survey/getSurveyForUserProfile",
            getSurveyForAnswer: came.contexto + "/survey/getSurveyForAnswer",
            answerSurvey: came.contexto + "/survey/answerSurvey",
            getMessages: came.contexto + "/messenger/getMessages",
            setMessage: came.contexto + "/messenger/setMessage"
        }
    };

    var getIds = {
        modal_check_message: "#modal-check-message",
        subject_message: "#subject-message",
        body_message: "#body-message",
        title_cant_messagges: "#title-cant-messagges",
        button_message_read: "#button-message-read",
        data_id_p_message1: "#data-id-p-message1",
        check_message1: "#customCheck1",


        modal_ver_encuesta: "#modal-ver-encuesta",
        ver_survey_nombre: "#ver-survey-nombre",
        ver_survey_questions_list: "#ver-survey-questions-list",
        ver_content_observations: "#ver-content-observations",
        answer_survey_save: "#answer-survey-save",
        button_survey_cancel: "#button-survey-cancel"
    }

    var initListener = function () {
        $(document)
            .on("click", getIds.answer_survey_save, function () {
                answerSurvey(this);
            })
            .on("click", getIds.button_survey_cancel, function () {
                closeModalSurvey(this);
            })
            .on("click", getIds.button_message_read, function () {
                updateReadMessage(this);
            })
            .on("hidden.bs.modal", getIds.modal_ver_encuesta, function () {
                clearModalAnswers(this);
            });
    };


    // var getIds = {
    //     home: "/came/home/index"
    // }

    function getCurrentPath() {
        $(came.menu.getIds.menu_start).addClass("active");
        console.log(window.location.pathname);
    }

    function getCurrentUser() {
        console.log("Get current user...");
        console.log(came.env.getEnvUser());
    }

    function getMessagesLogin() {
        console.log("INIT message");
        //console.log(sender);
        //var idMessage = $(sender).attr('data-id-message'); 
        var idPerfil = came.storage.getSessionStorage("userEnv").idPerfil; console.log("PRFIL USER LOGEADO : ", idPerfil);
        console.log("getSessionStorage : ", came.storage.getSessionStorage("userEnv").idPerfil);
        $.ajax({
            type: "GET",
            url: urls.api.getMessages,
            dataType: "json",
            async: false,
            contentType: 'application/json',
            data: {
                leido: false,
                idPerfil: idPerfil
            }
        }).done(function (response) {
            if (response.length > 0) {
                listMesg = response;
                console.log("response done", JSON.stringify(response));
                console.log("Largo Size : ", response.length);
                console.log("PRIMER ELEMENTO : ", response[0].asunto);
                var posMsg = 1;
                var cantMsg = response.length;
                var txtCantMsg = '"Mensajes sin leer (' + posMsg + ' de ' + cantMsg + ')"';
                $(getIds.title_cant_messagges).text(txtCantMsg);
                $(getIds.subject_message).text(response[0].asunto);
                $(getIds.body_message).text(response[0].contenidoMensaje);
                $(getIds.modal_check_message).attr("data-idpmessage1", response[0].idPerfilMensaje);


                $(getIds.modal_check_message).modal('show');
            }

        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
            return false;
        });
    }

    function updateReadMessage(sender) {

        var leido = $(getIds.check_message1).prop('checked');
        if (!leido) {
            return false;
        }
        var msgPaso = {
            idPerfilMensaje: $(getIds.modal_check_message).attr("data-idpmessage1"),
            leido: leido,

        };

        console.log(msgPaso);

        $.ajax({
            type: "POST",
            url: urls.api.setMessage,
            dataType: "json",
            contentType: 'application/json',
            data: JSON.stringify(msgPaso)
        }).done(function (msg) {
            console.log("response done", msg);
            came.notify.showSucces("Red", "Su solicitud se ha procesado correctamente.");
            location.reload();
        }).fail(function (jqXHR, textStatus) {
            console.error("response fail", jqXHR);
            // TODO: aqui se debe agregar un mensaje en caso de error.
            came.notify.showError("Red", "Su solicitud se ha procesado con errores.");
            return false;
        });

    }

    function clearModalAnswers(sender) {
        $(getIds.ver_survey_questions_list).children().each((index, item) => {
            console.log(item);
            $(item).remove();
        });
        $("#ver-survey-observation").val("");
        $(getIds.answer_survey_save).prop("disabled", false);
    }

    function getSurveyForUserProfile(){
        $.ajax({
            type: "GET",
            url: urls.api.getSurveyForUserProfile,            
            async: false,
            //contentType: 'application/json'            
        }).done(function (response) {
            if(response !== ""){
                viewSurvey(response);
            } else{
                getMessagesLogin();
            }
        });
    }   
    
    function viewSurvey(idSurvey) {
        
        $.ajax({
            type: "GET",
            url: urls.api.getSurveyForAnswer,
            dataType: "json",
            async: false,
            //contentType: 'application/json',
            data: { idSurvey: idSurvey }
        }).done(function (response) {
            $(getIds.answer_survey_save).attr('data-id-survey', idSurvey);
            $(getIds.ver_survey_nombre).text(response.nombre);
            addQuestionForAnswer(response.preguntas);
            if (response.permiteObservacion) {
                $(getIds.ver_content_observations).css("display", "");
            } else {
                $(getIds.ver_content_observations).css("display", "none");
            }            
            $(getIds.modal_ver_encuesta).modal('show');
        });
    }

    function addQuestionForAnswer(questions) {
        questions.forEach(qa => {
            $(getIds.ver_survey_questions_list).append(
                '<div class="col-12 mt-2"><div class= "alert-secondary" role = "alert" style = "border-radius: 4px;"><div class="p-2">' +
                '<p class=" text-center mb-0 question" data-id-question="' + qa.idQuestion + '">' + qa.question + '</p>' +
                '<div class="text-center mt-2">' +
                '<div class="form-check form-check-inline mr-4"><input class="form-check-input" type="radio" name="' + qa.idQuestion + '" id="inlineRadio1" value="1">' +
                '<label class="form-check-label" for="inlineRadio1">1</label></div>' +
                '<div class="form-check form-check-inline mr-4"><input class="form-check-input" type="radio" name="' + qa.idQuestion + '" id="inlineRadio2" value="2">' +
                '<label class="form-check-label" for="inlineRadio2">2</label></div>' +
                '<div class="form-check form-check-inline mr-4"><input class="form-check-input" type="radio" name="' + qa.idQuestion + '" id="inlineRadio3" value="3">' +
                '<label class="form-check-label" for="inlineRadio3">3</label></div>' +
                '<div class="form-check form-check-inline mr-4"><input class="form-check-input" type="radio" name="' + qa.idQuestion + '" id="inlineRadio4" value="4">' +
                '<label class="form-check-label" for="inlineRadio4">4</label></div>' +
                '<div class="form-check form-check-inline mr-4"><input class="form-check-input" type="radio" name="' + qa.idQuestion + '" id="inlineRadio5" value="5">' +
                '<label class="form-check-label" for="inlineRadio5">5</label></div>' +
                '<div class="form-check form-check-inline mr-4"><input class="form-check-input" type="radio" name="' + qa.idQuestion + '" id="inlineRadio6" value="6">' +
                '<label class="form-check-label" for="inlineRadio6">6</label></div>' +
                '<div class="form-check form-check-inline mr-4"><input class="form-check-input" type="radio" name="' + qa.idQuestion + '" id="inlineRadio7" value="7">' +
                '<label class="form-check-label" for="inlineRadio7">7</label></div>' +
                '</div></div></div></div>'
            );
        });
    }

    function answerSurvey(sender) {
        var valid = true;
        var idSurvey = $(sender).attr('data-id-survey');
        console.log(idSurvey);
        var answers = [];
        $(getIds.ver_survey_questions_list).children().each((index, item) => {
            console.log(item);
            console.log($(item).find('.question').text());

            var selected = $("input:radio[name=" + $(item).find('.question').attr('data-id-question') + "]:checked");
            console.log($(selected).val());

            if ($(selected).val() === undefined) {
                console.log('Error de respuesta');
                came.notify.showError("Mensaje", "Debe responder todas las preguntas de la encuesta.");
                return;
            }

            answers.push({
                idQuestion: $(item).find('.question').attr('data-id-question'),
                answer: $(selected).val()
            });
        });

        if ($(getIds.ver_content_observations).is(':visible')) {
            var observation = $("#ver-survey-observation").val();
            if (observation === '') {
                came.main.setInvalidControl($("#ver-survey-observation"));
                valid = false;
            } else {
                came.main.setValidControl($("#ver-survey-observation"));
            }
        }

        // console.log({
        //     idSurvey: idSurvey,
        //     answers: answers,
        //     observation: observation
        // });

        if (valid) {
            $.ajax({
                type: "POST",
                url: urls.api.answerSurvey,
                dataType: "json",
                contentType: 'application/json',
                data: JSON.stringify({
                    idSurvey: idSurvey,
                    answers: answers,
                    observation: observation
                })
            }).done(function (msg) {
                console.log("response done", msg);
                came.notify.showSucces("Encuesta", "Su solicitud se ha procesado correctamente.");
                $(getIds.modal_ver_encuesta).modal('hide');
                getSurveyForUserProfile();
            });
        }
    }

    function closeModalSurvey(sender){
        $(getIds.modal_ver_encuesta).modal('hide');
        getMessagesLogin();
    }

    function init() {
        console.log("Inicio de session...");
        getCurrentPath();
        getCurrentUser();
        initListener();
        getSurveyForUserProfile();        
        //carrusel(); 
    }

    function carrusel() {
        $('#recipeCarousel').carousel({
            interval: false
        })

        $('.carousel .carousel-item').each(function () {
            var minPerSlide = 1;
            var next = $(this).next();
            if (!next.length) {
                next = $(this).siblings(':first');
            }
            next.children(':first-child').clone().appendTo($(this));

            for (var i = 0; i < minPerSlide; i++) {
                next = next.next();
                if (!next.length) {
                    next = $(this).siblings(':first');
                }

                next.children(':first-child').clone().appendTo($(this));
            }
        });
    }

    return {
        getCurrentPath: getCurrentPath,
        init: init
    };
})();

$(document).ready(function () {
    came.home.init();

});