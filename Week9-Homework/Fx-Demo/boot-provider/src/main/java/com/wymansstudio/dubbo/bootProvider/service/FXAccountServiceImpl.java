package com.wymansstudio.dubbo.bootProvider.service;


import com.wymansstudio.dubbo.boot.api.fxAccount.entity.FXAccount;
import com.wymansstudio.dubbo.boot.api.fxAccount.mapper.FXAccountMapper;
import com.wymansstudio.dubbo.boot.api.fxAccount.service.FXAccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@DubboService(retries = 0)
public class FXAccountServiceImpl implements FXAccountService {

    @Autowired(required = false)
    private FXAccountMapper fxAccountMapper;

    @Override
    public List<FXAccount> findAllFXAccount() {
        return fxAccountMapper.findAll();
    }

    @Override
    public FXAccount findFXAccountByUserId(long user_id) {
        return null;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public boolean updateFXAccount(FXAccount fxAccount) {
        log.info("provider update FXAccount called");
        FXAccount updateDO ;
        if ("USD".equals(fxAccount.getFreeze_ccy())) {
            updateDO = new FXAccount(fxAccount.getUser_id(), fxAccount.getCcy_USD(), 0, fxAccount.getFreeze_amount(), fxAccount.getFreeze_ccy());
        } else {
            updateDO = new FXAccount(fxAccount.getUser_id(), 0, fxAccount.getCcy_CNH(), fxAccount.getFreeze_amount(), fxAccount.getFreeze_ccy());
        }
        log.info("{}",updateDO.toString());
        return fxAccountMapper.update(updateDO) > 0;
    }

    public boolean confirm(FXAccount fxAccount) {
        log.info("provider update FXAccount confirmed");
        FXAccount updateDO ;
        if ("USD".equals(fxAccount.getFreeze_ccy())) {
            updateDO = new FXAccount(fxAccount.getUser_id(), 0, fxAccount.getCcy_CNH(), 0, null);
        } else {
            updateDO = new FXAccount(fxAccount.getUser_id(), fxAccount.getCcy_USD(), 0, 0, null);
        }
        log.info("{}",updateDO.toString());
        return fxAccountMapper.update(updateDO) > 0;
    }

    public boolean cancel(FXAccount fxAccount) {
        log.info("provider update FXAccount cancelled");
        FXAccount updateDO ;
        if ("USD".equals(fxAccount.getFreeze_ccy())) {
            updateDO = new FXAccount(fxAccount.getUser_id(), fxAccount.getFreeze_amount(), 0,0, null);
        } else {
            updateDO = new FXAccount(fxAccount.getUser_id(), 0, fxAccount.getFreeze_amount(), 0, null);
        }
        log.info("{}",updateDO.toString());
        return fxAccountMapper.update(updateDO) > 0;
    }
}

