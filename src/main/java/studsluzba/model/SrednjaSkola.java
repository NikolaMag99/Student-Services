package studsluzba.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "srednjaSkola")
public class SrednjaSkola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSrednjaSkola;

    private String naziv;
    private String mesto;
    private String vrsta;

    @OneToMany(mappedBy = "srednjaSkola", fetch = FetchType.EAGER)
    private List<Student> studenti;

    public SrednjaSkola() {

    }

    public SrednjaSkola(String naziv, String mesto, String vrsta) {
        this.naziv = naziv;
        this.mesto = mesto;
        this.vrsta = vrsta;
    }

    public void addStudent(Student student) {
        if (studenti == null) {
            studenti = new ArrayList<>();
        }
        studenti.add(student);
        student.setSrednjaSkola(this);
    }

    @Override
    public String toString() {
        return naziv + ", " + mesto + "," + vrsta;
    }

}
