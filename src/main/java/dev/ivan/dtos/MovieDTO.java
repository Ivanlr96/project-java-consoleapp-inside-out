package dev.ivan.dtos;

import dev.ivan.value_objects.ShortInfoVO;

import com.google.gson.annotations.SerializedName;

public class MovieDTO {

    private String imdbId;

    @SerializedName("short")
    private ShortInfoVO shortInfo;

    public String getImdbId() {
        return imdbId;
    }

    public ShortInfoVO getShortInfo() {
        return shortInfo;
    }

}
