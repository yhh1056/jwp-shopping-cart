package woowacourse.shoppingcart.dao;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import woowacourse.shoppingcart.entity.OrderDetailEntity;

@Repository
public class OrdersDetailDao {
    private final JdbcTemplate jdbcTemplate;

    public OrdersDetailDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long addOrdersDetail(OrderDetailEntity orderDetail) {
        final String sql = "INSERT INTO orders_detail (orders_id, product_id, quantity) VALUES (?, ?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setLong(1, orderDetail.getOrdersId());
            preparedStatement.setLong(2, orderDetail.getProductId());
            preparedStatement.setLong(3, orderDetail.getQuantity());
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public List<OrderDetailEntity> findOrdersDetailsByOrderId(Long orderId) {
        final String sql = "SELECT product_id, quantity FROM orders_detail WHERE orders_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new OrderDetailEntity(
                rs.getLong("orders_id"),
                rs.getLong("product_id"),
                rs.getInt("quantity")
        ), orderId);
    }
}
