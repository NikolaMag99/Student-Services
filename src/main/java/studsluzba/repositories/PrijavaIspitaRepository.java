package studsluzba.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import studsluzba.model.IspitniRok;
import studsluzba.model.Predmet;
import studsluzba.model.PrijavaIspita;

import java.util.List;

public interface PrijavaIspitaRepository extends CrudRepository<PrijavaIspita, Integer> {

    // ovo treba da vrati listu svih prijava studenata za ispit prosledjenog predmeta i roka i da su izasli na ispit
    @Query("select p from PrijavaIspita p where p.ispitniRok.idIspitniRok = (select ir.idIspitniRok from IspitniRok ir where ir.ispit.predmet" +
            "= :predmet and ir = :ispitniRok) and p.izlazakNaIspit.izasaoNaIspit is not null")
    List<PrijavaIspita> prijave (Predmet predmet, IspitniRok ispitniRok);

}
