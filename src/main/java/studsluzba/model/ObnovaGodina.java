package studsluzba.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "obnovaGodine")
public class ObnovaGodina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idObnovaGodina;

    private int datum;
    private String napomena;

    @ManyToOne()
    @JoinColumn(name = "idStudIndex")
    private StudIndex studentIndeks;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "idSkolskaGodina")
    private SkolskaGodina skolskaGodina;

    @OneToMany(mappedBy = "obnova")
    private List<Predmet> predmetPrenos;

    public ObnovaGodina() {

    }

    public ObnovaGodina(int datum, String napomena, StudIndex studentIndeks, SkolskaGodina skolskaGodina) {
        this.datum = datum;
        this.napomena = napomena;
        this.studentIndeks = studentIndeks;
        this.skolskaGodina = skolskaGodina;
    }

    @Override
    public String toString() {
        return "godina: " + getSkolskaGodina().getDatum()+"/" + (getSkolskaGodina().getDatum()+1)%1000;
    }
}
