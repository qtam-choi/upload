package com.admin.upload.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @ClassName : DateUtil.java
 * @Description : 
 * @Author  모바일개발팀
 * @Since 2013. 3. 27.
 * @Version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일             수정자                수정내용
 *  --------------        ---------------       ---------------------------
 *   2013. 3. 27.        모바일개발팀            최초작성
 *
 * </pre>
 *  
 * Copyright (C) by SMC All right reserved.
 */
public class DateUtil {
	/**
	 * 현재날짜 가져오기. 기본포맷적용(yyyy-MM-dd)
	 * 
	 * @return 현재일자 yyyy-MM-dd형식
	 */
	public static String getToday() {
		return getToday("yyyy-MM-dd");
	}

	/**
	 * 입력받은 형식에 맞는 현재날짜(시간) 가져오기
	 * 
	 * @param fmt 날짜형식
	 * @return 현재일자
	 */
	public static String getToday(String fmt) {
		SimpleDateFormat sfmt = new SimpleDateFormat(fmt, Locale.getDefault());
		return sfmt.format(new Date());
	}

	/**
	 * Date형을 yyyy-MM-dd형의 String으로 변환.
	 * 
	 * @param date 날짜 Date형
	 * @return 날짜 String형. null일 경우 null return
	 */
	public static String dateToString(Date date) {
		if (date != null)
			return dateToString(date, "yyyy-MM-dd");
		else
			return null;
	}

	/**
	 * String형 yyyymmdd를 yyyy-MM-dd형의 String으로 변환.
	 * 
	 * @param date 날짜 String
	 * @return 날짜 String형. null일 경우 공백 "" return
	 */
	public static String chgYMDFormat(String date2) {
		String date = date2;
        if(date == null)
        {
        	date = "";
        }
        if(date.length() == 8)
        {
            String year = date.substring(0, 4);
            String month = date.substring(4, 6);
            String day = date.substring(6, 8);
            date = year + "-" + month + "-" + day;
        }
        return date;
	}
	
	/**
	 * Date형을 원하는 포맷으로 변환하여 스트링으로 전환한다.
	 * 
	 * @param date 날짜 Date형
	 * @return 날짜 String형. null일 경우 null return
	 */
	public static String dateToString(Date date, String fmt) {
		if (date != null && fmt != null) {
			SimpleDateFormat sfmt = new SimpleDateFormat(fmt, Locale.getDefault());
			return sfmt.format(date);
		} else
			return null;
	}

	/**
	 * 특정 Format의 String을 Date로 변환
	 * 
	 * @param date 날짜 String형
	 * @param fmt 날짜 String형의 Format
	 * @return 날짜 Date형. 날짜 String형의 오류가 있을 경우 null return
	 */
	public static Date stringToDate(String date, String fmt) {

		if (date != null && fmt != null) {
			SimpleDateFormat sfmt = new SimpleDateFormat(fmt, Locale.getDefault());
			try {
				return sfmt.parse(date);
			} catch (ParseException pe) {
				return null;
			}
		} else
			return null;
	}

	/**
	 * java.util.Date를 java.sql.Date로 변환
	 * 
	 * @param date java.util.Date
	 * @return java.sql.Date
	 */
	public static java.sql.Date dateToSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * "MMMM dd, yyyy HH:mm:ss z" 포맷의 Time stamp 반환한다.
	 * 
	 * @return "MMMM dd, yyyy HH:mm:ss z" 포맷의 Time stamp
	 */
	public static String getTimeStamp() {
		return getTimeStamp(1);
	}

