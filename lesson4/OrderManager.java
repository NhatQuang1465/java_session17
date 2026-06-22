package lesson4;

import java.sql.*;

public class OrderManager {

    public void addProduct(Product product) {

        String checkSql =
                "SELECT * FROM products WHERE name=?";

        String insertSql =
                "INSERT INTO products(name,price) VALUES(?,?)";

        try (Connection conn =
                     DBConnection.getConnection()) {

            PreparedStatement check =
                    conn.prepareStatement(checkSql);

            check.setString(1, product.getName());

            ResultSet rs =
                    check.executeQuery();

            if (rs.next()) {

                System.out.println(
                        "Sản phẩm đã tồn tại!");
                return;
            }

            PreparedStatement ps =
                    conn.prepareStatement(insertSql);

            ps.setString(1,
                    product.getName());

            ps.setDouble(2,
                    product.getPrice());

            ps.executeUpdate();

            System.out.println(
                    "Thêm sản phẩm thành công!");

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage());
        }
    }

    public void updateCustomer(
            int customerId,
            Customer customer) {

        String sql =
                "UPDATE customers " +
                        "SET name=?,email=? " +
                        "WHERE id=?";

        try (Connection conn =
                     DBConnection.getConnection()) {

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1,
                    customer.getName());

            ps.setString(2,
                    customer.getEmail());

            ps.setInt(3,
                    customerId);

            int rows =
                    ps.executeUpdate();

            if (rows == 0) {

                System.out.println(
                        "Không tìm thấy khách hàng!");
            } else {

                System.out.println(
                        "Cập nhật thành công!");
            }

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage());
        }
    }

    public void createOrder(Order order) {

        String sql =
                "INSERT INTO orders(customer_id,order_date,total_amount) " +
                        "VALUES(?,?,?)";

        try (Connection conn =
                     DBConnection.getConnection()) {

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1,
                    order.getCustomerId());

            ps.setDate(2,
                    order.getOrderDate());

            ps.setDouble(3,
                    order.getTotalAmount());

            ps.executeUpdate();

            System.out.println(
                    "Tạo đơn hàng thành công!");

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage());
        }
    }

    public void listAllOrders() {

        String sql =
                """
                SELECT o.id,
                       c.name,
                       o.order_date,
                       o.total_amount
                FROM orders o
                JOIN customers c
                ON o.customer_id = c.id
                """;

        try (Connection conn =
                     DBConnection.getConnection();

             Statement st =
                     conn.createStatement();

             ResultSet rs =
                     st.executeQuery(sql)) {

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id")
                                + " | "
                                + rs.getString("name")
                                + " | "
                                + rs.getDate("order_date")
                                + " | "
                                + rs.getDouble("total_amount")
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage());
        }
    }

    public void getOrdersByCustomer(
            int customerId) {

        String sql =
                """
                SELECT o.id,
                       o.order_date,
                       o.total_amount
                FROM orders o
                WHERE o.customer_id = ?
                """;

        try (Connection conn =
                     DBConnection.getConnection();

             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setInt(1,
                    customerId);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id")
                                + " | "
                                + rs.getDate("order_date")
                                + " | "
                                + rs.getDouble("total_amount")
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage());
        }
    }
}