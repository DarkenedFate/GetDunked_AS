package com.jt.getdunked;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.jt.getdunked.championdata.AllyTips;
import com.jt.getdunked.championdata.Blurb;
import com.jt.getdunked.championdata.ChampIds;
import com.jt.getdunked.championdata.ChampInfo;
import com.jt.getdunked.championdata.ChampPassive;
import com.jt.getdunked.championdata.ChampSkins;
import com.jt.getdunked.championdata.ChampSpell;
import com.jt.getdunked.championdata.ChampStats;
import com.jt.getdunked.championdata.Champion;
import com.jt.getdunked.championdata.EnemyTips;
import com.jt.getdunked.championdata.Lore;
import com.jt.getdunked.championdata.Partype;
import com.jt.getdunked.championdata.RecommendedItems;
import com.jt.getdunked.championdata.Tags;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity implements
        NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the
     * navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in
     * {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.argb(255, 0, 153, 204)));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

        Bundle args = new Bundle();
        args.putInt("position", position);
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (position) {
            case 1:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.container,
                                ProfileActivity.newInstance(position + 1), "ProfileActivity").commit();
                break;
            default:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.container,
                                ChampionGridFragment.newInstance(args),
                                "ChampionGridFragment").commit();
                break;
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("unused")
    private class GetChampionsForGrid extends
            AsyncTask<Void, Void, List<Champion>> {

        private Context cxt;
        private GridView gvChamps;
        long startTimeMillis;

        private GetChampionsForGrid(Context c, GridView gv) {
            cxt = c;
            gvChamps = gv;
        }

        @Override
        protected List<Champion> doInBackground(Void... params) {

            addAllChampionsToDatabase(null);

            return new ArrayList<Champion>();
        }

        @Override
        protected void onPostExecute(List<Champion> result) {

            long totalTime = System.currentTimeMillis() - startTimeMillis;
            Log.w("TIMER", "Time to get from db: " + totalTime / 1000.0f
                    + " seconds");

            super.onPostExecute(result);
        }
    }

    void addAllChampionsToDatabase(ChampIds champIds) {

        ChampionDatabaseHelper db = new ChampionDatabaseHelper(this);
        // for (Champions champ : champIds.getChampions()) {

        Blurb blurb = JsonUtil.fromJsonUrl(
                "https://prod.api.pvp.net/api/lol/static-data/na/v1.2/champion/"
                        + 201 + "?champData=blurb&api_key=" + Api.KEY,
                Blurb.class
        );
        AllyTips allyTips = JsonUtil.fromJsonUrl(
                "https://prod.api.pvp.net/api/lol/static-data/na/v1.2/champion/"
                        + 201 + "?champData=allytips&api_key="
                        + Api.KEY, AllyTips.class
        );

        EnemyTips enemyTips = JsonUtil.fromJsonUrl(
                "https://prod.api.pvp.net/api/lol/static-data/na/v1.2/champion/"
                        + 201 + "?champData=enemytips&api_key="
                        + Api.KEY, EnemyTips.class
        );

        ChampInfo info = JsonUtil.fromJsonUrl(
                "https://prod.api.pvp.net/api/lol/static-data/na/v1.2/champion/"
                        + 201 + "?champData=info&api_key=" + Api.KEY,
                ChampInfo.class
        );
        Lore lore = JsonUtil.fromJsonUrl(
                "https://prod.api.pvp.net/api/lol/static-data/na/v1.2/champion/"
                        + 201 + "?champData=lore&api_key=" + Api.KEY,
                Lore.class
        );

        Partype partype = JsonUtil.fromJsonUrl(
                "https://prod.api.pvp.net/api/lol/static-data/na/v1.2/champion/"
                        + 201 + "?champData=partype&api_key="
                        + Api.KEY, Partype.class
        );

        ChampPassive passive = JsonUtil.fromJsonUrl(
                "https://prod.api.pvp.net/api/lol/static-data/na/v1.2/champion/"
                        + 201 + "?champData=passive&api_key="
                        + Api.KEY, ChampPassive.class
        );

        RecommendedItems recommended = JsonUtil.fromJsonUrl(
                "https://prod.api.pvp.net/api/lol/static-data/na/v1.2/champion/"
                        + 201 + "?champData=recommended&api_key="
                        + Api.KEY, RecommendedItems.class
        );
        ChampSkins skins = JsonUtil.fromJsonUrl(
                "https://prod.api.pvp.net/api/lol/static-data/na/v1.2/champion/"
                        + 201 + "?champData=skins&api_key=" + Api.KEY,
                ChampSkins.class
        );

        ChampSpell spell = JsonUtil
                .fromJsonUrl(
                        "https://prod.api.pvp.net/api/lol/static-data/na/v1.2/champion/"
                                + 201 + "?champData=spells&api_key="
                                + Api.KEY, ChampSpell.class
                );

        ChampStats stats = JsonUtil.fromJsonUrl(
                "https://prod.api.pvp.net/api/lol/static-data/na/v1.2/champion/"
                        + 201 + "?champData=stats&api_key=" + Api.KEY,
                ChampStats.class
        );
        Log.w("stats", stats.toString());

        Tags tags = JsonUtil.fromJsonUrl(
                "https://prod.api.pvp.net/api/lol/static-data/na/v1.2/champion/"
                        + 201 + "?champData=tags&api_key=" + Api.KEY,
                Tags.class
        );

        Champion champion = new Champion();

        champion.setTags(tags.getTags());
        champion.setStats(stats.getStats());
        champion.setSpells(spell.getSpells());
        champion.setSkins(skins.getSkins());
        champion.setRecommended(recommended.getRecommended());
        champion.setPassive(passive.getPassive());
        champion.setPartype(partype.getPartype());
        champion.setLore(lore.getLore());
        champion.setInfo(info.getInfo());
        champion.setAllytips(allyTips.getAllytips());
        champion.setEnemytips(enemyTips.getEnemytips());
        champion.setBlurb(blurb.getBlurb());
        champion.setId(201);
        champion.setKey(tags.getKey());
        champion.setName(blurb.getName());
        champion.setTitle(blurb.getTitle());

        db.addChampion(champion);

        Log.w("Champion Found", "Champion Found: " + champion.getName());

        // }
    }

}