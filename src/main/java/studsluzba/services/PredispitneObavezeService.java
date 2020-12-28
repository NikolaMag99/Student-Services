package studsluzba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studsluzba.model.OsvojeniPredispitniPoeni;
import studsluzba.model.PredispitneObaveze;
import studsluzba.model.Predmet;
import studsluzba.model.SkolskaGodina;
import studsluzba.repositories.PredispitneObavezeRepository;

import javax.persistence.Access;

@Service
public class PredispitneObavezeService {

    @Autowired
    PredispitneObavezeRepository predispitneObavezeRepository;


    public PredispitneObaveze createPredispitne(double poeni, Predmet predmet, String naziv, SkolskaGodina skolskaGodina, OsvojeniPredispitniPoeni osvojeniPredispitniPoeni){
        int maxPredispitneObavezePoeni = 0;
        PredispitneObaveze  predispitneObaveze = new PredispitneObaveze();
        predispitneObaveze.setPoeni(poeni);
        predispitneObaveze.setPredmet(predmet);
        predispitneObaveze.setVrstaObabeza(naziv);
        predispitneObaveze.setOsvojeniPredispitniPoeni(osvojeniPredispitniPoeni);
        switch (naziv){
            case "kolokvijum":
                maxPredispitneObavezePoeni = 20;
                break;
            case "test":
                maxPredispitneObavezePoeni = 20;
                break;
            case "kvizovi":
                maxPredispitneObavezePoeni = 10;
                break;
        }

        predispitneObaveze.setMaxBrp(maxPredispitneObavezePoeni);
        predispitneObaveze.setSkolskaGodina(skolskaGodina);

        return predispitneObavezeRepository.save(predispitneObaveze);
    }



}
