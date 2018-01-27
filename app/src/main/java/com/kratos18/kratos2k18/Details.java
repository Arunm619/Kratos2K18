package com.kratos18.kratos2k18;

/**
 * Created by root on 27/1/18.
 */
public class Details {
    private String time;

    private String userid;
    private String ambassadorID;

    Details(String time, String UUID, String ambassadorID) {
        this.time = time;
        this.userid = UUID;
        this.ambassadorID = ambassadorID;
    }

    public Details() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAmbassadorID() {
        return ambassadorID;
    }

    public void setAmbassadorID(String ambassadorID) {
        this.ambassadorID = ambassadorID;
    }
}
