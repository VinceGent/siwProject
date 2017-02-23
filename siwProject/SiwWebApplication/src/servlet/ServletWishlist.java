package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import dbconnection.WishlistDAO;
import elements.Insertion;

@WebServlet(description = "wishlist", urlPatterns = { ServletWishlist.loadWishlist, ServletWishlist.removeWishlistItem,ServletWishlist.isWishlistItem,
		ServletWishlist.addWishlistItem ,ServletWishlist.clearWishlist})
public class ServletWishlist extends Servlet {

	private static final long serialVersionUID = 1L;
	protected static final String loadWishlist = "/loadWishlist";
	protected static final String removeWishlistItem = "/removeWishlistItem";
	protected static final String addWishlistItem = "/addWishlistItem";
	protected static final String isWishlistItem = "/isWishlistItem";
	protected static final String clearWishlist = "/clearWishlist";
	public ServletWishlist() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case loadWishlist:
			loadWishlist(req, resp);
			break;
		case isWishlistItem:
			isWishlistItem(req,resp);
			break;
		case clearWishlist:
			clearWishlist(req,resp);
			break;
		}
	}

	private void clearWishlist(HttpServletRequest req, HttpServletResponse resp) {
		if(!isLogged(req))
			return;
		wishlistDAO.clearWishlist(getUserId(req));
		
	}

	private void isWishlistItem(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if(!isLogged(req))
		{
			badRequestPage(req, resp);
			return;
		}
		if(wishlistDAO.isWishlistItem(getIdItem(req), getUserId(req)))
			resp.getWriter().write("true");
		else
			resp.getWriter().write("false");
		
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
			HashMap<Insertion, List<String>> insertions=new HashMap<Insertion,List<String>>();
			for (Insertion insertion : list) {
				List<String> images = resource.getImageInsertion(insertion.getId_item());
				insertions.put(insertion, images);
			}
			
			req.getSession().setAttribute("wishlist", insertions);
			req.getRequestDispatcher("wishlist.jsp").forward(req, resp);
		}
		else
			badRequestPage(req, resp);
	}

}
