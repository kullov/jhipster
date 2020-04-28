package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class RequestAssignmentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RequestAssignment.class);
        RequestAssignment requestAssignment1 = new RequestAssignment();
        requestAssignment1.setId(1L);
        RequestAssignment requestAssignment2 = new RequestAssignment();
        requestAssignment2.setId(requestAssignment1.getId());
        assertThat(requestAssignment1).isEqualTo(requestAssignment2);
        requestAssignment2.setId(2L);
        assertThat(requestAssignment1).isNotEqualTo(requestAssignment2);
        requestAssignment1.setId(null);
        assertThat(requestAssignment1).isNotEqualTo(requestAssignment2);
    }
}
