package com.asuscomm.yangyinetwork.bitenpeach.models.witai;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jaeyoung on 2017. 4. 18..
 */
public class MeaningOfSentence implements Serializable {
    @SerializedName("msg_id")
    private String msg_id;
    @SerializedName("_text")
    private String _text;
    @SerializedName("entities")
    private Entity entities;

    public MeaningOfSentence(String msg_id, String _text, Entity entities) {
        this.msg_id = msg_id;
        this._text = _text;
        this.entities = entities;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String get_text() {
        return _text;
    }

    public void set_text(String _text) {
        this._text = _text;
    }

    public Entity getEntities() {
        return entities;
    }

    public void setEntities(Entity entities) {
        this.entities = entities;
    }
}
