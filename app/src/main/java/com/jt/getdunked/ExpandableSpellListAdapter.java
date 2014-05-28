package com.jt.getdunked;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.jt.getdunked.championdata.Champion;
import com.jt.getdunked.championdata.Spell;
import com.jt.getdunked.championdata.Var;

public class ExpandableSpellListAdapter extends BaseExpandableListAdapter {

	private Champion champ;
	private Context c;
	private int id;
	private Typeface tf;

	public ExpandableSpellListAdapter(Context c, Champion champ) {
		this.champ = champ;
		this.c = c;
		id = champ.getId();
		tf = Typeface.createFromAsset(c.getAssets(), "fonts/Roboto-Light.ttf");
	}

	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	static class ViewHolder {
		@InjectView(R.id.tv_cost)
		TextView tvCost;
		@InjectView(R.id.tv_spell_title)
		TextView tvCostTitle;
		@InjectView(R.id.tv_cooldown)
		TextView tvCooldown;
		@InjectView(R.id.tv_cooldown_title)
		TextView tvCooldownTitle;
		@InjectView(R.id.tv_range)
		TextView tvRange;
		@InjectView(R.id.tv_range_title)
		TextView tvRangeTitle;

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		ViewHolder vh;
		if (convertView == null) {

			convertView = View.inflate(c, R.layout.spell_child_list_layout,
					null);
			vh = new ViewHolder(convertView);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		vh.tvCost.setTypeface(tf);
		vh.tvCostTitle.setTypeface(tf);
		vh.tvCooldown.setTypeface(tf);
		vh.tvCooldownTitle.setTypeface(tf);
		vh.tvRange.setTypeface(tf);
		vh.tvRangeTitle.setTypeface(tf);

		vh.tvRange.setText(champ.getSpells().get(groupPosition).getRangeBurn());

		vh.tvCooldown.setText(champ.getSpells().get(groupPosition)
				.getCooldownBurn());

		vh.tvCost.setText(makeTooltip(champ.getSpells().get(groupPosition),
				true).replace("{{ cost }}",
				champ.getSpells().get(groupPosition).getCostBurn()));

		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return champ.getSpells().get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return champ.getSpells().size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	static class ParentViewHolder {
		ImageView ivSpellIcon;
		ImageView ivSecondarySpellIcon;
		TextView tvSpellTooltip;
		TextView tvSpellTitle;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		ParentViewHolder vh;

		if (convertView == null) {
			convertView = View.inflate(c, R.layout.spell_list_layout, null);

			vh = new ParentViewHolder();

			vh.tvSpellTitle = (TextView) convertView
					.findViewById(R.id.tv_spell_title);
			vh.tvSpellTooltip = (TextView) convertView
					.findViewById(R.id.section_label);
			vh.ivSpellIcon = (ImageView) convertView
					.findViewById(R.id.iv_spell_one);
			vh.ivSecondarySpellIcon = (ImageView) convertView
					.findViewById(R.id.iv_spell_two);

			convertView.setTag(vh);

		} else {
			vh = (ParentViewHolder) convertView.getTag();
		}

		if (groupPosition == 0) {
			convertView.setClickable(false);
		}

		vh.tvSpellTooltip.setTypeface(tf);
		vh.tvSpellTitle.setTypeface(tf);

		vh.tvSpellTitle.setText(champ.getSpells().get(groupPosition).getName());

		if (!champ.getSpells().get(groupPosition).getName().contains("/")) {
			vh.ivSecondarySpellIcon.setVisibility(View.GONE);
		} else {
			vh.ivSecondarySpellIcon.setVisibility(View.VISIBLE);
		}
		if (champ.getId() == 74 && groupPosition != 4 && groupPosition != 0) {
			vh.ivSecondarySpellIcon.setVisibility(View.VISIBLE);
		}

		switch (champ.getId()) {
		case 51: // Caitlyn
			switch (groupPosition) {
			case 3:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"ninety_caliber_net"));
				break;
			default:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(
						c,
						champ.getSpells().get(groupPosition).getName()
								.replace(" ", "_").replace("'", "")
								.replace("-", "").replace(",", "")
								.replace("!", "")));
				break;
			}
			break;
		case 60: // Elise
			switch (groupPosition) {
			case 0:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"spider_swarm"));
				break;
			case 1:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"neurotoxin"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "venomous_bite"));
				break;
			case 2:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"volatile_spiderling"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "skittering_frenzy"));
				break;
			case 3:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"cocoon"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "rappel"));
				break;
			case 4:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"spider_form"));
				break;
			}
			break;
		case 64: // Lee Sin
			switch (groupPosition) {
			case 0:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"flurry"));
				break;
			case 1:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"sonic_wave"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "resonating_strike"));
				break;
			case 2:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"safeguard"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "iron_will"));
				break;
			case 3:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"tempest"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "cripple"));
				break;
			case 4:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"dragons_rage"));
				break;
			}
			break;
		case 74: // Heimer
			switch (groupPosition) {
			case 0:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"techmaturgical_repair_bots"));
				break;
			case 1:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"hg_evolution_turret"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "hq_apex_turret"));
				break;
			case 2:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"hextech_microrockets"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "hextech_rocket_swarm"));
				break;
			case 3:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"ch1_concussion_grenade"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "ch3x_lightning_grenade"));
				break;
			case 4:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"upgrade"));
				break;
			}
			break;
		case 76: // Nidalee
			switch (groupPosition) {
			case 0:
				vh.ivSpellIcon.setImageResource(Utils
						.getResIdByName(c, "prowl"));
				break;
			case 1:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"javelin_toss"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "takedown"));
				break;
			case 2:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"bushwhack"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "pounce"));
				break;
			case 3:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"primal_surge"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "swipe"));
				break;
			case 4:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"aspect_of_the_cougar"));
				break;
			}
			break;
		case 126: // Jayce
			switch (groupPosition) {
			case 0:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"hextech_capacitor"));
				break;
			case 1:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"to_the_skies"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "shock_blast"));
				break;
			case 2:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"lightning_field"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "hyper_charge"));
				break;
			case 3:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"thundering_blow"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "acceleration_gate"));
				break;
			case 4:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"transform_mercury_cannon"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "transform_mercury_hammer"));
				break;
			}
			break;
		case 266: // Aatrox
			if (groupPosition == 2) {
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"blood_thirst"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "blood_price"));
				break;
			}

		default:
			vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c, champ
					.getSpells().get(groupPosition).getName().replace(" ", "_")
					.replace("'", "").replace("-", "").replace(",", "")
					.replace("!", "")));
			break;
		}

		switch (id) {
		case 3: // Galio
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(groupPosition)).replace("{{ e4 }}",
					"4"));
			break;
		case 7: // LeBlanc
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(groupPosition)).replace("{{ e4 }}",
					"1.5"));
			break;
		case 21: // Miss Fortune
			if (groupPosition == 1) {
				vh.tvSpellTooltip.setText(makeTooltip(
						champ.getSpells().get(groupPosition)).replace(
						"{{ f1 }}", "0.8").replace("{{ f2 }}", "1.0"));
			} else if (groupPosition == 2) {
				vh.tvSpellTooltip
						.setText(makeTooltip(
								champ.getSpells().get(groupPosition))
								.replace("{{ f1 }}", "6% AD")
								.replace("{{ f2 }}", "30% AD")
								.replace("{{ f3 }}", "5"));
			} else {
				vh.tvSpellTooltip.setText(makeTooltip(champ.getSpells().get(
						groupPosition)));
			}
			break;
		case 30: // Karthus
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(groupPosition)).replace("{{ cost }}",
					champ.getSpells().get(3).getCostBurn()));
			break;
		case 38: // Kassadin
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(groupPosition))
					.replace(
							"1.5 (+0.3)",
							champ.getSpells().get(1).getEffectBurn().get(1)
									+ " (+0.8)")
					.replace("{{ e4 }}",
							champ.getSpells().get(1).getEffectBurn().get(2))
					.replace("{{ f2 }}", "2% of max mana")
					.replace("{{ f1 }}", "1% of max mana"));
			break;
		case 42: // Corki
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(groupPosition)).replace("{{ f1 }}",
					"0.5 bonus AD"));
			break;
		case 79: // Gragas
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(groupPosition)).replace("{{ f1 }}",
					"3"));
			break;
		case 83: // Yorick
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(groupPosition)).replace("{{ e5 }}",
					"40"));
			break;
		case 86: // Garen
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(groupPosition)).replace(
					"(+{{ f1 }})", "damage"));
			break;
		case 143: // Zyra
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(groupPosition)).replace("{{ f3 }}",
					"23 + (6.5 × level)").replace("{{ f3 }}",
					"23 + (6.5 × level)"));
			break;
		case 201: // Braum
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(groupPosition)).replace(
					"{{ f1 }}) [", "").replace("Health]", "Health)"));
			break;
		case 238: // Zed
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(groupPosition)).replace(
					" (+{{ f3 }})", ""));
			break;
		case 266: // Aatrox
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(groupPosition)).replace(
					"({{ f5 }}) ", "").replace("{{ f4 }} ", ""));
			break;
		case 267: // Nami
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(groupPosition)).replace("{{ f1 }}%",
					"-15% + (7.5% per 100 AP)"));
			break;
		case 103: // Ahri
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(groupPosition)).replace("{{ f1 }}",
					champ.getSpells().get(2).getEffectBurn().get(2)));
			break;

		case 101: // Xerath
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(groupPosition)).replace("{{ f1 }}",
					"90/135/180/225/270"));
			break;
		default:
			vh.tvSpellTooltip.setText(makeTooltip(champ.getSpells().get(
					groupPosition)));
			break;
		}
		vh.tvSpellTooltip.invalidate();
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	private String makeTooltip(Spell spell) {

		String tooltip = spell.getSanitizedTooltip();

		for (int i = 0; i < spell.getEffectBurn().size(); i++) {
			if (tooltip.contains("{{ e" + (i + 1) + " }}")) {
				tooltip = tooltip.replace("{{ e" + (i + 1) + " }}", spell
						.getEffectBurn().get(i));
			}
		}
		for (Var vars : spell.getVars()) {
			if (vars.getKey() != null && vars.getCoeff() != null
					&& tooltip.contains(vars.getKey())) {
				tooltip = tooltip.replace("{{ " + vars.getKey() + " }}", vars
						.getCoeff().toString());
			}
		}

		return tooltip;
	}

	private String makeTooltip(Spell spell, boolean isCost) {

		String tooltip = isCost ? spell.getResource() : spell
				.getSanitizedTooltip();

		for (int i = 0; i < spell.getEffectBurn().size(); i++) {
			if (tooltip.contains("{{ e" + (i + 1) + " }}")) {
				tooltip = tooltip.replace("{{ e" + (i + 1) + " }}", spell
						.getEffectBurn().get(i));
			}
		}
		for (Var vars : spell.getVars()) {
			if (vars.getKey() != null && vars.getCoeff() != null
					&& tooltip.contains(vars.getKey())) {
				tooltip = tooltip.replace("{{ " + vars.getKey() + " }}", String.valueOf(vars
						.getCoeff()));
			}
		}

		return tooltip;
	}
}