	/**
	 * 설정된 mode에 따른 Time stamp를 반환한다.
	 * 
	 * @param iMode mode
	 * @return Time stamp
	 */
	public static String getTimeStamp(int iMode) {
		String sFormat;
		// if (iMode == 1) sFormat = "E MMM dd HH:mm:ss z yyyy"; // Wed Feb 03
		// 15:26:32 GMT+09:00 1999
		if (iMode == 1)
			sFormat = "MMMM dd, yyyy HH:mm:ss z"; // Jun 03, 2001 15:26:32
													// GMT+09:00
		else if (iMode == 2)
			sFormat = "MM/dd/yyyy";// 02/15/1999
		else if (iMode == 3)
			sFormat = "yyyyMMdd";// 19990215
		else if (iMode == 4)
			sFormat = "HHmmss";// 121241
		else if (iMode == 5)
			sFormat = "dd MMM yyyy";// 15 Jan 1999
		else if (iMode == 6)
			sFormat = "yyyyMMddHHmm"; // 200101011010
		else if (iMode == 7)
			sFormat = "yyyyMMddHHmmss"; // 20010101101052
		else if (iMode == 8)
			sFormat = "HHmmss";
		else if (iMode == 9)
			sFormat = "yyyy-MM-dd";
		else if (iMode == 10)
			sFormat = "yyyy";
		else if(iMode == 11)
			sFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
        else if(iMode == 12)
            sFormat = "yyyy-MM-dd HH:mm:ss";
		else
			sFormat = "E MMM dd HH:mm:ss z yyyy";// Wed Feb 03 15:26:32
													// GMT+09:00 1999

		Locale locale = new Locale("en", "EN");
		// SimpleTimeZone timeZone = new SimpleTimeZone(32400000, "KST");
		SimpleDateFormat formatter = new SimpleDateFormat(sFormat, locale);
		// formatter.setTimeZone(timeZone);
		// SimpleDateFormat formatter = new SimpleDateFormat(sFormat);

		return formatter.format(new Date());
	}

	/**
	 * "yyyyMMdd" 포맷의 Time stamp 반환한다.
	 * 
	 * @return "yyyyMMdd" 포맷의 Time stamp
	 */
	public static String getDate() {
		// return getDate(0);
		return getTimeStamp(3);
	}

	/**
	 * 오늘 날짜에서 지정한 날수를 계산한 날짜 반환
	 * 
	 * @param i 더하거나 뺄 날 수
	 * @return 계산된 날짜
	 */
	public static String getDate(int i) {
		return getDate(1, null, i);
	}

	/**
	 * 지정한 날짜에서 지정한 날수를 계산한 날짜 반환
	 * 
	 * @param sDate 지정한 날짜
	 * @param i 더하거나 뺄 날 수
	 * @return 계산된 날짜
	 */
	public static String getDate(String sDate, int i) {
		return getDate(1, sDate, i);
	}

	/**
	 * 지정한 날짜에서 지정한 날수를 계산한 날짜 반환
	 * 
	 * @param iType 앞/뒤로 계산할 단위 (1:일 단위, 2:월 단위, 3:년 단위)
	 * @param sDate 기준이 되는 날짜 - null일 경우, 오늘 날짜를 기준
	 * @param i 앞/뒤로 증가/감소 시킬 수
	 * @return 계산된 날짜
	 */
	public static String getDate(int iType, String sDateTo, int i) {
		String sDate = sDateTo;
		
		if (sDate == null) sDate = getTimeStamp(3);

		if (i == 0)
			return sDate;
		else {
			Calendar cal = Calendar.getInstance();
			cal.set(Integer.parseInt(sDate.substring(0, 4)), Integer
					.parseInt(sDate.substring(4, 6)) - 1, Integer
					.parseInt(sDate.substring(6, 8)));

			if (iType == 2)
				cal.add(Calendar.MONTH, i); // 월 단위
			else if (iType == 3)
				cal.add(Calendar.YEAR, i); // 년 단위
			else
				cal.add(Calendar.DATE, i); // 일 단위

			int iYear = cal.get(Calendar.YEAR);
			int iMonth = cal.get(Calendar.MONTH) + 1;
			int iDate = cal.get(Calendar.DATE);

			String sNewDate = "" + iYear;
			if (iMonth < 10)
				sNewDate += "0" + iMonth;
			else
				sNewDate += iMonth;
			if (iDate < 10)
				sNewDate += "0" + iDate;
			else
				sNewDate += iDate;

			return sNewDate;
		}
	}

	/**
	 * 오늘 날짜에서 지정한 날수를 계산한 날짜 반환
	 * 
	 * @param iType 앞/뒤로 계산할 단위 (1:일 단위, 2:월 단위, 3:년 단위)
	 * @param i 앞/뒤로 증가/감소 시킬 수
	 * @param dFormat 날짜 포맷
	 */
	public static String getDate(int iType, int i, String dFormat) {
		if (i == 0)
			return getToday(dFormat);
		else {
			Calendar cal = Calendar.getInstance();

			if (iType == 2)
				cal.add(Calendar.MONTH, i); // 월 단위
			else if (iType == 3)
				cal.add(Calendar.YEAR, i); // 년 단위
			else
				cal.add(Calendar.DATE, i); // 일 단위

			SimpleDateFormat sdf = new SimpleDateFormat(dFormat, Locale.getDefault());
			String sNewDate = sdf.format(cal.getTime());

			return sNewDate;
		}
	}

