package com.asuscomm.yangyinetwork.bitenpeach.utils.firebase;

import android.util.Log;

import com.asuscomm.yangyinetwork.bitenpeach.models.domain.OrderSheet;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.ProcessedText;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.RawText;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.Reply;
import com.asuscomm.yangyinetwork.bitenpeach.models.logic.OrderSheetValidater;
import com.asuscomm.yangyinetwork.bitenpeach.utils.consts.FbDBPath;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        String fromPhoneNumber;
        String objectName;

        DatabaseReference object_ref;
        if (object instanceof RawText) {
            objectName = FbDBPath.DBPATH_RAWTEXT;
            fromPhoneNumber = ((RawText)object).getPhoneNumber();
            Log.d(TAG, "save: RawText");
        }
        else if (object instanceof ProcessedText) {
            objectName = FbDBPath.DBPATH_PROCESSEDTEXT;
            fromPhoneNumber = ((ProcessedText)object).getPhoneNumber();
            Log.d(TAG, "save: ProcessedText");
        }
        else if (object instanceof OrderSheet) {
            OrderSheet orderSheet = (OrderSheet) object;
            objectName = FbDBPath.DBPATH_ORDERSHEET;
            fromPhoneNumber = orderSheet.getFrom_phone_number();
            if (OrderSheetValidater.orderSheetFilled(orderSheet)) {
                List<String> paths = new ArrayList<>();
                paths.add(fromPhoneNumber);
                paths.add(FbDBPath.DBPATH_ONGOINGORDERSHEET);
                clear(findRef(mRef, paths));
            } else {
                List<String> paths = new ArrayList<>();
                paths.add(fromPhoneNumber);
                paths.add(FbDBPath.DBPATH_ONGOINGORDERSHEET);
                clear(findRef(mRef, paths));
                save(findRef(mRef, paths), object);
            }
            Log.d(TAG, "save: OrderSheet");
        }
        else if (object instanceof Reply) {
            objectName = FbDBPath.DBPATH_REPLY;
            fromPhoneNumber = ((Reply)object).getPhoneNumber();
            Log.d(TAG, "save: Reply");
        } else {
            Log.e(TAG, "save: invalidObject", new Throwable("Invalid Object"));
            return;
        }

        object_ref = mRef.child(fromPhoneNumber).child(objectName);
        String key = object_ref.push().getKey();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(key, object);

        object_ref.updateChildren(childUpdates);
    }

    private void save(DatabaseReference ref, Object object) {
        Log.d(TAG, "save: ");
        String key = ref.push().getKey();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(key, object);

        ref.updateChildren(childUpdates);
    }

    private void initFirebaseDatabase() {
        mRef = FirebaseDatabase.getInstance().getReference(FbDBPath.DBPATH_USER_ROOT);
    }

    private void clear(DatabaseReference ref) {
        Log.d(TAG, "clear: ");
        ref.removeValue();
    }

    private DatabaseReference findRef(DatabaseReference ref, List<String> paths) {
        Log.d(TAG, "findRef: paths="+paths.toString());
        DatabaseReference result = ref;
        for (String path:
             paths) {
            result = result.child(path);
        }
        return result;
    }
}
