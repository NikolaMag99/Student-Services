package studsluzba.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "polozioPredmet")
public class PolozioPredmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPolozioPredmet;
    private int ocena;
    private float ukupanBrojPoena;
    private boolean priznatSaDrugogFaksa;

    @ManyToOne()
    @JoinColumn(name = "idStudIndex")
    private StudIndex studentIndex;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "polozioPredmet")
    private List<Predmet> predmeti;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "idIzlazak") //todo bilo je all
    private IzlazakNaIspit izlazakNaIspit;

    public PolozioPredmet() {

    }

    public PolozioPredmet(int ocena, float ukupanBrojPoena, boolean priznatSaDrugogFaksa, StudIndex studentIndex) {
        this.ocena = ocena;
        this.ukupanBrojPoena = ukupanBrojPoena;
        this.priznatSaDrugogFaksa = priznatSaDrugogFaksa;
        this.studentIndex = studentIndex;
    }

    @Override
    public String toString() {
        return this.getPredmeti().toString();
    }
}
