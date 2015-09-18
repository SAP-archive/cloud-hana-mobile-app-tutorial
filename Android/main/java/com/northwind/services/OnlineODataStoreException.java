package com.northwind.services;

/**
 * Exception class for OnlineODataStore operations
 */
public class OnlineODataStoreException extends Exception
{

    private static final long serialVersionUID = 1L;

    public OnlineODataStoreException(String errorMessage)
    {
        super(errorMessage);
    }

    public OnlineODataStoreException(Throwable throwable)
    {
        super(throwable);
    }

}
