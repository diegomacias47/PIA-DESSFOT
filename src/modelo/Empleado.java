package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sql.MySQLConnector;

public class Empleado 
{
	
	/*  Attributes */
	private int idEmpleado;
	private String nombreEmpleado;
	private String edadEmpleado;
	private String sexoEmpleado;	
	private String direccionEmpleado;
	private String telefonoEmpleado;
	private int idPuestoEmpleado;
	private String puestoEmpleado;
	
	
	private MySQLConnector con = new MySQLConnector();
	private Connection cn;
	private PreparedStatement pps=null;
	private ResultSet rs = null;
	
	/*		Constructor	 */
	
	public Empleado()
	{
		
	}
	
	public Empleado(Empleado emp)
	{
		this.idEmpleado = emp.idEmpleado;
		this.nombreEmpleado = emp.nombreEmpleado;
		this.edadEmpleado = emp.edadEmpleado;		
		this.sexoEmpleado = emp.sexoEmpleado;		
		this.direccionEmpleado = emp.direccionEmpleado;
		this.telefonoEmpleado = emp.telefonoEmpleado;
		this.idPuestoEmpleado = emp.idPuestoEmpleado;
	}
	
	public Empleado(int idEmpleado,String nombreEmpleado, String edadEmpleado, String sexoEmpleado, String direccionEmpleado, String telefonoEmpleado, String puestoEmpleado) {
		
		this.idEmpleado = idEmpleado;
		this.nombreEmpleado = nombreEmpleado;
		this.edadEmpleado = edadEmpleado;		
		this.sexoEmpleado = sexoEmpleado;		
		this.direccionEmpleado = direccionEmpleado;
		this.telefonoEmpleado = telefonoEmpleado;
		this.puestoEmpleado = puestoEmpleado;
	}
	
	public Empleado(int idEmpleado,String nombreEmpleado, String edadEmpleado, String sexoEmpleado, String direccionEmpleado, String telefonoEmpleado, int idPuestoEmpleado) {
		
		this.idEmpleado = idEmpleado;
		this.nombreEmpleado = nombreEmpleado;
		this.edadEmpleado = edadEmpleado;		
		this.sexoEmpleado = sexoEmpleado;		
		this.direccionEmpleado = direccionEmpleado;
		this.telefonoEmpleado = telefonoEmpleado;
		this.idPuestoEmpleado = idPuestoEmpleado;
	}
	
	public Empleado(String nombreEmpleado, String edadEmpleado, String sexoEmpleado, String direccionEmpleado, String telefonoEmpleado, int idPuestoEmpleado) {
		
		this.nombreEmpleado = nombreEmpleado;
		this.edadEmpleado = edadEmpleado;		
		this.sexoEmpleado = sexoEmpleado;		
		this.direccionEmpleado = direccionEmpleado;
		this.telefonoEmpleado = telefonoEmpleado;
		this.idPuestoEmpleado = idPuestoEmpleado;
	}
		
	/*			Methods			*/
	
