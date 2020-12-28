package studsluzba.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "izlazakIspit")
public class IzlazakNaIspit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idIzlazak;

    private double brojOsvojenihPoena;
    private String napomena;
    private boolean ponistavanje;
    private boolean izasaoNaIspit;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "idPrijava")
    private PrijavaIspita prijavljenIspit;


    @ManyToOne()
    @JoinColumn(name = "idIspit")
    private Ispit ispit;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "idPolozioPredmet") //todo bilo je all
    private PolozioPredmet polozioPredmet;

    public IzlazakNaIspit() {

    }

    public IzlazakNaIspit(int brojOsvojenihPoena, String napomena, boolean ponistavanje, boolean izasaoNaIspit, PrijavaIspita prijavljenIspit, Ispit ispit) {
        this.brojOsvojenihPoena = brojOsvojenihPoena;
        this.napomena = napomena;
        this.ponistavanje = ponistavanje;
        this.izasaoNaIspit = izasaoNaIspit;
        this.prijavljenIspit = prijavljenIspit;
        this.ispit = ispit;
    }

    @Override
    public String toString() {
        return "IzlazakNaIspit{" +
                "idIzlazak=" + idIzlazak +
                ", brojOsvojenihPoena=" + brojOsvojenihPoena +
                ", napomena='" + napomena + '\'' +
                ", ponistavanje=" + ponistavanje +
                ", izasaoNaIspit=" + izasaoNaIspit +
                ", prijavljenIspit=" + prijavljenIspit +
                ", ispit=" + ispit +
                '}';
    }
}
