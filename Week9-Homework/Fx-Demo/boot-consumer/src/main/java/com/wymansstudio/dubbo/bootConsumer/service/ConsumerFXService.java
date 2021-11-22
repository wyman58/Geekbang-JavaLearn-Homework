package com.wymansstudio.dubbo.bootConsumer.service;

import com.wymansstudio.dubbo.boot.api.fxAccount.dto.FXAccountDTO;
import com.wymansstudio.dubbo.boot.api.fxAccount.entity.FXAccount;

import java.util.List;

public interface ConsumerFXService {


    List<FXAccount> findAllFXAccount();

    FXAccount findFXAccountByUserId(long user_id);

    int updateFXAccount(FXAccountDTO fxAccountDTO);
}
