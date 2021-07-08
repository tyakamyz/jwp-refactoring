package kitchenpos.menu.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kitchenpos.common.error.NotFoundMenuGroupException;
import kitchenpos.common.domian.Price;
import kitchenpos.common.domian.Quantity;
import kitchenpos.menu.repository.MenuProductDao;
import kitchenpos.menu.domain.ProductsQuantities;
import kitchenpos.menu.domain.Quantities;
import kitchenpos.menu.domain.Products;
import kitchenpos.menu.dto.MenuResponse;
import kitchenpos.menu.dto.MenuRequest;
import kitchenpos.menu.repository.MenuDao;
import kitchenpos.menugroup.domain.MenuGroup;
import kitchenpos.menugroup.repository.MenuGroupDao;
import kitchenpos.menu.dto.MenuProductRequest;
import kitchenpos.product.repository.ProductDao;
import kitchenpos.menu.domain.Menu;

@Service
@Transactional
public class MenuService {
    private final MenuDao menuDao;
    private final MenuGroupDao menuGroupDao;
    private final MenuProductDao menuProductDao;
    private final ProductDao productDao;

    public MenuService(
            final MenuDao menuDao,
            final MenuGroupDao menuGroupDao,
            final MenuProductDao menuProductDao,
            final ProductDao productDao
    ) {
        this.menuDao = menuDao;
        this.menuGroupDao = menuGroupDao;
        this.menuProductDao = menuProductDao;
        this.productDao = productDao;
    }

    public MenuResponse create(final MenuRequest menuRequest) {
        MenuGroup menuGroup = menuGroupDao.findById(menuRequest.getMenuGroupId())
                .orElseThrow(NotFoundMenuGroupException::new);

        List<MenuProductRequest> menuProductsRequest = menuRequest.getMenuProducts();

        ProductsQuantities productsQuantities = new ProductsQuantities(
                new Products(productDao.findAllById(menuProductsRequest.stream()
                        .map(MenuProductRequest::getProductId)
                        .collect(Collectors.toList()))
                        , menuProductsRequest.size())
                ,
                new Quantities(menuProductsRequest.stream()
                        .collect(Collectors.toMap(MenuProductRequest::getProductId, request -> new Quantity(request.getQuantity())))
                        , menuProductsRequest.size())
                ,
                new Price(menuRequest.getPrice())
        );

        Menu menu = Menu.of(menuGroup, menuRequest.getName(), productsQuantities);
        menuProductDao.saveAll(menu.getMenuProducts());
        return MenuResponse.of(menuDao.save(menu));
    }

    @Transactional(readOnly = true)
    public List<MenuResponse> list() {
        return MenuResponse.listOf(menuDao.findAll());
    }
}