package controller;

import javadb.CustomerDAO;
import javadb.DBaccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Customer;
import view.ApplicationLauncher;

public class WijzigKlantController {
    private CustomerDAO cdao;
    private DBaccess db;

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



    public WijzigKlantController() {
        super();
        this.db = ApplicationLauncher.getDBaccess();;
        this.cdao = new CustomerDAO(db.getConnection());
    }

    @FXML
    public void doUpdate(ActionEvent event) {
        int id = Integer.parseInt(klantnummerTextfield.getText());
        String initials = voorlettersTextfield.getText();
        String prefix = tussenvoegselTextfield.getText();
        String surname = achternaamTextfield.getText();
        String mobile = mobielTextfield.getText();
        Customer customer = new Customer(id, initials, prefix, surname, mobile);
        cdao.updateCustomer(customer);
        db.closeConnection();
    }

    public void doBackToMenu(ActionEvent actionEvent) {
        ApplicationLauncher.getSceneManager().showWelcomeScene();
    }

    public void setup(Customer customer) {
        klantnummerTextfield.setText(String.valueOf(customer.getCustomerId()));
        voorlettersTextfield.setText(customer.getInitials());
        tussenvoegselTextfield.setText(customer.getPrefix());
        achternaamTextfield.setText(customer.getSurName());
        mobielTextfield.setText(customer.getMobilePhone());
    }
}
