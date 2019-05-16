package tran.example.basicwebapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import tran.example.basicwebapp.model.BlogEntry;

@Controller
public class BlogEntryController {

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
}
