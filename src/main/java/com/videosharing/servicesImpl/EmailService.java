package com.videosharing.servicesImpl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.videosharing.models.User;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class EmailService {

	  @Autowired
	  private JavaMailSender mailSender;

	  public void sendEmailForAccountCreated(User user) {
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    
	    MimeMessage message = mailSender.createMimeMessage();


	    LocalDateTime joinDate = LocalDateTime.ofInstant(user.getJoinDate().toInstant(), ZoneId.systemDefault());

	    String formattedJoinDate = formatter.format(joinDate);
	    try {

	      MimeMessageHelper helper = new MimeMessageHelper(message, true);
	      
	      helper.setFrom("videosharing@example.com");
	      
	      helper.setTo(user.getEmail());
	      
	      helper.setSubject("Chúc mừng bạn đã tạo tài khoản thành công trên Video Sharing!");

	   
	      String htmlContent = "<!DOCTYPE html>" +
	                          "<html>" +
	                          "  <head>" +
	                          "    <style>" +
	                          "      /* CSS cho phần nội dung email */" +
	                          "      body {" +
	                          "        font-family: Arial, sans-serif;" + 
	                          "      }" +
	                          "      .email-container {" +
	                          "        max-width: 800px;" +
	                          "        margin: 0 auto;" + 
	                          "        padding: 20px;" +
	                          "        background-color: #f0f0f0;" +
	                          "      }" +
	                          "      .header {" +
	                          "        background-color: #007bff;" +
	                          "        color: #ffffff;" +
	                          "        text-align: center;" +
	                          "        padding: 10px;" +
	                          "      }" +
	                          "      table {" +
	                          "        width: 100%;" +
	                          "        margin-bottom: 20px;" +
	                          "        border: 1px solid #ddd;" +
	                          "        border-collapse: collapse;" +
	                          "      }" +
	                          "      th," +
	                          "      td {" +
	                          "        padding: 10px;" +
	                          "      }" +  
	                          "      .footer {" +
	                          "        background-color: #007bff;" +
	                          "        color: #ffffff;" +
	                          "        text-align: center;" +
	                          "        padding: 10px;" +
	                          "      }" +
	                          "      .title {" +
	                          "        font-weight: bold;" +
	                          "      }" +
	                          "      * {" +
	                          "        margin: 0;" +
	                          "        padding: 0;" +
	                          "      }" +
	                          "    </style>" +
	                          "  </head>" +
	                          "  <body>" +
	                          "    <div class=\"email-container\">" +
	                          "      <div class=\"header\">" +
	                          "        <h3 style=\"font-weight: bold\">" +
	                          "          CHÚC MỪNG BẠN ĐÃ TẠO TÀI KHOẢN THÀNH CÔNG!" +
	                          "        </h3>" +
	                          "      </div>";
	      htmlContent += "      <p class=\"title\">Xin chào " + user.getUsername() + ",</p>" +
	                     "      <p>Cảm ơn bạn đã đăng ký tài khoản trên Video Sharing vào ngày  " + formattedJoinDate + ". Chúng tôi rất vui được đồng hành cùng bạn.</p>" + 
	                     "      <p>Tài khoản của bạn được tạo tối đa 2 channel và tải lên tối đa 4 video mỗi tháng</p>" +
	                     "      <p>Để tăng giới hạn , hãy cân nhắc đăng kí VIP </p>" +
	                     "      <p>Chúc bạn có những trải nghiệm tuyệt vời trên ứng dụng của chúng tôi!</p>" +
	                     "      <p>Trân trọng,</p>" +
	                     "      <p>Đội ngũ Video Sharing</p>";
	      
	      htmlContent += "      <div class=\"footer\">" +
	                     "        <p>&copy; 2023 Video Sharing</p>" +
	                     "      </div>" +
	                     "    </div>" +
	                     "  </body>" +
	                     "</html>";

	      helper.setText(htmlContent, true);

	      mailSender.send(message);

	    } catch (MessagingException e) {
	      e.printStackTrace();
	    }

	  }

	}