package studsluzba.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "vrstaStudija")
public class VrstaStudija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVrstaStudija;

    private String punNaziv;
    private String skraceniNaziv;

    @ManyToOne()
    @JoinColumn(name = "idstudProgram")
    private StudProgram studProgram;

    public VrstaStudija() {
        super();
    }

    public VrstaStudija(int vrstaStudijaid, String punNaziv, String skraceniNaziv) {
        this.idVrstaStudija = vrstaStudijaid;
        this.punNaziv = punNaziv;
        this.skraceniNaziv = skraceniNaziv;
    }

    @Override
    public String toString() {
        return "VrstaStudija{" +
                "idVrstaStudija=" + idVrstaStudija +
                ", punNaziv='" + punNaziv + '\'' +
                ", skraceniNaziv='" + skraceniNaziv + '\'' +
                ", studProgram=" + studProgram +
                '}';
    }
}
