package studsluzba.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStudent;

    private String ime;
    private String prezime;
    private String srednjeIme;
    private long jmbg;
    private LocalDate daumRodjenja;
    private String mestoRodjenja;
    private String drzavljanstvo;
    private String nacionalnost;
    private char pol;
    private String mesto;
    private String ulica;
    private int brojUlice;
    private long brojTelefona;
    private String privemail;
    private String studemail;
    private String brojLicneKarte;
    private String licnuKartuIzdao;
    private boolean upisaoPrvuGodinu;
    private double uspehSrednjaSkola;
    private double uspehPrijemni;
    private String prelaz;


    @ManyToOne()
    @JoinColumn(name = "idSrednjaSkola")
    private SrednjaSkola srednjaSkola;

    @ManyToOne()
    @JoinColumn(name = "idVisokaSkola")
    private VisokaSkola visokaSkola;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private List<StudIndex> indexi;

    public Student() {

    }

    public Student(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    public Student(String ime, String prezime, String srednjeIme, long jmbg, LocalDate daumRodjenja, String mestoRodjenja, String drzavljanstvo, String nacionalnost, char pol, String mesto, String ulica, int brojUlice, long brojTelefona, String privemail, String studemail, String brojLicneKarte, String licnuKartuIzdao, boolean upisaoPrvuGodinu, double uspehSrednjaSkola, double uspehPrijemni, String prelaz/*, SrednjaSkola srednjaSkola*/, VisokaSkola visokaSkola) {
        this.ime = ime;
        this.prezime = prezime;
        this.srednjeIme = srednjeIme;
        this.jmbg = jmbg;
        this.daumRodjenja = daumRodjenja;
        this.mestoRodjenja = mestoRodjenja;
        this.drzavljanstvo = drzavljanstvo;
        this.nacionalnost = nacionalnost;
        this.pol = pol;
        this.mesto = mesto;
        this.ulica = ulica;
        this.brojUlice = brojUlice;
        this.brojTelefona = brojTelefona;
        this.privemail = privemail;
        this.studemail = studemail;
        this.brojLicneKarte = brojLicneKarte;
        this.licnuKartuIzdao = licnuKartuIzdao;
        this.upisaoPrvuGodinu = upisaoPrvuGodinu;
        this.uspehSrednjaSkola = uspehSrednjaSkola;
        this.uspehPrijemni = uspehPrijemni;
        this.prelaz = prelaz;
        this.visokaSkola = visokaSkola;
    }


    @Override
    public String toString() {
        return ime + prezime + getIndexi().toString();
    }
}