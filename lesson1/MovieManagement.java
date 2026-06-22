package lesson1;

import java.sql.*;

public class MovieManagement {

    public void addMovie(
            String title,
            String director,
            int year) {

        String sql =
                "{CALL add_movie(?,?,?)}";

        try (
                Connection conn =
                        DBConnection.getConnection();

                CallableStatement cs =
                        conn.prepareCall(sql)
        ) {

            cs.setString(1, title);
            cs.setString(2, director);
            cs.setInt(3, year);

            cs.execute();

            System.out.println(
                    "Thêm phim thành công!");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listMovies() {

        String sql =
                "SELECT * FROM list_movies()";

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id")
                                + " | "
                                + rs.getString("title")
                                + " | "
                                + rs.getString("director")
                                + " | "
                                + rs.getInt("year")
                );
            }

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateMovie(
            int id,
            String title,
            String director,
            int year) {

        String sql =
                "{CALL update_movie(?,?,?,?)}";

        try (
                Connection conn =
                        DBConnection.getConnection();

                CallableStatement cs =
                        conn.prepareCall(sql)
        ) {

            cs.setInt(1, id);
            cs.setString(2, title);
            cs.setString(3, director);
            cs.setInt(4, year);

            cs.execute();

            System.out.println(
                    "Cập nhật thành công!");

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteMovie(int id) {

        String sql =
                "{CALL delete_movie(?)}";

        try (
                Connection conn =
                        DBConnection.getConnection();

                CallableStatement cs =
                        conn.prepareCall(sql)
        ) {

            cs.setInt(1, id);

            cs.execute();

            System.out.println(
                    "Xóa thành công!");

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}