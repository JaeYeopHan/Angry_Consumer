package com.ac.domain;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jbee on 2016. 11. 30..
 */
public final class ImageRowMapper implements RowMapper<Image> {
    @Override
    public Image mapRow(ResultSet rs, int i) throws SQLException {
        Image image = new Image();
        image.setFilePath(rs.getString("path"));
        return image;
    }
}
