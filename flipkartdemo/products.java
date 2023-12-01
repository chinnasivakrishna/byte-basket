package flipkartdemo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class products {
	private int id;
	private int count;
	private String pname;
	private int price;
	
	products(int id, int count, String pname, int price){
		this.id = id;
		this.count = count;
		this.pname = pname;
		this.price = price;
	}
	public int getid() {
		return id;
	}
	public int getcount() {
		return count;
	}
	public String getpname() {
		return pname;
	}
	public int getprice() {
		return price;
	}
	public void setcount(int val) {
		count=val;
	}
	public void setprice(int val) {
		price = val;
	}
	public static products createProductFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("productId");
        int count = resultSet.getInt("productCount");
        String pname = resultSet.getString("productName");
        int price = resultSet.getInt("productCost");

        return new products(id, count, pname, price);
    }
	
}
