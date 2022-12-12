package org.onlydevs.business;


import org.onlydevs.domain.AccessToken;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
