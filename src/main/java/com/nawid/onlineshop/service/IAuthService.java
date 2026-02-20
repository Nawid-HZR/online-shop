package com.nawid.onlineshop.service;

import com.nawid.onlineshop.dto.LoginDto;
import com.nawid.onlineshop.dto.RegisterDto;

public interface IAuthService {


    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
