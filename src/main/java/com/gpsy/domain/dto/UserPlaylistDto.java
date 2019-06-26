package com.gpsy.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPlaylistDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("playlistStringId")
    private String playlistStringId;

    @JsonProperty("tracks")
    private List<PlaylistTrackDto> tracks;

}
