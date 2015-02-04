package com.epam.dzmitry_slutski.githubapitest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.epam.dzmitry_slutski.githubapitest.R;
import com.epam.dzmitry_slutski.githubapitest.model.Owner;
import com.epam.dzmitry_slutski.githubapitest.model.Repository;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * RepositoriesAdapter
 * Version info
 * 04.02.2015
 * Created by Dzmitry_Slutski.
 */
public class RepositoriesAdapter extends BaseAdapter {

    private List<Repository> mRepositories;
    private Context mContext;
    private LayoutInflater mInflater;

    public RepositoriesAdapter(Context context) {
        mRepositories = new ArrayList<>();
        mContext = context.getApplicationContext();
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mRepositories.size();
    }

    @Override
    public Object getItem(int position) {
        return mRepositories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mRepositories.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_repository, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Repository repository = mRepositories.get(position);

        holder.mRepoName.setText(repository.getFullName());
        Owner owner = repository.getOwner();
        if (owner != null) {
            holder.mUserName.setText(owner.getLogin());
            Picasso.with(mContext).load(owner.getAvatarUrl()).into(holder.mIcon);
        }
        return convertView;
    }

    public void changeRepositories(List<Repository> reps) {
        mRepositories.clear();
        if (reps != null && reps.size() > 0) {
            mRepositories.addAll(reps);
        }
        notifyDataSetChanged();
    }

    private class ViewHolder {
        ImageView mIcon;
        TextView mUserName;
        TextView mRepoName;

        ViewHolder(View view) {
            mIcon = (ImageView) view.findViewById(R.id.user_icon);
            mUserName = (TextView) view.findViewById(R.id.user_name);
            mRepoName = (TextView) view.findViewById(R.id.repository_name);
        }
    }
}
