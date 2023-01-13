package fr.atelier.quizzy.question;

import java.util.ArrayList;

public class ListQuestion {
    static ArrayList<Question> listQuestion;

    public ListQuestion() {
        listQuestion = new ArrayList<>();
    }

    public int size() {
        return listQuestion.size();
    }

    public static Question get(int pos) {
        return listQuestion.get(pos);
    }

    public void construireListe(ArrayList<Question> listQ) {
        listQuestion = listQ;
    }
}