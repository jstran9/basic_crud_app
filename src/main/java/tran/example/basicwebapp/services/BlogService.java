package tran.example.basicwebapp.services;


import tran.example.basicwebapp.domain.Blog;
import tran.example.basicwebapp.domain.BlogEntry;
import tran.example.basicwebapp.services.util.BlogEntryList;
import tran.example.basicwebapp.services.util.BlogList;

public interface BlogService {
    /**
     * @param blogId the id of the blog to add this BlogEntry to
     * @param data the BlogEntry containing the data to be used for creating the new entity
     * @return the created BlogEntry with a generated ID
     * @throws tran.example.basicwebapp.services.exceptions.BlogNotFoundException if the blog to add to cannot be found
     */
    public BlogEntry createBlogEntry(Long blogId, BlogEntry data);

    public BlogList findAllBlogs(); // this can act as an index as it shows all the blogs.

    public BlogEntryList findAllBlogEntries(Long blogId); // findBlog all associated blog entries

    public Blog findBlogById(Long id);
}
