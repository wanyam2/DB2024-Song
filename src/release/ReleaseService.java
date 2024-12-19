package release;

import conf.Conf;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReleaseService {
    public static List<Release> selectAll() {
        ResultSet rs = null;
        PreparedStatement psmt = null;

        List<Release> releases = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM release";
            psmt = conn.prepareStatement(query);
            rs = psmt.executeQuery();

            while (rs.next()) {
                int artistId = rs.getInt("artist_id");
                int albumId = rs.getInt("album_id");
                releases.add(new Release(artistId, albumId));
            }
            return releases;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        } finally {
            closeResources(psmt, rs);
        }
    }

    public static int insert(int artistId, int albumId) {
        PreparedStatement psmt = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "INSERT INTO release (artist_id, album_id) VALUES (?, ?)";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1, artistId);
            psmt.setInt(2, albumId);

            return psmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;

        } finally {
            closeResources(psmt, null);
        }
    }

    public static int delete(int artistId, int albumId) {
        PreparedStatement psmt = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "DELETE FROM release WHERE artist_id = ? AND album_id = ?";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1, artistId);
            psmt.setInt(2, albumId);

            return psmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;

        } finally {
            closeResources(psmt, null);
        }
    }

    private static void closeResources(PreparedStatement psmt, ResultSet rs) {
        if (psmt != null) {
            try {
                psmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
