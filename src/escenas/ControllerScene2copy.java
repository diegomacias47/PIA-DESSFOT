package escenas;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.org.apache.bcel.internal.generic.FDIV;

import controlador.ItemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Empleado;
import modelo.ModeloTabla;
import modelo.Puesto;

public class ControllerScene2copy implements Initializable
{
	@FXML Pane panePrincipal;
	@FXML TabPane paneEmpleados;
	@FXML AnchorPane anchorPanePrincipal;
	@FXML Label labelPrincipal;
	@FXML TextField fMatricula, fNombre, fEdad, fDomicilio, fTelefono, fBuscador, fMatriculaBuscador, fMatriculaMod, fNombreMod, fEdadMod, fDomicilioMod, fTelefonoMod;
	@FXML RadioButton rbSexo1, rbSexo2, rbSexo1Mod, rbSexo2Mod;
	@FXML ComboBox<Puesto> cbPuesto, cbPuestoMod;
	
	@FXML Button mostrarDatos, cancelarDatos, btnActualizarDatos, btnDarAlta, btnBorrarRegistro;
	@FXML ToggleGroup sexo1;
	@FXML private TableView<ModeloTabla> tableVista;
	@FXML private TableColumn<ModeloTabla, String> tableMatricula;
	@FXML private TableColumn<ModeloTabla, String>tableNombre;
	@FXML private TableColumn<ModeloTabla, String>tableEdad;
	@FXML private TableColumn<ModeloTabla, String>tableSexo;
	@FXML private TableColumn<ModeloTabla, String>tableTelefono;
	@FXML private TableColumn<ModeloTabla, String>tablePuesto;
	
	ModeloTabla mp = new ModeloTabla();
	Puesto p = new Puesto();
	Empleado e = new Empleado();
	ObservableList<Puesto> obsP = p.obtenerPuesto();
	ObservableList<ModeloTabla> obsL = FXCollections.observableArrayList();
	double xOffSet, yOffSet;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{		
		paneEmpleados.setVisible(false);
		panePrincipal.setVisible(true);		
		this.cbPuesto.setItems(obsP);		
		this.cbPuestoMod.setItems(obsP);
		
		btnBorrarRegistro.setDisable(true);
		btnActualizarDatos.setDisable(true);
		
		iniciarTablaEmpleados();
		filtrarBusqueda();
		
		
		
	}
	
	public void iniciarTablaEmpleados()
	{
		
		obsL = mp.traerEmpleados();
		tableMatricula.setCellValueFactory(new PropertyValueFactory<>("matriculas"));
		tableNombre.setCellValueFactory(new PropertyValueFactory<>("nombres"));
		tableEdad.setCellValueFactory(new PropertyValueFactory<>("edads"));
		tableSexo.setCellValueFactory(new PropertyValueFactory<>("sexos"));
		tableTelefono.setCellValueFactory(new PropertyValueFactory<>("telefonos"));
		tablePuesto.setCellValueFactory(new PropertyValueFactory<>("puestos"));
		tableVista.setItems(obsL);
	}
	
