package controlador;

import java.io.IOException;
import java.util.ArrayList;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import de.jensd.fx.glyphs.octicons.OctIconView;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.util.Duration;
import modelo.Empleado;
import modelo.Factura;
import modelo.Habitacion;
import modelo.Reservacion;

public class ControllerFXML1  {

	@FXML Pane inicioPane, tituloPane;

    @FXML
    private Label menu, menuBack, titulo;
    
    @FXML
    private GridPane gridEmpleado, gridFacturas, gridHabitaciones, gridReservaciones;
    
    @FXML
    private OctIconView plus;

    @FXML
    private AnchorPane habitacionesPane, slider, empleadosPane, apP, reservacionesPane, facturasPane;
    
    @FXML
    private Button btnPrimero, btnSegundo, btnTercero;
    
    @FXML
    private TextField buscador;
    
    @FXML
    private Button inicio, empleados, habitaciones, reservaciones, facturas, cerrarSesion;

    @FXML
    private FontAwesomeIconView habIco, resIco, facIco, empIco, casaIco, iconoTitulo;
    
    @FXML
    private MaterialDesignIconView cerrarsesIco;
    
    @FXML
    private Empleado emp = new Empleado();
    
    private Habitacion hab = new Habitacion();
   
    private Reservacion res = new Reservacion();
    
    private Factura fac = new Factura();
    
    private ArrayList<Empleado> e = new ArrayList<Empleado>();
    
    private ArrayList<Habitacion> h = new ArrayList<Habitacion>();
    
    private ArrayList<Factura> f = new ArrayList<Factura>();
    
    private boolean sE = false;
    private boolean sH = false;
    private boolean sR = false;
    
    private ArrayList<Reservacion> r  = new ArrayList<Reservacion>();
    
    private double xOffSet, yOffSet;

	
	public void initialize() {
			
	
		mostrarEmpleados(gridEmpleado);		
		mostrarHabitaciones(gridHabitaciones);		
		mostrarReservaciones(gridReservaciones, r);
		mostrarFacturas(gridFacturas);
		
		empleadosPane.setVisible(false);
		habitacionesPane.setVisible(false);
		buscador.setVisible(false);
		inicioPane.setVisible(true);
		
		slider.setTranslateX(0);
		menuBack.setVisible(false);
		menu.setOnMouseClicked(event ->{
			TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-280);
            slide.play();

            slider.setTranslateX(0);
           
            System.out.println("Listo");
            slide.setOnFinished((ActionEvent e)-> {
                menu.setVisible(false);
                menuBack.setVisible(true);
            });
		});
			
