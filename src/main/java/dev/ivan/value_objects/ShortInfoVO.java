package dev.ivan.value_objects;


import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ShortInfoVO {

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((genres == null) ? 0 : genres.hashCode());
        result = prime * result + ((releaseYear == null) ? 0 : releaseYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ShortInfoVO other = (ShortInfoVO) obj;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (genres == null) {
            if (other.genres != null)
                return false;
        } else if (!genres.equals(other.genres))
            return false;
        if (releaseYear == null) {
            if (other.releaseYear != null)
                return false;
        } else if (!releaseYear.equals(other.releaseYear))
            return false;
        return true;
    }

    @SerializedName("name")
    private String title;

    @SerializedName("genre")
    private List<String> genres;

    @SerializedName("datePublished")
    private String releaseYear;

    public String getTitle() {
        return title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getReleaseYear() {
        return releaseYear;
    }
}
