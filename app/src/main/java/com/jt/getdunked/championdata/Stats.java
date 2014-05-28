
package com.jt.getdunked.championdata;

import java.io.Serializable;


public class Stats implements Serializable {

	private static final long serialVersionUID = -7577411985860317808L;
	private Number armor;
   	private Number armorperlevel;
   	private Number attackdamage;
   	private Number attackdamageperlevel;
   	private Number attackrange;
   	private Number attackspeedoffset;
   	private Number attackspeedperlevel;
   	private Number crit;
   	private Number critperlevel;
   	private Number hp;
   	private Number hpperlevel;
   	private Number hpregen;
   	private Number hpregenperlevel;
   	private Number movespeed;
   	private Number mp;
   	private Number mpperlevel;
   	private Number mpregen;
   	private Number mpregenperlevel;
   	private Number spellblock;
   	private Number spellblockperlevel;

 	public Number getArmor(){
		return this.armor;
	}
	public void setArmor(Number armor){
		this.armor = armor;
	}
 	public Number getArmorperlevel(){
		return this.armorperlevel;
	}
	public void setArmorperlevel(Number armorperlevel){
		this.armorperlevel = armorperlevel;
	}
 	public Number getAttackdamage(){
		return this.attackdamage;
	}
	public void setAttackdamage(Number attackdamage){
		this.attackdamage = attackdamage;
	}
 	public Number getAttackdamageperlevel(){
		return this.attackdamageperlevel;
	}
	public void setAttackdamageperlevel(Number attackdamageperlevel){
		this.attackdamageperlevel = attackdamageperlevel;
	}
 	public Number getAttackrange(){
		return this.attackrange;
	}
	public void setAttackrange(Number attackrange){
		this.attackrange = attackrange;
	}
 	public Number getAttackspeedoffset(){
		return this.attackspeedoffset;
	}
	public void setAttackspeedoffset(Number attackspeedoffset){
		this.attackspeedoffset = attackspeedoffset;
	}
 	public Number getAttackspeedperlevel(){
		return this.attackspeedperlevel;
	}
	public void setAttackspeedperlevel(Number attackspeedperlevel){
		this.attackspeedperlevel = attackspeedperlevel;
	}
 	public Number getCrit(){
		return this.crit;
	}
	public void setCrit(Number crit){
		this.crit = crit;
	}
 	public Number getCritperlevel(){
		return this.critperlevel;
	}
	public void setCritperlevel(Number critperlevel){
		this.critperlevel = critperlevel;
	}
 	public Number getHp(){
		return this.hp;
	}
	public void setHp(Number hp){
		this.hp = hp;
	}
 	public Number getHpperlevel(){
		return this.hpperlevel;
	}
	public void setHpperlevel(Number hpperlevel){
		this.hpperlevel = hpperlevel;
	}
 	public Number getHpregen(){
		return this.hpregen;
	}
	public void setHpregen(Number hpregen){
		this.hpregen = hpregen;
	}
 	public Number getHpregenperlevel(){
		return this.hpregenperlevel;
	}
	public void setHpregenperlevel(Number hpregenperlevel){
		this.hpregenperlevel = hpregenperlevel;
	}
 	public Number getMovespeed(){
		return this.movespeed;
	}
	public void setMovespeed(Number movespeed){
		this.movespeed = movespeed;
	}
 	public Number getMp(){
		return this.mp;
	}
	public void setMp(Number mp){
		this.mp = mp;
	}
 	public Number getMpperlevel(){
		return this.mpperlevel;
	}
	public void setMpperlevel(Number mpperlevel){
		this.mpperlevel = mpperlevel;
	}
 	public Number getMpregen(){
		return this.mpregen;
	}
	public void setMpregen(Number mpregen){
		this.mpregen = mpregen;
	}
 	public Number getMpregenperlevel(){
		return this.mpregenperlevel;
	}
	public void setMpregenperlevel(Number mpregenperlevel){
		this.mpregenperlevel = mpregenperlevel;
	}
 	public Number getSpellblock(){
		return this.spellblock;
	}
	public void setSpellblock(Number spellblock){
		this.spellblock = spellblock;
	}
 	public Number getSpellblockperlevel(){
		return this.spellblockperlevel;
	}
	public void setSpellblockperlevel(Number spellblockperlevel){
		this.spellblockperlevel = spellblockperlevel;
	}
}
