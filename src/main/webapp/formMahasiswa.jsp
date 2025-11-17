<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 
  PENTING: Kita gunakan taglib 'jakarta.tags.core' yang benar
  untuk Tomcat 10.1
--%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Form Mahasiswa</title>
    
    <link rel="stylesheet" href="style.css">
    
</head>
<body>

    <div class="container">

        <h2>
            <%-- 
              Logika JSTL:
              Jika Servlet mengirim data 'mhs' (mode edit),
              tampilkan "Edit Mahasiswa".
            --%>
            <c:if test="${mhs != null}">
                Edit Mahasiswa
            </c:if>
            <%-- 
              Jika Servlet TIDAK mengirim data 'mhs' (mode tambah),
              tampilkan "Tambah Mahasiswa".
            --%>
            <c:if test="${mhs == null}">
                Tambah Mahasiswa
            </c:if>
        </h2>

        <%-- 
        Form ini akan mengirim data ke 'MahasiswaServlet' menggunakan metode 'post'
        --%>
        <form action="MahasiswaServlet" method="post">
            
            <%-- Logika untuk 'action' dan 'id' --%>
            <c:if test="${mhs != null}">
                <input type="hidden" name="action" value="update" />
                <input type="hidden" name="id" value="<c:out value='${mhs.id}' />" />
            </c:if>
            <c:if test="${mhs == null}">
                <input type="hidden" name="action" value="simpan" />
            </c:if>

            <div class="form-group">
                <label for="nim">NIM:</label>
                <input type="text" id="nim" name="nim" class="form-control" 
                       value="<c:out value='${mhs.nim}' />" required>
            </div>
            
            <div class="form-group">
                <label for="nama">Nama:</label>
                <input type="text" id="nama" name="nama" class="form-control" 
                       value="<c:out value='${mhs.nama}' />" required>
            </div>
            
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" id="email" name="email" class="form-control" 
                       value="<c:out value='${mhs.email}' />">
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Simpan</button>
                <a href="MahasiswaServlet?action=daftar" class="btn btn-secondary">Batal</a>
            </div>
            
        </form>
        
    </div> </body>
</html>