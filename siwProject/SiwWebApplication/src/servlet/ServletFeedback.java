package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import dbconnection.FeedbackDAO;
import elements.Feedback;
import elements.Insertion;

@WebServlet(description = "feedback", urlPatterns = { ServletFeedback.addFeedback, ServletFeedback.getFeedback })

public class ServletFeedback extends Servlet {

	private static final long serialVersionUID = 1L;
	protected static final String addFeedback = "/addFeedback";
	protected static final String getFeedback = "/getFeedback";

	public ServletFeedback() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (missingParameter(req, resp)) {
			badRequestPage(req, resp);
			return;
		}
		switch (req.getServletPath()) {
		case addFeedback:
			addFeedback(req, resp);
			break;
		}

	}

	private void addFeedback(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JsonObject jsonObject = new JsonObject();
		if (isLogged(req) && getUsername(req) != null) {
			feedbackDAO.addFeedback(getUserId(req), getUsername(req), getIdItem(req), req.getParameter("description"),
					Integer.parseInt(req.getParameter("rating")));
			writeStateSuccess(jsonObject);
			jsonObject.addProperty("inFeedback", true);
		} else {
			writeStateFailed(jsonObject);
		}
			writeResponse(resp, jsonObject);
	}

}
