package studsluzba.client.fxmlcontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import studsluzba.model.Ispit;
import studsluzba.services.IspitiService;

@Component
public class AddIspitController {

    @Autowired
    IspitiService ispitiService;

    @Autowired
    IspitController ispitController;

    @FXML
    TextField sifraIspitaTf;

    @FXML
    public void addIspit(ActionEvent event) {
        Ispit ss = new Ispit();
        ss.setSifraIspita(sifraIspitaTf.getText());
        closeStage(event);
    }

    @FXML
    public void initialize() {

    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
