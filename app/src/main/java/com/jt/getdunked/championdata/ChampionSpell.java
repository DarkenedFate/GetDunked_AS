
package com.jt.getdunked.championdata;

import java.util.ArrayList;
import java.util.List;


public class ChampionSpell {

    
    private String name;
    
    private String description;
    
    private String sanitizedDescription;
    
    private String tooltip;
    
    private String sanitizedTooltip;
    
    private LevelTip leveltip;
    
    private Image image;
    
    private String resource;
    
    private Integer maxrank;
    
    private List<Integer> cost = new ArrayList<Integer>();
    
    private String costType;
    
    private String costBurn;
    
    private List<Integer> cooldown = new ArrayList<Integer>();
    
    private String cooldownBurn;
    
    private List<List<Integer>> effect = new ArrayList<List<Integer>>();
    
    private List<String> effectBurn = new ArrayList<String>();
    
    private List<SpellVars> vars = new ArrayList<SpellVars>();
    
    private List<Integer> range = new ArrayList<Integer>();
    
    private String rangeBurn;
    
    private String key;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSanitizedDescription() {
        return sanitizedDescription;
    }

    public void setSanitizedDescription(String sanitizedDescription) {
        this.sanitizedDescription = sanitizedDescription;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public String getSanitizedTooltip() {
        return sanitizedTooltip;
    }

    public void setSanitizedTooltip(String sanitizedTooltip) {
        this.sanitizedTooltip = sanitizedTooltip;
    }

    public LevelTip getLeveltip() {
        return leveltip;
    }

    public void setLeveltip(LevelTip leveltip) {
        this.leveltip = leveltip;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public Integer getMaxrank() {
        return maxrank;
    }

    public void setMaxrank(Integer maxrank) {
        this.maxrank = maxrank;
    }

    public List<Integer> getCost() {
        return cost;
    }

    public void setCost(List<Integer> cost) {
        this.cost = cost;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public String getCostBurn() {
        return costBurn;
    }

    public void setCostBurn(String costBurn) {
        this.costBurn = costBurn;
    }

    public List<Integer> getCooldown() {
        return cooldown;
    }

    public void setCooldown(List<Integer> cooldown) {
        this.cooldown = cooldown;
    }

    public String getCooldownBurn() {
        return cooldownBurn;
    }

    public void setCooldownBurn(String cooldownBurn) {
        this.cooldownBurn = cooldownBurn;
    }

    public List<List<Integer>> getEffect() {
        return effect;
    }

    public void setEffect(List<List<Integer>> effect) {
        this.effect = effect;
    }

    public List<String> getEffectBurn() {
        return effectBurn;
    }

    public void setEffectBurn(List<String> effectBurn) {
        this.effectBurn = effectBurn;
    }

    public List<SpellVars> getSpellVarss() {
        return vars;
    }

    public void setSpellVarss(List<SpellVars> vars) {
        this.vars = vars;
    }

    public List<Integer> getRange() {
        return range;
    }

    public void setRange(List<Integer> range) {
        this.range = range;
    }

    public String getRangeBurn() {
        return rangeBurn;
    }

    public void setRangeBurn(String rangeBurn) {
        this.rangeBurn = rangeBurn;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
