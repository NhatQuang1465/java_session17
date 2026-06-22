package lesson4;

import java.sql.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        OrderManager manager =
                new OrderManager();

        while (true) {

            System.out.println("\n===== ORDER MANAGEMENT =====");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Cập nhật khách hàng");
            System.out.println("3. Tạo đơn hàng");
            System.out.println("4. Hiển thị tất cả đơn hàng");
            System.out.println("5. Tìm đơn hàng theo khách");
            System.out.println("0. Thoát");

            try {

                System.out.print("Chọn: ");
                int choice =
                        Integer.parseInt(
                                sc.nextLine());

                switch (choice) {

                    case 1:

                        System.out.print(
                                "Tên sản phẩm: ");

                        String productName =
                                sc.nextLine();

                        System.out.print(
                                "Giá: ");

                        double price =
                                Double.parseDouble(
                                        sc.nextLine());

                        manager.addProduct(
                                new Product(
                                        productName,
                                        price));

                        break;

                    case 2:

                        System.out.print(
                                "ID khách hàng: ");

                        int customerId =
                                Integer.parseInt(
                                        sc.nextLine());

                        System.out.print(
                                "Tên mới: ");

                        String customerName =
                                sc.nextLine();

                        System.out.print(
                                "Email mới: ");

                        String email =
                                sc.nextLine();

                        manager.updateCustomer(
                                customerId,
                                new Customer(
                                        customerName,
                                        email));

                        break;

                    case 3:

                        System.out.print(
                                "ID khách hàng: ");

                        customerId =
                                Integer.parseInt(
                                        sc.nextLine());

                        System.out.print(
                                "Tổng tiền: ");

                        double totalAmount =
                                Double.parseDouble(
                                        sc.nextLine());

                        Order order =
                                new Order(
                                        customerId,
                                        new Date(
                                                System.currentTimeMillis()),
                                        totalAmount
                                );

                        manager.createOrder(
                                order);

                        break;

                    case 4:

                        manager.listAllOrders();
                        break;

                    case 5:

                        System.out.print(
                                "ID khách hàng: ");

                        customerId =
                                Integer.parseInt(
                                        sc.nextLine());

                        manager.getOrdersByCustomer(
                                customerId);

                        break;

                    case 0:

                        System.out.println(
                                "Thoát chương trình!");

                        return;

                    default:

                        System.out.println(
                                "Lựa chọn không hợp lệ!");
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Sai định dạng số!");

            } catch (Exception e) {

                System.out.println(
                        e.getMessage());
            }
        }
    }
}