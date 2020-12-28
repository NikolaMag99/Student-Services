package studsluzba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studsluzba.model.*;
import studsluzba.repositories.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class SifarniciService {

    @Autowired
    SrednjaSkolaRepository srednjaSkolaRepository;


    @Autowired
    NastavnikRepository nastavnikRepository;

    @Autowired
    PredmetRepository predmetRepository;

    @Autowired
    IspitniRokRepository ispitniRokRepository;

    @Autowired
    SkolskaGodinaRepository skolskaGodinaRepository;

    @Autowired
    DrziPredmetRepository drziPredmetRepository;

    public List<SrednjaSkola> getSrednjeSkole() {
        Iterable<SrednjaSkola> iter = srednjaSkolaRepository.findAll();
        List<SrednjaSkola> rez = new ArrayList<SrednjaSkola>();
        iter.forEach(rez::add);
        return rez;
    }

    public List<SkolskaGodina> getSkolskeGodine() {
        Iterable<SkolskaGodina> iter = skolskaGodinaRepository.findAll();
        List<SkolskaGodina> rez = new ArrayList<SkolskaGodina>();
        iter.forEach(rez::add);
        return rez;
    }

    public SkolskaGodina saveSkolska(SkolskaGodina s){
        return skolskaGodinaRepository.save(s);
    }

    public List<IspitniRok> getIspitniRokovi() {
        Iterable<IspitniRok> iter = ispitniRokRepository.findAll();
        List<IspitniRok> rez = new ArrayList<IspitniRok>();
        iter.forEach(rez::add);
        return rez;
    }

    public List<DrziPredmet> getDrziPredmeti() {
        Iterable<DrziPredmet> iter = drziPredmetRepository.findAll();
        List<DrziPredmet> rez = new ArrayList<DrziPredmet>();
        iter.forEach(rez::add);
        return rez;
    }


    public List<Predmet> getPredmeti() {
        Iterable<Predmet> iter = predmetRepository.findAll();
        List<Predmet> rez = new ArrayList<Predmet>();
        iter.forEach(rez::add);
        return rez;
    }


    public List<Nastavnik> getNastavnici() {
        Iterable<Nastavnik> iter = nastavnikRepository.findAll();
        List<Nastavnik> rez = new ArrayList<Nastavnik>();
        iter.forEach(rez::add);
        return rez;
    }

    public SrednjaSkola saveSrednjaSkola(SrednjaSkola ss) {
        return srednjaSkolaRepository.save(ss);
    }
}
