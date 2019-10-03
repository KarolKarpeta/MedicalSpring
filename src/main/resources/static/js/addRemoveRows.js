function replaceTable(html) {
    // Replace the <fieldset id="medicineTable"> with a new one returned by server.
    $('#medicineTableF').replaceWith($(html));
}


function addRowMedicine() {
        // e.preventDefault();
        var data = $('form').serialize();
        // Add parameter "addItem" to POSTed form data. Button's name and value is
        // POSTed only when clicked. Since "event.preventDefault();" prevents from
        // actual clicking the button, following line will add parameter to form
        // data.
        data += '&addRow=addRow';
        $.post('/patients/addNewPatient', data, replaceTable);
    }

function dellRowMedicine(e) {
        // event.preventDefault();
        var data = $('form').serialize();
        // Add parameter and index of item that is going to be removed.
        data += '&removeRow=' + e.target.value;
        $.post('/patients/addNewPatient', data, replaceTable);
    }


// function addRowMedicine() {
//     $('button[name="addRow"]').click(function () {
//         // event.preventDefault();
//         var data = $('form').serialize();
//         // Add parameter "addItem" to POSTed form data. Button's name and value is
//         // POSTed only when clicked. Since "event.preventDefault();" prevents from
//         // actual clicking the button, following line will add parameter to form
//         // data.
//         data += 'addItem';
//         alert("UUuu");
//         // $.post('/patients/addNewPatient', data, replaceTable);
//     });
//
// }


