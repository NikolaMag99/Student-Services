package studsluzba.client.fxmlcontrollers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import studsluzba.client.reports.ReportsManager;
import studsluzba.client.reports.reportbeans.TEST;
import studsluzba.model.*;
import studsluzba.services.IzlazakNaIspitService;
import studsluzba.services.SifarniciService;
import studsluzba.services.StudProgramService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SpiskoviStudenataIzvestajiController {

    @Autowired
    ReportsManager reportsManager;

    @FXML
    ComboBox<StudProgram> studProgramiCb;

    @FXML
    ComboBox<Predmet> predmetiCb;

    @FXML
    ComboBox<IspitniRok> ispitniRokoviCb;

    @Autowired
    StudProgramService studProgramiService;

    @Autowired
    SifarniciService sifarniciService;

    @Autowired
    IzlazakNaIspitService izlazakNaIspitService;

    @FXML
    public void initialize() {

        List<Predmet> predmetList = sifarniciService.getPredmeti();
        predmetiCb.setItems(FXCollections.observableArrayList(predmetList));

        List<IspitniRok> ispitniRokList = sifarniciService.getIspitniRokovi();
        ispitniRokoviCb.setItems(FXCollections.observableArrayList(ispitniRokList));

    }

    public void handleGenerisiSpisakPoStudProgramu(ActionEvent ecent) {
        List<TEST> sviIzlasci = new ArrayList<>();

        Map<String, Object> params = new HashMap<String, Object>();

        Predmet predmet = predmetiCb.getValue();
        IspitniRok rok = ispitniRokoviCb.getValue();

        List<PrijavaIspita> prijaveIspita = izlazakNaIspitService.prijave(predmet, rok);
        System.out.println(prijaveIspita.size());
        PrijavaIspita prijava = prijaveIspita.get(0);
        String vremeOdrzavanja = prijava.getIspit().getVremePocetka().toString();
        String datumOdrzavanja = prijava.getIspit().getDatumOdrzavanja().toString();
        String ispitniRok = rok.getDatumPocetka() + "-" + rok.getDatumZavrsetka();
        String skolskaGod = rok.getSkolskaGod().toString();

        String imePredmeta = predmet.getNazivPredmeta();
        String sifraPredmeta = predmet.getSifraPredmeta();
        String studProgram = predmet.getStudProgram().getNaziv();

        for (PrijavaIspita p : prijaveIspita) {
            TEST t = new TEST();
            t.setIme(p.getStudIndexi().getStudent().getIme());
            t.setPrezime(p.getStudIndexi().getStudent().getPrezime());
            t.setStudIndex(p.getStudIndexi().toString());
            PolozioPredmet pp = p.getIzlazakNaIspit().getPolozioPredmet();
            if (pp == null) {
                continue;
            }
            t.setBrojPoena(p.getIzlazakNaIspit().getPolozioPredmet().getUkupanBrojPoena());
            t.setOcena(p.getIzlazakNaIspit().getPolozioPredmet().getOcena());

            sviIzlasci.add(t);
        }

        int brojPrijavljenih = prijaveIspita.size();
        int brojPolaganja = 0;
        int nisuPolagali = 0;
        int polozili = 0;
        double prolaznost = 0;
        String Prolaznost;

        double procenatOsvojenih5 = 0;
        int count5 = 0;
        double procenatOsvojenih6 = 0;
        int count6 = 0;
        double procenatOsvojenih7 = 0;
        int count7 = 0;
        double procenatOsvojenih8 = 0;
        int count8 = 0;
        double procenatOsvojenih9 = 0;
        int count9 = 0;
        double procenatOsvojenih10 = 0;
        int count10 = 0;


        for (PrijavaIspita p : prijaveIspita) {
            if (p.getIzlazakNaIspit() != null) brojPolaganja++;
            else nisuPolagali++;
            PolozioPredmet pp2 = p.getIzlazakNaIspit().getPolozioPredmet();
            if (pp2 == null) {
                continue;
            }
            if (p.getIzlazakNaIspit().getPolozioPredmet().getUkupanBrojPoena() > 50)
                polozili++;

            if (p.getIzlazakNaIspit().getPolozioPredmet() != null) {
                if (p.getIzlazakNaIspit().getPolozioPredmet().getOcena() == 5) count5++;
                else if (p.getIzlazakNaIspit().getPolozioPredmet().getOcena() == 6) count6++;
                else if (p.getIzlazakNaIspit().getPolozioPredmet().getOcena() == 7) count7++;
                else if (p.getIzlazakNaIspit().getPolozioPredmet().getOcena() == 8) count8++;
                else if (p.getIzlazakNaIspit().getPolozioPredmet().getOcena() == 9) count9++;
                else if (p.getIzlazakNaIspit().getPolozioPredmet().getOcena() == 10) count10++;
            }
        }
        prolaznost = (polozili * 100) / brojPolaganja;
        Prolaznost = Double.toString(prolaznost) + '%';


        procenatOsvojenih5 = (count5 * 100) / brojPolaganja;
        procenatOsvojenih6 = (count6 * 100) / polozili;
        procenatOsvojenih7 = (count7 * 100) / polozili;
        procenatOsvojenih8 = (count8 * 100) / polozili;
        procenatOsvojenih9 = (count9 * 100) / polozili;
        procenatOsvojenih10 = (count10 * 100) / polozili;

        String procenat5 = Double.toString(procenatOsvojenih5) + '%';
        String procenat6 = Double.toString(procenatOsvojenih6) + '%';
        String procenat7 = Double.toString(procenatOsvojenih7) + '%';
        String procenat8 = Double.toString(procenatOsvojenih8) + '%';
        String procenat9 = Double.toString(procenatOsvojenih9) + '%';
        String procenat10 = Double.toString(procenatOsvojenih10) + '%';

        int sifraNastavnika = prijava.getIspit().getNastavnik().getIdNastavnik();
        Nastavnik nastavnik = prijava.getIspit().getNastavnik();
        String imeNastavnika = nastavnik.getIme() + " " + nastavnik.getSrednjeIme() + " " + nastavnik.getPrezime();
        params.put("sifraPredmeta", sifraPredmeta);
        params.put("imePredmeta", imePredmeta);
        params.put("imeNastavnika", imeNastavnika);
        params.put("sifraNastavnika", sifraNastavnika);
        params.put("datumOdrzavanja", datumOdrzavanja);
        params.put("ispitniRok", ispitniRok);
        params.put("vremeOdrzavanja", vremeOdrzavanja);
        params.put("skolskaGod", skolskaGod);

        params.put("brojPrijavljenih", brojPrijavljenih);
        params.put("brojPolaganja", brojPolaganja);
        params.put("nisuPolagali", nisuPolagali);
        params.put("polozili", polozili);
        params.put("prolaznost", Prolaznost);

        params.put("procenat5", procenat5);
        params.put("procenat6", procenat6);
        params.put("procenat7", procenat7);
        params.put("procenat8", procenat8);
        params.put("procenat9", procenat9);
        params.put("procenat10", procenat10);

        reportsManager.openReport(sviIzlasci, params, "spisakStudenataZaStudProgram");
    }

}
