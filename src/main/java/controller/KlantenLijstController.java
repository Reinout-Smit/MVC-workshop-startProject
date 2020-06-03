package controller;

import javadb.CustomerDAO;
import javadb.DBaccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Customer;
import view.ApplicationLauncher;

import java.util.List;

public class KlantenLijstController {
    private CustomerDAO cdao;
    private DBaccess db;

    @FXML
    ListView<Customer> customerList;

    @FXML
    TextField warningText;

    public KlantenLijstController() {
        super();
        this.db = ApplicationLauncher.getDBaccess();
    }

    public void setup() {
        this.cdao = new CustomerDAO(db.getConnection());
        List<Customer> allCustomers = cdao.getAllCustomers();
        for (Customer c : allCustomers) {
            customerList.getItems().add(c);
        }
    }

    public void doChangeCustomer(ActionEvent event) {
        Customer customer = customerList.getSelectionModel().getSelectedItem();
        if (customer == null) {
            warningText.setVisible(true);
            warningText.setText("Je moet eerst een klant kiezen!");
            return;
        }
        ApplicationLauncher.getSceneManager().showExistingCustomerScene(customer);

    }

    public void doBackToMenu(){
        ApplicationLauncher.getSceneManager().showWelcomeScene();
    }

}
