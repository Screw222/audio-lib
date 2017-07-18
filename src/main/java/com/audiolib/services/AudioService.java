/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audiolib.services;

import com.audiolib.model.dao.DAOFactory;
import com.audiolib.model.entites.Audio;
import com.audiolib.utils.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Victor Novikov
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class AudioService {

    public static final Logger logger = LoggerFactory.getLogger(AudioService.class);

    /**
     * Get Audio By Id
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/audio/{id}")
    public ResponseEntity<Audio> getAudioById(@PathVariable("id") int id) {
        logger.info("Fetching audio with id {}", id);
        Audio audio = DAOFactory.getDAOAudio().getAudioByID(id);
        if (audio == null) {
            logger.warn("Audio with id {} not found", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(audio, HttpStatus.OK);

    }

    /**
     * Update Audio
     *
     * @param id
     * @param audio
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/audio/{id}")
    public ResponseEntity<?> updateAudio(@PathVariable("id") int id, @RequestBody Audio audio) {
        logger.info("Updating audio with id {}", id);
        Audio oldAudio = DAOFactory.getDAOAudio().getAudioByID(id);
        if (oldAudio == null) {
            logger.warn("Audio with id {} not found", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        oldAudio.setAlbum(audio.getAlbum());
        oldAudio.setName(audio.getName());
        oldAudio.setYear(audio.getYear());
        oldAudio.setAuthor(audio.getAuthor());
        oldAudio.setUrl(audio.getUrl());
        DAOFactory.getDAOAudio().updateAudio(oldAudio);
        return new ResponseEntity<>(oldAudio, HttpStatus.CREATED);
    }

    /**
     * Delete Audio
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/audio/{id}")
    public ResponseEntity<Audio> deleteAudio(@PathVariable("id") int id) {
        logger.info("Deleting audio with id {}", id);

        Audio audio = DAOFactory.getDAOAudio().getAudioByID(id);
        if (audio == null) {
            logger.warn("Audio with id {} not found", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        DAOFactory.getDAOAudio().deleteAudio(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * Get List with All Audios with Pagination
     *
     * @param size number of audio in page
     * @param num  number of page with current size
     * @return
     */
    @RequestMapping(value = "/audio/page", method = GET)
    public ResponseEntity<Page> findAudioPaginated(
            @RequestParam(value = "num", defaultValue = "1", required = false) Integer num,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {

        if (num == null || size == null) {
            logger.warn("Bad Params for Audio Page");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        logger.info("Fethcing Audio Page {} with Size {} ", num, size);
        Page page = DAOFactory.getDAOAudio().getPage(num, size);
        if (page == null || page.isEmpty()) {
            logger.warn("Audio Page {} with Size {} not found", num, size);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(
                page, HttpStatus.OK);
    }

    /**
     * Get Audio By Params
     *
     * @param name   audio name
     * @param album  audio album name
     * @param author audio author
     * @param year   year of audio publishing
     * @param size   number of audio in page
     * @param page   number of page with current size
     * @return Page Object with Audio Content
     */
    @RequestMapping(method = RequestMethod.GET, value = "/audio")
    public ResponseEntity<Page> findAudioByParams(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "album", required = false) String album,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {

        logger.info("Fethcing audio with params: name {}, author {}, album {}, year {}, page {}, pageSize {}",
                name, album, album, year, page, size);
        Page pageObject = DAOFactory.getDAOAudio().getPageParams(page, size, name, author, album, year);

        if (pageObject == null) {
            logger.warn("Audio with params {} name {}, author {}, album {}, year {}, page {}, pageSize {}"
                    + " not found",
                    name, album, album, year, page, size);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(
                pageObject, HttpStatus.OK);
    }

    /**
     * Create Audio
     *
     * @param audio
     * @param ucBuilder
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/audio")
    public ResponseEntity<?> createAudio(@RequestBody Audio audio,
            UriComponentsBuilder ucBuilder) {
        logger.info("Inserting Audio...");
        if (audio == null) {
            logger.warn("Input Audio is NULL");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        DAOFactory.getDAOAudio().insertAudio(audio);
        logger.warn("Audio {} successefully insert");
        return new ResponseEntity<>(audio, HttpStatus.CREATED);
    }

}
