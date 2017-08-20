package com.bgt.multipleviewrecycleview.model;


public class NewType {

    public static final int INVALID_NEW = -1;
    public static final int SINGLE_PHOTO = 0;
    public static final int CAROUSEL = 1;
    public static final int VIDEO = 2;

    public static int getNewType(FeedData feedData) {

        if (feedData != null) {
            if (feedData.getVideo() != null && !feedData.getVideo().isEmpty()) {
                return VIDEO;

            } else if (feedData.getImages() != null && !feedData.getImages().isEmpty()) {
                if (feedData.getImages().size() > 1) {
                    return CAROUSEL;
                } else {
                    return SINGLE_PHOTO;
                }
            }

        }

        return INVALID_NEW;
    }
}
