package com.jt.getdunked.championdata;

import java.io.Serializable;

public class Info implements Serializable {

	private static final long serialVersionUID = -5546970491543701060L;
	private Number attack;
	private Number defense;
	private Number difficulty;
	private Number magic;

	public static class Builder {
		// required

		private int attack = 0;
		private int defense = 0;
		private int difficulty = 0;
		private int magic = 0;

		public Builder setAttack(int value) {
			attack = value;
			return this;
		}

		public Builder setDefense(int value) {
			defense = value;
			return this;
		}

		public Builder setDifficulty(int value) {
			difficulty = value;
			return this;
		}

		public Builder setMagic(int value) {
			magic = value;
			return this;
		}

		public Info build() {
			return new Info(this);
		}
	}

	private Info(Builder builder) {
		attack = builder.attack;
		defense = builder.defense;
		difficulty = builder.difficulty;
		magic = builder.magic;
	}

	public Number getAttack() {
		return this.attack;
	}

	public void setAttack(Number attack) {
		this.attack = attack;
	}

	public Number getDefense() {
		return this.defense;
	}

	public void setDefense(Number defense) {
		this.defense = defense;
	}

	public Number getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(Number difficulty) {
		this.difficulty = difficulty;
	}

	public Number getMagic() {
		return this.magic;
	}

	public void setMagic(Number magic) {
		this.magic = magic;
	}
}
