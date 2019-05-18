package tran.example.basicwebapp.api.v1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class BlogEntryListResource extends ResourceSupport {
    private List<BlogEntryResource> entries;

    public List<BlogEntryResource> getEntries() {
        return entries;
    }

    public void setEntries(List<BlogEntryResource> entries) {
        this.entries = entries;
    }
}
