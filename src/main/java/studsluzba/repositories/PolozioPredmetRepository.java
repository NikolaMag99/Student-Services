package studsluzba.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import studsluzba.model.PolozioPredmet;
import studsluzba.model.SlusaPredmet;

import java.util.List;

public interface PolozioPredmetRepository extends CrudRepository<PolozioPredmet,Integer> {

    @Query("select pp from PolozioPredmet pp where pp.studentIndex.broj = :broj")
    List<PolozioPredmet> getPolozeniPredmetiByIndex(int broj);


}
