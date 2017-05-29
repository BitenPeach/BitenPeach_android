package com.asuscomm.yangyinetwork.bitenpeach.models.logic.witai;

import android.util.Log;

import com.asuscomm.yangyinetwork.bitenpeach.models.domain.OrderSheet;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.ProcessedText;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.witai.MeaningOfSentence;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.HashMap;

import static com.asuscomm.yangyinetwork.bitenpeach.models.domain.OrderSheet.COMPONENTS.AMOUNT_OF_MONEY_IDX;

/**
 * Created by jaeyoung on 2017. 5. 29..
 */

public class WitaiParser {
    private static final String TAG = "JYP/WitaiParser";
    public static ProcessedText witaiParser(MeaningOfSentence sentence) {
        ProcessedText processedText = new ProcessedText();
        HashMap<String, ArrayList> entities = sentence.getEntities();

        if( entities.containsKey("intent") ) {
            ArrayList<LinkedTreeMap> candidates = entities.get("intent");
            for (int i = 0; i < candidates.size(); i++) {
                LinkedTreeMap<String, String> candidate= candidates.get(i);
                String intent = candidate.get("value");
                if(isValidIntent(intent)) {
                    Log.i(TAG, "witaiParser: valid intent="+intent);
                    processedText.setIntent(intent);
                    break;
                }
                Log.d(TAG, "witaiParser: intent="+intent);
            }
        }

        for (String component:
                OrderSheet.COMPONENTS.NAMES) {
            if( entities.containsKey(component) ) {
                ArrayList<LinkedTreeMap> candidates = entities.get(component);
                for (int i = 0; i < candidates.size(); i++) {
                    LinkedTreeMap candidate= candidates.get(i);
                    processedText.addContent(component, candidate.get("value"));
                    Log.d(TAG, "witaiParser: component=" + component + " value=" + candidate.get("value"));
                }
            }
        }


        return processedText;
    }

    public static boolean isValidIntent(String intent) {
        if( ProcessedText.INTENT.ORDER.equals(intent) ) {
            return true;
        } else if ( ProcessedText.INTENT.NEWORDER.equals(intent) ) {
            return true;
        } else if (
                ProcessedText.INTENT.SUPPLEMENTARYORDER.equals(intent) ) {
            return true;
        } else if (
                ProcessedText.INTENT.CANCEL.equals(intent) ) {
            return true;
        } else if (
                ProcessedText.INTENT.CHAT.equals(intent) ) {
            return true;
        } else { return false; }

    }
}
