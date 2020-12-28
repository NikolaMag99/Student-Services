package studsluzba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studsluzba.model.DrziPredmet;
import studsluzba.model.Nastavnik;
import studsluzba.model.SkolskaGodina;
import studsluzba.model.Zvanje;
import studsluzba.repositories.NastavnikRepository;
import studsluzba.repositories.ZvanjeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class NastavnikService {

    @Autowired
    NastavnikRepository nastavnikRepository;

    @Autowired
    ZvanjeRepository zvanjeRepository;


    public List<Nastavnik> getNastavnici() {
        Iterable<Nastavnik> iter = nastavnikRepository.findAll();
        List<Nastavnik> rez = new ArrayList<Nastavnik>();
        iter.forEach(rez::add);
        return rez;
    }

    public List<DrziPredmet> findNastavnikBySk(SkolskaGodina skolskaGodina) {
        return nastavnikRepository.findNastavnikBySk(skolskaGodina);
    }

    public void addZvanje(String nastavnikNazivZvanja, String nastavnikUzaNaucObla, LocalDate nastavnikDatumIzbora, Nastavnik nastavnik) {
        Zvanje zvanje = new Zvanje();
        zvanje.setUzaNaucnaOblast(nastavnikUzaNaucObla);
        zvanje.setDatumIzbora(nastavnikDatumIzbora);
        zvanje.setNazivZvanja(nastavnikNazivZvanja);

        zvanje.setNastavnik(nastavnik);

        zvanjeRepository.save(zvanje);

    }

}
