
package com.jt.getdunked.championdata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class Spell implements Serializable {

	private static final long serialVersionUID = -2151622478016815676L;
	@Expose
    private String name;
    @Expose
    private String description;
    @Expose
    private String sanitizedDescription;
    @Expose
    private String tooltip;
    @Expose
    private String sanitizedTooltip;
    @Expose
    private LevelTip leveltip;
    @Expose
    private Image image;
    @Expose
    private String resource;
    @Expose
    private Integer maxrank;
    @Expose
    private List<Integer> cost = new ArrayList<Integer>();
    @Expose
    private String costType;
    @Expose
    private String costBurn;
    @Expose
    private List<Double> cooldown = new ArrayList<Double>();
    @Expose
    private String cooldownBurn;
    @Expose
    private List<List<Integer>> effect = new ArrayList<List<Integer>>();
    @Expose
    private List<String> effectBurn = new ArrayList<String>();
    @Expose
    private List<Var> vars = new ArrayList<Var>();
//    @Expose
//    private String range;
    @Expose
    private String rangeBurn;
    @Expose
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

    public LevelTip getLevelTip() {
        return leveltip;
    }

    public void setLevelTip(LevelTip leveltip) {
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

    public List<Double> getCooldown() {
        return cooldown;
    }

    public void setCooldown(List<Double> cooldown) {
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

    public List<Var> getVars() {
        return vars;
    }

    public void setVars(List<Var> vars) {
        this.vars = vars;
    }

//    public String getRange() {
//        return range;
//    }
//
//    public void setRange(String range) {
//        this.range = range;
//    }

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
