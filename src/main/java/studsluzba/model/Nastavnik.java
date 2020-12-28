package studsluzba.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "nastavnik")
public class Nastavnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNastavnik;

    private String ime;
    private String prezime;
    private String srednjeIme;
    private String email;
    private String obrazovanje;

    @OneToMany(mappedBy = "nastavnik", fetch = FetchType.EAGER)
    private List<Zvanje> zvanja;

    @OneToMany(mappedBy = "nastavnik")
    private List<DrziPredmet> drziPredmet;

    public Nastavnik(String ime, String prezime, String srednjeIme, String email, String obrazovanje) {
        this.ime = ime;
        this.prezime = prezime;
        this.srednjeIme = srednjeIme;
        this.email = email;
        this.obrazovanje = obrazovanje;
    }

    public Nastavnik() {

    }

    @Override
    public String toString() {
        return ime + prezime + zvanja.toString();
    }
}
