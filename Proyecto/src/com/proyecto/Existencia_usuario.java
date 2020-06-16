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
 * Servlet implementation class Existencia_usuario
 */
@WebServlet("/Existencia_usuario")
public class Existencia_usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource (name="jdbc/Proyecto")
	private DataSource miPool;
       
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Existencia_usuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String doc = request.getParameter("documento");
		PrintWriter respuesta=response.getWriter();
		Connection conexion=null;
		Statement	sentencia=null;
		ResultSet	salida=null;
		String articulo=null;
		
		try {
			
			conexion=miPool.getConnection();
			String miquery="SELECT id_usuario FROM usuario WHERE id_documento = '" + doc +"'";
			sentencia=conexion.createStatement();
			salida=sentencia.executeQuery(miquery);
			
		
			if(salida.isBeforeFirst()) {
				salida.next();
				articulo="{existe : \"true\", id:\"" + salida.getString(1) +"\"}";

				
				
			}else { 
				articulo ="{existe : \"false\"}";
			}

		respuesta.println(articulo);
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
