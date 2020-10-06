package com.ldm.practica1.utils;

import com.ldm.practica1.models.Answer;
import com.ldm.practica1.models.CorrectAnswer;
import com.ldm.practica1.models.Question;

public class AnswerChecker {
    public static boolean CheckCorrectAnswer(String answerString, Question question){
        Answer answer = question.getAnswers();
        final String answerA = answer.getAnswer_a();
        final String answerC = answer.getAnswer_c();
        final String answerB = answer.getAnswer_b();
        final String answerD = answer.getAnswer_d();
        final String answerE = answer.getAnswer_e();
        final String answerF = answer.getAnswer_f();

        CorrectAnswer correctAnswer = question.getCorrect_answers();
        boolean correctA = correctAnswer.getAnswer_a_correct();
        boolean correctB = correctAnswer.getAnswer_b_correct();
        boolean correctC = correctAnswer.getAnswer_c_correct();
        boolean correctD = correctAnswer.getAnswer_d_correct();
        boolean correctE = correctAnswer.getAnswer_e_correct();
        boolean correctF = correctAnswer.getAnswer_f_correct();

        boolean check = false;

        if (answerString.equals(answerA) && correctA)
            check = true;
        if (answerString.equals(answerB) && correctB)
            check = true;
        if (answerString.equals(answerC) && correctC)
            check = true;
        if (answerString.equals(answerD) && correctD)
            check = true;
        if (answerString.equals(answerE) && correctE)
            check = true;
        if (answerString.equals(answerF) && correctF)
            check = true;

        return check;
    }
}
