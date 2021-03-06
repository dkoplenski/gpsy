package com.gpsy.domain.spotify;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "recommended_tracks")
@NoArgsConstructor
@Getter
public class RecommendedTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long recommendedTrackId;

    @Column(name = "track_string_id")
    private String trackStringId;

    private String title;

    private String authors;

    private String sample;

    private RecommendedTrack(String trackStringId, String title, String authors, String sample) {
        this.trackStringId = trackStringId;
        this.title = title;
        this.authors = authors;
        this.sample = sample;
    }

    public static class Builder {

        private String trackStringId;

        private String title;

        private String artists;

        private String sample;


        public Builder stringId(String trackStringId) {
            this.trackStringId = trackStringId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;

        }

        public Builder artists(String artists) {
            this.artists = artists;
            return this;
        }

        public Builder sample(String sample) {
            this.sample = sample;
            return this;
        }

        public RecommendedTrack build() {
            return new RecommendedTrack(trackStringId, title, artists, sample);
        }
    }
}
