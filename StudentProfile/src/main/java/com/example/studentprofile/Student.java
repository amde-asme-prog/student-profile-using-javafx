package com.example.studentprofile;
import java.io.Serializable;

public class Student implements Serializable {
    String fullName;
    String listOfHobbies;
    String yourDream;
    String favoriteMusics;
    String favoriteMovies;
    String photoPath;
    String skills;



    public Student(String name , String list_of_hobbies , String your_dream , String favorite_music , String favorite_movies ) throws Exception ,NullPointerException{

        this.fullName = name;
        this.listOfHobbies = list_of_hobbies;
        this.yourDream = your_dream;
        this.favoriteMusics = favorite_music;
        this.favoriteMovies = favorite_movies;
        System.out.println(name + " " + list_of_hobbies +  " " + your_dream + " " + favorite_movies + " " + favorite_music);

//        this.photoPath = photo_path;
    }
    public Student(String name ,String list_of_hobbies , String your_dream , String favorite_music , String favorite_movies , String photo_path ,String skills ) throws Exception ,NullPointerException{


        this.fullName = name;
        this.listOfHobbies = list_of_hobbies;
        this.yourDream = your_dream;
        this.favoriteMusics = favorite_music;
        this.favoriteMovies = favorite_movies;
        this.skills = skills;
        this.photoPath = photo_path;

        System.out.println(name + " " + list_of_hobbies +  " " + your_dream + " " + favorite_movies + " " + favorite_music);

//
    }

    public String getFullName() {
        return fullName;
    }

    public String getListOfHobbies() {
        return listOfHobbies;
    }

    public String getYourDream() {
        return yourDream;
    }

    public String getFavoriteMusics() {
        return favoriteMusics;
    }

    public String getFavoriteMovies() {
        return favoriteMovies;
    }

    public String getPhotoPath() {
        return photoPath;
    }
    public String getSkills() {
        return skills;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setListOfHobbies(String listOfHobbies) {
        this.listOfHobbies = listOfHobbies;
    }

    public void setYourDream(String yourDream) {
        this.yourDream = yourDream;
    }

    public void setFavoriteMusics(String favoriteMusics) {
        this.favoriteMusics = favoriteMusics;
    }

    public void setFavoriteMovies(String favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
