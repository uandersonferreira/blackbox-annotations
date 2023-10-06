const listItems = document.querySelectorAll(".sidebar-list li");

listItems.forEach((item) => {
    item.addEventListener("click", () => {
        let isActive = item.classList.contains("active");

        listItems.forEach((el) => {
            el.classList.remove("active");
        });

        if (isActive) item.classList.remove("active");
        else item.classList.add("active");
    });
});

const toggleSidebar = document.querySelector(".toggle-sidebar");
const logo = document.querySelector(".logo-box");
const sidebar = document.querySelector(".sidebar");

toggleSidebar.addEventListener("click", () => {
    sidebar.classList.toggle("close");
});

logo.addEventListener("click", () => {
    sidebar.classList.toggle("close");
});



// SELECTION OPTIONS CARGOS CADASTRO FUNCIÓNÁRIO
const selectCargo = document.getElementById("cargo");
const selectedOptionsList = document.getElementById("selected-options-list");

selectCargo.addEventListener("change", () => {
    const selectedOptions = Array.from(selectCargo.selectedOptions);
    selectedOptionsList.innerHTML = "";

    if (selectedOptions.length === 0) {
        selectedOptionsList.innerHTML = "Nenhuma opção selecionada.";
    } else {
        selectedOptions.forEach((option) => {
            const listItem = document.createElement("li");
            listItem.textContent = option.textContent;
            selectedOptionsList.appendChild(listItem);
        });
    }
});




//-----------------------
$(function () {
    $('[data-toggle="popover"]').popover();
});

$(document).ready(function () {
    $(".navbar-toggle").click(function () {
        $(".sidebar").toggleClass("sidebar-open");
    })

});