package kitchenpos.fixture;

import kitchenpos.domain.*;

import java.util.Collections;
import java.util.List;

public class TestMenuProductsFactory {
    public static MenuProducts 메뉴상품들_생성(final Menu menu, final Long productId, final int quantity) {
        final MenuProduct menuProduct = MenuProduct.of(menu, productId, quantity);
        final List<MenuProduct> menuProducts = Collections.singletonList(menuProduct);
        return MenuProducts.from(menuProducts);
    }
}