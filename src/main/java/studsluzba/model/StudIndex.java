package studsluzba.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "studIndex")
public class StudIndex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStudIndex;

    private int broj;
    private int godina;
    private boolean aktivan;
    private LocalDate odKadJeAktivan;

    @ManyToOne()
    @JoinColumn(name = "idStudent")
    private Student student;

    @OneToMany(mappedBy = "studIndexi")
    private List<PrijavaIspita> prijavaIspita;

    @OneToMany(mappedBy = "studentIndex")
    private List<UpisGodina> upisGodina;

    @OneToMany(mappedBy = "studentIndeks")
    private List<ObnovaGodina> obnovljeneGodine;

    @OneToMany(mappedBy = "studentIndex", fetch = FetchType.EAGER)
    private List<PolozioPredmet> polozioPredmete;

    @OneToMany(mappedBy = "studentIndeks")
    private List<OsvojeniPredispitniPoeni> predIspitne;

    @OneToMany(mappedBy = "index")
    private List<SlusaPredmet> slusaPredmete;

    @ManyToOne()
    @JoinColumn(name = "idstudProgram")
    private StudProgram studProgram;

    public StudIndex(int broj, int godina, StudProgram studProgram){
        this.broj = broj;
        this.godina = godina;
        this.studProgram = studProgram;
    }

    public StudIndex(int broj, int godina, boolean aktivan, LocalDate odKadJeAktivan, Student student, StudProgram studProgram) {
        this.broj = broj;
        this.godina = godina;
        this.aktivan = aktivan;
        this.odKadJeAktivan = odKadJeAktivan;
        this.student = student;
        this.studProgram = studProgram;
    }

    public StudIndex() {

    }

    @Override
    public String toString() {
        String aktivanIndex;
        if(aktivan){
            aktivanIndex = "aktivan";
        }else
            aktivanIndex = "nije aktivan";

        return "indeks: " + broj + "/" + godina + studProgram + " - " + aktivanIndex;
    }
}
