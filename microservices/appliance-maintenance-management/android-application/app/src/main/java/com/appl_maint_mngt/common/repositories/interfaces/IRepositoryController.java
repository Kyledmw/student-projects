package com.appl_maint_mngt.common.repositories.interfaces;

/**
 * Created by Kyle on 07/04/2017.
 */

public interface IRepositoryController {

    IReadableRepositoryRetriever getReadableRepositoryRetriever();
    IUpdateableRepositoryRetriever getUpdateableRepositoryRetriever();
    IRepositoryObserverHandler getRepositoryObserverHandler();
    IRepositoryUnObserveHandler getRepositoryUnObserveHandler();
    void clear();
}
