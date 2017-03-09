package com.viettel.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.res.Resources.NotFoundException;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

import com.viettel.common.SpannableObject;
import com.viettel.constants.Constants;
import com.viettel.dto.UserDTO;

public class StringUtil {
	// public static final String TAG_PATERN =
	// "@\\[(\\d*):([ a-zA-Z \\d\\sÀÁÂÃÈÉÊẾÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềếểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳýỵỷỹ]*)]";
	public static String EMPTY_STRING = "";
	public static String SPACE_STRING = " ";
	public static char CHAR_SPLIT = '�';
	public static String[] arrSpecialChar = { "‘", "’", "“", "”", "„", "†",
			"‡", "‰", "‹", "›", "♠", "♣", "♥", "♦", "‾", "←", "↑", "→", "↓",
			"™", "!", "“", "#", "$", "%", "&", "‘", "(", ")", "*", "+", ",",
			"-", ".", "/", ":", ";", "<", "=", ">", "?", "@", "[", "\\", "]",
			"_", "`", "{", "|", "}", "~", "–", "—", "¡", "¢", "£", "¤", "¥",
			"¦", "§", "¨", "©", "ª", "«", "¬", "¬", "®", "¯", "°", "±", "²",
			"³", "´", "µ", "¶", "•", "¸", "¹", "º", "¿", "Ä", "Å", "Æ", "Ç",
			"Ë", "Î", "Ï", "Ñ", "Ö", "×", "Ø", "Û", "Ü", "Þ", "ß", "ã", "ä",
			"å", "æ", "ç", "ë", "î", "ï", "ð", "ñ", "õ", "ö", "÷", "ø", "û",
			"ü", "þ", "ÿ" };
	/** The codau. */
	static char codau[] = { 'à', 'á', 'ả', 'ã', 'ạ', 'ă', 'ằ', 'ắ', 'ẳ', 'ẵ',
			'ặ', 'â', 'ầ', 'ấ', 'ẩ', 'ẫ', 'ậ', 'À', 'Á', 'Ả', 'Ã', 'Ạ', 'Ă',
			'Ằ', 'Ắ', 'Ẳ', 'Ẵ', 'Ặ', 'Â', 'Ầ', 'Ấ', 'Ẩ', 'Ẫ', 'Ậ', 'è', 'é',
			'ẻ', 'ẽ', 'ẹ', 'ê', 'ề', 'ế', 'ể', 'ễ', 'ệ', 'È', 'É', 'Ẻ', 'Ẽ',
			'Ẹ', 'Ê', 'Ề', 'Ế', 'Ể', 'Ễ', 'Ệ', 'ì', 'í', 'ỉ', 'ĩ', 'ị', 'Ì',
			'Í', 'Ỉ', 'Ĩ', 'Ị', 'ò', 'ó', 'ỏ', 'õ', 'ọ', 'ô', 'ồ', 'ố', 'ổ',
			'ỗ', 'ộ', 'ơ', 'ờ', 'ớ', 'ở', 'ỡ', 'ợ', 'Ò', 'Ó', 'Ỏ', 'Õ', 'Ọ',
			'Ô', 'Ồ', 'Ố', 'Ổ', 'Ỗ', 'Ộ', 'Ơ', 'Ờ', 'Ớ', 'Ở', 'Ỡ', 'Ợ', 'ù',
			'ú', 'ủ', 'ũ', 'ụ', 'ư', 'ừ', 'ứ', 'ử', 'ữ', 'ự', 'Ù', 'Ú', 'Ủ',
			'Ũ', 'Ụ', 'ỳ', 'ý', 'ỷ', 'ỹ', 'ỵ', 'Ỳ', 'Ý', 'Ỷ', 'Ỹ', 'Ỵ', 'đ',
			'Đ' };
	/** The khongdau. */
	static char khongdau[] = { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a',
			'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'A', 'A', 'A', 'A', 'A',
			'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'e',
			'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'E', 'E', 'E',
			'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'i', 'i', 'i', 'i', 'i',
			'I', 'I', 'I', 'I', 'I', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o',
			'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'O', 'O', 'O', 'O',
			'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O',
			'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'U', 'U',
			'U', 'U', 'U', 'y', 'y', 'y', 'y', 'y', 'Y', 'Y', 'Y', 'Y', 'Y',
			'd', 'D' };

