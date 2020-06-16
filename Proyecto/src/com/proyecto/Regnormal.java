package com.proyecto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class Registro
 */
@WebServlet("/Registro")
public class Regnormal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource (name="jdbc/Proyecto")
	private DataSource miPool;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Regnormal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//id_usuario, id_documento, nombre, apellido, fecha_nacimiento, discapacitado(bool), correo.
		
		String id_usuario = request.getParameter("id_usuario");
		String id_documento = request.getParameter("id_documento");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String fecha_nacimiento = request.getParameter("fecha_nacimiento");
		Boolean discapacitado = Boolean.parseBoolean(request.getParameter("discapacitado"));
		String correo = request.getParameter("correo");

		PrintWriter respuesta=response.getWriter();
		Connection conexion=null;
		Statement	sentencia=null;
		ResultSet	salida=null;

		try {
			
			conexion=miPool.getConnection();
			String miquery= "INSERT INTO usuario (id_usuario, id_ubicacion , id_documento, nombre, apellido, fecha_nacimiento, correo, discapacitado) VALUES (?, 1 , ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = conexion.prepareStatement(miquery);
			preparedStmt.setString(1, id_usuario);
			preparedStmt.setString(2, id_documento);
			preparedStmt.setString(3, nombre);
			preparedStmt.setString(4, apellido);
			preparedStmt.setString(5, fecha_nacimiento);
			preparedStmt.setString(6, correo);
			preparedStmt.setBoolean(7, discapacitado);
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
