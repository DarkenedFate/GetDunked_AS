
package com.jt.getdunked.championdata;

import java.util.ArrayList;
import java.util.List;


public class Block {

    
    private String type;
    
    private List<Item> items = new ArrayList<Item>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
