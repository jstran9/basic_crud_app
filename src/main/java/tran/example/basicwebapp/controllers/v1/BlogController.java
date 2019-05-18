package tran.example.basicwebapp.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tran.example.basicwebapp.api.v1.asm.BlogEntryListResourceAsm;
import tran.example.basicwebapp.api.v1.asm.BlogEntryResourceAsm;
import tran.example.basicwebapp.api.v1.asm.BlogListResourceAsm;
import tran.example.basicwebapp.api.v1.asm.BlogResourceAsm;
import tran.example.basicwebapp.api.v1.model.BlogEntryListResource;
import tran.example.basicwebapp.api.v1.model.BlogEntryResource;
import tran.example.basicwebapp.api.v1.model.BlogListResource;
import tran.example.basicwebapp.api.v1.model.BlogResource;
import tran.example.basicwebapp.domain.Blog;
import tran.example.basicwebapp.domain.BlogEntry;
import tran.example.basicwebapp.exceptions.NotFoundException;
import tran.example.basicwebapp.services.BlogService;
import tran.example.basicwebapp.services.exceptions.BlogNotFoundException;
import tran.example.basicwebapp.services.util.BlogEntryList;
import tran.example.basicwebapp.services.util.BlogList;

import java.net.URI;

@Controller
@RequestMapping("/api/v1/blogs")
public class BlogController {
    private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public ResponseEntity<BlogListResource> findAllBlogs()
    {
        BlogList blogList = blogService.findAllBlogs();
        BlogListResource blogListRes = new BlogListResourceAsm().toResource(blogList);
        return new ResponseEntity<>(blogListRes, HttpStatus.OK);
    }

    @GetMapping(value="/{blogId}")
    public ResponseEntity<BlogResource> getBlog(@PathVariable Long blogId)
    {
        Blog blog = blogService.findBlogById(blogId);
        BlogResource res = new BlogResourceAsm().toResource(blog);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping(value="/{blogId}/blog-entries")
    public ResponseEntity<BlogEntryResource> createBlogEntry(
            @PathVariable Long blogId,
            @RequestBody BlogEntryResource sentBlogEntry
    ) {
        BlogEntry createdBlogEntry = null;
        try {
            createdBlogEntry = blogService.createBlogEntry(blogId, sentBlogEntry.toBlogEntry());
            BlogEntryResource createdResource = new BlogEntryResourceAsm().toResource(createdBlogEntry);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(createdResource.getLink("self").getHref()));
            return new ResponseEntity<>(createdResource, headers, HttpStatus.CREATED);
        } catch (BlogNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @GetMapping(value="/{blogId}/blog-entries")
    public ResponseEntity<BlogEntryListResource> findAllBlogEntries(
            @PathVariable Long blogId)
    {
        try {
            BlogEntryList list = blogService.findAllBlogEntries(blogId);
            BlogEntryListResource res = new BlogEntryListResourceAsm().toResource(list);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch(BlogNotFoundException exception)
        {
            throw new NotFoundException(exception);
        }
    }

}
