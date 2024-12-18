package artist;

import conf.Conf;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistService {

    private static Artist setArtist(ResultSet rs) throws SQLException {
        int artistId = rs.getInt("artist_id");
        String artistName = rs.getString("artist_name");
        int debutYear = rs.getInt("debut_year");
        return new Artist(artistId, artistName, debutYear);
    }

    public static List<Artist> selectAll() {
        List<Artist> artistList = new ArrayList<>();
        String query = "SELECT * FROM artist";

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmt = conn.prepareStatement(query);
             ResultSet rs = psmt.executeQuery()) {

            while (rs.next()) {
                artistList.add(setArtist(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artistList;
    }

    public static boolean insert(int artistId, String artistName, int debutYear) {
        String selectQuery = "SELECT * FROM artist WHERE artist_id = ?";
        String insertQuery = "INSERT INTO artist (artist_id, artist_name, debut_year) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmtSelect = conn.prepareStatement(selectQuery)) {

            psmtSelect.setInt(1, artistId);
            try (ResultSet rs = psmtSelect.executeQuery()) {
                if (rs.next()) {
                    System.out.println("이미 존재하는 아티스트 ID입니다: " + artistId);
                    return false;
                }
            }

            try (PreparedStatement psmtInsert = conn.prepareStatement(insertQuery)) {
                psmtInsert.setInt(1, artistId);
                psmtInsert.setString(2, artistName);
                psmtInsert.setInt(3, debutYear);
                return psmtInsert.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Artist selectById(int artistId) {
        String query = "SELECT * FROM artist WHERE artist_id = ?";
        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmt = conn.prepareStatement(query)) {

            psmt.setInt(1, artistId);
            try (ResultSet rs = psmt.executeQuery()) {
                if (rs.next()) {
                    return setArtist(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean update(int artistId, String artistName, int debutYear) {
        String updateQuery = "UPDATE artist SET artist_name = ?, debut_year = ? WHERE artist_id = ?";

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmt = conn.prepareStatement(updateQuery)) {

            psmt.setString(1, artistName);
            psmt.setInt(2, debutYear);
            psmt.setInt(3, artistId);
            return psmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteById(int artistId) {
        String deleteQuery = "DELETE FROM artist WHERE artist_id = ?";

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmt = conn.prepareStatement(deleteQuery)) {

            psmt.setInt(1, artistId);
            return psmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
