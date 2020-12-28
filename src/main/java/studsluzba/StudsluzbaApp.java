package studsluzba;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import studsluzba.client.MainViewManager;

@SpringBootApplication
@EnableJpaRepositories
public class StudsluzbaApp extends Application {

    protected ConfigurableApplicationContext springContext;

    public static void main(String[] args) {
        launch(StudsluzbaApp.class);
    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(StudsluzbaApp.class);
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
        Platform.exit();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("RAF Studentska sluzba");
        MainViewManager mainView = springContext.getBean(MainViewManager.class);
        mainView.setMainStage(primaryStage);
        primaryStage.setScene(mainView.createScene());
        primaryStage.show();
    }

}
