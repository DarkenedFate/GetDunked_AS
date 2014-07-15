package com.jt.getdunked;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.BadTokenException;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jt.getdunked.summonerdata.FellowPlayers;
import com.jt.getdunked.summonerdata.Games;
import com.jt.getdunked.summonerdata.Summoner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by JT on 5/30/2014.
 */
public class CustomPopupWindow {

    private Context mContext;
    private PopupWindow mPopupWindow;
    private Games mGame;
    private Summoner mSummoner;
    private SparseArray<String> mSparsePlayers = new SparseArray<String>();

    private int dipsWidthPortrait_Normal;
    private int dipsHeightPortrait_Normal;
    private int dipsWidthLandscape_Normal;
    private int dipsHeightLandscape_Normal;
    private int dipsWidthPortrait_Large;
    private int dipsHeightPortrait_Large;
    private int dipsWidthLandscape_Large;
    private int dipsHeightLandscape_Large;
    private int dipsWidthPortrait_Small;
    private int dipsHeightPortrait_Small;
    private int dipsWidthLandscape_Small;
    private int dipsHeightLandscape_Small;
    private int dipsWidthPortrait_XLarge;
    private int dipsHeightPortrait_XLarge;
    private int dipsWidthLandscape_XLarge;
    private int dipsHeightLandscape_XLarge;

    @InjectView(R.id.btnClose)
    ImageButton btnClose;
    @InjectView(R.id.tv_total_damage_dealt)
    TextView tvTotalDamageDealt;
    @InjectView(R.id.tv_total_true_damage_dealt)
    TextView tvTotalTrueDamageDealt;
    @InjectView(R.id.tv_total_physical_damage_dealt)
    TextView tvTotalPhysicalDamageDealt;
    @InjectView(R.id.tv_total_magic_damage_dealt)
    TextView tvTotalMagicDamageDealt;
    @InjectView(R.id.tv_total_damage_dealt_champs)
    TextView tvTotalDamageToChamps;
    @InjectView(R.id.tv_true_damage_dealt_champs)
    TextView tvTrueDamageToChamps;
    @InjectView(R.id.tv_magic_damage_dealt_champs)
    TextView tvMagicDamageToChamps;
    @InjectView(R.id.tv_physical_damage_dealt_champs)
    TextView tvPhysicalDamageToChamps;
    @InjectView(R.id.tv_largest_crit)
    TextView tvLargestCrit;
    @InjectView(R.id.tv_total_damage_taken)
    TextView tvTotalDamageTaken;
    @InjectView(R.id.tv_true_damage_taken)
    TextView tvTrueDamageTaken;
    @InjectView(R.id.tv_magic_damage_taken)
    TextView tvMagicDamageTaken;
    @InjectView(R.id.tv_physical_damage_taken)
    TextView tvPhysicalDamageTaken;
    @InjectView(R.id.tv_num_killing_sprees)
    TextView tvNumKillingSprees;
    @InjectView(R.id.tv_largest_multikill)
    TextView tvLargestMultiKill;
    @InjectView(R.id.tv_double_kills)
    TextView tvDoubleKills;
    @InjectView(R.id.tv_triple_kills)
    TextView tvTripleKills;
    @InjectView(R.id.tv_quadra_kill)
    TextView tvQuadraKills;
    @InjectView(R.id.tv_penta_kills)
    TextView tvPentaKills;
    @InjectView(R.id.tv_minions_killed)
    TextView tvMinionsKilled;
    @InjectView(R.id.tv_neutral_minions_killed)
    TextView tvNeutralMinionsKilled;
    @InjectView(R.id.tv_healing_done)
    TextView tvHealingDone;
    @InjectView(R.id.tv_sight_wards_purchased)
    TextView tvSightWardsPurchased;
    @InjectView(R.id.tv_vision_wards_purchased)
    TextView tvVisionWardsPurchased;
    @InjectView(R.id.tv_wards_killed)
    TextView tvWardsKilled;
    @InjectView(R.id.tv_gold_earned)
    TextView tvGoldEarned;
    @InjectView(R.id.tv_gold_spent)
    TextView tvGoldSpent;
    @InjectView(R.id.tv_team_1_player_1)
    TextView tvTeam1Player1;
    @InjectView(R.id.tv_team_1_player_2)
    TextView tvTeam1Player2;
    @InjectView(R.id.tv_team_1_player_3)
    TextView tvTeam1Player3;
    @InjectView(R.id.tv_team_1_player_4)
    TextView tvTeam1Player4;
    @InjectView(R.id.tv_team_1_player_5)
    TextView tvTeam1Player5;
    @InjectView(R.id.tv_team_2_player_1)
    TextView tvTeam2Player1;
    @InjectView(R.id.tv_team_2_player_2)
    TextView tvTeam2Player2;
    @InjectView(R.id.tv_team_2_player_3)
    TextView tvTeam2Player3;
    @InjectView(R.id.tv_team_2_player_4)
    TextView tvTeam2Player4;
    @InjectView(R.id.tv_team_2_player_5)
    TextView tvTeam2Player5;
    @InjectView(R.id.iv_team_1_player_1)
    ImageView ivTeam1Player1;
    @InjectView(R.id.iv_team_1_player_2)
    ImageView ivTeam1Player2;
    @InjectView(R.id.iv_team_1_player_3)
    ImageView ivTeam1Player3;
    @InjectView(R.id.iv_team_1_player_4)
    ImageView ivTeam1Player4;
    @InjectView(R.id.iv_team_1_player_5)
    ImageView ivTeam1Player5;
    @InjectView(R.id.iv_team_2_player_1)
    ImageView ivTeam2Player1;
    @InjectView(R.id.iv_team_2_player_2)
    ImageView ivTeam2Player2;
    @InjectView(R.id.iv_team_2_player_3)
    ImageView ivTeam2Player3;
    @InjectView(R.id.iv_team_2_player_4)
    ImageView ivTeam2Player4;
    @InjectView(R.id.iv_team_2_player_5)
    ImageView ivTeam2Player5;
    @InjectView(R.id.progressBar)
    ProgressBar progressBar;

