package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class MySQLConnector 
{

	Connection cn;
	
	public Connection Conectar() {
		
		try {
			
			cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/dessoft_v3?serverTimezone=UTC","root","8VdFxva6"); 
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		return cn;
	}
	
    public Connection Desconectar(){
        try{
        	
            cn.close();            
        }catch(SQLException ex){
            
        }return cn;
    }
    
    
	public void registrarCliente(String nombre, String celular) {
		Conectar();
		long num1 = 0;
		
		try {
			num1 = System.nanoTime();
			PreparedStatement pps = cn.prepareStatement("INSERT INTO cliente(nombreCliente, celularCliente) VALUES(?,?)");

			
			pps.setString(1, nombre);
			pps.setString(2, celular);

			pps.executeUpdate();
			pps.close();
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Los campos no fueron llenados");
			
		}											
		
		Desconectar();
		
		long num2 = System.nanoTime();
		System.out.println(num2-num1);
	}
	
	
	
	
}
