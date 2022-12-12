package org.onlydevs.business;


import org.onlydevs.domain.AccessToken;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
