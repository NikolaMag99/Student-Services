package studsluzba.client.fxmlcontrollers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import studsluzba.model.SkolskaGodina;
import studsluzba.services.IspitiService;
import studsluzba.services.SifarniciService;

import java.time.LocalDate;
import java.util.List;

@Component
public class AddIspitniRokController {

    @Autowired
    SifarniciService sifarniciService;

    @Autowired
    IspitiService ispitiService;

    @Autowired
    NewIspitController newIspitController;

    @FXML
    DatePicker datumPocetkaDp;

    @FXML
    DatePicker datumZavrsetkaDp;

    @FXML
    ComboBox<SkolskaGodina> skolskaGodinaCb = new ComboBox<>();


    @FXML
    public void addIspitniRok(ActionEvent event) {
        LocalDate pocetak = datumPocetkaDp.getValue();
        LocalDate zavrsetak = datumZavrsetkaDp.getValue();
        SkolskaGodina skolskaGodina = skolskaGodinaCb.getValue();

        ispitiService.saveIspitniRok(pocetak, zavrsetak, skolskaGodina);

        newIspitController.updateIspitniRokovi();
        closeStage(event);

    }

    @FXML
    public void initialize() {
        List<SkolskaGodina> skolskeLista = FXCollections.observableList(sifarniciService.getSkolskeGodine());
        skolskaGodinaCb.setItems(FXCollections.observableArrayList(skolskeLista));
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
