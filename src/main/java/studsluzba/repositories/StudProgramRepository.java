package studsluzba.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import studsluzba.model.Predmet;
import studsluzba.model.StudProgram;
import studsluzba.model.Student;

import java.util.List;

public interface StudProgramRepository extends CrudRepository<StudProgram, Integer> {
    //spisak predmeta na studijskom programu
    @Query("select p from Predmet p where p.studProgram.skraceniNaziv like :skraceni_naziv")
    List<Predmet> selectPredmetiByStudProg(String skraceni_naziv);

    //prosečna ocena studenata na predmetu za raspon godina (na primer zadaje se godine
    //2015-2018 i vraća se prosečna ocena svih studenata koji su položili predmet u tom
    //periodu)
    @Query("select avg(pp.ocena) from PolozioPredmet pp where pp.idPolozioPredmet in " +
            "(select pre.predmet.polozioPredmet.idPolozioPredmet from DrziPredmet pre " +
            "where pre.skolskaGod.datum between :odGodine and :doGodine and pre.predmet.nazivPredmeta like :nazivPred)")
    Float selectProsecnaOcenaZaRasponGodina(String nazivPred, int odGodine, int doGodine);

    @Query("select sp from StudProgram sp where sp.skraceniNaziv like :skraceniNaziv")
    StudProgram getStudProgramBySkraceniNaziv(String skraceniNaziv);

    @Query("select si.student from StudIndex si where si.studProgram = :sp ")
        //dodati da je indeks aktivan and si.aktivan = true
    List<Student> getStudentiNaProgramu(StudProgram sp);

}
