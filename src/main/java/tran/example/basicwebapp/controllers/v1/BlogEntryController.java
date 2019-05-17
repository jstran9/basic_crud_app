package tran.example.basicwebapp.controller.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tran.example.basicwebapp.api.v1.asm.BlogEntryResourceAsm;
import tran.example.basicwebapp.api.v1.model.BlogEntryResource;
import tran.example.basicwebapp.domain.BlogEntry;
import tran.example.basicwebapp.service.BlogEntryService;

@Controller
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

    @PostMapping("testPBE")
    public @ResponseBody BlogEntry testPostBlogEntry(@RequestBody BlogEntry blogEntry/* Receive JSON object */) {
        System.out.println("HELLO 12312321321312312321");
        return blogEntry;
    }

    @GetMapping("/testBEAlt")
    public @ResponseBody BlogEntry testBlogEntryAlt() {
        System.out.println("inside testBlogEntry");
        BlogEntry blogEntry = new BlogEntry();
        blogEntry.setTitle("Test Blog Entry");
        return blogEntry;
    }

    @GetMapping(value = "/api/v1/blog-entries/{blogEntryId}")
    public ResponseEntity<BlogEntryResource> getBlogEntry(@PathVariable Long blogEntryId) {
        BlogEntry blogEntry = blogEntryService.find(blogEntryId);
        BlogEntryResource blogEntryResource = new BlogEntryResourceAsm().toResource(blogEntry);
        return new ResponseEntity<>(blogEntryResource, HttpStatus.OK);
    }


}
