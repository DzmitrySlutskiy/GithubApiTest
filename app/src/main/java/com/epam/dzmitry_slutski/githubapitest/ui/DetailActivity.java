package com.epam.dzmitry_slutski.githubapitest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.epam.dzmitry_slutski.githubapitest.R;
import com.epam.dzmitry_slutski.githubapitest.adapter.CommitAdapter;
import com.epam.dzmitry_slutski.githubapitest.model.GitHubCommit;
import com.epam.dzmitry_slutski.githubapitest.model.GitHubOwner;
import com.epam.dzmitry_slutski.githubapitest.model.GitHubRepository;
import com.epam.dzmitry_slutski.githubapitest.network.request.CommitRequest;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.SpiceRequest;
import com.octo.android.robospice.request.listener.RequestListener;
import com.squareup.picasso.Picasso;

/**
 * DetailActivity
 * Version info
 * 04.02.2015
 * Created by Dzmitry_Slutski.
 */
public class DetailActivity extends BaseActivity {
    public static final String ARG_REPOSITORY = "DetailActivity.Repository";

    private CommitRequest mRequest = null;
    private ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mList = (ListView) findViewById(R.id.list);
        mList.setAdapter(new CommitAdapter(this));
        Intent intent = getIntent();

        Bundle params = intent.getExtras();
        if (params != null) {
            GitHubRepository gitHubRepository = params.getParcelable(ARG_REPOSITORY);
            GitHubOwner owner = gitHubRepository.getOwner();

            TextView userName = (TextView) findViewById(R.id.user_name);
            String userNameFormatted = String.format(getString(R.string.user_name_format), owner.getLogin());
            userName.setText(userNameFormatted);

            TextView fullName = (TextView) findViewById(R.id.full_name);
            String fullNameFormatted = String.format(getString(R.string.repository_format), gitHubRepository.getName());
            fullName.setText(fullNameFormatted);

            TextView description = (TextView) findViewById(R.id.description);
            description.setText(gitHubRepository.getDescription());

            Picasso.with(this).load(owner.getAvatarUrl()).placeholder(R.mipmap.ic_launcher).into((android.widget.ImageView) findViewById(R.id.user_icon));

            mRequest = new CommitRequest(owner.getLogin(), gitHubRepository.getName());
        }
    }

    @Override
    protected SpiceRequest getRequest() {
        return mRequest;
    }

    @Override
    protected RequestListener getListener() {
        return new CommitRequestListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        executeRequest();
    }

    public final class CommitRequestListener implements RequestListener<GitHubCommit[]> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(DetailActivity.this, "failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(final GitHubCommit[] commits) {
            Log.d("CommitRequestListener", "onRequestSuccess: " + commits);

            CommitAdapter adapter = (CommitAdapter) mList.getAdapter();
            adapter.changeCommits(commits);
        }
    }
}
