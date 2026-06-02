package AimsProject.Src.hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.List;

import AimsProject.Src.hust.soict.dsai.aims.exception.PlayerException;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private List<Track> tracks = new ArrayList<>();
    
    public CompactDisc(String title, String category, String director, int length, float cost, String artist) {
        super(title, category, director, length, cost);
        this.artist = artist;
    }
    public CompactDisc(int id, String title, String category, String director, int length, float cost, String artist) {
    super(id, title, category, cost, director, length); 
    this.artist = artist;
    }
    public String getArtist() { return artist; }

    public void addTrack(Track track) {
        if (tracks.contains(track)) {
            System.out.println("Track already exists.");
        } else {
            tracks.add(track);
        }
    }

    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
        } else {
            System.out.println("Track does not exist.");
        }
    }

    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    @Override
    public void play() throws PlayerException {
        if (this.getLength() <= 0) {
            throw new PlayerException("ERROR: CD \"" + this.getTitle() + "\" has non-positive length!");
        }
        System.out.println("Playing CD: " + this.getTitle() + " by " + this.getArtist());
        for (Track track : tracks) {
            try {
                track.play();
            } catch (PlayerException e) {
                // Quăng tiếp ra ngoài để GUI bắt lấy và hiển thị Dialog thông báo
                throw e; 
            }
        }
    }
}