	/**
	 * 오늘 날짜보다 일주일 전 날짜를 반환한다.
	 * 
	 * @return 오늘 날짜보다 일주일 전 날짜
	 */
	public static String getPreviousWeek() {
		return getDate(1, null, -7);
	}

	/**
	 * 오늘 날짜보다 일주일 후 날짜를 반환한다.
	 * 
	 * @return 오늘 날짜보다 일주일 후 날짜
	 */
	public static String getNextWeek() {
		return getDate(1, null, 7);
	}

	/**
	 * 오늘 날짜보다 한달 전 날짜를 반환한다.
	 * 
	 * @return 오늘 날짜보다 한달 전 날짜
	 */
	public static String getPreviousMonth() {
		return getDate(2, null, -1);
	}

	/**
	 * 오늘 날짜보다 한달 후 날짜를 반환한다.
	 * 
	 * @return 오늘 날짜보다 한달 후 날짜
	 */
	public static String getNextMonth() {
		return getDate(2, null, 1);
	}
	
	/**
	 * 날짜를 비교하여 차이값을 정수값으로 리턴,
	 * 0이면 동일한 날짜,
	 * 양수이면 fromDateStr가 toDateStr보다 큼
	 * 음수이면 fromDateStr가 toDateStr보다 작음
	 *
	 * @param fromDateStr 시작일자
	 * @param toDateStr 종료일자
	 * @return 차이값
	 */
	public static long compareDate(String fromDateStr, String toDateStr) {
		long result = 0;
		
		Date fromDate = stringToDate(fromDateStr.replaceAll("-", ""), "yyyyMMdd");
		Date toDate = stringToDate(toDateStr.replaceAll("-", ""), "yyyyMMdd");
		
		result = (fromDate.getTime()-toDate.getTime())/(60*60*24*1000);
		
		return result;
	}
	
