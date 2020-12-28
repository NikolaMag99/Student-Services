package studsluzba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studsluzba.model.*;
import studsluzba.repositories.IzlazakNaIspitRepository;
import studsluzba.repositories.PolozioPredmetRepository;
import studsluzba.repositories.PrijavaIspitaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class IzlazakNaIspitService {

    @Autowired
    IzlazakNaIspitRepository izlazakNaIspitRepository;

    @Autowired
    PolozioPredmetRepository polozioPredmetRepository;

    @Autowired
    PrijavaIspitaRepository prijavaIspitaRepository;

    @Autowired
    StudentService studentService;

    public IzlazakNaIspit createIzlazak(Ispit ispit, PrijavaIspita prijavaIspita, PolozioPredmet polozioPredmet, double poeni){
        IzlazakNaIspit izlazakNaIspit = new IzlazakNaIspit();
        izlazakNaIspit.setPrijavljenIspit(prijavaIspita);
        izlazakNaIspit.setIspit(ispit);
        izlazakNaIspit.setPolozioPredmet(polozioPredmet);
        izlazakNaIspit.setBrojOsvojenihPoena(poeni);
        izlazakNaIspit.setIzasaoNaIspit(true);

        IzlazakNaIspit toReturn = izlazakNaIspitRepository.save(izlazakNaIspit);

        if(polozioPredmet != null) {
            polozioPredmet.setIzlazakNaIspit(izlazakNaIspit);
            polozioPredmetRepository.save(polozioPredmet);
        }
        prijavaIspita.setIzlazakNaIspit(izlazakNaIspit);
        prijavaIspitaRepository.save(prijavaIspita);

        return toReturn;
    }

    public List<Student> prijaveljeniStudenti(Predmet predmet, IspitniRok rok){
        List<PrijavaIspita> StudPrijave = new ArrayList<>();
        StudPrijave = prijavaIspitaRepository.prijave(predmet, rok);

        List<Student> studenti = new ArrayList<>();
        for(PrijavaIspita p : StudPrijave)
        {
            Student s = studentService.findStudentByIndex(p.getStudIndexi().getStudProgram().getSkraceniNaziv(),
                                                           p.getStudIndexi().getBroj(), p.getStudIndexi().getGodina());

            if(s != null) studenti.add(s);
            System.out.println(s.toString());
        }
        return studenti;
    }

    public List<PrijavaIspita> prijave(Predmet predmet, IspitniRok rok){
        List<PrijavaIspita> StudPrijave = new ArrayList<>();
        StudPrijave = prijavaIspitaRepository.prijave(predmet, rok);
        return StudPrijave;
    }
}
