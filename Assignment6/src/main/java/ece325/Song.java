package ece325;

import java.util.Comparator;

/**
 * Assignment 6: Test Driven Development <br />
 * Charles Ancheta, 1581672 <br />
 * The {@code Song} class
 */
public class Song {
    private String artist;
    private String title;
    private double length;

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
        return this.artist.equalsIgnoreCase(artist);
    }

    /**
     * @param title {@code String}
     * @return {@code boolean} true if the title matches this title, ignoring case
     */
    public boolean isTitle(String title) {
        return this.title.equalsIgnoreCase(title);
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

// Interface for finding unique artists and titles
// Can be extended for other fields like genre, composer, etc.
interface SongHashable {
    public String getField(Song song);
}

class ArtistHash implements SongHashable {
    public String getField(Song song) {
        return song.getArtist();
    }
}

class TitleHash implements SongHashable {
    public String getField(Song song) {
        return song.getTitle();
    }
}

/**
 * Alphabetical comparator for different {@code Song} fields<br />
 * note that they are case insensitive
 */
class SongComparator implements Comparator<Song> {
    private SongHashable hash;

    /**
     * Constructor for {@code SongComparator} class
     * 
     * @param hash {@code SongHashable} field used for comparison
     */
    public SongComparator(SongHashable hash) {
        this.hash = hash;
    }

    /**
     * @param a {@code String} the first object
     * @param b {@code String} the second object
     * @return {@code int} result of string comparison
     */
    public int compare(Song a, Song b) {
        return hash.getField(a).compareToIgnoreCase(hash.getField(b));
    }
}