package album;

import conf.Conf;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumService {
    private static Album setAlbum(ResultSet rs) throws SQLException {
        int albumId = rs.getInt("album_id");
        String albumName = rs.getString("album_name");
        return new Album(albumId, albumName);
    }

    public static List<Album> selectAll() {
        List<Album> albumList = new ArrayList<>();
        String query = "SELECT * FROM album";

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmt = conn.prepareStatement(query);
             ResultSet rs = psmt.executeQuery()) {

            while (rs.next()) {
                albumList.add(setAlbum(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return albumList;
    }

    public static boolean insert(int albumId, String albumName) {
        String selectQuery = "SELECT * FROM album WHERE album_id = ?";
        String insertQuery = "INSERT INTO album (album_id, album_name) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmtSelect = conn.prepareStatement(selectQuery)) {

            psmtSelect.setInt(1, albumId);
            try (ResultSet rs = psmtSelect.executeQuery()) {
                if (rs.next()) {
                    System.out.println("이미 존재하는 앨범 ID입니다: " + albumId);
                    return false;
                }
            }

            try (PreparedStatement psmtInsert = conn.prepareStatement(insertQuery)) {
                psmtInsert.setInt(1, albumId);
                psmtInsert.setString(2, albumName);

                return psmtInsert.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(int albumId, String albumName) {
        String updateQuery = "UPDATE album SET album_name = ? WHERE album_id = ?";

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmt = conn.prepareStatement(updateQuery)) {

            psmt.setString(1, albumName);
            psmt.setInt(2, albumId);

            return psmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteById(int albumId) {
        String deleteSongQuery = "DELETE FROM song WHERE album_id = ?";
        String deleteAlbumQuery = "DELETE FROM album WHERE album_id = ?";

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmtSong = conn.prepareStatement(deleteSongQuery);
             PreparedStatement psmtAlbum = conn.prepareStatement(deleteAlbumQuery)) {

            // 먼저 관련된 곡들 삭제
            psmtSong.setInt(1, albumId);
            psmtSong.executeUpdate();

            // 앨범 삭제
            psmtAlbum.setInt(1, albumId);
            return psmtAlbum.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Album selectById(int albumId) {
        String query = "SELECT * FROM album WHERE album_id = ?";
        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmt = conn.prepareStatement(query)) {

            psmt.setInt(1, albumId);
            try (ResultSet rs = psmt.executeQuery()) {
                if (rs.next()) {
                    return setAlbum(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
