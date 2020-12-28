package studsluzba.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import studsluzba.model.DrziPredmet;
import studsluzba.model.Nastavnik;
import studsluzba.model.SkolskaGodina;

import java.util.List;

public interface NastavnikRepository extends CrudRepository<Nastavnik, Integer> {

    @Query("select dp from DrziPredmet dp where dp.skolskaGod = :sk")
    List<DrziPredmet> findNastavnikBySk(SkolskaGodina sk);

}
