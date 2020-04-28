package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AbilityMapperTest {

    private AbilityMapper abilityMapper;

    @BeforeEach
    public void setUp() {
        abilityMapper = new AbilityMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(abilityMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(abilityMapper.fromId(null)).isNull();
    }
}
