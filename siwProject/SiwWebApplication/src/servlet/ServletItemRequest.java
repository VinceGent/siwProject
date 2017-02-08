package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import dbconnection.InsertionDAO;
import dbconnection.UserDAO;
import elements.AuctionOffer;
import elements.Insertion;

@WebServlet(description = "item", urlPatterns = { ServletItemRequest.item_selected, ServletItemRequest.buy_now,
		ServletItemRequest.auction_sales, ServletItemRequest.update_item })
public class ServletItemRequest extends HttpServlet {
	final static String item_selected = "/item_Selected";
	final static String buy_now = "/buyNow";
	final static String auction_sales = "/auctionSales";
	final static String update_item = "/update_item";

	public ServletItemRequest() {
		db = new InsertionDAO();

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
		default:

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
		}

	}

	private void doOffer(HttpServletRequest req, HttpServletResponse resp) {
		// aggiornare l'offerta massima sul database per l'asta scelta
//		System.out.println(req.getParameter("id_item"));
//		System.out.println(req.getParameter("offer"));
//		System.out.println(req.getSession().getAttribute("user_id"));
		
		db.insertOffer(req.getParameter("id_item"),req.getSession().getAttribute("user_id").toString(),req.getParameter("offer"));
	}

	private void buyItem(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println(req.getParameter("id"));
		// vai a jsp di schermata completamento pagamento

	}

	private void getOffer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_item = -1;
		id_item = Integer.parseInt(req.getParameter("id_item"));
		if (id_item != -1) {
			System.out.println("getoffer");
			ArrayList<AuctionOffer> offers = db.getOfferByIdItem(id_item);
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
//			System.out.println("sessione attuale utente : " +req.getSession().getAttribute("user_id").toString());
			System.out.println("offerta massima è di utente: " + max.getId_user());
			if(req.getSession().getAttribute("user_id")!=null&&req.getSession().getAttribute("user_id").toString().equals(Integer.toString(max.getId_user())))
			{
				System.out.println("HAI VINTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
				obj.addProperty("yourOffer", "true");
			}
			else{
				obj.addProperty("yourOffer", "false");
				
				
			}
			System.out.println(obj.toString());
			resp.getWriter().write(obj.toString());

		} else {

			// go to error jsp page
		}

	}

	private void getInsertion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_item = -1;
		id_item = Integer.parseInt(req.getParameter("id_item"));
		if (id_item != -1) {

			Insertion insertion = db.getInsertionById(id_item);
			req.setAttribute("insertion", insertion);
			RequestDispatcher dispatcher = req.getRequestDispatcher("item.jsp");
			dispatcher.forward(req, resp);
		} else {

			// go to error jsp page
		}

	}

	private static final long serialVersionUID = 1L;
	private InsertionDAO db;

}
