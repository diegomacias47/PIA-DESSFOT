package controlador;

import java.io.IOException;
import java.sql.Timestamp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.DetalleRS;
import modelo.Factura;
import modelo.Habitacion;
import modelo.Reservacion;

public class ControllerRFDetalle
{
    @FXML
    private AnchorPane padre;

    @FXML
    private Label nombre;

    @FXML
    private Label matricula;

    @FXML
    private Label telefono;

    @FXML
    private Label total;

    @FXML
    private Label dias;

    @FXML
    private Label huesp;

    
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
	
    private ObservableList<DetalleRS> obsLisDS;
   
    private DetalleRS rs1 = new DetalleRS();
    private Reservacion res;
    private Cliente c = new Cliente();
    
    private GridPane pane;
    
    private int idReservacion;
    private double precioP;
    private double preS;
    
    public void establecerDatos(Reservacion res)
    {
		this.res = res;
		matricula.setText("" + res.getIdReservacion());
		telefono.setText("" + res.getIdHabitacion());
		nombre.setText("" + res.getNombreCliente());
		huesp.setText("" + res.getCantidadPersonas());
		dias.setText("" + res.getDiasReservacion());
		
		
    	precioP = res.getPrecioInicialReservacion();    	
    	preS=0;
		
		idReservacion = res.getIdReservacion();
		iniciarTablaDS();
		
		if(obsLisDS.size() == 0)
    	{
    		
    	}
    	else 
    	{
    		 for(int i=0; i<obsLisDS.size(); i++)
    		 {
    			 	preS+=obsLisDS.get(i).getPrecio();
    		 }
    	
    	}

    	precioP += preS;
    	
    	total.setText("" + precioP);
    	
    	
    	
    }
    
    public void establecerGrid(GridPane pane)
    {
    	this.pane = pane;
    }
    
	public void iniciarTablaDS()
	{								
			obsLisDS = FXCollections.observableArrayList(rs1.obtenerDetallesRS(idReservacion));
			
			no.setCellValueFactory(new PropertyValueFactory<>("contador"));
			servicio.setCellValueFactory(new PropertyValueFactory<>("idServicio"));
			fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
			precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
			tabla.setItems(obsLisDS);
			
			
		
	}

	public void facturar()
	{
    	int id = res.getIdReservacion();        	
    	Timestamp fecha = new Timestamp(System.currentTimeMillis());    
    	
    	new Factura(id, fecha, precioP).insertarFactura();
    	res.actualizarEstado(id);
    	new Habitacion().actualizarEstado(res.getIdHabitacion(), 2);
    	
    	try 
		{
    		FXMLLoader fxml = new FXMLLoader();
    		fxml.setLocation(getClass().getResource("/vista/FXML-Secundario.fxml"));
			Parent root = fxml.load();
			ControllerFXML2 c = fxml.getController();
			c.mostrarReservaciones(pane);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	
    	Stage s = (Stage) padre.getScene().getWindow();
    	s.close();

	}
	
    
    public void cerrarVentana()
    {
    	Stage s = (Stage) padre.getScene().getWindow();
    	s.close();
    }
	
}
