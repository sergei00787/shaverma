package com.jbond.shaurmito.repo;

import com.jbond.shaurmito.Ingredient;
import com.jbond.shaurmito.Shaverma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JdbcShavermaRepository implements ShavermaRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public JdbcShavermaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Shaverma save(Shaverma shaverma) {
        long shavermaId = saveOneShaverma(shaverma);
        shaverma.setId(shavermaId);
        for (Ingredient ingredient : shaverma.getIngredients()) {
            saveIngredientToShaverma(ingredient, shavermaId);
        }

        return shaverma;
    }

    private long saveOneShaverma(Shaverma shaverma) {
        shaverma.setCreatedAt(new Date());
        PreparedStatementCreator psc =
                new PreparedStatementCreatorFactory(
                "insert into Shaverma(name, createdAt) values (?, ?)",
                Types.VARCHAR, Types.TIMESTAMP
        ).newPreparedStatementCreator(
                Arrays.asList(
                        shaverma.getName(),
                        new Timestamp(shaverma.getCreatedAt().getTime())));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToShaverma(Ingredient ingredient, long shavermaId) {
        jdbc.update("insert into Shaverma_Ingredients(shaverma, ingredient) values (?,?)",
                shavermaId,
                ingredient.getId());
    }


}
