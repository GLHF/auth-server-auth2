package com.craftsmengroup.paymentsystem.authserver;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"eureka.client.enabled=false"})
@AutoConfigureMockMvc
class AuthServerApplicationTests {
    @Autowired
    MockMvc mvc;

    @Test
    public void requestTokenWhenUsingPasswordGrantTypeThenOk()
            throws Exception {

        this.mvc.perform(post("/oauth/token")
                .param("grant_type", "password")
                .param("username", "myFuckinMail@mail.ru")
                .param("password", "123456")
                //basic auth with payment-system:payment-secret creds
                .header("Authorization", "Basic cGF5bWVudC1zeXN0ZW06cGF5bWVudC1zZWNyZXQ="))
                .andExpect(status().isOk());
    }

    @Test
    public void requestJwkSetWhenUsingDefaultsThenOk()
            throws Exception {

        this.mvc.perform(get("/.well-known/jwks.json"))
                .andExpect(status().isOk());
    }
}
