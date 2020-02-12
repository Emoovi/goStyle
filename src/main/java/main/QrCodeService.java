package main;

import model.QrCode;
import org.springframework.data.querydsl.QuerydslRepositoryInvokerAdapter;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import dal.*;

@Service
public class QrCodeService {



    public List<QrCode> getAllQrCode() throws SQLException, IOException, ClassNotFoundException {
        QrCodeDAO dao = new QrCodeDAO();
        List<QrCode> listReturn = dao.findAll();
        return listReturn;
    }

    public boolean createQrCode(QrCode qrcode) throws SQLException, IOException, ClassNotFoundException, ParseException {
        QrCodeDAO dao = new QrCodeDAO();
        return dao.create(qrcode);
    }

    public QrCode findByUidQrCode(String uid) throws SQLException, IOException, ClassNotFoundException {
        QrCodeDAO dao = new QrCodeDAO();
        return dao.findByUid(uid);
    }

    public QrCode updateByUID(QrCode qrcode) throws SQLException, IOException, ClassNotFoundException, ParseException {
        QrCodeDAO dao = new QrCodeDAO();
        dao.update(qrcode);
        QrCode qrModify = dao.findByUid(qrcode.getUid());
        return qrModify;
    }

    public String deleteByUID(String uid) throws SQLException, IOException, ClassNotFoundException {
        QrCodeDAO dao = new QrCodeDAO();
        dao.delete(uid);
        return uid;
    }



}
