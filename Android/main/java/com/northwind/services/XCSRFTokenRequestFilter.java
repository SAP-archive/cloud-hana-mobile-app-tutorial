package com.northwind.services;

import android.util.Log;

import com.sap.maf.tools.logon.core.LogonCoreContext;
import com.sap.maf.tools.logon.core.LogonCoreException;
import com.sap.smp.client.httpc.HttpMethod;
import com.sap.smp.client.httpc.events.ISendEvent;
import com.sap.smp.client.httpc.filters.IRequestFilter;
import com.sap.smp.client.httpc.filters.IRequestFilterChain;

/**
 * Manages XCSRF Tokens for OData requests
 */
public class XCSRFTokenRequestFilter implements IRequestFilter
{
    private static final String HTTP_HEADER_SUP_APPCID = "X-SUP-APPCID";
    private static final String HTTP_HEADER_SMP_APPCID = "X-SMP-APPCID";

    private static XCSRFTokenRequestFilter instance;

    private String lastXCSRFToken = null;
    private final LogonCoreContext lgCtx;

    private XCSRFTokenRequestFilter(LogonCoreContext logonContext)
    {
        lgCtx = logonContext;
    }

    /**
     * Creates new request filter if non exists
     * @param logonContext logon context
     * @return XCSRFTokenRequestFilter request filter
     */
    public static XCSRFTokenRequestFilter getInstance(LogonCoreContext logonContext)
    {
        if (instance == null)
        {
            instance = new XCSRFTokenRequestFilter(logonContext);
        }
        return instance;
    }


    @Override
    public Object filter(ISendEvent event, IRequestFilterChain chain)
    {
        HttpMethod method = event.getMethod();
        Log.d("XCSRFTokenRequestFilter", "method: " + method + ", lastXCSRFToken: " + lastXCSRFToken);
        if (method == HttpMethod.GET /* && lastXCSRFToken == null */)
        {
            event.getRequestHeaders().put("X-CSRF-Token", "Fetch");
        }
        else if (lastXCSRFToken != null)
        {
            event.getRequestHeaders().put("X-CSRF-Token", lastXCSRFToken);
        }
        else
        {
            event.getRequestHeaders().put("X-Requested-With", "XMLHttpRequest");
        }

        String appConnID = null;
        try
        {
            appConnID = lgCtx.getConnId();
        }
        catch (LogonCoreException e)
        {
            Log.e("XCSRFTokenRequestFilter", "error getting connection id", e);
        }

        //for backward compatibility. not needed for SMP 3.0 SP05
        if (appConnID != null)
        {
            event.getRequestHeaders().put(HTTP_HEADER_SUP_APPCID, appConnID);
            event.getRequestHeaders().put(HTTP_HEADER_SMP_APPCID, appConnID);
        }
        event.getRequestHeaders().put("Connection", "Keep-Alive");

        return chain.filter();
    }

    @Override
    public Object getDescriptor()
    {
        return "XCSRFTokenRequestFilter";
    }

    public void setLastXCSRFToken(String lastXCSRFToken)
    {
        this.lastXCSRFToken = lastXCSRFToken;
    }

}