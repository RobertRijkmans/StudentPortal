package com.example.clibby.studentportal;

public class Reminder {
    private String mSiteURL;
    private String mTitle;

    public Reminder(String mReminderText, String mUrl) {
        this.mSiteURL = mUrl;
        this.mTitle = mReminderText;
    }

    @Override

    public String toString() {

        return mSiteURL;

    }

    public String getmSiteURL() {
        return mSiteURL;
    }

    public void setmSiteURL(String mSiteURL) {
        this.mSiteURL = mSiteURL;
    }
    public String getmTitle(){
        return mTitle;
    }
}
