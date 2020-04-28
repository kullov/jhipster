package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AbilityCategoryMapperTest {

    private AbilityCategoryMapper abilityCategoryMapper;

    @BeforeEach
    public void setUp() {
        abilityCategoryMapper = new AbilityCategoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(abilityCategoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(abilityCategoryMapper.fromId(null)).isNull();
    }
}
