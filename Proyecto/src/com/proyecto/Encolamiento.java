package com.proyecto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Encolamiento
 */
@WebServlet("/Encolamiento")
public class Encolamiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Encolamiento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id_usuario = request.getParameter("id");
		String hora = request.getParameter("hora");
		String minuto = request.getParameter("minuto");
		
		int horaint = Integer.parseInt(hora);
		int minutoint = Integer.parseInt(minuto);

		PrintWriter respuesta=response.getWriter();
		Connection conexion=null;
		Statement	sentencia=null;
		ResultSet	salida=null;
		
		LocalTime horaCita = LocalTime.of(horaint, minutoint);
	    LocalTime startHour = Constcola.getPriorityQueueInstance().startHour;
	    int intervalLength = Constcola.getPriorityQueueInstance().intervalLength;
	    int posicion = (int) java.time.temporal.ChronoUnit.MINUTES.between( startHour, horaCita ) / intervalLength;
		
	    
	    try {
	    
	    Constcola.getPriorityQueueInstance().insert( new User(LocalTime.of(horaint, minutoint), id_usuario, 3.0));
		

			//Constcola.getPriorityQueueInstance().insert(Constcola.getBufferQueueInstance().users[0].popFront());
			//respuesta.println(Constcola.getPriorityQueueInstance().heap[0].getUid());
			respuesta.println("{encolado: \"true\"}");
		}catch(Exception e){
			e.printStackTrace();
			
		} 
	    

	/*	try {

	    if (Constcola.getBufferQueueInstance().users[posicion].getSize() < Constcola.getBufferQueueInstance().capacityPerTurn) {
	    	
	        Constcola.getBufferQueueInstance().insertUser(new User(LocalTime.of(hora, minuto), id_usuario,  2.0));
	        respuesta.print("{encolado: \"true\",  tamaño:\"" + Constcola.getBufferQueueInstance().users[0].getSize() + "\" }");
	    } else {
	    	respuesta.print("{encolado: \"false\",  tamaño:\"" + Constcola.getBufferQueueInstance().users[0].getSize() + "\" }");
	    			} 
	    
	}catch(Exception e){
		e.printStackTrace();
		
	} */
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
