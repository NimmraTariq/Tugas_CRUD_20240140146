const API_URL = '/api/citizen';
let editingId = null;

document.addEventListener('DOMContentLoaded', () => {
    loadData();
    setupForm();
});

function setupForm() {
    document.getElementById('formData').addEventListener('submit', handleSubmit);
}

async function loadData() {
    try {
        const response = await axios.get(API_URL);
        if (response.data.success) {
            renderTable(response.data.data);
            updateStats(response.data.data);
        }
    } catch (error) {
        showToast('Gagal memuat data', 'error');
        console.error(error);
    }
}

function renderTable(data) {
    const tbody = document.getElementById('tableBody');

    if (!data || data.length === 0) {
        tbody.innerHTML = `
            <tr>
                <td colspan="7" class="empty-state">
                    <svg viewBox="0 0 24 24" fill="currentColor">
                        <path d="M19 3H5c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2zm-5 14H7v-2h7v2zm3-4H7v-2h10v2zm0-4H7V7h10v2z"/>
                    </svg>
                    <p>Belum ada data penduduk</p>
                </td>
            </tr>
        `;
        return;
    }

    tbody.innerHTML = data.map((item, index) => `
        <tr>
            <td>${index + 1}</td>
            <td>${escapeHtml(item.nik)}</td>
            <td>${escapeHtml(item.nama)}</td>
            <td>${escapeHtml(item.alamat || '-')}</td>
            <td>${formatDate(item.tglLahir)}</td>
            <td>${item.jenisKelamin === 'L' ? 'Laki-laki' : 'Perempuan'}</td>
            <td class="action-cell">
                <button class="btn btn-edit" onclick="editData(${item.id})">
                    Edit
                </button>
                <button class="btn btn-delete" onclick="deleteData(${item.id})">
                    Hapus
                </button>
            </td>
        </tr>
    `).join('');
}

function updateStats(data) {
    document.getElementById('totalData').textContent = data ? data.length : 0;
    document.getElementById('totalLaki').textContent = data ? data.filter(d => d.jenisKelamin === 'L').length : 0;
    document.getElementById('totalPerempuan').textContent = data ? data.filter(d => d.jenisKelamin === 'P').length : 0;
}

async function handleSubmit(e) {
    e.preventDefault();

    const formData = {
        nik: document.getElementById('nik').value.trim(),
        nama: document.getElementById('nama').value.trim(),
        alamat: document.getElementById('alamat').value.trim(),
        tglLahir: document.getElementById('tglLahir').value,
        jenisKelamin: document.getElementById('jenisKelamin').value
    };

    if (!formData.nik || !formData.nama || !formData.alamat || !formData.tglLahir || !formData.jenisKelamin) {
        showToast('Mohon isi semua field', 'error');
        return;
    }

    try {
        let response;
        if (editingId) {
            response = await axios.put(`${API_URL}/${editingId}`, formData);
            showToast('Data berhasil diperbarui', 'success');
        } else {
            response = await axios.post(API_URL, formData);
            showToast('Data berhasil disimpan', 'success');
        }

        if (response.data.success) {
            resetForm();
            loadData();
        }
    } catch (error) {
        const message = error.response?.data?.message || 'Terjadi kesalahan';
        showToast(message, 'error');
    }
}

async function editData(id) {
    try {
        const response = await axios.get(`${API_URL}/${id}`);
        if (response.data.success) {
            const data = response.data.data;

            document.getElementById('editId').value = id;
            document.getElementById('nik').value = data.nik;
            document.getElementById('nama').value = data.nama;
            document.getElementById('alamat').value = data.alamat || '';
            document.getElementById('tglLahir').value = data.tglLahir;
            document.getElementById('jenisKelamin').value = data.jenisKelamin;

            editingId = id;
            document.getElementById('formTitle').textContent = 'Edit Data Penduduk';
            document.getElementById('btnText').textContent = 'Update Data';
            document.getElementById('btnIcon').textContent = 'U';

            window.scrollTo({ top: 0, behavior: 'smooth' });
        }
    } catch (error) {
        showToast('Gagal mengambil data', 'error');
    }
}

async function deleteData(id) {
    if (!confirm('Apakah Anda yakin ingin menghapus data ini?')) {
        return;
    }

    try {
        const response = await axios.delete(`${API_URL}/${id}`);
        if (response.data.success) {
            showToast('Data berhasil dihapus', 'success');
            loadData();
        }
    } catch (error) {
        const message = error.response?.data?.message || 'Gagal menghapus data';
        showToast(message, 'error');
    }
}

function resetForm() {
    document.getElementById('formData').reset();
    document.getElementById('editId').value = '';
    editingId = null;
    document.getElementById('formTitle').textContent = 'Tambah Data Penduduk';
    document.getElementById('btnText').textContent = 'Simpan Data';
    document.getElementById('btnIcon').textContent = '+';
}

function showToast(message, type) {
    const toast = document.getElementById('toast');
    toast.textContent = message;
    toast.className = `toast ${type}`;
    toast.style.display = 'block';

    setTimeout(() => {
        toast.style.display = 'none';
    }, 3000);
}

function formatDate(dateString) {
    if (!dateString) return '-';
    const date = new Date(dateString);
    const options = { day: 'numeric', month: 'long', year: 'numeric' };
    return date.toLocaleDateString('id-ID', options);
}

function escapeHtml(text) {
    if (!text) return '';
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}
