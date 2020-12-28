package studsluzba.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "visokaSkola")
public class VisokaSkola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVisokaSkola;

    private String naziv;
    private String mesto;
    private String vrsta;

    @OneToMany(mappedBy = "visokaSkola")
    private List<Student> studentii;

    public VisokaSkola() {
    }

    public VisokaSkola(String naziv, String mesto, String vrsta) {
        this.naziv = naziv;
        this.mesto = mesto;
        this.vrsta = vrsta;
    }

    @Override
    public String toString() {
        return "VisokaSkola{" +
                "idVisokaSkola=" + idVisokaSkola +
                ", naziv='" + naziv + '\'' +
                ", mesto='" + mesto + '\'' +
                ", vrsta='" + vrsta + '\'' +
                '}';
    }
}
