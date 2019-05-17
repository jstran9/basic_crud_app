package tran.example.basicwebapp.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tran.example.basicwebapp.api.v1.asm.BlogEntryResourceAsm;
import tran.example.basicwebapp.api.v1.model.BlogEntryResource;
import tran.example.basicwebapp.domain.BlogEntry;
import tran.example.basicwebapp.services.BlogEntryService;

@Controller
@RequestMapping("/api/v1/blog-entries")
public class BlogEntryController {

    private BlogEntryService blogEntryService;

    public BlogEntryController(BlogEntryService blogEntryService) {
        this.blogEntryService = blogEntryService;
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("WHAT!");
        return "view";
    }

    @GetMapping("/testBE")
    public ResponseEntity<Object> testBlogEntry() {
        System.out.println("inside testBlogEntry");
        BlogEntry blogEntry = new BlogEntry();
        blogEntry.setTitle("Test Blog Entry");
        return new ResponseEntity<>(blogEntry, HttpStatus.OK);
    }

    @PostMapping("/testPBE")
    public @ResponseBody BlogEntry testPostBlogEntry(@RequestBody BlogEntry blogEntry/* Receive JSON object */) {
        return blogEntry;
    }

    @GetMapping("/testBEAlt")
    public @ResponseBody BlogEntry testBlogEntryAlt() {
        System.out.println("inside testBlogEntry");
        BlogEntry blogEntry = new BlogEntry();
        blogEntry.setTitle("Test Blog Entry");
        return blogEntry;
    }

    @GetMapping(value = "/{blogEntryId}")
    public ResponseEntity<BlogEntryResource> getBlogEntry(@PathVariable Long blogEntryId) {
        BlogEntry blogEntry = blogEntryService.findBlogEntry(blogEntryId);
        if (blogEntry != null) {
            BlogEntryResource blogEntryResource = new BlogEntryResourceAsm().toResource(blogEntry);
            return new ResponseEntity<>(blogEntryResource, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping(value="/{blogEntryId}")
    public ResponseEntity<BlogEntryResource> deleteBlogEntry(
            @PathVariable Long blogEntryId) {
        BlogEntry entry = blogEntryService.deleteBlogEntry(blogEntryId);
        if(entry != null)
        {
            BlogEntryResource res = new BlogEntryResourceAsm().toResource(entry);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value="/{blogEntryId}")
    public ResponseEntity<BlogEntryResource> updateBlogEntry(
            @PathVariable Long blogEntryId, @RequestBody BlogEntryResource sentBlogEntry) {
        BlogEntry updatedEntry = blogEntryService.updateBlogEntry(blogEntryId, sentBlogEntry.toBlogEntry());
        if(updatedEntry != null)
        {
            BlogEntryResource res = new BlogEntryResourceAsm().toResource(updatedEntry);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
