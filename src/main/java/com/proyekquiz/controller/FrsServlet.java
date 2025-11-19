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
    private MahasiswaDao mahasiswaDao;
    
    public void init(){
        frsDao = new FrsDao();
        mahasiswaDao = new MahasiswaDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "tampil";
        }

        try {
            switch (action){
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
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("tambah".equals(action)){
                tambahKeFrs(request, response);
            }
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }


    private void tampilkanFrs(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int mahasiswaId = Integer.parseInt(request.getParameter("mahasiswa_id"));

        Mahasiswa mahasiswa = mahasiswaDao.ambilMahasiswaById(mahasiswaId);
        
        List<FrsDetail> mkDiambil = frsDao.getMatakuliahDiambil(mahasiswaId);
        
        List<MataKuliah> mkBelumDiambil = frsDao.getMatakuliahBelumDiambil(mahasiswaId);

        request.setAttribute("mahasiswa", mahasiswa);
        request.setAttribute("mkDiambil", mkDiambil);
        request.setAttribute("mkBelumDiambil", mkBelumDiambil);

        RequestDispatcher dispatcher = request.getRequestDispatcher("frs.jsp");
        dispatcher.forward(request, response);
    }

    private void tambahKeFrs(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        int mahasiswaId = Integer.parseInt(request.getParameter("mahasiswa_id"));
        int matakuliahId = Integer.parseInt(request.getParameter("matakuliah_id"));

        frsDao.tambahFrs(mahasiswaId, matakuliahId);

        response.sendRedirect("FrsServlet?action=tampil&mahasiswa_id=" + mahasiswaId);
    }

    private void hapusDariFrs(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        int frsId = Integer.parseInt(request.getParameter("frs_id"));
        int mahasiswaId = Integer.parseInt(request.getParameter("mahasiswa_id"));

        frsDao.hapusFrs(frsId);

        response.sendRedirect("FrsServlet?action=tampil&mahasiswa_id=" + mahasiswaId);
    }
}
