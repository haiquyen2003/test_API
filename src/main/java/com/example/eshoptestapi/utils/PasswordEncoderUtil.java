package com.example.eshoptestapi.utils; // Đảm bảo thay đổi package này nếu cần

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "123456"; // Thay thế bằng mật khẩu thực tế
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }
}
