package cn.smart.bo;

import cn.smart.entity.VideoUrlDO;

import java.util.List;

public class AudioBO {

    private List<VideoUrlBO> stories;

    private List<VideoUrlBO> songs;

    private List<VideoUrlBO> dances;

    public List<VideoUrlBO> getStories() {
        return stories;
    }

    public void setStories(List<VideoUrlBO> stories) {
        this.stories = stories;
    }

    public List<VideoUrlBO> getSongs() {
        return songs;
    }

    public void setSongs(List<VideoUrlBO> songs) {
        this.songs = songs;
    }

    public List<VideoUrlBO> getDances() {
        return dances;
    }

    public void setDances(List<VideoUrlBO> dances) {
        this.dances = dances;
    }
}
