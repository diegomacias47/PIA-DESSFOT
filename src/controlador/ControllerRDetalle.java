package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Cliente;
import modelo.Habitacion;
import modelo.Reservacion;

public class ControllerRDetalle implements Initializable
{
	
    @FXML
    private AnchorPane padre;

    @FXML
    private TextField fechaFinal;

    @FXML
    private Label idHab;

    @FXML
    private Label idRes;

    @FXML
    private Label idCli;

    @FXML
    private Label dias;

    @FXML
    private Label huespedes;

    @FXML
    private TextField fechaInicial;

    @FXML
    private Label precio;

    @FXML
    private Label servicios;
    
    @FXML
    private FontAwesomeIconView ser;

    @FXML
    private FontAwesomeIconView hab;
    
    @FXML
    private FontAwesomeIconView cli;
	
    private Reservacion res;
    private Habitacion habitacion;
    private Cliente c = new Cliente();
    
    private int idC=0;
    
    
    private ArrayList<Habitacion> listaH = new ArrayList<Habitacion>();
    
    private double xOffSet, yOffSet;
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		ser.setOnMouseClicked(event->{
			FXMLLoader fxml = new FXMLLoader();
	    	fxml.setLocation(getClass().getResource("/vista/FXML-RS-Agregar.fxml"));
	    	try {
				Parent root = fxml.load();
				
				
				
				ControllerRSAgregar c = fxml.getController();
				c.establecerDatos(res);
				
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
		});
		
		hab.setOnMouseClicked(event->{
			FXMLLoader fxml = new FXMLLoader();
	    	fxml.setLocation(getClass().getResource("/vista/FXML-H-Detalle.fxml"));
	    	try {
				Parent root = fxml.load();
				
				
				
				ControllerHDetalle c = fxml.getController();
				c.establecerDatos(habitacion);
				
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
		});
		
		cli.setOnMouseClicked(event->{
			FXMLLoader fxml = new FXMLLoader();
	    	fxml.setLocation(getClass().getResource("/vista/FXML-C-Detalle.fxml"));
	    	try {
				Parent root = fxml.load();
				
				
				
				ControllerCDetalle c = fxml.getController();
				c.establecerDatos(new Cliente().obtenerCliente(idC));
				
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
		});
		

		
		
		setOnMouseEnteredVerde(cli);
		setOnMouseEnteredVerde(hab);
		setOnMouseEnteredVerde(ser);
		
		
	}
	
	public void setOnMouseEnteredVerde(FontAwesomeIconView font)
	{
		font.setOnMouseEntered(event ->{
			font.setGlyphStyle("-fx-fill: green");
		});
		
		font.setOnMouseExited(event ->{
			font.setGlyphStyle("-fx-fill: black");
		});
	}
	
	public void establecerDatos(Reservacion res)
	{
		this.res = res;
		idRes.setText("" + res.getIdReservacion());
		idHab.setText("" + res.getIdHabitacion());
		idCli.setText("" + res.getIdClienteReservacion());
		dias.setText("" + res.getDiasReservacion());
		huespedes.setText("" + res.getCantidadPersonas());
		fechaInicial.setText("" + res.getFechaReservacion());
		fechaFinal.setText("" + res.getFechaExpiracion());
		precio.setText("" + res.getPrecioInicialReservacion());
		idC = res.getIdClienteReservacion();
		
		encontrarDatosHabitacion();
		
		
	}
	
	public void encontrarDatosHabitacion()
	{
		new Habitacion().obtenerHabitacion(listaH);
		
		for(int i=0; i<listaH.size(); i++)
		{
			if(listaH.get(i).getIdHabitacion() == res.getIdHabitacion())
			{
				habitacion = listaH.get(i);
			}
		}
		
	}
	
	
	
	public void cerrarVentana()
	{
		Stage stage = (Stage) padre.getScene().getWindow();
		stage.close();
	}
	

}
