package servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "*.jsp"})

public class MyFilter implements Filter {
//	protected final static String insertionPage = "/insertionPage.jsp";
//	protected final static String item = "/item.jsp";
//	protected final static String paymentPage = "/PaymentPage.jsp";
//	protected final static String searchPage = "/searchPage.jsp";
//	protected final static String shoppingCart = "/ShoppingCart.jsp";
//	protected final static String uploadSuccess = "/uploadSuccess.jsp";
//	protected final static String userProfile = "/userProfile.jsp";
//	protected final static String wishlist = "/wishlist.jsp";

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.sendRedirect("404.html");
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}