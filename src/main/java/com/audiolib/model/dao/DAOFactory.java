/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audiolib.model.dao;

/**
 * Factory for DAO Entites
 *
 * @author Victor Novikov
 */
public class DAOFactory {

    public static DAOAudio getDAOAudio() {
        return new DAOAudio();
    }

    public static DAOUser getDAOUser() {
        return new DAOUser();
    }
}
