package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sql.MySQLConnector;

public class Modelo 
{
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection cn = null;
	private MySQLConnector sql = new MySQLConnector();
	
	private int idModelo;
	private String descripcionModelo;
	
	
	public Modelo(int idModelo, String descripcionModelo)
	{
		this.idModelo = idModelo;
		this.descripcionModelo = descripcionModelo;
		
	}
	
	
	public Modelo() {
		
	}


	public ObservableList<Modelo> traerModelos()
	{
		ObservableList<Modelo> lista = FXCollections.observableArrayList();
		
		cn = sql.Conectar();
		int id = 0;
		String modelo = null;
		
		try
		{
			ps = cn.prepareStatement("SELECT idTipoHabitacion, descripcionTipo FROM tipoHabitacion");
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				id = rs.getInt(1);
				modelo = rs.getString(2);
				
				lista.add(new Modelo(id,modelo));
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
		
		
		
		return lista;
	}

	
	

	public int getIdModelo() {
		return idModelo;
	}


	public String getDescripcionModelo() {
		return descripcionModelo;
	}


	@Override
	public String toString() {
		return  descripcionModelo ;
	}
	
	
	
	
}
