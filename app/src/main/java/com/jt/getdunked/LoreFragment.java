package com.jt.getdunked;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.jt.getdunked.championdata.Champion;

public class LoreFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static LoreFragment newInstance(int sectionNumber) {
		LoreFragment fragment = new LoreFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@InjectView(R.id.tv_lore)
	RoboLightTextView tvLore;
	@InjectView(R.id.tv_champ_title)
	RoboLightTextView tvChampTitle;

	public LoreFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.lore_layout, null);
		ButterKnife.inject(this, rootView);

		Champion champ = getChampion();
		tvLore.setText(Html.fromHtml(champ.getLore()));
		tvChampTitle.setText(champ.getName() + ", " + champ.getTitle());

		return rootView;
	}
	
	private Champion getChampion() {
		Intent intent = getActivity().getIntent();
		final int champId = intent.getIntExtra("id", 1);
		ChampionDatabaseHelper db = new ChampionDatabaseHelper(getActivity());
		return db.getChampion(champId);
	}
}