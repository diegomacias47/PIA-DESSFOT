package controlador;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Reservacion;

public class ItemController implements Initializable {


    @FXML
    private Button btnDetalles;

    @FXML
    private Button btnFacturar;
    
    @FXML
    private Label idReserva;

    @FXML
    private Label idCliente;

    @FXML
    private Label idHabitacion;
    
    @FXML
    private int valorReserva;

    @FXML
    private Label idEstado;

    private double xOffSet, yOffSet;
    
    private Reservacion res = new Reservacion();
    
    private ArrayList<Reservacion> reservas = new ArrayList<Reservacion>();
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	cambiarColor(btnDetalles);
    	cambiarColor(btnFacturar);
    	quitarColor(btnDetalles);
    	quitarColor(btnFacturar);
    	
    	res.getReservaciones(reservas);
    	
    	
    	
    	btnDetalles.setOnAction((ActionEvent e)->{
    		try {
    	        FXMLLoader fxmlLoader = new FXMLLoader();
    	        fxmlLoader.setLocation(getClass().getResource("/vista/Scene3Res.fxml"));
    	        Parent root = fxmlLoader.load();
    	        Scene scene = new Scene(root);
    	        Stage stage = new Stage();
    	        stage.initStyle(StageStyle.UNDECORATED);
    	        
    	        stage.setScene(scene);
    	        stage.show();
    	        
    	        DetallesController c = fxmlLoader.getController();
    	        for(int i=0; i<reservas.size(); i++)
    	        {
    	        	if(reservas.get(i).getIdReservacion() == valorReserva)
    	        	{
    	        		c.establecerDatos2(reservas.get(i));
    	        	}
    	        }    	 
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
    					stage.setX(e.getScreenX() - xOffSet);
    					stage.setY(e.getScreenY() - yOffSet);
    				}
    				
    				});
    	       
	
			} catch (IOException el) {
				
				el.printStackTrace();
			}
    		
    		
    	});
    	
    	btnFacturar.setOnAction((ActionEvent e)->{
    		System.out.println("Helo");
    	});
		
	}
    
    public void establecerDatos(Reservacion res)
    {
    	this.res = res;
    	idReserva.setText("" + res.getIdReservacion());
    	idCliente.setText("" + res.getIdClienteReservacion());
    	idHabitacion.setText("" + res.getIdHabitacion());
    	idEstado.setText("" + res.getEstadoReservacion());
    	valorReserva = res.getIdReservacion();
    	
    	
		FXMLLoader fxml = new FXMLLoader();
		fxml.setLocation(getClass().getResource("/vista/Scene3Res.fxml"));
		
		
		

    }
    
    public void establecerInformacion() 
    {
    	int is = Integer.parseInt(idReserva.getText());
    	System.out.println(is);
    	
    	for(int i=0; i<reservas.size(); i++) 
		{
		
			if(Integer.parseInt(idReserva.getText()) == reservas.get(i).getIdReservacion())
			{
				System.out.println("Aki llegue");
				URL location = null;
				try {
					location = new URL("/vista/Scene3Res.fxml");
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				FXMLLoader loader = new FXMLLoader(location);
				DetallesController controller = loader.getController();
		        controller.establecerDatos2(reservas.get(i));
		        //c.hola();
			}


		
		}
    }
    

	public void cambiarColor(Button boton)
	{		
		boton.setOnMouseEntered((MouseEvent e)->{
			boton.setStyle("-fx-background-color: linear-gradient(#087DCD, #81C2FF); "); //-fx-effect: dropShadow(three-pass-box, rgba(0,0,0,.5), 10.0, 0.0, 0.0, 10.0);
		});
	}
	
	public void quitarColor(Button boton)
	{
		boton.setOnMouseExited((MouseEvent e)->{
			boton.setStyle("-fx-background-color: linear-gradient(#0098FF, #A2D2FF);  ");
		});
	}
    

    
}
