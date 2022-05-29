package com.example.cookieclickerv2.Model;

import com.example.cookieclickerv2.BuildConfig;

public class SystemUpdate {

    String URL;
    int VersionCode;

    public SystemUpdate() {
        VersionCode = BuildConfig.VERSION_CODE;
    }

    public SystemUpdate(String url, int versionCode) {
        URL = url;
        this.VersionCode = versionCode;
    }

    public String getURL() {
        return URL;
    }

    public int getVersionCode() {
        return VersionCode;
    }
}
