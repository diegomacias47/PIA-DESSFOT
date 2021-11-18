package controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.Habitacion;
import modelo.Reservacion;

public class ControllerRAgregar implements Initializable 
{

    @FXML
    private AnchorPane padre;
	
	@FXML
	private TextField nombre;

	@FXML
	private TextField celular;

	@FXML
	private TextField idHabitacion;


	@FXML
	private Spinner<Integer> spinner;
	
    @FXML
    private TableView<Habitacion> tabla;

    @FXML
    private TableColumn<Habitacion, String> tipo;

    @FXML
    private TableColumn<Habitacion, Integer> id;

    @FXML
    private TableColumn<Habitacion, Integer> camas;

    @FXML
    private TableColumn<Habitacion, Integer> capacidad;

    @FXML
    private TableColumn<Habitacion, Double> precio;

    @FXML
    private TableColumn<Habitacion, String> estado;
    
    @FXML
    private TextField buscador;    

    @FXML
    private TextField huespedes;
    
    private Habitacion habitacion = new Habitacion();
    
    private GridPane p;
    

    private ObservableList<Habitacion> hab = FXCollections.observableArrayList();
    

    
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		Parent root = padre;
		padre.setOnMouseClicked(event ->{
			padre.requestFocus();
		});

		SpinnerValueFactory<Integer> sp = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 15);
		sp.setValue(1);
		spinner.setValueFactory(sp);
		iniciarTabla();
		filtrarBusqueda();
		
	}
	
	public void iniciarTabla()
	{			
			//hab = habitacion.obtenerHabitaciones();
			hab = habitacion.obtenerHab();
			tipo.setCellValueFactory(new PropertyValueFactory<>("tipoHabitacion"));
			id.setCellValueFactory(new PropertyValueFactory<>("idHabitacion"));
			camas.setCellValueFactory(new PropertyValueFactory<>("camasHabitacion"));
			capacidad.setCellValueFactory(new PropertyValueFactory<>("capacidadHabitacion"));
			precio.setCellValueFactory(new PropertyValueFactory<>("precioHabitacion"));
			estado.setCellValueFactory(new PropertyValueFactory<>("estatusHabitacion"));
			tabla.setItems(hab);
		
	}
	
	public void filtrarBusqueda()
	{
		
		
		tipo.setCellValueFactory(new PropertyValueFactory<>("tipoHabitacion"));
		id.setCellValueFactory(new PropertyValueFactory<>("idHabitacion"));
		camas.setCellValueFactory(new PropertyValueFactory<>("camasHabitacion"));
		capacidad.setCellValueFactory(new PropertyValueFactory<>("capacidadHabitacion"));
		precio.setCellValueFactory(new PropertyValueFactory<>("precioHabitacion"));
		estado.setCellValueFactory(new PropertyValueFactory<>("estatusHabitacion"));
		tabla.setItems(hab);
		
		FilteredList<Habitacion> listaFiltrada = new FilteredList<>(hab, b -> true);
		buscador.textProperty().addListener((observable, oldValue, newValue) ->{
			listaFiltrada.setPredicate(person ->{
				
				if(newValue == null || newValue.isEmpty())
				{
					return true;
				}
				String filtroLoweCase = newValue.toLowerCase();
				if(person.getTipoHabitacion().toLowerCase().indexOf(filtroLoweCase) != -1)
				{
				
					return true;
				} else
					return false;
				
				
			});
		});
		SortedList<Habitacion> sortedData = new SortedList<>(listaFiltrada);
		sortedData.comparatorProperty().bind(tabla.comparatorProperty());
		tabla.setItems(sortedData);
	}
	
	public void registrarReservacion()
	{
		Cliente c = new Cliente(nombre.getText(),celular.getText());
		c.insertarCliente();
		int idCli = c.obtenerIdCliente();
		
		
		int idH = Integer.parseInt(idHabitacion.getText());
		double precio=0;
		for(int i=0; i<hab.size(); i++)
		{
			if(hab.get(i).getIdHabitacion() == idH)
			{
				precio = hab.get(i).getPrecioHabitacion() * spinner.getValue();
			}
		}
		
		int personas = Integer.parseInt(huespedes.getText());
		
		Timestamp fechaI = new Timestamp(System.currentTimeMillis());	
		
		Calendar cal2 = Calendar.getInstance();	
		cal2.setTime(fechaI);	
		cal2.add(Calendar.DATE, spinner.getValue());
		
		Date fechaE =  new Date(cal2.getTime().getTime());
		
		new Reservacion(idCli, idH, spinner.getValue(), personas,fechaI, fechaE, precio, 1).insertarReservacion();
		new Habitacion().actualizarEstado(idH, 1);
		
		iniciarTabla();
		filtrarBusqueda();
		
		
		FXMLLoader fxml = new FXMLLoader();
		fxml.setLocation(getClass().getResource("/vista/FXML-Secundario.fxml"));
		try {
			Parent root = fxml.load();
			ControllerFXML2 ca = fxml.getController();
			//ca.mostrarReservaciones(p);
			ca.vaciarPane(p);
			ca.mostrarReservaciones(p);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		nombre.setText(null);
		celular.setText(null);
		idHabitacion.setText(null);
		huespedes.setText(null);
		
		cerrarVentana();
	}
	
	public void establecerGrid(GridPane p)
	{
		this.p = p;
	}
	
	
	public void cerrarVentana()
	{
		Stage stage = (Stage)padre.getScene().getWindow();
		stage.close();
		
	}

}
