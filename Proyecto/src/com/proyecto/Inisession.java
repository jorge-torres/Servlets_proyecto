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
 * Servlet implementation class Inisession
 */
@WebServlet("/Inisession")
public class Inisession extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource (name="jdbc/Proyecto")
	private DataSource miPool;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inisession() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		PrintWriter respuesta=response.getWriter();
		Connection conexion=null;
		Statement	sentencia=null;
		ResultSet	salida=null;
		String articulo=null;
		
		try {
			
			conexion=miPool.getConnection();
			String miquery="SELECT * FROM usuario_empresarial WHERE id_usuario = '" + id +"' AND correo = '" + email + "'";
			sentencia=conexion.createStatement();
			salida=sentencia.executeQuery(miquery);
			
		
			if(salida.isBeforeFirst()) {
				articulo="{empleado : \"true\"}";

				
				
			}else { 
				articulo ="{empleado : \"false\"}";
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
