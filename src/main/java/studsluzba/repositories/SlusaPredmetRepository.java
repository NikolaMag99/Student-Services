package studsluzba.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import studsluzba.model.SlusaPredmet;

import java.util.List;

public interface SlusaPredmetRepository extends CrudRepository<SlusaPredmet, Integer>{

    @Query("select sp from SlusaPredmet sp where sp.index.broj = :broj")
    List<SlusaPredmet> getSlusaPredmetiByIndex(int broj);

}
