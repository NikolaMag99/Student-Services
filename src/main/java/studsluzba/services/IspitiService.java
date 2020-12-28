package studsluzba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studsluzba.model.*;
import studsluzba.repositories.IspitRepository;
import studsluzba.repositories.IspitniRokRepository;
import studsluzba.repositories.NastavnikRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IspitiService {

    @Autowired
    IspitRepository ispitRepository;

    @Autowired
    IspitniRokRepository ispitniRokRepository;

    @Autowired
    NastavnikRepository nastavnikRepository;

    public List<Ispit> getIspiti() {
        Iterable<Ispit> iter = ispitRepository.findAll();
        List<Ispit> rez = new ArrayList<Ispit>();
        iter.forEach(rez::add);
        return rez;
    }

    public List<StudIndex> getPrijavljeniStud(String sifraIspita) {
        return ispitRepository.findStudentsRegisteredForExam(sifraIspita);
    }

    public List<OsvojeniPredispitniPoeni> getRezIspit(String sifraIspita) {
        return ispitRepository.sortStudByExamResults(sifraIspita);
    }

    public void saveIspitniRok(LocalDate pocetak, LocalDate zavrsetak, SkolskaGodina skolskaGodina) {
        IspitniRok ispitniRok = new IspitniRok();
        ispitniRok.setDatumPocetka(pocetak.toString());
        ispitniRok.setDatumZavrsetka(zavrsetak.toString());
        ispitniRok.setSkolskaGod(skolskaGodina);

        ispitniRokRepository.save(ispitniRok);
    }

    public void saveIspit(String sifra, LocalDate datum, String vreme, IspitniRok ispitniRok, Predmet predmet, Nastavnik nastavnik) {
        Ispit ispit = new Ispit();
        ispit.setVremePocetka(vreme);
        ispit.setSifraIspita(sifra);
        ispit.setDatumOdrzavanja(datum);
        ispit.setPredmet(predmet);
        ispit.setNastavnik(nastavnik);
        ispit.setUnetiPoeni(false);

        ispitRepository.save(ispit);

        ispitniRok.setIspit(ispit);
        ispitniRokRepository.save(ispitniRok);
    }

    public Ispit createIspit(Predmet predmet, IspitniRok isj, IspitniRok isjul, IspitniRok isa, IspitniRok iss) {

        isj.setDatumPocetka("jun");
        isj.setDatumZavrsetka("jul");

        isjul.setDatumPocetka("jul");
        isjul.setDatumZavrsetka("avgust");

        isa.setDatumPocetka("avgust");
        isa.setDatumZavrsetka("septembar");

        iss.setDatumPocetka("septembar");
        iss.setDatumZavrsetka("oktobar");

        Ispit ispit = new Ispit();
        ispit.setPredmet(predmet);
        ispit.setSifraIspita("89842");
        ispit.setDatumOdrzavanja(LocalDate.now());
        ispit.setVremePocetka("13:00");
        Nastavnik nastavnik = new Nastavnik();
        nastavnik.setIme("Pera");
        nastavnik.setPrezime("Mikic");
        nastavnik.setSrednjeIme("Mika");

        nastavnikRepository.save(nastavnik);

        ispit.setNastavnik(nastavnik);

        Ispit toReturn = ispitRepository.save(ispit);

        isj.setIspit(ispit);
        isjul.setIspit(ispit);
        isa.setIspit(ispit);
        iss.setIspit(ispit);

        ispitniRokRepository.save(isj);
        ispitniRokRepository.save(isjul);
        ispitniRokRepository.save(isa);
        ispitniRokRepository.save(iss);

        return toReturn;
    }
}
