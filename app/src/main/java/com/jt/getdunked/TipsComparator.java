package com.jt.getdunked;

import java.util.Comparator;

import com.jt.getdunked.championdata.Champion;

/**
 * Custom Comparator implementation. Sorts cards either alphabetically, by mana
 * cost, by health, by attack, or by rarity.
 * 
 * @author JT
 */
public class TipsComparator implements Comparator<Champion> {

	public int compare(Champion left, Champion right) {
		return left.getName().toString().compareTo(right.getName().toString());

	}
}