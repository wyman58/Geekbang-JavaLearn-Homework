package com.wymansstudio.dubbo.boot.api.fxAccount.service;

import com.wymansstudio.dubbo.boot.api.fxAccount.entity.FXAccount;
import org.dromara.hmily.annotation.Hmily;

import java.util.List;

public interface FXAccountService {

    List<FXAccount> findAllFXAccount();

    FXAccount findFXAccountByUserId(long user_id);

    @Hmily
    boolean updateFXAccount(FXAccount fxAccount);
}
