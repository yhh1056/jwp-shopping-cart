package woowacourse.shoppingcart.application;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowacourse.common.exception.NotFoundException;
import woowacourse.shoppingcart.dao.CartItemDao;
import woowacourse.shoppingcart.dao.CustomerDao;
import woowacourse.shoppingcart.dao.OrderDao;
import woowacourse.shoppingcart.dao.OrdersDetailDao;
import woowacourse.shoppingcart.domain.OrderDetail;
import woowacourse.shoppingcart.domain.Orders;
import woowacourse.shoppingcart.domain.Product;
import woowacourse.shoppingcart.domain.customer.Customer;
import woowacourse.shoppingcart.dto.OrderRequest;
import woowacourse.shoppingcart.entity.OrderDetailEntity;
import woowacourse.shoppingcart.exception.InvalidOrderException;
import woowacourse.shoppingcart.repository.CartItemRepository;
import woowacourse.shoppingcart.repository.CustomerRepository;
import woowacourse.shoppingcart.repository.OrderDetailRepository;
import woowacourse.shoppingcart.repository.OrderRepository;
import woowacourse.shoppingcart.repository.ProductRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderService {

    private final OrderDao orderDao;
    private final OrdersDetailDao ordersDetailDao;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderService(OrderDao orderDao, OrdersDetailDao ordersDetailDao,
            ProductRepository productRepository,
            CustomerRepository customerRepository,
            OrderRepository orderRepository,
            CartItemRepository cartItemRepository,
            OrderDetailRepository orderDetailRepository) {
        this.orderDao = orderDao;
        this.ordersDetailDao = ordersDetailDao;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Transactional
    public Long addOrder(List<OrderRequest> orderDetailRequests, Long customerId) {
        Customer customer = getCustomer(customerId);
        Long ordersId = orderRepository.save(customer);
        for (OrderRequest orderDetail : orderDetailRequests) {
            Long cartId = orderDetail.getCartId();
            Product product = productRepository.findProductByCartId(cartId);
            int quantity = orderDetail.getQuantity();

            orderDetailRepository.save(ordersId, new OrderDetail(product, quantity));
            cartItemRepository.deleteCartItem(cartId);
        }
        return ordersId;
    }

    private Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 사용자입니다."));
    }

    public Orders findOrderById(Long customerId, Long orderId) {
        validateOrderIdByCustomerName(customerId, orderId);
        return findOrderResponseDtoByOrderId(orderId);
    }

    private void validateOrderIdByCustomerName(Long customerId, Long orderId) {
        if (!orderRepository.isValidOrderId(customerId, orderId)) {
            throw new InvalidOrderException("유저에게는 해당 order_id가 없습니다.");
        }
    }

    public List<Orders> findOrdersByCustomerName(Long customerId) {
        orderRepository.findOrdersByCustomerId(customerId);
        final List<Long> orderIds = orderDao.findOrderIdsByCustomerId(customerId);

        return orderIds.stream()
                .map(this::findOrderResponseDtoByOrderId)
                .collect(Collectors.toList());
    }

    private Orders findOrderResponseDtoByOrderId(final Long orderId) {
        final List<OrderDetail> ordersDetails = new ArrayList<>();
        for (final OrderDetailEntity productQuantity : ordersDetailDao.findOrdersDetailsByOrderId(orderId)) {
            final Product product = productRepository.findById(productQuantity.getProductId()).get();
            final int quantity = productQuantity.getQuantity();
            final int totalCost = product.getPrice() * quantity;
            ordersDetails.add(new OrderDetail(
                    product.getId(),
                    quantity,
                    product.getPrice(),
                    product.getName(),
                    product.getImageUrl(),
                    totalCost)
            );
        }

        return new Orders(orderId, ordersDetails);
    }
}
