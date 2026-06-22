package lesson3;

import java.sql.*;

public class BookManager {

    public void addBook(Book book) {

        String checkSql =
                "SELECT * FROM books WHERE title=? AND author=?";

        String insertSql =
                "INSERT INTO books(title,author,published_year,price) VALUES(?,?,?,?)";

        try (
                Connection conn =
                        DBConnection.getConnection()
        ) {

            PreparedStatement check =
                    conn.prepareStatement(checkSql);

            check.setString(1, book.getTitle());
            check.setString(2, book.getAuthor());

            ResultSet rs =
                    check.executeQuery();

            if (rs.next()) {

                System.out.println(
                        "Sách đã tồn tại!");
                return;
            }

            PreparedStatement ps =
                    conn.prepareStatement(insertSql);

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getPublishedYear());
            ps.setDouble(4, book.getPrice());

            ps.executeUpdate();

            System.out.println(
                    "Thêm sách thành công!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateBook(
            int id,
            Book book) {

        String checkSql =
                "SELECT * FROM books WHERE id=?";

        String updateSql =
                "UPDATE books SET title=?,author=?,published_year=?,price=? WHERE id=?";

        try (
                Connection conn =
                        DBConnection.getConnection()
        ) {

            PreparedStatement check =
                    conn.prepareStatement(checkSql);

            check.setInt(1, id);

            ResultSet rs =
                    check.executeQuery();

            if (!rs.next()) {

                System.out.println(
                        "Không tìm thấy sách!");
                return;
            }

            PreparedStatement ps =
                    conn.prepareStatement(updateSql);

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getPublishedYear());
            ps.setDouble(4, book.getPrice());
            ps.setInt(5, id);

            ps.executeUpdate();

            System.out.println(
                    "Cập nhật thành công!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteBook(int id) {

        String checkSql =
                "SELECT * FROM books WHERE id=?";

        String deleteSql =
                "DELETE FROM books WHERE id=?";

        try (
                Connection conn =
                        DBConnection.getConnection()
        ) {

            PreparedStatement check =
                    conn.prepareStatement(checkSql);

            check.setInt(1, id);

            ResultSet rs =
                    check.executeQuery();

            if (!rs.next()) {

                System.out.println(
                        "Không tìm thấy sách!");
                return;
            }

            PreparedStatement ps =
                    conn.prepareStatement(deleteSql);

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println(
                    "Xóa thành công!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void findBooksByAuthor(
            String author) {

        String sql =
                "SELECT * FROM books WHERE author ILIKE ?";

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    "%" + author + "%");

            ResultSet rs =
                    ps.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println(
                        rs.getInt("id")
                                + " | "
                                + rs.getString("title")
                                + " | "
                                + rs.getString("author")
                                + " | "
                                + rs.getInt("published_year")
                                + " | "
                                + rs.getDouble("price")
                );
            }

            if (!found) {
                System.out.println(
                        "Không tìm thấy sách!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listAllBooks() {

        String sql =
                "SELECT * FROM books";

        try (
                Connection conn =
                        DBConnection.getConnection();

                Statement st =
                        conn.createStatement();

                ResultSet rs =
                        st.executeQuery(sql)
        ) {

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id")
                                + " | "
                                + rs.getString("title")
                                + " | "
                                + rs.getString("author")
                                + " | "
                                + rs.getInt("published_year")
                                + " | "
                                + rs.getDouble("price")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}