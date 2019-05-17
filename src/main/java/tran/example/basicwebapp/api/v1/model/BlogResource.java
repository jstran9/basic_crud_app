package tran.example.basicwebapp.api.v1.model;

import org.springframework.hateoas.ResourceSupport;
import tran.example.basicwebapp.domain.Blog;

/**
 * Created by Chris on 6/30/14.
 */
public class BlogResource extends ResourceSupport {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Blog toBlog() {
        Blog blog = new Blog();
        blog.setTitle(title);
        return blog;
    }
}
