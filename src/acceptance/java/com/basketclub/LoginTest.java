package com.basketclub;

import com.basketclub.domain.MediaFile;
import com.basketclub.domain.SocialDetails;
import com.basketclub.domain.SocialProvider;
import com.basketclub.security.service.KakaoOauthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "spring.oauth.client=sample-oauth.yml")
@AutoConfigureMockMvc
public class LoginTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KakaoOauthService kakaoOauthService;

    private SocialDetails socialDetails;

    @BeforeEach
    void setUp() {
        socialDetails = SocialDetails.builder()
                .socialId(1L)
                .nickname("nick")
                .mediaFile(new MediaFile(""))
                .socialProvider(SocialProvider.KAKAO)
                .build();

        given(kakaoOauthService.getSocialDetails(anyString()))
                .willReturn(socialDetails);
    }

    @Test
    @DisplayName("로그인 전에는 로그인 url로 리다이렉트된다")
    void preLoginRedirectTest() throws Exception {
        mockMvc.perform(get("/players/1/reviews"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", containsString("/login")));
    }

    @Test
    @DisplayName("로그인하면 내부 리소스에 접근할 수 있다")
    void loginSuccess() throws Exception {
        HttpSession session = mockMvc.perform(get("/oauth/kakao")
                .param("code", "code"))
                .andExpect(status().is3xxRedirection())
                .andReturn()
                .getRequest()
                .getSession();

        mockMvc.perform(get("/players/1/reviews")
                .session((MockHttpSession) session))
                .andExpect(status().isOk());
    }
}
