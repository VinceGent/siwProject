package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dbconnection.UserDAO;
import elements.User;

// @WebServlet("/Validator")
@WebServlet(description = "login", urlPatterns = { ServletValidatorFields.validator })
public class ServletValidatorFields extends HttpServlet {
	final static String validator="/Validator";
	private static final long serialVersionUID = 1L;

	UserDAO userDB;

	public ServletValidatorFields() {
		super();
		userDB = UserDAO.getInstance();	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		if (request.getParameter("newUser") != null) {
			if (!validateUser(request.getParameter("newUser")))
				out.append("OK");
			else
				out.append("NO");
		} else {
			if (!validateEmail(request.getParameter("newEmail")))
				out.append("OK");
			else
				out.append("NO");
		}

		// doGet(request, response);
	}

	private boolean validateUser(String user) {

		// List queryresult = database.executeQuery("from User where username =
		// '"+user+"'");
		// System.out.println("query result is: " + queryresult.size());
//		System.out.println("validate user " + user);
		User u = userDB.getUserByUsername(user);
		if (u != null) {
//			System.out.println("username: " + u.getUsername());
			return true;
		}
		return false;
	}

	private boolean validateEmail(String email) {
		User u = userDB.getUserByEmail(email);
		if (u != null) {
//			System.out.println("username: " + u.getEmail());
			return true;
		}
		return false;
	}

}
