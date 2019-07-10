package com.gpsy.mapper.spotify;

import com.gpsy.domain.spotify.*;
import com.gpsy.domain.spotify.PlaylistTrack;
import com.gpsy.domain.spotify.dto.*;
import com.wrapper.spotify.model_objects.specification.*;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrackMapper {

    public PopularTrack mapSpotifyTrackToDbPopularTrack(Track spotifyDbTrack) {
        return new PopularTrack.PopularTrackBuiilder()
                                .stringId(spotifyDbTrack.getId())
                                .title(spotifyDbTrack.getName())
                                .artists(UniversalMappingMethods.simplifyArtist(spotifyDbTrack.getArtists()))
                                .popularity(spotifyDbTrack.getPopularity())
                                .build();
    }

    public RecentPlayedTrack mapSpotifyTrackToDbRecentPlayedTrack(PlayHistory playHistory) {
        TrackSimplified recentTrack = playHistory.getTrack();
        return new RecentPlayedTrack.RecentPlayedTrackBuilder()
                                      .stringId(recentTrack.getId())
                                      .title(recentTrack.getName())
                                      .artists(UniversalMappingMethods.simplifyArtist(recentTrack.getArtists()))
                                      .playDate(playHistory.getPlayedAt())
                                      .build();
    }

    public List<RecentPlayedTrackDto> mapToRecentPlayedTrackDtos(List<RecentPlayedTrack> recentPlayedTracks) {

        return recentPlayedTracks.stream()
                .map(track -> new RecentPlayedTrackDto(track.getTrackStringId(), track.getTitle(), track.getArtists(), track.getPlayDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()))
                .collect(Collectors.toList());
    }

    public List<MostFrequentTrackDto> mapToPopularTrackDtoList(List<MostFrequentTrack> dbPopularTracks) {
        return dbPopularTracks.stream()
                .map(track -> new MostFrequentTrackDto(track.getTrackStringId(), track.getTitle(), track.getArtists(), track.getPopularity()))
                .collect(Collectors.toList());
    }

    public List<RecommendedTrackDto> mapToRecommendedTrackDtoList(List<RecommendedTrack> recommendedTracks) {
        return recommendedTracks.stream()
                .map(track -> new RecommendedTrackDto(track.getTrackStringId(), track.getTitle(), track.getAuthors(), track.getSample()))
                .collect(Collectors.toList());
    }

    public List<PlaylistTrack> mapToPlaylistTrack(List<PlaylistTrackDto> playlistTrackDtoList) {
        return playlistTrackDtoList.stream()
                .map(playlistTrackDto -> new PlaylistTrack.PlaylistTrackBuilder()
                                                            .stringId(playlistTrackDto.getTrackStringId())
                                                            .artists(playlistTrackDto.getArtists())
                                                            .title(playlistTrackDto.getTitle())
                                                            .build()
                )
                .collect(Collectors.toList());
    }

    public List<RecommendedTrackForPlaylistDto> mapToRecommendedTrackForPlaylistDto(List<RecommendedPlaylistTrack> recommendedPlaylistTrack) {
        return recommendedPlaylistTrack.stream()
                .map(track -> new RecommendedTrackForPlaylistDto(track.getTrackStringId(), track.getTitle(), track.getArtists(), track.getSample()))
                .collect(Collectors.toList());
    }

    public List<PopularTrackDto> mapPopularTrackToPopularTrackDtoList(List<PopularTrack> popularTracks) {
        return popularTracks.stream()
                .map(track -> new PopularTrackDto(track.getTrackStringId(), track.getTitle(), track.getArtists(), track.getPopularity()))
                .collect(Collectors.toList());
    }

    public List<SearchTrackDto> mapToSearchTrackDto(List<Track> tracks) {

        return tracks.stream()
                .map(track -> new SearchTrackDto(track.getId(), track.getName(), UniversalMappingMethods.simplifyArtist(track.getArtists()).toString(), track.getPreviewUrl()))
                .collect(Collectors.toList());
    }
}
