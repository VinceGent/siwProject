package servlet;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale.Category;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval;
import org.joda.time.Minutes;

import com.google.gson.JsonObject;
import dbconnection.InsertionDAO;
import dbconnection.TradingManagerDAO;
import dbconnection.WishlistDAO;
import elements.AuctionOffer;
import elements.Feedback;
import elements.Insertion;
import elements.Order;
import elements.OrderState;
import elements.Sales_type;
import utility.JavaMail;

@WebServlet(description = "item", urlPatterns = { ServletItemRequest.item_selected, ServletItemRequest.buy_now,
		ServletItemRequest.auction_sales, ServletItemRequest.update_item, ServletItemRequest.buyNowInformation,
		ServletItemRequest.addToCartAndPay, ServletItemRequest.payment, ServletItemRequest.payItem,
		ServletItemRequest.addToCart, ServletItemRequest.payAllCart })
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
	final static String payAllCart = "/payAllCart";

	public ServletItemRequest() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (missingParameter(req, resp))
			return;

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
			try {
				payItem(req, resp);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case addToCart:
			addToCart(req, resp);
			break;

		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = req.getServletPath();
		if (!path.equals(payAllCart) && missingParameter(req, resp))
			return;
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
		case payAllCart:
			try {
				payAllCart(req, resp);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}

	}

	private void payAllCart(HttpServletRequest req, HttpServletResponse resp) throws IOException, AddressException, MessagingException {
		JsonObject jsonObject = new JsonObject();
		if (!isLogged(req))
			writeStateFailed(jsonObject);
		else {
			tradingManagerDAO.buyAllCart(getUserId(req));
			writeStateSuccess(jsonObject);

		}
		writeResponse(resp, jsonObject);
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
		if (!isLogged(req)) {
			badRequestPage(req, resp);
			return;
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("PaymentPage.jsp");
		if (getIdItem(req) != 0) {
			Insertion insertion = insertionDAO.getInsertionById(getIdItem(req));
			req.getSession().setAttribute("insertion", insertion);
		} else {
			req.getSession().removeAttribute("insertion");
			float total = 0;
			List<Order> orders = tradingManagerDAO.getOrdersByIdUser(getUserId(req));
			for (Order order : orders) {
				if (order.getState() == OrderState.pagato)
					continue;
				Insertion insertion = insertionDAO.getInsertionById(order.getId_insertion());
				if (insertion.getAmount() > 0 && insertion.getSales_type() == Sales_type.compraora)
					total += insertion.getPrice();
				else if (insertion.getAmount() > 0 && insertion.getSales_type() == Sales_type.asta) {
					total += getMaxOffertPrice(insertion);

				}
			}
			req.getSession().setAttribute("total", total);
		}
		req.getSession().setAttribute("id_item", getIdItem(req));
		dispatcher.forward(req, resp);
	}

	private void payItem(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, AddressException, MessagingException {
		JsonObject jsonObject = new JsonObject();
		if (isLogged(req)) {
			if (getIdItem(req) != 0) {
				if (tradingManagerDAO.buyItem(getIdItem(req), getUserId(req))) {
					sendMail(getIdItem(req));
				}
			}
			writeStateSuccess(jsonObject);

		} else {
			writeStateFailed(jsonObject);
		}
		writeResponse(resp, jsonObject);
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

	private void doOffer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (isLogged(req) && !isExpired(getIdItem(req)))
			tradingManagerDAO.insertOffer(getIdItem(req), getUserId(req), req.getParameter("offer"));
		else
			badRequestPage(req, resp);
	}

	private boolean isExpired(int idItem) {
		return new Date(insertionDAO.getInsertionById(idItem).getExpiration_date().getTime()).before(new Date());
	}

	private void buyItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (isLogged(req)) {
			tradingManagerDAO.buyItem(getIdItem(req), getUserId(req));

		} else
			badRequestPage(req, resp);

	}

	private void getBuyNowInformation(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		int id_item = -1;
		id_item = getIdItem(req);
		JsonObject obj = new JsonObject();
		if (id_item != -1) {
			Insertion insertion = insertionDAO.getInsertionById(id_item);
			if (insertion != null) {
				obj.addProperty("quantity", String.valueOf(insertion.getAmount()));
			}
		} else {
			badRequestPage(req, resp);
			return;
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

			badRequestPage(req, resp);
		}

	}

	private void getInsertion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_item = -1;
		id_item = Integer.parseInt(req.getParameter("id_item"));
		if (id_item != -1) {
			Insertion insertion = insertionDAO.getInsertionById(id_item);
			req.setAttribute("insertion", insertion);
			List<String> images = resource.getImageInsertion(id_item);
			req.getSession().setAttribute("images", images);
			List<Feedback> feedbacks = feedbackDAO.getCommentsByID(id_item);
			Feedback feed = feedbackDAO.getAvgFeedback(id_item);
			req.getSession().setAttribute("feedbacks", feedbacks);
			req.getSession().setAttribute("feedback", feed);
			RequestDispatcher dispatcher = req.getRequestDispatcher("item.jsp");
			dispatcher.forward(req, resp);
		} else {

			badRequestPage(req, resp);
		}

	}

	public static void main(String[] args) {
		InsertionDAO dao = new InsertionDAO();
		Date expirationDate = null;
		Date insertionDate = new Date();
		try {
			expirationDate = new SimpleDateFormat("dd/MM/yyyy").parse("22/02/2017");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		expirationDate.setHours(insertionDate.getHours());
		expirationDate.setMinutes(insertionDate.getMinutes() + 3);
		expirationDate.setSeconds(insertionDate.getSeconds());
		dao.addNewInsertion(1, "c", insertionDate, expirationDate, 10, Sales_type.asta, 1f, "aa", "Modellismo");
//		System.out.println("data inserimento   " + expirationDate.toInstant());

	}

}
