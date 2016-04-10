package io.tipsters.error;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RestErrorHandlerTest {

    private final RestErrorHandler underTest = new RestErrorHandler();

    @Test
    public void mapsTeamNotFoundError() throws Exception {
        TeamNotFoundError notFoundError = new TeamNotFoundError("Team ABC not found");

        ErrorResponse errorResponse = underTest.handleException(notFoundError);

        assertThat(errorResponse.getMessage(), is("Team ABC not found"));
        assertThat(errorResponse.getType(), is("TeamNotFoundError"));
    }
}