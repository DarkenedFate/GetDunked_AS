/*
package com.jt.getdunked;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.jt.getdunked.summonerdata.Games;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RecentGamesExpandableAdapter extends BaseExpandableListAdapter {

    private Context c;
    private Games mGame;

    public RecentGamesExpandableAdapter(Context c, Games game) {
        this.c = c;
        mGame = game;
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
        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = View.inflate(c, R.layout.recent_games_stats_view, null);

            vh = new ViewHolder(convertView);
            convertView.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGame;
    }

    @Override
    public int getGroupCount() {
        return 2;
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

    static class ParentViewHolder {
        @InjectView(R.id.tvSectionTitle)
        TextView tvSectionTitle;

        public ParentViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        ParentViewHolder vh;
        if (convertView == null) {

            convertView = View.inflate(c, R.layout.recent_games_popup_titles_view,
                    null);
            vh = new ParentViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ParentViewHolder) convertView.getTag();
        }

        switch (groupPosition) {
            case 0:
                vh.tvSectionTitle.setText("Teams");
                break;
            case 1:
                vh.tvSectionTitle.setText("Game Stats");
                break;
        }

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
}*/
