package com.fmi.checksumcalculator;

import com.fmi.exceptions.HashAlgorithmNotFound;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5ChecksumCalculator extends BaseChecksumCalculator {

    @Override
    protected MessageDigest getHashAlgorithm() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new HashAlgorithmNotFound("MD5 algorithm not found", e);
        }
    }
}
