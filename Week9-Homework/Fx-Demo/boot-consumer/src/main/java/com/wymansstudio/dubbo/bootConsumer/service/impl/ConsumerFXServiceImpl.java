package com.wymansstudio.dubbo.bootConsumer.service.impl;

import com.wymansstudio.dubbo.boot.api.fxAccount.dto.FXAccountDTO;
import com.wymansstudio.dubbo.boot.api.fxAccount.entity.FXAccount;
import com.wymansstudio.dubbo.boot.api.fxAccount.mapper.FXAccountMapper;
import com.wymansstudio.dubbo.boot.api.fxAccount.service.FXAccountService;
import com.wymansstudio.dubbo.bootConsumer.service.ConsumerFXService;
import com.wymansstudio.dubbo.bootConsumer.service.FXRateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ConsumerFXServiceImpl implements ConsumerFXService {

    @DubboReference
    private FXAccountService fxAccountService;

    private final FXAccountMapper fxAccountMapper;

    private final FXRateService fxRateService;

    @Autowired(required = false)
    public ConsumerFXServiceImpl(FXAccountMapper fxAccountMapper, FXRateService fxRateService) {
        this.fxAccountMapper = fxAccountMapper;
        this.fxRateService = fxRateService;
    }

    @Override
    public List<FXAccount> findAllFXAccount() {
        return fxAccountMapper.findAll();
    }

    @Override
    public FXAccount findFXAccountByUserId(long order_id) {
        return fxAccountMapper.findByUserId(order_id);
    }

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public int updateFXAccount(FXAccountDTO fxAccountDTO) {
        log.info("consumer update FXAccount called");
        fxAccountMapper.update(buildUpdateFXAccount(fxAccountDTO));
        fxAccountService.updateFXAccount(buildCtpyFXAccount(fxAccountDTO));
        return 1;
    }


    public void confirm(FXAccountDTO fxAccountDTO) {
        log.info("consumer update FXAccount confirmed");
        FXAccount fxAccount = buildConfirmFXAccount(fxAccountDTO);
        log.info("{}", fxAccount.toString());
        fxAccountMapper.update(fxAccount);

    }

    public void cancel(FXAccountDTO fxAccountDTO) {
        log.info("consumer update FXAccount cancelled");
        FXAccount fxAccount = buildCancelFXAccount(fxAccountDTO);
        log.info("{}", fxAccount.toString());
        fxAccountMapper.update(fxAccount);
    }

    private FXAccount buildUpdateFXAccount(FXAccountDTO fxAccountDTO) {
        double priAmount = fxAccountDTO.getAmount();
        double secAmount = priAmount * 7;
        FXAccount fxAccount;
        if (fxAccountDTO.isBuySellFlag()) {
            fxAccount = new FXAccount(fxAccountDTO.getUser_id(),0, secAmount * -1, secAmount, "CNH");
        } else {
            fxAccount = new FXAccount(fxAccountDTO.getUser_id(), priAmount * -1, 0, priAmount, "USD");
        }
        return fxAccount;
    }

    private FXAccount buildConfirmFXAccount(FXAccountDTO fxAccountDTO) {
        double priAmount = fxAccountDTO.getAmount();
        double secAmount = priAmount * 7;
        FXAccount fxAccount;
        if (fxAccountDTO.isBuySellFlag()) {
            fxAccount = new FXAccount(fxAccountDTO.getUser_id(),priAmount, 0, 0, null);
        } else {
            fxAccount = new FXAccount(fxAccountDTO.getUser_id(), 0, secAmount, 0, null);
        }
        log.info(fxAccount.toString());
        return fxAccount;
    }

    private FXAccount buildCancelFXAccount(FXAccountDTO fxAccountDTO) {
        FXAccount fxAccount;
        if (fxAccountDTO.isBuySellFlag()) {
            fxAccount = new FXAccount(fxAccountDTO.getUser_id(),0, fxAccountDTO.getAmount(), 0, null);
        } else {
            fxAccount = new FXAccount(fxAccountDTO.getUser_id(), fxAccountDTO.getAmount(), 0, 0, null);
        }
        return fxAccount;
    }

    private FXAccount buildCtpyFXAccount(FXAccountDTO fxAccountDTO) {
        double priAmount = fxAccountDTO.getAmount();
        double secAmount = priAmount * 7;
        FXAccount fxAccount;
        if (fxAccountDTO.isBuySellFlag()) {
            fxAccount = new FXAccount(fxAccountDTO.getCounterParty(), priAmount * -1, secAmount, priAmount, "USD");
        } else {
            fxAccount = new FXAccount(fxAccountDTO.getCounterParty(), priAmount, secAmount * -1, secAmount, "CNH");
        }
        return fxAccount;
    }
}
