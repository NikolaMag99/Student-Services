package studsluzba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studsluzba.model.Predmet;
import studsluzba.model.StudProgram;
import studsluzba.model.Student;
import studsluzba.repositories.StudProgramRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudProgramService {

    @Autowired
    StudProgramRepository studProgramRepo;

    public StudProgram saveStudProgram(String punNaziv, String skraceniNaziv) {
        StudProgram sp = new StudProgram();
        sp.setNaziv(punNaziv);
        sp.setSkraceniNaziv(skraceniNaziv);
        return studProgramRepo.save(sp);
    }

    public List<StudProgram> loadAll() {
        Iterable<StudProgram> iter = studProgramRepo.findAll();
        List<StudProgram> rez = new ArrayList<StudProgram>();
        iter.forEach(rez::add);
        return rez;
    }

    public List<Predmet> getPredmetiByProgram(String smer) {
        List<Predmet> predmetiToReturn = studProgramRepo.selectPredmetiByStudProg(smer);
        return predmetiToReturn;
    }

    public List<Student> getStudentiNaProgramu(StudProgram sp) {
        return studProgramRepo.getStudentiNaProgramu(sp);
    }

}
