package ece325;

/**
 * Assignment 6: Test Driven Development <br />
 * The {@code Song} class
 */
public class Song {
    String artist;
    String title;
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
        return this.artist.toLowerCase().equals(artist.toLowerCase());
    }

    /**
     * @param title {@code String}
     * @return {@code boolean} true if the title matches this title, ignoring case
     */
    public boolean isTitle(String title) {
        return this.title.toLowerCase().equals(title.toLowerCase());
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
        return song.artist.toLowerCase();
    }
}

class TitleHash implements SongHashable {
    public String hash(Song song) {
        return song.title.toLowerCase();
    }
}