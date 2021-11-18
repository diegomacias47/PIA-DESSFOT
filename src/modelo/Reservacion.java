package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import sql.MySQLConnector;

public class Reservacion 
{
	private int idReservacion;
	private int idClienteReservacion;
	private int idHabitacion;
	private String nombreCliente;   private String celularCliente;
	private int diasReservacion;
	private int cantidadPersonas;
	private Timestamp fechaReservacion;
	private Date fechaExpiracion;	
	private double precioInicialReservacion;
	private String estadoReservacion;
	private int idEstado;
	
	private MySQLConnector sql = new MySQLConnector();
	private Connection cn;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public Reservacion()
	{
		
	}
	public Reservacion(int idReservacion)
	{
		this.idReservacion = idReservacion;
	}
	
	
	
	
	public Reservacion(int idClienteReservacion, int idHabitacion, String nombreCliente, int diasReservacion, int cantidadPersonas,
			Timestamp fechaReservacion, Date fechaExpiracion, double precioInicialReservacion, String estadoReservacion,
			int idEstado) {
		this.idClienteReservacion = idClienteReservacion;
		this.idHabitacion = idHabitacion;
		this.nombreCliente = nombreCliente;
		this.diasReservacion = diasReservacion;
		this.cantidadPersonas = cantidadPersonas;
		this.fechaReservacion = fechaReservacion;
		this.fechaExpiracion = fechaExpiracion;
		this.precioInicialReservacion = precioInicialReservacion;
		this.estadoReservacion = estadoReservacion;
		this.idEstado = idEstado;
	}	
	
	public Reservacion(int idReservacion, int idClienteReservacion, int idHabitacion, String nombreCliente, int diasReservacion, int cantidadPersonas, Timestamp fechaReservacion, Date fechaExpiracion, double precioInicialReservacion, String estadoReservacion, int idEstado) {
		this.idReservacion = idReservacion;
		this.idClienteReservacion = idClienteReservacion;
		this.idHabitacion = idHabitacion;
		this.nombreCliente = nombreCliente;
		this.diasReservacion = diasReservacion;
		this.cantidadPersonas = cantidadPersonas;
		this.fechaReservacion = fechaReservacion;
		this.fechaExpiracion = fechaExpiracion;
		this.precioInicialReservacion = precioInicialReservacion;
		this.estadoReservacion = estadoReservacion;
		this.idEstado = idEstado;
	}
	
	public Reservacion(int idReservacion, int idClienteReservacion, int idHabitacion, String nombreCliente, String celularCliente ,int diasReservacion, int cantidadPersonas, Timestamp fechaReservacion, Date fechaExpiracion, double precioInicialReservacion, String estadoReservacion, int idEstado) {
		this.idReservacion = idReservacion;
		this.idClienteReservacion = idClienteReservacion;
		this.idHabitacion = idHabitacion;
		this.nombreCliente = nombreCliente;
		this.celularCliente = celularCliente;
		this.diasReservacion = diasReservacion;
		this.cantidadPersonas = cantidadPersonas;
		this.fechaReservacion = fechaReservacion;
		this.fechaExpiracion = fechaExpiracion;
		this.precioInicialReservacion = precioInicialReservacion;
		this.estadoReservacion = estadoReservacion;
		this.idEstado = idEstado;
	}
	
	
	
