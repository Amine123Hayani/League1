package com.footballmanager.exceptions;

/**
 * Exception levée lorsqu'une équipe existe déjà en base de données.
 */
public class TeamAlreadyExistsException extends RuntimeException {
    public TeamAlreadyExistsException(String mesadminge) {
        super(mesadminge);
    }
}