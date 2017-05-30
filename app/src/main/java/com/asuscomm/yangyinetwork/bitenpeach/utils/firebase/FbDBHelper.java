package com.asuscomm.yangyinetwork.bitenpeach.utils.firebase;

import android.util.Log;

import com.asuscomm.yangyinetwork.bitenpeach.models.domain.OrderSheet;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.ProcessedText;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.RawText;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.Reply;
import com.asuscomm.yangyinetwork.bitenpeach.utils.consts.FbDBPath;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaeyoung on 2017. 5. 30..
 */

public class FbDBHelper {
    private final String TAG = "JYP/"+getClass().getSimpleName();
    private static FbDBHelper mInstance = null;

    private DatabaseReference mRef = null;

    public static FbDBHelper getInstance() {
        if (mInstance == null) {
            mInstance = create();
        }
        return mInstance;
    }

    public static FbDBHelper create() {
        FbDBHelper instance = new FbDBHelper();
        instance.initFirebaseDatabase();

        return instance;
    }

    public void save(Object object) {
        Log.d(TAG, "save: object="+object.toString());
        DatabaseReference object_ref = null;
        if (object instanceof RawText) {
            object_ref = mRef.child(FbDBPath.DBPATH_RAWTEXT);
            Log.d(TAG, "save: RawText");
        }
        else if (object instanceof ProcessedText) {
            object_ref = mRef.child(FbDBPath.DBPATH_PROCESSEDTEXT);
            Log.d(TAG, "save: ProcessedText");
        }
        else if (object instanceof OrderSheet) {
            object_ref = mRef.child(FbDBPath.DBPATH_ORDERSHEET);
            Log.d(TAG, "save: OrderSheet");
        }
        else if (object instanceof Reply) {
            object_ref = mRef.child(FbDBPath.DBPATH_REPLY);
            Log.d(TAG, "save: Reply");
        } else {
            Log.e(TAG, "save: invalidObject", new Throwable("Invalid Object"));
            return;
        }

        String key = object_ref.push().getKey();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(key, object);

        object_ref.updateChildren(childUpdates);
    }

    private void initFirebaseDatabase() {
        mRef = FirebaseDatabase.getInstance().getReference(FbDBPath.DBPATH_USER_ROOT);
    }
}
