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
 * Servlet implementation class Infocola
 */
@WebServlet("/Infocola")
public class Infocola extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource (name="jdbc/Proyecto")
	private DataSource miPool;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Infocola() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter	respuesta=response.getWriter();
		Connection	conexion=null;
		Statement	sentencia=null;
		ResultSet	salida=null;
		Integer 	tmp =null;
		String articulo = "[";

		
		try {
/*
		for (int i = 0; i < Constcola.getBufferQueueInstance().users.length; i++) {
			
			if(Constcola.getBufferQueueInstance().users[i] != null) {
			
	            for (int j = 0; j < Constcola.getBufferQueueInstance().users[i].getSize(); j++) {
	                if (Constcola.getBufferQueueInstance().users[i].head == null) {
	                    break;
	                }
	            
	                Constcola.getPriorityQueueInstance().insert(Constcola.getBufferQueueInstance().users[i].popFront());
	            	}

			}
		}
		*/
		User[] users = Constcola.getPriorityQueueInstance().heap;

		conexion=miPool.getConnection();
		
		
        for (int i = 0; i < Constcola.getPriorityQueueInstance().size; i++ ) {

				String miquery="SELECT nombre, apellido, id_documento  FROM usuario WHERE id_usuario = '" + users[i].getUid() +"'";
				//respuesta.println(miquery);
				sentencia=conexion.createStatement();
				salida=sentencia.executeQuery(miquery);
				if(salida.isBeforeFirst()) {
					salida.next();
					articulo+="{documento : \"" + salida.getString(3)+ "\", nombre : \"" + salida.getString(1)+ " " + salida.getString(2)  + "\", hora : \"" + users[i].getAttentionHour() + "\"}";
					articulo += ",";	
				}


        }
        
        if (Constcola.getPriorityQueueInstance().isEmpty()) {
        respuesta.print(articulo +"]");
        }else {
            respuesta.print(articulo.substring(0,articulo.length()-1) +"]");

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
