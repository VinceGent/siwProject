
package dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.batch.Main;

import elements.Feedback;
import elements.Insertion;
import elements.Sales_type;

public class FeedbackDAO extends DbManager {
	private InsertionDAO dbInsertion;

	public FeedbackDAO() {
		super();
		dbInsertion = new InsertionDAO();
	}

	public void addFeedback(final int id_user, final String username, final int id_insertion, String description,
			final int rating) {
		String query = "INSERT INTO feedback (id_user,username,id_insertion,description,rating) VALUES(?,?,?,?,?)";

		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setInt(1, id_user);
			mPreparedStatement.setString(2, username);
			mPreparedStatement.setInt(3, id_insertion);
			mPreparedStatement.setString(4, description);
			mPreparedStatement.setInt(5, rating);
			mPreparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Feedback> getCommentsByID(final int id_item) {
		List<Feedback> feedbacks = new LinkedList<Feedback>();
		Feedback feedback = null;
		String query = "Select * from feedback where id_insertion=?;";
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setInt(1, id_item);
			final ResultSet mResultSet = mPreparedStatement.executeQuery();
			while (mResultSet.next()) {
				feedback = new Feedback(Integer.parseInt(mResultSet.getString("id_user")),
						mResultSet.getString("username"), Integer.parseInt(mResultSet.getString("id_insertion")),
						mResultSet.getString("description"), mResultSet.getInt("rating"));
				feedbacks.add(feedback);
			}
			closeConnection(mConnection);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return feedbacks;

	}

	public Feedback getAvgFeedback(final int id_item) {
		Feedback feedback = null;
		final String query = " Select AVG(rating) FROM feedback where id_insertion=?;";
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setInt(1, id_item);
			final ResultSet mResultSet = mPreparedStatement.executeQuery();
			while (mResultSet.next()) {
				feedback = new Feedback(mResultSet.getInt(1));
			}
			closeConnection(mConnection);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return feedback;

	}

}
