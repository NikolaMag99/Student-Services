package studsluzba.client.fxmlcontrollers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import studsluzba.model.StudProgram;
import studsluzba.services.StudProgramService;

@Component
public class StudProgramController {

    @Autowired
    StudProgramService studProgramService;

    @FXML
    private TextField nazivTf;

    @FXML
    private TextField skraceniNazivTf;

    private ObservableList<StudProgram> sviProgrami;

    @FXML
    private TableView<StudProgram> programiTable;

    @FXML
    protected void initialize() {
        sviProgrami = FXCollections.observableList(studProgramService.loadAll());
        programiTable.setItems(sviProgrami);
    }

    public void handleSaveStudProgram(ActionEvent event) {
        StudProgram sp = studProgramService.saveStudProgram(nazivTf.getText(), skraceniNazivTf.getText());
        sviProgrami.add(sp);
    }
}
