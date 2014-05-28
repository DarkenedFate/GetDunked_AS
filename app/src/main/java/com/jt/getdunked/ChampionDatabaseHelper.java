package com.jt.getdunked;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jt.getdunked.championdata.Blocks;
import com.jt.getdunked.championdata.Champion;
import com.jt.getdunked.championdata.Info;
import com.jt.getdunked.championdata.Item;
import com.jt.getdunked.championdata.Passive;
import com.jt.getdunked.championdata.Recommended;
import com.jt.getdunked.championdata.Skins;
import com.jt.getdunked.championdata.Spell;
import com.jt.getdunked.championdata.Stats;
import com.jt.getdunked.championdata.Var;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChampionDatabaseHelper extends SQLiteAssetHelper {

	private static final int DATABASE_VERSION = 31;
	public static final String DATABASE_NAME = "championManager";

	private static final String TABLE_CHAMPIONS = "champions";
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_TITLE = "title";
	private static final String KEY_TAGS = "tags";
	private static final String KEY_KEY = "key";
	private static final String KEY_BLURB = "blurb";
	private static final String KEY_LORE = "lore";
	private static final String KEY_RESOURCE = "partype";

	private static final String TABLE_TIPS = "tips";
	private static final String KEY_ALLYTIPS = "allyTips";
	private static final String KEY_ENEMYTIPS = "enemyTips";

	private static final String TABLE_INFO = "info";
	private static final String KEY_DEFENSE = "defense";
	private static final String KEY_DIFFICULTY = "difficulty";
	private static final String KEY_ATTACK = "attack";
	private static final String KEY_MAGIC = "magic";

	private static final String TABLE_SKINS = "skins";
	private static final String KEY_SKIN_ID = "skinId";
	private static final String KEY_SKIN_NAME = "skinName";

	private static final String TABLE_PASSIVE = "passive";
	private static final String KEY_PASSIVE_DESCRIPTION = "sanitizedDescription";
	private static final String KEY_PASSIVE_NAME = "passiveName";

	private static final String TABLE_RECOMMENDED = "recommended";
	private static final String KEY_MAP = "map";
	private static final String KEY_MODE = "mode";
	private static final String KEY_STARTING_0 = "starting_0";
	private static final String KEY_STARTING_1 = "starting_1";
	private static final String KEY_STARTING_2 = "starting_2";
	private static final String KEY_STARTING_3 = "starting_3";
	private static final String KEY_STARTING_4 = "starting_4";
	private static final String KEY_ESSENTIAL_0 = "essential_0";
	private static final String KEY_ESSENTIAL_1 = "essential_1";
	private static final String KEY_ESSENTIAL_2 = "essential_2";
	private static final String KEY_ESSENTIAL_3 = "essential_3";
	private static final String KEY_ESSENTIAL_4 = "essential_4";
	private static final String KEY_OFFENSIVE_0 = "offensive_0";
	private static final String KEY_OFFENSIVE_1 = "offensive_1";
	private static final String KEY_OFFENSIVE_2 = "offensive_2";
	private static final String KEY_OFFENSIVE_3 = "offensive_3";
	private static final String KEY_OFFENSIVE_4 = "offensive_4";
	private static final String KEY_DEFENSIVE_0 = "defensive_0";
	private static final String KEY_DEFENSIVE_1 = "defensive_1";
	private static final String KEY_DEFENSIVE_2 = "defensive_2";
	private static final String KEY_DEFENSIVE_3 = "defensive_3";
	private static final String KEY_DEFENSIVE_4 = "defensive_4";

	private static final String TABLE_SPELLS = "spells";
	private static final String KEY_SPELL_NAME = "spellName";
	private static final String KEY_DESCRIPTION = "sanitizedDescription";
	private static final String KEY_TOOLTIP = "sanitizedTooltip";
	private static final String KEY_COOLDOWNBURN = "cooldownBurn";
	private static final String KEY_EFFECTBURN = "effectBurn";
	private static final String KEY_COSTBURN = "costBurn";
	private static final String KEY_RANGEBURN = "rangeBurn";
	private static final String KEY_SPELL_RESOURCE = "resource";
	private static final String KEY_VARKEY_0 = "varKey_0";
	private static final String KEY_VARKEY_1 = "varKey_1";
	private static final String KEY_VARKEY_2 = "varKey_2";
	private static final String KEY_VARKEY_3 = "varKey_3";
	private static final String KEY_VARKEY_4 = "varKey_4";
	private static final String KEY_VARLINK_0 = "varLink_0";
	private static final String KEY_VARLINK_1 = "varLink_1";
	private static final String KEY_VARLINK_2 = "varLink_2";
	private static final String KEY_VARLINK_3 = "varLink_3";
	private static final String KEY_VARLINK_4 = "varLink_4";
	private static final String KEY_VARCOEFF_0 = "varCoeff_0";
	private static final String KEY_VARCOEFF_1 = "varCoeff_1";
	private static final String KEY_VARCOEFF_2 = "varCoeff_2";
	private static final String KEY_VARCOEFF_3 = "varCoeff_3";
	private static final String KEY_VARCOEFF_4 = "varCoeff_4";

	private static final String TABLE_STATS = "stats";
	private static final String KEY_ARMOR = "armor";
	private static final String KEY_ARMOR_PER_LEVEL = "armorPerLevel";
	private static final String KEY_ATTACK_RANGE = "attackRange";
	private static final String KEY_ATTACK_SPEED_OFFSET = "attackSpeedOffset";
	private static final String KEY_ATTACK_SPEED_PER_LEVEL = "attackSpeedPerLevel";
	private static final String KEY_ATTACK_DAMAGE = "attackDamage";
	private static final String KEY_ATTACK_DAMAGE_PER_LEVEL = "attackDamagePerLevel";
	private static final String KEY_CRIT = "crit";
	private static final String KEY_CRIT_PER_LEVEL = "critPerLevel";
	private static final String KEY_MP = "mp";
	private static final String KEY_MP_PER_LEVEl = "mpPerLevel";
	private static final String KEY_MP_REGEN = "mpRegen";
	private static final String KEY_MP_REGEN_PER_LEVEL = "mpRegenPerLevel";
	private static final String KEY_HP = "hitPoints";
	private static final String KEY_HP_PER_LEVEL = "hpPerLevel";
	private static final String KEY_HP_REGEN = "hpRegen";
	private static final String KEY_HP_REGEN_PER_LEVEL = "hpRegenPerLevel";
	private static final String KEY_MAGIC_RES = "spellBlock";
	private static final String KEY_MAGIC_RES_PER_LEVEL = "spellBlockPerLevel";
	private static final String KEY_MOVESPEED = "movespeed";

	public ChampionDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Splits a Champion object up into the correct tables
	public void addChampion(Champion champ) {

		SQLiteDatabase db = this.getWritableDatabase();

		addChampionBase(champ, db);
		addPassive(champ, db);
		addSkins(champ, db);
		addStats(champ, db);
		addRecommended(champ, db);
		addSpells(champ, db);
		addTips(champ, db);
		addInfo(champ, db);

		db.close();

	}

	public Champion getChampion(int id) {

		List<Skins> skinList = new ArrayList<Skins>();
		List<String> tipsList = new ArrayList<String>();
		List<String> enemyTipsList = new ArrayList<String>();
		List<Recommended> listRecommended = new ArrayList<Recommended>();
		List<Spell> listSpells = new ArrayList<Spell>();

		Passive passive = new Passive();
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor champCursor = db.rawQuery("SELECT * FROM " + TABLE_CHAMPIONS
				+ " WHERE " + KEY_ID + " = ?",
				new String[] { String.valueOf(id) });

		if (champCursor != null) {
			champCursor.moveToFirst();
		}

		Cursor tipsCursor = db.rawQuery("SELECT * FROM " + TABLE_TIPS
				+ " WHERE " + KEY_ID + " = ?",
				new String[] { String.valueOf(id) });

		if (tipsCursor.moveToFirst()) {
			tipsList = Arrays.asList(tipsCursor.getString(1).split("---"));
			enemyTipsList = Arrays.asList(tipsCursor.getString(2).split("---"));
		} else {
			Log.w("DATABASE HELPER", "tipsCursor couldn't move to first!");
		}

		Cursor passiveCursor = db.query(TABLE_PASSIVE, new String[] { KEY_ID,
				KEY_PASSIVE_DESCRIPTION, KEY_PASSIVE_NAME }, KEY_ID + " = ?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if (passiveCursor.moveToFirst()) {
			passive.setSanitizedDescription(passiveCursor.getString(1));
			passive.setName(passiveCursor.getString(2));
		} else {
			Log.w("DATABASE HELPER", "GET PASSIVE FAIL");
		}

		Cursor infoCursor = db.rawQuery("SELECT * FROM " + TABLE_INFO
				+ " WHERE " + KEY_ID + " = ?",
				new String[] { String.valueOf(id) });

		if (infoCursor != null) {
			infoCursor.moveToFirst();
		}

		Cursor skinsCursor = db.query(TABLE_SKINS, new String[] { KEY_ID,
				KEY_SKIN_ID, KEY_SKIN_NAME }, KEY_ID + " = ?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if (skinsCursor.moveToFirst()) {
			do {
				Skins skin = new Skins();
				skin.setId(Integer.valueOf(skinsCursor.getString(1)));
				skin.setName(skinsCursor.getString(2));

				skinList.add(skin);
			} while (skinsCursor.moveToNext());
		} else {
			Log.w("DATABASE HELPER", "skinsCursor couldn't move to first!");
		}

		Cursor recommendedCursor = db.query(TABLE_RECOMMENDED, new String[] {
				KEY_ID, KEY_MAP, KEY_TITLE, KEY_MODE, KEY_STARTING_0,
				KEY_STARTING_1, KEY_STARTING_2, KEY_STARTING_3, KEY_STARTING_4,
				KEY_ESSENTIAL_0, KEY_ESSENTIAL_1, KEY_ESSENTIAL_2,
				KEY_ESSENTIAL_3, KEY_ESSENTIAL_4, KEY_OFFENSIVE_0,
				KEY_OFFENSIVE_1, KEY_OFFENSIVE_2, KEY_OFFENSIVE_3,
				KEY_OFFENSIVE_4, KEY_DEFENSIVE_0, KEY_DEFENSIVE_1,
				KEY_DEFENSIVE_2, KEY_DEFENSIVE_3, KEY_DEFENSIVE_4 }, KEY_ID
				+ " = ?", new String[] { String.valueOf(id) }, null, null,
				null, null);

		if (recommendedCursor.moveToFirst()) {
			do {
				Recommended recommended = new Recommended();
				recommended.setMap(recommendedCursor.getString(1));
				recommended.setTitle(recommendedCursor.getString(2));
				recommended.setMode(recommendedCursor.getString(3));
				List<Blocks> listBlocks = new ArrayList<Blocks>();
				List<Item> listStartingItems = new ArrayList<Item>();
				List<Item> listEssentialItems = new ArrayList<Item>();
				List<Item> listDefensiveItems = new ArrayList<Item>();
				List<Item> listOffensiveItems = new ArrayList<Item>();

				for (int i = 0; i < 5; i++) {
					Item item = new Item();
					if (recommendedCursor.getString(i + 4) != null) {
						item.setId(Integer.parseInt(recommendedCursor
								.getString(i + 4)));
					}
					listStartingItems.add(item);
				}
				for (int i = 0; i < 5; i++) {
					Item item = new Item();
					if (recommendedCursor.getString(i + 9) != null) {
						item.setId(Integer.parseInt(recommendedCursor
								.getString(i + 9)));
					}
					listEssentialItems.add(item);
				}
				for (int i = 0; i < 5; i++) {
					Item item = new Item();
					if (recommendedCursor.getString(i + 14) != null) {
						item.setId(Integer.parseInt(recommendedCursor
								.getString(i + 14)));
					}
					listOffensiveItems.add(item);
				}
				for (int i = 0; i < 5; i++) {
					Item item = new Item();
					if (recommendedCursor.getString(i + 19) != null) {
						item.setId(Integer.parseInt(recommendedCursor
								.getString(i + 19)));
					}
					listDefensiveItems.add(item);
				}

				Blocks startingBlocks = new Blocks();
				startingBlocks.setItems(listStartingItems);
				startingBlocks.setType("starting");
				listBlocks.add(startingBlocks);

				Blocks essentialBlocks = new Blocks();
				essentialBlocks.setItems(listEssentialItems);
				essentialBlocks.setType("essential");
				listBlocks.add(essentialBlocks);

				Blocks defensiveBlocks = new Blocks();
				defensiveBlocks.setItems(listDefensiveItems);
				defensiveBlocks.setType("essential");
				listBlocks.add(defensiveBlocks);

				Blocks offensiveBlocks = new Blocks();
				offensiveBlocks.setItems(listOffensiveItems);
				offensiveBlocks.setType("essential");
				listBlocks.add(offensiveBlocks);

				recommended.setBlocks(listBlocks);
				listRecommended.add(recommended);

			} while (recommendedCursor.moveToNext());
		} else {
			Log.w("DATABASE HELPER",
					"recommendedCursor couldn't move to first!");
		}

		Cursor statsCursor = db.query(TABLE_STATS, new String[] { KEY_ID,
				KEY_ARMOR, KEY_ARMOR_PER_LEVEL, KEY_ATTACK_RANGE,
				KEY_ATTACK_SPEED_OFFSET, KEY_ATTACK_SPEED_PER_LEVEL,
				KEY_ATTACK_DAMAGE, KEY_ATTACK_DAMAGE_PER_LEVEL, KEY_CRIT,
				KEY_CRIT_PER_LEVEL, KEY_MP, KEY_MP_PER_LEVEl, KEY_MP_REGEN,
				KEY_MP_REGEN_PER_LEVEL, KEY_HP, KEY_HP_PER_LEVEL, KEY_HP_REGEN,
				KEY_HP_REGEN_PER_LEVEL, KEY_MAGIC_RES, KEY_MAGIC_RES_PER_LEVEL,
				KEY_MOVESPEED }, KEY_ID + " = ?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if (statsCursor != null) {
			statsCursor.moveToFirst();
		}

		Stats stats = new Stats();
		stats.setArmor(Double.parseDouble(statsCursor.getString(1)));
		stats.setArmorperlevel(Double.parseDouble(statsCursor.getString(2)));
		stats.setAttackrange(Double.parseDouble(statsCursor.getString(3)));
		stats.setAttackspeedoffset(Double.parseDouble(statsCursor.getString(4)));
		stats.setAttackspeedperlevel(Double.parseDouble(statsCursor
				.getString(5)));
		stats.setAttackdamage(Double.parseDouble(statsCursor.getString(6)));
		stats.setAttackdamageperlevel(Double.parseDouble(statsCursor
				.getString(7)));
		stats.setCrit(Double.parseDouble(statsCursor.getString(8)));
		stats.setCritperlevel(Double.parseDouble(statsCursor.getString(9)));
		stats.setMp(Double.parseDouble(statsCursor.getString(10)));
		stats.setMpperlevel(Double.parseDouble(statsCursor.getString(11)));
		stats.setMpregen(Double.parseDouble(statsCursor.getString(12)));
		stats.setMpregenperlevel(Double.parseDouble(statsCursor.getString(13)));
		stats.setHp(Double.parseDouble(statsCursor.getString(14)));
		stats.setHpperlevel(Double.parseDouble(statsCursor.getString(15)));
		stats.setHpregen(Double.parseDouble(statsCursor.getString(16)));
		stats.setHpregenperlevel(Double.parseDouble(statsCursor.getString(17)));
		stats.setSpellblock(Double.parseDouble(statsCursor.getString(18)));
		stats.setSpellblockperlevel(Double.parseDouble(statsCursor
				.getString(19)));
		stats.setMovespeed(Double.parseDouble(statsCursor.getString(20)));

		Cursor spellsCursor = db.rawQuery("SELECT * FROM " + TABLE_SPELLS
				+ " WHERE " + KEY_ID + " = ?",
				new String[] { String.valueOf(id) });

		if (spellsCursor.moveToFirst()) {
			do {
				Spell spell = new Spell();
				spell.setName(spellsCursor.getString(1));
				spell.setSanitizedDescription(spellsCursor.getString(2));
				spell.setSanitizedTooltip(spellsCursor.getString(3));
				spell.setCooldownBurn(spellsCursor.getString(4));
				spell.setEffectBurn(Arrays.asList(spellsCursor.getString(5)
						.split(",")));
				spell.setCostBurn(spellsCursor.getString(6));
				spell.setRangeBurn(spellsCursor.getString(7));
				spell.setResource(spellsCursor.getString(8));

				List<Var> varList = new ArrayList<Var>();
				for (int i = 0; i < 5; i++) {
					Var var = new Var();
					if (spellsCursor.getString(i + 9) != null) {
						var.setKey(spellsCursor.getString(i + 9));
					}
					if (spellsCursor.getString(i + 14) != null) {
						var.setLink(spellsCursor.getString(i + 14));
					}
					if (spellsCursor.getString(i + 19) != null) {
						var.setCoeff(Double.parseDouble(spellsCursor
								.getString(i + 19)));
					}
					varList.add(var);
				}

				spell.setVars(varList);

				listSpells.add(spell);
			} while (spellsCursor.moveToNext());
		}

		Champion champ = new Champion();

		champ.setId(Integer.parseInt(champCursor.getString(0)));
		champ.setTags(new ArrayList<String>(Arrays.asList(champCursor
				.getString(3).split(","))));
		champ.setKey(champCursor.getString(4));
		champ.setBlurb(champCursor.getString(5));
		champ.setLore(champCursor.getString(6));
		champ.setPartype(champCursor.getString(7));
		champ.setAllytips(tipsList);
		champ.setEnemytips(enemyTipsList);
		champ.setPassive(passive);
		champ.setSkins(skinList);
		champ.setInfo(new Info.Builder()
				.setDefense(Integer.parseInt(infoCursor.getString(1)))
				.setDifficulty(Integer.parseInt(infoCursor.getString(2)))
				.setAttack(Integer.parseInt(infoCursor.getString(3)))
				.setMagic(Integer.parseInt(infoCursor.getString(4))).build());
		champ.setName(champCursor.getString(1));
		champ.setRecommended(listRecommended);
		champ.setStats(stats);
		champ.setTitle(champCursor.getString(2));
		champ.setSpells(listSpells);

		// for (int i = 0; i < 5; i++) {
		// Log.w("SQL", cursor.getString(i));
		// }

		champCursor.close();
		tipsCursor.close();
		passiveCursor.close();
		statsCursor.close();
		recommendedCursor.close();
		skinsCursor.close();
		infoCursor.close();
		spellsCursor.close();

		db.close();

		return champ;
	}

	public Champion getChampionName(int id) {

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.rawQuery("SELECT " + KEY_NAME + ", " + KEY_KEY
				+ " FROM " + TABLE_CHAMPIONS + " WHERE " + KEY_ID + " = ?",
				new String[] { String.valueOf(id) });

		Champion champ = new Champion();
		if (cursor.moveToFirst()) {
			champ.setName(cursor.getString(0));
			champ.setKey(cursor.getString(1));
		}

		cursor.close();

		db.close();

		return champ;
	}

	public List<Champion> getAllChampions() {
		List<Champion> champList = new ArrayList<Champion>();

		String selectQuery = "SELECT * FROM " + TABLE_CHAMPIONS + " ORDER BY "
				+ KEY_ID;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Champion champion = new Champion();
				champion.setId(Integer.parseInt(cursor.getString(0)));
				champion.setKey(cursor.getString(4));
				champion.setName(cursor.getString(1));
				champion.setTitle(cursor.getString(2));
				champion.setBlurb(cursor.getString(5));

				Cursor spellsCursor = db.rawQuery("SELECT * FROM "
						+ TABLE_SPELLS + " WHERE " + KEY_ID + " = ?",
						new String[] { cursor.getString(0) });

				List<Spell> spells = new ArrayList<Spell>();
				if (spellsCursor.moveToFirst()) {
					do {
						Spell spell = new Spell();
						spell.setName(spellsCursor.getString(1));
						spells.add(spell);
					} while (spellsCursor.moveToNext());
				}
				champion.setSpells(spells);
				champList.add(champion);
				spellsCursor.close();
			} while (cursor.moveToNext());
		}

		cursor.close();
		db.close();

		return champList;
	}

	public int getChampionsCount() {
		String countQuery = "SELECT * FROM " + TABLE_CHAMPIONS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		return cursor.getCount();
	}

	public int updateChampion(Champion champ) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		if (champ.getAllytips().size() > 2) {
			values.put(KEY_ALLYTIPS, champ.getAllytips().get(0)
					+ "---" + champ.getAllytips().get(1) + "---"
					+ champ.getAllytips().get(2) + "---");
		} else {
			values.put(KEY_ALLYTIPS, champ.getAllytips().get(0)
					+ "---" + champ.getAllytips().get(1) + "---");
		}

		if (champ.getEnemytips().size() > 2) {
			values.put(KEY_ENEMYTIPS, champ.getEnemytips().get(0)
					+ "---" + champ.getEnemytips().get(1) + "---"
					+ champ.getEnemytips().get(2) + "---");
		} else {
			values.put(KEY_ENEMYTIPS, champ.getEnemytips().get(0)
					+ "---" + champ.getEnemytips().get(1) + "---");
		}

		values.put(KEY_ID, champ.getId());
		values.put(KEY_KEY, champ.getKey());
		values.put(KEY_NAME, champ.getName());
		values.put(KEY_TITLE, champ.getTitle());
		values.put(KEY_BLURB, champ.getBlurb());

		return db.update(TABLE_CHAMPIONS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(champ.getId()) });
	}

	public void deleteChampion(Champion champ) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CHAMPIONS, KEY_ID + " = ?",
				new String[] { String.valueOf(champ.getId()) });
		db.close();
	}

	public int getChampionIdByPos(int position) {

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT " + KEY_ID + " FROM "
				+ TABLE_CHAMPIONS + " ORDER BY " + KEY_NAME, null);

		int id = 0;

		if (cursor.moveToPosition(position)) {
			id = Integer.parseInt(cursor.getString(0));
			cursor.close();
			db.close();
			return id;
		} else {
			cursor.close();
			db.close();
			return id;
		}

	}

	private void addTips(Champion champ, SQLiteDatabase db) {
		ContentValues values = new ContentValues();

		if (champ.getAllytips().size() > 2) {
			values.put(KEY_ALLYTIPS, champ.getAllytips().get(0)
					+ "---" + champ.getAllytips().get(1) + "---"
					+ champ.getAllytips().get(2) + "---");
			// Log.w(KEY_ALLYTIPS, champ.getAllytips().get(0) + "---"
			// + champ.getAllytips().get(1) + "---"
			// + champ.getAllytips().get(2) + "---");
		} else {
			values.put(KEY_ALLYTIPS, champ.getAllytips().get(0)
					+ "---" + champ.getAllytips().get(1) + "---");
			// Log.w(KEY_ALLYTIPS, champ.getAllytips().get(0) + "---"
			// + champ.getAllytips().get(1) + "---");
		}

		if (champ.getEnemytips().size() > 2) {
			values.put(KEY_ENEMYTIPS, champ.getEnemytips().get(0)
					+ "---" + champ.getEnemytips().get(1) + "---"
					+ champ.getEnemytips().get(2) + "---");
			// Log.w(KEY_ENEMYTIPS, champ.getEnemytips().get(0) +
			// "---"
			// + champ.getEnemytips().get(1) + "---"
			// + champ.getEnemytips().get(2) + "---");
		} else {
			values.put(KEY_ENEMYTIPS, champ.getEnemytips().get(0)
					+ "---" + champ.getEnemytips().get(1) + "---");
			// Log.w(KEY_ENEMYTIPS, champ.getEnemytips().get(0) +
			// "---"
			// + champ.getEnemytips().get(1) + "---");
		}

		values.put(KEY_ID, champ.getId());
		db.insert(TABLE_TIPS, null, values);
		db.close();
	}

	private void addChampionBase(Champion champ, SQLiteDatabase db) {
		ContentValues values = new ContentValues();

		values.put(KEY_ID, champ.getId());
		values.put(KEY_NAME, champ.getName());
		values.put(KEY_TITLE, champ.getTitle());

		String champTags = "";
		List<String> tagList = champ.getTags();
		for (String tag : tagList) {
			champTags += tag + ",";
		}
		values.put(KEY_TAGS, champTags);

		values.put(KEY_KEY, champ.getKey());
		values.put(KEY_BLURB, champ.getBlurb());
		values.put(KEY_LORE, champ.getLore());
		values.put(KEY_RESOURCE, champ.getPartype());

		// Log.w(KEY_ID, champ.getId().toString());
		// Log.w(KEY_KEY, champ.getKey());
		// Log.w(KEY_NAME, champ.getName());
		// Log.w(KEY_TITLE, champ.getTitle());

		db.insert(TABLE_CHAMPIONS, null, values);
	}

	private void addPassive(Champion champ, SQLiteDatabase db) {
		ContentValues values = new ContentValues();

		values.put(KEY_ID, champ.getId());
		values.put(KEY_PASSIVE_DESCRIPTION, champ.getPassive()
				.getSanitizedDescription());
		values.put(KEY_PASSIVE_NAME, champ.getPassive().getName());

		db.insert(TABLE_PASSIVE, null, values);
	}

	private void addSkins(Champion champ, SQLiteDatabase db) {
		ContentValues values = new ContentValues();

		for (Skins skin : champ.getSkins()) {
			values.put(KEY_ID, champ.getId());
			values.put(KEY_SKIN_ID, skin.getId().intValue());
			values.put(KEY_SKIN_NAME, skin.getName());
			db.insert(TABLE_SKINS, null, values);
		}

	}

	private void addRecommended(Champion champ, SQLiteDatabase db) {
		ContentValues values = new ContentValues();

		for (Recommended rec : champ.getRecommended()) {
			values.put(KEY_TITLE, rec.getTitle());
			values.put(KEY_MAP, rec.getMap());
			values.put(KEY_MODE, rec.getMode());
			values.put(KEY_ID, champ.getId().intValue());

			for (Blocks block : rec.getBlocks()) {

				if (block.getType().equals("starting")) {
					int i = 0;
					for (Item item : block.getItems()) {
						values.put("starting_" + i, item.getId());
						i++;
					}
				} else if (block.getType().equals("essential")) {
					int i = 0;
					for (Item item : block.getItems()) {
						values.put("essential_" + i, item.getId());
						i++;
					}
				} else if (block.getType().equals("offensive")) {
					int i = 0;
					for (Item item : block.getItems()) {
						values.put("offensive_" + i, item.getId());
						i++;
					}
				} else if (block.getType().equals("defensive")) {
					int i = 0;
					for (Item item : block.getItems()) {
						values.put("defensive_" + i, item.getId());
						i++;
					}
				}
			}
			db.insert(TABLE_RECOMMENDED, null, values);
		}

	}

	private void addSpells(Champion champ, SQLiteDatabase db) {
		ContentValues values = new ContentValues();

		for (Spell spell : champ.getSpells()) {
			values.put(KEY_SPELL_NAME, spell.getName());
			values.put(KEY_ID, champ.getId());
			values.put(KEY_DESCRIPTION, spell.getSanitizedDescription());
			values.put(KEY_TOOLTIP, spell.getSanitizedTooltip());

			String mEffectBurn = "";
			for (String effectBurn : spell.getEffectBurn()) {
				mEffectBurn += effectBurn + ",";
			}
			values.put(KEY_EFFECTBURN, mEffectBurn);
			values.put(KEY_COOLDOWNBURN, spell.getCooldownBurn());
			values.put(KEY_COSTBURN, spell.getCostBurn());
			values.put(KEY_RANGEBURN, spell.getRangeBurn());
			values.put(KEY_SPELL_RESOURCE, spell.getResource());

			int i = 0;
			for (Var var : spell.getVars()) {
				values.put("varKey_" + i, var.getKey());
				values.put("varLink_" + i, var.getLink());
				values.put("varCoeff_" + i, var.getCoeff());
				i++;
			}

			db.insert(TABLE_SPELLS, null, values);
		}
	}

	private void addStats(Champion champ, SQLiteDatabase db) {
		ContentValues values = new ContentValues();
		values.put(KEY_ID, champ.getId());
		values.put(KEY_ARMOR, champ.getStats().getArmor().doubleValue());
		values.put(KEY_ARMOR_PER_LEVEL, champ.getStats().getArmorperlevel()
				.doubleValue());
		values.put(KEY_ATTACK_RANGE, champ.getStats().getAttackrange()
				.doubleValue());
		values.put(KEY_ATTACK_SPEED_OFFSET, champ.getStats()
				.getAttackspeedoffset().doubleValue());
		values.put(KEY_ATTACK_SPEED_PER_LEVEL, champ.getStats()
				.getAttackspeedperlevel().doubleValue());
		values.put(KEY_ATTACK_DAMAGE, champ.getStats().getAttackdamage()
				.doubleValue());
		values.put(KEY_ATTACK_DAMAGE_PER_LEVEL, champ.getStats()
				.getAttackdamageperlevel().doubleValue());
		values.put(KEY_CRIT, champ.getStats().getCrit().doubleValue());
		values.put(KEY_CRIT_PER_LEVEL, champ.getStats().getCritperlevel()
				.doubleValue());
		values.put(KEY_MP, champ.getStats().getMp().doubleValue());
		values.put(KEY_MP_PER_LEVEl, champ.getStats().getMpperlevel()
				.doubleValue());
		values.put(KEY_MP_REGEN, champ.getStats().getMpregen().doubleValue());
		values.put(KEY_MP_REGEN_PER_LEVEL, champ.getStats()
				.getMpregenperlevel().doubleValue());
		values.put(KEY_HP, champ.getStats().getHp().doubleValue());
		values.put(KEY_HP_PER_LEVEL, champ.getStats().getHpperlevel()
				.doubleValue());
		values.put(KEY_HP_REGEN, champ.getStats().getHpregen().doubleValue());
		values.put(KEY_HP_REGEN_PER_LEVEL, champ.getStats()
				.getHpregenperlevel().doubleValue());
		values.put(KEY_MAGIC_RES, champ.getStats().getSpellblock()
				.doubleValue());
		values.put(KEY_MAGIC_RES_PER_LEVEL, champ.getStats()
				.getSpellblockperlevel().doubleValue());
		values.put(KEY_MOVESPEED, champ.getStats().getMovespeed().doubleValue());

		db.insert(TABLE_STATS, null, values);
	}

	private void addInfo(Champion champ, SQLiteDatabase db) {
		ContentValues values = new ContentValues();

		values.put(KEY_ID, champ.getId());
		values.put(KEY_DEFENSE, champ.getInfo().getDefense().intValue());
		values.put(KEY_DIFFICULTY, champ.getInfo().getDifficulty().intValue());
		values.put(KEY_MAGIC, champ.getInfo().getMagic().intValue());
		values.put(KEY_ATTACK, champ.getInfo().getAttack().intValue());

		db.insert(TABLE_INFO, null, values);
	}

	private void createDb(SQLiteDatabase db) {
		String CREATE_CHAMPIONS_TABLE = "CREATE TABLE " + TABLE_CHAMPIONS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_TITLE + " TEXT," + KEY_TAGS + " TEXT," + KEY_KEY
				+ " TEXT," + KEY_BLURB + " TEXT," + KEY_LORE + " TEXT,"
				+ KEY_RESOURCE + " TEXT" + ")";

		String CREATE_INFO_TABLE = "CREATE TABLE " + TABLE_INFO + "(" + KEY_ID
				+ " INTEGER PRIMARY KEY," + KEY_DEFENSE + " TEXT,"
				+ KEY_DIFFICULTY + " TEXT," + KEY_ATTACK + " TEXT," + KEY_MAGIC
				+ " TEXT" + ")";

		String CREATE_TIPS_TABLE = "CREATE TABLE " + TABLE_TIPS + "(" + KEY_ID
				+ " INTEGER PRIMARY KEY," + KEY_ALLYTIPS + " TEXT,"
				+ KEY_ENEMYTIPS + " TEXT" + ")";

		String CREATE_SKINS_TABLE = "CREATE TABLE " + TABLE_SKINS + "("
				+ KEY_ID + " INTEGER," + KEY_SKIN_ID + " INTEGER PRIMARY KEY,"
				+ KEY_SKIN_NAME + " TEXT" + ")";

		String CREATE_PASSIVE_TABLE = "CREATE TABLE " + TABLE_PASSIVE + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_PASSIVE_DESCRIPTION
				+ " TEXT," + KEY_PASSIVE_NAME + " TEXT" + ")";

		String CREATE_SPELLS_TABLE = "CREATE TABLE " + TABLE_SPELLS + "("
				+ KEY_ID + " INTEGER," + KEY_SPELL_NAME + " TEXT PRIMARY KEY,"
				+ KEY_DESCRIPTION + " TEXT," + KEY_TOOLTIP + " TEXT,"
				+ KEY_COOLDOWNBURN + " TEXT," + KEY_EFFECTBURN + " TEXT,"
				+ KEY_COSTBURN + " TEXT," + KEY_RANGEBURN + " TEXT,"
				+ KEY_SPELL_RESOURCE + " TEXT," + KEY_VARKEY_0 + " TEXT,"
				+ KEY_VARKEY_1 + " TEXT," + KEY_VARKEY_2 + " TEXT,"
				+ KEY_VARKEY_3 + " TEXT," + KEY_VARKEY_4 + " TEXT,"
				+ KEY_VARLINK_0 + " TEXT," + KEY_VARLINK_1 + " TEXT,"
				+ KEY_VARLINK_2 + " TEXT," + KEY_VARLINK_3 + " TEXT,"
				+ KEY_VARLINK_4 + " TEXT," + KEY_VARCOEFF_0 + " TEXT,"
				+ KEY_VARCOEFF_1 + " TEXT," + KEY_VARCOEFF_2 + " TEXT,"
				+ KEY_VARCOEFF_3 + " TEXT," + KEY_VARCOEFF_4 + " TEXT" + ")";

		String CREATE_STATS_TABLE = "CREATE TABLE " + TABLE_STATS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_ARMOR + " TEXT,"
				+ KEY_ARMOR_PER_LEVEL + " TEXT," + KEY_ATTACK_RANGE + " TEXT,"
				+ KEY_ATTACK_SPEED_OFFSET + " TEXT,"
				+ KEY_ATTACK_SPEED_PER_LEVEL + " TEXT," + KEY_ATTACK_DAMAGE
				+ " TEXT," + KEY_ATTACK_DAMAGE_PER_LEVEL + " TEXT," + KEY_CRIT
				+ " TEXT," + KEY_CRIT_PER_LEVEL + " TEXT," + KEY_MP + " TEXT,"
				+ KEY_MP_PER_LEVEl + " TEXT," + KEY_MP_REGEN + " TEXT,"
				+ KEY_MP_REGEN_PER_LEVEL + " TEXT," + KEY_HP + " TEXT,"
				+ KEY_HP_PER_LEVEL + " TEXT," + KEY_HP_REGEN + " TEXT,"
				+ KEY_HP_REGEN_PER_LEVEL + " TEXT," + KEY_MAGIC_RES + " TEXT,"
				+ KEY_MAGIC_RES_PER_LEVEL + " TEXT," + KEY_MOVESPEED + " TEXT"
				+ ")";

		String CREATE_RECOMMENDED_TABLE = "CREATE TABLE " + TABLE_RECOMMENDED
				+ "(" + KEY_ID + " INTEGER," + KEY_MAP + " TEXT," + KEY_TITLE
				+ " TEXT," + KEY_MODE + " INTEGER," + KEY_STARTING_0
				+ " INTEGER," + KEY_STARTING_1 + " INTEGER," + KEY_STARTING_2
				+ " INTEGER," + KEY_STARTING_3 + " INTEGER," + KEY_STARTING_4
				+ " INTEGER," + KEY_ESSENTIAL_0 + " INTEGER," + KEY_ESSENTIAL_1
				+ " INTEGER," + KEY_ESSENTIAL_2 + " INTEGER," + KEY_ESSENTIAL_3
				+ " INTEGER," + KEY_ESSENTIAL_4 + " INTEGER," + KEY_OFFENSIVE_0
				+ " INTEGER," + KEY_OFFENSIVE_1 + " INTEGER," + KEY_OFFENSIVE_2
				+ " INTEGER," + KEY_OFFENSIVE_3 + " INTEGER," + KEY_OFFENSIVE_4
				+ " INTEGER," + KEY_DEFENSIVE_0 + " INTEGER," + KEY_DEFENSIVE_1
				+ " INTEGER," + KEY_DEFENSIVE_2 + " INTEGER," + KEY_DEFENSIVE_3
				+ " INTEGER," + KEY_DEFENSIVE_4 + " INTEGER,"
				+ " PRIMARY KEY (" + KEY_ID + ", " + KEY_TITLE + ", "
				+ KEY_MODE + ")" + ")";

		db.execSQL(CREATE_CHAMPIONS_TABLE);
		db.execSQL(CREATE_INFO_TABLE);
		db.execSQL(CREATE_PASSIVE_TABLE);
		db.execSQL(CREATE_RECOMMENDED_TABLE);
		db.execSQL(CREATE_SKINS_TABLE);
		db.execSQL(CREATE_SPELLS_TABLE);
		db.execSQL(CREATE_TIPS_TABLE);
		db.execSQL(CREATE_STATS_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAMPIONS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_INFO);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PASSIVE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECOMMENDED);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SKINS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPELLS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIPS);

		// Create tables again
		createDb(db);

	}

}
