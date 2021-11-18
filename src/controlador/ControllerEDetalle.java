package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Empleado;

public class ControllerEDetalle 
{


    @FXML
    private AnchorPane padre;

    @FXML
    private TextField direccion;

    @FXML
    private Label nombre;

    @FXML
    private Label matricula;

    @FXML
    private Label edad;

    @FXML
    private Label sexo;

    @FXML
    private Label telefono;

    @FXML
    private Label puesto;
	
    private Empleado emp;
    
    public void establecerDetalles(Empleado emp)
    {
    	this.emp = emp;
    	matricula.setText("" + emp.getIdEmpleado());
    	nombre.setText("" + emp.getNombreEmpleado());
    	edad.setText("" + emp.getEdadEmpleado());
    	sexo.setText("" + emp.getSexoEmpleado());
    	direccion.setText("" + emp.getDireccionEmpleado());
    	telefono.setText("" + emp.getTelefonoEmpleado());
    	puesto.setText("" + emp.getPuestoEmpleado());
    	
    }
    
    
	public void cerrarVentana()
	{
		Stage stage = (Stage)padre.getScene().getWindow();		
		stage.close();			
		
	}
	
	
	
	
	
}
