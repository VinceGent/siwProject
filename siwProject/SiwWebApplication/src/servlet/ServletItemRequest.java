package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;
import dbconnection.InsertionDAO;
import dbconnection.TradingManagerDAO;
import dbconnection.WishlistDAO;
import elements.AuctionOffer;
import elements.Insertion;

@WebServlet(description = "item", urlPatterns = { ServletItemRequest.item_selected, ServletItemRequest.buy_now,
		ServletItemRequest.auction_sales, ServletItemRequest.update_item, ServletItemRequest.buyNowInformation,
		ServletItemRequest.addToCartAndPay, ServletItemRequest.payment, ServletItemRequest.payItem,
		ServletItemRequest.addToCart })
public class ServletItemRequest extends Servlet {

	private static final long serialVersionUID = 1L;
	final static String item_selected = "/item_Selected";
	final static String buy_now = "/buyNow";
	final static String auction_sales = "/auctionSales";
	final static String update_item = "/update_item";
	final static String buyNowInformation = "/buyNowInformation";
	final static String addToCartAndPay = "/addToCartAndPay";
	final static String addToCart = "/addToCart";
	final static String payment = "/payment";
	final static String payItem = "/payItem";

	public ServletItemRequest() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = req.getServletPath();
		switch (path) {
		case buy_now:
			buyItem(req, resp);
			break;
		case auction_sales:
			doOffer(req, resp);
			break;
		case update_item:
			getOffer(req, resp);
			break;
		case buyNowInformation:
			getBuyNowInformation(req, resp);
			break;
		case addToCartAndPay:
			addToCartAndPay(req, resp);
			break;
		case payItem:
			payItem(req, resp);
			break;
		case addToCart:
			addToCart(req, resp);
			break;

		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = req.getServletPath();
		switch (path) {
		case item_selected:
			getInsertion(req, resp);
			break;
		case payment:
			payment(req, resp);
			break;
		case addToCart:
			addToCart(req, resp);
			break;
		}

	}

	private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JsonObject jsonObject = new JsonObject();
		if (isLogged(req)) {
			tradingManagerDAO.addToCart(getIdItem(req), getUserId(req));
			writeStateSuccess(jsonObject);
		} else {
			writeStateFailed(jsonObject);
		}
		writeResponse(resp, jsonObject);

	}

	private void payment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("PaymentPage.jsp");
		Insertion insertion = insertionDAO.getInsertionById(getIdItem(req));
		req.getSession().setAttribute("insertion", insertion);
		dispatcher.forward(req, resp);

	}

	private void payItem(HttpServletRequest req, HttpServletResponse resp) {

		tradingManagerDAO.buyItem(Integer.parseInt(req.getParameter("id_item")),
				Integer.parseInt(req.getSession().getAttribute("user_id").toString()));
	}

	private void addToCartAndPay(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JsonObject jsonObject = new JsonObject();
		if (isLogged(req)) {
			tradingManagerDAO.addToCart(getIdItem(req), getUserId(req));
			writeStateSuccess(jsonObject);
		} else {
			writeStateFailed(jsonObject);
		}
		writeResponse(resp, jsonObject);
	}

	private void doOffer(HttpServletRequest req, HttpServletResponse resp) {
		if (isLogged(req))
			tradingManagerDAO.insertOffer(getIdItem(req), getUserId(req), req.getParameter("offer"));
	}

	private void buyItem(HttpServletRequest req, HttpServletResponse resp) {

		if (isLogged(req))
			tradingManagerDAO.buyItem(getIdItem(req), getUserId(req));
		// vai a jsp di schermata completamento pagamento

	}

	private void getBuyNowInformation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id_item = -1;
		id_item = getIdItem(req);
		JsonObject obj = new JsonObject();
		if (id_item != -1) {
			Insertion insertion = insertionDAO.getInsertionById(id_item);
			if (insertion != null) {
				obj.addProperty("quantity", String.valueOf(insertion.getAmount()));
			}
		}
		writeResponse(resp, obj);
	}

	private void getOffer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_item = -1;
		id_item = getIdItem(req);
		if (id_item != -1) {
			ArrayList<AuctionOffer> offers = tradingManagerDAO.getOfferByIdItem(id_item);
			JsonObject obj = new JsonObject();
			if (offers.isEmpty())
				return;
			AuctionOffer max = offers.get(0);
			for (int i = 1; i < offers.size(); i++) {
				if (offers.get(i).getOffer() > max.getOffer()) {
					max = offers.get(i);
				}
			}

			obj.addProperty("id_user", max.getId_user());
			obj.addProperty("id_item", max.getId_item());
			obj.addProperty("offer", max.getOffer());
			if (isLogged(req) && getUserId(req) == max.getId_user()) {
				obj.addProperty("yourOffer", "true");

			} else {
				obj.addProperty("yourOffer", "false");

			}
			writeResponse(resp, obj);

		} else {

			// go to error jsp page
		}

	}

	private void getInsertion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_item = -1;
		id_item = Integer.parseInt(req.getParameter("id_item"));
		if (id_item != -1) {

			Insertion insertion = insertionDAO.getInsertionById(id_item);
			req.setAttribute("insertion", insertion);
			if (isLogged(req)) {
				List<Insertion> list = wishlistDAO.getWishlist(getUserId(req));
				for (Insertion insertion2 : list) {
					if (insertion2.getId_item() == id_item)
						req.getSession().setAttribute("inWishlist", true);
				}
			}
			RequestDispatcher dispatcher = req.getRequestDispatcher("item.jsp");
			dispatcher.forward(req, resp);
		} else {

			// go to error jsp page
		}

	}

}
