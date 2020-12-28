package studsluzba.client.fxmlcontrollers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import studsluzba.client.MainViewManager;
import studsluzba.model.*;
import studsluzba.services.DosijeService;
import studsluzba.services.PolozioPredmetService;
import studsluzba.services.SifarniciService;
import studsluzba.services.SlusaPredmetService;

import java.util.ArrayList;
import java.util.List;

@Component
public class OpenDosijeController {

    @Autowired
    DosijeService dosijeService;

    @Autowired
    FindStudentController findStudentController;

    @Autowired
    OpenTokStudijaController openTokStudijaController;

    @Autowired
    MainViewManager mainViewManager;

    @Autowired
    SlusaPredmetService slusaPredmetService;

    @Autowired
    SifarniciService sifarniciService;

    @Autowired
    PolozioPredmetService polozioPredmetService;

    Student student;

    @FXML
    Text imeStudenta;

    @FXML
    Text prezimeStudenta;

    @FXML
    Text indeksStudenta;

    @FXML
    Text srednjeIme;

    @FXML
    Text jmbg;

    @FXML
    Text datumRodjenjaDp;

    @FXML
    Text emailTf;

    @FXML
    Text studemailTf;

    @FXML
    Text pol;

    @FXML
    Text brojTelefonaTf;

    @FXML
    Text ulica;

    @FXML
    Text mestoStanovanja;

    @FXML
    Text brojUlice;

    @FXML
    Text mestoRodjenja;

    @FXML
    Text drzavljanstvo;

    @FXML
    Text nacionalnost;

    @FXML
    Text brojLicneKarteTf;

    @FXML
    Text licnuKartuIzdaoTf;

    @FXML
    Text srednjeSkola;

    @FXML
    Text uspehSrednjaSkolaTf;

    @FXML
    Text uspehPrijemniTf;

    @FXML
    Text polozeniPredmeti;

    @FXML
    Text slusaniPredmeti;

    @FXML
    Text obnovaGodina;

    @FXML
    Text upisGodina;

    @FXML
    TextField novaGodina;

    @FXML
    RadioButton radioButtonUpis;

    @FXML
    RadioButton radioButtonObnova;

    @FXML
    TableView programiTable;

    @FXML
    TextField novaUlica;

    @FXML
    TextField novoMestoStanovanja;

    @FXML
    TextField noviBrojUlice;

    @FXML
    TableView SlusaPredemetTable = new TableView();

    @FXML
    TableView polozeniPredmetiTable = new TableView();

    @FXML
    ComboBox<DrziPredmet> drziPredmetCb = new ComboBox<>();

    @FXML
    ComboBox<Predmet> predmetiCb = new ComboBox<>();

    public void handleOpenTokStudija(ActionEvent ae) {
        openTokStudijaController.student = student;
        mainViewManager.openModal("openTokStudija");
    }

    public void preseljenje(ActionEvent ae) {
        student.setUlica(novaUlica.getText());
        student.setMesto(novoMestoStanovanja.getText());
        student.setBrojUlice(Integer.parseInt(noviBrojUlice.getText()));
        dosijeService.saveStudent(student);
        updateStundetData(student);
    }

    public void handleOpenDrziPredmet(ActionEvent ae) {
        mainViewManager.openModal("addDrziPredmet");
    }

