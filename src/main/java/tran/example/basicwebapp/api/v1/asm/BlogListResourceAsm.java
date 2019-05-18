package tran.example.basicwebapp.api.v1.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tran.example.basicwebapp.api.v1.model.BlogListResource;
import tran.example.basicwebapp.controllers.v1.BlogController;
import tran.example.basicwebapp.services.util.BlogList;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class BlogListResourceAsm extends ResourceAssemblerSupport<BlogList, BlogListResource> {

    public BlogListResourceAsm()
    {
        super(BlogController.class, BlogListResource.class);
    }

    @Override
    public BlogListResource toResource(BlogList blogList) {
        BlogListResource res = new BlogListResource();
        res.setBlogs(new BlogResourceAsm().toResources(blogList.getBlogs()));
        return res;
    }
}
