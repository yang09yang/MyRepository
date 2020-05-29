package com.shashi.srv;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Blob;
import com.mysql.jdbc.Statement;
import com.shashi.dao.ProductDaoImpl;
import com.shashi.utility.DBUtil;

@WebServlet("/ShowImage")
public class ShowImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ShowImage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String prodId = request.getParameter("pid");
		
		//System.out.print("ProdId= "+prodId+" Image is available: ");

//		ProductDaoImpl dao = new ProductDaoImpl();
		
//		byte[] image = dao.getImage(prodId);
		
		//connect mysql
		Connection con = DBUtil.provideConnection();
		try{ 
			Statement stmt = (Statement) con.createStatement(); 
			String sql = " SELECT image FROM product WHERE  pid = "+ prodId; 
			ResultSet rs = stmt.executeQuery(sql); 
			if (rs.next()) { 
				Blob b = (Blob) rs.getBlob("image"); 
				long size = b.length(); 
				//out.print(size); 
				byte[] bs = b.getBytes(1, (int)size); 
				response.setContentType("image/jpeg"); 
				OutputStream outs = response.getOutputStream(); 
				outs.write(bs); 
				outs.flush(); 
				rs.close(); 
			} else { 
				rs.close(); 
				response.sendRedirect("d:/java/project/shopping-cart-master/shopping-cart/Webcontent/images/err.jpeg"); 
		} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally{ 
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		} 
		
		//System.out.print("ProdId= "+prodId+" Image is available: ");
		
//		ServletOutputStream sos = null;
//
//		sos = response.getOutputStream();
//		
//		sos.write(image);
		/*
		sos.flush();
		
		sos.close();*/
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
