package com.basketclub.security.service;

import com.basketclub.domain.SocialDetails;

public interface SocialOauthService {
    SocialDetails getSocialDetails(String code);
}
