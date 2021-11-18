package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sql.MySQLConnector;

public class ModeloTabla 
{
	String matriculas;
	String nombres;
	String edads;
	String sexos;
	String telefonos;
	String puestos;
	MySQLConnector con = new MySQLConnector();
	Connection cn;
	
	public ModeloTabla()
	{
		
	}
	
	public ModeloTabla(String matricula, String nombre, String edad, String sexo, String telefono, String puesto) {
	
		this.matriculas = matricula;
		this.nombres = nombre;
		this.edads = edad;
		this.sexos = sexo;
		this.telefonos = telefono;
		this.puestos = puesto;
	}
	
	public ObservableList<ModeloTabla> traerEmpleados()
	{
		
		ObservableList<ModeloTabla> obs = FXCollections.observableArrayList();
		cn = con.Conectar();
		String id1;
		String nombre1;
		String edad1;
		String sexo1;		
		String telefono1;
		String idPuesto1;
		try {		
			Statement st = cn.createStatement();	
			ResultSet rs = st.executeQuery("SELECT * FROM empleado");			
			
			while(rs.next()) {
				id1 = rs.getString(1);
				nombre1 = rs.getString(2);
				edad1 = rs.getString(3);
				sexo1 = rs.getString(4);
				telefono1 = rs.getString(6);
				idPuesto1 = rs.getString(7);
				obs.add(new ModeloTabla(id1, nombre1, edad1, sexo1, telefono1, idPuesto1));
				
			}
			
			st.close();
			rs.close();
			}catch (SQLException e) {
			
			e.printStackTrace();
		}
			con.Desconectar();
		
		return obs;
	}
	



	public String getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(String matriculas) {
		this.matriculas = matriculas;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getEdads() {
		return edads;
	}

	public void setEdads(String edads) {
		this.edads = edads;
	}

	public String getSexos() {
		return sexos;
	}

	public void setSexos(String sexos) {
		this.sexos = sexos;
	}

	public String getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}

	public String getPuestos() {
		return puestos;
	}

	public void setPuestos(String puestos) {
		this.puestos = puestos;
	}


	

	
}
