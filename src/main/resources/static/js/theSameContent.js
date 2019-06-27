
function checkContent() {
    $(document).on('change', '.diffrentDisease', function(e) {
        var tralse = true;
        var selectRound_arr = []; // for contestant name
        $('.diffrentDisease').each(function(k, v) {
            var getVal = $(v).val();
            //alert(getVal);
            if (getVal && $.trim(selectRound_arr.indexOf(getVal)) != -1) {
                tralse = false;
                //it should be if value 1 = value 1 then alert, and not those if -select- = -select-. how to avoid those -select-
                alert('Disease can not be the same!');
                $(v).val("");
                return false;
            } else {
                selectRound_arr.push($(v).val());
            }
        });
        if (!tralse) {
            return false;
        }
    });
}

function checkMedicine() {
    $(document).on('change', '.diffrentMedicine', function(e) {
        var tralse = true;
        var selectRound_arr = []; // for contestant name
        $('.diffrentMedicine').each(function(k, v) {
            var getVal = $(v).val();
            //alert(getVal);
            if (getVal && $.trim(selectRound_arr.indexOf(getVal)) != -1) {
                tralse = false;
                //it should be if value 1 = value 1 then alert, and not those if -select- = -select-. how to avoid those -select-
                alert('Medicine can not be the same!');
                $(v).val("");
                return false;
            } else {
                selectRound_arr.push($(v).val());
            }
        });
        if (!tralse) {
            return false;
        }
    });
}