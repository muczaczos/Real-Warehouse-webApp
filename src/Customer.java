import java.io.Serializable;

public class Customer implements Serializable {
	

	/////////////////////////////////////////////////////////////////////
	/////////////////////////////// Main variables///////////////////////
	/////////////////////////////////////////////////////////////////////
	private static int count = 0;

	private int id;
	private String name;
	private String address;
	private String telephone;

	/////////////////////////////////////////////////////////////////////
	//////////////////////////// Constructors ///////////////////////////
	////////////////////////////////////////////////////////////////////
	public Customer(int id, String name, String address, String telephone) {

		this.id = id;
		this.name = name;
		this.address = address;
		this.telephone = telephone;

	}
	
	public Customer(String name, String address, String telephone) {

		this.name = name;
		this.address = address;
		this.telephone = telephone;

	}

	/////////////////////////////////////////////////////////////////////
	/////////////////////// getters & setters //////////////////////////
	////////////////////////////////////////////////////////////////////
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
