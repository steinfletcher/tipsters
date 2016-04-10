package io.tipsters.feature;

import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = {
                        "classpath:db/create.sql",
                        "classpath:db/insert.sql"})
})
public class TeamTest extends WebApplicationTest {

    @Test
    public void returnsTheTeamByName() throws Exception {
        String endpoint = "/team";

        mockMvc.perform(get(endpoint).param("name", "Arsenal"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Arsenal")))
                .andExpect(jsonPath("$.league.name", is("Premier League")));
    }

    @Test
    public void returnsNotFoundIfTeamNameDoesNotExist() throws Exception {
        String endpoint = "/team";

        mockMvc.perform(get(endpoint).param("name", "idontexist"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.type", is("TeamNotFoundError")));
    }

    @Test
    public void returnsBadRequestIfTeamNameNotProvided() throws Exception {
        String endpoint = "/team";

        mockMvc.perform(get(endpoint))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
