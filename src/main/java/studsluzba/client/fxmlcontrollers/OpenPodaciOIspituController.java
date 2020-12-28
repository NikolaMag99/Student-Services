package studsluzba.client.fxmlcontrollers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import studsluzba.client.MainViewManager;
import studsluzba.model.Ispit;
import studsluzba.model.OsvojeniPredispitniPoeni;
import studsluzba.model.StudIndex;
import studsluzba.services.IspitiService;

@Component
public class OpenPodaciOIspituController {

    @Autowired
    IspitiService ispitiService;

    @Autowired
    IspitController ispitController;

    @Autowired
    MainViewManager mainViewManager;

    Ispit ispit;

    @FXML
    TableView<OsvojeniPredispitniPoeni> studRezultatiTable = new TableView<>();

    @FXML
    TableView<StudIndex> prijavljeniStudTable = new TableView<>();

    private ObservableList<StudIndex> sviStudIndexi;

    private ObservableList<OsvojeniPredispitniPoeni> sviPredispitni;

    @FXML
    public void initialize() {
        String sifraIspita = ispit.getSifraIspita();
        sviStudIndexi = FXCollections.observableList(ispitiService.getPrijavljeniStud(sifraIspita));
        prijavljeniStudTable.getItems().clear();
        prijavljeniStudTable.setItems(sviStudIndexi);

        sviPredispitni = FXCollections.observableList(ispitiService.getRezIspit(sifraIspita));
        studRezultatiTable.getItems().clear();
        studRezultatiTable.setItems(sviPredispitni);

    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
