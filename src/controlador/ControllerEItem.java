package controlador;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Empleado;

public class ControllerEItem implements Initializable
{
    @FXML
    private Label matricula;

    @FXML
    private TextField nombre;

    @FXML
    private Label puesto;
    
    @FXML
    private Button btn;    
    
    @FXML
    private ImageView imagen;
    
    private Empleado emp;
    
    private GridPane gridEmpleado;
    
    private TextField buscador;        
    
    private int matriculaEmpleado;
    
    
    
    private ArrayList<Empleado> empleados = new ArrayList<Empleado>();
    
    private double xOffSet, yOffSet;
    
    public void establecerDatos(Empleado emp)
    {
    	this.emp = emp;
    	matricula.setText("" + emp.getIdEmpleado());
    	nombre.setText("" + emp.getNombreEmpleado());
    	puesto.setText("  " + emp.getPuestoEmpleado());
    	matriculaEmpleado = emp.getIdEmpleado();

    	if(emp.getSexoEmpleado().equals("Masculino"))
    	{
            File file = new File("src/img/man.png");
            Image image = new Image(file.toURI().toString());
    		imagen.setImage(image);
    	}else
    	{
            File file = new File("src/img/mujer.png");
            Image image = new Image(file.toURI().toString());
    		imagen.setImage(image);
    	}
    }
    
    
    public void establecerDatos(GridPane gridEmpleado, TextField buscador)
    {
    	this.gridEmpleado = gridEmpleado;
    	this.buscador = buscador;
    }
    
    
    public void mostrarDetalles()
    {
    	emp.obtenerEmpleados(empleados);
    	FXMLLoader fxml = new FXMLLoader();
    	fxml.setLocation(getClass().getResource("/vista/FXML-E-Detalle.fxml"));
    	try {
			Parent root = fxml.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
			
			ControllerEDetalle c = fxml.getController();
	        for(int i=0; i<empleados.size(); i++)
	        {
	        	if(empleados.get(i).getIdEmpleado() == matriculaEmpleado)
	        	{
	        		c.establecerDetalles(emp);
	        	}
	        } 
			
			
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
    
    
    @FXML
    public void eliminarRegistro()
    {
		Empleado es = new Empleado();		
		//ArrayList<Empleado> el = new ArrayList<Empleado>();
		es.eliminarEmpleado(matriculaEmpleado);
		//es.obtenerEmpleados(el);
		
		try {
			
	
    		FXMLLoader fxml = new FXMLLoader();
    		fxml.setLocation(getClass().getResource("/vista/FXML-Principal.fxml"));
			Parent root = fxml.load();
			ControllerFXML1 c = fxml.getController();
			c.vaciarBuscador(buscador);
			c.mostrarEmpleados(gridEmpleado);
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	
    	
    }
    
    public void modificarEmpleado()
    {
    	emp.obtenerEmpleados(empleados);
       	FXMLLoader fxml = new FXMLLoader();
    	fxml.setLocation(getClass().getResource("/vista/FXML-E-Modificar.fxml"));
    	try {
			Parent root = fxml.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
			
			ControllerEModificar c = fxml.getController();
			c.establecerDetalles(emp, gridEmpleado);
			
	        for(int i=0; i<empleados.size(); i++)
	        {
	        	if(empleados.get(i).getIdEmpleado() == matriculaEmpleado)
	        	{
	        		
	        	}
	        } 
			
			
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
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		
	}
    
    
    
}
