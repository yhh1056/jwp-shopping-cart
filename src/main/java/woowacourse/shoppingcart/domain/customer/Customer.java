package woowacourse.shoppingcart.domain.customer;

import woowacourse.shoppingcart.domain.customer.vo.Account;
import woowacourse.shoppingcart.domain.customer.vo.Address;
import woowacourse.shoppingcart.domain.customer.vo.EncryptPassword;
import woowacourse.shoppingcart.domain.customer.vo.Nickname;
import woowacourse.shoppingcart.domain.customer.vo.PhoneNumber;

public class Customer {

    private final Long id;
    private final Account account;
    private Nickname nickname;
    private final EncryptPassword password;
    private Address address;
    private PhoneNumber phoneNumber;

    public Customer(Long id, Account account, Nickname nickname, EncryptPassword password,
            Address address,
            PhoneNumber phoneNumber) {
        this.id = id;
        this.account = account;
        this.nickname = nickname;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Customer(String account, String nickname, String password, String address,
            PhoneNumber phoneNumber) {
        this(null, new Account(account), new Nickname(nickname), new EncryptPassword(password),
                new Address(address), phoneNumber);
    }

    public Customer(String account, String nickname, EncryptPassword password,
            String address, PhoneNumber phoneNumber) {
        this(null, new Account(account), new Nickname(nickname), password, new Address(address),
                phoneNumber);
    }

    public Customer(Long id, String account, String nickname, String password, String address,
            PhoneNumber phoneNumber) {
        this(id, new Account(account), new Nickname(nickname), new EncryptPassword(password),
                new Address(address), phoneNumber);
    }

    public void update(Nickname nickname, Address address, PhoneNumber phoneNumber) {
        this.nickname = nickname;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public EncryptPassword getPassword() {
        return password;
    }

    public Address getAddress() {
        return address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }
}
