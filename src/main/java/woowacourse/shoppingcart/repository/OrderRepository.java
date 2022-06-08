package woowacourse.shoppingcart.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import woowacourse.shoppingcart.dao.OrderDao;
import woowacourse.shoppingcart.domain.customer.Customer;
import woowacourse.shoppingcart.entity.OrderDetailEntity;

@Repository
public class OrderRepository {

    private final OrderDao orderDao;

    public OrderRepository(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Long save(Customer customer) {
        return orderDao.addOrders(customer.getId());
    }

    public boolean isValidOrderId(Long customerId, Long orderId) {
        return orderDao.isValidOrderId(customerId, orderId);
    }

    public List<Long> findOrdersByCustomerId(Long customerId) {
        return orderDao.findOrderIdsByCustomerId(customerId);
    }
}
