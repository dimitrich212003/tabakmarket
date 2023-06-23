package com.example.tabakjavafx;

import java.sql.Date;

public class buy {
    private int salesId;
    private Date salesDate;
    private int buyerId;

    private int productId;
    private String productName;
    private String taste;
    private double nicotineContent;
    private double purchasePrice;
    private double margin;
    private Long markingNumber;

    private int quantityProdSold;
    private int quantityDeliveredProd;

    private int supplyId;
    private Date deliveryDate;
    private int providerId;

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String phone;

    private String supplierName;
    private String paymentAccount;
    private String adress;



    public buy(int productId, String productName, String taste, double nicotineContent, double purchasePrice, double margin, Long markingNumber) {
        this.productId = productId;
        this.productName = productName;
        this.taste = taste;
        this.nicotineContent = nicotineContent;
        this.purchasePrice = purchasePrice;
        this.margin = margin;
        this.markingNumber = markingNumber;
    }

    public buy(int productId, int salesId, int quantityProdSold) {
        this.productId = productId;
        this.salesId = salesId;
        this.quantityProdSold = quantityProdSold;
    }

    public buy(int salesId, Date salesDate, int buyerId) {
        this.salesId = salesId;
        this.salesDate = salesDate;
        this.buyerId = buyerId;
    }

    public buy(int supplyId, Date deliveryDate, int providerId, boolean isSupply) {
        this.supplyId = supplyId;
        this.deliveryDate = deliveryDate;
        this.providerId = providerId;
    }
    public buy(int productId, int supplyId, int quantityDeliveredProd, boolean isSupply) {
        this.productId = productId;
        this.supplyId = supplyId;
        this.quantityDeliveredProd = quantityDeliveredProd;
    }

    public buy(int buyerId, String login, String password,String firstName, String lastName, String patronymic, String email, String phone) {
        this.buyerId = buyerId;
        this.password = password;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
    }

    public buy(int providerId, String supplierName, String paymentAccount, String adress) {
        this.providerId = providerId;
        this.supplierName = supplierName;
        this.paymentAccount = paymentAccount;
        this.adress = adress;
    }

    public buy(String login, String phone, String lastName) {
        this.login = login;
        this.lastName = lastName;
        this.phone = phone;
    }

    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

    public Date getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(int supplyId) {
        this.supplyId = supplyId;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public double getNicotineContent() {
        return nicotineContent;
    }

    public void setNicotineContent(double nicotineContent) {
        this.nicotineContent = nicotineContent;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getMargin() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    public Long getMarkingNumber() {
        return markingNumber;
    }

    public void setMarkingNumber(Long markingNumber) {
        this.markingNumber = markingNumber;
    }

    public int getQuantityProdSold() {
        return quantityProdSold;
    }

    public void setQuantityProdSold(int quantityProdSold) {
        this.quantityProdSold = quantityProdSold;
    }

    public int getQuantityDeliveredProd() {
        return quantityDeliveredProd;
    }

    public void setQuantityDeliveredPro(int quantityDeliveredProd) {
        this.quantityDeliveredProd = quantityDeliveredProd;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "buy{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", taste='" + taste + '\'' +
                ", nicotineContent=" + nicotineContent +
                ", purchasePrice=" + purchasePrice +
                ", margin=" + margin +
                ", markingNumber=" + markingNumber +
                '}';
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
