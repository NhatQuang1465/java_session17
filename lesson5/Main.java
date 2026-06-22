package lesson5;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc =
                new Scanner(System.in);

        Management manager =
                new Management();

        while(true) {

            try {

                System.out.println("\n===== COMPANY MANAGEMENT =====");
                System.out.println("1. Thêm nhân viên");
                System.out.println("2. Thêm dự án");
                System.out.println("3. Phân công nhân viên");
                System.out.println("4. Hiển thị nhân viên & dự án");
                System.out.println("5. Cập nhật lương");
                System.out.println("0. Thoát");

                System.out.print("Chọn: ");

                int choice =
                        Integer.parseInt(
                                sc.nextLine());

                switch(choice) {

                    case 1:

                        System.out.print(
                                "Tên: ");

                        String name =
                                sc.nextLine();

                        System.out.print(
                                "Phòng ban: ");

                        String department =
                                sc.nextLine();

                        System.out.print(
                                "Lương: ");

                        double salary =
                                Double.parseDouble(
                                        sc.nextLine());

                        manager.addEmployee(
                                new Employee(
                                        name,
                                        department,
                                        salary));

                        break;

                    case 2:

                        System.out.print(
                                "Tên dự án: ");

                        String projectName =
                                sc.nextLine();

                        System.out.print(
                                "Ngân sách: ");

                        double budget =
                                Double.parseDouble(
                                        sc.nextLine());

                        manager.addProject(
                                new Project(
                                        projectName,
                                        budget));

                        break;

                    case 3:

                        System.out.print(
                                "ID nhân viên: ");

                        int employeeId =
                                Integer.parseInt(
                                        sc.nextLine());

                        System.out.print(
                                "ID dự án: ");

                        int projectId =
                                Integer.parseInt(
                                        sc.nextLine());

                        System.out.print(
                                "Vai trò: ");

                        String role =
                                sc.nextLine();

                        manager.assignEmployeeToProject(
                                employeeId,
                                projectId,
                                role);

                        break;

                    case 4:

                        manager.listEmployeesAndProjects();
                        break;

                    case 5:

                        System.out.print(
                                "ID nhân viên: ");

                        employeeId =
                                Integer.parseInt(
                                        sc.nextLine());

                        System.out.print(
                                "Lương mới: ");

                        double newSalary =
                                Double.parseDouble(
                                        sc.nextLine());

                        manager.updateEmployeeSalary(
                                employeeId,
                                newSalary);

                        break;

                    case 0:

                        System.exit(0);

                    default:

                        System.out.println(
                                "Lựa chọn không hợp lệ!");
                }

            } catch(NumberFormatException e) {

                System.out.println(
                        "Sai định dạng dữ liệu!");

            } catch(Exception e) {

                System.out.println(
                        e.getMessage());
            }
        }
    }
}