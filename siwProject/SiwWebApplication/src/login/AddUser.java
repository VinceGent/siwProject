package login;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dbconnection.UserDAO;

/**
 * Servlet implementation class AddUser
 */
@WebServlet(description = "login", urlPatterns = {"/AddUser"})
//@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userdb;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
        super();
        userdb = UserDAO.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("chiamo dopost add user");
		System.out.println(request.getParameter("user"));
		System.out.println(request.getParameter("email"));
		System.out.println(request.getParameter("password"));
		
		userdb.addUser(request.getParameter("user"), request.getParameter("email"), request.getParameter("password"));
		
		response.getWriter().write("OK");
	}

}
