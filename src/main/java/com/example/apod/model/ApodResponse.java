package com.example.apod.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApodResponse {
	private String date;
    private String title;

    @JsonProperty("explanation")
    private String explanation;

    @JsonProperty("media_type")
    private String mediaType;

    @JsonProperty("url")
    private String url;

    @JsonProperty("hdurl")
    private String hdUrl;

    private String copyright;

}
