package tran.example.basicwebapp.services;

import tran.example.basicwebapp.domain.BlogEntry;

public interface BlogEntryService {
    /**
     * will be used when creating a mock object.
     * @param id The id of the BlogEntry
     * @return The BlogEntry with the specified id, null if no BlogEntry is found.
     */
    public BlogEntry find(Long id);

    /**
     * will be used to delete the BlogEntry.
     * @param id The id of the BlogEntry.
     * @return Returns the BlogEntry that was deleted or null if it was not found.
     */
    public BlogEntry delete(Long id);

    /**
     * @param id the id of the BlogEntry to update
     * @param data the BlogEntry containing the data to be used for the update
     * @return the updated BlogEntry or null if the BlogEntry with the id cannot be found
     */
    public BlogEntry update(Long id, BlogEntry data);
}