        menuBack.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            
            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-280);
           
            slide.setOnFinished((ActionEvent e)-> {
                menu.setVisible(true);
                menuBack.setVisible(false);
            });
        });
                
        cerrarSesion.setOnAction(event ->{
        	/*Stage s = (Stage) apP.getScene().getWindow();
        	s.close();*/
        	
        	
			try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/escenas/Scene1-Login.fxml"));
			Scene scene = new Scene(root);
			Stage stage1 = (Stage) apP.getScene().getWindow();
			
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
        	
        });

        iconoFA(inicio, casaIco);
        iconoFA(reservaciones, resIco);
        iconoFA(habitaciones, habIco);
        iconoFA(empleados, empIco);
        iconoFA(facturas, facIco);
        iconoMD(cerrarSesion, cerrarsesIco);
        
        buscadorItem();
	}	
	
	public void mostrarEmpleados(GridPane grid)
	{	
		grid.getChildren().clear();
		emp.obtenerEmpleados(e);
		int column = 0;
		int row = 1;		
		try {
			for(int i=0; i<e.size(); i++)
			{
				
				FXMLLoader fxml = new FXMLLoader();
				fxml.setLocation(getClass().getResource("/vista/FXML-E-Item2.fxml"));				
				AnchorPane anchorPane = fxml.load();
				
				ControllerEItem itemController = fxml.getController();
				itemController.establecerDatos(e.get(i));
				itemController.establecerDatos(grid, buscador);
				
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
	
	public void mostrarHabitaciones(GridPane grid)
	{	
		grid.getChildren().clear();
		hab.obtenerHabitacion(h);
		int column = 0;
		int row = 1;		
		try {
			for(int i=0; i<h.size(); i++)
			{
				
				FXMLLoader fxml = new FXMLLoader();
				fxml.setLocation(getClass().getResource("/vista/FXML-H-Item2.fxml"));				
				AnchorPane anchorPane = fxml.load();
				
				ControllerHItem c = fxml.getController();
				c.establecerDatos(h.get(i));
				c.establecerGrid(gridHabitaciones);
				if(h.get(i).getIdEstatus() != 1)
				{
					c.habilitarTuerca();
				}
					
				
				
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
	
	public void mostrarReservaciones(GridPane grid, ArrayList<Reservacion> array)
	{	
		grid.getChildren().clear();
		res.getReservacionesFacturadas(r);
		int column = 0;
		int row = 1;		
		try {
			for(int i=0; i<array.size(); i++)
			{
				
				FXMLLoader fxml = new FXMLLoader();
				fxml.setLocation(getClass().getResource("/vista/FXML-R-Item2.fxml"));				
				AnchorPane anchorPane = fxml.load();
				
				ControllerRItem itemController = fxml.getController();
				itemController.establecerDatos(array.get(i));
				itemController.desactivarBoton();
				
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
	
	public void mostrarFacturas(GridPane grid)
	{	
		grid.getChildren().clear();
		
		int column = 0;
		int row = 1;		
		fac.obtenerFacturas(f);
		
		try {
			for(int i=0; i<f.size(); i++)
			{
				
				FXMLLoader fxml = new FXMLLoader();
				fxml.setLocation(getClass().getResource("/vista/FXML-F-Item.fxml"));				
				AnchorPane anchorPane = fxml.load();
				
				ControllerFItem c = fxml.getController();
				c.establecerDatos(f.get(i));

					
				
				
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
	
	
	public void mostrarSeccionInicio()
	{
		inicioPane.setVisible(true);
		empleadosPane.setVisible(false);
		habitacionesPane.setVisible(false);
		reservacionesPane.setVisible(false);
		facturasPane.setVisible(false);
		
		tituloPane.setVisible(true);
		buscador.setVisible(false);
		
		titulo.setText("Inicio");
	}

	public void mostrarSeccionEmpleados()
	{
		inicioPane.setVisible(false);
		empleadosPane.setVisible(true);
		habitacionesPane.setVisible(false);
		reservacionesPane.setVisible(false);
		facturasPane.setVisible(false);
		tituloPane.setVisible(true);
		titulo.setText("Empleados");
		buscador.setVisible(true);
		
		accionBotonEmpleados();
		sE=true;
		sH=false;
		
		
	}
	
	
	public void mostrarSeccionHabitacion()	
	{
		inicioPane.setVisible(false);
		empleadosPane.setVisible(false);
		habitacionesPane.setVisible(true);
		reservacionesPane.setVisible(false);
		facturasPane.setVisible(false);
		tituloPane.setVisible(true);
		titulo.setText("Habitaciones");
		btnPrimero.setVisible(false);
		buscador.setVisible(true);
		accionBotonHabitacion();
		sE=false;
		sH=true;
		buscador.setPromptText("Buscar por ID");
		
		
	}
	
	public void mostrarSeccionReservacion()
	{
		inicioPane.setVisible(false);
		empleadosPane.setVisible(false);
		habitacionesPane.setVisible(false);
		reservacionesPane.setVisible(true);
		facturasPane.setVisible(false);
		tituloPane.setVisible(true);
		titulo.setText("Reservaciones");
		btnPrimero.setVisible(false);
		buscador.setVisible(true);
		buscador.setPromptText("Buscar por ID");
		
	}
	
	public void mostrarSeccionFactura()
	{
		inicioPane.setVisible(false);
		empleadosPane.setVisible(false);
		habitacionesPane.setVisible(false);
		reservacionesPane.setVisible(false);
		facturasPane.setVisible(true);
		tituloPane.setVisible(true);
		titulo.setText("Facturas");
		btnPrimero.setVisible(false);
		buscador.setVisible(true);
		buscador.setPromptText("Buscar por ID");
		
	}
		
	public void buscadorItem()
	{
		buscador.setOnKeyReleased(event ->
		{
			if(sE == true)
			{
				String dato = buscador.getText();
				gridEmpleado.getChildren().clear();
				
				
				if(buscador.getText().isEmpty())
				{
					mostrarEmpleados(gridEmpleado);
				}
				else 
				{					
					if(dato.matches("[+-]?\\d*(\\.\\d+)?"))
					{						
						int id = Integer.parseInt(dato);
						int column = 0;
						int row = 1;
						try 
						{												
							for(int i=0; i<e.size(); i++)
							{
								if(e.get(i).getIdEmpleado() == id)
								{
									FXMLLoader fxml = new FXMLLoader();
									fxml.setLocation(getClass().getResource("/vista/FXML-E-Item2.fxml"));
								
									AnchorPane anchorPane = fxml.load();
									ControllerEItem c = fxml.getController();
									c.establecerDatos(e.get(i));
									c.establecerDatos(gridEmpleado, buscador);
									
									if(column == 3)
									{
										column = 0;
										row++;
									}																		
									
									gridEmpleado.add(anchorPane, column++, row);
									GridPane.setMargin(anchorPane, new Insets(10,6,10,6));
									
									if(row == 1)
									{
										gridEmpleado.setPadding(new Insets(0,0,0,0));
										GridPane.setMargin(anchorPane, new Insets(-10,6,10,6));
									}
								}

								
							}		
						}
						catch (IOException e) {
								
							 e.printStackTrace();
						 }
					
					}
					else
					{
						int column = 0;
						int row = 1;
						try 
						{												
							for(int i=0; i<e.size(); i++)
							{
								
								if(e.get(i).getNombreEmpleado().equals(dato))
								{
									
									FXMLLoader fxml = new FXMLLoader();
									fxml.setLocation(getClass().getResource("/vista/FXML-E-Item.fxml"));
								
									AnchorPane anchorPane = fxml.load();
									ControllerEItem c = fxml.getController();
									c.establecerDatos(e.get(i));
									c.establecerDatos(gridEmpleado, buscador);
									
									if(column == 3)
									{
										column = 0;
										row++;
									}																		
									
									gridEmpleado.add(anchorPane, column++, row);
									GridPane.setMargin(anchorPane, new Insets(10,6,10,6));
									
									if(row == 1)
									{
										gridEmpleado.setPadding(new Insets(0,0,0,0));
										GridPane.setMargin(anchorPane, new Insets(-10,6,10,6));
									}
								}
							
							}		
						}
						catch (IOException e)
						{
								
							 e.printStackTrace();
						}
					}
				}

			}
				
				
				
			if(sH == true)
			{
				
			}
				
		});

	}
	
	
	public void accionBotonEmpleados()
	{
		btnPrimero.setVisible(true);
		btnPrimero.setText("Agregar empleado");
		iconoTitulo.setVisible(true);
		iconoTitulo.setGlyphName("USERS");
		plus.setVisible(true);
		plus.setStyle("-fx-fill: green");
		btnSegundo.setText("");
		btnTercero.setText("");
		
		btnPrimero.setOnAction(evento ->{
			FXMLLoader fxml = new FXMLLoader();
	    	fxml.setLocation(getClass().getResource("/vista/FXML-E-Agregar.fxml"));
	    	try {
				Parent root = fxml.load();
				ControllerEAgregar c = fxml.getController();
				c.establecerDatos(gridEmpleado);
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
		});
	}
	
	
	public void accionBotonHabitacion()
	{
		btnPrimero.setVisible(true);
		btnPrimero.setText("Agregar habitacion");
		iconoTitulo.setVisible(true);
		iconoTitulo.setGlyphName("HOTEL");
		plus.setVisible(true);
		plus.setStyle("-fx-fill: green");
		btnSegundo.setText("");
		btnTercero.setText("");
		
		btnPrimero.setOnAction(evento ->{
			FXMLLoader fxml = new FXMLLoader();
	    	fxml.setLocation(getClass().getResource("/vista/FXML-H-Agregar.fxml"));
	    	try {
				Parent root = fxml.load();
				ControllerHAgregar c = fxml.getController();
				c.establecerGrid(gridHabitaciones);
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
		});
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
	
	
	public void vaciarBuscador(TextField buscador)
	{
		buscador.setText(null);
		
	}


	
	
}
