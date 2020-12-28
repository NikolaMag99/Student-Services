package studsluzba.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "osvojeniPoeni")
public class OsvojeniPredispitniPoeni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOsvojeniPoeni;
    private double ukupanBrojPoena;

    @ManyToOne()
    @JoinColumn(name = "idStudIndex")
    private StudIndex studentIndeks;

    @OneToMany(mappedBy = "osvojeniPredispitniPoeni")
    private List<PredispitneObaveze> predIspitneObaveze;

    public OsvojeniPredispitniPoeni() {

    }

    public OsvojeniPredispitniPoeni(int ukupanBrojPoena, StudIndex studentIndeks) {
        this.ukupanBrojPoena = ukupanBrojPoena;
        this.studentIndeks = studentIndeks;
    }

    @Override
    public String toString() {
        return "OsvojeniPredispitniPoeni{" +
                "idOsvojeniPoeni=" + idOsvojeniPoeni +
                ", ukupanBrojPoena=" + ukupanBrojPoena +
                ", studentIndeks=" + studentIndeks +
                '}';
    }
}
