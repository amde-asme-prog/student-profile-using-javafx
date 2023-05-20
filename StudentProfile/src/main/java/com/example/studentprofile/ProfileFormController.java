package com.example.studentprofile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileFormController implements Initializable {

    private boolean updating;
    @FXML
    Label formTitle;

    //from input form fxml
    @FXML
    private Label warningLabel;
    @FXML
    TextField nameField;
    @FXML
    private TextArea hobbyField;
    @FXML
    TextArea dreamField;
    @FXML
    TextArea musicField;
    @FXML
    TextArea movieField;
    @FXML
    TextArea skillsInput;
    @FXML
    Button photoChooserButton;
    private String photoPath;

    public boolean isUpdating() {
        return this.updating;
    }

    public void setUpdating(boolean updating) {
        this.updating = updating;
    }

    public TextField getNameField() {
        return nameField;
    }

    public void setNameField(String text) {
        this.nameField.setText(text);
    }

    public TextArea getHobbyField() {
        return hobbyField;
    }

    public void setHobbyField(String text) {
        this.hobbyField.setText(text);
    }

    public TextArea getDreamField() {
        return dreamField;
    }

    public void setDreamField(String dreams) {
        this.dreamField.setText(dreams);
    }
    public TextArea getMusicField() {
        return musicField;
    }

    public void setMusicField(String text) {
        this.musicField.setText(text);
    }

    public TextArea getMovieField() {
        return movieField;
    }

    public void setMovieField(String text) {
        this.movieField.setText(text);
    }

    public TextArea getSkillsInput() {
        return skillsInput;
    }

    public void setSkillsInput(String text) {
        this.skillsInput.setText(text);
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }


    public void onSubmitButtonClicked(ActionEvent event) throws Exception{
        if(nameField.getText().isBlank()) {
            warningLabel.setText("the name field must be filled!");
        }
        else if(musicField.getText().isBlank())
        {
            warningLabel.setText("you must list out your favorite musics !");
        }else if(movieField.getText().isBlank())
        {
            warningLabel.setText("you must list out your favorite movies !");
        } else if(hobbyField.getText().isBlank())
        {
            warningLabel.setText("the hobbies field must be filled");
        } else if(dreamField.getText().isBlank())
        {
            warningLabel.setText("you must write something about your future dream");
        }
        else if(skillsInput.getText().isBlank())
        {
            warningLabel.setText("you must at least mention one skill");
        }
        else if(photoPath == null){
            warningLabel.setText("you must choose your profile photo");
        }
        else{
            warningLabel.setText("submitting ! ");
            if(updating){
                System.out.println("updating : "+ updating);

//                1. create a file with nameField text value
                File file = new File("listOfProfiles/"+nameField.getText().toLowerCase().concat(".dat"));
//                2. creating a file reader object
                ObjectInputStream ois = null;
                try {
                    ois = new ObjectInputStream(new FileInputStream(file));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
//                3. accessing student object in the file
                try {
                    Student student = (Student) ois.readObject();//student object
//                    4. assigning the value of the form field into the object
                    student.setFullName(nameField.getText());
                    student.setSkills(skillsInput.getText());
                    student.setListOfHobbies(getHobbyField().getId());
                    student.setFavoriteMusics(getMusicField().getText());
                    student.setFavoriteMovies(getMovieField().getText());
                    student.setYourDream(getDreamField().getText());
                    student.setPhotoPath(getPhotoPath());

                    ois.close();
//                    5. writing the updated object in the file
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                    oos.writeObject(student);
                    oos.close();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                warningLabel.setText("The student profile is updated successfully!!!");
            }
            else {
                System.out.println("updating : " + updating);
//                1. create a file with nameField text value
                File studentFile = new File("listOfProfiles/"+ nameField.getText()+".dat");
//                2. creating file writer object
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(studentFile));
//                3. writing student object in the file
                Student student = new Student( nameField.getText().toLowerCase(),
                        hobbyField.getText(),
                        dreamField.getText(),
                        musicField.getText(),
                        movieField.getText() ,
                        photoPath ,
                        skillsInput.getText());

                oos.writeObject(student);
                oos.close();
                warningLabel.setText("The form is submitted successfully!!!");
            }
           }
        }
    public void exitFunction(){

        System.out.println("exit button c");
        System.exit(0);
    }

    public void backToHomePage(ActionEvent event){
        StudentsProfileHome profileHome = new StudentsProfileHome();
        profileHome.start( (Stage) ((Node) event.getSource()).getScene().getWindow());
    }
    public void choosePhoto(ActionEvent event)  throws Exception{
        FileChooser photoChooser = new FileChooser();
        photoChooser.setTitle("Select Your Profile photo!");
        photoChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG files" , "*.jpg" ,"*.png"));
        photoPath = photoChooser.showOpenDialog(null).getPath();
        Image image = new Image(photoPath);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DropShadow shadow = new DropShadow(50 , Color.valueOf("#4fa3f1"));
        formTitle.setEffect(shadow);
    }
}
