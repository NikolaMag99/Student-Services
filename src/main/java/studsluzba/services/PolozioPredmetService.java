package studsluzba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studsluzba.model.PolozioPredmet;
import studsluzba.model.Predmet;
import studsluzba.model.StudIndex;
import studsluzba.repositories.PolozioPredmetRepository;
import studsluzba.repositories.PredmetRepository;

import java.util.List;

@Service
public class PolozioPredmetService {

    @Autowired
    PolozioPredmetRepository polozioPredmetRepository;

    @Autowired
    PredmetRepository predmetRepository;

    public void addPolozioPredmet(StudIndex aktivanIndex, Predmet predmet) {
        PolozioPredmet polozioPredmet = new PolozioPredmet();
        polozioPredmet.setStudentIndex(aktivanIndex);
        polozioPredmet.setUkupanBrojPoena(0);
        polozioPredmet.setPriznatSaDrugogFaksa(false);
        polozioPredmet.setIzlazakNaIspit(null);
        polozioPredmet.setOcena(0);
        polozioPredmetRepository.save(polozioPredmet);

        predmet.setPolozioPredmet(polozioPredmet);
        predmetRepository.save(predmet);

    }

    public PolozioPredmet createPolozioPred(StudIndex aktivanIndex, Predmet predmet, double poeni, boolean polozio) {
        if (polozio) {
            PolozioPredmet polozioPredmet = new PolozioPredmet();
            polozioPredmet.setStudentIndex(aktivanIndex);
            polozioPredmet.setUkupanBrojPoena((float) poeni);
            polozioPredmet.setPriznatSaDrugogFaksa(false);

            if (poeni < 51) {
                polozioPredmet.setOcena(5);
            } else if (poeni > 50 && poeni < 61) {
                polozioPredmet.setOcena(6);
            } else if (poeni > 60 && poeni < 71) {
                polozioPredmet.setOcena(7);
            } else if (poeni > 70 && poeni < 81) {
                polozioPredmet.setOcena(8);
            } else if (poeni > 80 && poeni < 91) {
                polozioPredmet.setOcena(9);
            } else {
                polozioPredmet.setOcena(10);
            }

            PolozioPredmet toReturn = polozioPredmetRepository.save(polozioPredmet);

            predmet.setPolozioPredmet(polozioPredmet);
            predmetRepository.save(predmet);

            return toReturn;
        } else
            return null;
    }

    public List<PolozioPredmet> getPolozeniPredmetiByIndex(int broj) {
        return polozioPredmetRepository.getPolozeniPredmetiByIndex(broj);
    }
}
