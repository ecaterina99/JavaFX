package com.internetPackages.Exception;

public class PackageServiceException extends RuntimeException {
    public PackageServiceException(String message) {
        super(message);
    }

    public PackageServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}