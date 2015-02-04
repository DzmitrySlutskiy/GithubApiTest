package com.epam.dzmitry_slutski.githubapitest.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.epam.dzmitry_slutski.githubapitest.R;
import com.epam.dzmitry_slutski.githubapitest.model.Commit;
import com.epam.dzmitry_slutski.githubapitest.model.Committer;
import com.squareup.picasso.Picasso;

/**
 * RepositoriesAdapter
 * Version info
 * 04.02.2015
 * Created by Dzmitry_Slutski.
 */
public class CommitAdapter extends BaseAdapter {

    private Commit[] mCommits;
    private Context mContext;
    private LayoutInflater mInflater;
    private String mUserNameFString;
    private String mEmailFString;

    public CommitAdapter(Context context) {
        mContext = context.getApplicationContext();
        mInflater = LayoutInflater.from(mContext);
        mUserNameFString = mContext.getString(R.string.user_name_format);
        mEmailFString = mContext.getString(R.string.email_format);
    }

    @Override
    public int getCount() {
        return mCommits != null ? mCommits.length : 0;
    }

    @Override
    public Object getItem(int position) {
        return mCommits != null ? mCommits[position] : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_commit, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Commit commit = mCommits[position];
        Committer author = commit.getAuthor();

        String icon = commit.getIcon();
        if (TextUtils.isEmpty(icon)) {
            holder.mIcon.setImageDrawable(null);
        } else {
            Picasso.with(mContext).load(icon).into(holder.mIcon);
        }
        holder.mUserName.setText(String.format(mUserNameFString, author.getName()));
        holder.mEmail.setText(String.format(mEmailFString, author.getMail()));
        holder.mDate.setText(author.getDate());
        holder.mMessage.setText(commit.getMessage());

        return convertView;
    }

    public void changeCommits(Commit[] reps) {
        mCommits = reps;
        notifyDataSetChanged();
    }

    private class ViewHolder {
        ImageView mIcon;
        TextView mUserName;
        TextView mEmail;
        TextView mDate;
        TextView mMessage;

        ViewHolder(View view) {
            mIcon = (ImageView) view.findViewById(R.id.user_icon);
            mUserName = (TextView) view.findViewById(R.id.user_name);
            mEmail = (TextView) view.findViewById(R.id.email);
            mDate = (TextView) view.findViewById(R.id.date);
            mMessage = (TextView) view.findViewById(R.id.message);
        }
    }
}