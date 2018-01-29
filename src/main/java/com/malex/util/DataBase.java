package com.malex.util;

import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Builder
 *
 * @author : malex
 */
@Builder
public class DataBase {

    private static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS";
    private static final String SQL_EMPTY = " ";
    private static final String SQL_END = ";";

    @Getter
    private String dbName;
    private String query;
    private Set<String> queryParameters;

    public static class DataBaseBuilder {
        public DataBaseBuilder addSql(String sql) {
            if (queryParameters == null) {
                queryParameters = new HashSet<>();
            }
            queryParameters.add(sql);
            return this;
        }

        public String getDbName() {
            return dbName;
        }

        public String getQuery() {
            StringBuilder sb = new StringBuilder(SQL_CREATE_TABLE);
            sb.append(SQL_EMPTY);
            sb.append(dbName);
            sb.append(SQL_EMPTY);
            sb.append(queryParameters.stream().collect(Collectors.joining(" ")));
            sb.append(SQL_END);
            return new String(sb);
        }
    }
}
