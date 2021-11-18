package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import sql.MySQLConnector;

public class DetalleRS 
{
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection cn = null;
	private MySQLConnector sql = new MySQLConnector();
	
	private int idDetalle;
	private int idReservacion;
	private int idServicio;
	private Timestamp fecha;
	private double precio;
	private int contador;
	
	
	public DetalleRS(int idDetalle, int idReservacion, int idServicio, Timestamp fecha, double precio, int contador) {
		this.idDetalle = idDetalle;
		this.idReservacion = idReservacion;
		this.idServicio = idServicio;
		this.fecha = fecha;
		this.precio = precio;
		this.contador = contador;

	}
	
	public DetalleRS(int idReservacion, int idServicio, Timestamp fecha) {
		this.idReservacion = idReservacion;
		this.idServicio = idServicio;
		this.fecha = fecha;
		this.precio = precio;

	}
	
	public DetalleRS() {
	
	}

	
	public void insertarDetalleRS()
	{
		try
		{
			cn = sql.Conectar();
			ps = cn.prepareStatement("INSERT INTO detallers(idReservacion, idServicio, fechaServicio) VALUES(?,?,?)");
			ps.setInt(1, idReservacion);
			ps.setInt(2, idServicio);			
			ps.setTimestamp(3,fecha, Calendar.getInstance());
		
			
			ps.executeUpdate();
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			if(ps != null)
				try {ps.close();} catch (SQLException e) {e.printStackTrace();}
				
			if(cn != null)
				try{cn.close();} catch(SQLException e) {e.printStackTrace();}
		}
	}

	public ArrayList<DetalleRS> obtenerDetallesRS(int id)
	{
		
		ArrayList<DetalleRS> lista = new ArrayList<DetalleRS>();
			
		try
		{
			int idD;
			int idR;
			int idS;
			Timestamp fecha;
			double pre;
			int cont=0;
			cn = sql.Conectar();
			ps = cn.prepareStatement("SELECT d.idDetalle, d.idReservacion, d.idServicio, d.fechaServicio, s.precioServicio FROM detallers AS d INNER JOIN servicio AS s ON d.idServicio = s.idServicio WHERE d.idReservacion = '" + id + "'");
			rs = ps.executeQuery();
			
			
			while(rs.next())
			{
				cont++;
				idD = rs.getInt(1);
				idR = rs.getInt(2);
				idS = rs.getInt(3);
				fecha = rs.getTimestamp(4, Calendar.getInstance());
				pre = rs.getDouble(5);
				
				lista.add(new DetalleRS(idD, idR, idS, fecha, pre, cont));			
			}

		}
		catch(SQLException e)
		{
			
		}
		finally 
		{
			if(ps != null)
				try {ps.close();} catch (SQLException e) {e.printStackTrace();}
				
			if(cn != null)
				try{cn.close();} catch(SQLException e) {e.printStackTrace();}
		}
		
		return lista;
	}
	


	public int getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public int getIdReservacion() {
		return idReservacion;
	}

	public void setIdReservacion(int idReservacion) {
		this.idReservacion = idReservacion;
	}

	public int getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public Timestamp getFecha() {
		return fecha;
	}
	
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}
	
	
	
	

}
