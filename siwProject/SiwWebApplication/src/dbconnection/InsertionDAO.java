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
import java.util.regex.Pattern;

import org.joda.time.DateTime;

import com.mysql.jdbc.Statement;

import elements.AuctionOffer;
import elements.Insertion;
import elements.OrderState;
import elements.Sales_type;

public class InsertionDAO extends DbManager {

	private java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public InsertionDAO() {
		super();
	}

	public int addNewInsertion(final int id_user, final String name, final Date insertion_date,
			final Date expiration_date, final int amount, final Sales_type sales_type, final float price,
			final String description, final String category) {
		Insertion insertion = new Insertion(id_user, name, insertion_date, expiration_date, amount, sales_type, price,
				description, category);
		// java.util.Date dt = new java.util.Date();

		// String currentTime = sdf.format(dt);
		final Connection mConnection = createConnection();

		String query = "INSERT INTO insertion (id_user, name, insertion_date, expiration_date, amount, sales_type, price, description,category,processed) VALUES (?,?,?,?,?,?,?,?,?,0);";
		try {
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			mPreparedStatement.setString(1, Integer.toString(insertion.getId_user()));
			mPreparedStatement.setString(2, name);
			// Long sec = insertion_date.getTime();
			// mPreparedStatement.setDate(3, new java.sql.Date(sec));
			mPreparedStatement.setString(3, sdf.format(insertion_date));
			// sec = expiration_date.getTime();
			// mPreparedStatement.setDate(4, new java.sql.Date(sec));
			mPreparedStatement.setString(4, sdf.format(expiration_date));
//			System.out.println("inserisco insertion con date exp " + sdf.format(expiration_date));
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(mConnection);
		}
		return -1;
	}

	public List<Insertion> getInsertionByName(final String name, final String category) {

		List<Insertion> insertions = new LinkedList<Insertion>();
		Insertion insertion = null;
		String query = null;
		Boolean ok = false;
		if (category.equals("Tutte le categorie")) {
			query = "select * from insertion where processed=0;";
		} else {
			query = "select * from insertion where category=? and processed=0;";
			ok = true;
		}
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			if (ok) {
				mPreparedStatement.setString(1, category);
			}
			final ResultSet mResultSet = mPreparedStatement.executeQuery();
			while (mResultSet.next()) {
				if (!Pattern.compile(Pattern.quote(name), Pattern.CASE_INSENSITIVE).matcher(mResultSet.getString("name")).find()
						|| (isExpired(new Date(mResultSet.getTimestamp("expiration_date").getTime()))
								&& Sales_type.valueOf(mResultSet.getString("sales_type")) == Sales_type.asta))
					continue;
				insertion = new Insertion(Integer.parseInt(mResultSet.getString("id_user")),
						Integer.parseInt(mResultSet.getString("id_item")), mResultSet.getString("name"),
						new Date(mResultSet.getTimestamp("insertion_date").getTime()),
						new Date(mResultSet.getTimestamp("expiration_date").getTime()),
						Integer.parseInt(mResultSet.getString("amount")),
						Sales_type.valueOf(mResultSet.getString("sales_type")),
						Float.parseFloat(mResultSet.getString("price")), mResultSet.getString("description"),
						mResultSet.getString("category"));

//				System.out.println("total insertion date exp " + mResultSet.getTimestamp("expiration_date").getTime());
				insertions.add(insertion);
			}
			closeConnection(mConnection);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return insertions;

	}

	private boolean isExpired(Date date) {
		return date.before(new Date());
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
						new Date(mResultSet.getTimestamp("insertion_date").getTime()),
						new Date(mResultSet.getTimestamp("expiration_date").getTime()),
						Integer.parseInt(mResultSet.getString("amount")),
						Sales_type.valueOf(mResultSet.getString("sales_type")),
						Float.parseFloat(mResultSet.getString("price")), mResultSet.getString("description"),
						mResultSet.getString("category"));
//				System.out.println("select insertion date exp " + insertion.getExpiration_date().toInstant());
			}
			closeConnection(mConnection);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return insertion;

	}

	public List<Insertion> getAuctionNotProcessed() {

		List<Insertion> insertions = new LinkedList<Insertion>();
		Insertion insertion = null;
		String query = null;
		query = "select * from insertion where sales_type='asta' and processed=0;";
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			final ResultSet mResultSet = mPreparedStatement.executeQuery();
			while (mResultSet.next()) {
				if (!isExpired(mResultSet.getTimestamp("expiration_date")))
					continue;
				insertion = new Insertion(mResultSet.getInt("id_user"),
						mResultSet.getInt("id_item"), mResultSet.getString("name"),
						new Date(mResultSet.getTimestamp("insertion_date").getTime()),
						new Date(mResultSet.getTimestamp("expiration_date").getTime()),
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

	public void updateProcessed(int id_item) {
		final String query = "UPDATE insertion SET processed=1 WHERE id_item=?;";
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setInt(1, id_item);
			mPreparedStatement.executeUpdate();
			closeConnection(mConnection);
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public List<Insertion> getLastInsertion() {
			
		String query="select * from insertion where sales_type='compraora' order by id_item desc limit 4 ;";		
		List<Insertion> insertions = new LinkedList<Insertion>();
		Insertion insertion = null;
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			final ResultSet mResultSet = mPreparedStatement.executeQuery();
			while (mResultSet.next()) {
//				if (Sales_type.valueOf(mResultSet.getString("sales_type"))==Sales_type.asta&&isExpired(mResultSet.getTimestamp("expiration_date")))
//					continue;
				insertion = new Insertion(mResultSet.getInt("id_user"),
						Integer.parseInt(mResultSet.getString("id_item")), mResultSet.getString("name"),
						new Date(mResultSet.getTimestamp("insertion_date").getTime()),
						new Date(mResultSet.getTimestamp("expiration_date").getTime()),
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

}
