package studsluzba.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "slusaPredmet")
public class SlusaPredmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSlusaPredmet;

    @ManyToOne()
    @JoinColumn(name = "idStudent")
    private StudIndex index;

    @ManyToOne()
    @JoinColumn(name = "idDrziPredmet")
    private DrziPredmet drziPredmet;

    public SlusaPredmet() {

    }

    public SlusaPredmet(StudIndex index, DrziPredmet drziPredmet) {
        this.index = index;
        this.drziPredmet = drziPredmet;
    }

    @Override
    public String toString() {
        return this.getDrziPredmet().getPredmet().getNazivPredmeta();
    }
}
