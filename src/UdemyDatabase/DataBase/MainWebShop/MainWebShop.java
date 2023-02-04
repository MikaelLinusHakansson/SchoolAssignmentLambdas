package UdemyDatabase.DataBase.MainWebShop;

import Database.RepDemo.Main;
import UdemyDatabase.DataBase.Repository.RepositoryWebShop;

public class MainWebShop {
    private static final RepositoryWebShop webbShop = new RepositoryWebShop();
    private static final utilityClass utility = new utilityClass();

    public static void main(String[] args) {
        initialize();
    }

    public static void initialize() {
        while (webbShop.login()) {
            while (true) {
                utility.printChoices();
                protocol();
            }
        }
    }

    public static void protocol(){
        switch (utility.scannerString()) {
            case "1" -> webbShop.printCustomers();
            case "2" -> webbShop.printShoeView();
            case "3" -> webbShop.makeOrder();
            case "4" -> webbShop.printNumberOfOrdersPerCustomer();
            case "5" -> webbShop.printTotalCustomerSpent(webbShop.calculateAmountOfMoneySpentPerCustomer());
            case "6" -> webbShop.printFilter();

            case "0" -> {
                System.out.println("you logged out!");
                webbShop.login();
            }
            case "quit" -> {
                System.out.println("Bye!");
                System.exit(0);
            }
            default -> System.out.println("Unknown command");
        }
    }
}
