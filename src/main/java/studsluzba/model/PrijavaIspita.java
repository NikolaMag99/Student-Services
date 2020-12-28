package studsluzba.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "prijavaIspit")
public class PrijavaIspita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPrijava;
    private LocalDate datum;

    @ManyToOne()
    @JoinColumn(name = "idIspit")
    private Ispit ispit;

    @ManyToOne()
    @JoinColumn(name = "idIspitniRok")
    private IspitniRok ispitniRok;

    @ManyToOne()
    @JoinColumn(name = "idStudIndex")
    private StudIndex studIndexi;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "idIzlazak")
    private IzlazakNaIspit izlazakNaIspit;

    public PrijavaIspita() {
    }

    public PrijavaIspita(LocalDate datum, Ispit ispit) {
        this.datum = datum;
        this.ispit = ispit;
    }

}
