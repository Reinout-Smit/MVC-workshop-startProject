package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import view.ApplicationLauncher;

public class WelcomeController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private MenuButton taskMenuButton;

    @FXML
    private Button logoutButton;

    public void setup() {

        welcomeLabel.setText("Welkom, maak een keuze uit het menu.");

        MenuItem item1 = new MenuItem("Aanmaken nieuwe klant");
        item1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ApplicationLauncher.getSceneManager().showNewCustomerScene();
            }
        });
        taskMenuButton.getItems().add(item1);

        MenuItem item2 = new MenuItem("Wijzigen bestaande klant");
        item2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                ApplicationLauncher.getSceneManager().showCustomerListScene();
            }
        });

        taskMenuButton.getItems().add(item2);
    }

    @FXML
    public void doQuit(ActionEvent event) {
        System.exit(0);
    }
}
