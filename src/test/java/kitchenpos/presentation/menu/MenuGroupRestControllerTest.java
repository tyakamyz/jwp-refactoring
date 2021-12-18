package kitchenpos.presentation.menu;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.dto.menu.MenuGroupDto;
import kitchenpos.testassistance.config.TestConfig;

@DisplayName("메뉴그룹 API기능에 관한")
public class MenuGroupRestControllerTest extends TestConfig {
    @DisplayName("메뉴그룹이 조회된다.")
    @Test
    void search_menuGroup() {
        // when
        ExtractableResponse<Response> response = 메뉴그룹_조회();

        // then
        메뉴그룹_조회됨(response);
    }

    @DisplayName("메뉴그룹이 저장된다.")
    @Test
    void save_menuGroup() {
        // given
        MenuGroupDto menuGroup = MenuGroupDto.of("테스트 메뉴");

        // when
        ExtractableResponse<Response> response = 메뉴그룹_저장(menuGroup);

        // then
        메뉴그룹_저장됨(response);
    }


    private void 메뉴그룹_조회됨(ExtractableResponse<Response> response) {
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }


    private void 메뉴그룹_저장됨(ExtractableResponse<Response> response) {
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    public static ExtractableResponse<Response> 메뉴그룹_저장(MenuGroupDto menuGroup) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(menuGroup)
                .when().post("/api/menu-groups")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 메뉴그룹_조회() {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/menu-groups")
                .then().log().all()
                .extract();
    }
}