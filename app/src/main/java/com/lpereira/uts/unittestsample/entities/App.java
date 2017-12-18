package com.lpereira.uts.unittestsample.entities;

/**
 * @author lpereira on 17/12/2017.
 */

public class App {
    private final String packageName;
    private final String versionName;
    private final String name;

    public App(String name, String packageName, String versionName) {
        this.packageName = packageName;
        this.versionName = versionName;
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getVersionName() {
        return versionName;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        App apps = (App) o;

        return (packageName != null ? packageName.equals(apps.packageName) : apps.packageName == null) && (versionName != null ? versionName.equals(apps.versionName) :
                apps.versionName == null) && (name != null ? name.equals(apps.name) : apps.name == null);
    }

    @Override
    public int hashCode() {
        int result = packageName != null ? packageName.hashCode() : 0;
        result = 31 * result + (versionName != null ? versionName.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
