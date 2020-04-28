package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class AbilityCategoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AbilityCategoryDTO.class);
        AbilityCategoryDTO abilityCategoryDTO1 = new AbilityCategoryDTO();
        abilityCategoryDTO1.setId(1L);
        AbilityCategoryDTO abilityCategoryDTO2 = new AbilityCategoryDTO();
        assertThat(abilityCategoryDTO1).isNotEqualTo(abilityCategoryDTO2);
        abilityCategoryDTO2.setId(abilityCategoryDTO1.getId());
        assertThat(abilityCategoryDTO1).isEqualTo(abilityCategoryDTO2);
        abilityCategoryDTO2.setId(2L);
        assertThat(abilityCategoryDTO1).isNotEqualTo(abilityCategoryDTO2);
        abilityCategoryDTO1.setId(null);
        assertThat(abilityCategoryDTO1).isNotEqualTo(abilityCategoryDTO2);
    }
}
