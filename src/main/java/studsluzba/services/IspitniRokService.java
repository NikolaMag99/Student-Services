package studsluzba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studsluzba.model.Ispit;
import studsluzba.model.IspitniRok;
import studsluzba.model.SkolskaGodina;
import studsluzba.repositories.IspitRepository;
import studsluzba.repositories.IspitniRokRepository;

@Service
public class IspitniRokService {

    @Autowired
    IspitniRokRepository ispitniRokRepository;

    @Autowired
    IspitRepository ispitRepository;

    public IspitniRok createIspitniRok(SkolskaGodina skolskaGodina, String naziv, Ispit ispit) {

        IspitniRok ispitniRok = ispitniRokRepository.findByDatumPocetka(naziv);
        ispitniRok.setSkolskaGod(skolskaGodina);

        IspitniRok toReturn = ispitniRokRepository.save(ispitniRok);

        return toReturn;

    }

}
