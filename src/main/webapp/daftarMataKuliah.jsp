<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Daftar Mata Kuliah</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h2>Manajemen Data Mata Kuliah</h2>
        <div class="add-link">
            <a href="MataKuliahServlet?action=tambah" class="btn btn-primary">Tambah Mata Kuliah Baru</a>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Kode MK</th>
                    <th>Nama Mata Kuliah</th>
                    <th>SKS</th>
                    <th>Aksi</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="mk" items="${daftarMk}">
                    <tr>
                        <td><c:out value="${mk.kodeMk}" /></td>
                        <td><c:out value="${mk.namaMk}" /></td>
                        <td><c:out value="${mk.sks}" /></td>
                        <td class="action-links">
                            <a href="MataKuliahServlet?action=edit&id=${mk.id}" 
                               class="btn btn-primary">Edit</a>
                            <a href="MataKuliahServlet?action=hapus&id=${mk.id}" 
                               class="btn btn-danger"
                               onclick="return confirm('Yakin ingin menghapus data ini?')">Hapus</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>