package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sql.MySQLConnector;

public class Habitacion 
{
	private int idHabitacion;
	private int camasHabitacion;
	private int capacidadHabitacion;	
	private double precioHabitacion;
	private String estatusHabitacion;	private int idEstatus;
	private String tipoHabitacion;		private int idTipo;
	
	
	
	private MySQLConnector con = new MySQLConnector();
	private Connection cn;
	private PreparedStatement pps = null;
	private ResultSet rs = null;
	
	
	public Habitacion()
	{
		
	}

	
	
	public Habitacion(int camasHabitacion, int capacidadHabitacion, double precioHabitacion, int idEstatus, int idTipo) {
		this.camasHabitacion = camasHabitacion;
		this.capacidadHabitacion = capacidadHabitacion;
		this.precioHabitacion = precioHabitacion;
		this.idEstatus = idEstatus;
		this.idTipo = idTipo;
	}

	public Habitacion(int idHabitacion, int camasHabitacion, int capacidadHabitacion, double precioHabitacion, String estatusHabitacion, String tipoHabitacion, int idEstatus, int idTipo) {
		this.idHabitacion = idHabitacion;
		this.camasHabitacion = camasHabitacion;
		this.capacidadHabitacion = capacidadHabitacion;
		this.precioHabitacion = precioHabitacion;
		this.estatusHabitacion = estatusHabitacion;
		this.tipoHabitacion = tipoHabitacion;
		this.idEstatus = idEstatus;
		this.idTipo = idTipo;
		
	}

	public Habitacion(int idHabitacion, int camasHabitacion, int capacidadHabitacion, double precioHabitacion, String estatusHabitacion, String tipoHabitacion) {
		this.idHabitacion = idHabitacion;
		this.camasHabitacion = camasHabitacion;
		this.capacidadHabitacion = capacidadHabitacion;
		this.precioHabitacion = precioHabitacion;
		this.estatusHabitacion = estatusHabitacion;
		this.tipoHabitacion = tipoHabitacion;

		
	}


	public Habitacion(int idHabitacion, double precioHabitacion, String estatusHabitacion) {
		this.idHabitacion = idHabitacion;
		this.precioHabitacion = precioHabitacion;
		this.estatusHabitacion = estatusHabitacion;
	}

