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
@WebServlet(description = "login", urlPatterns = { ServletValidatorFields.validateEmail,
		ServletValidatorFields.validateUsername, ServletValidatorFields.getUserPassword })
public class ServletValidatorFields extends HttpServlet {
	final static String validateUsername = "/validateUsername";
	final static String validateEmail = "/validateEmail";
	final static String getUserPassword = "/getUserPassword";
	private static final long serialVersionUID = 1L;

	UserDAO userDB;

	public ServletValidatorFields() {
		super();
		userDB = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * if (request.getParameter("newUser") != null) { if
		 * (!validateUser(request.getParameter("newUser"))) out.append("OK");
		 * else out.append("NO"); } else { if
		 * (!validateEmail(request.getParameter("newEmail"))) out.append("OK");
		 * else out.append("NO"); }
		 */
		String path = request.getServletPath();
		switch (path) {
		case validateUsername:
			validateUser(request, response);
			break;
		case validateEmail:
			validateEmail(request, response);
			break;
		case getUserPassword:
			checkUserPassword(request, response);
			break;
		}

		// doGet(request, response);
	}

	private void checkUserPassword(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("check user password");
		User u = userDB.getUserByUsername(request.getSession().getAttribute("username").toString());
		System.out.println("getpassword " + u.getPassword());
		System.out.println("oldpassword " + request.getParameter("oldpass"));
		try {
			if (u != null && u.getPassword().equals(request.getParameter("oldpass"))) {
				response.getWriter().print("OK");
			} else
				response.getWriter().print("NO");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void validateUser(HttpServletRequest request, HttpServletResponse response) {

		// List queryresult = database.executeQuery("from User where username =
		// '"+user+"'");
		// System.out.println("query result is: " + queryresult.size());
		try {
			User u = userDB.getUserByUsername(request.getParameter("newUser"));
			if (u != null) {
				response.getWriter().print("OK");
			} else
				response.getWriter().print("NO");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void validateEmail(HttpServletRequest request, HttpServletResponse response) {

		User u = userDB.getUserByEmail(request.getParameter("newEmail"));
		try {
			if (u != null) {

				response.getWriter().print("OK");

			} else
				response.getWriter().print("NO");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
