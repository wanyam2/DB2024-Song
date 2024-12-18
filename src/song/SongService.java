package song;

import conf.Conf;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongService {
    private static Song setSong(ResultSet rs) throws SQLException {
        int songId = rs.getInt("song_id");
        String songNameK = rs.getString("song_name_k");
        String songNameEn = rs.getString("song_name_en");
        String genre = rs.getString("genre");
        int duration = rs.getInt("duration");
        int albumId = rs.getInt("album_id");

        return new Song(songId, songNameK, songNameEn, genre, duration, albumId);
    }

    public static List<Song> selectAll() {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;

        List<Song> songList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {

            String query = "SELECT * FROM song";

            psmtQuery = conn.prepareStatement(query);

            rs = psmtQuery.executeQuery();

            while (rs.next()) {
                Song song = setSong(rs);
                songList.add(song);
            }
            return songList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                } catch (SQLException e) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public static Song selectById(final int songId) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {

            String query = "SELECT * FROM song WHERE song_id = ?";

            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setInt(1, songId);

            rs = psmtQuery.executeQuery();

            if (rs.next()) {
                Song song = setSong(rs);
                return song;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                } catch (SQLException e) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public static int insert(int songId, String songNameK, String songNameEn, String genre,
                             int duration, int albumId) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {

            String query = "SELECT * FROM song WHERE song_id = ?";

            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setInt(1, songId);

            rs = psmtQuery.executeQuery();

            if (!rs.next()) {
                String insertStatement =
                        "INSERT INTO song (song_id, song_name_k, song_name_en, genre, duration, album_id) " +
                                "VALUES(?,?,?,?,?,?)";
                psmtUpdate = conn.prepareStatement(insertStatement);
                psmtUpdate.setInt(1, songId);
                psmtUpdate.setString(2, songNameK);
                psmtUpdate.setString(3, songNameEn);
                psmtUpdate.setString(4, genre);
                psmtUpdate.setInt(5, duration);
                psmtUpdate.setInt(6, albumId);
                return psmtUpdate.executeUpdate();
            } else {
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;

        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                } catch (SQLException e) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public static int update(int songId, String genre) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {

            String query = "SELECT * FROM song WHERE song_id = ?";

            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setInt(1, songId);

            rs = psmtQuery.executeQuery();

            if (rs.next()) {
                String updateStatement =
                        "UPDATE song SET genre = ? WHERE song_id = ?";

                psmtUpdate = conn.prepareStatement(updateStatement);
                psmtUpdate.setString(1, genre);
                psmtUpdate.setInt(2, songId);
                return psmtUpdate.executeUpdate();
            } else {
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;

        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                } catch (SQLException e) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public static int deleteById(int songId) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {

            String query = "SELECT * FROM song WHERE song_id = ?";

            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setInt(1, songId);

            rs = psmtQuery.executeQuery();

            if (rs.next()) {
                String updateStatement =
                        "DELETE FROM song WHERE song_id = ?";

                psmtUpdate = conn.prepareStatement(updateStatement);
                psmtUpdate.setInt(1, songId);
                return psmtUpdate.executeUpdate();
            } else {
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;

        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                } catch (SQLException e) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
