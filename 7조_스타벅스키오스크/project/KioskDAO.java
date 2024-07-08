package project;

import java.util.ArrayList;

public interface KioskDAO {

    int KioskInsert(KioskVO mvo);
    int KioskUpdate(KioskVO mvo);
    int KioskDelete(KioskVO mvo);
    ArrayList<KioskVO> SelectALL();

}
