package woowacourse.shoppingcart.dto;

public class PhoneNumberRequest {

    private String start;
    private String middle;
    private String last;

    private PhoneNumberRequest() {
    }

    public PhoneNumberRequest(String start, String middle, String last) {
        this.start = start;
        this.middle = middle;
        this.last = last;
    }

    public String getStart() {
        return start;
    }

    public String getMiddle() {
        return middle;
    }

    public String getLast() {
        return last;
    }
}
