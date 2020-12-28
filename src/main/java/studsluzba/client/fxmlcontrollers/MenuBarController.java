package studsluzba.client.fxmlcontrollers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import studsluzba.client.MainViewManager;

@Component
public class MenuBarController {

    @Autowired
    MainViewManager mainViewManager;

    @FXML
    private MenuBar menuBar;

    public void findStudent(ActionEvent event) {
        mainViewManager.changeRoot("findStudent");
    }

    public void openStudProgrami(ActionEvent event) {
        mainViewManager.changeRoot("studProgrami");
    }

    public void openNewStudent(ActionEvent event) {
        mainViewManager.changeRoot("newStudent");
    }

    public void newSlusaPredmet(ActionEvent event) {
        mainViewManager.changeRoot("newSlusaPredmet");
    }

    public void newIspit(ActionEvent event) {
        mainViewManager.changeRoot("newIspit");
    }

    public void novaSkolska(ActionEvent event) {
        mainViewManager.changeRoot("novaSkolska");
    }

    public void findNastavnik(ActionEvent event) {
        mainViewManager.changeRoot("findNastavnik");
    }

    public void findIspit(ActionEvent event) {
        mainViewManager.changeRoot("findIspit");
    }

    public void findStudProgram(ActionEvent event) {
        mainViewManager.changeRoot("findStudProgram");
    }

    public void openSearchStudent(ActionEvent event) {
        mainViewManager.changeRoot("searchStudent");
    }

    public void openImport(ActionEvent event) {
        mainViewManager.changeRoot("importData");
    }

    public void openSpiskoviStudenataIzvestaj(ActionEvent ecent) {
        mainViewManager.changeRoot("spiskoviStudenataIzvestaji");
    }

}
