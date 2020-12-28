package studsluzba.client.fxmlcontrollers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import studsluzba.client.MainViewManager;
import studsluzba.model.DrziPredmet;
import studsluzba.model.StudIndex;
import studsluzba.services.IspitiService;
import studsluzba.services.NastavnikService;
import studsluzba.services.SifarniciService;
import studsluzba.services.SlusaPredmetService;

import java.util.List;

@Component
public class NewSlusaPredmetController {

    @Autowired
    IspitiService ispitiService;

    @Autowired
    NastavnikService nastavnikService;

    @Autowired
    MainViewManager mainViewManager;

    @Autowired
    SifarniciService sifarniciService;

    @Autowired
    SlusaPredmetService slusaPredmetService;

    @FXML
    TableView<StudIndex> indexiTable = new TableView<>();

    @FXML
    ComboBox<DrziPredmet> drziPredmetCb = new ComboBox<>();


    @FXML
    protected void initialize() {
        List<StudIndex> studIndexList = slusaPredmetService.getStudIndexi();
        indexiTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        indexiTable.setItems(FXCollections.observableArrayList(studIndexList));

        List<DrziPredmet> drziLista = sifarniciService.getDrziPredmeti();
        drziPredmetCb.setItems(FXCollections.observableArrayList(drziLista));
    }

    public void openSlusaPredmet(ActionEvent ae) {

    }

    public void updateDrziPredmet() {
        List<DrziPredmet> drziPredmetList = sifarniciService.getDrziPredmeti();
        drziPredmetCb.setItems(FXCollections.observableArrayList(drziPredmetList));
    }

    public void resetValues() {
        drziPredmetCb.getSelectionModel().clearSelection();
    }

    public void handleOpenDrziPredmet(ActionEvent ae) {
        mainViewManager.openModal("addDrziPredmet");
    }

    public void handleOpenSlusaPredmet(ActionEvent ae) {
        DrziPredmet drziPredmet = drziPredmetCb.getValue();
        List<StudIndex> studIndexList = indexiTable.getSelectionModel().getSelectedItems();

        slusaPredmetService.addSlusaPred(drziPredmet, studIndexList);

        resetValues();

        mainViewManager.openModal("openViewSlusaPredmet");
    }

}
