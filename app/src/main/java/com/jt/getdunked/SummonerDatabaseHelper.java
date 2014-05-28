package com.jt.getdunked;

import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jt.getdunked.summonerdata.Summoner;

public class SummonerDatabaseHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "summonerManager";
	public static final int DATABASE_VERSION = 15;

	private static final String TABLE_SUMMONER_ID = "summonerIds";
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";

	public SummonerDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void addSummoner(Summoner summoner) {

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		if (!summoner.getName().startsWith("IS1")) {
			values.put(KEY_ID, summoner.getId());
			values.put(KEY_NAME, summoner.getName());
			db.insert(TABLE_SUMMONER_ID, null, values);
			Log.w("Summoner added", "Summoner \"" + summoner.getName()
					+ "\" with ID " + summoner.getId() + " Level: " + summoner.getSummonerLevel());
		}

		db.close();

	}

	/**
	 * Function that takes Summoner Name as <i>String</i>, performs SQL query
	 * for lowercase and de-spaced Summoner Name, and returns the corresponding
	 * Summoner ID
	 * 
	 * @param name
	 *            The name of the Summoner as a <i>String</i>
	 * @return The ID of the Summoner as an <i>int</i>
	 * 
	 */
	public int getSummonerIdByName(String name) {

		Log.w("Summoner DB", "getSummonerIdByName arg: " + name);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor idCursor = db.rawQuery("SELECT " + KEY_ID + " FROM "
				+ TABLE_SUMMONER_ID + " WHERE REPLACE(LOWER(" + KEY_NAME
				+ "), ' ', '')" + " = ?",
				new String[] { name.toLowerCase(Locale.getDefault()) });

		if (idCursor.moveToFirst()) {
			int id = Integer.parseInt(idCursor.getString(0));
			idCursor.close();
			db.close();

			return id;
		} else {
			return 0;
		}
	}

	public Summoner getSummonerByName(String name) {

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor summonerCursor = db.rawQuery("SELECT * FROM "
				+ TABLE_SUMMONER_ID + " WHERE TRIM(LOWER(" + KEY_NAME
				+ "))" + " = ?",
				new String[] { name.toLowerCase(Locale.getDefault()) });
		
		Summoner summoner = new Summoner();
		if (summonerCursor.moveToFirst()) {
			summoner.setId(Integer.parseInt(summonerCursor.getString(0)));
			summoner.setName(summonerCursor.getString(1));
		} 
		
		Log.w("Summoner DB", "Name: " + summoner.getName());

		db.close();
		summonerCursor.close();

		return summoner;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_SUMMONER_ID_TABLE = "CREATE TABLE " + TABLE_SUMMONER_ID
				+ "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT)";

		db.execSQL(CREATE_SUMMONER_ID_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUMMONER_ID);
		onCreate(db);

	}

}
