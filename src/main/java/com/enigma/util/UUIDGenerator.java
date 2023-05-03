package com.enigma.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDGenerator implements RandomStringGenerator {
    @Override
    public String random() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
