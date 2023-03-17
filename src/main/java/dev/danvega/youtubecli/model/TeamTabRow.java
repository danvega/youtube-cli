package dev.danvega.youtubecli.model;

import java.time.LocalDate;

public record TeamTabRow(
        String event,
        String type,
        LocalDate startDate,
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
        String shortDesc = content.length() > 50 ? content.substring(0,50).replaceAll(",","") : content;
        System.out.printf("%s,%s,%s,%s,%s,%s,%d,%d,%s,%s,%s%n",event,type,startDate,endDate,location,confirmed,eyeballsLive,eyeballsReplay,shortDesc,contact,notes);
    }
}
