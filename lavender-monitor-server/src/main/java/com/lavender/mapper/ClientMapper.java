package com.lavender.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lavender.entity.dto.Client;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClientMapper extends BaseMapper<Client> {
}
