package com.epam.dzmitry_slutski.githubapitest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.epam.dzmitry_slutski.githubapitest.R;
import com.epam.dzmitry_slutski.githubapitest.adapter.CommitAdapter;
import com.epam.dzmitry_slutski.githubapitest.model.Commit;
import com.epam.dzmitry_slutski.githubapitest.model.Owner;
import com.epam.dzmitry_slutski.githubapitest.model.Repository;
import com.epam.dzmitry_slutski.githubapitest.network.CommitRequest;
import com.epam.dzmitry_slutski.githubapitest.network.GitHubService;
import com.epam.dzmitry_slutski.githubapitest.network.GitHubSpiceManager;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.squareup.picasso.Picasso;

/**
 * DetailActivity
 * Version info
 * 04.02.2015
 * Created by Dzmitry_Slutski.
 */
public class DetailActivity extends ActionBarActivity {
    public static final String ARG_REPOSITORY = "DetailActivity.Repository";
    private SpiceManager mSpiceManager = new GitHubSpiceManager(GitHubService.class);
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
            Repository repository = params.getParcelable(ARG_REPOSITORY);
            Owner owner = repository.getOwner();

            TextView userName = (TextView) findViewById(R.id.user_name);
            String userNameFormatted = String.format(getString(R.string.user_name_format), owner.getLogin());
            userName.setText(userNameFormatted);

            TextView fullName = (TextView) findViewById(R.id.full_name);
            String fullNameFormatted = String.format(getString(R.string.repository_format), repository.getName());
            fullName.setText(fullNameFormatted);

            TextView description = (TextView) findViewById(R.id.description);
            description.setText(repository.getDescription());

            Picasso.with(this).load(owner.getAvatarUrl()).into((android.widget.ImageView) findViewById(R.id.user_icon));

            mRequest = new CommitRequest(owner.getLogin(), repository.getName());
        }
    }

    @Override
    protected void onStart() {
        mSpiceManager.start(this);
        super.onStart();
        mSpiceManager.execute(mRequest, "github_cache_key", DurationInMillis.ONE_SECOND, new CommitRequestListener());
    }

    public final class CommitRequestListener implements RequestListener<Commit[]> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(DetailActivity.this, "failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(final Commit[] commits) {
            Log.d("CommitRequestListener", "onRequestSuccess: " + commits);

            CommitAdapter adapter = (CommitAdapter) mList.getAdapter();
            adapter.changeCommits(commits);
        }
    }
}
