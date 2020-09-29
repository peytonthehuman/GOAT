package com.mycompany.goat_gui;

import java.io.IOException;
import javafx.fxml.FXML;

public class SignupController {

    @FXML
    private void SwitchToProfile() throws IOException {
        App.setRoot("profile");
    }
}
