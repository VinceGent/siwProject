package dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import elements.AuctionOffer;
import elements.Insertion;
import elements.Sales_type;

public class InsertionDAO extends DbManager {

	public InsertionDAO() {
		super();
	}

	public void addNewInsertion(final int id_user, final String name, final Date insertion_date,
			final Date expiration_date, final int amount, final Sales_type sales_type, final float price,
			final String description) {
		Insertion insertion = new Insertion(id_user, name, insertion_date, expiration_date, amount, sales_type, price,
				description);

		String query = "INSERT INTO insertion (id_user, name, insertion_date, expiration_date, amount, sales_type, price, description) VALUES (?,?,?,?,?,?,?,?);";
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setString(1, Integer.toString(insertion.getId_user()));
			mPreparedStatement.setString(2, name);
			Long sec = insertion_date.getTime();
			mPreparedStatement.setDate(3, new java.sql.Date(sec));
			sec = expiration_date.getTime();
			mPreparedStatement.setDate(4, new java.sql.Date(sec));
			mPreparedStatement.setString(5, Integer.toString(amount));
			mPreparedStatement.setString(6, sales_type.name());
			mPreparedStatement.setString(7, Float.toString(price));
			mPreparedStatement.setString(8, description);
			mPreparedStatement.execute();
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Insertion> getInsertionByName(final String name) {

		List<Insertion> insertions = new LinkedList<Insertion>();
		Insertion insertion = null;

		final String query = "select * from insertion where name = ?;";
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setString(1, name);
			final ResultSet mResultSet = mPreparedStatement.executeQuery();
			while (mResultSet.next()) {
				insertion = new Insertion(Integer.parseInt(mResultSet.getString("id_user")),
						Integer.parseInt(mResultSet.getString("id_item")), mResultSet.getString("name"),
						new Date(mResultSet.getDate("insertion_date").getTime()),
						new Date(mResultSet.getDate("expiration_date").getTime()),
						Integer.parseInt(mResultSet.getString("amount")),
						Sales_type.valueOf(mResultSet.getString("sales_type")),
						Float.parseFloat(mResultSet.getString("price")), mResultSet.getString("description"));
				insertions.add(insertion);
			}
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return insertions;

	}

	public Insertion getInsertionById(final int id_item) {

		Insertion insertion = null;

		final String query = "select * from insertion where id_item= ?;";
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setString(1, Integer.toString(id_item));
			final ResultSet mResultSet = mPreparedStatement.executeQuery();
			while (mResultSet.next()) {
				insertion = new Insertion(Integer.parseInt(mResultSet.getString("id_user")),
						Integer.parseInt(mResultSet.getString("id_item")), mResultSet.getString("name"),
						new Date(mResultSet.getDate("insertion_date").getTime()),
						new Date(mResultSet.getDate("expiration_date").getTime()),
						Integer.parseInt(mResultSet.getString("amount")),
						Sales_type.valueOf(mResultSet.getString("sales_type")),
						Float.parseFloat(mResultSet.getString("price")), mResultSet.getString("description"));

			}
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return insertion;

	}

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
						Float.parseFloat(mResultSet.getString("offer")),mResultSet.getInt("id_offer"));
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

}
