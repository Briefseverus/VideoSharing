package com.videosharing.VNPay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.videosharing.services.UserService;

import io.jsonwebtoken.io.IOException;

@Service
public class VNPayServiceImpl implements VNPayService {

	@Autowired
	UserService userService;
	
	private static final String VNPAY_VERSION = "2.1.0";
	private static final String VNPAY_COMMAND = "pay";
	private static final String VNPAY_TmnCode = Config.vnp_TmnCode;

	private static final String VNPAY_ReturnUrl = Config.vnp_ReturnUrl;

	@Override
	public String getPayUrl(long price, int userId) throws UnsupportedEncodingException {
		Map<String, String> vnp_Params = new HashMap<>();
		vnp_Params.put("vnp_Version", VNPAY_VERSION);
		vnp_Params.put("vnp_Command", VNPAY_COMMAND);
		vnp_Params.put("vnp_TmnCode", VNPAY_TmnCode);
		vnp_Params.put("vnp_Amount", String.valueOf(price));
		vnp_Params.put("vnp_CurrCode", "VND");
		vnp_Params.put("vnp_BankCode", "NCB");
		vnp_Params.put("vnp_TxnRef", userId + "-" + System.currentTimeMillis());
		vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + userId);
		vnp_Params.put("vnp_OrderType", "other");
		vnp_Params.put("vnp_Locale", "vn");
		vnp_Params.put("vnp_ReturnUrl", VNPAY_ReturnUrl + "?userId=" + userId);
		vnp_Params.put("vnp_IpAddr", "127.0.0.1");

		Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String vnp_CreateDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

		cld.add(Calendar.MINUTE, 15);
		String vnp_ExpireDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

		List fieldNames = new ArrayList(vnp_Params.keySet());
		Collections.sort(fieldNames);
		StringBuilder hashData = new StringBuilder();
		StringBuilder query = new StringBuilder();
		Iterator itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) vnp_Params.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				// Build hash data
				hashData.append(fieldName);
				hashData.append('=');
				hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				// Build query
				query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
				query.append('=');
				query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				if (itr.hasNext()) {
					query.append('&');
					hashData.append('&');
				}
			}
		}
		String queryUrl = query.toString();
		String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
		queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
		String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;

		return paymentUrl;
	}
	

}