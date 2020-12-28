package studsluzba.client.fxmlcontrollers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import studsluzba.client.MainViewManager;
import studsluzba.coders.CoderFactory;
import studsluzba.coders.CoderType;
import studsluzba.coders.SimpleCode;
import studsluzba.model.SrednjaSkola;
import studsluzba.model.StudProgram;
import studsluzba.model.Student;
import studsluzba.services.SifarniciService;
import studsluzba.services.StudProgramService;
import studsluzba.services.StudentService;

import java.time.LocalDate;
import java.util.List;

@Component
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    SifarniciService sifarniciService;

    @Autowired
    StudProgramService studProgramService;

    @Autowired
    MainViewManager mainViewManager;

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
    ComboBox<SimpleCode> mestoStanovanjaCb;

    @FXML
    ComboBox<SimpleCode> mestoRodjenjaCb;

    @FXML
    ComboBox<SimpleCode> drzavaRodjenjaCb;

    @FXML
    ComboBox<SimpleCode> drzavljanstvoCb;

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

    @FXML
    TextField pretragaBrojaIndeksaTf;

    @FXML
    TextField godinaIndeksaTf;

    @FXML
    ComboBox pretragaSmerCb;


    @FXML
    ComboBox smerCb;

    @FXML
    TextField brojIndeksaTf;

    @FXML
    TextField brojUliceTf;

    @Autowired
    CoderFactory coderFactory;


    @FXML
    public void initialize() {

        drzavljanstvoCb.setItems(FXCollections.observableArrayList(coderFactory.getSimpleCoder(CoderType.DRZAVA).getCodes()));
        drzavaRodjenjaCb.setItems(FXCollections.observableArrayList(coderFactory.getSimpleCoder(CoderType.DRZAVA).getCodes()));
        mestoRodjenjaCb.setItems(FXCollections.observableArrayList(coderFactory.getSimpleCoder(CoderType.MESTO).getCodes()));
        mestoStanovanjaCb.setItems(FXCollections.observableArrayList(coderFactory.getSimpleCoder(CoderType.MESTO).getCodes()));

        List<SrednjaSkola> srednjeSkole = sifarniciService.getSrednjeSkole();
        srednjeSkolaCb.setItems(FXCollections.observableArrayList(srednjeSkole));

        smerCb.setItems(FXCollections.observableArrayList(coderFactory.getSimpleCoder(CoderType.SMER).getCodes()));

    }

    public void handleOpenModalSrednjeSkole(ActionEvent ae) {
        mainViewManager.openModal("addSrednjaSkola");
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
        String mestoRodjenja = mestoRodjenjaCb.getValue().toString();
        String drzavaRodjenja = drzavaRodjenjaCb.getValue().toString();
        String drzavljanstvo = drzavljanstvoCb.getValue().toString();
        String licna = brojLicneKarteTf.getText();
        String adresaStanovanja = adresaStanovanjaTf.getText();
        String mestoStanovanja = mestoStanovanjaCb.getValue().toString();
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
        char pol;
        boolean polMuski = radioButtonMuski.isSelected();
        String brojTelefona = brojTelefonaTf.getText();
        String brojUlice = brojUliceTf.getText();

        StudProgram studProgram = null;

        List<StudProgram> programi = studProgramService.loadAll();
        for (StudProgram sp : programi) {
            if (sp.getSkraceniNaziv().equals(smer)) {
                studProgram = sp;
            }
        }

        if (polMuski) {
            pol = 'm';
        } else {
            pol = 'z';
        }

        Student studet = studentService.save(ime, prezime, srednje, Long.parseLong(jmbg), rodjenje, mestoRodjenja, nacionalnost, drzavaRodjenja,
                pol,
                licnaIzdao, prelaz, drzavljanstvo, licna, adresaStanovanja, Integer.parseInt(brojUlice), Long.parseLong(brojTelefona), privEmail,
                mestoStanovanja, srednja, Double.parseDouble(uspehSrednja), datumUpisa, Double.parseDouble(uspehPrijemnni), smer, Integer.parseInt(indeks), studProgram);

    }
}
