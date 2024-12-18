package song;

public class Song {
    private int songId;
    private String songNameK;
    private String songNameEn;
    private String genre;
    private int duration;
    private int albumId;

    public Song(final int songId, final String songNameK, final String songNameEn,
            final String genre, final int duration, final int albumId) {
        this.songId = songId;
        this.songNameK = songNameK;
        this.songNameEn = songNameEn;
        this.genre = genre;
        this.duration = duration;
        this.albumId = albumId;
    }

    public int getSongId() { return songId; }
    public String getSongNameK() { return songNameK; }
    public String getSongNameEn() { return songNameEn; }
    public String getGenre() { return genre; }
    public int getDuration() { return duration; }
    public int getAlbumId() { return albumId; }

    @Override
    public String toString() {
        return "Song{"+
                "artistId= '" + songId + '\'' +
                "songNameK= '" +  songNameK + '\'' +
                "songNameEn= '" +  songNameEn + '\'' +
                "genre= '" + genre + '\'' +
                "duration= '" + duration + '\'' +
                "albumId= '" + albumId + '\'' +
                '}';

    }


}
