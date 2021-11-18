package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import sql.MySQLConnector;

public class Factura 
{
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection cn = null;
	private MySQLConnector sql = new MySQLConnector();
	
	private int idFactura;
	private int idReservacion;
	private Timestamp fechaFactura;
	private double precioTotal;
	
	
	
	
	
	public Factura(int idFactura, int idReservacion, Timestamp fechaFactura, double precioTotal) 
	{
		this.idFactura = idFactura;
		this.idReservacion = idReservacion;
		this.fechaFactura = fechaFactura;
		this.precioTotal = precioTotal;
	}
	
	
	
	public Factura(int idReservacion, Timestamp fechaFactura, double precioTotal)
	{
		this.idReservacion = idReservacion;
		this.fechaFactura = fechaFactura;
		this.precioTotal = precioTotal;
	}

	
	public Factura() {
		// TODO Auto-generated constructor stub
	}



	public void insertarFactura()
	{
		try
		{
			cn = sql.Conectar();
			ps = cn.prepareStatement("INSERT INTO factura(idReservacion, fechaFactura, precioTotal) VALUES(?,?,?)");
			ps.setInt(1, idReservacion);
			ps.setTimestamp(2, fechaFactura, Calendar.getInstance());
			ps.setDouble(3, precioTotal);
			
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
	
	
	public void obtenerFacturas(ArrayList<Factura> fac)
	{
		try
		{
			cn = sql.Conectar();
			ps = cn.prepareStatement("SELECT * FROM factura");
			rs = ps.executeQuery();
			while(rs.next())
			{
				fac.add(new Factura(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3), rs.getDouble(4)));
			}
			
	
			
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


	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public int getIdReservacion() {
		return idReservacion;
	}
	public void setIdReservacion(int idReservacion) {
		this.idReservacion = idReservacion;
	}
	public Timestamp getFechaFactura() {
		return fechaFactura;
	}
	public void setFechaFactura(Timestamp fechaFactura) {
		this.fechaFactura = fechaFactura;
	}
	public double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	
	
	
	
	
}
