package com.jt.getdunked;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jt.getdunked.summonerdata.Games;
import com.jt.getdunked.summonerdata.RecentGames;
import com.jt.getdunked.summonerdata.Summoner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RecentGamesFragment extends Fragment {

    @InjectView(R.id.lv_recent_games)
    ListView lvRecentGames;
    @InjectView(R.id.progressBar)
    ProgressBar progressBar;

    private List<Games> listGames = new ArrayList<Games>();
    private Summoner summoner;

    /**
     * The fragment argument representing the section number for this fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section number.
     */
    public static RecentGamesFragment newInstance(int position) {
        RecentGamesFragment fragment = new RecentGamesFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        return fragment;

    }

    public RecentGamesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recent_games, container,
                false);

        ButterKnife.inject(this, rootView);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (listGames.size() > 0) {
            RecentGamesAdapter adapter =
                    new RecentGamesAdapter(getActivity(), listGames);
            lvRecentGames.setAdapter(adapter);

            lvRecentGames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {
                    CustomPopupWindow window = new CustomPopupWindow(getActivity(), listGames.get(i), summoner);
                    window.initiatePopupWindow(getView());
                }
            });
            progressBar.setVisibility(View.INVISIBLE);
        } else {
            new GetRecentGames(getActivity()).execute("nwilliams239");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(
                ARG_SECTION_NUMBER));

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putSerializable("listGames", (ArrayList<Games>) listGames);
        Log.w("RecentGamesFragment", "State saved: " + outState);
    }

    class GetRecentGames extends AsyncTask<String, Void, List<Games>> implements AdapterView.OnItemClickListener {

        private Context mContext;
        private Gson gson = new Gson();

        public GetRecentGames(Context c) {
            mContext = c;
        }

        @Override
        protected List<Games> doInBackground(String... params) {

            if (listGames.size() > 0) listGames.clear();

            String urlForId = "http://54.224.222.135:4567/summoners/by-name/"
                    + params[0].toLowerCase(Locale.getDefault()).replace(" ", "");
            String jsonResponse = getJsonFromUrl(urlForId);
            int summonerId;
            try {
                summoner = gson.fromJson(jsonResponse, Summoner.class);
                summonerId = summoner.getId();
                Log.w("Summoner ID", "Summoner ID for " + summoner.getName()
                        + " found from " + summoner.getSource() + " with id: "
                        + summonerId);
            } catch (JsonSyntaxException e) {
                Log.w("GSON: Summoner ID", "GSON got null from API");
                return null;
            }

            String recentGamesUrl = "https://prod.api.pvp.net/api/lol/na/v1.3/game/by-summoner/"
                    + summoner.getId() + "/recent?api_key=" + Api.KEY;
            String recentGamesJsonResponse = getJsonFromUrl(recentGamesUrl);

            RecentGames recentGames;
            try {
                recentGames = gson.fromJson(recentGamesJsonResponse, RecentGames.class);
            } catch (JsonSyntaxException e) {

                Log.w("GSON: Recent Games", "GSON got null from API");
                return null;
            }

            if (recentGames != null) {
                for (Games game : recentGames.getGames()) {
                    listGames.add(game);
                }
            }

            return listGames;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            lvRecentGames.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onPostExecute(List<Games> listGames) {
            super.onPostExecute(listGames);

            if (!isCancelled()) {
                progressBar.setVisibility(View.INVISIBLE);
                lvRecentGames.setVisibility(View.VISIBLE);

                RecentGamesAdapter adapter = new RecentGamesAdapter(mContext, listGames);
                lvRecentGames.setAdapter(adapter);

                lvRecentGames.setOnItemClickListener(this);
            }
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {
            CustomPopupWindow window = new CustomPopupWindow(getActivity(), listGames.get(i), summoner);
            window.initiatePopupWindow(getView());
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