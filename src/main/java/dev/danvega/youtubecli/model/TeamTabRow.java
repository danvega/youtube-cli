package dev.danvega.youtubecli.model;

import java.time.LocalDate;

/*
event, type of content or engagement, start date, end date, location, confirmed, eyeballs live, eyeballs replay, content, contact, notes

 */
public record TeamTabRow(
        String event, // video title
        String type, // youtube
        LocalDate startDate, // publishedAt
        LocalDate endDate,
        String location,
        String confirmed,
        Integer eyeballsLive,
        Integer eyeballsReplay,
        String content,
        String contact,
        String notes
) {

    public void print() {
        int lineSeparatorIndex = content.indexOf(System.lineSeparator());
        String shortDesc = lineSeparatorIndex == -1 ? content.substring(0,100) : content.substring(0,lineSeparatorIndex);
        System.out.printf("%s|%s|%s|%s|%s|%s|%d|%d|%s|%s|%s%n",event.split(" - ")[0],type,startDate,endDate,location,confirmed,eyeballsLive,eyeballsReplay,"about",contact,notes);
    }
}
