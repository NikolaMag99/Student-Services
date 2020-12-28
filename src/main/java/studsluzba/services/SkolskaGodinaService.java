package studsluzba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studsluzba.model.SkolskaGodina;
import studsluzba.repositories.SkolskaGodinaRepository;

import java.util.List;


@Service
public class SkolskaGodinaService {

    @Autowired
    SkolskaGodinaRepository skolskaGodinaRepository;

    public void updateSkolskeGodine() {
        List<SkolskaGodina> skolskaGodinas = (List<SkolskaGodina>) skolskaGodinaRepository.findAll();
        for (SkolskaGodina sk : skolskaGodinas) {
            if (sk.isAktivna()) {
                sk.setAktivna(false);
                skolskaGodinaRepository.save(sk);
            }
        }
    }

    public SkolskaGodina getSkolskaGodina(String skGodina) {

        String[] split = skGodina.split("/");
        int godina = Integer.parseInt(split[0]);
        System.out.println(godina);

        SkolskaGodina skolskaGodina = skolskaGodinaRepository.findByDatum(godina);

        if (skolskaGodina == null) {
            skolskaGodina = new SkolskaGodina();
            skolskaGodina.setDatum(godina);
            skolskaGodina.setAktivna(true);
        }

        System.out.println(skolskaGodina);

        return skolskaGodinaRepository.save(skolskaGodina);

    }

}
