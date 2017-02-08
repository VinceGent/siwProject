package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbconnection.InsertionDAO;
import dbconnection.UserDAO;
import elements.Sales_type;

@WebServlet(description = "insertion", urlPatterns = { ServletInsertion.newInsertion })
public class ServletInsertion extends HttpServlet {
	public static final String newInsertion = "/newInsertion";
	private static final long serialVersionUID = 1L;
	private InsertionDAO db;

	public ServletInsertion() {

		db = new InsertionDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
	 	java.util.Date date=null;
		try {
			 date =new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("expiration_date"));
			Date date2=new Date(date.getTime());
			System.out.println(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		db.addNewInsertion(Integer.parseInt(req.getSession(false).getAttribute("user_id").toString()),
				req.getParameter("title"), new java.util.Date(), date, Integer.valueOf(req.getParameter("amount")),
				Sales_type.valueOf(req.getParameter("seller_type")), Float.valueOf(req.getParameter("price")),
				req.getParameter("description"));

		
		
	
		
		
	}



}
