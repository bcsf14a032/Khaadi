package com.example.lenovo.khaadi.Models;

/**
 * Created by abc on 1/20/18.
 *
 * @package pk.edu.pucit.mobilecomputing.database.Models
 * @project Database
 */

public class DressInfo {
    private String dcode;
    private String drtype;
    private String category;
    private int quantity;
    private int id;

    public DressInfo(int id, String dcode, String dtype, String category, int quantity) {
        setId(id);
        setDcode(dcode);
        setDtype(dtype);
        setCategory(category);
        setQuantity(quantity);
    }

    public String getDcoe() {
        return dcode;
    }

    public void setDcode(String dcode) {
        this.dcode = dcode;
    }

    public String getDtype() {
        return drtype;
    }

    public void setDtype(String dtpe) {
        this.drtype = dtpe;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
