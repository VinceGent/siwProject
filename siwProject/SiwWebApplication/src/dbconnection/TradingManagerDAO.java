package dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import elements.AuctionOffer;
import elements.Insertion;
import elements.OrderState;

public class TradingManagerDAO extends DbManager {

	public ArrayList<AuctionOffer> getOfferByIdItem(int id_item) {
		AuctionOffer offer = null;
		ArrayList<AuctionOffer> offers = new ArrayList<AuctionOffer>();
		final String query = "select * from auction_offer where id_insertion= ?;";
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setString(1, Integer.toString(id_item));
			final ResultSet mResultSet = mPreparedStatement.executeQuery();
			while (mResultSet.next()) {
				offer = new AuctionOffer(Integer.parseInt(mResultSet.getString("id_user")),
						Integer.parseInt(mResultSet.getString("id_insertion")),
						Float.parseFloat(mResultSet.getString("offer")), mResultSet.getInt("id_offer"));
				offers.add(offer);

			}
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return offers;
	}

	public void insertOffer(String id_item, String id_user, String offer) {

		AuctionOffer auctionOffer = new AuctionOffer(Integer.parseInt(id_user), Integer.parseInt(id_item),
				Float.parseFloat(offer));

		String query = "INSERT INTO auction_offer (id_user, id_insertion, offer) VALUES (?,?,?);";
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setString(1, Integer.toString(auctionOffer.getId_user()));
			mPreparedStatement.setInt(2, auctionOffer.getId_item());
			mPreparedStatement.setFloat(3, auctionOffer.getOffer());
			mPreparedStatement.execute();
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void addToCart(int id_item,int id_user)
	{
		
		final String query2 = "INSERT INTO orders(id_user,id_insertion,order_state,order_date) VALUES(?,?,?,?)";
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query2);
			mPreparedStatement.setInt(1, id_user);
			mPreparedStatement.setInt(2, id_item);
			mPreparedStatement.setString(3, OrderState.nonpagato.toString());
			mPreparedStatement.setDate(4, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			mPreparedStatement.execute();
			closeConnection();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void buyItem(int id_item, int id_user) {
		InsertionDAO insertionDao = new InsertionDAO();
		Insertion insertion = insertionDao.getInsertionById(id_item);
		if(insertion.getAmount()<1)
			return;
		final String query = "UPDATE insertion SET amount=? WHERE id_item=?;";
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setInt(1, insertion.getAmount() - 1);
			mPreparedStatement.setInt(2, id_item);
			mPreparedStatement.executeUpdate();
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

}