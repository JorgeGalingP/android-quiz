package com.ldm.practica1.models;

public class CorrectAnswer {
    private Boolean answer_a_correct;
    private Boolean answer_b_correct;
    private Boolean answer_c_correct;
    private Boolean answer_d_correct;
    private Boolean answer_e_correct;
    private Boolean answer_f_correct;

    public CorrectAnswer() {
        this.answer_a_correct = null;
        this.answer_b_correct = null;
        this.answer_c_correct = null;
        this.answer_d_correct = null;
        this.answer_e_correct = null;
        this.answer_f_correct = null;
    }

    public CorrectAnswer(Boolean answer_a_correct,
                         Boolean answer_b_correct,
                         Boolean answer_c_correct,
                         Boolean answer_d_correct,
                         Boolean answer_e_correct,
                         Boolean answer_f_correct) {
        this.answer_a_correct = answer_a_correct;
        this.answer_b_correct = answer_b_correct;
        this.answer_c_correct = answer_c_correct;
        this.answer_d_correct = answer_d_correct;
        this.answer_e_correct = answer_e_correct;
        this.answer_f_correct = answer_f_correct;
    }

    public Boolean getAnswer_a_correct() {
        return answer_a_correct;
    }

    public void setAnswer_a_correct(Boolean answer_a_correct) {
        this.answer_a_correct = answer_a_correct;
    }

    public Boolean getAnswer_b_correct() {
        return answer_b_correct;
    }

    public void setAnswer_b_correct(Boolean answer_b_correct) {
        this.answer_b_correct = answer_b_correct;
    }

    public Boolean getAnswer_c_correct() {
        return answer_c_correct;
    }

    public void setAnswer_c_correct(Boolean answer_c_correct) {
        this.answer_c_correct = answer_c_correct;
    }

    public Boolean getAnswer_d_correct() {
        return answer_d_correct;
    }

    public void setAnswer_d_correct(Boolean answer_d_correct) {
        this.answer_d_correct = answer_d_correct;
    }

    public Boolean getAnswer_e_correct() {
        return answer_e_correct;
    }

    public void setAnswer_e_correct(Boolean answer_e_correct) {
        this.answer_e_correct = answer_e_correct;
    }

    public Boolean getAnswer_f_correct() {
        return answer_f_correct;
    }

    public void setAnswer_f_correct(Boolean answer_f_correct) {
        this.answer_f_correct = answer_f_correct;
    }

    @Override
    public String toString() {
        return "CorrectAnswer{" +
                "answer_a_correct=" + answer_a_correct +
                ", answer_b_correct=" + answer_b_correct +
                ", answer_c_correct=" + answer_c_correct +
                ", answer_d_correct=" + answer_d_correct +
                ", answer_e_correct=" + answer_e_correct +
                ", answer_f_correct=" + answer_f_correct +
                '}';
    }
}
