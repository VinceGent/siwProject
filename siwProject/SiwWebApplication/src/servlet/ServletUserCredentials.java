package servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dbconnection.UserDAO;
import elements.User;

/**
 * Servlet implementation class AddUser
 */
@WebServlet(description = "login", urlPatterns = { ServletUserCredentials.addUser, ServletUserCredentials.loginUser,
		ServletUserCredentials.logoutUser })
// @WebServlet("/AddUser")
public class ServletUserCredentials extends HttpServlet {
	static final long serialVersionUID = 1L;
	static final String addUser = "/addUser";
	static final String loginUser = "/loginUser";
	static final String logoutUser = "/logoutUser";
	private UserDAO userdb;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletUserCredentials() {
		super();
		userdb = new UserDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getServletPath();
		switch (path) {
		case logoutUser:
			logoutUser(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		switch (path) {
		case addUser:
			addUser(request, response);
			break;
		case loginUser:
			loginUser(request, response);
			break;
		}

	}

	private void logoutUser(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		request.getSession().setAttribute("ss","sss");
		
		
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

		User user = userdb.getUserByUsername(request.getParameter("username"));
		JsonObject obj = new JsonObject();
		if (user != null && user.getPassword().equals(request.getParameter("password"))) {
			obj.addProperty("state", "ok");
			setLoginAttribute(request, user);

		} else {
			obj.addProperty("state", "failed");
		}
		response.getWriter().write(obj.toString());
	}

	private void setLoginAttribute(HttpServletRequest request, User user) {
//		request.getSession().setMaxInactiveInterval(300000000);
		request.getSession().setAttribute("login", "logged");
		request.getSession().setAttribute("user_id", user.getId());
		request.getSession().setAttribute("username", user.getUsername());
		

	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		userdb.addUser(request.getParameter("user"), request.getParameter("email"), request.getParameter("password"));
		setLoginAttribute(request, userdb.getUserByEmail(request.getParameter("email")));
		response.getWriter().write("OK");

	}

}
