package utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static final String[] FORMATS={
		"yyyy-MM-dd HH:mm:ss",
		"YYYY-MM-dd HH-mm-ss",
		"yyyy/MM/dd HH:mm:ss",
		"yyyyMMddHHmmss",
		"yyyy-MM-dd",
		"yyyy.MM.dd",
		"yyyy/MM/dd",
		"yyyyMMdd",
		"yyyyMM",
		"yyyy"
	};
	
	public static Date parseDate(String dateString) throws ParseException{
		Date date = null;
		SimpleDateFormat simpleDateFormat;
		if (dateString==null) {
			return null;
		}
		for (int i=0;i<FORMATS.length;i++) {
			simpleDateFormat=new SimpleDateFormat(FORMATS[i]);
			simpleDateFormat.setLenient(false);
			try {
				date=simpleDateFormat.parse(dateString);
				if ((i!=9)&&(i!=8)&&(i!=6)&&(i!=7)) {
					System.out.println(dateString+"-----"+date);
				}
				break;
			} catch (ParseException e) {
				if ((i+1)==FORMATS.length) {
					return null;
				}
			}
		}
		return date;
	}
}
