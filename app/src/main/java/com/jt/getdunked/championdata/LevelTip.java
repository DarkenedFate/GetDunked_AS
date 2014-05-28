
package com.jt.getdunked.championdata;

import java.util.ArrayList;
import java.util.List;


public class LevelTip {

    
    private List<String> label = new ArrayList<String>();
    
    private List<String> effect = new ArrayList<String>();

    public List<String> getLabel() {
        return label;
    }

    public void setLabel(List<String> label) {
        this.label = label;
    }

    public List<String> getEffect() {
        return effect;
    }

    public void setEffect(List<String> effect) {
        this.effect = effect;
    }

}
