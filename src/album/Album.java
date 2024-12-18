package album;

public class Album {
    private int albumId;
    private String albumName;

    public Album(final int albumId, final String albumName) {
        this.albumId = albumId;
        this.albumName = albumName;
    }
    public int getAlbumId() {
        return albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumId='" + albumId + '\'' +
                ", albumName='" + albumName + '\'' +
                '}';
    }
}
