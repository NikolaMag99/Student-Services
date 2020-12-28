package studsluzba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studsluzba.model.*;
import studsluzba.repositories.DrziPredmetRepository;
import studsluzba.repositories.SlusaPredmetRepository;
import studsluzba.repositories.StudIndexRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SlusaPredmetService {

    @Autowired
    SlusaPredmetRepository slusaPredmetRepo;

    @Autowired
    StudIndexRepository studIndexRepository;

    @Autowired
    DrziPredmetRepository drziPredmetRepository;


    public List<StudIndex> getStudIndexi() {
        Iterable<StudIndex> iter = studIndexRepository.findAll();
        List<StudIndex> rez = new ArrayList<StudIndex>();
        iter.forEach(rez::add);
        return rez;
    }

    public List<SlusaPredmet> getSlusaPredmetiByIndex(int broj){
        return slusaPredmetRepo.getSlusaPredmetiByIndex(broj);
    }

    public List<SlusaPredmet> getSlusaPredmeti(Student student) {
        Iterable<SlusaPredmet> iter = slusaPredmetRepo.findAll();
        List<SlusaPredmet> rez = new ArrayList<SlusaPredmet>();
        iter.forEach(rez::add);
        return rez;
    }

    public List<SlusaPredmet> getSlusaPred() {
        Iterable<SlusaPredmet> iter = slusaPredmetRepo.findAll();
        List<SlusaPredmet> rez = new ArrayList<SlusaPredmet>();
        iter.forEach(rez::add);
        return rez;
    }

    public void addSlusaPred(DrziPredmet drziPredmet, List<StudIndex> studIndexList) {
        SlusaPredmet slusaPredmet = new SlusaPredmet();
        slusaPredmet.setDrziPredmet(drziPredmet);

        for (StudIndex s : studIndexList) {
            if (s.isAktivan()) {
                slusaPredmet.setIndex(s);
            }
        }

        slusaPredmetRepo.save(slusaPredmet);

    }

    public void addDrziPredmet(SkolskaGodina skolskaGodina, Nastavnik nastavnik, Predmet predmet, String sifra) {
        DrziPredmet drziPredmet = new DrziPredmet();
        drziPredmet.setNastavnik(nastavnik);
        drziPredmet.setPredmet(predmet);
        drziPredmet.setSkolskaGod(skolskaGodina);
        drziPredmet.setSifra(sifra);

        drziPredmetRepository.save(drziPredmet);

    }

}
