package UdemyDatabase.DataBase.Repository;

import UdemyDatabase.DataBase.MainWebShop.MainWebShop;
import UdemyDatabase.DataBase.MainWebShop.utilityClass;
import UdemyDatabase.DataBase.Repository.Enums.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class RepositoryWebShop {
    private static final Properties p = new Properties();
    private static final utilityClass utility = new utilityClass();
    private static int customerId;

    public RepositoryWebShop() {
        try {
            p.load(new FileInputStream("propdemo.properties"));
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public boolean login() {
        while (true) {
            // en loop som ber personen att logga in
            /* TODO LOGIN 2015987738 222*/  // användarnamn och lösenord för kund #2 Nicole Kidman
            System.out.println("Enter SSA as username & your password:\n'quit' to quit");
            String userName = utility.scannerString();
            if ("quit".equalsIgnoreCase(userName)) {
                System.exit(0);
            }

            String password = utility.scannerString();  // TODO: GLÖM INTE ATT TA BORT HÅRD KOD
            if (checkUserNameAndPassword(userName, password)) {
                System.out.println("Login successful\nWelcome:" + queryCustomers().
                        stream().
                        filter(customersNot -> customersNot.getSSA().equals(userName)).
                        map(CustomersNot::getName).
                        toList());
                customerId = extractCustomerId(queryCustomers(), userName).get(0).getId();
                return true;
            } else {
                System.out.println("Incorrect password or username.");
            }
        }
    }

    private List<CustomersNot> extractCustomerId(List<CustomersNot> listOfCustomers, String SSA) {
        // Hämtar kundens id och lösenord för inloggning.
        return listOfCustomers.stream().filter(customersNot -> customersNot.getSSA().equals(SSA)).toList();
    }

    private boolean checkUserNameAndPassword(String userName, String password) {
        // Kollar så det är rätt lösenord för rätt användarnamn
        return queryCustomers().
                stream().
                map(customersNot -> customersNot.getSSA() + customersNot.getPassword()).
                anyMatch(s -> s.equals(userName + password));
    }


    public void printShoeView() {
        // printar ut alla skor vi har med lager ett lager saldo > 0
        shoeView().stream().filter(shoeView -> shoeView.getStock() > 0).forEach(shoeView -> System.out.println(
                shoeView.getNumberChoice() + " " + "Name: " + shoeView.getNameOfShoe()
                        + " Brand: " + shoeView.getNameOfBrand()
                        + " Colour: " + shoeView.getNameOfColour()
                        + " Size: " + shoeView.getSize()
                        + " Price: " + shoeView.getPrice() + " kr"));
    }

    // Metod för att lägga till i en order eller göra en ny
    public void makeOrder() {
        printShoeView();
        System.out.println("Pick a shoe to add to cart");
        try {
            int pickProduct = utility.scannerInt();
            List<Shoes> listOfShoes = insertExtractedShoe(pickProduct).stream().toList();

            System.out.println("New order? y/n");
            String yesOrNo = utility.scannerString();  // else if macka för felhantering
            if (yesOrNo.equalsIgnoreCase("n")) {
                System.out.println("Order id: ");
                try {
                    int orderId = utility.scannerInt();
                    if (checkMatchingOrderItemShoe(pickProduct, orderId)) {
                        addToCart(orderId, listOfShoes);
                    } else if (checkIfShoeExists(pickProduct)) {
                        System.out.println("Could not find ID but found SHOE. Making new order");
                        addToCart(-1, listOfShoes);
                    } else {
                        System.out.println("Invalid shoe or Id match.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input for order");
                }
            } else if (yesOrNo.equalsIgnoreCase("y")) {
                addToCart(-1, listOfShoes);
            } else {
                System.out.println("Invalid input");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input for shoe");
            // Om du skriver fel när du väljer produkt T.ex. Skriver in en string istället för en siffra
        }
    }

    private List<Shoes> insertExtractedShoe(int pickProduct) {
        // Returnerar en matchade produkt
        return queryShoes().
                stream().
                filter(shoes -> shoes.getName().equals(extractNameOfShoe(pickProduct).get(0))).
                toList();
    }

    public boolean checkMatchingOrderItemShoe(int theProductNumber, int orderId) {
        // Hjälp för felhantering. Kontrollerar ihop sammanhängande produkt och order

        List<OrderItem> listOfOrderItems = queryOrderItem().stream().toList();
        for (OrderItem listOfOrderItem : listOfOrderItems) {
            if (listOfOrderItem.getShoesId() == theProductNumber && listOfOrderItem.getOrderId() == orderId) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfShoeExists(int product) {
        // Returnerar true om produkten matchar med input från användare
        for (ShoeView shoeView : shoeView()) {
            if (shoeView.getNumberChoice() == product) {
                return true;
            }
        }
        return false;
    }

    private List<String> extractNameOfShoe(int pickProduct) {
        // Returnerar en ett namn för en sko som matchar
        return shoeView().
                stream().
                filter(shoeView -> shoeView.getNumberChoice() == pickProduct).
                map(ShoeView::getNameOfShoe).
                toList();
    }

    private void addToCart(int orderId, List<Shoes> theShoe) {  // todo ADD TO CART
        // AddToCart procedure
        List<OrderItem> OrderItem = queryOrderItem().stream().toList();
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionStringThree"),
                p.getProperty("name"),
                p.getProperty("password"));
             CallableStatement stmt = con.prepareCall("call AddToCart3(?, ?, ?, ?)")
        ) {
            stmt.setInt(1, orderId);
            stmt.setInt(2, theShoe.get(0).getId());
            stmt.setInt(3, customerId);
            stmt.registerOutParameter(4, Types.VARCHAR);
            stmt.executeQuery();
            String databaseRecall = stmt.getString(4);
            System.out.println(databaseRecall);  // skriver ut feedbacken från SQL- servern
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    private List<ShoeView> shoeView() {
        // Representerar en "view" i SQL fast i java.
        // Vi hämtar bara den informationen vi vill komma åt och skriva ut från sql
        // Finns ingen faktiskt view i sql som representerar detta.
        // Vi hämtar allt från original källorna T.ex. Customers
        List<ShoeView> shoeView = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionStringThree"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select " +
                     "Shoes.NameOfShoe, " +
                     "Brand.NameOfBrand, " +
                     "Colours.NameOfColour," +
                     "Sizes.Size," +
                     "Shoes.Price," +
                     "Shoes.Stock, " +
                     "Shoes.brandId, " +
                     "Shoes.SizeId," +
                     "Shoes.ColourId" +
                     " from Shoes" +
                     " inner join Brand on Shoes.brandId = Brand.Id" +
                     " inner join Colours on Shoes.ColourId = Colours.Id" +
                     " inner join Sizes on Shoes.SizeId = Sizes.Id")
        ) {
            int counter = 0;
            while (rs.next()) {
                ShoeView tempShoeView = new ShoeView();
                counter++;
                // En counter för att ge varje sko en "ny" nyckel som inte har någonting att göra med den
                // syntetisk nyckeln i sql för att hjälpa kunden till att peka ut en produkt
                // utan att behöva skriva in ett namn (1......*).
                // detta kan ändras till vilken siffra som helst. Kan börja räkna från 100 om vi vill...
                tempShoeView.setNameOfShoe(rs.getString(ShoesColumns.NAME.getValue()));
                tempShoeView.setNameOfBrand(rs.getString(BrandColumns.NAME.getValue()));
                tempShoeView.setNameOfColour(rs.getString(ColoursColumns.NAME.getValue()));
                tempShoeView.setSize(rs.getInt(SizesColumns.SIZE.getValue()));
                tempShoeView.setPrice(rs.getInt(ShoesColumns.PRICE.getValue()));
                tempShoeView.setStock(rs.getInt(ShoesColumns.STOCK.getValue()));
                tempShoeView.setNumberChoice(counter);

                tempShoeView.setBrandId(rs.getInt(ShoesColumns.BRAND.getValue()));
                tempShoeView.setSizeId(rs.getInt(ShoesColumns.SIZE.getValue()));
                tempShoeView.setColourId(rs.getInt(ShoesColumns.COLOUR.getValue()));

                shoeView.add(tempShoeView);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return shoeView;
    }

    private List<Shoes> queryShoes() { // TODO
        // Hämtar alla skor från table Shoes
        List<Shoes> listOfShoes = new ArrayList<>();
        List<Brand> listOfbrands = queryBrand().stream().toList();
        List<Sizes> listOfSizes = querySize().stream().toList();
        List<Colours> listOfColours = queryColours().stream().toList();

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionStringThree"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from " + ShoesColumns.SHOES.getValue())

        ) {
            int counter = 0;
            while (rs.next()) {
                counter++;
                Shoes tempShoes = new Shoes();
                tempShoes.setId(rs.getInt(ShoesColumns.ID.getValue()));
                tempShoes.setName(rs.getString(ShoesColumns.NAME.getValue()));
                tempShoes.setBrandId(rs.getInt(ShoesColumns.BRAND.getValue()));
                tempShoes.setSizeId(rs.getInt(ShoesColumns.SIZE.getValue()));
                tempShoes.setColourId(rs.getInt(ShoesColumns.COLOUR.getValue()));
                tempShoes.setPrice(rs.getDouble(ShoesColumns.PRICE.getValue()));
                tempShoes.setStock(rs.getInt(ShoesColumns.STOCK.getValue()));

                listOfbrands.forEach(brand -> {
                    if (brand.getId() == tempShoes.getBrandId()) {
                        tempShoes.setBrand(brand);
                    }
                });

                listOfSizes.forEach(sizes -> {
                    if (sizes.getId() == tempShoes.getSizeId()) {
                        tempShoes.setSize(sizes);
                    }
                });

                listOfColours.forEach(colours -> {
                    if (colours.getId() == tempShoes.getColourId()) {
                        tempShoes.setColour(colours);
                    }
                });

                tempShoes.setNumberChoice(counter);

                listOfShoes.add(tempShoes);
            }

        } catch (SQLException e) {
            e.getStackTrace();
        }
        return listOfShoes;
    }


    private List<Brand> queryBrand() {
        // Hämtar Table Brand
        List<Brand> listOfBrands = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionStringThree"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from " + BrandColumns.TABLE_BRAND.getValue())
        ) {
            while (rs.next()) {
                Brand tempBrand = new Brand();
                tempBrand.setId(rs.getInt(BrandColumns.ID.getValue()));
                tempBrand.setName(rs.getString(BrandColumns.NAME.getValue()));

                listOfBrands.add(tempBrand);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }

        return listOfBrands;
    }

    private List<Sizes> querySize() {
        List<Sizes> listOfSizes = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionStringThree"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement()
        ) {
            ResultSet rs = stmt.executeQuery("select * from " + SizesColumns.TABLE_SIZES.getValue());
            while (rs.next()) {
                Sizes tempSize = new Sizes();
                tempSize.setId(rs.getInt(SizesColumns.ID.getValue()));
                tempSize.setSize(rs.getInt(SizesColumns.SIZE.getValue()));

                listOfSizes.add(tempSize);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return listOfSizes;
    }

    public void printCustomers() {
        // printar ut alla Customers med namn och adress. Mer info kan läggas till om man vill
        List<CustomersNot> listOfCustomers = queryCustomers();
        listOfCustomers.forEach(customersNot ->
                System.out.println(
                        customersNot.getName() + " " +
                                customersNot.getAdress()));
    }

    private List<CustomersNot> queryCustomers() {
        // Hämtar Customers table
        List<CustomersNot> listOfCustomers = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionStringThree"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from " + CustomersColumns.TABLE_NAME.getValue())
        ) {
            while (rs.next()) {
                CustomersNot tempCustomer = new CustomersNot();
                tempCustomer.setId(rs.getInt(CustomersColumns.ID.getValue()));
                tempCustomer.setSSA(rs.getString(CustomersColumns.SSA.getValue()));
                tempCustomer.setName(rs.getString(CustomersColumns.NAME.getValue()));
                tempCustomer.setAdress(rs.getString(CustomersColumns.ADRESS.getValue()));
                tempCustomer.setZipCode(rs.getInt(CustomersColumns.ZIPCODE.getValue()));
                tempCustomer.setRegion(rs.getString(CustomersColumns.REGION.getValue()));
                tempCustomer.setPassword(rs.getString(CustomersColumns.PASSWORD.getValue()));

                listOfCustomers.add(tempCustomer);
            }
        } catch (Exception e) {
            System.out.println("Query failed " + e.getMessage());
            e.getStackTrace();
        }
        return listOfCustomers;
    }

    private List<Colours> queryColours() {
        // Hämtar Colours table
        List<Colours> listOfColours = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionStringThree"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from " + ColoursColumns.TABLE_COLOURS.getValue())
        ) {
            while (rs.next()) {
                Colours tempColour = new Colours();
                tempColour.setId(rs.getInt(ColoursColumns.ID.getValue()));
                tempColour.setName(rs.getString(ColoursColumns.NAME.getValue()));

                listOfColours.add(tempColour);
            }

        } catch (SQLException e) {
            e.getStackTrace();
        }
        return listOfColours;
    }


    public List<Orders> queryOrders() {
        // Hämtar Colours Orders
        List<Orders> listOfOrders = new ArrayList<>();
        List<CustomersNot> listOfCustomers = queryCustomers().stream().toList();
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionStringThree"),
                p.getProperty("name"), p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from " + OrdersColumns.TABLE_ORDER.getValue())
        ) {
            while (rs.next()) {
                Orders tempOrder = new Orders();
                tempOrder.setId(rs.getInt(OrdersColumns.ID.getValue()));
                tempOrder.setCustomerId(rs.getInt(OrdersColumns.CUSTOMERID.getValue()));
                tempOrder.setCreated(rs.getString(OrdersColumns.CREATED.getValue()));
                tempOrder.setUpdated(rs.getString(OrdersColumns.UPDATED.getValue()));

                listOfCustomers.forEach(customersNot -> {
                    if (tempOrder.getId() == customersNot.getId()) {
                        tempOrder.setCustomer(customersNot);
                    }
                });

                listOfOrders.add(tempOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfOrders;
    }

    public void printNumberOfOrdersPerCustomer() {
        // Sparar två listor med Ordrar och Kunder.
        List<Orders> listOfOrders = queryOrders().stream().toList();
        List<CustomersNot> listOfCustomers = queryCustomers().stream().toList();

        List<Integer> listOfCustomerId = new ArrayList<>();
        List<Integer> sumOfOrders = new ArrayList<>();

        listOfOrders.stream().map(Orders::getCustomerId).forEach(listOfCustomerId::add);
        // Iterar igenom en lista med hjälp av stream lägger till antal ordrar varje person har lagt.
        // Vi gjorde detta innan vi byggde om programmet and why change something that works right?
        listOfCustomers.stream().map(CustomersNot::getId).forEach(integer -> {
            int counter = 0;
            for (Integer value : listOfCustomerId) {
                if (integer.equals(value)) {
                    counter++;
                }
            }
            sumOfOrders.add(counter);
        });

        for (int i = 0; i < listOfCustomers.size(); i++) {
            System.out.println(listOfCustomers.get(i).getName() + " ordrar: " + sumOfOrders.get(i));
        }
    }

    public List<OrderItem> queryOrderItem() {
        // Hämtar OrderItem table
        List<OrderItem> listOfOrderItems = new ArrayList<>();
        List<Orders> listOfOrders = queryOrders().stream().toList();
        List<Shoes> listOfShoes = queryShoes().stream().toList();

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionStringThree"),
                p.getProperty("name"), p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from " + OrderItemColumns.TABLE_ORDERITEM.getValue())
        ) {
            while (rs.next()) {
                OrderItem tempOrderItem = new OrderItem();
                tempOrderItem.setId(rs.getInt(OrderItemColumns.ID.getValue()));
                tempOrderItem.setQuantity(rs.getInt(OrderItemColumns.QUANTITY.getValue()));

                tempOrderItem.setOrderId(rs.getInt(OrderItemColumns.ORDERID.getValue()));
                tempOrderItem.setShoesId(rs.getInt(OrderItemColumns.SHOESID.getValue()));
                // lägger in ett matchade shoe objekt och matchade Order objekt i OrderItem
                listOfShoes.forEach(shoes -> {
                    if (tempOrderItem.getShoesId() == shoes.getSizeId()) {
                        tempOrderItem.setShoes(shoes);
                    }
                });

                listOfOrders.forEach(orders -> {
                    if (orders.getId() == tempOrderItem.getOrderId()) {
                        tempOrderItem.setOrder(orders);
                    }
                });


                listOfOrderItems.add(tempOrderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfOrderItems;
    }


    public List<amountOfMoneyCustomer> orderSpentView() {
        List<amountOfMoneyCustomer> listOfAmountOfcustomers = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionStringThree"),
                p.getProperty("name"), p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select " +
                     "Customer.CustomerName, " +
                     "OrderItem.Quantity, " +
                     "OrderItem.ShoesId," +
                     "OrderItem.OrderId," +
                     "Shoes.Price," +
                     "Orders.CustomerId, " +
                     "Orders.Id" +
                     " from Orders" +
                     " inner join OrderItem on OrderItem.OrderId = Orders.Id" +
                     " inner join Customer on Orders.CustomerId = Customer.Id" +
                     " inner join Shoes on OrderItem.ShoesId = Shoes.Id")
        ) {
            while (rs.next()) {
                amountOfMoneyCustomer tempOrderOfCustomers = new amountOfMoneyCustomer();
                tempOrderOfCustomers.setCustomerName(rs.getString(AmountOfMoneyCustomerColumns.CUSTOMERNAME.getValue()));
                tempOrderOfCustomers.setQuantity(rs.getInt(AmountOfMoneyCustomerColumns.QUANTITY.getValue()));
                tempOrderOfCustomers.setShoeId(rs.getInt(AmountOfMoneyCustomerColumns.SHOEID.getValue()));
                tempOrderOfCustomers.setOrderId(rs.getInt(AmountOfMoneyCustomerColumns.ORDERID.getValue()));
                tempOrderOfCustomers.setPrice(rs.getDouble(AmountOfMoneyCustomerColumns.PRICE.getValue()));
                tempOrderOfCustomers.setOrderItemId(rs.getInt(AmountOfMoneyCustomerColumns.ORDERITEMID.getValue()));
                tempOrderOfCustomers.setCustomerId(rs.getInt(AmountOfMoneyCustomerColumns.CUSTOMERID.getValue()));

                listOfAmountOfcustomers.add(tempOrderOfCustomers);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfAmountOfcustomers;
    }

    public List<OrderCost> calculateAmountOfMoneySpentPerCustomer() {
        List<amountOfMoneyCustomer> customers = orderSpentView().stream().toList();
        List<String> person = new ArrayList<>();
        List<OrderCost> costs = new ArrayList<>();

        customers.stream().distinct().forEach(amountOfMoneyCustomer -> person.add(amountOfMoneyCustomer.getCustomerName()));

        person.stream().distinct().forEach(s -> costs.add(new OrderCost(s, 0)));


        for (OrderCost cost : costs) {
            for (amountOfMoneyCustomer customer : customers) {
                if (cost.getCustoemrName().equals(customer.getCustomerName())) {
                    cost.setPrice(cost.getPrice() + customer.getPrice() * customer.getQuantity());
                }
            }
        }
        return costs;
    }

    public List<Category> queryCategory() {
        List<Category> listOfCategories = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionStringThree"),
                p.getProperty("name"), p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from Category")
        ) {
            while (rs.next()) {
                listOfCategories.add(new Category(
                        rs.getInt("Id"),
                        rs.getString("NameOfCategory")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfCategories;
    }

    public void printTotalCustomerSpent(List<OrderCost> costs) {
        costs.forEach(orderCost -> System.out.println(orderCost.getCustoemrName() + " " + orderCost.getPrice()));
    }

    shoeSeachInterface brandSearch = (c, s) -> c.getBrand().getName().equalsIgnoreCase(s);
    shoeSeachInterface colourSearch = (c, s) -> c.getColour().getName().equalsIgnoreCase(s);
    shoeSeachInterface sizeSearch = (c, s) -> c.getSize().getSize() == Integer.parseInt(s);

    public void wordsToSeachFor(shoeSeachInterface ssi, String wordToSearch) {
        List<OrderItem> listOfOrderItem = queryOrderItem().stream().toList();
        listOfOrderItem.forEach(orderItem -> {
            if (orderItem.getShoes().getName() == null
                    || orderItem.getShoes().getBrand() == null
                    || orderItem.getShoes().getColour() == null
                    || orderItem.getShoes().getSize() == null
                    || orderItem.getOrder().getCustomer().getAdress() == null) {
            } else if (ssi.search(orderItem.getShoes(), wordToSearch)) {
                System.out.println(orderItem.getOrder().getCustomer().getName() +
                        " adress: " + orderItem.getOrder().getCustomer().getAdress() +
                        ", " + orderItem.getOrder().getCustomer().getRegion() +
                        ".\nshoe: " + orderItem.getShoes().getName() +
                        ".\nbrand: " + orderItem.getShoes().getBrand().getName() +
                        ".\ncolour: " + orderItem.getShoes().getColour().getName() +
                        "\nsize: " + orderItem.getShoes().getSize().getSize());
                System.out.println("--------------------------------------------------------------");
            }
        });
    }

    public void printFilter() {
        // 2015987738
        while (true) {
            System.out.println("1 for brand\n2 for Colour\n3 for size");
            final String attributeToSearchFor = utility.scannerString();

            switch (attributeToSearchFor) {
                case "1" -> System.out.println("What brand?");
                case "2" -> System.out.println("What colour?");
                case "3" -> System.out.println("What size?");
                default -> {
                    System.out.println("Invalid input");
                    continue;
                }
            }
// TODO Pink nike 43

            final String attributeToSearchFor2 = utility.scannerString();
            switch (attributeToSearchFor) {
                case "1" -> wordsToSeachFor(brandSearch, attributeToSearchFor2);
                case "2" -> wordsToSeachFor(colourSearch, attributeToSearchFor2);
                case "3" -> wordsToSeachFor(sizeSearch, attributeToSearchFor2);
                default -> {
                    System.out.println("Invalid input");
                    continue;
                }
            }
            return;
        }
    }
}


@FunctionalInterface
interface shoeSeachInterface {
    boolean search(Shoes shoe, String SearchWord);
}