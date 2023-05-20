package com.example.studentprofile;

import javafx.scene.image.Image;

import java.io.File;

class BitLogo{
    Image icon ;
    BitLogo(){
        System.out.println( System.getProperty("user.dir"));
        icon =  new Image(System.getProperty("user.dir")+"\\listOfPhotos\\bahir-dar_university_logo.png");
    }
    public Image getLogo(){
        return this.icon;
    }

}
