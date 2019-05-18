package tran.example.basicwebapp.repositories;

import tran.example.basicwebapp.domain.Blog;

import java.util.List;

public interface BlogRepository {
    public Blog createBlog(Blog data);
    public List<Blog> findAllBlogs();
    public Blog findBlog(Long id);
    public Blog findBlogByTitle(String title);
    public List<Blog> findBlogsByAccount(Long accountId);
}
