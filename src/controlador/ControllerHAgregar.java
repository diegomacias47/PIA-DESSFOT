package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modelo.Estado;
import modelo.Habitacion;
import modelo.Modelo;

public class ControllerHAgregar implements Initializable
{

    @FXML
    private AnchorPane padre;

    @FXML
    private TextField capacidad;

    @FXML
    private TextField camas;

    @FXML
    private ComboBox<Modelo> modelo;

    @FXML
    private TextField precio;

    @FXML
    private ComboBox<Estado> estado;
    
    private GridPane grid;
    
    private Modelo m1 = new Modelo();
    private Estado e1 = new Estado();
    
    private ObservableList<Modelo> listM = m1.traerModelos();
    private ObservableList<Estado> listE = e1.obtenerEstados();
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		modelo.setItems(listM);
		estado.setItems(listE);
		
	}
	
	public void agregarHabitacion()
	{
		int cama = Integer.parseInt(camas.getText());
		int personas = Integer.parseInt(capacidad.getText());
		double pre = Double.parseDouble(precio.getText());
		String mod = modelo.getValue().toString();
		String est = estado.getValue().toString();
		
		int idMod = obtenerIdModelo(mod);
		int idEst = obtenerIdEstado(est);
		
		new Habitacion(cama, personas, pre, idEst, idMod).crearHabitacion();
		
		camas.setText(null);
		capacidad.setText(null);
		precio.setText(null);
		modelo.setValue(null);
		estado.setValue(null);
		
		
		try 
		{
    		FXMLLoader fxml = new FXMLLoader();
    		fxml.setLocation(getClass().getResource("/vista/FXML-Principal.fxml"));
			Parent root = fxml.load();
			ControllerFXML1 c = fxml.getController();
			c.mostrarHabitaciones(grid);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		cerrarVentana();
	}
    
    
    public void cerrarVentana()
    {
    	Stage stage = (Stage)padre.getScene().getWindow();		
    	stage.close();
        
    }
    
    
    public void establecerGrid(GridPane grid)
    {
    	this.grid = grid;
    }

	public int obtenerIdModelo(String valor)
	{
		int id = 0;
		for(int i=0; i<listM.size(); i++) {
			if(listM.get(i).getDescripcionModelo().equals(valor)) {
				id = listM.get(i).getIdModelo();
				
			}
		}
		return id;
	}
	
	public int obtenerIdEstado(String valor)
	{
		int id = 0;
		for(int i=0; i<listE.size(); i++) {
			if(listE.get(i).getEstadoDes().equals(valor)) {
				id = listE.get(i).getIdEstado();
				
			}
		}
		return id;
	}

    
}
