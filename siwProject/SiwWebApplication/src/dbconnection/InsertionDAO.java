package dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.Statement;

import elements.AuctionOffer;
import elements.Insertion;
import elements.OrderState;
import elements.Sales_type;

public class InsertionDAO extends DbManager {

	public InsertionDAO() {
		super();
	}

	public int addNewInsertion(final int id_user, final String name, final Date insertion_date,
			final Date expiration_date, final int amount, final Sales_type sales_type, final float price,
			final String description, final String category) {
		Insertion insertion = new Insertion(id_user, name, insertion_date, expiration_date, amount, sales_type, price,
				description, category);

		String query = "INSERT INTO insertion (id_user, name, insertion_date, expiration_date, amount, sales_type, price, description,category) VALUES (?,?,?,?,?,?,?,?,?);";
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
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
			mPreparedStatement.setString(9, category);
			mPreparedStatement.executeUpdate();

			ResultSet result = mPreparedStatement.getGeneratedKeys();
			if (result.next()) {
				System.out.println("last insert id" + result.getInt(1));
				return result.getInt(1);
			}
			closeConnection(mConnection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public List<Insertion> getInsertionByName(final String name, final String category) {

		List<Insertion> insertions = new LinkedList<Insertion>();
		Insertion insertion = null;
		String query = null;
		Boolean ok = false;
		if(category.equals("Tutte le categorie")){
			query="select * from insertion;";
		}else{
			query="select * from insertion where category=?;";
			ok = true;
		}
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			if(ok){
				mPreparedStatement.setString(1, category);
			}
			final ResultSet mResultSet = mPreparedStatement.executeQuery();
			while (mResultSet.next()) {
				if (!mResultSet.getString("name").contains(name))
					continue;
				insertion = new Insertion(Integer.parseInt(mResultSet.getString("id_user")),
						Integer.parseInt(mResultSet.getString("id_item")), mResultSet.getString("name"),
						new Date(mResultSet.getDate("insertion_date").getTime()),
						new Date(mResultSet.getDate("expiration_date").getTime()),
						Integer.parseInt(mResultSet.getString("amount")),
						Sales_type.valueOf(mResultSet.getString("sales_type")),
						Float.parseFloat(mResultSet.getString("price")), mResultSet.getString("description"),
						mResultSet.getString("category"));
				insertions.add(insertion);
			}
			closeConnection(mConnection);
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
						Float.parseFloat(mResultSet.getString("price")), mResultSet.getString("description"),
						mResultSet.getString("category"));

			}
			closeConnection(mConnection);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return insertion;

	}

}
