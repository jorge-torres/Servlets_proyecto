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
 * Servlet implementation class Creacola
 */
@WebServlet("/Creacola")
public class Creacola extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Creacola() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer tcola = Integer.parseInt(request.getParameter("tamanioCola"));
		String hinicial = request.getParameter("horaInicial");
		String hfinal = request.getParameter("horaFinal");
		Integer mintervalo = Integer.parseInt(request.getParameter("minutosIntervalo"));
		Integer capacidad = Integer.parseInt(request.getParameter("capacidadTurno"));
		
		ServiceMetaData metaData = new ServiceMetaData();
		metaData.setMaxSize(tcola);
		metaData.setStartHour(LocalTime.of(Integer.parseInt(hinicial.split(":")[0]), Integer.parseInt(hinicial.split(":")[1])));
		metaData.setEndHour(LocalTime.of(Integer.parseInt(hfinal.split(":")[0]), Integer.parseInt(hfinal.split(":")[1])));
		metaData.setIntervalLength(mintervalo);
		metaData.setCapacityPerTurn(capacidad);
		
		Constcola.setMetaData(metaData);
		Constcola.getBufferQueueInstance();


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
