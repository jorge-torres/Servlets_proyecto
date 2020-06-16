package com.proyecto;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.*;
import java.sql.*;

/**
 * Servlet implementation class Conexion1
 */
@WebServlet("/Conexion1")
public class Conexion1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource (name="jdbc/Proyecto")
	private DataSource miPool;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Conexion1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter respuesta=response.getWriter();
		Connection conexion=null;
		Statement	sentencia=null;
		ResultSet	salida=null;
		
		try {
			conexion=miPool.getConnection();
			String miquery="SELECT * FROM usuario";
			sentencia=conexion.createStatement();
			salida=sentencia.executeQuery(miquery);
			while(salida.next()) {
				String articulo=salida.getString(2);
				respuesta.println(articulo + "<br>");
			}
			
			
			
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
