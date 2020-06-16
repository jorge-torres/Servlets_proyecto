package com.proyecto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class Historial
 */
@WebServlet("/Historial")
public class Historial extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource (name="jdbc/Proyecto")
	private DataSource miPool;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Historial() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String fecha = request.getParameter("fecha");
		PrintWriter respuesta=response.getWriter();
		Connection conexion=null;
		Statement	sentencia=null;
		ResultSet	salida=null;
		
		try {
			
			conexion=miPool.getConnection();
			String miquery="SELECT hora, minuto, atendido FROM cita WHERE id_usuario = '" + id +"' AND fecha = '"+ fecha +"'";
			sentencia=conexion.createStatement();
			salida=sentencia.executeQuery(miquery);
			String articulo="[";
			
			//respuesta.print("[");
			
			while(salida.next()) {
				articulo+="{hora : \"" + salida.getString(1)+ ":" + salida.getString(2)  + "\", atendido : \"" + salida.getBoolean(3) + "\"},";
				
			}
			if (articulo.contentEquals("[")) {
				respuesta.print(articulo.substring(0,articulo.length()) + "]");
				
			}else {
			respuesta.print(articulo.substring(0,articulo.length()-1) + "]");}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			
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
