<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulir Rencana Studi (FRS)</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h2>FRS untuk: <c:out value="${mahasiswa.nama}" /></h2>
        <p>NIM: <c:out value="${mahasiswa.nim}" /></p>
        <hr>
        <h3>Tambahkan Mata Kuliah</h3>
        <form action="FrsServlet" method="post">
            <input type="hidden" name="action" value="tambah">
            <input type="hidden" name="mahasiswa_id" value="${mahasiswa.id}">
            <div class="form-group">
                <label for="matakuliah_id">Mata Kuliah (yang belum diambil):</label>
                <select name="matakuliah_id" id="matakuliah_id" class="form-control">
                    <c:forEach var="mk" items="${mkBelumDiambil}">
                        <option value="${mk.id}">
                            <c:out value="${mk.kodeMk}" /> - <c:out value="${mk.namaMk}" />
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Tambahkan ke FRS</button>
            </div>
        </form>
        <hr>
        <h3>Mata Kuliah yang Sudah Diambil</h3>
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
                <c:forEach var="detail" items="${mkDiambil}">
                    <tr>
                        <td><c:out value="${detail.kodeMk}" /></td>
                        <td><c:out value="${detail.namaMk}" /></td>
                        <td><c:out value="${detail.sks}" /></td>
                        <td class="action-links">
                            <a href="FrsServlet?action=hapus&frs_id=${detail.frsId}&mahasiswa_id=${mahasiswa.id}"
                               class="btn btn-danger"
                               onclick="return confirm('Yakin ingin menghapus mata kuliah ini dari FRS?')">Hapus</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>
        <a href="MahasiswaServlet?action=daftar" class="btn btn-secondary">Kembali ke Daftar Mahasiswa</a>
    </div>
</body>
</html>