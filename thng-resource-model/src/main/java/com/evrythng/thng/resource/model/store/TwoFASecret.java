package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.ResourceModel;

public final class TwoFASecret extends ResourceModel {

    private String secretKey;

    public TwoFASecret(final String secretKey) {
        this.secretKey = secretKey;
    }

    public TwoFASecret() {

    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(final String secretKey) {
        this.secretKey = secretKey;
    }
}
