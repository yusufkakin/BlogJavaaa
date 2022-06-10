package com.hustlers.springboot.blog.controllers;

import com.hustlers.springboot.blog.model.Authority;
import com.hustlers.springboot.blog.services.AuthorityService;
import com.hustlers.springboot.blog.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    @Mock
    AuthorityService authorityService;

    @Mock
    UserService userService;

    @InjectMocks
    AdminController controller;

    MockMvc mockMvc;

    Set<Authority> authorities;

    @BeforeEach
    void setUp() {
        authorities = new HashSet<>();
        Authority authority1 = new Authority();
        authority1.setAuthority("USER_ROLE");
        authorities.add(authority1);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();

    }

    @Test
    void changeRights() throws Exception {

        mockMvc.perform(get("/Admin/change?userId=1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("authorityList"))
                .andExpect(view().name("admin/changeRight"));


    }

    @Test
    void confirmRightChange() throws Exception {

    }
}