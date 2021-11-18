package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modelo.Empleado;
import modelo.Estado;
import modelo.Habitacion;
import modelo.Modelo;

public class ControllerHModificar implements Initializable
{

    @FXML
    private AnchorPane padre;

    @FXML
    private TextField precio;

    @FXML
    private TextField camas;

    @FXML
    private TextField matricula;

    @FXML
    private TextField capacidad;

    @FXML
    private ComboBox<Modelo> modelo;

    @FXML
    private ComboBox<Estado> estado;
    
    private GridPane grid;
    
    private Habitacion hab;
    private Modelo m1 = new Modelo();
    private Estado e1 = new Estado();
    
    private ObservableList<Modelo> listM = m1.traerModelos();
    private ObservableList<Estado> listE = e1.obtenerEstados();

    public void establecerDetalles(Habitacion hab)
    {
    	this.hab = hab;
    	matricula.setText("" + hab.getIdHabitacion());
    	camas.setText("" + hab.getCamasHabitacion());
    	capacidad.setText("" + hab.getCapacidadHabitacion());
    	precio.setText("" + hab.getPrecioHabitacion());
		for(int i=0; i<listM.size(); i++) {
			
			if(listM.get(i).getDescripcionModelo().equals(hab.getTipoHabitacion())) {
				
				this.modelo.getSelectionModel().select(i);
			}
		}
    	
		for(int i=0; i<listE.size(); i++) {
			
			if(listE.get(i).getEstadoDes().equals(hab.getEstatusHabitacion())) {
				
				this.estado.getSelectionModel().select(i);
			}
		}
    	
    }
    
    
    public void modificarDatos()
    {
		int cama = Integer.parseInt(camas.getText());
		int personas = Integer.parseInt(capacidad.getText());
		double pre = Double.parseDouble(precio.getText());
		String mod = modelo.getValue().toString();
		String est = estado.getValue().toString();
		
		int idMod = obtenerIdModelo(mod);
		int idEst = obtenerIdEstado(est);
		
		new Habitacion().modificarHabitacion(hab.getIdHabitacion(), cama, personas, pre, idEst, idMod);
		System.out.println("Se hizo");
		try {
    		FXMLLoader fxml = new FXMLLoader();
    		fxml.setLocation(getClass().getResource("/vista/FXML-Principal.fxml"));
			Parent root = fxml.load();
			ControllerFXML1 c = fxml.getController();
			c.mostrarHabitaciones(grid);;
			System.out.println("Bien");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		cerrarVentana();
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
				System.out.println(id +  " Modelo");
				
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
				System.out.println(id +  " Estado");
				
			}
		}
		return id;
	}
    
    public void cerrarVentana()
    {
    	Stage s = (Stage) padre.getScene().getWindow();
    	s.close();
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		modelo.setItems(listM);
		estado.setItems(listE);
		
	}
}
