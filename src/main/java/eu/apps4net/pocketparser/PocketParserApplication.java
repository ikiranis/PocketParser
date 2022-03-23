package eu.apps4net.pocketparser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PocketParserApplication extends Application {

    public static void main(String[] args) {
//        SpringApplication.run(PocketParserApplication.class, args);

        Application.launch(args);
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
