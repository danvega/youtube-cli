package dev.danvega.youtubecli.model;

import java.util.List;

public record SearchListResponse(
        String kind,
        String etag,
        String regionCode,
        String nextPageToken,
        String prevPageToken,
        PageInfo pageInfo,
        List<SearchResult> items
) {
}