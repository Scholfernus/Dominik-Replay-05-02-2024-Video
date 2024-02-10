package com.example.video;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/videos", produces = {MediaType.APPLICATION_JSON_VALUE})
public class VideoApi {
    private List<Video> videoList;

    public VideoApi() {
        this.videoList = new ArrayList<>();
        videoList.add(new Video(1L, "[Express Biedrzyckiej] Michał KAMIŃSKI, dr Ewa PIETRZYK-ZIENIEWICZ", "https://www.youtube.com/live/plmwJL8XTSI?si=D9Yq00Aa02rlFSgi"));
        videoList.add(new Video(2L, "Jak mówić, żeby nas słuchano? - Prof. Jerzy Bralczyk", "https://youtu.be/TG4ZAGnlPOY?si=5hn8_7G5IXrJHA-6"));
        videoList.add(new Video(3L, "PiS to MENTALNY REZERWAT! Kamiński: Próbuje PODPALIĆ Polskę PLUSZOWYMI ZAPAŁKAMI", "https://youtu.be/vNJytjW2dYw?si=SzyHYXoyIZVNyPhD"));
        videoList.add(new Video(4L, "SZTURM Kaczyńskiego NISZCZY Polskę! Warzecha NIE GRYZIE się w JĘZYK", "https://youtu.be/aNQWUrv55iE?si=o-GVFnex34p7XbR2"));
    }

    @GetMapping
    public ResponseEntity<List<Video>> getVideo() {
        return ResponseEntity.ok(videoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Video> getVideoById(@PathVariable Long id) {
        Optional<Video> first = videoList.stream().filter(video -> video.getId() == id).findFirst();
        return first.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity addVideo(@RequestBody Video video){
        boolean add = videoList.add(video);
        if(add){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity removeVideoById(@PathVariable Long id){
        Optional<Video> first = videoList.stream().filter(video -> video.getId() == id).findFirst();
        if (first.isPresent()){
            videoList.remove(first.get());
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
