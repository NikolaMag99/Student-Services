package studsluzba.client.fxmlcontrollers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import studsluzba.client.MainViewManager;
import studsluzba.model.*;
import studsluzba.services.DosijeService;
import studsluzba.services.SifarniciService;

import java.util.ArrayList;
import java.util.List;


@Component
public class OpenTokStudijaController {

    @Autowired
    DosijeService dosijeService;

    @Autowired
    FindStudentController findStudentController;

    @Autowired
    SifarniciService sifarniciService;

    @Autowired
    MainViewManager mainViewManager;

    Student student;

    @FXML
    Text obnovaGodina;

    @FXML
    TextArea napomena;

    @FXML
    Text upisGodina;

    @FXML
    TextField novaGodina;


    @FXML
    ComboBox<SkolskaGodina> skolskaGodinaCb = new ComboBox<>();

    @FXML
    RadioButton radioButtonUpis;

    @FXML
    RadioButton radioButtonObnova;

    private ObservableList<Predmet> sviPredmeti;

    @FXML
    private TableView<Predmet> programiTable;

    @FXML
    public void initialize() {
        List<SkolskaGodina> skolskeLista = FXCollections.observableList(sifarniciService.getSkolskeGodine());
        List<SkolskaGodina> aktivnaSkolska = new ArrayList<>();
        for (SkolskaGodina sk : skolskeLista) {
            if (sk.isAktivna()) {
                aktivnaSkolska.add(sk);
            }
        }
        skolskaGodinaCb.setItems(FXCollections.observableArrayList(aktivnaSkolska));
        List<UpisGodina> upisGodinaList = new ArrayList<>();
        List<ObnovaGodina> obnovaGodinaList = new ArrayList<>();
        List<StudIndex> studIndexList = student.getIndexi();
        StudIndex aktivniIndex = new StudIndex();
        for (StudIndex i : studIndexList) {
            if (i.isAktivan()) {
                aktivniIndex = i;
            }
            upisGodinaList.addAll(dosijeService.getUpisGodina(i.getStudProgram().getSkraceniNaziv(), i.getBroj(), i.getGodina()));
            obnovaGodinaList.addAll(dosijeService.getObnovaGodina(i.getStudProgram().getSkraceniNaziv(), i.getBroj(), i.getGodina()));
        }
        upisGodina.setText(upisGodinaList.toString());
        obnovaGodina.setText(obnovaGodinaList.toString());

        sviPredmeti = FXCollections.observableList(dosijeService.getPredmetiZaStudProgram(aktivniIndex.getStudProgram().getSkraceniNaziv()));
        programiTable.setItems(sviPredmeti);
        programiTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    public void saveUpisObnova(ActionEvent ae) {
        SkolskaGodina upisSkolskaGodina = skolskaGodinaCb.getValue();
        List<Predmet> predmetiSelected = programiTable.getSelectionModel().getSelectedItems();
        boolean upisObnovaRaddioB = radioButtonUpis.isSelected();
        List<StudIndex> studIndexList = student.getIndexi();
        StudIndex aktivniIndex = new StudIndex();
        String napomenaText = napomena.getText();

        for (StudIndex i : studIndexList) {
            if (i.isAktivan()) {
                aktivniIndex = i;
            }
        }

        if (upisObnovaRaddioB) {
            dosijeService.savaUpis(predmetiSelected, upisSkolskaGodina, aktivniIndex, napomenaText);
        } else {
            dosijeService.saveObnova(predmetiSelected, upisSkolskaGodina, aktivniIndex, napomenaText);
        }
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
