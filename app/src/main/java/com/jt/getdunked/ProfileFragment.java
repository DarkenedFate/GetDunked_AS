package com.jt.getdunked;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jt.getdunked.summonerdata.LeagueEntry;
import com.jt.getdunked.summonerdata.PlayerStatSummaries;
import com.jt.getdunked.summonerdata.PlayerStats;
import com.jt.getdunked.summonerdata.Summoner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ProfileFragment extends Fragment {

    private int responseCodeId;
    private int responseCodeLeague;
    private Bundle suchBundle = new Bundle();

    @InjectView(R.id.layoutProfile)
    RelativeLayout layoutProfile;
    @InjectView(R.id.progressBar)
    ProgressBar progressBar;
    @InjectView(R.id.tvFiveLeague)
    TextView tvFiveLeague;
    @InjectView(R.id.tvSoloLeague)
    TextView tvSoloLeague;
    @InjectView(R.id.tvThreeLeague)
    TextView tvThreeLeague;
    @InjectView(R.id.tvThreeLP)
    TextView tvThreeLp;
    @InjectView(R.id.tvFiveLP)
    TextView tvFiveLp;
    @InjectView(R.id.tvSoloLP)
    TextView tvSoloLp;
    @InjectView(R.id.tvThreeWins)
    TextView tvWins3v3;
    @InjectView(R.id.tvThreeLosses)
    TextView tvLosses3v3;
    @InjectView(R.id.tvFiveWins)
    TextView tvWins5v5;
    @InjectView(R.id.tvFiveLosses)
    TextView tvLosses5v5;
    @InjectView(R.id.tvSoloWins)
    TextView tvWinsSolo;
    @InjectView(R.id.tvSoloLosses)
    TextView tvLossesSolo;
    @InjectView(R.id.ivSoloFiveIcon)
    ImageView ivSoloIcon;
    @InjectView(R.id.ivTeamFiveIcon)
    ImageView ivTeamFiveIcon;
    @InjectView(R.id.ivTeamThreeIcon)
    ImageView ivTeamThreeIcon;

    /**
     * The fragment argument representing the section number for this fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section number.
     */
    public static ProfileFragment newInstance(int sectionNumber) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container,
                false);

        ButterKnife.inject(this, rootView);

        if (suchBundle.size() > 0) {
            restoreState(suchBundle);
            progressBar.setVisibility(View.INVISIBLE);
        } else {
            new SetSummonerName().execute("Nwilliams239"); // Load some data for now
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    class SetSummonerName extends AsyncTask<String, Void, Integer> {

        Locale l = Locale.getDefault();
        private String rankTeam5v5;
        private String rankTeam3v3;
        private String rankSolo5v5;
        private int lpTeam5v5;
        private int lpTeam3v3;
        private int lpSolo5v5;
        private int numWinsTeam5v5;
        private int numWinsTeam3v3;
        private int numWinsSolo5v5;
        private int numLossesTeam5v5;
        private int numLossesTeam3v3;
        private int numLossesSolo5v5;
        private Gson gson = new Gson();
        private Summoner summoner;

        @Override
        protected Integer doInBackground(String... params) {
            Log.d("ProfileFragment", "doInBackground started");

            // Take spaces out of input for URL
            params[0] = params[0].toLowerCase(l).replace(" ", "");

            // URL for Summoner Name -> Summoner ID conversion
            String urlForId = "http://54.224.222.135:4567/summoners/by-name/"
                    + params[0];

            // Reformat Riot's JSON slightly
            String jsonResponseWithId = getJsonFromUrl(urlForId, "id");

            int summonerId;
            try {
                summoner = gson.fromJson(jsonResponseWithId, Summoner.class);
                summonerId = summoner.getId();
                Log.w("Summoner ID", "Summoner ID for " + summoner.getName()
                        + " found from " + summoner.getSource() + " with id: "
                        + summonerId);
            } catch (JsonSyntaxException e) {
                Log.w("GSON: Summoner ID", "GSON got null from API");
                return responseCodeId;
            }

            String playerStatsUrl = "https://prod.api.pvp.net/api/lol/na/v1.3/stats/by-summoner/"
                    + summonerId
                    + "/summary?season=SEASON4&api_key="
                    + Api.KEY;
            String leagueUrl = "https://prod.api.pvp.net/api/lol/na/v2.3/league/by-summoner/"
                    + summonerId + "/entry?api_key=" + Api.KEY;
            String jsonResponseWithLeagues = getJsonFromUrl(leagueUrl, "league");
            String jsonResponseWithStats = getJsonFromUrl(playerStatsUrl,
                    "stats");

            LeagueEntry[] league;
            PlayerStats stats;
            try {
                league = gson.fromJson(jsonResponseWithLeagues,
                        LeagueEntry[].class);
                stats = gson.fromJson(jsonResponseWithStats, PlayerStats.class);
            } catch (JsonSyntaxException e) {
                Log.w("GSON: Leagues", "GSON got null from API");
                return responseCodeLeague;
            }

            int team5v5 = 0;
            int team3v3 = 0;
            int solo5v5 = 0;
            if (league != null) {
                for (LeagueEntry entry : league) {
                    if (entry.getQueueType().equals("RANKED_TEAM_5x5")) {
                        if (team5v5 == 0) {
                            rankTeam5v5 = entry.getTier() + " "
                                    + entry.getRank();
                            lpTeam5v5 = entry.getLeaguePoints().intValue();
                            team5v5++;
                        }
                    } else if (entry.getQueueType().equals("RANKED_TEAM_3x3")) {
                        if (team3v3 == 0) {
                            rankTeam3v3 = entry.getTier() + " "
                                    + entry.getRank();
                            lpTeam3v3 = entry.getLeaguePoints().intValue();
                            team3v3++;
                        }
                    } else if (entry.getQueueType().equals("RANKED_SOLO_5x5")) {
                        if (solo5v5 == 0) {
                            rankSolo5v5 = entry.getTier() + " "
                                    + entry.getRank();
                            lpSolo5v5 = entry.getLeaguePoints().intValue();
                            solo5v5++;
                        }
                    }
                }
            }
            if (stats != null && stats.getPlayerStatSummaries() != null) {
                for (PlayerStatSummaries summary : stats
                        .getPlayerStatSummaries()) {
                    if (summary.getPlayerStatSummaryType().equals("RankedTeam3x3")) {
                        numWinsTeam3v3 = summary.getWins().intValue();
                        numLossesTeam3v3 = summary.getLosses().intValue();
                    } else if (summary.getPlayerStatSummaryType().equals("RankedTeam5x5")) {
                        numWinsTeam5v5 = summary.getWins().intValue();
                        numLossesTeam5v5 = summary.getLosses().intValue();
                    } else if (summary.getPlayerStatSummaryType().equals("RankedSolo5x5")) {
                        numWinsSolo5v5 = summary.getWins().intValue();
                        numLossesSolo5v5 = summary.getLosses().intValue();
                    }
                }
            }



            return responseCodeLeague;
        }

        /**
         * Shows our progress bar (spinny thing) and hides the rest of the
         * layout
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            for (int i = 0; i < layoutProfile.getChildCount(); i++) {
                layoutProfile.getChildAt(i).setVisibility(View.INVISIBLE);
            }

            progressBar.setVisibility(View.VISIBLE);


        }

        @Override
        protected void onPostExecute(Integer result) {

            super.onPostExecute(result);

            // HTML partially-colorized strings, green for wins, red for losses
            String winsSolo5v5 = "<font color=#669900>"
                    + String.valueOf(numWinsSolo5v5)
                    + " </font><font color=#000000>Wins</font>";
            String winsTeam5v5 = "<font color=#669900>"
                    + String.valueOf(numWinsTeam5v5)
                    + " </font><font color=#000000>Wins</font>";
            String winsTeam3v3 = "<font color=#669900>"
                    + String.valueOf(numWinsTeam3v3)
                    + " </font><font color=#000000>Wins</font>";
            String lossesSolo5v5 = "<font color=#cc0000>"
                    + String.valueOf(numLossesSolo5v5)
                    + " </font><font color=#000000>Losses</font>";
            String lossesTeam5v5 = "<font color=#cc0000>"
                    + String.valueOf(numLossesTeam5v5)
                    + " </font><font color=#000000>Losses</font>";
            String lossesTeam3v3 = "<font color=#cc0000>"
                    + String.valueOf(numLossesTeam3v3)
                    + " </font><font color=#000000>Losses</font>";

            // Un-hide everything
            for (int i = 0; i < layoutProfile.getChildCount(); i++) {
                layoutProfile.getChildAt(i).setVisibility(View.VISIBLE);
            }
            // Hide progress spinner
            progressBar.setVisibility(View.INVISIBLE);

            Log.i("ResponseCodeId", "" + responseCodeId);
            Log.i("ResponseCodeLeague", "" + responseCodeLeague);

			/*
             * Based on response code, do stuff: 200: Set text in the
			 * TextViews to values from API data. 400+: Display the
			 * appropriate error message, based on response code.
			 */
            switch (result) {
                case 200:
                    if (ProfileFragment.this.isVisible()) {
                        ((ActionBarActivity) getActivity()).getSupportActionBar()
                                .setTitle(summoner.getName());

                        tvFiveLeague.setText(rankTeam5v5 != null ? Utils
                                .toSpecialCase(rankTeam5v5) : "Unranked");
                        tvSoloLeague.setText(rankSolo5v5 != null ? Utils
                                .toSpecialCase(rankSolo5v5) : "Unranked");
                        tvThreeLeague.setText(rankTeam3v3 != null ? Utils
                                .toSpecialCase(rankTeam3v3) : "Unranked");

                        tvFiveLp.setText(lpTeam5v5 + " League Points");
                        tvThreeLp.setText(lpTeam3v3 + " League Points");
                        tvSoloLp.setText(lpSolo5v5 + " League Points");

                        tvWins5v5.setText(Html.fromHtml(winsTeam5v5));
                        tvWins3v3.setText(Html.fromHtml(winsTeam3v3));
                        tvWinsSolo.setText(Html.fromHtml(winsSolo5v5));

                        tvLosses5v5.setText(Html.fromHtml(lossesTeam5v5));
                        tvLosses3v3.setText(Html.fromHtml(lossesTeam3v3));
                        tvLossesSolo.setText(Html.fromHtml(lossesSolo5v5));

                        ivSoloIcon.setImageResource(rankSolo5v5 != null ? Utils
                                .getResIdByName(getActivity(), rankSolo5v5
                                        .toLowerCase(l).replace(" ", "_"))
                                : R.drawable.unranked);
                        ivTeamFiveIcon.setImageResource(rankTeam5v5 != null ? Utils
                                .getResIdByName(getActivity(), rankTeam5v5
                                        .toLowerCase(l).replace(" ", "_"))
                                : R.drawable.unranked);
                        ivTeamThreeIcon
                                .setImageResource(rankTeam3v3 != null ? Utils
                                        .getResIdByName(getActivity(), rankTeam3v3
                                                .toLowerCase(l).replace(" ", "_"))
                                        : R.drawable.unranked);

                        suchBundle.putString("rankTeam5v5", rankTeam5v5);
                        suchBundle.putString("rankSolo5v5", rankSolo5v5);
                        suchBundle.putString("rankTeam3v3", rankTeam3v3);
                        suchBundle.putInt("lpTeam5v5", lpTeam5v5);
                        suchBundle.putInt("lpTeam3v3", lpTeam3v3);
                        suchBundle.putInt("lpSolo5v5", lpSolo5v5);
                        suchBundle.putString("winsTeam5v5", winsTeam5v5);
                        suchBundle.putString("winsTeam3v3", winsTeam3v3);
                        suchBundle.putString("winsSolo5v5", winsSolo5v5);
                        suchBundle.putString("lossesSolo5v5", lossesSolo5v5);
                        suchBundle.putString("lossesTeam3v3", lossesTeam3v3);
                        suchBundle.putString("lossesTeam5v5", lossesTeam5v5);

                    }
                    break;
                case 400:
                case 401:
                    Toast.makeText(getActivity(), "Failed to load!",
                            Toast.LENGTH_SHORT).show();
                    break;
                case 404:
                    Toast.makeText(getActivity(),
                            "Failed to load! League data not found",
                            Toast.LENGTH_SHORT).show();
                    break;
                case 429:
                    Toast.makeText(getActivity(),
                            "Failed to load! Rate limit exceeded",
                            Toast.LENGTH_SHORT).show();
                    break;
                case 500:
                    Toast.makeText(
                            getActivity(),
                            "Failed to load! Internal server error. Try again later",
                            Toast.LENGTH_SHORT).show();
                    break;
                case 503:
                    Toast.makeText(
                            getActivity(),
                            "Failed to load! Service unavailable. Try again later.",
                            Toast.LENGTH_SHORT).show();
                    break;
            }

            Log.i("ProfileFragment", "AsyncTask finished");
        }
    }

    /**
     * Function to get JSON data from API as a String
     *
     * @param url          URL (as String) of the API GET request
     * @param responseCode String tag to determine which response code to set
     * @return String      JSON as String
     */
    private String getJsonFromUrl(String url, String responseCode) {

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

        if (responseCode.equals("id")) {
            responseCodeId = code;
        } else {
            responseCodeLeague = code;
        }

        return jsonResponse;
    }

    private void executeSearch(String query) {
        SetSummonerName suchTask = new SetSummonerName();
        suchTask.execute(query.toLowerCase(Locale.getDefault()));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putAll(suchBundle);
    }

    private void restoreState(Bundle savedInstanceState) {

        String rankTeam5v5 = savedInstanceState.getString("rankTeam5v5");
        String rankTeam3v3 = savedInstanceState.getString("rankTeam3v3");
        String rankSolo5v5 = savedInstanceState.getString("rankSolo5v5");
        int lpTeam5v5 = savedInstanceState.getInt("lpTeam5v5");
        int lpTeam3v3 = savedInstanceState.getInt("lpTeam3v3");
        int lpSolo5v5 = savedInstanceState.getInt("lpSolo5v5");
        String winsSolo5v5 = savedInstanceState.getString("winsSolo5v5");
        String winsTeam5v5 = savedInstanceState.getString("winsTeam5v5");
        String winsTeam3v3 = savedInstanceState.getString("winsTeam3v3");
        String lossesSolo5v5 = savedInstanceState.getString("lossesSolo5v5");
        String lossesTeam5v5 = savedInstanceState.getString("lossesTeam5v5");
        String lossesTeam3v3 = savedInstanceState.getString("lossesTeam3v3");
        Locale l = Locale.getDefault();

        tvFiveLeague.setText(rankTeam5v5 != null ? Utils
                .toSpecialCase(rankTeam5v5) : "Unranked");
        tvSoloLeague.setText(rankSolo5v5 != null ? Utils
                .toSpecialCase(rankSolo5v5) : "Unranked");
        tvThreeLeague.setText(rankTeam3v3 != null ? Utils
                .toSpecialCase(rankTeam3v3) : "Unranked");

        tvFiveLp.setText(lpTeam5v5 + " League Points");
        tvThreeLp.setText(lpTeam3v3 + " League Points");
        tvSoloLp.setText(lpSolo5v5 + " League Points");

        tvWins5v5.setText(Html.fromHtml(winsTeam5v5));
        tvWins3v3.setText(Html.fromHtml(winsTeam3v3));
        tvWinsSolo.setText(Html.fromHtml(winsSolo5v5));

        tvLosses5v5.setText(Html.fromHtml(lossesTeam5v5));
        tvLosses3v3.setText(Html.fromHtml(lossesTeam3v3));
        tvLossesSolo.setText(Html.fromHtml(lossesSolo5v5));

        ivSoloIcon.setImageResource(rankSolo5v5 != null ? Utils
                .getResIdByName(getActivity(), rankSolo5v5
                        .toLowerCase(l).replace(" ", "_"))
                : R.drawable.unranked);
        ivTeamFiveIcon.setImageResource(rankTeam5v5 != null ? Utils
                .getResIdByName(getActivity(), rankTeam5v5
                        .toLowerCase(l).replace(" ", "_"))
                : R.drawable.unranked);
        ivTeamThreeIcon
                .setImageResource(rankTeam3v3 != null ? Utils
                        .getResIdByName(getActivity(), rankTeam3v3
                                .toLowerCase(l).replace(" ", "_"))
                        : R.drawable.unranked);
    }

}

