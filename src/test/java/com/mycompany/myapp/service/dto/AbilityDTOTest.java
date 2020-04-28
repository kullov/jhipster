package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class AbilityDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AbilityDTO.class);
        AbilityDTO abilityDTO1 = new AbilityDTO();
        abilityDTO1.setId(1L);
        AbilityDTO abilityDTO2 = new AbilityDTO();
        assertThat(abilityDTO1).isNotEqualTo(abilityDTO2);
        abilityDTO2.setId(abilityDTO1.getId());
        assertThat(abilityDTO1).isEqualTo(abilityDTO2);
        abilityDTO2.setId(2L);
        assertThat(abilityDTO1).isNotEqualTo(abilityDTO2);
        abilityDTO1.setId(null);
        assertThat(abilityDTO1).isNotEqualTo(abilityDTO2);
    }
}
