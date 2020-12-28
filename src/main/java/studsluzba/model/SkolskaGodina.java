package studsluzba.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "skolskaGodina")
public class SkolskaGodina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSkolskaGodina;
    private int datum;
    private boolean aktivna;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "idObnovaGodina")
    private ObnovaGodina obnovaGodine;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "idUpisGodina")
    private UpisGodina upisGodine;

    @OneToMany(mappedBy = "skolskaGod")
    private List<IspitniRok> ispitniRokovi;


    public SkolskaGodina() {

    }

    public SkolskaGodina(int datum, boolean aktivna, ObnovaGodina obnovaGodine) {
        this.datum = datum;
        this.aktivna = aktivna;
        this.obnovaGodine = obnovaGodine;
    }

    @Override
    public String toString() {
        return "skolska godina: " + datum;
    }
}
