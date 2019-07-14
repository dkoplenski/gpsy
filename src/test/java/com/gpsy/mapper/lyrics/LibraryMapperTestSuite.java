package com.gpsy.mapper.lyrics;

import com.gpsy.domain.lyrics.Library;
import com.gpsy.domain.lyrics.LyricsInLibrary;
import com.gpsy.domain.lyrics.dto.LibraryDto;
import com.gpsy.domain.lyrics.dto.LyricsInLibraryDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryMapperTestSuite {

    @Autowired
    private LibraryMapper libraryMapper;

    @Test
    public void mapToLibraryDto() {
        //Given
        List<LyricsInLibrary> lyrics = new ArrayList<>();
        List<Library> libraries = new ArrayList<>();
        LyricsInLibrary lyricsInLibrary = new LyricsInLibrary("Test_name", "Test_artist", "Test_lyrics");
        lyrics.add(lyricsInLibrary);
        Library library = new Library("Test_name", lyrics);
        Library library2 = new Library("Library_name2", new ArrayList<>());
        libraries.add(library);
        libraries.add(library2);

        //When
        List<LibraryDto> libraryDtoList = libraryMapper.mapToLibraryDto(libraries);

        //Then
        assertEquals("Test_name",libraryDtoList.get(0).getLibraryName());
        assertEquals("Test_lyrics",libraryDtoList.get(0).getLyrics().get(0).getLyrics());
        assertEquals("Test_name",libraryDtoList.get(0).getLyrics().get(0).getTitle());
        assertEquals("Test_artist",libraryDtoList.get(0).getLyrics().get(0).getArtists());
        assertEquals("Library_name2", libraryDtoList.get(1).getLibraryName());
    }

    @Test
    public void mapToLyricsInLibraryDto() {
    }

    @Test
    public void mapToLyricsInLibrary() {
    }

    @Test
    public void mapToLibrary() {
        //Given
        List<LyricsInLibraryDto> lyricsInLibraryDtoList = new ArrayList<>();
        LyricsInLibraryDto lyricsInLibraryDto = new LyricsInLibraryDto("Test_name", "Test_artist", "Test_lyrics");
        lyricsInLibraryDtoList.add(lyricsInLibraryDto);
        LibraryDto libraryDto = new LibraryDto(1L, "Library_test", lyricsInLibraryDtoList);

        //When
        Library library = libraryMapper.mapToLibrary(libraryDto);

        //Then
        assertEquals("Library_test", library.getLibraryName());
        assertEquals("Test_lyrics", library.getLyrics().get(0).getLyrics());
        assertEquals("Test_name", library.getLyrics().get(0).getTitle());
        assertEquals("Test_artist", library.getLyrics().get(0).getArtists());
    }

    @Test
    public void mapToSaveLibrary() {
    }
}