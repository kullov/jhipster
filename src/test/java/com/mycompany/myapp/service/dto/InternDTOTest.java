package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class InternDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InternDTO.class);
        InternDTO internDTO1 = new InternDTO();
        internDTO1.setId(1L);
        InternDTO internDTO2 = new InternDTO();
        assertThat(internDTO1).isNotEqualTo(internDTO2);
        internDTO2.setId(internDTO1.getId());
        assertThat(internDTO1).isEqualTo(internDTO2);
        internDTO2.setId(2L);
        assertThat(internDTO1).isNotEqualTo(internDTO2);
        internDTO1.setId(null);
        assertThat(internDTO1).isNotEqualTo(internDTO2);
    }
}
