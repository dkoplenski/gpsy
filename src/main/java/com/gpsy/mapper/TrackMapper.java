package com.gpsy.mapper;

import com.gpsy.domain.DbMostFrequentTrack;
import com.gpsy.domain.DbPopularTrack;
import com.gpsy.domain.DbRecentPlayedTrack;
import com.gpsy.domain.DbRecommendedTrack;
import com.gpsy.domain.PlaylistTrack;
import com.gpsy.domain.dto.PlaylistTrackDto;
import com.gpsy.domain.dto.PopularTrackDto;
import com.gpsy.domain.dto.RecentPlayedTrackDto;
import com.gpsy.domain.dto.RecommendedTrackDto;
import com.wrapper.spotify.model_objects.specification.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrackMapper {

    public DbPopularTrack mapSpotifyTrackToDbPopularTrack(Track spotifyDbTrack) {
        ArtistSimplified[] artists = spotifyDbTrack.getArtists();
        return new DbPopularTrack(spotifyDbTrack.getId(), spotifyDbTrack.getName(), UniversalMethods.simplifyArtist(artists).toString(), spotifyDbTrack.getPopularity());
    }

    public DbRecentPlayedTrack mapSpotifyTrackToDbRecentPlayedTrack(PlayHistory playHistory) {
        TrackSimplified recentTrack = playHistory.getTrack();
        ArtistSimplified[] artistSimplifieds = recentTrack.getArtists();

        return new DbRecentPlayedTrack(recentTrack.getId(), recentTrack.getName(), UniversalMethods.simplifyArtist(artistSimplifieds).toString() , playHistory.getPlayedAt());
    }

    public List<RecentPlayedTrackDto> mapToRecentPlayedTrackDtos(List<DbRecentPlayedTrack> dbRecentPlayedTracks) {

        return dbRecentPlayedTracks.stream()
                .map(track -> new RecentPlayedTrackDto(track.getTrackId(), track.getTitle(), track.getAuthors(), track.getPlayDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()))
                .collect(Collectors.toList());
    }

//    public DbRecommendedTrack mapSpotifyRecommendedTrackToDbRecommendedTrack() {
//
//    }

    public List<PopularTrackDto> mapToPopularTrackDtoList(List<DbMostFrequentTrack> dbPopularTracks) {
        return dbPopularTracks.stream()
                .map(track -> new PopularTrackDto(track.getTrack_ids(), track.getTitles(), track.getAuthors(), track.getPopularity()))
                .collect(Collectors.toList());
    }

    public List<RecommendedTrackDto> mapToRecommendedTrackDtoList(List<DbRecommendedTrack> dbRecommendedTracks) {
        return dbRecommendedTracks.stream()
                .map(track -> new RecommendedTrackDto(track.getStringId(), track.getTitles(), track.getAuthors(), track.getUrl()))
                .collect(Collectors.toList());
    }

    public List<PlaylistTrack> mapToPlaylistTrack(List<PlaylistTrackDto> playlistTrackDtoList) {
        return playlistTrackDtoList.stream()
                .map(playlistTrackDto -> new PlaylistTrack(playlistTrackDto.getTrackStringId(), playlistTrackDto.getTitle(), playlistTrackDto.getAuthors()))
                .collect(Collectors.toList());
    }
}