package servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
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
import elements.Order;
import elements.OrderState;
import elements.Sales_type;

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
		case payAllCart:
			payAllCart(req, resp);
			break;
		}

	}

	private void payAllCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
		RequestDispatcher dispatcher = req.getRequestDispatcher("PaymentPage.jsp");
		if (getIdItem(req) != 0) {
			Insertion insertion = insertionDAO.getInsertionById(getIdItem(req));
			req.getSession().setAttribute("insertion", insertion);
		} else {
			float total = 0;
			List<Order> orders = tradingManagerDAO.getOrdersByIdUser(getUserId(req));
			for (Order order : orders) {
				if(order.getState()==OrderState.pagato)
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

	private float getMaxOffertPrice(Insertion insertion) {
		ArrayList<AuctionOffer> offers = tradingManagerDAO.getOfferByIdItem(insertion.getId_item());
		float max = offers.get(0).getOffer();
		for (AuctionOffer auctionOffer : offers) {
			if (auctionOffer.getOffer() > max)
				max = auctionOffer.getOffer();
		}
		return max;
	}

	private void payItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JsonObject jsonObject = new JsonObject();
		if (isLogged(req)) {
			if (getIdItem(req) != 0) {
				tradingManagerDAO.buyItem(getIdItem(req), getUserId(req));
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
	      List<String>images=new LinkedList<String>();
	    
	      
	      
	      
	      
	      File folder = new File(ServletUpload.uploadPathFolder+"insertion_"+id_item+"/");
	      File[] listOfFiles = folder.listFiles();
	      if(listOfFiles!=null)
	          for (int i = 0; i < listOfFiles.length; i++) {
	            if (listOfFiles[i].isFile()) {
	              
	              System.out.println("File " + listOfFiles[i].getName());
	              images.add(listOfFiles[i].getName());
	            } else if (listOfFiles[i].isDirectory()) {
	              System.out.println("Directory " + listOfFiles[i].getName());
	            }
	          }
	          
	      req.getSession().setAttribute("images", images);
	      RequestDispatcher dispatcher = req.getRequestDispatcher("item.jsp");
	      dispatcher.forward(req, resp);
	    } else {

	      // go to error jsp page
	    }

	  }

}
