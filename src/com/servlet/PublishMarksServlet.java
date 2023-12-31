package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assign.dao.BQuery;
import com.assign.dao.LecturerDao;
import com.assign.dao.SubjectsDao;
import com.assign.entites.Lecturer;
import com.assign.entites.Student;
import com.assign.entites.Subject;

/**
 * Servlet implementation class PublishMarksServlet
 */
@WebServlet("/PublishMarksServlet")
public class PublishMarksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublishMarksServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		Integer lecturerID = (Integer) request.getAttribute("lecturerID"); 
		String fullName = (String) request.getAttribute("fullName");
		
		System.out.println("======== PublishMarksServlet ======== ");

		SubjectsDao sd = new SubjectsDao();
		ArrayList<Subject> subjects = sd.getLecturerSubjects(lecturerID); 
		request.setAttribute("subjects", subjects);
		
		request.getRequestDispatcher("/publishMarks.jsp").forward(request, resp);

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
