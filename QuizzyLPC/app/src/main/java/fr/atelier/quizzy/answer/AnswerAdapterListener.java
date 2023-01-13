package fr.atelier.quizzy.answer;

import java.util.HashMap;

public interface AnswerAdapterListener {
    public void onClickAnswer(String item, HashMap<String, Double> listPoint);
}
