package sing;

public class Sing {
    private int artistId;
    private int songId;

    public Sing(int artistId, int songId) {
        this.artistId = artistId;
        this.songId = songId;
    }

    public int getArtistId() {
        return artistId;
    }

    public int getSongId() {
        return songId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }
}
