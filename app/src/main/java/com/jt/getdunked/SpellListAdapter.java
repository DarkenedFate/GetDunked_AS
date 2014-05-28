package com.jt.getdunked;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jt.getdunked.championdata.Champion;
import com.jt.getdunked.championdata.Spell;
import com.jt.getdunked.championdata.Var;

public class SpellListAdapter extends BaseAdapter {
	private Champion champ;
	private Context c;
	private int id;
	private Typeface tf;

	public SpellListAdapter(Context c, Champion champ) {
		this.champ = champ;
		this.c = c;
		id = champ.getId();
		tf = Typeface.createFromAsset(c.getAssets(), "fonts/Roboto-Light.ttf");

	}

	@Override
	public int getCount() {
		return champ.getSpells().size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	static class ViewHolder {
		ImageView ivSpellIcon;
		ImageView ivSecondarySpellIcon;
		TextView tvSpellTooltip;
		TextView tvSpellTitle;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder vh;

		if (convertView == null) {
			convertView = View.inflate(c, R.layout.spell_list_layout, null);

			vh = new ViewHolder();

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
			vh = (ViewHolder) convertView.getTag();
		}

		vh.tvSpellTooltip.setTypeface(tf);
		vh.tvSpellTitle.setTypeface(tf);
		// vh.tvSpellTitle.setPaintFlags(vh.tvSpellTitle.getPaintFlags() |
		// Paint.UNDERLINE_TEXT_FLAG);
		vh.tvSpellTitle.setText(champ.getSpells().get(position).getName());

		if (!champ.getSpells().get(position).getName().contains("/")) {
			vh.ivSecondarySpellIcon.setVisibility(View.GONE);
		}

		switch (champ.getId()) {
		case 60: // Elise
			switch (position) {
			case 0:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"neurotoxin"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "venomous_bite"));
				break;
			case 1:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"volatile_spiderling"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "skittering_frenzy"));
				break;
			case 2:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"cocoon"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "rappel"));
				break;
			case 3:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"spider_form"));
				break;
			}
			break;
		case 64: // Lee Sin
			switch (position) {
			case 0:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"sonic_wave"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "resonating_strike"));
				break;
			case 1:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"safeguard"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "iron_will"));
				break;
			case 2:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"tempest"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "cripple"));
				break;
			case 3:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"dragons_rage"));
				break;
			}
			break;
		case 76: // Nidalee
			switch (position) {
			case 0:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"javelin_toss"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "takedown"));
				break;
			case 1:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"bushwhack"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "pounce"));
				break;
			case 2:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"primal_surge"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "swipe"));
				break;
			case 3:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"aspect_of_the_cougar"));
				break;
			}
			break;
		case 126: // Jayce
			switch (position) {
			case 0:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"to_the_skies"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "shock_blast"));
				break;
			case 1:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"lightning_field"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "hyper_charge"));
				break;
			case 2:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"thundering_blow"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "acceleration_gate"));
				break;
			case 3:
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"transform_mercury_cannon"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "transform_mercury_hammer"));
				break;
			}
			break;
		case 266: // Aatrox
			if (position == 1) {
				vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c,
						"blood_thirst"));
				vh.ivSecondarySpellIcon.setImageResource(Utils.getResIdByName(
						c, "blood_price"));
				break;
			}

		default:
			vh.ivSpellIcon.setImageResource(Utils.getResIdByName(c, champ
					.getSpells().get(position).getName().replace(" ", "_")
					.replace("'", "").replace("-", "").replace(",", "")
					.replace("!", "")));
			break;
		}

		switch (id) {
		case 3: // Galio
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(position)).replace("{{ e4 }}", "4"));
			break;
		case 7: // LeBlanc
			vh.tvSpellTooltip
					.setText(makeTooltip(champ.getSpells().get(position))
							.replace("{{ e4 }}", "1.5"));
			break;
		case 21: // Miss Fortune
			if (position == 0) {
				vh.tvSpellTooltip.setText(makeTooltip(
						champ.getSpells().get(position)).replace("{{ f1 }}",
						"0.8").replace("{{ f2 }}", "1.0"));
			} else if (position == 1) {
				vh.tvSpellTooltip
						.setText(makeTooltip(champ.getSpells().get(position))
								.replace("{{ f1 }}", "6% AD")
								.replace("{{ f2 }}", "30% AD")
								.replace("{{ f3 }}", "5"));
			} else {
				vh.tvSpellTooltip.setText(makeTooltip(champ.getSpells().get(
						position)));
			}
			break;
		case 30: // Karthus
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(position)).replace("{{ cost }}",
					champ.getSpells().get(2).getCostBurn()));
			break;
		case 38: // Kassadin
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(position))
					.replace(
							"1.5 (+0.3)",
							champ.getSpells().get(0).getEffectBurn().get(1)
									+ " (+0.8)")
					.replace("{{ e4 }}",
							champ.getSpells().get(0).getEffectBurn().get(2))
					.replace("{{ f2 }}", "2% of max mana")
					.replace("{{ f1 }}", "1% of max mana"));
			break;
		case 42: // Corki
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(position)).replace("{{ f1 }}",
					"0.5 bonus AD"));
			break;
		case 79: // Gragas
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(position)).replace("{{ f1 }}", "3"));
			break;
		case 83: // Yorick
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(position)).replace("{{ e5 }}", "40"));
			break;
		case 86: // Garen
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(position)).replace("(+{{ f1 }})",
					"damage"));
			break;
		case 143: // Zyra
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(position)).replace("{{ f3 }}",
					"23 + (6.5 × level)").replace("{{ f3 }}",
					"23 + (6.5 × level)"));
			break;
		case 238: // Zed
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(position))
					.replace(" (+{{ f3 }})", ""));
			break;
		case 266: // Aatrox
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(position)).replace("({{ f5 }}) ", "")
					.replace("{{ f4 }} ", ""));
			break;
		case 267: // Nami
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(position)).replace("{{ f1 }}%",
					"-15% + (7.5% per 100 AP)"));
			break;
		case 103: // Ahri
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(position)).replace("{{ f1 }}",
					champ.getSpells().get(1).getEffectBurn().get(2)));
			break;

		case 101: // Xerath
			vh.tvSpellTooltip.setText(makeTooltip(
					champ.getSpells().get(position)).replace("{{ f1 }}",
					"90/135/180/225/270"));
			break;
		default:
			vh.tvSpellTooltip.setText(makeTooltip(champ.getSpells().get(
					position)));
			break;
		}
		vh.tvSpellTooltip.invalidate();
		return convertView;
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

}
