package ece325;

/**
 * Assignment 6: Test Driven Development <br />
 * The {@code Song} class
 */
public class Song {
    String artist;
    String title;
    double length;

    public Song(String artist, String title, double length) {
        this.artist = artist;
        this.title = title;
        this.length = length;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public double getLength() {
        return length;
    }

    public boolean isArtist(String artist) {
        return this.artist.toLowerCase().equals(artist.toLowerCase());
    }

    public boolean isTitle(String title) {
        return this.title.toLowerCase().equals(title.toLowerCase());
    }

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
