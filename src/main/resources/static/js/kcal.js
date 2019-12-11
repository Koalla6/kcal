function f1() {
    var elements = document.getElementsByClassName('in_products');
   for ( var i = 0; i< elements.length; i++ ){
       elements[i].style.display = "block";
   }
}

let $products = $('.products');
let $in_products = $('.in_products');

let appendProducts = (kcal) => {
    $products.html(kcal.name);
    $in_products.html(kcal.name);
};

let getDataFromApi = () => {
    $.ajax({
        url: `http://localhost:63342/kcal/static/kcal.html`,
        type: 'get',
        success: (res) => {
            console.log(res);
            pages = res.totalPages;
            appendProducts(res.data);
            //addActionOnToCartButtons();
        }
    })
};
$.ajax({
    type: 'get',
    success: (res) => {

    },
    error: console.log
})
