
package com.jt.getdunked.championdata;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class ChampSpell {

    @Expose
    private Integer id;
    @Expose
    private String key;
    @Expose
    private String name;
    @Expose
    private String title;
    @Expose
    private List<Spell> spells = new ArrayList<Spell>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }

}
