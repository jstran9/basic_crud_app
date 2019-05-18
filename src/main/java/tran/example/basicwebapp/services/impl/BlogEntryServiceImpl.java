package tran.example.basicwebapp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tran.example.basicwebapp.domain.BlogEntry;
import tran.example.basicwebapp.repositories.BlogEntryRepository;
import tran.example.basicwebapp.services.BlogEntryService;

@Service
@Transactional // this is the same as putting the annotation at each method.
public class BlogEntryServiceImpl implements BlogEntryService {

    @Autowired
    private BlogEntryRepository entryRepo;

    @Override
    public BlogEntry findBlogEntry(Long id) {
        return entryRepo.findBlogEntry(id);
    }

    @Override
    public BlogEntry deleteBlogEntry(Long id) {
        return entryRepo.deleteBlogEntry(id);
    }

    @Override
    public BlogEntry updateBlogEntry(Long id, BlogEntry data) {
        return entryRepo.updateBlogEntry(id, data);
    }
}
