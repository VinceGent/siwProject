package elements;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {

	private static final long serialVersionUID = -2709426825987549837L;

	public Order(Date orderdate, OrderState state, int id_insertion, int id_user, int id_order) {
		this.orderdate = orderdate;
		this.state = state;
		this.id_insertion = id_insertion;
		this.id_user = id_user;
		this.id_order = id_order;
	}

	public Order(Date orderdate, OrderState state, int id_insertion, int id_user) {
		this.orderdate = orderdate;
		this.state = state;
		this.id_insertion = id_insertion;
		this.id_user = id_user;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
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

	public int getId_order() {
		return id_order;
	}

	public void setId_order(int id_order) {
		this.id_order = id_order;
	}

	private Date orderdate;
	private OrderState state;
	private int id_insertion;
	private int id_user;
	private int id_order;
}
