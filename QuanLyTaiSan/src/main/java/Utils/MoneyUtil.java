package Utils;

import java.text.NumberFormat;

public class MoneyUtil {

    //Chuyển số sang str tiền tệ
    public static String castIntToMoney(Long money) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        return numberFormat.format(money);
    }
}
