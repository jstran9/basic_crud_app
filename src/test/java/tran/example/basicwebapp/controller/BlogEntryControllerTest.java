package tran.example.basicwebapp.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.hamcrest.Matchers.is;

public class BlogEntryControllerTest {

    @InjectMocks // used to inject a concrete implementation into the field.
    private BlogEntryController blogEntryController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        /**
         * 1. standaloneSetup sets up a MockMvc environment that will give you ability to use certain annotations and certain default message converters.
         * 2. webAppContextSetup lets you to specify a configuration file for your MockMvc environment.
         */
        mockMvc = MockMvcBuilders.standaloneSetup(blogEntryController).build();
    }

    @Test
    public void test() throws Exception {
        // build our request via method chaining.
        // andDo just prints the request in the response (we can see this in the run output window).
        mockMvc.perform(get("/test")).andDo(print());
    }

    @Test
    public void testBE() throws Exception {
        // build our request via method chaining.
        // andDo just prints the request in the response (we can see this in the run output window).
        mockMvc.perform(get("/testBE")).andDo(print());
    }

    @Test
    public void testBEAlt() throws Exception {
        // build our request via method chaining.
        // andDo just prints the request in the response (we can see this in the run output window).
        mockMvc.perform(get("/testBEAlt")).andDo(print());
    }

    @Test
    public void testPBE() throws Exception {
        // build our request via method chaining.
        // andDo just prints the request in the response (we can see this in the run output window).
        mockMvc.perform(post("/testPBE")
                        .contentType(MediaType.APPLICATION_JSON)
//                        .characterEncoding("utf-8")
                        .content("{\"title\":\"Test Blog Title\"}"))
                        .andExpect(jsonPath("$.title", is("Test Blog Title")))
//                        .andExpect(jsonPath("$.title").doesNotExist())
                        .andDo(print());
    }
}
