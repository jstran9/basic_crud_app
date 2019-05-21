package tran.example.basicwebapp.api.v1.asm;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tran.example.basicwebapp.api.v1.model.BlogEntryResource;
import tran.example.basicwebapp.controllers.v1.BlogController;
import tran.example.basicwebapp.controllers.v1.BlogEntryController;
import tran.example.basicwebapp.domain.BlogEntry;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class BlogEntryResourceAsm extends ResourceAssemblerSupport<BlogEntry, BlogEntryResource> {
    public BlogEntryResourceAsm() {
        super(BlogEntryController.class, BlogEntryResource.class);
    }

    @Override
    public BlogEntryResource toResource(BlogEntry blogEntry) {
        BlogEntryResource resource = new BlogEntryResource();
        resource.setTitle(blogEntry.getTitle());
        resource.setContent(blogEntry.getContent());
        resource.setRid(blogEntry.getId());
        //        Link link = linkTo(methodOn(BlogEntryController.class).getBlogEntry(blogEntry.getId())).withSelfRel();
        Link self = linkTo(BlogEntryController.class).slash(blogEntry.getId()).withSelfRel();
        resource.add(self);
        if(blogEntry.getBlog() != null)
        {
            resource.add((linkTo(BlogController.class).slash(blogEntry.getBlog().getId()).withRel("blog")));
        }
        return resource;
    }
}
