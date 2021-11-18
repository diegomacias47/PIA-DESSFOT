package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Cliente;

public class ControllerCDetalle 
{
    @FXML
    private AnchorPane padre;

    @FXML
    private Label nombre;

    @FXML
    private Label matricula;

    @FXML
    private Label telefono;
    
    private Cliente c;
    
    public void establecerDatos(Cliente c)
    {
    	this.c = c;
    	nombre.setText("" + c.getNombre());
    	matricula.setText("" + c.getIdCliente());
    	telefono.setText("" + c.getCelular());
    }
    
    public void cerrarVentana()
    {
    	Stage s = (Stage)padre.getScene().getWindow();
    	s.close();
    }
    
}
