package com.mb.test.data;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.mb.test.R;
import com.mb.test.db.Areas;
import com.mb.test.db.Citys;
import com.mb.test.db.DBManager;
import com.mb.test.db.Provinces;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class DataActivity extends Activity {

    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<AreaIPickerViewData>>> options3Items = new ArrayList<>();
    private TextView tvTime, tvOptions;
    TimePickerView pvTime;
    OptionsPickerView pvOptions;
    View vMasker;
    private DBManager mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        vMasker=findViewById(R.id.vMasker);
        tvTime=(TextView) findViewById(R.id.tvTime);
        tvOptions=(TextView) findViewById(R.id.tvOptions);
        mDb = new DBManager(getApplicationContext());

        //时间选择器
        pvTime = new TimePickerView(this, TimePickerView.Type.ALL);
        //控制时间范围
        Calendar calendar = Calendar.getInstance();
        pvTime.setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR)+20);//要在setTime 之前才有效果哦
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        //时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                tvTime.setText(getTime(date));
            }
        });
        //弹出时间选择器
        tvTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                pvTime.show();
            }
        });

        //选项选择器
        pvOptions = new OptionsPickerView(this);
        pvOptions.setCancelable(true);
        //选项1
        List<Provinces> provinces = mDb.searchProvince();
        for (int i = 0; i <provinces.size() ; i++) {
            options1Items.add(new ProvinceBean(provinces.get(i).getName()));
            String firstCityCode = provinces.get(i).getCode().substring(0, 2) + "%";
            List<Citys> cityses = mDb.searchCitys(provinces.get(i).getCode(),firstCityCode);
            ArrayList<String> options2Items_01=new ArrayList<>();
            ArrayList<ArrayList<AreaIPickerViewData>> options3Items_01 = new ArrayList<>();

            for (int j = 0; j <cityses.size() ; j++) {
                options2Items_01.add(cityses.get(j).getName());
                String firstAreaCode = cityses.get(j).getCode().substring(0, 4) + "%";
                List<Areas> areases = mDb.searchAreas(cityses.get(j).getCode(), firstAreaCode);
                ArrayList<AreaIPickerViewData> options3Items_01_01=new ArrayList<>();

                for (int k = 0; k <areases.size() ; k++) {
                    options3Items_01_01.add(new AreaIPickerViewData(areases.get(k).getCode(),areases.get(k).getName()));
                    options3Items_01.add(options3Items_01_01);
                }
                options3Items_01.add(options3Items_01_01);
            }
            options2Items.add(options2Items_01);
            options3Items.add(options3Items_01);
        }
        //三级联动效果
        pvOptions.setPicker(options1Items, options2Items, options3Items, true);
        //设置选择的三级单位
//        pwOptions.setLabels("省", "市", "区");
        pvOptions.setTitle("选择城市");
        pvOptions.setCyclic(false, false, false);
        //设置默认选中的三级项目
        //监听确定选择按钮
        pvOptions.setSelectOptions(1, 1, 1);

        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()
                        + options2Items.get(options1).get(option2)
                        + options3Items.get(options1).get(option2).get(options3).getCode();
                tvOptions.setText(tx);
                vMasker.setVisibility(View.GONE);
            }
        });
        //点击弹出选项选择器
        tvOptions.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                pvOptions.show();
            }
        });
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(pvOptions.isShowing()||pvTime.isShowing()){
                pvOptions.dismiss();
                pvTime.dismiss();
                return true;
            }
            if(pvTime.isShowing()){
                pvTime.dismiss();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
