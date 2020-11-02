package ece325;

import java.util.HashMap;
import java.util.Comparator;
import java.util.Collections;

class ArtistComparator<E extends Song> implements Comparator<E> {
    public int compare(E a, E b) {
        return a.artist.toLowerCase().compareTo(b.artist.toLowerCase());
    }
}

class TitleComparator<E extends Song> implements Comparator<E> {
    public int compare(E a, E b) {
        return a.title.toLowerCase().compareTo(b.title.toLowerCase());
    }
}

/**
 * Assignment 6: Test Driven Development <br />
 * The {@code Playlist} class
 */
@SuppressWarnings("serial")
public class Playlist<E extends Song> extends java.util.ArrayList<E> {

    java.util.Iterator<E> itr = this.iterator(); // Generic Iterator; Use it whenever you need it!
    String title;

    public Playlist(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public boolean addtoPlist(E song) {
        if (song == null || findSong(song) != -1)
            return false;

        this.add(song);
        return true;
    }

    public boolean removeFromPlist(E song) {
        return this.remove(song);
    }

    public E getSong(int index) {
        return this.get(index);
    }

    public int findSong(E song) {
        itr = this.iterator();
        int i = 0;
        while (itr.hasNext()) {
            if (itr.next().equals(song))
                return i;
            i++;
        }

        return -1;
    }

    public boolean hasArtist(String artist) {
        itr = this.iterator();
        while (itr.hasNext()) {
            if (itr.next().isArtist(artist))
                return true;
        }

        return false;
    }

    public boolean hasTitle(String title) {
        return this.title.toLowerCase().equals(title.toLowerCase());
    }

    public float playTime() {
        itr = this.iterator();
        float playTime = 0;
        while (itr.hasNext()) {
            playTime += itr.next().getLength();
        }
        return playTime;
    }

    public int numberOfSongs() {
        return this.size();
    }

    public int numberOfArtists() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        itr = this.iterator();
        int i = 0;
        while (itr.hasNext()) {
            String artist = itr.next().artist.toLowerCase();
            if (map.get(artist) == null)
                map.put(artist, ++i);
        }

        return i;
    }

    public int numberOfTitles() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        itr = this.iterator();
        int i = 0;
        while (itr.hasNext()) {
            String title = itr.next().title.toLowerCase();
            if (map.get(title) == null)
                map.put(title, ++i);
        }

        return i;
    }

    public void sortByArtist() {
        Collections.sort(this, new ArtistComparator<E>());
    }

    public void sortByTitle() {
        Collections.sort(this, new TitleComparator<E>());
    }
}
