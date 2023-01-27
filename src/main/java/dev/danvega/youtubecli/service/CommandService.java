package dev.danvega.youtubecli.service;

import dev.danvega.youtubecli.model.TeamTabRow;
import dev.danvega.youtubecli.model.Video;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandService {

    public TableBuilder listToArrayTableModel(List<Video> videos) {
        ArrayTableModel model = new ArrayTableModel(videos.stream()
                .map(v -> new String[]{v.id(), v.snippet().title(), v.snippet().publishedAt().toString()})
                .toArray(String[][]::new));
        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.addInnerBorder(BorderStyle.fancy_light_double_dash);
        return tableBuilder;
    }

    public List<TeamTabRow> videosToTeamTabRows(List<Video> videos) {
        return videos.stream()
                .map(video -> new TeamTabRow(video.snippet().title(),
                        "YouTube",
                        video.snippet().publishedAt().toLocalDate(),
                        video.snippet().publishedAt().toLocalDate(),
                        "Virtual",
                        "Y",
                        video.statistics().viewCount(),
                        video.statistics().viewCount(),
                        video.snippet().description(),
                        "n/a",
                        "n/a"))
                .toList();
    }


}
