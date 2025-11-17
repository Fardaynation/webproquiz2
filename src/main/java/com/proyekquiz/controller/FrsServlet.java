package com.proyekquiz.controller;

import java.io.IOException;
import java.util.List;

import com.proyekquiz.dao.FrsDao;
import com.proyekquiz.dao.MahasiswaDao; // Kita perlu ini
import com.proyekquiz.model.FrsDetail;
import com.proyekquiz.model.Mahasiswa;
import com.proyekquiz.model.MataKuliah;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/FrsServlet")
public class FrsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private FrsDao frsDao;
    private MahasiswaDao mahasiswaDao; // Untuk mengambil data 1 mahasiswa
    
    public void init() {
        frsDao = new FrsDao();
        mahasiswaDao = new MahasiswaDao(); // Inisialisasi MahasiswaDao
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "tampil"; // Default action
        }

        try {
            switch (action) {
                case "tambah":
                    break;
                case "hapus":
                    hapusDariFrs(request, response);
                    break;
                case "tampil":
                default:
                    tampilkanFrs(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("tambah".equals(action)) {
                tambahKeFrs(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }


    private void tampilkanFrs(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // 1. Ambil ID Mahasiswa dari URL
        int mahasiswaId = Integer.parseInt(request.getParameter("mahasiswa_id"));

        // 2. Ambil data Mahasiswa (untuk menampilkan nama)
        Mahasiswa mahasiswa = mahasiswaDao.ambilMahasiswaById(mahasiswaId);
        
        // 3. Ambil daftar MK yang SUDAH diambil (untuk tabel)
        List<FrsDetail> mkDiambil = frsDao.getMatakuliahDiambil(mahasiswaId);
        
        // 4. Ambil daftar MK yang BELUM diambil (untuk dropdown)
        List<MataKuliah> mkBelumDiambil = frsDao.getMatakuliahBelumDiambil(mahasiswaId);

        // 5. Kirim semua data ini ke JSP
        request.setAttribute("mahasiswa", mahasiswa);
        request.setAttribute("mkDiambil", mkDiambil);
        request.setAttribute("mkBelumDiambil", mkBelumDiambil);

        // 6. Tampilkan halaman JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("frs.jsp");
        dispatcher.forward(request, response);
    }

    private void tambahKeFrs(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        // Ambil ID dari form yang di-submit
        int mahasiswaId = Integer.parseInt(request.getParameter("mahasiswa_id"));
        int matakuliahId = Integer.parseInt(request.getParameter("matakuliah_id"));

        // Panggil DAO untuk INSERT ke tabel 'frs'
        frsDao.tambahFrs(mahasiswaId, matakuliahId);

        // Redirect kembali ke halaman FRS untuk mahasiswa yang sama
        response.sendRedirect("FrsServlet?action=tampil&mahasiswa_id=" + mahasiswaId);
    }

    private void hapusDariFrs(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        // Ambil ID FRS dan ID Mahasiswa dari URL
        int frsId = Integer.parseInt(request.getParameter("frs_id"));
        int mahasiswaId = Integer.parseInt(request.getParameter("mahasiswa_id"));

        // Panggil DAO untuk DELETE dari tabel 'frs'
        frsDao.hapusFrs(frsId);

        // Redirect kembali ke halaman FRS untuk mahasiswa yang sama
        response.sendRedirect("FrsServlet?action=tampil&mahasiswa_id=" + mahasiswaId);
    }
}
