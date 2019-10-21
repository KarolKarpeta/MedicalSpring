
$(document).ready(function () {
    // $("#dtBasicExample_filter").addClass("hidden");
    // $('.dataTables_length').addClass('bs-select');
    $('#dtBasicExample').DataTable({
        // "bFilter": true,
        searchHighlight: true,
        paging: false,
        scrollY: "70vh",
        fixedHeader: {
            header: true,
            footer: true
        },

    });

    $("#searchPatient").on("input", function (e) {
        e.preventDefault();
        $('#dtBasicExample').DataTable().search($(this).val()).draw();
    });

});


