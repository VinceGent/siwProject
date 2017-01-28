package dbconnection;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

import elements.Insertion;
import elements.Sales_type;
import elements.User;
import elements.UserInformation;

public class UserDAO extends DbManager {

	public UserDAO() {
		super();
	}

	public User getUserByEmail(String email) {
		User user = null;
		final String query = "select * from users where users.email = ?;";
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setString(1, email);
			final ResultSet mResultSet = mPreparedStatement.executeQuery();
			while (mResultSet.next()) {
				user = new User(Integer.parseInt(mResultSet.getString("id")), mResultSet.getString("username"),
						mResultSet.getString("email"), mResultSet.getString("password"));
			}
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public User getUserByUsername(String username) {
		User user = null;
		final String query = "select * from users where users.username = ?;";
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setString(1, username);
			final ResultSet mResultSet = mPreparedStatement.executeQuery();
			while (mResultSet.next()) {
				user = new User(Integer.parseInt(mResultSet.getString("id")), mResultSet.getString("username"),
						mResultSet.getString("email"), mResultSet.getString("password"));
			}
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	/* insert user in database */
	public void addUser(final String username, final String email, final String password) {
		if (this.getUserByEmail(email) == null) {
			String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?);";
			try {
				final Connection mConnection = createConnection();
				final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
				mPreparedStatement.setString(1, username);
				mPreparedStatement.setString(2, email);
				mPreparedStatement.setString(3, password);
				mPreparedStatement.execute();
				closeConnection();
			} catch (SQLException e) {
				// e.printStackTrace();

				if (e.getMessage().contains("username")) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	public void addUserInfo(final String username, final String name, final String surname, final String address,
			final String telephone) {
		if (this.getUserByUsername(username) != null) {
			User user = getUserByUsername(username);
			String query = "INSERT INTO user_info (id_user, name, surname, address, telephone) VALUES (?,?,?,?,?);";
			try {
				final Connection mConnection = createConnection();
				final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
				mPreparedStatement.setString(1, Integer.toString(user.getId()));
				mPreparedStatement.setString(2, name);
				mPreparedStatement.setString(3, surname);
				mPreparedStatement.setString(4, address);
				mPreparedStatement.setString(5, telephone);
				mPreparedStatement.execute();
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void addNewInsertion(final int id_user, final String name, final Date insertion_date,
			final Date expiration_date, final int amount, final Sales_type sales_type, final float price,
			final String description) {
		Insertion insertion = new Insertion(id_user, name, insertion_date, expiration_date, amount, sales_type, price,
				description);

		String query = "INSERT INTO inserzione (id_user, name, insertion_date, expiration_date, amount, sales_type, price, description) VALUES (?,?,?,?,?,?,?,?);";
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

	public Insertion getInsertionById(final int id_user) {

		Insertion insertion = null;

		final String query = "select * from insertion where id_user= ?;";
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setString(1, Integer.toString(id_user));
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

	public UserInformation getUserInfo(String username) {

		UserInformation userinfo = null;
		User u = getUserByUsername(username);
		if (u != null) {
			final String query = "select user_info.name,user_info.surname,user_info.address,user_info.telephone from user_info where user_info.id_user = ?;";
			try {
				final Connection mConnection = createConnection();
				final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
				mPreparedStatement.setString(1, Integer.toString(u.getId()));
				final ResultSet mResultSet = mPreparedStatement.executeQuery();
				while (mResultSet.next()) {
					userinfo = new UserInformation(u.getId(), mResultSet.getString("name"),
							mResultSet.getString("surname"), mResultSet.getString("address"),
							mResultSet.getString("telephone"));
				}
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();

			}
		} else
			System.out.println("username non presente nel db");
		return userinfo;
	}

	public static void main(String[] args) {
		UserDAO db = new UserDAO();
		 db.addUser("ciccio", "cicc@hot.it", "porco");
		 db.addUserInfo("ciccio", "francesco", "rossi", "via della pace 17",
		 "333123465");
		UserInformation info = db.getUserInfo("ciccio");
		System.out.println(info.getName() + "   " + info.getId());
		
	}
}
