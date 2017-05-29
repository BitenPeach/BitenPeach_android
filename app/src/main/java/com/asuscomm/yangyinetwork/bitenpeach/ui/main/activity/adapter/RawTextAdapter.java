package com.asuscomm.yangyinetwork.bitenpeach.ui.main.activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asuscomm.yangyinetwork.bitenpeach.R;
import com.asuscomm.yangyinetwork.bitenpeach.models.domain.RawText;

import java.util.List;

/**
 * Created by jaeyoung on 2017. 4. 7..
 */

public class RawTextAdapter extends RecyclerView.Adapter<RawTextAdapter.ViewHolder>{
    List<RawText> mRawTexts;

    public RawTextAdapter(List<RawText> mRawTexts) {
        this.mRawTexts = mRawTexts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rawtext, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return mRawTexts.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RawText rawText = mRawTexts.get(position);
        holder.phoneNumberTv.setText(rawText.getPhoneNumber());
        holder.messageBodyTv.setText(rawText.getMessageBody());
    }

    public void add(RawText rawText) {
        this.mRawTexts.add(rawText);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView phoneNumberTv;
        public final TextView messageBodyTv;

        public ViewHolder(View v) {
            super(v);
            this.phoneNumberTv = (TextView) v.findViewById(R.id.phoneNumberTv);
            this.messageBodyTv = (TextView) v.findViewById(R.id.messageBodyTv);
        }
    }
}
