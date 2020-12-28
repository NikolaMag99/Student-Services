package studsluzba.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import studsluzba.model.*;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    //upit selekcija studenta koristeci ime ili prezime ili ime i prezime
    @Query("select s from Student s where "
            + "(:ime is null or lower(s.ime) like :ime) or "
            + "(:prezime is null or lower(s.prezime) like :prezime)")
    List<Student> findStudentByNameAndSurname(String ime, String prezime);

    //upit izvlacenje studenta preko broja indeksa
    @Query("select s from Student s where s.idStudent = (select k.student.idStudent from StudIndex k where k.broj = :br and k.studProgram.skraceniNaziv like :smer and k.godina = :godina)")
    Student selectStudentByIndex(String smer, int br, int godina);

    //upit selekcija svih položenih ispita preko broja indeksa studenta
    @Query("select p from PolozioPredmet p where p.studentIndex.broj = :br and p.studentIndex.godina = :godina and p.studentIndex.studProgram.skraceniNaziv like :smer")
    List<PolozioPredmet> selectPolozeniPredByIndex(String smer, int br, int godina);

    //upit selekcija studenata koji su zavrsili odredjenu srednju skolu
    @Query("select s from Student s where lower(s.srednjaSkola.naziv) like :ime_srednje_skole")
    List<Student> findStudentByHighSchool(String ime_srednje_skole);

    //pregled svih upisanih godina za broj indeksa
    @Query("select u from UpisGodina u where u.studentIndex.broj = :br and u.studentIndex.godina = :godina and u.studentIndex.studProgram.skraceniNaziv like :smer")
    List<UpisGodina> findUpisaneGodineByIndex(String smer, int br, int godina);

    //pregled obnovljenih godina za broj indeksa
    @Query("select o from ObnovaGodina o where o.studentIndeks.broj = :br and o.studentIndeks.godina = :godina and o.studentIndeks.studProgram.skraceniNaziv like :smer ")
    List<ObnovaGodina> findObnovljeneGodineByIndex(String smer, int br, int godina);

    //upit selekcija svih ispita koje slusa
    @Query("select p from SlusaPredmet p where p.index.broj = :br and p.index.godina = :godina and p.index.studProgram.skraceniNaziv like :smer")
    List<SlusaPredmet> selectSlusaPredByIndex(String smer, int br, int godina);
}