	public Reservacion(int idClienteReservacion, int idHabitacion, int diasReservacion,
			int cantidadPersonas, Timestamp fechaReservacion, Date fechaExpiracion, double precioInicialReservacion,
			int idEstado) {
		this.idClienteReservacion = idClienteReservacion;
		this.idHabitacion = idHabitacion;
		this.diasReservacion = diasReservacion;
		this.cantidadPersonas = cantidadPersonas;
		this.fechaReservacion = fechaReservacion;
		this.fechaExpiracion = fechaExpiracion;
		this.precioInicialReservacion = precioInicialReservacion;
		this.idEstado = idEstado;
	}
	/* OBSOLETOS */
	public Reservacion(int idClienteReservacion, int idHabitacion, int diasReservacion, Timestamp fechaReservacion, Date fechaExpiracion, double precioInicialReservacion, int idEstado) {
		this.idClienteReservacion = idClienteReservacion;
		this.idHabitacion = idHabitacion;
		this.diasReservacion = diasReservacion;
		this.fechaReservacion = fechaReservacion;
		this.fechaExpiracion = fechaExpiracion;
		this.precioInicialReservacion = precioInicialReservacion;
		this.idEstado = idEstado;
	}
	public Reservacion(int idReservacion, int idClienteReservacion, String nombreCliente, int idHabitacion ,int diasReservacion, Timestamp fechaReservacion,	Date fechaExpiracion, double precioInicialReservacion, String estadoReservacion) {
		
		this.idReservacion = idReservacion;
		this.idClienteReservacion = idClienteReservacion;		
		this.nombreCliente = nombreCliente;
		this.idHabitacion = idHabitacion;
		this.diasReservacion = diasReservacion;
		this.fechaReservacion = fechaReservacion;
		this.fechaExpiracion = fechaExpiracion;
		this.precioInicialReservacion = precioInicialReservacion;
		this.estadoReservacion = estadoReservacion;
	}

