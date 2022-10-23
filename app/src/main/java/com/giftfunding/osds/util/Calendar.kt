package com.giftfunding.osds.util

import android.widget.NumberPicker

private const val MIN_MONTH = 1
private const val MAX_MONTH = 12
private const val MIN_DAY = 1
private const val EVEN_MONTH_MAX_DAY = 30
private const val ODD_MONTH_MAX_DAY = 31
private const val FEBRUARY_MONTH_MAX_DAY = 28

fun NumberPicker.initMonth(){
    this.maxValue = MAX_MONTH
    this.minValue = MIN_MONTH
}

fun NumberPicker.initDay(){
    this.minValue = MIN_DAY
}

fun NumberPicker.setDayOfMonth(month: Int){
    when(month){
        1, 3, 5, 7, 8, 10, 12 ->
            this.maxValue = ODD_MONTH_MAX_DAY
        2 ->
            this.maxValue = FEBRUARY_MONTH_MAX_DAY
        4, 6, 9, 11 ->
            this.maxValue = EVEN_MONTH_MAX_DAY
    }
}
