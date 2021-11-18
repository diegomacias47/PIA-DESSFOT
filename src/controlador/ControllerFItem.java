package controlador;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Factura;

public class ControllerFItem 
{
	   @FXML
	    private Label label1;

	    @FXML
	    private Label label2;

	    @FXML
	    private Label label3;

	    @FXML
	    private Label label4;
	    
	    private Factura fac;
	    
	    double xOffSet, yOffSet;
	    
	    public void establecerDatos(Factura fac)
	    {
	    	this.fac = fac;
	    	label1.setText("" + fac.getIdFactura());
	    	label2.setText("" + fac.getIdReservacion());
	    	label3.setText("" + fac.getFechaFactura());
	    	label4.setText("" + fac.getPrecioTotal());
	    	
	    }
	    
	    public void mostrarDetalles()
	    {
	    	
	    	FXMLLoader fxml = new FXMLLoader();
	    	fxml.setLocation(getClass().getResource("/vista/FXML-F-Detalle.fxml"));
	    	try {
				Parent root = fxml.load();
				ControlladorFDetalle c = fxml.getController();
				c.establecerDatos(fac);
				
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.initStyle(StageStyle.UNDECORATED);
				stage.show();
				

				
				
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
}
