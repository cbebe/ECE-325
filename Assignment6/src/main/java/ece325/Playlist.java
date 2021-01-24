package ece325;

import java.util.HashSet;
import java.util.Collections;

/**
 * Assignment 6: Test Driven Development <br />
 * Charles Ancheta, 1581672 <br />
 * The {@code Playlist} class
 */
@SuppressWarnings("serial")
public class Playlist<E extends Song> extends java.util.ArrayList<E> {
    private String title;
    private static ArtistHash artistHash = new ArtistHash();
    private static TitleHash titleHash = new TitleHash();

    /**
     * Constructor for the {@code Playlist} class
     * 
     * @param title {@code String} the title of the playlist
     */
    public Playlist(String title) {
        this.title = title;
    }

    /**
     * @return {@code String} the title of the playlist
     */
    public String getTitle() {
        return title;
    }

    /**
     * Add a song to the playlist
     * 
     * @param song {@code E} the song to add
     * @return {@code boolean} true if the song was added successfully
     */
    public boolean addtoPlist(E song) {
        // will not add null and duplicate Songs
        if (song == null || findSong(song) != -1)
            return false;

        return this.add(song);
    }

    /**
     * Remove a song from the playlist
     * 
     * @return {@code boolean} true if the song was removed successfully
     */
    public boolean removeFromPlist(E song) {
        return this.remove(song);
    }

    /**
     * Get a song from the playlist
     * 
     * @param index {@code int} the index of the song to get
     * @return {@code E} the song with the given index
     */
    public E getSong(int index) {
        return this.get(index);
    }

    /**
     * Find a song in the playlist
     * 
     * @param song {@code E} the song to find
     * @return {@code int} the index of the song in the playlist, -1 if absent
     */
    public int findSong(E song) {
        return this.indexOf(song);
    }

    /**
     * Check if the playlist has a song with the given artist
     * 
     * @param artist {@code String} the artist to find
     * @return {@code boolean} true if the artist has a song in the playlist
     */
    public boolean hasArtist(String artist) {
        for (E s : this)
            if (s.isArtist(artist))
                return true;

        return false;
    }

    /**
     * @param title {@code String} the title to compare
     * @return {@code boolean} true if the given title matches the playlist's title
     */
    public boolean hasTitle(String title) {
        return this.title.equalsIgnoreCase(title);
    }

    /**
     * @return {@code double} the total playtime of the playlist
     */
    public double playTime() {
        double playTime = 0;
        for (E s : this)
            playTime += s.getLength();
        return playTime;
    }

    /**
     * @return {@code int} the number of songs (i.e. the size of the list)
     */
    public int numberOfSongs() {
        return this.size();
    }

    /**
     * Counts the number of unique strings in the playlist using {@code SongHash}
     * interface
     * 
     * @param h {@code SongHash} the hash to use (either ArtistHash or TitleHash)
     * @return {@code int} the number of unique strings
     */
    private int numberOfUnique(SongHashable h) {
        HashSet<String> set = new HashSet<>();
        for (E s : this)
            set.add(h.getField(s).toLowerCase());

        return set.size();
    }

    /**
     * @return {@code int} the number of unique artists in the playlist
     */
    public int numberOfArtists() {
        return numberOfUnique(artistHash);
    }

    /**
     * @return {@code int} the number of unique titles in the playlist
     */
    public int numberOfTitles() {
        return numberOfUnique(titleHash);
    }

    /**
     * Sorts the playlist by artist
     */
    public void sortByArtist() {
        Collections.sort(this, new SongComparator(artistHash));
    }

    /**
     * Sorts the playlist by title
     */
    public void sortByTitle() {
        Collections.sort(this, new SongComparator(titleHash));
    }

    /**
     * Prints out the playlist
     */
    public void printPlaylist() {
        for (E s : this)
            System.out.println(s.toString());
    }

    public static void main(String args[]) {
        Playlist<Song> p = new Playlist<>("Hello");
        p.add(new Song("domino", "baila baila comigo", 3.383));
        p.add(new Song("paul mccartney", "temporary secretary", 3.217));
        p.add(new Song("smash mouth", "all star", 3.367));
        p.sortByArtist();
        p.printPlaylist();
        p.sortByTitle();
        p.printPlaylist();
        System.out.println(artistHash);
    }
}
