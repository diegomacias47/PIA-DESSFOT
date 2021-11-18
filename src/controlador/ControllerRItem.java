package controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Cliente;
import modelo.DetalleRS;
import modelo.Factura;
import modelo.Habitacion;
import modelo.Reservacion;

public class ControllerRItem implements Initializable
{
    @FXML
    private AnchorPane padre;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Button btnFactura;
    
    @FXML
    private Label label4;
    
    @FXML
    private Button btnDetalles;
    
    private Reservacion res;
    private DetalleRS drs = new DetalleRS();
    
    private GridPane pane;
    
    
    
    private double xOffSet, yOffSet;
    
    private ArrayList<Reservacion> reservacion = new ArrayList<Reservacion>();
    private ArrayList<DetalleRS> detalle = new ArrayList<DetalleRS>();
    
    
    public void establecerDatos(Reservacion res)
    {
    	this.res = res;
    	label1.setText("" + res.getIdReservacion());
    	label2.setText("" + res.getIdClienteReservacion());
    	label3.setText("" + res.getIdHabitacion());
    	label4.setText("" + res.getEstadoReservacion());
    }
    
    public void mostrarDetalles()
    {
    	
    	FXMLLoader fxml = new FXMLLoader();
    	fxml.setLocation(getClass().getResource("/vista/FXML-R-Detalle.fxml"));
    	try {
			Parent root = fxml.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
			
			ControllerRDetalle c = fxml.getController();
			c.establecerDatos(res);
			
			
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
    
    public void habilitarBotonFactura()
    {
    	btnFactura.setVisible(true);
    }
    
    public void establecerPane(GridPane pane)
    {
    	this.pane = pane;
    }
    
    public void desactivarBoton()
    {
    	btnDetalles.setVisible(false);
    }
    
    public void facturar()
    {
    	
    	/*int id = res.getIdReservacion();        	
    	Timestamp fecha = new Timestamp(System.currentTimeMillis());    	
    	double precio = res.getPrecioInicialReservacion();    	
    	double preS=0;
    	detalle = drs.obtenerDetallesRS(id);
    	    	
    	if(detalle.size() == 0)
    	{
    		
    	}
    	else 
    	{
    		 for(int i=0; i<detalle.size(); i++)
    		 {
    			 	preS+=detalle.get(i).getPrecio();
    		 }
    	
    	}

    	precio += preS;
    	
    	new Factura(id, fecha, precio).insertarFactura();
    	res.actualizarEstado(id);
    	new Habitacion().actualizarEstado(res.getIdHabitacion(), 2);
    	
    	try 
		{
    		FXMLLoader fxml = new FXMLLoader();
    		fxml.setLocation(getClass().getResource("/vista/FXML-Secundario.fxml"));
			Parent root = fxml.load();
			ControllerFXML2 c = fxml.getController();
			c.mostrarReservaciones(pane);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}*/
    	
    	FXMLLoader fxml = new FXMLLoader();
    	fxml.setLocation(getClass().getResource("/vista/FXML-FR-Detalle.fxml"));
    	try {
			Parent root = fxml.load();						
			
			ControllerRFDetalle c = fxml.getController();
			c.establecerDatos(res);
			c.establecerGrid(pane);
			
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnFactura.setOnAction(event ->{
			facturar();
		});
		
	}
    
}
