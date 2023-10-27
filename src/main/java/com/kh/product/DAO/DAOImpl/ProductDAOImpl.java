package com.kh.product.DAO.DAOImpl;

import com.kh.product.DAO.ProductDAO;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ProductDAOImpl implements ProductDAO {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public Long GeneratedKey(){
        String sql = "SELECT MAX(PID) FROM PRODUCT";

            Long maxNum = jdbcTemplate.queryForObject(sql, Long.class);

        return maxNum==null?1L:maxNum+1L;
    }
}
