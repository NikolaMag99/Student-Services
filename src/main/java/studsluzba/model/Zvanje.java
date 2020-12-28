package studsluzba.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "zvanje")
public class Zvanje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idZvanje;

    private LocalDate datumIzbora;
    private String uzaNaucnaOblast;
    private String nazivZvanja;

    @ManyToOne()
    @JoinColumn(name = "idNastavnik")
    private Nastavnik nastavnik;

    public Zvanje() {
    }

    public Zvanje(LocalDate datumIzbora, String uzaNaucnaOblast, String nazivZvanja) {
        this.datumIzbora = datumIzbora;
        this.uzaNaucnaOblast = uzaNaucnaOblast;
        this.nazivZvanja = nazivZvanja;
    }

    @Override
    public String toString() {
        return nazivZvanja;
    }
}
