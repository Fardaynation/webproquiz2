<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Daftar Mahasiswa</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h2>Manajemen Data Mahasiswa</h2>
        <div class="add-link">
            <a href="MahasiswaServlet?action=tambah" class="btn btn-primary">Tambah Mahasiswa Baru</a>
        </div>
        <table>
            <thead>
                <tr>
                    <th>NIM</th>
                    <th>Nama</th>
                    <th>Email</th>
                    <th>Aksi</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="mhs" items="${daftarMhs}">
                    <tr>
                        <td><c:out value="${mhs.nim}" /></td>
                        <td><c:out value="${mhs.nama}" /></td>
                        <td><c:out value="${mhs.email}" /></td>
                        <td class="action-links">
                            <a href="MahasiswaServlet?action=edit&id=${mhs.id}" 
                               class="btn btn-primary">Edit</a>
                            <a href="MahasiswaServlet?action=hapus&id=${mhs.id}" 
                               class="btn btn-danger"
                               onclick="return confirm('Yakin ingin menghapus data ini?')">Hapus</a>
                            <a href="FrsServlet?action=tampil&mahasiswa_id=${mhs.id}" 
                               class="btn btn-success">FRS</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>