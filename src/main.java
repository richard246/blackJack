import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Controller.fxml"));
            Scene scene = new Scene(root, 700, 500);
            stage.setTitle("BlackJack");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch();



    }
}