	public void getReservaciones(ArrayList<Reservacion> reservaciones)
	{
		reservaciones.clear();
		
		
		String url = "SELECT res.idReservacion, res.idCliente, cli.nombreCliente, res.idHabitacion, res.diasReservacion, res.fechaReservacion, res.fechaExpiracion, res.precioInicialReservacion, est.descripcionEstatusRes FROM reservacion AS res INNER JOIN cliente AS cli ON res.idCliente = cli.idCliente INNER JOIN estatusres AS est ON res.idEstatusRes = est.idEstatusRes";
		String url2 = "SELECT res.idReservacion, res.idCliente, cli.nombreCliente, res.idHabitacion, res.diasReservacion, res.cantidadPersonas ,res.fechaReservacion, res.fechaExpiracion, res.precioInicialReservacion, res.idEstatusRes ,est.descripcionEstatusRes FROM reservacion AS res INNER JOIN cliente AS cli ON res.idCliente = cli.idCliente INNER JOIN estatusres AS est ON res.idEstatusRes = est.idEstatusRes WHERE res.idEstatusRes = 1";
		String url3 = "SELECT res.idReservacion, res.idCliente, cli.nombreCliente, res.idHabitacion, res.diasReservacion, res.cantidadPersonas ,res.fechaReservacion, res.fechaExpiracion, res.precioInicialReservacion, res.idEstatusRes ,est.descripcionEstatusRes FROM reservacion AS res INNER JOIN cliente AS cli ON res.idCliente = cli.idCliente INNER JOIN estatusres AS est ON res.idEstatusRes = est.idEstatusRes WHERE res.idEstatusRes = 2";
		
		int idRes;
		int idCli;
		String nombre;
		String cel;
		int idHab;
		int diasRes;
		int huesp;
		Timestamp fechaRes=null;
		Date fechaExp;
		double precioInicial;
		int idEst;
		String estadoRes;
				
		try {
			// "SELECT res.idReservacion, res.idClienteReservacion, cli.nombreCliente, res.idHabitacionReservacion, res.diasReservacion, res.fechaReservacion, res.fechaExpiracion, res.precioInicialReservacion, est.descripcionEstatusRes FROM reservacion AS res INNER JOIN cliente AS cli ON res.idClienteReservacion = cli.idCliente INNER JOIN estatusres AS est ON res.idEstatusRes = est.idEstatusRes"
			cn = sql.Conectar();
			ps = cn.prepareStatement(url2);
			rs = ps.executeQuery();

			while(rs.next())
			{
				idRes = Integer.parseInt(rs.getString(1));
				idCli = Integer.parseInt(rs.getString(2));
				nombre = rs.getString(3);				
				idHab = rs.getInt(4);
				diasRes = Integer.parseInt(rs.getString(5));
				huesp = rs.getInt(6);
				fechaRes = rs.getTimestamp(7, Calendar.getInstance());
				fechaExp = rs.getDate(8, Calendar.getInstance());
				precioInicial = rs.getDouble(9);
				idEst = rs.getInt(10);
				estadoRes = rs.getString(11);
			
				reservaciones.add(new Reservacion(idRes, idCli, idHab, nombre ,diasRes, huesp ,fechaRes, fechaExp, precioInicial, estadoRes ,idEst));
			}			

			
			
		} catch (SQLException e) {
			
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
	
	public Reservacion getReservacionConcreta(int id)
	{
		
		
		Reservacion res = null;
		
		String url = "SELECT res.idReservacion, res.idCliente, cli.nombreCliente, res.idHabitacion, res.diasReservacion, res.fechaReservacion, res.fechaExpiracion, res.precioInicialReservacion, est.descripcionEstatusRes FROM reservacion AS res INNER JOIN cliente AS cli ON res.idCliente = cli.idCliente INNER JOIN estatusres AS est ON res.idEstatusRes = est.idEstatusRes";
		String url2 = "SELECT res.idReservacion, res.idCliente, cli.nombreCliente, cli.celularCliente ,res.idHabitacion, res.diasReservacion, res.cantidadPersonas ,res.fechaReservacion, res.fechaExpiracion, res.precioInicialReservacion, res.idEstatusRes ,est.descripcionEstatusRes FROM reservacion AS res INNER JOIN cliente AS cli ON res.idCliente = cli.idCliente INNER JOIN estatusres AS est ON res.idEstatusRes = est.idEstatusRes WHERE res.idReservacion = " + id;
		
		
		int idRes;
		int idCli;
		String nombre;
		String cel;
		int idHab;
		int diasRes;
		int huesp;
		Timestamp fechaRes=null;
		Date fechaExp;
		double precioInicial;
		int idEst;
		String estadoRes;
				
		try {
			
			cn = sql.Conectar();
			ps = cn.prepareStatement(url2);
			rs = ps.executeQuery();

			while(rs.next())
			{
				idRes = Integer.parseInt(rs.getString(1));
				idCli = Integer.parseInt(rs.getString(2));
				nombre = rs.getString(3);
				cel = rs.getString(4);
				idHab = rs.getInt(5);
				diasRes = Integer.parseInt(rs.getString(6));
				huesp = rs.getInt(7);
				fechaRes = rs.getTimestamp(8, Calendar.getInstance());
				fechaExp = rs.getDate(9, Calendar.getInstance());
				precioInicial = rs.getDouble(10);
				idEst = rs.getInt(11);
				estadoRes = rs.getString(12);
			
				res = new Reservacion(idRes, idCli, idHab, nombre, cel,diasRes, huesp ,fechaRes, fechaExp, precioInicial, estadoRes ,idEst);
			}			

			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally
		{
			if(ps != null)
				try {ps.close();} catch (SQLException e) {e.printStackTrace();}
				
			if(cn != null)
				try{cn.close();} catch(SQLException e) {e.printStackTrace();}
		}
				
		return res;
	}
	
	
	public void getReservacionesFacturadas(ArrayList<Reservacion> reservaciones)
	{
		reservaciones.clear();
		
		
		String url3 = "SELECT res.idReservacion, res.idCliente, cli.nombreCliente, res.idHabitacion, res.diasReservacion, res.cantidadPersonas ,res.fechaReservacion, res.fechaExpiracion, res.precioInicialReservacion, res.idEstatusRes ,est.descripcionEstatusRes FROM reservacion AS res INNER JOIN cliente AS cli ON res.idCliente = cli.idCliente INNER JOIN estatusres AS est ON res.idEstatusRes = est.idEstatusRes WHERE res.idEstatusRes = 2";
		
		int idRes;
		int idCli;
		String nombre;
		int idHab;
		int diasRes;
		int huesp;
		Timestamp fechaRes=null;
		Date fechaExp;
		double precioInicial;
		int idEst;
		String estadoRes;
				
		try {
			cn = sql.Conectar();
			ps = cn.prepareStatement(url3);
			rs = ps.executeQuery();

			while(rs.next())
			{
				idRes = Integer.parseInt(rs.getString(1));
				idCli = Integer.parseInt(rs.getString(2));
				nombre = rs.getString(3);
				idHab = rs.getInt(4);
				diasRes = Integer.parseInt(rs.getString(5));
				huesp = rs.getInt(6);
				fechaRes = rs.getTimestamp(7, Calendar.getInstance());
				fechaExp = rs.getDate(8, Calendar.getInstance());
				precioInicial = rs.getDouble(9);
				idEst = rs.getInt(10);
				estadoRes = rs.getString(11);
			
				reservaciones.add(new Reservacion(idRes, idCli, idHab, nombre,diasRes, huesp ,fechaRes, fechaExp, precioInicial, estadoRes ,idEst));
			}			

			
			
		} catch (SQLException e) {
			
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
	
	
	public void insertarReservacion()
	{
		
		try
		{
			cn = sql.Conectar();
			ps = cn.prepareStatement("INSERT INTO reservacion(idCliente, idHabitacion, diasReservacion, cantidadPersonas ,fechaReservacion, fechaExpiracion, precioInicialReservacion, idEstatusRes) VALUES(?,?,?,?,?,?,?,?)");
			ps.setInt(1, idClienteReservacion);
			ps.setInt(2, idHabitacion);
			ps.setInt(3, diasReservacion);
			ps.setInt(4, cantidadPersonas);
			ps.setTimestamp(5, fechaReservacion, Calendar.getInstance());
			ps.setDate(6, fechaExpiracion);
			ps.setDouble(7, precioInicialReservacion);
			ps.setInt(8, idEstado);
			
			ps.executeUpdate();
			ps.close();
		
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		finally
		{
			if(ps != null)
				try {ps.close();} catch (SQLException e) {e.printStackTrace();}
				
			if(cn != null)
				try{cn.close();} catch(SQLException e) {e.printStackTrace();}
		}
	}
	
	public void actualizarEstado(int id)
	{
		
		try
		{
			cn = sql.Conectar();
			ps = cn.prepareStatement("UPDATE reservacion SET idEstatusRes = 2" + " WHERE idReservacion = " + id +"");
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
	
	
	public int getIdReservacion() {
		return idReservacion;
	}

	public void setIdReservacion(int idReservacion) {
		this.idReservacion = idReservacion;
	}

	public int getIdClienteReservacion() {
		return idClienteReservacion;
	}

	public void setIdClienteReservacion(int idClienteReservacion) {
		this.idClienteReservacion = idClienteReservacion;
	}

	public int getDiasReservacion() {
		return diasReservacion;
	}

	public void setDiasReservacion(int diasReservacion) {
		this.diasReservacion = diasReservacion;
	}

	public Timestamp getFechaReservacion() {
		return fechaReservacion;
	}

	public void setFechaReservacion(Timestamp fechaReservacion) {
		this.fechaReservacion = fechaReservacion;
	}

	public Date getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(Date fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public double getPrecioInicialReservacion() {
		return precioInicialReservacion;
	}

	public void setPrecioInicialReservacion(double precioInicialReservacion) {
		this.precioInicialReservacion = precioInicialReservacion;
	}	
	
	public String getEstadoReservacion() {
		return estadoReservacion;
	}
	public void setEstadoReservacion(String estadoReservacion) {
		this.estadoReservacion = estadoReservacion;
	}
		
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
			
	
	
	public String getCelularCliente() {
		return celularCliente;
	}
	public int getCantidadPersonas() {
		return cantidadPersonas;
	}
	public int getIdEstado() {
		return idEstado;
	}
	public int getIdHabitacion() {
		return idHabitacion;
	}
	public void setIdHabitacion(int idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	
}
