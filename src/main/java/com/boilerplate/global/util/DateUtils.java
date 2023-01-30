package com.boilerplate.global.util;

import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@UtilityClass
public class DateUtils {

    public Date getCurrentDate() {
        return new Date();
    }

    public Date addTime(Date date, long millisecond) {
        return new Date(date.getTime() + millisecond);
    }

    /**
     * 날짜를 Pattern 에 맞는 형식의 문자열로 변환
     * ex) yyyy, MM, dd, yyyy-MM-dd HH:mm:ss ...
     **/
    public String formatDate(Date date, String pattern) {
        if (Objects.isNull(date)) {
            throw new NullPointerException();
        }

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }
}
