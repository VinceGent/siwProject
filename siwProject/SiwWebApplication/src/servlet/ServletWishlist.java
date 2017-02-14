package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import dbconnection.WishlistDAO;
import elements.Insertion;

@WebServlet(description = "wishlist", urlPatterns = { ServletWishlist.loadWishlist, ServletWishlist.removeWishlistItem,
		ServletWishlist.addWishlistItem })
public class ServletWishlist extends Servlet {

	private static final long serialVersionUID = 1L;
	protected static final String loadWishlist = "/loadWishlist";
	protected static final String removeWishlistItem = "/removeWishlistItem";
	protected static final String addWishlistItem = "/addWishlistItem";

	public ServletWishlist() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case loadWishlist:
			loadWishlist(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case addWishlistItem:
			addItem(req, resp);
			break;
		case removeWishlistItem:
			removeItem(req, resp);
			break;
		}

	}

	private void addItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JsonObject jsonObject = new JsonObject();
		if (isLogged(req)) {
			wishlistDAO.addWishlistItem(getUserId(req), getIdItem(req));
			writeStateSuccess(jsonObject);
			jsonObject.addProperty("inWishlist", true);
		} else {
			writeStateFailed(jsonObject);
		}
		resp.getWriter().write(jsonObject.toString());
	}

	protected void removeItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("rimuovoooooooooooooooooooo");
		JsonObject jsonObject = new JsonObject();
		if (isLogged(req)) {
			wishlistDAO.removeWishlistItem(getIdItem(req), getUserId(req));
			req.getSession().setAttribute("inWishlist", false);
			writeStateSuccess(jsonObject);
		} else {
			writeStateSuccess(jsonObject);
		}
		writeResponse(resp,jsonObject);
	}

	

	private void loadWishlist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (isLogged(req)) {
			List<Insertion> list = wishlistDAO.getWishlist(getUserId(req));
			req.getSession().setAttribute("wishlist", list);
			req.getRequestDispatcher("wishlist.jsp").forward(req, resp);
		}
	}

}
