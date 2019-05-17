package tran.example.basicwebapp.api.v1.model;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 7/1/14.
 */
public class BlogListResource extends ResourceSupport {
    private List<BlogResource> blogs = new ArrayList<BlogResource>();

    public List<BlogResource> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<BlogResource> blogs) {
        this.blogs = blogs;
    }
}
