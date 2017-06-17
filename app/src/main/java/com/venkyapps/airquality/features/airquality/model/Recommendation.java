package com.venkyapps.airquality.features.airquality.model;

/**
 * Created by venkatesh on 17-Jun-17.
 */

public class Recommendation {
    public String title;
    public String message;

    public Recommendation(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
