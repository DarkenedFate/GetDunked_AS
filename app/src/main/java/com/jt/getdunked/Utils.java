package com.jt.getdunked;

import android.annotation.SuppressLint;
import android.content.Context;

@SuppressLint("DefaultLocale")
public class Utils {

	/**
	 * Returns ID of drawable resource from name <br>
	 * ex: <i>foo_bar.png</i> would be <br>
	 * <code>Utils.getResIdByName(context, "foo_bar");</code>
	 * 
	 * @param context
	 *            Context.
	 * @param drawableName
	 *            Name of resource as a String
	 * 
	 * @return Resource ID of drawable. Ex: <code>R.id.foo_bar</code>
	 */
	static int getResIdByName(Context context, String drawableName) {
		return context.getResources().getIdentifier(drawableName.toLowerCase(),
				"drawable", context.getPackageName());
	}

	/**
	 * Gets fragment name (tag) for findFragmentByTag()
	 * 
	 * @param viewId
	 *            Resource ID of ViewPager
	 * @param index
	 *            Position of fragment in ViewPager
	 * @return Tag of Fragment as String
	 */
	static String makeFragmentName(int viewId, int index) {
		return "android:switcher:" + viewId + ":" + index;
	}

	/**
	 * Function that takes a String of the summoner's rating (gold v, plat iii,
	 * etc) and converts it from all caps (as the API sends it) "BRONZE III" to
	 * something a little nicer, like "Bronze III"
	 * 
	 * @param string
	 *            The String to be transformed
	 * @return The transformed String
	 */
	static String toSpecialCase(String string) {

		return string.replace("BRONZE", "Bronze").replace("SILVER", "Silver")
				.replace("GOLD", "Gold").replace("PLATINUM", "Platinum")
				.replace("DIAMOND", "Diamond")
				.replace("CHALLENGER", "Challenger");

	}

}
