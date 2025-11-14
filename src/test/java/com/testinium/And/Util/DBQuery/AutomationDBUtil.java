package com.testinium.And.Util.DBQuery;

/*import com.bein.AndTV.Model.TVFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutomationDBUtil extends MySqlConnection {
    private static AutomationDBUtil instance;
    private static final Logger log = LoggerFactory.getLogger(AutomationDBUtil.class);

    public static synchronized AutomationDBUtil getInstance(){
        if (instance == null)
            instance = new AutomationDBUtil();
        return instance;
    }

    public TVFormat getPhoneFormat(String countryCode) throws Exception {
        String query = "SELECT initial, digit FROM automation.APAC_PHONEFORMAT where country_code = '" + countryCode + "'";
        Object[] result = executeSingleResultQuery(query);
        if(result == null){
            String error = "PHONE FORMAT DATA NOT FOUND IN DB. QUERY IS: " + query;
            log.error(error);
            throw new Exception(error);
        }
        TVFormat phoneFormat = new TVFormat();
        phoneFormat.setDigit((Integer)result[1]);
        phoneFormat.setInitial((String)result[0]);
        return phoneFormat;
    }

    public String getRandomCreditCardNumber(String countryCode){
        String query = "SELECT id, card_number FROM automation.APAC_CREDITCARD where region = '" + countryCode + " and is_used = 0 limit 1";
        Object[] result = executeSingleResultQuery(query);
        int id = (int)result[0];
        query = "UPDATE automation.APAC_CREDITCARD set is_used = 1 where id = " + id;
        executeSQLScript(query);
        return (String)result[1];
    }
}*/
