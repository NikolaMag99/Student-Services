package studsluzba.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "upisGodina")
public class UpisGodina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUpisGodina;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "idSkolskaGodina")
    private SkolskaGodina skolskaGodina;

    @ManyToOne()
    @JoinColumn(name = "idStudIndex")
    private StudIndex studentIndex;

    @OneToMany(mappedBy = "upisGodina")
    private List<Predmet> predmetPrenos;

    private int datum;
    private String napomena;


    public UpisGodina() {

    }

    public UpisGodina(SkolskaGodina skolskaGodina, StudIndex studentIndex, int datum, String napomena) {
        this.skolskaGodina = skolskaGodina;
        this.studentIndex = studentIndex;
        this.datum = datum;
        this.napomena = napomena;
    }

    @Override
    public String toString() {
        return "godina: " + getSkolskaGodina().getDatum() + "/" + (getSkolskaGodina().getDatum() + 1) % 1000;
    }
}
