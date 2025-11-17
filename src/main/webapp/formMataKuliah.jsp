<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Form Mata Kuliah</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">

        <h2>
            <c:if test="${mk != null}">
                Edit Mata Kuliah
            </c:if>
            <c:if test="${mk == null}">
                Tambah Mata Kuliah
            </c:if>
        </h2>

        <form action="MataKuliahServlet" method="post">
            
            <c:if test="${mk != null}">
                <input type="hidden" name="action" value="update" />
                <input type="hidden" name="id" value="<c:out value='${mk.id}' />" />
            </c:if>
            <c:if test="${mk == null}">
                <input type="hidden" name="action" value="simpan" />
            </c:if>

            <div class="form-group">
                <label for="kodeMk">Kode MK:</label>
                <input type="text" id="kodeMk" name="kodeMk" class="form-control" 
                       value="<c:out value='${mk.kodeMk}' />" required>
            </div>
            
            <div class="form-group">
                <label for="namaMk">Nama Mata Kuliah:</label>
                <input type="text" id="namaMk" name="namaMk" class="form-control" 
                       value="<c:out value='${mk.namaMk}' />" required>
            </div>
            
            <div class="form-group">
                <label for="sks">SKS:</label>
                <input type="number" id="sks" name="sks" class="form-control" 
                       value="<c:out value='${mk.sks}' />" required>
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Simpan</button>
                <a href="MataKuliahServlet?action=daftar" class="btn btn-secondary">Batal</a>
            </div>
            
        </form>
        
    </div>
</body>
</html>