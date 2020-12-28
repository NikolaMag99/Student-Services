package studsluzba.client.fxmlcontrollers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import studsluzba.coders.CoderFactory;
import studsluzba.coders.CoderType;
import studsluzba.coders.SimpleCode;
import studsluzba.model.SrednjaSkola;
import studsluzba.services.SifarniciService;

@Component
public class AddSrednjaSkolaController {

    @Autowired
    SifarniciService sifarniciService;

    @Autowired
    StudentController studentController;

    @FXML
    TextField nazivNoveSrednjeSkoleTf;

    @FXML
    ComboBox<SimpleCode> mestoNoveSrednjeSkoleCb;

    @FXML
    ComboBox<SimpleCode> tipNoveSrednjeSkoleCb;

    @Autowired
    CoderFactory coderFactory;

    @FXML
    public void addSrednjaSkola(ActionEvent event) {
        SrednjaSkola ss = new SrednjaSkola();
        if (mestoNoveSrednjeSkoleCb.getValue() != null) ss.setMesto(mestoNoveSrednjeSkoleCb.getValue().toString());
        ss.setNaziv(nazivNoveSrednjeSkoleTf.getText());
        if (tipNoveSrednjeSkoleCb.getValue() != null) ss.setVrsta(tipNoveSrednjeSkoleCb.getValue().toString());
        sifarniciService.saveSrednjaSkola(ss);
        studentController.updateSrednjeSkole();
        closeStage(event);
    }

    @FXML
    public void initialize() {
        mestoNoveSrednjeSkoleCb.setItems(FXCollections.observableArrayList(coderFactory.getSimpleCoder(CoderType.MESTO).getCodes()));
        tipNoveSrednjeSkoleCb.setItems(FXCollections.observableArrayList(coderFactory.getSimpleCoder(CoderType.TIP_SREDNJE_SKOLE).getCodes()));
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
