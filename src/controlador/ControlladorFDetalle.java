package controlador;

import java.sql.Timestamp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.DetalleRS;
import modelo.Factura;
import modelo.Reservacion;

public class ControlladorFDetalle 
{
	  @FXML
	    private AnchorPane padre;

	    @FXML
	    private Label reservacion;

	    @FXML
	    private Label factura;

	    @FXML
	    private Label habitacion;

	    @FXML
	    private Label total;

	    @FXML
	    private Label dias;

	    @FXML
	    private Label huesp;

	    @FXML
	    private Label nombre;

	    @FXML
	    private Label telefono;

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
	    
	    private int idReservacion;
	    
	    private Factura fac;
	    private Reservacion res;
	  
	    
	    public void establecerDatos(Factura fac)
	    {
	    	this.fac = fac;
	    	factura.setText("" + fac.getIdFactura());
	    	reservacion.setText("" + fac.getIdReservacion());
	    	idReservacion = fac.getIdReservacion();
	    	total.setText("" + fac.getPrecioTotal());
	    	obtenerDatos();
	    	iniciarTablaDS();
	    }
	    
	    public void obtenerDatos()
	    {
	    	res = new Reservacion().getReservacionConcreta(idReservacion);
	    	nombre.setText("" + res.getNombreCliente());
	    	telefono.setText("" + res.getCelularCliente());
	    	habitacion.setText("" + res.getIdHabitacion());
	    	dias.setText("" + res.getDiasReservacion());
	    	huesp.setText("" + res.getCantidadPersonas());
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
	    
		
		public void cerrarVentana()
		{
			Stage s = (Stage) padre.getScene().getWindow();
			s.close();
		}
	    
	    
	    
}
