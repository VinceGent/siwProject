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
	
	public List<Insertion> getWishlist(int user){
		List<Insertion> insertions = null;
		System.out.println("called getWishlist");
		String query = "select * from wishlist_item where id_user=?";
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setInt(1, user);
			final ResultSet mResultSet = mPreparedStatement.executeQuery();
				insertions = new ArrayList<>();
			while(mResultSet.next()){
				Insertion i = dbInsertion.getInsertionById(Integer.parseInt(mResultSet.getString("id_insertion").toString()));
				insertions.add(i);
			}
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertions;
	}
	
	public void removeWishlistItem(int item, int user){
		String query = "delete from wishlist_item where id_insertion=? and id_user=?";
		
		try {
		final Connection mConnection = createConnection();
		final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setInt(1, item);
			mPreparedStatement.setInt(2, user);
			mPreparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void addWishlistItem(int user,int item){
		String query = "INSERT INTO wishlist_item (id_user,id_insertion) VALUES(?,?)";
		
		try {
		final Connection mConnection = createConnection();
		final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setInt(1, user);
			mPreparedStatement.setInt(2, item);
			mPreparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


