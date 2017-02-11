package elements;

public class UserInformation {
	private int id;
	private String name, surname, address, telephone;
	private String city;
	private String province;
	private int postal_code;
	private String country;

	public UserInformation(int i, String n, String s, String a, String t, String city, String province, int postal_code,
			String country) {
		id = i;
		name = n;
		surname = s;
		address = a;
		telephone = t;
		this.city = city;
		this.province = province;
		this.postal_code = postal_code;
		this.country = country;
	}

	

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String proince) {
		this.province = proince;
	}

	public int getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(int postal_code) {
		this.postal_code = postal_code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public UserInformation() {
		telephone = address = surname = name = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
