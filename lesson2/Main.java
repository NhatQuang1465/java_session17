package lesson2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        TaskManagement task =
                new TaskManagement();

        while (true) {

            System.out.println("\n===== TO DO LIST =====");
            System.out.println("1. Thêm công việc");
            System.out.println("2. Danh sách công việc");
            System.out.println("3. Cập nhật trạng thái");
            System.out.println("4. Xóa công việc");
            System.out.println("5. Tìm kiếm công việc");
            System.out.println("6. Thống kê");
            System.out.println("0. Thoát");

            try {

                System.out.print("Chọn: ");
                int choice =
                        Integer.parseInt(
                                sc.nextLine());

                switch (choice) {

                    case 1:

                        System.out.print(
                                "Tên công việc: ");
                        String taskName =
                                sc.nextLine();

                        System.out.print(
                                "Trạng thái: ");
                        String status =
                                sc.nextLine();

                        if (taskName.isBlank()
                                || status.isBlank()) {

                            System.out.println(
                                    "Không được để trống!");
                            break;
                        }

                        task.addTask(
                                taskName,
                                status);
                        break;

                    case 2:

                        task.listTasks();
                        break;

                    case 3:

                        System.out.print(
                                "ID công việc: ");
                        int id =
                                Integer.parseInt(
                                        sc.nextLine());

                        System.out.print(
                                "Trạng thái mới: ");
                        status =
                                sc.nextLine();

                        task.updateTaskStatus(
                                id,
                                status);
                        break;

                    case 4:

                        System.out.print(
                                "ID cần xóa: ");

                        id =
                                Integer.parseInt(
                                        sc.nextLine());

                        task.deleteTask(id);
                        break;

                    case 5:

                        System.out.print(
                                "Tên cần tìm: ");

                        taskName =
                                sc.nextLine();

                        task.searchTaskByName(
                                taskName);
                        break;

                    case 6:

                        task.taskStatistics();
                        break;

                    case 0:

                        return;

                    default:

                        System.out.println(
                                "Lựa chọn không hợp lệ!");
                }

            }
            catch (NumberFormatException e) {

                System.out.println(
                        "Vui lòng nhập số!");

            }
            catch (Exception e) {

                System.out.println(
                        e.getMessage());
            }
        }
    }
}