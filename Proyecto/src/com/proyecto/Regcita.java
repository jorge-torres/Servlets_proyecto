package com.proyecto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Calendar;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class Regcita
 */
@WebServlet("/Regcita")
public class Regcita extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource (name="jdbc/Proyecto")
	private DataSource miPool;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Regcita() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id_usuario = request.getParameter("id");


		PrintWriter respuesta=response.getWriter();
		Connection conexion=null;
		Statement	sentencia=null;
		ResultSet	salida=null;
		
		Integer hora=2, minuto=1;
		Boolean atendido = true;
		
	    Calendar calendar = Calendar.getInstance();
	    java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
	    
        DateTimeFormatter hh = DateTimeFormatter.ofPattern("HH");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter mm = DateTimeFormatter.ofPattern("mm");
        hora = Integer.parseInt(hh.format(now));
        minuto = Integer.parseInt(mm.format(now));
		
		

		try {
			
			conexion=miPool.getConnection();
			String miquery= "INSERT INTO cita (fecha, hora, minuto, atendido, id_usuario) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = conexion.prepareStatement(miquery);
			preparedStmt.setDate(1, ourJavaDateObject);
			preparedStmt.setInt(2, hora);
			preparedStmt.setInt(3, minuto);
			preparedStmt.setBoolean(4, atendido);
			preparedStmt.setString(5, id_usuario);
			preparedStmt.execute();

			
			
			
		}catch(Exception e){
			e.printStackTrace();
			response.setStatus(400);
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
