package Utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class MoneyUtil {

    public static NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("VI", "VN"));

    //Chuyển số sang str tiền tệ
    public static String castIntToMoney(Long money) {
//        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        return numberFormat.format(money);
    }

    public static long castMoneyToInt(String money)  {
        try {
            return (long) numberFormat.parse(money);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
