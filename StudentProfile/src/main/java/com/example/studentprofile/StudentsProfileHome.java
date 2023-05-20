package com.example.studentprofile;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.*;
import java.util.Objects;

public class StudentsProfileHome extends Application implements EventHandler<ActionEvent>{
    Label messageLabel;
    AnchorPane anchorPane;
    private double x;
    private double y;
    private boolean isFullScreen = false;
    @Override
    public void start(Stage stage) {

//        1. setting up a container object
        anchorPane = new AnchorPane();
        anchorPane.setId("studentsProfileView");
        anchorPane.prefHeight(740);
        anchorPane.prefWidth(640);

//        2. modifying the welcome text
        DropShadow shadow = new DropShadow(40 , Color.valueOf("#4fa3f1"));
        Label welcomeText = new Label("Welcome");
        welcomeText.setAlignment(Pos.CENTER);
        welcomeText.setMinSize(250 , 17);
        welcomeText.setLayoutX(195);
        welcomeText.setLayoutY(30);
        welcomeText.setFont(new Font(53));
        welcomeText.setEffect(shadow);
        welcomeText.setId("welcomeText");
        //=====================================

//        3 . modifying list of students label
        Label listOfStudentsProfile = new Label("LIST OF STUDENTS PROFILE");
        listOfStudentsProfile.setAlignment(Pos.CENTER);
        listOfStudentsProfile.setMinSize(580 , 39);
//        listOfStudentsProfile.prefHeight(39);
//        listOfStudentsProfile.prefWidth(536);
        listOfStudentsProfile.setLayoutX(30);
        listOfStudentsProfile.setLayoutY(110);
        listOfStudentsProfile.setFont(new Font(26));
        listOfStudentsProfile.setId("welcomeText");

        //==================================================
//        3. creating a button to add new profile
        Button addNewProfileButton = new Button("Create Profile");
        addNewProfileButton.setAlignment(Pos.CENTER);
        addNewProfileButton.prefHeight(39);
        addNewProfileButton.prefWidth(246);
        addNewProfileButton.setLayoutX(420);
        addNewProfileButton.setLayoutY(645);
        addNewProfileButton.setFont(new Font(18));
        addNewProfileButton.setOnAction(this::addNewProfiles);

        messageLabel = new Label("create new profile  ?");
        messageLabel.setAlignment(Pos.CENTER);
        messageLabel.setMinSize(200 , 25);
        messageLabel.setLayoutX(180);
        messageLabel.setLayoutY(650);
        messageLabel.setFont(new Font(24));
        messageLabel.setId("messageLabel");
        messageLabel.setEffect(new DropShadow(20 , Color.valueOf("#4fa3f1")));

//        4. creating a button to exit the system
        Button exit = new Button("exit");
        exit.setAlignment(Pos.CENTER);
        exit.prefHeight(39);
        exit.prefWidth(79);
        exit.setLayoutX(45);
        exit.setLayoutY(645);
        exit.setFont(new Font(18));
        exit.setOnAction(this);

//        ==========================================
//        5. loading list of student profiles into the scene
        loadStudentsProfile();

        //==============adding children objects ==================

        anchorPane.getChildren().addAll(welcomeText ,
                                listOfStudentsProfile ,
                                addNewProfileButton,
                                messageLabel,
                                exit
                                    );
        //================================
        anchorPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x = event.getSceneX();
                y = event.getSceneY();
            }
        });
        anchorPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            }
        });
        Scene scene = new Scene(anchorPane);
        String studentProfileStylesheet = Objects.requireNonNull(this.getClass().getResource("StudentsProfileStylesheet.css")).toExternalForm();
        scene.getStylesheets().add(studentProfileStylesheet);

        stage.setTitle("  Students Profile  ");//providing title for the stage
//        set up the position and the size of the stage
        stage.setHeight(750);
        stage.setWidth(650);
        stage.getIcons().add(new BitLogo().getLogo() );//setting up the stage icon
        stage.setScene(scene);

//        resizeScreen(stage);
        stage.show();
    }

