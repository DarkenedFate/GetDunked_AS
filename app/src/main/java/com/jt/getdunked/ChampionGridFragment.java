package com.jt.getdunked;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.jt.getdunked.championdata.Champion;

public class ChampionGridFragment extends Fragment {

	private GridView gvChamps;
	private List<Champion> listChamps = new ArrayList<Champion>();
	private ImageAdapter adapter;

	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static ChampionGridFragment newInstance(Bundle args) {
		ChampionGridFragment fragment = new ChampionGridFragment();
		fragment.setArguments(args);
		return fragment;
	}

	public ChampionGridFragment() {
        setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);

        gvChamps = (GridView) rootView.findViewById(R.id.gvChamps);
        gvChamps.setVisibility(View.VISIBLE);
        return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		((ActionBarActivity) getActivity()).getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

        if (savedInstanceState != null) {
            listChamps = (List<Champion>) savedInstanceState.getSerializable("listChamps");
        } else {
            ChampionDatabaseHelper db = new ChampionDatabaseHelper(getActivity());
            listChamps = db.getAllChampions();
            db.close();
        }

        adapter = new ImageAdapter(getActivity(), listChamps);
        gvChamps.setAdapter(adapter);

		gvChamps.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				ChampionDatabaseHelper db = new ChampionDatabaseHelper(
						getActivity());

				Log.w("onItemClick", "id: " + db.getChampionIdByPos(arg2));

				Intent intent = new Intent(getActivity(),
						ChampionActivity.class);
				intent.putExtra("id",
						(Integer) arg1.findViewById(R.id.grid_item_image)
								.getTag());
				startActivity(intent);

				db.close();
			}
		});
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.champion_grid, menu);

		final MenuItem item = menu.findItem(R.id.action_search);

		SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

		searchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String arg0) {
				MenuItemCompat.collapseActionView(item);
				return true;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				List<Champion> newChampList = new ArrayList<Champion>();
				for (Champion champ : listChamps) {
					if (champ.getName().toLowerCase(Locale.getDefault())
							.contains(newText.toLowerCase(Locale.getDefault()))) {
						newChampList.add(champ);
					}
				}
				if (adapter != null) {
					adapter.update(newChampList);
				}
				return true;
			}
		});

	}

    @Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putSerializable("listChamps", (ArrayList<Champion>) listChamps);
		super.onSaveInstanceState(outState);
	}
}