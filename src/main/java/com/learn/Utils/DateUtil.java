package com.learn.Utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DateUtil {
	public static final String DATE_FORMAT = "dd-MM-yyyy";

	public static final String DATE_FORMAT_SQL = "yyyy-MM-dd";

	public static Timestamp getCurrentTimestamp() {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		return timestamp;

	}

	public static SimpleDateFormat standardDateFormat = new SimpleDateFormat(DATE_FORMAT);

	public static SimpleDateFormat standardDateFormatSql = new SimpleDateFormat(DATE_FORMAT_SQL);

	public static Date parseDate(String dateText) throws ParseException {

		return standardDateFormat.parse(dateText);

	}

	public static Date getDateFromTimeStamp(Timestamp timestamp) {

		Date date = null;

		String ds = standardDateFormat.format(timestamp);

		try {

			date = standardDateFormat.parse(ds);

		} catch (ParseException e) {

			e.printStackTrace();

		}

		return date;

	}

	// added to convert Date to String

	public static String getStringDateFromTimeStamp(Date timestamp) {

		String ds = standardDateFormat.format(timestamp);

		return ds;

	}

	public static Date getDateWithoutTime(Date date) {

		Calendar cal = Calendar.getInstance();

		cal.setTime(date);

		cal.set(Calendar.HOUR_OF_DAY, 0);

		cal.set(Calendar.MINUTE, 0);

		cal.set(Calendar.SECOND, 0);

		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();

	}

	public static String getDateFormatted(Date date) {

		try {

			Calendar cal = Calendar.getInstance();

			cal.setTime(date);

			cal.set(Calendar.HOUR_OF_DAY, 0);

			cal.set(Calendar.MINUTE, 0);

			cal.set(Calendar.SECOND, 0);

			cal.set(Calendar.MILLISECOND, 0);

			String dateLong = DateFormat.getDateInstance(DateFormat.LONG).format(cal.getTime());

			return dateLong;

		} catch (Exception e) {

			return null;

		}

	}

	public static boolean isNotAWeekend(Date date) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		if (calendar.get(Calendar.DAY_OF_WEEK) == 1 || calendar.get(Calendar.DAY_OF_WEEK) == 7) {

			return false;

		} else

			return true;

	}

	public static java.sql.Date getSqlDateFromString(String strdate) {

		SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyy");

		sdf1.setLenient(false);

		java.util.Date date;

		java.sql.Date sqlStartDate = null;

		try {

			if (null != strdate) {

				date = sdf1.parse(strdate);

				sqlStartDate = new java.sql.Date(date.getTime());

			}

		} catch (ParseException e) {

		}

		return sqlStartDate;

	}

	public static int getCurrentFinancialYear() {

		int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);

		SimpleDateFormat simpleformat = new SimpleDateFormat("yy");

		String strYear = String.valueOf(CurrentYear)

				+ String.valueOf(Integer.parseInt(simpleformat.format(new Date())) + 1);

		return Integer.valueOf(strYear);

	}

	public static int getFinancialYearByBillDate(String billdate) {

		String strYear = "";

		LocalDate currentDate = LocalDate.parse(billdate);

		SimpleDateFormat simpleformat = new SimpleDateFormat("yy");

		int year = currentDate.getYear();

		int mon = currentDate.getMonthValue();

		if (mon < 4) {

			strYear = String.valueOf(year - 1) + (year % 100);

		} else {

			strYear = String.valueOf(year) + String.valueOf(Integer.parseInt(simpleformat.format(new Date())) + 1);

		}

		return Integer.parseInt(strYear);

	}

	public static List<Integer> getFinancialYearList(Integer startYear) {

		List<Integer> listOfFy = new LinkedList<Integer>();

		Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);

		SimpleDateFormat simpleformat = new SimpleDateFormat("yy");

		Date date = new Date();

		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		int mon = localDate.getMonthValue();

		for (int i = startYear; i <= currentYear; i++) {

			if (i != currentYear) {

				String y = String.valueOf(i) + String.valueOf((i + 1) % 2000);

				listOfFy.add(Integer.parseInt(y)); // add to the list

			} else {

				String strYear = String.valueOf(currentYear);

				/*
				 * if (i == currentYear && mon < 4) {
				 * 
				 * strYear = String.valueOf(i - 1);
				 * 
				 * listOfFy.add(Integer.parseInt(strYear));
				 * 
				 * } else
				 */

				if ((mon >= 4)) {

					strYear = String.valueOf(i) + String.valueOf(Integer.parseInt(simpleformat.format(new Date())) + 1);

					listOfFy.add(Integer.parseInt(strYear));

				}

			}

		}

		return listOfFy;

	}

	// Format a date to the specified format
	public static String formatDate(Date date, String format) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	// Get the current date in the specified format
	public static String formatCurrentDate(String format) {
		return formatDate(new Date(), format);
	}

	// Get the current date in "yyyy-MM-dd" format
	public static String getCurrentDate() {
		return formatCurrentDate("yyyy-MM-dd");
	}

	// Add days to the current date
	public static Date addDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}

	// Get the current date and time as a string
	public static String getCurrentDateTime() {
		return formatCurrentDate("yyyy-MM-dd HH:mm:ss");
	}

	// Convert a string to Date based on a given format
	public static Date parseDate(String dateStr, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(dateStr);
		} catch (Exception e) {
			return null; // Return null if parsing fails
		}
	}

	// Get the difference between two dates in days
	public static long daysBetween(Date start, Date end) {
		long diffInMillis = end.getTime() - start.getTime();
		return diffInMillis / (1000 * 60 * 60 * 24);
	}
}
