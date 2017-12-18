package com.lpereira.uts.unittestsample;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import com.lpereira.uts.unittestsample.entities.App;
import com.lpereira.uts.unittestsample.utils.JavaTextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lpereira on 17/12/2017.
 */

public class MainActivityPresenter implements IMainActivity.Presenter {

    @Override
    public List<App> searchInstalledApps(Context context, String searchText) {
        List<App> applications = new ArrayList<>();
        List<PackageInfo> apps = getInstalledApps(context);

        for (PackageInfo pack : apps) {
            //checking if is system app
            if (!isSystemPackage(pack.applicationInfo.flags)) {
                String appName = getApplicationInfoLabelName(context, pack);
                //checking if the app is allowed to be shown
                if (isAppAllowed(searchText, appName)) {
                    applications.add(new App(appName, pack.packageName, pack.versionName));
                }
            }
        }
        return applications;
    }

    public String getApplicationInfoLabelName(Context context, PackageInfo pack) {
        return pack.applicationInfo.loadLabel(context.getPackageManager()).toString();
    }

    public boolean isAppAllowed(String searchText, String name) {
        return JavaTextUtils.isEmpty(searchText) || name.toLowerCase().contains(searchText.toLowerCase());
    }

    public List<PackageInfo> getInstalledApps(Context context) {
        return context.getPackageManager().getInstalledPackages(0);
    }

    private boolean isSystemPackage(int flag) {
        return ((flag & ApplicationInfo.FLAG_SYSTEM) != 0);
    }
}
