package dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
//				e.printStackTrace();
				
				if(e.getMessage().contains("username")){
					System.out.println(e.getMessage());
				}
			}
		}
	}

	public void addUserInfo(final String username, final String name, final String surname, final String address,
			final String telephone) {
		if (this.getUserByUsername(username) != null) {
			User user = getUserByUsername(username);
			String query = "INSERT INTO user_info (id_user, nome, cognome, indirizzo, telefono) VALUES (?,?,?,?,?);";
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

	public UserInformation getUserInfo(String username) {
		UserInformation userinfo = null;
		User u = getUserByUsername(username);
		if (u != null) {
			final String query = "select user_info.nome,user_info.cognome,user_info.indirizzo,user_info.telefono from user_info where user_info.id_user = ?;";
			try {
				final Connection mConnection = createConnection();
				final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
				mPreparedStatement.setString(1, Integer.toString(u.getId()));
				final ResultSet mResultSet = mPreparedStatement.executeQuery();
				while (mResultSet.next()) {
					userinfo = new UserInformation(u.getId(), mResultSet.getString("nome"), mResultSet.getString("cognome"), mResultSet.getString("indirizzo"), mResultSet.getString("telefono"));
				}
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}else
			System.out.println("username non presente nel db");
		return userinfo;
	}

	public static void main(String[] args) {
//		UserDAO db = new UserDAO();
//
//		db.addUser("vincenzo", "cicc@hot.it", "porco");

//		db.addUserInfo("ciccio", "francesco", "rossi", "via della pace 17", "333123465");
//		UserInformation info = db.getUserInfo("ciccio");

	}
}
