package studsluzba.client.fxmlcontrollers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import studsluzba.client.MainViewManager;
import studsluzba.model.IspitniRok;
import studsluzba.model.Nastavnik;
import studsluzba.model.Predmet;
import studsluzba.services.IspitiService;
import studsluzba.services.NastavnikService;
import studsluzba.services.SifarniciService;

import java.time.LocalDate;
import java.util.List;

@Component
public class NewIspitController {

    @Autowired
    IspitiService ispitiService;

    @Autowired
    NastavnikService nastavnikService;

    @Autowired
    MainViewManager mainViewManager;

    @Autowired
    SifarniciService sifarniciService;

    @FXML
    DatePicker datumOdrzavanjaTf;

    @FXML
    TextField sifraIspitaTf;

    @FXML
    TextField vremePocetkaTf;

    @FXML
    ComboBox<IspitniRok> ispitniRokCb;

    @FXML
    ComboBox<Predmet> predmetCb;

    @FXML
    ComboBox<Nastavnik> nastavnikCb = new ComboBox<>();


    @FXML
    protected void initialize() {
        List<IspitniRok> ispitniRokList = sifarniciService.getIspitniRokovi();
        ispitniRokCb.setItems(FXCollections.observableArrayList(ispitniRokList));

        List<Predmet> predmetList = sifarniciService.getPredmeti();
        predmetCb.setItems(FXCollections.observableArrayList(predmetList));

        List<Nastavnik> nastavnikList = sifarniciService.getNastavnici();
        nastavnikCb.setItems(FXCollections.observableArrayList(nastavnikList));
    }

    public void addIspit(ActionEvent ae) {
        String sifraIspita = sifraIspitaTf.getText();
        LocalDate datumOdrzavanja = datumOdrzavanjaTf.getValue();
        String vremePocetka = vremePocetkaTf.getText();
        IspitniRok ispitniRok = ispitniRokCb.getValue();
        Predmet predmet = predmetCb.getValue();
        Nastavnik nastavnik = nastavnikCb.getValue();

        ispitiService.saveIspit(sifraIspita, datumOdrzavanja, vremePocetka, ispitniRok, predmet, nastavnik);

        resetValues();

    }

    public void resetValues() {
        sifraIspitaTf.clear();
        vremePocetkaTf.clear();
        datumOdrzavanjaTf.getEditor().clear();
        ispitniRokCb.getSelectionModel().clearSelection();
        predmetCb.getSelectionModel().clearSelection();
        nastavnikCb.getSelectionModel().clearSelection();
    }

    public void updateIspitniRokovi() {
        List<IspitniRok> ispitniRokList = sifarniciService.getIspitniRokovi();
        ispitniRokCb.setItems(FXCollections.observableArrayList(ispitniRokList));
    }

    public void handleOpenPodaci(ActionEvent ae) {
        mainViewManager.openModal("addIspitniRok");
    }

}
