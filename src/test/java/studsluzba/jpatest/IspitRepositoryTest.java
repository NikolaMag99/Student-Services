package studsluzba.jpatest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import studsluzba.model.OsvojeniPredispitniPoeni;
import studsluzba.model.PredispitneObaveze;
import studsluzba.model.StudIndex;
import studsluzba.repositories.IspitRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IspitRepositoryTest {

    @Autowired
    IspitRepository ispitRepository;

    @Test
    public void ispitTest() throws Exception {

        //svi prijavljeni studenti za ispit
      /*  List<StudIndex> studIndeksi = ispitRepository.findStudentsRegisteredForExam("1234");
        for (StudIndex index : studIndeksi) {
            System.out.println(index);
        }
*/
        // prosečna ocena na ispitu
       /* Float avg = ispitRepository.getAverageGradeOnTheExam("1234");
        System.out.println(avg);*/

        //selekcija ostvarenih poena na predispinim obavezama za studenta na određenom
        //predmetu u školskoj godini
      /*  List<PredispitneObaveze> predispitneObaveze = ispitRepository.getPoint(55,"Softverske komponente", 2019);
        for (PredispitneObaveze obaveze : predispitneObaveze){
            System.out.println(obaveze);
        }*/

        //za datog studenta i predmet vratiti koliko je puta student polagao predmet (izašao na
        //ispit)
   /*     Integer cnt = ispitRepository.getCountIspitOut("Softverske komponente", 55);
        System.out.println(cnt);*/


        //rezultati ispita u vidu sortiranog spiska studenata (sortirati prvo po stud programu, zatim
        //po godini upisa, pa po broju) sa ukupnim brojem poena na ispitu (predispitne obaveze
        //plus poeni sa ispita)
      /*  List<OsvojeniPredispitniPoeni> osvojeniPredispitniPoenis = ispitRepository.sortStudByExamResults("1234");
        for (OsvojeniPredispitniPoeni os : osvojeniPredispitniPoenis) {
            System.out.println(os);
        }*/


    }

}
