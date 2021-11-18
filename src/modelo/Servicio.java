package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sql.MySQLConnector;

public class Servicio 
{
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection cn = null;
	private MySQLConnector sql = new MySQLConnector();
	
	private int idServicio;
	private String descripcionServicio;
	private double precioServicio;
	
	private ArrayList<Servicio> serviciosLst = new ArrayList<Servicio>();

	
	public Servicio(int idServicio, String descripcionServicio, double precioServicio) {
		this.idServicio = idServicio;
		this.descripcionServicio = descripcionServicio;
		this.precioServicio = precioServicio;
	}


	public Servicio(String descripcionServicio, double precioServicio) {
		this.descripcionServicio = descripcionServicio;
		this.precioServicio = precioServicio;
	}
	
	
	public Servicio() {
		// TODO Auto-generated constructor stub
	}


	public void insertarServicio()
	{
		try
		{
			cn = sql.Conectar();
			ps = cn.prepareStatement("INSERT INTO servicio(descripcionServicio, precioServicio) VALUES(?,?)");
			ps.setString(1, descripcionServicio);
			ps.setDouble(2, precioServicio);
			
			ps.executeUpdate();
			
			
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
		
		
	}
		
	public ArrayList<Servicio> obtenerServicios()
	{
		try
		{
			int id;
			String des;
			double pre;
			cn = sql.Conectar();
			ps = cn.prepareStatement("SELECT idServicio, descripcionServicio, precioServicio FROM servicio");
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				
				id = rs.getInt(1);
				des = rs.getString(2);
				pre = rs.getDouble(3);
				
				serviciosLst.add(new Servicio(id, des, pre));
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
		
		
		return serviciosLst;
	}
	
	public void borrarServicio(int id)
	{
		try
		{
			cn = sql.Conectar();
			ps = cn.prepareStatement("DELETE FROM servicio WHERE idServicio='" + id + "'");			
			ps.executeUpdate();
			
			
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
	}


	public int getIdServicio() {
		return idServicio;
	}


	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}


	public String getDescripcionServicio() {
		return descripcionServicio;
	}


	public void setDescripcionServicio(String descripcionServicio) {
		this.descripcionServicio = descripcionServicio;
	}


	public double getPrecioServicio() {
		return precioServicio;
	}


	public void setPrecioServicio(double precioServicio) {
		this.precioServicio = precioServicio;
	}
	
	
	
	
}

