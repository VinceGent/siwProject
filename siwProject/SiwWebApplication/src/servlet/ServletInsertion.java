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
		System.out.println();
		if(req.getParameter("new")!=null)
			req.getSession().removeAttribute("loaded");
		if(req.getParameter("currentPage")!=null)
			req.getSession().setAttribute("currentPage",req.getParameter("currentPage"));
		if(req.getSession().getAttribute("loaded")==null)
		{
			System.out.println("cercoooooo");
//			req.getSession().setAttribute("", arg1);
		
//		System.out.println("Categoria         " + req.getParameter("category"));
		List<Insertion> inser = insertionDAO.getInsertionByName(req.getParameter("name"),req.getParameter("category"));
		HashMap<Insertion, List<String>> insertions = new HashMap<Insertion, List<String>>();
		for (Insertion insertion : inser) {
			insertions.put(insertion, resource.getImageInsertion(insertion.getId_item()));
		}

		req.getSession().setAttribute("insertions", insertions);
		req.getSession().setAttribute("searchedCategory",req.getParameter("category"));
		
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("searchPage.jsp");
		dispatcher.forward(req, resp);
	}

	private void addNewInsertion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("cat     "+req.getParameter("category"));
		java.util.Date date = null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("expiration_date"));
			Date date2 = new Date(date.getTime());
			// System.out.println(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int id = insertionDAO.addNewInsertion(
				Integer.parseInt(req.getSession(false).getAttribute("user_id").toString()), req.getParameter("title"),
				new java.util.Date(), date, Integer.valueOf(req.getParameter("amount")),
				Sales_type.valueOf(req.getParameter("seller_type")), Float.valueOf(req.getParameter("price")),
				req.getParameter("description"),req.getParameter("category"));
		resp.getWriter().write(Integer.toString(id));
	}
}
