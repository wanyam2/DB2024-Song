package classify;

import conf.Conf;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassifyService {
    public static List<Classify> selectAll() {
        ResultSet rs = null;
        PreparedStatement psmt = null;

        List<Classify> classifies = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM classify";
            psmt = conn.prepareStatement(query);
            rs = psmt.executeQuery();

            while (rs.next()) {
                int songId = rs.getInt("song_id");
                String genre = rs.getString("genre");
                classifies.add(new Classify(songId, genre));
            }
            return classifies;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        } finally {
            closeResources(psmt, rs);
        }
    }

    public static int insert(int songId, String genre) {
        PreparedStatement psmt = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "INSERT INTO classify (song_id, genre) VALUES (?, ?)";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1, songId);
            psmt.setString(2, genre);

            return psmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;

        } finally {
            closeResources(psmt, null);
        }
    }

    public static int delete(int songId, String genre) {
        PreparedStatement psmt = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "DELETE FROM classify WHERE song_id = ? AND genre = ?";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1, songId);
            psmt.setString(2, genre);

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
