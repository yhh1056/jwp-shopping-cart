package woowacourse.shoppingcart.dto;

import java.util.List;

public class CartResponse {

    private List<ProductResponse> cart;

    public CartResponse() {
    }

    public CartResponse(List<ProductResponse> cart) {
        this.cart = cart;
    }

    public List<ProductResponse> getCart() {
        return cart;
    }
}