    private TextView[] teamOneTextViews;
    private TextView[] teamTwoTextViews;
    private ImageView[] teamChampImageViews;

    public CustomPopupWindow(Context context, Games game, Summoner summoner) {
        mContext = context;
        mGame = game;
        mSummoner = summoner;

        dipsWidthPortrait_Normal = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, mContext
                        .getResources().getDisplayMetrics());

        dipsHeightPortrait_Normal = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 460, mContext
                        .getResources().getDisplayMetrics());

        dipsWidthLandscape_Normal = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 475, mContext
                        .getResources().getDisplayMetrics());

        dipsHeightLandscape_Normal = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, mContext
                        .getResources().getDisplayMetrics());

        dipsWidthPortrait_Large = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 425, mContext
                        .getResources().getDisplayMetrics());

        dipsHeightPortrait_Large = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 600, mContext
                        .getResources().getDisplayMetrics());

        dipsWidthLandscape_Large = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 650, mContext
                        .getResources().getDisplayMetrics());

        dipsHeightLandscape_Large = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 425, mContext
                        .getResources().getDisplayMetrics());

        dipsWidthPortrait_Small = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, mContext
                        .getResources().getDisplayMetrics());

        dipsHeightPortrait_Small = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 350, mContext
                        .getResources().getDisplayMetrics());

        dipsWidthLandscape_Small = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 350, mContext
                        .getResources().getDisplayMetrics());

        dipsHeightLandscape_Small = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, mContext
                        .getResources().getDisplayMetrics());


        dipsWidthPortrait_XLarge = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 600, mContext
                        .getResources().getDisplayMetrics());

        dipsHeightPortrait_XLarge = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 750, mContext
                        .getResources().getDisplayMetrics());

        dipsWidthLandscape_XLarge = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 900, mContext
                        .getResources().getDisplayMetrics());

        dipsHeightLandscape_XLarge = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 600, mContext
                        .getResources().getDisplayMetrics());

    }


    void doSomeWindow(View layout, View parent, int widthLandscape,
                      int heightLandscape, int widthPortrait, int heightPortrait) {

        if (mContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mPopupWindow = new PopupWindow(layout, widthLandscape, heightLandscape);
            mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setAnimationStyle(R.style.AnimationPopup);
            mPopupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
            mPopupWindow.setFocusable(true);

        } else {
            mPopupWindow = new PopupWindow(layout, widthPortrait, heightPortrait);
            mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setAnimationStyle(R.style.AnimationPopup);
            mPopupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
            mPopupWindow.setFocusable(true);
        }
    }

    void initiatePopupWindow(View parent) {
        // get screen size of device
        try {
            int screenSize = mContext.getResources().getConfiguration().screenLayout
                    & Configuration.SCREENLAYOUT_SIZE_MASK;

            // We need to get the instance of the LayoutInflater,
            // Gotta give the PopupWindow a layout
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            RelativeLayout viewGroup = (RelativeLayout) ((ActionBarActivity) mContext).findViewById(R.id.fl_popup);
            View layout = inflater.inflate(R.layout.recent_games_child_view, viewGroup);
            ButterKnife.inject(this, layout);

            teamChampImageViews = new ImageView[]{ivTeam1Player1, ivTeam1Player2, ivTeam1Player3,
                    ivTeam1Player4, ivTeam1Player5, ivTeam2Player1, ivTeam2Player2, ivTeam2Player3,
                    ivTeam2Player4, ivTeam2Player5};
            teamTwoTextViews = new TextView[]{tvTeam2Player1, tvTeam2Player2, tvTeam2Player3,
                    tvTeam2Player4, tvTeam2Player5};
            teamOneTextViews = new TextView[]{tvTeam1Player1, tvTeam1Player2, tvTeam1Player3,
                    tvTeam1Player4, tvTeam1Player5};

            // make different popupWindows for different screen sizes
            switch (screenSize) {

                // XLARGE = 10"+ Tablets usually
                case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                    doSomeWindow(layout, parent, dipsWidthLandscape_XLarge,
                            dipsHeightLandscape_XLarge, dipsWidthPortrait_XLarge,
                            dipsHeightPortrait_XLarge);
                    break;

                // LARGE = 7"+ Tablets usually, maybe some giant phones
                case Configuration.SCREENLAYOUT_SIZE_LARGE:
                    doSomeWindow(layout, // View of the popupWindow
                            parent, // Some view from the parent activity/fragment
                            dipsWidthLandscape_Large, // Width for landscape
                            dipsHeightLandscape_Large, // Height for landscape
                            dipsWidthPortrait_Large, // Width for portrait
                            dipsHeightPortrait_Large); // Height for portrait
                    break;

                // NORMAL = 95% of all phones
                case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                    doSomeWindow(layout, parent, dipsWidthLandscape_Normal,
                            dipsHeightLandscape_Normal, dipsWidthPortrait_Normal,
                            dipsHeightPortrait_Normal);
                    break;
                default:
                    doSomeWindow(layout, parent, dipsWidthLandscape_Small,
                            dipsHeightLandscape_Small, dipsWidthPortrait_Small,
                            dipsHeightPortrait_Small);
                    break;
            }
        } catch (BadTokenException e) {
            e.printStackTrace();
        }

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });

        setGameStats();
        new GetFellowPlayersNames().execute(mGame.getFellowPlayers());

    }

    void setContext(Context context) {
        mContext = context;
    }

    private void setGameStats() {

        int totalDamageDealt = mGame.getStats().getTotalDamageDealt() == null ? 0 : mGame.getStats().getTotalDamageDealt();
        int trueDamageDealt = mGame.getStats().getTrueDamageDealtPlayer() == null ? 0 : mGame.getStats().getTrueDamageDealtPlayer();
        int physicalDamageDealt = mGame.getStats().getPhysicalDamageDealtPlayer() == null ? 0 : mGame.getStats().getPhysicalDamageDealtPlayer();
        int magicDamageDealt = mGame.getStats().getMagicDamageDealtPlayer() == null ? 0 : mGame.getStats().getMagicDamageDealtPlayer();

        int totalDamageToChamps = mGame.getStats().getTotalDamageDealtToChampions() == null ? 0 : mGame.getStats().getTotalDamageDealtToChampions();
        int trueDamageToChamps = mGame.getStats().getTrueDamageDealtToChampions() == null ? 0 : mGame.getStats().getTrueDamageDealtToChampions();
        int physicalDamageToChamps = mGame.getStats().getPhysicalDamageDealtToChampions() == null ? 0 : mGame.getStats().getPhysicalDamageDealtToChampions();
        int magicDamageToChamps = mGame.getStats().getMagicDamageDealtToChampions() == null ? 0 : mGame.getStats().getMagicDamageDealtToChampions();

        int totalDamageTaken = mGame.getStats().getTotalDamageTaken() == null ? 0 : mGame.getStats().getTotalDamageTaken();
        int trueDamageTaken = mGame.getStats().getTrueDamageTaken() == null ? 0 : mGame.getStats().getTrueDamageTaken();
        int physicalDamageTaken = mGame.getStats().getPhysicalDamageTaken() == null ? 0 : mGame.getStats().getPhysicalDamageTaken();
        int magicDamageTaken = mGame.getStats().getMagicDamageTaken() == null ? 0 : mGame.getStats().getMagicDamageTaken();

        int numKillingSprees = mGame.getStats().getKillingSprees() == null ? 0 : mGame.getStats().getKillingSprees();
        int largestMultiKill = mGame.getStats().getTotalDamageDealt() == null ? 0 : mGame.getStats().getLargestMultiKill();
        int largestCrit = mGame.getStats().getLargestCriticalStrike() == null ? 0 : mGame.getStats().getLargestCriticalStrike();
        int doubleKills = mGame.getStats().getDoubleKills() == null ? 0 : mGame.getStats().getDoubleKills();
        int tripleKills = mGame.getStats().getTripleKills() == null ? 0 : mGame.getStats().getTripleKills();
        int quadraKills = mGame.getStats().getQuadraKills() == null ? 0 : mGame.getStats().getQuadraKills();
        int pentaKills = mGame.getStats().getPentaKills() == null ? 0 : mGame.getStats().getPentaKills();

        int minionsKilled = mGame.getStats().getMinionsKilled() == null ? 0 : mGame.getStats().getMinionsKilled();
        int neutralMinionsKilled = mGame.getStats().getNeutralMinionsKilled() == null ? 0 : mGame.getStats().getNeutralMinionsKilled();

        int healingDone = mGame.getStats().getTotalHeal() == null ?
                0 : mGame.getStats().getTotalHeal();

        int sightWardsPurchased = mGame.getStats().getSightWardsBought() == null ?
                0 : mGame.getStats().getSightWardsBought();
        int visionWardsPurchased = mGame.getStats().getVisionWardsBought() == null ?
                0 : mGame.getStats().getVisionWardsBought();
        int wardsKilled = mGame.getStats().getWardKilled() == null ? 0 : mGame.getStats().getWardKilled();
        int goldEarned = mGame.getStats().getGoldEarned() == null ? 0 : mGame.getStats().getGoldEarned();
        int goldSpent = mGame.getStats().getGoldSpent() == null ? 0 : mGame.getStats().getGoldSpent();

        tvTotalDamageDealt.setText(formatNumbers(String.valueOf(totalDamageDealt)));
        tvTotalTrueDamageDealt.setText(formatNumbers(String.valueOf(trueDamageDealt)));
        tvTotalPhysicalDamageDealt.setText(formatNumbers(String.valueOf(physicalDamageDealt)));
        tvTotalMagicDamageDealt.setText(formatNumbers(String.valueOf(magicDamageDealt)));

        tvTotalDamageToChamps.setText(formatNumbers(String.valueOf(totalDamageToChamps)));
        tvTrueDamageToChamps.setText(formatNumbers(String.valueOf(trueDamageToChamps)));
        tvMagicDamageToChamps.setText(formatNumbers(String.valueOf(magicDamageToChamps)));
        tvPhysicalDamageToChamps.setText(formatNumbers(String.valueOf(physicalDamageToChamps)));

        tvTotalDamageTaken.setText(formatNumbers(String.valueOf(totalDamageTaken)));
        tvMagicDamageTaken.setText(formatNumbers(String.valueOf(magicDamageTaken)));
        tvPhysicalDamageTaken.setText(formatNumbers(String.valueOf(physicalDamageTaken)));
        tvTrueDamageTaken.setText(formatNumbers(String.valueOf(trueDamageTaken)));

        tvNumKillingSprees.setText(formatNumbers(String.valueOf(numKillingSprees)));
        tvLargestMultiKill.setText(formatNumbers(String.valueOf(largestMultiKill)));
        tvLargestCrit.setText(formatNumbers(String.valueOf(largestCrit)));
        tvDoubleKills.setText(formatNumbers(String.valueOf(doubleKills)));
        tvTripleKills.setText(formatNumbers(String.valueOf(tripleKills)));
        tvQuadraKills.setText(formatNumbers(String.valueOf(quadraKills)));
        tvPentaKills.setText(formatNumbers(String.valueOf(pentaKills)));

        tvMinionsKilled.setText(formatNumbers(String.valueOf(minionsKilled)));
        tvNeutralMinionsKilled.setText(formatNumbers(String.valueOf(neutralMinionsKilled)));

        tvHealingDone.setText(formatNumbers(String.valueOf(healingDone)));

        tvSightWardsPurchased.setText(formatNumbers(String.valueOf(sightWardsPurchased)));
        tvVisionWardsPurchased.setText(formatNumbers(String.valueOf(visionWardsPurchased)));
        tvWardsKilled.setText(formatNumbers(String.valueOf(wardsKilled)));

        tvGoldEarned.setText(formatNumbers(String.valueOf(goldEarned)));
        tvGoldSpent.setText(formatNumbers(String.valueOf(goldSpent)));
    }

    /**
     * Function that sets the <i>TextView</i>s of the "Teams" section of the PopupWindow layout.
     *
     * @param sparsePlayers <i>SparseArray<String></i>, the key is the summoner ID, and the value is
     *                      the summoner name
     */
    private void setTeams(SparseArray<String> sparsePlayers) {

        List<FellowPlayers> teamOne = new ArrayList<FellowPlayers>();
        List<FellowPlayers> teamTwo = new ArrayList<FellowPlayers>();

        for (FellowPlayers player : mGame.getFellowPlayers()) {
            if (player.getTeamId() == mGame.getTeamId().intValue()) {
                teamOne.add(player);
            } else {
                teamTwo.add(player);
            }
        }

        for (int i = 0; i < teamOne.size(); i++) {
            teamOneTextViews[0].setText(mSummoner.getName());
            teamOneTextViews[i + 1].setText(sparsePlayers.get(teamOne.get(i).getSummonerId()));
        }
        for (int i = 0; i < teamTwo.size(); i++) {
            teamTwoTextViews[i].setText(sparsePlayers.get(teamTwo.get(i).getSummonerId()));
        }


    }

    /**
     * Function that takes a String of numbers, and outputs them as a String of formatted
     * (with commas) numbers (Example input: 1234567 -- Example output: 1,234,567)
     *
     * @param input String of numbers to format
     * @return String of formatted numbers
     */
    private String formatNumbers(String input) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(input);
        NumberFormat nf = NumberFormat.getInstance();
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String g = m.group();
            m.appendReplacement(sb, nf.format(Double.parseDouble(g)));
        }
        return m.appendTail(sb).toString();
    }

    private class GetFellowPlayersNames extends AsyncTask<List<FellowPlayers>, Void, SparseArray<String>> {

        private List<Integer> listResIds = new ArrayList<Integer>();

        @Override
        protected SparseArray<String> doInBackground(List<FellowPlayers>... players) {

            // Begin grabbing player name & champion data
            List<FellowPlayers> listPlayers = players[0];

            StringBuilder nameUrl = new StringBuilder();
            nameUrl.append("https://na.api.pvp.net/api/lol/na/v1.4/summoner/");
            for (int i = 0; i < listPlayers.size(); i++) {
                if (i != listPlayers.size() - 1) {
                    nameUrl.append(listPlayers.get(i).getSummonerId());
                    nameUrl.append(",");
                } else {
                    nameUrl.append(listPlayers.get(i).getSummonerId());
                }
            }
            nameUrl.append("/name?api_key=" + Api.KEY);
            String namesJson = getJsonFromUrl(nameUrl.toString());
            if (!namesJson.contains("status")) {
                namesJson = namesJson.replace("\"", "").replace("{", "").replace("}", "");
                String[] playas = namesJson.split(",");
                for (String player : playas) {
                    String[] sup = player.split(":");
                    mSparsePlayers.put(Integer.valueOf(sup[0]), sup[1]);
                }
            }

            // Convert champ ID to champ name
            ChampionDatabaseHelper db = new ChampionDatabaseHelper(mContext);

            listResIds.add(Utils.getResIdByName(mContext, db.getChampionName(mGame.getChampionId().intValue()).getKey() + "square"));
            for (FellowPlayers player : mGame.getFellowPlayers()) {
                int resId = Utils.getResIdByName(mContext, db.getChampionName(player.getChampionId()).getKey() + "square");
                listResIds.add(resId);
            }
            db.close();

            return mSparsePlayers;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);
            for (ImageView iv : teamChampImageViews) {
                iv.setVisibility(View.INVISIBLE);
            }
            for (TextView tv : teamOneTextViews) {
                tv.setVisibility(View.INVISIBLE);
            }
            for (TextView tv : teamTwoTextViews) {
                tv.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        protected void onPostExecute(SparseArray<String> sparsePlayers) {
            super.onPostExecute(sparsePlayers);

            progressBar.setVisibility(View.INVISIBLE);
            for (ImageView iv : teamChampImageViews) {
                iv.setVisibility(View.VISIBLE);
            }
            for (TextView tv : teamOneTextViews) {
                tv.setVisibility(View.VISIBLE);
            }
            for (TextView tv : teamTwoTextViews) {
                tv.setVisibility(View.VISIBLE);
            }
            setTeams(sparsePlayers);
            setTeamChampImages(listResIds);
        }
    }

    /**
     * Function that sets the <i>ImageView</i>s of the "Teams" section of the PopupWindow layout.
     *
     * @param listResIds A <i>List</i> of Resource IDs for the champion squares.
     */
    private void setTeamChampImages(List<Integer> listResIds) {

        for (int i = 0; i < listResIds.size(); i++) {
            teamChampImageViews[i].setImageResource(listResIds.get(i));
        }
    }

    private String getJsonFromUrl(String url) {

        int code = 0;
        String jsonResponse = "";

        try {
            URL jsonUrl;
            HttpsURLConnection connectionSsl;
            HttpURLConnection connection;
            InputStream in;
            BufferedReader reader;
            StringBuilder sb = new StringBuilder();
            String line;

            if (url.contains("https")) {
                jsonUrl = new URL(url);
                connectionSsl = (HttpsURLConnection) jsonUrl.openConnection();
                connectionSsl.setRequestMethod("GET");
                code = connectionSsl.getResponseCode();
                in = (code == 200) ? connectionSsl.getInputStream()
                        : connectionSsl.getErrorStream();
            } else {
                jsonUrl = new URL(url);
                connection = (HttpURLConnection) jsonUrl.openConnection();
                connection.setRequestMethod("GET");
                code = connection.getResponseCode();
                in = (code == 200) ? connection.getInputStream() : connection
                        .getErrorStream();

            }
            reader = new BufferedReader(new InputStreamReader(in));

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            jsonResponse = sb.toString();
        } catch (MalformedURLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e) {
            Log.w("getJsonFromUrl",
                    "IOException, most likely no data returned from server.\nError: "
                            + e.getClass().getName() + "\nAt line: "
                            + e.getStackTrace()[0].getLineNumber()
                            + "\nResponse: " + jsonResponse + "\nURL: " + url
                            + "\nCode: " + code
            );

        }
        return jsonResponse;
    }

}

