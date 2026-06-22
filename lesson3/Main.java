package lesson3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BookManager manager =
                new BookManager();

        while (true) {

            System.out.println("\n===== BOOK MANAGEMENT =====");
            System.out.println("1. Thêm sách");
            System.out.println("2. Cập nhật sách");
            System.out.println("3. Xóa sách");
            System.out.println("4. Tìm theo tác giả");
            System.out.println("5. Hiển thị tất cả");
            System.out.println("0. Thoát");

            try {

                System.out.print("Chọn: ");
                int choice =
                        Integer.parseInt(
                                sc.nextLine());

                switch (choice) {

                    case 1:

                        System.out.print("Tên sách: ");
                        String title =
                                sc.nextLine();

                        System.out.print("Tác giả: ");
                        String author =
                                sc.nextLine();

                        System.out.print("Năm XB: ");
                        int year =
                                Integer.parseInt(
                                        sc.nextLine());

                        System.out.print("Giá: ");
                        double price =
                                Double.parseDouble(
                                        sc.nextLine());

                        Book book =
                                new Book(
                                        title,
                                        author,
                                        year,
                                        price);

                        manager.addBook(book);
                        break;

                    case 2:

                        System.out.print("ID: ");
                        int id =
                                Integer.parseInt(
                                        sc.nextLine());

                        System.out.print("Tên mới: ");
                        title =
                                sc.nextLine();

                        System.out.print("Tác giả mới: ");
                        author =
                                sc.nextLine();

                        System.out.print("Năm XB mới: ");
                        year =
                                Integer.parseInt(
                                        sc.nextLine());

                        System.out.print("Giá mới: ");
                        price =
                                Double.parseDouble(
                                        sc.nextLine());

                        manager.updateBook(
                                id,
                                new Book(
                                        title,
                                        author,
                                        year,
                                        price));
                        break;

                    case 3:

                        System.out.print("ID: ");
                        id =
                                Integer.parseInt(
                                        sc.nextLine());

                        manager.deleteBook(id);
                        break;

                    case 4:

                        System.out.print("Tác giả: ");
                        author =
                                sc.nextLine();

                        manager.findBooksByAuthor(author);
                        break;

                    case 5:

                        manager.listAllBooks();
                        break;

                    case 0:

                        return;

                    default:

                        System.out.println(
                                "Lựa chọn không hợp lệ!");
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Sai kiểu dữ liệu!");

            } catch (Exception e) {

                System.out.println(
                        e.getMessage());
            }
        }
    }
}