module com.example.studentprofile {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.studentprofile to javafx.fxml;
    exports com.example.studentprofile;
    //    exports ShowProfile;
//    opens ShowProfile to javafx.fxml;
}