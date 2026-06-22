package lesson2;

import java.sql.*;

public class TaskManagement {

    public void addTask(
            String taskName,
            String status) {

        String sql =
                "{CALL add_task(?,?)}";

        try (
                Connection conn =
                        DBConnection.getConnection();

                CallableStatement cs =
                        conn.prepareCall(sql)
        ) {

            cs.setString(1, taskName);
            cs.setString(2, status);

            cs.execute();

            System.out.println(
                    "Thêm công việc thành công!");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listTasks() {

        String sql =
                "SELECT * FROM list_tasks()";

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
                                + rs.getString("task_name")
                                + " | "
                                + rs.getString("status")
                );
            }

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateTaskStatus(
            int id,
            String status) {

        String sql =
                "{CALL update_task_status(?,?)}";

        try (
                Connection conn =
                        DBConnection.getConnection();

                CallableStatement cs =
                        conn.prepareCall(sql)
        ) {

            cs.setInt(1, id);
            cs.setString(2, status);

            cs.execute();

            System.out.println(
                    "Cập nhật thành công!");

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(int id) {

        String sql =
                "{CALL delete_task(?)}";

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

    public void searchTaskByName(
            String taskName) {

        String sql =
                "SELECT * FROM search_task_by_name(?)";

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(1, taskName);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id")
                                + " | "
                                + rs.getString("task_name")
                                + " | "
                                + rs.getString("status")
                );
            }

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void taskStatistics() {

        String sql =
                "SELECT * FROM task_statistics()";

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
                        rs.getString("status")
                                + " : "
                                + rs.getLong("total")
                );
            }

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}