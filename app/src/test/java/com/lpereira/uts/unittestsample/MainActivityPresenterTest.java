package com.lpereira.uts.unittestsample;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import com.lpereira.uts.unittestsample.entities.App;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.List;

import static android.content.pm.ApplicationInfo.FLAG_SYSTEM;

/**
 * @author lpereira on 17/12/2017.
 */
public class MainActivityPresenterTest {
    @Test
    public void searchInstalledApps() throws Exception {
        //GIVEN
        final String appName = "Shazam";
        MainActivityPresenter mainActivityPresenter = Mockito.spy(new MainActivityPresenter());
        PackageInfo systemPackageInfo = new PackageInfo();
        systemPackageInfo.versionName = "1.1.1";
        systemPackageInfo.packageName = "system.app";
        systemPackageInfo.applicationInfo = new ApplicationInfo();
        systemPackageInfo.applicationInfo.flags = FLAG_SYSTEM;

        PackageInfo packageInfo = new PackageInfo();
        packageInfo.versionName = "7.1.1";
        packageInfo.packageName = "com.vodafone.myvodafone";
        packageInfo.applicationInfo = new ApplicationInfo();

        App expected = new App(appName, packageInfo.packageName, packageInfo.versionName);
        final List<PackageInfo> packageInfos = Arrays.asList(systemPackageInfo, packageInfo);

        //Mocks
        Context context = Mockito.mock(Context.class);
        //this
//        Mockito.when(packageManager.getInstalledPackages(Mockito.anyInt())).thenReturn(packageInfos);
        //or
        Mockito.doAnswer(new Answer() {
            @Override
            public List<PackageInfo> answer(InvocationOnMock invocation) throws Throwable {
                return packageInfos;
            }
        }).when(mainActivityPresenter).getInstalledApps(Mockito.any(Context.class));
        //this will allow to override the method and do what I want instead
        Mockito.doAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                return appName;
            }
        }).when(mainActivityPresenter).getApplicationInfoLabelName(Mockito.any(Context.class), Mockito.any(PackageInfo.class));

        //WHEN
        List<App> apps = mainActivityPresenter.searchInstalledApps(context, null);

        //THEN
        Assert.assertEquals(apps.get(0), expected);
    }

    @Test
    public void isAppAllowed() throws Exception {
        //GIVEN
        MainActivityPresenter presenter = new MainActivityPresenter();
        String textToSearch = "vodaf";
        String name = "vodafone";
        String otherName = "nothing";
        String nullName = null;
        String emptyName = "";
        String upperName = "VODAFONE";
        //WHEN
        boolean appAllowed = presenter.isAppAllowed(textToSearch, name);
        boolean appNotAllowed = presenter.isAppAllowed(textToSearch, otherName);
        boolean nullAppAllowed = presenter.isAppAllowed(nullName, otherName);
        boolean emptyNameAllowed = presenter.isAppAllowed(emptyName, otherName);
        boolean upperNameAllowed = presenter.isAppAllowed(textToSearch, upperName);
        //THEN
        Assert.assertTrue(appAllowed);
        Assert.assertFalse(appNotAllowed);
        Assert.assertTrue(nullAppAllowed);
        Assert.assertTrue(emptyNameAllowed);
        Assert.assertTrue(upperNameAllowed);
    }

}