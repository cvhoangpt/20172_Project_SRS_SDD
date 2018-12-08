package library.management.ui.main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.management.database.DataAccessHelper;
import library.management.util.Util;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/library/management/ui/login/login.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setTitle(" Login");

        
        
        new Thread(() -> {
            DataAccessHelper.getInstance();
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
