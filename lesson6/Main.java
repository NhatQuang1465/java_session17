package lesson6;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc =
                new Scanner(System.in);

        StudentManager manager =
                new StudentManager();

        while(true) {

            try {

                System.out.println("\n===== SCHOOL MANAGEMENT =====");
                System.out.println("1. Thêm sinh viên");
                System.out.println("2. Thêm khóa học");
                System.out.println("3. Ghi danh");
                System.out.println("4. Hiển thị điểm");
                System.out.println("5. Cập nhật điểm");
                System.out.println("0. Thoát");

                System.out.print("Chọn: ");

                int choice =
                        Integer.parseInt(
                                sc.nextLine());

                switch(choice) {

                    case 1:

                        System.out.print(
                                "Tên SV: ");

                        String name =
                                sc.nextLine();

                        System.out.print(
                                "Email: ");

                        String email =
                                sc.nextLine();

                        manager.addStudent(
                                new Student(
                                        name,
                                        email));
                        break;

                    case 2:

                        System.out.print(
                                "Tên khóa học: ");

                        String title =
                                sc.nextLine();

                        System.out.print(
                                "Số tín chỉ: ");

                        int credits =
                                Integer.parseInt(
                                        sc.nextLine());

                        manager.addCourse(
                                new Course(
                                        title,
                                        credits));
                        break;

                    case 3:

                        System.out.print(
                                "ID sinh viên: ");

                        int studentId =
                                Integer.parseInt(
                                        sc.nextLine());

                        System.out.print(
                                "ID khóa học: ");

                        int courseId =
                                Integer.parseInt(
                                        sc.nextLine());

                        manager.enrollStudent(
                                studentId,
                                courseId);
                        break;

                    case 4:

                        manager.listStudentsAndGrades();
                        break;

                    case 5:

                        System.out.print(
                                "ID sinh viên: ");

                        studentId =
                                Integer.parseInt(
                                        sc.nextLine());

                        System.out.print(
                                "ID khóa học: ");

                        courseId =
                                Integer.parseInt(
                                        sc.nextLine());

                        System.out.print(
                                "Điểm: ");

                        double grade =
                                Double.parseDouble(
                                        sc.nextLine());

                        manager.updateStudentGrade(
                                studentId,
                                courseId,
                                grade);
                        break;

                    case 0:
                        System.exit(0);

                    default:
                        System.out.println(
                                "Lựa chọn không hợp lệ!");
                }

            } catch(NumberFormatException e) {

                System.out.println(
                        "Sai kiểu dữ liệu!");

            } catch(Exception e) {

                System.out.println(
                        e.getMessage());
            }
        }
    }
}