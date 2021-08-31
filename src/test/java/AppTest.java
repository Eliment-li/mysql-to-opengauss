
import com.tiange.core.migrate.DataMigrateService;
import com.tiange.core.opengauss.table.GaussTable;
import com.tiange.core.utils.database.jdbc.MySqlDbUtil;
import com.tiange.core.utils.database.jdbc.OpenGaussDbUtil;

import java.util.List;
import java.util.Map;

public class AppTest {




    /**
     * 数据传输测试
     *
     * @throws Exception
     */
    public static void DataMigrateTest(GaussTable gaussTable) {

        MySqlDbUtil util = new MySqlDbUtil();

        List<Map<String, Object>> Data = MySqlDbUtil.queryForMapList("select * from " + gaussTable.getTable_name() + ";");

        List<String> sqlList = DataMigrateService.convertDataToInsertSql(Data, gaussTable);

        for (String sql : sqlList) {

            System.out.println(sql);

            OpenGaussDbUtil.execute(sql);

        }
    }


    /**
     *
     */


    /**
     * 功能测试主程序
     */
    public static void main(String[] args) {
        //  init();
        /*
         *  数据库表结构迁移 & 数据迁移
         */
        //    TableTest();

        /*
         *  数据库表索引迁移
         */
        // keyTest();

        double d = 5d;
        byte b = 3 + 4;
    }


}
