package com.example.video;

import lombok.Getter;

@Getter
public class Video {
    private Long id;
    private String title;
    private String url;

    public Video(Long id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
