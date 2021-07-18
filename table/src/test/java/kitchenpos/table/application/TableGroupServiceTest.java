package kitchenpos.table.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import kitchenpos.table.domain.OrderTableRepository;
import kitchenpos.table.domain.TableGroup;
import kitchenpos.table.domain.TableGroupRepository;
import kitchenpos.table.dto.OrderTableRequest;
import kitchenpos.table.dto.TableGroupRequest;
import kitchenpos.table.dto.TableGroupResponse;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("단체 그룹 관련 기능 테스트")
@ExtendWith(MockitoExtension.class)
public class TableGroupServiceTest {
    @Mock
    private TableGroupRepository tableGroupRepository;

    @Mock
    private OrderTableRepository orderTableRepository;

    @Mock
    private ApplicationEventPublisher publisher;

    TableGroupService tableGroupService;

    @BeforeEach
    void setUp() {
        tableGroupService = new TableGroupService(tableGroupRepository, orderTableRepository, publisher);
    }

    @DisplayName("주문 테이블을 그룹화 한다.")
    @Test
    void create() {
        TableGroupRequest tableGroupRequest = mock(TableGroupRequest.class);

        OrderTableRequest orderTableRequest = 주문_테이블_요청값_생성(1L, null, 2, false);
        OrderTableRequest orderTableRequest2 = 주문_테이블_요청값_생성(2L, null, 2, false);

        TableGroup tableGroup = mock(TableGroup.class);

        when(tableGroupRequest.getOrderTables()).thenReturn(Arrays.asList(orderTableRequest, orderTableRequest2));
        when(tableGroupRepository.save(any())).thenReturn(tableGroup);

        TableGroupResponse response = 주문테이블_그룹화_요청(tableGroupRequest);

        주문테이블_그룹화_완료(response);
    }

    private TableGroupResponse 주문테이블_그룹화_요청(TableGroupRequest tableGroupRequest) {
        return tableGroupService.create(tableGroupRequest);
    }

    private void 주문테이블_그룹화_완료(TableGroupResponse response) {
        assertThat(response).isNotNull();
    }

    private OrderTableRequest 주문_테이블_요청값_생성(Long id, Long tableGroupId, int numberOfGuests, boolean empty) {
        return new OrderTableRequest(id, tableGroupId, numberOfGuests, empty);
    }
}
