package classify;

public class Classify {
    private int songId;
    private String genre;

    public Classify(int songId, String genre) {
        this.songId = songId;
        this.genre = genre;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
