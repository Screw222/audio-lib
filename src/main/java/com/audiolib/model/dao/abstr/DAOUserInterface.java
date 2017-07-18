/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audiolib.model.dao.abstr;

import com.audiolib.model.entites.User;

/**
 *
 * @author Victor Novikov
 */
public interface DAOUserInterface {

    User getUserByID(int i);

    User getUserByName(String name);

}
