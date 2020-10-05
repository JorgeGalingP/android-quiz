package com.ldm.practica1.models;

import java.util.Arrays;
import java.util.List;

public class Question {
    private String id;
    private String question;
    private String description;
    private Answer answers;
    private Boolean multiple_correct_answers;
    private CorrectAnswer correct_answers;
    private String correct_answer;
    private String explanation;
    private String tip;
    private List<Tag> tags;
    private String category;
    private String difficulty;

    public Question() {
        this.id = null;
        this.question = null;
        this.description = null;
        this.answers = null;
        this.multiple_correct_answers = null;
        this.correct_answers = null;
        this.correct_answer = null;
        this.explanation = null;
        this.tip = null;
        this.tags = null;
        this.category = null;
        this.difficulty = null;
    }

    public Question(String id,
                    String question,
                    String description,
                    Answer answers,
                    Boolean multiple_correct_answers,
                    CorrectAnswer correct_answers,
                    String correct_answer,
                    String explanation,
                    String tip,
                    List<Tag> tags,
                    String category,
                    String difficulty) {
        this.id = id;
        this.question = question;
        this.description = description;
        this.answers = answers;
        this.multiple_correct_answers = multiple_correct_answers;
        this.correct_answers = correct_answers;
        this.correct_answer = correct_answer;
        this.explanation = explanation;
        this.tip = tip;
        this.tags = tags;
        this.category = category;
        this.difficulty = difficulty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Answer getAnswers() {
        return answers;
    }

    public void setAnswers(Answer answers) {
        this.answers = answers;
    }

    public Boolean getMultiple_correct_answers() {
        return multiple_correct_answers;
    }

    public void setMultiple_correct_answers(Boolean multiple_correct_answers) {
        this.multiple_correct_answers = multiple_correct_answers;
    }

    public CorrectAnswer getCorrect_answers() {
        return correct_answers;
    }

    public void setCorrect_answers(CorrectAnswer correct_answers) {
        this.correct_answers = correct_answers;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", item_question='" + question + '\'' +
                ", description='" + description + '\'' +
                ", answers='" + answers.toString() + '\'' +
                ", multiple_correct_answers=" + multiple_correct_answers +
                ", correct_answers='" + correct_answers.toString() + '\'' +
                ", correct_answer='" + correct_answer + '\'' +
                ", explanation='" + explanation + '\'' +
                ", tip='" + tip + '\'' +
                ", tags='" + Arrays.toString(tags.toArray()) + '\'' +
                ", category='" + category + '\'' +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }
}
