package tran.example.basicwebapp.services.util;

import tran.example.basicwebapp.domain.Blog;

import java.util.ArrayList;
import java.util.List;

public class BlogList {

    private List<Blog> blogs = new ArrayList<Blog>();

    public BlogList() { }

    public BlogList(List resultList) {
        this.blogs = resultList;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
