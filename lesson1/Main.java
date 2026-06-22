package lesson1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        MovieManagement movie =
                new MovieManagement();

        while (true) {

            System.out.println("\n===== MENU =====");
            System.out.println("1. Thêm phim");
            System.out.println("2. Danh sách phim");
            System.out.println("3. Sửa phim");
            System.out.println("4. Xóa phim");
            System.out.println("0. Thoát");

            System.out.print("Chọn: ");

            try {

                int choice =
                        Integer.parseInt(
                                sc.nextLine());

                switch (choice) {

                    case 1:

                        System.out.print(
                                "Tiêu đề: ");
                        String title =
                                sc.nextLine();

                        System.out.print(
                                "Đạo diễn: ");
                        String director =
                                sc.nextLine();

                        System.out.print(
                                "Năm phát hành: ");
                        int year =
                                Integer.parseInt(
                                        sc.nextLine());

                        if (title.isBlank()
                                || director.isBlank()) {

                            System.out.println(
                                    "Không được để trống!");
                            break;
                        }

                        movie.addMovie(
                                title,
                                director,
                                year);
                        break;

                    case 2:

                        movie.listMovies();
                        break;

                    case 3:

                        System.out.print(
                                "ID phim: ");
                        int idUpdate =
                                Integer.parseInt(
                                        sc.nextLine());

                        System.out.print(
                                "Tiêu đề mới: ");
                        title =
                                sc.nextLine();

                        System.out.print(
                                "Đạo diễn mới: ");
                        director =
                                sc.nextLine();

                        System.out.print(
                                "Năm mới: ");
                        year =
                                Integer.parseInt(
                                        sc.nextLine());

                        movie.updateMovie(
                                idUpdate,
                                title,
                                director,
                                year);
                        break;

                    case 4:

                        System.out.print(
                                "ID cần xóa: ");

                        int idDelete =
                                Integer.parseInt(
                                        sc.nextLine());

                        movie.deleteMovie(
                                idDelete);
                        break;

                    case 0:

                        System.out.println(
                                "Kết thúc chương trình");
                        return;

                    default:

                        System.out.println(
                                "Lựa chọn không hợp lệ!");
                }

            }
            catch (NumberFormatException e) {

                System.out.println(
                        "Vui lòng nhập số hợp lệ!");

            }
            catch (Exception e) {

                System.out.println(
                        e.getMessage());
            }
        }
    }
}