package com.example.androidsdemo;

import android.app.Activity;
import android.os.Bundle;
import cn.forward.androids.utils.DateUtil;
import cn.forward.androids.utils.LogUtil;
import cn.forward.androids.views.ScrollPickerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ScrollPickerViewDemo extends Activity {

    // ��ʼ�·�
    private static final int ORIGIN_YEAR = 2000;
    private static final int ORIGIN_MONTH = 1;
    private static final int ORIGIN_DAY = 1;

    // ��ѡ�����ݣ���1900������
    private static final String[] YEARS;
    static {
        ArrayList<String> list = new ArrayList<String>();
        int curYear = DateUtil.getYear();
        for (int i = 1900; i <= curYear; i++) {
            list.add(i + "");
        }
        YEARS = list.toArray(new String[list.size()]);
    }

    // �·�
    private static final String[] MONTHS = {"1", "2", "3", "4", "5", "6", "7",
            "8", "9", "10", "11", "12"};

    private ScrollPickerView mYearView;
    private ScrollPickerView mMonthView;
    private ScrollPickerView mDayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollpickerview);

        setTitle("ScrollPickerView");

        mYearView = (ScrollPickerView) this.findViewById(R.id.view_year);
        mMonthView = (ScrollPickerView) this.findViewById(R.id.view_month);
        mDayView = (ScrollPickerView) this.findViewById(R.id.view_day);

        init();

    }

    private void init() {
        // ��������
        mYearView.setData(new ArrayList<String>(Arrays.asList(YEARS)));
        mMonthView.setData(new ArrayList<String>(Arrays.asList(MONTHS)));
        mDayView.setData(DateUtil.getMonthDaysArray(ORIGIN_YEAR, ORIGIN_MONTH));

        // ���ó�ʼֵ
        mYearView.setSelectedPosition(mYearView.getData().indexOf(
                "" + ORIGIN_YEAR));
        mMonthView.setSelectedPosition(ORIGIN_MONTH - 1);
        mDayView.setSelectedPosition(ORIGIN_DAY - 1);

        // �������
        mYearView.setOnSelectedListener(new ScrollPickerView.OnSelectedListener() {
            @Override
            public void onSelected(List<String> data, int position) {
                LogUtil.i("hzw", "year " + mYearView.getSelectedItem());

                int month = Integer.parseInt(mMonthView.getSelectedItem());
                // ���·�,��������
                if (month == 2) {
                    changeMonthDays();
                }
            }
        });

        // �����·�
        mMonthView.setOnSelectedListener(new ScrollPickerView.OnSelectedListener() {
            @Override
            public void onSelected(List<String> data, int position) {

                LogUtil.i("hzw", "month " + mMonthView.getSelectedItem());

                changeMonthDays();


            }
        });

    }

    // ��������
    private void changeMonthDays() {
        int year = Integer.parseInt(mYearView.getSelectedItem());
        int month = Integer.parseInt(mMonthView.getSelectedItem());
        int day = Integer.parseInt(mDayView.getSelectedItem());
        List<String> dayList = DateUtil.getMonthDaysArray(year, month);

        mDayView.setData(dayList);
        mDayView.setSelectedPosition(day > dayList.size() ? dayList
                .size() - 1 : day - 1);
    }

}
