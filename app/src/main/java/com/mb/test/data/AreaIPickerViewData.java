package com.mb.test.data;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * Created by Administrator on 2016/12/27 0027.
 */

public class AreaIPickerViewData implements IPickerViewData {

    private String code;
    private String name;

    public AreaIPickerViewData(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {

        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}
