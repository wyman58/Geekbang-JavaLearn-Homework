package com.wymansstudio.dubbo.boot.api.fxAccount.mapper;

import com.wymansstudio.dubbo.boot.api.fxAccount.entity.FXAccount;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface FXAccountMapper {

    @Select("select * from fx_account")
    List<FXAccount> findAll();

    @Select("select * from fx_account where user_id = #{user_id}")
    FXAccount findByUserId(long user_id);

    @Update("update fx_account set ccy_USD = ccy_USD + #{ccy_USD}, " +
            "ccy_CNH = ccy_CNH + #{ccy_CNH}, " +
            "freeze_amount=#{freeze_amount}, " +
            "freeze_ccy=#{freeze_ccy} where user_id=#{user_id}")
    int update(FXAccount fxAccount);
}
