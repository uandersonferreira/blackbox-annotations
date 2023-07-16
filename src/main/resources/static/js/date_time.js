const  data = document.getElementById('dataFormatada');
const  hora = document.getElementById('horasFormatada');

const relogio = setInterval(function time(){
    let dateToday = new Date();

    let horaAtual = dateToday.getHours();
    let minutoAtual = dateToday.getMinutes();
    let segundoAtual = dateToday.getSeconds();

    let diaAtual = dateToday.getDate();
    let mesAtual = dateToday.getMonth();
    let anoAtual = dateToday.getFullYear();

    if(horaAtual < 10) horaAtual = '0' + horaAtual;
    if(minutoAtual < 10) minutoAtual = '0' + minutoAtual;
    if(segundoAtual < 10) segundoAtual = '0' + segundoAtual;

    if(diaAtual < 10) diaAtual = '0' + diaAtual;
    if(mesAtual < 10) mesAtual = '0' + mesAtual;

    const dataFormatada = `${diaAtual}/${mesAtual}/${anoAtual}`;
    const horaFormatada = `${horaAtual}:${minutoAtual}:${segundoAtual}`;

    data.textContent = dataFormatada;
    hora.textContent = horaFormatada;

});
