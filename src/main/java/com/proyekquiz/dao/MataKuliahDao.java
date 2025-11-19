package com.proyekquiz.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.proyekquiz.model.MataKuliah;
import com.proyekquiz.util.DButil;

public class MataKuliahDao {

    // CRUD (C section)
    public void tambahMataKuliah(MataKuliah mk){
        String sql = "INSERT INTO mata_kuliah (kode_mk, nama_mk, sks) VALUES (?, ?, ?)";
        try (Connection conn = DButil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, mk.getKodeMk());
            ps.setString(2, mk.getNamaMk());
            ps.setInt(3, mk.getSks());
            ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    // CRUD (R section)
    public List<MataKuliah> ambilSemuaMataKuliah(){
        List<MataKuliah> daftarMk = new ArrayList<>();
        String sql = "SELECT * FROM mata_kuliah ORDER BY nama_mk ASC";
        
        try (Connection conn = DButil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            
            while (rs.next()){
                MataKuliah mk = new MataKuliah();
                mk.setId(rs.getInt("id"));
                mk.setKodeMk(rs.getString("kode_mk"));
                mk.setNamaMk(rs.getString("nama_mk"));
                mk.setSks(rs.getInt("sks"));
                daftarMk.add(mk);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return daftarMk;
    }

    // CRUD (R section by ID, untuk form Edit)
    public MataKuliah ambilMataKuliahById(int id){
        MataKuliah mk = null;
        String sql = "SELECT * FROM mata_kuliah WHERE id = ?";
        
        try (Connection conn = DButil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    mk = new MataKuliah();
                    mk.setId(rs.getInt("id"));
                    mk.setKodeMk(rs.getString("kode_mk"));
                    mk.setNamaMk(rs.getString("nama_mk"));
                    mk.setSks(rs.getInt("sks"));
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return mk;
    }

    // CRUD (U section)
    public void updateMataKuliah(MataKuliah mk){
        String sql = "UPDATE mata_kuliah SET kode_mk = ?, nama_mk = ?, sks = ? WHERE id = ?";
        try (Connection conn = DButil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, mk.getKodeMk());
            ps.setString(2, mk.getNamaMk());
            ps.setInt(3, mk.getSks());
            ps.setInt(4, mk.getId());
            ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    // CRUD (D section)
    public void hapusMataKuliah(int id){
        String sql = "DELETE FROM mata_kuliah WHERE id = ?";
        try (Connection conn = DButil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}