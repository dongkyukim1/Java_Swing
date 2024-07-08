package project;

import project.db.Query;

import java.util.ArrayList;

public class KioskDAOimpl implements KioskDAO {

    @Override
    public int KioskInsert(KioskVO mvo) {
        int result = Query.insert( "INSERT INTO kiosk(PRODUCT, PRICE, TYPE, DELETEYN, INSERTDATE, UPDATEDATE) "
                + "VALUES (?, ?, ?, 'Y', SYSDATE, SYSDATE)", mvo.getPRODUCT(), mvo.getPRICE(), mvo.getTYPE().toString());

        System.out.println("해당 메뉴 " + result + "건 입력 되었습니다.");
        return result;
    }


    @Override
    public int KioskUpdate(KioskVO mvo) {
        String sql = "UPDATE kiosk "
                + "   SET PRODUCT = ?, "
                + "       PRICE = ?, "
                + "       UPDATEDATE = SYSDATE "
                + "   WHERE PRODUCT = ? AND TYPE = ? AND DELETEYN = 'N'";
        int result = Query.update(sql,mvo.getPRODUCT(), mvo.getPRICE(), mvo.getPRODUCT(), mvo.getTYPE().name()); // TYPE는 문자열로 설정
        System.out.println(result + " 건 수정 완료 되었습니다.");
        return result;
    }

    @Override
    public int KioskDelete(KioskVO mvo) {
        String sql = "DELETE FROM kiosk WHERE PRODUCT = ? AND TYPE = ? AND DELETEYN = 'Y'";
        int result = Query.delete(sql, mvo.getPRODUCT(), mvo.getTYPE().name());
        return result;
    }

    @Override
    public ArrayList<KioskVO> SelectALL() {
        return Query.select("SELECT PRODUCT, PRICE, TYPE FROM kiosk ORDER BY TYPE, PRODUCT");
    }
}