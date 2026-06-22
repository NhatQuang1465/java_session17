package lesson6;

import java.sql.*;

public class StudentManager {

    public void addStudent(Student student) {

        String checkSql =
                "SELECT * FROM student WHERE email=?";

        String insertSql =
                "INSERT INTO student(name,email) VALUES(?,?)";

        try(Connection conn =
                    DBConnection.getConnection()) {

            PreparedStatement check =
                    conn.prepareStatement(checkSql);

            check.setString(
                    1,
                    student.getEmail());

            if(check.executeQuery().next()) {

                System.out.println(
                        "Email đã tồn tại!");
                return;
            }

            PreparedStatement ps =
                    conn.prepareStatement(insertSql);

            ps.setString(
                    1,
                    student.getName());

            ps.setString(
                    2,
                    student.getEmail());

            ps.executeUpdate();

            System.out.println(
                    "Thêm sinh viên thành công!");

        } catch(SQLException e) {

            System.out.println(
                    e.getMessage());
        }
    }

    public void addCourse(Course course) {

        String checkSql =
                "SELECT * FROM course WHERE title=?";

        String insertSql =
                "INSERT INTO course(title,credits) VALUES(?,?)";

        try(Connection conn =
                    DBConnection.getConnection()) {

            PreparedStatement check =
                    conn.prepareStatement(checkSql);

            check.setString(
                    1,
                    course.getTitle());

            if(check.executeQuery().next()) {

                System.out.println(
                        "Khóa học đã tồn tại!");
                return;
            }

            PreparedStatement ps =
                    conn.prepareStatement(insertSql);

            ps.setString(
                    1,
                    course.getTitle());

            ps.setInt(
                    2,
                    course.getCredits());

            ps.executeUpdate();

            System.out.println(
                    "Thêm khóa học thành công!");

        } catch(SQLException e) {

            System.out.println(
                    e.getMessage());
        }
    }

    public void enrollStudent(
            int studentId,
            int courseId) {

        String checkStudent =
                "SELECT * FROM student WHERE id=?";

        String checkCourse =
                "SELECT * FROM course WHERE id=?";

        String insertSql =
                "INSERT INTO enrollment(student_id,course_id) VALUES(?,?)";

        try(Connection conn =
                    DBConnection.getConnection()) {

            PreparedStatement ps1 =
                    conn.prepareStatement(checkStudent);

            ps1.setInt(1, studentId);

            if(!ps1.executeQuery().next()) {

                System.out.println(
                        "Sinh viên không tồn tại!");
                return;
            }

            PreparedStatement ps2 =
                    conn.prepareStatement(checkCourse);

            ps2.setInt(1, courseId);

            if(!ps2.executeQuery().next()) {

                System.out.println(
                        "Khóa học không tồn tại!");
                return;
            }

            PreparedStatement ps3 =
                    conn.prepareStatement(insertSql);

            ps3.setInt(1, studentId);
            ps3.setInt(2, courseId);

            ps3.executeUpdate();

            System.out.println(
                    "Ghi danh thành công!");

        } catch(SQLException e) {

            System.out.println(
                    e.getMessage());
        }
    }

    public void updateStudentGrade(
            int studentId,
            int courseId,
            double grade) {

        String sql =
                """
                UPDATE enrollment
                SET grade = ?
                WHERE student_id = ?
                AND course_id = ?
                """;

        try(Connection conn =
                    DBConnection.getConnection()) {

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setDouble(1, grade);
            ps.setInt(2, studentId);
            ps.setInt(3, courseId);

            int rows =
                    ps.executeUpdate();

            if(rows == 0) {

                System.out.println(
                        "Không tìm thấy bản ghi!");
            }
            else {

                System.out.println(
                        "Cập nhật điểm thành công!");
            }

        } catch(SQLException e) {

            System.out.println(
                    e.getMessage());
        }
    }

    public void listStudentsAndGrades() {

        String sql =
                """
                SELECT s.name student_name,
                       c.title course_title,
                       e.grade
                FROM enrollment e
                JOIN student s
                ON e.student_id = s.id
                JOIN course c
                ON e.course_id = c.id
                ORDER BY s.name
                """;

        try(Connection conn =
                    DBConnection.getConnection();

            Statement st =
                    conn.createStatement();

            ResultSet rs =
                    st.executeQuery(sql)) {

            while(rs.next()) {

                System.out.println(
                        rs.getString("student_name")
                                + " | "
                                + rs.getString("course_title")
                                + " | "
                                + rs.getString("grade")
                );
            }

        } catch(SQLException e) {

            System.out.println(
                    e.getMessage());
        }
    }
}