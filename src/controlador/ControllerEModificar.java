package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modelo.Empleado;
import modelo.Puesto;

public class ControllerEModificar implements Initializable
{

    @FXML
    private AnchorPane padre;

    @FXML
    private TextField direccion;

    @FXML
    private TextField edad;

    @FXML
    private TextField matricula;

    @FXML
    private TextField nombre;

    @FXML
    private TextField telefono;

    @FXML
    private ComboBox<String> sexo;

    @FXML
    private ComboBox<Puesto> puesto;
    
    private GridPane gridEmpleado;
    
    private Puesto p1 = new Puesto(); 
    
    private Empleado emp;
    
    private int idEmpleado;
    
    private ObservableList<Puesto> obsP = p1.obtenerPuesto();
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		this.sexo.setItems(FXCollections.observableArrayList(
		        "Masculino",
		        "Femenino"       
		));
		this.puesto.setItems(obsP);
		
	}
    
    public void establecerDetalles(Empleado emp, GridPane gridEmpleado)
    {
    	this.emp = emp;
    	idEmpleado = emp.getIdEmpleado();
    	//matricula.setText("" + emp.getIdEmpleado());
    	nombre.setText("" + emp.getNombreEmpleado());
    	edad.setText("" + emp.getEdadEmpleado());
    	sexo.getSelectionModel().select(emp.getSexoEmpleado());
    	direccion.setText("" + emp.getDireccionEmpleado());
    	telefono.setText("" + emp.getTelefonoEmpleado());
		for(int i=0; i<obsP.size(); i++) {
			
			if(obsP.get(i).getDescripcionPuesto().equals(emp.getPuestoEmpleado())) {
				
				this.puesto.getSelectionModel().select(i);
			}
		}
    	this.gridEmpleado = gridEmpleado;
		
    }
    
    public void cerrarVentana()
    {
		Stage stage = (Stage)padre.getScene().getWindow();		
		stage.close();
    }

	public int obtenerIdPuesto(String valor)
	{
		int id = 0;
		for(int i=0; i<obsP.size(); i++) {
			if(obsP.get(i).getDescripcionPuesto().equals(valor)) {
				id = obsP.get(i).getIdPuesto();
				
			}
		}
		return id;
	}
	
	public void modificarDatos()
	{
		String nom = nombre.getText();
		String años = edad.getText();
		String sex = sexo.getSelectionModel().getSelectedItem();
		String dir = direccion.getText();
		String tel = telefono.getText();
		String pues = puesto.getValue().toString();
		int idP = obtenerIdPuesto(pues);
		
		emp.modificarEmpleados(new Empleado(idEmpleado,nom, años, sex, dir, tel, idP));
		
		try {
    		FXMLLoader fxml = new FXMLLoader();
    		fxml.setLocation(getClass().getResource("/vista/FXML-Principal.fxml"));
			Parent root = fxml.load();
			ControllerFXML1 c = fxml.getController();
			c.mostrarEmpleados(gridEmpleado);;
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		cerrarVentana();
	}

    
}
