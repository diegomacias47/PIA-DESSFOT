package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Habitacion;

public class ControllerHDetalle implements Initializable
{
	  	@FXML
	    private AnchorPane padre;

	    @FXML
	    private Label camas;

	    @FXML
	    private Label id;

	    @FXML
	    private Label capacidad;

	    @FXML
	    private Label precio;

	    @FXML
	    private Label modelo;

	    @FXML
	    private Label estado;

	    private Habitacion hab;
	    
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) 
		{
			
			
		}
		
		
		public void establecerDatos(Habitacion hab)
		{
			this.hab = hab;
			id.setText("" + hab.getIdHabitacion());
			camas.setText("" + hab.getCamasHabitacion());
			precio.setText("" + hab.getPrecioHabitacion());
			estado.setText("" + hab.getEstatusHabitacion());
			modelo.setText("" + hab.getTipoHabitacion());
			capacidad.setText("" + hab.getCapacidadHabitacion());
			
		}
		
		public void cerrarVentana()
		{
			Stage s = (Stage) padre.getScene().getWindow();
			s.close();
		}

}
