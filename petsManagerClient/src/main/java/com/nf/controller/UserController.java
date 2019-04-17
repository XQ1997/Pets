package com.nf.controller;

import com.nf.entity.Account;
import com.nf.exception.ServiceException;
import com.nf.service.AccountService;
import com.nf.service.PetsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private AccountService accountService;
    @Autowired
    private PetsService petsService;


}