    /**
     * 날짜를 비교하여 개월수를 정수값으로 리턴,
     * 0이면 동일한 월,
     * 양수이면 fromDateStr가 toDateStr보다 큼
     * 음수이면 fromDateStr가 toDateStr보다 작음
     *
     * @param fromDateStr 시작일자
     * @param toDateStr 종료일자
     * @return 개월수
     */
    public static long compareMonth(String fromDateStr, String toDateStr) {
        if (fromDateStr == null || fromDateStr.trim().equals("")
                || toDateStr == null || toDateStr.trim().equals("")) {
            return 0;
        }
        long result = 0;
        
        Calendar fromCal = Calendar.getInstance();
        Calendar toCal = Calendar.getInstance();
        
        fromCal.setTime(stringToDate(fromDateStr.replaceAll("-", ""), "yyyyMMdd"));
        toCal.setTime(stringToDate(toDateStr.replaceAll("-", ""), "yyyyMMdd"));
        int year1 = fromCal.get(Calendar.YEAR);
        int month1 = fromCal.get(Calendar.MONTH);
        int date1 = fromCal.get(Calendar.DATE);
        int year2 = toCal.get(Calendar.YEAR);
        int month2 = toCal.get(Calendar.MONTH);
        int date2 = toCal.get(Calendar.DATE);
        result = ((year2 * 12) + month2) - ((year1 * 12) + month1);
        result = (date1 > date2) ? result - 1 : result;
        
        return result;
    }
    

    
    /**
     * 입력받은 두 수 사이의 순차값을 List 형태로 반환.
     * 
     * @param startYear 시작 값
     * @param endYear 종료 값
     * @return 순차값 배열, 종료 값이 작은경우  Null 반환
     */
    public static List<String> yearList(int startYear, int endYear){
    	if(startYear > endYear)	return null;
    	
    	List<String> result = new ArrayList<String>();
    	
    	for(int i = startYear; i <= endYear; ++i){
    		result.add(String.valueOf(i));
    	}
    	
    	return result;
    }
    

    
	/**
	 * 해당일의 요일정보 리턴
	 * 
	 * @param sY	년도
	 * @param sM 	월
	 * @param sD 	일
	 * @return 1(일),..,7(토)
	 */
	public static int getSelWeek(String sY, String sM, String sD){

		GregorianCalendar cal = 	new GregorianCalendar(Integer.parseInt(sY),Integer.parseInt(sM)-1,Integer.parseInt(sD));
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	public static String getSelWeekStr(int weekInt){
		if( weekInt == 1 ) return "일";
		else if( weekInt == 2 ) return "월";
		else if( weekInt == 3 ) return "화";
		else if( weekInt == 4 ) return "수";
		else if( weekInt == 5 ) return "목";
		else if( weekInt == 6 ) return "금";
		else if( weekInt == 7 ) return "토";
		else return "";
	}

    
	 /**
     * 달의 마지막날
     * @param Y 	년도
     * @param M 	월
     * @return 달의 마지막날
     */
	public static int getMonthLastDay(String Y, String M){
		int dom[] = {
				31,28,31,30,
				31,30,31,31,
				30,31,30,31
			};
		int yy = Integer.parseInt(Y);
		int mm = Integer.parseInt(M);
		mm--;

		GregorianCalendar cal = new GregorianCalendar(yy,mm,1);
		int daysInMonth = dom[mm];
		if(cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 1){
			daysInMonth++;
		}
		return daysInMonth;
	}
	
    public static String getCurDateStr(String formatString) {
        SimpleDateFormat formatter  = new SimpleDateFormat(formatString);
        Date currentTime= new Date();
        return formatter.format(currentTime);
    }
    
    
	/**
	 * 나이(만)를 구한다.
	 * 
	 * @param fromYear
	 * @param fromMonth
	 * @param fromDate
	 * @param toYear
	 * @param toMonth
	 * @param toDate
	 * @return
	 */
	public static int getAge(int fromYear,int fromMonth, int fromDate, int toYear, int toMonth, int toDate) {
	    int age = 0;
        if (toYear > fromYear) {
            age = toYear - fromYear;
            if (DateUtil.toDate(toYear, fromMonth, fromDate).getTime() > DateUtil.toDate(toYear, toMonth, toDate).getTime()) {
                age--;
            }
        }
        return age;
	}

	
	
	/**
	 * 나이(만)를 구한다.
	 * 
	 * @param birthYear
	 * @param birthMonth
	 * @param birthDate
	 * @return
	 */
	public static int getAge(int birthYear, int birthMonth, int birthDate) {
		Calendar cal = Calendar.getInstance();
        return getAge(birthYear, birthMonth, birthDate, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,cal.get(Calendar.DATE));
	}
	
	/**
	 * 문자열날짜를 Date객체로 반환
	 * @param str 문자열날짜
	 * @param pattern DateFormat
	 * @param defaultDate 디폴트 Date
	 * @return
	 */
	public static Date toDate(String str, String pattern, Date defaultDate) {
	    Date date = defaultDate;
	    try {
		    DateFormat dateFormat = new SimpleDateFormat(pattern); 
	        date = dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        } 
	    return date;
	}
	/**
	 * 문자열을 Date객체로 반환.
	 * 
	 * @param str yyyy-MM-dd HH:mm:ss 형식
	 * @return
	 */
	public static Date toDate(String str) {
	    return toDate(str, "yyyy-MM-dd HH:mm:ss", new Date());
	}
	
	/**
	 * 문자열을 Date객체로 반환
	 * @param str yyyy-MM-dd HH:mm:ss 형식
	 * @param defaultDate
	 * @return
	 */
	public static Date toDate(String str, Date defaultDate) {
	    return toDate(str, "yyyy-MM-dd HH:mm:ss", defaultDate);
	}
	
	
	/**
	 * Date객체로 반환
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
    public static Date toDate(String year, String month, String day) {
        Date date = null;
        try {
            date = toDate(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        } catch (NumberFormatException e) {
            date = new Date();
        }
        return date;
    }
	/**
	 * Date객체로 반환
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Date toDate(int year, int month, int day, int hour, int minute, int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, day, hour, minute, second);
		return calendar.getTime();
	}
	
	/**
	 * 입력받은 데이터를 java.util.Date형으로 변환한다.
	 * 
	 * @param year 년
	 * @param month 월
	 * @param day 일
	 * @param hour 시
	 * @param minute 분
	 * @return
	 */
	public static Date toDate(int year, int month, int day, int hour, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, day, hour, minute, 0);
		return calendar.getTime();
	}
	
	/**
	 * 입력받은 데이터를 java.util.Date형으로 변환한다.
	 * 
	 * @param year 년
	 * @param month 월
	 * @param day 일
	 * @return
	 */
	public static Date toDate(int year, int month, int day) {
		return toDate(year, month, day, 0, 0);
	}	
	
    
    /*
	public static void main(String[] args) {
		long gap = DateUtil.compareDate("2011-05-19", "2011-05-20");
		
		System.out.println(gap);
	}
	*/
}