package tran.example.basicwebapp.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tran.example.basicwebapp.controllers.v1.BlogEntryController;
import tran.example.basicwebapp.domain.BlogEntry;
import tran.example.basicwebapp.services.BlogEntryService;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

public class BlogEntryControllerTest {

    // mockito should inject this blogEntryService into our blogEntryController during testing.
    @Mock
    private BlogEntryService blogEntryService;

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

//    @Test
    public void test() throws Exception {
        // build our request via method chaining.
        // andDo just prints the request in the response (we can see this in the run output window).
        mockMvc.perform(get("/test")).andDo(print());
    }

//    @Test
    public void testBE() throws Exception {
        // build our request via method chaining.
        // andDo just prints the request in the response (we can see this in the run output window).
        mockMvc.perform(get("/testBE")).andDo(print());
    }

//    @Test
    public void testBEAlt() throws Exception {
        // build our request via method chaining.
        // andDo just prints the request in the response (we can see this in the run output window).
        mockMvc.perform(get("/testBEAlt")).andDo(print());
    }

//    @Test
    public void testPBE() throws Exception {
        // build our request via method chaining.
        // andDo just prints the request in the response (we can see this in the run output window).
        mockMvc.perform(post("/api/v1/blog-entries/testPBE")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content("{\"title\":\"Test Blog Title\"}"))
//                        .andExpect(jsonPath("$.title", is("Test Blog Title")))
                        .andDo(print());
    }

    @Test
    public void getExistingBlogEntry() throws Exception {
        /**
         * tests for what happens when we get a BlogEntry that the BlogEntryService can find.
         */
        BlogEntry blogEntry = new BlogEntry();
        blogEntry.setTitle("test title");
        blogEntry.setId(1L);

        when(blogEntryService.find(1L)).thenReturn(blogEntry);

        mockMvc.perform(get("/api/v1/blog-entries/1"))
                .andDo(print())
                .andExpect(jsonPath("$.title", is(blogEntry.getTitle())))
                .andExpect(jsonPath(".links[*].href", hasItem(endsWith("/blog-entries/1"))))
                .andExpect(status().isOk());
    }

    @Test
    public void getNonExistingBlogEntry() throws Exception {
        /**
         * tests for what happens when we get a BlogEntry that the BlogEntryService cannot find.
         */
        when(blogEntryService.find(1L)).thenReturn(null);

        mockMvc.perform(get("/api/v1/blog-entries/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteExistingBlogEntry() throws Exception {
        BlogEntry deletedBlogEntry = new BlogEntry();
        deletedBlogEntry.setId(1L);
        deletedBlogEntry.setTitle("Test Title");

        when(blogEntryService.delete(1L)).thenReturn(deletedBlogEntry);

        mockMvc.perform(delete("/rest/blog-entries/1"))
                .andExpect(jsonPath("$.title", is(deletedBlogEntry.getTitle())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/blog-entries/1"))))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNonExistingBlogEntry() throws Exception {
        when(blogEntryService.delete(1L)).thenReturn(null);

        mockMvc.perform(delete("/rest/blog-entries/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateExistingBlogEntry() throws Exception {
        BlogEntry updatedEntry = new BlogEntry();
        updatedEntry.setId(1L);
        updatedEntry.setTitle("Test Title");

        when(blogEntryService.update(eq(1L), any(BlogEntry.class)))
                .thenReturn(updatedEntry);

        mockMvc.perform(put("/rest/blog-entries/1")
                .content("{\"title\":\"Test Title\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is(updatedEntry.getTitle())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/blog-entries/1"))))
                .andExpect(status().isOk());
    }

    @Test
    public void updateNonExistingBlogEntry() throws Exception {
        when(blogEntryService.update(eq(1L), any(BlogEntry.class)))
                .thenReturn(null);

        mockMvc.perform(put("/rest/blog-entries/1")
                .content("{\"title\":\"Test Title\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
