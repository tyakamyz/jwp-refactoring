package kitchenpos.order.domain;

import java.util.*;
import java.util.stream.Collectors;

public class FakeOrderLineItemRepository implements OrderLineItemRepository {
    private Map<Long, OrderLineItem> map = new HashMap<>();
    private Long key = 1L;

    @Override
    public OrderLineItem save(OrderLineItem inputOrderLineItem) {
        OrderLineItem orderLineItem = new OrderLineItem(key, inputOrderLineItem.getOrder(), inputOrderLineItem.getMenu(), inputOrderLineItem.getQuantity());
        map.put(key, orderLineItem);
        key++;
        return orderLineItem;
    }

    @Override
    public Optional<OrderLineItem> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<OrderLineItem> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public List<OrderLineItem> findAllByOrderId(Long orderId) {
        return map.values().stream()
                .filter(orderLineItem -> orderLineItem.getOrder().equals(orderId))
                .collect(Collectors.toList());
    }
}