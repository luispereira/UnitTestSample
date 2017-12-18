package com.lpereira.uts.unittestsample.entities;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author lpereira on 17/12/2017.
 */
public class AppsTest {
    @Test
    public void getPackageName() throws Exception {
        //GIVEN
        String packageName = "com.vodafone.myvodafone";
        //WHEN
        App app = new App("vodafone", "com.vodafone.myvodafone", "7.0.0");
        //THEN
        Assert.assertEquals(packageName, app.getPackageName());
    }

    @Test
    public void getVersionName() throws Exception {
        //GIVEN
        String versionName = "7.0.0";
        //WHEN
        App app = new App("vodafone", "com.vodafone.myvodafone", "7.0.0");
        //THEN
        Assert.assertEquals(versionName, app.getVersionName());
    }

    @Test
    public void getName() throws Exception {
        //GIVEN
        String name = "vodafone";
        //WHEN
        App app = new App("vodafone", "com.vodafone.myvodafone", "7.0.0");
        //THEN
        Assert.assertEquals(name, app.getName());
    }

    @Test
    public void equalsTest() throws Exception{
        //GIVEN
        App apps = new App("Shazam", "com.shazam.ula", "1.0.0");
        App appsEqual = new App("Shazam", "com.shazam.ula", "1.0.0");
        App appsNotEqual = new App("Shazam2", "com.shazam.ula", "1.0.0");
        //WHEN
        boolean isAppEqual = apps.equals(appsEqual);
        boolean isAppNotEqual = apps.equals(appsNotEqual);
        //THEN
        Assert.assertTrue(isAppEqual);
        Assert.assertFalse(isAppNotEqual);
        Assert.assertEquals(apps, appsEqual);
    }

    @Test
    public void hashCodeTest() throws Exception{
        //GIVEN
        App apps = new App("Shazam", "com.shazam.ula", "1.0.0");
        App appsEqual = new App("Shazam", "com.shazam.ula", "1.0.0");
        App appsNotEqual = new App("Shazam", "com.shazam.ula2", "1.0.0");
        //WHEN
        boolean isAppEqual = apps.hashCode() == appsEqual.hashCode();
        boolean isAppNotEqual = apps.hashCode() == appsNotEqual.hashCode();
        //THEN
        Assert.assertTrue(isAppEqual);
        Assert.assertFalse(isAppNotEqual);
    }
}