//==================================================================
//    ==================================================================
//    ===================================================================
//    list of methods that handle an events
    public void loadStudentsProfile(){
        int positionY = 180;
        File listOfProfiles = new File("listOfProfiles");
        String[] lists = listOfProfiles.list();
//        listing out files and display the listed files in the scene
        assert lists != null;
        for( String list : lists){
//             ====user name button============
            Button userDataButton = new Button(list.replace(".dat" , ""));
            userDataButton.setAlignment(Pos.CENTER);
            userDataButton.prefHeight(35);
            userDataButton.prefWidth(199);
            userDataButton.minWidth(100);
            userDataButton.setLayoutX(60);
            userDataButton.setLayoutY(positionY);
            userDataButton.setFont(new Font(18));
            userDataButton.setOnAction(this::showProfile);

            //=========delete button ===========
            Button deleteProfile = new Button("delete profile");
            deleteProfile.setAlignment(Pos.CENTER);
            deleteProfile.prefHeight(35);
            deleteProfile.prefWidth(170);
            deleteProfile.minWidth(100);
            deleteProfile.setLayoutX(300);
            deleteProfile.setLayoutY(positionY);
            deleteProfile.setFont(new Font(18));

            deleteProfile.setId(list);

            deleteProfile.setOnAction(this::deleteSelectedProfile);

//            ===================================
            Button updateProfile = new Button("update profile");
            updateProfile.setAlignment(Pos.CENTER);
            updateProfile.prefHeight(35);
            updateProfile.prefWidth(170);
            updateProfile.minWidth(100);
            updateProfile.setLayoutX(450);
            updateProfile.setLayoutY(positionY);
            updateProfile.setFont(new Font(18));
            updateProfile.setId(list);
            updateProfile.setOnAction(this::updateSelectedProfiles);


//        ==================================

            anchorPane.getChildren().addAll(userDataButton ,
                    deleteProfile ,
                    updateProfile);

            positionY +=45;

        }
    }
    public void deleteSelectedProfile(ActionEvent event){
        String pathName = ((Button) event.getSource()).getId();
        //create a file with the path of the button text  value
        File file = new File("listOfProfiles/"+pathName);

        if(file.delete()){
            ((Button) event.getSource()).setDisable(true);
            ((Button) event.getSource()).setText("deleted");
        }
    }
    public void updateSelectedProfiles(ActionEvent event){
//        1. accessing the id of the button which is the name of the object
        String pathName = ((Button) event.getSource()).getId();
//        2. loading tht profileForm fxml file
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("ProfileForm.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        2.1 . creating the controller instance object
        ProfileFormController formController = loader.getController();

//            3.creating a file with name of the student object
            File file = new File("listOfProfiles/"+pathName);
            //4. creating student file reader object
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(file));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

//          5. reading an object
            try {
                formController.nameField.setDisable(true);
                Student student = (Student) ois.readObject();//student object
                formController.setNameField(student.getFullName());
                formController.setSkillsInput(student.getSkills());
                formController.setDreamField(student.getYourDream());
                formController.setHobbyField(student.getListOfHobbies());
                formController.setMusicField(student.getFavoriteMusics());
                formController.setMovieField(student.getFavoriteMovies());
                formController.setPhotoPath(student.getPhotoPath());
                formController.setUpdating(true);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void addNewProfiles(ActionEvent event){

        if(Objects.equals(((Button) event.getSource()).getText(), "Create Profile")){
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("ProfileForm.fxml"));

            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//            TODO isUpdating variable must be set to false
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void showProfile(ActionEvent event){
        ProfileView profileView = null;
        try {
            profileView = new ProfileView();

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String pathName = ((Button) event.getSource()).getText();

        //create a file with the path of the button text  value
        File file = new File("listOfProfiles/"+pathName.toLowerCase().concat(".dat"));

        //creating an object reader object
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//            reading an object
        try {
//
            Student student = (Student) ois.readObject();//student object
            profileView.setImageProfile(student.getPhotoPath());
            System.out.println("photoPath in home page :" + student.getPhotoPath());
            profileView.setNameLabel(student.getFullName());
            profileView.setFavoriteMusicInput(student.getFavoriteMusics());
            profileView.setFavoriteMovieInput(student.getFavoriteMovies());
            profileView.setSkillsInputLabel(student.getSkills());
            profileView.setVisionInputLabel(student.getYourDream());
            profileView.setHobbiesInputLabel(student.getListOfHobbies());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handle(ActionEvent event){
         if(Objects.equals(((Button) event.getSource()).getText(), "exit")){
            System.exit(0);//exit out of the system
        }
    }
    public void resizeScreen(Stage stage){
        if(!this.isFullScreen){
            stage.setFullScreen(true);
//            anchorPane.setLayoutX(300);
            stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("esc"));
            stage.setFullScreenExitHint("Press esc to exit fullscreen Mode");
            this.isFullScreen = true;
        }
        else{
            //setting up the position of the stage appearance

            stage.setHeight(750);
            stage.setWidth(650);
            //TODO all scenes screen size must be the same
        }
    }
    public static void main(String[] args) {

        launch();
    }

}
