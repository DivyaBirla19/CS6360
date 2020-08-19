let contact_id = $("input[name*='contact_id']")
contact_id.attr("readonly","readonly");


$(".btnedit").click( e =>{
    let textvalues = displayData(e);

    ;
   let first_name = $("input[name*='first_name']");
    let middle_name = $("input[name*='middle_name']");
    let last_name = $("input[name*='last_name']");
    let birth_date = $("input[name*='birth_date']");
    let anni_date = $("input[name*='anni_date']");
    let home_phone = $("input[name*='home_phone']");
    let cell_phone = $("input[name*='cell_phone']");
    let home_address = $("input[name*='home_address']");
    let home_city = $("input[name*='home_city']");
    let home_state = $("input[name*='home_state']");
    let home_zip = $("input[name*='home_zip']");
    let work_phone = $("input[name*='work_phone']");
    let work_address = $("input[name*='work_address']");
    let work_city = $("input[name*='work_city']");
    let work_state = $("input[name*='work_state']");
    let work_zip = $("input[name*='work_zip']");
    contact_id.val(textvalues[0]);
    first_name.val(textvalues[1]);
    middle_name.val(textvalues[2]);
    last_name.val(textvalues[3]);
    birth_date.val(textvalues[4]);
    anni_date.val(textvalues[5]);
    home_phone.val(textvalues[6]);
    cell_phone.val(textvalues[7]);
    home_address.val(textvalues[8]);
    home_city.val(textvalues[9]);
    home_state.val(textvalues[10]);
    home_zip.val(textvalues[11]);
    work_phone.val(textvalues[12]);
    work_address.val(textvalues[13]);
    work_city.val(textvalues[14]);
    work_state.val(textvalues[15]);
    work_zip.val(textvalues[16]);

});


function displayData(e) {
    let id = 0;
    const td = $("#tbody tr td");
    let textvalues = [];

    for (const value of td){
        if(value.dataset.id == e.target.dataset.id){
           textvalues[id++] = value.textContent;
        }
    }
    return textvalues;

}