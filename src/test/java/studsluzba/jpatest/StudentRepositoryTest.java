package studsluzba.jpatest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import studsluzba.model.*;
import studsluzba.repositories.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    DrziPredmetRepository drziPredmetRepository;

    @Autowired
    IspitniRokRepository ispitniRokRepository;

    @Autowired
    IspitRepository ispitRepository;

    @Autowired
    IzlazakNaIspitRepository izlazakNaIspitRepository;

    @Autowired
    NastavnikRepository nastavnikRepository;

    @Autowired
    ObnovaGodinaRepository obnovaGodinaRepository;

    @Autowired
    OsvojeniPredispitniPoeniRepository osvojeniPredispitniPoeniRepository;

    @Autowired
    PolozioPredmetRepository polozioPredmetRepository;

    @Autowired
    PredispitneObavezeRepository predispitneObavezeRepository;

    @Autowired
    PredmetRepository predmetRepository;

    @Autowired
    PrijavaIspitaRepository prijavaIspitaRepository;

    @Autowired
    SkolskaGodinaRepository skolskaGodinaRepository;

    @Autowired
    SlusaPredmetRepository slusaPredmetRepository;

    @Autowired
    SrednjaSkolaRepository srednjaSkolaRepository;

    @Autowired
    StudentRepository studRepo;

    @Autowired
    StudIndexRepository studIndexRepository;

    @Autowired
    StudProgramRepository studProgramRepository;

    @Autowired
    UpisGodineRepository upisGodineRepository;

    @Autowired
    VisokaSkolaRepository visokaSkolaRepository;

    @Autowired
    VrstaStudijaRepository vrstaStudijaRepository;

    @Autowired
    ZvanjeRepository zvanjeRepository;


    @Test
    public void saveStudentTest() throws Exception {
        SrednjaSkola srednjaSkola = new SrednjaSkola();
        Student s = new Student();
        StudIndex studIndex = new StudIndex();
        PolozioPredmet polozioPredmet = new PolozioPredmet();
        StudProgram studProgram = new StudProgram();
        Predmet predmet = new Predmet();
        VrstaStudija vrstaStudija = new VrstaStudija();
        SkolskaGodina skolskaGodina = new SkolskaGodina();
        ObnovaGodina obnovaGodina = new ObnovaGodina();
        UpisGodina upisGodina = new UpisGodina();
        Ispit ispit = new Ispit();
        IspitniRok ispitniRok = new IspitniRok();
        PrijavaIspita prijavaIspita = new PrijavaIspita();
        Zvanje zvanje = new Zvanje();
        Nastavnik nastavnik = new Nastavnik();
        IzlazakNaIspit izlazakNaIspit = new IzlazakNaIspit();
        IzlazakNaIspit izlazakNaIspit1 = new IzlazakNaIspit();
        PredispitneObaveze predispitneObaveze = new PredispitneObaveze();
        OsvojeniPredispitniPoeni osvojeniPredispitniPoeni = new OsvojeniPredispitniPoeni();
        SlusaPredmet slusaPredmet = new SlusaPredmet();
        DrziPredmet drziPredmet = new DrziPredmet();
        PredispitneObaveze predispitneObaveze1 = new PredispitneObaveze();
        VisokaSkola visokaSkola = new VisokaSkola();

        // Slusa predmet
        //   slusaPredmet.setIndex(studIndex);
        //      slusaPredmet.setDrziPredmet(drziPredmet);//ovo

        //Drzi predmet
        //   drziPredmet.addSlusaPredmet(slusaPredmet);
        //    drziPredmet.setNastavnik(nastavnik);
        //     drziPredmet.setPredmet(predmet);
        //  drziPredmet.setSkolskaGod(skolskaGodina);
        drziPredmet.setSifra("1234");

        //Osvojene pred
        osvojeniPredispitniPoeni.setUkupanBrojPoena(60);
        //  osvojeniPredispitniPoeni.setStudentIndeks(studIndex);
        //  osvojeniPredispitniPoeni.addPredIspitne(predispitneObaveze);
        //  osvojeniPredispitniPoeni.addPredIspitne(predispitneObaveze1);

        //pred ispitne obaveze
        predispitneObaveze.setPoeni(45);
        predispitneObaveze.setMaxBrp(50);
        predispitneObaveze.setVrstaObabeza("Kolokvijum");
        //  predispitneObaveze.setSkolskaGodina(skolskaGodina);

        predispitneObaveze1.setPoeni(30);
        predispitneObaveze1.setMaxBrp(50);
        predispitneObaveze1.setVrstaObabeza("Kolokvijum");
        // predispitneObaveze1.setSkolskaGodina(skolskaGodina);

        //nastavnil
        //   nastavnik.addZvanje(zvanje);
        nastavnik.setIme("Petar");
        nastavnik.setPrezime("Petric");
        nastavnik.setEmail("pp@raf.rs");
        nastavnik.setObrazovanje("treci stepen");
        nastavnik.setSrednjeIme("Pera");
        //  nastavnik.addDrziPredmet(drziPredmet);

        //zvanje
        zvanje.setDatumIzbora(LocalDate.now());
        zvanje.setNazivZvanja("Dipl inzinjer");
        zvanje.setUzaNaucnaOblast("truc truc...");

        //ispit
        ispit.setDatumOdrzavanja(LocalDate.now());
        ispit.setUnetiPoeni(false);
        ispit.setVremePocetka("12:00");
        // ispit.setNastavnik(nastavnik);
        //   ispit.addPrijavaIspita(prijavaIspita);
        ispit.setSifraIspita("1234");
        // ispit.addIzlazakNaIspit(izlazakNaIspit);
        //ispit.addIzlazakNaIspit(izlazakNaIspit1);

        //ispitni rok
        ispitniRok.setDatumPocetka("14.06.2021");
        ispitniRok.setDatumZavrsetka("20.06.2021");
        // ispitniRok.addIspit(ispit);

        //izlazak na Ispit
        izlazakNaIspit.setNapomena("Sta god");
        izlazakNaIspit.setIzasaoNaIspit(true);
        izlazakNaIspit.setPonistavanje(false);
        izlazakNaIspit.setBrojOsvojenihPoena(95);
        //    izlazakNaIspit.setPrijavljenIspit(prijavaIspita);
        //    izlazakNaIspit.setPolozioPredmet(polozioPredmet);

        izlazakNaIspit1.setNapomena("Staaaaaa god");
        izlazakNaIspit1.setIzasaoNaIspit(true);
        izlazakNaIspit1.setPonistavanje(false);
        izlazakNaIspit1.setBrojOsvojenihPoena(90);
        //  izlazakNaIspit1.setPrijavljenIspit(prijavaIspita);
        //  izlazakNaIspit1.setPolozioPredmet(polozioPredmet);

        //prijavaIpsita
        prijavaIspita.setDatum(LocalDate.now());
        //   prijavaIspita.setIzlazakNaIspit(izlazakNaIspit);

        //Upis godine
        upisGodina.setDatum(2020);
        //   upisGodina.setSkolskaGodina(skolskaGodina);
        upisGodina.setNapomena("napomena");

        //Obnova godine
        obnovaGodina.setDatum(2020);
        obnovaGodina.setNapomena("Napomena");
        //  obnovaGodina.setSkolskaGodina(skolskaGodina);
        //  obnovaGodina.setStudentIndeks(studIndex);
        //obnovaGodina.addPredmetPrenos(predmet);
        //obnovaGodina.addPredmetPrenos(predmet);

        //Skolska godina
        skolskaGodina.setDatum(2019);
        skolskaGodina.setAktivna(true);
        // skolskaGodina.addIspitniRok(ispitniRok);
        // skolskaGodina.setObnovaGodine(obnovaGodina);

        //vrsta studija
        vrstaStudija.setPunNaziv("Osnovne akademske studije");
        vrstaStudija.setSkraceniNaziv("OAS");

        //polozioPred
        polozioPredmet.setOcena(9);
        //   polozioPredmet.addPredmet(predmet);
        polozioPredmet.setPriznatSaDrugogFaksa(false);
        polozioPredmet.setUkupanBrojPoena(60);
        // polozioPredmet.setIzlazakNaIspit(izlazakNaIspit);

        //predmet
        predmet.setBrojESPB(6);
        predmet.setBrojSemestra(5);
        predmet.setFondPredavanja(40);
        predmet.setFondVezbi(40);
        predmet.setOpis("Najjaci predmet");
        predmet.setNazivPredmeta("Softverske komponente");
        predmet.setSifraPredmeta("sk1");
        //predmet.addIspiti(ispit);
        //predmet.addPredIspitne(predispitneObaveze);
        //predmet.addPredIspitne(predispitneObaveze1);

        //studProg
        //  studProgram.addVrstaStudija(vrstaStudija);
        //  studProgram.addPredmet(predmet);
        studProgram.setGodinaAkreditacije(LocalDate.now());
        studProgram.setNaziv("Racunarsko Inzinjerstvo");
        studProgram.setSkraceniNaziv("RI");
        studProgram.setNazivZvanja("Diplomirani inzinjer");
        studProgram.setTrajanje(4);
        //studProgram.addStudIndex(studIndex);


        //index
        studIndex.setGodina(2018);
        studIndex.setAktivan(true);
        studIndex.setBroj(55);
        studIndex.setOdKadJeAktivan(LocalDate.now());
        studIndex.setStudProgram(null);

        //studIndex.addPolozioPredmet(polozioPredmet);
        //studIndex.addUpisGodina(upisGodina);
        //studIndex.addObnovaGodine(obnovaGodina);
        //studIndex.addOsvojeniPoeni(osvojeniPredispitniPoeni);
        //studIndex.addSlusaPred(slusaPredmet);
        // studIndex.addPrijavaIspita(prijavaIspita);

        //srednja sk
        srednjaSkola.setMesto("Obrenovac");
        srednjaSkola.setNaziv("Gimnazija u Obrenovcu");
        srednjaSkola.setVrsta("Gimnazija");
        //srednjaSkola.addStudent(s);

        //visoka skola
        visokaSkola.setNaziv("visa etf");
        visokaSkola.setMesto("Beograd");
        visokaSkola.setVrsta("visoka skola");
        //visokaSkola.addStudent(s);

        //student
        s.setIme("Luka");
        s.setPrezime("Micic");
        s.setSrednjeIme("Marko");
        s.setBrojLicneKarte("52156654433");
        s.setBrojTelefona(060123433);
        s.setBrojUlice(50);
        s.setDaumRodjenja(LocalDate.now());
        s.setDrzavljanstvo("srpsko");
        s.setJmbg(12345678);
        s.setMesto("Beograd");
        s.setLicnuKartuIzdao("Izdavac");
        s.setMestoRodjenja("Beograd");
        s.setNacionalnost("Srbina");
        s.setPol('m');
        s.setPrelaz("");
        s.setUspehSrednjaSkola(4.50);
        s.setPrivemail("nn@gmail.com");
        s.setStudemail("nn@raf.rs");
        s.setUpisaoPrvuGodinu(false);
        s.setUlica("Petra Petrica");
        s.setPrivemail("lm@gmail.com");
        s.setStudemail("lm@raf.rs");
        s.setUspehPrijemni(32);

//        srednjaSkolaRepository.save(srednjaSkola);
//        s.setSrednjaSkola(srednjaSkola);
//
//        visokaSkolaRepository.save(visokaSkola);
//        s.setVisokaSkola(visokaSkola);
//
//        studRepo.save(s);
//
//        studProgramRepository.save(studProgram);
//
//        studIndex.setStudProgram(studProgram);
//        studIndex.setStudent(s);
//        studIndexRepository.save(studIndex);
//
//        polozioPredmet.setStudentIndex(studIndex);
//        polozioPredmet.setIzlazakNaIspit(izlazakNaIspit);
//        polozioPredmetRepository.save(polozioPredmet);
//
//
//        skolskaGodina.setObnovaGodine(obnovaGodina);
//        skolskaGodina.setUpisGodine(upisGodina);
//        skolskaGodinaRepository.save(skolskaGodina);
//
//        obnovaGodina.setSkolskaGodina(skolskaGodina);
//        obnovaGodina.setStudentIndeks(studIndex);
//        obnovaGodinaRepository.save(obnovaGodina);
//
//        upisGodina.setStudentIndex(studIndex);
//        upisGodina.setSkolskaGodina(skolskaGodina);
//        upisGodineRepository.save(upisGodina);
//
//        predmet.setStudProgram(studProgram);
//        predmet.setPolozioPredmet(polozioPredmet);
//        predmet.setObnova(obnovaGodina);
//        predmet.setUpisGodina(upisGodina);
//        predmetRepository.save(predmet);
//
//        nastavnikRepository.save(nastavnik);
//
//        ispitniRok.setSkolskaGod(skolskaGodina);
//        ispitniRokRepository.save(ispitniRok);
//
//        ispit.setIspitniRok(ispitniRok);
//        ispit.setNastavnik(nastavnik);
//        ispit.setPredmet(predmet);
//        ispitRepository.save(ispit);
//
//        prijavaIspita.setIspit(ispit);
//        prijavaIspita.setStudIndexi(studIndex);
//        prijavaIspita.setIzlazakNaIspit(izlazakNaIspit);
//        prijavaIspitaRepository.save(prijavaIspita);
//
//        izlazakNaIspit.setPrijavljenIspit(prijavaIspita);
//        izlazakNaIspit.setIspit(ispit);
//        izlazakNaIspit.setPolozioPredmet(polozioPredmet);
//        izlazakNaIspitRepository.save(izlazakNaIspit);
//
//        drziPredmet.setPredmet(predmet);
//        drziPredmet.setNastavnik(nastavnik);
//        drziPredmet.setSkolskaGod(skolskaGodina);
//        drziPredmetRepository.save(drziPredmet);
//
//        slusaPredmet.setDrziPredmet(drziPredmet);
//        slusaPredmet.setIndex(studIndex);
//        slusaPredmetRepository.save(slusaPredmet);
//
//        zvanje.setNastavnik(nastavnik);
//        zvanjeRepository.save(zvanje);
//
//        vrstaStudija.setStudProgram(studProgram);
//        vrstaStudijaRepository.save(vrstaStudija);
//
//        osvojeniPredispitniPoeni.setStudentIndeks(studIndex);
//        osvojeniPredispitniPoeniRepository.save(osvojeniPredispitniPoeni);
//
//        predispitneObaveze.setSkolskaGodina(skolskaGodina);
//        predispitneObaveze.setOsvojeniPredispitniPoeni(osvojeniPredispitniPoeni);
//        predispitneObaveze.setPredmet(predmet);
//        predispitneObavezeRepository.save(predispitneObaveze);


        //upit izvlacenje studenta preko broja indeksa
//        Student student = studRepo.selectStudentByIndex(55);

//        System.out.println(student);

        //upit selekcija svih položenih ispita preko broja indeksa studenta
//       List<PolozioPredmet> polozioPredmeti = studRepo.selectPolozeniPredByIndex(55);
//        for (PolozioPredmet pred : polozioPredmeti) {
//            System.out.println(pred);
//        }

        // upit selekcija studenta koristeci ime ili prezime ili ime i prezime
//        List<Student> studenti = studRepo.findStudentByNameAndSurname("Luka", "Micic");
//        for (Student student : studenti) {
//            System.out.println(student);
//        }

        //upit selekcija studenata koji su zavrsili odredjenu srednju skolu
//        List<Student> studenti = studRepo.findStudentByHighSchool("gimnazija u obrenovcu");
//        for (Student student : studenti) {
//            System.out.println(student);
//        }

        //pregled svih upisanih godina za broj indeksa
//        List<UpisGodina> upisaneGodine = studRepo.findUpisaneGodineByIndex(55);
//        for (UpisGodina upis : upisaneGodine) {
//            System.out.println(upis);
//        }

        //pregled obnovljenih godina za broj indeksa
//        List<ObnovaGodina> obnovaGodine = studRepo.findObnovljeneGodineByIndex(55);
//        for (ObnovaGodina obnova : obnovaGodine) {
//            System.out.println(obnova);
//        }
    }
}

