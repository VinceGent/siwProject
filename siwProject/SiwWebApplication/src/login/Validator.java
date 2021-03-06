package login;

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

/**
 * Servlet implementation class Validator
 */
// @WebServlet("/Validator")
@WebServlet(description = "login", urlPatterns = { "/Validator" })
public class Validator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserDAO userDB;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Validator() {
		super();
		userDB = UserDAO.getInstance();	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		System.out.println("called GET Validator servlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Username & Email Validator
//		System.out.println("called POST Validator servlet");
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
		System.out.println("validate user " + user);
		User u = userDB.getUserByUsername(user);
		if (u != null) {
			System.out.println("username: " + u.getUsername());
			return true;
		}
		return false;
	}

	private boolean validateEmail(String email) {
		User u = userDB.getUserByEmail(email);
		if (u != null) {
			System.out.println("username: " + u.getEmail());
			return true;
		}
		return false;
	}

}
