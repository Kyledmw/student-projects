package com.appl_maint_mngt.common.repositories;

import com.appl_maint_mngt.common.repositories.interfaces.IReadableRepositoryRetriever;
import com.appl_maint_mngt.common.repositories.interfaces.IRepositoryController;
import com.appl_maint_mngt.common.repositories.interfaces.IRepositoryFactory;
import com.appl_maint_mngt.common.repositories.interfaces.IRepositoryObserverHandler;
import com.appl_maint_mngt.common.repositories.interfaces.IRepositoryUnObserveHandler;
import com.appl_maint_mngt.common.repositories.interfaces.IUpdateableRepositoryRetriever;

/**
 * Created by Kyle on 07/04/2017.
 */
public class RepositoryController implements IRepositoryController {

    private IRepositoryFactory repositoryFactory;
    private IReadableRepositoryRetriever readableRepositoryRetriever;
    private IUpdateableRepositoryRetriever updateableRepositoryRetriever;
    private IRepositoryObserverHandler repositoryObserverHandler;
    private IRepositoryUnObserveHandler repositoryUnObserveHandler;

    public RepositoryController() {
        repositoryFactory = new RepositoryFactory();
        readableRepositoryRetriever = new ReadableRepositoryRetriever(repositoryFactory);
        updateableRepositoryRetriever = new UpdateableRepositoryRetriever(repositoryFactory);
        repositoryObserverHandler = new RepositoryObserverHandler(repositoryFactory);
        repositoryUnObserveHandler = new RepositoryUnObserveHandler(repositoryFactory);
    }

    @Override
    public IReadableRepositoryRetriever getReadableRepositoryRetriever() {
        return readableRepositoryRetriever;
    }

    @Override
    public IUpdateableRepositoryRetriever getUpdateableRepositoryRetriever() {
        return updateableRepositoryRetriever;
    }

    @Override
    public IRepositoryObserverHandler getRepositoryObserverHandler() {
        return repositoryObserverHandler;
    }

    @Override
    public IRepositoryUnObserveHandler getRepositoryUnObserveHandler() {
        return repositoryUnObserveHandler;
    }

    @Override
    public void clear() {
        repositoryFactory.clear();
    }
}
