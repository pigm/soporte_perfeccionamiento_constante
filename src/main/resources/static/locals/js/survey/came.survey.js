'use strict';
var came = came || {};

came.survey = (function () {
    var readOnly = false;

    var urls = {
        api: {
            getProvincias: came.contexto + "/survey/getProvincias",
            getSurvey: came.contexto + "/survey/getSurvey",
            getSurveyDetail: came.contexto + "/survey/getSurveyDetail",
            getSurveyForAnswer: came.contexto + "/survey/getSurveyForAnswer",
            setSurvey: came.contexto + "/survey/setSurvey",
            answerSurvey: came.contexto + "/survey/answerSurvey",
            surveyDetail: came.contexto + "/survey/survey-detail",
            setSurveyStatus: came.contexto + "/survey/setSurveyStatus",
        }
    };

    var getIds = {
        button_back: "#button-back",

        selector_region: "#selector-region",
        selector_deprov: "#selector-deprov",
        selector_perfil: "#selector-perfil",
        selector_estado: "#selector-estado",
        button_search: "#button-search",
        panel_search: "#panel-search",
        panel_search_result: "#panel-search-result",
        table_survey: "#table-survey",
        count_survey: "#count-survey",
        survey_find_again: "#survey-find-again",
        button_nueva_encuesta: "#button-nueva-encuesta",
        modal_crear_encuesta: "#modal-crear-encuesta",
        button_add_question: "#button-add-question",
        survey_questions_list: "#survey-questions-list",
        remove_question: ".remove-question",
        survey_question: ".question",
        survey_nombre: "#survey-nombre",
        survey_add_perfil: "#select-add-perfil",
        survey_fecha_inicio: "#survey-fecha-inicio",
        survey_fecha_fin: "#survey-fecha-fin",
        survey_permite_observacion: "#survey-permite-observacion",
        change_survey: ".change-survey",
        button_survey_save: "#button-survey-save",

        ver_survey: ".ver-survey",
        modal_ver_encuesta: "#modal-ver-encuesta",
        ver_survey_nombre: "#ver-survey-nombre",
        ver_survey_questions_list: "#ver-survey-questions-list",
        ver_content_observations: "#ver-content-observations",
        answer_survey_save: "#answer-survey-save",

        set_status: ".set-status"
    };

    function initListener() {
        $(document)
            .on("change", getIds.selector_region, function () {
                getProvincias(this);
            })
            .on("click", getIds.button_nueva_encuesta, function () {
                showNewSurvey(this);
            })
            .on("click", getIds.change_survey, function () {
                showEditSurvey(this);
            })
            .on("click", getIds.button_add_question, function () {
                addQuestion(this);
            })
            .on("click", getIds.remove_question, function () {
                removeQuestion(this);
            })
            .on("click", getIds.button_survey_save, function () {
                setSurvey(this);
            })
            .on("click", getIds.button_search, function () {
                getSurvey(this);
            })
            .on("click", getIds.survey_find_again, function () {
                findAgain(this);
            })
            .on("click", getIds.ver_survey, function () {
                viewSurvey(this);
            })
            .on("click", getIds.answer_survey_save, function () {
                answerSurvey(this);
            })
            .on("click", getIds.button_back, function () {
                goBack();
            })
            .on("change", getIds.set_status, function () {
                setStatusSurvey(this);
            })
            .on("hidden.bs.modal", getIds.modal_crear_encuesta, function () {
                clearModal(this);
            })
            .on("hidden.bs.modal", getIds.modal_ver_encuesta, function () {
                clearModalAnswers(this);
            });
    }

    function clearModal(sender) {
        $(getIds.survey_nombre).val('');
        $(getIds.survey_nombre).prop('disabled', '');
        var $select = $(getIds.survey_add_perfil).selectize({
            create: true,
        });
        $select[0].selectize.setValue([]);

        $(getIds.survey_fecha_inicio).val('');
        $(getIds.survey_fecha_fin).val('');

        $(getIds.survey_questions_list).children().each((index, item) => {
            var question = $(item).find(".question");
            $(question).val('');
            $(question).prop('disabled', '');
            var remove = $(item).find(".remove-question");
            if (remove !== undefined) {
                $(remove).click();
            }
            //remove-question
        });

        // $(getIds.survey_permite_observacion).prop('checked', false);
        $(getIds.survey_permite_observacion).prop('checked', false);
        $(getIds.button_survey_save).removeAttr('data-id-survey');
    }

    function clearModalAnswers(sender) {
        $(getIds.ver_survey_questions_list).children().each((index, item) => {
            console.log(item);
            $(item).remove();
        });
        $("#ver-survey-observation").val("");
        $(getIds.answer_survey_save).prop("disabled", false);
    }

    function showNewSurvey(sender) {
        console.log(sender);
        if (readOnly) {
            came.notify.showError("Encuesta", "No tienes permitido crear o modificar encuestas.");
        } else {
            $(getIds.modal_crear_encuesta).modal('show');
        }
    }

    function showEditSurvey(sender) {
        var idSurvey = $(sender).attr('data-id-survey');

        $.ajax({
            type: "GET",
            url: urls.api.getSurveyDetail,
            dataType: "json",
            async: false,
            //contentType: 'application/json',
            data: { idSurvey: idSurvey }
        }).done(function (response) {

            $(getIds.survey_nombre).val(response.nombre);
            $(getIds.survey_nombre).prop('disabled', 'disabled');
            var $select = $(getIds.survey_add_perfil).selectize({
                create: true,
            });
            $select[0].selectize.setValue(response.perfiles);

            $(getIds.survey_fecha_inicio).val(response.fechaInicio);
            $(getIds.survey_fecha_fin).val(response.fechaFin);

            for (var i = 3; i < response.preguntas.length; i++) {
                $(getIds.button_add_question).click();
            }

            $(getIds.survey_questions_list).children().each((index, item) => {
                var question = $(item).find(".question");
                $(question).val(response.preguntas[index]);
                $(question).prop('disabled', 'disabled');
            });

            $(getIds.survey_permite_observacion).prop("checked", response.permiteObservacion);
            $(getIds.button_survey_save).attr('data-id-survey', idSurvey);

            $(getIds.modal_crear_encuesta).modal('show');
        });
    }

    function viewSurvey(sender) {
        var idSurvey = $(sender).attr('data-id-survey');

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
            $(getIds.answer_survey_save).prop("disabled", true);
            $(getIds.modal_ver_encuesta).modal('show');
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
                location.reload();
            });
        }
    }

    function addQuestion(sender) {
        if ($(getIds.survey_questions_list).children().length < 15) {
            var numberPaso = $(getIds.survey_questions_list).children().length + 1;
            $(getIds.survey_questions_list).append(
                '<div class="col-12">' +
                '<div class= "form-group mb-0">' +
                '<label class="mb-0 question-title" for="">Pregunta ' + numberPaso + '</label>' +
                '<a href="#" class="text-danger float-right remove-question">' +
                '<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">' +
                '<path fill-rule="evenodd" d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z" />' +
                '</svg></a>' +
                '<input type="text" class="form-control form-control-sm question" aria-describedby="nombreDoc" maxlength="150">' +
                '<div class="invalid-feedback">' +
                '<p>Debe ingresar la pregunta.</p>' +
                '</div>' +
                '</div></div>');
        }

        if ($(getIds.survey_questions_list).children().length === 15) {
            $(sender).css("display", "none");
        }
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

    function removeQuestion(sender) {
        console.log($(sender).closest(".col-12"));
        $(sender).closest(".col-12").remove();

        $(getIds.survey_questions_list).children().each((index, item) => {
            var questionTitle = $(item).find(".question-title");
            $(questionTitle).text('Pregunta ' + (index + 1));
        });

        if ($(getIds.survey_questions_list).children().length === 14) {
            $(getIds.button_add_question).css("display", "");
        }
    }

    function setSurvey(sender) {
        var valid = true;
        console.log(sender);

        var suveyNombre = $(getIds.survey_nombre).val();
        if (suveyNombre === '') {
            came.main.setInvalidControl($(getIds.survey_nombre));
            valid = false;
        } else {
            came.main.setValidControl($(getIds.survey_nombre));
        }

        var supervisors = $(getIds.survey_add_perfil).val();
        if (supervisors.length === 0) {
            came.main.setInvalidControl($(getIds.survey_add_perfil));
        } else {
            came.main.setValidControl($(getIds.survey_add_perfil));
        }

        var fechaInicio = $(getIds.survey_fecha_inicio).val();
        if (fechaInicio === '') {
            came.main.setInvalidControl($(getIds.survey_fecha_inicio));
            valid = false;
        } else {
            came.main.setValidControl($(getIds.survey_fecha_inicio));
        }

        var fechaFin = $(getIds.survey_fecha_fin).val();
        if (fechaFin === '') {
            came.main.setInvalidControl($(getIds.survey_fecha_fin));
            valid = false;
        } else {
            came.main.setValidControl($(getIds.survey_fecha_fin));
        }

        var questions = [];
        $(getIds.survey_questions_list).children().each((index, item) => {
            console.log($(item).find(".question"));
            console.log($(item).find(".question").val());
            var question = $(item).find(".question");
            if ($(question).val() === '') {
                came.main.setInvalidControl($(question));
                valid = false;
            } else {
                came.main.setValidControl($(question));
                questions.push($(question).val());
            }
        });

        var permiteObs = $(getIds.survey_permite_observacion).is(":checked");

        var param = {
            idSurvey: $(sender).attr('data-id-survey'),
            nombre: suveyNombre,
            fechaInicio: fechaInicio,
            fechaFin: fechaFin,
            permiteObservacion: permiteObs,
            preguntas: questions,
            perfiles: supervisors
        }

        console.log(param);
        if (valid) {
            $.ajax({
                type: "POST",
                url: urls.api.setSurvey,
                dataType: "json",
                contentType: 'application/json',
                data: JSON.stringify(param)
            }).done(function (msg) {
                console.log("response done", msg);
                came.notify.showSucces("Encuesta", "Su solicitud se ha procesado correctamente.");
                location.reload();
            });
        }

    }

    function findAgain(sender) {
        console.log(sender);
        $(getIds.panel_search).css("display", "");
        $(getIds.panel_search_result).css("display", "none");
    }

    function getSurvey(sender) {
        $.ajax({
            type: "GET",
            url: urls.api.getSurvey,
            dataType: "json",
            // async: false,
            contentType: 'application/json',
            data: jQuery.param({
                idPerfil: $(getIds.selector_perfil).val(),
                estado: $(getIds.selector_estado).val()
            })
        }).done(function (response) {
            if (typeof (response) !== "string") {
                $(getIds.count_survey).text(response.length);
                $(getIds.table_survey + ' tbody > tr').remove();

                //var tablePaso = $(getIds.table_survey);
                loadSurvey(getIds.table_survey, response);

                $(getIds.panel_search).css("display", "none");
                $(getIds.panel_search_result).css("display", "");
            } else {
                came.notify.showError("Asignar supervisores", "Su solicitud se ha procesado con errores.");
            }
        });
    }

    function loadSurvey(table, elements) {

        console.log(elements);
        if (elements) {
            for (var i = 0; i < elements.length; i++) {
                var row = $('<tr></tr>');
                // class="ver-survey"
                var tdPaso1 = $('<td></td>');
                tdPaso1.append('<a href="' + urls.api.surveyDetail + "?idSurvey=" + elements[i].idSurvey + '">' + elements[i].nombre + '</a>');
                var editPaso = '<a href="#" class="change-survey float-right" title="" data-id-survey="' + elements[i].idSurvey + '">' +
                    '<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">' +
                    '<path fill-rule="evenodd" d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"></path>' +
                    '</svg>' +
                    '</a>';

                if (!readOnly) {
                    $(tdPaso1).append(editPaso);
                }

                var viewPaso = '<a href="#" class="float-right mr-2 ver-survey" data-id-survey="' + elements[i].idSurvey + '">' +
                    '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">' +
                    '<path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z" />' +
                    '<path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z" />' +
                    '</svg>' +
                    '</a>';

                $(tdPaso1).append(viewPaso);
                row.append(tdPaso1);

                var destinatarios = '';
                elements[i].perfiles.forEach(element => {
                    destinatarios += element + '</br>';
                });

                //row.append($('<td></td>').append('<a href="#" data-id-survey="' + elements[i].idSurvey + '">' + destinatarios + '</a>'));
                row.append($('<td></td>').append(destinatarios));
                row.append($('<td></td>').append($('<span />').text(elements[i].fechaInicio).html()));
                row.append($('<td></td>').append($('<span />').text(elements[i].fechaFin).html()));

                var buttonID = elements[i].idSurvey;
                var checked = elements[i].estado ? 'checked' : '';
                var readOnlyPaso = readOnly ? 'disabled' : '';
                var statusPaso = "<span class='custom-control custom-switch text-center'> <input type='checkbox' class='custom-control-input set-status'" +
                    " id='" + buttonID + "' " + checked + " select " + readOnlyPaso + "> <label class='custom-control-label' for='" + buttonID + "'></label></span>"
                row.append($('<td></td>').append(statusPaso));

                $(table).append(row);
            }

            // came.main.initList(table);
        }
    }

    function setStatusSurvey(sender) {
        console.log(sender);

        var idSurvey = $(sender).attr('id');
        var status = $(sender).prop('checked');

        $.ajax({
            type: "POST",
            url: urls.api.setSurveyStatus,
            //dataType: "json",
            //contentType: 'application/json',
            data: jQuery.param({ idSurvey: idSurvey, status: status })
        }).done(function (msg) {
            console.log("response done", msg);
            came.notify.showSucces("Encuesta", "Su solicitud se ha procesado correctamente.");
        });
    }

    function getProvincias(sender) {

        $(getIds.selector_deprov + " option").each(function () {
            $(this).remove();
        });

        var idRegion = $(sender).val();
        if (idRegion !== '') {
            $.ajax({
                type: "GET",
                url: urls.api.getProvincias,
                dataType: "json",
                async: false,
                //contentType: 'application/json',
                data: { idRegion: idRegion }
            }).done(function (response) {
                console.log("response done", JSON.stringify(response));
                $(getIds.selector_deprov).append($('<option selected>').val('').text('Seleccionar'));
                for (var i = 0; i < response.length; i++) {
                    $(getIds.selector_deprov).append($('<option>').val(response[i].value).text(response[i].displayText));
                }
            }).fail(function (jqXHR, textStatus) {
                console.error("response fail", jqXHR);
                came.notify.showError("Usuarios", "Su solicitud se ha procesado con errores.");
                return false;
            });
        } else {
            $(getIds.selector_deprov).append($('<option selected>').val('').text('Seleccionar'));
        }
    }

    function selectRegionDeprov() {
        var usuarioPaso = came.env.getEnvUser();
        if (usuarioPaso) {
            $(getIds.selector_region).val(usuarioPaso.idRegion);
            $(getIds.selector_region).change();
            $(getIds.selector_deprov).val(usuarioPaso.idDeprov);
            $(getIds.selector_deprov).change();

            if (usuarioPaso.nombrePerfilNivel === "regional") {
                $(getIds.selector_region).prop('disabled', 'disabled');
            } else if (usuarioPaso.nombrePerfilNivel === "provincial") {
                $(getIds.selector_region).prop('disabled', 'disabled');
                $(getIds.selector_deprov).prop('disabled', 'disabled');
            } else {
                $(getIds.selector_region).prop('disabled', false);
                $(getIds.selector_deprov).prop('disabled', false);
            }

            console.log(usuarioPaso.idPerfil);
            if (usuarioPaso.idPerfil !== "2416264829766468643") {
                $(getIds.selector_perfil).val(usuarioPaso.idPerfil);
                $(getIds.selector_perfil).prop('disabled', 'disabled');
            }
        }
    }

    function goBack() {
        window.history.back();
    }

    function init() {
        // setViewAcces();
        initListener();
        //selectRegionDeprov();
    }

    return {
        init: init
    };
})();

$(document).ready(function () {
    console.log('came.survey.init()');
    came.survey.init();
});