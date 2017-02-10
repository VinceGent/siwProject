package servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dbconnection.UserDAO;
import elements.User;
import elements.UserInformation;

/**
 * Servlet implementation class AddUser
 */
@WebServlet(description = "login", urlPatterns = { ServletUserCredentials.addUser, ServletUserCredentials.loginUser,
		ServletUserCredentials.logoutUser, ServletUserCredentials.userinfo, ServletUserCredentials.modifyUser, ServletUserCredentials.modifyPassword })
// @WebServlet("/AddUser")
public class ServletUserCredentials extends HttpServlet {
	static final long serialVersionUID = 1L;
	static final String addUser = "/addUser";
	static final String loginUser = "/loginUser";
	static final String logoutUser = "/logoutUser";
	static final String userinfo = "/userinfo";
	static final String modifyUser = "/modifyUser";
	static final String modifyPassword = "/modifyPassword";
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
		case userinfo:
			userinfo(request, response);
			break;
		case modifyUser:
			modifyUser(request, response);
			break;
		case modifyPassword:
			modifyPassword(request,response);
			break;
		}

	}

	private void modifyPassword(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("modify password method");
		System.out.println("la nuova password è " + request.getParameter("newPassword"));
		System.out.println("id utente è " + request.getSession().getAttribute("user_id"));
		
		userdb.setPassword(Integer.parseInt(request.getSession().getAttribute("user_id").toString()), request.getParameter("newPassword"));
	}

	private void modifyUser(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("chiamata servlet modifica");
		userdb.modifyUser(Integer.parseInt(request.getSession().getAttribute("user_id").toString()),
				request.getParameter("username"), request.getParameter("email"), request.getParameter("name"),
				request.getParameter("surname"));
		updateSessionInfo(request.getSession(), request.getParameter("username"), request.getParameter("email"));
	}

	private void userinfo(HttpServletRequest request, HttpServletResponse response) {
		User user = userdb.getUserByUsername((String) request.getSession().getAttribute("username"));
		UserInformation info = userdb.getUserInfo(user.getUsername());
		JsonObject obj = new JsonObject();

		obj.addProperty("username", user.getUsername());
		obj.addProperty("name", info.getName());
		obj.addProperty("surname", info.getSurname());
		obj.addProperty("email", user.getEmail());

		try {
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void logoutUser(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		request.getSession().setAttribute("ss", "sss");

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
		// request.getSession().setMaxInactiveInterval(300000000);
		request.getSession().setAttribute("login", "logged");
		request.getSession().setAttribute("user_id", user.getId());

		updateSessionInfo(request.getSession(), user.getUsername(), user.getEmail());

	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		userdb.addUser(request.getParameter("user"), request.getParameter("email"), request.getParameter("password"));
		setLoginAttribute(request, userdb.getUserByEmail(request.getParameter("email")));
		response.getWriter().write("OK");

	}

	private void updateSessionInfo(HttpSession session, String username, String email) {
		session.setAttribute("username", username);
		session.setAttribute("email", email);
		session.setAttribute("userinfo", userdb.getUserInfo(username));
	}

}
