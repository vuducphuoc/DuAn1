package Test;

import Entity.QuocGia;
import Utils.SingletonDaoUtil;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<QuocGia> list = SingletonDaoUtil.getQuocGiaDaoImpl().findAll();
        System.out.println(list.size());
    }
}
