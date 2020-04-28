package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RequestAssignmentMapperTest {

    private RequestAssignmentMapper requestAssignmentMapper;

    @BeforeEach
    public void setUp() {
        requestAssignmentMapper = new RequestAssignmentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(requestAssignmentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(requestAssignmentMapper.fromId(null)).isNull();
    }
}
