package escenas;

import java.io.IOException;
import java.math.BigDecimal;

import com.jfoenix.controls.JFXProgressBar;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import sql.MySQLConnector;

public class ControllerScene1
{

	
	@FXML AnchorPane panelPrincipal;
	@FXML JFXProgressBar barraProgreso;
	@FXML Button iniciarSesionBTN;

    @FXML
    private TextField mat;
    

    @FXML
    private PasswordField contra;

	
	MySQLConnector sql = new MySQLConnector();
	BigDecimal progreso = new BigDecimal(String.format("%.2f", 0.0));
	
	boolean termino=false;
	double xOffSet, yOffSet;
	
	
    
	
	public void cerrarAplicacion(ActionEvent e) 	
	{		
		Stage stage = (Stage) panelPrincipal.getScene().getWindow();
		stage.close();
		System.exit(0);
	}
	
	public void minimizarAplicacion(ActionEvent e)
	{
		Stage stage = (Stage) panelPrincipal.getScene().getWindow();
		stage =  ((Stage)((Button)e.getSource()).getScene().getWindow());
		stage.setIconified(true);
		
	}
	
	public void inicioSesion(ActionEvent e) throws IOException 
	{		
		barraProgreso.setVisible(true);
		new SleepService().start();
			
	}
	
	public void hacerTransicion(boolean reloj)
	{

		if(reloj == true) {
		FadeTransition fade = new FadeTransition();
		fade.setDuration(Duration.millis(1000));
		fade.setNode(panelPrincipal);
		fade.setFromValue(1);
		fade.setToValue(0);
		fade.play();
		
		fade.setOnFinished((ActionEvent e)->{
			segundaVentana();
		});
		
		}


		
	}
	
	private void segundaVentana()
	{
		
		
		if(mat.getText().equals("Admin") && contra.getText().equals("123"))
		{
			try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/vista/FXML-Principal.fxml"));
			Scene scene = new Scene(root);
			Stage stage1 = (Stage) panelPrincipal.getScene().getWindow();
			
			stage1.setTitle("Administrador");
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
		
		
		
		
		
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
			
		}else
		{
			try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/vista/FXML-Secundario.fxml"));
			Scene scene = new Scene(root);
			Stage stage1 = (Stage) panelPrincipal.getScene().getWindow();
			
			stage1.setTitle("Administrador");
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
		
		
		
		
		
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		}
		
		

	}
	
	

	
	private  class  SleepService extends Service<Boolean>
	{
		private SleepService() {
			setOnSucceeded(new EventHandler<WorkerStateEvent>() {

				@Override
				public void handle(WorkerStateEvent arg0) {
					segundaVentana();//hacerTransicion((boolean) arg0.getSource().getValue());
					
				}
				
			});
		}
		
		@Override
		protected   Task<Boolean> createTask() {
			// TODO Auto-generated method stub
			return new Task<Boolean>() {

				@Override
				protected Boolean call() throws Exception {
					Thread.sleep(2000);					
					return true;
				}
				
			};
		}
		
	}
	
	
	
	
	

	
}



