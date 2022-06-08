package woowacourse.shoppingcart.dto;

import java.util.List;

public class OrderDto {

    private List<OrderRequest> order;

    public OrderDto() {
    }

    public OrderDto(List<OrderRequest> order) {
        this.order = order;
    }

    public List<OrderRequest> getOrder() {
        return order;
    }
}
