package com.jt.getdunked;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.MenuItem;

import com.jt.getdunked.championdata.Champion;

public class ChampionSearchListener implements OnQueryTextListener {

	private List<Champion> champList = new ArrayList<Champion>();
	private ImageAdapter adapter;
	private List<Champion> newChampList = new ArrayList<Champion>();
	private ProfileFragment profileFragment;
	private MenuItem menuItem;
	private ProfileActivity fragment;
	private ActionBarActivity cxt;

	public ChampionSearchListener(ActionBarActivity cxt,
			List<Champion> champList, ImageAdapter adapter, MenuItem menuItem) {
		this.champList.addAll(champList);
		this.adapter = adapter;
		this.menuItem = menuItem;
		this.cxt = cxt;
	}

	public boolean onQueryTextChange(String newText) {

		if (!newChampList.isEmpty()) {
			newChampList.clear();
		}

		fragment = (ProfileActivity) cxt.getSupportFragmentManager()
				.findFragmentByTag("ProfileActivity");

		if (!fragment.isVisible()) {
			for (Champion champ : champList) {
				if (champ.getName().toLowerCase(Locale.getDefault())
						.contains(newText.toLowerCase())) {
					newChampList.add(champ);
				}
			}

			adapter.update(newChampList);
		}

		return false;
	}

	public boolean onQueryTextSubmit(String query) {

		if (fragment.isVisible()) {
			profileFragment = (ProfileFragment) fragment
					.getChildFragmentManager().findFragmentByTag(
							Utils.makeFragmentName(R.id.pager, 0));
			
			profileFragment.new SetSummonerName().execute(query
					.toLowerCase(Locale.getDefault()));
			menuItem.collapseActionView();
			return false;
		}
		return true;
	}

	public boolean onClose() {
		return false;
	}

	protected boolean isAlwaysExpanded() {
		return false;
	}

}
