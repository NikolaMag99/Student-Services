package studsluzba.client.fxmlcontrollers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import studsluzba.client.MainViewManager;
import studsluzba.coders.CoderFactory;
import studsluzba.coders.CoderType;
import studsluzba.model.SrednjaSkola;
import studsluzba.model.StudIndex;
import studsluzba.model.StudProgram;
import studsluzba.model.Student;
import studsluzba.services.SifarniciService;
import studsluzba.services.StudProgramService;
import studsluzba.services.StudentService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class FindStudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    SifarniciService sifarniciService;

    @Autowired
    StudProgramService studProgramService;

    @Autowired
    MainViewManager mainViewManager;

    @Autowired
    OpenDosijeController openDosijeController;

    private Student student;

    @FXML
    private TextField imeTf;

    @FXML
    private TextField prezimeTf;

    @FXML
    private TextField srednjeImeTf;

    @FXML
    private TextField jmbgTf;

    @FXML
    private DatePicker datumRodjenjaDp;

    @FXML
    private Text actionTarget;

    @FXML
    private TextField emailTf;

    @FXML
    private RadioButton radioButtonMuski;

    @FXML
    private RadioButton radioButtonZenski;

    @FXML
    TextField brojTelefonaTf;

    @FXML
    TextField adresaPrebivalistaTf;

    @FXML
    ComboBox<String> mestoPrebivalistaCb;

    @FXML
    TextField adresaStanovanjaTf;

    @FXML
    ComboBox<String> mestoStanovanjaCb;

    @FXML
    ComboBox<String> mestoRodjenjaCb;

    @FXML
    ComboBox<String> drzavaRodjenjaCb;

    @FXML
    ComboBox<String> drzavljanstvoCb;

    @FXML
    TextField nacionalnostTf;

    @FXML
    TextField brojLicneKarteTf;

    @FXML
    TextField licnuKartuIzdaoTf;

    @FXML
    ComboBox<SrednjaSkola> srednjeSkolaCb;

    @FXML
    TextField strucnaSpremaTf;

    @FXML
    TextField uspehSrednjaSkolaTf;

    @FXML
    TextField uspehPrijemniTf;

    @FXML
    TextField godinaZavrsetkaSrednjeSkoleTf;

    @FXML
    TextField prelazSaVisokoskolskeUstanoveTf;

    @FXML
    TextField prethodnoZavrseneStudijeTf;

    @FXML
    TextField visokoskolskaUstanovaPrethodnihStudijaTf;

    @FXML
    TextField stecenoZvanjeTf;

    @FXML
    TextField prosecnaOcenaTf;

    @FXML
    DatePicker datumUpisaDp;

    //
    @FXML
    TextField pretragaBrojaIndeksaTf;

    @FXML
    TextField pretragaGodinaIndeksaTf;

    @FXML
    ComboBox pretragaSmerCb;

    @FXML
    TextField godinaIndeksaTf;

    @FXML
    ComboBox smerCb;

    @FXML
    TextField brojIndeksaTf;

    @FXML
    TextField NovaGodinaIndeksaTf;

    @FXML
    ComboBox NovSmerCb;

    @FXML
    TextField NovBrojIndeksaTf;

    @FXML
    TextField brojUliceTf;

    @FXML
    private TableView<Student> studentiTable;

    @FXML
    Label validacija;

    @Autowired
    CoderFactory coderFactory;


    @FXML
    public void initialize() {
        pretragaSmerCb.setItems(FXCollections.observableArrayList(coderFactory.getSimpleCoder(CoderType.SMER).getCodes()));
        NovSmerCb.setItems(FXCollections.observableArrayList(coderFactory.getSimpleCoder(CoderType.SMER).getCodes()));
    }

    public void changeIndex(ActionEvent event) {
        StudIndex studIndexSelected = new StudIndex();
        List<StudIndex> studIndexiSelected = studentiTable.getSelectionModel().getSelectedItem().getIndexi();
        for (StudIndex si : studIndexiSelected) {
            if (si.isAktivan()) {
                studIndexSelected = si;
            }
        }
        Integer novaGodina = Integer.parseInt(NovaGodinaIndeksaTf.getText());
        String novSmer = NovSmerCb.getValue().toString();
        Integer novBroj = Integer.parseInt(NovBrojIndeksaTf.getText());
        Student studentClicked = studentiTable.getSelectionModel().getSelectedItem();
        Optional<Student> studentChangedIndex = studentService.changeIndex(studentClicked, novaGodina, novSmer, novBroj, studIndexSelected);

        updateStudentIndex(novBroj, novSmer, novaGodina);
    }

    public void updateStudentIndex(Integer broj, String smer, Integer godina) {
        Student student = studentService.findStudentByIndex(smer, broj, godina);
        studentiTable.getItems().clear();
        studentiTable.setItems(FXCollections.observableArrayList(student));
    }

    public void findStudentByName(ActionEvent event) {
        String ime = imeTf.getText();
        String prezime = prezimeTf.getText();
        if (ime == null && prezime == null) return;
        List<Student> studenti = studentService.findStudent(ime, prezime);
        studentiTable.setItems(FXCollections.observableList(studenti));
    }

    public void findStudentByIndex(ActionEvent event) {
        studentiTable.getItems().clear();
        if (pretragaBrojaIndeksaTf.getText().isEmpty() || pretragaSmerCb.getValue() == null || pretragaGodinaIndeksaTf.getText().isEmpty()) {
            validacija.setText("Popunite sva polja");
            validacija.setStyle("-fx-background-color: #ff0000;");
            return;
        }
        String Broj = pretragaBrojaIndeksaTf.getText();
        String Godina = pretragaGodinaIndeksaTf.getText();
        if (!(Broj.matches("\\d*") && Godina.matches("\\d*"))) {
            validacija.setText("Unesite ispravan format podataka");
            validacija.setStyle("-fx-background-color: #ff0000;");
            return;
        }

        Integer broj = Integer.parseInt(pretragaBrojaIndeksaTf.getText());
        String smer = pretragaSmerCb.getValue().toString();
        Integer godina = Integer.parseInt(pretragaGodinaIndeksaTf.getText());
        if (studentService.findStudentByIndex(smer, broj, godina) == null) {
            validacija.setText("Student ne postoji");
            validacija.setStyle("-fx-background-color: #ff0000;");
            return;
        }
        Student s = studentService.findStudentByIndex(smer, broj, godina);
        validacija.setText("");
        validacija.setStyle("-fx-background-color: white;");
        studentiTable.getItems().add(s);
    }


    public void handleOpenDosije(ActionEvent ae) {
        openDosijeController.student = studentiTable.getSelectionModel().getSelectedItem();
        mainViewManager.openModal("StudentTabPane");
    }

    public void updateSrednjeSkole() {
        List<SrednjaSkola> srednjeSkole = sifarniciService.getSrednjeSkole();
        srednjeSkolaCb.setItems(FXCollections.observableArrayList(srednjeSkole));
    }

    public void saveStudent(ActionEvent ae) {
        String ime = imeTf.getText();
        String prezime = prezimeTf.getText();
        String srednje = srednjeImeTf.getText();
        String jmbg = jmbgTf.getText();
        LocalDate rodjenje = datumRodjenjaDp.getValue();
        String mestoRodjenja = mestoRodjenjaCb.getValue();
        String drzavaRodjenja = drzavaRodjenjaCb.getValue();
        String drzavljanstvo = drzavljanstvoCb.getValue();
        String licna = brojLicneKarteTf.getText();
        String adresaStanovanja = adresaStanovanjaTf.getText();
        String mestoStanovanja = mestoStanovanjaCb.getValue();
        SrednjaSkola srednja = srednjeSkolaCb.getValue();
        String uspehSrednja = uspehSrednjaSkolaTf.getText();
        String uspehPrijemnni = uspehPrijemniTf.getText();
        LocalDate datumUpisa = datumUpisaDp.getValue();
        String smer = String.valueOf(smerCb.getValue().toString());
        String indeks = brojIndeksaTf.getText();
        String prelaz = prelazSaVisokoskolskeUstanoveTf.getText();
        String nacionalnost = nacionalnostTf.getText();
        String licnaIzdao = licnuKartuIzdaoTf.getText();
        String privEmail = emailTf.getText();
        String pol = radioButtonMuski.getText();
        String brojTelefona = brojTelefonaTf.getText();
        String brojUlice = brojUliceTf.getText();


        StudProgram studProgram = null;

        List<StudProgram> programi = studProgramService.loadAll();
        for (StudProgram sp : programi) {
            if (sp.getSkraceniNaziv().equals(smer)) {
                studProgram = sp;
            }
        }


        Student studet = studentService.save(ime, prezime, srednje, Long.parseLong(jmbg), rodjenje, mestoRodjenja, nacionalnost, drzavaRodjenja,
                pol.charAt(0),
                licnaIzdao, prelaz, drzavljanstvo, licna, adresaStanovanja, Integer.parseInt(brojUlice), Long.parseLong(brojTelefona), privEmail,
                mestoStanovanja, srednja, Double.parseDouble(uspehSrednja), datumUpisa, Double.parseDouble(uspehPrijemnni), smer, Integer.parseInt(indeks), studProgram);

        System.out.println(studet.toString());

    }
}
