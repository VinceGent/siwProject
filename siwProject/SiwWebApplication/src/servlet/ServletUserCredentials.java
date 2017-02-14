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
		ServletUserCredentials.logoutUser, ServletUserCredentials.userinfo, ServletUserCredentials.modifyUser,
		ServletUserCredentials.modifyPassword })
// @WebServlet("/AddUser")
public class ServletUserCredentials extends Servlet {
	static final long serialVersionUID = 1L;
	static final String addUser = "/addUser";
	static final String loginUser = "/loginUser";
	static final String logoutUser = "/logoutUser";
	static final String userinfo = "/userinfo";
	static final String modifyUser = "/modifyUser";
	static final String modifyPassword = "/modifyPassword";

	public ServletUserCredentials() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getServletPath();
		switch (path) {
		case logoutUser:
			logoutUser(request, response);
			break;
		}
	}

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
			modifyPassword(request, response);
			break;
		}

	}

	private void modifyPassword(HttpServletRequest request, HttpServletResponse response) {
		userDAO.setPassword(getUserId(request), request.getParameter("newPassword"));
	}

	private void modifyUser(HttpServletRequest request, HttpServletResponse response) {
		userDAO.modifyUser(getUserId(request), request.getParameter("username"), request.getParameter("email"),
				request.getParameter("name"), request.getParameter("surname"), request.getParameter("address"),
				Integer.parseInt(request.getParameter("telephone")), request.getParameter("city"),
				request.getParameter("province"), Integer.parseInt(request.getParameter("postal_code")),
				request.getParameter("country"));
		updateSessionInfo(request.getSession(), request.getParameter("username"), request.getParameter("email"));
	}

	private void userinfo(HttpServletRequest request, HttpServletResponse response) {
		User user = userDAO.getUserByUsername(getUsername(request));

		UserInformation info = userDAO.getUserInfo(user.getUsername());
		JsonObject obj = new JsonObject();
		if (info != null) {
			obj.addProperty("username", user.getUsername());
			obj.addProperty("name", info.getName());
			obj.addProperty("surname", info.getSurname());
			obj.addProperty("email", user.getEmail());
			obj.addProperty("address", info.getAddress());
			obj.addProperty("telephone", info.getTelephone());
			obj.addProperty("city", info.getCity());
			obj.addProperty("province", info.getProvince());
			obj.addProperty("postal_code", info.getPostal_code());
			obj.addProperty("country", info.getCountry());
		}
		try {
			writeResponse(response, obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void logoutUser(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

		User user = userDAO.getUserByUsername(request.getParameter("username"));
		JsonObject obj = new JsonObject();
		if (user != null && user.getPassword().equals(request.getParameter("password"))) {
			writeStateSuccess(obj);
			setLoginAttribute(request, user);

		} else {
			writeStateFailed(obj);
		}
		writeResponse(response, obj);
	}

	private void setLoginAttribute(HttpServletRequest request, User user) {
		request.getSession().setAttribute("login", "logged");
		request.getSession().setAttribute("user_id", user.getId());
		updateSessionInfo(request.getSession(), user.getUsername(), user.getEmail());

	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		userDAO.addUser(request.getParameter("user"), request.getParameter("email"), request.getParameter("password"));
		userDAO.addUserInfo(request.getParameter("user"), request.getParameter("name"), request.getParameter("surname"), request.getParameter("address"), request.getParameter("telephone"), request.getParameter("city"), request.getParameter("province"), Integer.parseInt(request.getParameter("postalcode").toString()), request.getParameter("country"));
		
		
		
		
		
		setLoginAttribute(request, userDAO.getUserByEmail(request.getParameter("email")));
		response.getWriter().write("OK");

	}

	private void updateSessionInfo(HttpSession session, String username, String email) {
		session.setAttribute("username", username);
		session.setAttribute("email", email);
		session.setAttribute("userinfo", userDAO.getUserInfo(username));
	}

}
