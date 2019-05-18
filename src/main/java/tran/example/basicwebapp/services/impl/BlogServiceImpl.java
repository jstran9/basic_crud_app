package tran.example.basicwebapp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tran.example.basicwebapp.domain.Blog;
import tran.example.basicwebapp.domain.BlogEntry;
import tran.example.basicwebapp.repositories.BlogEntryRepository;
import tran.example.basicwebapp.repositories.BlogRepository;
import tran.example.basicwebapp.services.BlogService;
import tran.example.basicwebapp.services.exceptions.BlogNotFoundException;
import tran.example.basicwebapp.services.util.BlogEntryList;
import tran.example.basicwebapp.services.util.BlogList;

import javax.persistence.Entity;
import javax.persistence.PersistenceContext;

@Service
@Transactional // this is the same as putting the annotation at each method.
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepo;

    @Autowired
    private BlogEntryRepository entryRepo;

    @Override
    public BlogEntry createBlogEntry(Long blogId, BlogEntry data) {
        Blog blog = blogRepo.findBlog(blogId);
        if(blog == null)
        {
            throw new BlogNotFoundException();
        }
        BlogEntry entry = entryRepo.createBlogEntry(data);
        entry.setBlog(blog);
        return entry;
    }

    @Override
    public BlogList findAllBlogs() {
        return new BlogList(blogRepo.findAllBlogs());
    }

    @Override
    public BlogEntryList findAllBlogEntries(Long blogId) {
        Blog blog = blogRepo.findBlog(blogId);
        if(blog == null)
        {
            throw new BlogNotFoundException();
        }
        return new BlogEntryList(blogId, entryRepo.findByBlogId(blogId));
    }

    @Override
    public Blog findBlogById(Long id) {
        return blogRepo.findBlog(id);
    }
}
