package elements;

import java.util.Date;

public class Insertion {
	private String name;
	private Date insertion_date;
	private Date expiration_date;
	private int amount;
	private Sales_type sales_type;
	private float price;
	private String description;
	private int id_user;
	private int id_item;

	public Insertion(int id_user, int id_item, String name, Date insertion_date, Date expiration_date, int amount,
			Sales_type sales_type, float price, String description) {
		setId_item(id_item);
		setId_user(id_user);
		setName(name);
		setInsertion_date(insertion_date);
		setExpiration_date(expiration_date);
		setAmount(amount);
		setSales_type(sales_type);
		setPrice(price);
		setDescription(description);
	}

	public Insertion(int id_user, String name, Date insertion_date, Date expiration_date, int amount,
			Sales_type sales_type, float price, String description) {
		setId_user(id_user);
		setName(name);
		setInsertion_date(insertion_date);
		setExpiration_date(expiration_date);
		setAmount(amount);
		setSales_type(sales_type);
		setPrice(price);
		setDescription(description);
	}

	public Insertion() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getInsertion_date() {
		return insertion_date;
	}

	public void setInsertion_date(Date insertion_date) {
		this.insertion_date = insertion_date;
	}

	public Date getExpiration_date() {
		return expiration_date;
	}

	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Sales_type getSales_type() {
		return sales_type;
	}

	public void setSales_type(Sales_type sales_type) {
		this.sales_type = sales_type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
}
