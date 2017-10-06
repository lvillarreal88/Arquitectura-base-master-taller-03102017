package com.cosmo.arquitecturamvpbase.repository;

/**
 * Created by leonardo on 04/10/2017.
 */

public class RepositoryError extends Exception {

    private int idError;

    public RepositoryError(String message) {
        super(message);
    }

    public int getIdError() {
        return idError;
    }

    public void setIdError(int idError) {
        this.idError = idError;
    }
}