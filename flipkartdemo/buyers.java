package flipkartdemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class buyers {
	String Uname;
    int id;
    String prname;
    int count;
    Date date;
    int cost=0;

    buyers() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of User: ");
        Uname = sc.next();
        System.out.println("Enter product Name ");
        prname = sc.next();
        System.out.println("Enter Number of item order: ");
        count = sc.nextInt();
        System.out.println("Enter date dd-MM-yyyy");
        String dateip = sc.next();
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = dt.parse(dateip);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public boolean isAvailable(ArrayList<buyers> buyer, ArrayList<products> product) {
        
        
        for (products produc : product) {
            
            if (prname.equals(produc.getpname())) {
                int capacity = produc.getcount();
                cost = produc.getprice();

                System.out.println("Cost: " + cost*count);

                return count <= capacity; 
            }
        }


        return false;
    

    }
 

    public String getUname() {
        return Uname;
    }

    public String getPrname() {
        return prname;
    }

    public int getCount() {
        return count;
    }

    public Date getDate() {
        return date;
    }

    public int getCost() {
        return cost;
    }

}
