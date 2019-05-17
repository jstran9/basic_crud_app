package tran.example.basicwebapp.api.v1.model;

import org.springframework.hateoas.ResourceSupport;
import tran.example.basicwebapp.domain.BlogEntry;

/**
 * I believe this provides the same functionality as what a DTO would do.
 */
public class BlogEntryResource extends ResourceSupport {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BlogEntry toBlogEntry() {
        BlogEntry blogEntry = new BlogEntry();
        blogEntry.setTitle(title);
        return blogEntry;
    }
}
