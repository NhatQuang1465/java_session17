package lesson5;

import java.sql.*;

public class Management {

    public void addEmployee(Employee employee) {

        String checkSql =
                "SELECT * FROM employee WHERE name=?";

        String insertSql =
                "INSERT INTO employee(name,department,salary) VALUES(?,?,?)";

        try(Connection conn =
                    DBConnection.getConnection()) {

            PreparedStatement check =
                    conn.prepareStatement(checkSql);

            check.setString(
                    1,
                    employee.getName());

            ResultSet rs =
                    check.executeQuery();

            if(rs.next()) {

                System.out.println(
                        "Nhân viên đã tồn tại!");
                return;
            }

            PreparedStatement ps =
                    conn.prepareStatement(insertSql);

            ps.setString(
                    1,
                    employee.getName());

            ps.setString(
                    2,
                    employee.getDepartment());

            ps.setDouble(
                    3,
                    employee.getSalary());

            ps.executeUpdate();

            System.out.println(
                    "Thêm nhân viên thành công!");

        } catch(SQLException e) {

            System.out.println(
                    e.getMessage());
        }
    }

    public void addProject(Project project) {

        String checkSql =
                "SELECT * FROM project WHERE name=?";

        String insertSql =
                "INSERT INTO project(name,budget) VALUES(?,?)";

        try(Connection conn =
                    DBConnection.getConnection()) {

            PreparedStatement check =
                    conn.prepareStatement(checkSql);

            check.setString(
                    1,
                    project.getName());

            ResultSet rs =
                    check.executeQuery();

            if(rs.next()) {

                System.out.println(
                        "Dự án đã tồn tại!");
                return;
            }

            PreparedStatement ps =
                    conn.prepareStatement(insertSql);

            ps.setString(
                    1,
                    project.getName());

            ps.setDouble(
                    2,
                    project.getBudget());

            ps.executeUpdate();

            System.out.println(
                    "Thêm dự án thành công!");

        } catch(SQLException e) {

            System.out.println(
                    e.getMessage());
        }
    }

    public void assignEmployeeToProject(
            int employeeId,
            int projectId,
            String role) {

        String checkEmployee =
                "SELECT * FROM employee WHERE id=?";

        String checkProject =
                "SELECT * FROM project WHERE id=?";

        String insertSql =
                "INSERT INTO assignment(employee_id,project_id,role) VALUES(?,?,?)";

        try(Connection conn =
                    DBConnection.getConnection()) {

            PreparedStatement ps1 =
                    conn.prepareStatement(checkEmployee);

            ps1.setInt(1, employeeId);

            if(!ps1.executeQuery().next()) {

                System.out.println(
                        "Nhân viên không tồn tại!");
                return;
            }

            PreparedStatement ps2 =
                    conn.prepareStatement(checkProject);

            ps2.setInt(1, projectId);

            if(!ps2.executeQuery().next()) {

                System.out.println(
                        "Dự án không tồn tại!");
                return;
            }

            PreparedStatement ps3 =
                    conn.prepareStatement(insertSql);

            ps3.setInt(1, employeeId);
            ps3.setInt(2, projectId);
            ps3.setString(3, role);

            ps3.executeUpdate();

            System.out.println(
                    "Phân công thành công!");

        } catch(SQLException e) {

            System.out.println(
                    e.getMessage());
        }
    }

    public void updateEmployeeSalary(
            int employeeId,
            double newSalary) {

        String sql =
                "UPDATE employee SET salary=? WHERE id=?";

        try(Connection conn =
                    DBConnection.getConnection()) {

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setDouble(1, newSalary);
            ps.setInt(2, employeeId);

            int rows =
                    ps.executeUpdate();

            if(rows == 0) {

                System.out.println(
                        "Không tìm thấy nhân viên!");
            }
            else {

                System.out.println(
                        "Cập nhật lương thành công!");
            }

        } catch(SQLException e) {

            System.out.println(
                    e.getMessage());
        }
    }

    public void listEmployeesAndProjects() {

        String sql =
                """
                SELECT e.name employee_name,
                       p.name project_name,
                       a.role
                FROM assignment a
                JOIN employee e
                ON a.employee_id = e.id
                JOIN project p
                ON a.project_id = p.id
                ORDER BY e.name
                """;

        try(Connection conn =
                    DBConnection.getConnection();

            Statement st =
                    conn.createStatement();

            ResultSet rs =
                    st.executeQuery(sql)) {

            while(rs.next()) {

                System.out.println(
                        rs.getString("employee_name")
                                + " | "
                                + rs.getString("project_name")
                                + " | "
                                + rs.getString("role")
                );
            }

        } catch(SQLException e) {

            System.out.println(
                    e.getMessage());
        }
    }
}