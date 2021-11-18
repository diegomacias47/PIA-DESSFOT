package controlador;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.DetalleRS;
import modelo.Reservacion;
import modelo.Servicio;

public class ControllerRSAgregar implements Initializable
{
	

    @FXML
    private AnchorPane padre;

    @FXML
    private Label idRes;

    @FXML
    private Label cantS;
    
    @FXML
    private TextField nombre;

    @FXML
    private TableView<DetalleRS> tabla;

    @FXML
    private TableColumn<DetalleRS, Integer> no;

    @FXML
    private TableColumn<DetalleRS, Integer> servicio;

    @FXML
    private TableColumn<DetalleRS, Timestamp> fecha;

    @FXML
    private TableColumn<DetalleRS, Double> precio;

    @FXML
    private TextField buscador;

    @FXML
    private TableView<Servicio> tabla1;

    @FXML
    private TableColumn<Servicio, Integer> idS;

    @FXML
    private TableColumn<Servicio, String> servicio1;

    @FXML
    private TableColumn<Servicio, Double> precio1;
    
    private ObservableList<DetalleRS> obsLisDS;
    
    private ObservableList<Servicio> obsLisS;

	private DetalleRS rs1 = new DetalleRS();
	
	private Servicio s1 = new Servicio();
	
	private int idReservacion;
	
	private Reservacion res;

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		
	}
	
	public void establecerDatos(Reservacion res)
	{
		this.res = res;
		idReservacion = res.getIdReservacion();
		idRes.setText("" + idReservacion);
		iniciarTablaDS();
		iniciarTablaS();
	}
	
	public void insertarServicio()
	{
		int id = Integer.parseInt(nombre.getText());
		Timestamp fecha = new Timestamp(System.currentTimeMillis());
		double pre=0;
		for(int i=0; i<obsLisS.size(); i++)
		{
			if(obsLisS.get(i).getIdServicio() == id)
			{
				pre = obsLisS.get(i).getPrecioServicio();
			}
		}
		System.out.println("Hola");
		new DetalleRS(idReservacion, id, fecha).insertarDetalleRS();
		iniciarTablaDS();
	}
	
	
	public void iniciarTablaDS()
	{								
			obsLisDS = FXCollections.observableArrayList(rs1.obtenerDetallesRS(idReservacion));
			
			no.setCellValueFactory(new PropertyValueFactory<>("contador"));
			servicio.setCellValueFactory(new PropertyValueFactory<>("idServicio"));
			fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
			precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
			tabla.setItems(obsLisDS);
			
			cantS.setText("" + obsLisDS.size());
		
	}

	public void iniciarTablaS()
	{
		obsLisS = FXCollections.observableArrayList(s1.obtenerServicios());
		
		idS.setCellValueFactory(new PropertyValueFactory<>("idServicio"));
		servicio1.setCellValueFactory(new PropertyValueFactory<>("descripcionServicio"));		
		precio1.setCellValueFactory(new PropertyValueFactory<>("precioServicio"));
		tabla1.setItems(obsLisS);
		
	}
	
	public void cerrarVentana()
	{
		Stage stage = (Stage) padre.getScene().getWindow();
		stage.close();
	}
	
}
