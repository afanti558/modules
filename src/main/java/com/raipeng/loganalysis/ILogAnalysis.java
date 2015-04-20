package com.raipeng.loganalysis;

import java.text.ParseException;
import java.util.Map;

public interface ILogAnalysis {

    void statistic(LogData logData, String analysiskey, String starttime, String endtime, Map<String, String> map) throws ParseException;

}
