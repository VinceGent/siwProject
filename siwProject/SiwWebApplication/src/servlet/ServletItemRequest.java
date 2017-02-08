package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbconnection.UserDAO;
import elements.Insertion;

@WebServlet(description = "item", urlPatterns = { ServletItemRequest.item_selected, ServletItemRequest.buy_now,
		ServletItemRequest.auction_sales })
public class ServletItemRequest extends HttpServlet {
	final static String item_selected = "/item_Selected";
	final static String buy_now = "/buyNow";
	final static String auction_sales = "/auctionSales";

	public ServletItemRequest() {
		db = UserDAO.getInstance();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case item_selected:
			getInsertion(req, resp);
			break;
		case buy_now:
			buyItem(req, resp);
			break;
		case auction_sales:
			doOffer(req,resp);
			break;
		default:

			break;
		}

	}

	private void doOffer(HttpServletRequest req, HttpServletResponse resp) {
		// aggiornare l'offerta massima sul database per l'asta scelta
		
	}

	private void buyItem(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println(req.getParameter("id"));
		//vai a jsp di schermata  completamento pagamento
		

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
	private UserDAO db;

}
