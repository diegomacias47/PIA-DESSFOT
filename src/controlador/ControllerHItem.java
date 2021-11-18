package controlador;

import java.io.IOException;
import java.util.ArrayList;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Habitacion;

public class ControllerHItem
{

    @FXML
    private AnchorPane padre;

    @FXML
    private Label estatus;

    @FXML
    private Label precio;

    @FXML
    private Label habitacion;
    
    @FXML
    private FontAwesomeIconView tuerca;
    
    @FXML
    private Label modelo;
    
    
    private GridPane gridHabitaciones;
    
    private Habitacion hab;   
    private ArrayList<Habitacion> h = new ArrayList<Habitacion>();
    double xOffSet, yOffSet;
    
    
    public void establecerDatos(Habitacion hab)
    {
    	this.hab = hab;
    	precio.setText("" + hab.getPrecioHabitacion());
    	habitacion.setText("" + hab.getIdHabitacion());
    	estatus.setText("" + hab.getEstatusHabitacion());
    	modelo.setText("" + hab.getTipoHabitacion());
    }
    
    public void mostrarDetalles()
    {
    	FXMLLoader fxml = new FXMLLoader();
    	fxml.setLocation(getClass().getResource("/vista/FXML-H-Detalle.fxml"));
    	try {
			Parent root = fxml.load();
			
			ControllerHDetalle c = fxml.getController();
			c.establecerDatos(hab);
			
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
			
			root.setOnMousePressed(event1 ->{
					xOffSet = event1.getSceneX();
					yOffSet = event1.getSceneY();
			});
			
			root.setOnMouseDragged(event1 ->{
					stage.setX(event1.getScreenX() - xOffSet);
					stage.setY(event1.getScreenY() - yOffSet);
			});
 			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }
    
    public void habilitarTuerca()
    {
    	tuerca.setVisible(true);
    	tuerca.setOnMouseClicked(event->{
    		modificarDatos();
    	});
    }
    
    public void modificarDatos()
    {
    	
    	
    	System.out.println("Hola");
       	FXMLLoader fxml = new FXMLLoader();
    	fxml.setLocation(getClass().getResource("/vista/FXML-H-Modificar.fxml"));
    	try {
			Parent root = fxml.load();
			
			ControllerHModificar c = fxml.getController();
			c.establecerDetalles(hab);
			c.establecerGrid(gridHabitaciones);
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
			
			

			
			
			root.setOnMousePressed(event ->{
					xOffSet = event.getSceneX();
					yOffSet = event.getSceneY();
			});
			
			root.setOnMouseDragged(event ->{
					stage.setX(event.getScreenX() - xOffSet);
					stage.setY(event.getScreenY() - yOffSet);
			});
 			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }
    
    public void establecerGrid(GridPane pane)
    {
    	gridHabitaciones = pane;
    }
    
}
