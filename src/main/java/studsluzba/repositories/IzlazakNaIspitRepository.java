package studsluzba.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import studsluzba.model.IspitniRok;
import studsluzba.model.IzlazakNaIspit;
import studsluzba.model.Predmet;
import studsluzba.model.PrijavaIspita;

import java.util.List;

public interface IzlazakNaIspitRepository extends CrudRepository<IzlazakNaIspit, Integer>
{

}
