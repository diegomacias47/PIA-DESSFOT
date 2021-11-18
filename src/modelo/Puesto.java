package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sql.MySQLConnector;

public class Puesto 
{
	
	/*  Attributes */
	private int idPuesto;
	private String descripcionPuesto;	
	private double pagaDiaPuesto;

	
	private MySQLConnector con = new MySQLConnector();
	private Connection cn;
	private PreparedStatement pps = null;
	private ResultSet rs = null;
	
	/*		Constructor	 */
	
	public Puesto()
	{
		
	}
	
	public Puesto(int idPuesto, String descripcionPuesto) {

		this.idPuesto = idPuesto;
		this.descripcionPuesto = descripcionPuesto;
	}
	
	public Puesto(int idPuesto, String descripcionPuesto, double pagaDiaPuesto) {

		this.idPuesto = idPuesto;
		this.descripcionPuesto = descripcionPuesto;
	}

	
	/*			Methods			*/
	
	public ObservableList<Puesto> obtenerPuesto()
	{
		ObservableList<Puesto> puestos = FXCollections.observableArrayList();
		
		try
		{	
			cn = con.Conectar();
			pps = cn.prepareStatement("SELECT * FROM puesto");	
			rs = pps.executeQuery();		
			int id;
			String des;
			while(rs.next()) {
				id = Integer.parseInt(rs.getString(1));
				des = rs.getString(2);
				
				puestos.add(new Puesto(id,des));
				
			}
			

			}catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally
		{
			if(pps != null)
				try {pps.close();} catch (SQLException e) {e.printStackTrace();}
				
			if(cn != null)
				try{cn.close();} catch(SQLException e) {e.printStackTrace();}
		}
			
		return puestos;
		
	}
	
	public ObservableList<Puesto> obtenerPuestos()
	{
		ObservableList<Puesto> puestos = FXCollections.observableArrayList();
		cn = con.Conectar();
		String sql = "SELECT * FROM puesto";
		try 
		{		
			pps = cn.prepareStatement(sql);	
			rs = pps.executeQuery();		
			int id;
			String des;
			double pago;
			while(rs.next()) 
			{
				id = Integer.parseInt(rs.getString(1));
				des = rs.getString(2);
				pago = rs.getDouble(3);
				puestos.add(new Puesto(id,des,pago));
				
			}

		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally
		{
			if(pps != null)
				try {pps.close();} catch (SQLException e) {e.printStackTrace();}
				
			if(cn != null)
				try{cn.close();} catch(SQLException e) {e.printStackTrace();}
		}
			
		return puestos;
		
	}
	
	
	/* GETTERS and SETTERS */
	
	
	
	public int getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(int idPuesto) {
		this.idPuesto = idPuesto;
	}

	public String getDescripcionPuesto() {
		return descripcionPuesto;
	}

	public void setDescripcionPuesto(String descripcionPuesto) {
		this.descripcionPuesto = descripcionPuesto;
	}	
	
	public double getPagaDiaPuesto() {
		return pagaDiaPuesto;
	}

	public void setPagaDiaPuesto(double pagaDiaPuesto) {
		this.pagaDiaPuesto = pagaDiaPuesto;
	}

	@Override
	public String toString() {
		return descripcionPuesto;
	}
	
	

}
