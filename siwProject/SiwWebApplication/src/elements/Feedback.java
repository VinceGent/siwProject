package elements;

import java.io.Serializable;

public class Feedback implements Serializable {

	private static final long serialVersionUID = 8412457411365321480L;
	private int id_feedback;
	private int id_user;
	private int id_insertion;
	private String description;
	private String username;
	private int rating;
	private int avg;

	public Feedback(int avg) {
		this.avg = avg;

	}

	public Feedback(int id_feedback, int id_user, String username, int id_insertion, String description, int rating) {
		this.setRating(rating);
		this.setUsername(username);
		this.setId_feedback(id_feedback);
		this.setId_user(id_user);
		this.setId_insertion(id_insertion);
		this.setDescription(description);

	}

	public Feedback(int id_user, String username, int id_insertion, String description, int rating) {
		this.setRating(rating);
		this.setUsername(username);
		this.setId_user(id_user);
		this.setId_insertion(id_insertion);
		this.setDescription(description);

	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId_insertion() {
		return id_insertion;
	}

	public void setId_insertion(int id_insertion) {
		this.id_insertion = id_insertion;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getId_feedback() {
		return id_feedback;
	}

	public void setId_feedback(int id_feedback) {
		this.id_feedback = id_feedback;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getAvg() {
		return avg;
	}

	public void setAvg(int avg) {
		this.avg = avg;
	}

}
