package flipkartdemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;



public class sellers {
	
	String sellerName;
    int sellerId;
    String productName;
    int quantity;
    Date date;
    int productPrice;
	
	sellers(){
        Scanner sc = new Scanner(System.in);
		System.out.println("Enter seller ID:");
        sellerId = sc.nextInt();
        System.out.println("Enter seller name:");
        sellerName = sc.next();
        System.out.println("Enter product name:");
        productName = sc.next();
        System.out.println("Enter product quantity:");
        quantity = sc.nextInt();
        System.out.println("Enter product price:");
        productPrice = sc.nextInt();
        System.out.println("Enter date dd-MM-yyyy");
        String dateip = sc.next();
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = dt.parse(dateip);
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}


    public String getsellerName() {
        return sellerName;
    }

    public boolean sellProduct(ArrayList<products> productList, products newProduct) {
        for (products product : productList) {
            if (product.getid() == newProduct.getid()) {
                System.out.println("Product with the same ID already exists. Unable to add the product.");
                return false;
            }
        }

        productList.add(newProduct);
        System.out.println("Product added successfully.");
        return true;
    }
    
    public String getproductName() {
        return productName;
    }
    
    public int getquantity() {
        return quantity;
    }
    
    public Date getdate() {
        return date;
    }
    
    public int getproductPrice() {
        return productPrice;
    }
    public int getsellerId() {
        return sellerId;
    }

	
}
