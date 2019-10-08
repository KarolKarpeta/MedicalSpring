function replaceTableMedicine(html) {
    // Replace the <fieldset id="medicineTable"> with a new one returned by server.
    $('#medicineTableF').replaceWith($(html));

    // Material Select Initialization
    $('.selectMedicine').materialSelect();
}
function addRowMedicine() {
        // e.preventDefault();
        var data = $('form').serialize();
        // Add parameter "addItem" to POSTed form data. Button's name and value is
        // POSTed only when clicked. Since "event.preventDefault();" prevents from
        // actual clicking the button, following line will add parameter to form
        // data.
        data += '&addMedicineRow=addMedicineRow';
        $.post('/patients/addNewPatient', data, replaceTableMedicine);
    }

function dellRowMedicine(e) {
        // event.preventDefault();
        var data = $('form').serialize();
        // Add parameter and index of item that is going to be removed.
        data += '&removeMedicineRow=' + e.target.value;
        $.post('/patients/addNewPatient', data, replaceTableMedicine);
    }


function replaceTableDisease(html) {
    // Replace the <fieldset id="medicineTable"> with a new one returned by server.
    $('#diseaseTableF').replaceWith($(html));

    // Material Select Initialization
    $('.selectDisease').materialSelect();
}


function addRowDisease() {
    // e.preventDefault();
    var data = $('form').serialize();
    // Add parameter "addItem" to POSTed form data. Button's name and value is
    // POSTed only when clicked. Since "event.preventDefault();" prevents from
    // actual clicking the button, following line will add parameter to form
    // data.
    data += '&addDiseaseRow=addDiseaseRow';
    $.post('/patients/addNewPatient', data, replaceTableDisease);
}

function dellRowDisease(e) {
    // event.preventDefault();
    var data = $('form').serialize();
    // Add parameter and index of item that is going to be removed.
    data += '&removeDiseaseRow=' + e.target.value;
    $.post('/patients/addNewPatient', data, replaceTableDisease);
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


