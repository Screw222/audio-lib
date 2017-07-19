/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audiolib.model.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 *
 * @author Victor Novikov
 */
public interface TablesMapper {

    final String createAudioTable = "CREATE SEQUENCE IF NOT EXISTS public.audio_id_seq; "
            + "CREATE TABLE IF NOT EXISTS public.audio (id integer NOT NULL DEFAULT nextval('audio_id_seq'::regclass),"
            + "  name character varying NOT NULL,"
            + "  author character varying,"
            + "  album character varying,"
            + "  year smallint,"
            + "  url character varying,"
            + "  CONSTRAINT audio_pkey PRIMARY KEY (id)"
            + ")";

    final String insertTestAudio = "INSERT INTO audio (name,author,album,year,url) "
            + "VALUES ('A Line In The Sand', 'Linkin Park', 'The Hunting Party', 2015, "
            + "'http://dnl10.mp3crazy.me/dl/online/XUlcvp9-Ty64PHxlR7IBow/1500346731/download_music/2014/06/linkin-park-a-line-in-the-sand-(mp3crazy.me).mp3');";

    final String createUsersTable = "CREATE SEQUENCE IF NOT EXISTS public.users_id_seq; "
            + "CREATE TABLE IF NOT EXISTS public.users"
            + " (id integer NOT NULL DEFAULT nextval('users_id_seq'::regclass),"
            + "  admin boolean NOT NULL,"
            + "  login character varying NOT NULL,"
            + "  pwd character varying NOT NULL,"
            + "  CONSTRAINT users_pkey PRIMARY KEY (id)"
            + ")";

    final String insertTestUsers = "INSERT INTO users (admin,login,pwd) "
            + "VALUES (true,'admin','$2a$10$Rn9RxijIopC45Gf0fJAyGeyDxxJZHEx.Ws1zhhb9t47dn22nn.CLi');"
            + "INSERT INTO users (admin,login,pwd) "
            + "VALUES (false,'user','$2a$10$jiQYRXqlHvv.nTj2IME9KuL5ynLnVq5XI0IXyOWuv8w.qQs8vwm/S');";

    final String checkTableExists = "SELECT EXISTS (SELECT 1 FROM pg_tables WHERE"
            + " schemaname = 'public' AND tablename=#{name})";

    @Insert(createAudioTable)
    void createAudioTable();

    @Insert(insertTestAudio)
    void insertTestAudio();

    @Insert(createUsersTable)
    void createUsersTable();

    @Insert(insertTestUsers)
    void insertTestUsers();

    @Select(checkTableExists)
    boolean checkTableExists(String name);

}
