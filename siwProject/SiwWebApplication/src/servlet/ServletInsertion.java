package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dbconnection.InsertionDAO;
import elements.Insertion;
import elements.Sales_type;

@WebServlet(description = "insertion", urlPatterns = { ServletInsertion.newInsertion,
		ServletInsertion.searchInsertion })
public class ServletInsertion extends Servlet {
	public static final String newInsertion = "/newInsertion";
	public static final String searchInsertion = "/searchInsertion";
	private static final long serialVersionUID = 1L;

	public ServletInsertion() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case searchInsertion:
			searchInsertion(req, resp);
			break;

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case newInsertion:
			addNewInsertion(req, resp);
			break;
		}
	}

	private void searchInsertion(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		List<Insertion> insertions = insertionDAO.getInsertionByName(req.getParameter("name"));
		req.getSession().setAttribute("insertions", insertions);
		RequestDispatcher dispatcher = req.getRequestDispatcher("searchPage.jsp");
		dispatcher.forward(req, resp);
	}

	private void addNewInsertion(HttpServletRequest req, HttpServletResponse resp) {
		java.util.Date date = null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("expiration_date"));
			Date date2 = new Date(date.getTime());
			System.out.println(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		insertionDAO.addNewInsertion(Integer.parseInt(req.getSession(false).getAttribute("user_id").toString()),
				req.getParameter("title"), new java.util.Date(), date, Integer.valueOf(req.getParameter("amount")),
				Sales_type.valueOf(req.getParameter("seller_type")), Float.valueOf(req.getParameter("price")),
				req.getParameter("description"));

	}

}
