package Contant;

/**
 * Created by Admin on 10/6/2017.
 */
import Utils.SingletonDaoUtil;

import java.util.List;

public class CoreConstant {
    public static final String SORT_ASC = "1";
    public static final String SORT_DESC = "2";
    public static final String FOLDER_UPLOAD = "fileupload";

    public static final boolean LOGIN_SUCCESS = true;
    public static final boolean LOGIN_FAILE = false;


    public static final int FLAG_EMTY = 0;
    public static final int FLAG_INSERT = 1;
    public static final int FLAG_UPDATE = 2;

    public static final int TYPE_INFORMATION = 1;
    public static final int TYPE_WARNING = 2;
    public static final int TYPE_ERROR= 3;

    public static final  Object[] iconButton = new Object[] {
        "src/Image/icon-new.png",
                "src/Image/icon-edit.png",
                "src/Image/icon-delete.png",
                "src/Image/icon-save.png",
                "src/Image/icon-cancel.png"
    };
    public static final int ROLE_ADMIN = 1;
    public static final int ROLE_RESOURCES = 2; //KHO
    public static final int ROLE_ACCOUNTANT = 3; //KẾ TOÁN
    public static final int ROLE_STAFF = 4; // NHÂN VIÊN
}
