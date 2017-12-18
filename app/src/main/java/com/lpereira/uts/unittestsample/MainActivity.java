package com.lpereira.uts.unittestsample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.lpereira.uts.unittestsample.entities.App;

import java.util.List;

/**
 * @author lpereira on 17/12/2017.
 */

public class MainActivity extends Activity implements IMainActivity.View {
    private final static int UNINSTALL_REQUEST_CODE = 1;
    private IMainActivity.Presenter presenter;
    private ListView appList;
    private AppListAdapter appAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new MainActivityPresenter();

        setContentView(R.layout.activity_main);
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView viewById = findViewById(R.id.etSearch);
                refreshList(viewById.getText().toString());
            }
        });

        findViewById(R.id.etSearch).clearFocus();
        refreshList(null);

        findViewById(R.id.btnUninstall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App app = appAdapter.getCheckedApps().get(0);
                removeIntent(app);
            }
        });
    }

    private void removeIntent(App app) {
        Intent intent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);
        intent.setData(Uri.parse("package:" + app.getPackageName()));
        intent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
        intent.putExtra("android.intent.extra.UNINSTALL_ALL_USERS", true);
        startActivityForResult(intent, UNINSTALL_REQUEST_CODE);
    }

    private void refreshList(String search) {
        List<App> appsList = presenter.searchInstalledApps(this, search);
        appList = findViewById(R.id.appList);
        appAdapter = new AppListAdapter(this, appsList, this);
        appList.setAdapter(appAdapter);
        appAdapter.notifyDataSetChanged();
    }

    @Override
    public void changeUninstallButton(boolean isChecked) {
        findViewById(R.id.btnUninstall).setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UNINSTALL_REQUEST_CODE) {
            removeNext(resultCode == RESULT_OK);
        }
    }

    private void removeNext(boolean didRemoveApp) {
        App currentApp = appAdapter.getCheckedApps().get(0);
        if (didRemoveApp) {
            appAdapter.remove(currentApp);
        }
        appAdapter.removeCheckedApps(currentApp);
        appAdapter.notifyDataSetChanged();

        if (!appAdapter.getCheckedApps().isEmpty()) {
            App nextApp = appAdapter.getCheckedApps().get(0);
            Toast.makeText(this, "Continuing to remove apps", Toast.LENGTH_SHORT).show();
            removeIntent(nextApp);
        } else {
            changeUninstallButton(false);
            Toast.makeText(this, "All apps uninstalled", Toast.LENGTH_SHORT).show();
        }
    }
}
