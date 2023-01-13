package fr.atelier.quizzy.question;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
    private String question;
    private String answer;
    private String answerPrint;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
            this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerPrint() {
        return answerPrint;
    }

    public void setAnswerPrint(String answerPrint) {
        this.answerPrint = answerPrint;
    }

    public Question(String question, String answer, String answerPrint) {
        setQuestion(question);
        setAnswer(answer);
        setAnswerPrint(answerPrint);
    }

    public Question(Parcel in) {
        setQuestion(in.readString());
        setAnswer(in.readString());
        setAnswerPrint(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(question);
        parcel.writeString(answer);
        parcel.writeString(answerPrint);
    }

    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {

        @Override
        public Question createFromParcel(Parcel source) {
            return new Question(source);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public static Parcelable.Creator<Question> getCreator() {
        return CREATOR;
    }
}