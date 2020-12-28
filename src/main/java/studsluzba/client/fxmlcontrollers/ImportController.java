package studsluzba.client.fxmlcontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import studsluzba.client.MainViewManager;
import studsluzba.client.importer.CSVPoeniImporter;

import java.io.File;

@Component
public class ImportController {

    @FXML
    Label nazivFajlaLabel;

    @FXML
    Label izvestajImportLabel;

    @Autowired
    MainViewManager mainViewManager;

    @Autowired
    CSVPoeniImporter csvPoeniImporter;

    private File fajl;

    public void izaberiFajl(ActionEvent ae) {
        fajl = mainViewManager.openFileChooser();
        nazivFajlaLabel.setText(fajl.getName());
    }

    public void zapocniImport(ActionEvent ae) {
        String poruka = csvPoeniImporter.importCSV(fajl);
        izvestajImportLabel.setText(poruka);
    }

}
