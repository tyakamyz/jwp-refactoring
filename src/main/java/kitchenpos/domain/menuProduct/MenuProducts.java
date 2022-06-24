package kitchenpos.domain.menuProduct;

import kitchenpos.domain.menu.Menu;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class MenuProducts {
    @OneToMany(mappedBy = "menu")
    private final List<MenuProduct> menuProducts;

    protected MenuProducts() {
        menuProducts = new ArrayList<>();
    }

    public MenuProducts(List<MenuProduct> menuProducts) {
        this.menuProducts = menuProducts;
    }

    public List<MenuProduct> getMenuProducts() {
        return menuProducts;
    }

    public BigDecimal calculateProductsSum() {
        BigDecimal sum = BigDecimal.ZERO;
        for (final MenuProduct menuProduct : menuProducts) {
            sum = sum.add(menuProduct.calculateAmount());
        }

        return sum;
    }

    public void updateMenu(Menu menu) {
        menuProducts.forEach(menuProduct -> menuProduct.updateMenu(menu));
    }

    public List<Long> findProductIds() {
        return menuProducts.stream()
                .map(menuProduct -> menuProduct.getProduct().getId())
                .collect(Collectors.toList());
    }
}
