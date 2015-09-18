package com.northwind.services;

import android.content.Context;
import android.util.Log;

import com.sap.maf.tools.logon.core.LogonCore;
import com.sap.maf.tools.logon.core.LogonCoreContext;
import com.sap.maf.tools.logon.logonui.api.LogonUIFacade;
import com.sap.smp.client.httpc.HttpConversationManager;
import com.sap.smp.client.httpc.IManagerConfigurator;
import com.sap.smp.client.odata.online.OnlineODataStore;

import java.net.URL;

/**
 * Creates an OData OnlineStore
 */
public class ODataOnlineManager
{
    private static final String TAG = ODataOnlineManager.class.getSimpleName();

    /**
     * Initialize an online OData store for online access
     * @param context used only to access the application context
     * @throws OnlineODataStoreException Throws exception if open fails
     */
    public static void openOnlineStore(Context context) throws OnlineODataStoreException
    {
        /**
         * ODataOpenListener implements OpenListener interface.
         * Listener to be invoked when the opening process of an OnlineODataStore.
         */
        ODataOpenListener openListener = ODataOpenListener.getInstance();
        if (openListener.getStore()==null)
        {
            LogonCore lcore = LogonCore.getInstance();
            LogonCoreContext lgCtx = lcore.getLogonContext();
            //LogonCoreContext lgCtx = LogonCore.getInstance().getLogonContext();

            //The logon configurator uses the information obtained in the registration // (i.e endpoint URL, login, etc ) to configure the conversation manager
            IManagerConfigurator configurator = LogonUIFacade.getInstance().getLogonConfigurator(context);
            HttpConversationManager manager = new HttpConversationManager(context);
            configurator.configure(manager);

            //XCSRFTokenRequestFilter implements IRequestFilter
            //Request filter that is allowed to preprocess the request before sending
            XCSRFTokenRequestFilter requestFilter = XCSRFTokenRequestFilter.getInstance(lgCtx);
            XCSRFTokenResponseFilter responseFilter = XCSRFTokenResponseFilter.getInstance(context, requestFilter);
            manager.addFilter(requestFilter);
            manager.addFilter(responseFilter);
            try
            {
                String endPointURL = lgCtx.getAppEndPointUrl();
                URL url = new URL(endPointURL);

                //Method to open a new online store asynchronously
                OnlineODataStore.open(context, url, manager, openListener, null);
                openListener.waitForCompletion();
                if (openListener.getError() != null)
                {
                    Log.e(TAG, "OnlineODataStore.open() failed");
                    throw openListener.getError();
                }
            }
            catch(Exception e)
            {
                throw new OnlineODataStoreException(e);
            }

            //Check if OnlineODataStore opened successfully
            OnlineODataStore store = openListener.getStore();
            if (store != null)
            {
                Log.d(TAG, "OnlineODataStore.open() success");
            }
            else
            {
                return;
            }
        }
        else
        {
            return;
        }
    }

}
