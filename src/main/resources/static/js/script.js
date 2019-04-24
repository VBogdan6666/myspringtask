$(document).ready(loadPage());

function addCarModel() {
    var json = {};
    var brand = {};
    json.name = $("#name").val();
    json.brand = brand;
    json.brand.id = $("#brand").val();
    json.brand.name = $("#brand option:selected").text();

    $.ajax({
        type: 'POST',
        url: '/rest/add-car-model/',
        //dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(json),
        error: function (jqXHR, exception) {
            console.log(exception);
            loadPage();
        },
        success: function (){
            loadPage();
        }
    });
}

function delCarModel(id) {

    $.ajax({
        type: 'DELETE',
        url: '/rest/del-car-model/' + id,
        success: function () {
            loadPage();
        }
    });
}

function loadPage() {
    $.ajax({
        type: 'GET',
        url: "/rest/index-data",
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
            var cars = data.cars;
            var brands = data.brands;
            var rows = '';
            var brandOption = '';
            brands.forEach(function (obj) {
                brandOption += '<option value="' + obj.id + '">' + obj.name + '</option>'
            });
            cars.forEach(function (obj) {
                rows += '<tr>' +
                    '<td>' + obj.name + '</td>' +
                    '<td>' + obj.brand.name + '</td>' +
                    '<td>' + '<button type="button" class="btn btn-sm" id="qwer" data-toggle="modal"' +
                    ' data-target="#editCarModelModal" onclick="editCarModelData(\''+obj.name+'\',\''+obj.brand.id+'\',\''+obj.id+'\')">edit</button>' + '</td>' +
                    '<td>' + '<button class="btn btn-sm btn-danger" type="submit" onclick="delCarModel(' + obj.id + ')">&times;</button>' + '</td>' +
                    '</tr>';
            });
            rows += '<tr>' +
                '<td><input type="text" maxlength="75" class="form-control" name="name" id="name"></td>' +
                '<td><select class="form-control" id="brand">';
            rows += brandOption;
            rows += '</select>' +
                '</td>' +
                '<td>' +
                '<button class="btn btn-sm btn-success" type="button" onclick="addCarModel()">Add Car</button>' +
                '</td>' +
                '</tr>';

            $('#tbodyCarModel').html(rows);
            $('#editBrand').html(brandOption);
        }
    });
}

function editCarModelData(name,brand,id){
    $("#editName").val(name);
    $("#editBrand").val(brand);
    $("#editId").val(id);
}

function editCarModel() {
    var json = {};
    var brand = {};
    json.id = $("#editId").val();
    json.name = $("#editName").val();
    json.brand = brand;
    json.brand.id = $("#editBrand").val();
    json.brand.name = $("#editBrand option:selected").text();

    $.ajax({
        type: 'PUT',
        url: '/rest/edit-car-model/',
        //dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(json),
        error: function (jqXHR, exception) {
            console.log(exception);
            loadPage();
        },
        success: function (){
            $('#editCarModelModal').modal('hide');
            $("#editName").val(null);
            $("#editBrand").val(null);
            loadPage();
        }
    });
}