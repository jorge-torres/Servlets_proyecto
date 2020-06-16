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
 * Servlet implementation class Infousuario
 */
@WebServlet("/Infousuario")
public class Infousuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource (name="jdbc/Proyecto")
	private DataSource miPool;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Infousuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		PrintWriter respuesta=response.getWriter();
		Connection conexion=null;
		Statement	sentencia=null;
		ResultSet	salida=null;
		
		try {
			
			conexion=miPool.getConnection();
			String miquery="SELECT nombre, apellido, id_documento,  fecha_nacimiento, correo, discapacitado FROM usuario WHERE id_usuario = '" + id +"'";
			sentencia=conexion.createStatement();
			salida=sentencia.executeQuery(miquery);
		
			while(salida.next()) {
				String articulo="{nombre : \"" + salida.getString(1)+ " " + salida.getString(2)  + "\", documento : \"" + salida.getString(3) +"\", fecha_nacimiento : \"" + salida.getString(6) +  "\", discapacitado : \""+ salida.getBoolean(6) + "\"}";
				
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
