package com.alnajim.osama.library.Utilites;

/**
 * Created by Osama Alnajm on 01-Feb-20.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
  static  private SharedPreferences pref;

    private Editor editor;
    private Context _context;

    // Shared pref mode
    private int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "Library";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_USER_ID = "USER_ID";
    private static final String KEY_USER_NAME = "USER_NAME";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn,String userId,String userName) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.putString(KEY_USER_ID, userId);
        editor.putString(KEY_USER_NAME, userName);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public  boolean isLoggedIn()
    {
        try {
            return pref.getBoolean(KEY_IS_LOGGEDIN, false);
        }
        catch (Exception  e)
        {return false;}
    }
    public  String GetUserId(){
        return pref.getString(KEY_USER_ID, "");
    }
    public  String  GetUserName(){
        return pref.getString(KEY_USER_NAME, "");
    }

}