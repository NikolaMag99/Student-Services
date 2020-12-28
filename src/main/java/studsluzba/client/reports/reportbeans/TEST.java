package studsluzba.client.reports.reportbeans;

public class TEST {

     String ime;
     String prezime;
     String studIndex;
     double brojPoena;
     int ocena;

     public TEST(){

     }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getStudIndex() {
        return studIndex;
    }

    public double getBrojPoena() {
        return brojPoena;
    }

    public int getOcena() {
        return ocena;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setBrojPoena(double brojPoena) {
        this.brojPoena = brojPoena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public void setStudIndex(String studIndex) {
        this.studIndex = studIndex;
    }
}