    public void updateStundetData(Student student) {
        imeStudenta.setText(student.getIme());
        prezimeStudenta.setText(student.getPrezime());
        indeksStudenta.setText(student.getIndexi().toString());
        srednjeIme.setText(student.getSrednjeIme());
        jmbg.setText(Long.toString(student.getJmbg()));
        datumRodjenjaDp.setText(student.getDaumRodjenja().toString());
        emailTf.setText(student.getPrivemail());
        studemailTf.setText(student.getStudemail());
        pol.setText(String.valueOf(student.getPol()));
        brojTelefonaTf.setText(Long.toString(student.getBrojTelefona()));

        ulica.setText(student.getUlica());
        mestoStanovanja.setText(student.getMesto());
        brojUlice.setText(Integer.toString(student.getBrojUlice()));

        mestoRodjenja.setText(student.getMestoRodjenja());
        drzavljanstvo.setText(student.getDrzavljanstvo());
        nacionalnost.setText(student.getNacionalnost());
        brojLicneKarteTf.setText(student.getBrojLicneKarte());
        licnuKartuIzdaoTf.setText(student.getLicnuKartuIzdao());
        srednjeSkola.setText(student.getSrednjaSkola().toString());
        uspehSrednjaSkolaTf.setText(Double.toString(student.getUspehSrednjaSkola()));
        uspehPrijemniTf.setText(Double.toString(student.getUspehPrijemni()));
        List<PolozioPredmet> polozeniLista = new ArrayList<>();
        List<StudIndex> indeksi = new ArrayList<>();
        List<SlusaPredmet> slusaLista = new ArrayList<>();
        indeksi = student.getIndexi();
        for (StudIndex i : indeksi) {
            polozeniLista.addAll(dosijeService.getPolozeni(i.getStudProgram().getSkraceniNaziv(), i.getBroj(), i.getGodina()));
        }
        polozeniPredmeti.setText(polozeniLista.toString());
        for (StudIndex i : indeksi) {
            slusaLista.addAll(dosijeService.getSlusa(i.getStudProgram().getSkraceniNaziv(), i.getBroj(), i.getGodina()));
        }
        slusaniPredmeti.setText(slusaLista.toString());

    }

    public void handleDodajPolozeniPredmet(ActionEvent ae) {
        StudIndex aktivniIndex = new StudIndex();
        List<StudIndex> indexi = student.getIndexi();
        for (StudIndex index : indexi) {
            if (index.isAktivan()) {
                aktivniIndex = index;
            }
        }
        Predmet predmet = predmetiCb.getValue();
        polozioPredmetService.addPolozioPredmet(aktivniIndex, predmet);
        updatePolozeniPredTable(aktivniIndex);
    }


    @FXML
    public void initialize() {
        updateStundetData(student);
        StudIndex aktivniIndex = new StudIndex();
        List<StudIndex> indexi = student.getIndexi();
        for (StudIndex index : indexi) {
            if (index.isAktivan()) {
                aktivniIndex = index;
            }
        }
        List<Predmet> predmetList = sifarniciService.getPredmeti();
        predmetiCb.setItems(FXCollections.observableArrayList(predmetList));
        List<DrziPredmet> drziPredmetList = sifarniciService.getDrziPredmeti();
        drziPredmetCb.setItems(FXCollections.observableArrayList(drziPredmetList));
        updatePolozeniPredTable(aktivniIndex);
        updateSlusaPredTable(aktivniIndex);
    }

    public void updatePolozeniPredTable(StudIndex aktivniIndex) {
        List<PolozioPredmet> polozioPredmets = polozioPredmetService.getPolozeniPredmetiByIndex(aktivniIndex.getBroj());
        polozeniPredmetiTable.setItems(FXCollections.observableArrayList(polozioPredmets));

    }

    public void updateSlusaPredTable(StudIndex aktivniIndex) {
        List<SlusaPredmet> slusaPredmets = slusaPredmetService.getSlusaPredmetiByIndex(aktivniIndex.getBroj());
        SlusaPredemetTable.setItems(FXCollections.observableArrayList(slusaPredmets));

    }

    public void handleAddSlusaPredmet(ActionEvent ae) {
        StudIndex aktivniIndex = new StudIndex();
        List<StudIndex> indexi = student.getIndexi();
        for (StudIndex index : indexi) {
            if (index.isAktivan()) {
                aktivniIndex = index;
            }
        }
        DrziPredmet drziPredmet = drziPredmetCb.getValue();
        slusaPredmetService.addSlusaPred(drziPredmet, student.getIndexi());
        updateSlusaPredTable(aktivniIndex);
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
