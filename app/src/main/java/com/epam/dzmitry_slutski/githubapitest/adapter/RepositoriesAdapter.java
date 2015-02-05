package com.epam.dzmitry_slutski.githubapitest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.epam.dzmitry_slutski.githubapitest.R;
import com.epam.dzmitry_slutski.githubapitest.model.GitHubOwner;
import com.epam.dzmitry_slutski.githubapitest.model.GitHubRepository;
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

    private List<GitHubRepository> mRepositories;
    private Context mContext;
    private LayoutInflater mInflater;
    private String mUserNameFString;
    private String mRepNameFString;

    public RepositoriesAdapter(Context context) {
        mRepositories = new ArrayList<>();
        mContext = context.getApplicationContext();
        mInflater = LayoutInflater.from(mContext);
        mUserNameFString = mContext.getString(R.string.user_name_format);
        mRepNameFString = mContext.getString(R.string.repository_format);
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
        GitHubRepository gitHubRepository = mRepositories.get(position);

        holder.mRepoName.setText(String.format(mUserNameFString, gitHubRepository.getFullName()));
        GitHubOwner owner = gitHubRepository.getOwner();
        if (owner != null) {
            holder.mUserName.setText(String.format(mRepNameFString, owner.getLogin()));
            Picasso.with(mContext).load(owner.getAvatarUrl()).placeholder(R.mipmap.ic_launcher).into(holder.mIcon);
        }else{
            Log.d("RepositoriesAdapter","RepositoriesAdapter.getView: owner == null");
        }
        return convertView;
    }

    public void changeRepositories(List<GitHubRepository> reps) {
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
