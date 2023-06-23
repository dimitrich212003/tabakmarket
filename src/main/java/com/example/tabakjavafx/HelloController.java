
package com.example.tabakjavafx;

        import java.net.URL;
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.PreparedStatement;
        import java.sql.SQLException;
        import java.text.DateFormat;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;
        import java.util.ResourceBundle;
        import java.util.stream.Collectors;


        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.TableColumn;
        import javafx.scene.control.TableView;
        import javafx.scene.control.ComboBox;
        import javafx.scene.control.TextField;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.input.MouseEvent;
        import javafx.stage.Stage;
        import javafx.fxml.Initializable;

public class HelloController implements Initializable {

    private DataAccessor dataAccessor;

    public void setDataAccessor(DataAccessor dataAccessor) {
        this.dataAccessor = dataAccessor;
    }

    @FXML
    private TableView<buy> tvMain;
    @FXML
    private TableColumn<buy, Integer> productIdColumn;
    @FXML
    private TableColumn<buy, String> productNameColumn;
    @FXML
    private TableColumn<buy, String> tasteColumn;
    @FXML
    private TableColumn<buy, Integer> nicotineContentColumn;
    @FXML
    private TableColumn<buy, Float> purchasePriceColumn;
    @FXML
    private TableColumn<buy, Float> marginColumn;
    @FXML
    private TableColumn<buy, Long> markingNumberColumn;

    @FXML
    private TableColumn<buy, Integer> salesIdIdColumn;
    @FXML
    private TableColumn<buy, Integer> quantityProdSoldColumn;

    @FXML
    private TableColumn<buy, String> loginColumn;
    @FXML
    private TableColumn<buy, String> phoneColumn;
    @FXML
    private TableColumn<buy, String> lastNameColumn;

    @FXML
    private TextField ItemChange1;

    @FXML
    private TextField ItemChange2;

    @FXML
    private TextField ItemChange3;

    @FXML
    private TextField ItemChange4;

    @FXML
    private TextField ItemChange5;

    @FXML
    private TextField ItemChange6;

    @FXML
    private TextField ItemChange7;

    @FXML
    private TextField ItemChange8;

    @FXML
    private Button join1;

    @FXML
    private Button join2;

    @FXML
    private Button join3;

    @FXML
    private Button join4;

    @FXML
    private TextField joinSearch;

    @FXML
    private TextField Search;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnChange;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnDel;

    @FXML
    private ComboBox<String> comb;

