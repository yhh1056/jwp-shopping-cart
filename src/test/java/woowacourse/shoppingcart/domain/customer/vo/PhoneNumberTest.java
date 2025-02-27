package woowacourse.shoppingcart.domain.customer.vo;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class PhoneNumberTest {

    @ParameterizedTest(name = "휴대폰 번호 : {0}-{1}-{2}")
    @CsvSource({"01, 1234, 1234", "0101, 1234, 1234",
            "010, 123, 1234", "010, 12345, 1234",
            "010, 1234, 123", "010, 1234, 12345"})
    void 올바르지_않은_글자수_휴대폰_번호_생성_예외(String start, String middle, String last) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PhoneNumber(start, middle, last))
                .withMessageContaining("휴대폰 번호양식이 불일치 합니다.");
    }

    @ParameterizedTest(name = "휴대폰 번호 : {0}-{1}-{2}")
    @CsvSource({"공일공, 1234, 1234", "010, 일이삼사, 1234", "010, 1234, 일이삼사"})
    void 숫자가_아닌_휴대폰_번호_생성_예외(String start, String middle, String last) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PhoneNumber(start, middle, last))
                .withMessageContaining("휴대폰 번호는 숫자만 가능합니다.");
    }
}
