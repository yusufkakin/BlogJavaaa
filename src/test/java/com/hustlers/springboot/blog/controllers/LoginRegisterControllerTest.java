package com.hustlers.springboot.blog.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class LoginRegisterControllerTest {

    @InjectMocks
    LoginRegisterController controller;

    MockMvc mockMvc;

    @Autowired
    private LocalValidatorFactoryBean validator;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void login() throws Exception {
        mockMvc.perform(get("/showMyLoginPage"))
                .andExpect(view().name("users/login"));
    }

    @Test
    void loginPage() throws Exception {

    }

    @Test
    void register() throws Exception {
        mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("blogUser"))
                .andExpect(view().name("users/register"));
    }

    @Test
    void registrationPage() {
    }

    @Test
    void logoutPage() {
    }
}