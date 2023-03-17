package dev.danvega.youtubecli.service;

import dev.danvega.youtubecli.client.YouTubeDataClient;
import dev.danvega.youtubecli.config.YouTubeConfigProps;
import dev.danvega.youtubecli.model.SearchListResponse;
import dev.danvega.youtubecli.model.SearchResult;
import dev.danvega.youtubecli.model.Video;
import dev.danvega.youtubecli.model.VideoListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

    private static final Logger log = LoggerFactory.getLogger(VideoService.class);
    private final YouTubeDataClient client;
    private final List<Video> videos = new ArrayList<>();
    private final YouTubeConfigProps youtubeConfig;

    public VideoService(YouTubeDataClient client, YouTubeConfigProps youtubeConfig) {
        this.client = client;
        this.youtubeConfig = youtubeConfig;
//        this.loadAllVideos("");
        this.loadAllVideosThisYear("");
        log.info("Loaded {} videos", videos.size());
    }

    /**
     * Returns all videos
     * @return The list of videos.
     */
    public List<Video> findAll() {
        return videos;
    }

    /**
     * Returns the most recent videos
     * @param max The maximum number of videos to return.
     * @return The list of videos.
     */
    public List<Video> findRecent(Integer max) {
        return videos.stream().limit(max).toList();
    }

    /**
     * Returns all videos from the current year
     * @param year The year to filter by.
     * @return The list of videos from the year being filtered on.
     */
    public List<Video> findAllByYear(Integer year) {
        return videos.stream().filter(v -> v.snippet().publishedAt().getYear() == year).toList();
    }

    /**
     * The YouTube search API only returns 50 results per page. This method recursively calls itself until there are no more pages.
     * @param pageToken The page token to use for the next page.
     */
    public void loadAllVideos(String pageToken) {
        SearchListResponse search = client.search(youtubeConfig.channelId(), youtubeConfig.key(), 50, pageToken);
        for(SearchResult result : search.items()) {
            if(result.id().kind().equals("youtube#video")) {
                VideoListResponse videoListResponse = client.getVideo(result.id().videoId(), youtubeConfig.key());
                videos.addAll(videoListResponse.items());
            }
        }

        // recursively call this method until there are no more pages
        if(search.nextPageToken() != null && !search.nextPageToken().isEmpty()) {
            loadAllVideos(search.nextPageToken());
        }

    }

    public void loadAllVideosThisYear(String pageToken) {
        SearchListResponse search = client.searchByPublishedAfter(youtubeConfig.channelId(), youtubeConfig.key(), 50, pageToken, "2023-01-01T00:00:00Z");
        for(SearchResult result : search.items()) {
            if(result.id().kind().equals("youtube#video")) {
                VideoListResponse videoListResponse = client.getVideo(result.id().videoId(), youtubeConfig.key());
                videos.addAll(videoListResponse.items());
            }
        }

        // recursively call this method until there are no more pages
        if(search.nextPageToken() != null && !search.nextPageToken().isEmpty()) {
            loadAllVideos(search.nextPageToken());
        }

    }

}
