package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class RequestAssignmentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RequestAssignmentDTO.class);
        RequestAssignmentDTO requestAssignmentDTO1 = new RequestAssignmentDTO();
        requestAssignmentDTO1.setId(1L);
        RequestAssignmentDTO requestAssignmentDTO2 = new RequestAssignmentDTO();
        assertThat(requestAssignmentDTO1).isNotEqualTo(requestAssignmentDTO2);
        requestAssignmentDTO2.setId(requestAssignmentDTO1.getId());
        assertThat(requestAssignmentDTO1).isEqualTo(requestAssignmentDTO2);
        requestAssignmentDTO2.setId(2L);
        assertThat(requestAssignmentDTO1).isNotEqualTo(requestAssignmentDTO2);
        requestAssignmentDTO1.setId(null);
        assertThat(requestAssignmentDTO1).isNotEqualTo(requestAssignmentDTO2);
    }
}
