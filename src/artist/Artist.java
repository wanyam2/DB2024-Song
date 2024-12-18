package artist;

public class Artist {
    private int artistId;
    private String artistName;
    private int debutYear;

    public int getArtistId() {
        return artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public int getDebutYear() {
        return debutYear;
    }

    public Artist(final int artistId, final String artistName, final int debutYear) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.debutYear = debutYear;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artistId='" + artistId + '\'' +
                ", artistName='" + artistName + '\'' +
                ", debutYear=" + debutYear +
                '}';
    }
}

