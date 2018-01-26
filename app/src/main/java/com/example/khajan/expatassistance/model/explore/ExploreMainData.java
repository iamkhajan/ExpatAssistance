
package com.example.khajan.expatassistance.model.explore;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExploreMainData {

    @SerializedName("refresh_time")
    @Expose
    private String refreshTime;
    @SerializedName("meta_data")
    @Expose
    private String metaData;
    @SerializedName("events")
    @Expose
    private List<Event> events = null;

    public String getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(String refreshTime) {
        this.refreshTime = refreshTime;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

}
