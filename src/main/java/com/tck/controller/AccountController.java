package com.tck.controller;

import com.tck.base.BaseData;
import com.tck.entity.AccountBean;
import com.tck.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tck on 2017/8/6.
 */
@RequestMapping("/account")
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/addAccount")
    public BaseData<String> addAccount(@RequestParam("accountName") String accountName,
                                       @RequestParam("remark") String remark,
                                       @RequestParam("userId") Integer userId) {

        return accountService.addAccount(accountName,remark,userId);
    }
    @RequestMapping("/getAccountList")
    public BaseData<List<AccountBean>> getAccountList(@RequestParam("userId") Integer userId) {

        return accountService.getAccountList(userId);
    }
}
