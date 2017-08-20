
package com.bgt.multipleviewrecycleview.model;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

public class FeedData implements Serializable, Comparable<FeedData>{

    private Integer id;
    private String title;
    private String excerpt;
    private String description;
    private String date;
    private String type;
    private String link;
    private List<FeedsImage> images;
    private String video;

    private boolean exclusive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<FeedsImage> getImages() {
        return images;
    }

    public void setImages(List<FeedsImage> images) {
        this.images = images;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public void setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
    }

    @Override
    public int compareTo(@NonNull FeedData o) {
        if(getDate() == null || o.getDate() == null)
            return 0;
        return getDate().compareTo(o.getDate());
    }
}
