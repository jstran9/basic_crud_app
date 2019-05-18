package tran.example.basicwebapp.services.util;


import tran.example.basicwebapp.domain.BlogEntry;

import java.util.ArrayList;
import java.util.List;

public class BlogEntryList {
    private List<BlogEntry> entries = new ArrayList<BlogEntry>();
    private Long blogId;

    public BlogEntryList() { }

    public BlogEntryList(Long blogId, List<BlogEntry> entries) {
        this.blogId = blogId;
        this.entries = entries;
    }

    public List<BlogEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<BlogEntry> entries) {
        this.entries = entries;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }
}
