const url = "/ktp";

window.currentId = null;


function loadData() {

    fetch(url)
        .then(res => res.json())
        .then(data => {

            let table = document.getElementById("table");

            table.innerHTML =
                "<tr>" +
                "<th>ID</th>" +
                "<th>Nomor</th>" +
                "<th>Nama</th>" +
                "<th>Alamat</th>" +
                "<th>Tanggal</th>" +
                "<th>Gender</th>" +
                "<th>Aksi</th>" +
                "</tr>";

            data.forEach(d => {

                table.innerHTML +=
                    "<tr>" +
                    "<td>" + d.id + "</td>" +
                    "<td>" + d.nomorKtp + "</td>" +
                    "<td>" + d.namaLengkap + "</td>" +
                    "<td>" + d.alamat + "</td>" +
                    "<td>" + d.tanggalLahir + "</td>" +
                    "<td>" + d.jenisKelamin + "</td>" +
                    "<td>" +
                    "<button onclick='edit(" + d.id + ")'>Edit</button> " +
                    "<button onclick='hapus(" + d.id + ")'>Hapus</button>" +
                    "</td>" +
                    "</tr>";
            });

        });
}



function tambah() {

    let data = {

        nomorKtp: document.getElementById("nomorKtp").value,
        namaLengkap: document.getElementById("namaLengkap").value,
        alamat: document.getElementById("alamat").value,
        tanggalLahir: document.getElementById("tanggalLahir").value,
        jenisKelamin: document.getElementById("jenisKelamin").value
    };

    let method = "POST";
    let requestUrl = url;

    if (window.currentId != null) {

        method = "PUT";
        requestUrl = url + "/" + window.currentId;

    }

    fetch(requestUrl, {

        method: method,
        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(data)

    })
        .then(() => {

            window.currentId = null;

            document.getElementById("formKtp").reset();

            loadData();

        });
}



function hapus(id) {

    fetch(url + "/" + id, {

        method: "DELETE"

    })
        .then(() => loadData());
}



function edit(id) {

    fetch(url + "/" + id)
        .then(res => res.json())
        .then(d => {

            document.getElementById("nomorKtp").value = d.nomorKtp;
            document.getElementById("namaLengkap").value = d.namaLengkap;
            document.getElementById("alamat").value = d.alamat;
            document.getElementById("tanggalLahir").value = d.tanggalLahir;
            document.getElementById("jenisKelamin").value = d.jenisKelamin;

            window.currentId = id;

        });
}


loadData();