package com.gpsy.repository;

import com.gpsy.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotifyRepository extends JpaRepository<Track, Long> {
}
