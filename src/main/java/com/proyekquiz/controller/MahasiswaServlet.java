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

@WebServlet("/MahasiswaServlet")
public class MahasiswaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MahasiswaDao mhsDao;
    
    public void init(){
        mhsDao = new MahasiswaDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action == null){
            action = "daftar";
        }

        try {
            switch (action){
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
        }
        catch (Exception e){
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");

        try {
            if (action.equals("simpan")){
                simpanMahasiswa(request, response);
            }
            else if (action.equals("update")){
                updateMahasiswa(request, response);
            }
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void daftarMahasiswa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Mahasiswa> daftarMhs = mhsDao.ambilSemuaMahasiswa();
        request.setAttribute("daftarMhs", daftarMhs);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("daftarMahasiswa.jsp");
        dispatcher.forward(request, response);
    }

    private void tampilkanFormTambah(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("formMahasiswa.jsp");
        dispatcher.forward(request, response);
    }

    private void tampilkanFormEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Mahasiswa mhs = mhsDao.ambilMahasiswaById(id);
        request.setAttribute("mhs", mhs);
        RequestDispatcher dispatcher = request.getRequestDispatcher("formMahasiswa.jsp");
        dispatcher.forward(request, response);
    }

    private void simpanMahasiswa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Mahasiswa mhs = new Mahasiswa();
        mhs.setNim(request.getParameter("nim"));
        mhs.setNama(request.getParameter("nama"));
        mhs.setEmail(request.getParameter("email"));
        
        mhsDao.tambahMahasiswa(mhs);
        
        response.sendRedirect("MahasiswaServlet?action=daftar");
    }

    private void updateMahasiswa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Mahasiswa mhs = new Mahasiswa();
        mhs.setId(Integer.parseInt(request.getParameter("id")));
        mhs.setNim(request.getParameter("nim"));
        mhs.setNama(request.getParameter("nama"));
        mhs.setEmail(request.getParameter("email"));
        
        mhsDao.updateMahasiswa(mhs);
        
        response.sendRedirect("MahasiswaServlet?action=daftar");
    }

    private void hapusMahasiswa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        mhsDao.hapusMahasiswa(id);
        
        response.sendRedirect("MahasiswaServlet?action=daftar");
    }
}