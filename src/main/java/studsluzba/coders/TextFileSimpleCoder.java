package studsluzba.coders;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextFileSimpleCoder extends Coder<SimpleCode> {

    private String filePath;

    public TextFileSimpleCoder(String filePath) {
        this.filePath = filePath;
        loadCodes();
    }

    @Override
    protected void loadCodes() {
        if (codes != null) return;
        codes = new ArrayList<SimpleCode>();
        Resource resource = new ClassPathResource(filePath);
        Scanner scanner = null;
        try {
            File finput = resource.getFile();
            scanner = new Scanner(finput, "utf-8");
            while (scanner.hasNext()) {
                String code = scanner.nextLine();
                codes.add(new SimpleCode(code));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }

    }

}
