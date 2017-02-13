package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbconnection.WishlistDAO;
import elements.Insertion;

@WebServlet(description = "wishlist", urlPatterns = { ServletWishlist.loadWishlist, ServletWishlist.removeItem })
public class ServletWishlist extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected static final String loadWishlist = "/loadWishlist";
	protected static final String removeItem = "/removeItem";

	protected WishlistDAO dbWishlist;

	public ServletWishlist() {
		dbWishlist = new WishlistDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("wishlist doget " + req.getServletPath());

		switch (req.getServletPath()) {
		case loadWishlist:
			loadWishlist(req, resp);
			break;

		case removeItem:
			removeItem(req, resp);
			break;

		default:
			break;
		}
	}

	private void removeItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("remove wishlist item");
		if (req.getSession().getAttribute("login").toString().equals("logged")) {
			dbWishlist.removeWishlistItem(Integer.parseInt(req.getParameter("id_item").toString()),
					Integer.parseInt(req.getSession().getAttribute("user_id").toString()));
		}
		// req.getRequestDispatcher("wishlist.jsp").forward(req, resp);
	}

	private void loadWishlist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("called load wishlist");
		if (req.getSession().getAttribute("login").toString().equals("logged")) {
			List<Insertion> list = dbWishlist
					.getWishlist(Integer.parseInt(req.getSession().getAttribute("user_id").toString()));
			req.getSession().setAttribute("wishlist", list);
			req.getRequestDispatcher("wishlist.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// super.doPost(req, resp);
		System.out.println("wishlist dopost");

	}
}
