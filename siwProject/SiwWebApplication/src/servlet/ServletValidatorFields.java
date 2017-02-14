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
	}

	private void checkUserPassword(HttpServletRequest request, HttpServletResponse response) {
		User u = userDB.getUserByUsername(request.getAttribute("username").toString());
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
		try {
			User u = userDB.getUserByUsername(request.getParameter("newUser"));
			if (u != null) {
				response.getWriter().print("OK");
			} else
				response.getWriter().print("NO");
		} catch (IOException e) {
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
