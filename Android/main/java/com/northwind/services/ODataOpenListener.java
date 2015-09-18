package com.northwind.services;

import com.northwind.nwandroid.R;
import com.sap.smp.client.odata.exception.ODataException;
import com.sap.smp.client.odata.online.OnlineODataStore;
import com.sap.smp.client.odata.online.OnlineODataStore.OpenListener;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Listener for OData connections
 */
public class ODataOpenListener implements OpenListener
{
    private static ODataOpenListener instance;

    private final CountDownLatch latch = new CountDownLatch(1);
    private OnlineODataStore store;
    private Exception error;

    private ODataOpenListener()
    {

    }

    /**
     * @return ODataOpenListener
     */
    public static ODataOpenListener getInstance()
    {
        if (instance == null) {
            instance = new ODataOpenListener();
        }
        return instance;
    }


    @Override
    public void storeOpenError(ODataException e)
    {
        this.error = e;
        latch.countDown();
    }

    @Override
    public void storeOpened(OnlineODataStore store)
    {
        this.store = store;
        latch.countDown();
    }

    private synchronized boolean finished()
    {
        return (store != null || error != null);
    }

    public synchronized Exception getError()
    {
        return error;
    }

    public synchronized OnlineODataStore getStore()
    {
        return store;
    }

    /**
     * Waits for the completion of the asynchronous process. In case this listener is not invoked within "timeout_ODataOpenListener"
     * seconds then it fails with an exception.
     */
    public void waitForCompletion()
    {
        try {
            if (!latch.await(R.integer.timeout_ODataOpenListener, TimeUnit.SECONDS))
                throw new IllegalStateException(String.valueOf(R.string.open_listener_not_called_timeout));
            else if (!finished())
                throw new IllegalStateException(String.valueOf(R.string.open_listener_not_in_finished_state));
        } catch (InterruptedException e) {
            throw new IllegalStateException(String.valueOf(R.string.open_listener_interrupted), e);
        }
    }

}
