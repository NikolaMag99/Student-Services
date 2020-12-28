package studsluzba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studsluzba.model.*;
import studsluzba.repositories.StudIndexRepository;
import studsluzba.repositories.StudProgramRepository;
import studsluzba.repositories.StudentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudIndexRepository studIndexRepository;

    @Autowired
    StudProgramService studProgramService;

    @Autowired
    StudProgramRepository studProgramRepository;


    public Student save(String ime, String prezime, String srednje, long jmbg, LocalDate rodjenje, String mestoRodjenja, String nacionalnost,
                        String drzavaRodjenja, char pol, String licnaIzdao, String prelaz,
                        String drzavljanstvo, String licna, String adresaStanovanja, int brojUlice, long brojTelefona,
                        String privEmail, String mestoStanovanja, SrednjaSkola srednja,
                        double uspehSrednja, LocalDate datumUpisa, double uspehPrijemnni, String smer, int indeks, StudProgram program) {

        if (ime != null && prezime != null && srednje != null && rodjenje != null && mestoRodjenja
                != null && drzavaRodjenja != null && drzavljanstvo != null && licna != null && adresaStanovanja
                != null && mestoStanovanja != null && srednja != null && datumUpisa != null && smer != null) {

            VisokaSkola visokaSkola = null;
            Student student = new Student(ime, prezime, srednje, jmbg, rodjenje, mestoRodjenja, drzavljanstvo, nacionalnost, pol,
                    mestoStanovanja, adresaStanovanja, brojUlice, brojTelefona, privEmail, null, licna, licnaIzdao, false,
                    uspehSrednja, uspehPrijemnni, prelaz, visokaSkola);

            student.setSrednjaSkola(srednja);
            srednja.addStudent(student);
            StudIndex studIndex = new StudIndex(indeks, datumUpisa.getYear(), true, datumUpisa, student, program);

            boolean upisaoPrvuGodinu;
            upisaoPrvuGodinu = prelaz != null;

            int godinaIndex = studIndex.getGodina();
            godinaIndex = godinaIndex % 1000;
            String studEmail = ime.charAt(0) + prezime + studIndex.getBroj() + godinaIndex + "@raf.rs";
            student.setStudemail(studEmail);
            student.setUpisaoPrvuGodinu(upisaoPrvuGodinu);

            try {
                Student toReturn = studentRepository.save(student);
                studIndex.setStudent(student);
                studIndexRepository.save(studIndex);
                return toReturn;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        } else {
            return null;
        }

    }

    public Optional<Student> changeIndex(Student s, Integer novaGodina, String novSmer, Integer novBroj, StudIndex studeIndexSelected) {
        StudIndex novIndex = new StudIndex();

        StudProgram studProgram = null;

        List<StudProgram> programi = studProgramService.loadAll();
        for (StudProgram sp : programi) {
            if (sp.getSkraceniNaziv().equals(novSmer)) {
                studProgram = sp;
            }
        }
        studeIndexSelected.setAktivan(false);
        studIndexRepository.save(studeIndexSelected);

        novIndex.setStudProgram(studProgram);
        novIndex.setAktivan(true);
        novIndex.setStudent(s);
        novIndex.setOdKadJeAktivan(LocalDate.now());
        novIndex.setBroj(novBroj);
        novIndex.setGodina(novaGodina);
        studIndexRepository.save(novIndex);

        return studentRepository.findById(s.getIdStudent());
    }

    public List<Student> findStudent(String ime, String prezime) {
        try {
            return studentRepository.findStudentByNameAndSurname(ime.toLowerCase(), prezime.toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Student findStudentByIndex(String smer, Integer broj, Integer godina) {
        try {
            return studentRepository.selectStudentByIndex(smer, broj, godina);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public StudIndex getStudentIndeks(String studProgram, int broj, int godinaUpisa) {
        List<StudIndex> indeksi = studIndexRepository.getStudentIndeksi(studProgram, broj, godinaUpisa);
        if (indeksi.isEmpty()) return null;
        return indeksi.get(0);
    }

    public StudIndex saveStudentAndIndex(String ime, String prezime, String studProgram, int broj, int godinaUpisa) {
        Student s = new Student(ime, prezime);
        s = studentRepository.save(s);
        StudProgram sp = studProgramRepository.getStudProgramBySkraceniNaziv(studProgram);
        StudIndex si = new StudIndex(broj, godinaUpisa, sp);
        si.setAktivan(true);
        si.setStudent(s);
        return studIndexRepository.save(si);
    }

}
