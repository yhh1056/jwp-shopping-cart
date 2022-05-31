package woowacourse.shoppingcart.ui;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import woowacourse.auth.dto.TokenResponse;
import woowacourse.shoppingcart.application.CustomerService;
import woowacourse.shoppingcart.dto.CustomerRequest;
import woowacourse.shoppingcart.dto.SigninRequest;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody CustomerRequest customerRequest) {
        customerService.create(customerRequest);
        URI uri = URI.create("/signin");
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/signin")
    public TokenResponse signin(@RequestBody SigninRequest signinRequest) {
        return customerService.signin(signinRequest);
    }
}
