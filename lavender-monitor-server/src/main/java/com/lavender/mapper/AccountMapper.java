package com.lavender.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lavender.entity.dto.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
