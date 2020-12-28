package studsluzba.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import studsluzba.model.StudIndex;

import java.util.List;

public interface StudIndexRepository extends CrudRepository<StudIndex, Integer> {

    @Query("select si from StudIndex si where si.studProgram.skraceniNaziv like :skraceniNazivStudPrograma and "
            + "si.broj = :broj and si.godina = :godinaUpisa")
    List<StudIndex> getStudentIndeksi(String skraceniNazivStudPrograma, int broj, int godinaUpisa);

}
