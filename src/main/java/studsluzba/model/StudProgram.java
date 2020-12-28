package studsluzba.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "studProgram")
public class StudProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idstudProgram;

    private String naziv;
    private String skraceniNaziv;
    private LocalDate godinaAkreditacije;
    private int trajanje;
    private String nazivZvanja;

    @OneToMany(mappedBy = "studProgram")
    private List<VrstaStudija> vrsteStudija;

    @OneToMany(mappedBy = "studProgram")
    private List<StudIndex> studIndexi;

    @OneToMany(mappedBy = "studProgram")
    private List<Predmet> predmeti;

    public StudProgram() {
    }

    public StudProgram(String naziv, String skraceniNaziv, LocalDate godinaAkreditacije, int trajanje, String nazivZvanja) {
        this.naziv = naziv;
        this.skraceniNaziv = skraceniNaziv;
        this.godinaAkreditacije = godinaAkreditacije;
        this.trajanje = trajanje;
        this.nazivZvanja = nazivZvanja;
    }

    @Override
    public String toString() {
        return skraceniNaziv;
    }
}