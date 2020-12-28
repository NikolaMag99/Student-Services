package studsluzba.client.fxmlcontrollers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import studsluzba.client.MainViewManager;
import studsluzba.model.SlusaPredmet;
import studsluzba.services.IspitiService;
import studsluzba.services.NastavnikService;
import studsluzba.services.SifarniciService;
import studsluzba.services.SlusaPredmetService;

@Component
public class OpenViewSlusaPredmetController {

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

    private ObservableList<SlusaPredmet> sviSlusaPredmet;

    @FXML
    TableView<SlusaPredmet> slusaTable = new TableView<>();

    @FXML
    protected void initialize() {
        sviSlusaPredmet = FXCollections.observableList(slusaPredmetService.getSlusaPred());
        slusaTable.getItems().clear();
        slusaTable.setItems(sviSlusaPredmet);
    }

}
