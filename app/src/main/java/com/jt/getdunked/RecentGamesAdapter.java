package com.jt.getdunked;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jt.getdunked.summonerdata.Games;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by JT on 5/30/2014.
 */
public class RecentGamesAdapter extends BaseAdapter {

    private List<Games> mListGames = new ArrayList<Games>();
    private SparseArray<String> mSparseItems = new SparseArray<String>();
    private Context mContext;

    public RecentGamesAdapter(Context context, List<Games> listGames) {
        mListGames = listGames;
        mContext = context;

        mSparseItems = new SparseArray<String>();

        try {
            InputStream inputStream = mContext.getAssets().open("items.json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String inputDataLine;

            while ((inputDataLine = reader.readLine()) != null) {
                String[] split = inputDataLine.split(",");
                mSparseItems.put(Integer.parseInt(split[0]), split[1]);
            }

        } catch (IOException e) {
            Log.w("RecentGamesExpandableAdapter", "IOException in getItemNameFromId");
        }
    }

    @Override
    public int getCount() {
        return mListGames.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
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
        @InjectView(R.id.iv_item_7)
        ImageView ivItem7;
        @InjectView(R.id.iv_summoner_1)
        ImageView ivSummoner1;
        @InjectView(R.id.iv_summoner_2)
        ImageView ivSummoner2;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if (view == null) {

            view = View.inflate(mContext, R.layout.recent_games_listview,
                    null);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        Games game = null;
        String champName = "";

        ChampionDatabaseHelper db = new ChampionDatabaseHelper(mContext);
        if (mListGames.size() > 0) {
            game = mListGames.get(i);
            champName = db.getChampionName(game.getChampionId().intValue()).getKey();
        }



        vh.ivChampion.setImageResource(Utils.getResIdByName(mContext, champName + "square"));
        vh.tvKda.setText(setKda(game));
        vh.tvMode.setText(getGameType(game).toUpperCase(Locale.getDefault()));

        if (game.getStats().getWin()) {
            vh.tvResult.setTextColor(Color.rgb(102, 153, 0));
            vh.tvResult.setText("VICTORY");
        } else {
            vh.tvResult.setTextColor(Color.rgb(204, 0, 0));
            vh.tvResult.setText("DEFEAT");
        }

        setItemImages(game, vh);
        setAbilityImages(game, vh);

        return view;
    }

    private int getItemResId(int id) {
        return Utils.getResIdByName(mContext, getItemNameFromId(id).toLowerCase(Locale.getDefault())
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

    private void setAbilityImages(Games game, ViewHolder vh) {
        switch (game.getSpell1().intValue()) {
            case 1:
                vh.ivSummoner1.setImageResource(R.drawable.cleanse);
                break;
            case 2:
                vh.ivSummoner1.setImageResource(R.drawable.clairvoyance);
                break;
            case 3:
                vh.ivSummoner1.setImageResource(R.drawable.exhaust);
                break;
            case 4:
                vh.ivSummoner1.setImageResource(R.drawable.flash);
                break;
            case 6:
                vh.ivSummoner1.setImageResource(R.drawable.ghost);
                break;
            case 7:
                vh.ivSummoner1.setImageResource(R.drawable.heal);
                break;
            case 10:
                vh.ivSummoner1.setImageResource(R.drawable.revive);
                break;
            case 11:
                vh.ivSummoner1.setImageResource(R.drawable.smite);
                break;
            case 12:
                vh.ivSummoner1.setImageResource(R.drawable.teleport);
                break;
            case 13:
                vh.ivSummoner1.setImageResource(R.drawable.clarity);
                break;
            case 14:
                vh.ivSummoner1.setImageResource(R.drawable.ignite);
                break;
            case 17:
                vh.ivSummoner1.setImageResource(R.drawable.garrison);
                break;
            case 21:
                vh.ivSummoner1.setImageResource(R.drawable.barrier);
                break;
            default:
                break;
        }
        switch (game.getSpell2().intValue()) {
            case 1:
                vh.ivSummoner2.setImageResource(R.drawable.cleanse);
                break;
            case 2:
                vh.ivSummoner2.setImageResource(R.drawable.clairvoyance);
                break;
            case 3:
                vh.ivSummoner2.setImageResource(R.drawable.exhaust);
                break;
            case 4:
                vh.ivSummoner2.setImageResource(R.drawable.flash);
                break;
            case 6:
                vh.ivSummoner2.setImageResource(R.drawable.ghost);
                break;
            case 7:
                vh.ivSummoner2.setImageResource(R.drawable.heal);
                break;
            case 10:
                vh.ivSummoner2.setImageResource(R.drawable.revive);
                break;
            case 11:
                vh.ivSummoner2.setImageResource(R.drawable.smite);
                break;
            case 12:
                vh.ivSummoner2.setImageResource(R.drawable.teleport);
                break;
            case 13:
                vh.ivSummoner2.setImageResource(R.drawable.clarity);
                break;
            case 14:
                vh.ivSummoner2.setImageResource(R.drawable.ignite);
                break;
            case 17:
                vh.ivSummoner2.setImageResource(R.drawable.garrison);
                break;
            case 21:
                vh.ivSummoner2.setImageResource(R.drawable.barrier);
                break;
            default:
                break;
        }
    }

    private void setItemImages(Games game, ViewHolder vh) {
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
        if (game.getStats().getItem6() != null) {
            vh.ivItem7.setVisibility(View.VISIBLE);
            vh.ivItem7.setImageResource(getItemResId(game.getStats().getItem6().intValue()));
        } else {
            vh.ivItem7.setVisibility(View.INVISIBLE);
        }
    }

    private String setKda(Games game) {

        int champKills = game.getStats().getChampionsKilled() == null ? 0 : game.getStats().getChampionsKilled().intValue();
        int deaths = game.getStats().getNumDeaths() == null ? 0 : game.getStats().getNumDeaths().intValue();
        int assists = game.getStats().getAssists() == null ? 0 : game.getStats().getAssists().intValue();

        return champKills + " / " + deaths + " / " + assists;
    }

    private String getItemNameFromId(int id) {
        return mSparseItems.get(id);
    }
}
