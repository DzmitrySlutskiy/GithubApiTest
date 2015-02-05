package com.epam.dzmitry_slutski.githubapitest.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.epam.dzmitry_slutski.githubapitest.R;
import com.epam.dzmitry_slutski.githubapitest.adapter.RepositoriesAdapter;
import com.epam.dzmitry_slutski.githubapitest.model.GitHubRepository;
import com.epam.dzmitry_slutski.githubapitest.model.Repository;
import com.epam.dzmitry_slutski.githubapitest.network.RepositoryRequest;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.SpiceRequest;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.List;


public class MainActivity extends BaseActivity implements SearchView.OnQueryTextListener, AdapterView.OnItemClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();

    private ListView mList;
    private View mEmptyData;

    private RepositoryRequest mRequest;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmptyData = findViewById(R.id.empty_data);

        mList = (ListView) findViewById(android.R.id.list);
        mList.setAdapter(new RepositoriesAdapter(this));
        mList.setOnItemClickListener(this);
        mRequest = new RepositoryRequest("");

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            doSearch(query);
        }
    }

    private void showEmpty(boolean isEmptyData) {
        mEmptyData.setVisibility(isEmptyData ? View.VISIBLE : View.GONE);
        mList.setVisibility(!isEmptyData ? View.VISIBLE : View.GONE);
    }

    private void doSearch(String searchKey) {
        mRequest.changeSearchKey(searchKey);
        executeRequest();
    }

    private void updateRepositories(final GitHubRepository repositories) {
        if (repositories != null) {
            List<Repository> items = repositories.getItems();
            if (items != null && items.size() > 0) {
                Log.d(TAG, "updateRepositories: " + items.size());
                RepositoriesAdapter adapter = (RepositoriesAdapter) mList.getAdapter();
                adapter.changeRepositories(items);
                showEmpty(false);
            } else {
                showEmpty(true);
            }
        } else {
            Log.d(TAG, "updateRepositories NULL!");
            showEmpty(true);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            doSearch(query);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        doSearch(s);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RepositoriesAdapter adapter = (RepositoriesAdapter) mList.getAdapter();
        Repository item = (Repository) adapter.getItem(position);

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.ARG_REPOSITORY, item);

        startActivity(intent);
    }

    @Override
    protected SpiceRequest getRequest() {
        return mRequest;
    }

    @Override
    protected RequestListener getListener() {
        return new RepositoryRequestListener();
    }

    public final class RepositoryRequestListener implements RequestListener<GitHubRepository> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Log.d(TAG, "SpiceException: " + spiceException);
            Toast.makeText(MainActivity.this, "failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(final GitHubRepository repositories) {
            Log.d(TAG, "onRequestSuccess: " + repositories);
            updateRepositories(repositories);
        }
    }
}
