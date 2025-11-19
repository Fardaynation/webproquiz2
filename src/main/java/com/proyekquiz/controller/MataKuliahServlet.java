package com.proyekquiz.controller;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.proyekquiz.dao.MataKuliahDao;
import com.proyekquiz.model.MataKuliah;

@WebServlet("/MataKuliahServlet")
public class MataKuliahServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MataKuliahDao mkDao;

    public void init(){
        mkDao = new MataKuliahDao();
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
                    hapusMataKuliah(request, response);
                    break;
                case "daftar":
                default:
                    daftarMataKuliah(request, response);
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
                simpanMataKuliah(request, response);
            }
            else if (action.equals("update")){
                updateMataKuliah(request, response);
            }
        }
        catch (Exception e){
            throw new ServletException(e);
        }
    }


    private void daftarMataKuliah(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<MataKuliah> daftarMk = mkDao.ambilSemuaMataKuliah();
        request.setAttribute("daftarMk", daftarMk);
        

        RequestDispatcher dispatcher = request.getRequestDispatcher("daftarMataKuliah.jsp"); 
        dispatcher.forward(request, response);
    }

    private void tampilkanFormTambah(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("formMataKuliah.jsp"); 
        dispatcher.forward(request, response);
    }

    private void tampilkanFormEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        MataKuliah mk = mkDao.ambilMataKuliahById(id);
        request.setAttribute("mk", mk);

        RequestDispatcher dispatcher = request.getRequestDispatcher("formMataKuliah.jsp"); 
        dispatcher.forward(request, response);
    }

    private void simpanMataKuliah(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MataKuliah mk = new MataKuliah();

        mk.setKodeMk(request.getParameter("kodeMk"));
        mk.setNamaMk(request.getParameter("namaMk"));
        mk.setSks(Integer.parseInt(request.getParameter("sks")));
        
        mkDao.tambahMataKuliah(mk);
        
        response.sendRedirect("MataKuliahServlet?action=daftar"); 
    }

    private void updateMataKuliah(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MataKuliah mk = new MataKuliah();
        mk.setId(Integer.parseInt(request.getParameter("id")));
        mk.setKodeMk(request.getParameter("kodeMk"));
        mk.setNamaMk(request.getParameter("namaMk"));
        mk.setSks(Integer.parseInt(request.getParameter("sks")));
        
        mkDao.updateMataKuliah(mk);
        
        response.sendRedirect("MataKuliahServlet?action=daftar"); 
    }

    private void hapusMataKuliah(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        mkDao.hapusMataKuliah(id);
        
        response.sendRedirect("MataKuliahServlet?action=daftar"); 
    }
}