package elements;

import java.io.Serializable;

public class AuctionOffer implements Serializable {


	private static final long serialVersionUID = -2719683914157292501L;
	public AuctionOffer(int id_user, int id_item, float offer) {
		this.id_user = id_user;
		this.id_item = id_item;
		this.offer = offer;
	}

	public AuctionOffer(int id_user, int id_item, float offer, int id_offer) {
		this.id_user = id_user;
		this.id_item = id_item;
		this.offer = offer;
		this.id_offer = id_offer;
	}

	public float getOffer() {
		return offer;
	}

	public void setOffer(float offer) {
		this.offer = offer;
	}

	public int getId_item() {
		return id_item;
	}

	public void setId_item(int id_item) {
		this.id_item = id_item;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	private float offer;
	private int id_item;
	private int id_user;
	private int id_offer;
}
