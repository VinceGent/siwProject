package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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

@WebServlet(description = "insertion", urlPatterns = { ServletInsertion.newInsertion, ServletInsertion.searchInsertion,
		ServletInsertion.getInsertionPage })
public class ServletInsertion extends Servlet {
	public static final String newInsertion = "/newInsertion";
	public static final String searchInsertion = "/searchInsertion";
	public static final String getInsertionPage = "/getInsertionPage";
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
		case getInsertionPage:
			getInsertionPage(req, resp);
			break;

		}

	}

	private void getInsertionPage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println(isLogged(req));
		if (!isLogged(req)) {
			badRequestPage(req, resp);
			return;
		}
		System.out.println("get insertion dio");
		req.getRequestDispatcher("insertionPage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!isLogged(req)) {
			return;
		}
		String path = req.getServletPath();
		switch (path) {
		case newInsertion:
			addNewInsertion(req, resp);
			break;
		}

	}

	private void searchInsertion(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(req.getParameter("name")==null)
		{
			badRequestPage(req, resp);
			return;
		}
		if (req.getParameter("new") != null)
			req.getSession().removeAttribute("loaded");
		if (req.getParameter("currentPage") != null)
			req.getSession().setAttribute("currentPage", req.getParameter("currentPage"));
		if (req.getSession().getAttribute("loaded") == null) {
			System.out.println("cercoooooo");
			// req.getSession().setAttribute("", arg1);
			// System.out.println("Categoria " + req.getParameter("category"));
			List<Insertion> inser = insertionDAO.getInsertionByName(req.getParameter("name"),
					req.getParameter("category"));
			HashMap<Insertion, List<String>> insertions = new HashMap<Insertion, List<String>>();
			for (Insertion insertion : inser) {
				insertions.put(insertion, resource.getImageInsertion(insertion.getId_item()));
			}

			req.getSession().setAttribute("insertions", insertions);
			req.getSession().setAttribute("searchedCategory", req.getParameter("category"));

		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("searchPage.jsp");
		if(dispatcher==null)
			System.out.println("nullooooo");
		dispatcher.forward(req, resp);
	}

	private void addNewInsertion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		java.util.Date date = null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("expiration_date"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.util.Date insertionDate=new java.util.Date();
		date.setHours(insertionDate.getHours());
		date.setMinutes(insertionDate.getMinutes());
		date.setSeconds(insertionDate.getSeconds());
		int id = insertionDAO.addNewInsertion(
				Integer.parseInt(req.getSession(false).getAttribute("user_id").toString()), req.getParameter("title"),
				insertionDate, date, Integer.valueOf(req.getParameter("amount")),
				Sales_type.valueOf(req.getParameter("seller_type")), Float.valueOf(req.getParameter("price")),
				req.getParameter("description"), req.getParameter("category"));
		resp.getWriter().write(Integer.toString(id));
	}
}
