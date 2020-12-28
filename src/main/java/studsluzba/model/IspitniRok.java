package studsluzba.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ispitniRok")
public class IspitniRok {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idIspitniRok;

    private String datumPocetka;
    private String datumZavrsetka;

    @OneToMany(mappedBy = "ispitniRok")
    private List<PrijavaIspita> prijavaIspitaList;

    @ManyToOne()
    @JoinColumn(name = "idIspit")
    private Ispit ispit;

    @ManyToOne()
    @JoinColumn(name = "idSkolskaGodina")
    private SkolskaGodina skolskaGod;

    public IspitniRok(String datumPocetka, String datumZavrsetka, SkolskaGodina skolskaGod) {
        this.datumPocetka = datumPocetka;
        this.datumZavrsetka = datumZavrsetka;
        this.skolskaGod = skolskaGod;
    }

    public IspitniRok() {

    }

    @Override
    public String toString() {
        return "datum pocetka: " + datumPocetka + "-" + "datumZavrsetka: " + datumZavrsetka;
    }
}
