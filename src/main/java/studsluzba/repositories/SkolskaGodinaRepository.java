package studsluzba.repositories;

import org.springframework.data.repository.CrudRepository;
import studsluzba.model.SkolskaGodina;
import studsluzba.services.SkolskaGodinaService;

public interface SkolskaGodinaRepository extends CrudRepository<SkolskaGodina,Integer> {

    SkolskaGodina findByDatum(int godina);

}
