package servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import dbconnection.InsertionDAO;
import dbconnection.TradingManagerDAO;
import dbconnection.UserDAO;
import dbconnection.WishlistDAO;

public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected InsertionDAO insertionDAO;
	protected TradingManagerDAO tradingManagerDAO;
	protected WishlistDAO wishlistDAO;
	protected UserDAO userDAO;

	public Servlet() {
		wishlistDAO = new WishlistDAO();
		insertionDAO = new InsertionDAO();
		tradingManagerDAO = new TradingManagerDAO();
		wishlistDAO = new WishlistDAO();
		userDAO = new UserDAO();

	}

	protected boolean isLogged(HttpServletRequest req) {
		return (req.getSession().getAttribute("login") != null
				&& req.getSession().getAttribute("login").toString().equals("logged"));
	}

	protected int getIdItem(HttpServletRequest req) {
		return Integer.parseInt(req.getParameter("id_item"));

	}

	protected String getUsername(HttpServletRequest request) {
		return request.getSession().getAttribute("username").toString();
	}

	protected int getUserId(HttpServletRequest req) {
		return Integer.parseInt(req.getSession().getAttribute("user_id").toString());
	}

	protected void writeStateSuccess(JsonObject jsonObject) {
		jsonObject.addProperty("state", "OK");
	}

	protected void writeStateFailed(JsonObject jsonObject) {
		jsonObject.addProperty("state", "failed");
	}

	protected void writeResponse(HttpServletResponse resp, JsonObject jsonObject) throws IOException {
		resp.getWriter().write(jsonObject.toString());
	}
	

}
