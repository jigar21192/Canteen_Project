package com.example.jp.reclwviewdemo.model;

public class DataModel {
    int id;
    String ItemName,ItemPrice,Quantity,Total,amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    Boolean isSelected;

    public int getId() {
        return id;
    }
    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(String itemPrice) {
        ItemPrice = itemPrice;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }
}
