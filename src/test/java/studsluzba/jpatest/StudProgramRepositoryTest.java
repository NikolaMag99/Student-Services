package studsluzba.jpatest;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import studsluzba.model.Predmet;
import studsluzba.repositories.StudProgramRepository;
import studsluzba.repositories.StudentRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudProgramRepositoryTest {


    @Autowired
    StudProgramRepository studProgramRepo;

    @Test
    public void studPrgoramTest() throws Exception {

        //spisak predmeta na studijskom programu
       /* List<Predmet> predmeti = studProgramRepo.selectPredmetiByStudProg("RI");
        for (Predmet p : predmeti) {
            System.out.println(p);
        }*/

        //prosečna ocena studenata na predmetu za raspon godina (na primer zadaje se godine
        //2015-2018 i vraća se prosečna ocena svih studenata koji su položili predmet u tom
        //periodu)
//        Float prosecnaOcena = studProgramRepo.selectProsecnaOcenaZaRasponGodina("Softverske komponente", 2018, 2020);
//        System.out.println(prosecnaOcena);

    }

}
