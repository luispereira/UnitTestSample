package com.lpereira.uts.unittestsample;

import android.content.Context;
import com.lpereira.uts.unittestsample.entities.App;

import java.util.List;

/**
 * @author lpereira on 17/12/2017.
 */

public interface IMainActivity {
    interface View{

        void changeUninstallButton(boolean isChecked);
    }

    interface Presenter{

        List<App> searchInstalledApps(Context context, String searchText);
    }
}
