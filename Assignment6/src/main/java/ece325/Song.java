package ece325;

import java.util.Comparator;

/**
 * Assignment 6: Test Driven Development <br />
 * Charles Ancheta, 1581672 <br />
 * The {@code Song} class
 */
public class Song {
    static AlphabetComparator c = new AlphabetComparator();
    String artist, title;
    double length;

    /**
     * Constructor for the {@code Song} class
     * 
     * @param artist {@code String} the artist of the song
     * @param title  {@code String} the title of the song
     * @param length {@code double} the length of the song
     */
    public Song(String artist, String title, double length) {
        this.artist = artist;
        this.title = title;
        this.length = length;
    }

    /**
     * Getters for each field
     */
    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public double getLength() {
        return length;
    }

    /**
     * @param artist {@code String}
     * @return {@code boolean} true if the artist matches this artist, ignoring case
     */
    public boolean isArtist(String artist) {
        return c.compare(this.artist, artist) == 0;
    }

    /**
     * @param title {@code String}
     * @return {@code boolean} true if the title matches this title, ignoring case
     */
    public boolean isTitle(String title) {
        return c.compare(this.title, title) == 0;
    }

    /**
     * @return {@code String} String with format title - artist - length
     */
    public String toString() {
        return String.format("%s - %s - %s", title, artist, length);
    }

    /**
     * Override of Object.equals for {@code Song} class
     * 
     * @return {@code boolean} if the object matches all of song's fields
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instanceof Song))
            return false;

        Song s = (Song) obj;

        return s.isArtist(artist) && s.isTitle(title) && s.length == length;
    }
}

// Use polymorphism for finding unique artists and titles
interface SongHashable {
    public String hash(Song song);
}

class ArtistHash implements SongHashable {
    public String hash(Song song) {
        return song.artist;
    }
}

class TitleHash implements SongHashable {
    public String hash(Song song) {
        return song.title;
    }
}

/**
 * String comparator that ignores case
 */
class AlphabetComparator implements Comparator<String> {
    public int compare(String a, String b) {
        return b.toLowerCase().compareTo(a.toLowerCase());
    }
}

/**
 * Alphabetical comparators for artist and title Strings <br />
 * note that they are case insensitive
 * 
 * @param a {@code String} the first object
 * @param b {@code String} the second object
 * @return {@code int} result of CaseIgnoreComparator.compare(String, String)
 */
class ArtistComparator implements Comparator<Song> {
    public int compare(Song a, Song b) {
        return new AlphabetComparator().compare(a.artist, b.artist);
    }
}

class TitleComparator implements Comparator<Song> {
    public int compare(Song a, Song b) {
        return new AlphabetComparator().compare(a.title, b.title);
    }
}