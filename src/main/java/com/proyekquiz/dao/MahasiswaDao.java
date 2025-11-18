package com.proyekquiz.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.proyekquiz.model.Mahasiswa;
import com.proyekquiz.util.DButil;

public class MahasiswaDao {
    public void tambahMahasiswa(Mahasiswa mhs) {
        String sql = "INSERT INTO mahasiswa (nim, nama, email) VALUES (?, ?, ?)";
        try (Connection conn = DButil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mhs.getNim());
            ps.setString(2, mhs.getNama());
            ps.setString(3, mhs.getEmail());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Mahasiswa> ambilSemuaMahasiswa() {
        List<Mahasiswa> daftarMhs = new ArrayList<>();
        String sql = "SELECT * FROM mahasiswa ORDER BY nama ASC";
        try (Connection conn = DButil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Mahasiswa mhs = new Mahasiswa();
                mhs.setId(rs.getInt("id"));
                mhs.setNim(rs.getString("nim"));
                mhs.setNama(rs.getString("nama"));
                mhs.setEmail(rs.getString("email"));
                daftarMhs.add(mhs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return daftarMhs;
    }

    public Mahasiswa ambilMahasiswaById(int id) {
        Mahasiswa mhs = null;
        String sql = "SELECT * FROM mahasiswa WHERE id = ?";
        try (Connection conn = DButil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    mhs = new Mahasiswa();
                    mhs.setId(rs.getInt("id"));
                    mhs.setNim(rs.getString("nim"));
                    mhs.setNama(rs.getString("nama"));
                    mhs.setEmail(rs.getString("email"));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return mhs;
    }

    public void updateMahasiswa(Mahasiswa mhs) {
        String sql = "UPDATE mahasiswa SET nim = ?, nama = ?, email = ? WHERE id = ?";
        try (Connection conn = DButil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mhs.getNim());
            ps.setString(2, mhs.getNama());
            ps.setString(3, mhs.getEmail());
            ps.setInt(4, mhs.getId());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void hapusMahasiswa(int id) {
        String sql = "DELETE FROM mahasiswa WHERE id = ?";
        try (Connection conn = DButil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}