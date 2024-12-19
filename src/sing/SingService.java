package sing;

import conf.Conf;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SingService {
    public static List<Sing> selectAll() {
        ResultSet rs = null;
        PreparedStatement psmt = null;

        List<Sing> sings = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM sing";
            psmt = conn.prepareStatement(query);
            rs = psmt.executeQuery();

            while (rs.next()) {
                int artistId = rs.getInt("artist_id");
                int songId = rs.getInt("song_id");
                sings.add(new Sing(artistId, songId));
            }
            return sings;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        } finally {
            closeResources(psmt, rs);
        }
    }

    public static int insert(int artistId, int songId) {
        PreparedStatement psmt = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "INSERT INTO sing (artist_id, song_id) VALUES (?, ?)";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1, artistId);
            psmt.setInt(2, songId);

            return psmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;

        } finally {
            closeResources(psmt, null);
        }
    }

    public static int delete(int artistId, int songId) {
        PreparedStatement psmt = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "DELETE FROM sing WHERE artist_id = ? AND song_id = ?";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1, artistId);
            psmt.setInt(2, songId);

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
