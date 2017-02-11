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

	public int getIdByUsername(String username) {
		int id = -1;
		final String query = "select id from users where users.username=?;";
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setString(1, username);
			final ResultSet mResultSet = mPreparedStatement.executeQuery();
			while (mResultSet.next()) {
				id = Integer.parseInt(mResultSet.getString("id"));
			}
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
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
			final int telephone, String city, String province, int postal_code, String country) {
		if (getUserByUsername(username) != null) {
			User user = getUserByUsername(username);
			String query = "INSERT INTO user_info (id_user, name, surname, address, telephone,city,province,postal_code,country) VALUES (?,?,?,?,?,?,?,?,?);";

			try {
				final Connection mConnection = createConnection();
				final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
				mPreparedStatement.setString(1, Integer.toString(user.getId()));
				mPreparedStatement.setString(2, name);
				mPreparedStatement.setString(3, surname);
				mPreparedStatement.setString(4, address);
				mPreparedStatement.setInt(5, telephone);
				mPreparedStatement.setString(6, city);
				mPreparedStatement.setString(7, province);
				mPreparedStatement.setInt(8, postal_code);
				mPreparedStatement.setString(9, country);
				mPreparedStatement.execute();
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public UserInformation getUserInfo(String username) {

		UserInformation userinfo = null;
		User u = getUserByUsername(username);
		if (u != null) {
			final String query = "select * from user_info where user_info.id_user = ?;";
			try {
				final Connection mConnection = createConnection();
				final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
				mPreparedStatement.setString(1, Integer.toString(u.getId()));
				final ResultSet mResultSet = mPreparedStatement.executeQuery();
				while (mResultSet.next()) {
					userinfo = new UserInformation(u.getId(), mResultSet.getString("name"),
							mResultSet.getString("surname"), mResultSet.getString("address"),
							mResultSet.getString("telephone"), mResultSet.getString("city"),
							mResultSet.getString("province"), Integer.parseInt(mResultSet.getString("postal_code")),
							mResultSet.getString("country"));
				}

				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();

			}
		} else
			System.out.println("username non presente nel db");
		return userinfo;
	}

	public void modifyUser(int id, String username, String email, String name, String surname,String address,int telephone,String city, String province,int postal_code,String country) {

		final String query = "UPDATE users SET username=?, email=? WHERE id=?;";
		final String query2 = "UPDATE user_info SET name=?, surname=?, address=?, telephone=?, city=?, province=?, postal_code=?, country=? WHERE id_user=?";
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setString(1, username);
			mPreparedStatement.setString(2, email);
			mPreparedStatement.setInt(3, id);
			mPreparedStatement.executeUpdate();
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query2);
			mPreparedStatement.setString(1, name);
			mPreparedStatement.setString(2, surname);
			mPreparedStatement.setString(3, address);
			mPreparedStatement.setInt(4, telephone);
			mPreparedStatement.setString(5, city);
			mPreparedStatement.setString(6, province);
			mPreparedStatement.setInt(7, postal_code);
			mPreparedStatement.setString(8, country);
			mPreparedStatement.setInt(9, id);
			mPreparedStatement.executeUpdate();
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public void setPassword(int id_user, String password) {
		final String query = "UPDATE users SET password=? WHERE id=?;";
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setString(1, password);
			mPreparedStatement.setInt(2, id_user);
			mPreparedStatement.executeUpdate();
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	 public static void main(String[] args) {
	 UserDAO db = new UserDAO();
	// db.addUser("ciccio", "cicc@hot.it", "porco");
	// db.addUserInfo("ciccio", "francesco", "rossi", "via della pace 17",
	// "333123465");
	// UserInformation info = db.getUserInfo("ciccio");
	// System.out.println(info.getName() + " " + info.getId());
	 db.addUserInfo("ciccio", "porco", "riolo"," porcile",666, "porcellinara", "crotone", 87000, "calabbbbria");
	 }
}
