package eu.apps4net.pocketparser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Created by Yiannis Kiranis <yiannis.kiranis@gmail.com>
 * https://apps4net.eu
 * Date: 23/3/22
 * Time: 10:08 μ.μ.
 */

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/Main.fxml"));

        Scene scene = new Scene(root, 1217, 630);

        primaryStage.setTitle("Pocket Parser");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
