package studsluzba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studsluzba.model.OsvojeniPredispitniPoeni;
import studsluzba.model.PredispitneObaveze;
import studsluzba.model.StudIndex;
import studsluzba.repositories.OsvojeniPredispitniPoeniRepository;

import java.util.List;

@Service
public class OsvojeniPredispitniPoeniService {

    @Autowired
    OsvojeniPredispitniPoeniRepository osvojeniPredispitniPoeniRepository;

    public OsvojeniPredispitniPoeni createOsvojeniPredi(StudIndex si) {
        OsvojeniPredispitniPoeni osvojeniPredispitniPoeni = new OsvojeniPredispitniPoeni();
        osvojeniPredispitniPoeni.setStudentIndeks(si);
        osvojeniPredispitniPoeni.setUkupanBrojPoena(0);
        return osvojeniPredispitniPoeniRepository.save(osvojeniPredispitniPoeni);
    }

    public void updateUkupni(OsvojeniPredispitniPoeni osvojeniPredispitniPoeni, double ukupni) {
        osvojeniPredispitniPoeni.setUkupanBrojPoena(ukupni);
        osvojeniPredispitniPoeniRepository.save(osvojeniPredispitniPoeni);
    }

}

