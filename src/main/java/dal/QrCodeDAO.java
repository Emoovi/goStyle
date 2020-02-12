package dal;

import model.QrCode;


import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QrCodeDAO {

    public static final String FINDALL_QUERY = "SELECT * FROM QrCode";
    private static final String CREATE_QUERY = "INSERT INTO qrcode (\"uid\", \"label\", \"description\", \"validitydate\", \"startdate\" )VALUES (?,?,?,?,?)";
    private static final String FIND_BY_ID = "SELECT * FROM qrCode WHERE \"uid\" = ?";
    private static final String UPDATE_BY_ID = "UPDATE qrcode SET \"label\" = ?, \"description\" = ?, \"validitydate\" = ?, \"startdate\" = ? WHERE \"UID\" = ?";
    private static final String DELETE_BY_UID = "DELETE FROM qrcode WHERE \"uid\" = ?";

    SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");

    public List<QrCode> findAll() throws SQLException, IOException, ClassNotFoundException {

        List<QrCode> listQrCode = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FINDALL_QUERY)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        QrCode qrCode = new QrCode();
                        qrCode.setValidityDate(formatter.format(rs.getDate("validitydate")));
                        qrCode.setStartDate(formatter.format(rs.getDate("startdate")));
                        qrCode.setUid(rs.getString("uid"));
                        qrCode.setCode(rs.getString("label"));
                        qrCode.setDescription(rs.getString("description"));
                        listQrCode.add(qrCode);
                    }
                }

            }
        }
        return listQrCode;
    }


    public QrCode findByUid(String id) throws SQLException, IOException, ClassNotFoundException {
        QrCode qrCode = new QrCode();
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            UUID toInsertUUID = UUID.fromString(id);
            try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
                ps.setObject(1, toInsertUUID);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        qrCode.setValidityDate(rs.getDate("validitydate").toString());
                        qrCode.setStartDate(rs.getDate("startdate").toString());
                        qrCode.setUid(rs.getString("uid"));
                        qrCode.setCode(rs.getString("label"));
                        qrCode.setDescription(rs.getString("description"));
                    }
                }
                ps.executeQuery();
            }
        }
        return qrCode;

    }

    public boolean create(QrCode object) throws SQLException, IOException, ClassNotFoundException, ParseException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            UUID toInsertUUID = UUID.fromString(object.getUid());
            try (PreparedStatement ps = connection.prepareStatement(CREATE_QUERY)) {
                String[] split = object.getStartDate().split(" ");
                String[] split2 = object.getValidityDate().split(" ");
                ps.setDate(5, Date.valueOf(split[0]));
                ps.setObject(1, toInsertUUID);
                ps.setString(2, object.getCode());
                ps.setString(3, object.getDescription());
                ps.setObject(4, Date.valueOf(split2[0]));
                ps.executeUpdate();
                return true;
            }
        }

        return false;
    }


    public Boolean update(QrCode qrcode) throws SQLException, IOException, ClassNotFoundException, ParseException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            UUID toInsertUUID = UUID.fromString(qrcode.getUid());
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_BY_ID)) {
                String[] split = qrcode.getStartDate().split(" ");
                String[] split2 = qrcode.getValidityDate().split(" ");
                ps.setDate(1, Date.valueOf(split[0]));
                ps.setString(2, qrcode.getCode());
                ps.setString(3, qrcode.getDescription());
                ps.setObject(4, toInsertUUID);
                ps.setDate(5, Date.valueOf(split2[0]));
                ps.executeUpdate();
                return true;
            }
        }
        return false;
    }

    public Boolean delete(String uuid) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if (connection != null) {
            UUID toInsertUUID = UUID.fromString(uuid);
            try (PreparedStatement ps = connection.prepareStatement(DELETE_BY_UID)) {
                ps.setObject(1, toInsertUUID);
                ps.executeUpdate();
                return true;
            }
        }
        return false;
    }

}
