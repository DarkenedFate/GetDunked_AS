package com.jt.getdunked;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.jt.getdunked.championdata.Champion;
import com.jt.getdunked.championdata.Stats;

public class StatsFragment extends Fragment {

	@InjectView(R.id.tv_ad)
	TextView tvAd;
	@InjectView(R.id.tv_ad_per_lvl)
	TextView tvAdPerLevel;
	@InjectView(R.id.tv_as)
	TextView tvAs;
	@InjectView(R.id.tv_as_per_lvl)
	TextView tvAsPerLevel;
	@InjectView(R.id.tv_range)
	TextView tvRange;
	@InjectView(R.id.tv_hp)
	TextView tvHp;
	@InjectView(R.id.tv_hp_per_lvl)
	TextView tvHpPerLevel;
	@InjectView(R.id.tv_hp_regen)
	TextView tvHpRegen;
	@InjectView(R.id.tv_hp_regen_per_lvl)
	TextView tvHpRegenPerLevel;
	@InjectView(R.id.tv_armor)
	TextView tvArmor;
	@InjectView(R.id.tv_armor_per_lvl)
	TextView tvArmorPerLevel;
	@InjectView(R.id.tv_mr)
	TextView tvMr;
	@InjectView(R.id.tv_mr_per_lvl)
	TextView tvMrPerLevel;
	@InjectView(R.id.tv_mana)
	TextView tvMana;
	@InjectView(R.id.tv_mana_per_lvl)
	TextView tvManaPerLevel;
	@InjectView(R.id.tv_mana_regen)
	TextView tvManaRegen;
	@InjectView(R.id.tv_mana_regen_per_lvl)
	TextView tvManaRegenPerLevel;
	@InjectView(R.id.tv_movespeed)
	TextView tvMovespeed;
	@InjectView(R.id.tv_movespeed_per_lvl)
	TextView tvMovespeedPerLevel;
	
	
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static StatsFragment newInstance(int sectionNumber) {
		StatsFragment fragment = new StatsFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public StatsFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_stats, null);

		Intent intent = getActivity().getIntent();
		final int champId = intent.getIntExtra("id", 1);
		ChampionDatabaseHelper db = new ChampionDatabaseHelper(getActivity());
		Champion champ = db.getChampion(champId);
		db.close();

		Stats stats = champ.getStats();

		ButterKnife.inject(this, rootView);

		tvAd.setText(stats.getAttackdamage().toString());
		tvAdPerLevel.setText("+" + stats.getAttackdamageperlevel());

		/*
		 * Riot's Attack Speed calculation: 1/(1.6 * (1 +
		 * stats.attackspeedoffset)) * (1 + aspdbonuses + stats.aspdperlevel *
		 * (level - 1) / 100)
		 * 
		 * Base attack speed: 1/(1.6 * (1 + stats.attackspeedoffset)) OR 0.625 /
		 * (1 - stats.attackspeedoffset)
		 */
		tvAs.setText(String.valueOf(1 / (1.6 * (1 + stats
				.getAttackspeedoffset().intValue()))));
		tvAsPerLevel.setText("+" + stats.getAttackspeedperlevel() + "%");

		int attackRange = stats.getAttackrange().intValue();
		if (attackRange <= 175) {
			tvRange.setText(attackRange + " (Melee)");
		} else {
			tvRange.setText(attackRange + " (Ranged)");
		}

		tvHp.setText(String.valueOf(stats.getHp().intValue()));
		tvHpPerLevel.setText("+" + stats.getHpperlevel());
		tvHpRegen.setText(stats.getHpregen().toString());
		tvHpRegenPerLevel.setText("+" + stats.getHpregenperlevel());
		tvArmor.setText(stats.getArmor().toString());
		tvArmorPerLevel.setText("+" + stats.getArmorperlevel());
		tvMr.setText(stats.getSpellblock().toString());
		tvMrPerLevel.setText("+" + stats.getSpellblockperlevel());
		tvMana.setText(stats.getMp().toString());
		tvManaPerLevel.setText("+" + stats.getMpperlevel());
		tvManaRegen.setText(stats.getMpregen().toString());
		tvManaRegenPerLevel.setText("+" + stats.getMpregenperlevel());
        tvMovespeed.setText(String.valueOf(stats.getMovespeed().intValue()));

		return rootView;
	}
}