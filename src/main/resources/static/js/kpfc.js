//let Result;
//var Result;
var Result;

function func1() {

    var H = parseInt(parameters.height.value);
    var W = parseInt(parameters.weight.value);
    var Ag = parseInt(parameters.age.value);
    var R = 0;


    var male = document.getElementById('male');
    var female = document.getElementById('female');

    var min_12 = document.getElementById('min_1.2');
    var min_137 = document.getElementById('min_1.37');
    var norm_145 = document.getElementById('norm_1.45');
    var int_155 = document.getElementById('int_1.55');
    var int_165 = document.getElementById('int_1.65');
    var int_1725 = document.getElementById('int_1.725');
    var prof_19 = document.getElementById('prof_1.9');

    R = (10 * W) + (6.25 * H) - (5 * Ag);
    if (male.type === "radio" && male.checked){
        R += 5;
        if (min_12.type === "radio" && min_12.checked) {
            R *= 1.2;
        }
        else if (min_137.type === "radio" && min_137.checked){
            R *= 1.37;
        }
        else if (norm_145.type === "radio" && norm_145.checked){
            R *= 1.45;
        }
        else if (int_155.type === "radio" && int_155.checked){
            R *= 1.55;
        }
        else if (int_165.type === "radio" && int_165.checked){
            R *= 1.65;
        }
        else if (int_1725.type === "radio" && int_1725.checked){
            R *= 1.725;
        }
        else if (prof_19.type === "radio" && prof_19.checked){
            R *= 1.9;
        }
    }
    else if (female.type === "radio" && female.checked)
    {
        R -= 161;
        if (min_12.type === "radio" && min_12.checked) {
            R *= 1.2;
        }
        else if (min_137.type === "radio" && min_137.checked){
            R *= 1.37;
        }
        else if (norm_145.type === "radio" && norm_145.checked){
            R *= 1.45;
        }
        else if (int_155.type === "radio" && int_155.checked){
            R *= 1.55;
        }
        else if (int_165.type === "radio" && int_165.checked){
            R *= 1.65;
        }
        else if (int_1725.type === "radio" && int_1725.checked){
            R *= 1.725;
        }
        else if (prof_19.type === "radio" && prof_19.checked){
            R *= 1.9;
        }
    }
    //result.value = R.toFixed(0);
    document.getElementById('result').innerHTML = R.toFixed(0) + ' ккал';

     if (R > 0){
        document.getElementById('div').style.display = "block";
        document.getElementById('oneMore').style.display = "none";
    }
    else {
        document.getElementById('oneMore').innerHTML = 'Оновіть сторінку та введіть параметри ще раз';
    }
    Result = R;
    //console.log(Result);
    //f(R);
    /*console.log(R);
    console.log(Result);*/
    //return Result;
    return Result;
    //console.log(Result);
}

function func3() {
    document.getElementById('result').style.display = "none";
    document.getElementById('div').style.display = "none";
    document.getElementById('button').style.display = "none";

    if (document.getElementById('form_result').style.display === "none") {
        document.getElementById('form_result').style.display = "block";
    }
    func2();
}


function func2(){
    //console.log(Result);
    let R_kcal = Result;
    //console.log(R_kcal);
    var FN;
    var PN;
    var CN;
    document.getElementById('result2').innerHTML = R_kcal.toFixed(0) + ' ккал';
    FN = 0.2 * R_kcal;
    PN = 0.3 * R_kcal;
    CN = 0.5 * R_kcal;
    document.getElementById('fats_n').innerHTML = FN.toFixed(0) + ' ккал    ';
    document.getElementById('prot_n').innerHTML = PN.toFixed(0) + ' ккал    ';
    document.getElementById('carb_n').innerHTML = CN.toFixed(0) + ' ккал    ';

    var R_kcal_lose;
    R_kcal_lose = 0.8 * R_kcal;
    document.getElementById('result2_lose').innerHTML = R_kcal_lose.toFixed(0) + ' ккал';
    var FL = 0.2 * R_kcal_lose;
    var PL = 0.3 * R_kcal_lose;
    var CL = 0.5 * R_kcal_lose;
    document.getElementById('fats_l').innerHTML = FL.toFixed(0) + ' ккал    ';
    document.getElementById('prot_l').innerHTML = PL.toFixed(0) + ' ккал    ';
    document.getElementById('carb_l').innerHTML = CL.toFixed(0) + ' ккал    ';

    var R_kcal_over;
    R_kcal_over = 1.2 * R_kcal;
    document.getElementById('result2_over').innerHTML = R_kcal_over.toFixed(0) + ' ккал';
    var FO = 0.2 * R_kcal_over;
    var PO = 0.3 * R_kcal_over;
    var CO = 0.5 * R_kcal_over;
    document.getElementById('fats_o').innerHTML = FO.toFixed(0) + ' ккал    ';
    document.getElementById('prot_o').innerHTML = PO.toFixed(0) + ' ккал    ';
    document.getElementById('carb_o').innerHTML = CO.toFixed(0) + ' ккал    ';
}

//console.log(Result);

//console.log("numberUsers (getUser1) = "+ func1());
//Result=R;
//func1();
//console.log(Result);
//console.log(R);

/*function result(res) {
Result = res;
    //incrementUntil(Result)
    console.log(Result);
    //removeEventListener(Result);
return res;
}*/

//Result=result(Result);

