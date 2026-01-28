package com.lavender.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lavender.entity.dto.Account;
import com.lavender.entity.vo.request.ConfirmResetVO;
import com.lavender.entity.vo.request.CreateSubAccountVO;
import com.lavender.entity.vo.request.EmailResetVO;
import com.lavender.entity.vo.request.ModifyEmailVO;
import com.lavender.entity.vo.response.SubAccountVO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends IService<Account>, UserDetailsService {
    Account findAccountByNameOrEmail(String text);
    String registerEmailVerifyCode(String type, String email, String address);
    String resetEmailAccountPassword(EmailResetVO info);
    String resetConfirm(ConfirmResetVO info);
    boolean changePassword(int id, String oldPass, String newPass);
    void createSubAccount(CreateSubAccountVO vo);
    void deleteSubAccount(int uid);
    List<SubAccountVO> listSubAccount();
    String modifyEmail(int id, ModifyEmailVO vo);
}
