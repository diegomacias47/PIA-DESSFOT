package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Reservacion;


public class ControllerFXML2 implements Initializable
{
    @FXML
    private FontAwesomeIconView casaIco, resIco, habIco;

    @FXML
    private AnchorPane padre;
    
    @FXML
    private MaterialDesignIconView cerrarsesIco;
    
    @FXML
    private Button btnPrimero;

    @FXML
    private TextField buscador;
    
    @FXML
    private Button btnInicio, btnServicios;

    @FXML
    private Button btnReservaciones;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private AnchorPane reservacionesPane;
    
    @FXML
    private AnchorPane inicioPane;

    @FXML
    private GridPane gridReservacion;

    @FXML
    private Pane tituloPane;

    @FXML
    private Label titulo;

    @FXML
    private FontAwesomeIconView iconoTitulo;
    
    private Reservacion reservacion = new Reservacion();
    private ArrayList<Reservacion> reservaciones = new ArrayList<Reservacion>();
    
    double xOffSet, yOffSet;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		iconoFA(btnInicio, casaIco);
	    iconoFA(btnReservaciones, resIco);
	    iconoFA(btnServicios, habIco);
	    iconoMD(btnCerrarSesion, cerrarsesIco);
	    
	    buscador.setVisible(false);
		
		mostrarReservaciones(gridReservacion);
		acciones();
	}
	
	public void acciones()
	{
		btnInicio.setOnAction(event ->{
			mostrarSeccionInicio();
		});
		
		btnReservaciones.setOnAction(event ->{
			mostrarSeccionReservacion();
		});
	}
	
	
    
	public void mostrarSeccionInicio()
	{
		inicioPane.setVisible(true);
		reservacionesPane.setVisible(false);
		btnPrimero.setVisible(false);
	}
	
	
	public void mostrarSeccionReservacion()
	{	
		inicioPane.setVisible(false);
		reservacionesPane.setVisible(true);
		buscador.setVisible(true);
		
		btnPrimero.setVisible(true);
		btnPrimero.setText("Agregar reservacion");
		btnPrimero.setOnAction(event ->{
			FXMLLoader fxml = new FXMLLoader();
	    	fxml.setLocation(getClass().getResource("/vista/FXML-R-Agregar.fxml"));
	    	try {
				Parent root = fxml.load();
				ControllerRAgregar c = fxml.getController();
				c.establecerGrid(gridReservacion);
				
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.initStyle(StageStyle.UNDECORATED);
				stage.show();				
				
				root.setOnMousePressed(event1 ->{
						xOffSet = event1.getSceneX();
						yOffSet = event1.getSceneY();
				});
				
				root.setOnMouseDragged(event1 ->{
						stage.setX(event1.getScreenX() - xOffSet);
						stage.setY(event1.getScreenY() - yOffSet);
				});
	 									
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		});
		
		
	}
	
	public void vaciarPane(GridPane grid)
	{
		grid.getChildren().clear();
	}
    
    public void mostrarReservaciones(GridPane grid)
	{	
		grid.getChildren().clear();
		reservacion.getReservaciones(reservaciones);
		int column = 0;
		int row = 1;		
		try {
			for(int i=0; i<reservaciones.size(); i++)
			{
				
				FXMLLoader fxml = new FXMLLoader();
				fxml.setLocation(getClass().getResource("/vista/FXML-R-Item2.fxml"));				
				AnchorPane anchorPane = fxml.load();
				
				ControllerRItem itemController = fxml.getController();
				itemController.establecerDatos(reservaciones.get(i));
				itemController.habilitarBotonFactura();
				itemController.establecerPane(grid);
							
				if(column == 2)
				{
					column = 0;
					row++;
				}				
				
				grid.add(anchorPane, column++, row);				
				grid.setPadding(new Insets(0,0,0,4));
				GridPane.setMargin(anchorPane, new Insets(10,6,10,6));
				
				if(row == 1)
				{
					grid.setPadding(new Insets(0,0,0,0));
					GridPane.setMargin(anchorPane, new Insets(-10,6,10,6));
				}
					
			}		
		 }catch (IOException e) {
				
			 e.printStackTrace();
		 }
	}

	public void iconoFA(Button boton, FontAwesomeIconView icono )
	{

		boton.setOnMouseEntered(event ->{
			icono.setGlyphStyle("-fx-fill: black");
		});
		
		boton.setOnMouseExited(event ->{
			icono.setGlyphStyle("-fx-fill: white");
		});
		
		
		
	}
	
	
	public void iconoMD(Button boton, MaterialDesignIconView icono )
	{
		boton.setOnMouseEntered(event ->{
			icono.setGlyphStyle("-fx-fill: black");
		});
		
		boton.setOnMouseExited(event ->{
			icono.setGlyphStyle("-fx-fill: white");
		});
		
	}
	
	public void cerrarAplicacion()
	{
		try {
		Parent root;
		root = FXMLLoader.load(getClass().getResource("/escenas/Scene1-Login.fxml"));
		Scene scene = new Scene(root);
		Stage stage1 = (Stage) padre.getScene().getWindow();
		
		stage1.setTitle("Administrador");
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
	
	
	
	
	
	} catch (IOException e1) {
		
		e1.printStackTrace();
	}
    	
	}
    
    
}
