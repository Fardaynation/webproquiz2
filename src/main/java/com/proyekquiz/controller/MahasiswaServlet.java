package com.proyekquiz.controller;

import java.io.IOException;
import java.util.List;

import com.proyekquiz.dao.MahasiswaDao;
import com.proyekquiz.model.Mahasiswa;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Anotasi ini mendaftarkan Servlet ke URL /MahasiswaServlet
@WebServlet("/MahasiswaServlet")
public class MahasiswaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Kita buat variabel untuk DAO di sini
    private MahasiswaDao mhsDao;

    // init() adalah metode yang dipanggil Tomcat saat servlet pertama kali dimuat
    public void init() {
        mhsDao = new MahasiswaDao(); // Inisialisasi DAO
    }

    // doGet() menangani permintaan GET (mengambil data, menampilkan form, menghapus)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Baca parameter 'action' dari URL
        String action = request.getParameter("action");
        if (action == null) {
            action = "daftar"; // Aksi default jika tidak ada parameter
        }

        try {
            // Panggil metode yang sesuai berdasarkan 'action'
            switch (action) {
                case "tambah":
                    tampilkanFormTambah(request, response);
                    break;
                case "edit":
                    tampilkanFormEdit(request, response);
                    break;
                case "hapus":
                    hapusMahasiswa(request, response);
                    break;
                case "daftar":
                default:
                    daftarMahasiswa(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // doPost() menangani permintaan POST (mengirim data dari form)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Baca parameter 'action' dari form
        String action = request.getParameter("action");

        try {
            if (action.equals("simpan")) {
                simpanMahasiswa(request, response);
            } else if (action.equals("update")) {
                updateMahasiswa(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // --- Kumpulan Metode private untuk setiap aksi ---

    // AKSI: Menampilkan semua data
    private void daftarMahasiswa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Mahasiswa> daftarMhs = mhsDao.ambilSemuaMahasiswa();
        request.setAttribute("daftarMhs", daftarMhs); // Mengirim data ke JSP
        
        // Meneruskan request ke file JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("daftarMahasiswa.jsp");
        dispatcher.forward(request, response);
    }

    // AKSI: Menampilkan form kosong
    private void tampilkanFormTambah(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("formMahasiswa.jsp");
        dispatcher.forward(request, response);
    }

    // AKSI: Menampilkan form berisi data yang mau di-edit
    private void tampilkanFormEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Mahasiswa mhs = mhsDao.ambilMahasiswaById(id);
        request.setAttribute("mhs", mhs); // Kirim data mhs yg akan diedit ke JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("formMahasiswa.jsp");
        dispatcher.forward(request, response);
    }

    // AKSI: Menyimpan data baru (dari POST)
    private void simpanMahasiswa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Mahasiswa mhs = new Mahasiswa();
        // Ambil data dari form
        mhs.setNim(request.getParameter("nim"));
        mhs.setNama(request.getParameter("nama"));
        mhs.setEmail(request.getParameter("email"));
        
        mhsDao.tambahMahasiswa(mhs);
        
        // Setelah simpan, redirect kembali ke halaman daftar
        response.sendRedirect("MahasiswaServlet?action=daftar");
    }

    // AKSI: Update data (dari POST)
    private void updateMahasiswa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Mahasiswa mhs = new Mahasiswa();
        // Ambil data dari form, termasuk ID
        mhs.setId(Integer.parseInt(request.getParameter("id")));
        mhs.setNim(request.getParameter("nim"));
        mhs.setNama(request.getParameter("nama"));
        mhs.setEmail(request.getParameter("email"));
        
        mhsDao.updateMahasiswa(mhs);
        
        // Setelah update, redirect kembali ke halaman daftar
        response.sendRedirect("MahasiswaServlet?action=daftar");
    }

    // AKSI: Hapus data (dari GET)
    private void hapusMahasiswa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        mhsDao.hapusMahasiswa(id);
        
        // Setelah hapus, redirect kembali ke halaman daftar
        response.sendRedirect("MahasiswaServlet?action=daftar");
    }
}