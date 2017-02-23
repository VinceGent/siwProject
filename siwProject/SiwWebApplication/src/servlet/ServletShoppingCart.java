package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import elements.Insertion;
import elements.Order;
import elements.Sales_type;

@WebServlet(description = "shoppingCart", urlPatterns = { ServletShoppingCart.shoppingCartPage ,ServletShoppingCart.removeFromCart})
public class ServletShoppingCart extends Servlet {

	private static final long serialVersionUID = 1L;
	public final static String shoppingCartPage = "/shoppingCartPage";
	public final static String removeFromCart = "/removeFromCart";

	public ServletShoppingCart() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String path = req.getServletPath();
		switch (path) {
		case shoppingCartPage:
			goToShoppingCartPage(req, resp);
			break;

		}

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case removeFromCart:
			removeFromCart(req,resp);
			break;

		}

	}

	private void removeFromCart(HttpServletRequest req, HttpServletResponse resp) {
		if(!isLogged(req))
			return;
		tradingManagerDAO.removeOrder(Integer.parseInt(req.getParameter("id_order")));
		
	}

	private void goToShoppingCartPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(!isLogged(req))
		{
			badRequestPage(req, resp);
			return;	
		}
		HashMap<Order, Insertion>ordersMap=new HashMap<Order,Insertion>();
		HashMap<Order, List<String>>imagesMap=new HashMap<Order,List<String>>();
		List<Order>orders=tradingManagerDAO.getOrdersByIdUser(getUserId(req));
		for (Order order : orders) {
			Insertion insertion=insertionDAO.getInsertionById(order.getId_insertion());
			List<String>images=resource.getImageInsertion(insertion.getId_item());
			if(insertion.getSales_type()==Sales_type.asta)
			{
			insertion.setPrice(getMaxOffertPrice(insertion));	
			}
			imagesMap.put(order, images);
			ordersMap.put(order,insertion);
		}
		req.getSession().setAttribute("images", imagesMap);
		req.getSession().setAttribute("orders", ordersMap);
		RequestDispatcher dispatcher = req.getRequestDispatcher("ShoppingCart.jsp");
		dispatcher.forward(req, resp);

	}

}
