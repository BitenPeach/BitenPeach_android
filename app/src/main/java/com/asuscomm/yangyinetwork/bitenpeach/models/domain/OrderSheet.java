package com.asuscomm.yangyinetwork.bitenpeach.models.domain;

/**
 * Created by jaeyoung on 2017. 5. 29..
 */

public class OrderSheet {
    public interface COMPONENTS {
        int LOCATION_IDX = 0;
        int AMOUNT_OF_MONEY_IDX = 1;

        int FROM_NAME_IDX = 2;

//        int TO_LOCATION_IDX = 3;
        int TO_PHONE_NUMBER_IDX = 4;
        int TO_NAME_IDX = 5;

        int PEACH_SIZE_IDX = 6;
        int PEACH_KIND_IDX = 7;
        int PEACH_NUMOFBOX_IDX = 8;
//        int PEACH_AMOUNTOFMONEY_IDX = 9;
        String[] NAMES = {
                "location",
                "amount_of_money",

                "from_name",

                "to_location_unused",
                "phone_number",
                "to_name",

                "peach_size",
                "peach_kind",
                "peach_numofbox",
                "peach_amount_of_money_unused"
        };
    }

    private String to_location = null;
    private String to_name = null;
    private String to_phone_number = null;

    private String from_name = null;
    private String from_phone_number = null;

    private Integer peach_size = null;
    public interface PEACH_SIZE {
        int SIZE9 = 9;
        int SIZE10= 10;
        int SIZE11 = 11;
        int SIZE12 = 12;
        int SIZE1314 = 1314;
        int SIZE1516 = 1516;
    }

    private String peach_kind = null;
    public interface PEACH_KIND {
        String WHITEPEACH = "백도";
        String YELLOWPEACH = "황도";
    }
    private Integer peach_numofbox = null;
    private Double peach_amount_of_money = null;

    public OrderSheet() {
    }

    public OrderSheet(String phone_number) {
        from_phone_number = phone_number;
    }

    public boolean isEnough() {
        if(!isEnoughPeachInfo()) {
            return false;
        }
        //todo implement
        if(!true) {
            return false;
        }

        return true;
    }

    public boolean isEnoughPeachInfo() {
        // todo implement
        return true;
    }

    public String getTo_location() {
        return to_location;
    }

    public void setTo_location(String to_location) {
        this.to_location = to_location;
    }

    public String getTo_name() {
        return to_name;
    }

    public void setTo_name(String to_name) {
        this.to_name = to_name;
    }

    public String getTo_phone_number() {
        return to_phone_number;
    }

    public void setTo_phone_number(String to_phone_number) {
        this.to_phone_number = to_phone_number;
    }

    public String getFrom_name() {
        return from_name;
    }

    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }

    public String getFrom_phone_number() {
        return from_phone_number;
    }

    public void setFrom_phone_number(String from_phone_number) {
        this.from_phone_number = from_phone_number;
    }

    public Integer getPeach_size() {
        return peach_size;
    }

    public void setPeach_size(Integer peach_size) {
        this.peach_size = peach_size;
    }

    public String getPeach_kind() {
        return peach_kind;
    }

    public void setPeach_kind(String peach_kind) {
        this.peach_kind = peach_kind;
    }

    public Integer getPeach_numofbox() {
        return peach_numofbox;
    }

    public void setPeach_numofbox(Integer peach_numofbox) {
        this.peach_numofbox = peach_numofbox;
    }

    public Double getPeach_amount_of_money() {
        return peach_amount_of_money;
    }

    public void setPeach_amount_of_money(Double peach_amount_of_money) {
        this.peach_amount_of_money = peach_amount_of_money;
    }

    @Override
    public String toString() {
        return "OrderSheet{" +
                "isEnough()='" + isEnough()+ '\'' +
                ", to_location='" + to_location + '\'' +
                ", to_name='" + to_name + '\'' +
                ", to_phone_number='" + to_phone_number + '\'' +
                ", from_name='" + from_name + '\'' +
                ", from_phone_number='" + from_phone_number + '\'' +
                ", peach_size=" + peach_size +
                ", peach_kind='" + peach_kind + '\'' +
                ", peach_numofbox=" + peach_numofbox +
                ", peach_amount_of_money=" + peach_amount_of_money +
                '}';
    }
}
