package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.IntegerStringConverter;
import modelo.Reservacion;

public class ReservistaController implements Initializable
{
	
	@FXML
	private AnchorPane anchorPane;
	
	 @FXML
	 private Button btnAñadirReservacion;

	@FXML
	private Button btnServicio;

	@FXML
	private Button btnCerrarSesion;


    @FXML
    private ScrollPane scroll;
    

    @FXML
    private Button boton;
    
    @FXML
    private GridPane grid;
    
    private ArrayList<Reservacion> reservacion = new ArrayList<Reservacion>();
    private Reservacion res = new Reservacion();
    
    @FXML
    private TextField buscador;

    private double xOffSet, yOffSet;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		res.getReservaciones(reservacion);		
		
		
		
		anchorPane.setOnMouseClicked((MouseEvent e)->{
			anchorPane.requestFocus();
		
		});
		
		mostrarTodasLasReservaciones();
		
		buscador.setOnMouseEntered((MouseEvent e)->{
			buscador.setStyle("-fx-background-color: #81C2FF;");
		});
		
		buscador.setOnMouseExited((MouseEvent e)->{
			buscador.setStyle("-fx-background-color: linear-gradient(#0098FF, #A2D2FF);");
		});				
		
		cambiarColor(btnAñadirReservacion);
		quitarColor(btnAñadirReservacion);
		cambiarColor(btnCerrarSesion);
		quitarColor(btnCerrarSesion);
		cambiarColor(btnServicio);
		quitarColor(btnServicio);
		
		
		buscador.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		            buscador.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
						

	}
    
	public void cambiarColor(Button boton)
	{		
		boton.setOnMouseEntered((MouseEvent e)->{
			boton.setStyle("-fx-background-color: linear-gradient(#F0F1F1, #BABABA); -fx-effect: dropShadow(three-pass-box, rgba(0,0,0,.5), 10.0, 0.0, 0.0, 10.0);"); //-fx-effect: dropShadow(three-pass-box, rgba(0,0,0,.5), 10.0, 0.0, 0.0, 10.0);
		});
	}
	
	public void quitarColor(Button boton)
	{
		boton.setOnMouseExited((MouseEvent e)->{
			boton.setStyle("-fx-background-color: linear-gradient(#F0F1F1, #D8DDE5);  ");
		});
	}
	
	
	
	
	public void mostrarTodasLasReservaciones()
	{
		
		int column = 0;
		int row = 1;
		try {
			for(int i=0; i<reservacion.size(); i++)
			{
				FXMLLoader fxml = new FXMLLoader();
				fxml.setLocation(getClass().getResource("/vista/ItemReservacion.fxml"));
			
				AnchorPane anchorPane = fxml.load();
				
				ItemController itemController = fxml.getController();
				itemController.establecerDatos(reservacion.get(i));
				
				if(column == 2)
				{
					column = 0;
					row++;
				}
				
				grid.setMinWidth(Region.USE_COMPUTED_SIZE);
				grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
				grid.setMaxWidth(Region.USE_PREF_SIZE);
				
				grid.setMinHeight(Region.USE_COMPUTED_SIZE);
				grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
				grid.setMaxHeight(Region.USE_PREF_SIZE);
				
				grid.add(anchorPane, column++, row);
				grid.setPadding(new Insets(0,0,0,4));
				GridPane.setMargin(anchorPane, new Insets(15,35,15,35));
				
				if(row == 1)
				{
					grid.setPadding(new Insets(0,0,0,4));
					GridPane.setMargin(anchorPane, new Insets(-10,35,15,35));
				}
					
			}		
		 }catch (IOException e) {
				
			 e.printStackTrace();
		 }

		
	}
	
	
	public void mostrarPorId()
	{
		
		grid.getChildren().clear();
		String id = buscador.getText();
		
		if(id.length() == 0)
		{
			mostrarTodasLasReservaciones();
		}
		else
		{			
			
			int hola = Integer.parseInt(id);
			int column = 0;
			int row = 1;
			try {												
				for(int i=0; i<reservacion.size(); i++)
				{

					if(reservacion.get(i).getIdReservacion() == hola || reservacion.get(i).getIdClienteReservacion() == hola)
					{
						FXMLLoader fxml = new FXMLLoader();
						fxml.setLocation(getClass().getResource("/vista/ItemReservacion.fxml"));
					
						AnchorPane anchorPane = fxml.load();
						ItemController itemController = fxml.getController();
						itemController.establecerDatos(reservacion.get(i));
						
						if(column == 2)
						{
							column = 0;
							row++;
						}
						
						
						grid.setMinWidth(Region.USE_COMPUTED_SIZE);
						grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
						grid.setMaxWidth(Region.USE_PREF_SIZE);
						
						grid.setMinHeight(Region.USE_COMPUTED_SIZE);
						grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
						grid.setMaxHeight(Region.USE_PREF_SIZE);
						
						grid.add(anchorPane, column++, row);
						GridPane.setMargin(anchorPane, new Insets(-10,35,15,35));
					}

					
				}		
			 }catch (IOException e) {
					
				 e.printStackTrace();
			 }
		}
	
		
	}
	
	
	public void agregarReservacion()
	{
		try {
			System.out.println("Hola");
			FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setLocation(getClass().getResource("/vista/Scene3-AgregarReservacion.fxml"));
	        Parent root = fxmlLoader.load();
	        AgregarResController c = fxmlLoader.getController();
	        Scene scene = new Scene(root);
	        Stage stage = new Stage();
	        stage.initStyle(StageStyle.UNDECORATED);
	        
	        stage.setScene(scene);
	        stage.show();
	        	         	 
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
		
	}

    
    
    
	
}
