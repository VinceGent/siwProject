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
		int id =-1;
		final String query = "select id from users where users.username=?;";
		try {
			final Connection mConnection = createConnection();
			final PreparedStatement mPreparedStatement = mConnection.prepareStatement(query);
			mPreparedStatement.setString(1, username);
			final ResultSet mResultSet = mPreparedStatement.executeQuery();
			while (mResultSet.next()) {
				id= Integer.parseInt(mResultSet.getString("id"));
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

	
	public void modifyUser(int id, String username, String email, String name, String surname){
	
		final String query = "UPDATE users SET username=?, email=? WHERE id=?;";
		final String query2 = "UPDATE user_info SET name=?, surname=? WHERE id_user=?";
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
			mPreparedStatement.setInt(3, id);
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
	
//	public static void main(String[] args) {
//		UserDAO db = new UserDAO();
////		 db.addUser("ciccio", "cicc@hot.it", "porco");
////		 db.addUserInfo("ciccio", "francesco", "rossi", "via della pace 17",
////		 "333123465");
////		UserInformation info = db.getUserInfo("ciccio");
////		System.out.println(info.getName() + "   " + info.getId());
//	db.addNewInsertion(1, "compueter", new Date(), new Date(2017, 3, 2), 30, Sales_type.COMPRAORA, 20, "sta ceppa sjsja kakka kdjlaksdjlakjsdlk lakjdl sld    kjkalksda ask sdkasdkasd adk akasdklks l laks dlfkaslf lskasdfkaiasld als   km lk sks ,s ls m ksl kmlksm lkmslcsòldm òmsm lsk m!!!!");
//System.out.println(db.getIdByUsername("ciccio"));		
//		
//	}
}