	public void crearHabitacion()
	{
		try
		{
			cn = con.Conectar();
			pps = cn.prepareStatement("INSERT INTO habitacion (camasHabitacion, capacidadHabitacion, precioNocheHabitacion, idEstatusHabitacion, idTipoHabitacion) "
					+ "VALUES ("+ camasHabitacion+"," + capacidadHabitacion + "," + precioHabitacion + "," + idEstatus + "," + idTipo +")");
			pps.executeUpdate();
			
			System.out.println("Registro exitoso");
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pps!=null) try {pps.close();}catch(SQLException e) {e.printStackTrace();}
			if(cn!=null) try{cn.close();}catch(SQLException e) {e.printStackTrace();}
		}
		
	}
	
	
	public void modificarHabitacion(int id, int camas, int capacidad, double precio, int idestatus, int idtipo)
	{
		String s = "UPDATE habitacion SET camasHabitacion= 2, capacidadHabitacion = 4, precioNocheHabitacion =  1500.0, idEstatusHabitacion = 2 , idTipoHabitacion= 4 WHERE idHabitacion = 4";
		try
		{
			cn = con.Conectar();
			pps = cn.prepareStatement("UPDATE habitacion SET camasHabitacion= " + camas +", capacidadHabitacion = "+ capacidad +", precioNocheHabitacion = " + precio +" , idEstatusHabitacion = " + idestatus + " , idTipoHabitacion= " + idtipo + " WHERE idHabitacion = " + id);
			
			pps.executeUpdate();
			
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pps!=null) try {pps.close();}catch(SQLException e) {e.printStackTrace();}
			if(cn!=null) try{cn.close();}catch(SQLException e) {e.printStackTrace();}
		}
	}
	
	
	
	
	public void obtenerHabitacion(ArrayList<Habitacion> hab)	
	{
		hab.clear();
		
		String sql = "SELECT h.idHabitacion, m.precioNocheHabitacion, e.habitacionEstado FROM habitacion AS h INNER JOIN modelohabitacion AS m ON h.idModeloHabitacion = m.idModeloHabitacion INNER JOIN estatushab AS e ON h.idEstatusHabitacion = e.idEstatus;";
		String back = "SELECT h.idHabitacion, h.precioHabitacion ,e.habitacionEstado FROM habitacion AS h INNER JOIN estatus AS e ON h.idEstatusHabitacion=e.idEstatus";
		String nuevo = "SELECT h.idHabitacion, h.precioNocheHabitacion, eh.habitacionEstado FROM habitacion AS h INNER JOIN estatushab AS eh ON h.idEstatusHabitacion = eh.idEstatus;";
		String nuevoSQL = "SELECT h.idHabitacion, h.camasHabitacion, h.capacidadHabitacion, h.precioNocheHabitacion, h.idEstatusHabitacion, h.idTipoHabitacion ,e.habitacionEstado, t.descripcionTipo  FROM habitacion AS h INNER JOIN estatushab AS e ON h.idEstatusHabitacion = e.idEstatus INNER JOIN tipohabitacion AS t ON h.idTipoHabitacion = t.idTipoHabitacion ORDER BY idHabitacion";
		
		int id;
		int cam;
		int cap;
		double pre;
		int idEst;
		int idTip;
		String desEst;
		String desTip;
		
		try 
		{		
			cn = con.Conectar();
			pps = cn.prepareStatement(nuevoSQL);	
			rs = pps.executeQuery();		
			
			while(rs.next()) {

				id = rs.getInt(1);
				cam = rs.getInt(2);
				cap = rs.getInt(3);
				pre = rs.getDouble(4);
				idEst = rs.getInt(5);
				idTip = rs.getInt(6);
				desEst = rs.getString(7);
				desTip = rs.getString(8);
				
				hab.add(new Habitacion(id, cam, cap, pre, desEst, desTip, idEst, idTip));
				
			}

		}
		catch (SQLException e) 
		{			
			e.printStackTrace();
		}
		finally
		{
			if(pps != null)
				try {pps.close();} catch (SQLException e) {e.printStackTrace();}
				
			if(cn != null)
				try{cn.close();} catch(SQLException e) {e.printStackTrace();}
		}
			
	}
	
	
	
	public ObservableList<Habitacion> obtenerHab()	
	{
		
		ObservableList<Habitacion> lista = FXCollections.observableArrayList();
		
		
		String nuevoSQL = "SELECT h.idHabitacion, h.camasHabitacion, h.capacidadHabitacion, h.precioNocheHabitacion, h.idEstatusHabitacion, h.idTipoHabitacion ,e.habitacionEstado, t.descripcionTipo  FROM habitacion AS h INNER JOIN estatushab AS e ON h.idEstatusHabitacion = e.idEstatus INNER JOIN tipohabitacion AS t ON h.idTipoHabitacion = t.idTipoHabitacion WHERE h.idEstatusHabitacion = 2 ";
		
		int id;
		int cam;
		int cap;
		double pre;
		int idEst;
		int idTip;
		String desEst;
		String desTip;
		
		try 
		{		
			cn = con.Conectar();
			pps = cn.prepareStatement(nuevoSQL);	
			rs = pps.executeQuery();		
			
			while(rs.next()) {

				id = rs.getInt(1);
				cam = rs.getInt(2);
				cap = rs.getInt(3);
				pre = rs.getDouble(4);
				idEst = rs.getInt(5);
				idTip = rs.getInt(6);
				desEst = rs.getString(7);
				desTip = rs.getString(8);
				
				lista.add(new Habitacion(id, cam, cap, pre, desEst, desTip, idEst, idTip));
				
			}

		}
		catch (SQLException e) 
		{			
			e.printStackTrace();
		}
		finally
		{
			if(pps != null)
				try {pps.close();} catch (SQLException e) {e.printStackTrace();}
				
			if(cn != null)
				try{cn.close();} catch(SQLException e) {e.printStackTrace();}
		}
		
		return lista;
			
	}
	
	
	
	
	public ObservableList<Habitacion> obtenerHabitaciones()
	{
		ObservableList<Habitacion> lista = FXCollections.observableArrayList();
		
		String sql = "SELECT h.idHabitacion, m.precioNocheHabitacion, e.habitacionEstado FROM habitacion AS h INNER JOIN modelohabitacion AS m ON h.idModeloHabitacion = m.idModeloHabitacion INNER JOIN estatushab AS e ON h.idEstatusHabitacion = e.idEstatus;";
		String back = "SELECT h.idHabitacion, h.precioHabitacion ,e.habitacionEstado FROM habitacion AS h INNER JOIN estatus AS e ON h.idEstatusHabitacion=e.idEstatus";
		String nuevo = "SELECT h.idHabitacion, h.precioNocheHabitacion, eh.habitacionEstado FROM habitacion AS h INNER JOIN estatushab AS eh ON h.idEstatusHabitacion = eh.idEstatus;";
		String bueno = "SELECT th.descripcionTipo, h.idHabitacion, h.camasHabitacion, h.capacidadHabitacion, h.precioNocheHabitacion, eh.habitacionEstado FROM habitacion AS h INNER JOIN estatushab AS eh ON h.idEstatusHabitacion = eh.idEstatus INNER JOIN tipohabitacion AS th ON h.idTipoHabitacion = th.idTipoHabitacion ORDER BY th.descripcionTipo";
		int id;
		int camas;
		int capacidad;
		double pre;
		String des;
		String tipo;
		try 
		{		
			cn = con.Conectar();
			pps = cn.prepareStatement(bueno);	
			ResultSet rs = pps.executeQuery();		
			
			while(rs.next()) {

				tipo = rs.getString(1);
				id = rs.getInt(2);
				camas = rs.getInt(3);
				capacidad = rs.getInt(4);				
				pre = rs.getDouble(5);
				des = rs.getString(6);
				lista.add(new Habitacion(id, camas, capacidad, pre,des, tipo));
				
			}

		}
		catch (SQLException e) 
		{			
			e.printStackTrace();
		}
		finally
		{
			if(pps != null)
				try {pps.close();} catch (SQLException e) {e.printStackTrace();}
				
			if(cn != null)
				try{cn.close();} catch(SQLException e) {e.printStackTrace();}
		}
			
		
		return lista;
		
	}
	
	public void actualizarEstado(int id, int estatus)
	{
		
		/*			
		 *	1 = Ocupada
		 * 	2 = Disponible
		 * 	3 = No disponible
		 * 
		 * 
		*/
		try
		{
			cn = con.Conectar();
			pps = cn.prepareStatement("UPDATE habitacion SET idEstatusHabitacion = "+ estatus + " WHERE idHabitacion = " + id +"");
			pps.executeLargeUpdate();
			
			
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pps != null)
				try {pps.close();} catch (SQLException e) {e.printStackTrace();}
				
			if(cn != null)
				try{cn.close();} catch(SQLException e) {e.printStackTrace();}
		}
	}
	
	public int getIdHabitacion() {
		return idHabitacion;
	}

	public void setIdHabitacion(int idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	public String getEstatusHabitacion() {
		return estatusHabitacion;
	}

	public void setEstatusHabitacion(String estatusHabitacion) {
		this.estatusHabitacion = estatusHabitacion;
	}

	public double getPrecioHabitacion() {
		return precioHabitacion;
	}

	public void setPrecioHabitacion(double precioHabitacion) {
		this.precioHabitacion = precioHabitacion;
	}

	
	
	public int getCamasHabitacion() {
		return camasHabitacion;
	}



	public void setCamasHabitacion(int camasHabitacion) {
		this.camasHabitacion = camasHabitacion;
	}



	public String getTipoHabitacion() {
		return tipoHabitacion;
	}



	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}



	public int getCapacidadHabitacion() {
		return capacidadHabitacion;
	}



	public void setCapacidadHabitacion(int capacidadHabitacion) {
		this.capacidadHabitacion = capacidadHabitacion;
	}



	public int getIdEstatus() {
		return idEstatus;
	}



	public void setIdEstatus(int idEstatus) {
		this.idEstatus = idEstatus;
	}



	public int getIdTipo() {
		return idTipo;
	}



	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}



	@Override
	public String toString() {
		return "Habitacion [idHabitacion=" + idHabitacion + ", precioHabitacion=" + precioHabitacion
				+ ", estatusHabitacion=" + estatusHabitacion + "]";
	}

	
	
}