    @FXML
    private TextField addField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("product", "supply_log", "sales_log", "buy", "supply", "buyer", "provider");
        comb.setItems(list);
    }

    @FXML
    private void handleBtnCloseAction(ActionEvent event) {
        Stage s = (Stage) btnClose.getScene().getWindow();
        s.close();
    }

    @FXML
    private void handleBtnOpenAction(ActionEvent event) {
        try {
            DataAccessor da = DataAccessor.getDataAccessor();
            tvMain.getColumns().clear();

            String s = comb.getSelectionModel().getSelectedItem().toString();
            dataAccessor.setCurrentTable(s);

            // buy
            TableColumn<buy, Integer> id_sCol = new TableColumn<>("sales_id");
            TableColumn<buy, Date> dateCol = new TableColumn<>("sales_date");
            TableColumn<buy, Integer> id_bCol = new TableColumn<>("buyer_id");

            id_sCol.setCellValueFactory(new PropertyValueFactory<>("salesId"));
            dateCol.setCellValueFactory(new PropertyValueFactory<>("salesDate"));
            id_bCol.setCellValueFactory(new PropertyValueFactory<>("buyerId"));

            // product
            TableColumn<buy, Integer> product_id = new TableColumn<>("product_id");
            TableColumn<buy, String> product_name = new TableColumn<>("product_name");
            TableColumn<buy, String> taste = new TableColumn<>("taste");
            TableColumn<buy, Integer> nicotine_content  = new TableColumn<>("nicotine_content");
            TableColumn<buy, Float> purchase_price  = new TableColumn<>("purchase_price");
            TableColumn<buy, Float> margin = new TableColumn<>("margin");
            TableColumn<buy, Long> marking_number = new TableColumn<>("marking_number");

            product_id.setCellValueFactory(new PropertyValueFactory<>("productId"));
            product_name.setCellValueFactory(new PropertyValueFactory<>("productName"));
            taste.setCellValueFactory(new PropertyValueFactory<>("taste"));
            nicotine_content.setCellValueFactory(new PropertyValueFactory<>("nicotineContent"));
            purchase_price.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
            margin.setCellValueFactory(new PropertyValueFactory<>("margin"));
            marking_number.setCellValueFactory(new PropertyValueFactory<>("markingNumber"));

            //sales_log
            TableColumn<buy, Integer> quantity_prod_sold = new TableColumn<>("quantity_prod_sold");
            quantity_prod_sold.setCellValueFactory(new PropertyValueFactory<>("quantityProdSold"));

            //supply
            TableColumn<buy, Integer> supply_id = new TableColumn<>("supply_id");
            TableColumn<buy, Date> delivery_date = new TableColumn<>("delivery_date");
            TableColumn<buy, Integer> provider_id = new TableColumn<>("provider_id");

            supply_id.setCellValueFactory(new PropertyValueFactory<>("supplyId"));
            delivery_date.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
            provider_id.setCellValueFactory(new PropertyValueFactory<>("providerId"));

            //supply_log
            TableColumn<buy, Integer> quantity_delivered_prod = new TableColumn<>("quantity_delivered_prod");
            quantity_delivered_prod.setCellValueFactory(new PropertyValueFactory<>("quantityDeliveredProd"));

            //buyer
            TableColumn<buy, String> login = new TableColumn<>("login");
            TableColumn<buy, String> password = new TableColumn<>("password");
            TableColumn<buy, String> first_name = new TableColumn<>("first_name");
            TableColumn<buy, String> last_name = new TableColumn<>("last_name");
            TableColumn<buy, String> patronymic = new TableColumn<>("patronymic");
            TableColumn<buy, String> email = new TableColumn<>("email");
            TableColumn<buy, String> phone = new TableColumn<>("phone");

            login.setCellValueFactory(new PropertyValueFactory<>("login"));
            password.setCellValueFactory(new PropertyValueFactory<>("password"));
            first_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            last_name.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            patronymic.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

            //provider
            TableColumn<buy, String> supplier_name = new TableColumn<>("supplier_name");
            TableColumn<buy, String> payment_account = new TableColumn<>("payment_account");
            TableColumn<buy, String> adress = new TableColumn<>("adress");

            supplier_name.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
            payment_account.setCellValueFactory(new PropertyValueFactory<>("paymentAccount"));
            adress.setCellValueFactory(new PropertyValueFactory<>("adress"));


            List<buy> buyList = da.getBuyList();
            ObservableList<buy> observableBuyList = FXCollections.observableArrayList(buyList);

            switch (s) {
                case "product":
                    tvMain.getColumns().addAll(product_id,product_name,taste,nicotine_content,purchase_price,margin,marking_number);
                    break;
                case "buy":
                    tvMain.getColumns().addAll(id_sCol,dateCol,id_bCol);
                    break;
                case "sales_log":
                    tvMain.getColumns().addAll(product_id,id_sCol,quantity_prod_sold);
                    break;
                case "supply":
                    tvMain.getColumns().addAll(supply_id,delivery_date,provider_id);
                    break;
                case "supply_log":
                    tvMain.getColumns().addAll(product_id,supply_id,quantity_delivered_prod);
                    break;
                case "buyer":
                    tvMain.getColumns().addAll(id_bCol,login,password,first_name,last_name,patronymic,email,phone);
                    break;
                case "provider":
                    tvMain.getColumns().addAll(provider_id,supplier_name,payment_account,adress);
                    break;
                default:
                    System.out.println("error");
            }
            tvMain.setItems(observableBuyList);
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBtnAddAction(ActionEvent event) throws SQLException {
        String value = addField.getText();
        String[] values = value.split(", ");
        DataAccessor dataAccessor = new DataAccessor(values);
        String s = comb.getSelectionModel().getSelectedItem().toString();
        dataAccessor.setCurrentTable(s);
        dataAccessor.addToTable();

        DataAccessor da = DataAccessor.getDataAccessor();
        List<buy> buyList = da.getBuyList();
        // Обновляем таблицу
        ObservableList<buy> updatedData = FXCollections.observableArrayList(buyList);
        tvMain.setItems(updatedData);
    }

    @FXML
    private void filterData() {
        String searchTerm = Search.getText().toLowerCase();
        String s = comb.getSelectionModel().getSelectedItem().toString();
        List<buy> filteredList;
        try {
            // Получите полный список данных
            filteredList = DataAccessor.getDataAccessor().getBuyList();

            if (!searchTerm.isEmpty()) {
                // Если поле поиска не пустое, выполните фильтрацию по каждой колонке
                switch (s) {
                    case "product":
                        filteredList = filteredList.stream()
                                .filter(item -> item.getProductName().toLowerCase().contains(searchTerm) ||
                                        item.getTaste().toLowerCase().contains(searchTerm) ||
                                        String.valueOf(item.getNicotineContent()).toLowerCase().contains(searchTerm) ||
                                        String.valueOf(item.getPurchasePrice()).toLowerCase().contains(searchTerm) ||
                                        String.valueOf(item.getMargin()).toLowerCase().contains(searchTerm) ||
                                        String.valueOf(item.getMarkingNumber()).toLowerCase().contains(searchTerm))
                                .collect(Collectors.toList());
                        break;
                    case "buy":
                        filteredList = filteredList.stream()
                                .filter(item ->
                                        String.valueOf(item.getSalesId()).toLowerCase().contains(searchTerm) ||
                                        String.valueOf(item.getSalesDate()).toLowerCase().contains(searchTerm) ||
                                        String.valueOf(item.getBuyerId()).toLowerCase().contains(searchTerm))
                                .collect(Collectors.toList());
                        break;
                    case "sales_log":
                        filteredList = filteredList.stream()
                                .filter(item ->
                                        String.valueOf(item.getProductId()).toLowerCase().contains(searchTerm) ||
                                                String.valueOf(item.getSalesId()).toLowerCase().contains(searchTerm) ||
                                                String.valueOf(item.getQuantityProdSold()).toLowerCase().contains(searchTerm))
                                .collect(Collectors.toList());
                        break;
                    case "supply":
                        filteredList = filteredList.stream()
                                .filter(item ->
                                        String.valueOf(item.getSupplyId()).toLowerCase().contains(searchTerm) ||
                                                String.valueOf(item.getDeliveryDate()).toLowerCase().contains(searchTerm) ||
                                                String.valueOf(item.getProviderId()).toLowerCase().contains(searchTerm))
                                .collect(Collectors.toList());
                        break;
                    case "supply_log":
                        filteredList = filteredList.stream()
                                .filter(item ->
                                        String.valueOf(item.getProductId()).toLowerCase().contains(searchTerm) ||
                                                String.valueOf(item.getSupplyId()).toLowerCase().contains(searchTerm) ||
                                                String.valueOf(item.getQuantityDeliveredProd()).toLowerCase().contains(searchTerm))
                                .collect(Collectors.toList());
                        break;
                    case "buyer":
                        filteredList = filteredList.stream()
                                .filter(item ->
                                        item.getLogin().toLowerCase().contains(searchTerm) ||
                                                item.getPassword().toLowerCase().contains(searchTerm) ||
                                                item.getFirstName().toLowerCase().contains(searchTerm) ||
                                                item.getLastName().toLowerCase().contains(searchTerm) ||
                                                (item.getPatronymic() != null && item.getPatronymic().toLowerCase().contains(searchTerm)) ||
                                                item.getEmail().toLowerCase().contains(searchTerm) ||
                                                (item.getPhone() != null && item.getPhone().toLowerCase().contains(searchTerm))
                                )
                                .collect(Collectors.toList());
                        break;
                    case "provider":
                        filteredList = filteredList.stream()
                                .filter(item -> item.getSupplierName().toLowerCase().contains(searchTerm) ||
                                        item.getPaymentAccount().toLowerCase().contains(searchTerm) ||
                                        item.getAdress().toLowerCase().contains(searchTerm)
                                )
                                .collect(Collectors.toList());
                        break;
                    default:
                        System.out.println("error");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Установите отфильтрованный список в таблицу
        tvMain.setItems(FXCollections.observableArrayList(filteredList));
    }

    @FXML
    private void joinSearch() {
        String searchTerm = joinSearch.getText().toLowerCase();
        System.out.println(searchTerm);
        List<buy> filteredList;

        try {
            // Получите полный список данных
            filteredList = DataAccessor.getDataAccessor().getBuyListWithJoin(searchTerm);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        for (buy item : filteredList) {
            System.out.println(item);
        }

        // Очистите существующие колонки таблицы
        tvMain.getColumns().clear();

        // Создайте колонки только для таблицы product
        productIdColumn = new TableColumn<>("Product ID");
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productNameColumn = new TableColumn<>("Product Name");
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tasteColumn = new TableColumn<>("Taste");
        tasteColumn.setCellValueFactory(new PropertyValueFactory<>("taste"));
        nicotineContentColumn = new TableColumn<>("Nicotine Content");
        nicotineContentColumn.setCellValueFactory(new PropertyValueFactory<>("nicotineContent"));
        purchasePriceColumn = new TableColumn<>("Purchase Price");
        purchasePriceColumn.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        marginColumn = new TableColumn<>("Margin");
        marginColumn.setCellValueFactory(new PropertyValueFactory<>("margin"));
        markingNumberColumn = new TableColumn<>("Marking Number");
        markingNumberColumn.setCellValueFactory(new PropertyValueFactory<>("markingNumber"));

        // Добавьте колонки в таблицу
        tvMain.getColumns().addAll(productIdColumn, productNameColumn, tasteColumn, nicotineContentColumn, purchasePriceColumn, marginColumn, markingNumberColumn);

        // Установите отфильтрованный список в таблицу
        tvMain.setItems(FXCollections.observableArrayList(filteredList));
    }

    @FXML
    private void joinSearch2() {
        String searchTerm = joinSearch.getText().toLowerCase();
        System.out.println(searchTerm);
        List<buy> filteredList;

        try {
            // Получите полный список данных
            filteredList = DataAccessor.getDataAccessor().getBuyListWithJoin2(searchTerm);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Очистите существующие колонки таблицы
        tvMain.getColumns().clear();

        // Создайте колонки только для таблицы product
        productIdColumn = new TableColumn<>("Product ID");
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productNameColumn = new TableColumn<>("Product Name");
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tasteColumn = new TableColumn<>("Taste");
        tasteColumn.setCellValueFactory(new PropertyValueFactory<>("taste"));
        nicotineContentColumn = new TableColumn<>("Nicotine Content");
        nicotineContentColumn.setCellValueFactory(new PropertyValueFactory<>("nicotineContent"));
        purchasePriceColumn = new TableColumn<>("Purchase Price");
        purchasePriceColumn.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        marginColumn = new TableColumn<>("Margin");
        marginColumn.setCellValueFactory(new PropertyValueFactory<>("margin"));
        markingNumberColumn = new TableColumn<>("Marking Number");
        markingNumberColumn.setCellValueFactory(new PropertyValueFactory<>("markingNumber"));

        // Добавьте колонки в таблицу
        tvMain.getColumns().addAll(productIdColumn, productNameColumn, tasteColumn, nicotineContentColumn, purchasePriceColumn, marginColumn, markingNumberColumn);

        // Установите отфильтрованный список в таблицу
        tvMain.setItems(FXCollections.observableArrayList(filteredList));
    }

    @FXML
    private void joinSearch3() {
        String searchTerm = joinSearch.getText().toLowerCase();
        System.out.println(searchTerm);
        List<buy> filteredList;

        try {
            // Получите полный список данных
            filteredList = DataAccessor.getDataAccessor().getBuyListWithJoin3(searchTerm);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        for (buy item : filteredList) {
            System.out.println(item);
        }

        // Очистите существующие колонки таблицы
        tvMain.getColumns().clear();

        // Создайте колонки только для таблицы product
        productIdColumn = new TableColumn<>("Product ID");
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        salesIdIdColumn = new TableColumn<>("Sales ID");
        salesIdIdColumn.setCellValueFactory(new PropertyValueFactory<>("salesId"));
        quantityProdSoldColumn = new TableColumn<>("Quantity Prod Sold");
        quantityProdSoldColumn.setCellValueFactory(new PropertyValueFactory<>("quantityProdSold"));

        // Добавьте колонки в таблицу
        tvMain.getColumns().addAll(productIdColumn, salesIdIdColumn, quantityProdSoldColumn);

        // Установите отфильтрованный список в таблицу
        tvMain.setItems(FXCollections.observableArrayList(filteredList));
    }

    @FXML
    private void joinSearch4() {
        String searchTerm = joinSearch.getText().toLowerCase();
        System.out.println(searchTerm);
        List<buy> filteredList;

        try {
            // Получите полный список данных
            filteredList = DataAccessor.getDataAccessor().getBuyListWithJoin4(searchTerm);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        for (buy item : filteredList) {
            System.out.println(item);
        }

        // Очистите существующие колонки таблицы
        tvMain.getColumns().clear();

        loginColumn = new TableColumn<>("Login");
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        // Добавьте колонки в таблицу
        tvMain.getColumns().addAll(loginColumn, phoneColumn, lastNameColumn);

        // Установите отфильтрованный список в таблицу
        tvMain.setItems(FXCollections.observableArrayList(filteredList));
    }

    int index = -1;
    @FXML
    void getSelected(MouseEvent event) {
        index = tvMain.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }

        buy selectedBuy = tvMain.getItems().get(index);

        String currentTable = comb.getSelectionModel().getSelectedItem().toString();

        ItemChange1.clear();
        ItemChange2.clear();
        ItemChange3.clear();
        ItemChange4.clear();
        ItemChange5.clear();
        ItemChange6.clear();
        ItemChange7.clear();
        ItemChange8.clear();

        switch (currentTable) {
            case "product":
                // Обработка клика на строку в таблице "product"
                int productId = selectedBuy.getProductId();
                String productName = selectedBuy.getProductName();
                String taste = selectedBuy.getTaste();
                Double NicotineContent = selectedBuy.getNicotineContent();
                Double PurchasePrice = selectedBuy.getPurchasePrice();
                Double Margin = selectedBuy.getMargin();
                Long MarkingNumber = selectedBuy.getMarkingNumber();

                ItemChange1.setText(String.valueOf(productId));
                ItemChange2.setText(String.valueOf(productName));
                ItemChange3.setText(String.valueOf(taste));
                ItemChange4.setText(String.valueOf(NicotineContent));
                ItemChange5.setText(String.valueOf(PurchasePrice));
                ItemChange6.setText(String.valueOf(Margin));
                ItemChange7.setText(String.valueOf(MarkingNumber));
                break;
            case "sales_log":
                // Обработка клика на строку в таблице "sales_log"
                productId = selectedBuy.getProductId();
                int salesId = selectedBuy.getSalesId();
                int QuantityProdSold = selectedBuy.getQuantityProdSold();

                ItemChange1.setText(String.valueOf(productId));
                ItemChange2.setText(String.valueOf(salesId));
                ItemChange3.setText(String.valueOf(QuantityProdSold));
                break;
            case "supply_log":
                // Обработка клика на строку в таблице "supply_log"
                productId = selectedBuy.getProductId();
                int supplyId = selectedBuy.getSupplyId();
                int QuantityDeliveredProd = selectedBuy.getQuantityDeliveredProd();

                ItemChange1.setText(String.valueOf(productId));
                ItemChange2.setText(String.valueOf(supplyId));
                ItemChange3.setText(String.valueOf(QuantityDeliveredProd));
                break;
            case "buy":
                // Получение значений соответствующих полей из объекта buy
                salesId = selectedBuy.getSalesId();
                Date salesDate = selectedBuy.getSalesDate();
                int buyerId = selectedBuy.getBuyerId();

                // Установите значения в соответствующие поля textField
                ItemChange1.setText(String.valueOf(salesId));
                ItemChange2.setText(salesDate.toString());
                ItemChange3.setText(String.valueOf(buyerId));
                break;
            case "supply":
                // Получение значений соответствующих полей из объекта buy
                supplyId = selectedBuy.getSupplyId();
                Date deliveryDate = selectedBuy.getDeliveryDate();
                int providerId = selectedBuy.getProviderId();

                // Установите значения в соответствующие поля textField
                ItemChange1.setText(String.valueOf(supplyId));
                ItemChange2.setText(deliveryDate.toString());
                ItemChange3.setText(String.valueOf(providerId));
                break;
            case "buyer":
                // Обработка клика на строку в таблице "buyer"
                buyerId = selectedBuy.getBuyerId();
                String login = selectedBuy.getLogin();
                String password = selectedBuy.getPassword();
                String firstName = selectedBuy.getFirstName();
                String lastName = selectedBuy.getLastName();
                String patronymic = selectedBuy.getPatronymic();
                String email = selectedBuy.getEmail();
                String phone = selectedBuy.getPhone();

                // Установите значения в соответствующие поля textField
                ItemChange1.setText(String.valueOf(buyerId));
                ItemChange2.setText(String.valueOf(login));
                ItemChange3.setText(String.valueOf(password));
                ItemChange4.setText(String.valueOf(firstName));
                ItemChange5.setText(String.valueOf(lastName));
                ItemChange6.setText(String.valueOf(patronymic));
                ItemChange7.setText(String.valueOf(email));
                ItemChange8.setText(String.valueOf(phone));
                break;
            case "provider":
                // Обработка клика на строку в таблице "provider"
                providerId = selectedBuy.getProviderId();
                String supplierName = selectedBuy.getSupplierName();
                String paymentAccount = selectedBuy.getPaymentAccount();
                String adress = selectedBuy.getAdress();

                ItemChange1.setText(String.valueOf(providerId));
                ItemChange2.setText(String.valueOf(supplierName));
                ItemChange3.setText(String.valueOf(paymentAccount));
                ItemChange4.setText(String.valueOf(adress));
                break;
        }

    }

    @FXML
    void handleBtnChangeAction(ActionEvent event) throws ParseException {
        String currentTable = comb.getSelectionModel().getSelectedItem().toString();

        switch (currentTable) {
            case "product":
                // Обработка изменения строки в таблице "product"
                int productId = Integer.parseInt(ItemChange1.getText());
                String productName = ItemChange2.getText();
                String taste = ItemChange3.getText();
                double nicotineContent = Double.parseDouble(ItemChange4.getText());
                double purchasePrice = Double.parseDouble(ItemChange5.getText());
                double margin = Double.parseDouble(ItemChange6.getText());
                long markingNumber = Long.parseLong(ItemChange7.getText());

                // Изменение значений выбранной строки в таблице "product"
                modifyProduct(productId, productName, taste, nicotineContent, purchasePrice, margin, markingNumber);
                break;
            case "sales_log":
                // Обработка изменения строки в таблице "sales_log"
                productId = Integer.parseInt(ItemChange1.getText());
                int salesId = Integer.parseInt(ItemChange2.getText());
                int quantityProdSold = Integer.parseInt(ItemChange3.getText());

                // Изменение значений выбранной строки в таблице "sales_log"
                modifySalesLog(productId, salesId, quantityProdSold);
                break;

            case "supply_log":
                // Обработка изменения строки в таблице "supply_log"
                productId = Integer.parseInt(ItemChange1.getText());
                int supplyId = Integer.parseInt(ItemChange2.getText());
                int quantityDeliveredProd = Integer.parseInt(ItemChange3.getText());

                // Изменение значений выбранной строки в таблице "supply_log"
                modifySupplyLog(productId, supplyId, quantityDeliveredProd);
                break;

            case "buyer":
                // Обработка изменения строки в таблице "buy"
                int buyerId = Integer.parseInt(ItemChange1.getText());
                String login = ItemChange2.getText();
                String password = ItemChange3.getText();
                String firstName = ItemChange4.getText();
                String lastName = ItemChange5.getText();
                String patronymic = ItemChange6.getText();
                String email = ItemChange7.getText();
                String phone = ItemChange8.getText();

                // Изменение значений выбранной строки в таблице "buy"
                modifyBuyer(buyerId, login, password, firstName, lastName, patronymic, email, phone);
                break;
            // Добавьте обработку изменения строк в остальные таблицы
            case "provider":
                // Обработка изменения строки в таблице "buy"
                int providerId = Integer.parseInt(ItemChange1.getText());
                String supplierName = ItemChange2.getText();
                String paymentAccount = ItemChange3.getText();
                String adress = ItemChange4.getText();

                // Изменение значений выбранной строки в таблице "buy"
                modifyProvider(providerId, supplierName, paymentAccount, adress);
                break;
            case "buy":
                // Обработка изменения строки в таблице "sales_log"
                salesId = Integer.parseInt(ItemChange1.getText());

                String salesDateStr = ItemChange2.getText();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date salesDate = null;
                salesDate = dateFormat.parse(salesDateStr);

                buyerId = Integer.parseInt(ItemChange3.getText());

                // Изменение значений выбранной строки в таблице "sales_log"
                modifyBuy(salesId, salesDate, buyerId);
                break;
            case "supply":
                // Обработка изменения строки в таблице "sales_log"
                supplyId = Integer.parseInt(ItemChange1.getText());

                String deliveryDateStr = ItemChange2.getText();
                DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                Date deliveryDate = null;
                deliveryDate = dateFormat1.parse(deliveryDateStr);

                providerId = Integer.parseInt(ItemChange3.getText());

                // Изменение значений выбранной строки в таблице "sales_log"
                modifySupply(supplyId, deliveryDate, providerId);
                break;
            default:
                break;
        }
    }

    private void modifyProduct(int productId, String productName, String taste, double nicotineContent, double purchasePrice, double margin, long markingNumber) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/tabakmarket", "postgres", "Ytrewq11!");

            String updateSql = "UPDATE product SET product_name = ?, taste = ?, nicotine_content = ?, purchase_price = ?, margin = ?, marking_number = ? WHERE product_id = ?";
            PreparedStatement statement = conn.prepareStatement(updateSql);

            statement.setString(1, productName);
            statement.setString(2, taste);
            statement.setDouble(3, nicotineContent);
            statement.setDouble(4, purchasePrice);
            statement.setDouble(5, margin);
            statement.setLong(6, markingNumber);
            statement.setInt(7, productId);

            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " rows affected");

            DataAccessor da = DataAccessor.getDataAccessor();
            List<buy> buyList = da.getBuyList();
            // Обновляем таблицу
            ObservableList<buy> updatedData = FXCollections.observableArrayList(buyList);
            tvMain.setItems(updatedData);

            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void modifySalesLog(int productId, int salesId, int quantityProdSold) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/tabakmarket", "postgres", "Ytrewq11!");

            String updateSql = "UPDATE sales_log SET quantity_prod_sold = ? WHERE product_id = ? AND sales_id = ?";
            PreparedStatement statement = conn.prepareStatement(updateSql);

            statement.setInt(1, quantityProdSold);
            statement.setInt(2, productId);
            statement.setInt(3, salesId);

            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " rows affected");

            DataAccessor da = DataAccessor.getDataAccessor();
            List<buy> buyList = da.getBuyList();
            // Обновляем таблицу
            ObservableList<buy> updatedData = FXCollections.observableArrayList(buyList);
            tvMain.setItems(updatedData);

            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void modifySupplyLog(int productId, int supplyId, int quantityDeliveredProd) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/tabakmarket", "postgres", "Ytrewq11!");

            String updateSql = "UPDATE supply_log SET quantity_delivered_prod = ? WHERE product_id = ? AND supply_id = ?";
            PreparedStatement statement = conn.prepareStatement(updateSql);

            statement.setInt(1, quantityDeliveredProd);
            statement.setInt(2, productId);
            statement.setInt(3, supplyId);

            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " rows affected");

            DataAccessor da = DataAccessor.getDataAccessor();
            List<buy> buyList = da.getBuyList();
            // Обновляем таблицу
            ObservableList<buy> updatedData = FXCollections.observableArrayList(buyList);
            tvMain.setItems(updatedData);

            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void modifyBuyer(int buyerId, String login, String password, String firstName, String lastName, String patronymic, String email, String phone) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/tabakmarket", "postgres", "Ytrewq11!");

            String updateSql = "UPDATE buyer SET login = ?, password = ?, first_name = ?, last_name = ?, patronymic = ?, email = ?, phone = ? WHERE buyer_id = ?";
            PreparedStatement statement = conn.prepareStatement(updateSql);

            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setString(5, patronymic);
            statement.setString(6, email);
            statement.setString(7, phone);
            statement.setInt(8, buyerId);

            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " rows affected");

            DataAccessor da = DataAccessor.getDataAccessor();
            List<buy> buyList = da.getBuyList();
            // Обновляем таблицу
            ObservableList<buy> updatedData = FXCollections.observableArrayList(buyList);
            tvMain.setItems(updatedData);

            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void modifyProvider(int providerId, String supplierName, String paymentAccount, String adress) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/tabakmarket", "postgres", "Ytrewq11!");

            String updateSql = "UPDATE provider SET supplier_name = ?, payment_account = ?, adress = ? WHERE provider_id = ?";
            PreparedStatement statement = conn.prepareStatement(updateSql);

            statement.setString(1, supplierName);
            statement.setString(2, paymentAccount);
            statement.setString(3, adress);
            statement.setInt(4, providerId);

            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " rows affected");

            DataAccessor da = DataAccessor.getDataAccessor();
            List<buy> buyList = da.getBuyList();
            // Обновляем таблицу
            ObservableList<buy> updatedData = FXCollections.observableArrayList(buyList);
            tvMain.setItems(updatedData);

            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void modifyBuy(int salesId, Date salesDate, int buyerId) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/tabakmarket", "postgres", "Ytrewq11!");

            String updateSql = "UPDATE buy SET sales_date = ?, buyer_id = ? WHERE sales_id = ?";
            PreparedStatement statement = conn.prepareStatement(updateSql);

            statement.setDate(1, new java.sql.Date(salesDate.getTime()));
            statement.setInt(2, buyerId);
            statement.setInt(3, salesId);

            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " rows affected");

            DataAccessor da = DataAccessor.getDataAccessor();
            List<buy> buyList = da.getBuyList();
            // Обновляем таблицу
            ObservableList<buy> updatedData = FXCollections.observableArrayList(buyList);
            tvMain.setItems(updatedData);

            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void modifySupply(int supplyId, Date deliveryDate, int providerId) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/tabakmarket", "postgres", "Ytrewq11!");

            String updateSql = "UPDATE supply SET delivery_date = ?, provider_id = ? WHERE supply_id = ?";
            PreparedStatement statement = conn.prepareStatement(updateSql);

            statement.setDate(1, new java.sql.Date(deliveryDate.getTime()));
            statement.setInt(2, providerId);
            statement.setInt(3, supplyId);

            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " rows affected");

            DataAccessor da = DataAccessor.getDataAccessor();
            List<buy> buyList = da.getBuyList();
            // Обновляем таблицу
            ObservableList<buy> updatedData = FXCollections.observableArrayList(buyList);
            tvMain.setItems(updatedData);

            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBtnDeleteAction(ActionEvent event) {
        // Получите выбранную строку из таблицы
        String s = comb.getSelectionModel().getSelectedItem().toString();
        dataAccessor.setCurrentTable(s);
        buy selectedBuy = tvMain.getSelectionModel().getSelectedItem();
        if (selectedBuy != null) {
            // Выполните удаление выбранной строки из базы данных
            try {
                DataAccessor da = DataAccessor.getDataAccessor();
                da.deleteBuy(selectedBuy);
                // Удаление успешно выполнено
                // Обновите таблицу, чтобы отобразить изменения
                tvMain.getItems().remove(selectedBuy);
            } catch (SQLException e) {
                e.printStackTrace();
                // Обработка ошибки удаления
            }
        } else {
            // Сообщение об ошибке: не выбрана строка для удаления
        }
    }

    @FXML
    void initialize()  {
        TableColumn<buy, Integer> productIdColumn = new TableColumn<>("Product ID");
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));

        TableColumn<buy, String> productNameColumn = new TableColumn<>("Product Name");
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));

        TableColumn<buy, String> tasteColumn = new TableColumn<>("Taste");
        tasteColumn.setCellValueFactory(new PropertyValueFactory<>("taste"));

        TableColumn<buy, Integer> nicotineContentColumn = new TableColumn<>("Nicotine Content");
        nicotineContentColumn.setCellValueFactory(new PropertyValueFactory<>("nicotineContent"));

        TableColumn<buy, Float> purchasePriceColumn = new TableColumn<>("Purchase Price");
        purchasePriceColumn.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));

        TableColumn<buy, Float> marginColumn = new TableColumn<>("Margin");
        marginColumn.setCellValueFactory(new PropertyValueFactory<>("margin"));

        TableColumn<buy, Long> markingNumberColumn = new TableColumn<>("Marking Number");
        markingNumberColumn.setCellValueFactory(new PropertyValueFactory<>("markingNumber"));

        tvMain.getColumns().addAll(productIdColumn, productNameColumn, tasteColumn, nicotineContentColumn, purchasePriceColumn, marginColumn, markingNumberColumn);
    }


}

