package e3;

import java.util.*;

public class Release {
    private String id;
    private String artist;
    private String title;
    private ArrayList<Track> trackList;

    public Release(String id) {
        this.id = id;
        artist = "No artist";
        title = "Untitled";
        trackList = new ArrayList<Track>();
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }

    public void setArtist(String artist){
        this.artist = artist;
    }
    public String getArtist(){
        return artist;
    }

    public void addTrack(Track track){
        if(trackList != null && trackList.contains(track)) throw new ArrayStoreException("Track already exists");
        trackList.add(track);
    }

    @Override
    public String toString(){
        if (trackList.isEmpty()){
            return "ID: " + id + "\nArtist: " + artist + "\nTitle: " + title + "\nTracklist: Empty\n";
        }
        String trackstr="";
        for (int i=0;i<trackList.size();i++){
            trackstr+= trackList.get(i).title() + "\n";
        }
        return "ID: " + id + "\nArtist: " + artist + "\nTitle: " + title + "\nTracklist: " + trackstr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Release other = (Release) o;

        return trackList.size() == other.trackList.size() && other.trackList.containsAll(trackList); //if same size and contains same elements
    }


    @Override
    public int hashCode() {
        ArrayList<Integer> hashes = new ArrayList<>(); //create an array with hash codes of recordings
        for(Track track : trackList){
            hashes.add(Objects.hash(track.recording()));
        }
        Collections.sort(hashes);
        return Objects.hash(hashes);
    }

}
