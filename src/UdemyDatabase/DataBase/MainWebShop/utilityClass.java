package UdemyDatabase.DataBase.MainWebShop;

import java.util.Scanner;

public class utilityClass {
    public void printChoices(){
        System.out.println("""
                        
                        1 to print customers
                        2 to print shoes in stock
                        3 to place order
                        4 to print sum of orders from customers
                        5 to print total amount spent per customer
                        6 to search for brand, colour or size
                        0 to logout
                        'quit' to quit""");
    }

    public String scannerString(){
        return new Scanner(System.in).nextLine().trim();
    }

    public int scannerInt(){
        return new Scanner(System.in).nextInt();
    }
}
