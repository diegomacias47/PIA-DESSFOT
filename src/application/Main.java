package application;
	
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.octicons.OctIcon;
import de.jensd.fx.glyphs.weathericons.WeatherIcon;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
public class Main extends Application {
	
	private double xOffSet = 0;
	private double yOffSet = 0;
	
	@Override
	public void start(Stage primaryStage) {
			
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/escenas/Scene1-Login.fxml"));  /*/vista/Scene3-PanelReserva.fxml*/ //"/vista/FXML-Principal.fxml"
			Scene scene = new Scene(root);			
			Image icon = new Image("/img/hotel.png");
			primaryStage.setScene(scene);
			primaryStage.setTitle("Iniciar Sesion");
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.getIcons().add(icon);			
			primaryStage.setResizable(true);				
			primaryStage.centerOnScreen();
	        primaryStage.show();
	
	        

			
			root.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					xOffSet = event.getSceneX();
					yOffSet = event.getSceneY();
					
				}
			});
			
			root.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					primaryStage.setX(e.getScreenX() - xOffSet);
					primaryStage.setY(e.getScreenY() - yOffSet);
				
				}
				
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
