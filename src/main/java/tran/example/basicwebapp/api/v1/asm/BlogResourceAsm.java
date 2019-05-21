package tran.example.basicwebapp.api.v1.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tran.example.basicwebapp.api.v1.model.BlogResource;
import tran.example.basicwebapp.controllers.v1.AccountController;
import tran.example.basicwebapp.controllers.v1.BlogController;
import tran.example.basicwebapp.domain.Blog;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class BlogResourceAsm extends ResourceAssemblerSupport<Blog, BlogResource> {
    public BlogResourceAsm() {
        super(BlogController.class, BlogResource.class);
    }

    @Override
    public BlogResource toResource(Blog blog) {
        BlogResource resource = new BlogResource();
        resource.setTitle(blog.getTitle());
        resource.setRid(blog.getId());
        resource.add(linkTo(BlogController.class).slash(blog.getId()).withSelfRel());
        resource.add(linkTo(BlogController.class).slash(blog.getId()).slash("blog-entries").withRel("entries"));
        if(blog.getOwner() != null)
            resource.add(linkTo(AccountController.class).slash(blog.getOwner().getId()).withRel("owner"));
        return resource;
    }
}
