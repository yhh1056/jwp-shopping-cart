package woowacourse.shoppingcart.repository;

import org.springframework.stereotype.Repository;
import woowacourse.shoppingcart.dao.OrdersDetailDao;
import woowacourse.shoppingcart.domain.OrderDetail;
import woowacourse.shoppingcart.entity.OrderDetailEntity;

@Repository
public class OrderDetailRepository {

    private final OrdersDetailDao ordersDetailDao;

    public OrderDetailRepository(OrdersDetailDao ordersDetailDao) {
        this.ordersDetailDao = ordersDetailDao;
    }

    public void save(Long ordersId, OrderDetail orderDetail) {
        ordersDetailDao.addOrdersDetail(
                ordersId, orderDetail.getId(), orderDetail.getQuantity());
    }
}
