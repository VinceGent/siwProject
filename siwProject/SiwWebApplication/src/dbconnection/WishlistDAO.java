package dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import elements.Insertion;
import elements.Sales_type;

public class WishlistDAO extends DbManager {

	private InsertionDAO dbInsertion;

	public WishlistDAO() {
		super();
		dbInsertion = new InsertionDAO();
	}

	public List<Insertion> getWishlist(int user) {
		List<Insertion> insertions = null;
		String query = "select * from wishlist_item where id_user=?;";
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setInt(1, user);
			final ResultSet mResultSet = mPreparedStatement.executeQuery();
			insertions = new ArrayList<>();
			while (mResultSet.next()) {
				Insertion i = dbInsertion
						.getInsertionById(Integer.parseInt(mResultSet.getString("id_insertion").toString()));
				insertions.add(i);
			}
			closeConnection(mConnection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertions;
	}

	public boolean isWishlistItem(int id, int user) {
		String query = "select count(*) from wishlist_item where id_user=? and id_insertion=?;";
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setInt(1, user);
			mPreparedStatement.setInt(2, id);
			final ResultSet mResultSet = mPreparedStatement.executeQuery();
			if (mResultSet.next()) {
				System.out.println(mResultSet.getString("count(*)"));
				if (Integer.parseInt(mResultSet.getString("count(*)")) > 0)
					return true;
			}
			closeConnection(mConnection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void removeWishlistItem(int item, int user) {
		String query = "delete from wishlist_item where id_insertion=? and id_user=?;";

		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setInt(1, item);
			mPreparedStatement.setInt(2, user);
			mPreparedStatement.executeUpdate();
			closeConnection(mConnection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addWishlistItem(int user, int item) {
		String query = "INSERT INTO wishlist_item (id_user,id_insertion) VALUES(?,?);";

		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setInt(1, user);
			mPreparedStatement.setInt(2, item);
			mPreparedStatement.executeUpdate();
			closeConnection(mConnection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void clearWishlist(int userId) {

		String query = "DELETE FROM wishlist_item WHERE id_user=?;";

		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setInt(1, userId);
			mPreparedStatement.executeUpdate();
			closeConnection(mConnection);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