	static char charsInName[] = { 'à', 'á', 'ả', 'ã', 'ạ', 'ă', 'ằ', 'ắ', 'ẳ',
			'ẵ', 'ặ', 'â', 'ầ', 'ấ', 'ẩ', 'ẫ', 'ậ', 'À', 'Á', 'Ả', 'Ã', 'Ạ',
			'Ă', 'Ằ', 'Ắ', 'Ẳ', 'Ẵ', 'Ặ', 'Â', 'Ầ', 'Ấ', 'Ẩ', 'Ẫ', 'Ậ', 'è',
			'é', 'ẻ', 'ẽ', 'ẹ', 'ê', 'ề', 'ế', 'ể', 'ễ', 'ệ', 'È', 'É', 'Ẻ',
			'Ẽ', 'Ẹ', 'Ê', 'Ề', 'Ế', 'Ể', 'Ễ', 'Ệ', 'ì', 'í', 'ỉ', 'ĩ', 'ị',
			'Ì', 'Í', 'Ỉ', 'Ĩ', 'Ị', 'ò', 'ó', 'ỏ', 'õ', 'ọ', 'ô', 'ồ', 'ố',
			'ổ', 'ỗ', 'ộ', 'ơ', 'ờ', 'ớ', 'ở', 'ỡ', 'ợ', 'Ò', 'Ó', 'Ỏ', 'Õ',
			'Ọ', 'Ô', 'Ồ', 'Ố', 'Ổ', 'Ỗ', 'Ộ', 'Ơ', 'Ờ', 'Ớ', 'Ở', 'Ỡ', 'Ợ',
			'ù', 'ú', 'ủ', 'ũ', 'ụ', 'ư', 'ừ', 'ứ', 'ử', 'ữ', 'ự', 'Ù', 'Ú',
			'Ủ', 'Ũ', 'Ụ', 'Ư', 'Ừ', 'Ứ', 'Ử', 'Ữ', 'Ự', 'ỳ', 'ý', 'ỷ', 'ỹ',
			'ỵ', 'Ỳ', 'Ý', 'Ỷ', 'Ỹ', 'Ỵ', 'đ', 'Đ', 'a', 'A', 'b', 'B', 'c',
			'C', 'd', 'D', 'e', 'E', 'f', 'F', 'g', 'G', 'h', 'H', 'i', 'I',
			'j', 'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p',
			'P', 'q', 'Q', 'r', 'R', 's', 'S', 't', 'T', 'u', 'U', 'v', 'V',
			'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z', ' ' };

	/**
	 * 
	 * kiem tra ten co chua cac ki tu hop le
	 * 
	 * @author: AnhND
	 * @param input
	 * @return
	 * @return: String
	 * @throws:
	 */
	public static boolean isNameContainValidChars(String name) {
		boolean isContain = false;
		for (int j = 0, m = name.length(); j < m; j++) {
			isContain = false;
			for (int i = 0, n = charsInName.length; i < n; i++) {
				if (name.charAt(j) == charsInName[i]) {
					isContain = true;
					break;
				}
			}
		}
		return isContain;
	}

	

	
	/**
	 * 
	 * bo dau tieng viet
	 * 
	 * @author: AnhND
	 * @param input
	 * @return: String
	 * @throws:
	 */
	public static String getEngStringFromUnicodeString(String input) {
		if (isNullOrEmpty(input)) {
			return "";
		}
		input = input.trim();

		for (int i = 0; i < codau.length; i++) {
			input = input.replace(codau[i], khongdau[i]);
		}
		return input;
	}

	public static String replace(String _text, String _searchStr,
			String _replacementStr) {
		// String buffer to store str
		StringBuffer sb = new StringBuffer();

		// Search for search
		int searchStringPos = _text.indexOf(_searchStr);
		int startPos = 0;
		int searchStringLength = _searchStr.length();

		// Iterate to add string
		while (searchStringPos != -1) {
			sb.append(_text.substring(startPos, searchStringPos)).append(
					_replacementStr);
			startPos = searchStringPos + searchStringLength;
			searchStringPos = _text.indexOf(_searchStr, startPos);
		}

		// Create string
		sb.append(_text.substring(startPos, _text.length()));

		return sb.toString();
	}

