package kitchenpos.ordertable.dto;

public class OrderTableRequest {

    private Long id;
    private int numberOfGuests;
    private boolean orderClose;

    private OrderTableRequest() {
    }

    public OrderTableRequest(int numberOfGuests, boolean orderClose) {
        this.numberOfGuests = numberOfGuests;
        this.orderClose = orderClose;
    }

    public OrderTableRequest(Long id) {
        this.id = id;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public boolean isOrderClose() {
        return orderClose;
    }
    
    public Long getId() {
        return id;
    }

}