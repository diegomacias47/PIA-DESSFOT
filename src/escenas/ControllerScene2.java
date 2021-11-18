package escenas;

import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;
import java.util.regex.PatternSyntaxException;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import controlador.ItemController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ControllerScene2 implements Initializable
{
	@FXML AnchorPane apPrincipal;
	@FXML Pane pPrincipal, pSeccionHabitaciones, pSeccionFacturas, pSeccionesEmpleados;
	@FXML TabPane pSeccionEmpleado;
	@FXML StackPane spPane;
	@FXML Button btnCerrar;
	Pane panelAnterior;
	
	double xOffSet, yOffSet;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {


	
	}
	
	
	
	public void cerrarAplicacion(ActionEvent e) 	
	{		
		Stage stage = (Stage) apPrincipal.getScene().getWindow();
		stage.close();
		System.exit(0);
	}
	
	public void minimizarAplicacion(ActionEvent e)
	{
		Stage stage = (Stage) apPrincipal.getScene().getWindow();
		stage =  ((Stage)((Button)e.getSource()).getScene().getWindow());
		stage.setIconified(true);
		
	}
	
	
	public void cerrarSesion(ActionEvent e) throws IOException
	{
		Stage stage = (Stage)apPrincipal.getScene().getWindow();		
		stage.close();
	
		
		Parent root = FXMLLoader.load(getClass().getResource("/escenas/Scene1-Login.fxml"));
		Stage stage1 = (Stage)((Node) e.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage1.setScene(scene);
		stage1.centerOnScreen();
		stage1.show();
	
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
				stage1.setX(e.getScreenX() - xOffSet);
				stage1.setY(e.getScreenY() - yOffSet);
			}
			
			});

		
	}


	
	public void cargarCuadro() {
		Alert alert = new Alert(AlertType.NONE);
		alert.initStyle(StageStyle.UNDECORATED);
		alert.setTitle("Information Dialog");
		
		alert.setHeaderText("Look, an Information Dialog");
		alert.setContentText("I have a great message for you!");
		alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
		alert.showAndWait();
	}


	
}
