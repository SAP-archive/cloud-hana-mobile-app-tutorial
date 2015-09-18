

package com.northwind.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.northwind.model.ProductDataSingleton;
import com.northwind.nwandroid.R;
import com.sap.maf.tools.logon.core.LogonCore;
import com.sap.maf.tools.logon.logonui.api.LogonListener;
import com.sap.maf.tools.logon.logonui.api.LogonUIFacade;
import com.sap.maf.tools.logon.manager.LogonContext;

/**
 * This class initiates the MAF-based logon to the SAP Server (HCPms or SMP3). It is mostly generic,
 * except at the end of {@link MAFLogonActivity#onLogonFinished onLogonFinished},
 * it initializes the NorthwindApp and creates/starts the Intent for the Product List UI
 *
 */
public class MAFLogonActivity extends AppCompatActivity implements LogonListener
{
    private static final String TAG = "MAFLogonActivity";

    /**
     * The operations in this method make customizations to the MAF Logon screen.
     * @param savedInstanceState Bundle with app context
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Hide MobilePlace window
        SharedPreferences prefs = getSharedPreferences(LogonCore.PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor pEditor = prefs.edit();
        pEditor.putBoolean(LogonCore.SharedPreferenceKeys.PREFERENCE_ID_MOBILEPLACE.toString(), false);
        pEditor.commit();

        // set context reference
        Context mContext = this;
        // get an instance of the LogonUIFacade
        LogonUIFacade mLogonUIFacade = LogonUIFacade.getInstance();

        //Initialize the Logon UI Facade
        mLogonUIFacade.init(this, mContext, getString(R.string.HCPMS_APP_ID));
        // Set the server URL, username, port and HTTPS status using the resource file strings
        mLogonUIFacade.setDefaultValue(LogonCore.SharedPreferenceKeys.PREFERENCE_ID_SUPSERVERURL, getString(R.string.SERVER_URL));
        mLogonUIFacade.setDefaultValue(LogonCore.SharedPreferenceKeys.PREFERENCE_ID_USERNAME, getString(R.string.USERNAME));
        mLogonUIFacade.setDefaultValue(LogonCore.SharedPreferenceKeys.PREFERENCE_ID_SUPSERVERPORT, getString(R.string.PORT));
        mLogonUIFacade.setDefaultValue(LogonCore.SharedPreferenceKeys.PREFERENCE_ID_HTTPSSTATUS, getString(R.string.SETSECURE));

        // Customize the fields displayed: Set to false to show the field, true to hide it
        mLogonUIFacade.isFieldHidden(LogonCore.SharedPreferenceKeys.PREFERENCE_ID_SUPSERVERURL, false);
        mLogonUIFacade.isFieldHidden(LogonCore.SharedPreferenceKeys.PREFERENCE_ID_SUPSERVERPORT, true);
        mLogonUIFacade.isFieldHidden(LogonCore.SharedPreferenceKeys.PREFERENCE_ID_SECCONFIG, true);
        mLogonUIFacade.isFieldHidden(LogonCore.SharedPreferenceKeys.PREFERENCE_ID_SUPSERVERFARMID, true);
        mLogonUIFacade.isFieldHidden(LogonCore.SharedPreferenceKeys.PREFERENCE_ID_URLSUFFIX, true);
        mLogonUIFacade.isFieldHidden(LogonCore.SharedPreferenceKeys.PREFERENCE_ID_MOBILEUSER, true);
        mLogonUIFacade.isFieldHidden(LogonCore.SharedPreferenceKeys.PREFERENCE_ID_ACTIVATIONCODE, true);
        mLogonUIFacade.isFieldHidden(LogonCore.SharedPreferenceKeys.PREFERENCE_ID_HTTPSSTATUS, true);
        mLogonUIFacade.isFieldHidden(LogonCore.SharedPreferenceKeys.PREFERENCE_ID_GATEWAYCLIENT, true);
        mLogonUIFacade.isFieldHidden(LogonCore.SharedPreferenceKeys.PREFERENCE_ID_SUPSERVERDOMAIN, true);
        mLogonUIFacade.isFieldHidden(LogonCore.SharedPreferenceKeys.PREFERENCE_ID_PINGPATH, true);
        mLogonUIFacade.isFieldHidden(LogonCore.SharedPreferenceKeys.PREFERENCE_ID_GWONLY, true);


        // Present the logon screen to the user
        setContentView(mLogonUIFacade.logon());

        // Hide the splash screen (do this at the end, so defaults are not reset)
        mLogonUIFacade.showSplashScreen(false);
    }


    /**
     * errorMessage - is the message for the logon failure. Possible values are: ERROR_MSG_INIT_FAILED, ERROR_MSG_INVALID_SSO_PASSCODE, ERROR_MSG_URL_NOT_PERMITTED, ERROR_MSG_NULL_RESPONSE, ERROR_MSG_REGISTRATION_FAILED, ERROR_MSG_IN_LOGON_FIELDS, ERROR_MSG_PASSCODE_TIMEOUT, ERROR_MSG_SSO_PASS_USER_CANCELLED_OPERATION, ERROR_MSG_SSO_PASS_USER_FORGOT. It is empty on logon success.
     * isUserLoggedOn -
     * logonContext - logon context
     * @param errorMessage
     * @param isUserLoggedOn
     * @param logonContext
     */
    @Override
    public void onLogonFinished(String errorMessage, boolean isUserLoggedOn, LogonContext logonContext)
    {

        //Note: isUserLoggedOn is related to the app client initialization, not actual network connectivity
        if (isUserLoggedOn && errorMessage.isEmpty())
        {

            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setContentView(R.layout.activity_initappdata);
                }
            });

            ProductDataSingleton.initialize(this);

            Intent goToNextActivity = new Intent(this, ProductListActivity.class);
            startActivity(goToNextActivity);

            finish();
        }
        else
        {
            final String errorExplanation = getString(R.string.ui_init_callbackerror,
                    new Boolean(isUserLoggedOn).toString(), errorMessage);
            Log.e(TAG, errorExplanation);
            showTextUIThread(errorExplanation);
        }
    }


    private void showTextUIThread(final String message) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MAFLogonActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onSecureStorePasswordChanged(boolean b, String s)
    {

    }

    @Override
    public void onBackendPasswordChanged(boolean b)
    {

    }

    @Override
    public void onUserDeleted()
    {

    }

    @Override
    public void onApplicationSettingsUpdated()
    {

    }

    @Override
    public void registrationInfo()
    {

    }

    @Override
    public void objectFromSecureStoreForKey()
    {

    }

    @Override
    public void onRefreshCertificate(boolean b, String s)
    {

    }
}
