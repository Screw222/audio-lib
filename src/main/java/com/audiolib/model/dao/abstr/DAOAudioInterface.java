/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audiolib.model.dao.abstr;

import com.audiolib.model.entites.Audio;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Victor Novikov
 */
public interface DAOAudioInterface {

    Audio getAudioByID(int i);

    void insertAudio(Audio audio);

    void updateAudio(Audio audio);

    void deleteAudio(int i);

    List<Audio> getAllAudios();

}
