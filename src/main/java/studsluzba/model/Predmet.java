package studsluzba.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "predmet")
public class Predmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPredmet;

    private String sifraPredmeta;
    private String nazivPredmeta;
    private String opis;
    private int brojESPB;
    private int fondPredavanja;
    private int fondVezbi;
    private int brojSemestra;

    @ManyToOne()
    @JoinColumn(name = "idPolozioPredmet")
    private PolozioPredmet polozioPredmet;

    @OneToMany(mappedBy = "predmet")
    private List<Ispit> ispit;

    @OneToMany(mappedBy = "predmet")
    private List<PredispitneObaveze> obaveze;

    @ManyToOne()
    @JoinColumn(name = "idObnovaGodina")
    private ObnovaGodina obnova;

    @ManyToOne()
    @JoinColumn(name = "idUpisGodina")
    private UpisGodina upisGodina;

    @ManyToOne()
    @JoinColumn(name = "idstudProgram")
    private StudProgram studProgram;

    public Predmet() {
    }

    public Predmet(String sifraPredmeta, String nazivPredmeta, String opis, int brojESPB, int fondPredavanja, int fondVezbi, int brojSemestra, PolozioPredmet polozioPredmet,  ObnovaGodina obnovaGodina, StudProgram studProgram) {
        this.sifraPredmeta = sifraPredmeta;
        this.nazivPredmeta = nazivPredmeta;
        this.opis = opis;
        this.brojESPB = brojESPB;
        this.fondPredavanja = fondPredavanja;
        this.fondVezbi = fondVezbi;
        this.brojSemestra = brojSemestra;
        this.polozioPredmet = polozioPredmet;
        this.obnova = obnovaGodina;
        this.studProgram = studProgram;
    }

    @Override
    public String toString() {
        return "Naziv: " + nazivPredmeta;
    }
}
