package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assign.dao.Security;


/**
 * Servlet implementation class VarifySignatureServlet
 */
@WebServlet("/VerifySignatureServlet")
public class VerifySignatureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerifySignatureServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		String message = (String) request.getParameter("message"); 
		String signature = (String) request.getParameter("signature");
		int lecturerID = Integer.parseInt(request.getParameter("lecturerID"));
		
		System.out.println("======== VerifySignatureServlet ======== ");
		System.out.println("message = " + message);
		System.out.println("signature = " + signature);
		
		Security se = new Security(lecturerID);
		
		if(se.verifySignature(signature, message)) {
			request.getRequestDispatcher("/VerifyPreviousScoresServlet").forward(request, resp);
		} else { 
			resp.setContentType("text/html; charset=utf-8");
    		PrintWriter out = resp.getWriter();
    	    out.print("signfFailed");
    	    out.flush();
    		return;
		}
		
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		this.doGet(request, response);
	}
	
	

}
