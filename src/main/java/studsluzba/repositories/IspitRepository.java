package studsluzba.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import studsluzba.model.Ispit;
import studsluzba.model.OsvojeniPredispitniPoeni;
import studsluzba.model.PredispitneObaveze;
import studsluzba.model.StudIndex;

import java.util.List;

public interface IspitRepository extends CrudRepository<Ispit, Integer> {
    //svi prijavljeni studenti za ispit
    @Query("select s from StudIndex s inner join s.prijavaIspita as pi where pi.ispit.sifraIspita = :sifra")
    List<StudIndex> findStudentsRegisteredForExam(String sifra);

    // prosečna ocena na ispitu
    @Query("select avg(pp.ocena) from PolozioPredmet pp where pp.izlazakNaIspit.ispit.sifraIspita like :ispitSifra")
    Float getAverageGradeOnTheExam(String ispitSifra);

    //za datog studenta i predmet vratiti koliko je puta student polagao predmet (izašao na
    //ispit)
    @Query("select count(iz.izasaoNaIspit) from IzlazakNaIspit iz where iz.ispit.idIspit = (select i.idIspit " +
            "from Ispit i where i.predmet.nazivPredmeta like :nazivPredmeta " +
            "and i.predmet.polozioPredmet.studentIndex.idStudIndex = (select si.idStudIndex from StudIndex si where si.broj = :broj))")
    Integer getCountIspitOut(String nazivPredmeta, int broj);

    //selekcija ostvarenih poena na predispinim obavezama za studenta na određenom
    //predmetu u školskoj godini
    @Query("select p from PredispitneObaveze p where p.predmet.idPredmet = (select pre.predmet.idPredmet from" +
            " DrziPredmet pre where pre.predmet.nazivPredmeta like :naziv and" +
            " pre.skolskaGod.datum = :godina and pre.idDrziPredmet = (select sl.drziPredmet.idDrziPredmet from SlusaPredmet sl where " +
            "sl.index.broj = :broj))")
    List<PredispitneObaveze> getPoint(int broj, String naziv, int godina);

    //rezultati ispita u vidu sortiranog spiska studenata (sortirati prvo po stud programu, zatim
    //po godini upisa, pa po broju) sa ukupnim brojem poena na ispitu (predispitne obaveze
    //plus poeni sa ispita)
    @Query("select si.predIspitne from StudIndex si where si.idStudIndex in (select pp.studentIndex.idStudIndex from PolozioPredmet pp where pp.izlazakNaIspit.ispit.sifraIspita like :sifraIspita) " +
            "order by si.studProgram.skraceniNaziv asc, si.godina asc, si.broj")
    List<OsvojeniPredispitniPoeni> sortStudByExamResults(String sifraIspita);


}