	public void registrarEmpleado() {
		
		Calendar calendario;
		Date hola;
		
		try {
			cn = con.Conectar();
			pps = cn.prepareStatement("INSERT INTO empleado(nombreEmpleado, edadEmpleado, sexoEmpleado , direccionEmpleado, telefonoEmpleado,idPuestoEmpleado) VALUES(?,?,?,?,?,?)");
			
			pps.setString(1, nombreEmpleado);
			pps.setString(2, edadEmpleado);				
			pps.setString(3, sexoEmpleado);	
			pps.setString(4, direccionEmpleado);
			pps.setString(5, telefonoEmpleado);
			pps.setInt(6, idPuestoEmpleado);
		
			pps.executeUpdate();
			
			
		
		
		} catch (SQLException e) {
			
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
	
	public void modificarEmpleados(Empleado emp)
	{
		
		
		try 
		{		
			cn = con.Conectar();
			pps = cn.prepareStatement("UPDATE empleado SET nombreEmpleado = '" + emp.getNombreEmpleado() + "', edadEmpleado='" + emp.getEdadEmpleado() +"', sexoEmpleado ='"+ emp.getSexoEmpleado() +"'"
					+ ",direccionEmpleado='" + emp.getDireccionEmpleado() + "', telefonoEmpleado='"+ emp.getTelefonoEmpleado()+"', idPuestoEmpleado='" + emp.getIdPuestoEmpleado()+"' WHERE idEmpleado='" + emp.getIdEmpleado() + "';");
			pps.executeUpdate();			
			System.out.println("Registro modificado");

			
			
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
	
	public   ObservableList<ModeloTabla> traerEmpleados()
	{
		ObservableList<ModeloTabla> obs = FXCollections.observableArrayList();
		cn = con.Conectar();
		String id;
		String nombre;
		String edad;
		String sexo;		
		String telefono;
		String idPuesto;
		try {		
			Statement st = cn.createStatement();	
			ResultSet rs = st.executeQuery("SELECT * FROM empleado");		
			
			while(rs.next()) {
				id = rs.getString(1);
				nombre = rs.getString(2);
				edad = rs.getString(3);
				sexo = rs.getString(4);
				telefono = rs.getString(6);
				idPuesto = rs.getString(7);
				obs.add(new ModeloTabla(id, nombre, edad, sexo, telefono, idPuesto));
				
			}
			
			st.close();
			rs.close();
			}catch (SQLException e) {
			
			e.printStackTrace();
		}
			con.Desconectar();
		
		return obs;
	}
	
	
	public void obtenerEmpleados(ArrayList<Empleado> emp)	
	{
		emp.clear();
		
		int id;
		String nombre;
		String edad;
		String sexo;	
		String direccion;
		String telefono;
		String puesto;
		try {	
			cn = con.Conectar();
			pps = cn.prepareStatement("SELECT emp.idEmpleado, emp.nombreEmpleado, emp.edadEmpleado, emp.sexoEmpleado, emp.direccionEmpleado, emp.telefonoEmpleado, p.descripcionPuesto  FROM empleado AS emp INNER JOIN puesto AS p ON emp.idPuestoEmpleado = p.idPuesto ORDER BY idEmpleado");	
			ResultSet rs = pps.executeQuery();		
			
			while(rs.next()) {
				id = rs.getInt(1);
				nombre = rs.getString(2);
				edad = rs.getString(3);
				sexo = rs.getString(4);
				direccion = rs.getString(5);
				telefono = rs.getString(6);
				puesto = rs.getString(7);
				emp.add(new Empleado(id, nombre, edad, sexo,direccion ,telefono, puesto));
				
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
			
	}

	
	public void modificarEmpleados(int matriculaBuscadora, int matricula, String nombre, String edad, String sexo, String direccion, String telefono, int idPuesto)
	{
		
		try 
		{		
			cn = con.Conectar();
			pps = cn.prepareStatement("UPDATE empleado SET idEmpleado='" + matricula + "', nombreEmpleado = '" + nombre + "', edadEmpleado='" + edad +"', sexoEmpleado ='"+ sexo +"'"
					+ ",direccionEmpleado='" + direccion + "', telefonoEmpleado='"+ telefono+"', idPuestoEmpleado='" + idPuesto+"' WHERE idEmpleado='" + matriculaBuscadora + "';");
			pps.executeUpdate();			
			

			
			
		}catch (SQLException e) 
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
	
	
	public ArrayList<Integer> traerMatriculas()
	{
		ArrayList<Integer> matriculas = new ArrayList<Integer>();
		cn = con.Conectar();
		int id;
		try {		
			Statement st = cn.createStatement();	
			ResultSet rs = st.executeQuery("SELECT * FROM empleado");		
			
			while(rs.next()) {
				id = rs.getInt(1);
				matriculas.add(id);
				
			}
			
			st.close();
			rs.close();
			}catch (SQLException e) {
			
			e.printStackTrace();
		}
		con.Desconectar();
		return matriculas;
	}
	
	public Empleado traerEmpleadoConcreto(int id)
	{
		cn = con.Conectar();
		Empleado emp = null;
		try {
			PreparedStatement ps = cn.prepareStatement("SELECT * FROM empleado WHERE idEmpleado='" + id + "'");			
			ResultSet rs = ps.executeQuery();
			int matricula;
			String nombre;
			String edad;
			String sexo;
			String domicilio;
			String telefono;
			int puesto;
			
			while(rs.next())
			{
				
				matricula = rs.getInt(1);
				
				if(matricula == id) {
					matricula = rs.getInt(1);
					nombre = rs.getString(2);
					edad = rs.getString(3);
					sexo = rs.getString(4);
					domicilio = rs.getString(5);
					telefono = rs.getString(6);
					puesto = rs.getInt(7);
					emp = new Empleado(matricula, nombre, edad, sexo, domicilio, telefono, puesto);
				}else {
					System.out.println("No se encontro el ID");
				
				}

				
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		con.Desconectar();
		return emp;
	}
	
	public void eliminarEmpleado(int id)
	{
		
		try 
		{
			cn = con.Conectar();
			pps = cn.prepareStatement("DELETE FROM empleado WHERE idEmpleado='" + id + "'");
			pps.executeUpdate();
		
			
			
		} catch (SQLException e) {
			
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

	
	
	/* Getters and Setters */
	
	public String getPuestoEmpleado() {
		return puestoEmpleado;
	}

	public void setPuestoEmpleado(String puestoEmpleado) {
		this.puestoEmpleado = puestoEmpleado;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public String getEdadEmpleado() {
		return edadEmpleado;
	}

	public void setEdadEmpleado(String edadEmpleado) {
		this.edadEmpleado = edadEmpleado;
	}

	public String getSexoEmpleado() {
		return sexoEmpleado;
	}

	public void setSexoEmpleado(String sexoEmpleado) {
		this.sexoEmpleado = sexoEmpleado;
	}

	public String getDireccionEmpleado() {
		return direccionEmpleado;
	}

	
	
	public void setDireccionEmpleado(String direccionEmpleado) {
		this.direccionEmpleado = direccionEmpleado;
	}

	public String getTelefonoEmpleado() {
		return telefonoEmpleado;
	}

	public void setTelefonoEmpleado(String telefonoEmpleado) {
		this.telefonoEmpleado = telefonoEmpleado;
	}

	public int getIdPuestoEmpleado() {
		return idPuestoEmpleado;
	}

	public void setIdPuestoEmpleado(int idPuestoEmpleado) {
		this.idPuestoEmpleado = idPuestoEmpleado;
	}
	
	
}
