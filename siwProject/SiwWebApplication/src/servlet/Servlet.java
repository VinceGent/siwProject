package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import dbconnection.FeedbackDAO;
import dbconnection.InsertionDAO;
import dbconnection.TradingManagerDAO;
import dbconnection.UserDAO;
import dbconnection.WishlistDAO;
import elements.AuctionOffer;
import elements.Insertion;
import utility.JavaMail;
import utility.Resource;

@WebServlet(description = "home", urlPatterns = { Servlet.homepage })
public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected static final String homepage = "/home";
	protected static InsertionDAO insertionDAO;
	protected static TradingManagerDAO tradingManagerDAO;
	protected static FeedbackDAO feedbackDAO;
	protected static WishlistDAO wishlistDAO;
	protected static UserDAO userDAO;
	protected static Resource resource;

	public Servlet() {
		wishlistDAO = new WishlistDAO();
		insertionDAO = new InsertionDAO();
		tradingManagerDAO = new TradingManagerDAO();
		wishlistDAO = new WishlistDAO();
		userDAO = new UserDAO();
		resource = new Resource();
		feedbackDAO = new FeedbackDAO();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HashMap<Insertion, List<String>> insertions = new HashMap<Insertion, List<String>>();
		List<Insertion> insert = insertionDAO.getLastInsertion();
		for (Insertion insertion : insert) {
			List<String> image = resource.getImageInsertion(insertion.getId_item());
			insertions.put(insertion, image);
		}
		req.getSession().setAttribute("lastInsertion", insertions);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
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

	protected boolean missingParameter(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getParameter("id_item") == null) {
			System.out.println("missing parameter");
			badRequestPage(req, resp);
			return true;
		}
		return false;
	}

	protected void badRequestPage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("400.html").forward(req, resp);
	}

	public float getMaxOffertPrice(Insertion insertion) {
		ArrayList<AuctionOffer> offers = tradingManagerDAO.getOfferByIdItem(insertion.getId_item());
		float max = offers.get(0).getOffer();
		for (AuctionOffer auctionOffer : offers) {
			if (auctionOffer.getOffer() > max)
				max = auctionOffer.getOffer();
		}
		return max;
	}

	public static void sendMail(int id_item) throws AddressException, MessagingException {
		Insertion insertion = insertionDAO.getInsertionById(id_item);
		JavaMail.sendMail(userDAO.getMailByUserId(insertion.getId_user()),
				JavaMail.Seller(insertion, insertion.getPrice()));
	}

}
