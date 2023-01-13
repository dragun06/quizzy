package fr.atelier.quizzy.course;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import fr.atelier.quizzy.question.Question;

public class Course implements Parcelable {
    private int id;
    private int image1;
    private int image2;
    private String title;
    private String paragraph1;
    private String paragraph2;
    private String paragraph3;
    private double latitude;
    private double longitude;
    private ArrayList<Question> listQuestion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage1() {
        return image1;
    }

    public void setImage1(int image) {
        this.image1 = image;
    }

    public int getImage2() {
        return image2;
    }

    public void setImage2(int image) {
        this.image2 = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParagraph1() {
        return paragraph1;
    }

    public void setParagraph1(String paragraph) {
        this.paragraph1 = paragraph;
    }

    public String getParagraph2() {
        return paragraph2;
    }

    public void setParagraph2(String paragraph) {
        this.paragraph2 = paragraph;
    }

    public String getParagraph3() {
        return paragraph3;
    }

    public void setParagraph3(String paragraph) {
        this.paragraph3 = paragraph;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setListQuestion(ArrayList<Question> listQuestion) {
        this.listQuestion = listQuestion;
    }

    public ArrayList<Question> getListQuestion() {
        return listQuestion;
    }

    public Course(int id, int image1, int image2, String title, String paragraph1, String paragraph2, String paragraph3, double latitude, double longitude, ArrayList<Question> listQuestion) {
        setId(id);
        setImage1(image1);
        setImage2(image2);
        setTitle(title);
        setParagraph1(paragraph1);
        setParagraph2(paragraph2);
        setParagraph3(paragraph3);
        setLatitude(latitude);
        setLongitude(longitude);
        setListQuestion(listQuestion);
    }

    public Course(Parcel in) {
        setId(in.readInt());
        setImage1(in.readInt());
        setImage2(in.readInt());
        setTitle(in.readString());
        setParagraph1(in.readString());
        setParagraph2(in.readString());
        setParagraph3(in.readString());
        setLatitude(in.readDouble());
        setLongitude(in.readDouble());
        setListQuestion(in.readArrayList(Question.class.getClassLoader()));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(image1);
        parcel.writeInt(image2);
        parcel.writeString(title);
        parcel.writeString(paragraph1);
        parcel.writeString(paragraph2);
        parcel.writeString(paragraph3);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeList(listQuestion);
    }

    public static final Parcelable.Creator<Course> CREATOR = new Parcelable.Creator<Course>() {

        @Override
        public Course createFromParcel(Parcel source) {
            return new Course(source);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    public static Parcelable.Creator<Course> getCreator() {
        return CREATOR;
    }
}