	public void filtrarBusqueda()
	{
		
		
		tableMatricula.setCellValueFactory(new PropertyValueFactory<>("matriculas"));
		tableNombre.setCellValueFactory(new PropertyValueFactory<>("nombres"));
		tableEdad.setCellValueFactory(new PropertyValueFactory<>("edads"));
		tableSexo.setCellValueFactory(new PropertyValueFactory<>("sexos"));
		tableTelefono.setCellValueFactory(new PropertyValueFactory<>("telefonos"));
		tablePuesto.setCellValueFactory(new PropertyValueFactory<>("puestos"));
		tableVista.setItems(obsL);
		
		FilteredList<ModeloTabla> listaFiltrada = new FilteredList<>(obsL, b -> true);
		fBuscador.textProperty().addListener((observable, oldValue, newValue) ->{
			listaFiltrada.setPredicate(person ->{
				if(newValue == null || newValue.isEmpty())
				{
					return true;
				}
				String filtroLoweCase = newValue.toLowerCase();
				if(person.getMatriculas().toLowerCase().indexOf(filtroLoweCase) != -1)
				{
					return true;
				} else if(person.getNombres().toLowerCase().indexOf(filtroLoweCase) != -1)
				{
					return true;
				}else
					return false;
				
				
			});
		});
		SortedList<ModeloTabla> sortedData = new SortedList<>(listaFiltrada);
		sortedData.comparatorProperty().bind(tableVista.comparatorProperty());
		tableVista.setItems(sortedData);
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
	
	
	public void mostrarSeccionEmpleados(MouseEvent e)
	{
		panePrincipal.setVisible(false);
		paneEmpleados.setVisible(true);
		labelPrincipal.setText("Empleados");		
		
		

		
	}
	
	
	
	
	public void mostrarSeccionPrincipal(MouseEvent e)
	{
		panePrincipal.setVisible(true);
		paneEmpleados.setVisible(false);
		labelPrincipal.setText("Menu Principal");
		
		
	}
	
	public void obtenerDatos()
	{
		
		if(fMatricula.getText().equals(null) || fNombre.getText().equals(null) || fEdad.getText().equals(null) || (rbSexo1.isSelected() == false && rbSexo2.isSelected() == false) || fDomicilio.getText().equals(null) || fTelefono.getText().equals(null) || cbPuesto.getValue()==null) {
			System.out.println("Error");
			
		}else 
		{
		
		int id = Integer.parseInt(fMatricula.getText());
		String nombre = fNombre.getText();
		String edad = fEdad.getText();
		String sexo="X";
		if(rbSexo1.isSelected())
			sexo = "Masculino";
		else if(rbSexo2.isSelected())
			sexo = "Femenino";
		String direccion = fDomicilio.getText();
		String telefono = fTelefono.getText();
		String puesto = cbPuesto.getValue().toString();
		int idPuesto = obtenerIdPuesto(puesto);
		Empleado emp = new Empleado(id, nombre, edad, sexo, direccion,telefono, idPuesto);
		emp.registrarEmpleado();
		iniciarTablaEmpleados();
		filtrarBusqueda();
		
		fMatricula.setText(null);
		fNombre.setText(null);
		fEdad.setText(null);
		fDomicilio.setText(null);
		fTelefono.setText(null);
		cbPuesto.getSelectionModel().select(null);
		rbSexo1.setSelected(false);
		rbSexo2.setSelected(false);
			
		btnDarAlta.setDisable(true);
		}

	}
	
	public void mostrarDatosDelEmpleado()
	{
		ArrayList<Integer> mat = e.traerMatriculas();
		int id = Integer.parseInt(fMatriculaBuscador.getText());
		boolean cierto = false;
		for(int i=0;i<mat.size();i++) {
			if(mat.get(i) == id) {
				cierto = true;
			}
		}
		
		if(fMatriculaBuscador.getText().equals(null) || fMatriculaBuscador.getText().equals("") || cierto == false)
		{
			System.out.println("Error");
		}else
		{
			
			btnActualizarDatos.setDisable(false);
			btnBorrarRegistro.setDisable(false);
			Empleado emp = new Empleado();
			emp = emp.traerEmpleadoConcreto(id);
						
			fMatriculaMod.setText("" + emp.getIdEmpleado());
			fNombreMod.setText("" + emp.getNombreEmpleado());
			fEdadMod.setText("" + emp.getEdadEmpleado());
			fDomicilioMod.setText(""+emp.getDireccionEmpleado());
			fTelefonoMod.setText("" + emp.getTelefonoEmpleado());
			
			if(rbSexo1Mod.getText().equals(emp.getSexoEmpleado()))			
				rbSexo1Mod.setSelected(true);
			else if(rbSexo2Mod.getText().equals(emp.getSexoEmpleado()))
				rbSexo2Mod.setSelected(true);
			
			for(int i=0; i<obsP.size(); i++) {
				if(obsP.get(i).getIdPuesto() == emp.getIdPuestoEmpleado()) {
					
					cbPuestoMod.getSelectionModel().select(i);
				}
			}
			
			fMatriculaMod.setEditable(true);
			fNombreMod.setEditable(true);
			fEdadMod.setEditable(true);
			fDomicilioMod.setEditable(true);
			fTelefonoMod.setEditable(true);
			
			cancelarDatos.setVisible(true);
			mostrarDatos.setVisible(false);
			
			btnActualizarDatos.setDisable(false);
			
		}
	}
	
	public void borrarRegistro()
	{
		
		
	}
	
	public void actualizarDatos()
	{
		if(fMatriculaMod.getText().equals(null) || fNombreMod.getText().equals(null) || fEdadMod.getText().equals(null) || (rbSexo1Mod.isSelected() == false && rbSexo2Mod.isSelected() == false) || fDomicilioMod.getText().equals(null) || fTelefonoMod.getText().equals(null) || cbPuestoMod.getValue()==null) {
			System.out.println("Error");
		}else {
		int mat = Integer.parseInt(fMatriculaBuscador.getText());
		int id = Integer.parseInt(fMatriculaMod.getText());
		String nombre = fNombreMod.getText();
		String edad = fEdadMod.getText();
		String sexo="X";
		if(rbSexo1Mod.isSelected())
			sexo = "Masculino";
		else if(rbSexo2Mod.isSelected())
			sexo = "Femenino";
		String direccion = fDomicilioMod.getText();
		String telefono = fTelefonoMod.getText();
		String puesto = cbPuestoMod.getValue().toString();
		int idPuesto = obtenerIdPuesto(puesto);
		Empleado emp = new Empleado();
		emp.modificarEmpleados(mat, id, nombre, edad, sexo, direccion, telefono, idPuesto);
		iniciarTablaEmpleados();
		filtrarBusqueda();
		
		fMatriculaBuscador.setText(null);
		fMatriculaMod.setText(null);
		fNombreMod.setText(null);
		fEdadMod.setText(null);
		fDomicilioMod.setText(null);
		fTelefonoMod.setText(null);
		cbPuestoMod.getSelectionModel().select(null);
		rbSexo1Mod.setSelected(false);
		rbSexo2Mod.setSelected(false);
		
		
		cancelarDatos.setVisible(false);
		mostrarDatos.setVisible(true);
		
		}
	}
	
	public void cancelar()
	{
		fMatriculaBuscador.setText(null);
		fMatriculaMod.setText(null);
		fNombreMod.setText(null);
		fEdadMod.setText(null);
		fDomicilioMod.setText(null);
		fTelefonoMod.setText(null);
		cbPuestoMod.getSelectionModel().select(null);
		rbSexo1Mod.setSelected(false);
		rbSexo2Mod.setSelected(false);
		
		
		cancelarDatos.setVisible(false);
		mostrarDatos.setVisible(true);
		
		btnActualizarDatos.setDisable(true);
		btnBorrarRegistro.setDisable(true);
	}
	
	public void cerrarSesion(MouseEvent e) throws IOException
	{
		
			Stage stage = (Stage)anchorPanePrincipal.getScene().getWindow();		
			stage.close();
		
			
			Parent root = FXMLLoader.load(getClass().getResource("/escenas/Scene1-Login.fxml"));
			Stage stage1 = (Stage)((Node) e.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage1.setScene(scene);
			stage1.centerOnScreen();
			stage1.show();
		
			root.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					
					xOffSet = event.getSceneX();
					yOffSet = event.getSceneY();
					
				}
				});
			
				root.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					stage1.setX(e.getScreenX() - xOffSet);
					stage1.setY(e.getScreenY() - yOffSet);
				}
				
				});
			

		
	}
	
	


}
