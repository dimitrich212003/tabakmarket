package com.example.tabakjavafx;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataAccessor {
    private static DataAccessor da;

    private String currentTable; // текущая таблица

    static {
        try {
            da = new DataAccessor("jdbc:postgresql://localhost/tabakmarket", "postgres", "Ytrewq11!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection connection;

    public DataAccessor() {

    }

    public static DataAccessor getDataAccessor() {
        return da;
    }

    public void setCurrentTable(String table) {
        this.currentTable = table;
    }

    private DataAccessor(String url, String username, String password) throws SQLException {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database");
        }
        catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }
    };


    //open
    public List<buy> getBuyList() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost/tabakmarket";
            String username = "postgres";
            String password = "Ytrewq11!";

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database");

            Statement st = connection.createStatement();
            // брать значение currentTable из контроллера
            ResultSet rs = st.executeQuery("SELECT * FROM " + currentTable);
            System.out.println(currentTable);

            List<buy> bl = new ArrayList<>();

            switch (currentTable) {
                case "product":
                    while (rs.next()) {
                        int productId = rs.getInt(1);
                        String productName = rs.getString(2);
                        String taste = rs.getString(3);
                        int nicotineContent = rs.getInt(4);
                        float purchasePrice = rs.getFloat(5);
                        float margin = rs.getFloat(6);
                        Long markingNumber = rs.getLong(7);
                        buy b = new buy(productId, productName, taste, nicotineContent, purchasePrice, margin, markingNumber);
                        bl.add(b);
                    }
                    break;
                case "buy":
                    while (rs.next()) {
                        int s_id = rs.getInt(1);
                        Date date = rs.getDate(2);
                        int b_id = rs.getInt(3);
                        buy b = new buy(s_id, (java.sql.Date) date, b_id);
                        bl.add(b);
                    }
                    break;
                case "sales_log":
                    while (rs.next()) {
                        int productId = rs.getInt(1);
                        int s_id = rs.getInt(2);
                        int quantityProdSold = rs.getInt(3);
                        buy b = new buy(productId, s_id, quantityProdSold);
                        bl.add(b);
                    }
                    break;
                case "supply":
                    while (rs.next()) {
                        int supply_id = rs.getInt(1);
                        Date delivery_date = rs.getDate(2);
                        int provider_id = rs.getInt(3);
                        buy b = new buy(supply_id, (java.sql.Date) delivery_date, provider_id, true);
                        bl.add(b);
                    }
                    break;
                case "supply_log":
                    while (rs.next()) {
                        int productId = rs.getInt(1);
                        int supplyId = rs.getInt(2);
                        int quantityDeliveredProd = rs.getInt(3);
                        buy b = new buy(productId, supplyId, quantityDeliveredProd, true);
                        bl.add(b);
                    }
                    break;
                case "buyer":
                    while (rs.next()) {
                        int buyerId = rs.getInt(1);
                        String login = rs.getString(2);
                        String pass = rs.getString(3);
                        String firstName = rs.getString(4);
                        String lastName = rs.getString(5);
                        String patronymic = rs.getString(6);
                        String email = rs.getString(7);
                        String phone = rs.getString(8);
                        buy b = new buy(buyerId, login, pass, firstName, lastName, patronymic, email, phone);
                        bl.add(b);
                    }
                    break;
                case "provider":
                    while (rs.next()) {
                        int providerId = rs.getInt(1);
                        String supplierName = rs.getString(2);
                        String paymentAccount = rs.getString(3);
                        String adress = rs.getString(4);
                        buy b = new buy(providerId, supplierName, paymentAccount, adress);
                        bl.add(b);
                    }
                    break;
                default:
                    System.out.println("error");
            }

            return bl;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBC Driver is not found. Include it in your library path");
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    //add
    private String[] values;

    public DataAccessor(String[] values) {
        this.values = values;
    }

    public void addToTable() throws SQLException{
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost/tabakmarket";
            String username = "postgres";
            String password = "Ytrewq11!";

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database");
            switch (currentTable) {
                case "product":
                    String insertSql = "INSERT INTO product (product_id, product_name, taste, nicotine_content, purchase_price, margin, marking_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(insertSql);

                    statement.setInt(1, Integer.parseInt(values[0]));

                    statement.setString(2, values[1]);
                    statement.setString(3, values[2]);

                    statement.setInt(4, Integer.parseInt(values[3]));

                    statement.setFloat(5, Float.parseFloat(values[4]));

                    statement.setFloat(6, Float.parseFloat(values[5]));

                    statement.setLong(7, Long.parseLong(values[6]));

                    int rowsAffected = statement.executeUpdate();
                    System.out.println(rowsAffected + " rows affected");
                    break;
                case "sales_log":
                    insertSql = "INSERT INTO sales_log (product_id, sales_id, quantity_prod_sold) VALUES (?, ?, ?)";
                    PreparedStatement statement2 = connection.prepareStatement(insertSql);

                    statement2.setInt(1, Integer.parseInt(values[0]));
                    statement2.setInt(2, Integer.parseInt(values[1]));
                    statement2.setInt(3, Integer.parseInt(values[2]));

                    int rowsAffected2 = statement2.executeUpdate();
                    System.out.println(rowsAffected2 + " rows affected");
                    break;
                case "supply_log":
                    insertSql = "INSERT INTO supply_log (product_id, supply_id, quantity_delivered_prod) VALUES (?, ?, ?)";
                    PreparedStatement statement4 = connection.prepareStatement(insertSql);

                    statement4.setInt(1, Integer.parseInt(values[0]));
                    statement4.setInt(2, Integer.parseInt(values[1]));
                    statement4.setInt(3, Integer.parseInt(values[2]));

                    int rowsAffected4 = statement4.executeUpdate();
                    System.out.println(rowsAffected4 + " rows affected");
                    break;
                case "buy":
                    insertSql = "INSERT INTO buy (sales_id, sales_date, buyer_id) VALUES (?, ?, ?)";
                    PreparedStatement statement3 = connection.prepareStatement(insertSql);

                    statement3.setInt(1, Integer.parseInt(values[0]));

                    String dateString = values[1];
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = dateFormat.parse(dateString);
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    statement3.setDate(2, sqlDate);

                    statement3.setInt(3, Integer.parseInt(values[2]));

                    int rowsAffected3 = statement3.executeUpdate();
                    System.out.println(rowsAffected3 + " rows affected");
                    break;
                case "supply":
                    insertSql = "INSERT INTO supply (supply_id, delivery_date, provider_id) VALUES (?, ?, ?)";
                    PreparedStatement statement5 = connection.prepareStatement(insertSql);

                    statement5.setInt(1, Integer.parseInt(values[0]));

                    String dateString1 = values[1];
                    DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                    Date date1 = dateFormat1.parse(dateString1);
                    java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
                    statement5.setDate(2, sqlDate1);

                    statement5.setInt(3, Integer.parseInt(values[2]));

                    int rowsAffected5 = statement5.executeUpdate();
                    System.out.println(rowsAffected5 + " rows affected");
                    break;
                case "buyer":
                    insertSql = "INSERT INTO buyer (buyer_id, login, password, first_name, last_name, patronymic, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement6 = connection.prepareStatement(insertSql);

                    statement6.setInt(1, Integer.parseInt(values[0]));

                    statement6.setString(2, values[1]);
                    statement6.setString(3, values[2]);
                    statement6.setString(4, values[3]);
                    statement6.setString(5, values[4]);
                    statement6.setString(6, values[5]);
                    statement6.setString(7, values[6]);
                    statement6.setString(8, values[7]);

                    int rowsAffected6 = statement6.executeUpdate();
                    System.out.println(rowsAffected6 + " rows affected");
                    break;
                case "provider":
                    insertSql = "INSERT INTO provider (provider_id, supplier_name, payment_account, adress) VALUES (?, ?, ?, ?)";
                    PreparedStatement statement7 = connection.prepareStatement(insertSql);

                    statement7.setInt(1, Integer.parseInt(values[0]));

                    statement7.setString(2, values[1]);
                    statement7.setString(3, values[2]);
                    statement7.setString(4, values[3]);

                    int rowsAffected7 = statement7.executeUpdate();
                    System.out.println(rowsAffected7 + " rows affected");
                    break;
            }

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
        } catch (ClassNotFoundException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBuy(buy selectedBuy) throws SQLException {
        Connection connection = null;
        try {
            // Установите соединение с базой данных
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost/tabakmarket";
            String username = "postgres";
            String password = "Ytrewq11!";

            connection = DriverManager.getConnection(url, username, password);
            // Создайте SQL-запрос DELETE для удаления строки из таблицы
            switch (currentTable) {
                case "product":
                    String deleteSql = "DELETE FROM product WHERE product_id = ?";
                    PreparedStatement statement = connection.prepareStatement(deleteSql);
                    statement.setInt(1, selectedBuy.getSalesId());
                    int rowsAffected = statement.executeUpdate();
                    System.out.println(rowsAffected + " rows affected");
                    break;
                case "buy":
                    deleteSql = "DELETE FROM buy WHERE sales_id = ?";
                    PreparedStatement statement1 = connection.prepareStatement(deleteSql);
                    statement1.setInt(1, selectedBuy.getSalesId());
                    int rowsAffected1 = statement1.executeUpdate();
                    System.out.println(rowsAffected1 + " rows affected");
                    break;
                case "sales_log":
                    deleteSql = "DELETE FROM sales_log WHERE product_id = ? AND sales_id = ?";
                    PreparedStatement statement2 = connection.prepareStatement(deleteSql);
                    statement2.setInt(1, selectedBuy.getSalesId());
                    statement2.setInt(2, selectedBuy.getSupplyId());
                    int rowsAffected2 = statement2.executeUpdate();
                    System.out.println(rowsAffected2 + " rows affected");
                    break;
                case "supply":
                    deleteSql = "DELETE FROM supply WHERE supply_id = ?";
                    PreparedStatement statement4 = connection.prepareStatement(deleteSql);
                    statement4.setInt(1, selectedBuy.getSalesId());
                    int rowsAffected4 = statement4.executeUpdate();
                    System.out.println(rowsAffected4 + " rows affected");
                    break;
                case "supply_log":
                    deleteSql = "DELETE FROM supply_log WHERE product_id = ? AND supply_id = ?";
                    PreparedStatement statement3 = connection.prepareStatement(deleteSql);
                    statement3.setInt(1, selectedBuy.getSalesId());
                    statement3.setInt(2, selectedBuy.getSupplyId());
                    int rowsAffected3 = statement3.executeUpdate();
                    System.out.println(rowsAffected3 + " rows affected");
                    break;
                case "buyer":
                    deleteSql = "DELETE FROM buyer WHERE buyer_id = ?";
                    PreparedStatement statement5 = connection.prepareStatement(deleteSql);
                    statement5.setInt(1, selectedBuy.getSalesId());
                    int rowsAffected5 = statement5.executeUpdate();
                    System.out.println(rowsAffected5 + " rows affected");
                    break;
                case "provider":
                    deleteSql = "DELETE FROM provider WHERE provider_id = ?";
                    PreparedStatement statement6 = connection.prepareStatement(deleteSql);
                    statement6.setInt(1, selectedBuy.getSalesId());
                    int rowsAffected6 = statement6.executeUpdate();
                    System.out.println(rowsAffected6 + " rows affected");
                    break;
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            // Закройте соединение с базой данных
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<buy> getBuyListWithJoin(String searchTerm) throws SQLException {
        List<buy> resultList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost/tabakmarket";
            String username = "postgres";
            String password = "Ytrewq11!";

            connection = DriverManager.getConnection(url, username, password);

            String sqlQuery = "SELECT p.product_id, p.product_name, p.taste, p.nicotine_content, p.purchase_price, p.margin, p.marking_number " +
                    "FROM product p " +
                    "JOIN supply_log sl ON p.product_id = sl.product_id " +
                    "JOIN supply s ON sl.supply_id = s.supply_id " +
                    "JOIN provider pr ON s.provider_id = pr.provider_id " +
                    "WHERE LOWER(pr.supplier_name) LIKE ?";

            statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, '%' + searchTerm + '%');

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                String taste = resultSet.getString("taste");
                int nicotineContent = resultSet.getInt("nicotine_content");
                float purchasePrice = resultSet.getFloat("purchase_price");
                float margin = resultSet.getFloat("margin");
                long markingNumber = resultSet.getLong("marking_number");

                buy b = new buy(productId, productName, taste, nicotineContent, purchasePrice, margin, markingNumber);
                resultList.add(b);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBC Driver is not found. Include it in your library path");
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return resultList;
    }

    public List<buy> getBuyListWithJoin2(String searchTerm) throws SQLException {
        List<buy> resultList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost/tabakmarket";
            String username = "postgres";
            String password = "Ytrewq11!";

            connection = DriverManager.getConnection(url, username, password);

            String sqlQuery = "SELECT p.product_id, p.product_name, p.taste, p.nicotine_content, p.purchase_price, p.margin, p.marking_number " +
                    "FROM product p " +
                    "JOIN sales_log sl ON p.product_id = sl.product_id " +
                    "JOIN buy b ON sl.sales_id = b.sales_id " +
                    "JOIN buyer br ON b.buyer_id = br.buyer_id " +
                    "WHERE LOWER(br.login) LIKE ?";

            statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, '%' + searchTerm + '%');

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                String taste = resultSet.getString("taste");
                int nicotineContent = resultSet.getInt("nicotine_content");
                float purchasePrice = resultSet.getFloat("purchase_price");
                float margin = resultSet.getFloat("margin");
                long markingNumber = resultSet.getLong("marking_number");

                buy b = new buy(productId, productName, taste, nicotineContent, purchasePrice, margin, markingNumber);
                resultList.add(b);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBC Driver is not found. Include it in your library path");
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return resultList;
    }

    public List<buy> getBuyListWithJoin3(String searchTerm) throws SQLException {
        List<buy> resultList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost/tabakmarket";
            String username = "postgres";
            String password = "Ytrewq11!";

            connection = DriverManager.getConnection(url, username, password);

            String sqlQuery = "SELECT DISTINCT sl.product_id, sl.sales_id, sl.quantity_prod_sold " +
                    "FROM sales_log sl " +
                    "JOIN buy b ON sl.sales_id = b.sales_id " +
                    "WHERE b.sales_date >= ? " +
                    "AND sl.product_id IN ( " +
                    "  SELECT DISTINCT product_id " +
                    "  FROM sales_log " +
                    "  JOIN buy b ON sl.sales_id = b.sales_id " +
                    "  WHERE b.sales_date >= ? " +
                    ")";

            statement = connection.prepareStatement(sqlQuery);

            LocalDate searchDate = LocalDate.parse(searchTerm, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            statement.setDate(1, java.sql.Date.valueOf(searchDate));
            statement.setDate(2, java.sql.Date.valueOf(searchDate));

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                int salesId = resultSet.getInt("sales_id");
                int quantityProdSold = resultSet.getInt("quantity_prod_sold");

                buy b = new buy(productId, salesId, quantityProdSold);
                resultList.add(b);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBC Driver is not found. Include it in your library path");
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return resultList;
    }

    public List<buy> getBuyListWithJoin4(String searchTerm) throws SQLException {
        List<buy> resultList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost/tabakmarket";
            String username = "postgres";
            String password = "Ytrewq11!";

            connection = DriverManager.getConnection(url, username, password);

            String sqlQuery = "SELECT DISTINCT br.login, br.phone, br.last_name " +
                    "FROM buyer br " +
                    "JOIN buy b ON br.buyer_id = b.buyer_id " +
                    "JOIN sales_log sl ON b.sales_id = sl.sales_id " +
                    "JOIN product p ON sl.product_id = p.product_id " +
                    "WHERE p.nicotine_content > ? AND br.phone IS NOT NULL " +
                    "ORDER BY br.last_name ASC";

            statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, Integer.parseInt(searchTerm));

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String phone = resultSet.getString("phone");
                String lastName = resultSet.getString("last_name");

                buy b = new buy(login, phone, lastName);
                resultList.add(b);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBC Driver is not found. Include it in your library path");
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return resultList;
    }

    public static void main(String[] args) {
        try {
            DataAccessor dataAccessor = DataAccessor.getDataAccessor();
            List<buy> buyList = dataAccessor.getBuyList();
            // Далее можно выполнить необходимые операции с полученным списком buyList
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}