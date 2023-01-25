package dev.danvega.youtubecli.service;

import dev.danvega.youtubecli.client.YouTubeDataClient;
import dev.danvega.youtubecli.config.YouTubeConfigProps;
import dev.danvega.youtubecli.model.SearchListResponse;
import dev.danvega.youtubecli.model.SearchResult;
import dev.danvega.youtubecli.model.Video;
import dev.danvega.youtubecli.model.VideoListResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

    private final YouTubeDataClient client;
    private final List<Video> videos = new ArrayList<>();
    private final YouTubeConfigProps youtubeConfig;

    public VideoService(YouTubeDataClient client, YouTubeConfigProps youtubeConfig) {
        this.client = client;
        this.youtubeConfig = youtubeConfig;
    }

    public List<Video> findAll() {
        return videos;
    }
    public List<Video> findRecent(Integer max) {
        return videos;
    }

    @PostConstruct
    private void loadAllVideos() {
        SearchListResponse search = client.search(youtubeConfig.channelId(), youtubeConfig.key(), 999);
        for(SearchResult result : search.items()) {
            VideoListResponse videoListResponse = client.getVideo(result.id().videoId(), youtubeConfig.key());
            videos.addAll(videoListResponse.items());
        }   
    }

}
