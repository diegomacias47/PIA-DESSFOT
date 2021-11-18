package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modelo.Empleado;
import modelo.Puesto;

public class ControllerEAgregar implements Initializable
{
    @FXML
    private AnchorPane padre;

    @FXML
    private TextField telefono, direccion, edad, matricula, nombre;

    @FXML
    private ComboBox<Puesto> puesto;
    
    @FXML
    private ComboBox<String> sexo;
    
    private GridPane gridEmpleado;
    
    private Empleado e1;
    
    private Puesto p1 = new Puesto(); 
    
    private ObservableList<Puesto> obsP = p1.obtenerPuesto();
    
    public void cerrarVentana()
    {
		Stage stage = (Stage)padre.getScene().getWindow();		
		stage.close();
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Parent root = padre;
		
		Platform.runLater( () -> root.requestFocus() );
		
		this.puesto.setItems(obsP);
		this.sexo.setItems(FXCollections.observableArrayList(
        "Masculino",
        "Femenino"       
		));
		
	}
	
	
	public void agregarEmpleado()
	{
		
		if(nombre.getText().equals(null) || edad.getText().equals(null) || sexo.getValue() == null|| direccion.getText().equals(null) || telefono.getText().equals(null) || puesto.getValue()==null)
		{
			System.out.println("No se pudo");
		}else
		{				
			String nom = nombre.getText();
			String años = edad.getText();
			String sex = sexo.getValue();
			String tel = telefono.getText();
			String dir = direccion.getText();
			String pues = puesto.getValue().toString();
			int idpues = obtenerIdPuesto(pues);
			
			Empleado em = new Empleado(nom, años,sex, dir, tel, idpues);
			em.registrarEmpleado();
			
			nombre.setText(null);
			edad.setText(null);
			direccion.setText(null);
			telefono.setText(null);
			puesto.getSelectionModel().select(null);
			sexo.getSelectionModel().select(null);				
								    	
			try {
	    		FXMLLoader fxml = new FXMLLoader();
	    		fxml.setLocation(getClass().getResource("/vista/FXML-Principal.fxml"));
				Parent root = fxml.load();
				ControllerFXML1 c = fxml.getController();
				c.mostrarEmpleados(gridEmpleado);
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			cerrarVentana();
		}

		
		
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
    
    public void establecerDatos(GridPane gridEmpleado)
    {
    	this.gridEmpleado = gridEmpleado;
   
    }
	
	
}
