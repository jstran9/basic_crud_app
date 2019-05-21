package tran.example.basicwebapp.api.v1.model;

import org.springframework.hateoas.ResourceSupport;
import tran.example.basicwebapp.domain.Blog;

/**
 * Created by Chris on 6/30/14.
 */
public class BlogResource extends ResourceSupport {

    private Long rid;

    private String title;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

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
