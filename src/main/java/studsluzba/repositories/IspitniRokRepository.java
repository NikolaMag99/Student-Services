package studsluzba.repositories;

import org.springframework.data.repository.CrudRepository;
import studsluzba.model.Ispit;
import studsluzba.model.IspitniRok;

public interface IspitniRokRepository extends CrudRepository<IspitniRok, Integer> {

    IspitniRok findByDatumPocetka(String naziv);
}
