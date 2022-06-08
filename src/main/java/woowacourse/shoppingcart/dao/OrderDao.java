package woowacourse.shoppingcart.dao;

import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import woowacourse.shoppingcart.entity.CartItemEntity;
import woowacourse.shoppingcart.entity.OrderDetailEntity;

@Repository
public class OrderDao {

    private static final RowMapper<OrderDetailEntity> ROW_MAPPER = (rs, rowNum) -> new OrderDetailEntity(
            rs.getLong("id"),
            rs.getLong("orders_id"),
            rs.getLong("product_id"),
            rs.getInt("quantity")
    );


    private final JdbcTemplate jdbcTemplate;

    public OrderDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long addOrders(final Long customerId) {
        final String sql = "INSERT INTO orders (customer_id) VALUES (?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setLong(1, customerId);
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public List<Long> findOrderIdsByCustomerId(final Long customerId) {
        final String sql = "SELECT id FROM orders WHERE customer_id = ? ";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getLong("id"), customerId);
    }

    public boolean isValidOrderId(final Long customerId, final Long orderId) {
        final String query = "SELECT EXISTS(SELECT * FROM orders WHERE customer_id = ? AND id = ?)";
        return jdbcTemplate.queryForObject(query, Boolean.class, customerId, orderId);
    }
}
