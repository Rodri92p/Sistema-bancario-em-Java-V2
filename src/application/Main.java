package application;
	
import Controllers.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {	
	Controller controle = new Controller();
	@SuppressWarnings("unused")
	@Override
	public void start(Stage stage) {
		try {
			System.setProperty("java.util.logging.config.file", "META-INF/logging.properties");
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Inicio/Scr_Login.fxml"));
	        Scene scene = new Scene(fxmlLoader.load());
	        
	        stage.setScene(scene);
	        stage.setResizable(false);
	        stage.centerOnScreen();
	        stage.show();
	        

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
