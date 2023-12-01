package flipkartdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class flipkatdemo1 {

	public static void main(String[] args) {
		ArrayList<products> product= new ArrayList <products>();
		ArrayList<buyers> buyer = new ArrayList<buyers>();
		ArrayList<sellers> seller = new ArrayList<sellers>();
		int useropt =1;
		
		
		
		
		Scanner sc = new Scanner(System.in);
		while(useropt == 1) {
			System.out.println("Enter 1 to order and 2 to sell and 3 to saw the products and 4 to exit");
			useropt = sc.nextInt();
			
			
			String query = "select * from productDetails";
    		String url = "jdbc:mysql://localhost:3305/siva";
    		String user = "root";
    		String password = "i love you amma";
    		Connection chinna = null;
    		try {
    			chinna = DriverManager.getConnection(url,user,password);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		if (chinna != null)
    			System.out.println("connected to Data Base");
    		else
    			System.out.println("Not Connected");
    		
    		Statement st;
    		try {
    			st = chinna.createStatement();
    			ResultSet rs = st.executeQuery(query);
    			while(rs.next()) {
    				product.add(new products(rs.getInt("productId"),rs.getInt("productCount"),rs.getString("productName"),rs.getInt("productCost")));
    				
    			}
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
			
			
    		if (useropt == 1) {
    		    buyers newBuyer = new buyers(); 
    		    if (newBuyer.isAvailable(buyer, product)) {
    		        buyer.add(newBuyer);

    		        String buyerName = newBuyer.getUname();
    		        String buyerProductName = newBuyer.getPrname();
    		        int buyerCount = newBuyer.getCount();
    		        Date buyerDate = newBuyer.getDate();
    		        int buyerCost = newBuyer.getCost();
    		        int totalCost = buyerCost * buyerCount;
    		        try {
    		            Class.forName("com.mysql.cj.jdbc.Driver");
    		        } catch (ClassNotFoundException e) {
    		            e.printStackTrace();
    		        }
    		        Connection krish = null;
    		        try {
    		            krish = DriverManager.getConnection("jdbc:mysql://localhost:3305/siva", "root", "i love you amma");
    		        } catch (SQLException e) {
    		            e.printStackTrace();
    		        }

    		        String updateProductCountQuery = "UPDATE productDetails SET productCount = productCount - ? WHERE productName = ?";
    		        try (PreparedStatement updateProductCountStatement = krish.prepareStatement(updateProductCountQuery)) {
    		            updateProductCountStatement.setInt(1, buyerCount);
    		            updateProductCountStatement.setString(2, buyerProductName);
    		            updateProductCountStatement.executeUpdate();
    		        } catch (SQLException e) {
    		            e.printStackTrace();
    		        }

    		        String query1 = "INSERT INTO buyerDetails(buyerName, buyerProductName, buyerCount, buyercost, buyerDate, totalCost) VALUES (?, ?, ?, ?, ?, ?)";

    		        try (PreparedStatement preparedStatement = krish.prepareStatement(query1)) {
    		            preparedStatement.setString(1, buyerName);
    		            preparedStatement.setString(2, buyerProductName);
    		            preparedStatement.setInt(3, buyerCount);
    		            preparedStatement.setInt(4, buyerCost);
    		            preparedStatement.setDate(5, new java.sql.Date(buyerDate.getTime()));
    		            preparedStatement.setInt(6, totalCost);

    		            preparedStatement.executeUpdate();

    		        } catch (SQLException e) {
    		            e.printStackTrace();
    		        }

    		        System.out.println("Your order is successful");
    		        System.out.println("Thank You");
    		    } else {
    		        System.out.println("Product not available");
    		    }
    		}

			else if (useropt == 2) {
                int id = 1;
                sellers newSeller = new sellers();
                int sellerId = newSeller.getsellerId();
                String sellerName = newSeller.getsellerName();
                String sellerProductName = newSeller.getproductName();
                int sellerCount = newSeller.getquantity();
                Date sellerDate = newSeller.getdate();
                int sellerCost = newSeller.getproductPrice();
                int totalCost = sellerCost * sellerCount;

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Connection siva = null;
                try {
                    siva = DriverManager.getConnection("jdbc:mysql://localhost:3305/siva", "root", "i love you amma");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String checkProductQuery = "SELECT * FROM productDetails WHERE productName = ?";
                try (PreparedStatement checkProductStatement = siva.prepareStatement(checkProductQuery)) {
                    checkProductStatement.setString(1, sellerProductName);
                    try (ResultSet resultSet = checkProductStatement.executeQuery()) {
                        if (resultSet.next()) {
                            int existingProductCount = resultSet.getInt("productCount");
                            int newProductCount = existingProductCount + sellerCount;

                            String updateProductQuery = "UPDATE productDetails SET productCount = ? WHERE productName = ?";
                            try (PreparedStatement updateProductStatement = siva.prepareStatement(updateProductQuery)) {
                                updateProductStatement.setInt(1, newProductCount);
                                updateProductStatement.setString(2, sellerProductName);
                                updateProductStatement.executeUpdate();
                            }
                        } else {
                            String insertProductQuery = "INSERT INTO productDetails (productName, productCount, productCost) VALUES (?, ?, ?)";
                            try (PreparedStatement insertProductStatement = siva
                                    .prepareStatement(insertProductQuery)) {
                                insertProductStatement.setString(1, sellerProductName);
                                insertProductStatement.setInt(2, sellerCount);
                                insertProductStatement.setInt(3, sellerCost);
                                insertProductStatement.executeUpdate();
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String query2 = "INSERT INTO sellerDetails (sellerId, sellerName, sellerProductName, sellerCount, sellerCost, sellerDate, totalCost) VALUES (?, ?, ?, ?, ?, ?, ?)";
                String query3 = "INSERT INTO productDetails (productName, productCount, productCost) VALUES (?, ?, ?)";

                try (PreparedStatement preparedStatement = siva.prepareStatement(query2)) {
                    preparedStatement.setInt(1, sellerId);
                    preparedStatement.setString(2, sellerName);
                    preparedStatement.setString(3, sellerProductName);
                    preparedStatement.setInt(4, sellerCount);
                    preparedStatement.setInt(5, sellerCost);
                    preparedStatement.setDate(6, new java.sql.Date(sellerDate.getTime()));
                    preparedStatement.setInt(7, totalCost);

                    preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("totalCost :" + sellerCost * sellerCount);
                try (PreparedStatement preparedStatement = siva.prepareStatement(query3)) {
                    preparedStatement.setString(1, sellerProductName);
                    preparedStatement.setInt(2, sellerCount);
                    preparedStatement.setInt(3, sellerCost);

                    preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                useropt = 1;
            }
			else if (useropt == 3) {
				try {
		            Class.forName("com.mysql.cj.jdbc.Driver");

		            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/siva","root","i love you amma")) {
		                String selectProductsQuery = "SELECT * FROM productDetails";

		                try (PreparedStatement preparedStatement = connection.prepareStatement(selectProductsQuery)) {
		                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		                        while (resultSet.next()) {
		                            products produc = products.createProductFromResultSet(resultSet);
		                            product.add(produc);
		                        }
		                    }
		                }
		            }

		            for (products produc : product) {
		                System.out.println(produc.getid() + " - " + produc.getpname() + " - " + produc.getprice());
		            }

		        } catch (ClassNotFoundException | SQLException e) {
		            e.printStackTrace();
		        }
		    }
			else if(useropt==4) {
				useropt=2;
				System.out.println("Thank You");
				System.out.println(("Have a nice day"));
			}
			
		}
			
			
		}
	

}