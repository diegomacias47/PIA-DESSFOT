package controlador;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AgregarResController
{

    @FXML
    private ComboBox<?> habitacion;

    @FXML
    private TextField nombre;

    @FXML
    private TextField celular;

    @FXML
    private TextField dias;

    @FXML
    private FontAwesomeIconView infomracion;

    @FXML
    private Button agregar;
    
    
    
    public void cerrarVentana(ActionEvent e)
    {
    	Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();	
		stage.close();
		
		
    }
}
