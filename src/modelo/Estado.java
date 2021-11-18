package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sql.MySQLConnector;

public class Estado 
{
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection cn = null;
	private MySQLConnector sql = new MySQLConnector();
	
	private int idEstado;
	private String estadoDes;
	
	public Estado()
	{
		
	}
	
	public Estado(int idEstado, String estadoDes)
	{
		this.idEstado = idEstado;
		this.estadoDes = estadoDes;
	}
	
	public ObservableList<Estado> obtenerEstados()
	{
		ObservableList<Estado> lista = FXCollections.observableArrayList();
		
		int id;
		String des;
		
		try
		{
			cn = sql.Conectar();
			ps = cn.prepareStatement("SELECT * FROM estatushab");
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				id = rs.getInt(1);
				des = rs.getString(2);
				lista.add(new Estado(id,des));
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(ps!=null) try {ps.close();}catch(SQLException e) {e.printStackTrace();}
			if(cn != null) try {cn.close();}catch(SQLException e) {e.printStackTrace();}
			
		}
		
		
		return lista;
	}
	

	public int getIdEstado() {
		return idEstado;
	}

	public String getEstadoDes() {
		return estadoDes;
	}

	@Override
	public String toString() {
		return  estadoDes ;
	}
	
	
	
}
