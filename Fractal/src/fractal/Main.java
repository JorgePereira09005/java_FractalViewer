

package fractal;


import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(Main.class, (java.lang.String[])null);
    }

    @Override
    public void start(Stage stage) {
        try {            
            Parent root = FXMLLoader.load(getClass().getResource("FracInterface.fxml"));
            Scene scene = new Scene(root, 850, 800);
            stage.setTitle("Fractal Viewer");
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}