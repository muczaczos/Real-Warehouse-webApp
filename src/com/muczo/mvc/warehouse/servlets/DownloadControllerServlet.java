package com.muczo.mvc.warehouse.servlets;

import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class DownloadControllerServlet
 */
@WebServlet("/DownloadControllerServlet")
public class DownloadControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "jdbc/kp_warehouse_documents")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filename = "workbook.xls";
		 
		 
        ServletOutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(filename);
 
        response.setContentType("application/vnd.ms-excel");
        response.addHeader("content-disposition",
                "attachment; filename=" + filename);
 
        int octet;
        while((octet = in.read()) != -1)
            out.write(octet);
 
        in.close();
        out.close();
	}


}
