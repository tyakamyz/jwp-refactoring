package kitchenpos.dto;

import java.util.List;
import java.util.stream.Collectors;

public class TableGroupRequest {

    List<OrderTableRequest> orderTables;

    private TableGroupRequest() {
    }

    private TableGroupRequest(List<OrderTableRequest> orderTables) {
        this.orderTables = orderTables;
    }

    public static TableGroupRequest from(List<OrderTableRequest> orderTables) {
        return new TableGroupRequest(orderTables);
    }

    public List<OrderTableRequest> getOrderTables() {
        return orderTables;
    }

    public List<Long> getOrderTableIds() {
        return orderTables.stream()
                .map(OrderTableRequest::getId)
                .collect(Collectors.toList());
    }
}