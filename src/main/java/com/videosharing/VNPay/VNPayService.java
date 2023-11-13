package com.videosharing.VNPay;

import java.io.UnsupportedEncodingException;

public interface VNPayService {
    String getPayUrl(long price, int userId) throws UnsupportedEncodingException;
 
}
