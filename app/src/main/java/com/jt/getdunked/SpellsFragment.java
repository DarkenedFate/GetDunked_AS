package com.jt.getdunked;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.jt.getdunked.championdata.Champion;
import com.jt.getdunked.championdata.Spell;

import java.util.List;
import java.util.Locale;

import butterknife.InjectView;

public class SpellsFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static SpellsFragment newInstance(int sectionNumber) {
		SpellsFragment fragment = new SpellsFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@InjectView(R.id.tv_lore)
	RoboLightTextView tvLore;
	@InjectView(R.id.tv_champ_title)
	RoboLightTextView tvChampTitle;

	public SpellsFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView;
		Champion champ = getChampion();

		Spell passive = new Spell();
		passive.setSanitizedTooltip(champ.getPassive()
				.getSanitizedDescription());
		passive.setName(champ.getPassive().getName());

		List<Spell> spellList = champ.getSpells();
		spellList.add(0, passive);
		champ.setSpells(spellList);

        ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle(champ.getName());
        ((ActionBarActivity)getActivity()).getSupportActionBar().setIcon(
				Utils.getResIdByName(getActivity(),
						champ.getKey().toLowerCase(Locale.getDefault())
								+ "square"));

		rootView = inflater.inflate(R.layout.fragment_champion, container,
				false);

		ExpandableListView lvSpells = (ExpandableListView) rootView
				.findViewById(R.id.lv_spells);
		ExpandableSpellListAdapter adapter = new ExpandableSpellListAdapter(
				getActivity(), champ);
		
		lvSpells.setDividerHeight(0);
		lvSpells.setDivider(null);
		lvSpells.setAdapter(adapter);
		lvSpells.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				if (groupPosition == 0) {
					return true;
				} else {
					return false;
				}
			}
		});

		return rootView;
	}

	private Champion getChampion() {
		Intent intent = getActivity().getIntent();
		final int champId = intent.getIntExtra("id", 1);
		ChampionDatabaseHelper db = new ChampionDatabaseHelper(getActivity());
		Champion champion = db.getChampion(champId);
		db.close();
		return champion;
	}
}