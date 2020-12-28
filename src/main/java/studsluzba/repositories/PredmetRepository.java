package studsluzba.repositories;

import org.springframework.data.repository.CrudRepository;
import studsluzba.model.Predmet;

public interface PredmetRepository  extends CrudRepository<Predmet,Integer>{

    Predmet getPredmetByNazivPredmeta(String naziv);

}
