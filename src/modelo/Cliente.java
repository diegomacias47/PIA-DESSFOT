package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sql.MySQLConnector;

public class Cliente 
{
	private int idCliente;
	private	String nombre;
	private	String celular;
	private MySQLConnector sql = new MySQLConnector();
	private Connection cn;
	private PreparedStatement pps;
	private ResultSet rs = null;
	
	public Cliente()
	{
		
	}
	
	public Cliente(int idCliente, String nombre, String celular)
	{
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.celular = celular;
	}
	
	public Cliente(String nombre, String celular) {
		this.nombre = nombre;
		this.celular = celular;
	}
	
	public void insertarCliente()
	{
		
		try
		{
			cn = sql.Conectar();
			pps = cn.prepareStatement("INSERT INTO cliente(nombreCliente, celularCliente) VALUES(?,?)");
			pps.setString(1,nombre);
			pps.setString(2, celular);
			
			pps.executeUpdate();
		
		}
		catch(SQLException e)
		{
			
		}
		finally
		{
			if(pps != null)
				try {pps.close();} catch (SQLException e) {e.printStackTrace();}
				
			if(cn != null)
				try{cn.close();} catch(SQLException e) {e.printStackTrace();}
		}
	
	
	}
	
	public int obtenerIdCliente()
	{
		int id = 0;
		
		try
		{
			cn = sql.Conectar();
			pps = cn.prepareStatement("SELECT idCliente FROM cliente ORDER BY idCliente DESC LIMIT 1");
			rs = pps.executeQuery();
			while(rs.next())
			{
				id = rs.getInt(1);
				
			}
			
			pps.close();
			rs.close();
		}
		catch(SQLException e)
		{
			
		}
		finally
		{
			if(pps != null)
				try {pps.close();} catch (SQLException e) {e.printStackTrace();}
				
			if(cn != null)
				try{cn.close();} catch(SQLException e) {e.printStackTrace();}
		}
		
		return id;
	}
	
	public Cliente obtenerCliente(int id)
	{
		
		Cliente cli = null;
		int idC;
		String nom;
		String tel;
		try
		{
			cn = sql.Conectar();
			pps = cn.prepareStatement("SELECT * FROM cliente WHERE idCliente =" + id);
			rs = pps.executeQuery();
			while(rs.next())
			{
				idC = rs.getInt(1);
				nom = rs.getString(2);
				tel = rs.getString(3);
				
				cli = new Cliente(idC, nom, tel);
			}
			
			pps.close();
			rs.close();
		}
		catch(SQLException e)
		{
			
		}
		finally
		{
			if(pps != null)
				try {pps.close();} catch (SQLException e) {e.printStackTrace();}
				
			if(cn != null)
				try{cn.close();} catch(SQLException e) {e.printStackTrace();}
		}
		
		return cli;
	}
	
	
	public String getNombre() {
		return nombre;
	}

	public String getCelular() {
		return celular;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	
	
	
}
