/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audiolib.model.dao;

import com.audiolib.model.dao.abstr.DAOAudioInterface;
import com.audiolib.model.entites.Audio;
import com.audiolib.model.mappers.AudioMapper;
import com.audiolib.utils.MyBatisUtils;
import com.audiolib.utils.Page;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Victor Novikov
 */
public class DAOAudio implements DAOAudioInterface {

    DAOAudio() {
    }

    /**
     * Get Audio By Id
     *
     * @param i
     * @return
     */
    @Override
    public Audio getAudioByID(int i) {
        try (SqlSession session = MyBatisUtils.getSession().openSession()) {
            AudioMapper mapper = session.getMapper(AudioMapper.class);
            return mapper.getAudioById(i);
        }
    }

    /**
     * Inser Audio Entity
     *
     * @param audio
     */
    @Override
    public void insertAudio(Audio audio) {
        try (SqlSession session = MyBatisUtils.getSession().openSession()) {
            AudioMapper mapper = session.getMapper(AudioMapper.class);
            mapper.insertAudio(audio);
            session.commit();
        }
    }

    /**
     * Update Audio Entity
     *
     * @param audio
     */
    @Override
    public void updateAudio(Audio audio) {
        try (SqlSession session = MyBatisUtils.getSession().openSession()) {
            AudioMapper mapper = session.getMapper(AudioMapper.class);
            mapper.updateAudio(audio);
            session.commit();
        }
    }

    /**
     * Delete Audio
     *
     * @param i audio id
     */
    @Override
    public void deleteAudio(int i) {
        try (SqlSession session = MyBatisUtils.getSession().openSession()) {
            AudioMapper mapper = session.getMapper(AudioMapper.class);
            mapper.deleteAudio(i);
            session.commit();
        }
    }

    /**
     * Get List with all audio from storage
     *
     * @return
     */
    public List<Audio> getAllAudios() {
        try (SqlSession session = MyBatisUtils.getSession().openSession()) {
            AudioMapper mapper = session.getMapper(AudioMapper.class);
            return mapper.getAllAudio();
        }
    }

    /**
     * Find Audios By Name
     *
     * @param name
     * @return
     */
    public List<Audio> findByName(String name) {
        try (SqlSession session = MyBatisUtils.getSession().openSession()) {
            AudioMapper mapper = session.getMapper(AudioMapper.class);
            return mapper.findAudioByName(name);
        }
    }

    /**
     * Find Audios By Album
     *
     * @param album
     * @return
     */
    public List<Audio> findByAlbum(String album) {
        try (SqlSession session = MyBatisUtils.getSession().openSession()) {
            AudioMapper mapper = session.getMapper(AudioMapper.class);
            return mapper.findAudioByAlbum(album);
        }
    }

    /**
     * Find Audios By Author
     *
     * @param author
     * @return
     */
    public List<Audio> findByAuthor(String author) {
        try (SqlSession session = MyBatisUtils.getSession().openSession()) {
            AudioMapper mapper = session.getMapper(AudioMapper.class);
            return mapper.findAudioByAuthor(author);
        }
    }

    /**
     * Find Audios By Year
     *
     * @param year
     * @return
     */
    public List<Audio> findByYear(int year) {
        try (SqlSession session = MyBatisUtils.getSession().openSession()) {
            AudioMapper mapper = session.getMapper(AudioMapper.class);
            return mapper.findAudioByYear(year);
        }
    }

    /**
     * Get Audio By Params
     *
     * @param name   audio name
     * @param album  audio album name
     * @param author audio author
     * @param year   year of audio publishing
     * @param size   number of audio in page
     * @param num    number of page with current size
     * @return Page Object with Audio Content
     */
    public Page getPageParams(int num, int size, String name, String author, String album, Integer year) {
        int offset = (num - 1) * size;
        RowBounds rowBounds;
        if (num <= 0) {
            rowBounds = new RowBounds();
        } else {
            rowBounds = new RowBounds(offset, size);
        }
        try (SqlSession session = MyBatisUtils.getSession().openSession()) {
            AudioMapper mapper = session.getMapper(AudioMapper.class);
            List<Audio> audioList = mapper.findByParametrs(rowBounds, name, author, album, year);
            int numRows = getNumRowsWithParams(name, author, album, year);
            return new Page(audioList, size, num, numRows);
        }
    }

    /**
     * Get page object with current boundaries
     *
     * @param size number of audio in page
     * @param num  number of page with current size
     * @return
     */
    public Page getPage(int num, int size) {
        List<Audio> audioList = findPaginated(num, size);
        int numRows = getNumRows();
        return new Page(audioList, size, num, numRows);
    }

    /**
     * Get AudioList with current boundaries
     *
     * @param size number of audio in page
     * @param num  number of page with current size
     * @return
     */
    public List<Audio> findPaginated(int num, int size) {
        int offset = (num - 1) * size;
        RowBounds rowBounds;
        if (num <= 0) {
            rowBounds = new RowBounds();
        } else {
            rowBounds = new RowBounds(offset, size);
        }
        try (SqlSession session = MyBatisUtils.getSession().openSession()) {
            AudioMapper mapper = session.getMapper(AudioMapper.class);
            return mapper.getPaginationAudio(rowBounds);
        }
    }

    /**
     * Get Number of rows in storage
     *
     * @return
     */
    public int getNumRows() {
        try (SqlSession session = MyBatisUtils.getSession().openSession()) {
            AudioMapper mapper = session.getMapper(AudioMapper.class);
            int numRows = mapper.getNumRows();
            return numRows;
        }
    }

    /**
     * Get Number of rows in storage with current parametrs
     *
     * @param name   audio name
     * @param album  audio album name
     * @param author audio author
     * @param year   year of audio publishing
     * @return
     */
    public int getNumRowsWithParams(String name, String author, String album, Integer year) {
        try (SqlSession session = MyBatisUtils.getSession().openSession()) {
            AudioMapper mapper = session.getMapper(AudioMapper.class);
            int numRows = mapper.getNumRowsWithParametrs(name, author, album, year);
            return numRows;
        }
    }
}