	/**
	 * Kiem tra nick name hop le
	 * 
	 * @author: TruongHN3
	 * @param userName
	 * @return: boolean
	 * @throws:
	 */
	public static boolean isValidateUserName(String userName) {
		// UserName khong the toan so
		// UserName chi chua cac ky tu a-z, A-Z, 0-9
		Boolean isValid = false;
		int length = userName.length();
		for (int i = 0; i < length; i++) {
			char ch = userName.charAt(i);
			if (((ch >= 0x30 && ch <= 0x39) || (ch >= 0x41 && ch <= 0x5a) || (ch >= 0x61 && ch <= 0x7a))) {
				isValid = true;
			} else {
				isValid = false;
				break;
			}
		}
		return isValid;
	}

	/**
	 * Kiem tra noi dung hop le
	 * 
	 * @author: TruongHN3
	 * @param name
	 * @return: boolean
	 * @throws:
	 */
	public static boolean isValidatePaymentContent(String name) {
		// Noi dung chi chua cac ky tu a-z, A-Z, 0-9, khoang trang
		Boolean isValid = false;
		int length = name.length();
		for (int i = 0; i < length; i++) {
			char ch = name.charAt(i);
			if (((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')
					|| (ch >= '0' && ch <= '9') || (ch == ' '))) {
				isValid = true;
			} else {
				isValid = false;
				break;
			}
		}
		return isValid;
	}

	/**
	 * Kiem tra noi dung hop le
	 * 
	 * @author: TruongHN3
	 * @param name
	 * @return: boolean
	 * @throws:
	 */
	public static boolean isValidatePaymentContentWithoutSpace(String name) {
		// Noi dung chi chua cac ky tu a-z, A-Z, 0 - 9
		Boolean isValid = false;
		int length = name.length();
		for (int i = 0; i < length; i++) {
			char ch = name.charAt(i);
			if (((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9'))) {
				isValid = true;
			} else {
				isValid = false;
				break;
			}
		}
		return isValid;
	}

	/**
	 * validate phone
	 * 
	 * @param phone
	 * @return
	 * @Author : DoanDM Since Dec 9, 2010
	 */
	public static boolean isValidPhone(String phone) {
		int length = phone.length();
		for (int i = 0; i < length; i++) {
			if (phone.charAt(i) < '0' || phone.charAt(i) > '9') {
				return false;
			}
		}
		return true;
	}

	/**
	 * validate email
	 * 
	 * @param strEmail
	 * @return
	 * @Author : DoanDM Since Dec 9, 2010
	 */
	public static boolean isValidateEmail(String email) {
		if (email == null || email.length() == 0 || email.indexOf("@") == -1
				|| email.indexOf(" ") != -1) {
			return false;
		}
		int emailLenght = email.length();
		int atPosition = email.indexOf("@");

		String beforeAt = email.substring(0, atPosition);
		String afterAt = email.substring(atPosition + 1, emailLenght);

		if (beforeAt.length() == 0 || afterAt.length() == 0) {
			// #ifdef DEBUG
			// # //System.out.println("only @ is");
			// #endif
			return false;
		}

		// CHECK for .(dot) before @(at) = aaa.@domain.com
		if (email.charAt(atPosition - 1) == '.') {
			// #ifdef DEBUG
			// # //System.out.println(".(dot) before @(at)");
			// #endif
			return false;
		}

		// CHECK for .(dot) before @(at) = aaa@.domain.com
		if (email.charAt(atPosition + 1) == '.') {
			// #ifdef DEBUG
			// # //System.out.println("@.");
			// #endif
			return false;
		}

		// CHECK for .(dot) = email@domaincom
		if (afterAt.indexOf(".") == -1) {
			// #ifdef DEBUG
			// # //System.out.println("no dot(.)");
			// #endif
			return false;
		}

		// CHECK for ..(2 dots) = email@domain..com
		char dotCh = 0;
		for (int i = 0; i < afterAt.length(); i++) {
			char ch = afterAt.charAt(i);
			// soan validate
			if (!((ch >= 0x30 && ch <= 0x39) || (ch >= 0x41 && ch <= 0x5a)
					|| (ch >= 0x61 && ch <= 0x7a) || (ch == 0x2e)
					|| (ch == 0x2d) || (ch == 0x5f))) {
				// #ifdef DEBUG
				// # //System.out.println("not valid chars");
				// #endif
				return false;
			}
			// end soan
			if ((ch == 0x2e) && (ch == dotCh)) {
				// #ifdef DEBUG
				// # // System.out.println("find .. (2 dots) in @>");
				// #endif
				return false;
			}
			dotCh = ch;
		}

		// CHECK for double '@' = example@@domain.com
		if (afterAt.indexOf("@") != -1) {
			// #ifdef DEBUG
			// # //System.out.println("find 2 @");
			// #endif
			return false;
		}
		// check domain name 2-5 chars
		int ind = 0;
		do {
			int newInd = afterAt.indexOf(".", ind + 1);

			if (newInd == ind || newInd == -1) {
				String prefix = afterAt.substring(ind + 1);
				if (prefix.length() > 1 && prefix.length() < 6) {
					break;
				} else {
					// #ifdef DEBUG
					// # //System.out.println("prefix not 2-5 chars");
					// #endif
					return false;
				}
			} else {
				ind = newInd;
			}
		} while (true);

		// CHECK for valid chars[a-z][A-Z][0-9][. - _]
		// CHECK for ..(2 dots)
		dotCh = 0;
		for (int i = 0; i < beforeAt.length(); i++) {
			char ch = beforeAt.charAt(i);
			if (!((ch >= 0x30 && ch <= 0x39) || (ch >= 0x41 && ch <= 0x5a)
					|| (ch >= 0x61 && ch <= 0x7a) || (ch == 0x2e)
					|| (ch == 0x2d) || (ch == 0x5f))) {
				// #ifdef DEBUG
				// # //System.out.println("not valid chars");
				// #endif
				return false;
			}
			if ((ch == 0x2e) && (ch == dotCh)) {
				// #ifdef DEBUG
				// # //System.out.println("find .. (2 dots)");
				// #endif
				return false;
			}
			dotCh = ch;
		}
		return true;
	}

	public static Vector split(String s, String str) {
		Vector v = new Vector();
		int pos = s.indexOf(str);
		int space = str.length();
		while (pos != -1 && !"".equals(s)) {
			v.addElement(s.substring(0, pos));
			s = s.substring(pos + space);
			pos = s.indexOf(str);
		}
		if (!"".equals(s)) {
			v.addElement(s);

		}
		return v;
	}

	public static Vector split(String s, char ch) {

		Vector v = new Vector();
		String str = String.valueOf(ch);
		int pos = s.indexOf(str);
		while (pos != -1 && !"".equals(s)) {
			v.addElement(s.substring(0, pos));
			// try {

			s = s.substring(pos + 1);
			// } catch (Exception e) {
			// e.printStackTrace();
			// }

			pos = s.indexOf(str);
		}
		if (!"".equals(s)) {
			v.addElement(s);

		}
		return v;
	}

	public final static void supplementCodePointToSurrogatePair(int codePoint,
			int[] surrogatePair) {
		int high4 = ((codePoint >> 16) & 0x1F) - 1;
		int mid6 = ((codePoint >> 10) & 0x3F);
		int low10 = codePoint & 0x3FF;

		surrogatePair[0] = (0xD800 | (high4 << 6) | (mid6));
		surrogatePair[1] = (0xDC00 | (low10));
	}

	private static int GetDelim(String s, int Offset) {
		while (Offset < s.length()) {
			char c = s.charAt(Offset);
			if ((c == ' ') || (c == '~')) {
				return Offset;
			}
			Offset++;
		}
		return -1;
	}

	public static boolean isNullOrEmpty(String aString) {
		return (aString == null) || ("".equals(aString.trim()));
	}

	public static String getStringDateNoSecond(String strDate) {
		// yyyy/MM/dd HH:mm:ss
		String result = "";
		if (!StringUtil.isNullOrEmpty(strDate)) {
			Vector arrStringDate = split(strDate, ' ');
			if (arrStringDate != null && arrStringDate.size() >= 2) {
				// yyyy/MM/dd
				Vector arrDate = split(arrStringDate.elementAt(0).toString(),
						'/');
				// HH:mm:ss
				Vector arrHour = split(arrStringDate.elementAt(1).toString(),
						':');

				// change to HH:mm yyyy/MM/dd
				StringBuffer str = new StringBuffer();
				str.append(arrHour.elementAt(0));
				str.append(":");
				str.append(arrHour.elementAt(1));
				str.append(' ');
				str.append(arrDate.elementAt(0));
				str.append("/");
				str.append(arrDate.elementAt(1));
				str.append("/");
				str.append(arrDate.elementAt(2));

				result = str.toString();
			}
		}
		return result;
	}






	/**
	 * chuyen phone ve dang 01, 09
	 * 
	 * @author: BangHN
	 * @param phoneString
	 * @return
	 * @return: String
	 * @throws:
	 */
	public static String converPhoneStringToHeader0109(String phoneString) {
		if (!isNullOrEmpty(phoneString)) {
			if (phoneString.startsWith("84")) {
				phoneString = phoneString.substring(2);
				phoneString = "0" + phoneString;
			} else if (phoneString.startsWith("+84")) {
				phoneString = phoneString.substring(3);
				phoneString = "0" + phoneString;
			}
		} else {
			phoneString = "";
		}
		return phoneString;
	}
	/**
	 * 
	*  1 chuoi so thanh dang : 5-->5,5.0-->5, 5.5 --> 5.5
	*  @author: DoanDM
	*  @param value
	*  @return
	*  @return: String
	*  @throws:
	 */
	public static String checkDoubleString(String str,String addition){
		String result="";
		Vector tmp = split(String.valueOf(str), '.');
		int tmpNumber = Integer.parseInt(tmp.get(0).toString());
		double n = Double.parseDouble(str);
		if(tmpNumber==n){
			result+=String.valueOf(tmp.get(0).toString())+addition;
		}else{
			result+=String.valueOf(tmp.get(0))+","+tmp.get(1).toString()+addition;
		}
		return result;
	}
	/**
	 * 
	*  Thay the khoang trang thanh %20
	*  @author: DoanDM
	*  @return: void
	*  @throws:
	 */
	public static String replaceSpaceByHtmlCode(String ori){
		if(isNullOrEmpty(ori)) return Constants.STR_BLANK;
		return ori.replace(" ", "%20");
	}
	/**
	 * validate va chuyen phone ve dang 01, 09
	 * 
	 * @author: BangHN
	 * @param phoneString
	 * @return
	 * @return: String
	 * @throws:
	 */
	public static String validateAndConverPhoneStringToHeader0109(
			String phoneString) {
		String res = phoneString;
		if (validateMobileNumber(phoneString)) {
			res = converPhoneStringToHeader0109(phoneString);
		}
		return res;
	}

	/**
	 * Co the la so dien thoai hay khong
	 * 
	 * @author: BangHN
	 * @param phonenumber
	 * @return: boolean
	 */
	public static boolean canBePhoneNumber(String phonenumber) {
		if (StringUtil.isNullOrEmpty(phonenumber)) {
			return false;
		}
		Pattern p = Pattern.compile("^[09|01|849|841|+849|+841][0-9]+$");
		Matcher m = p.matcher(phonenumber);

		boolean matchFound = m.matches();
		return matchFound;
	}

	/**
	 * Kiem tra mot chuoi co phai la so dien thoai hop le hay khong
	 * 
	 * @author: BangHN
	 * @param mobileNumber
	 * @return: boolean
	 */
	public static boolean validateMobileNumber(String mobileNumber) {
		mobileNumber = mobileNumber.trim();
		final String prefix849 = "849";
		final String prefix849plus = "+849";
		final String prefix841 = "841";
		final String prefix841plus = "+841";
		final String prefix09 = "09";
		final String prefix01 = "01";
		boolean result = false;

		if (!StringUtil.isNullOrEmpty(mobileNumber)
				&& canBePhoneNumber(mobileNumber)) {
			int length = mobileNumber.length();
			switch (length) {
			case 10:
				if (mobileNumber.startsWith(prefix09)) {
					result = true;
				}
				break;
			case 11:
				if (mobileNumber.startsWith(prefix01)
						|| mobileNumber.startsWith(prefix849)) {
					result = true;
				}
				break;
			case 12:
				if (mobileNumber.startsWith(prefix841)
						|| mobileNumber.startsWith(prefix849plus)) {
					result = true;
				}
				break;
			case 13:
				if (mobileNumber.startsWith(prefix841plus)) {
					result = true;
				}
				break;
			}
		}

		return result;
	}

	/**
	 * Them ma code quoc gia vietnam vao so dien thoai
	 * 
	 * @author: BangHN
	 * @param mobileNumber
	 * @return: String
	 */
	public static String parseMobileNumber(String mobileNumber) {
		final String countryCode = "84";
		final String countryCodePlus = "+84";
		final String _9x = "9";
		final String _09x = "09";
		final String _1x = "1";
		final String _01x = "01";

		if (mobileNumber == null) {
			return null;
		}
		mobileNumber = mobileNumber.trim();

		if (mobileNumber.startsWith(countryCodePlus)) {
			mobileNumber = mobileNumber.substring(1);
		}

		String validatePhone = null;

		// phone start with "84" or "+84"
		if (mobileNumber.startsWith(countryCode)) {
			boolean isMobileNumber = (mobileNumber.length() == 11 && mobileNumber
					.substring(2).startsWith(_9x))
					|| (mobileNumber.length() == 12 && mobileNumber
							.substring(2).startsWith(_1x));
			if (isMobileNumber) {
				validatePhone = mobileNumber;
			}
		}
		// phone = "09xxxxxxxx" (like: 0987868686)
		else if (mobileNumber.length() == 10 && mobileNumber.startsWith(_09x)) {
			validatePhone = countryCode + mobileNumber.substring(1);
		}
		// phone = "1xxxxxxxx" (like: 01696999999)
		else if (mobileNumber.length() == 11 && mobileNumber.startsWith(_01x)) {
			validatePhone = countryCode + mobileNumber.substring(1);
		}

		return validatePhone;
	}

	public static String md5(String s) {
		byte[] defaultBytes = s.getBytes();
		try {
			MessageDigest algorithm;
			algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte messageDigest[] = algorithm.digest();

			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				String hex = Integer.toHexString(0xFF & messageDigest[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Tao mot string tien duoc ngan cach boi dau cham vi du :1.600.000
	 * 
	 * @author: PhucNT6
	 * @param source
	 * @return
	 * @return: String
	 * @throws:
	 */
	public static String parseAmountMoney(String money) {
		String result = "";
		String head = ""; //truoc dau ","
		String last = ""; //sau dau ","
		//TamPQ: doi dau phay thap phan tu` "." thanh ","
		if (!isNullOrEmpty(money)) {
			money = money.replace(".", ",");
			int index = money.indexOf(",");
			if(index > 0){
				head = money.substring(0, index);
				last = money.substring(index);
			}else{
				head = money;
			}
			
			for (int i = head.length() - 1; i >= 0; i--) {
				int offsetLast = head.length() - 1 - i;
				if ((offsetLast > 0) && (offsetLast % 3) == 0
						&& (head.charAt(i) != '+') && (head.charAt(i) != '-'))
					result = "." + result;
				result = head.charAt(i) + result;
			}
		}
		//TamPQ: neu dinh danh la "12345." thi chuyen thanh "12345"
		if(last.length() <= 1){
			return result;
		}else{
			return result + last;
		}
	}


	/**
	 * Cat khoang trang o giua String, giua 2 ky tu chi co duy nhat 1 khoang
	 * trang
	 * 
	 * @author: TruongHN
	 * @param source
	 * @return
	 * @return: String
	 * @throws:
	 */
	public static String itrim(String source) {
		if (!isNullOrEmpty(source)) {
			return source.replaceAll("\\b\\s{2,}\\b", " ");
		}
		return source;
	}


	/**
	 * Checks if is viettel phone number.
	 * 
	 * @author: PhucNT
	 * @param phoneNumber
	 *            the phone number
	 * @return true, if is viettel phone number
	 */
	public static boolean isViettelPhoneNumber(String phoneNumber) {
		boolean match = false;
		if (canBePhoneNumber(phoneNumber)) {
			String formatedPhone = parseMobileNumber(phoneNumber);
			if (formatedPhone != null) {
				Pattern p = Pattern
						.compile("^84(98|97|163|164|165|166|167|168|169)[0-9]*$");
				Matcher m = p.matcher(formatedPhone);
				match = m.matches();
			}
		}
		return match;
	}


}
