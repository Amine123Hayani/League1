package com.footballmanager.exceptions;

/**
 * Exception personnalisée pour gérer les ressources non trouvées.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mesadminge) {
        super(mesadminge);
    }
}