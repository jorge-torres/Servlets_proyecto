package com.proyecto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class Desencolar
 */
@WebServlet("/Desencolar")
public class Desencolar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource (name="jdbc/Proyecto")
	private DataSource miPool;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Desencolar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter respuesta=response.getWriter();
		Connection conexion=null;
		Statement	sentencia=null;
		ResultSet	salida=null;
		
		try {
			
			conexion=miPool.getConnection();
		
		    Calendar calendar = Calendar.getInstance();
		    java.sql.Date date = new java.sql.Date(calendar.getTime().getTime());
	
	
			User userExtracted = Constcola.getPriorityQueueInstance().extractMax();
			String query = "INSERT INTO cita(id_usuario, hora, minuto, fecha, atendido) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			preparedStatement.setString(1, userExtracted.getUid());
			preparedStatement.setInt(2, userExtracted.getAttentionHour().getHour());
			preparedStatement.setInt(3, userExtracted.getAttentionHour().getMinute());
			preparedStatement.setDate(4, date);
			preparedStatement.setBoolean(5, true);
			preparedStatement.execute();

			
		}catch(Exception e){
			e.printStackTrace();
			response.setStatus(400);
			
			
		};
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
