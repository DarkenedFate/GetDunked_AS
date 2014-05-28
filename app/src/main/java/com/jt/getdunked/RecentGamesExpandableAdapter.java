package com.jt.getdunked;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jt.getdunked.summonerdata.Games;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RecentGamesExpandableAdapter extends BaseExpandableListAdapter {

    private Context c;
    private List<Games> listGames = new ArrayList<Games>();
    private HashMap<Integer, String> itemMap;

    public RecentGamesExpandableAdapter(Context c, List<Games> listGames) {
        this.c = c;
        this.listGames = listGames;

        itemMap = new HashMap<Integer, String>();

        try {
            InputStream inputStream = c.getAssets().open("items.json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String inputDataLine;

            while ((inputDataLine = reader.readLine()) != null) {
                String[] split = inputDataLine.split(",");
                itemMap.put(Integer.parseInt(split[0]), split[1]);
            }

        } catch (IOException e) {
            Log.w("RecentGamesExpandableAdapter", "IOException in getItemNameFromId");
        }
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
        @InjectView(R.id.ivChampion)
        ImageView ivChampion;
        @InjectView(R.id.iv_item_1)
        ImageView ivItem1;
        @InjectView(R.id.iv_item_2)
        ImageView ivItem2;
        @InjectView(R.id.iv_item_3)
        ImageView ivItem3;
        @InjectView(R.id.iv_item_4)
        ImageView ivItem4;
        @InjectView(R.id.iv_item_5)
        ImageView ivItem5;
        @InjectView(R.id.iv_item_6)
        ImageView ivItem6;
        @InjectView(R.id.tv_result)
        TextView tvResult;
        @InjectView(R.id.tv_mode)
        TextView tvMode;
        @InjectView(R.id.tv_kda)
        TextView tvKda;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        ParentViewHolder vh;

        if (convertView == null) {
            convertView = View.inflate(c, R.layout.spell_list_layout, null);

            vh = new ParentViewHolder();
            convertView.setTag(vh);

        } else {
            vh = (ParentViewHolder) convertView.getTag();
        }

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
        return listGames.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return listGames.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

    static class ParentViewHolder {

    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {

            convertView = View.inflate(c, R.layout.recent_games_listview,
                    null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Games game = listGames.get(groupPosition);

        ChampionDatabaseHelper db = new ChampionDatabaseHelper(c);
        String champName = db.getChampionName(game.getChampionId().intValue()).getKey();
        vh.ivChampion.setImageResource(Utils.getResIdByName(c, champName + "square"));
        vh.tvKda.setText(game.getStats().getChampionsKilled().toString() + " / "
                + game.getStats().getNumDeaths() + " / " + game.getStats().getAssists());
        vh.tvMode.setText(getGameType(game).toUpperCase(Locale.getDefault()));

        if (game.getStats().getWin()) {
            vh.tvResult.setTextColor(Color.rgb(102, 153, 0));
            vh.tvResult.setText("VICTORY");
        } else {
            vh.tvResult.setTextColor(Color.rgb(204, 0, 0));
            vh.tvResult.setText("DEFEAT");
        }

        setUpItemImages(game, vh);

        db.close();
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

    private String getItemNameFromId(int id) {
        return itemMap.get(id);
    }

    private int getItemResId(int id) {
        return Utils.getResIdByName(c, getItemNameFromId(id).toLowerCase(Locale.getDefault())
                .replace(" ", "_").replace("'", "") + "_item");
    }

    private String getGameType(Games game) {
        if (game.getGameMode().equals("ODIN") && game.getGameType().equals("CUSTOM_GAME")) {
            return "Dominion (Custom)";
        } else if (game.getGameMode().equals("ODIN") && game.getGameType().equals("MATCHED_GAME")) {
            return "Dominion";
        } else if (game.getGameMode().equals("ODIN") && game.getSubType().equals("BOT")) {
            return "Coop vs AI Dominion";
        } else if (game.getGameMode().equals("CLASSIC") && game.getGameType().equals("CUSTOM_GAME")) {
            return "Custom Game";
        } else if (game.getGameMode().equals("CLASSIC") && game.getSubType().equals("NORMAL")) {
            return "Normal 5v5";
        } else if (game.getGameMode().equals("CLASSIC") && game.getSubType().equals("NORMAL_3x3")) {
            return "Normal 3v3";
        } else if (game.getGameMode().equals("CLASSIC") && game.getSubType().equals("BOT")) {
            return "Coop vs AI 5v5";
        } else if (game.getGameMode().equals("CLASSIC") && game.getSubType().equals("BOT_3x3")) {
            return "Coop vs AI 3v3";
        } else if (game.getGameMode().equals("CLASSIC") && game.getSubType().equals("RANKED_SOLO_5x5")) {
            return "Ranked Solo 5v5";
        } else if (game.getGameMode().equals("CLASSIC") && game.getSubType().equals("RANKED_TEAM_5x5")) {
            return "Ranked Team 5v5";
        } else if (game.getGameMode().equals("CLASSIC") && game.getSubType().equals("RANKED_TEAM_3x3")) {
            return "Ranked Team 3v3";
        } else if (game.getSubType().equals("ONEFORALL_5x5")) {
            return "One for All";
        } else if (game.getSubType().equals("FIRSTBLOOD_1x1")) {
            return "First Blood 1v1";
        } else if (game.getSubType().equals("FIRSTBLOOD_2x2")) {
            return "First Blood 2v2";
        } else if (game.getSubType().equals("SR_6x6")) {
            return "Hexakill";
        } else if (game.getSubType().equals("CAP_5x5")) {
            return "Team Builder";
        } else if (game.getSubType().equals("URF")) {
            return "Ultra Rapid Fire";
        } else if (game.getSubType().equals("URF_BOT")) {
            return "Ultra Rapid Fire vs AI";
        } else if (game.getSubType().equals("URF_BOT")) {
            return "Ultra Rapid Fire vs AI";
        } else if (game.getGameMode().equals("ARAM")) {
            return "ARAM";
        } else if (game.getGameMode().equals("TUTORIAL")) {
            return "Tutorial";
        } else if (game.getGameMode().equals("ARAM")) {
            return "ARAM";
        }

        return "";
    }

    private void setUpItemImages(Games game, ViewHolder vh) {
        if (game.getStats().getItem0() != null) {
            vh.ivItem1.setVisibility(View.VISIBLE);
            vh.ivItem1.setImageResource(getItemResId(game.getStats().getItem0().intValue()));
        } else {
            vh.ivItem1.setVisibility(View.INVISIBLE);
        }
        if (game.getStats().getItem1() != null) {
            vh.ivItem2.setVisibility(View.VISIBLE);
            vh.ivItem2.setImageResource(getItemResId(game.getStats().getItem1().intValue()));
        } else {
            vh.ivItem2.setVisibility(View.INVISIBLE);
        }
        if (game.getStats().getItem2() != null) {
            vh.ivItem3.setVisibility(View.VISIBLE);
            vh.ivItem3.setImageResource(getItemResId(game.getStats().getItem2().intValue()));
        } else {
            vh.ivItem3.setVisibility(View.INVISIBLE);
        }
        if (game.getStats().getItem3() != null) {
            vh.ivItem4.setVisibility(View.VISIBLE);
            vh.ivItem4.setImageResource(getItemResId(game.getStats().getItem3().intValue()));
        } else {
            vh.ivItem4.setVisibility(View.INVISIBLE);
        }
        if (game.getStats().getItem4() != null) {
            vh.ivItem5.setVisibility(View.VISIBLE);
            vh.ivItem5.setImageResource(getItemResId(game.getStats().getItem4().intValue()));
        } else {
            vh.ivItem5.setVisibility(View.INVISIBLE);
        }
        if (game.getStats().getItem5() != null) {
            vh.ivItem6.setVisibility(View.VISIBLE);
            vh.ivItem6.setImageResource(getItemResId(game.getStats().getItem5().intValue()));
        } else {
            vh.ivItem6.setVisibility(View.INVISIBLE);
        }
    }
}
