package com.tiange.core.opengauss.table;

import com.tiange.core.opengauss.column.GaussColumn;
import com.tiange.core.utils.database.jdbc.OpenGaussDbUtil;
import com.tiange.core.utils.others.FileUtils;
import com.tiange.core.utils.others.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.tiange.core.utils.others.MessageDigestUtil.getSHA256Digest;

public class GaussTableService {

    /**
     * 获取表信息的摘要
     *
     * @param gaussTable
     * @return
     */
    public static String getTableDigest(GaussTable gaussTable) {

        StringBuilder content = new StringBuilder();

        content.append(gaussTable.getTable_name());

        for (GaussColumn column : gaussTable.getGaussColumns()) {
            content.append(column.getColumn_name());
          /*  content.append(column.getDatetype());
            content.append(column.getComment());
            content.append(column.getIs_nullable());
            content.append(column.getNumeric_precision());
            content.append(column.getColumn_default());*/
        }

        String digest = getSHA256Digest(content.toString());

        return digest;
    }

    /**
     * 比较两个表的信息是否相等
     *
     * @param table_1
     * @param table_2
     * @return
     */
    public static boolean compareTable(GaussTable table_1, GaussTable table_2) {

        String Digest_1 = getTableDigest(table_1);
        String Digest_2 = getTableDigest(table_2);

        return Digest_1.equals(Digest_2);

    }


    /**
     * 比较迁移结果
     *
     * @return
     */
    public static boolean migrateCheck(GaussTable source) {


        GaussTable destination = new GaussTable();

        destination.setTable_name(source.getTable_name());

        destination.setGaussColumns(listColumns(destination.getTable_name()));


        return compareTable(source, destination);

    }

    public static List<GaussColumn> listColumns(String tableName) {
        List<GaussColumn> gaussColumns = new ArrayList<>();

        StringBuilder sql = new StringBuilder(FileUtils.getStringByClasspath("opengauss/metadata/query_column.sql"));

        StringUtils.replace("${tableName}", tableName, sql);

        gaussColumns = (List<GaussColumn>) OpenGaussDbUtil.queryForObjectList(sql.toString(), GaussColumn.class);


        return gaussColumns;
    }

}
