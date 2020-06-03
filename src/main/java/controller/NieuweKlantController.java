package controller;

import javadb.CustomerDAO;
import javadb.DBaccess;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Customer;
import view.ApplicationLauncher;

import java.util.List;

public class NieuweKlantController {
    private CustomerDAO cdao;
    private DBaccess db;

    @FXML
    TextField warningText;

    @FXML
    private TextField klantnummerTextfield;

    @FXML
    private TextField voorlettersTextfield;

    @FXML
    private TextField tussenvoegselTextfield;

    @FXML
    private TextField achternaamTextfield;

    @FXML
    private TextField mobielTextfield;



    public NieuweKlantController() {
        super();
        this.db = ApplicationLauncher.getDBaccess();
        this.cdao = new CustomerDAO(db.getConnection());
    }

    @FXML
    public void doStore(ActionEvent actionEvent) {
        String initials = voorlettersTextfield.getText();
        String prefix = tussenvoegselTextfield.getText();
        String surname = achternaamTextfield.getText();
        String mobile = mobielTextfield.getText();
        if(initials.trim().isEmpty() || surname.trim().isEmpty()){
            warningText.setVisible(true);
            warningText.setText("Je moet een voornaam en achternaam invullen!");
            return;
        }
        Customer newCustomer = new Customer(initials, prefix, surname, mobile );
        cdao.storeCustomer(newCustomer);
        klantnummerTextfield.setText(String.valueOf(newCustomer.getCustomerId()));
        // Bevestiging voor de gebruiker dat het aanmaken van een nieuwe klant succesvol is.
        warningText.setVisible(true);
        warningText.setText("Klant aangemaakt, veel winkelplezier!");
        db.closeConnection();
        System.out.println("Connection closed");
    }

    public void doBackToMenu(ActionEvent actionEvent) {
        ApplicationLauncher.getSceneManager().showWelcomeScene();
    }


}
