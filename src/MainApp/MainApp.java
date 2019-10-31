package MainApp;

import java.beans.EventHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Huilton
 * @version 1.1
 */
public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setOnCloseRequest(new javafx.event.EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });

        Parent root = FXMLLoader.load(getClass().getResource("/view/HomeEdit.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Home");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/iconeFaQi.png")));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
