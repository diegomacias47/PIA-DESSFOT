package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Reservacion;

public class DetallesController implements Initializable
{

    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Label idRes;

    @FXML
    private Label idCli;

    @FXML
    private Label nomCli;

    @FXML
    private Label idHab;

    @FXML
    private Label cantDias;

    @FXML
    private Label fechaIni;

    @FXML
    private Label fechaF;

    @FXML
    private Label preIni;

    @FXML
    private Label cantSer;
    
    
    private double xOffSet, yOffSet;
    
    private Reservacion res = new Reservacion();
    
    public void establecerDatos2(Reservacion res)
    {
    	this.res = res;
    	
    	idRes.setText("" + res.getIdReservacion());
    	idCli.setText("" + res.getIdClienteReservacion());
    	nomCli.setText("" + res.getNombreCliente());
    	idHab.setText("" + res.getIdHabitacion());
    	cantDias.setText("" + res.getDiasReservacion());
    	fechaIni.setText("" + res.getFechaReservacion());
    	fechaF.setText("" + res.getFechaExpiracion());
    	preIni.setText("" + res.getPrecioInicialReservacion());
    	cantSer.setText("");
    
    }
    
    public void hola() {
    	
    	System.out.println("Hola");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

			
	
		
	}
	
	public void cerrarVentana()
	{
		Stage stage = (Stage)anchorPane.getScene().getWindow();		
		stage.close();
	}
    
}
