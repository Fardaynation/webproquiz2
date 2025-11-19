package com.proyekquiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Import model yang benar
import com.proyekquiz.model.MataKuliah;
import com.proyekquiz.model.FrsDetail; // <-- IMPORT BARU
import com.proyekquiz.util.DButil;

public class FrsDao {

    // CRUD (C Section)
    public void tambahFrs(int mahasiswaId, int matakuliahId){
        String sql = "INSERT INTO frs (mahasiswa_id, matakuliah_id) VALUES (?, ?)";
        try (Connection conn = DButil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, mahasiswaId);
            ps.setInt(2, matakuliahId);
            ps.executeUpdate();
            
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    // CRUD (D Section)
    public void hapusFrs(int frsId){
        String sql = "DELETE FROM frs WHERE id = ?";
        try (Connection conn = DButil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, frsId);
            ps.executeUpdate();
            
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    // CRUD (R Section, MK Taken)
    public List<FrsDetail> getMatakuliahDiambil(int mahasiswaId){
        List<FrsDetail> daftarMk = new ArrayList<>();
        
        String sql = "SELECT frs.id AS frs_id, mk.kode_mk, mk.nama_mk, mk.sks FROM mata_kuliah mk " + "JOIN frs ON mk.id = frs.matakuliah_id " +
                     "WHERE frs.mahasiswa_id = ?";
        
        try (Connection conn = DButil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, mahasiswaId);
            
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    FrsDetail detail = new FrsDetail();
                    detail.setFrsId(rs.getInt("frs_id"));
                    detail.setKodeMk(rs.getString("kode_mk"));
                    detail.setNamaMk(rs.getString("nama_mk"));
                    detail.setSks(rs.getInt("sks"));
                    daftarMk.add(detail);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return daftarMk;
    }

    // CRUD (R Section, MK not taken yet)
    public List<MataKuliah> getMatakuliahBelumDiambil(int mahasiswaId){
        List<MataKuliah> daftarMk = new ArrayList<>();
        
        String sql = "SELECT * FROM mata_kuliah WHERE id NOT IN " + "(SELECT matakuliah_id FROM frs WHERE mahasiswa_id = ?)";
        
        try (Connection conn = DButil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, mahasiswaId);
            
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    MataKuliah mk = new MataKuliah();
                    mk.setId(rs.getInt("id"));
                    mk.setKodeMk(rs.getString("kode_mk"));
                    mk.setNamaMk(rs.getString("nama_mk"));
                    mk.setSks(rs.getInt("sks"));
                    daftarMk.add(mk);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return daftarMk;
    }
}