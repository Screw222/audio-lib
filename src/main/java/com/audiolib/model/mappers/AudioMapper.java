/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audiolib.model.mappers;

import com.audiolib.model.entites.Audio;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.RowBounds;

/**
 *
 * @author Victor Novikov
 */
public interface AudioMapper {

    final String getAudioById = "SELECT * FROM audio WHERE id=#{id}";
    final String updateAudio = "UPDATE audio SET name=#{name}, author=#{author}, "
            + "album=#{album}, year=#{year}, url=#{url} WHERE id=#{id}";
    final String insertAudio = "INSERT INTO audio(name,author,album,year,url) VALUES "
            + "(#{name},#{author},#{album},#{year},#{url})";
    final String deleteAudio = "DELETE FROM audio WHERE id=#{id}";

    final String getNumRows = "SELECT COUNT(*) FROM audio";

    final String getAllAudio = "SELECT * FROM audio";
    final String findPagination = "SELECT * FROM audio";
    final String findAudioByName = "SELECT * FROM audio WHERE name= #{name}";
    final String findAudioByAuthor = "SELECT * FROM audio WHERE author= #{author}";
    final String findAudioByAlbum = "SELECT * FROM audio WHERE album= #{album}";
    final String findAudioByYear = "SELECT * FROM audio WHERE year= #{year}";

    @Results(value = {
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "author", column = "author"),
        @Result(property = "album", column = "album"),
        @Result(property = "year", column = "year"),
        @Result(property = "url", column = "url")})
    @SelectProvider(type = AudioSqlBuilder.class, method = "buildGetAudiosByParams")
    List<Audio> findByParametrs(RowBounds rowBounds,
            @Param(value = "name") String name,
            @Param(value = "author") String author,
            @Param(value = "album") String album,
            @Param(value = "year") Integer year);

    @SelectProvider(type = AudioSqlBuilder.class, method = "buildGetRowNumsAudiosByParams")
    int getNumRowsWithParametrs(
            @Param(value = "name") String name,
            @Param(value = "author") String author,
            @Param(value = "album") String album,
            @Param(value = "year") Integer year);

    @Select(getNumRows)
    int getNumRows();

    @Select(findPagination)
    @Results(value = {
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "author", column = "author"),
        @Result(property = "album", column = "album"),
        @Result(property = "year", column = "year"),
        @Result(property = "url", column = "url")})
    List<Audio> getPaginationAudio(RowBounds rowBounds);

    @Select(getAudioById)
    @Results(value = {
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "author", column = "author"),
        @Result(property = "album", column = "album"),
        @Result(property = "year", column = "year"),
        @Result(property = "url", column = "url")})
    Audio getAudioById(int id);

    @Update(updateAudio)
    void updateAudio(Audio audio);

    @Insert(insertAudio)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertAudio(Audio audio);

    @Delete(deleteAudio)
    void deleteAudio(long id);

    @Select(getAllAudio)
    @Results(value = {
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "author", column = "author"),
        @Result(property = "album", column = "album"),
        @Result(property = "year", column = "year"),
        @Result(property = "url", column = "url")})
    List<Audio> getAllAudio();

    @Select(findAudioByName)
    @Results(value = {
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "author", column = "author"),
        @Result(property = "album", column = "album"),
        @Result(property = "year", column = "year"),
        @Result(property = "url", column = "url")})
    List<Audio> findAudioByName(String name);

    @Select(findAudioByAlbum)
    @Results(value = {
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "author", column = "author"),
        @Result(property = "album", column = "album"),
        @Result(property = "year", column = "year"),
        @Result(property = "url", column = "url")})
    List<Audio> findAudioByAlbum(String album);

    @Select(findAudioByAuthor)
    @Results(value = {
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "author", column = "author"),
        @Result(property = "album", column = "album"),
        @Result(property = "year", column = "year"),
        @Result(property = "url", column = "url")})
    List<Audio> findAudioByAuthor(String author);

    @Select(findAudioByYear)
    @Results(value = {
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "author", column = "author"),
        @Result(property = "album", column = "album"),
        @Result(property = "year", column = "year"),
        @Result(property = "url", column = "url")})
    List<Audio> findAudioByYear(int year);

    class AudioSqlBuilder {

        public String buildGetAudiosByParams(@Param(value = "name") final String name,
                @Param(value = "author") final String author,
                @Param(value = "album") final String album,
                @Param(value = "year") final Integer year) {
            return new SQL() {
                {
                    SELECT("*");
                    FROM("audio");
                    if (name != null) {
                        WHERE("name=#{name}");
                    }
                    if (author != null) {
                        WHERE("author=#{author}");
                    }
                    if (album != null) {
                        WHERE("album=#{album}");
                    }
                    if (year != null) {
                        WHERE("year=#{year}");
                    }
                }
            }.toString();
        }

        public String buildGetRowNumsAudiosByParams(@Param(value = "name") final String name,
                @Param(value = "author") final String author,
                @Param(value = "album") final String album,
                @Param(value = "year") final Integer year) {
            return new SQL() {
                {
                    SELECT("COUNT(*)");
                    FROM("audio");
                    if (name != null) {
                        WHERE("name=#{name}");
                    }
                    if (author != null) {
                        WHERE("author=#{author}");
                    }
                    if (album != null) {
                        WHERE("album=#{album}");
                    }
                    if (year != null) {
                        WHERE("year=#{year}");
                    }
                }
            }.toString();
        }
    }
}
