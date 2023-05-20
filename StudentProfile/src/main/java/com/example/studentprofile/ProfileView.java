package com.example.studentprofile;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.StringTokenizer;


public class ProfileView implements EventHandler<ActionEvent> {
    AnchorPane anchorPane;
    Label nameLabel;
    Label skillsInputLabel;
    Label favoriteMusicInput;
    Label visionInputLabel;
    Label favoriteMovieInput;
    Label hobbiesInputLabel;
    Image imageProfile;

    public Label getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(String  name) {
        DropShadow shadow = new DropShadow(40 , Color.valueOf("#4fa3f1"));
        nameLabel.setEffect(shadow);
        this.nameLabel.setText(name.toUpperCase() );

    }

    public Label getSkillsInputLabel() {
        return skillsInputLabel;
    }

    public void setSkillsInputLabel(String skills) {
        if(skills != null){
            this.skillsInputLabel.setText(splitData(skills));
        }
    }
    public Image getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imagePath) throws FileNotFoundException {
        this.imageProfile = new Image(new FileInputStream(Objects.requireNonNullElse(imagePath, "half_face.jpg")));

        ImageView imageView  = new ImageView();
        imageView.setImage(imageProfile);
        double height = imageProfile.getHeight();
        double width = imageProfile.getWidth();
        if(height >= 205){
            while(height >= 205){
                height *= 0.9;
                width *= 0.9;
            }
        }
        else{
            while(height <= 205){
                height *= 1.1;
                width *= 1.1;
            }
        }


        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        imageView.setLayoutX(310);
        imageView.setLayoutY(110);
        imageView.setPreserveRatio(true);
        imageView.setVisible(true);

        anchorPane.getChildren().add(imageView);

    }


    public Label getFavoriteMusicInput() {
        return favoriteMusicInput;
    }

    public void setFavoriteMusicInput(String favoriteMusics) {
        this.favoriteMusicInput.setText(splitData(favoriteMusics));
    }

    public Label getVisionInputLabel() {
        return visionInputLabel;
    }

    public void setVisionInputLabel(String visions) {
        this.visionInputLabel.setText(visions);
    }

    public Label getFavoriteMovieInput() {
        return favoriteMovieInput;
    }

    public void setFavoriteMovieInput(String favoriteMovies) {
        this.favoriteMovieInput.setText(splitData(favoriteMovies));
    }

    public Label getHobbiesInputLabel() {
        return hobbiesInputLabel;
    }

    public void setHobbiesInputLabel(String hobbies) {
        this.hobbiesInputLabel.setText(splitData(hobbies));
    }

    private String  splitData(String data){
        System.out.println("the raw data is :" + data);
        StringBuilder sb = new StringBuilder();
        StringTokenizer listData;
        if((data.contains(" ") && data.contains(",")  )  || (data.contains(",") && !data.contains(" ")) ) {
            listData = new StringTokenizer(data, ",");
            while(listData.hasMoreElements()) {
                sb.append("->").append(listData.nextElement()).append("\n");
            }
        }

            else if(data.contains(" ") && !data.contains(",")){
                listData = new StringTokenizer(data , " ");
                while(listData.hasMoreElements()){
                    sb.append(" ->").append(listData.nextElement()).append("\n");
            }

        }
            else return data;
        return (sb.toString().trim());

    }
    @Override
    public void handle(ActionEvent event) {
        if(Objects.equals(((Button) event.getSource()).getText(), "back to home page")){
            StudentsProfileHome profileHome = new StudentsProfileHome();
            try {
                profileHome.start( (Stage) ((Node) event.getSource()).getScene().getWindow());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else if(Objects.equals(((Button) event.getSource()).getText(), "exit")){
            System.exit(0);
        }

    }

    public ProfileView() throws FileNotFoundException {

//      creating a container object
        anchorPane = new AnchorPane();
        anchorPane.setId("Profile");
        anchorPane.prefHeight(740);
        anchorPane.prefWidth(640);
        Scene scene = new Scene(anchorPane);
//      --adding stylesheet--
        String studentProfileStylesheet = this.getClass().getResource("StudentsProfileStylesheet.css").toExternalForm();
        scene.getStylesheets().add(studentProfileStylesheet);


//      ==========creating labels for the user profile============
//      ==========user name label===========
        nameLabel = new Label("user name");
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.setMinSize(453 ,47 );
        nameLabel.setLayoutX(97);
        nameLabel.setLayoutY(14);
        nameLabel.setId("nameLabel");
//        ====================================


//      ============================================
//      ==========skills  label=======================
        Label skillLabel = new Label("My Skills");
        skillLabel.setAlignment(Pos.CENTER);
        skillLabel.prefHeight(25);
        skillLabel.prefWidth(178);
        skillLabel.setMinSize(130 , 25);
        skillLabel.setMaxWidth(130);
        skillLabel.setLayoutX(60);
        skillLabel.setLayoutY(80);
        skillLabel.setId("skills");
//      ============skills  input label===================
        skillsInputLabel = new Label("my skills");
        skillsInputLabel.setAlignment(Pos.CENTER);
        skillsInputLabel.setMinSize(200 , 180);
        skillsInputLabel.setLayoutX(35);
        skillsInputLabel.setLayoutY(117);
        skillsInputLabel.setId("skillsInput");
//       ======================================
        Label profileLabel = new Label("My Profile");
        profileLabel.setAlignment(Pos.CENTER);
        profileLabel.prefHeight(25);
        profileLabel.prefWidth(178);
        profileLabel.setMinSize(178 , 25);
        profileLabel.setLayoutX(368);
        profileLabel.setLayoutY(80);
        profileLabel.setId("skills");

//        ==============================================

//        =============================================
//      ========favorite music Label==================
        Label favoriteMusic = new Label("Favorite Music");
        favoriteMusic.setAlignment(Pos.CENTER);
        favoriteMusic.setMinSize(153 , 17);
        favoriteMusic.setLayoutX(66);
        favoriteMusic.setLayoutY(315);
        favoriteMusic.setId("musicLabel");
//      ==========music input label=========
        favoriteMusicInput = new Label();
        favoriteMusicInput.setAlignment(Pos.CENTER);
        favoriteMusicInput.setMinSize(202 , 87);
        favoriteMusicInput.setLayoutX(40);
        favoriteMusicInput.setLayoutY(344);
        favoriteMusicInput.setId("musicInputLabel");
//        ============================================


//      ================================================
//      =============favorite movies Label=================
        Label favoriteMovie = new Label("favorite movies");
        favoriteMovie.setAlignment(Pos.CENTER);
        favoriteMovie.setMinSize(153 ,17);
        favoriteMovie.setLayoutX(360);
        favoriteMovie.setLayoutY(315);
        favoriteMovie.setId("favoriteLabel");
//      ===========movies input label================
        favoriteMovieInput = new Label();
        favoriteMovieInput.setAlignment(Pos.CENTER);
        favoriteMovieInput.setMinSize( 202 , 87);
        favoriteMovieInput.setLayoutX(332);
        favoriteMovieInput.setLayoutY(344);
        favoriteMovieInput.setId("movieInputLabel");


//      ===========================================
        Label hobbiesLabel = new Label("My Hobbies");
        hobbiesLabel.setAlignment(Pos.CENTER);
        hobbiesLabel.setLayoutX(80);
        hobbiesLabel.setLayoutY(498);
        hobbiesLabel.setId("hobbiesLabel");
        hobbiesLabel.setMinSize(150 , 17);


//      ===========vision label input=====================
        hobbiesInputLabel = new Label();
        hobbiesInputLabel.setAlignment(Pos.CENTER);
        hobbiesInputLabel.setLayoutX(35);
        hobbiesInputLabel.setLayoutY(525);
        hobbiesInputLabel.setId("hobbiesInputLabel");
        hobbiesInputLabel.setMinSize(250 , 110 );

//      ============my vision Label===============
        Label visionLabel = new Label("My Vision");
        visionLabel.setAlignment(Pos.CENTER);
        visionLabel.setLayoutX(380);
        visionLabel.setLayoutY(498);
        visionLabel.setId("visionLabel");
        visionLabel.setMinSize(150 , 17);
//      ===========vision label input=====================
        visionInputLabel = new Label();
        visionInputLabel.setAlignment(Pos.CENTER);
        visionInputLabel.setLayoutX(330);
        visionInputLabel.setLayoutY(525);
        visionInputLabel.setId("visionInputLabel");
        visionInputLabel.setMinSize(250 , 110 );
//        =================================================

//      ===========================================
//        =================================================
        Button homePage = new Button("back to home page");
        homePage.setAlignment(Pos.CENTER);
        homePage.prefHeight(50);
        homePage.prefWidth(189);
        homePage.setLayoutX(423);
        homePage.setLayoutY(656);
        homePage.setFont(new Font(19));
        homePage.setOnAction(this);
//
        //        =====exit button =====================
        Button exit = new Button("exit");
        exit.setAlignment(Pos.CENTER);
        exit.prefHeight(39);
        exit.prefWidth(79);
        exit.setLayoutX(40);
        exit.setLayoutY(650);
        exit.setFont(new Font(18));
        exit.setOnAction(this);

//      adding all elements in the scene
        anchorPane.
                getChildren().
                addAll(nameLabel ,
                    skillLabel ,
                    skillsInputLabel ,
                    profileLabel,
                    favoriteMusic,
                    favoriteMusicInput ,
                    favoriteMovie ,
                    favoriteMovieInput ,
                    visionLabel,
                    visionInputLabel,
                    hobbiesLabel,
                    hobbiesInputLabel,
                    homePage,
                    exit
                );

//        ===============================
        Stage stage = new Stage();
        stage.setTitle(" Students Profile view ");//providing title for the stage

//      set up the position and the size of the stage
        stage.setHeight(750);
        stage.setWidth(650);

        stage.getIcons().add(new BitLogo().getLogo() );//setting up the stage icon
        stage.setScene(scene);

//        stage.setResizable(false);//forcing user not to resize the dimension of the stage

        stage.getMaxHeight();
        stage.getMaxWidth();
        stage.show();
    }
}
